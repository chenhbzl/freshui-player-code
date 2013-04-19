package com.yingyonghui.market.online;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.yingyonghui.market.AssetBrowser;
import com.yingyonghui.market.AssetInfoActivity;
import com.yingyonghui.market.install.InstallAppProgress;
import com.yingyonghui.market.util.DateUtil;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.Signature;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class AsyncTaskNotifier
{
  private static AsyncTaskNotifier instance = null;
  private Context mContext;

  @Signature({"Ljava/util/HashMap", "<", "Lcom/yingyonghui/market/online/AsyncTaskNotifier$TaskItem;", "Landroid/app/Notification;", ">;"})
  private HashMap mNotificationMap;
  private NotificationManager mNotificationMgr;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Lcom/yingyonghui/market/online/AsyncTaskNotifier$TaskItem;", ">;"})
  private HashMap mTasks;

  private AsyncTaskNotifier(Context paramContext)
  {
    HashMap localHashMap1 = new HashMap();
    this.mNotificationMap = localHashMap1;
    this.mContext = paramContext;
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    this.mNotificationMgr = localNotificationManager;
    HashMap localHashMap2 = new HashMap();
    this.mTasks = localHashMap2;
  }

  private String getDownloadingText(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0);
    StringBuilder localStringBuilder;
    for (String str = ""; ; str = localStringBuilder.toString())
    {
      return str;
      double d1 = paramInt2 * 1.0D;
      double d2 = paramInt1;
      int i = (int)(d1 / d2 * 0.0F);
      localStringBuilder = new StringBuilder();
      if (i > 100)
        i = 100;
      localStringBuilder.append(i);
      localStringBuilder.append(37);
    }
  }

  private String getInstallingText()
  {
    return this.mContext.getString(2131296276);
  }

  public static AsyncTaskNotifier getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new AsyncTaskNotifier(paramContext);
    return instance;
  }

  /** @deprecated */
  public int addNotificationItem(int paramInt1, String paramString, int paramInt2)
  {
    monitorenter;
    try
    {
      HashMap localHashMap = this.mTasks;
      Integer localInteger = Integer.valueOf(paramInt1);
      TaskItem localTaskItem = new TaskItem(paramInt2);
      localHashMap.put(localInteger, localTaskItem);
      monitorexit;
      return paramInt1;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void cancelAllNotification()
  {
    monitorenter;
    try
    {
      this.mTasks.clear();
      this.mNotificationMgr.cancelAll();
      this.mNotificationMap.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void cancelNotification(int paramInt)
  {
    monitorenter;
    try
    {
      HashMap localHashMap1 = this.mTasks;
      Integer localInteger1 = Integer.valueOf(paramInt);
      TaskItem localTaskItem = (TaskItem)localHashMap1.get(localInteger1);
      if (localTaskItem == null);
      while (true)
      {
        return;
        HashMap localHashMap2 = this.mTasks;
        Integer localInteger2 = Integer.valueOf(paramInt);
        localHashMap2.remove(localInteger2);
        this.mNotificationMgr.cancel(paramInt);
        if (this.mNotificationMap.get(localTaskItem) == null)
          continue;
        this.mNotificationMap.remove(localTaskItem);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void cancelTaskItem(int paramInt)
  {
    monitorenter;
    try
    {
      HashMap localHashMap = this.mTasks;
      Integer localInteger = Integer.valueOf(paramInt);
      TaskItem localTaskItem = (TaskItem)localHashMap.get(localInteger);
      if (localTaskItem != null)
        localTaskItem.canceled = 1;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public TaskItem getTaskItem(int paramInt)
  {
    monitorenter;
    try
    {
      HashMap localHashMap = this.mTasks;
      Integer localInteger = Integer.valueOf(paramInt);
      TaskItem localTaskItem = (TaskItem)localHashMap.get(localInteger);
      monitorexit;
      return localTaskItem;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void sendUpdateNumNotification(int paramInt)
  {
    monitorenter;
    if (paramInt <= 0);
    try
    {
      this.mNotificationMgr.cancel(99999999);
      while (true)
      {
        return;
        String str1 = this.mContext.getString(2131296342);
        String str2 = this.mContext.getString(2131296343);
        String str3 = this.mContext.getString(2131296257);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = str3;
        Integer localInteger = Integer.valueOf(paramInt);
        arrayOfObject[1] = localInteger;
        String str4 = String.format(str2, arrayOfObject);
        long l = System.currentTimeMillis();
        Notification localNotification = new Notification(2130837530, str4, l);
        localNotification.number = paramInt;
        Intent localIntent = new Intent();
        String str5 = this.mContext.getPackageName();
        String str6 = AssetBrowser.class.getName();
        localIntent.setClassName(str5, str6);
        localIntent.putExtra("tabId", 4);
        PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, 0, localIntent, 134217728);
        localNotification.contentIntent = localPendingIntent;
        Context localContext = this.mContext;
        localNotification.setLatestEventInfo(localContext, str1, str4, localPendingIntent);
        this.mNotificationMgr.notify(99999999, localNotification);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void updateActiveNotification(int paramInt1, int paramInt2)
  {
    monitorenter;
    while (true)
    {
      TaskItem localTaskItem;
      try
      {
        HashMap localHashMap = this.mTasks;
        Integer localInteger = Integer.valueOf(paramInt1);
        localTaskItem = (TaskItem)localHashMap.get(localInteger);
        if (localTaskItem == null)
          return;
        localTaskItem.setProgress(paramInt2);
        if (this.mNotificationMap.get(localTaskItem) == null)
        {
          localNotification = new Notification();
          this.mNotificationMap.put(localTaskItem, localNotification);
          localNotification.icon = 17301633;
          int i = localNotification.flags | 0x2;
          localNotification.flags = i;
          int j = localNotification.flags | 0x20;
          localNotification.flags = j;
          String str1 = this.mContext.getPackageName();
          RemoteViews localRemoteViews = new RemoteViews(str1, 2130903107);
          String str2 = localTaskItem.label;
          CharSequence localCharSequence = this.mContext.getText(2131296412);
          localRemoteViews.setTextViewText(2131427385, localCharSequence);
          localRemoteViews.setTextViewText(2131427417, str2);
          int k = 2131427470;
          int m = localTaskItem.totalTotal;
          int n = localTaskItem.totalCurrent;
          if (localTaskItem.totalTotal != -1)
            break label324;
          int i1 = 1;
          localRemoteViews.setProgressBar(k, m, n, i1);
          int i3 = localTaskItem.totalTotal;
          int i4 = localTaskItem.totalCurrent;
          String str3 = getDownloadingText(i3, i4);
          int i5 = localTaskItem.totalTotal;
          int i6 = localTaskItem.totalCurrent;
          String str4 = getDownloadingText(i5, i6);
          localRemoteViews.setTextViewText(2131427524, str4);
          localRemoteViews.setImageViewResource(2131427523, 17301633);
          localNotification.contentView = localRemoteViews;
          this.mNotificationMgr.notify(paramInt1, localNotification);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Notification localNotification = (Notification)this.mNotificationMap.get(localTaskItem);
      continue;
      label324: int i2 = 0;
    }
  }

  /** @deprecated */
  public void updateDownloadCompletedNotification(int paramInt, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      TaskItem localTaskItem;
      RemoteViews localRemoteViews;
      try
      {
        HashMap localHashMap1 = this.mTasks;
        Integer localInteger1 = Integer.valueOf(paramInt);
        localTaskItem = (TaskItem)localHashMap1.get(localInteger1);
        if (localTaskItem != null)
          continue;
        DownloadService.flagCancelling = 0;
        return;
        if (this.mNotificationMap.get(localTaskItem) == null)
        {
          localNotification = new Notification();
          this.mNotificationMap.put(localTaskItem, localNotification);
          localNotification.icon = 17301634;
          int i = localNotification.flags | 0x2;
          localNotification.flags = i;
          String str1 = this.mContext.getPackageName();
          localRemoteViews = new RemoteViews(str1, 2130903107);
          String str2 = localTaskItem.label;
          if (!paramBoolean)
            break label311;
          String str3 = getInstallingText();
          localRemoteViews.setTextViewText(2131427385, str3);
          localRemoteViews.setTextViewText(2131427417, str2);
          if (!paramBoolean)
            break label335;
          localRemoteViews.setProgressBar(2131427470, 0, 0, 1);
          localRemoteViews.setTextViewText(2131427524, "");
          localRemoteViews.setImageViewResource(2131427523, 17301634);
          localNotification.contentView = localRemoteViews;
          this.mNotificationMgr.notify(paramInt, localNotification);
          if (paramBoolean)
            continue;
          Toast.makeText(this.mContext, 2131296415, 0).show();
          if (this.mNotificationMap.get(localTaskItem) == null)
            continue;
          this.mNotificationMap.remove(localTaskItem);
          HashMap localHashMap2 = this.mTasks;
          Integer localInteger2 = Integer.valueOf(paramInt);
          if (localHashMap2.get(localInteger2) == null)
            continue;
          HashMap localHashMap3 = this.mTasks;
          Integer localInteger3 = Integer.valueOf(paramInt);
          localHashMap3.remove(localInteger3);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Notification localNotification = (Notification)this.mNotificationMap.get(localTaskItem);
      continue;
      label311: CharSequence localCharSequence = this.mContext.getText(2131296415);
      localRemoteViews.setTextViewText(2131427385, localCharSequence);
      continue;
      label335: localRemoteViews.setImageViewResource(2131427523, 17301624);
    }
  }

  /** @deprecated */
  public void updateDownloadCompletedNotification2(int paramInt, boolean paramBoolean, String paramString1, String paramString2)
  {
    monitorenter;
    while (true)
    {
      TaskItem localTaskItem;
      Notification localNotification;
      RemoteViews localRemoteViews;
      Intent localIntent;
      try
      {
        HashMap localHashMap = this.mTasks;
        Integer localInteger = Integer.valueOf(paramInt);
        localTaskItem = (TaskItem)localHashMap.get(localInteger);
        if (localTaskItem != null)
          continue;
        DownloadService.flagCancelling = 0;
        return;
        localNotification = new Notification();
        int i = localNotification.flags | 0x10;
        localNotification.flags = i;
        long l1 = System.currentTimeMillis();
        localNotification.when = l1;
        String str1 = this.mContext.getPackageName();
        localRemoteViews = new RemoteViews(str1, 2130903106);
        String str2 = localTaskItem.label;
        localRemoteViews.setTextViewText(2131427417, str2);
        long l2 = localNotification.when;
        String str3 = DateUtil.format(new Date(l2), "HH:mm");
        localRemoteViews.setTextViewText(2131427430, str3);
        localIntent = new Intent();
        localIntent.addFlags(268435456);
        if (!paramBoolean)
          break label405;
        localNotification.icon = 2130837699;
        localRemoteViews.setImageViewResource(2131427520, 2130837699);
        String str4 = this.mContext.getString(2131296277);
        localRemoteViews.setTextViewText(2131427521, str4);
        if (GlobalUtil.isSystemApp(this.mContext.getPackageManager()))
        {
          Context localContext1 = this.mContext;
          localIntent.setClass(localContext1, InstallAppProgress.class);
          localIntent.putExtra("_id", paramInt);
          String str5 = localTaskItem.label;
          localIntent.putExtra("title", str5);
          localIntent.putExtra("packageName", paramString2);
          Uri localUri1 = Uri.fromFile(new File(paramString1));
          localIntent.setDataAndType(localUri1, "application/vnd.android.package-archive");
          localNotification.contentView = localRemoteViews;
          PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, 0, localIntent, 0);
          localNotification.contentIntent = localPendingIntent;
          this.mNotificationMgr.notify(paramInt, localNotification);
          if (!paramBoolean)
            break label548;
          if (GlobalUtil.isSystemApp(this.mContext.getPackageManager()))
            continue;
          cancelNotification(paramInt);
          this.mContext.startActivity(localIntent);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      continue;
      label405: localNotification.icon = 17301642;
      localRemoteViews.setImageViewResource(2131427520, 17301624);
      String str6 = this.mContext.getString(2131296415);
      localRemoteViews.setTextViewText(2131427521, str6);
      Context localContext2 = this.mContext;
      localIntent.setClass(localContext2, AssetInfoActivity.class);
      localIntent.putExtra("_id", paramInt);
      String str7 = localTaskItem.label;
      localIntent.putExtra("title", str7);
      localIntent.putExtra("packageName", paramString2);
      localIntent.putExtra("from", "Notification");
      Uri localUri2 = Uri.parse("market://package/" + paramString2);
      localIntent.setData(localUri2);
      continue;
      label548: Toast.makeText(this.mContext, 2131296415, 0).show();
    }
  }

  /** @deprecated */
  public void updateInstallCompletedNotification(int paramInt, boolean paramBoolean, String paramString)
  {
    monitorenter;
    while (true)
    {
      Notification localNotification;
      RemoteViews localRemoteViews;
      try
      {
        HashMap localHashMap = this.mTasks;
        Integer localInteger = Integer.valueOf(paramInt);
        TaskItem localTaskItem = (TaskItem)localHashMap.get(localInteger);
        if (localTaskItem == null)
          return;
        localNotification = new Notification();
        if (paramBoolean)
        {
          localNotification.icon = 2130837699;
          int i = localNotification.flags | 0x10;
          localNotification.flags = i;
          String str1 = this.mContext.getPackageName();
          localRemoteViews = new RemoteViews(str1, 2130903107);
          String str2 = localTaskItem.label;
          if (!paramBoolean)
            break label333;
          CharSequence localCharSequence1 = this.mContext.getText(2131296416);
          localRemoteViews.setTextViewText(2131427385, localCharSequence1);
          localRemoteViews.setTextViewText(2131427417, str2);
          if (!paramBoolean)
            break label357;
          localRemoteViews.setImageViewResource(2131427523, 2130837699);
          localNotification.contentView = localRemoteViews;
          if (!paramBoolean)
            break label370;
          localIntent = this.mContext.getPackageManager().getLaunchIntentForPackage(paramString);
          if (localIntent != null)
            continue;
          localIntent = new Intent();
          String str3 = this.mContext.getPackageName();
          String str4 = AssetInfoActivity.class.getName();
          localIntent.setClassName(str3, str4);
          localIntent.putExtra("_id", paramInt);
          String str5 = localTaskItem.label;
          localIntent.putExtra("title", str5);
          PendingIntent localPendingIntent = PendingIntent.getActivity(this.mContext, 0, localIntent, 134217728);
          localNotification.contentIntent = localPendingIntent;
          Context localContext = this.mContext;
          if (!paramBoolean)
            break label409;
          localCharSequence2 = this.mContext.getText(2131296416);
          localNotification.setLatestEventInfo(localContext, str2, localCharSequence2, localPendingIntent);
          this.mNotificationMgr.notify(paramInt, localNotification);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = 17301624;
      localNotification.icon = j;
      continue;
      label333: CharSequence localCharSequence3 = this.mContext.getText(2131296417);
      localRemoteViews.setTextViewText(2131427385, localCharSequence3);
      continue;
      label357: localRemoteViews.setImageViewResource(2131427523, 17301624);
      continue;
      label370: Intent localIntent = new Intent();
      String str6 = this.mContext.getPackageName();
      String str7 = AssetInfoActivity.class.getName();
      localIntent.setClassName(str6, str7);
      continue;
      label409: CharSequence localCharSequence2 = this.mContext.getText(2131296417);
    }
  }

  /** @deprecated */
  public void updatePreActiveNotification(int paramInt)
  {
    monitorenter;
    while (true)
    {
      TaskItem localTaskItem;
      try
      {
        HashMap localHashMap = this.mTasks;
        Integer localInteger = Integer.valueOf(paramInt);
        localTaskItem = (TaskItem)localHashMap.get(localInteger);
        if (localTaskItem == null)
          return;
        if (this.mNotificationMap.get(localTaskItem) == null)
        {
          localNotification = new Notification();
          this.mNotificationMap.put(localTaskItem, localNotification);
          localNotification.icon = 17301633;
          int i = localNotification.flags | 0x2;
          localNotification.flags = i;
          int j = localNotification.flags | 0x20;
          localNotification.flags = j;
          String str1 = localTaskItem.label;
          String str2 = this.mContext.getPackageName();
          RemoteViews localRemoteViews = new RemoteViews(str2, 2130903107);
          CharSequence localCharSequence1 = this.mContext.getText(2131296411);
          localRemoteViews.setTextViewText(2131427385, localCharSequence1);
          localRemoteViews.setTextViewText(2131427417, str1);
          localRemoteViews.setProgressBar(2131427470, 0, 0, 1);
          int k = localTaskItem.totalTotal;
          int m = localTaskItem.totalCurrent;
          String str3 = getDownloadingText(k, m);
          localRemoteViews.setTextViewText(2131427524, str3);
          localRemoteViews.setImageViewResource(2131427523, 17301633);
          String str4 = DownloadService.getInstance(this.mContext).getPackageName(paramInt);
          Intent localIntent = new Intent();
          String str5 = this.mContext.getPackageName();
          String str6 = AssetInfoActivity.class.getName();
          localIntent.setClassName(str5, str6);
          localIntent.putExtra("_id", paramInt);
          String str7 = localTaskItem.label;
          localIntent.putExtra("title", str7);
          int n = localTaskItem.totalTotal;
          localIntent.putExtra("size", n);
          localIntent.putExtra("pkgName", str4);
          localIntent.putExtra("from", "Notification");
          localIntent.setFlags(268435456);
          AssetInfoActivity.DOWNLOADING_ASSET_ID = paramInt;
          PendingIntent localPendingIntent1 = PendingIntent.getActivity(this.mContext, paramInt, localIntent, 134217728);
          localNotification.contentIntent = localPendingIntent1;
          localNotification.contentView = localRemoteViews;
          CharSequence localCharSequence2 = this.mContext.getText(2131296411);
          localNotification.tickerText = localCharSequence2;
          Context localContext = this.mContext;
          int i1 = localTaskItem.totalTotal;
          int i2 = localTaskItem.totalCurrent;
          String str8 = getDownloadingText(i1, i2);
          PendingIntent localPendingIntent2 = PendingIntent.getActivity(this.mContext, paramInt, localIntent, 134217728);
          localNotification.setLatestEventInfo(localContext, str1, str8, localPendingIntent2);
          this.mNotificationMgr.notify(paramInt, localNotification);
          Toast.makeText(this.mContext, 2131296413, 0).show();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Notification localNotification = (Notification)this.mNotificationMap.get(localTaskItem);
    }
  }

  public class TaskItem
  {
    public boolean canceled = 0;
    public int totalCurrent = 0;
    public int totalTotal = 0;

    public TaskItem(int arg2)
    {
      int i;
      this.totalTotal = i;
    }

    void setProgress(int paramInt)
    {
      this.totalCurrent = paramInt;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.AsyncTaskNotifier
 * JD-Core Version:    0.6.0
 */