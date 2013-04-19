package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import java.util.Hashtable;

class NewsListActivity$1
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
      NewsListActivity.access$0(this.this$0, 0);
      int i = paramAbsListView.getFirstVisiblePosition();
      int j = paramAbsListView.getChildCount();
      int k = 0;
      while (true)
      {
        if (k >= j)
        {
          if (NewsListActivity.access$2(this.this$0) == null)
            break;
          int m = i + j;
          int n = NewsListActivity.access$2(this.this$0).getCount() - 2;
          if (m < n)
            break;
          NewsListActivity.access$3(this.this$0);
          break;
        }
        View localView = paramAbsListView.getChildAt(k);
        int i1 = i + k;
        Hashtable localHashtable = NewsListActivity.access$1(this.this$0);
        Integer localInteger = Integer.valueOf(i1);
        if (!localHashtable.containsKey(localInteger))
        {
          NewsListActivity.ViewHolder localViewHolder = (NewsListActivity.ViewHolder)localView.getTag();
          if (localViewHolder != null)
          {
            NewsListActivity localNewsListActivity = this.this$0;
            String str = NewsListActivity.access$2(this.this$0).getThumbURL(i1);
            Drawable localDrawable = localNewsListActivity.getThumbnail(i1, str);
            localViewHolder.thumbnail.setImageDrawable(localDrawable);
            localDrawable.setCallback(null);
          }
        }
        k += 1;
      }
      if (NewsListActivity.access$4(this.this$0))
        continue;
      NewsListActivity.access$5(this.this$0);
      NewsListActivity.access$0(this.this$0, 1);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsListActivity.1
 * JD-Core Version:    0.6.0
 */