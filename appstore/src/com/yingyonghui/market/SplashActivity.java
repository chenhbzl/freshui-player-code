package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.online.DataCacheService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.InternetManager;
import java.util.Date;

public class SplashActivity extends Activity
{
  public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
  private static final int ACTION_APP_ICON = 100;
  private static final int ACTION_NETWORK_ERROR = 400;
  private static final int ACTION_TOP_FOUR_APP_DETAIL = 200;
  private static final int DIALOG_NETWORK_ERROR = 100;
  public static final String PACKAGE_NAME = "com.yingyonghui.market";
  private static final int SEND_INSTALL_LOG = 300;
  private DataCacheService dataCacheService;
  private Drawable drawableLogo;
  private Drawable drawableWord;
  private int logoHeight;
  private int logoWidth;
  private Handler mHandler;
  private RatingBar mProgressBar;
  float progress;
  private boolean readyAppList;
  private boolean readyTop4;
  private int screenHeight;
  private int screenWidth;
  private int[] topAppId;

  public SplashActivity()
  {
    int[] arrayOfInt = new int[4];
    this.topAppId = arrayOfInt;
    this.progress = 1.0F;
  }

  private void addInstallLogRequest()
  {
    Request localRequest = new Request(0L, 65562);
    String str = "";
    try
    {
      Bundle localBundle = getPackageManager().getApplicationInfo("com.yingyonghui.market", 128).metaData;
      if (localBundle != null)
        str = localBundle.getString("UMENG_CHANNEL");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str;
      localRequest.setData(arrayOfObject);
      SplashActivity.5 local5 = new SplashActivity.5(this, localRequest);
      localRequest.addObserver(local5);
      MarketService.getServiceInstance(this).sendInstallLog(localRequest);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void createShortCut(Intent paramIntent)
  {
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    Intent.ShortcutIconResource localShortcutIconResource = Intent.ShortcutIconResource.fromContext(this, 2130837558);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", localShortcutIconResource);
    String str = getString(2131296257);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", str);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
    sendBroadcast(localIntent);
  }

  private Matrix getMatrix(float paramFloat)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.reset();
    localMatrix.postScale(paramFloat, paramFloat);
    return localMatrix;
  }

  private float getScale(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    float f1 = i * 2.0F;
    float f2 = this.screenWidth;
    float f3 = f1 / f2;
    float f4 = j * 2.0F;
    float f5 = this.screenHeight;
    float f6 = f4 / f5;
    float f7 = Math.max(f3, f6);
    int k = 1065353216;
    float f8;
    if (f7 > 1.0F)
      f8 = 1.0F / f7;
    return f8;
  }

  private void initAppList()
  {
    Request localRequest = new Request(0L, 65538);
    Object[] arrayOfObject = new Object[4];
    Integer localInteger1 = Integer.valueOf(9291);
    arrayOfObject[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(0);
    arrayOfObject[1] = localInteger2;
    Integer localInteger3 = Integer.valueOf(10);
    arrayOfObject[2] = localInteger3;
    Integer localInteger4 = Integer.valueOf(0);
    arrayOfObject[3] = localInteger4;
    localRequest.setData(arrayOfObject);
    SplashActivity.4 local4 = new SplashActivity.4(this, localRequest);
    localRequest.addObserver(local4);
    this.dataCacheService.getAppList(localRequest);
  }

  private void initData()
  {
    initTop4Data();
    initAppList();
  }

  private void initHandlerInUIThread()
  {
    SplashActivity.2 local2 = new SplashActivity.2(this);
    this.mHandler = local2;
  }

  private void initTop4Data()
  {
    Request localRequest = new Request(0L, 65549);
    SplashActivity.3 local3 = new SplashActivity.3(this, localRequest);
    localRequest.addObserver(local3);
    this.dataCacheService.getTopFourApp(localRequest);
  }

  private void setLoading()
  {
    RatingBar localRatingBar1 = (RatingBar)findViewById(2131427470);
    this.mProgressBar = localRatingBar1;
    RatingBar localRatingBar2 = this.mProgressBar;
    float f = this.progress;
    localRatingBar2.setRating(f);
  }

  private void setupViews()
  {
    setLoading();
  }

  static boolean shortcutExists(Context paramContext, String paramString)
  {
    String str1 = "com.android.launcher.settings";
    if (DeviceUtil.getSDKVersionInt() > 7)
      str1 = "com.android.launcher2.settings";
    Uri localUri = Uri.parse("content://" + str1 + "/favorites?notify=true");
    ContentResolver localContentResolver = paramContext.getContentResolver();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "intent";
    String str2 = "intent like '%" + paramString + "%'";
    String str3 = null;
    Cursor localCursor = localContentResolver.query(localUri, arrayOfString, str2, null, str3);
    try
    {
      boolean bool = localCursor.moveToFirst();
      return bool;
    }
    finally
    {
      localCursor.close();
    }
    throw localObject;
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    getWindow().setFormat(1);
    Display localDisplay = getWindowManager().getDefaultDisplay();
    int i = localDisplay.getWidth();
    this.screenWidth = i;
    int j = localDisplay.getHeight();
    this.screenHeight = j;
    MobclickAgent.onError(this);
    MobclickAgent.setReportPolicy(0);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    DataCacheService localDataCacheService = DataCacheService.getServiceInstance(this);
    this.dataCacheService = localDataCacheService;
    int k = new Date().getHours();
    int m;
    if ((k > 6) && (k < 20))
      m = 1;
    while (true)
    {
      if (m != 0)
      {
        setContentView(2130903104);
        setupViews();
        initHandlerInUIThread();
      }
      try
      {
        SharedPreferences localSharedPreferences = getSharedPreferences("com.yingyonghui.market_preferences", 0);
        if ((localSharedPreferences.getBoolean("checkbox_create_yingyonghui_shortcut", 1)) && (!shortcutExists(this, "com.yingyonghui.market")))
        {
          Intent localIntent = getIntent();
          createShortCut(localIntent);
        }
        if ((localSharedPreferences.getBoolean("install_log", 1)) && (InternetManager.hasInternet(this)))
          addInstallLogRequest();
        initData();
        SplashActivity.1 local1 = new SplashActivity.1(this);
        new Thread(local1).start();
        return;
        m = 0;
        continue;
        setContentView(2130903105);
      }
      catch (Throwable localThrowable)
      {
        while (true)
          localThrowable.printStackTrace();
      }
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder;
    SplashActivity.6 local6;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder.setPositiveButton(2131296372, local6).create())
    {
      return localAlertDialog;
      localBuilder = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296371);
      local6 = new SplashActivity.6(this);
    }
  }

  public void onPause()
  {
    onPause();
    MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    onResume();
    MobclickAgent.onResume(this);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SplashActivity
 * JD-Core Version:    0.6.0
 */