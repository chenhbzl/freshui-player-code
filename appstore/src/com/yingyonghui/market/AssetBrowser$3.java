package com.yingyonghui.market;

import android.os.Handler;
import com.yingyonghui.market.model.TestUser;
import dalvik.annotation.EnclosingMethod;
import java.util.Observable;
import java.util.Observer;

@EnclosingMethod
class AssetBrowser$3
  implements Observer
{
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof TestUser)) && (((TestUser)paramObject).mIsTestUser))
      AssetBrowser.access$1(this.this$0).sendEmptyMessage(5);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser.3
 * JD-Core Version:    0.6.0
 */