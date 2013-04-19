package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.EnclosingMethod;
import java.util.Vector;

@EnclosingMethod
class FilteredCategoryListActivity$4 extends Handler
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
      Vector localVector = (Vector)paramMessage.obj;
      FilteredCategoryListActivity localFilteredCategoryListActivity1 = this.this$0;
      FilteredCategoryListActivity localFilteredCategoryListActivity2 = this.this$0;
      FilteredCategoryListActivity localFilteredCategoryListActivity3 = this.this$0;
      FilteredCategoryListActivity.CategoriesWithAppsAdapter localCategoriesWithAppsAdapter1 = new FilteredCategoryListActivity.CategoriesWithAppsAdapter(localFilteredCategoryListActivity2, localFilteredCategoryListActivity3, localVector);
      FilteredCategoryListActivity.access$10(localFilteredCategoryListActivity1, localCategoriesWithAppsAdapter1);
      ListView localListView1 = FilteredCategoryListActivity.access$6(this.this$0);
      FilteredCategoryListActivity.CategoriesWithAppsAdapter localCategoriesWithAppsAdapter2 = FilteredCategoryListActivity.access$2(this.this$0);
      localListView1.setAdapter(localCategoriesWithAppsAdapter2);
      if (FilteredCategoryListActivity.access$6(this.this$0).getAdapter().isEmpty())
      {
        ListView localListView2 = FilteredCategoryListActivity.access$6(this.this$0);
        View localView = FilteredCategoryListActivity.access$11(this.this$0);
        localListView2.removeHeaderView(localView);
      }
      FilteredCategoryListActivity.access$7(this.this$0).setVisibility(8);
      FilteredCategoryListActivity.access$6(this.this$0).setVisibility(0);
      continue;
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable = localImage2.icon;
      FilteredCategoryListActivity localFilteredCategoryListActivity4 = this.this$0;
      int i = localImage2._id;
      CachedThumbnails.cacheThumbnail(localFilteredCategoryListActivity4, i, localDrawable);
      FilteredCategoryListActivity.access$2(this.this$0).notifyDataSetChanged();
      continue;
      try
      {
        FilteredCategoryListActivity.access$7(this.this$0).setVisibility(8);
        FilteredCategoryListActivity.access$6(this.this$0).setVisibility(0);
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredCategoryListActivity.4
 * JD-Core Version:    0.6.0
 */