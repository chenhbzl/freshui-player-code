package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class FilteredAppListActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((FilteredAppListActivity.access$24(this.this$0) != null) && (FilteredAppListActivity.access$25(this.this$0) != null))
    {
      FilteredAppListActivity.access$9(this.this$0).setVisibility(0);
      FilteredAppListActivity.access$25(this.this$0).setVisibility(8);
      FilteredAppListActivity.access$5(this.this$0, 0);
      FilteredAppListActivity.access$7(this.this$0, 0);
      FilteredAppListActivity.access$6(this.this$0, -1);
      if ((FilteredAppListActivity.access$25(this.this$0).getFooterViewsCount() == 0) && (FilteredAppListActivity.access$26(this.this$0) != null) && (FilteredAppListActivity.access$0(this.this$0) != 11) && (FilteredAppListActivity.access$0(this.this$0) != 12))
      {
        ListView localListView = FilteredAppListActivity.access$25(this.this$0);
        View localView = FilteredAppListActivity.access$26(this.this$0);
        localListView.addFooterView(localView);
      }
      IMarketService localIMarketService = FilteredAppListActivity.access$23(this.this$0);
      Request localRequest = FilteredAppListActivity.access$24(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListActivity.5
 * JD-Core Version:    0.6.0
 */