package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AssetInfoDetailActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_APP_DETAILS = 0;
  private static final int ACTION_APP_SCREEN_SHORT = 2;
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private NumberFormat instance;
  private final BroadcastReceiver mApplicationsReceiver;
  private AssetDetail mAssetDetail;
  private View mAssetDetailView;
  private int mAssetId;
  private Request mCurrentRequest;
  private View mGalleryContainer;
  private View mGalleryLoadingIndicator;
  private Handler mHandler;
  private View mLoadingIndicator;
  private IMarketService mMarketService;
  private ScreenImageAdapter mScreenAdapter;
  private Gallery mScreenGallery;

  @Signature({"Ljava/util/ArrayList", "<", "Landroid/graphics/drawable/Drawable;", ">;"})
  private ArrayList mScreenShorts;

  public AssetInfoDetailActivity()
  {
    AssetInfoDetailActivity.1 local1 = new AssetInfoDetailActivity.1(this);
    this.mApplicationsReceiver = local1;
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    this.instance = localNumberFormat;
  }

  private void findMoreApplication()
  {
    String str1 = this.mAssetDetail.devLogin;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    Uri localUri = Uri.parse("market://market.iworks.com/search?q=pub:" + str1);
    localIntent.setData(localUri);
    String str2 = this.mAssetDetail.author;
    localIntent.putExtra("title", str2);
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      while (true)
        localActivityNotFoundException.printStackTrace();
    }
  }

  private String getAssetDescription()
  {
    if ((this.mAssetDetail != null) && (this.mAssetDetail.description != null) && (this.mAssetDetail.description.trim().length() > 0));
    for (String str = this.mAssetDetail.description; ; str = getString(2131296350))
      return str;
  }

  private String getAssetDownloadsInfo()
  {
    StringBuilder localStringBuilder;
    int i;
    if (this.mAssetDetail != null)
    {
      String str1 = String.valueOf(getString(2131296316));
      localStringBuilder = new StringBuilder(str1);
      i = this.mAssetDetail.downloadsNumber;
    }
    for (String str2 = i; ; str2 = null)
      return str2;
  }

  private String getAssetLastUpdateLabel()
  {
    StringBuilder localStringBuilder;
    String str2;
    if (this.mAssetDetail != null)
    {
      String str1 = String.valueOf(getString(2131296274));
      localStringBuilder = new StringBuilder(str1);
      str2 = this.mAssetDetail.lastUpdate;
    }
    for (String str3 = str2; ; str3 = null)
      return str3;
  }

  private String getAssetRatingsInfo()
  {
    StringBuilder localStringBuilder;
    int i;
    if (this.mAssetDetail != null)
    {
      String str1 = String.valueOf(getString(2131296318));
      localStringBuilder = new StringBuilder(str1);
      i = this.mAssetDetail.ratingNumbers;
    }
    for (String str2 = i; ; str2 = null)
      return str2;
  }

  private String getAssetSizeLabel()
  {
    int i;
    String str2;
    String str3;
    if (this.mAssetDetail != null)
    {
      i = this.mAssetDetail.size;
      if (i > 1232896)
      {
        this.instance.setMaximumFractionDigits(2);
        NumberFormat localNumberFormat1 = this.instance;
        double d = i / 1048576.0F;
        String str1 = String.valueOf(localNumberFormat1.format(d));
        str2 = str1 + "M";
        str3 = String.valueOf(getString(2131296272));
      }
    }
    for (String str4 = str3 + " " + str2; ; str4 = null)
    {
      return str4;
      this.instance.setMaximumFractionDigits(0);
      NumberFormat localNumberFormat2 = this.instance;
      long l = i / 1024;
      String str5 = String.valueOf(localNumberFormat2.format(l));
      str2 = str5 + "K";
      break;
    }
  }

  private String getAssetVersionLabel()
  {
    StringBuilder localStringBuilder;
    String str2;
    if (this.mAssetDetail != null)
    {
      String str1 = String.valueOf(getString(2131296273));
      localStringBuilder = new StringBuilder(str1).append(" ");
      str2 = this.mAssetDetail.version;
    }
    for (String str3 = str2; ; str3 = null)
      return str3;
  }

  private String getDevMail()
  {
    if (this.mAssetDetail != null);
    for (String str = this.mAssetDetail.email; ; str = null)
      return str;
  }

  private void initHandlerInUIThread()
  {
    AssetInfoDetailActivity.6 local6 = new AssetInfoDetailActivity.6(this);
    this.mHandler = local6;
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter = new IntentFilter("send_asset_detail");
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver, localIntentFilter);
  }

  private void retrieveScreenShort()
  {
    Request localRequest = new Request(0L, 65545);
    Integer localInteger = Integer.valueOf(this.mAssetId);
    localRequest.setData(localInteger);
    AssetInfoDetailActivity.5 local5 = new AssetInfoDetailActivity.5(this, localRequest);
    localRequest.addObserver(local5);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getAppScreenShorts(localRequest);
  }

  private void sendMail(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      Toast.makeText(this, 2131296387, 1).show();
    while (true)
    {
      return;
      Intent localIntent1 = new Intent();
      localIntent1.setAction("android.intent.action.SEND");
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramString;
      localIntent1.putExtra("android.intent.extra.EMAIL", arrayOfString);
      String str1 = this.mAssetDetail.title;
      localIntent1.putExtra("android.intent.extra.SUBJECT", str1);
      localIntent1.putExtra("android.intent.extra.TEXT", "");
      localIntent1.setType("message/rfc882");
      int i = 2131296302;
      try
      {
        String str2 = getString(i);
        Intent localIntent2 = Intent.createChooser(localIntent1, str2);
        startActivity(localIntent2);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        localActivityNotFoundException.printStackTrace();
      }
    }
  }

  private void setupAppInfo()
  {
    ImageView localImageView = (ImageView)findViewById(2131427373);
    int i = this.mAssetId;
    Drawable localDrawable = CachedThumbnails.lookupThumbnail(this, i);
    if (localDrawable != null)
    {
      localImageView.setImageDrawable(localDrawable);
      localDrawable.setCallback(null);
    }
    TextView localTextView1 = (TextView)findViewById(2131427372);
    String str1 = this.mAssetDetail.title;
    localTextView1.setText(str1);
    TextView localTextView2 = (TextView)findViewById(2131427376);
    String str2 = getAssetVersionLabel();
    localTextView2.setText(str2);
    TextView localTextView3 = (TextView)findViewById(2131427378);
    String str3 = getAssetLastUpdateLabel();
    localTextView3.setText(str3);
    TextView localTextView4 = (TextView)findViewById(2131427375);
    String str4 = getAssetSizeLabel();
    localTextView4.setText(str4);
    RatingBar localRatingBar = (RatingBar)findViewById(2131427377);
    float f = this.mAssetDetail.rating;
    localRatingBar.setRating(f);
    TextView localTextView5 = (TextView)findViewById(2131427379);
    String str5 = getAssetDownloadsInfo();
    localTextView5.setText(str5);
    String str6 = getAssetRatingsInfo();
    ((TextView)findViewById(2131427380)).setText(str6);
    TextView localTextView6 = (TextView)findViewById(2131427385);
    String str7 = getAssetDescription();
    localTextView6.setText(str7);
  }

  private void setupFooterButton()
  {
    ((Button)findViewById(2131427388)).setOnClickListener(this);
    ((Button)findViewById(2131427386)).setOnClickListener(this);
    ((Button)findViewById(2131427389)).setOnClickListener(this);
  }

  private void setupList()
  {
    setupAppInfo();
    retrieveScreenShort();
  }

  private void setupPriceText(TextView paramTextView)
  {
    float f = this.mAssetDetail.price;
    int i = this.mAssetDetail.installed;
    String str;
    if (i == 1)
    {
      paramTextView.setCompoundDrawables(null, null, null, null);
      str = getString(2131296358);
    }
    while (true)
    {
      paramTextView.setText(str);
      return;
      if (i == 2)
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

  private void setupViews()
  {
    View localView1 = findViewById(2131427468);
    this.mLoadingIndicator = localView1;
    this.mLoadingIndicator.setVisibility(0);
    View localView2 = findViewById(2131427384);
    this.mAssetDetailView = localView2;
    this.mAssetDetailView.setVisibility(8);
    Gallery localGallery1 = (Gallery)findViewById(2131427400);
    this.mScreenGallery = localGallery1;
    TextView localTextView = (TextView)findViewById(2131427403);
    Gallery localGallery2 = this.mScreenGallery;
    AssetInfoDetailActivity.4 local4 = new AssetInfoDetailActivity.4(this, localTextView);
    localGallery2.setOnItemSelectedListener(local4);
    View localView3 = findViewById(2131427401);
    this.mScreenGallery.setEmptyView(localView3);
    View localView4 = findViewById(2131427404);
    this.mGalleryLoadingIndicator = localView4;
    View localView5 = findViewById(2131427399);
    this.mGalleryContainer = localView5;
    setupFooterButton();
    startLoadDetail();
  }

  private void startLoadDetail()
  {
    this.mGalleryLoadingIndicator.setVisibility(0);
    this.mGalleryContainer.setVisibility(8);
  }

  private void unregisterIntentReceivers()
  {
    try
    {
      BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
      unregisterReceiver(localBroadcastReceiver);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131427387:
    default:
    case 2131427388:
    case 2131427389:
    case 2131427386:
    }
    while (true)
    {
      return;
      findMoreApplication();
      continue;
      String str = getDevMail();
      sendMail(str);
      continue;
      Intent localIntent = new Intent();
      localIntent.setClass(this, ReportErrorActivity.class);
      int i = this.mAssetId;
      localIntent.putExtra("_id", i);
      startActivity(localIntent);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    int i = getIntent().getIntExtra("_id", 0);
    this.mAssetId = i;
    setContentView(2130903048);
    View localView;
    if (Integer.parseInt(Build.VERSION.SDK) > 4)
      localView = findViewById(2131427384);
    try
    {
      Class localClass1 = localView.getClass();
      Class[] arrayOfClass = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass[0] = localClass2;
      Method localMethod = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      Boolean localBoolean = Boolean.valueOf(1);
      arrayOfObject[0] = localBoolean;
      localMethod.invoke(localView, arrayOfObject);
      setupViews();
      initHandlerInUIThread();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
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
    AssetInfoDetailActivity.3 local3;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local3).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      AssetInfoDetailActivity.2 local2 = new AssetInfoDetailActivity.2(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local2);
      local3 = new AssetInfoDetailActivity.3(this);
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
    int i;
    int j;
    if (this.mScreenShorts != null)
    {
      i = this.mScreenShorts.size();
      j = 0;
    }
    while (true)
    {
      if (j >= i)
      {
        unregisterIntentReceivers();
        return;
      }
      Drawable localDrawable = (Drawable)this.mScreenShorts.get(j);
      BitmapDrawable localBitmapDrawable;
      if ((localDrawable instanceof BitmapDrawable))
        localBitmapDrawable = (BitmapDrawable)localDrawable;
      try
      {
        localBitmapDrawable.getBitmap().recycle();
        label64: j += 1;
      }
      catch (Throwable localThrowable)
      {
        break label64;
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
    registerIntentReceivers();
  }

  class ScreenImageAdapter extends BaseAdapter
  {
    private LayoutInflater mInflator;

    public ScreenImageAdapter(Context arg2)
    {
      Context localContext;
      LayoutInflater localLayoutInflater = LayoutInflater.from(localContext);
      this.mInflator = localLayoutInflater;
    }

    public int getCount()
    {
      if (AssetInfoDetailActivity.this.mScreenShorts == null);
      for (int i = 0; ; i = AssetInfoDetailActivity.this.mScreenShorts.size())
        return i;
    }

    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView != null);
      for (ImageView localImageView = (ImageView)paramView; ; localImageView = (ImageView)this.mInflator.inflate(2130903054, null))
      {
        Drawable localDrawable = (Drawable)AssetInfoDetailActivity.this.mScreenShorts.get(paramInt);
        localImageView.setImageDrawable(localDrawable);
        ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
        localImageView.setScaleType(localScaleType);
        Gallery.LayoutParams localLayoutParams = new Gallery.LayoutParams(-1, -1);
        localImageView.setLayoutParams(localLayoutParams);
        return localImageView;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoDetailActivity
 * JD-Core Version:    0.6.0
 */