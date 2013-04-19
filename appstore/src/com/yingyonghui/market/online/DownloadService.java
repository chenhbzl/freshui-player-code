package com.yingyonghui.market.online;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.Constants;
import com.yingyonghui.market.FileManager;
import com.yingyonghui.market.install.IWorksPackageManager;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.Signature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class DownloadService
{
  private static final int BUFFER_SIZE = 8192;
  public static final int EVENT_DOWNLOAD_COMPLETE = 300;
  public static final int EVENT_INSTALL_COMPLETE = 1;
  public static final int EVENT_PREPARE_FOR_DOWNLOAD = 100;
  public static final int EVENT_SYSTEM_INSTALL = 301;
  public static final int EVENT_UPDATE_DOWNLOAD_STATUS = 200;
  public static final int INSTALL_SUCCESS = 1;
  private static final int UPDATE_PER_COUNT = 20;
  public static boolean flagCancelling = 0;
  private static DownloadService instance;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/lang/String;", ">;"})
  private HashMap mCachedFilePath;
  private Context mContext;
  private Handler mHandler;
  private AsyncTaskNotifier mNotificationService;
  private IWorksPackageManager mPackageManager;

  @Signature({"Ljava/util/Vector", "<", "Lcom/yingyonghui/market/online/DownloadService$Task;", ">;"})
  private Vector mPendingTask;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/util/List", "<", "Landroid/os/Handler;", ">;>;"})
  private HashMap mStatusHandler;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Lcom/yingyonghui/market/online/DownloadService$Task;", ">;"})
  private Hashtable mTaskPool;

  private DownloadService(Context paramContext)
  {
    Hashtable localHashtable = new Hashtable();
    this.mTaskPool = localHashtable;
    Vector localVector = new Vector();
    this.mPendingTask = localVector;
    HashMap localHashMap1 = new HashMap();
    this.mCachedFilePath = localHashMap1;
    HashMap localHashMap2 = new HashMap();
    this.mStatusHandler = localHashMap2;
    DownloadService.1 local1 = new DownloadService.1(this);
    this.mHandler = local1;
    this.mContext = paramContext;
    AsyncTaskNotifier localAsyncTaskNotifier = AsyncTaskNotifier.getInstance(this.mContext);
    this.mNotificationService = localAsyncTaskNotifier;
    IWorksPackageManager localIWorksPackageManager = IWorksPackageManager.getInstance(this.mContext);
    this.mPackageManager = localIWorksPackageManager;
  }

  /** @deprecated */
  private RandomAccessFile getApkCacheStream(int paramInt, String paramString)
    throws FileNotFoundException
  {
    monitorenter;
    if (paramString == null);
    try
    {
      paramString = "tmp.apk";
      String str = String.valueOf(paramString);
      Context localContext = this.mContext;
      HashMap localHashMap = this.mCachedFilePath;
      RandomAccessFile localRandomAccessFile = FileManager.getApkCacheStream(localContext, paramInt, str, localHashMap);
      monitorexit;
      return localRandomAccessFile;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static DownloadService getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new DownloadService(paramContext);
    return instance;
  }

  /** @deprecated */
  public void cancelTask(int paramInt)
  {
    monitorenter;
    while (true)
    {
      int j;
      int k;
      try
      {
        Hashtable localHashtable1 = this.mTaskPool;
        Integer localInteger1 = Integer.valueOf(paramInt);
        if (!localHashtable1.containsKey(localInteger1))
          continue;
        Hashtable localHashtable2 = this.mTaskPool;
        Integer localInteger2 = Integer.valueOf(paramInt);
        Task localTask = (Task)localHashtable2.remove(localInteger2);
        flagCancelling = 1;
        localTask.setInterrupt(1);
        this.mNotificationService.cancelNotification(paramInt);
        if ((this.mPendingTask.size() <= 0) || (this.mTaskPool.size() >= 3))
          continue;
        ((Task)this.mPendingTask.remove(0)).startTask();
        return;
        int i = this.mPendingTask.size();
        j = -1;
        k = 0;
        if (k >= i)
        {
          if (j == -1)
            continue;
          this.mPendingTask.remove(j);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (((Task)this.mPendingTask.get(k)).mTaskId == paramInt)
      {
        j = k;
        this.mNotificationService.cancelNotification(paramInt);
        continue;
      }
      k += 1;
    }
  }

  public String getCachedPath(int paramInt)
  {
    HashMap localHashMap = this.mCachedFilePath;
    Integer localInteger = Integer.valueOf(paramInt);
    return (String)localHashMap.get(localInteger);
  }

  public String getPackageName(int paramInt)
  {
    String str = "";
    Hashtable localHashtable = this.mTaskPool;
    Integer localInteger = Integer.valueOf(paramInt);
    Task localTask = (Task)localHashtable.get(localInteger);
    if (localTask != null)
      str = localTask.mPackageName;
    return str;
  }

  /** @deprecated */
  public boolean isTaskRunning(int paramInt)
  {
    monitorenter;
    try
    {
      Hashtable localHashtable = this.mTaskPool;
      Integer localInteger = Integer.valueOf(paramInt);
      boolean bool = localHashtable.containsKey(localInteger);
      if (bool)
        bool = true;
      while (true)
      {
        return bool;
        int j = this.mPendingTask.size();
        int k = -1;
        int m = 0;
        while (true)
        {
          if (m >= j);
          while (true)
          {
            if (k == -1)
              break label107;
            bool = true;
            break;
            i = ((Task)this.mPendingTask.get(m)).mTaskId;
            if (i != paramInt)
              break label98;
            k = m;
          }
          label98: m += 1;
        }
        label107: int i = 0;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void newTask(int paramInt1, MarketServiceAgent paramMarketServiceAgent, int paramInt2, String paramString1, String paramString2)
    throws SocketException
  {
    monitorenter;
    try
    {
      DownloadService localDownloadService = this;
      int i = paramInt1;
      MarketServiceAgent localMarketServiceAgent = paramMarketServiceAgent;
      int j = paramInt2;
      String str1 = paramString1;
      String str2 = paramString2;
      Task localTask = new Task(i, localMarketServiceAgent, j, str1, str2);
      if (this.mTaskPool.size() >= 3)
        this.mPendingTask.add(localTask);
      while (true)
      {
        return;
        localTask.startTask();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String removeCachedPath(int paramInt)
  {
    HashMap localHashMap = this.mCachedFilePath;
    Integer localInteger = Integer.valueOf(paramInt);
    return (String)localHashMap.remove(localInteger);
  }

  /** @deprecated */
  public void removeHandler(int paramInt)
  {
    monitorenter;
    try
    {
      HashMap localHashMap1 = this.mStatusHandler;
      Integer localInteger1 = Integer.valueOf(paramInt);
      if (localHashMap1.containsKey(localInteger1))
      {
        HashMap localHashMap2 = this.mStatusHandler;
        Integer localInteger2 = Integer.valueOf(paramInt);
        localHashMap2.remove(localInteger2);
      }
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
  public void setHandler(int paramInt, Handler paramHandler)
  {
    monitorenter;
    try
    {
      HashMap localHashMap1 = this.mStatusHandler;
      Integer localInteger1 = Integer.valueOf(paramInt);
      List localList = (List)localHashMap1.get(localInteger1);
      if ((localList != null) && (!localList.contains(paramHandler)))
        localList.add(paramHandler);
      while (true)
      {
        return;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(paramHandler);
        HashMap localHashMap2 = this.mStatusHandler;
        Integer localInteger2 = Integer.valueOf(paramInt);
        localHashMap2.put(localInteger2, localArrayList);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public void taskCompleted(int paramInt)
  {
    monitorenter;
    try
    {
      Hashtable localHashtable1 = this.mTaskPool;
      Integer localInteger1 = Integer.valueOf(paramInt);
      if (localHashtable1.containsKey(localInteger1))
      {
        this.mNotificationService.cancelTaskItem(paramInt);
        Hashtable localHashtable2 = this.mTaskPool;
        Integer localInteger2 = Integer.valueOf(paramInt);
        localHashtable2.remove(localInteger2);
        if (this.mPendingTask.size() > 0)
          ((Task)this.mPendingTask.remove(0)).startTask();
      }
      HashMap localHashMap = this.mStatusHandler;
      Integer localInteger3 = Integer.valueOf(paramInt);
      localHashMap.containsKey(localInteger3);
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

  class Task
    implements Runnable
  {
    private MarketServiceAgent agent;
    private boolean interrupted = 0;
    private String mAppName;
    private String mPackageName;
    private int mTaskId;
    private int mTotalSize;

    public Task(int paramMarketServiceAgent, MarketServiceAgent paramInt1, int paramString1, String paramString2, String arg6)
    {
      this.mTaskId = paramMarketServiceAgent;
      this.agent = paramInt1;
      this.mPackageName = paramString2;
      Object localObject;
      this.mAppName = localObject;
      this.mTotalSize = paramString1;
    }

    public void run()
    {
      int k;
      if (!this.interrupted)
      {
        Handler localHandler1 = DownloadService.this.mHandler;
        int i = this.mTaskId;
        int j = this.mTotalSize;
        String str1 = this.mAppName;
        Message localMessage1 = Message.obtain(localHandler1, 100, i, j, str1);
        Handler localHandler2 = DownloadService.this.mHandler;
        Message localMessage2 = localMessage1;
        localHandler2.sendMessage(localMessage2);
        k = 0;
      }
      try
      {
        DownloadService localDownloadService1 = DownloadService.this;
        int i1 = this.mTaskId;
        String str2 = this.mPackageName;
        localRandomAccessFile1 = localDownloadService1.getApkCacheStream(i1, str2);
        l1 = localRandomAccessFile1.length();
        MarketServiceAgent localMarketServiceAgent = this.agent;
        int i2 = this.mTaskId;
        String str3 = this.mPackageName;
        ApkContentInputstream localApkContentInputstream = localMarketServiceAgent.getAppContentStream(i2, 0, l1, str3);
        localInputStream = localApkContentInputstream.input;
        if (localApkContentInputstream.repickOutput)
        {
          DownloadService localDownloadService2 = DownloadService.this;
          int i3 = this.mTaskId;
          String str4 = this.mPackageName;
          localRandomAccessFile1 = localDownloadService2.getApkCacheStream(i3, str4);
          l1 = localRandomAccessFile1.length();
        }
        if ((localInputStream == null) || (localRandomAccessFile1 == null))
          throw new IOException();
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Handler localHandler3 = DownloadService.this.mHandler;
        int i4 = this.mTaskId;
        Message localMessage3 = Message.obtain(localHandler3, 300, i4, 0);
        Boolean localBoolean1 = Boolean.valueOf(k);
        localMessage3.obj = localBoolean1;
        Bundle localBundle1 = new Bundle();
        String str5 = this.mPackageName;
        localBundle1.putString("packageName", str5);
        Message localMessage4 = localMessage3;
        Bundle localBundle2 = localBundle1;
        localMessage4.setData(localBundle2);
        Handler localHandler4 = DownloadService.this.mHandler;
        Message localMessage5 = localMessage3;
        localHandler4.sendMessage(localMessage5);
        if (localInputStream == null);
      }
      finally
      {
        try
        {
          RandomAccessFile localRandomAccessFile1;
          long l1;
          InputStream localInputStream;
          localInputStream.close();
          if (localRandomAccessFile1 != null)
            localRandomAccessFile1.close();
          label335: if ((GlobalUtil.isSystemApp(DownloadService.this.mContext.getPackageManager())) && (k != 0))
          {
            String str6 = Constants.PACKAGE_NAME;
            String str7 = this.mPackageName;
            if (!str6.equals(str7))
              break label1337;
            Message localMessage6 = Message.obtain(DownloadService.this.mHandler, 301);
            Bundle localBundle3 = new Bundle();
            String str8 = this.mPackageName;
            Bundle localBundle4 = localBundle3;
            String str9 = "packageName";
            String str10 = str8;
            localBundle4.putString(str9, str10);
            int i5 = this.mTaskId;
            Bundle localBundle5 = localBundle3;
            String str11 = "taskId";
            int i6 = i5;
            localBundle5.putInt(str11, i6);
            Message localMessage7 = localMessage6;
            Bundle localBundle6 = localBundle3;
            localMessage7.setData(localBundle6);
            Handler localHandler5 = DownloadService.this.mHandler;
            Message localMessage8 = localMessage6;
            localHandler5.sendMessage(localMessage8);
          }
          while (true)
          {
            return;
            if (l1 != 0L)
            {
              RandomAccessFile localRandomAccessFile2 = localRandomAccessFile1;
              long l2 = l1;
              localRandomAccessFile2.seek(l2);
            }
            byte[] arrayOfByte1 = new byte[8192];
            int i7 = (int)localRandomAccessFile1.length();
            int i8 = 0;
            label534: int i9;
            int i12;
            int m;
            if (!this.interrupted)
            {
              i9 = localInputStream.read(arrayOfByte1, 0, 8192);
              int i10 = i9;
              int i11 = -1;
              if (i10 != i11);
            }
            else
            {
              i12 = this.interrupted;
              if (i12 == 0)
                break label984;
              m = 0;
              label584: Handler localHandler6 = DownloadService.this.mHandler;
              int i13 = this.mTaskId;
              Message localMessage9 = Message.obtain(localHandler6, 300, i13, 0);
              Boolean localBoolean2 = Boolean.valueOf(m);
              localMessage9.obj = localBoolean2;
              Bundle localBundle7 = new Bundle();
              String str12 = this.mPackageName;
              localBundle7.putString("packageName", str12);
              Message localMessage10 = localMessage9;
              Bundle localBundle8 = localBundle7;
              localMessage10.setData(localBundle8);
              Handler localHandler7 = DownloadService.this.mHandler;
              Message localMessage11 = localMessage9;
              localHandler7.sendMessage(localMessage11);
              if (localInputStream == null);
            }
            try
            {
              localInputStream.close();
              if (localRandomAccessFile1 != null)
                localRandomAccessFile1.close();
              if ((!GlobalUtil.isSystemApp(DownloadService.this.mContext.getPackageManager())) || (m == 0))
                continue;
              String str13 = Constants.PACKAGE_NAME;
              String str14 = this.mPackageName;
              if (str13.equals(str14))
              {
                Message localMessage12 = Message.obtain(DownloadService.this.mHandler, 301);
                Bundle localBundle9 = new Bundle();
                String str15 = this.mPackageName;
                Bundle localBundle10 = localBundle9;
                String str16 = "packageName";
                String str17 = str15;
                localBundle10.putString(str16, str17);
                int i14 = this.mTaskId;
                Bundle localBundle11 = localBundle9;
                String str18 = "taskId";
                int i15 = i14;
                localBundle11.putInt(str18, i15);
                Message localMessage13 = localMessage12;
                Bundle localBundle12 = localBundle9;
                localMessage13.setData(localBundle12);
                Handler localHandler8 = DownloadService.this.mHandler;
                Message localMessage14 = localMessage12;
                localHandler8.sendMessage(localMessage14);
                continue;
                i12 = 0;
                RandomAccessFile localRandomAccessFile3 = localRandomAccessFile1;
                byte[] arrayOfByte2 = arrayOfByte1;
                int i16 = i12;
                int i17 = i9;
                localRandomAccessFile3.write(arrayOfByte2, i16, i17);
                i7 += i9;
                int i18 = i8 + 1;
                if (i8 % 20 == 0)
                {
                  Handler localHandler9 = DownloadService.this.mHandler;
                  int i19 = this.mTaskId;
                  Message localMessage15 = Message.obtain(localHandler9, 200, i19, 0, null);
                  int i20 = i7;
                  localMessage15.arg2 = i20;
                  Handler localHandler10 = DownloadService.this.mHandler;
                  Message localMessage16 = localMessage15;
                  localHandler10.sendMessage(localMessage16);
                }
                i8 = i18;
                break label534;
                label984: n = 1;
                Context localContext1 = DownloadService.this.mContext;
                int i21 = this.mTaskId;
                String str19 = this.mPackageName;
                HashMap localHashMap1 = DownloadService.this.mCachedFilePath;
                FileManager.renameFileToEndWithApk(localContext1, i21, str19, localHashMap1);
                Context localContext2 = DownloadService.this.mContext;
                String str20 = this.mPackageName;
                FileManager.removeTimestampforApk(localContext2, str20);
                break label584;
                localObject = finally;
                Handler localHandler11 = DownloadService.this.mHandler;
                int i22 = this.mTaskId;
                Message localMessage17 = Message.obtain(localHandler11, 300, i22, 0);
                Boolean localBoolean3 = Boolean.valueOf(n);
                localMessage17.obj = localBoolean3;
                Bundle localBundle13 = new Bundle();
                String str21 = this.mPackageName;
                localBundle13.putString("packageName", str21);
                Message localMessage18 = localMessage17;
                Bundle localBundle14 = localBundle13;
                localMessage18.setData(localBundle14);
                Handler localHandler12 = DownloadService.this.mHandler;
                Message localMessage19 = localMessage17;
                localHandler12.sendMessage(localMessage19);
                if (localInputStream == null);
              }
            }
            catch (IOException localIOException1)
            {
              try
              {
                int n;
                localInputStream.close();
                if (localRandomAccessFile1 != null)
                  localRandomAccessFile1.close();
                label1178: if ((GlobalUtil.isSystemApp(DownloadService.this.mContext.getPackageManager())) && (n != 0))
                {
                  String str22 = Constants.PACKAGE_NAME;
                  String str23 = this.mPackageName;
                  if (!str22.equals(str23))
                    break label1464;
                  Message localMessage20 = Message.obtain(DownloadService.this.mHandler, 301);
                  Bundle localBundle15 = new Bundle();
                  String str24 = this.mPackageName;
                  Bundle localBundle16 = localBundle15;
                  String str25 = "packageName";
                  String str26 = str24;
                  localBundle16.putString(str25, str26);
                  int i23 = this.mTaskId;
                  Bundle localBundle17 = localBundle15;
                  String str27 = "taskId";
                  int i24 = i23;
                  localBundle17.putInt(str27, i24);
                  Message localMessage21 = localMessage20;
                  Bundle localBundle18 = localBundle15;
                  localMessage21.setData(localBundle18);
                  Handler localHandler13 = DownloadService.this.mHandler;
                  Message localMessage22 = localMessage20;
                  localHandler13.sendMessage(localMessage22);
                }
                while (true)
                {
                  throw localObject;
                  label1337: HashMap localHashMap2 = DownloadService.this.mCachedFilePath;
                  Integer localInteger1 = Integer.valueOf(this.mTaskId);
                  String str28 = (String)localHashMap2.get(localInteger1);
                  StringBuilder localStringBuilder1 = new StringBuilder("file://");
                  String str29 = str28;
                  Uri localUri1 = Uri.parse(str29);
                  IWorksPackageManager localIWorksPackageManager1 = DownloadService.this.mPackageManager;
                  String str30 = this.mPackageName;
                  Handler localHandler14 = DownloadService.this.mHandler;
                  int i25 = this.mTaskId;
                  IWorksPackageManager localIWorksPackageManager2 = localIWorksPackageManager1;
                  Uri localUri2 = localUri1;
                  String str31 = str30;
                  Handler localHandler15 = localHandler14;
                  int i26 = i25;
                  localIWorksPackageManager2.installPackage(localUri2, str31, localHandler15, i26);
                  break;
                  label1464: HashMap localHashMap3 = DownloadService.this.mCachedFilePath;
                  Integer localInteger2 = Integer.valueOf(this.mTaskId);
                  String str32 = (String)localHashMap3.get(localInteger2);
                  StringBuilder localStringBuilder2 = new StringBuilder("file://");
                  String str33 = str32;
                  Uri localUri3 = Uri.parse(str33);
                  IWorksPackageManager localIWorksPackageManager3 = DownloadService.this.mPackageManager;
                  String str34 = this.mPackageName;
                  Handler localHandler16 = DownloadService.this.mHandler;
                  int i27 = this.mTaskId;
                  IWorksPackageManager localIWorksPackageManager4 = localIWorksPackageManager3;
                  Uri localUri4 = localUri3;
                  String str35 = str34;
                  Handler localHandler17 = localHandler16;
                  int i28 = i27;
                  localIWorksPackageManager4.installPackage(localUri4, str35, localHandler17, i28);
                }
                HashMap localHashMap4 = DownloadService.this.mCachedFilePath;
                Integer localInteger3 = Integer.valueOf(this.mTaskId);
                String str36 = (String)localHashMap4.get(localInteger3);
                StringBuilder localStringBuilder3 = new StringBuilder("file://");
                String str37 = str36;
                Uri localUri5 = Uri.parse(str37);
                IWorksPackageManager localIWorksPackageManager5 = DownloadService.this.mPackageManager;
                String str38 = this.mPackageName;
                Handler localHandler18 = DownloadService.this.mHandler;
                int i29 = this.mTaskId;
                IWorksPackageManager localIWorksPackageManager6 = localIWorksPackageManager5;
                Uri localUri6 = localUri5;
                String str39 = str38;
                Handler localHandler19 = localHandler18;
                int i30 = i29;
                localIWorksPackageManager6.installPackage(localUri6, str39, localHandler19, i30);
                continue;
                localIOException1 = localIOException1;
              }
              catch (IOException localIOException2)
              {
                break label1178;
              }
            }
          }
        }
        catch (IOException localIOException3)
        {
          break label335;
        }
      }
    }

    public void setInterrupt(boolean paramBoolean)
    {
      this.interrupted = paramBoolean;
    }

    public void startTask()
    {
      Hashtable localHashtable = DownloadService.this.mTaskPool;
      Integer localInteger = Integer.valueOf(this.mTaskId);
      localHashtable.put(localInteger, this);
      new Thread(this).start();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DownloadService
 * JD-Core Version:    0.6.0
 */