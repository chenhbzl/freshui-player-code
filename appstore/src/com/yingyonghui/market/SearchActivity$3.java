package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver;
import android.widget.TabHost;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SearchActivity$3
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (!SearchActivity.access$7(this.this$0))
    {
      SearchActivity.access$8(this.this$0, 1);
      ViewTreeObserver localViewTreeObserver = SearchActivity.access$9(this.this$0).getViewTreeObserver();
      if (localViewTreeObserver != null)
      {
        TabHost localTabHost = SearchActivity.access$9(this.this$0);
        localViewTreeObserver.removeOnTouchModeChangeListener(localTabHost);
        SearchActivity.3.1 local1 = new SearchActivity.3.1(this);
        localViewTreeObserver.addOnTouchModeChangeListener(local1);
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchActivity.3
 * JD-Core Version:    0.6.0
 */