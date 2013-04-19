package com.yingyonghui.market;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.install.InstallAppProgress;
import com.yingyonghui.market.install.UninstallAppProgress;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.Signature;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssetInfoActivity extends TabActivity
  implements TabHost.OnTabChangeListener, View.OnClickListener
{
  private static final int ACTION_APP_DETAILS = 0;
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int ACTIVITY_INSTALL = 100;
  private static final int ACTIVITY_UNINSTALL = 200;
  private static final int BUTTON_STATUS_BUY = 1;
  private static final int BUTTON_STATUS_COMMENT = 7;
  private static final int BUTTON_STATUS_DOWNLOADING = 5;
  private static final int BUTTON_STATUS_INSTALL = 0;
  private static final int BUTTON_STATUS_INSTALLING = 6;
  private static final int BUTTON_STATUS_OPEN = 2;
  private static final int BUTTON_STATUS_SELECT = 4;
  private static final int BUTTON_STATUS_UPDATE = 3;
  private static final int DIALOG_NETWORK_ERROR = 100;
  public static int DOWNLOADING_ASSET_ID = 0;
  public static final int ID_RELATIVE_APP = 12;
  private static final String TAB_APP_COMMENT = "comment";
  private static final String TAB_APP_DETAIL = "detail";
  protected static final int TAB_ID_COMMENT = 1;
  protected static final int TAB_ID_DETAIL = 0;
  protected static final int TAB_ID_RELATIVE = 2;
  private static final String TAB_RELATIVE_APP = "relative";

  @Signature({"Ljava/util/ArrayList", "<", "Ljava/lang/Integer;", ">;"})
  private ArrayList downloadArray;
  private int height = 44;
  private NumberFormat instance;
  private float labelTextSize = 18.0F;
  private int mAppIntallStatus;
  private final BroadcastReceiver mApplicationsReceiver;
  private Asset mAsset = null;
  private AssetDetail mAssetDetail = null;
  private int mAssetId;
  private View mAssetInfo;
  private int mAssetInstalled;
  private String mAssetTitle;
  private Button mBottomButton;
  private Field mBottomLeftStrip;
  private Field mBottomRightStrip;
  private Button mBuyButton;
  private boolean mCancel;
  private Button mCancelDownloadButton;
  private Request mCurrentRequest;
  private int mCurrentTab = 0;
  private boolean mDestroyed;
  private TextView mDownloadIndicator;
  private ProgressBar mDownloadProgress;
  private View mDownloadStatusContainer;
  private Handler mDownloadStatusHandler;
  private Button mDummyButton;
  private String mFromPage;
  private Handler mHandler;
  private int mInfoButtonStatus;
  private View mInfoButtonsBar;
  private Button mInstallButton;
  private Button mInstallingButton;
  private Button mLaunchButton;
  private View mLoadingIndicator;
  private IMarketService mMarketService;
  private String mPackageName;
  private boolean mPreinstallReview;
  private Button mSelectButton;
  private View mSpacerLeft;
  private View mSpacerRight;
  private TabHost mTabHost;
  private Button mUninstallButton;
  private Button mUnselectButton;
  private Button mUpdateButton;
  private Handler mWifiHandler;

  @Signature({"Ljava/util/ArrayList", "<", "Landroid/view/View;", ">;"})
  private ArrayList tabViews;
  private TextView titleView;
  private TabWidget tw;

  public AssetInfoActivity()
  {
    ArrayList localArrayList = new ArrayList();
    this.downloadArray = localArrayList;
    this.mDestroyed = 0;
    this.mCancel = 0;
    AssetInfoActivity.1 local1 = new AssetInfoActivity.1(this);
    this.mApplicationsReceiver = local1;
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    this.instance = localNumberFormat;
  }

  private void addDetailRequest()
  {
    Request localRequest = new Request(0L, 65539);
    String str = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    if (str == null)
      str = "";
    Object[] arrayOfObject = new Object[2];
    Integer localInteger = Integer.valueOf(this.mAssetId);
    arrayOfObject[0] = localInteger;
    arrayOfObject[1] = str;
    localRequest.setData(arrayOfObject);
    AssetInfoActivity.6 local6 = new AssetInfoActivity.6(this, localRequest);
    localRequest.addObserver(local6);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getAppDetail(localRequest);
  }

  private void addDownloadAndInstallRequest()
  {
    if (this.mAsset != null)
    {
      Request localRequest = new Request(0L, 65541);
      int i = this.mAsset.size;
      String str1 = this.mAsset.pkgName;
      String str2 = this.mAsset.title;
      Object[] arrayOfObject = new Object[4];
      Integer localInteger1 = Integer.valueOf(this.mAssetId);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject[1] = localInteger2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = str2;
      localRequest.setData(arrayOfObject);
      Handler localHandler = this.mWifiHandler;
      localRequest.addWifiObserver(localHandler);
      MarketService.getServiceInstance(this).getAppContentStream(localRequest);
      ArrayList localArrayList = this.downloadArray;
      Integer localInteger3 = Integer.valueOf(this.mAsset._id);
      localArrayList.add(localInteger3);
    }
  }

  private void addDownloadLogRequest()
  {
    if (this.mAsset == null);
    int i;
    do
    {
      return;
      ArrayList localArrayList = this.downloadArray;
      Integer localInteger1 = Integer.valueOf(this.mAsset._id);
      i = localArrayList.indexOf(localInteger1);
    }
    while (i == -1);
    this.downloadArray.remove(i);
    Request localRequest = new Request(0L, 65553);
    if (this.mAssetInstalled == 2);
    for (String str1 = "update"; ; str1 = "install")
    {
      StringBuilder localStringBuilder = new StringBuilder("AssetInfo:");
      String str2 = this.mFromPage;
      String str3 = str2;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = str3;
      Integer localInteger2 = Integer.valueOf(this.mAssetId);
      arrayOfObject[2] = localInteger2;
      localRequest.setData(arrayOfObject);
      MarketService.getServiceInstance(this).getInstallUpdateLog(localRequest);
      break;
    }
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    StringBuilder localStringBuilder = new StringBuilder("AssetInfo:");
    String str1 = this.mFromPage;
    String str2 = str1;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = str2;
    Integer localInteger = Integer.valueOf(this.mAssetId);
    arrayOfObject[1] = localInteger;
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private boolean checkAppDownloading(int paramInt)
  {
    return DownloadService.getInstance(this).isTaskRunning(paramInt);
  }

  private String getDownloadingText(int paramInt1, int paramInt2)
  {
    String str1;
    if (paramInt1 <= 0)
    {
      str1 = "";
      return str1;
    }
    double d1 = paramInt2 * 1.0D;
    double d2 = paramInt1;
    int i = (int)(d1 / d2 * 0.0F);
    StringBuilder localStringBuilder = new StringBuilder();
    if (i > 100)
      i = 100;
    localStringBuilder.append(i);
    localStringBuilder.append(37);
    localStringBuilder.append("  ");
    if (paramInt1 > 1232896)
    {
      this.instance.setMaximumFractionDigits(2);
      NumberFormat localNumberFormat1 = this.instance;
      double d3 = paramInt2 / 1048576.0F;
      String str2 = String.valueOf(localNumberFormat1.format(d3));
      String str3 = str2 + "M";
      localStringBuilder.append(str3);
      localStringBuilder.append("/");
      NumberFormat localNumberFormat2 = this.instance;
      double d4 = paramInt1 / 1048576.0F;
      String str4 = String.valueOf(localNumberFormat2.format(d4));
      String str5 = str4 + "M";
      localStringBuilder.append(str5);
    }
    while (true)
    {
      str1 = localStringBuilder.toString();
      break;
      this.instance.setMaximumFractionDigits(0);
      NumberFormat localNumberFormat3 = this.instance;
      long l1 = paramInt2 / 1024;
      String str6 = String.valueOf(localNumberFormat3.format(l1));
      String str7 = str6 + "K";
      localStringBuilder.append(str7);
      localStringBuilder.append("/");
      NumberFormat localNumberFormat4 = this.instance;
      long l2 = paramInt1 / 1024;
      String str8 = String.valueOf(localNumberFormat4.format(l2));
      String str9 = str8 + "K";
      localStringBuilder.append(str9);
    }
  }

  private int getInstalledAppVersionCode(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(paramString, 8192);
      if (localPackageInfo != null)
      {
        i = localPackageInfo.versionCode;
        return i;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        int i = -1;
    }
  }

  public static Intent getIntent(String paramString, PackageManager paramPackageManager)
  {
    Intent localIntent1 = new Intent("android.intent.action.MAIN");
    localIntent1.addCategory("android.intent.category.LAUNCHER");
    Object localObject = paramPackageManager.queryIntentActivities(localIntent1, 0).iterator();
    if (!((Iterator)localObject).hasNext());
    Intent localIntent2;
    for (localObject = null; ; localObject = localIntent2)
    {
      return localObject;
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      String str1 = localResolveInfo.activityInfo.packageName;
      if (!paramString.equals(str1))
        break;
      localIntent2 = new Intent("android.intent.action.MAIN");
      localIntent2.addCategory("android.intent.category.LAUNCHER");
      String str2 = localResolveInfo.activityInfo.name;
      localIntent2.setClassName(paramString, str2);
    }
  }

  private void initHandlerInUIThread()
  {
    AssetInfoActivity.7 local7 = new AssetInfoActivity.7(this);
    this.mHandler = local7;
    AssetInfoActivity.8 local8 = new AssetInfoActivity.8(this);
    this.mDownloadStatusHandler = local8;
  }

  private void initTabs()
  {
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("detail");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView.setBackgroundResource(2130837700);
    localTextView.getBackground().setCallback(null);
    localTextView.setText(2131296324);
    int i = getResources().getColor(2130837744);
    localTextView.setTextColor(i);
    float f1 = this.labelTextSize;
    localTextView.setTextSize(f1);
    localTextView.setLayoutParams(localLayoutParams);
    localTextView.setTag("detail");
    try
    {
      Class[] arrayOfClass1 = new Class[1];
      arrayOfClass1[0] = View.class;
      Method localMethod1 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass1);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localTextView;
      localMethod1.invoke(localTabSpec, arrayOfObject1);
      label193: this.tabViews.add(localTextView);
      Intent localIntent1 = new Intent();
      String str1 = AssetInfoDetailActivity.class.getName();
      localIntent1.setClassName(this, str1);
      int j = this.mAssetId;
      localIntent1.putExtra("_id", j);
      localTabSpec.setContent(localIntent1);
      this.mTabHost.addTab(localTabSpec);
      localTabSpec = this.mTabHost.newTabSpec("comment");
      localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
      localTextView.setBackgroundResource(2130837700);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296325);
      int k = getResources().getColor(2130837744);
      localTextView.setTextColor(k);
      float f2 = this.labelTextSize;
      localTextView.setTextSize(f2);
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setTag("comment");
      try
      {
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = View.class;
        Method localMethod2 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass2);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localTextView;
        localMethod2.invoke(localTabSpec, arrayOfObject2);
        label403: this.tabViews.add(localTextView);
        Intent localIntent2 = new Intent();
        String str2 = CommentsActivity.class.getName();
        localIntent2.setClassName(this, str2);
        int m = this.mAssetId;
        localIntent2.putExtra("_id", m);
        localTabSpec.setContent(localIntent2);
        this.mTabHost.addTab(localTabSpec);
        localTabSpec = this.mTabHost.newTabSpec("relative");
        localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
        localTextView.setBackgroundResource(2130837700);
        localTextView.getBackground().setCallback(null);
        localTextView.setText(2131296326);
        int n = getResources().getColor(2130837744);
        localTextView.setTextColor(n);
        float f3 = this.labelTextSize;
        localTextView.setTextSize(f3);
        localTextView.setLayoutParams(localLayoutParams);
        localTextView.setTag("relative");
        try
        {
          Class[] arrayOfClass3 = new Class[1];
          arrayOfClass3[0] = View.class;
          Method localMethod3 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass3);
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = localTextView;
          localMethod3.invoke(localTabSpec, arrayOfObject3);
          label613: this.tabViews.add(localTextView);
          Intent localIntent3 = new Intent();
          String str3 = FilteredAppListActivity.class.getName();
          localIntent3.setClassName(this, str3);
          localIntent3.putExtra("_id", 12);
          localIntent3.putExtra("ranking_type", 2);
          localIntent3.putExtra("header", 1);
          String str4 = this.mPackageName;
          localIntent3.putExtra("pkgName", str4);
          localTabSpec.setContent(localIntent3);
          this.mTabHost.addTab(localTabSpec);
          this.mTabHost.setOnTabChangedListener(this);
          setCurrentTab(0);
          return;
        }
        catch (Exception localException1)
        {
          break label613;
        }
      }
      catch (Exception localException2)
      {
        break label403;
      }
    }
    catch (Exception localException3)
    {
      break label193;
    }
  }

  private void initTabsLow()
  {
    TabHost.TabSpec localTabSpec1 = this.mTabHost.newTabSpec("detail");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView1 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView1.setBackgroundResource(2130837700);
    localTextView1.getBackground().setCallback(null);
    localTextView1.setText(2131296324);
    int i = getResources().getColor(2130837744);
    localTextView1.setTextColor(i);
    float f1 = this.labelTextSize;
    localTextView1.setTextSize(f1);
    localTextView1.setLayoutParams(localLayoutParams);
    localTextView1.setTag("detail");
    String str1 = getResources().getString(2131296324);
    localTabSpec1.setIndicator(str1);
    this.tabViews.add(localTextView1);
    Intent localIntent1 = new Intent();
    String str2 = AssetInfoDetailActivity.class.getName();
    localIntent1.setClassName(this, str2);
    int j = this.mAssetId;
    localIntent1.putExtra("_id", j);
    localTabSpec1.setContent(localIntent1);
    this.mTabHost.addTab(localTabSpec1);
    TabHost.TabSpec localTabSpec2 = this.mTabHost.newTabSpec("comment");
    TextView localTextView2 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView2.setBackgroundResource(2130837700);
    localTextView2.getBackground().setCallback(null);
    localTextView2.setText(2131296325);
    int k = getResources().getColor(2130837744);
    localTextView2.setTextColor(k);
    float f2 = this.labelTextSize;
    localTextView2.setTextSize(f2);
    localTextView2.setLayoutParams(localLayoutParams);
    localTextView2.setTag("comment");
    String str3 = getResources().getString(2131296325);
    localTabSpec2.setIndicator(str3);
    this.tabViews.add(localTextView2);
    Intent localIntent2 = new Intent();
    String str4 = CommentsActivity.class.getName();
    localIntent2.setClassName(this, str4);
    int m = this.mAssetId;
    localIntent2.putExtra("_id", m);
    localTabSpec2.setContent(localIntent2);
    this.mTabHost.addTab(localTabSpec2);
    TabHost.TabSpec localTabSpec3 = this.mTabHost.newTabSpec("relative");
    TextView localTextView3 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView3.setBackgroundResource(2130837700);
    localTextView3.getBackground().setCallback(null);
    localTextView3.setText(2131296326);
    int n = getResources().getColor(2130837744);
    localTextView3.setTextColor(n);
    float f3 = this.labelTextSize;
    localTextView3.setTextSize(f3);
    localTextView3.setLayoutParams(localLayoutParams);
    localTextView3.setTag("relative");
    String str5 = getResources().getString(2131296326);
    localTabSpec3.setIndicator(str5);
    this.tabViews.add(localTextView3);
    Intent localIntent3 = new Intent();
    String str6 = FilteredAppListActivity.class.getName();
    localIntent3.setClassName(this, str6);
    localIntent3.putExtra("_id", 12);
    localIntent3.putExtra("ranking_type", 2);
    localIntent3.putExtra("header", 1);
    String str7 = this.mPackageName;
    localIntent3.putExtra("pkgName", str7);
    localTabSpec3.setContent(localIntent3);
    this.mTabHost.addTab(localTabSpec3);
    this.mTabHost.setOnTabChangedListener(this);
    setCurrentTabLow(0);
  }

  private boolean isPermissionsDangerous(String[] paramArrayOfString)
  {
    int i = 0;
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
        return false;
      String str = paramArrayOfString[i];
      if ((!"android.permission.PROCESS_OUTGOING_CALLS".equals(str)) && (!"android.permission.READ_CONTACTS".equals(str)) && (!"android.permission.READ_SMS".equals(str)) && (!"android.permission.SEND_SMS".equals(str)) && (!"android.permission.CALL_PHONE".equals(str)) && (!"android.permission.CALL_PRIVILEGED".equals(str)) && ("android.permission.CHANGE_CONFIGURATION".equals(str)));
      i += 1;
    }
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter1 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter1.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter1.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter1.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver1 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver1, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("request_comment_detail");
    BroadcastReceiver localBroadcastReceiver2 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver2, localIntentFilter2);
  }

  private void sendAssetDetail()
  {
    if (this.mAssetDetail != null)
    {
      Intent localIntent = new Intent("send_asset_detail");
      Bundle localBundle = new Bundle();
      int i = this.mAssetDetail._id;
      localBundle.putInt("_id", i);
      String str1 = this.mAssetDetail.title;
      localBundle.putString("title", str1);
      String str2 = this.mAssetDetail.author;
      localBundle.putString("author", str2);
      float f = this.mAssetDetail.rating;
      localBundle.putFloat("rating", f);
      int j = this.mAssetDetail.ratingNumbers;
      localBundle.putInt("ratingNumbers", j);
      String str3 = this.mAssetDetail.pkgName;
      localBundle.putString("pkgName", str3);
      int k = this.mAssetDetail.size;
      localBundle.putInt("size", k);
      String str4 = this.mAssetDetail.version;
      localBundle.putString("version", str4);
      String str5 = this.mAssetDetail.lastUpdate;
      localBundle.putString("lastUpdate", str5);
      int m = this.mAssetDetail.versionCode;
      localBundle.putInt("versionCode", m);
      int n = this.mAssetDetail.downloadsNumber;
      localBundle.putInt("downloadsNumber", n);
      String str6 = this.mAssetDetail.description;
      localBundle.putString("description", str6);
      String str7 = this.mAssetDetail.email;
      localBundle.putString("email", str7);
      String str8 = this.mAssetDetail.myComment;
      localBundle.putString("myComment", str8);
      long l = this.mAssetDetail.myCommentDate;
      localBundle.putLong("myCommentDate", l);
      int i1 = this.mAssetDetail.myRating;
      localBundle.putInt("myRating", i1);
      String str9 = this.mAssetDetail.devLogin;
      localBundle.putString("devLogin", str9);
      int i2 = this.mAssetDetail.installed;
      localBundle.putInt("installed", i2);
      localIntent.putExtras(localBundle);
      sendBroadcast(localIntent);
    }
  }

  private void sendCommentDetail()
  {
    if (this.mAssetDetail != null)
    {
      Intent localIntent = new Intent("send_comment_detail");
      Bundle localBundle = new Bundle();
      int i = this.mAssetDetail._id;
      localBundle.putInt("_id", i);
      String str1 = this.mAssetDetail.title;
      localBundle.putString("title", str1);
      String str2 = this.mAssetDetail.author;
      localBundle.putString("author", str2);
      float f = this.mAssetDetail.rating;
      localBundle.putFloat("rating", f);
      int j = this.mAssetDetail.ratingNumbers;
      localBundle.putInt("ratingNumbers", j);
      String str3 = this.mAssetDetail.pkgName;
      localBundle.putString("pkgName", str3);
      int k = this.mAssetDetail.size;
      localBundle.putInt("size", k);
      String str4 = this.mAssetDetail.version;
      localBundle.putString("version", str4);
      String str5 = this.mAssetDetail.lastUpdate;
      localBundle.putString("lastUpdate", str5);
      int m = this.mAssetDetail.versionCode;
      localBundle.putInt("versionCode", m);
      int n = this.mAssetDetail.downloadsNumber;
      localBundle.putInt("downloadsNumber", n);
      String str6 = this.mAssetDetail.description;
      localBundle.putString("description", str6);
      String str7 = this.mAssetDetail.email;
      localBundle.putString("email", str7);
      String str8 = this.mAssetDetail.myComment;
      localBundle.putString("myComment", str8);
      long l = this.mAssetDetail.myCommentDate;
      localBundle.putLong("myCommentDate", l);
      int i1 = this.mAssetDetail.myRating;
      localBundle.putInt("myRating", i1);
      String str9 = this.mAssetDetail.devLogin;
      localBundle.putString("devLogin", str9);
      int i2 = this.mAssetDetail.installed;
      localBundle.putInt("installed", i2);
      localIntent.putExtras(localBundle);
      sendBroadcast(localIntent);
    }
  }

  private void setButtonStatus(int paramInt)
  {
    if (paramInt != 7)
      this.mInfoButtonStatus = paramInt;
    switch (paramInt)
    {
    default:
      this.mAppIntallStatus = paramInt;
      int i = this.mAssetId;
      if (checkAppDownloading(i))
      {
        this.mDownloadStatusContainer.setVisibility(0);
        this.mBottomButton.setVisibility(8);
        this.mLaunchButton.setVisibility(8);
        this.mUpdateButton.setVisibility(8);
        this.mUninstallButton.setVisibility(8);
        this.mSelectButton.setVisibility(8);
        this.mUnselectButton.setVisibility(8);
        this.mDownloadStatusContainer.setVisibility(0);
        ((TextView)this.mDownloadStatusContainer.findViewById(2131427348)).setText(2131296412);
        this.mInstallingButton.setVisibility(8);
        this.mDummyButton.setVisibility(8);
        this.mCancelDownloadButton.setVisibility(0);
        this.mSpacerLeft.setVisibility(0);
        this.mSpacerRight.setVisibility(0);
      }
      if (this.mCurrentTab != 0)
        break;
      this.mInfoButtonsBar.setVisibility(0);
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      this.mBottomButton.setVisibility(8);
      this.mLaunchButton.setVisibility(8);
      this.mUpdateButton.setVisibility(8);
      this.mUninstallButton.setVisibility(8);
      this.mCancelDownloadButton.setVisibility(8);
      this.mInstallingButton.setVisibility(8);
      this.mSelectButton.setVisibility(8);
      this.mUnselectButton.setVisibility(8);
      this.mDownloadStatusContainer.setVisibility(8);
      this.mDummyButton.setVisibility(8);
      this.mBottomButton.setVisibility(0);
      this.mSpacerLeft.setVisibility(0);
      this.mSpacerRight.setVisibility(0);
      break;
      this.mBottomButton.setVisibility(8);
      this.mUpdateButton.setVisibility(8);
      this.mCancelDownloadButton.setVisibility(8);
      this.mInstallingButton.setVisibility(8);
      this.mSelectButton.setVisibility(8);
      this.mUnselectButton.setVisibility(8);
      this.mDownloadStatusContainer.setVisibility(8);
      this.mLaunchButton.setVisibility(0);
      this.mDummyButton.setVisibility(0);
      this.mUninstallButton.setVisibility(0);
      this.mSpacerLeft.setVisibility(0);
      this.mSpacerRight.setVisibility(0);
      break;
      this.mBottomButton.setVisibility(8);
      this.mLaunchButton.setVisibility(8);
      this.mCancelDownloadButton.setVisibility(8);
      this.mInstallingButton.setVisibility(8);
      this.mSelectButton.setVisibility(8);
      this.mUnselectButton.setVisibility(8);
      this.mDownloadStatusContainer.setVisibility(8);
      this.mUpdateButton.setVisibility(0);
      this.mDummyButton.setVisibility(0);
      this.mUninstallButton.setVisibility(0);
      this.mSpacerLeft.setVisibility(0);
      this.mSpacerRight.setVisibility(0);
      break;
      this.mBottomButton.setVisibility(8);
      this.mLaunchButton.setVisibility(8);
      this.mUpdateButton.setVisibility(8);
      this.mUninstallButton.setVisibility(8);
      this.mCancelDownloadButton.setVisibility(8);
      this.mInstallingButton.setVisibility(8);
      this.mDownloadStatusContainer.setVisibility(8);
      this.mSelectButton.setVisibility(0);
      this.mDummyButton.setVisibility(0);
      this.mUnselectButton.setVisibility(0);
      this.mSpacerLeft.setVisibility(8);
      this.mSpacerRight.setVisibility(8);
      break;
      this.mBottomButton.setVisibility(8);
      this.mLaunchButton.setVisibility(8);
      this.mUpdateButton.setVisibility(8);
      this.mUninstallButton.setVisibility(8);
      this.mSelectButton.setVisibility(8);
      this.mUnselectButton.setVisibility(8);
      this.mInstallingButton.setVisibility(8);
      this.mDummyButton.setVisibility(8);
      this.mCancelDownloadButton.setVisibility(0);
      this.mSpacerLeft.setVisibility(0);
      this.mSpacerRight.setVisibility(0);
      this.mDownloadStatusContainer.setVisibility(0);
      ((TextView)this.mDownloadStatusContainer.findViewById(2131427348)).setText(2131296412);
      break;
      this.mBottomButton.setVisibility(8);
      this.mLaunchButton.setVisibility(8);
      this.mUpdateButton.setVisibility(8);
      this.mUninstallButton.setVisibility(8);
      this.mSelectButton.setVisibility(8);
      this.mUnselectButton.setVisibility(8);
      this.mDummyButton.setVisibility(8);
      this.mDownloadStatusContainer.setVisibility(0);
      ((TextView)this.mDownloadStatusContainer.findViewById(2131427348)).setText(2131296276);
      this.mCancelDownloadButton.setVisibility(8);
      this.mInstallingButton.setVisibility(0);
      if (GlobalUtil.isSystemApp(getPackageManager()))
      {
        this.mInstallingButton.setText(2131296276);
        this.mInstallingButton.setEnabled(0);
      }
      while (true)
      {
        this.mSpacerLeft.setVisibility(0);
        this.mSpacerRight.setVisibility(0);
        break;
        this.mInstallingButton.setText(2131296278);
        Button localButton = this.mInstallingButton;
        Typeface localTypeface = Typeface.DEFAULT;
        localButton.setTypeface(localTypeface, 0);
        this.mInstallingButton.setTextColor(-1);
        this.mInstallingButton.setEnabled(1);
      }
      this.mInfoButtonsBar.setVisibility(8);
    }
  }

  private void setCurrentTab(int paramInt)
  {
    View localView1;
    View localView2;
    View localView3;
    if (paramInt == 0)
    {
      localView1 = (View)this.tabViews.get(0);
      localView2 = (View)this.tabViews.get(1);
      localView3 = (View)this.tabViews.get(2);
    }
    while (true)
    {
      localView1.setBackgroundResource(2130837700);
      localView1.getBackground().setCallback(null);
      localView1.setFocusable(1);
      localView1.setFocusableInTouchMode(1);
      localView1.requestFocus();
      localView1.requestFocusFromTouch();
      localView2.setBackgroundDrawable(null);
      localView3.setBackgroundDrawable(null);
      TextView localTextView1 = (TextView)localView1;
      Typeface localTypeface1 = Typeface.DEFAULT_BOLD;
      localTextView1.setTypeface(localTypeface1);
      TextView localTextView2 = (TextView)localView2;
      Typeface localTypeface2 = Typeface.DEFAULT;
      localTextView2.setTypeface(localTypeface2);
      return;
      if (paramInt == 1)
      {
        localView1 = (View)this.tabViews.get(1);
        localView2 = (View)this.tabViews.get(0);
        localView3 = (View)this.tabViews.get(2);
        continue;
      }
      localView1 = (View)this.tabViews.get(2);
      localView2 = (View)this.tabViews.get(0);
      localView3 = (View)this.tabViews.get(1);
    }
  }

  private void setCurrentTabLow(int paramInt)
  {
    RelativeLayout localRelativeLayout1 = (RelativeLayout)this.tw.getChildAt(0);
    RelativeLayout localRelativeLayout2 = (RelativeLayout)this.tw.getChildAt(1);
    RelativeLayout localRelativeLayout3 = (RelativeLayout)this.tw.getChildAt(2);
    if (paramInt == 0)
    {
      Drawable localDrawable1 = getResources().getDrawable(2130837700);
      localRelativeLayout1.setBackgroundDrawable(localDrawable1);
      localRelativeLayout1.getBackground().setCallback(null);
      localRelativeLayout2.setBackgroundDrawable(null);
      localRelativeLayout3.setBackgroundDrawable(null);
      localRelativeLayout1.setFocusable(1);
      localRelativeLayout1.setFocusableInTouchMode(1);
      localRelativeLayout1.requestFocus();
      localRelativeLayout1.requestFocusFromTouch();
    }
    while (true)
    {
      return;
      if (paramInt == 1)
      {
        localRelativeLayout1.setBackgroundDrawable(null);
        Drawable localDrawable2 = getResources().getDrawable(2130837700);
        localRelativeLayout2.setBackgroundDrawable(localDrawable2);
        localRelativeLayout2.getBackground().setCallback(null);
        localRelativeLayout3.setBackgroundDrawable(null);
        localRelativeLayout2.setFocusable(1);
        localRelativeLayout2.setFocusableInTouchMode(1);
        localRelativeLayout2.requestFocus();
        localRelativeLayout2.requestFocusFromTouch();
        continue;
      }
      localRelativeLayout1.setBackgroundDrawable(null);
      localRelativeLayout2.setBackgroundDrawable(null);
      Drawable localDrawable3 = getResources().getDrawable(2130837700);
      localRelativeLayout3.setBackgroundDrawable(localDrawable3);
      localRelativeLayout3.getBackground().setCallback(null);
      localRelativeLayout3.setFocusable(1);
      localRelativeLayout3.setFocusableInTouchMode(1);
      localRelativeLayout3.requestFocus();
      localRelativeLayout3.requestFocusFromTouch();
    }
  }

  private void setupButtonStatus()
  {
    if (!this.mPreinstallReview)
      switch (this.mAssetInstalled)
      {
      default:
      case 0:
      case 2:
      case 1:
      }
    while (true)
    {
      return;
      setButtonStatus(0);
      continue;
      setButtonStatus(3);
      if (this.mUpdateButton == null)
        continue;
      Asset localAsset = this.mAsset;
      if (FileManager.getFile(this, localAsset) == null)
      {
        this.mUpdateButton.setText(2131296362);
        continue;
      }
      this.mUpdateButton.setText(2131296418);
      continue;
      setButtonStatus(2);
      try
      {
        PackageManager localPackageManager = getPackageManager();
        String str = this.mPackageName;
        if (localPackageManager.getLaunchIntentForPackage(str) == null)
          break label146;
        this.mLaunchButton.setEnabled(1);
        this.mLaunchButton.setTextColor(-16777216);
      }
      catch (Exception localException)
      {
      }
      continue;
      label146: this.mLaunchButton.setEnabled(0);
      this.mLaunchButton.setTextColor(-7829368);
    }
  }

  private void setupDownloadStatusHandler()
  {
    DownloadService localDownloadService = DownloadService.getInstance(this);
    int i = this.mAssetId;
    Handler localHandler = this.mDownloadStatusHandler;
    localDownloadService.setHandler(i, localHandler);
  }

  private void setupPriceText(TextView paramTextView)
  {
    float f = this.mAsset.price;
    String str;
    if (this.mAppIntallStatus == 2)
    {
      paramTextView.setCompoundDrawables(null, null, null, null);
      str = getString(2131296358);
    }
    while (true)
    {
      paramTextView.setText(str);
      return;
      if (this.mAppIntallStatus == 3)
      {
        paramTextView.setCompoundDrawables(null, null, null, null);
        str = getString(2131296363);
        continue;
      }
      if (f == 0.0F)
      {
        str = getString(2131296357);
        continue;
      }
      str = String.valueOf((int)f);
      Drawable localDrawable = getResources().getDrawable(2130837526);
      paramTextView.setCompoundDrawablePadding(10);
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, localDrawable, null);
    }
  }

  private void setupTabs()
  {
    initTabs();
  }

  private void setupTabsLow()
  {
    initTabsLow();
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
      i = 0;
      int j = this.tw.getChildCount();
      if (i >= j)
        return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        int i;
        localException.printStackTrace();
        continue;
        TextView localTextView = (TextView)((RelativeLayout)this.tw.getChildAt(i)).getChildAt(1);
        if (localTextView != null)
        {
          int k = getResources().getColor(2130837744);
          localTextView.setTextColor(k);
          float f1 = AssetBrowser.density;
          int m = (int)(6.0F * f1);
          localTextView.setPadding(0, 0, 0, m);
        }
        ViewGroup.LayoutParams localLayoutParams = this.tw.getChildAt(i).getLayoutParams();
        float f2 = this.height;
        float f3 = AssetBrowser.density;
        int n = (int)(f2 * f3);
        localLayoutParams.height = n;
        i += 1;
      }
    }
  }

  private void setupViews()
  {
    View localView1 = findViewById(2131427346);
    this.mAssetInfo = localView1;
    View localView2 = findViewById(2131427468);
    this.mLoadingIndicator = localView2;
    this.mAssetInfo.setVisibility(8);
    this.mLoadingIndicator.setVisibility(0);
    TextView localTextView1 = (TextView)findViewById(2131427392);
    this.titleView = localTextView1;
    ImageButton localImageButton1 = (ImageButton)findViewById(2131427390);
    AssetInfoActivity.4 local4 = new AssetInfoActivity.4(this);
    localImageButton1.setOnClickListener(local4);
    ImageButton localImageButton2 = (ImageButton)findViewById(2131427391);
    localImageButton2.setVisibility(0);
    AssetInfoActivity.5 local5 = new AssetInfoActivity.5(this);
    localImageButton2.setOnClickListener(local5);
    View localView3 = findViewById(2131427347);
    this.mDownloadStatusContainer = localView3;
    ProgressBar localProgressBar = (ProgressBar)this.mDownloadStatusContainer.findViewById(2131427349);
    this.mDownloadProgress = localProgressBar;
    TextView localTextView2 = (TextView)this.mDownloadStatusContainer.findViewById(2131427350);
    this.mDownloadIndicator = localTextView2;
    View localView4 = findViewById(2131427352);
    this.mSpacerLeft = localView4;
    View localView5 = findViewById(2131427366);
    this.mSpacerRight = localView5;
    if (getIntent().getFloatExtra("price", 0.0F) == 0.0F)
    {
      Button localButton1 = (Button)findViewById(2131427359);
      this.mBottomButton = localButton1;
      Button localButton2 = (Button)findViewById(2131427359);
      this.mInstallButton = localButton2;
      Button localButton3 = (Button)findViewById(2131427358);
      this.mBuyButton = localButton3;
      Button localButton4 = (Button)findViewById(2131427353);
      this.mLaunchButton = localButton4;
      Button localButton5 = (Button)findViewById(2131427354);
      this.mUpdateButton = localButton5;
      Button localButton6 = (Button)findViewById(2131427360);
      this.mUninstallButton = localButton6;
      Button localButton7 = (Button)findViewById(2131427361);
      this.mCancelDownloadButton = localButton7;
      Button localButton8 = (Button)findViewById(2131427363);
      this.mInstallingButton = localButton8;
      Button localButton9 = (Button)findViewById(2131427355);
      this.mSelectButton = localButton9;
      Button localButton10 = (Button)findViewById(2131427364);
      this.mUnselectButton = localButton10;
      Button localButton11 = (Button)findViewById(2131427331);
      this.mDummyButton = localButton11;
      this.mLaunchButton.setOnClickListener(this);
      this.mUpdateButton.setOnClickListener(this);
      this.mUninstallButton.setOnClickListener(this);
      this.mBottomButton.setOnClickListener(this);
      this.mCancelDownloadButton.setOnClickListener(this);
      this.mSelectButton.setOnClickListener(this);
      this.mUnselectButton.setOnClickListener(this);
      this.mInstallingButton.setOnClickListener(this);
      View localView6 = findViewById(2131427351);
      this.mInfoButtonsBar = localView6;
      if (this.mInfoButtonsBar != null)
        this.mInfoButtonsBar.setVisibility(0);
      if (this.mPreinstallReview)
        break label597;
      setButtonStatus(0);
      this.mBottomButton.setEnabled(0);
      label543: setupDownloadStatusHandler();
      this.mBottomButton.setEnabled(1);
      if (DeviceUtil.getSDKVersionInt() != 3)
        break label605;
      setupTabsLow();
    }
    while (true)
    {
      setupButtonStatus();
      addDetailRequest();
      return;
      Button localButton12 = (Button)findViewById(2131427358);
      this.mBottomButton = localButton12;
      break;
      label597: setButtonStatus(4);
      break label543;
      label605: setupTabs();
    }
  }

  private void startBuy()
  {
    Toast.makeText(this, 2131296452, 0).show();
  }

  private void startInstall(File paramFile, int paramInt, String paramString1, String paramString2)
  {
    if (GlobalUtil.isSystemApp(getPackageManager()))
    {
      Intent localIntent1 = new Intent("android.intent.action.VIEW");
      localIntent1.putExtra("_id", paramInt);
      localIntent1.putExtra("title", paramString1);
      localIntent1.putExtra("packageName", paramString2);
      Uri localUri1 = Uri.fromFile(paramFile);
      localIntent1.setDataAndType(localUri1, "application/vnd.android.package-archive");
      localIntent1.setClass(this, InstallAppProgress.class);
      startActivity(localIntent1);
    }
    while (true)
    {
      return;
      Intent localIntent2 = new Intent();
      localIntent2.setAction("android.intent.action.VIEW");
      localIntent2.addCategory("android.intent.category.DEFAULT");
      Uri localUri2 = Uri.fromFile(paramFile);
      localIntent2.setDataAndType(localUri2, "application/vnd.android.package-archive");
      startActivity(localIntent2);
    }
  }

  private void startUninstallProgress()
  {
    Intent localIntent1;
    if (GlobalUtil.isSystemApp(getPackageManager()))
    {
      localIntent1 = new Intent("android.intent.action.VIEW");
      Intent localIntent2 = getIntent();
      int i = this.mAssetId;
      localIntent1.putExtra("_id", i);
      String str1 = localIntent2.getStringExtra("title");
      localIntent1.putExtra("title", str1);
    }
    while (true)
    {
      try
      {
        PackageManager localPackageManager = getPackageManager();
        String str2 = this.mAsset.pkgName;
        ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str2, 8192);
        localIntent1.putExtra("application_info", localApplicationInfo);
        localIntent1.setClass(this, UninstallAppProgress.class);
        startActivity(localIntent1);
        return;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        continue;
      }
      String str3 = this.mAsset.pkgName;
      Uri localUri = Uri.fromParts("package", str3, null);
      Intent localIntent3 = new Intent("android.intent.action.DELETE", localUri);
      try
      {
        startActivity(localIntent3);
        setResult(-1);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
      }
    }
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
      case 84:
      }
    int i;
    for (boolean bool = dispatchKeyEvent(paramKeyEvent); ; i = 1)
      return bool;
  }

  public int getCurrentTab()
  {
    return this.mCurrentTab;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 100) || (paramInt1 == 200))
      if ((this.mAsset != null) && (paramInt2 == -1))
      {
        String str1 = this.mAsset.pkgName;
        int i = this.mAsset.versionCode;
        int j = this.mAssetId;
        int k = PackageInstallInfo.getPackageInstalledStatus(this, str1, i, j);
        this.mAssetInstalled = k;
        setupButtonStatus();
      }
    while (true)
    {
      return;
      if ((paramInt1 == 400) && (paramInt2 == -1))
      {
        LocalActivityManager localLocalActivityManager = getLocalActivityManager();
        localLocalActivityManager.destroyActivity("comment", 1);
        Intent localIntent = new Intent();
        String str2 = CommentsActivity.class.getName();
        localIntent.setClassName(this, str2);
        int m = this.mAssetId;
        localIntent.putExtra("_id", m);
        localLocalActivityManager.startActivity("comment", localIntent);
        continue;
      }
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131427356:
    case 2131427357:
    case 2131427362:
    default:
    case 2131427358:
    case 2131427354:
    case 2131427359:
    case 2131427355:
    case 2131427364:
    case 2131427360:
    case 2131427353:
    case 2131427361:
    case 2131427363:
    }
    while (true)
    {
      return;
      int i = this.mAssetId;
      if (checkAppDownloading(i))
      {
        Toast.makeText(this, 2131296351, 1).show();
        continue;
      }
      startBuy();
      continue;
      this.mCancel = 0;
      int j = this.mAssetId;
      if (checkAppDownloading(j))
      {
        Toast.makeText(this, 2131296351, 1).show();
        continue;
      }
      if (getIntent().getFloatExtra("price", 0.0F) > 0.0F)
      {
        startBuy();
        continue;
      }
      Asset localAsset1 = this.mAsset;
      File localFile = FileManager.getFile(this, localAsset1);
      if (localFile == null)
      {
        if (GlobalUtil.isSystemApp(getPackageManager()))
        {
          int k = this.mAsset._id;
          String str1 = this.mAsset.title;
          int m = this.mAsset.size;
          String str2 = this.mAsset.pkgName;
          String[] arrayOfString1 = this.mAsset.permissions;
          GlobalUtil.startPermissionsActivity(this, k, str1, m, str2, arrayOfString1);
          continue;
        }
        addDownloadAndInstallRequest();
        continue;
      }
      int n = this.mAsset._id;
      String str3 = this.mAsset.title;
      String str4 = this.mAsset.pkgName;
      startInstall(localFile, n, str3, str4);
      continue;
      this.mCancel = 0;
      int i1 = this.mAssetId;
      if (checkAppDownloading(i1))
      {
        Toast.makeText(this, 2131296351, 1).show();
        continue;
      }
      Asset localAsset2 = this.mAsset;
      localFile = FileManager.getFile(this, localAsset2);
      if ((localFile == null) && (this.mAsset != null))
      {
        if (GlobalUtil.isSystemApp(getPackageManager()))
        {
          int i2 = this.mAsset._id;
          String str5 = this.mAsset.title;
          int i3 = this.mAsset.size;
          String str6 = this.mAsset.pkgName;
          String[] arrayOfString2 = this.mAsset.permissions;
          GlobalUtil.startPermissionsActivity(this, i2, str5, i3, str6, arrayOfString2);
          continue;
        }
        addDownloadAndInstallRequest();
        continue;
      }
      int i4 = this.mAsset._id;
      String str7 = this.mAsset.title;
      String str8 = this.mAsset.pkgName;
      startInstall(localFile, i4, str7, str8);
      continue;
      Intent localIntent1 = new Intent();
      int i5 = this.mAssetId;
      localIntent1.putExtra("_id", i5);
      int i6 = getIntent().getIntExtra("position", 0);
      localIntent1.putExtra("position", i6);
      localIntent1.putExtra("select", 1);
      setResult(-1, localIntent1);
      finish();
      continue;
      Intent localIntent2 = new Intent();
      int i7 = this.mAssetId;
      localIntent2.putExtra("_id", i7);
      int i8 = getIntent().getIntExtra("position", 0);
      localIntent2.putExtra("position", i8);
      localIntent2.putExtra("select", 0);
      setResult(-1, localIntent2);
      finish();
      continue;
      startUninstallProgress();
      continue;
      try
      {
        PackageManager localPackageManager1 = getPackageManager();
        String str9 = this.mPackageName;
        Intent localIntent3 = localPackageManager1.getLaunchIntentForPackage(str9);
        if (localIntent3 == null)
          break label716;
        try
        {
          startActivity(localIntent3);
        }
        catch (Exception localException)
        {
          String str10 = this.mPackageName;
          PackageManager localPackageManager2 = getPackageManager();
          Intent localIntent4 = getIntent(str10, localPackageManager2);
          startActivity(localIntent4);
        }
      }
      catch (Throwable localThrowable)
      {
      }
      continue;
      label716: this.mLaunchButton.setEnabled(0);
      this.mLaunchButton.setTextColor(-7829368);
      continue;
      this.mCancel = 1;
      DownloadService localDownloadService1 = DownloadService.getInstance(this);
      int i9 = this.mAssetId;
      localDownloadService1.cancelTask(i9);
      setButtonStatus(0);
      continue;
      this.mCancel = 0;
      DownloadService localDownloadService2 = DownloadService.getInstance(this);
      int i10 = this.mAssetId;
      String str11 = localDownloadService2.getCachedPath(i10);
      if (str11 == null)
        continue;
      Intent localIntent5 = new Intent();
      localIntent5.setAction("android.intent.action.VIEW");
      localIntent5.addCategory("android.intent.category.DEFAULT");
      Uri localUri = Uri.fromFile(new File(str11));
      localIntent5.setDataAndType(localUri, "application/vnd.android.package-archive");
      startActivity(localIntent5);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    setTheme(16973834);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    Intent localIntent = getIntent();
    Asset localAsset1 = new Asset();
    this.mAsset = localAsset1;
    Asset localAsset2 = this.mAsset;
    String str1 = localIntent.getStringExtra("title");
    localAsset2.title = str1;
    Asset localAsset3 = this.mAsset;
    int i = localIntent.getIntExtra("installed", -1);
    localAsset3.installed = i;
    Asset localAsset4 = this.mAsset;
    String str2 = localIntent.getStringExtra("pkgName");
    localAsset4.pkgName = str2;
    Asset localAsset5 = this.mAsset;
    int j = localIntent.getIntExtra("size", -1);
    localAsset5.size = j;
    Asset localAsset6 = this.mAsset;
    int k = localIntent.getIntExtra("versionCode", -1);
    localAsset6.versionCode = k;
    int m = localIntent.getIntExtra("_id", 0);
    this.mAssetId = m;
    String str3 = localIntent.getStringExtra("title");
    this.mAssetTitle = str3;
    String str4 = localIntent.getStringExtra("pkgName");
    this.mPackageName = str4;
    int n = localIntent.getIntExtra("installed", -1);
    this.mAssetInstalled = n;
    String str5 = localIntent.getStringExtra("from");
    this.mFromPage = str5;
    boolean bool = localIntent.getBooleanExtra("preinstall", 0);
    this.mPreinstallReview = bool;
    setContentView(2130903044);
    initHandlerInUIThread();
    Handler localHandler = WifiDownloadManager.initHandlerInUIThread(this);
    this.mWifiHandler = localHandler;
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabWidget localTabWidget = (TabWidget)this.mTabHost.getChildAt(1);
    this.tw = localTabWidget;
    setupViews();
    registerIntentReceivers();
    this.mDestroyed = 0;
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder2;
    AssetInfoActivity.3 local3;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local3).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      AssetInfoActivity.2 local2 = new AssetInfoActivity.2(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local2);
      local3 = new AssetInfoActivity.3(this);
    }
  }

  public void onDestroy()
  {
    onDestroy();
    unregisterIntentReceivers();
  }

  public void onPause()
  {
    if (isFinishing())
    {
      this.mDestroyed = 1;
      DownloadService localDownloadService = DownloadService.getInstance(this);
      int i = this.mAssetId;
      localDownloadService.removeHandler(i);
    }
    onPause();
    MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    if (this.mPackageName != null)
    {
      String str = this.mPackageName;
      int i = this.mAsset.versionCode;
      int j = this.mAssetId;
      int k = PackageInstallInfo.getPackageInstalledStatus(this, str, i, j);
      this.mAssetInstalled = k;
      setupButtonStatus();
      if (this.mBottomButton != null)
      {
        Asset localAsset = this.mAsset;
        if (FileManager.getFile(this, localAsset) != null)
          break label89;
        this.mBottomButton.setText(2131296361);
      }
    }
    while (true)
    {
      addPageViewLogRequest();
      onResume();
      MobclickAgent.onResume(this);
      return;
      label89: this.mBottomButton.setText(2131296418);
    }
  }

  public void onTabChanged(String paramString)
  {
    if ("detail".equals(paramString))
    {
      this.mCurrentTab = 0;
      if (DeviceUtil.getSDKVersionInt() != 3)
        break label72;
      int i = this.mCurrentTab;
      setCurrentTabLow(i);
      label31: if (!"comment".equals(paramString))
        break label85;
      setButtonStatus(7);
    }
    while (true)
    {
      return;
      if ("comment".equals(paramString))
      {
        this.mCurrentTab = 1;
        break;
      }
      this.mCurrentTab = 2;
      break;
      label72: int j = this.mCurrentTab;
      setCurrentTab(j);
      break label31;
      label85: int k = this.mInfoButtonStatus;
      setButtonStatus(k);
    }
  }

  public void setCommentCount(int paramInt)
  {
    if (DeviceUtil.getSDKVersionInt() == 3)
    {
      TextView localTextView1 = (TextView)((RelativeLayout)this.tw.getChildAt(1)).getChildAt(1);
      if (localTextView1 != null)
      {
        String str1 = String.valueOf(getString(2131296325));
        String str2 = str1 + "(" + paramInt + ")";
        localTextView1.setText(str2);
      }
    }
    while (true)
    {
      return;
      TextView localTextView2 = (TextView)this.tabViews.get(1);
      String str3 = String.valueOf(getString(2131296325));
      String str4 = str3 + "(" + paramInt + ")";
      localTextView2.setText(str4);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoActivity
 * JD-Core Version:    0.6.0
 */