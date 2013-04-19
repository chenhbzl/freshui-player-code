package com.yingyonghui.market.online;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.yingyonghui.market.AssetInfoActivity;
import com.yingyonghui.market.FileManager;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.util.PackageInstallInfo;
import java.io.File;

class UpdateService$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    if ("download_recommend_app".equals(str1))
      if (UpdateService.access$0(this.this$0) != null)
      {
        Asset localAsset1 = UpdateService.access$0(this.this$0);
        UpdateService localUpdateService1 = this.this$0;
        String str2 = UpdateService.access$0(this.this$0).pkgName;
        int i = UpdateService.access$0(this.this$0).versionCode;
        int j = UpdateService.access$0(this.this$0)._id;
        int k = PackageInstallInfo.getPackageInstalledStatus(localUpdateService1, str2, i, j);
        localAsset1.installed = k;
        if (UpdateService.access$0(this.this$0).installed != 1)
          break label123;
        Toast.makeText(this.this$0, 2131296402, 0).show();
      }
    while (true)
    {
      return;
      label123: UpdateService localUpdateService2 = this.this$0;
      Asset localAsset2 = UpdateService.access$0(this.this$0);
      File localFile = FileManager.getFile(localUpdateService2, localAsset2);
      if (localFile != null)
      {
        UpdateService.access$1(this.this$0, localFile);
        continue;
      }
      DownloadService localDownloadService = DownloadService.getInstance(this.this$0);
      int m = UpdateService.access$0(this.this$0)._id;
      if (localDownloadService.isTaskRunning(m))
      {
        Toast.makeText(this.this$0, 2131296351, 0).show();
        continue;
      }
      UpdateService.access$2(this.this$0);
      Toast.makeText(this.this$0, 2131296413, 0).show();
      continue;
      String str3 = paramIntent.getAction();
      if ("widget_asset_info".equals(str3))
      {
        Intent localIntent = new Intent();
        UpdateService localUpdateService3 = this.this$0;
        String str4 = AssetInfoActivity.class.getName();
        localIntent.setClassName(localUpdateService3, str4);
        localIntent.addFlags(268435456);
        int n = UpdateService.access$0(this.this$0)._id;
        localIntent.putExtra("_id", n);
        String str5 = UpdateService.access$0(this.this$0).title;
        localIntent.putExtra("title", str5);
        String str6 = UpdateService.access$0(this.this$0).author;
        localIntent.putExtra("author", str6);
        float f1 = UpdateService.access$0(this.this$0).rating;
        localIntent.putExtra("rating", f1);
        float f2 = UpdateService.access$0(this.this$0).price;
        localIntent.putExtra("price", f2);
        int i1 = UpdateService.access$0(this.this$0).installed;
        localIntent.putExtra("installed", i1);
        String str7 = UpdateService.access$0(this.this$0).pkgName;
        localIntent.putExtra("pkgName", str7);
        int i2 = UpdateService.access$0(this.this$0).size;
        localIntent.putExtra("size", i2);
        int i3 = UpdateService.access$0(this.this$0).versionCode;
        localIntent.putExtra("versionCode", i3);
        localIntent.putExtra("from", "Widget");
        this.this$0.startActivity(localIntent);
        continue;
      }
      String str8 = paramIntent.getAction();
      if ("android.intent.action.PACKAGE_ADDED".equals(str8))
      {
        if (UpdateService.access$0(this.this$0) == null)
          continue;
        String str9 = paramIntent.getData().getEncodedSchemeSpecificPart();
        String str10 = UpdateService.access$0(this.this$0).pkgName;
        if (!str9.equals(str10))
          continue;
        UpdateService.access$3(this.this$0, 3);
        continue;
      }
      String str11 = paramIntent.getAction();
      if ((!"android.intent.action.PACKAGE_REMOVED".equals(str11)) || (UpdateService.access$0(this.this$0) == null))
        continue;
      String str12 = paramIntent.getData().getEncodedSchemeSpecificPart();
      String str13 = UpdateService.access$0(this.this$0).pkgName;
      if (!str12.equals(str13))
        continue;
      UpdateService.access$3(this.this$0, 0);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.UpdateService.1
 * JD-Core Version:    0.6.0
 */