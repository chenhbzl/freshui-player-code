package com.yingyonghui.market;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

class AssetInfoActivity$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (AssetInfoActivity.access$0(this.this$0) == null);
    while (true)
    {
      return;
      String str2;
      try
      {
        String str1 = paramIntent.getAction();
        if (!"android.intent.action.PACKAGE_REMOVED".equals(str1))
          break label86;
        str2 = paramIntent.getData().getSchemeSpecificPart();
        if ((AssetInfoActivity.access$0(this.this$0) == null) || (AssetInfoActivity.access$1(this.this$0) == null) || (!AssetInfoActivity.access$1(this.this$0).equals(str2)))
          continue;
        AssetInfoActivity.access$2(this.this$0, 0);
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
      continue;
      label86: String str3 = paramIntent.getAction();
      if ("android.intent.action.PACKAGE_ADDED".equals(str3))
      {
        str2 = paramIntent.getData().getSchemeSpecificPart();
        if ((AssetInfoActivity.access$0(this.this$0) == null) || (AssetInfoActivity.access$1(this.this$0) == null) || (!AssetInfoActivity.access$1(this.this$0).equals(str2)))
          continue;
        AssetInfoActivity.access$2(this.this$0, 2);
        continue;
      }
      String str4 = paramIntent.getAction();
      if (!"request_comment_detail".equals(str4))
        continue;
      AssetInfoActivity.access$3(this.this$0);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoActivity.1
 * JD-Core Version:    0.6.0
 */