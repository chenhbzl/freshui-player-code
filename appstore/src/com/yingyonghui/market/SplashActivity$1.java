package com.yingyonghui.market;

import android.content.Intent;
import android.widget.RatingBar;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SplashActivity$1
  implements Runnable
{
  public void run()
  {
    while (true)
    {
      if ((SplashActivity.access$0(this.this$0)) && (SplashActivity.access$1(this.this$0)))
      {
        SplashActivity.access$2(this.this$0).setRating(5.0F);
        Intent localIntent = new Intent();
        SplashActivity localSplashActivity1 = this.this$0;
        localIntent.setClass(localSplashActivity1, AssetBrowser.class);
        this.this$0.startActivity(localIntent);
        this.this$0.finish();
        return;
      }
      this.this$0.progress = 1.0F;
      if (SplashActivity.access$0(this.this$0))
      {
        SplashActivity localSplashActivity2 = this.this$0;
        float f1 = localSplashActivity2.progress + 2.0F;
        localSplashActivity2.progress = f1;
      }
      if (SplashActivity.access$1(this.this$0))
      {
        SplashActivity localSplashActivity3 = this.this$0;
        float f2 = localSplashActivity3.progress + 2.0F;
        localSplashActivity3.progress = f2;
      }
      RatingBar localRatingBar = SplashActivity.access$2(this.this$0);
      float f3 = this.this$0.progress;
      localRatingBar.setRating(f3);
      try
      {
        if (this.this$0.progress == 5.0F)
          continue;
        Thread.sleep(200L);
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SplashActivity.1
 * JD-Core Version:    0.6.0
 */