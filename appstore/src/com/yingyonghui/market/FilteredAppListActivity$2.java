package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

class FilteredAppListActivity$2
  implements AbsListView.OnScrollListener
{
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      FilteredAppListActivity.access$11(this.this$0, 0);
      int i = paramAbsListView.getFirstVisiblePosition();
      int j = paramAbsListView.getChildCount();
      int k = 0;
      if (k >= j)
      {
        if (FilteredAppListActivity.access$8(this.this$0) == null)
          continue;
        int m = i + j;
        int n = FilteredAppListActivity.access$8(this.this$0).getCount() - 2;
        if (m < n)
          continue;
        if (FilteredAppListActivity.access$13(this.this$0) > 0)
        {
          FilteredAppListActivity.access$14(this.this$0);
          continue;
        }
      }
      else
      {
        View localView = paramAbsListView.getChildAt(k);
        if (FilteredAppListActivity.access$12(this.this$0));
        for (int i1 = i + k - 1; ; i1 = i + k)
        {
          FilteredAppListAdapter.ViewHolder localViewHolder = (FilteredAppListAdapter.ViewHolder)localView.getTag();
          if (localViewHolder != null)
          {
            FilteredAppListActivity localFilteredAppListActivity = this.this$0;
            int i2 = (int)FilteredAppListActivity.access$8(this.this$0).getItemId(i1);
            Drawable localDrawable = localFilteredAppListActivity.getThumbnail(i1, i2);
            localViewHolder.thumbnail.setImageDrawable(localDrawable);
            localDrawable.setCallback(null);
          }
          k += 1;
          break;
        }
      }
      if (FilteredAppListActivity.access$15(this.this$0) == 901)
      {
        FilteredAppListActivity.access$16(this.this$0);
        continue;
      }
      if ((FilteredAppListActivity.access$0(this.this$0) == 11) || (FilteredAppListActivity.access$0(this.this$0) == 12))
        continue;
      if (FilteredAppListActivity.access$0(this.this$0) == 56)
      {
        FilteredAppListActivity.access$10(this.this$0);
        continue;
      }
      FilteredAppListActivity.access$17(this.this$0);
      continue;
      if (FilteredAppListActivity.access$18(this.this$0))
        continue;
      FilteredAppListActivity.access$19(this.this$0);
      FilteredAppListActivity.access$11(this.this$0, 1);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListActivity.2
 * JD-Core Version:    0.6.0
 */