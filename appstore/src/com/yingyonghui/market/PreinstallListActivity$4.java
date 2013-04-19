package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class PreinstallListActivity$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if ((PreinstallListActivity.access$10(this.this$0) != null) && (PreinstallListActivity.access$11(this.this$0) != null))
    {
      PreinstallListActivity.access$12(this.this$0).setVisibility(0);
      PreinstallListActivity.access$11(this.this$0).setVisibility(8);
      IMarketService localIMarketService = PreinstallListActivity.access$13(this.this$0);
      Request localRequest = PreinstallListActivity.access$10(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.PreinstallListActivity.4
 * JD-Core Version:    0.6.0
 */