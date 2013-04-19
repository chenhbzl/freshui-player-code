package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class AssetBrowser$5 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      if (!((Boolean)paramMessage.obj).booleanValue())
        continue;
      AssetBrowser.access$2(this.this$0);
      continue;
      try
      {
        this.this$0.showDialog(200);
      }
      catch (Exception localException1)
      {
      }
      continue;
      AssetBrowser localAssetBrowser1 = this.this$0;
      AssetDetail localAssetDetail1 = (AssetDetail)paramMessage.obj;
      AssetBrowser.access$3(localAssetBrowser1, localAssetDetail1);
      if (AssetBrowser.access$4(this.this$0) == null)
        continue;
      AssetDetail localAssetDetail2 = AssetBrowser.access$4(this.this$0);
      AssetBrowser localAssetBrowser2 = this.this$0;
      String str1 = AssetBrowser.access$4(this.this$0).pkgName;
      int i = AssetBrowser.access$4(this.this$0).versionCode;
      int j = AssetBrowser.access$4(this.this$0)._id;
      int k = PackageInstallInfo.getPackageInstalledStatus(localAssetBrowser2, str1, i, j);
      localAssetDetail2.installed = k;
      if (AssetBrowser.access$4(this.this$0).installed != 2)
        continue;
      try
      {
        this.this$0.showDialog(300);
      }
      catch (Exception localException2)
      {
      }
      continue;
      try
      {
        int m = ((Integer)paramMessage.obj).intValue();
        if (m <= 0)
          break label287;
        TextView localTextView = AssetBrowser.access$5(this.this$0);
        String str2 = String.valueOf(m);
        String str3 = str2;
        localTextView.setText(str3);
        AssetBrowser.access$5(this.this$0).setVisibility(0);
      }
      catch (Exception localException3)
      {
      }
      continue;
      label287: AssetBrowser.access$5(this.this$0).setText("");
      AssetBrowser.access$5(this.this$0).setVisibility(8);
      continue;
      AssetBrowser.access$6(this.this$0);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser.5
 * JD-Core Version:    0.6.0
 */