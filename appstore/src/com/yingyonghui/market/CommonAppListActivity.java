package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.DataCacheService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.provider.MarketStore.Searchs;
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

@Signature({"Landroid/app/Activity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class CommonAppListActivity extends Activity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 2;
  private static final int ACTION_LIST_APPS = 1;
  private static final int ACTION_LOGIN = 0;
  private static final int ACTION_NETWORK_ERROR = 3;
  private static final int ACTION_TOP_FOUR_APP = 4;
  private static final int ACTION_TOP_FOUR_APP_DETAIL = 5;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final String[] SEARCH_PROJECTION;
  private static ImageView imageViewProgress;
  private static Context mContext;
  private static View mFooterView;
  private static View mHeaderView = null;
  private static Button topButton1;
  private static Button topButton2;
  private static Button topButton3;
  private static Button topButton4;
  private static WindowManager windowManager;
  private boolean appListFlag = 0;
  private int category_id;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  boolean inflatingAppList;
  private AnimationDrawable mAd;
  private FilteredAppListAdapter mAppListAdapter;
  private final BroadcastReceiver mApplicationsReceiver;
  private boolean mBusy;
  private boolean mContainsHeader = 0;
  private Request mCurrentRequest;
  private DataCacheService mDataCacheService;
  private Handler mDownloadStatusHandler;
  private Handler mHandler;
  private Request mIconRequest;
  private String[] mInstalledPackages;
  private ListView mListView;
  private IMarketService mMarketService;
  boolean mNewAppList;
  private View mProgressIndicator;
  private int mRankingType = 0;
  private boolean mReachEnd;
  private Request mTopDetailRequest;
  private Request mTopRequest;
  private int oldStartIndex;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;
  private Asset[] topApp;
  private View.OnClickListener topAppClickListener;
  private boolean topAppFlag = 0;

  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "date";
    arrayOfString[2] = "keyword";
    SEARCH_PROJECTION = arrayOfString;
  }

  public CommonAppListActivity()
  {
    Asset[] arrayOfAsset = new Asset[4];
    this.topApp = arrayOfAsset;
    CommonAppListActivity.1 local1 = new CommonAppListActivity.1(this);
    this.mApplicationsReceiver = local1;
    CommonAppListActivity.2 local2 = new CommonAppListActivity.2(this);
    this.topAppClickListener = local2;
    this.mBusy = 0;
    CommonAppListActivity.3 local3 = new CommonAppListActivity.3(this);
    this.scrollListener = local3;
    Hashtable localHashtable = new Hashtable();
    this.iconStatusMap = localHashtable;
    this.startIndex = 0;
    this.oldStartIndex = -1;
    this.mReachEnd = 0;
    this.inflatingAppList = 0;
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "New";
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    CommonAppListActivity.7 local7 = new CommonAppListActivity.7(this, localRequest);
    localRequest.addObserver(local7);
    this.mIconRequest = localRequest;
    this.mMarketService.getCategory(localRequest);
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
    String str1 = null;
    Object localObject;
    if (this.mInstalledPackages != null)
    {
      localObject = this.mInstalledPackages;
      return localObject;
    }
    List localList = getPackageManager().getInstalledApplications(8192);
    ArrayList localArrayList;
    int i;
    int j;
    if (localList != null)
    {
      localArrayList = new ArrayList();
      i = localList.size();
      j = 0;
    }
    while (true)
    {
      if (j >= i)
      {
        String[] arrayOfString1 = new String[localArrayList.size()];
        this.mInstalledPackages = arrayOfString1;
        String[] arrayOfString2 = this.mInstalledPackages;
        localArrayList.toArray(arrayOfString2);
        localObject = this.mInstalledPackages;
        break;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(j);
      String str2 = localApplicationInfo.sourceDir;
      if (!GlobalUtil.isSystemApp(getPackageManager()));
      try
      {
        PackageManager localPackageManager = getPackageManager();
        String str3 = localApplicationInfo.packageName;
        int k = localPackageManager.getPackageInfo(str3, 8192).versionCode;
        label154: String str4 = String.valueOf(localApplicationInfo.packageName);
        localObject = new StringBuilder(str4);
        if (k == -1);
        for (str1 = ""; ; str1 = ";" + k)
        {
          String str5 = str1;
          localArrayList.add(str5);
          j += 1;
          break;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        break label154;
      }
    }
  }

  private void inflateAppList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      if (!this.inflatingAppList)
      {
        this.inflatingAppList = 1;
        Request localRequest;
        if (this.mNewAppList)
        {
          localRequest = new Request(0L, 65538);
          Object[] arrayOfObject1 = new Object[4];
          Integer localInteger1 = Integer.valueOf(this.category_id);
          arrayOfObject1[0] = localInteger1;
          Integer localInteger2 = Integer.valueOf(this.startIndex);
          arrayOfObject1[1] = localInteger2;
          Integer localInteger3 = Integer.valueOf(10);
          arrayOfObject1[2] = localInteger3;
          Integer localInteger4 = Integer.valueOf(this.mRankingType);
          arrayOfObject1[3] = localInteger4;
          localRequest.setData(arrayOfObject1);
        }
        while (true)
        {
          CommonAppListActivity.9 local9 = new CommonAppListActivity.9(this, localRequest);
          localRequest.addObserver(local9);
          this.mCurrentRequest = localRequest;
          int i = this.startIndex;
          this.oldStartIndex = i;
          if ((!this.mNewAppList) || (this.startIndex != 0))
            break label245;
          this.mDataCacheService.getAppList(localRequest);
          break;
          localRequest = new Request(0L, 65544);
          String[] arrayOfString = getInstalledAppPackages();
          Object[] arrayOfObject2 = new Object[4];
          arrayOfObject2[0] = arrayOfString;
          Integer localInteger5 = Integer.valueOf(9);
          arrayOfObject2[1] = localInteger5;
          Integer localInteger6 = Integer.valueOf(this.startIndex);
          arrayOfObject2[2] = localInteger6;
          Integer localInteger7 = Integer.valueOf(10);
          arrayOfObject2[3] = localInteger7;
          localRequest.setData(arrayOfObject2);
        }
        label245: this.mMarketService.getAppList(localRequest);
        continue;
      }
    }
  }

  private void initHandlerInUIThread()
  {
    CommonAppListActivity.8 local8 = new CommonAppListActivity.8(this);
    this.mHandler = local8;
  }

  private void initTopFourApp()
  {
    Request localRequest1 = new Request(0L, 65549);
    CommonAppListActivity.10 local10 = new CommonAppListActivity.10(this, localRequest1);
    localRequest1.addObserver(local10);
    this.mTopRequest = localRequest1;
    this.mDataCacheService.getTopFourApp(localRequest1);
    Request localRequest2 = new Request(0L, 65550);
    Object[] arrayOfObject = new Object[4];
    Integer localInteger1 = Integer.valueOf(91);
    arrayOfObject[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(0);
    arrayOfObject[1] = localInteger2;
    Integer localInteger3 = Integer.valueOf(10);
    arrayOfObject[2] = localInteger3;
    Integer localInteger4 = Integer.valueOf(0);
    arrayOfObject[3] = localInteger4;
    localRequest2.setData(arrayOfObject);
    CommonAppListActivity.11 local11 = new CommonAppListActivity.11(this, localRequest1);
    localRequest2.addObserver(local11);
    this.mTopDetailRequest = localRequest2;
    this.mDataCacheService.getAppDetail(localRequest2);
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

  public static void resetTopFourApp()
  {
    int i = windowManager.getDefaultDisplay().getWidth();
    int j = i * 9 / 16;
    if (mHeaderView != null)
    {
      View localView = mHeaderView;
      AbsListView.LayoutParams localLayoutParams = new AbsListView.LayoutParams(i, j);
      localView.setLayoutParams(localLayoutParams);
      int k = i / 2;
      int m = j / 2;
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(k, m);
      localLayoutParams1.addRule(9, -1);
      localLayoutParams1.addRule(10, -1);
      topButton1.setLayoutParams(localLayoutParams1);
      int n = i / 2;
      int i1 = j / 2;
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(n, i1);
      localLayoutParams2.addRule(11, -1);
      localLayoutParams2.addRule(10, -1);
      localLayoutParams2.addRule(1, 2131427535);
      topButton2.setLayoutParams(localLayoutParams2);
      int i2 = i / 2;
      int i3 = j / 2;
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(i2, i3);
      localLayoutParams3.addRule(9, -1);
      localLayoutParams3.addRule(12, -1);
      localLayoutParams3.addRule(3, 2131427535);
      topButton3.setLayoutParams(localLayoutParams3);
      int i4 = i / 2;
      int i5 = j / 2;
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(i4, i5);
      localLayoutParams4.addRule(11, -1);
      localLayoutParams4.addRule(12, -1);
      localLayoutParams4.addRule(1, 2131427537);
      topButton4.setLayoutParams(localLayoutParams4);
    }
  }

  private void setupListViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
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
      CommonAppListActivity.4 local4 = new CommonAppListActivity.4(this);
      localButton.setOnClickListener(local4);
      ListView localListView3 = this.mListView;
      View localView2 = mFooterView;
      localListView3.addFooterView(localView2);
      ListView localListView4 = this.mListView;
      View localView3 = findViewById(2131427408);
      localListView4.setEmptyView(localView3);
      TextView localTextView = (TextView)findViewById(2131427409);
      CommonAppListActivity.5 local5 = new CommonAppListActivity.5(this);
      localTextView.setOnClickListener(local5);
      this.mListView.setOnItemClickListener(this);
      ListView localListView5 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView5.setOnScrollListener(localOnScrollListener);
      if (this.mNewAppList)
        initTopFourApp();
      inflateAppList();
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
    CommonAppListActivity.6 local6 = new CommonAppListActivity.6(this);
    this.mDownloadStatusHandler = local6;
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
    Object localObject;
    if (this.mBusy)
    {
      Hashtable localHashtable1 = this.iconStatusMap;
      Integer localInteger1 = Integer.valueOf(paramInt1);
      if (!localHashtable1.containsKey(localInteger1))
        localObject = CachedThumbnails.getDefaultIcon(this);
    }
    while (true)
    {
      return localObject;
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
    mContext = this;
    windowManager = getWindowManager();
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    DataCacheService localDataCacheService = DataCacheService.getServiceInstance(this);
    this.mDataCacheService = localDataCacheService;
    label76: Bundle localBundle;
    if ((paramBundle != null) && (paramBundle.containsKey("new")))
    {
      boolean bool1 = paramBundle.getBoolean("new");
      this.mNewAppList = bool1;
      if (!this.mNewAppList)
        break label179;
      int i = -1;
      this.category_id = i;
      localBundle = getIntent().getExtras();
      if (localBundle == null)
        break label186;
    }
    label179: int j;
    label186: for (boolean bool2 = localBundle.getBoolean("header"); ; j = 0)
    {
      this.mContainsHeader = bool2;
      boolean bool3 = getIntent().getBooleanExtra("topAppFlag", 0);
      this.topAppFlag = bool3;
      setContentView(2130903067);
      initHandlerInUIThread();
      setupMDownloadStatusHandler();
      setupListViews();
      registerIntentReceivers();
      return;
      boolean bool4 = getIntent().getBooleanExtra("new", 0);
      this.mNewAppList = bool4;
      break;
      j = -1;
      break label76;
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder2;
    CommonAppListActivity.13 local13;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local13).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      CommonAppListActivity.12 local12 = new CommonAppListActivity.12(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local12);
      local13 = new CommonAppListActivity.13(this);
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
    Drawable localDrawable;
    if (mHeaderView != null)
    {
      localDrawable = mHeaderView.getBackground();
      if ((localDrawable == null) || (!(localDrawable instanceof BitmapDrawable)));
    }
    try
    {
      ((BitmapDrawable)localDrawable).getBitmap().recycle();
      label34: onDestroy();
      unregisterIntentReceivers();
      return;
    }
    catch (Throwable localThrowable)
    {
      break label34;
    }
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
      localIntent.putExtra("from", "New");
      startActivity(localIntent);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }

  public void onPause()
  {
    onPause();
    if (mHeaderView != null)
    {
      Drawable localDrawable = mHeaderView.getBackground();
      if (localDrawable != null)
        localDrawable.setCallback(null);
    }
  }

  public void onResume()
  {
    if (this.mContainsHeader)
    {
      resetTopFourApp();
      addPageViewLogRequest();
    }
    onResume();
    if (this.mAppListAdapter != null)
      this.mAppListAdapter.notifyDataSetChanged();
  }

  public boolean whetherNew()
  {
    return this.mNewAppList;
  }

  public class SearchAdapter extends CursorAdapter
    implements Filterable
  {
    private ContentResolver mContent;

    public SearchAdapter(Cursor arg2)
    {
      super(localCursor);
      ContentResolver localContentResolver = this$1.getContentResolver();
      this.mContent = localContentResolver;
    }

    public void bindView(View paramView, Context paramContext, Cursor paramCursor)
    {
      TextView localTextView = (TextView)paramView;
      String str = paramCursor.getString(2);
      localTextView.setText(str);
    }

    public String convertToString(Cursor paramCursor)
    {
      return paramCursor.getString(2);
    }

    public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
    {
      TextView localTextView = (TextView)LayoutInflater.from(paramContext).inflate(17367050, paramViewGroup, 0);
      String str = paramCursor.getString(2);
      localTextView.setText(str);
      return localTextView;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
    {
      if (getFilterQueryProvider() != null)
      {
        localObject = getFilterQueryProvider().runQuery(paramCharSequence);
        return localObject;
      }
      StringBuilder localStringBuilder = null;
      String[] arrayOfString1 = (String[])0;
      if (paramCharSequence != null)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("UPPER(");
        localStringBuilder.append("keyword");
        localStringBuilder.append(") GLOB ?");
        arrayOfString1 = new String[1];
        String str1 = String.valueOf(paramCharSequence.toString().toUpperCase());
        String str2 = str1 + "*";
        arrayOfString1[0] = str2;
      }
      Object localObject = this.mContent;
      Uri localUri = MarketStore.Searchs.CONTENT_URI;
      String[] arrayOfString2 = CommonAppListActivity.SEARCH_PROJECTION;
      if (localStringBuilder == null);
      for (String str3 = null; ; str3 = localStringBuilder.toString())
      {
        localObject = ((ContentResolver)localObject).query(localUri, arrayOfString2, str3, arrayOfString1, "date DESC");
        break;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommonAppListActivity
 * JD-Core Version:    0.6.0
 */