package com.yingyonghui.market;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabWidget;
import android.widget.Toast;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.util.PackageInstallInfo;

class CommonAppListActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent;
    if (paramView.getId() == 2131427535)
    {
      localIntent = new Intent();
      if (CommonAppListActivity.access$1(this.this$0)[0].pkgName != "jumptotheme")
      {
        int i = CommonAppListActivity.access$1(this.this$0)[0]._id;
        localIntent.putExtra("_id", i);
        String str1 = CommonAppListActivity.access$1(this.this$0)[0].title;
        localIntent.putExtra("title", str1);
        CommonAppListActivity localCommonAppListActivity1 = this.this$0;
        String str2 = CommonAppListActivity.access$1(this.this$0)[0].pkgName;
        int j = CommonAppListActivity.access$1(this.this$0)[0].versionCode;
        int k = CommonAppListActivity.access$1(this.this$0)[0]._id;
        int m = PackageInstallInfo.getPackageInstalledStatus(localCommonAppListActivity1, str2, j, k);
        localIntent.putExtra("installed", m);
        String str3 = CommonAppListActivity.access$1(this.this$0)[0].pkgName;
        localIntent.putExtra("pkgName", str3);
        int n = CommonAppListActivity.access$1(this.this$0)[0].size;
        localIntent.putExtra("size", n);
        int i1 = CommonAppListActivity.access$1(this.this$0)[0].versionCode;
        localIntent.putExtra("versionCode", i1);
        localIntent.putExtra("from", "TopFour1");
        CommonAppListActivity localCommonAppListActivity2 = this.this$0;
        String str4 = AssetInfoActivity.class.getName();
        localIntent.setClassName(localCommonAppListActivity2, str4);
        if (CommonAppListActivity.access$1(this.this$0)[0]._id > 0)
          this.this$0.startActivity(localIntent);
      }
    }
    while (true)
    {
      return;
      if (CommonAppListActivity.access$1(this.this$0)[0]._id == -1)
      {
        Context localContext1 = CommonAppListActivity.access$2();
        String str5 = CommonAppListActivity.access$2().getResources().getString(2131296496);
        Toast.makeText(localContext1, str5, 1).show();
        continue;
        try
        {
          AssetBrowser.tw_out.getChildAt(2).performClick();
          TabbedTopCategoriesBrowser.tw_out.getChildAt(2).performClick();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
        continue;
        if (paramView.getId() == 2131427536)
        {
          localIntent = new Intent();
          if (CommonAppListActivity.access$1(this.this$0)[1].pkgName != "jumptotheme")
          {
            int i2 = CommonAppListActivity.access$1(this.this$0)[1]._id;
            localIntent.putExtra("_id", i2);
            String str6 = CommonAppListActivity.access$1(this.this$0)[1].title;
            localIntent.putExtra("title", str6);
            CommonAppListActivity localCommonAppListActivity3 = this.this$0;
            String str7 = CommonAppListActivity.access$1(this.this$0)[1].pkgName;
            int i3 = CommonAppListActivity.access$1(this.this$0)[1].versionCode;
            int i4 = CommonAppListActivity.access$1(this.this$0)[1]._id;
            int i5 = PackageInstallInfo.getPackageInstalledStatus(localCommonAppListActivity3, str7, i3, i4);
            localIntent.putExtra("installed", i5);
            String str8 = CommonAppListActivity.access$1(this.this$0)[1].pkgName;
            localIntent.putExtra("pkgName", str8);
            int i6 = CommonAppListActivity.access$1(this.this$0)[1].size;
            localIntent.putExtra("size", i6);
            int i7 = CommonAppListActivity.access$1(this.this$0)[1].versionCode;
            localIntent.putExtra("versionCode", i7);
            localIntent.putExtra("from", "TopFour2");
            CommonAppListActivity localCommonAppListActivity4 = this.this$0;
            String str9 = AssetInfoActivity.class.getName();
            localIntent.setClassName(localCommonAppListActivity4, str9);
            if (CommonAppListActivity.access$1(this.this$0)[1]._id > 0)
            {
              this.this$0.startActivity(localIntent);
              continue;
            }
            if (CommonAppListActivity.access$1(this.this$0)[1]._id != -1)
              continue;
            Context localContext2 = CommonAppListActivity.access$2();
            String str10 = CommonAppListActivity.access$2().getResources().getString(2131296496);
            Toast.makeText(localContext2, str10, 1).show();
            continue;
          }
          try
          {
            AssetBrowser.tw_out.getChildAt(2).performClick();
            TabbedTopCategoriesBrowser.tw_out.getChildAt(2).performClick();
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
          }
          continue;
        }
        if (paramView.getId() == 2131427537)
        {
          localIntent = new Intent();
          if (CommonAppListActivity.access$1(this.this$0)[2].pkgName != "jumptotheme")
          {
            int i8 = CommonAppListActivity.access$1(this.this$0)[2]._id;
            localIntent.putExtra("_id", i8);
            String str11 = CommonAppListActivity.access$1(this.this$0)[2].title;
            localIntent.putExtra("title", str11);
            CommonAppListActivity localCommonAppListActivity5 = this.this$0;
            String str12 = CommonAppListActivity.access$1(this.this$0)[2].pkgName;
            int i9 = CommonAppListActivity.access$1(this.this$0)[2].versionCode;
            int i10 = CommonAppListActivity.access$1(this.this$0)[2]._id;
            int i11 = PackageInstallInfo.getPackageInstalledStatus(localCommonAppListActivity5, str12, i9, i10);
            localIntent.putExtra("installed", i11);
            String str13 = CommonAppListActivity.access$1(this.this$0)[2].pkgName;
            localIntent.putExtra("pkgName", str13);
            int i12 = CommonAppListActivity.access$1(this.this$0)[2].size;
            localIntent.putExtra("size", i12);
            int i13 = CommonAppListActivity.access$1(this.this$0)[2].versionCode;
            localIntent.putExtra("versionCode", i13);
            localIntent.putExtra("from", "TopFour3");
            CommonAppListActivity localCommonAppListActivity6 = this.this$0;
            String str14 = AssetInfoActivity.class.getName();
            localIntent.setClassName(localCommonAppListActivity6, str14);
            if (CommonAppListActivity.access$1(this.this$0)[2]._id > 0)
            {
              this.this$0.startActivity(localIntent);
              continue;
            }
            if (CommonAppListActivity.access$1(this.this$0)[2]._id != -1)
              continue;
            Context localContext3 = CommonAppListActivity.access$2();
            String str15 = CommonAppListActivity.access$2().getResources().getString(2131296496);
            Toast.makeText(localContext3, str15, 1).show();
            continue;
          }
          try
          {
            AssetBrowser.tw_out.getChildAt(2).performClick();
            TabbedTopCategoriesBrowser.tw_out.getChildAt(2).performClick();
          }
          catch (Exception localException3)
          {
            localException3.printStackTrace();
          }
          continue;
        }
        if (paramView.getId() != 2131427538)
          continue;
        localIntent = new Intent();
        if (CommonAppListActivity.access$1(this.this$0)[3].pkgName != "jumptotheme")
        {
          int i14 = CommonAppListActivity.access$1(this.this$0)[3]._id;
          localIntent.putExtra("_id", i14);
          String str16 = CommonAppListActivity.access$1(this.this$0)[3].title;
          localIntent.putExtra("title", str16);
          CommonAppListActivity localCommonAppListActivity7 = this.this$0;
          String str17 = CommonAppListActivity.access$1(this.this$0)[3].pkgName;
          int i15 = CommonAppListActivity.access$1(this.this$0)[3].versionCode;
          int i16 = CommonAppListActivity.access$1(this.this$0)[3]._id;
          int i17 = PackageInstallInfo.getPackageInstalledStatus(localCommonAppListActivity7, str17, i15, i16);
          localIntent.putExtra("installed", i17);
          String str18 = CommonAppListActivity.access$1(this.this$0)[3].pkgName;
          localIntent.putExtra("pkgName", str18);
          int i18 = CommonAppListActivity.access$1(this.this$0)[3].size;
          localIntent.putExtra("size", i18);
          int i19 = CommonAppListActivity.access$1(this.this$0)[3].versionCode;
          localIntent.putExtra("versionCode", i19);
          localIntent.putExtra("from", "TopFour4");
          CommonAppListActivity localCommonAppListActivity8 = this.this$0;
          String str19 = AssetInfoActivity.class.getName();
          localIntent.setClassName(localCommonAppListActivity8, str19);
          if (CommonAppListActivity.access$1(this.this$0)[3]._id > 0)
          {
            this.this$0.startActivity(localIntent);
            continue;
          }
          if (CommonAppListActivity.access$1(this.this$0)[3]._id != -1)
            continue;
          Context localContext4 = CommonAppListActivity.access$2();
          String str20 = CommonAppListActivity.access$2().getResources().getString(2131296496);
          Toast.makeText(localContext4, str20, 1).show();
          continue;
        }
        try
        {
          AssetBrowser.tw_out.getChildAt(2).performClick();
          TabbedTopCategoriesBrowser.tw_out.getChildAt(2).performClick();
        }
        catch (Exception localException4)
        {
          localException4.printStackTrace();
        }
        continue;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.2
 * JD-Core Version:    0.6.0
 */