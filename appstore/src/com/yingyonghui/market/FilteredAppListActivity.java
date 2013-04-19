package com.yingyonghui.market;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.provider.MarketStore.Searchs;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

@Signature({"Landroid/app/ListActivity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class FilteredAppListActivity extends ListActivity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 1;
  private static final int ACTION_LIST_APPS = 0;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final String[] SEARCH_PROJECTION;
  private int category_id;
  private String category_label;
  boolean doingSearchQuery;
  private View headerView;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  boolean inflatingAppLikeList;
  boolean inflatingAppList;
  boolean inflatingAppRankingList;
  boolean inflatingAppRelativeList;
  private FilteredAppListAdapter mAppListAdapter;
  private final BroadcastReceiver mApplicationsReceiver;
  private boolean mBusy;
  private boolean mContainsHeader = 1;
  private Request mCurrentRequest;
  private Handler mDownloadStatusHandler;
  private View mEmptyView;
  private View mFooterView;
  private Handler mHandler;
  private int mItemType;
  private String mKeywords = "";
  private ListView mListView;
  private IMarketService mMarketService;
  private String mPackageName;
  private View mProgressIndicator;
  private int mRankingType;
  private boolean mReachEnd;
  private View.OnClickListener mSearchBarClickListener;
  private int mSearchPage = 2;
  private AutoCompleteTextView mSearchText;
  private int mSearchType = 3;
  private String mTitle = "";
  private int oldStartIndex;
  private int periodType;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;

  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "date";
    arrayOfString[2] = "keyword";
    SEARCH_PROJECTION = arrayOfString;
  }

  public FilteredAppListActivity()
  {
    FilteredAppListActivity.1 local1 = new FilteredAppListActivity.1(this);
    this.mApplicationsReceiver = local1;
    FilteredAppListActivity.2 local2 = new FilteredAppListActivity.2(this);
    this.scrollListener = local2;
    Hashtable localHashtable = new Hashtable();
    this.iconStatusMap = localHashtable;
    this.startIndex = 0;
    this.oldStartIndex = -1;
    this.mReachEnd = 0;
    this.inflatingAppList = 0;
    this.inflatingAppRankingList = 0;
    this.inflatingAppLikeList = 0;
    this.inflatingAppRelativeList = 0;
    this.doingSearchQuery = 0;
    FilteredAppListActivity.3 local3 = new FilteredAppListActivity.3(this);
    this.mSearchBarClickListener = local3;
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    String str1 = getPage();
    if (str1 == "Category")
    {
      Object[] arrayOfObject1 = new Object[6];
      arrayOfObject1[0] = str1;
      Integer localInteger1 = Integer.valueOf(getCategory());
      arrayOfObject1[1] = localInteger1;
      Integer localInteger2 = Integer.valueOf(getRankingType());
      arrayOfObject1[2] = localInteger2;
      Integer localInteger3 = Integer.valueOf(55537);
      arrayOfObject1[3] = localInteger3;
      Integer localInteger4 = Integer.valueOf(55537);
      arrayOfObject1[4] = localInteger4;
      String str2 = getCategoryLabel();
      arrayOfObject1[5] = str2;
      localRequest.setData(arrayOfObject1);
    }
    while (true)
    {
      MarketService.getServiceInstance(this).getPageviewLog(localRequest);
      return;
      if (str1 == "Ranking")
      {
        Object[] arrayOfObject2 = new Object[5];
        arrayOfObject2[0] = str1;
        Integer localInteger5 = Integer.valueOf(55537);
        arrayOfObject2[1] = localInteger5;
        Integer localInteger6 = Integer.valueOf(55537);
        arrayOfObject2[2] = localInteger6;
        Integer localInteger7 = Integer.valueOf(getPeriodType());
        arrayOfObject2[3] = localInteger7;
        Integer localInteger8 = Integer.valueOf(55537);
        arrayOfObject2[4] = localInteger8;
        localRequest.setData(arrayOfObject2);
        continue;
      }
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = str1;
      localRequest.setData(arrayOfObject3);
    }
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    FilteredAppListActivity.7 local7 = new FilteredAppListActivity.7(this, localRequest);
    localRequest.addObserver(local7);
    this.mCurrentRequest = localRequest;
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

  private void doSearchQuery()
  {
    if (this.mReachEnd)
      return;
    Request localRequest1;
    if (!this.doingSearchQuery)
    {
      this.doingSearchQuery = 1;
      localRequest1 = new Request(0L, 65546);
      if (this.mTitle != null)
        break label244;
    }
    label244: for (String str1 = ""; ; str1 = this.mTitle)
    {
      Object[] arrayOfObject1 = new Object[5];
      String str2 = this.mKeywords;
      arrayOfObject1[0] = str2;
      Integer localInteger1 = Integer.valueOf(this.startIndex);
      arrayOfObject1[1] = localInteger1;
      Integer localInteger2 = Integer.valueOf(10);
      arrayOfObject1[2] = localInteger2;
      Integer localInteger3 = Integer.valueOf(this.mSearchType);
      arrayOfObject1[3] = localInteger3;
      arrayOfObject1[4] = str1;
      localRequest1.setData(arrayOfObject1);
      FilteredAppListActivity.13 local13 = new FilteredAppListActivity.13(this, localRequest1);
      localRequest1.addObserver(local13);
      this.mCurrentRequest = localRequest1;
      int i = this.startIndex;
      this.oldStartIndex = i;
      this.mMarketService.getAppListByKeywords(localRequest1);
      Request localRequest2 = new Request(0L, 65558);
      StringBuilder localStringBuilder = new StringBuilder("Search");
      int j = this.mSearchPage;
      String str3 = j;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = str3;
      String str4 = this.mKeywords;
      arrayOfObject2[1] = str4;
      localRequest2.setData(arrayOfObject2);
      this.mMarketService.getSearchLog(localRequest2);
      break;
      break;
    }
  }

  private void inflateAppDateList()
  {
    inflateAppList();
  }

  private void inflateAppLikeList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      if (!this.inflatingAppLikeList)
      {
        this.inflatingAppLikeList = 1;
        Request localRequest = new Request(0L, 65555);
        String[] arrayOfString = PackageInstallInfo.getInstalledAppPackages(this, 1);
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = arrayOfString;
        Integer localInteger1 = Integer.valueOf(this.startIndex);
        arrayOfObject[1] = localInteger1;
        Integer localInteger2 = Integer.valueOf(10);
        arrayOfObject[2] = localInteger2;
        localRequest.setData(arrayOfObject);
        FilteredAppListActivity.11 local11 = new FilteredAppListActivity.11(this, localRequest);
        localRequest.addObserver(local11);
        this.mCurrentRequest = localRequest;
        int i = this.startIndex;
        this.oldStartIndex = i;
        this.mMarketService.getAppList(localRequest);
        continue;
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
        Request localRequest = new Request(0L, 65538);
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
        FilteredAppListActivity.9 local9 = new FilteredAppListActivity.9(this, localRequest);
        localRequest.addObserver(local9);
        this.mCurrentRequest = localRequest;
        Object[] arrayOfObject2 = (Object[])this.mCurrentRequest.getData();
        int i = this.startIndex;
        this.oldStartIndex = i;
        this.mMarketService.getAppList(localRequest);
        continue;
      }
    }
  }

  private void inflateAppRankingList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      if (!this.inflatingAppRankingList)
      {
        this.inflatingAppRankingList = 1;
        Request localRequest = new Request(0L, 65552);
        Object[] arrayOfObject = new Object[4];
        Integer localInteger1 = Integer.valueOf(this.category_id);
        arrayOfObject[0] = localInteger1;
        Integer localInteger2 = Integer.valueOf(this.periodType);
        arrayOfObject[1] = localInteger2;
        Integer localInteger3 = Integer.valueOf(this.startIndex);
        arrayOfObject[2] = localInteger3;
        Integer localInteger4 = Integer.valueOf(10);
        arrayOfObject[3] = localInteger4;
        localRequest.setData(arrayOfObject);
        FilteredAppListActivity.10 local10 = new FilteredAppListActivity.10(this, localRequest);
        localRequest.addObserver(local10);
        this.mCurrentRequest = localRequest;
        int i = this.startIndex;
        this.oldStartIndex = i;
        this.mMarketService.getAppList(localRequest);
        continue;
      }
    }
  }

  private void inflateAppRelativeList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      if (!this.inflatingAppRelativeList)
      {
        this.inflatingAppRelativeList = 1;
        Request localRequest = new Request(0L, 65556);
        Object[] arrayOfObject = new Object[3];
        String str = this.mPackageName;
        arrayOfObject[0] = str;
        Integer localInteger1 = Integer.valueOf(this.startIndex);
        arrayOfObject[1] = localInteger1;
        Integer localInteger2 = Integer.valueOf(10);
        arrayOfObject[2] = localInteger2;
        localRequest.setData(arrayOfObject);
        FilteredAppListActivity.12 local12 = new FilteredAppListActivity.12(this, localRequest);
        localRequest.addObserver(local12);
        this.mCurrentRequest = localRequest;
        int i = this.startIndex;
        this.oldStartIndex = i;
        this.mMarketService.getAppList(localRequest);
        continue;
      }
    }
  }

  private void initHandlerInUIThread()
  {
    FilteredAppListActivity.8 local8 = new FilteredAppListActivity.8(this);
    this.mHandler = local8;
  }

  private void initSearchBar(View paramView)
  {
    ContentResolver localContentResolver = getContentResolver();
    Uri localUri = MarketStore.Searchs.CONTENT_URI;
    String[] arrayOfString1 = SEARCH_PROJECTION;
    String[] arrayOfString2 = null;
    Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, null, arrayOfString2, "date DESC");
    CommonAppListActivity.SearchAdapter localSearchAdapter = new CommonAppListActivity.SearchAdapter(this, localCursor);
    AutoCompleteTextView localAutoCompleteTextView = (AutoCompleteTextView)paramView.findViewById(2131427507);
    this.mSearchText = localAutoCompleteTextView;
    this.mSearchText.setAdapter(localSearchAdapter);
    Button localButton1 = (Button)paramView.findViewById(2131427506);
    View.OnClickListener localOnClickListener1 = this.mSearchBarClickListener;
    localButton1.setOnClickListener(localOnClickListener1);
    Button localButton2 = (Button)paramView.findViewById(2131427508);
    View.OnClickListener localOnClickListener2 = this.mSearchBarClickListener;
    localButton2.setOnClickListener(localOnClickListener2);
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter1 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    localIntentFilter1.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter1.addAction("android.intent.action.PACKAGE_CHANGED");
    localIntentFilter1.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver1 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver1, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter("broadcast_search_request");
    BroadcastReceiver localBroadcastReceiver2 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver2, localIntentFilter2);
  }

  private void saveKeywords(String paramString)
  {
    ContentResolver localContentResolver = getContentResolver();
    Uri localUri1 = MarketStore.Searchs.CONTENT_URI;
    String[] arrayOfString = SEARCH_PROJECTION;
    String str = "keyword='" + paramString + "'";
    Cursor localCursor = localContentResolver.query(localUri1, arrayOfString, str, null, "date DESC LIMIT 1");
    if ((localCursor != null) && (localCursor.moveToFirst()))
      localCursor.close();
    while (true)
    {
      return;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("keyword", paramString);
      Uri localUri2 = MarketStore.Searchs.CONTENT_URI;
      localContentResolver.insert(localUri2, localContentValues);
    }
  }

  private void setupListViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
    View localView2 = findViewById(2131427407);
    this.mEmptyView = localView2;
    ListView localListView1 = getListView();
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
      if (this.mContainsHeader)
      {
        if (this.category_id == 11)
        {
          View localView3 = LayoutInflater.from(this).inflate(2130903078, null);
          this.headerView = localView3;
          this.headerView.setEnabled(0);
          ListView localListView3 = this.mListView;
          View localView4 = this.headerView;
          localListView3.addHeaderView(localView4, null, 0);
        }
      }
      else
      {
        View localView5 = LayoutInflater.from(this).inflate(2130903056, null);
        this.mFooterView = localView5;
        Button localButton = (Button)this.mFooterView.findViewById(2131427414);
        FilteredAppListActivity.4 local4 = new FilteredAppListActivity.4(this);
        localButton.setOnClickListener(local4);
        if ((this.category_id != 11) && (this.category_id != 12))
        {
          ListView localListView4 = this.mListView;
          View localView6 = this.mFooterView;
          localListView4.addFooterView(localView6);
        }
        if (this.category_id == 56)
          break label502;
        ListView localListView5 = this.mListView;
        View localView7 = findViewById(2131427408);
        localListView5.setEmptyView(localView7);
        TextView localTextView = (TextView)findViewById(2131427409);
        FilteredAppListActivity.5 local5 = new FilteredAppListActivity.5(this);
        localTextView.setOnClickListener(local5);
        this.mListView.setOnItemClickListener(this);
        ListView localListView6 = this.mListView;
        AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
        localListView6.setOnScrollListener(localOnScrollListener);
        if (this.periodType <= 0)
          break label527;
        inflateAppRankingList();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        continue;
        if (this.category_id == 12)
        {
          View localView8 = LayoutInflater.from(this).inflate(2130903094, null);
          this.headerView = localView8;
          this.headerView.setEnabled(0);
          ListView localListView7 = this.mListView;
          View localView9 = this.headerView;
          localListView7.addHeaderView(localView9, null, 0);
          continue;
        }
        View localView10 = LayoutInflater.from(this).inflate(2130903098, null);
        this.headerView = localView10;
        this.headerView.setEnabled(0);
        ListView localListView8 = this.mListView;
        View localView11 = this.headerView;
        localListView8.addHeaderView(localView11, null, 0);
        View localView12 = this.headerView;
        initSearchBar(localView12);
        continue;
        label502: ListView localListView9 = this.mListView;
        View localView13 = findViewById(2131427407);
        localListView9.setEmptyView(localView13);
        continue;
        label527: if (this.mItemType == 901)
        {
          inflateAppDateList();
          continue;
        }
        if (this.category_id == 11)
        {
          inflateAppLikeList();
          continue;
        }
        if (this.category_id == 12)
        {
          inflateAppRelativeList();
          continue;
        }
        if (this.category_id == 56)
        {
          this.mProgressIndicator.setVisibility(8);
          this.mListView.setVisibility(8);
          continue;
        }
        inflateAppList();
      }
    }
  }

  private void setupMDownloadStatusHandler()
  {
    FilteredAppListActivity.6 local6 = new FilteredAppListActivity.6(this);
    this.mDownloadStatusHandler = local6;
  }

  private void startSearch(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, SearchActivity.class);
    localIntent.putExtra("keyword", paramString);
    startActivity(localIntent);
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
  }

  public int getCategory()
  {
    return this.category_id;
  }

  public String getCategoryLabel()
  {
    return this.category_label;
  }

  public Handler getDownloadStatusHandler()
  {
    return this.mDownloadStatusHandler;
  }

  public String getPage()
  {
    String str;
    if (this.periodType > 0)
      str = "Ranking";
    while (true)
    {
      return str;
      if (this.category_id == 3)
      {
        str = "Most_recent";
        continue;
      }
      if (this.category_id == 11)
      {
        str = "Guess";
        continue;
      }
      if (this.category_id == 12)
      {
        str = "Relative";
        continue;
      }
      if (this.category_id == 56)
      {
        str = "Search";
        continue;
      }
      str = "Category";
    }
  }

  public int getPeriodType()
  {
    return this.periodType;
  }

  public int getRankingType()
  {
    return this.mRankingType;
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
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    if (paramBundle == null)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
      {
        int i = localBundle.getInt("_id", 1);
        this.category_id = i;
        if (localBundle == null)
          break label248;
        String str1 = localBundle.getString("label");
        label59: this.category_label = str1;
        if (localBundle == null)
          break label255;
        int j = localBundle.getInt("period", 0);
        label79: this.periodType = j;
        if (localBundle == null)
          break label262;
        boolean bool1 = localBundle.getBoolean("header");
        label98: this.mContainsHeader = bool1;
        if (localBundle == null)
          break label269;
        int k = localBundle.getInt("ranking_type");
        label117: this.mRankingType = k;
        if (localBundle == null)
          break label276;
        k = localBundle.getInt("list_item");
        label136: this.mItemType = k;
        if (localBundle == null)
          break label284;
        String str2 = localBundle.getString("pkgName");
        label155: this.mPackageName = str2;
      }
    }
    while (true)
    {
      setContentView(2130903055);
      initHandlerInUIThread();
      setupMDownloadStatusHandler();
      setupListViews();
      registerIntentReceivers();
      if ((paramBundle != null) && (this.category_id == 56))
      {
        this.mReachEnd = 0;
        this.oldStartIndex = -1;
        this.startIndex = 0;
        if (this.mAppListAdapter != null)
          this.mAppListAdapter.clear();
        this.mProgressIndicator.setVisibility(0);
        doSearchQuery();
      }
      return;
      int m = -1;
      break;
      label248: String str3 = "";
      break label59;
      label255: int n = -1;
      break label79;
      label262: n = 0;
      break label98;
      label269: n = -1;
      break label117;
      label276: n = 900;
      break label136;
      label284: String str4 = "";
      break label155;
      int i1 = paramBundle.getInt("_id");
      this.category_id = i1;
      String str5 = paramBundle.getString("label");
      this.category_label = str5;
      int i2 = paramBundle.getInt("period");
      this.periodType = i2;
      boolean bool2 = paramBundle.getBoolean("header");
      this.mContainsHeader = bool2;
      int i3 = paramBundle.getInt("ranking_type");
      this.mRankingType = i3;
      int i4 = paramBundle.getInt("list_item");
      this.mItemType = i4;
      String str6 = paramBundle.getString("pkgName");
      this.mPackageName = str6;
      if (this.category_id != 56)
        continue;
      String str7 = paramBundle.getString("keyword");
      this.mKeywords = str7;
      String str8 = paramBundle.getString("title");
      this.mTitle = str8;
      int i5 = paramBundle.getInt("searchType");
      this.mSearchType = i5;
      int i6 = paramBundle.getInt("source");
      this.mSearchPage = i6;
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
    FilteredAppListActivity.15 local15;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local15).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      FilteredAppListActivity.14 local14 = new FilteredAppListActivity.14(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local14);
      local15 = new FilteredAppListActivity.15(this);
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
    if (this.mAppListAdapter != null)
      this.mAppListAdapter.clear();
    if (this.mListView != null)
      this.mListView.setAdapter(null);
    unregisterIntentReceivers();
  }

  @Signature({"(", "Landroid/widget/AdapterView", "<*>;", "Landroid/view/View;", "IJ)V"})
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str1 = getPage();
    Intent localIntent = new Intent();
    String str2 = AssetInfoActivity.class.getName();
    localIntent.setClassName(this, str2);
    Asset localAsset = (Asset)paramAdapterView.getItemAtPosition(paramInt);
    if (localAsset == null)
      return;
    if (str1 == "Category")
    {
      String str3 = String.valueOf(str1);
      StringBuilder localStringBuilder1 = new StringBuilder(str3);
      int i = getCategory();
      StringBuilder localStringBuilder2 = localStringBuilder1.append(i).append("Type:");
      int j = getRankingType();
      str1 = j;
    }
    while (true)
    {
      int k = localAsset._id;
      localIntent.putExtra("_id", k);
      String str4 = localAsset.title;
      localIntent.putExtra("title", str4);
      int m = localAsset.installed;
      localIntent.putExtra("installed", m);
      String str5 = localAsset.pkgName;
      localIntent.putExtra("pkgName", str5);
      int n = localAsset.size;
      localIntent.putExtra("size", n);
      int i1 = localAsset.versionCode;
      localIntent.putExtra("versionCode", i1);
      localIntent.putExtra("from", str1);
      startActivity(localIntent);
      if (this.category_id != 12)
        break;
      finish();
      break;
      if (str1 == "Ranking")
      {
        String str6 = String.valueOf(str1);
        StringBuilder localStringBuilder3 = new StringBuilder(str6);
        int i2 = getPeriodType();
        str1 = i2;
        continue;
      }
      if (str1 != "Search")
        continue;
      String str7 = String.valueOf(str1);
      StringBuilder localStringBuilder4 = new StringBuilder(str7);
      int i3 = this.mSearchPage;
      str1 = i3;
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
    int i;
    if ((isFinishing()) && (this.mAppListAdapter != null))
      i = 0;
    while (true)
    {
      int j = this.mAppListAdapter.getCount();
      if (i >= j)
      {
        onPause();
        return;
      }
      Asset localAsset = (Asset)this.mAppListAdapter.getItem(i);
      if (localAsset != null)
      {
        DownloadService localDownloadService = DownloadService.getInstance(this);
        int k = localAsset._id;
        localDownloadService.removeHandler(k);
      }
      i += 1;
    }
  }

  public void onResume()
  {
    onResume();
    if (this.mAppListAdapter != null)
      this.mAppListAdapter.notifyDataSetChanged();
    addPageViewLogRequest();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = this.category_id;
    paramBundle.putInt("_id", i);
    String str1 = this.category_label;
    paramBundle.putString("label", str1);
    int j = this.periodType;
    paramBundle.putInt("period", j);
    boolean bool = this.mContainsHeader;
    paramBundle.putBoolean("header", bool);
    int k = this.mRankingType;
    paramBundle.putInt("ranking_type", k);
    int m = this.mItemType;
    paramBundle.putInt("list_item", m);
    String str2 = this.mPackageName;
    paramBundle.putString("pkgName", str2);
    if (this.category_id == 56)
    {
      String str3 = this.mKeywords;
      paramBundle.putString("keyword", str3);
      String str4 = this.mTitle;
      paramBundle.putString("title", str4);
      int n = this.mSearchType;
      paramBundle.putInt("searchType", n);
      int i1 = this.mSearchPage;
      paramBundle.putInt("source", i1);
    }
    onSaveInstanceState(paramBundle);
  }

  public boolean whetherRanking()
  {
    if (this.periodType > 0);
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredAppListActivity
 * JD-Core Version:    0.6.0
 */