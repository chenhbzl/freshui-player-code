package com.yingyonghui.market.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceUtil
{
  public static final int SDK_VERSION_1_5 = 3;
  public static final int SDK_VERSION_1_6 = 4;
  public static final int SDK_VERSION_2_0 = 5;
  public static final int SDK_VERSION_2_0_1 = 6;
  public static final int SDK_VERSION_2_1 = 7;
  public static final int SDK_VERSION_2_2 = 8;
  public static final int SDK_VERSION_2_3 = 9;
  public static final int SDK_VERSION_2_3_3 = 10;
  public static final int SDK_VERSION_3_0 = 11;

  public static String getDeviceModel()
  {
    return StringUtil.makeSafe(Build.MODEL);
  }

  public static String getIMEI(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
  }

  public static String getIMSI(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
  }

  public static String getSDKVersion()
  {
    return Build.VERSION.SDK;
  }

  public static int getSDKVersionInt()
  {
    try
    {
      i = Integer.parseInt(Build.VERSION.SDK);
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = 0;
    }
  }

  public static float getScreenDensity(Context paramContext)
  {
    return paramContext.getApplicationContext().getResources().getDisplayMetrics().density;
  }

  public static Rect getScreenRect(Context paramContext)
  {
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    int i = localDisplay.getWidth();
    int j = localDisplay.getHeight();
    return new Rect(0, 0, i, j);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.DeviceUtil
 * JD-Core Version:    0.6.0
 */