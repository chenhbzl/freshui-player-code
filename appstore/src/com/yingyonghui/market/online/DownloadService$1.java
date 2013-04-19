package com.yingyonghui.market.online;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class DownloadService$1 extends Handler
{
  /** @deprecated */
  public void handleMessage(Message paramMessage)
  {
    monitorenter;
    while (true)
    {
      int i;
      try
      {
        i = paramMessage.arg1;
        HashMap localHashMap1 = DownloadService.access$0(this.this$0);
        Integer localInteger1 = Integer.valueOf(i);
        List localList = (List)localHashMap1.get(localInteger1);
        if (localList == null)
          continue;
        Iterator localIterator = localList.iterator();
        if (localIterator.hasNext())
          continue;
        int j = paramMessage.what;
        switch (j)
        {
        default:
          return;
          Handler localHandler = (Handler)j.next();
          Message localMessage1 = new Message();
          Message localMessage2 = localMessage1;
          Message localMessage3 = paramMessage;
          localMessage2.copyFrom(localMessage3);
          localHandler.sendMessage(localMessage1);
          continue;
        case 100:
        case 200:
        case 300:
        case 1:
        case 301:
        }
      }
      finally
      {
        monitorexit;
      }
      int k = paramMessage.arg2;
      String str1 = (String)paramMessage.obj;
      AsyncTaskNotifier localAsyncTaskNotifier1 = DownloadService.access$1(this.this$0);
      int m = i;
      String str2 = str1;
      int n = k;
      int i1 = localAsyncTaskNotifier1.addNotificationItem(m, str2, n);
      AsyncTaskNotifier localAsyncTaskNotifier2 = DownloadService.access$1(this.this$0);
      int i2 = i1;
      localAsyncTaskNotifier2.updatePreActiveNotification(i2);
      continue;
      int i3 = paramMessage.arg2;
      AsyncTaskNotifier localAsyncTaskNotifier3 = DownloadService.access$1(this.this$0);
      int i4 = i;
      int i5 = i3;
      localAsyncTaskNotifier3.updateActiveNotification(i4, i5);
      continue;
      boolean bool1 = ((Boolean)paramMessage.obj).booleanValue();
      Bundle localBundle1 = paramMessage.getData();
      String str3 = null;
      if (localBundle1 != null)
      {
        Bundle localBundle2 = localBundle1;
        String str4 = "packageName";
        str3 = localBundle2.getString(str4);
      }
      HashMap localHashMap2 = DownloadService.access$2(this.this$0);
      Integer localInteger2 = Integer.valueOf(i);
      String str5 = (String)localHashMap2.get(localInteger2);
      AsyncTaskNotifier localAsyncTaskNotifier4 = DownloadService.access$1(this.this$0);
      int i6 = i;
      boolean bool2 = bool1;
      String str6 = str5;
      String str7 = str3;
      localAsyncTaskNotifier4.updateDownloadCompletedNotification2(i6, bool2, str6, str7);
      DownloadService localDownloadService1 = this.this$0;
      int i7 = i;
      localDownloadService1.taskCompleted(i7);
      continue;
      HashMap localHashMap3 = DownloadService.access$0(this.this$0);
      Integer localInteger3 = Integer.valueOf(i);
      if (localHashMap3.containsKey(localInteger3))
      {
        HashMap localHashMap4 = DownloadService.access$0(this.this$0);
        Integer localInteger4 = Integer.valueOf(i);
        localHashMap4.remove(localInteger4);
      }
      String str8 = (String)paramMessage.obj;
      int i8 = paramMessage.arg2;
      int i9 = 1;
      if (i8 == i9)
      {
        AsyncTaskNotifier localAsyncTaskNotifier5 = DownloadService.access$1(this.this$0);
        int i10 = i;
        int i11 = 1;
        String str9 = str8;
        localAsyncTaskNotifier5.updateInstallCompletedNotification(i10, i11, str9);
      }
      while (true)
      {
        DownloadService localDownloadService2 = this.this$0;
        int i12 = i;
        localDownloadService2.taskCompleted(i12);
        break;
        AsyncTaskNotifier localAsyncTaskNotifier6 = DownloadService.access$1(this.this$0);
        int i13 = i;
        int i14 = 0;
        String str10 = str8;
        localAsyncTaskNotifier6.updateInstallCompletedNotification(i13, i14, str10);
      }
      Bundle localBundle3 = paramMessage.getData();
      Bundle localBundle4 = localBundle3;
      String str11 = "packageName";
      String str12 = localBundle4.getString(str11);
      Bundle localBundle5 = localBundle3;
      String str13 = "taskId";
      int i15 = localBundle5.getInt(str13);
      AlertDialog.Builder localBuilder1 = new android/app/AlertDialog$Builder;
      Context localContext1 = DownloadService.access$3(this.this$0);
      AlertDialog.Builder localBuilder2 = localBuilder1;
      Context localContext2 = localContext1;
      localBuilder2.<init>(localContext2);
      AlertDialog.Builder localBuilder3 = localBuilder1;
      int i16 = 17301543;
      AlertDialog.Builder localBuilder4 = localBuilder3.setIcon(i16).setTitle(2131296378).setMessage(2131296520).setCancelable(0);
      DownloadService.1.1 local11 = new com/yingyonghui/market/online/DownloadService$1$1;
      DownloadService.1.1 local12 = local11;
      1 local1 = this;
      int i17 = i15;
      String str14 = str12;
      local12.<init>(local1, i17, str14);
      localBuilder4.setPositiveButton(2131296372, local11).create().show();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DownloadService.1
 * JD-Core Version:    0.6.0
 */