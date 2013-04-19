#ifndef AUDIO_H_INCLUDED
#define AUDIO_H_INCLUDED


extern int16_t *samples;
extern uint8_t *audio_outbuf;
extern int audio_outbuf_size;
extern int audio_input_frame_size;

AVStream* add_audio_stream(AVFormatContext *fmtCtx, AVCodecContext *cdcCtx);

void open_audio(AVFormatContext *pOutputFmtCtx, AVStream *st);

void write_audio_frame(AVFormatContext *pOutputFmtCtx, AVStream *st);


#endif // AUDIO_H_INCLUDED
