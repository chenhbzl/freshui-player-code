package com.yingyonghui.market.online;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.yingyonghui.market.FileManager;
import com.yingyonghui.market.WifiDownloadManager;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.Image2;
import com.yingyonghui.market.provider.MarketAppWidgetProvider;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.Signature;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpdateService extends Service
{
  private static final int DAILY_RECOMMEND_APP_ID = 95;
  private static final int DOWNLOAD = 0;
  private static final int INSTALL = 2;
  private static final int INSTALLED = 3;
  private static final int UPDATE = 1;
  private MarketServiceAgent agent;
  private Image2 icon;
  private final BroadcastReceiver mBroadcastReceiver;
  private Handler mDownloadStatusHandler;
  private Handler mWifiHandler;
  private Asset recommendApp;

  @Signature({"Ljava/util/List", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  private List recommendAppList;

  public UpdateService()
  {
    ArrayList localArrayList = new ArrayList();
    this.recommendAppList = localArrayList;
    UpdateService.1 local1 = new UpdateService.1(this);
    this.mBroadcastReceiver = local1;
  }

  private void addDownloadAndInstallRequest()
  {
    Request localRequest2;
    if (this.recommendApp != null)
    {
      Request localRequest1 = new Request(0L, 65541);
      int i = this.recommendApp.size;
      String str1 = this.recommendApp.pkgName;
      String str2 = this.recommendApp.title;
      Object[] arrayOfObject1 = new Object[4];
      Integer localInteger1 = Integer.valueOf(this.recommendApp._id);
      arrayOfObject1[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject1[1] = localInteger2;
      arrayOfObject1[2] = str1;
      arrayOfObject1[3] = str2;
      localRequest1.setData(arrayOfObject1);
      Handler localHandler = this.mWifiHandler;
      localRequest1.addWifiObserver(localHandler);
      MarketService.getServiceInstance(this).getAppContentStream(localRequest1);
      localRequest2 = new Request(0L, 65553);
      if (this.recommendApp.installed != 2)
        break label200;
    }
    label200: for (String str3 = "update"; ; str3 = "install")
    {
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = str3;
      arrayOfObject2[1] = "Widget";
      Integer localInteger3 = Integer.valueOf(this.recommendApp._id);
      arrayOfObject2[2] = localInteger3;
      localRequest2.setData(arrayOfObject2);
      MarketService.getServiceInstance(this).getInstallUpdateLog(localRequest2);
      return;
    }
  }

  private void destroy()
  {
    if (this.icon != null)
      ((BitmapDrawable)this.icon.icon).getBitmap().recycle();
    this.icon = null;
    this.recommendApp = null;
    unregisterIntentReceivers();
  }

  private void initHandler()
  {
    UpdateService.2 local2 = new UpdateService.2(this);
    this.mDownloadStatusHandler = local2;
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter1 = new IntentFilter("download_recommend_app");
    localIntentFilter1.addAction("widget_asset_info");
    BroadcastReceiver localBroadcastReceiver1 = this.mBroadcastReceiver;
    registerReceiver(localBroadcastReceiver1, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter2.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver2 = this.mBroadcastReceiver;
    registerReceiver(localBroadcastReceiver2, localIntentFilter2);
  }

  private void startInstall(File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    Uri localUri = Uri.fromFile(paramFile);
    localIntent.setDataAndType(localUri, "application/vnd.android.package-archive");
    localIntent.addFlags(268435456);
    startActivity(localIntent);
  }

  private void unregisterIntentReceivers()
  {
    try
    {
      BroadcastReceiver localBroadcastReceiver = this.mBroadcastReceiver;
      unregisterReceiver(localBroadcastReceiver);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void updateWidget(int paramInt)
  {
    String str1 = getPackageName();
    RemoteViews localRemoteViews = new RemoteViews(str1, 2130903083);
    try
    {
      Intent localIntent1 = new Intent("widget_asset_info");
      PendingIntent localPendingIntent1 = PendingIntent.getBroadcast(this, 0, localIntent1, 0);
      localRemoteViews.setOnClickPendingIntent(2131427472, localPendingIntent1);
      String str2 = this.recommendApp.title;
      localRemoteViews.setTextViewText(2131427478, str2);
      String str3 = this.recommendApp.shortDescription;
      localRemoteViews.setTextViewText(2131427480, str3);
      Bitmap localBitmap = ((BitmapDrawable)this.icon.icon).getBitmap();
      localRemoteViews.setImageViewBitmap(2131427473, localBitmap);
      if (paramInt != 3)
      {
        Intent localIntent2 = new Intent("download_recommend_app");
        localPendingIntent2 = PendingIntent.getBroadcast(this, 0, localIntent2, 0);
        switch (paramInt)
        {
        default:
        case 0:
          while (true)
          {
            ComponentName localComponentName = new ComponentName(this, MarketAppWidgetProvider.class);
            AppWidgetManager.getInstance(this).updateAppWidget(localComponentName, localRemoteViews);
            return;
            i = 2131427474;
            j = 0;
            localRemoteViews.setViewVisibility(i, j);
            localRemoteViews.setViewVisibility(2131427476, 8);
            localRemoteViews.setViewVisibility(2131427477, 8);
            localRemoteViews.setViewVisibility(2131427475, 8);
            localRemoteViews.setOnClickPendingIntent(2131427474, localPendingIntent2);
          }
        case 1:
        case 2:
        }
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        PendingIntent localPendingIntent2;
        localThrowable.printStackTrace();
        continue;
        int i = 2131427474;
        int j = 8;
        localRemoteViews.setViewVisibility(i, j);
        localRemoteViews.setViewVisibility(2131427476, 0);
        localRemoteViews.setViewVisibility(2131427477, 8);
        localRemoteViews.setViewVisibility(2131427475, 8);
        localRemoteViews.setOnClickPendingIntent(2131427476, localPendingIntent2);
        continue;
        localRemoteViews.setViewVisibility(2131427474, 8);
        localRemoteViews.setViewVisibility(2131427476, 8);
        localRemoteViews.setViewVisibility(2131427477, 0);
        localRemoteViews.setViewVisibility(2131427475, 8);
        localRemoteViews.setOnClickPendingIntent(2131427477, localPendingIntent2);
        continue;
        localRemoteViews.setViewVisibility(2131427474, 8);
        localRemoteViews.setViewVisibility(2131427476, 8);
        localRemoteViews.setViewVisibility(2131427477, 8);
        localRemoteViews.setViewVisibility(2131427475, 0);
      }
    }
  }

  public RemoteViews buildUpdate(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    RemoteViews localRemoteViews = new RemoteViews(str1, 2130903083);
    Handler localHandler1 = WifiDownloadManager.initHandlerInUIThread(paramContext);
    this.mWifiHandler = localHandler1;
    try
    {
      ArrayList localArrayList = this.agent.getRecommendApp(95, 0, 1);
      this.recommendAppList = localArrayList;
      if ((this.recommendAppList != null) && (this.recommendAppList.size() > 0))
      {
        Asset localAsset1 = (Asset)this.recommendAppList.get(0);
        this.recommendApp = localAsset1;
        Asset localAsset2 = this.recommendApp;
        String str2 = this.recommendApp.pkgName;
        int i = this.recommendApp.versionCode;
        int j = this.recommendApp._id;
        int k = PackageInstallInfo.getPackageInstalledStatus(this, str2, i, j);
        localAsset2.installed = k;
        Intent localIntent1 = new Intent("download_recommend_app");
        localPendingIntent1 = PendingIntent.getBroadcast(paramContext, 0, localIntent1, 0);
      }
      switch (this.recommendApp.installed)
      {
      default:
        Asset localAsset3 = this.recommendApp;
        if (FileManager.getFile(this, localAsset3) == null)
          break;
        localRemoteViews.setViewVisibility(2131427474, 8);
        localRemoteViews.setViewVisibility(2131427476, 8);
        localRemoteViews.setViewVisibility(2131427477, 0);
        localRemoteViews.setViewVisibility(2131427475, 8);
        localRemoteViews.setOnClickPendingIntent(2131427477, localPendingIntent1);
      case 1:
        while (true)
        {
          MarketServiceAgent localMarketServiceAgent = this.agent;
          int m = this.recommendApp._id;
          Image2 localImage2 = localMarketServiceAgent.getAppIcon2(m);
          this.icon = localImage2;
          String str3 = this.recommendApp.title;
          localRemoteViews.setTextViewText(2131427478, str3);
          String str4 = this.recommendApp.shortDescription;
          localRemoteViews.setTextViewText(2131427480, str4);
          Bitmap localBitmap = ((BitmapDrawable)this.icon.icon).getBitmap();
          localRemoteViews.setImageViewBitmap(2131427473, localBitmap);
          Intent localIntent2 = new Intent("widget_asset_info");
          PendingIntent localPendingIntent2 = PendingIntent.getBroadcast(paramContext, 0, localIntent2, 0);
          localRemoteViews.setOnClickPendingIntent(2131427472, localPendingIntent2);
          DownloadService localDownloadService = DownloadService.getInstance(this);
          int n = this.recommendApp._id;
          Handler localHandler2 = this.mDownloadStatusHandler;
          localDownloadService.setHandler(n, localHandler2);
          return localRemoteViews;
          localRemoteViews.setViewVisibility(2131427474, 8);
          localRemoteViews.setViewVisibility(2131427476, 8);
          localRemoteViews.setViewVisibility(2131427477, 8);
          localRemoteViews.setViewVisibility(2131427475, 0);
        }
      case 2:
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        PendingIntent localPendingIntent1;
        localThrowable.printStackTrace();
        continue;
        int i1 = 2131427474;
        int i2 = 8;
        localRemoteViews.setViewVisibility(i1, i2);
        localRemoteViews.setViewVisibility(2131427476, 0);
        localRemoteViews.setViewVisibility(2131427477, 8);
        localRemoteViews.setViewVisibility(2131427475, 8);
        localRemoteViews.setOnClickPendingIntent(2131427476, localPendingIntent1);
        continue;
        localRemoteViews.setViewVisibility(2131427474, 0);
        localRemoteViews.setViewVisibility(2131427476, 8);
        localRemoteViews.setViewVisibility(2131427477, 8);
        localRemoteViews.setViewVisibility(2131427475, 8);
        localRemoteViews.setOnClickPendingIntent(2131427474, localPendingIntent1);
      }
    }
  }

  public void finalize()
  {
    destroy();
    try
    {
      finalize();
      label8: return;
    }
    catch (Throwable localThrowable)
    {
      break label8;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
    onDestroy();
    unregisterIntentReceivers();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    MarketServiceAgent localMarketServiceAgent = MarketServiceAgent.getInstance(this);
    this.agent = localMarketServiceAgent;
    registerIntentReceivers();
    initHandler();
    RemoteViews localRemoteViews = buildUpdate(this);
    ComponentName localComponentName = new ComponentName(this, MarketAppWidgetProvider.class);
    AppWidgetManager.getInstance(this).updateAppWidget(localComponentName, localRemoteViews);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.UpdateService
 * JD-Core Version:    0.6.0
 */