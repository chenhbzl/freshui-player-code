package com.yingyonghui.market.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.online.UpdateService;

public class MarketAppWidgetProvider extends AppWidgetProvider
{
  public void onDisabled(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, UpdateService.class);
    paramContext.stopService(localIntent);
    Request localRequest = new Request(0L, 65560);
    MarketService.getServiceInstance(paramContext).getWidgetDisabledLog(localRequest);
  }

  public void onEnabled(Context paramContext)
  {
    Request localRequest = new Request(0L, 65559);
    MarketService.getServiceInstance(paramContext).getWidgetEnabledLog(localRequest);
  }

  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    Intent localIntent = new Intent(paramContext, UpdateService.class);
    paramContext.startService(localIntent);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.MarketAppWidgetProvider
 * JD-Core Version:    0.6.0
 */