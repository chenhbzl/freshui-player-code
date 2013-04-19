package com.yingyonghui.market;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.model.NewsContent;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.EnclosingMethod;
import java.util.HashMap;

@EnclosingMethod
class NewsContentActivity$3 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      NewsContent localNewsContent = (NewsContent)paramMessage.obj;
      NewsContentActivity.access$5(this.this$0, localNewsContent);
      NewsContentActivity.access$6(this.this$0).setVisibility(0);
      NewsContentActivity.access$7(this.this$0).setVisibility(8);
      NewsContentActivity.access$8(this.this$0).setVisibility(8);
      continue;
      Image2 localImage21 = (Image2)paramMessage.obj;
      if (localImage21.icon == null)
        continue;
      HashMap localHashMap1 = NewsContentActivity.access$9(this.this$0);
      Integer localInteger1 = Integer.valueOf(localImage21._id);
      View localView1 = (View)localHashMap1.get(localInteger1);
      if (localView1 == null)
        continue;
      ImageView localImageView1 = (ImageView)localView1.findViewById(2131427530);
      if ((localImageView1 == null) || (localImage21.icon == null))
        continue;
      Rect localRect = DeviceUtil.getScreenRect(this.this$0);
      int i = localRect.right;
      int j = localRect.bottom;
      int k = Math.min(i, j);
      float f1 = k - 30;
      float f2 = localImage21.icon.getIntrinsicWidth();
      float f3 = f1 / f2;
      int m = k - 20;
      int n = (int)(localImage21.icon.getIntrinsicHeight() * f3);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(m, n);
      localImageView1.setLayoutParams(localLayoutParams);
      Drawable localDrawable1 = localImage21.icon;
      localImageView1.setImageDrawable(localDrawable1);
      localImageView1.getDrawable().setCallback(null);
      continue;
      Object[] arrayOfObject = (Object[])paramMessage.obj;
      int i1 = arrayOfObject.length;
      AssetDetail localAssetDetail = (AssetDetail)arrayOfObject[0];
      int i2 = ((Integer)arrayOfObject[1]).intValue();
      if (localAssetDetail == null)
        continue;
      HashMap localHashMap2 = NewsContentActivity.access$9(this.this$0);
      Integer localInteger2 = Integer.valueOf(i2);
      View localView2 = (View)localHashMap2.get(localInteger2);
      if (localView2 == null)
        continue;
      NewsContentActivity.access$10(this.this$0, localView2, localAssetDetail);
      NewsContentActivity.access$11(this.this$0).put(localAssetDetail, localView2);
      DownloadService localDownloadService = DownloadService.getInstance(this.this$0);
      int i3 = localAssetDetail._id;
      Handler localHandler = NewsContentActivity.access$12(this.this$0);
      localDownloadService.setHandler(i3, localHandler);
      continue;
      Image2 localImage22 = (Image2)paramMessage.obj;
      if (localImage22.icon == null)
        continue;
      HashMap localHashMap3 = NewsContentActivity.access$9(this.this$0);
      Integer localInteger3 = Integer.valueOf(localImage22._id);
      localView2 = (View)localHashMap3.get(localInteger3);
      if (localView2 == null)
        continue;
      ImageView localImageView2 = (ImageView)localView2.findViewById(2131427373);
      if (localImageView2 == null)
        continue;
      Drawable localDrawable2 = localImage22.icon;
      localImageView2.setImageDrawable(localDrawable2);
      localImageView2.getDrawable().setCallback(null);
      continue;
      try
      {
        NewsContentActivity.access$7(this.this$0).setVisibility(0);
        NewsContentActivity.access$6(this.this$0).setVisibility(8);
        NewsContentActivity.access$8(this.this$0).setVisibility(8);
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity.3
 * JD-Core Version:    0.6.0
 */