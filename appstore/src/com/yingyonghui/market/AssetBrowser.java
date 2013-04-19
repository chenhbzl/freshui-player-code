package com.yingyonghui.market;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.online.AsyncTaskNotifier;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.provider.MarketStore;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AssetBrowser extends TabActivity
  implements TabHost.OnTabChangeListener
{
  public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
  private static final int ACTION_CHECK_SELF_UPDATE = 3;
  private static final int ACTION_CHECK_TEST_USER = 5;
  private static final int ACTION_GET_UPDATE_AVALIABLE_NUM = 4;
  private static final int ACTION_LOGIN = 1;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int DIALOG_CONFIRM_EXIT = 100;
  private static final int DIALOG_NETWORK_ERROR = 200;
  private static final int DIALOG_UPDATE_AVALIABLE = 300;
  public static final int ID_IMAGE_UPDATE = 5;
  public static final int MOBILE_APPLICATION_ID = 9999;
  public static final String PACKAGE_NAME = "com.yingyonghui.market";
  public static final String TAB_CATEGORY = "category";
  public static final String TAB_DOWNLOADS = "downloads";
  public static final int TAB_ID_CATEGORY = 2;
  public static final int TAB_ID_DOWNLOADS = 4;
  public static final int TAB_ID_NEW = 0;
  public static final int TAB_ID_RATINGS = 1;
  public static final int TAB_ID_SEARCH = 3;
  public static final String TAB_NEW = "new";
  public static final String TAB_RATINGS = "ratings";
  public static final String TAB_SEARCH = "search";
  public static final String TOP_RECOMMEND = "top_recommend";
  public static float density;
  public static DisplayMetrics displayMetrics;
  public static int longerSide;
  public static int originalOrientation;
  public static int screenHeight;
  public static int screenWidth = 0;
  public static int shorterSide;
  public static TabWidget tw_out;
  public int currentTabId;
  private Display display;
  private RelativeLayout.LayoutParams lp1;
  private final BroadcastReceiver mApplicationsReceiver;
  private AssetDetail mAssetDetail;
  private Field mBottomLeftStrip;
  private Field mBottomRightStrip;
  private Request mCurrentRequest;
  private Handler mHandler;
  private IMarketService mMarketService;
  private TabHost mTabHost;
  private int mUpdateAvaliableNum = 0;
  private TextView mUpdateView;
  private TabWidget tw;
  private int updateNumberLeftMargin;

  static
  {
    screenHeight = 0;
    longerSide = 0;
    shorterSide = 0;
    originalOrientation = 0;
    density = 0.0F;
    displayMetrics = new DisplayMetrics();
  }

  public AssetBrowser()
  {
    AssetBrowser.1 local1 = new AssetBrowser.1(this);
    this.mApplicationsReceiver = local1;
  }

  private void checkSelfUpdateAvaliable()
  {
    Request localRequest = new Request(0L, 65539);
    String str = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    if (str == null)
      str = "";
    Object[] arrayOfObject = new Object[2];
    Integer localInteger = Integer.valueOf(9999);
    arrayOfObject[0] = localInteger;
    arrayOfObject[1] = str;
    localRequest.setData(arrayOfObject);
    AssetBrowser.4 local4 = new AssetBrowser.4(this);
    localRequest.addObserver(local4);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getAppDetail(localRequest);
  }

  private void checkTestUserAvailable()
  {
    Request localRequest = new Request(0L, 268500997);
    AssetBrowser.3 local3 = new AssetBrowser.3(this);
    localRequest.addObserver(local3);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getTestUserAvailable(localRequest);
  }

  private void initHandlerInUIThread()
  {
    AssetBrowser.5 local5 = new AssetBrowser.5(this);
    this.mHandler = local5;
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver, localIntentFilter);
  }

  private void requestUpdateAvaliableNums()
  {
    Request localRequest = new Request(0L, 65551);
    String[] arrayOfString = PackageInstallInfo.getInstalledAppPackages(this);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = arrayOfString;
    Integer localInteger = Integer.valueOf(9);
    arrayOfObject[1] = localInteger;
    localRequest.setData(arrayOfObject);
    AssetBrowser.2 local2 = new AssetBrowser.2(this, localRequest);
    localRequest.addObserver(local2);
    this.mMarketService.getUpdateAvaliableNum(localRequest);
  }

  private void setCurrentTab(int paramInt)
  {
    Drawable localDrawable = getResources().getDrawable(2130837706);
    this.currentTabId = paramInt;
    View localView1 = this.tw.getChildAt(0);
    View localView2 = this.tw.getChildAt(1);
    View localView3 = this.tw.getChildAt(2);
    View localView4 = this.tw.getChildAt(3);
    View localView5 = this.tw.getChildAt(4);
    if (paramInt == 0)
    {
      localView1.setBackgroundDrawable(localDrawable);
      localView1.getBackground().setCallback(null);
      localView2.setBackgroundDrawable(null);
      localView3.setBackgroundDrawable(null);
      localView4.setBackgroundDrawable(null);
      localView5.setBackgroundDrawable(null);
    }
    while (true)
    {
      return;
      if (paramInt == 1)
      {
        localView1.setBackgroundDrawable(null);
        localView2.setBackgroundDrawable(localDrawable);
        localView2.getBackground().setCallback(null);
        localView3.setBackgroundDrawable(null);
        localView4.setBackgroundDrawable(null);
        localView5.setBackgroundDrawable(null);
        continue;
      }
      if (paramInt == 2)
      {
        localView1.setBackgroundDrawable(null);
        localView2.setBackgroundDrawable(null);
        localView3.setBackgroundDrawable(localDrawable);
        localView3.getBackground().setCallback(null);
        localView4.setBackgroundDrawable(null);
        localView5.setBackgroundDrawable(null);
        continue;
      }
      if (paramInt == 3)
      {
        localView1.setBackgroundDrawable(null);
        localView2.setBackgroundDrawable(null);
        localView3.setBackgroundDrawable(null);
        localView4.setBackgroundDrawable(localDrawable);
        localView4.getBackground().setCallback(null);
        localView5.setBackgroundDrawable(null);
        continue;
      }
      localView1.setBackgroundDrawable(null);
      localView2.setBackgroundDrawable(null);
      localView3.setBackgroundDrawable(null);
      localView4.setBackgroundDrawable(null);
      localView5.setBackgroundDrawable(localDrawable);
      localView5.getBackground().setCallback(null);
    }
  }

  private void setupCategoryTab()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, TabbedTopCategoriesBrowser.class);
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("category");
    String str = getResources().getString(2131296260);
    Drawable localDrawable = getResources().getDrawable(2130837535);
    localTabSpec.setIndicator(str, localDrawable);
    localTabSpec.setContent(localIntent);
    this.mTabHost.addTab(localTabSpec);
  }

  private void setupDownloadsTab()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, UpdateAppListActivity.class);
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("downloads");
    String str = getResources().getString(2131296261);
    Drawable localDrawable = getResources().getDrawable(2130837537);
    localTabSpec.setIndicator(str, localDrawable);
    localTabSpec.setContent(localIntent);
    this.mTabHost.addTab(localTabSpec);
  }

  private void setupNewTab()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, TabbedAssetBrowser.class);
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("new");
    String str = getResources().getString(2131296258);
    Drawable localDrawable = getResources().getDrawable(2130837542);
    localTabSpec.setIndicator(str, localDrawable);
    localTabSpec.setContent(localIntent);
    this.mTabHost.addTab(localTabSpec);
  }

  private void setupRatingTab()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, TabbedTopAppBrowser.class);
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("ratings");
    String str = getResources().getString(2131296259);
    Drawable localDrawable = getResources().getDrawable(2130837541);
    localTabSpec.setIndicator(str, localDrawable);
    localTabSpec.setContent(localIntent);
    this.mTabHost.addTab(localTabSpec);
  }

  private void setupSearchTab()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, SearchActivity.class);
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("search");
    String str = getResources().getString(2131296453);
    Drawable localDrawable = getResources().getDrawable(2130837544);
    localTabSpec.setIndicator(str, localDrawable);
    localTabSpec.setContent(localIntent);
    this.mTabHost.addTab(localTabSpec);
  }

  private void setupTabWidget()
  {
    View localView1 = this.tw.getChildAt(0);
    View localView2 = this.tw.getChildAt(1);
    View localView3 = this.tw.getChildAt(2);
    View localView4 = this.tw.getChildAt(3);
    View localView5 = this.tw.getChildAt(4);
    int i = this.display.getWidth() / 5;
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i, -1);
    localView1.setLayoutParams(localLayoutParams);
    localView2.setLayoutParams(localLayoutParams);
    localView3.setLayoutParams(localLayoutParams);
    localView4.setLayoutParams(localLayoutParams);
    localView5.setLayoutParams(localLayoutParams);
  }

  private void setupTabs()
  {
    setupNewTab();
    setupRatingTab();
    setupCategoryTab();
    setupSearchTab();
    setupDownloadsTab();
    setupTabWidget();
    int j;
    if ((this.tw.getChildAt(0) instanceof LinearLayout))
    {
      LinearLayout localLinearLayout = (LinearLayout)this.tw.getChildAt(4);
      ((ImageView)localLinearLayout.getChildAt(0)).setId(5);
      TextView localTextView1 = (TextView)LayoutInflater.from(this).inflate(2130903120, null);
      this.mUpdateView = localTextView1;
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      int i = this.updateNumberLeftMargin;
      localLayoutParams.leftMargin = i;
      TextView localTextView2 = this.mUpdateView;
      localLinearLayout.addView(localTextView2, localLayoutParams);
      TextView localTextView3 = this.mUpdateView;
      localLinearLayout.bringChildToFront(localTextView3);
      TextView localTextView4 = this.mUpdateView;
      ColorStateList localColorStateList1 = getResources().getColorStateList(2130903113);
      localTextView4.setTextColor(localColorStateList1);
      this.mUpdateView.setVisibility(8);
      j = 0;
    }
    while (true)
    {
      int k = this.tw.getChildCount();
      if (j >= k)
      {
        return;
        RelativeLayout localRelativeLayout = (RelativeLayout)this.tw.getChildAt(4);
        View localView = localRelativeLayout.getChildAt(0);
        if ((localView instanceof RelativeLayout))
          ((ImageView)((RelativeLayout)localView).getChildAt(0)).setId(5);
        while (true)
        {
          TextView localTextView5 = (TextView)LayoutInflater.from(this).inflate(2130903120, null);
          this.mUpdateView = localTextView5;
          RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
          this.lp1 = localLayoutParams1;
          this.lp1.addRule(5, 5);
          this.lp1.addRule(10);
          RelativeLayout.LayoutParams localLayoutParams2 = this.lp1;
          int m = this.updateNumberLeftMargin;
          localLayoutParams2.leftMargin = m;
          TextView localTextView6 = this.mUpdateView;
          RelativeLayout.LayoutParams localLayoutParams3 = this.lp1;
          localRelativeLayout.addView(localTextView6, localLayoutParams3);
          TextView localTextView7 = this.mUpdateView;
          localRelativeLayout.bringChildToFront(localTextView7);
          TextView localTextView8 = this.mUpdateView;
          ColorStateList localColorStateList2 = getResources().getColorStateList(2130903113);
          localTextView8.setTextColor(localColorStateList2);
          this.mUpdateView.setVisibility(8);
          break;
          ((ImageView)localRelativeLayout.getChildAt(0)).setId(5);
        }
      }
      if (DeviceUtil.getSDKVersionInt() <= 7);
      try
      {
        Field localField1 = this.tw.getClass().getDeclaredField("mBottomLeftStrip");
        this.mBottomLeftStrip = localField1;
        Field localField2 = this.tw.getClass().getDeclaredField("mBottomRightStrip");
        this.mBottomRightStrip = localField2;
        if (!this.mBottomLeftStrip.isAccessible())
          this.mBottomLeftStrip.setAccessible(1);
        if (!this.mBottomRightStrip.isAccessible())
          this.mBottomRightStrip.setAccessible(1);
        Field localField3 = this.mBottomLeftStrip;
        TabWidget localTabWidget1 = this.tw;
        Drawable localDrawable1 = getResources().getDrawable(2130837510);
        localField3.set(localTabWidget1, localDrawable1);
        Field localField4 = this.mBottomRightStrip;
        TabWidget localTabWidget2 = this.tw;
        Drawable localDrawable2 = getResources().getDrawable(2130837510);
        localField4.set(localTabWidget2, localDrawable2);
        while (true)
        {
          label549: TextView localTextView9 = (TextView)((ViewGroup)this.tw.getChildAt(j)).getChildAt(1);
          if (localTextView9 != null)
          {
            ColorStateList localColorStateList3 = getResources().getColorStateList(2130903113);
            localTextView9.setTextColor(localColorStateList3);
          }
          j += 1;
          break;
          try
          {
            Class[] arrayOfClass = new Class[1];
            Class localClass = Boolean.TYPE;
            arrayOfClass[0] = localClass;
            Method localMethod = TabWidget.class.getMethod("setStripEnabled", arrayOfClass);
            TabWidget localTabWidget3 = this.tw;
            Object[] arrayOfObject = new Object[1];
            Boolean localBoolean = Boolean.valueOf(0);
            arrayOfObject[0] = localBoolean;
            localMethod.invoke(localTabWidget3, arrayOfObject);
          }
          catch (Exception localException1)
          {
          }
        }
      }
      catch (Exception localException2)
      {
        break label549;
      }
    }
  }

  private void startInstall()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("_id", 9999);
    String str1 = this.mAssetDetail.title;
    localIntent.putExtra("title", str1);
    int i = this.mAssetDetail.size;
    localIntent.putExtra("size", i);
    String str2 = this.mAssetDetail.pkgName;
    localIntent.putExtra("package", str2);
    String[] arrayOfString = this.mAssetDetail.permissions;
    localIntent.putExtra("permission", arrayOfString);
    String str3 = AssetPermissionsSubActivity.class.getName();
    localIntent.setClassName(this, str3);
    startActivity(localIntent);
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
      switch (paramKeyEvent.getKeyCode())
      {
      default:
      case 4:
      }
    int i;
    for (boolean bool = dispatchKeyEvent(paramKeyEvent); ; i = 1)
    {
      return bool;
      Intent localIntent = new Intent().setClass(this, ExitDialog.class);
      startActivityForResult(localIntent, 0);
    }
  }

  public int getUpdateAvaliableNum()
  {
    return this.mUpdateAvaliableNum;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      AsyncTaskNotifier.getInstance(this).sendUpdateNumNotification(0);
      AsyncTaskNotifier.getInstance(this).cancelAllNotification();
      unregisterIntentReceivers();
      FileManager.deleteApkFiles(this);
      Process.killProcess(Process.myPid());
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    setupTabWidget();
    if (this.currentTabId == 0)
      CommonAppListActivity.resetTopFourApp();
    onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    Display localDisplay1 = getWindowManager().getDefaultDisplay();
    this.display = localDisplay1;
    Display localDisplay2 = getWindowManager().getDefaultDisplay();
    DisplayMetrics localDisplayMetrics1 = displayMetrics;
    localDisplay2.getMetrics(localDisplayMetrics1);
    screenWidth = this.display.getWidth();
    screenHeight = this.display.getHeight();
    int i = screenHeight;
    int j = screenWidth;
    int k;
    if (i > j)
    {
      k = screenHeight;
      longerSide = k;
      int m = screenHeight;
      int n = screenWidth;
      if (m >= n)
        break label325;
      k = screenHeight;
      shorterSide = k;
      int i1 = shorterSide / 20;
      this.updateNumberLeftMargin = i1;
      originalOrientation = getResources().getConfiguration().orientation;
      Display localDisplay3 = this.display;
      DisplayMetrics localDisplayMetrics2 = displayMetrics;
      localDisplay3.getMetrics(localDisplayMetrics2);
      density = displayMetrics.density;
      requestWindowFeature(1);
      MarketService localMarketService = MarketService.getServiceInstance(this);
      this.mMarketService = localMarketService;
      setTheme(16973834);
      Intent localIntent1 = getIntent();
      if (localIntent1.getData() == null)
      {
        Uri localUri = MarketStore.CONTENT_URI;
        localIntent1.setData(localUri);
      }
      setContentView(2130903082);
      initHandlerInUIThread();
      TabHost localTabHost = getTabHost();
      this.mTabHost = localTabHost;
      TabWidget localTabWidget = this.mTabHost.getTabWidget();
      this.tw = localTabWidget;
      setupTabs();
      setCurrentTab(0);
      this.mTabHost.setOnTabChangedListener(this);
      requestUpdateAvaliableNums();
      if (!GlobalUtil.allowInstallNonMarketApps(this))
        break label339;
      if (paramBundle == null);
    }
    while (true)
    {
      try
      {
        if (paramBundle.getBoolean("checked_self_update"))
          continue;
        checkTestUserAvailable();
        registerIntentReceivers();
        tw_out = this.tw;
        return;
        k = screenWidth;
        break;
        label325: k = screenWidth;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        continue;
      }
      label339: Intent localIntent2 = new Intent(this, NotMarketWarningDialog.class);
      startActivity(localIntent2);
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    AlertDialog localAlertDialog;
    switch (paramInt)
    {
    default:
      localAlertDialog = null;
    case 100:
    case 300:
    case 200:
    }
    while (true)
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296338).setMessage(2131296339);
      AssetBrowser.6 local6 = new AssetBrowser.6(this);
      localAlertDialog = localBuilder1.setPositiveButton(2131296336, local6).setNegativeButton(2131296337, null).create();
      continue;
      if (this.mAssetDetail != null)
      {
        View localView = LayoutInflater.from(this).inflate(2130903118, null);
        TextView localTextView = (TextView)localView.findViewById(2131427540);
        String str = this.mAssetDetail.description;
        int i = str.indexOf("&nbsp;&nbsp;");
        if (i != -1)
          str = str.substring(0, i);
        localTextView.setText(str);
        AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296340).setView(localView);
        AssetBrowser.7 local7 = new AssetBrowser.7(this);
        AlertDialog.Builder localBuilder3 = localBuilder2.setPositiveButton(2131296336, local7);
        AssetBrowser.8 local8 = new AssetBrowser.8(this);
        localAlertDialog = localBuilder3.setNegativeButton(2131296337, local8).create();
        continue;
      }
      localAlertDialog = null;
      continue;
      AlertDialog.Builder localBuilder4 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      AssetBrowser.9 local9 = new AssetBrowser.9(this);
      AlertDialog.Builder localBuilder5 = localBuilder4.setPositiveButton(2131296379, local9);
      AssetBrowser.10 local10 = new AssetBrowser.10(this);
      localAlertDialog = localBuilder5.setNegativeButton(2131296373, local10).create();
    }
  }

  protected void onDestroy()
  {
    AsyncTaskNotifier.getInstance(this).sendUpdateNumNotification(0);
    unregisterIntentReceivers();
    onDestroy();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    onNewIntent(paramIntent);
    setIntent(paramIntent);
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
    if (getIntent().getIntExtra("tabId", -1) == 4)
    {
      onTabChanged("downloads");
      this.tw.setCurrentTab(4);
      this.tw.getChildAt(4).performClick();
      getIntent().putExtra("tabId", -1);
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("checked_self_update", 1);
    onSaveInstanceState(paramBundle);
  }

  public void onTabChanged(String paramString)
  {
    int i;
    if ("new".equals(paramString))
    {
      i = 0;
      MobclickAgent.onEvent(this, "ChangeTab", "new");
    }
    while (true)
    {
      setCurrentTab(i);
      return;
      if ("ratings".equals(paramString))
      {
        i = 1;
        MobclickAgent.onEvent(this, "ChangeTab", "ratings");
        continue;
      }
      if ("category".equals(paramString))
      {
        i = 2;
        MobclickAgent.onEvent(this, "ChangeTab", "category");
        continue;
      }
      if ("search".equals(paramString))
      {
        i = 3;
        MobclickAgent.onEvent(this, "ChangeTab", "search");
        continue;
      }
      i = 4;
      MobclickAgent.onEvent(this, "ChangeTab", "downloads");
    }
  }

  public void setUpdateAvaliableNum(int paramInt)
  {
    this.mUpdateAvaliableNum = paramInt;
    try
    {
      Handler localHandler = this.mHandler;
      Integer localInteger = Integer.valueOf(paramInt);
      Message localMessage = Message.obtain(localHandler, 4, localInteger);
      this.mHandler.sendMessage(localMessage);
      AsyncTaskNotifier.getInstance(this).sendUpdateNumNotification(paramInt);
      label41: return;
    }
    catch (Exception localException)
    {
      break label41;
    }
  }

  protected void update()
  {
    if (DownloadService.getInstance(this).isTaskRunning(9999))
      Toast.makeText(this, 2131296351, 1).show();
    while (true)
    {
      return;
      startInstall();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetBrowser
 * JD-Core Version:    0.6.0
 */