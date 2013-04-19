package com.yingyonghui.market;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class ReplyCommentActivity$2 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    this.this$0.dismissDialog(200);
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      if (((Boolean)paramMessage.obj).booleanValue())
      {
        GlobalUtil.longToast(this.this$0, 2131296290);
        Intent localIntent = new Intent();
        String str = ReplyCommentActivity.access$1(this.this$0).getText().toString().trim();
        localIntent.putExtra("comment", str);
        this.this$0.setResult(-1, localIntent);
        this.this$0.finish();
        continue;
      }
      GlobalUtil.longToast(this.this$0, 2131296287);
      continue;
      try
      {
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.ReplyCommentActivity.2
 * JD-Core Version:    0.6.0
 */