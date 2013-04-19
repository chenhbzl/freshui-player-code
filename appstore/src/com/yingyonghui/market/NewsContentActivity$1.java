package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.EnclosingMethod;
import java.io.File;
import java.util.HashMap;

@EnclosingMethod
class NewsContentActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    NewsContentActivity localNewsContentActivity1 = this.this$0;
    String str1 = this.val$mDetail.pkgName;
    int i = this.val$mDetail.versionCode;
    int j = this.val$mDetail._id;
    int k = PackageInstallInfo.getPackageInstalledStatus(localNewsContentActivity1, str1, i, j);
    if (k == 1);
    while (true)
    {
      return;
      NewsContentActivity localNewsContentActivity2 = this.this$0;
      AssetDetail localAssetDetail1 = this.val$mDetail;
      File localFile = FileManager.getFile(localNewsContentActivity2, localAssetDetail1);
      if (localFile != null)
      {
        NewsContentActivity localNewsContentActivity3 = this.this$0;
        int m = this.val$mDetail._id;
        String str2 = this.val$mDetail.title;
        String str3 = this.val$mDetail.pkgName;
        NewsContentActivity.access$0(localNewsContentActivity3, localFile, m, str2, str3);
        continue;
      }
      NewsContentActivity localNewsContentActivity4 = this.this$0;
      int n = this.val$mDetail._id;
      if (!NewsContentActivity.access$1(localNewsContentActivity4, n))
        break;
      Toast.makeText(this.this$0, 2131296351, 1).show();
    }
    if (GlobalUtil.isSystemApp(this.this$0.getPackageManager()))
    {
      NewsContentActivity localNewsContentActivity5 = this.this$0;
      int i1 = this.val$mDetail._id;
      String str4 = this.val$mDetail.title;
      int i2 = this.val$mDetail.size;
      String str5 = this.val$mDetail.pkgName;
      String[] arrayOfString = this.val$mDetail.permissions;
      GlobalUtil.startPermissionsActivity(localNewsContentActivity5, i1, str4, i2, str5, arrayOfString);
      label244: if (k != 2)
        break label374;
    }
    label374: for (String str6 = "update"; ; str6 = "install")
    {
      HashMap localHashMap = NewsContentActivity.access$3(this.this$0);
      Integer localInteger = Integer.valueOf(this.val$mDetail._id);
      localHashMap.put(localInteger, str6);
      NewsContentActivity localNewsContentActivity6 = this.this$0;
      String str7 = String.valueOf(NewsContentActivity.access$4(this.this$0));
      StringBuilder localStringBuilder = new StringBuilder(str7).append(" : ");
      int i3 = this.val$mDetail._id;
      String str8 = i3;
      MobclickAgent.onEvent(localNewsContentActivity6, "news_download", str8);
      break;
      NewsContentActivity localNewsContentActivity7 = this.this$0;
      AssetDetail localAssetDetail2 = this.val$mDetail;
      NewsContentActivity.access$2(localNewsContentActivity7, localAssetDetail2);
      break label244;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity.1
 * JD-Core Version:    0.6.0
 */