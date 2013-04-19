package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class UpdateAppListActivity$7
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null)
    {
      Message localMessage1 = Message.obtain(UpdateAppListActivity.access$15(this.this$0), 1, paramObject);
      UpdateAppListActivity.access$15(this.this$0).sendMessage(localMessage1);
    }
    while (true)
    {
      return;
      Message localMessage2 = Message.obtain(UpdateAppListActivity.access$15(this.this$0), 3, paramObject);
      UpdateAppListActivity.access$15(this.this$0).sendMessage(localMessage2);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity.7
 * JD-Core Version:    0.6.0
 */