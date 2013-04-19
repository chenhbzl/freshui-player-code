package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class AssetBrowser$4
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null)
    {
      Message localMessage = Message.obtain(AssetBrowser.access$1(this.this$0), 3, paramObject);
      AssetBrowser.access$1(this.this$0).sendMessage(localMessage);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser.4
 * JD-Core Version:    0.6.0
 */