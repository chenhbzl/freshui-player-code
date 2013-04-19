package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class AssetInfoActivity$7 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      AssetInfoActivity localAssetInfoActivity1 = this.this$0;
      AssetDetail localAssetDetail1 = (AssetDetail)paramMessage.obj;
      AssetInfoActivity.access$9(localAssetInfoActivity1, localAssetDetail1);
      AssetDetail localAssetDetail2 = AssetInfoActivity.access$10(this.this$0);
      AssetInfoActivity localAssetInfoActivity2 = this.this$0;
      String str = AssetInfoActivity.access$10(this.this$0).pkgName;
      int i = AssetInfoActivity.access$10(this.this$0).versionCode;
      int j = AssetInfoActivity.access$10(this.this$0)._id;
      int k = PackageInstallInfo.getPackageInstalledStatus(localAssetInfoActivity2, str, i, j);
      localAssetDetail2.installed = k;
      AssetInfoActivity localAssetInfoActivity3 = this.this$0;
      AssetDetail localAssetDetail3 = AssetInfoActivity.access$10(this.this$0);
      AssetInfoActivity.access$11(localAssetInfoActivity3, localAssetDetail3);
      AssetInfoActivity.access$12(this.this$0);
      AssetInfoActivity.access$13(this.this$0).setVisibility(0);
      AssetInfoActivity.access$14(this.this$0).setVisibility(8);
      continue;
      try
      {
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoActivity.7
 * JD-Core Version:    0.6.0
 */