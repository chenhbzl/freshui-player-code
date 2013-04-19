package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Gallery;
import dalvik.annotation.EnclosingMethod;
import java.util.ArrayList;

@EnclosingMethod
class AssetInfoDetailActivity$6 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 2:
    case 1:
    }
    while (true)
    {
      return;
      AssetInfoDetailActivity.access$10(this.this$0).setVisibility(8);
      AssetInfoDetailActivity.access$11(this.this$0).setVisibility(0);
      AssetInfoDetailActivity localAssetInfoDetailActivity1 = this.this$0;
      ArrayList localArrayList = (ArrayList)paramMessage.obj;
      AssetInfoDetailActivity.access$12(localAssetInfoDetailActivity1, localArrayList);
      if ((AssetInfoDetailActivity.access$5(this.this$0) != null) && (AssetInfoDetailActivity.access$5(this.this$0).size() > 0))
      {
        AssetInfoDetailActivity localAssetInfoDetailActivity2 = this.this$0;
        int i = AssetInfoDetailActivity.access$13(this.this$0);
        Drawable localDrawable = (Drawable)AssetInfoDetailActivity.access$5(this.this$0).get(0);
        FileManager.writeFirstScreetShotToFileSystem(localAssetInfoDetailActivity2, i, localDrawable);
      }
      AssetInfoDetailActivity localAssetInfoDetailActivity3 = this.this$0;
      AssetInfoDetailActivity localAssetInfoDetailActivity4 = this.this$0;
      AssetInfoDetailActivity localAssetInfoDetailActivity5 = this.this$0;
      AssetInfoDetailActivity.ScreenImageAdapter localScreenImageAdapter1 = new AssetInfoDetailActivity.ScreenImageAdapter(localAssetInfoDetailActivity4, localAssetInfoDetailActivity5);
      AssetInfoDetailActivity.access$14(localAssetInfoDetailActivity3, localScreenImageAdapter1);
      Gallery localGallery = AssetInfoDetailActivity.access$15(this.this$0);
      AssetInfoDetailActivity.ScreenImageAdapter localScreenImageAdapter2 = AssetInfoDetailActivity.access$8(this.this$0);
      localGallery.setAdapter(localScreenImageAdapter2);
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
 * Qualified Name:     com.yingyonghui.market.AssetInfoDetailActivity.6
 * JD-Core Version:    0.6.0
 */