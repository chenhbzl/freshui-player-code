package com.yingyonghui.market;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

class UpdateAppListActivity$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    UpdateAppListActivity.access$0(this.this$0).setVisibility(0);
    UpdateAppListActivity.access$1(this.this$0).setVisibility(8);
    UpdateAppListActivity.access$2(this.this$0).setVisibility(8);
    if ((UpdateAppListActivity.access$2(this.this$0).getFooterViewsCount() == 0) && (UpdateAppListActivity.access$3() != null))
    {
      ListView localListView = UpdateAppListActivity.access$2(this.this$0);
      View localView = UpdateAppListActivity.access$3();
      localListView.addFooterView(localView);
    }
    UpdateAppListActivity.access$4(this.this$0, 0);
    UpdateAppListActivity.access$5(this.this$0, 0);
    UpdateAppListActivity.access$6(this.this$0, -1);
    UpdateAppListActivity.access$7(this.this$0).clear();
    UpdateAppListActivity.access$8(this.this$0, 1);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity.1
 * JD-Core Version:    0.6.0
 */