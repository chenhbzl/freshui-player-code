package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class UpdateAppListActivity$6$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((UpdateAppListActivity.access$14(UpdateAppListActivity.6.access$0(this.this$1)) != null) && (UpdateAppListActivity.access$2(UpdateAppListActivity.6.access$0(this.this$1)) != null))
    {
      UpdateAppListActivity.access$0(UpdateAppListActivity.6.access$0(this.this$1)).setVisibility(0);
      UpdateAppListActivity.access$2(UpdateAppListActivity.6.access$0(this.this$1)).setVisibility(8);
      UpdateAppListActivity.access$4(UpdateAppListActivity.6.access$0(this.this$1), 0);
      UpdateAppListActivity.access$5(UpdateAppListActivity.6.access$0(this.this$1), 0);
      UpdateAppListActivity.access$6(UpdateAppListActivity.6.access$0(this.this$1), -1);
      if ((UpdateAppListActivity.access$2(UpdateAppListActivity.6.access$0(this.this$1)).getFooterViewsCount() == 0) && (UpdateAppListActivity.access$3() != null))
      {
        ListView localListView = UpdateAppListActivity.access$2(UpdateAppListActivity.6.access$0(this.this$1));
        View localView = UpdateAppListActivity.access$3();
        localListView.addFooterView(localView);
      }
      IMarketService localIMarketService = UpdateAppListActivity.access$13(UpdateAppListActivity.6.access$0(this.this$1));
      Request localRequest = UpdateAppListActivity.access$14(UpdateAppListActivity.6.access$0(this.this$1));
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity.6.1
 * JD-Core Version:    0.6.0
 */