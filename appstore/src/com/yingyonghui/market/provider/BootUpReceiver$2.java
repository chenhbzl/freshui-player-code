package com.yingyonghui.market.provider;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class BootUpReceiver$2
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.val$requestLog.getStatus() == 196610)
    {
      Message localMessage = Message.obtain(BootUpReceiver.access$0(this.this$0), 100, paramObject);
      BootUpReceiver.access$0(this.this$0).sendMessage(localMessage);
    }
    while (true)
    {
      return;
      this.val$requestLog.getStatus();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.BootUpReceiver.2
 * JD-Core Version:    0.6.0
 */