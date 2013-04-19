package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Process;
import com.yingyonghui.market.online.DataCacheServiceAgent;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SplashActivity$6
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    try
    {
      DataCacheServiceAgent.setInstanceNull();
      FileManager.deleteFile(this.this$0, "top_recommend");
      Process.killProcess(Process.myPid());
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SplashActivity.6
 * JD-Core Version:    0.6.0
 */