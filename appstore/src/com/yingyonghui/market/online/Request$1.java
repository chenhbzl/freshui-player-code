package com.yingyonghui.market.online;

import android.os.Handler;
import android.os.Message;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class Request$1
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.this$0.getStatus() == 11)
    {
      Message localMessage1 = Message.obtain(this.val$mHandler, 13, paramObject);
      this.val$mHandler.sendMessage(localMessage1);
    }
    while (true)
    {
      return;
      if (this.this$0.getStatus() == 10)
      {
        Message localMessage2 = Message.obtain(this.val$mHandler, 12, paramObject);
        this.val$mHandler.sendMessage(localMessage2);
        continue;
      }
      if (this.this$0.getStatus() != 14)
        continue;
      Message localMessage3 = Message.obtain(this.val$mHandler, 15, paramObject);
      this.val$mHandler.sendMessage(localMessage3);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.Request.1
 * JD-Core Version:    0.6.0
 */