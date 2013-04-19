package com.yingyonghui.market.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class BootUpReceiver$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 100:
    }
    while (true)
    {
      return;
      SharedPreferences.Editor localEditor = this.this$0.mContext.getSharedPreferences("com.yingyonghui.market_preferences", 0).edit();
      localEditor.putBoolean("install_log", 0);
      localEditor.commit();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.BootUpReceiver.1
 * JD-Core Version:    0.6.0
 */