package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class SearchAssetListActivity$8 extends Handler
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
      if (SearchAssetListActivity.access$0(this.this$0) == null)
      {
        SearchAssetListActivity localSearchAssetListActivity1 = this.this$0;
        SearchAssetListActivity localSearchAssetListActivity2 = this.this$0;
        FilteredAppListAdapter localFilteredAppListAdapter1 = new FilteredAppListAdapter(localSearchAssetListActivity2, localArrayList, 900);
        SearchAssetListActivity.access$9(localSearchAssetListActivity1, localFilteredAppListAdapter1);
        ListView localListView1 = SearchAssetListActivity.access$8(this.this$0);
        FilteredAppListAdapter localFilteredAppListAdapter2 = SearchAssetListActivity.access$0(this.this$0);
        localListView1.setAdapter(localFilteredAppListAdapter2);
      }
      do
      {
        if (localArrayList != null)
        {
          SearchAssetListActivity localSearchAssetListActivity3 = this.this$0;
          int i = SearchAssetListActivity.access$10(localSearchAssetListActivity3);
          int j = localArrayList.size();
          int k = i + j;
          SearchAssetListActivity.access$11(localSearchAssetListActivity3, k);
        }
        if ((localArrayList == null) || (localArrayList.size() == 0) || (localArrayList.size() < 10))
        {
          SearchAssetListActivity.access$12(this.this$0, 1);
          ListView localListView2 = SearchAssetListActivity.access$8(this.this$0);
          View localView = SearchAssetListActivity.access$13(this.this$0);
          localListView2.removeFooterView(localView);
        }
        SearchAssetListActivity.access$14(this.this$0).setVisibility(8);
        SearchAssetListActivity.access$8(this.this$0).setVisibility(0);
        break;
      }
      while (localArrayList == null);
      int m = localArrayList.size();
      int n = 0;
      while (true)
      {
        if (n >= m)
        {
          SearchAssetListActivity.access$0(this.this$0).notifyDataSetChanged();
          break;
        }
        FilteredAppListAdapter localFilteredAppListAdapter3 = SearchAssetListActivity.access$0(this.this$0);
        Asset localAsset = (Asset)localArrayList.get(n);
        localFilteredAppListAdapter3.add(localAsset);
        n += 1;
      }
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable = localImage2.icon;
      SearchAssetListActivity localSearchAssetListActivity4 = this.this$0;
      int i1 = localImage2._id;
      CachedThumbnails.cacheThumbnail(localSearchAssetListActivity4, i1, localDrawable);
      SearchAssetListActivity.access$0(this.this$0).notifyDataSetChanged();
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
 * Qualified Name:     com.yingyonghui.market.SearchAssetListActivity.8
 * JD-Core Version:    0.6.0
 */