#include <stdio.h>
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libswscale/swscale.h>

#include "video.h"
#include "main.h"

uint8_t *video_outbuf;
int video_outbuf_size;
AVFrame *picture, *tmp_picture;

AVStream* add_video_stream(AVFormatContext *fmtCtx, AVCodecContext *cdcCtx){
    AVCodecContext *c;
    AVStream *st;

    st = av_new_stream(fmtCtx, 0);

    if (!st)
        fail("Could not alloc stream\n");

    c = st->codec;
    c->codec_id = fmtCtx->oformat->video_codec;
    c->codec_type = CODEC_TYPE_VIDEO;

    c->bit_rate = cdcCtx->bit_rate;
    c->width = cdcCtx->width;
    c->height = cdcCtx->height;
    c->time_base = cdcCtx->time_base;
    c->gop_size = 12;
    c->pix_fmt = PIX_FMT_YUV420P;
    return st;
}

static AVFrame *alloc_picture(int pix_fmt, int width, int height) {
    AVFrame *picture;
    uint8_t *picture_buf;
    int size;

    picture = avcodec_alloc_frame();

    if (!picture) return NULL;

    size = avpicture_get_size(pix_fmt, width, height);
    picture_buf = av_malloc(size);

    if (!picture_buf) {
        av_free(picture);
        return NULL;
    }

    avpicture_fill((AVPicture *)picture, picture_buf,
                   pix_fmt, width, height);
    return picture;
}

void open_video(AVFormatContext *oc, AVStream *st) {
    AVCodec *codec;
    AVCodecContext *c;

    c = st->codec;

    codec = avcodec_find_encoder(c->codec_id);

    if (!codec) fail("codec not found.\n");

    if (avcodec_open(c, codec) < 0) fail("could not open codec.\n");

    video_outbuf = NULL;

    if (!(oc->oformat->flags & AVFMT_RAWPICTURE)) {
        video_outbuf_size = 200000;
        video_outbuf = av_malloc(video_outbuf_size);
    }

    picture = alloc_picture(c->pix_fmt, c->width, c->height);

    if (!picture) fail("Could not allocate picture.\n");

    tmp_picture = NULL;

    if (c->pix_fmt != PIX_FMT_YUV420P) {
        tmp_picture = alloc_picture(PIX_FMT_YUV420P, c->width, c->height);

        if (!tmp_picture) {
            fprintf(stderr, "Could not allocate temporary picture\n");
            exit(1);
        }
    }
}

void write_video_frame(AVFormatContext *oc, AVStream *st, AVFrame *fr) {
    int out_size, ret;
    AVCodecContext *c = st->codec;

    memcpy(picture, fr, sizeof(AVFrame));

    if (oc->oformat->flags & AVFMT_RAWPICTURE) {
        /* raw video case. The API will change slightly in the near
           future for that */
        AVPacket pkt;
        av_init_packet(&pkt);

        pkt.flags |= PKT_FLAG_KEY;
        pkt.stream_index = st->index;
        pkt.data = (uint8_t *)picture;
        pkt.size = sizeof(AVPicture);

        ret = av_write_frame(oc, &pkt);
    }
    else {
        /* encode the image */
        out_size = avcodec_encode_video(c, video_outbuf, video_outbuf_size, picture);

        /* if zero size, it means the image was buffered */
        if (out_size > 0) {
            AVPacket pkt;
            av_init_packet(&pkt);

            pkt.pts = av_rescale_q(c->coded_frame->pts, c->time_base, st->time_base);

            if(c->coded_frame->key_frame)
                pkt.flags |= PKT_FLAG_KEY;

            pkt.stream_index = st->index;
            pkt.data = video_outbuf;
            pkt.size = out_size;

            ret = av_write_frame(oc, &pkt);
        }
        else {
            ret = 0;
        }
    }

    if (ret != 0)
        fail("Error while writing video frame\n");
}
