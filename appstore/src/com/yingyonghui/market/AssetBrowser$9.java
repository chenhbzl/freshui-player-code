package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class AssetBrowser$9
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (AssetBrowser.access$7(this.this$0) != null)
    {
      IMarketService localIMarketService = AssetBrowser.access$8(this.this$0);
      Request localRequest = AssetBrowser.access$7(this.this$0);
      localIMarketService.loginToServer(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser.9
 * JD-Core Version:    0.6.0
 */