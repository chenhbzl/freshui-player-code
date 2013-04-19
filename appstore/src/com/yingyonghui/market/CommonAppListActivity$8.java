package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class CommonAppListActivity$8 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 4:
    case 5:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      CommonAppListActivity.access$5(this.this$0);
      continue;
      if (((Drawable)paramMessage.obj != null) && (CommonAppListActivity.access$4(this.this$0)))
      {
        CommonAppListActivity.access$19(LayoutInflater.from(this.this$0).inflate(2130903115, null));
        ListView localListView1 = CommonAppListActivity.access$11(this.this$0);
        View localView1 = CommonAppListActivity.access$20();
        localListView1.addHeaderView(localView1, null, 0);
        int i = AssetBrowser.screenWidth;
        int j = i * 9 / 16;
        View localView2 = CommonAppListActivity.access$20();
        AbsListView.LayoutParams localLayoutParams = new AbsListView.LayoutParams(i, j);
        localView2.setLayoutParams(localLayoutParams);
        CommonAppListActivity.access$21((Button)this.this$0.findViewById(2131427535));
        Button localButton1 = CommonAppListActivity.access$22();
        View.OnClickListener localOnClickListener1 = CommonAppListActivity.access$23(this.this$0);
        localButton1.setOnClickListener(localOnClickListener1);
        CommonAppListActivity.access$24((Button)this.this$0.findViewById(2131427536));
        Button localButton2 = CommonAppListActivity.access$25();
        View.OnClickListener localOnClickListener2 = CommonAppListActivity.access$23(this.this$0);
        localButton2.setOnClickListener(localOnClickListener2);
        CommonAppListActivity.access$26((Button)this.this$0.findViewById(2131427537));
        Button localButton3 = CommonAppListActivity.access$27();
        View.OnClickListener localOnClickListener3 = CommonAppListActivity.access$23(this.this$0);
        localButton3.setOnClickListener(localOnClickListener3);
        CommonAppListActivity.access$28((Button)this.this$0.findViewById(2131427538));
        Button localButton4 = CommonAppListActivity.access$29();
        View.OnClickListener localOnClickListener4 = CommonAppListActivity.access$23(this.this$0);
        localButton4.setOnClickListener(localOnClickListener4);
        View localView3 = CommonAppListActivity.access$20();
        Drawable localDrawable1 = (Drawable)paramMessage.obj;
        localView3.setBackgroundDrawable(localDrawable1);
      }
      while (true)
      {
        CommonAppListActivity.access$30(this.this$0, 1);
        if (!CommonAppListActivity.access$31(this.this$0))
          break;
        CommonAppListActivity.access$12(this.this$0).setVisibility(8);
        CommonAppListActivity.access$11(this.this$0).setVisibility(0);
        break;
        CommonAppListActivity.access$13(this.this$0, 0);
      }
      ArrayList localArrayList = (ArrayList)paramMessage.obj;
      if (localArrayList == null)
        continue;
      int k = 0;
      while (true)
      {
        int m = Math.min(localArrayList.size(), 4);
        if (k >= m)
          break;
        Asset[] arrayOfAsset = CommonAppListActivity.access$1(this.this$0);
        Asset localAsset1 = (Asset)localArrayList.get(k);
        arrayOfAsset[k] = localAsset1;
        CommonAppListActivity localCommonAppListActivity1 = this.this$0;
        int n = CommonAppListActivity.access$1(this.this$0)[k]._id;
        CommonAppListActivity.access$32(localCommonAppListActivity1, -1, n);
        k += 1;
      }
      localArrayList = (ArrayList)paramMessage.obj;
      if (CommonAppListActivity.access$0(this.this$0) == null)
      {
        CommonAppListActivity localCommonAppListActivity2 = this.this$0;
        CommonAppListActivity localCommonAppListActivity3 = this.this$0;
        FilteredAppListAdapter localFilteredAppListAdapter1 = new FilteredAppListAdapter(localCommonAppListActivity3, localArrayList, 900);
        CommonAppListActivity.access$33(localCommonAppListActivity2, localFilteredAppListAdapter1);
        ListView localListView2 = CommonAppListActivity.access$11(this.this$0);
        FilteredAppListAdapter localFilteredAppListAdapter2 = CommonAppListActivity.access$0(this.this$0);
        localListView2.setAdapter(localFilteredAppListAdapter2);
      }
      do
      {
        if (localArrayList != null)
        {
          CommonAppListActivity localCommonAppListActivity4 = this.this$0;
          int i1 = CommonAppListActivity.access$34(localCommonAppListActivity4);
          int i2 = localArrayList.size();
          int i3 = i1 + i2;
          CommonAppListActivity.access$15(localCommonAppListActivity4, i3);
        }
        if ((localArrayList == null) || (localArrayList.size() == 0) || (localArrayList.size() < 10))
        {
          CommonAppListActivity.access$14(this.this$0, 1);
          ListView localListView3 = CommonAppListActivity.access$11(this.this$0);
          View localView4 = CommonAppListActivity.access$17();
          localListView3.removeFooterView(localView4);
        }
        if (CommonAppListActivity.access$11(this.this$0).getAdapter().isEmpty())
        {
          ListView localListView4 = CommonAppListActivity.access$11(this.this$0);
          View localView5 = CommonAppListActivity.access$20();
          localListView4.removeHeaderView(localView5);
        }
        CommonAppListActivity.access$35(this.this$0, 1);
        if (CommonAppListActivity.access$36(this.this$0))
        {
          CommonAppListActivity.access$12(this.this$0).setVisibility(8);
          CommonAppListActivity.access$11(this.this$0).setVisibility(0);
        }
        this.this$0.inflatingAppList = 0;
        break;
      }
      while (localArrayList == null);
      int i4 = localArrayList.size();
      k = 0;
      while (true)
      {
        if (k >= i4)
        {
          CommonAppListActivity.access$0(this.this$0).notifyDataSetChanged();
          break;
        }
        FilteredAppListAdapter localFilteredAppListAdapter3 = CommonAppListActivity.access$0(this.this$0);
        Asset localAsset2 = (Asset)localArrayList.get(k);
        localFilteredAppListAdapter3.add(localAsset2);
        k += 1;
      }
      Image2 localImage2 = (Image2)paramMessage.obj;
      if (localImage2.icon == null)
        continue;
      Drawable localDrawable2 = localImage2.icon;
      CommonAppListActivity localCommonAppListActivity5 = this.this$0;
      int i5 = localImage2._id;
      CachedThumbnails.cacheThumbnail(localCommonAppListActivity5, i5, localDrawable2);
      if (CommonAppListActivity.access$0(this.this$0) == null)
        continue;
      CommonAppListActivity.access$0(this.this$0).notifyDataSetChanged();
      continue;
      try
      {
        this.this$0.showDialog(100);
        this.this$0.inflatingAppList = 0;
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity.8
 * JD-Core Version:    0.6.0
 */