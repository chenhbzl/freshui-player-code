package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@Signature({"Landroid/app/Activity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class UpdateAppListActivity extends Activity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 2;
  private static final int ACTION_LIST_APPS = 1;
  private static final int ACTION_LOGIN = 0;
  private static final int ACTION_NETWORK_ERROR = 3;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final String TAG = UpdateAppListActivity.class.getSimpleName();
  private static View mFooterView;
  static Method mdGetPackageSizeInfo;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  private FilteredAppListAdapter mAppListAdapter;
  private final BroadcastReceiver mApplicationsReceiver;
  private boolean mBusy;
  private Request mCurrentRequest;
  private Handler mDownloadStatusHandler;
  private Handler mHandler;
  private String[] mInstalledPackages;
  private ListView mListView;
  private IMarketService mMarketService;
  private View mNetworkError;
  private PackageManager mPackageManager;
  private View mProgressIndicator;
  private boolean mReachEnd;
  private Request mTopDetailRequest;
  private Request mTopRequest;
  private int oldStartIndex;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;

  static
  {
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = IPackageStatsObserver.class;
      mdGetPackageSizeInfo = PackageManager.class.getMethod("getPackageSizeInfo", arrayOfClass);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str1 = TAG;
        String str2 = localException.getLocalizedMessage();
        Log.e(str1, str2, localException);
      }
    }
  }

  public UpdateAppListActivity()
  {
    UpdateAppListActivity.1 local1 = new UpdateAppListActivity.1(this);
    this.mApplicationsReceiver = local1;
    this.mBusy = 0;
    UpdateAppListActivity.2 local2 = new UpdateAppListActivity.2(this);
    this.scrollListener = local2;
    Hashtable localHashtable = new Hashtable();
    this.iconStatusMap = localHashtable;
    this.startIndex = 0;
    this.oldStartIndex = -1;
    this.mReachEnd = 0;
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "Update";
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    UpdateAppListActivity.5 local5 = new UpdateAppListActivity.5(this, localRequest);
    localRequest.addObserver(local5);
    this.mMarketService.getAppIcon(localRequest);
  }

  private void clearPendingThumbRequest()
  {
    Iterator localIterator = this.iconStatusMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        MarketService localMarketService = (MarketService)this.mMarketService;
        int i = MarketService.THREAD_THUMB;
        localMarketService.clearPendingRequest(i);
        return;
      }
      Integer localInteger = (Integer)localIterator.next();
      if (((Boolean)this.iconStatusMap.get(localInteger)).booleanValue())
        continue;
      localIterator.remove();
    }
  }

  private String[] getInstalledAppPackages()
  {
    List localList = getPackageManager().getInstalledApplications(0);
    ArrayList localArrayList;
    int i;
    int j;
    if (localList != null)
    {
      localArrayList = new ArrayList();
      i = 0;
      j = localList.size();
    }
    while (true)
    {
      if (i >= j)
      {
        String[] arrayOfString1 = new String[localArrayList.size()];
        this.mInstalledPackages = arrayOfString1;
        String[] arrayOfString2 = this.mInstalledPackages;
        localArrayList.toArray(arrayOfString2);
        return this.mInstalledPackages;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      String str1 = localApplicationInfo.sourceDir;
      if (!GlobalUtil.isSystemApp(getPackageManager()));
      try
      {
        PackageManager localPackageManager = getPackageManager();
        String str2 = localApplicationInfo.packageName;
        int k = localPackageManager.getPackageInfo(str2, 8192).versionCode;
        label127: String str3 = String.valueOf(localApplicationInfo.packageName);
        StringBuilder localStringBuilder = new StringBuilder(str3);
        if (k == -1);
        for (String str4 = ""; ; str4 = ";" + k)
        {
          String str5 = str4;
          localArrayList.add(str5);
          i += 1;
          break;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break label127;
      }
    }
  }

  private void inflateAppList(boolean paramBoolean)
  {
    if (!paramBoolean)
      if (!this.mReachEnd)
      {
        int i = this.oldStartIndex;
        int j = this.startIndex;
        if (i != j)
          break label27;
      }
    while (true)
    {
      return;
      label27: Request localRequest = new Request(0L, 65544);
      String[] arrayOfString = getInstalledAppPackages();
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = arrayOfString;
      Integer localInteger1 = Integer.valueOf(9);
      arrayOfObject[1] = localInteger1;
      Integer localInteger2 = Integer.valueOf(this.startIndex);
      arrayOfObject[2] = localInteger2;
      Integer localInteger3 = Integer.valueOf(10);
      arrayOfObject[3] = localInteger3;
      localRequest.setData(arrayOfObject);
      UpdateAppListActivity.7 local7 = new UpdateAppListActivity.7(this);
      localRequest.addObserver(local7);
      this.mCurrentRequest = localRequest;
      int k = this.startIndex;
      this.oldStartIndex = k;
      this.mMarketService.getAppList(localRequest);
    }
  }

  private void initHandlerInUIThread()
  {
    UpdateAppListActivity.6 local6 = new UpdateAppListActivity.6(this);
    this.mHandler = local6;
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver, localIntentFilter);
  }

  private void setupListViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
    View localView2 = findViewById(2131427408);
    this.mNetworkError = localView2;
    ListView localListView1 = (ListView)findViewById(16908298);
    this.mListView = localListView1;
    if (DeviceUtil.getSDKVersionInt() > 4);
    try
    {
      Class localClass1 = this.mListView.getClass();
      Class[] arrayOfClass = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass[0] = localClass2;
      Method localMethod = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass);
      ListView localListView2 = this.mListView;
      Object[] arrayOfObject = new Object[1];
      Boolean localBoolean = Boolean.valueOf(1);
      arrayOfObject[0] = localBoolean;
      localMethod.invoke(localListView2, arrayOfObject);
      mFooterView = LayoutInflater.from(this).inflate(2130903056, null);
      Button localButton = (Button)mFooterView.findViewById(2131427414);
      UpdateAppListActivity.3 local3 = new UpdateAppListActivity.3(this);
      localButton.setOnClickListener(local3);
      ListView localListView3 = this.mListView;
      View localView3 = mFooterView;
      localListView3.addFooterView(localView3);
      ListView localListView4 = this.mListView;
      View localView4 = findViewById(2131427407);
      localListView4.setEmptyView(localView4);
      this.mListView.setOnItemClickListener(this);
      ListView localListView5 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView5.setOnScrollListener(localOnScrollListener);
      this.mProgressIndicator.setVisibility(0);
      this.mNetworkError.setVisibility(8);
      this.mListView.setVisibility(8);
      inflateAppList(0);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void setupMDownloadStatusHandler()
  {
    UpdateAppListActivity.4 local4 = new UpdateAppListActivity.4(this);
    this.mDownloadStatusHandler = local4;
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
  }

  public Handler getDownloadStatusHandler()
  {
    return this.mDownloadStatusHandler;
  }

  public Drawable getThumbnail(int paramInt1, int paramInt2)
  {
    Asset localAsset = (Asset)this.mAppListAdapter.getItem(paramInt1);
    Object localObject;
    if ((localAsset != null) && (!localAsset.exist))
    {
      PackageManager localPackageManager1 = this.mPackageManager;
      String str = localAsset.pkgName;
      PackageInfo localPackageInfo = GlobalUtil.getPackageInfo(localPackageManager1, str);
      if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null))
      {
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        PackageManager localPackageManager2 = this.mPackageManager;
        localObject = localApplicationInfo.loadIcon(localPackageManager2);
      }
    }
    while (true)
    {
      return localObject;
      localObject = CachedThumbnails.getDefaultIcon(this);
      continue;
      if (this.mBusy)
      {
        Hashtable localHashtable1 = this.iconStatusMap;
        Integer localInteger1 = Integer.valueOf(paramInt1);
        if (!localHashtable1.containsKey(localInteger1))
        {
          localObject = CachedThumbnails.getDefaultIcon(this);
          continue;
        }
      }
      Drawable localDrawable = CachedThumbnails.lookupThumbnail(this, paramInt2);
      if (localDrawable != null)
      {
        Hashtable localHashtable2 = this.iconStatusMap;
        Integer localInteger2 = Integer.valueOf(paramInt1);
        Boolean localBoolean1 = Boolean.valueOf(1);
        localHashtable2.put(localInteger2, localBoolean1);
        localObject = localDrawable;
        continue;
      }
      Hashtable localHashtable3 = this.iconStatusMap;
      Integer localInteger3 = Integer.valueOf(paramInt1);
      if (localHashtable3.containsKey(localInteger3))
      {
        Hashtable localHashtable4 = this.iconStatusMap;
        Integer localInteger4 = Integer.valueOf(paramInt1);
        if (!((Boolean)localHashtable4.get(localInteger4)).booleanValue());
      }
      else
      {
        Hashtable localHashtable5 = this.iconStatusMap;
        Integer localInteger5 = Integer.valueOf(paramInt1);
        Boolean localBoolean2 = Boolean.valueOf(0);
        localHashtable5.put(localInteger5, localBoolean2);
        addThumbnailRequest(paramInt1, paramInt2);
      }
      localObject = CachedThumbnails.getDefaultIcon(this);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    setContentView(2130903119);
    PackageManager localPackageManager = getPackageManager();
    this.mPackageManager = localPackageManager;
    setupListViews();
    initHandlerInUIThread();
    setupMDownloadStatusHandler();
    registerIntentReceivers();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder2;
    UpdateAppListActivity.9 local9;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local9).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      UpdateAppListActivity.8 local8 = new UpdateAppListActivity.8(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local8);
      local9 = new UpdateAppListActivity.9(this);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = onCreateOptionsMenu(paramMenu);
    if (bool)
      OptionsMenu.onCreateOptionsMenu(paramMenu);
    return bool;
  }

  public void onDestroy()
  {
    onDestroy();
    unregisterIntentReceivers();
  }

  @Signature({"(", "Landroid/widget/AdapterView", "<*>;", "Landroid/view/View;", "IJ)V"})
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent();
    String str1 = AssetInfoActivity.class.getName();
    localIntent.setClassName(this, str1);
    Asset localAsset = (Asset)paramAdapterView.getItemAtPosition(paramInt);
    if (localAsset == null);
    while (true)
    {
      return;
      if (localAsset.exist)
      {
        int i = localAsset._id;
        localIntent.putExtra("_id", i);
        String str2 = localAsset.title;
        localIntent.putExtra("title", str2);
        int j = localAsset.installed;
        localIntent.putExtra("installed", j);
        String str3 = localAsset.pkgName;
        localIntent.putExtra("pkgName", str3);
        int k = localAsset.size;
        localIntent.putExtra("size", k);
        int m = localAsset.versionCode;
        localIntent.putExtra("versionCode", m);
        localIntent.putExtra("from", "Update");
        startActivity(localIntent);
        continue;
      }
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }

  public void onResume()
  {
    onResume();
    if (this.mAppListAdapter != null)
      this.mAppListAdapter.notifyDataSetChanged();
    addPageViewLogRequest();
  }

  final class PkgSizeObserver extends IPackageStatsObserver.Stub
  {
    private Activity ac;
    private Asset asset;

    PkgSizeObserver(Activity paramAsset, Asset arg3)
    {
      this.ac = paramAsset;
      Object localObject;
      this.asset = localObject;
    }

    void invokeGetPkgSize(String paramString, PackageManager paramPackageManager)
    {
      if (UpdateAppListActivity.mdGetPackageSizeInfo != null);
      try
      {
        Method localMethod = UpdateAppListActivity.mdGetPackageSizeInfo;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = this;
        localMethod.invoke(paramPackageManager, arrayOfObject);
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          String str1 = UpdateAppListActivity.TAG;
          String str2 = localException.getLocalizedMessage();
          Log.e(str1, str2, localException);
        }
      }
    }

    public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
      throws RemoteException
    {
      if (this.asset != null);
      synchronized (this.asset)
      {
        Asset localAsset2 = this.asset;
        int i = (int)paramPackageStats.codeSize;
        localAsset2.size = i;
        UpdateAppListActivity.this.countDown();
        return;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.UpdateAppListActivity
 * JD-Core Version:    0.6.0
 */