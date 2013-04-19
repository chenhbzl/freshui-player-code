package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

class UpdateAppListActivity$2
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
      UpdateAppListActivity.access$9(this.this$0, 0);
      int i = paramAbsListView.getFirstVisiblePosition();
      int j = paramAbsListView.getChildCount();
      int k = 0;
      while (true)
      {
        if (k >= j)
        {
          if (UpdateAppListActivity.access$7(this.this$0) == null)
            break;
          int m = i + j;
          int n = UpdateAppListActivity.access$7(this.this$0).getCount() - 2;
          if (m < n)
            break;
          UpdateAppListActivity.access$8(this.this$0, 0);
          break;
        }
        View localView = paramAbsListView.getChildAt(k);
        int i1 = i + k;
        FilteredAppListAdapter.ViewHolder localViewHolder = (FilteredAppListAdapter.ViewHolder)localView.getTag();
        if (localViewHolder != null)
        {
          ImageView localImageView = localViewHolder.thumbnail;
          UpdateAppListActivity localUpdateAppListActivity = this.this$0;
          int i2 = (int)UpdateAppListActivity.access$7(this.this$0).getItemId(i1);
          Drawable localDrawable = localUpdateAppListActivity.getThumbnail(i1, i2);
          localImageView.setImageDrawable(localDrawable);
        }
        k += 1;
      }
      if (UpdateAppListActivity.access$10(this.this$0))
        continue;
      UpdateAppListActivity.access$11(this.this$0);
      UpdateAppListActivity.access$9(this.this$0, 1);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity.2
 * JD-Core Version:    0.6.0
 */