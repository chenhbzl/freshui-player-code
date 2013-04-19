package com.yingyonghui.market;

import android.content.Context;
import android.os.Handler;
import java.text.NumberFormat;

public class WifiDownloadManager
{
  private static NumberFormat instance = NumberFormat.getInstance();

  public static Handler initHandlerInUIThread(Context paramContext)
  {
    return new WifiDownloadManager.1(paramContext);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.WifiDownloadManager
 * JD-Core Version:    0.6.0
 */