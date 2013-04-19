package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class FilteredAppListActivity$14
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (FilteredAppListActivity.access$24(this.this$0) != null)
    {
      IMarketService localIMarketService = FilteredAppListActivity.access$23(this.this$0);
      Request localRequest = FilteredAppListActivity.access$24(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListActivity.14
 * JD-Core Version:    0.6.0
 */