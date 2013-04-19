#include <stdio.h>

#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libswscale/swscale.h>

#include "audio.h"
#include "main.h"


int16_t *samples;
uint8_t *audio_outbuf;
int audio_outbuf_size;
int audio_input_frame_size;


AVStream* add_audio_stream(AVFormatContext *fmtCtx, AVCodecContext *cdcCtx){
    AVCodecContext *c;
    AVStream *st;

    st = av_new_stream(fmtCtx, 1);

    if (!st) {
        fprintf(stderr, "Could not alloc stream\n");
        exit(1);
    }


    c = st->codec;
    c->codec_id = fmtCtx->oformat->audio_codec;
    c->codec_type = CODEC_TYPE_AUDIO;

    /* put sample parameters */

    c->bit_rate = cdcCtx->bit_rate;
    c->sample_rate = cdcCtx->sample_rate;

    c->channels = 1;
    return st;
}


void write_audio_frame(AVFormatContext *oc, AVStream *st) {
    AVCodecContext *c;
    AVPacket pkt;
    av_init_packet(&pkt);

    c = st->codec;

    pkt.size = avcodec_encode_audio(c, audio_outbuf, audio_outbuf_size, samples);

    pkt.pts = av_rescale_q(c->coded_frame->pts, c->time_base, st->time_base);
    pkt.flags |= PKT_FLAG_KEY;
    pkt.stream_index = st->index;
    pkt.data = audio_outbuf;

    /* write the compressed frame in the media file */
    if (av_write_frame(oc, &pkt) != 0) {
        fprintf(stderr, "Error while writing audio frame\n");
        exit(1);
    }
}



void open_audio(AVFormatContext *oc, AVStream *st) {
    AVCodecContext *c;
    AVCodec *codec;

    c = st->codec;

    codec = avcodec_find_encoder(c->codec_id);

    if (!codec) {
        fprintf(stderr, "codec not found\n");
        exit(1);
    }

    /* open it */
    if (avcodec_open(c, codec) < 0) {
        fprintf(stderr, "could not open codec\n");
        exit(1);
    }

    audio_outbuf_size = AVCODEC_MAX_AUDIO_FRAME_SIZE;

    audio_outbuf = av_malloc(audio_outbuf_size);

    /* ugly hack for PCM codecs (will be removed ASAP with new PCM
       support to compute the input frame size in samples */
    if (c->frame_size <= 1) {
        audio_input_frame_size = audio_outbuf_size / c->channels;

        switch(st->codec->codec_id) {
        case CODEC_ID_PCM_S16LE:
        case CODEC_ID_PCM_S16BE:
        case CODEC_ID_PCM_U16LE:
        case CODEC_ID_PCM_U16BE:
            audio_input_frame_size >>= 1;
            break;
        default:
            break;
        }
    }
    else {
        audio_input_frame_size = c->frame_size;
    }

    samples = av_malloc(audio_input_frame_size * 2 * c->channels);
}

