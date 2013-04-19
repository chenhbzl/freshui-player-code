package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import com.yingyonghui.market.model.ImageURL;
import com.yingyonghui.market.model.News;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;
import java.util.Hashtable;

@EnclosingMethod
class NewsListActivity$5 extends Handler
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
      ArrayList localArrayList1 = (ArrayList)paramMessage.obj;
      if (NewsListActivity.access$11() != null)
      {
        int i = localArrayList1.size();
        j = 0;
        label55: if (j < i);
      }
      else
      {
        if (NewsListActivity.access$2(this.this$0) != null)
          break label320;
        NewsListActivity localNewsListActivity1 = this.this$0;
        NewsListActivity localNewsListActivity2 = this.this$0;
        NewsListActivity localNewsListActivity3 = this.this$0;
        NewsListActivity.NewsAdapter localNewsAdapter1 = new NewsListActivity.NewsAdapter(localNewsListActivity2, localNewsListActivity3, localArrayList1);
        NewsListActivity.access$12(localNewsListActivity1, localNewsAdapter1);
        ListView localListView1 = NewsListActivity.access$8(this.this$0);
        NewsListActivity.NewsAdapter localNewsAdapter2 = NewsListActivity.access$2(this.this$0);
        localListView1.setAdapter(localNewsAdapter2);
      }
      label320: 
      do
      {
        if (localArrayList1 != null)
        {
          NewsListActivity localNewsListActivity4 = this.this$0;
          int k = NewsListActivity.access$13(localNewsListActivity4);
          int m = localArrayList1.size();
          int n = k + m;
          NewsListActivity.access$14(localNewsListActivity4, n);
        }
        if ((localArrayList1 == null) || (localArrayList1.size() == 0) || (localArrayList1.size() < 10))
        {
          NewsListActivity.access$15(this.this$0, 1);
          if (NewsListActivity.access$8(this.this$0).getFooterViewsCount() > 0)
          {
            ListView localListView2 = NewsListActivity.access$8(this.this$0);
            View localView = NewsListActivity.access$16(this.this$0);
            localListView2.removeFooterView(localView);
          }
        }
        NewsListActivity.access$9(this.this$0).setVisibility(8);
        NewsListActivity.access$8(this.this$0).setVisibility(0);
        NewsListActivity.access$17(this.this$0, 0);
        break;
        News localNews1 = (News)localArrayList1.get(j);
        if (localNews1 != null)
        {
          ArrayList localArrayList2 = NewsListActivity.access$11();
          String str1 = localNews1.url;
          localArrayList2.add(str1);
        }
        j += 1;
        break label55;
      }
      while (localArrayList1 == null);
      int i1 = localArrayList1.size();
      int j = 0;
      while (true)
      {
        if (j >= i1)
        {
          NewsListActivity.access$2(this.this$0).notifyDataSetChanged();
          break;
        }
        NewsListActivity.NewsAdapter localNewsAdapter3 = NewsListActivity.access$2(this.this$0);
        News localNews2 = (News)localArrayList1.get(j);
        localNewsAdapter3.add(localNews2);
        j += 1;
      }
      ImageURL localImageURL = (ImageURL)paramMessage.obj;
      if (localImageURL.icon == null)
        continue;
      Hashtable localHashtable = NewsListActivity.access$18(this.this$0);
      String str2 = localImageURL.url;
      Drawable localDrawable = localImageURL.icon;
      localHashtable.put(str2, localDrawable);
      NewsListActivity.access$2(this.this$0).notifyDataSetChanged();
      continue;
      try
      {
        NewsListActivity.access$9(this.this$0).setVisibility(8);
        NewsListActivity.access$8(this.this$0).setVisibility(0);
        NewsListActivity.access$17(this.this$0, 0);
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsListActivity.5
 * JD-Core Version:    0.6.0
 */