package com.yingyonghui.market;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

@EnclosingMethod
class UpdateAppListActivity$6 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      UpdateAppListActivity.access$8(this.this$0, 0);
      continue;
      ArrayList localArrayList1 = (ArrayList)paramMessage.obj;
      if (localArrayList1 != null)
      {
        i = 0;
        j = localArrayList1.size();
        label69: if (i < j);
      }
      else
      {
        if (UpdateAppListActivity.access$7(this.this$0) != null)
          break label585;
        UpdateAppListActivity localUpdateAppListActivity1 = this.this$0;
        FilteredAppListAdapter localFilteredAppListAdapter1 = new com/yingyonghui/market/FilteredAppListAdapter;
        UpdateAppListActivity localUpdateAppListActivity2 = this.this$0;
        FilteredAppListAdapter localFilteredAppListAdapter2 = localFilteredAppListAdapter1;
        UpdateAppListActivity localUpdateAppListActivity3 = localUpdateAppListActivity2;
        ArrayList localArrayList2 = localArrayList1;
        int k = 900;
        localFilteredAppListAdapter2.<init>(localUpdateAppListActivity3, localArrayList2, k);
        UpdateAppListActivity.access$17(localUpdateAppListActivity1, localFilteredAppListAdapter1);
        ListView localListView1 = UpdateAppListActivity.access$2(this.this$0);
        FilteredAppListAdapter localFilteredAppListAdapter3 = UpdateAppListActivity.access$7(this.this$0);
        localListView1.setAdapter(localFilteredAppListAdapter3);
      }
      label585: 
      do
      {
        if (localArrayList1 != null)
        {
          UpdateAppListActivity localUpdateAppListActivity4 = this.this$0;
          int m = UpdateAppListActivity.access$18(localUpdateAppListActivity4);
          int n = localArrayList1.size();
          int i1 = m + n;
          UpdateAppListActivity.access$5(localUpdateAppListActivity4, i1);
        }
        if ((localArrayList1 != null) && (localArrayList1.size() != 0))
        {
          int i2 = localArrayList1.size();
          int i3 = 10;
          if (i2 >= i3);
        }
        else
        {
          UpdateAppListActivity.access$4(this.this$0, 1);
          ListView localListView2 = UpdateAppListActivity.access$2(this.this$0);
          View localView = UpdateAppListActivity.access$3();
          localListView2.removeFooterView(localView);
        }
        UpdateAppListActivity.access$0(this.this$0).setVisibility(8);
        UpdateAppListActivity.access$1(this.this$0).setVisibility(8);
        UpdateAppListActivity.access$2(this.this$0).setVisibility(0);
        break;
        Asset localAsset1 = (Asset)localArrayList1.get(i);
        PackageManager localPackageManager1 = UpdateAppListActivity.access$16(this.this$0);
        String str1 = localAsset1.pkgName;
        PackageInfo localPackageInfo = GlobalUtil.getPackageInfo(localPackageManager1, str1);
        if ((localPackageInfo == null) || (localPackageInfo.applicationInfo == null))
        {
          i += 1;
          break label69;
        }
        ApplicationInfo localApplicationInfo1 = localPackageInfo.applicationInfo;
        int i4 = localApplicationInfo1.flags & 0x1;
        int i5 = 1;
        if (i4 == i5);
        for (int i6 = 1; ; i6 = 0)
        {
          while (true)
          {
            int i7 = i6;
            localAsset1.isSystem = i7;
            if (localAsset1.exist)
              break;
            PackageManager localPackageManager2 = UpdateAppListActivity.access$16(this.this$0);
            ApplicationInfo localApplicationInfo2 = localApplicationInfo1;
            PackageManager localPackageManager3 = localPackageManager2;
            String str2 = localApplicationInfo2.loadLabel(localPackageManager3).toString();
            localAsset1.title = str2;
            CountDownLatch localCountDownLatch1 = new java/util/concurrent/CountDownLatch;
            CountDownLatch localCountDownLatch2 = localCountDownLatch1;
            int i8 = 1;
            localCountDownLatch2.<init>(i8);
            UpdateAppListActivity.PkgSizeObserver localPkgSizeObserver1 = new com/yingyonghui/market/UpdateAppListActivity$PkgSizeObserver;
            UpdateAppListActivity localUpdateAppListActivity5 = this.this$0;
            UpdateAppListActivity.PkgSizeObserver localPkgSizeObserver2 = localPkgSizeObserver1;
            CountDownLatch localCountDownLatch3 = localCountDownLatch1;
            UpdateAppListActivity localUpdateAppListActivity6 = localUpdateAppListActivity5;
            Asset localAsset2 = localAsset1;
            localPkgSizeObserver2.<init>(localCountDownLatch3, localUpdateAppListActivity6, localAsset2);
            String str3 = localAsset1.pkgName;
            PackageManager localPackageManager4 = UpdateAppListActivity.access$16(this.this$0);
            UpdateAppListActivity.PkgSizeObserver localPkgSizeObserver3 = localPkgSizeObserver1;
            String str4 = str3;
            PackageManager localPackageManager5 = localPackageManager4;
            localPkgSizeObserver3.invokeGetPkgSize(str4, localPackageManager5);
            try
            {
              localCountDownLatch1.await();
            }
            catch (Exception localException1)
            {
              String str5 = UpdateAppListActivity.access$12();
              String str6 = localException1.getLocalizedMessage();
              String str7 = str5;
              String str8 = str6;
              Exception localException2 = localException1;
              Log.e(str7, str8, localException2);
            }
          }
          break;
        }
      }
      while (localArrayList1 == null);
      int j = localArrayList1.size();
      int i = 0;
      while (true)
      {
        if (i >= j)
        {
          UpdateAppListActivity.access$7(this.this$0).notifyDataSetChanged();
          break;
        }
        FilteredAppListAdapter localFilteredAppListAdapter4 = UpdateAppListActivity.access$7(this.this$0);
        Asset localAsset3 = (Asset)localArrayList1.get(i);
        FilteredAppListAdapter localFilteredAppListAdapter5 = localFilteredAppListAdapter4;
        Asset localAsset4 = localAsset3;
        localFilteredAppListAdapter5.add(localAsset4);
        i += 1;
      }
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable1 = localImage2.icon;
      UpdateAppListActivity localUpdateAppListActivity7 = this.this$0;
      int i9 = localImage2._id;
      UpdateAppListActivity localUpdateAppListActivity8 = localUpdateAppListActivity7;
      int i10 = i9;
      Drawable localDrawable2 = localDrawable1;
      CachedThumbnails.cacheThumbnail(localUpdateAppListActivity8, i10, localDrawable2);
      ListView localListView3 = UpdateAppListActivity.access$2(this.this$0);
      StringBuilder localStringBuilder1 = new StringBuilder();
      int i11 = localImage2._id;
      String str9 = i11;
      if (localListView3.findViewWithTag(str9) == null)
        continue;
      ListView localListView4 = UpdateAppListActivity.access$2(this.this$0);
      StringBuilder localStringBuilder2 = new StringBuilder();
      int i12 = localImage2._id;
      String str10 = i12;
      ImageView localImageView = (ImageView)localListView4.findViewWithTag(str10);
      Drawable localDrawable3 = localDrawable1;
      localImageView.setImageDrawable(localDrawable3);
      continue;
      try
      {
        UpdateAppListActivity.access$0(this.this$0).setVisibility(8);
        UpdateAppListActivity.access$1(this.this$0).setVisibility(0);
        UpdateAppListActivity.access$2(this.this$0).setVisibility(8);
        TextView localTextView1 = (TextView)this.this$0.findViewById(2131427409);
        UpdateAppListActivity.6.1 local11 = new com/yingyonghui/market/UpdateAppListActivity$6$1;
        UpdateAppListActivity.6.1 local12 = local11;
        6 local6 = this;
        local12.<init>(local6);
        TextView localTextView2 = localTextView1;
        UpdateAppListActivity.6.1 local13 = local11;
        localTextView2.setOnClickListener(local13);
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity.6
 * JD-Core Version:    0.6.0
 */