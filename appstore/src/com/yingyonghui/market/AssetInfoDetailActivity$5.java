package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class AssetInfoDetailActivity$5
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.val$request.getStatus() == 196610)
    {
      Message localMessage1 = Message.obtain(AssetInfoDetailActivity.access$9(this.this$0), 2, paramObject);
      AssetInfoDetailActivity.access$9(this.this$0).sendMessage(localMessage1);
    }
    while (true)
    {
      return;
      if (this.val$request.getStatus() == 196613)
      {
        Message localMessage2 = Message.obtain(AssetInfoDetailActivity.access$9(this.this$0), 1, paramObject);
        AssetInfoDetailActivity.access$9(this.this$0).sendMessage(localMessage2);
        continue;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoDetailActivity.5
 * JD-Core Version:    0.6.0
 */