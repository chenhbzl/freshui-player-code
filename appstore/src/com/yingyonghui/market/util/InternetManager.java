package com.yingyonghui.market.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetManager
{
  public static boolean hasInternet(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()));
    for (int i = 0; ; i = 1)
      return i;
  }

  public static boolean hasMoreThanOneConnection(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i;
    if (localConnectivityManager == null)
      i = 0;
    while (true)
    {
      return i;
      NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      int j = 0;
      int k = 0;
      while (true)
      {
        int m = arrayOfNetworkInfo.length;
        if (k >= m)
        {
          if (j <= 1)
            break label76;
          i = 1;
          break;
        }
        if (arrayOfNetworkInfo[k].isConnected())
          int n = j + 1;
        k += 1;
      }
      label76: i = 0;
    }
  }

  public static boolean isWiFiActive(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
    NetworkInfo[] arrayOfNetworkInfo;
    int i;
    if (localConnectivityManager != null)
    {
      arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      if (arrayOfNetworkInfo != null)
        i = 0;
    }
    while (true)
    {
      int j = arrayOfNetworkInfo.length;
      if (i >= j);
      for (int k = 0; ; k = 1)
      {
        return k;
        if ((!arrayOfNetworkInfo[i].getTypeName().equals("WIFI")) || (!arrayOfNetworkInfo[i].isConnected()))
          break;
      }
      i += 1;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.InternetManager
 * JD-Core Version:    0.6.0
 */