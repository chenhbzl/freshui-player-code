package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class FilteredCategoryListActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((FilteredCategoryListActivity.access$5(this.this$0) != null) && (FilteredCategoryListActivity.access$6(this.this$0) != null))
    {
      FilteredCategoryListActivity.access$7(this.this$0).setVisibility(0);
      FilteredCategoryListActivity.access$6(this.this$0).setVisibility(8);
      IMarketService localIMarketService = FilteredCategoryListActivity.access$8(this.this$0);
      Request localRequest = FilteredCategoryListActivity.access$5(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredCategoryListActivity.2
 * JD-Core Version:    0.6.0
 */