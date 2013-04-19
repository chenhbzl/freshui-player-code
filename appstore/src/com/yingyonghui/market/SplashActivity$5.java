package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class SplashActivity$5
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.val$requestLog.getStatus() == 196610)
    {
      Message localMessage = Message.obtain(SplashActivity.access$4(this.this$0), 300, paramObject);
      SplashActivity.access$4(this.this$0).sendMessage(localMessage);
    }
    while (true)
    {
      return;
      this.val$requestLog.getStatus();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SplashActivity.5
 * JD-Core Version:    0.6.0
 */