package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class CommonAppListActivity$12
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (CommonAppListActivity.access$10(this.this$0) != null)
    {
      IMarketService localIMarketService1 = CommonAppListActivity.access$9(this.this$0);
      Request localRequest1 = CommonAppListActivity.access$10(this.this$0);
      localIMarketService1.getAppList(localRequest1);
      IMarketService localIMarketService2 = CommonAppListActivity.access$9(this.this$0);
      Request localRequest2 = CommonAppListActivity.access$37(this.this$0);
      localIMarketService2.getTopFourApp(localRequest2);
      IMarketService localIMarketService3 = CommonAppListActivity.access$9(this.this$0);
      Request localRequest3 = CommonAppListActivity.access$38(this.this$0);
      localIMarketService3.getAppList(localRequest3);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.12
 * JD-Core Version:    0.6.0
 */