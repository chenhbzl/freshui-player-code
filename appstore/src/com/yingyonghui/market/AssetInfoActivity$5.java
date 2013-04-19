package com.yingyonghui.market;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import dalvik.annotation.EnclosingMethod;
import java.io.File;

@EnclosingMethod
class AssetInfoActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent1;
    if (AssetInfoActivity.access$6(this.this$0) > 0)
    {
      localIntent1 = new Intent("android.intent.action.SEND");
      String str1 = this.this$0.getResources().getString(2131296492);
      Object[] arrayOfObject = new Object[2];
      String str2 = AssetInfoActivity.access$7(this.this$0);
      arrayOfObject[0] = str2;
      Integer localInteger = Integer.valueOf(AssetInfoActivity.access$6(this.this$0));
      arrayOfObject[1] = localInteger;
      String str3 = String.format(str1, arrayOfObject);
      AssetInfoActivity localAssetInfoActivity1 = this.this$0;
      int i = AssetInfoActivity.access$6(this.this$0);
      File localFile = FileManager.loopupScreenshot(localAssetInfoActivity1, i);
      localIntent1.putExtra("sms_body", str3);
      if (localFile != null)
      {
        Uri localUri = Uri.fromFile(localFile);
        localIntent1.putExtra("android.intent.extra.STREAM", localUri);
      }
      localIntent1.putExtra("android.intent.extra.SUBJECT", "Share");
      localIntent1.putExtra("android.intent.extra.TEXT", str3);
      localIntent1.setType("text/plain");
    }
    try
    {
      AssetInfoActivity localAssetInfoActivity2 = this.this$0;
      Intent localIntent2 = Intent.createChooser(localIntent1, "share");
      localAssetInfoActivity2.startActivity(localIntent2);
      label180: return;
    }
    catch (Throwable localThrowable)
    {
      break label180;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoActivity.5
 * JD-Core Version:    0.6.0
 */