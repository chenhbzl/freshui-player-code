package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class AssetBrowser$2
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null);
    try
    {
      AssetBrowser localAssetBrowser = this.this$0;
      int i = ((Integer)paramObject).intValue();
      localAssetBrowser.setUpdateAvaliableNum(i);
      while (true)
      {
        label24: return;
        if (this.val$request.getStatus() == 196613)
        {
          Message localMessage = Message.obtain(AssetBrowser.access$1(this.this$0), 2, paramObject);
          AssetBrowser.access$1(this.this$0).sendMessage(localMessage);
          continue;
        }
      }
    }
    catch (Exception localException)
    {
      break label24;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser.2
 * JD-Core Version:    0.6.0
 */