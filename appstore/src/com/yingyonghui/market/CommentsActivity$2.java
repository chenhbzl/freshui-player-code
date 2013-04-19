package com.yingyonghui.market;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class CommentsActivity$2
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
      CommentsActivity.access$10(this.this$0, 0);
      int i = paramAbsListView.getFirstVisiblePosition();
      int j = paramAbsListView.getChildCount();
      if (CommentsActivity.access$11(this.this$0) == null)
        continue;
      int k = i + j;
      int m = CommentsActivity.access$11(this.this$0).getCount() - 2;
      if (k < m)
        continue;
      CommentsActivity.access$3(this.this$0);
      continue;
      if (CommentsActivity.access$12(this.this$0))
        continue;
      CommentsActivity.access$10(this.this$0, 1);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommentsActivity.2
 * JD-Core Version:    0.6.0
 */