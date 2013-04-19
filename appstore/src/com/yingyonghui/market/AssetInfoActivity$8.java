package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yingyonghui.market.model.Asset;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class AssetInfoActivity$8 extends Handler
{
  private int mCurrentBytes = 0;
  private int mTotalBytes = 0;

  /** @deprecated */
  public void handleMessage(Message paramMessage)
  {
    monitorenter;
    while (true)
    {
      try
      {
        int i = paramMessage.arg1;
        if (AssetInfoActivity.access$15(this.this$0))
          continue;
        int j = AssetInfoActivity.access$6(this.this$0);
        if (j != i)
          return;
        switch (paramMessage.what)
        {
        default:
          break;
        case 1:
          if (paramMessage.arg2 != 1)
            break label405;
          AssetInfoActivity.access$2(this.this$0, 2);
          AssetInfoActivity.access$21(this.this$0, 1);
          continue;
        case 100:
        case 200:
        case 300:
        }
      }
      finally
      {
        monitorexit;
      }
      if (AssetInfoActivity.access$0(this.this$0) == null)
        continue;
      int k = AssetInfoActivity.access$0(this.this$0).size;
      this.mTotalBytes = k;
      AssetInfoActivity.access$16(this.this$0).setIndeterminate(1);
      AssetInfoActivity.access$2(this.this$0, 5);
      AssetInfoActivity.access$17(this.this$0);
      continue;
      if ((AssetInfoActivity.access$0(this.this$0) == null) || (AssetInfoActivity.access$18(this.this$0)))
        continue;
      int m = AssetInfoActivity.access$0(this.this$0).size;
      this.mTotalBytes = m;
      if (this.mTotalBytes == 0)
        continue;
      AssetInfoActivity.access$2(this.this$0, 5);
      int n = paramMessage.arg2;
      this.mCurrentBytes = n;
      AssetInfoActivity.access$16(this.this$0).setIndeterminate(0);
      ProgressBar localProgressBar = AssetInfoActivity.access$16(this.this$0);
      float f1 = this.mCurrentBytes;
      float f2 = this.mTotalBytes;
      int i1 = (int)(f1 / f2 * 100.0F);
      localProgressBar.setProgress(i1);
      TextView localTextView = AssetInfoActivity.access$19(this.this$0);
      AssetInfoActivity localAssetInfoActivity = this.this$0;
      int i2 = this.mTotalBytes;
      int i3 = this.mCurrentBytes;
      String str = AssetInfoActivity.access$20(localAssetInfoActivity, i2, i3);
      localTextView.setText(str);
      continue;
      if (((Boolean)paramMessage.obj).booleanValue())
      {
        AssetInfoActivity.access$16(this.this$0).setIndeterminate(1);
        AssetInfoActivity.access$19(this.this$0).setText(2131296276);
        AssetInfoActivity.access$2(this.this$0, 6);
        continue;
      }
      AssetInfoActivity.access$19(this.this$0).setText(2131296415);
      continue;
      label405: AssetInfoActivity.access$19(this.this$0).setText(2131296417);
      AssetInfoActivity.access$2(this.this$0, 0);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoActivity.8
 * JD-Core Version:    0.6.0
 */