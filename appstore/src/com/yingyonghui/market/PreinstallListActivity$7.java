package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class PreinstallListActivity$7 extends Handler
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
      ArrayList localArrayList = (ArrayList)paramMessage.obj;
      if (PreinstallListActivity.access$0(this.this$0) == null)
      {
        PreinstallListActivity localPreinstallListActivity1 = this.this$0;
        PreinstallListActivity localPreinstallListActivity2 = this.this$0;
        PreinstallListActivity localPreinstallListActivity3 = this.this$0;
        PreinstallListActivity.PreinstallAppListAdapter localPreinstallAppListAdapter1 = new PreinstallListActivity.PreinstallAppListAdapter(localPreinstallListActivity2, localPreinstallListActivity3, localArrayList);
        PreinstallListActivity.access$15(localPreinstallListActivity1, localPreinstallAppListAdapter1);
        ListView localListView1 = PreinstallListActivity.access$11(this.this$0);
        PreinstallListActivity.PreinstallAppListAdapter localPreinstallAppListAdapter2 = PreinstallListActivity.access$0(this.this$0);
        localListView1.setAdapter(localPreinstallAppListAdapter2);
      }
      do
      {
        if (localArrayList != null)
        {
          PreinstallListActivity localPreinstallListActivity4 = this.this$0;
          int i = PreinstallListActivity.access$16(localPreinstallListActivity4);
          int j = localArrayList.size();
          int k = i + j;
          PreinstallListActivity.access$17(localPreinstallListActivity4, k);
        }
        if ((localArrayList == null) || (localArrayList.size() == 0) || (localArrayList.size() < 10))
        {
          PreinstallListActivity.access$18(this.this$0, 1);
          ListView localListView2 = PreinstallListActivity.access$11(this.this$0);
          View localView = PreinstallListActivity.access$19(this.this$0);
          localListView2.removeFooterView(localView);
        }
        PreinstallListActivity.access$12(this.this$0).setVisibility(8);
        PreinstallListActivity.access$11(this.this$0).setVisibility(0);
        break;
      }
      while (localArrayList == null);
      int m = localArrayList.size();
      int n = 0;
      while (true)
      {
        if (n >= m)
        {
          PreinstallListActivity.access$0(this.this$0).notifyDataSetChanged();
          break;
        }
        PreinstallListActivity.PreinstallAppListAdapter localPreinstallAppListAdapter3 = PreinstallListActivity.access$0(this.this$0);
        Asset localAsset = (Asset)localArrayList.get(n);
        localPreinstallAppListAdapter3.add(localAsset);
        n += 1;
      }
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable = localImage2.icon;
      PreinstallListActivity localPreinstallListActivity5 = this.this$0;
      int i1 = localImage2._id;
      CachedThumbnails.cacheThumbnail(localPreinstallListActivity5, i1, localDrawable);
      PreinstallListActivity.access$0(this.this$0).notifyDataSetChanged();
      continue;
      try
      {
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.PreinstallListActivity.7
 * JD-Core Version:    0.6.0
 */