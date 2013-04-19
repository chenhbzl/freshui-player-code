package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

@Signature({"Landroid/app/Activity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class SearchAssetListActivity extends Activity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 1;
  private static final int ACTION_LIST_APPS = 0;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  private FilteredAppListAdapter mAppListAdapter;
  private final BroadcastReceiver mApplicationsReceiver;
  private boolean mBusy;
  private Request mCurrentRequest;
  private Handler mDownloadStatusHandler;
  private View mEmpty;
  private View mFooterView;
  private Handler mHandler;
  private View mHeaderView;
  private String mKeywords;
  private ListView mListView;
  private IMarketService mMarketService;
  private View mProgressIndicator;
  private boolean mReachEnd;
  private int mSearchType = 3;
  private String mTitle;
  private int oldStartIndex;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;

  public SearchAssetListActivity()
  {
    SearchAssetListActivity.1 local1 = new SearchAssetListActivity.1(this);
    this.mApplicationsReceiver = local1;
    SearchAssetListActivity.2 local2 = new SearchAssetListActivity.2(this);
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
    arrayOfObject[0] = "SearchAsset";
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    SearchAssetListActivity.7 local7 = new SearchAssetListActivity.7(this, localRequest);
    localRequest.addObserver(local7);
    this.mCurrentRequest = localRequest;
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

  private void doSearchQuery()
  {
    if (!this.mReachEnd)
    {
      int i = this.oldStartIndex;
      int j = this.startIndex;
      if (i != j);
    }
    else
    {
      return;
    }
    Request localRequest = new Request(0L, 65546);
    String str1 = this.mKeywords;
    if (this.mTitle == null);
    for (String str2 = ""; ; str2 = this.mTitle)
    {
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = str1;
      Integer localInteger1 = Integer.valueOf(this.startIndex);
      arrayOfObject[1] = localInteger1;
      Integer localInteger2 = Integer.valueOf(10);
      arrayOfObject[2] = localInteger2;
      Integer localInteger3 = Integer.valueOf(this.mSearchType);
      arrayOfObject[3] = localInteger3;
      arrayOfObject[4] = str2;
      localRequest.setData(arrayOfObject);
      SearchAssetListActivity.5 local5 = new SearchAssetListActivity.5(this, localRequest);
      localRequest.addObserver(local5);
      this.mCurrentRequest = localRequest;
      int k = this.startIndex;
      this.oldStartIndex = k;
      this.mMarketService.getAppListByKeywords(localRequest);
      break;
    }
  }

  private void initHandlerInUIThread()
  {
    SearchAssetListActivity.8 local8 = new SearchAssetListActivity.8(this);
    this.mHandler = local8;
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

  private void setupMDownloadStatusHandler()
  {
    SearchAssetListActivity.6 local6 = new SearchAssetListActivity.6(this);
    this.mDownloadStatusHandler = local6;
  }

  private void setupViews()
  {
    TextView localTextView = (TextView)findViewById(2131427392);
    String str1 = getIntent().getStringExtra("title");
    this.mTitle = str1;
    if ((this.mTitle == null) || (this.mTitle.length() == 0))
      if ((this.mKeywords == null) || (this.mKeywords.length() == 0))
        localTextView.setText(2131296355);
    while (true)
    {
      ImageButton localImageButton = (ImageButton)findViewById(2131427390);
      SearchAssetListActivity.3 local3 = new SearchAssetListActivity.3(this);
      localImageButton.setOnClickListener(local3);
      View localView1 = findViewById(2131427468);
      this.mProgressIndicator = localView1;
      View localView2 = findViewById(2131427407);
      this.mEmpty = localView2;
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
        View localView3 = LayoutInflater.from(this).inflate(2130903098, null);
        this.mHeaderView = localView3;
        this.mHeaderView.setEnabled(0);
        View localView4 = LayoutInflater.from(this).inflate(2130903056, null);
        this.mFooterView = localView4;
        Button localButton = (Button)this.mFooterView.findViewById(2131427414);
        SearchAssetListActivity.4 local4 = new SearchAssetListActivity.4(this);
        localButton.setOnClickListener(local4);
        ListView localListView3 = this.mListView;
        View localView5 = this.mFooterView;
        localListView3.addFooterView(localView5);
        ListView localListView4 = this.mListView;
        View localView6 = findViewById(2131427407);
        localListView4.setEmptyView(localView6);
        ListView localListView5 = this.mListView;
        AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
        localListView5.setOnScrollListener(localOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        doSearchQuery();
        return;
        String str2 = this.mKeywords;
        localTextView.setText(str2);
        continue;
        String str3 = this.mTitle;
        localTextView.setText(str3);
      }
      catch (Throwable localThrowable)
      {
        while (true)
          localThrowable.printStackTrace();
      }
    }
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
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    requestWindowFeature(1);
    setTheme(16973834);
    setContentView(2130903099);
    initHandlerInUIThread();
    setupMDownloadStatusHandler();
    Intent localIntent = getIntent();
    String str1 = localIntent.getAction();
    if ("android.intent.action.SEARCH".equals(str1))
    {
      String str2 = localIntent.getStringExtra("query");
      this.mKeywords = str2;
    }
    String str3;
    while (this.mKeywords == null)
    {
      str3 = localIntent.getData().getEncodedQuery();
      if ((str3 == null) || (str3.length() < 3) || (!str3.startsWith("q=")))
      {
        return;
        String str4 = localIntent.getStringExtra("keyword");
        this.mKeywords = str4;
        continue;
      }
      str3 = str3.substring(2);
      label148: if (str3 == null)
        break label302;
      if ((!str3.startsWith("pname:")) || (str3.length() <= 6))
        break label304;
      String str5 = str3.substring(6);
      this.mKeywords = str5;
      this.mSearchType = 2;
    }
    while (true)
    {
      String str6 = this.mKeywords.trim();
      this.mKeywords = str6;
      String str7 = this.mKeywords.replace("%", "");
      this.mKeywords = str7;
      String str8 = this.mKeywords.replace("'", "");
      this.mKeywords = str8;
      String str9 = this.mKeywords.replace("\"", "");
      this.mKeywords = str9;
      String str10 = Uri.decode(this.mKeywords);
      this.mKeywords = str10;
      setupViews();
      registerIntentReceivers();
      break;
      str3 = this.mKeywords;
      break label148;
      label302: break;
      label304: if ((str3.startsWith("pnames:")) && (str3.length() > 7))
      {
        String str11 = str3.substring(7);
        this.mKeywords = str11;
        this.mSearchType = 4;
        continue;
      }
      if (str3.startsWith("pub:"))
      {
        String str12;
        if (str3.length() > 4)
          str12 = str3.substring(4);
        for (this.mKeywords = str12; ; this.mKeywords = "")
        {
          this.mSearchType = 1;
          break;
        }
      }
      this.mKeywords = str3;
      this.mSearchType = 3;
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
    SearchAssetListActivity.10 local10;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local10).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      SearchAssetListActivity.9 local9 = new SearchAssetListActivity.9(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local9);
      local10 = new SearchAssetListActivity.10(this);
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
      int i = localAsset._id;
      localIntent.putExtra("_id", i);
      String str2 = localAsset.title;
      localIntent.putExtra("title", str2);
      String str3 = localAsset.author;
      localIntent.putExtra("author", str3);
      float f1 = localAsset.rating;
      localIntent.putExtra("rating", f1);
      float f2 = localAsset.price;
      localIntent.putExtra("price", f2);
      int j = localAsset.installed;
      localIntent.putExtra("installed", j);
      String str4 = localAsset.pkgName;
      localIntent.putExtra("pkgName", str4);
      int k = localAsset.size;
      localIntent.putExtra("size", k);
      int m = localAsset.versionCode;
      localIntent.putExtra("versionCode", m);
      startActivity(localIntent);
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
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchAssetListActivity
 * JD-Core Version:    0.6.0
 */