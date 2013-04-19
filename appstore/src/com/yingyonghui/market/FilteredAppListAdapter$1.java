package com.yingyonghui.market;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import java.io.File;

class FilteredAppListAdapter$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    int i = ((Integer)paramView.getTag()).intValue();
    FilteredAppListAdapter localFilteredAppListAdapter1 = this.this$0;
    Asset localAsset1 = (Asset)this.this$0.getItem(i);
    FilteredAppListAdapter.access$0(localFilteredAppListAdapter1, localAsset1);
    if (FilteredAppListAdapter.access$1(this.this$0) == null);
    while (true)
    {
      return;
      if (FilteredAppListAdapter.access$1(this.this$0).isSystem)
        break;
      Context localContext1 = FilteredAppListAdapter.access$2(this.this$0);
      String str1 = FilteredAppListAdapter.access$1(this.this$0).pkgName;
      int j = FilteredAppListAdapter.access$1(this.this$0).versionCode;
      int k = FilteredAppListAdapter.access$1(this.this$0)._id;
      if (PackageInstallInfo.getPackageInstalledStatus(localContext1, str1, j, k) == 1)
      {
        if (!(FilteredAppListAdapter.access$2(this.this$0) instanceof UpdateAppListActivity))
          continue;
        FilteredAppListAdapter localFilteredAppListAdapter2 = this.this$0;
        Asset localAsset2 = FilteredAppListAdapter.access$1(this.this$0);
        FilteredAppListAdapter.access$3(localFilteredAppListAdapter2, localAsset2);
        continue;
      }
      FilteredAppListAdapter localFilteredAppListAdapter3 = this.this$0;
      Asset localAsset3 = FilteredAppListAdapter.access$1(this.this$0);
      File localFile = FilteredAppListAdapter.access$4(localFilteredAppListAdapter3, localAsset3);
      if (localFile != null)
      {
        FilteredAppListAdapter localFilteredAppListAdapter4 = this.this$0;
        Asset localAsset4 = FilteredAppListAdapter.access$1(this.this$0);
        FilteredAppListAdapter.access$5(localFilteredAppListAdapter4, localFile, localAsset4);
        continue;
      }
      if (FilteredAppListAdapter.access$1(this.this$0).price > 0.0F)
      {
        FilteredAppListAdapter localFilteredAppListAdapter5 = this.this$0;
        Asset localAsset5 = FilteredAppListAdapter.access$1(this.this$0);
        FilteredAppListAdapter.access$6(localFilteredAppListAdapter5, localAsset5);
        MobclickAgent.onEvent(this.this$0.getContext(), "BuyButton", "Paid_Confirm");
        continue;
      }
      FilteredAppListAdapter localFilteredAppListAdapter6 = this.this$0;
      int m = FilteredAppListAdapter.access$1(this.this$0)._id;
      if (FilteredAppListAdapter.access$7(localFilteredAppListAdapter6, m))
      {
        Toast.makeText(FilteredAppListAdapter.access$2(this.this$0), 2131296351, 1).show();
        continue;
      }
      if (!GlobalUtil.isSystemApp(FilteredAppListAdapter.access$2(this.this$0).getPackageManager()))
        break label432;
      Context localContext2 = FilteredAppListAdapter.access$2(this.this$0);
      int n = FilteredAppListAdapter.access$1(this.this$0)._id;
      String str2 = FilteredAppListAdapter.access$1(this.this$0).title;
      int i1 = FilteredAppListAdapter.access$1(this.this$0).size;
      String str3 = FilteredAppListAdapter.access$1(this.this$0).pkgName;
      String[] arrayOfString = FilteredAppListAdapter.access$1(this.this$0).permissions;
      GlobalUtil.startPermissionsActivity(localContext2, n, str2, i1, str3, arrayOfString);
    }
    while (true)
    {
      MobclickAgent.onEvent(this.this$0.getContext(), "BuyButton", "Free_Confirm");
      break;
      break;
      label432: FilteredAppListAdapter.access$8(this.this$0);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListAdapter.1
 * JD-Core Version:    0.6.0
 */