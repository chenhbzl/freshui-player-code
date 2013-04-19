package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;

class CommonAppListActivity$3
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
      CommonAppListActivity.access$3(this.this$0, 0);
      int i = paramAbsListView.getFirstVisiblePosition();
      int j = paramAbsListView.getChildCount();
      int k = 0;
      if (k >= j)
      {
        int m = i + j;
        int n = CommonAppListActivity.access$0(this.this$0).getCount() - 2;
        if (m < n)
          continue;
        CommonAppListActivity.access$5(this.this$0);
        continue;
      }
      View localView = paramAbsListView.getChildAt(k);
      if (CommonAppListActivity.access$4(this.this$0));
      for (int i1 = i + k - 1; ; i1 = i + k)
      {
        FilteredAppListAdapter.ViewHolder localViewHolder = (FilteredAppListAdapter.ViewHolder)localView.getTag();
        if (localViewHolder != null)
        {
          ImageView localImageView = localViewHolder.thumbnail;
          CommonAppListActivity localCommonAppListActivity = this.this$0;
          int i2 = (int)CommonAppListActivity.access$0(this.this$0).getItemId(i1);
          Drawable localDrawable = localCommonAppListActivity.getThumbnail(i1, i2);
          localImageView.setImageDrawable(localDrawable);
        }
        k += 1;
        break;
      }
      if (CommonAppListActivity.access$6(this.this$0))
        continue;
      CommonAppListActivity.access$7(this.this$0);
      CommonAppListActivity.access$3(this.this$0, 1);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.3
 * JD-Core Version:    0.6.0
 */