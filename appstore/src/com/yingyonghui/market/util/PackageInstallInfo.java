package com.yingyonghui.market.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PackageInstallInfo
{
  public static final int PACKAGE_INSTALLED = 1;
  public static final int PACKAGE_NOT_INSTALLED = 0;
  public static final int PACKAGE_UPDATE_AVAILABLE = 2;

  public static String[] getInstalledAppPackages(Context paramContext)
  {
    return getInstalledAppPackages(paramContext, 0);
  }

  public static String[] getInstalledAppPackages(Context paramContext, boolean paramBoolean)
  {
    String[] arrayOfString = new String[0];
    List localList = paramContext.getPackageManager().getInstalledApplications(8192);
    ArrayList localArrayList;
    int j;
    if (localList != null)
    {
      localArrayList = new ArrayList();
      int i = localList.size();
      j = 0;
      if (j >= i)
      {
        arrayOfString = new String[localArrayList.size()];
        localArrayList.toArray(arrayOfString);
      }
    }
    else
    {
      return arrayOfString;
    }
    ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(j);
    String str1 = localApplicationInfo.sourceDir;
    if ((!paramBoolean) && (isInstalledOnROM(str1)));
    while (true)
    {
      j += 1;
      break;
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        String str2 = localApplicationInfo.packageName;
        int k = localPackageManager.getPackageInfo(str2, 8192).versionCode;
        label134: String str3 = String.valueOf(localApplicationInfo.packageName);
        StringBuilder localStringBuilder = new StringBuilder(str3);
        if (k == -1);
        for (String str4 = ""; ; str4 = ";" + k)
        {
          String str5 = str4;
          localArrayList.add(str5);
          break;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break label134;
      }
    }
  }

  public static int getPackageInstalledStatus(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    int i = 0;
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString, 8192);
      int j;
      int n;
      if (localPackageInfo != null)
      {
        j = 1;
        i = 0 + j;
        n = localPackageInfo.versionCode;
        if (!paramString.equals("com.yingyonghui.market"))
          break label84;
        if (paramInt2 == 9999)
        {
          if (n >= paramInt1)
            break label78;
          j = 1;
          label62: i += j;
        }
      }
      label69: label78: label84: 
      do
      {
        return i;
        j = 0;
        break;
        j = 0;
        break label62;
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo == null) || (localApplicationInfo.sourceDir == null))
          break label121;
        k = localApplicationInfo.sourceDir.startsWith("/system/app");
      }
      while (k != 0);
      label121: if (n < paramInt1);
      int m;
      for (int k = 1; ; m = 0)
      {
        i += k;
        break;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label69;
    }
  }

  public static boolean isInstalledOnROM(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str, 1);
      if ((localApplicationInfo != null) && (localApplicationInfo.sourceDir != null))
      {
        bool = localApplicationInfo.sourceDir.startsWith("/system/app");
        if (bool)
        {
          bool = true;
          return bool;
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        boolean bool = false;
      }
    }
  }

  public static boolean isInstalledOnROM(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("/system/app")));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.PackageInstallInfo
 * JD-Core Version:    0.6.0
 */