#include <stdio.h>
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
#include <libswscale/swscale.h>

#include "main.h"
#include "audio.h"
#include "video.h"

//#define _Dump_Info

#define FNAME  "1.3gp"
#define AFNAME "1.wav"
#define VFNAME "1.yuv"


#define AUDIO_FORMAT "wav"
//#define AUDIO_FORMAT "libopencore_amrnb"
#define VIDEO_FORMAT "rawvideo"



void fail(char *format, ...) {
    va_list va;
    va_start(va, format);
    vfprintf(stderr, format, va);
    exit(-1);
}

int main() {

    av_register_all();
    AVFormatContext *pFormatCtx;

    if(0 != av_open_input_file(&pFormatCtx, FNAME, NULL, 0, NULL))
        fail("Couldn't open file %s \n", FNAME);

    if(0 > av_find_stream_info(pFormatCtx))
        fail("Couldn't find stream information\n");

#ifdef _Dump_Info
    dump_format(pFormatCtx, 0, FNAME, 0);
#endif

    AVCodecContext *apCodecCtx, *vpCodecCtx;

    int aStream = -1, vStream = -1;

    for(int i = pFormatCtx->nb_streams - 1; i >= 0; i--)
        if(pFormatCtx->streams[i]->codec->codec_type == CODEC_TYPE_VIDEO)
            vStream = i;
        else if(pFormatCtx->streams[i]->codec->codec_type == CODEC_TYPE_AUDIO)
            aStream = i;

    if(-1 == vStream || -1 == aStream)
        fail("Couldn't find a video and an audio stream.\n");

    apCodecCtx = pFormatCtx->streams[aStream]->codec;
    vpCodecCtx = pFormatCtx->streams[vStream]->codec;

    AVCodec *apCodec, *vpCodec;

    if(NULL == (apCodec = avcodec_find_decoder(apCodecCtx->codec_id)))
        fail("Unfupported audio codec %d\n", vpCodecCtx->codec_name);

    if(NULL == (vpCodec = avcodec_find_decoder(vpCodecCtx->codec_id)))
        fail("Unsupported video codec %d\n", vpCodecCtx->codec_name);

    if(avcodec_open(apCodecCtx, apCodec) < 0)
        fail("Couldn't open audio codec %d\n", apCodec->name);

    if(avcodec_open(vpCodecCtx, vpCodec) < 0)
        fail("Couldn't open video codec %d\n", vpCodec->name);

    AVFrame *vpFrame = avcodec_alloc_frame();

    /**Open Output-Video**/

    AVFormatContext *pOutputVideoFmtCtx = avformat_alloc_context();
    AVFormatContext *pOutputAudioFmtCtx = avformat_alloc_context();

    if(!pOutputVideoFmtCtx)
        fail("Couldn't allocate memory for format context.\n");

    if(NULL == (pOutputVideoFmtCtx->oformat =
                    guess_format(VIDEO_FORMAT, NULL, NULL)))
        fail("Couldn't find video format.\n");

    AVStream *video_st = add_video_stream(pOutputVideoFmtCtx, vpCodecCtx);

	if(NULL == (pOutputAudioFmtCtx->oformat =
				guess_format(AUDIO_FORMAT, NULL, NULL)))
        fail("Could not find audio format\n");

    AVStream *audio_st = add_audio_stream(pOutputAudioFmtCtx, apCodecCtx);

    if(av_set_parameters(pOutputVideoFmtCtx, NULL) < 0)
        fail("Invalid output video format parameters.\n");

    open_video(pOutputVideoFmtCtx, video_st);
    open_audio(pOutputAudioFmtCtx, audio_st);

    if(url_fopen(&pOutputVideoFmtCtx->pb, VFNAME, URL_WRONLY) < 0)
        fail("Couldn't open file %s.\n", VFNAME);

    av_write_header(pOutputVideoFmtCtx);

    if (url_fopen(&pOutputAudioFmtCtx->pb, AFNAME, URL_WRONLY) < 0)
        fail("Couldn't open file %s.\n", AFNAME);

    av_write_header(pOutputAudioFmtCtx);

    /**_Open Output-Video**/

    /**Write Frame**/
    picture = av_malloc(sizeof(AVFrame));

    int frameFinished;
    AVPacket packet;

    while(av_read_frame(pFormatCtx, &packet) >= 0) {
        if(packet.stream_index == vStream) {
            avcodec_decode_video2(vpCodecCtx, vpFrame,
                                  &frameFinished, &packet);

            if(frameFinished) {
                write_video_frame(pOutputVideoFmtCtx, video_st, vpFrame);
            }
        }
        else if(packet.stream_index == aStream) {
            //This is an ugly hack for the ugly hacking
            //of our "audio_outbuf_size"
            audio_outbuf_size =  AVCODEC_MAX_AUDIO_FRAME_SIZE;
            int result = avcodec_decode_audio3(apCodecCtx, samples,
                                               &audio_outbuf_size,
                                               &packet);

            if (result > 0) {
                write_audio_frame(pOutputAudioFmtCtx, audio_st);
            }
            else if (result < 0) {
                fail("Error while decode audio\n");
            }

        }
    }

    /**_Write Frame**/


    av_write_trailer(pOutputVideoFmtCtx);
    av_write_trailer(pOutputAudioFmtCtx);

    av_free(vpFrame);
    vpFrame = NULL;

    avcodec_close(apCodecCtx);
    avcodec_close(vpCodecCtx);
    apCodecCtx = NULL;
    vpCodecCtx = NULL;

    av_close_input_file(pFormatCtx);
    pFormatCtx = NULL;

	url_fclose(pOutputAudioFmtCtx->pb);
	url_fclose(pOutputVideoFmtCtx->pb);

	av_free(pOutputAudioFmtCtx);
	av_free(pOutputVideoFmtCtx);

    printf("Everything is done fine and fine.\nThx.\n");
    return 0;
}
