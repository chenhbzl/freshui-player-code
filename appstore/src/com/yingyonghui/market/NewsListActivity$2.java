package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class NewsListActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    IMarketService localIMarketService = NewsListActivity.access$6(this.this$0);
    Request localRequest = NewsListActivity.access$7(this.this$0);
    localIMarketService.getAppList(localRequest);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsListActivity.2
 * JD-Core Version:    0.6.0
 */