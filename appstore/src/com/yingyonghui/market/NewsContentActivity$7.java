package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class NewsContentActivity$7
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject != null)
    {
      Message localMessage1 = Message.obtain(NewsContentActivity.access$14(this.this$0), 2, paramObject);
      NewsContentActivity.access$14(this.this$0).sendMessage(localMessage1);
    }
    while (true)
    {
      return;
      if (this.val$request.getStatus() == 196613)
      {
        Message localMessage2 = Message.obtain(NewsContentActivity.access$14(this.this$0), 4, paramObject);
        NewsContentActivity.access$14(this.this$0).sendMessage(localMessage2);
        continue;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity.7
 * JD-Core Version:    0.6.0
 */