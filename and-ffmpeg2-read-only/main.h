#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

extern uint8_t *video_outbuf;
extern int video_outbuf_size;
extern AVFrame *picture, *tmp_picture;

void fail(char *format, ...);

#endif // MAIN_H_INCLUDED
