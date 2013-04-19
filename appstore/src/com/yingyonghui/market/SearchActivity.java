package com.yingyonghui.market;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import com.yingyonghui.market.provider.MarketStore.Searchs;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SearchActivity extends TabActivity
  implements TabHost.OnTabChangeListener
{
  public static final int ID_DEFAULT_SEARCH = 56;
  public static final int ID_SEARCH_DAY = 0;
  public static final int ID_SEARCH_RESULT = 1;
  private static final String[] SEARCH_PROJECTION;
  private static final int TAB_ID_DAY = 0;
  private static final int TAB_ID_RESULT = 1;
  private static final String TAB_SEARCH_DAY = "day";
  private static final String TAB_SEARCH_RESULT = "result";
  private int height = 44;
  private boolean isProcessedTouchModeChange = 0;
  private float labelTextSize = 18.0F;
  private final BroadcastReceiver mApplicationsReceiver;
  private Field mBottomLeftStrip;
  private Field mBottomRightStrip;
  private View mHeaderView;
  private String mKeywords;
  private View.OnClickListener mSearchBarClickListener;
  private AutoCompleteTextView mSearchText;
  private int mSearchType = 3;
  private TabHost mTabHost;
  private String mTitle;

  @Signature({"Ljava/util/ArrayList", "<", "Landroid/view/View;", ">;"})
  private ArrayList tabViews;
  private TabWidget tw;

  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "date";
    arrayOfString[2] = "keyword";
    SEARCH_PROJECTION = arrayOfString;
  }

  public SearchActivity()
  {
    SearchActivity.1 local1 = new SearchActivity.1(this);
    this.mSearchBarClickListener = local1;
    SearchActivity.2 local2 = new SearchActivity.2(this);
    this.mApplicationsReceiver = local2;
  }

  private void initSearchBar()
  {
    ContentResolver localContentResolver = getContentResolver();
    try
    {
      Uri localUri = MarketStore.Searchs.CONTENT_URI;
      String[] arrayOfString = SEARCH_PROJECTION;
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString, null, null, "date DESC");
      label25: CommonAppListActivity.SearchAdapter localSearchAdapter = new CommonAppListActivity.SearchAdapter(this, localCursor);
      AutoCompleteTextView localAutoCompleteTextView1 = (AutoCompleteTextView)this.mHeaderView.findViewById(2131427507);
      this.mSearchText = localAutoCompleteTextView1;
      this.mSearchText.setAdapter(localSearchAdapter);
      this.mSearchText.setThreshold(1);
      AutoCompleteTextView localAutoCompleteTextView2 = this.mSearchText;
      SearchActivity.3 local3 = new SearchActivity.3(this);
      localAutoCompleteTextView2.setOnFocusChangeListener(local3);
      Button localButton1 = (Button)this.mHeaderView.findViewById(2131427506);
      View.OnClickListener localOnClickListener1 = this.mSearchBarClickListener;
      localButton1.setOnClickListener(localOnClickListener1);
      Button localButton2 = (Button)this.mHeaderView.findViewById(2131427508);
      View.OnClickListener localOnClickListener2 = this.mSearchBarClickListener;
      localButton2.setOnClickListener(localOnClickListener2);
      return;
    }
    catch (Exception localException)
    {
      break label25;
    }
  }

  private void initTabs()
  {
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("day");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView.setBackgroundResource(2130837700);
    localTextView.setText(2131296332);
    int i = getResources().getColor(2130837744);
    localTextView.setTextColor(i);
    float f1 = this.labelTextSize;
    localTextView.setTextSize(f1);
    localTextView.setLayoutParams(localLayoutParams);
    try
    {
      Class[] arrayOfClass1 = new Class[1];
      arrayOfClass1[0] = View.class;
      Method localMethod1 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass1);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localTextView;
      localMethod1.invoke(localTabSpec, arrayOfObject1);
      label170: this.tabViews.add(localTextView);
      Intent localIntent1 = new Intent();
      String str1 = SearchPageActivity.class.getName();
      localIntent1.setClassName(this, str1);
      localIntent1.putExtra("search_type", 0);
      localIntent1.addFlags(268435456);
      localTabSpec.setContent(localIntent1);
      this.mTabHost.addTab(localTabSpec);
      localTabSpec = this.mTabHost.newTabSpec("result");
      localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
      localTextView.setBackgroundResource(2130837700);
      localTextView.setText(2131296334);
      int j = getResources().getColor(2130837744);
      localTextView.setTextColor(j);
      float f2 = this.labelTextSize;
      localTextView.setTextSize(f2);
      localTextView.setLayoutParams(localLayoutParams);
      try
      {
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = View.class;
        Method localMethod2 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass2);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localTextView;
        localMethod2.invoke(localTabSpec, arrayOfObject2);
        label362: this.tabViews.add(localTextView);
        Intent localIntent2 = new Intent();
        String str2 = FilteredAppListActivity.class.getName();
        localIntent2.setClassName(this, str2);
        localIntent2.putExtra("_id", 56);
        localIntent2.putExtra("header", 0);
        localTabSpec.setContent(localIntent2);
        this.mTabHost.addTab(localTabSpec);
        this.mTabHost.setOnTabChangedListener(this);
        setCurrentTab(1);
        setCurrentTab(0);
        return;
      }
      catch (Exception localException1)
      {
        break label362;
      }
    }
    catch (Exception localException2)
    {
      break label170;
    }
  }

  private void initTabsLow()
  {
    TabHost.TabSpec localTabSpec1 = this.mTabHost.newTabSpec("day");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView1 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView1.setBackgroundResource(2130837700);
    localTextView1.setText(2131296332);
    int i = getResources().getColor(2130837744);
    localTextView1.setTextColor(i);
    float f1 = this.labelTextSize;
    localTextView1.setTextSize(f1);
    localTextView1.setLayoutParams(localLayoutParams);
    String str1 = getResources().getString(2131296332);
    localTabSpec1.setIndicator(str1);
    this.tabViews.add(localTextView1);
    Intent localIntent1 = new Intent();
    String str2 = SearchPageActivity.class.getName();
    localIntent1.setClassName(this, str2);
    localIntent1.putExtra("search_type", 0);
    localIntent1.addFlags(268435456);
    localTabSpec1.setContent(localIntent1);
    this.mTabHost.addTab(localTabSpec1);
    TabHost.TabSpec localTabSpec2 = this.mTabHost.newTabSpec("result");
    TextView localTextView2 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView2.setBackgroundResource(2130837700);
    localTextView2.setText(2131296334);
    int j = getResources().getColor(2130837744);
    localTextView2.setTextColor(j);
    float f2 = this.labelTextSize;
    localTextView2.setTextSize(f2);
    localTextView2.setLayoutParams(localLayoutParams);
    String str3 = getResources().getString(2131296334);
    localTabSpec2.setIndicator(str3);
    this.tabViews.add(localTextView2);
    Intent localIntent2 = new Intent();
    String str4 = FilteredAppListActivity.class.getName();
    localIntent2.setClassName(this, str4);
    localIntent2.putExtra("_id", 56);
    localIntent2.putExtra("header", 0);
    localTabSpec2.setContent(localIntent2);
    this.mTabHost.addTab(localTabSpec2);
    this.mTabHost.setOnTabChangedListener(this);
    setCurrentTabLow(1);
    setCurrentTabLow(0);
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter = new IntentFilter("show_search_result");
    localIntentFilter.addAction("broadcast_search_request");
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver, localIntentFilter);
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

  private void setCurrentTab(int paramInt)
  {
    View localView1;
    View localView2;
    if (paramInt == 0)
    {
      localView1 = (View)this.tabViews.get(0);
      localView2 = (View)this.tabViews.get(1);
      localView1.setBackgroundResource(2130837700);
      localView1.setFocusable(1);
      localView1.setFocusableInTouchMode(1);
      localView1.requestFocus();
      localView1.requestFocusFromTouch();
    }
    while (true)
    {
      localView2.setBackgroundDrawable(null);
      TextView localTextView1 = (TextView)localView1;
      Typeface localTypeface1 = Typeface.DEFAULT_BOLD;
      localTextView1.setTypeface(localTypeface1);
      TextView localTextView2 = (TextView)localView2;
      Typeface localTypeface2 = Typeface.DEFAULT;
      localTextView2.setTypeface(localTypeface2);
      return;
      localView1 = (View)this.tabViews.get(1);
      localView2 = (View)this.tabViews.get(0);
      localView1.setBackgroundResource(2130837700);
      localView1.setFocusable(1);
      localView1.setFocusableInTouchMode(1);
      localView1.requestFocus();
      localView1.requestFocusFromTouch();
    }
  }

  private void setCurrentTabLow(int paramInt)
  {
    RelativeLayout localRelativeLayout1 = (RelativeLayout)this.tw.getChildAt(0);
    RelativeLayout localRelativeLayout2 = (RelativeLayout)this.tw.getChildAt(1);
    if (paramInt == 0)
    {
      Drawable localDrawable1 = getResources().getDrawable(2130837700);
      localRelativeLayout1.setBackgroundDrawable(localDrawable1);
      localRelativeLayout2.setBackgroundDrawable(null);
      localRelativeLayout1.setFocusable(1);
      localRelativeLayout1.setFocusableInTouchMode(1);
      localRelativeLayout1.requestFocus();
      localRelativeLayout1.requestFocusFromTouch();
    }
    while (true)
    {
      return;
      localRelativeLayout1.setBackgroundDrawable(null);
      Drawable localDrawable2 = getResources().getDrawable(2130837700);
      localRelativeLayout2.setBackgroundDrawable(localDrawable2);
      localRelativeLayout2.setFocusable(1);
      localRelativeLayout2.setFocusableInTouchMode(1);
      localRelativeLayout2.requestFocus();
      localRelativeLayout2.requestFocusFromTouch();
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
    View localView = findViewById(2131427505);
    this.mHeaderView = localView;
    initSearchBar();
    if (DeviceUtil.getSDKVersionInt() == 3)
      setupTabsLow();
    while (true)
    {
      return;
      setupTabs();
    }
  }

  private void startSearch(String paramString)
  {
    ((InputMethodManager)getSystemService("input_method")).toggleSoftInput(0, 2);
    Intent localIntent1 = getIntent();
    String str1 = localIntent1.getAction();
    if ("android.intent.action.SEARCH".equals(str1))
    {
      String str2 = localIntent1.getStringExtra("query");
      this.mKeywords = str2;
    }
    String str3;
    while (this.mKeywords == null)
    {
      str3 = localIntent1.getData().getEncodedQuery();
      if ((str3 == null) || (str3.length() < 3) || (!str3.startsWith("q=")))
      {
        return;
        this.mKeywords = paramString;
        continue;
      }
      str3 = str3.substring(2);
      label108: if (str3 == null)
        break label272;
      if ((!str3.startsWith("pname:")) || (str3.length() <= 6))
        break label274;
      String str4 = str3.substring(6);
      this.mKeywords = str4;
      this.mSearchType = 2;
    }
    while (true)
    {
      String str5 = this.mKeywords.trim();
      this.mKeywords = str5;
      String str6 = Uri.decode(this.mKeywords);
      this.mKeywords = str6;
      Intent localIntent2 = new Intent("broadcast_search_request");
      localIntent2.putExtra("source", 1);
      String str7 = this.mKeywords;
      localIntent2.putExtra("keyword", str7);
      String str8 = this.mTitle;
      localIntent2.putExtra("title", str8);
      int i = this.mSearchType;
      localIntent2.putExtra("searchType", i);
      sendBroadcast(localIntent2);
      break;
      str3 = this.mKeywords;
      break label108;
      label272: break;
      label274: if ((str3.startsWith("pnames:")) && (str3.length() > 7))
      {
        String str9 = str3.substring(7);
        this.mKeywords = str9;
        this.mSearchType = 4;
        continue;
      }
      if (str3.startsWith("pub:"))
      {
        String str10;
        if (str3.length() > 4)
          str10 = str3.substring(4);
        for (this.mKeywords = str10; ; this.mKeywords = "")
        {
          this.mSearchType = 1;
          break;
        }
      }
      this.mKeywords = str3;
      this.mSearchType = 3;
    }
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    setTheme(16973834);
    setContentView(2130903102);
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabWidget localTabWidget = (TabWidget)this.mTabHost.getChildAt(1);
    this.tw = localTabWidget;
    setupViews();
    registerIntentReceivers();
  }

  public void onDestroy()
  {
    onDestroy();
    unregisterIntentReceivers();
  }

  public void onTabChanged(String paramString)
  {
    int i;
    if ("day".equals(paramString))
    {
      i = 0;
      if (DeviceUtil.getSDKVersionInt() != 3)
        break label29;
      setCurrentTabLow(i);
    }
    while (true)
    {
      return;
      i = 1;
      break;
      label29: setCurrentTab(i);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchActivity
 * JD-Core Version:    0.6.0
 */