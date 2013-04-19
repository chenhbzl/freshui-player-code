package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class FilteredCategoryListActivity$6
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (FilteredCategoryListActivity.access$5(this.this$0) != null)
    {
      IMarketService localIMarketService = FilteredCategoryListActivity.access$8(this.this$0);
      Request localRequest = FilteredCategoryListActivity.access$5(this.this$0);
      localIMarketService.getAppList(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredCategoryListActivity.6
 * JD-Core Version:    0.6.0
 */