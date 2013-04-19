package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class CommonAppListActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((CommonAppListActivity.access$10(this.this$0) != null) && (CommonAppListActivity.access$11(this.this$0) != null))
    {
      CommonAppListActivity.access$12(this.this$0).setVisibility(0);
      CommonAppListActivity.access$11(this.this$0).setVisibility(8);
      CommonAppListActivity.access$13(this.this$0, 0);
      CommonAppListActivity.access$14(this.this$0, 0);
      CommonAppListActivity.access$15(this.this$0, 0);
      CommonAppListActivity.access$16(this.this$0, -1);
      if ((CommonAppListActivity.access$11(this.this$0).getFooterViewsCount() == 0) && (CommonAppListActivity.access$17() != null))
      {
        ListView localListView = CommonAppListActivity.access$11(this.this$0);
        View localView = CommonAppListActivity.access$17();
        localListView.addFooterView(localView);
      }
      IMarketService localIMarketService = CommonAppListActivity.access$9(this.this$0);
      Request localRequest = CommonAppListActivity.access$10(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.5
 * JD-Core Version:    0.6.0
 */