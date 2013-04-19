package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SearchPageActivity extends Activity
{
  private static final int ACTION_LIST_KEYWORDS = 0;
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private int COUNT_PER_TIME_KEYWORD;
  private View.OnClickListener listener;
  private Request mCurrentRequest;
  private View mDailyHeaderView;
  private View mEmptyView;
  private Handler mHandler;
  private String mKeywords = "";
  private IMarketService mMarketService;
  private View mProgressIndicator;
  private TextView mRefreshDaily;
  private TextView mRefreshWeek;
  private float mScreenDensity;
  private int mScreenHeight;
  private int mScreenWidth;
  private View mScroll;
  private LinearLayout mSearchTags;
  private int mSearchType = 3;
  private String mTitle = "";
  private View mWeekHeaderView;
  private int periodType;
  private int startIndexKeyword;

  @Signature({"Ljava/util/ArrayList", "<", "Ljava/lang/String;", ">;"})
  ArrayList tags;

  public SearchPageActivity()
  {
    ArrayList localArrayList = new ArrayList();
    this.tags = localArrayList;
    SearchPageActivity.1 local1 = new SearchPageActivity.1(this);
    this.listener = local1;
    this.startIndexKeyword = 0;
    this.COUNT_PER_TIME_KEYWORD = 20;
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    StringBuilder localStringBuilder = new StringBuilder("SearchPage");
    int i = this.periodType;
    String str = i;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = str;
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addSearchTagsRequest()
  {
    Request localRequest = new Request(0L, 65561);
    Object[] arrayOfObject = new Object[3];
    Integer localInteger1 = Integer.valueOf(this.startIndexKeyword);
    arrayOfObject[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(this.COUNT_PER_TIME_KEYWORD);
    arrayOfObject[1] = localInteger2;
    Integer localInteger3 = Integer.valueOf(this.periodType);
    arrayOfObject[2] = localInteger3;
    localRequest.setData(arrayOfObject);
    SearchPageActivity.5 local5 = new SearchPageActivity.5(this, localRequest);
    localRequest.addObserver(local5);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getTopKeywords(localRequest);
  }

  private void initHandlerInUIThread()
  {
    SearchPageActivity.4 local4 = new SearchPageActivity.4(this);
    this.mHandler = local4;
  }

  private void setupTagsView()
  {
    int j;
    int n;
    int i1;
    LinearLayout.LayoutParams localLayoutParams3;
    int i20;
    label378: int i23;
    label387: LinearLayout localLinearLayout2;
    try
    {
      LinearLayout localLinearLayout1 = this.mSearchTags;
      int i = this.mSearchTags.getChildCount() - 2;
      localLinearLayout1.removeViews(2, i);
      if (this.mScreenDensity > 1.0F)
      {
        j = 22;
        int k = getResources().getConfiguration().orientation;
        int m = 1;
        if (k != m)
          break label378;
        n = this.mScreenWidth;
        i1 = 0;
        localLayoutParams1 = new android/widget/LinearLayout$LayoutParams;
        float f1 = j + 17;
        float f2 = this.mScreenDensity;
        int i2 = (int)(f1 * f2);
        LinearLayout.LayoutParams localLayoutParams2 = localLayoutParams1;
        int i3 = 65535;
        int i4 = i2;
        localLayoutParams2.<init>(i3, i4);
        float f3 = this.mScreenDensity;
        int i5 = (int)(5.0F * f3);
        localLayoutParams1.leftMargin = i5;
        float f4 = this.mScreenDensity;
        int i6 = (int)(5.0F * f4);
        localLayoutParams1.rightMargin = i6;
        float f5 = j - 15;
        float f6 = this.mScreenDensity;
        int i7 = (int)(f5 * f6);
        localLayoutParams1.topMargin = i7;
        float f7 = j - 15;
        float f8 = this.mScreenDensity;
        int i8 = (int)(f7 * f8);
        localLayoutParams1.bottomMargin = i8;
        int i9 = 17;
        localLayoutParams1.gravity = i9;
        localLayoutParams3 = new android/widget/LinearLayout$LayoutParams;
        LinearLayout.LayoutParams localLayoutParams4 = localLayoutParams3;
        int i10 = 65534;
        int i11 = 65535;
        localLayoutParams4.<init>(i10, i11);
        float f9 = this.mScreenDensity;
        int i12 = (int)(5.0F * f9);
        localLayoutParams3.leftMargin = i12;
        float f10 = this.mScreenDensity;
        int i13 = (int)(5.0F * f10);
        localLayoutParams3.rightMargin = i13;
        int i14 = 0;
        localLayoutParams3.topMargin = i14;
        int i15 = 0;
        localLayoutParams3.bottomMargin = i15;
        int i16 = 17;
        localLayoutParams3.gravity = i16;
        int i17 = localLayoutParams1.leftMargin;
        int i18 = localLayoutParams1.rightMargin;
        int i19 = i17 + i18;
        n -= i19;
        i20 = 0;
        int i21 = i20;
        int i22 = 5;
        if (i21 < i22)
          break label387;
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      LinearLayout.LayoutParams localLayoutParams1;
      while (true)
      {
        localThrowable.printStackTrace();
        continue;
        j = 18;
        continue;
        n = this.mScreenHeight;
      }
      i23 = n;
      localLinearLayout2 = new android/widget/LinearLayout;
      LinearLayout localLinearLayout3 = localLinearLayout2;
      SearchPageActivity localSearchPageActivity1 = this;
      localLinearLayout3.<init>(localSearchPageActivity1);
      LinearLayout localLinearLayout4 = localLinearLayout2;
      int i24 = 0;
      localLinearLayout4.setOrientation(i24);
      localLinearLayout2.setLayoutParams(localLayoutParams1);
    }
    while (true)
    {
      int i25 = this.tags.size();
      int i26 = i1;
      int i27 = i25;
      if (i26 >= i27);
      do
      {
        LinearLayout localLinearLayout5 = this.mSearchTags;
        LinearLayout localLinearLayout6 = localLinearLayout2;
        localLinearLayout5.addView(localLinearLayout6);
        i20 += 1;
        break;
        TextView localTextView1 = new android/widget/TextView;
        TextView localTextView2 = localTextView1;
        SearchPageActivity localSearchPageActivity2 = this;
        localTextView2.<init>(localSearchPageActivity2);
        float f11 = j;
        TextView localTextView3 = localTextView1;
        float f12 = f11;
        localTextView3.setTextSize(f12);
        TextPaint localTextPaint1 = localTextView1.getPaint();
        ArrayList localArrayList1 = this.tags;
        int i28 = i1;
        String str1 = (String)localArrayList1.get(i28);
        TextPaint localTextPaint2 = localTextPaint1;
        String str2 = str1;
        float f13 = localTextPaint2.measureText(str2);
        float f14 = this.mScreenDensity;
        float f15 = 20.0F * f14;
        int i29 = (int)(f13 + f15 + 0.5D);
        int i30 = localLayoutParams3.leftMargin;
        int i31 = localLayoutParams3.rightMargin;
        int i32 = i30 + i31 + i29;
        int i33 = i23;
        int i34 = i32;
        if (i33 <= i34)
          continue;
        localTextView1.setLayoutParams(localLayoutParams3);
        int i35 = getResources().getColor(2131230733);
        TextView localTextView4 = localTextView1;
        int i36 = i35;
        localTextView4.setTextColor(i36);
        int i37 = DeviceUtil.getSDKVersionInt();
        int i38 = 3;
        if (i37 == i38)
        {
          ArrayList localArrayList2 = this.tags;
          int i39 = i1;
          CharSequence localCharSequence = (CharSequence)localArrayList2.get(i39);
          localTextView1.setText(localCharSequence);
        }
        while (true)
        {
          TextView localTextView5 = localTextView1;
          int i40 = 2130837681;
          localTextView5.setBackgroundResource(i40);
          localTextView1.setWidth(i29);
          float f16 = this.mScreenDensity;
          int i41 = (int)(10.0F * f16);
          float f17 = this.mScreenDensity;
          int i42 = (int)(5.0F * f17);
          float f18 = this.mScreenDensity;
          int i43 = (int)(10.0F * f18);
          float f19 = this.mScreenDensity;
          int i44 = (int)(5.0F * f19);
          TextView localTextView6 = localTextView1;
          int i45 = i41;
          int i46 = i42;
          int i47 = i43;
          int i48 = i44;
          localTextView6.setPadding(i45, i46, i47, i48);
          SearchPageActivity.3 local31 = new com/yingyonghui/market/SearchPageActivity$3;
          SearchPageActivity.3 local32 = local31;
          SearchPageActivity localSearchPageActivity3 = this;
          local32.<init>(localSearchPageActivity3);
          TextView localTextView7 = localTextView1;
          SearchPageActivity.3 local33 = local31;
          localTextView7.setOnClickListener(local33);
          localLinearLayout2.addView(localTextView1);
          int i49 = localLayoutParams3.leftMargin;
          int i50 = localLayoutParams3.rightMargin;
          int i51 = i49 + i50 + i29;
          i23 -= i51;
          i1 += 1;
          break;
          StringBuilder localStringBuilder1 = new StringBuilder("<u>");
          ArrayList localArrayList3 = this.tags;
          int i52 = i1;
          String str3 = (String)localArrayList3.get(i52);
          StringBuilder localStringBuilder2 = localStringBuilder1;
          String str4 = str3;
          Spanned localSpanned1 = Html.fromHtml(str4 + "</u>");
          TextView localTextView8 = localTextView1;
          Spanned localSpanned2 = localSpanned1;
          localTextView8.setText(localSpanned2);
        }
      }
      while (i23 < n);
      i1 += 1;
    }
  }

  private void setupViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
    this.mProgressIndicator.setVisibility(0);
    View localView2 = findViewById(2131427408);
    this.mEmptyView = localView2;
    this.mEmptyView.setVisibility(8);
    TextView localTextView1 = (TextView)findViewById(2131427409);
    SearchPageActivity.2 local2 = new SearchPageActivity.2(this);
    localTextView1.setOnClickListener(local2);
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427516);
    this.mSearchTags = localLinearLayout;
    View localView3 = findViewById(2131427515);
    this.mScroll = localView3;
    if (DeviceUtil.getSDKVersionInt() > 4);
    try
    {
      Class localClass1 = this.mScroll.getClass();
      Class[] arrayOfClass = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass[0] = localClass2;
      Method localMethod = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass);
      View localView4 = this.mScroll;
      Object[] arrayOfObject = new Object[1];
      Boolean localBoolean = Boolean.valueOf(1);
      arrayOfObject[0] = localBoolean;
      localMethod.invoke(localView4, arrayOfObject);
      this.mScroll.setVisibility(8);
      View localView5 = findViewById(2131427440);
      this.mDailyHeaderView = localView5;
      View localView6 = findViewById(2131427542);
      this.mWeekHeaderView = localView6;
      this.mDailyHeaderView.setVisibility(8);
      this.mWeekHeaderView.setVisibility(8);
      TextView localTextView2 = (TextView)findViewById(2131427441);
      this.mRefreshDaily = localTextView2;
      TextView localTextView3 = this.mRefreshDaily;
      View.OnClickListener localOnClickListener1 = this.listener;
      localTextView3.setOnClickListener(localOnClickListener1);
      if (DeviceUtil.getSDKVersionInt() > 3)
      {
        this.mRefreshDaily.setGravity(17);
        TextView localTextView4 = (TextView)findViewById(2131427543);
        this.mRefreshWeek = localTextView4;
        TextView localTextView5 = this.mRefreshWeek;
        View.OnClickListener localOnClickListener2 = this.listener;
        localTextView5.setOnClickListener(localOnClickListener2);
        addSearchTagsRequest();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        continue;
        TextView localTextView6 = this.mRefreshDaily;
        int i = (int)(this.mScreenDensity * 10.0F);
        int j = (int)(this.mScreenDensity * 5.0F);
        int k = (int)(this.mScreenDensity * 10.0F);
        int m = (int)(this.mScreenDensity * 5.0F);
        localTextView6.setPadding(i, j, k, m);
      }
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    onConfigurationChanged(paramConfiguration);
    setupTagsView();
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    int i = localDisplayMetrics.heightPixels;
    this.mScreenHeight = i;
    int j = localDisplayMetrics.widthPixels;
    this.mScreenWidth = j;
    float f = localDisplayMetrics.density;
    this.mScreenDensity = f;
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    int k = getIntent().getIntExtra("search_type", -1);
    this.periodType = k;
    requestWindowFeature(1);
    setTheme(16973834);
    setContentView(2130903101);
    initHandlerInUIThread();
    setupViews();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder2;
    SearchPageActivity.7 local7;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local7).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      SearchPageActivity.6 local6 = new SearchPageActivity.6(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local6);
      local7 = new SearchPageActivity.7(this);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = onCreateOptionsMenu(paramMenu);
    if (bool)
      OptionsMenu.onCreateOptionsMenu(paramMenu);
    return bool;
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
    addPageViewLogRequest();
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchPageActivity
 * JD-Core Version:    0.6.0
 */