#ifndef VIDEO_H_INCLUDED
#define VIDEO_H_INCLUDED


extern uint8_t *video_outbuf;
extern int video_outbuf_size;
extern AVFrame *picture, *tmp_picture;


AVStream *add_video_stream(AVFormatContext *fmtCtx, AVCodecContext *cdcCtx);

void open_video(AVFormatContext *pOutputFmtCtx, AVStream *st);

void write_video_frame(AVFormatContext *pOutputFmtCtx, AVStream *st, AVFrame *fr);


#endif // VIDEO_H_INCLUDED
