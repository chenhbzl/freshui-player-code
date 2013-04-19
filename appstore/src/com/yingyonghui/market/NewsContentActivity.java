package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.install.InstallAppProgress;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.model.NewsContent;
import com.yingyonghui.market.model.NewsContent.NewsHeader;
import com.yingyonghui.market.model.NewsContent.SubApp;
import com.yingyonghui.market.model.NewsContent.SubImage;
import com.yingyonghui.market.model.NewsContent.SubItem;
import com.yingyonghui.market.model.NewsContent.SubNews;
import com.yingyonghui.market.model.NewsContent.SubText;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.Signature;
import java.io.File;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class NewsContentActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_NETWORK_ERROR = 4;
  private static final int ACTION_NEWS_APP = 2;
  private static final int ACTION_NEWS_CONTENT = 0;
  private static final int ACTION_NEWS_IMAGE = 1;
  private static final int ACTION_NEWS_THUMB = 3;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private LayoutInflater inflater;
  private NumberFormat instance;
  private Request mCurrentRequest;

  @Signature({"Ljava/util/HashMap", "<", "Lcom/yingyonghui/market/model/AssetDetail;", "Landroid/view/View;", ">;"})
  private HashMap mDownloadMap;
  private Handler mDownloadStatusHandler;
  private View mEmptyView;
  private Handler mHandler;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/lang/String;", ">;"})
  private HashMap mInstallUpdateMap;
  private IMarketService mMarketService;
  private LinearLayout mNews;
  private String mNewsURL;
  private View mProgressIndicator;
  private View mScroll;
  private int mSubContainerIndex;
  private int mViewIndex;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Landroid/view/View;", ">;"})
  private HashMap mViewMap;
  private Handler mWifiHandler;

  public NewsContentActivity()
  {
    HashMap localHashMap1 = new HashMap();
    this.mViewMap = localHashMap1;
    HashMap localHashMap2 = new HashMap();
    this.mDownloadMap = localHashMap2;
    HashMap localHashMap3 = new HashMap();
    this.mInstallUpdateMap = localHashMap3;
    this.mViewIndex = 1;
    this.mSubContainerIndex = 1;
    this.inflater = null;
    this.mNewsURL = null;
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    this.instance = localNumberFormat;
  }

  private void addAppInfoRequest(NewsContent.SubApp paramSubApp)
  {
    Request localRequest = new Request(0L, 65566);
    Object[] arrayOfObject = new Object[2];
    Integer localInteger1 = Integer.valueOf(paramSubApp.applicationId);
    arrayOfObject[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(this.mViewIndex);
    arrayOfObject[1] = localInteger2;
    localRequest.setData(arrayOfObject);
    NewsContentActivity.7 local7 = new NewsContentActivity.7(this, localRequest);
    localRequest.addObserver(local7);
    this.mMarketService.getNewsAppInfo(localRequest);
  }

  private void addAppThumbRequest(NewsContent.SubApp paramSubApp)
  {
    Request localRequest = new Request(0L, 65567);
    Object[] arrayOfObject = new Object[2];
    Integer localInteger1 = Integer.valueOf(paramSubApp.applicationId);
    arrayOfObject[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(this.mViewIndex);
    arrayOfObject[1] = localInteger2;
    localRequest.setData(arrayOfObject);
    NewsContentActivity.8 local8 = new NewsContentActivity.8(this, localRequest);
    localRequest.addObserver(local8);
    this.mMarketService.getNewsThumb(localRequest);
  }

  private void addDownloadAndInstallRequest(AssetDetail paramAssetDetail)
  {
    if (paramAssetDetail != null)
    {
      Request localRequest = new Request(0L, 65541);
      int i = paramAssetDetail.size;
      String str1 = paramAssetDetail.pkgName;
      String str2 = paramAssetDetail.title;
      Object[] arrayOfObject = new Object[4];
      Integer localInteger1 = Integer.valueOf(paramAssetDetail._id);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject[1] = localInteger2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = str2;
      localRequest.setData(arrayOfObject);
      Handler localHandler = this.mWifiHandler;
      localRequest.addWifiObserver(localHandler);
      MarketService.getServiceInstance(this).getAppContentStream(localRequest);
    }
  }

  private void addImageRequest(NewsContent.SubImage paramSubImage)
  {
    Request localRequest = new Request(0L, 65565);
    Object[] arrayOfObject = new Object[2];
    String str = paramSubImage.url;
    arrayOfObject[0] = str;
    Integer localInteger = Integer.valueOf(this.mViewIndex);
    arrayOfObject[1] = localInteger;
    localRequest.setData(arrayOfObject);
    NewsContentActivity.6 local6 = new NewsContentActivity.6(this, localRequest);
    localRequest.addObserver(local6);
    this.mMarketService.getNewsImage(localRequest);
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65568);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "Zhuanlan";
    Integer localInteger = Integer.valueOf(this.mNewsURL.hashCode());
    arrayOfObject[1] = localInteger;
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private boolean checkAppDownloading(int paramInt)
  {
    return DownloadService.getInstance(this).isTaskRunning(paramInt);
  }

  private void constructNewsContent(NewsContent paramNewsContent)
  {
    if ((this.mNews == null) || (paramNewsContent == null));
    List localList;
    do
    {
      return;
      NewsContent.NewsHeader localNewsHeader = paramNewsContent.getHeader();
      if (localNewsHeader != null)
      {
        LinearLayout localLinearLayout1 = this.mNews;
        View localView = newsTitle(localNewsHeader);
        localLinearLayout1.addView(localView);
      }
      localList = paramNewsContent.getSubNewsList();
    }
    while (localList == null);
    int i = 0;
    while (true)
    {
      int j = localList.size();
      if (i >= j)
        break;
      NewsContent.SubNews localSubNews = (NewsContent.SubNews)localList.get(i);
      if (localSubNews != null)
      {
        LinearLayout localLinearLayout2 = this.mNews;
        int k = this.mSubContainerIndex;
        LinearLayout localLinearLayout3 = newsContainer(k, localSubNews);
        localLinearLayout2.addView(localLinearLayout3);
      }
      i += 1;
    }
  }

  private String getAssetSizeLabel(int paramInt)
  {
    String str1;
    if (paramInt > 1048576)
    {
      this.instance.setMaximumFractionDigits(2);
      NumberFormat localNumberFormat1 = this.instance;
      double d = paramInt / 1048576.0F;
      str1 = String.valueOf(localNumberFormat1.format(d));
    }
    String str4;
    for (String str2 = str1 + "M"; ; str2 = str4 + "K")
    {
      String str3 = String.valueOf(getString(2131296272));
      return str3 + " " + str2;
      this.instance.setMaximumFractionDigits(0);
      NumberFormat localNumberFormat2 = this.instance;
      long l = paramInt / 1024;
      str4 = String.valueOf(localNumberFormat2.format(l));
    }
  }

  private LayoutInflater getInflater()
  {
    if (this.inflater == null)
    {
      LayoutInflater localLayoutInflater = getLayoutInflater();
      this.inflater = localLayoutInflater;
    }
    return this.inflater;
  }

  private void inflateNewsContent()
  {
    Request localRequest = new Request(0L, 65563);
    Object[] arrayOfObject = new Object[1];
    String str = this.mNewsURL;
    arrayOfObject[0] = str;
    localRequest.setData(arrayOfObject);
    NewsContentActivity.5 local5 = new NewsContentActivity.5(this, localRequest);
    localRequest.addObserver(local5);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getNewsContent(localRequest);
  }

  private void initHandlerInUIThread()
  {
    NewsContentActivity.3 local3 = new NewsContentActivity.3(this);
    this.mHandler = local3;
    NewsContentActivity.4 local4 = new NewsContentActivity.4(this);
    this.mDownloadStatusHandler = local4;
  }

  private LinearLayout newsContainer(int paramInt, NewsContent.SubNews paramSubNews)
  {
    if (this.inflater == null)
    {
      LayoutInflater localLayoutInflater = getLayoutInflater();
      this.inflater = localLayoutInflater;
    }
    LinearLayout localLinearLayout = (LinearLayout)getInflater().inflate(2130903085, null);
    if (!paramSubNews.subTitle.equals("null"))
    {
      String str = paramSubNews.subTitle;
      View localView1 = subTitle(paramInt, str);
      localLinearLayout.addView(localView1);
      int i = this.mSubContainerIndex + 1;
      this.mSubContainerIndex = i;
    }
    List localList = paramSubNews.blocksList;
    int j = 0;
    int k = localList.size();
    if (j >= k)
      return localLinearLayout;
    NewsContent.SubItem localSubItem = (NewsContent.SubItem)localList.get(j);
    if (localSubItem != null)
      switch (localSubItem.itemType)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
    while (true)
    {
      j += 1;
      break;
      NewsContent.SubText localSubText = (NewsContent.SubText)localSubItem;
      View localView2 = subText(localSubText);
      localLinearLayout.addView(localView2);
      continue;
      NewsContent.SubImage localSubImage1 = (NewsContent.SubImage)localSubItem;
      View localView3 = subImage(localSubImage1);
      localLinearLayout.addView(localView3);
      HashMap localHashMap1 = this.mViewMap;
      Integer localInteger1 = Integer.valueOf(this.mViewIndex);
      localHashMap1.put(localInteger1, localView3);
      NewsContent.SubImage localSubImage2 = (NewsContent.SubImage)localSubItem;
      addImageRequest(localSubImage2);
      int m = this.mViewIndex + 1;
      this.mViewIndex = m;
      continue;
      NewsContent.SubApp localSubApp1 = (NewsContent.SubApp)localSubItem;
      View localView4 = subApp(localSubApp1);
      localLinearLayout.addView(localView4);
      HashMap localHashMap2 = this.mViewMap;
      Integer localInteger2 = Integer.valueOf(this.mViewIndex);
      localHashMap2.put(localInteger2, localView4);
      NewsContent.SubApp localSubApp2 = (NewsContent.SubApp)localSubItem;
      addAppInfoRequest(localSubApp2);
      NewsContent.SubApp localSubApp3 = (NewsContent.SubApp)localSubItem;
      addAppThumbRequest(localSubApp3);
      int n = this.mViewIndex + 1;
      this.mViewIndex = n;
    }
  }

  private View newsTitle(NewsContent.NewsHeader paramNewsHeader)
  {
    View localView = getInflater().inflate(2130903089, null);
    TextView localTextView1 = (TextView)localView.findViewById(2131427489);
    String str1 = paramNewsHeader.title;
    localTextView1.setText(str1);
    ImageView localImageView = (ImageView)localView.findViewById(2131427488);
    if (paramNewsHeader.hot);
    for (int i = 0; ; i = 8)
    {
      localImageView.setVisibility(i);
      TextView localTextView2 = (TextView)localView.findViewById(2131427490);
      Resources localResources1 = getResources();
      Object[] arrayOfObject1 = new Object[1];
      String str2 = paramNewsHeader.date;
      arrayOfObject1[0] = str2;
      String str3 = localResources1.getString(2131296500, arrayOfObject1);
      localTextView2.setText(str3);
      TextView localTextView3 = (TextView)localView.findViewById(2131427491);
      Resources localResources2 = getResources();
      Object[] arrayOfObject2 = new Object[1];
      Integer localInteger = Integer.valueOf(paramNewsHeader.visit);
      arrayOfObject2[0] = localInteger;
      String str4 = localResources2.getString(2131296501, arrayOfObject2);
      localTextView3.setText(str4);
      return localView;
    }
  }

  private void refreshDownloadStatus()
  {
    Iterator localIterator = this.mDownloadMap.entrySet().iterator();
    if (localIterator == null)
      return;
    label18: AssetDetail localAssetDetail;
    TextView localTextView;
    File localFile;
    label126: int k;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localAssetDetail = (AssetDetail)localEntry.getKey();
      View localView = (View)localEntry.getValue();
      if ((localAssetDetail == null) || (localView == null))
        continue;
      localTextView = (TextView)localView.findViewById(2131427420);
      localFile = FileManager.getFile(this, localAssetDetail);
      if (localFile == null)
        break label239;
      localTextView.setText(2131296418);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837596);
      localTextView.getBackground().setCallback(null);
      String str = localAssetDetail.pkgName;
      int i = localAssetDetail.versionCode;
      int j = localAssetDetail._id;
      k = PackageInstallInfo.getPackageInstalledStatus(this, str, i, j);
      if (k != 1)
        break label276;
      localTextView.setText(2131296358);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837576);
      localTextView.getBackground().setCallback(null);
    }
    while (true)
    {
      int m = localAssetDetail._id;
      if (!checkAppDownloading(m))
        break label18;
      localTextView.setBackgroundResource(2130837576);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296412);
      break label18;
      break;
      label239: localTextView.setText(2131296361);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837577);
      localTextView.getBackground().setCallback(null);
      break label126;
      label276: if ((k != 2) || (localFile != null))
        continue;
      localTextView.setText(2131296362);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837596);
      localTextView.getBackground().setCallback(null);
    }
  }

  private void removeDownloadHandler()
  {
    Iterator localIterator = this.mDownloadMap.entrySet().iterator();
    if (localIterator == null);
    while (true)
    {
      return;
      while (localIterator.hasNext())
      {
        AssetDetail localAssetDetail = (AssetDetail)((Map.Entry)localIterator.next()).getKey();
        if (localAssetDetail == null)
          continue;
        DownloadService localDownloadService = DownloadService.getInstance(this);
        int i = localAssetDetail._id;
        localDownloadService.removeHandler(i);
      }
    }
  }

  private void setSubAppDownload(View paramView, AssetDetail paramAssetDetail)
  {
    View localView = paramView.findViewById(2131427527);
    ImageView localImageView = (ImageView)paramView.findViewById(2131427373);
    TextView localTextView1 = (TextView)paramView.findViewById(2131427417);
    String str1 = paramAssetDetail.title;
    localTextView1.setText(str1);
    TextView localTextView2 = (TextView)paramView.findViewById(2131427375);
    int i = paramAssetDetail.size;
    String str2 = getAssetSizeLabel(i);
    localTextView2.setText(str2);
    RatingBar localRatingBar = (RatingBar)paramView.findViewById(2131427377);
    float f = paramAssetDetail.rating;
    localRatingBar.setRating(f);
    TextView localTextView3 = (TextView)paramView.findViewById(2131427420);
    File localFile = FileManager.getFile(this, paramAssetDetail);
    int m;
    if (localFile != null)
    {
      localTextView3.setText(2131296418);
      localTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView3.setBackgroundResource(2130837596);
      localTextView3.getBackground().setCallback(null);
      String str3 = paramAssetDetail.pkgName;
      int j = paramAssetDetail.versionCode;
      int k = paramAssetDetail._id;
      m = PackageInstallInfo.getPackageInstalledStatus(this, str3, j, k);
      paramAssetDetail.installed = m;
      if (m != 1)
        break label357;
      localTextView3.setText(2131296358);
      localTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView3.setBackgroundResource(2130837576);
      localTextView3.getBackground().setCallback(null);
    }
    while (true)
    {
      int n = paramAssetDetail._id;
      if (checkAppDownloading(n))
      {
        localTextView3.setBackgroundResource(2130837576);
        localTextView3.getBackground().setCallback(null);
        localTextView3.setText(2131296412);
      }
      localTextView3.setClickable(1);
      NewsContentActivity.1 local1 = new NewsContentActivity.1(this, paramAssetDetail);
      localTextView3.setOnClickListener(local1);
      NewsContentActivity.2 local2 = new NewsContentActivity.2(this, paramAssetDetail);
      paramView.setOnClickListener(local2);
      return;
      localTextView3.setText(2131296361);
      localTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView3.setBackgroundResource(2130837577);
      localTextView3.getBackground().setCallback(null);
      break;
      label357: if ((m != 2) || (localFile != null))
        continue;
      localTextView3.setText(2131296362);
      localTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView3.setBackgroundResource(2130837596);
      localTextView3.getBackground().setCallback(null);
    }
  }

  private void setupViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
    this.mProgressIndicator.setVisibility(0);
    View localView2 = findViewById(2131427481);
    this.mScroll = localView2;
    this.mScroll.setVisibility(8);
    if (DeviceUtil.getSDKVersionInt() > 4);
    try
    {
      Class localClass1 = this.mScroll.getClass();
      Class[] arrayOfClass = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass[0] = localClass2;
      Method localMethod = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass);
      View localView3 = this.mScroll;
      Object[] arrayOfObject = new Object[1];
      Boolean localBoolean = Boolean.valueOf(1);
      arrayOfObject[0] = localBoolean;
      localMethod.invoke(localView3, arrayOfObject);
      View localView4 = findViewById(2131427408);
      this.mEmptyView = localView4;
      this.mEmptyView.setVisibility(8);
      LinearLayout localLinearLayout = (LinearLayout)findViewById(2131427482);
      this.mNews = localLinearLayout;
      ((ImageButton)findViewById(2131427390)).setOnClickListener(this);
      ((Button)findViewById(2131427485)).setOnClickListener(this);
      ((Button)findViewById(2131427484)).setOnClickListener(this);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
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

  private View subApp(NewsContent.SubApp paramSubApp)
  {
    View localView = getInflater().inflate(2130903109, null);
    StringBuilder localStringBuilder = new StringBuilder("app");
    int i = paramSubApp.applicationId;
    String str = i;
    localView.setTag(str);
    return localView;
  }

  private View subImage(NewsContent.SubImage paramSubImage)
  {
    return getInflater().inflate(2130903110, null);
  }

  private View subText(NewsContent.SubText paramSubText)
  {
    View localView = getInflater().inflate(2130903108, null);
    TextView localTextView = (TextView)localView.findViewById(2131427526);
    String str = paramSubText.text;
    localTextView.setText(str);
    return localView;
  }

  private View subTitle(int paramInt, String paramString)
  {
    View localView = getInflater().inflate(2130903111, null);
    TextView localTextView = (TextView)localView.findViewById(2131427531);
    String str1 = String.valueOf(paramInt);
    String str2 = str1;
    localTextView.setText(str2);
    ((TextView)localView.findViewById(2131427532)).setText(paramString);
    return localView;
  }

  public void addDownloadLogRequest(int paramInt)
  {
    HashMap localHashMap1 = this.mInstallUpdateMap;
    Integer localInteger1 = Integer.valueOf(paramInt);
    String str = (String)localHashMap1.get(localInteger1);
    if (str == null);
    while (true)
    {
      return;
      HashMap localHashMap2 = this.mInstallUpdateMap;
      Integer localInteger2 = Integer.valueOf(paramInt);
      localHashMap2.remove(localInteger2);
      Request localRequest = new Request(0L, 65553);
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str;
      arrayOfObject[1] = "Zhuanlan";
      Integer localInteger3 = Integer.valueOf(paramInt);
      arrayOfObject[2] = localInteger3;
      localRequest.setData(arrayOfObject);
      MarketService.getServiceInstance(this).getInstallUpdateLog(localRequest);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131427390:
    case 2131427484:
    case 2131427485:
    }
    while (true)
    {
      return;
      finish();
      continue;
      String str1 = NewsListActivity.getLastURL(this.mNewsURL);
      if (str1 == null)
        continue;
      Intent localIntent1 = new Intent();
      String str2 = NewsContentActivity.class.getName();
      localIntent1.setClassName(this, str2);
      localIntent1.putExtra("url", str1);
      startActivity(localIntent1);
      finish();
      continue;
      str1 = NewsListActivity.getNextURL(this.mNewsURL);
      if (str1 == null)
        continue;
      Intent localIntent2 = new Intent();
      String str3 = NewsContentActivity.class.getName();
      localIntent2.setClassName(this, str3);
      localIntent2.putExtra("url", str1);
      startActivity(localIntent2);
      finish();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    setTheme(16973834);
    String str1;
    if (paramBundle == null)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
        str1 = localBundle.getString("url");
    }
    String str2;
    for (this.mNewsURL = str1; ; this.mNewsURL = str2)
    {
      setContentView(2130903086);
      initHandlerInUIThread();
      Handler localHandler = WifiDownloadManager.initHandlerInUIThread(this);
      this.mWifiHandler = localHandler;
      setupViews();
      inflateNewsContent();
      return;
      str1 = null;
      break;
      str2 = paramBundle.getString("url");
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
    NewsContentActivity.10 local10;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local10).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      NewsContentActivity.9 local9 = new NewsContentActivity.9(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local9);
      local10 = new NewsContentActivity.10(this);
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
    this.mViewMap.clear();
    this.mDownloadMap.clear();
    this.mInstallUpdateMap.clear();
    onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }

  public void onPause()
  {
    if (isFinishing())
    {
      removeDownloadHandler();
      MarketService localMarketService = (MarketService)this.mMarketService;
      int i = MarketService.THREAD_THUMB;
      localMarketService.clearPendingRequest(i);
    }
    onPause();
    MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    refreshDownloadStatus();
    addPageViewLogRequest();
    onResume();
    MobclickAgent.onResume(this);
    String str = this.mNewsURL;
    MobclickAgent.onEvent(this, "news_content", str);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    onSaveInstanceState(paramBundle);
    String str = this.mNewsURL;
    paramBundle.putString("url", str);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity
 * JD-Core Version:    0.6.0
 */