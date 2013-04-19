package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class NewsListActivity$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((NewsListActivity.access$7(this.this$0) != null) && (NewsListActivity.access$8(this.this$0) != null))
    {
      NewsListActivity.access$9(this.this$0).setVisibility(0);
      NewsListActivity.access$8(this.this$0).setVisibility(8);
      IMarketService localIMarketService = NewsListActivity.access$6(this.this$0);
      Request localRequest = NewsListActivity.access$7(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsListActivity.3
 * JD-Core Version:    0.6.0
 */