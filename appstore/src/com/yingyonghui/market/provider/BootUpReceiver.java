package com.yingyonghui.market.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.InternetManager;

public class BootUpReceiver extends BroadcastReceiver
{
  private static final int SEND_INSTALL_LOG = 100;
  Context mContext;
  private Handler mHandler;

  private void addInstallLogRequest()
  {
    Request localRequest = new Request(0L, 65562);
    String str = "";
    try
    {
      Bundle localBundle = this.mContext.getPackageManager().getApplicationInfo("com.yingyonghui.market", 128).metaData;
      if (localBundle != null)
        str = localBundle.getString("UMENG_CHANNEL");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str;
      localRequest.setData(arrayOfObject);
      BootUpReceiver.2 local2 = new BootUpReceiver.2(this, localRequest);
      localRequest.addObserver(local2);
      MarketService.getServiceInstance(this.mContext).sendInstallLog(localRequest);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void initHandlerInUIThread()
  {
    BootUpReceiver.1 local1 = new BootUpReceiver.1(this);
    this.mHandler = local1;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.mContext = paramContext;
    initHandlerInUIThread();
    try
    {
      if ((paramContext.getSharedPreferences("com.yingyonghui.market_preferences", 0).getBoolean("install_log", 1)) && (InternetManager.hasInternet(paramContext)))
        addInstallLogRequest();
      label39: return;
    }
    catch (Throwable localThrowable)
    {
      break label39;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.BootUpReceiver
 * JD-Core Version:    0.6.0
 */