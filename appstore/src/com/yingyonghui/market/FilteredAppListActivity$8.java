package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class FilteredAppListActivity$8 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      ArrayList localArrayList = (ArrayList)paramMessage.obj;
      if (FilteredAppListActivity.access$8(this.this$0) == null)
      {
        FilteredAppListActivity localFilteredAppListActivity1 = this.this$0;
        FilteredAppListActivity localFilteredAppListActivity2 = this.this$0;
        int i = FilteredAppListActivity.access$15(this.this$0);
        FilteredAppListAdapter localFilteredAppListAdapter1 = new FilteredAppListAdapter(localFilteredAppListActivity2, localArrayList, i);
        FilteredAppListActivity.access$28(localFilteredAppListActivity1, localFilteredAppListAdapter1);
        ListView localListView1 = FilteredAppListActivity.access$25(this.this$0);
        FilteredAppListAdapter localFilteredAppListAdapter2 = FilteredAppListActivity.access$8(this.this$0);
        localListView1.setAdapter(localFilteredAppListAdapter2);
      }
      do
      {
        if (localArrayList != null)
        {
          FilteredAppListActivity localFilteredAppListActivity3 = this.this$0;
          int j = FilteredAppListActivity.access$29(localFilteredAppListActivity3);
          int k = localArrayList.size();
          int m = j + k;
          FilteredAppListActivity.access$7(localFilteredAppListActivity3, m);
        }
        if ((localArrayList == null) || (localArrayList.size() == 0) || (localArrayList.size() < 10))
        {
          FilteredAppListActivity.access$5(this.this$0, 1);
          if (FilteredAppListActivity.access$25(this.this$0).getFooterViewsCount() > 0)
          {
            ListView localListView2 = FilteredAppListActivity.access$25(this.this$0);
            View localView = FilteredAppListActivity.access$26(this.this$0);
            localListView2.removeFooterView(localView);
          }
        }
        FilteredAppListActivity.access$9(this.this$0).setVisibility(8);
        FilteredAppListActivity.access$25(this.this$0).setVisibility(0);
        this.this$0.inflatingAppList = 0;
        this.this$0.inflatingAppRankingList = 0;
        this.this$0.inflatingAppLikeList = 0;
        this.this$0.inflatingAppRelativeList = 0;
        this.this$0.doingSearchQuery = 0;
        break;
      }
      while (localArrayList == null);
      int n = localArrayList.size();
      int i1 = 0;
      while (true)
      {
        if (i1 >= n)
        {
          FilteredAppListActivity.access$8(this.this$0).notifyDataSetChanged();
          break;
        }
        FilteredAppListAdapter localFilteredAppListAdapter3 = FilteredAppListActivity.access$8(this.this$0);
        Asset localAsset = (Asset)localArrayList.get(i1);
        localFilteredAppListAdapter3.add(localAsset);
        i1 += 1;
      }
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable = localImage2.icon;
      FilteredAppListActivity localFilteredAppListActivity4 = this.this$0;
      int i2 = localImage2._id;
      CachedThumbnails.cacheThumbnail(localFilteredAppListActivity4, i2, localDrawable);
      ListView localListView3 = FilteredAppListActivity.access$25(this.this$0);
      StringBuilder localStringBuilder1 = new StringBuilder();
      int i3 = localImage2._id;
      String str1 = i3;
      if (localListView3.findViewWithTag(str1) == null)
        continue;
      ListView localListView4 = FilteredAppListActivity.access$25(this.this$0);
      StringBuilder localStringBuilder2 = new StringBuilder();
      int i4 = localImage2._id;
      String str2 = i4;
      ((ImageView)localListView4.findViewWithTag(str2)).setImageDrawable(localDrawable);
      continue;
      try
      {
        FilteredAppListActivity.access$9(this.this$0).setVisibility(8);
        FilteredAppListActivity.access$25(this.this$0).setVisibility(0);
        this.this$0.showDialog(100);
        this.this$0.inflatingAppList = 0;
        this.this$0.inflatingAppRankingList = 0;
        this.this$0.inflatingAppLikeList = 0;
        this.this$0.inflatingAppRelativeList = 0;
        this.this$0.doingSearchQuery = 0;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListActivity.8
 * JD-Core Version:    0.6.0
 */