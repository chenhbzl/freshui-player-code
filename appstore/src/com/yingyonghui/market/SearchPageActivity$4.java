package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class SearchPageActivity$4 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      ArrayList localArrayList = (ArrayList)paramMessage.obj;
      if (localArrayList.size() > 0)
      {
        this.this$0.tags.clear();
        this.this$0.tags.addAll(localArrayList);
        SearchPageActivity localSearchPageActivity = this.this$0;
        int i = SearchPageActivity.access$11(localSearchPageActivity);
        int j = localArrayList.size();
        int k = i + j;
        SearchPageActivity.access$12(localSearchPageActivity, k);
        if (SearchPageActivity.access$11(this.this$0) >= 200)
          SearchPageActivity.access$12(this.this$0, 0);
        if (SearchPageActivity.access$7(this.this$0) == 0)
          SearchPageActivity.access$13(this.this$0).setVisibility(0);
        while (true)
        {
          SearchPageActivity.access$15(this.this$0);
          SearchPageActivity.access$2(this.this$0).setVisibility(0);
          SearchPageActivity.access$3(this.this$0).setVisibility(8);
          SearchPageActivity.access$4(this.this$0).setVisibility(8);
          break;
          SearchPageActivity.access$14(this.this$0).setVisibility(0);
        }
      }
      SearchPageActivity.access$4(this.this$0).setVisibility(0);
      SearchPageActivity.access$2(this.this$0).setVisibility(8);
      SearchPageActivity.access$3(this.this$0).setVisibility(8);
      continue;
      try
      {
        SearchPageActivity.access$4(this.this$0).setVisibility(0);
        SearchPageActivity.access$2(this.this$0).setVisibility(8);
        SearchPageActivity.access$3(this.this$0).setVisibility(8);
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchPageActivity.4
 * JD-Core Version:    0.6.0
 */