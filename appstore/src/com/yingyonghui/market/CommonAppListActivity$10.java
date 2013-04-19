package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class CommonAppListActivity$10
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (this.val$request.getStatus() == 196610)
    {
      Message localMessage1 = Message.obtain(CommonAppListActivity.access$18(this.this$0), 4, paramObject);
      CommonAppListActivity.access$18(this.this$0).sendMessage(localMessage1);
    }
    while (true)
    {
      return;
      if (this.val$request.getStatus() == 196613)
      {
        Message localMessage2 = Message.obtain(CommonAppListActivity.access$18(this.this$0), 3, paramObject);
        CommonAppListActivity.access$18(this.this$0).sendMessage(localMessage2);
        continue;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.10
 * JD-Core Version:    0.6.0
 */