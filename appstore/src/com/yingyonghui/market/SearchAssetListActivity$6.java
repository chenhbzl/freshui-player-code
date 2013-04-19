package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SearchAssetListActivity$6 extends Handler
{
  /** @deprecated */
  public void handleMessage(Message paramMessage)
  {
    monitorenter;
    while (true)
    {
      TextView localTextView;
      try
      {
        int i = paramMessage.arg1;
        localTextView = null;
        if (SearchAssetListActivity.access$8(this.this$0) == null)
          continue;
        ListView localListView = SearchAssetListActivity.access$8(this.this$0);
        String str1 = String.valueOf(i);
        String str2 = str1;
        View localView = localListView.findViewWithTag(str2);
        if (localView == null)
          continue;
        ViewParent localViewParent = localView.getParent();
        if (!(localViewParent instanceof View))
          continue;
        localTextView = (TextView)((View)localViewParent).findViewById(2131427420);
        if (localTextView == null)
          continue;
        j = paramMessage.what;
        switch (j)
        {
        default:
          return;
        case 100:
          j = 2130837586;
          localTextView.setBackgroundResource(j);
          localTextView.getBackground().setCallback(null);
          localTextView.setText(2131296412);
          if (SearchAssetListActivity.access$0(this.this$0) == null)
            continue;
          SearchAssetListActivity.access$0(this.this$0).addDownloadLogRequest();
          continue;
        case 200:
        case 300:
        case 1:
        }
      }
      finally
      {
        monitorexit;
      }
      int j = 2130837586;
      localTextView.setBackgroundResource(j);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296412);
      continue;
      localTextView.setBackgroundResource(2130837586);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296276);
      continue;
      if (paramMessage.arg2 == 1)
      {
        localTextView.setBackgroundResource(2130837586);
        localTextView.getBackground().setCallback(null);
        localTextView.setText(2131296416);
        continue;
      }
      localTextView.setBackgroundResource(2130837586);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296417);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchAssetListActivity.6
 * JD-Core Version:    0.6.0
 */