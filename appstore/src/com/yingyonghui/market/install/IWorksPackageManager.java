package com.yingyonghui.market.install;

import android.content.Context;
import android.content.pm.IPackageDeleteObserver.Stub;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageInstallObserver.Stub;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;

public class IWorksPackageManager
{
  public static final int FAILED = 0;
  public static final int INSTALL_COMPLETE = 1;
  public static final int INSTALL_REPLACE_EXISTING = 2;
  public static final int SUCCEEDED = 1;
  public static final int UNINSTALL_COMPLETE = 2;
  private static IWorksPackageManager instance;
  private String TAG;
  private Context mContext;
  private IPackageManager mService;

  private IWorksPackageManager(Context paramContext)
  {
    String str = IWorksPackageManager.class.getSimpleName();
    this.TAG = str;
    this.mService = null;
    this.mContext = paramContext;
  }

  public static IWorksPackageManager getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new IWorksPackageManager(paramContext);
    return instance;
  }

  public boolean deletePackage(String paramString, Handler paramHandler)
  {
    PackageDeleteObserver localPackageDeleteObserver = new PackageDeleteObserver(paramHandler);
    if (this.mService == null)
    {
      IPackageManager localIPackageManager = (IPackageManager)ServiceManager.getServiceStub("package", "android.content.pm.IPackageManager$Stub");
      this.mService = localIPackageManager;
    }
    try
    {
      this.mService.deletePackage(paramString, localPackageDeleteObserver, 0);
      label47: return true;
    }
    catch (RemoteException localRemoteException)
    {
      break label47;
    }
  }

  public boolean installPackage(Uri paramUri, String paramString, Handler paramHandler, int paramInt)
  {
    int i = 0;
    PackageManager localPackageManager = this.mContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 8192);
      if (localPackageInfo != null)
        i = 0x0 | 0x2;
      label33: if ((i & 0x2) != 0)
      {
        String str1 = this.TAG;
        String str2 = "Replacing package:" + paramString;
        Log.w(str1, str2);
      }
      PackageInstallObserver localPackageInstallObserver = new PackageInstallObserver(paramHandler, paramInt);
      try
      {
        Class localClass1 = localPackageManager.getClass();
        Class[] arrayOfClass = new Class[4];
        arrayOfClass[0] = Uri.class;
        arrayOfClass[1] = IPackageInstallObserver.class;
        Class localClass2 = Integer.TYPE;
        arrayOfClass[2] = localClass2;
        arrayOfClass[3] = String.class;
        Method localMethod = localClass1.getMethod("installPackage", arrayOfClass);
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = paramUri;
        arrayOfObject[1] = localPackageInstallObserver;
        Integer localInteger = Integer.valueOf(i);
        arrayOfObject[2] = localInteger;
        arrayOfObject[3] = paramString;
        localMethod.invoke(localPackageManager, arrayOfObject);
        return true;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          localThrowable.printStackTrace();
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label33;
    }
  }

  class PackageDeleteObserver extends IPackageDeleteObserver.Stub
  {
    private Handler mHandler;

    public PackageDeleteObserver(Handler arg2)
    {
      Object localObject;
      this.mHandler = localObject;
    }

    public void packageDeleted(boolean paramBoolean)
    {
      Message localMessage = this.mHandler.obtainMessage(2);
      if (paramBoolean);
      for (int i = 1; ; i = 0)
      {
        localMessage.arg1 = i;
        this.mHandler.sendMessage(localMessage);
        return;
      }
    }
  }

  class PackageInstallObserver extends IPackageInstallObserver.Stub
  {
    private int id;
    private Handler mHandler;

    public PackageInstallObserver(Handler paramInt, int arg3)
    {
      this.mHandler = paramInt;
      int i;
      this.id = i;
    }

    private void deleteFile(int paramInt, String paramString)
    {
      Context localContext = IWorksPackageManager.this.mContext;
      String str = String.valueOf(paramString.hashCode());
      File localFile = localContext.getFileStreamPath(str);
      if (localFile != null)
        localFile.delete();
    }

    public void packageInstalled(String paramString, int paramInt)
    {
      Message localMessage = this.mHandler.obtainMessage(1);
      int i = this.id;
      localMessage.arg1 = i;
      localMessage.arg2 = paramInt;
      localMessage.obj = paramString;
      this.mHandler.sendMessage(localMessage);
      int j = this.id;
      deleteFile(j, paramString);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.IWorksPackageManager
 * JD-Core Version:    0.6.0
 */