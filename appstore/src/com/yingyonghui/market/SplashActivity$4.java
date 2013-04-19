package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class SplashActivity$4
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null)
      SplashActivity.access$5(this.this$0, 1);
    while (true)
    {
      return;
      if (this.val$request.getStatus() == 196613)
      {
        Message localMessage = Message.obtain(SplashActivity.access$4(this.this$0), 400, paramObject);
        SplashActivity.access$4(this.this$0).sendMessage(localMessage);
        continue;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SplashActivity.4
 * JD-Core Version:    0.6.0
 */