package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SearchPageActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (SearchPageActivity.access$1(this.this$0) != null)
    {
      SearchPageActivity.access$2(this.this$0).setVisibility(8);
      SearchPageActivity.access$3(this.this$0).setVisibility(0);
      SearchPageActivity.access$4(this.this$0).setVisibility(8);
      IMarketService localIMarketService = SearchPageActivity.access$5(this.this$0);
      Request localRequest = SearchPageActivity.access$1(this.this$0);
      localIMarketService.getTopKeywords(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchPageActivity.2
 * JD-Core Version:    0.6.0
 */