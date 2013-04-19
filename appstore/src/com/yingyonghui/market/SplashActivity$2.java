package com.yingyonghui.market;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SplashActivity$2 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 300:
    case 400:
    }
    while (true)
    {
      return;
      SharedPreferences.Editor localEditor = this.this$0.getSharedPreferences("com.yingyonghui.market_preferences", 0).edit();
      localEditor.putBoolean("install_log", 0);
      localEditor.commit();
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
 * Qualified Name:     com.yingyonghui.market.SplashActivity.2
 * JD-Core Version:    0.6.0
 */