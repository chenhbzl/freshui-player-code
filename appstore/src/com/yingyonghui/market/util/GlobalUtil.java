package com.yingyonghui.market.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.widget.Toast;
import com.yingyonghui.market.AssetPermissionsSubActivity;
import com.yingyonghui.market.Constants;
import java.io.File;

public class GlobalUtil
{
  public static final int NOT_ALLOW_INSTALL_NON_MARKET_APPS;

  public static boolean allowInstallNonMarketApps(Context paramContext)
  {
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) == 0);
    for (int i = 0; ; i = 1)
      return i;
  }

  public static PackageInfo getPackageInfo(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, 0);
      label7: return localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label7;
    }
  }

  public static boolean isSystemApp(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo.flags & 0x1) == 1);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isSystemApp(PackageManager paramPackageManager)
  {
    String str = Constants.PACKAGE_NAME;
    return isSystemApp(paramPackageManager, str);
  }

  public static boolean isSystemApp(PackageManager paramPackageManager, String paramString)
  {
    PackageInfo localPackageInfo = getPackageInfo(paramPackageManager, paramString);
    int i;
    if ((localPackageInfo == null) || (localPackageInfo.applicationInfo == null))
      i = 0;
    while (true)
    {
      return i;
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 1)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public static void longToast(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramInt, 1).show();
  }

  public static void longToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }

  public static void shortToast(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramInt, 0).show();
  }

  public static void shortToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }

  public static void startInstall(Context paramContext, File paramFile, int paramInt1, String paramString1, int paramInt2, String paramString2, String[] paramArrayOfString)
  {
    if (PackageInstallInfo.isInstalledOnROM(paramContext))
    {
      Intent localIntent1 = new Intent();
      localIntent1.putExtra("_id", paramInt1);
      localIntent1.putExtra("title", paramString1);
      localIntent1.putExtra("size", paramInt2);
      localIntent1.putExtra("package", paramString2);
      localIntent1.putExtra("permission", paramArrayOfString);
      String str = AssetPermissionsSubActivity.class.getName();
      localIntent1.setClassName(paramContext, str);
      paramContext.startActivity(localIntent1);
    }
    while (true)
    {
      return;
      Intent localIntent2 = new Intent();
      localIntent2.setAction("android.intent.action.VIEW");
      localIntent2.addCategory("android.intent.category.DEFAULT");
      Uri localUri = Uri.fromFile(paramFile);
      localIntent2.setDataAndType(localUri, "application/vnd.android.package-archive");
      paramContext.startActivity(localIntent2);
    }
  }

  public static void startPermissionsActivity(Context paramContext, int paramInt1, String paramString1, int paramInt2, String paramString2, String[] paramArrayOfString)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("_id", paramInt1);
    localIntent.putExtra("title", paramString1);
    localIntent.putExtra("size", paramInt2);
    localIntent.putExtra("package", paramString2);
    localIntent.putExtra("permission", paramArrayOfString);
    String str = AssetPermissionsSubActivity.class.getName();
    localIntent.setClassName(paramContext, str);
    paramContext.startActivity(localIntent);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.GlobalUtil
 * JD-Core Version:    0.6.0
 */