package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.DownloadService;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Signature({"Landroid/app/Activity;", "Landroid/view/View$OnClickListener;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class PreinstallListActivity extends Activity
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 1;
  private static final int ACTION_LIST_APPS = 0;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int ACTIVITY_APP_DETAIL = 100;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final int MAX_APP_COUNT_PER_TIME = 30;
  private int category_id;
  private String category_label;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  private final BroadcastReceiver mApplicationsReceiver;
  private boolean mBusy;
  private Request mCurrentRequest;
  private View mFooterView;
  private Handler mHandler;
  private TextView mHeaderSelectIndicator;
  private Button mInstallButton;
  private PreinstallAppListAdapter mListAdapter;
  private ListView mListView;
  private IMarketService mMarketService;
  private View mProgressIndicator;
  private boolean mReachEnd;
  private Button mSelectAllButton;
  private Button mSelectNoneButton;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/lang/Integer;", ">;"})
  private HashMap mStatusMap;
  private View mToolbar;
  private int mTotalBytesNeeded;
  private Handler mWifiHandler;
  private int oldStartIndex;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;

  public PreinstallListActivity()
  {
    HashMap localHashMap = new HashMap();
    this.mStatusMap = localHashMap;
    this.mTotalBytesNeeded = 0;
    PreinstallListActivity.1 local1 = new PreinstallListActivity.1(this);
    this.mApplicationsReceiver = local1;
    PreinstallListActivity.2 local2 = new PreinstallListActivity.2(this);
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
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = "Preinstall";
    Integer localInteger1 = Integer.valueOf(this.category_id);
    arrayOfObject[1] = localInteger1;
    Integer localInteger2 = Integer.valueOf(55537);
    arrayOfObject[2] = localInteger2;
    Integer localInteger3 = Integer.valueOf(55537);
    arrayOfObject[3] = localInteger3;
    Integer localInteger4 = Integer.valueOf(55537);
    arrayOfObject[4] = localInteger4;
    String str = this.category_label;
    arrayOfObject[5] = str;
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    PreinstallListActivity.6 local6 = new PreinstallListActivity.6(this, localRequest);
    localRequest.addObserver(local6);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getAppIcon(localRequest);
  }

  private boolean checkAppDownloading(int paramInt)
  {
    return DownloadService.getInstance(this).isTaskRunning(paramInt);
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

  private void downloadAndInstallApps()
  {
    if (!itemChecked())
    {
      PreinstallListActivity localPreinstallListActivity1 = this;
      int i = 2131296401;
      int j = 1;
      Toast.makeText(localPreinstallListActivity1, i, j).show();
    }
    while (true)
    {
      return;
      int k = this.mStatusMap.size();
      int m = 30;
      if (k <= m)
        break;
      PreinstallListActivity localPreinstallListActivity2 = this;
      int n = 2131296285;
      int i1 = 1;
      Toast.makeText(localPreinstallListActivity2, n, i1).show();
    }
    Iterator localIterator = this.mStatusMap.keySet().iterator();
    label85: int i3;
    Request localRequest6;
    while (localIterator.hasNext())
    {
      int i2 = ((Integer)localIterator.next()).intValue();
      HashMap localHashMap = this.mStatusMap;
      Integer localInteger1 = Integer.valueOf(i2);
      i3 = ((Integer)localHashMap.get(localInteger1)).intValue();
      PreinstallAppListAdapter localPreinstallAppListAdapter = this.mListAdapter;
      int i4 = i2;
      Asset localAsset = (Asset)localPreinstallAppListAdapter.getItem(i4);
      int i5 = localAsset.size;
      String str1 = localAsset.pkgName;
      String str2 = localAsset.title;
      PreinstallListActivity localPreinstallListActivity3 = this;
      int i6 = i3;
      if (localPreinstallListActivity3.checkAppDownloading(i6))
      {
        String str3 = getResources().getString(2131296352);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = str2;
        String str4 = str3;
        Object[] arrayOfObject2 = arrayOfObject1;
        String str5 = String.format(str4, arrayOfObject2);
        PreinstallListActivity localPreinstallListActivity4 = this;
        String str6 = str5;
        int i7 = 1;
        Toast.makeText(localPreinstallListActivity4, str6, i7).show();
        continue;
      }
      Request localRequest1 = new com/yingyonghui/market/online/Request;
      Request localRequest2 = localRequest1;
      long l1 = 0L;
      int i8 = 65541;
      localRequest2.<init>(l1, i8);
      Object[] arrayOfObject3 = new Object[4];
      Integer localInteger2 = Integer.valueOf(i3);
      arrayOfObject3[0] = localInteger2;
      Integer localInteger3 = Integer.valueOf(i5);
      arrayOfObject3[1] = localInteger3;
      arrayOfObject3[2] = str1;
      arrayOfObject3[3] = str2;
      Request localRequest3 = localRequest1;
      Object[] arrayOfObject4 = arrayOfObject3;
      localRequest3.setData(arrayOfObject4);
      Handler localHandler1 = this.mWifiHandler;
      Request localRequest4 = localRequest1;
      Handler localHandler2 = localHandler1;
      localRequest4.addWifiObserver(localHandler2);
      IMarketService localIMarketService = this.mMarketService;
      Request localRequest5 = localRequest1;
      localIMarketService.getAppContentStream(localRequest5);
      localRequest6 = new com/yingyonghui/market/online/Request;
      Request localRequest7 = localRequest6;
      long l2 = 0L;
      int i9 = 65553;
      localRequest7.<init>(l2, i9);
      int i10 = localAsset.installed;
      int i11 = 2;
      if (i10 != i11)
        break label562;
    }
    label562: for (String str7 = "update"; ; str7 = "install")
    {
      Object[] arrayOfObject5 = new Object[7];
      arrayOfObject5[0] = str7;
      arrayOfObject5[1] = "Preinstall";
      Integer localInteger4 = Integer.valueOf(this.category_id);
      arrayOfObject5[2] = localInteger4;
      Integer localInteger5 = Integer.valueOf(55537);
      arrayOfObject5[3] = localInteger5;
      Integer localInteger6 = Integer.valueOf(55537);
      arrayOfObject5[4] = localInteger6;
      Integer localInteger7 = Integer.valueOf(i3);
      arrayOfObject5[5] = localInteger7;
      String str8 = this.category_label;
      arrayOfObject5[6] = str8;
      Request localRequest8 = localRequest6;
      Object[] arrayOfObject6 = arrayOfObject5;
      localRequest8.setData(arrayOfObject6);
      MarketService localMarketService = MarketService.getServiceInstance(this);
      Request localRequest9 = localRequest6;
      localMarketService.getInstallUpdateLog(localRequest9);
      break label85;
      break;
    }
  }

  private String formatSize(int paramInt)
  {
    NumberFormat localNumberFormat = NumberFormat.getInstance();
    localNumberFormat.setMinimumFractionDigits(2);
    double d = paramInt / 1048576.0F;
    return localNumberFormat.format(d);
  }

  private void inflateAppList()
  {
    if (!this.mReachEnd)
    {
      int i = this.oldStartIndex;
      int j = this.startIndex;
      if (i != j)
        break label23;
    }
    while (true)
    {
      return;
      label23: Request localRequest = new Request(0L, 65538);
      Object[] arrayOfObject = new Object[4];
      Integer localInteger1 = Integer.valueOf(this.category_id);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(this.startIndex);
      arrayOfObject[1] = localInteger2;
      Integer localInteger3 = Integer.valueOf(10);
      arrayOfObject[2] = localInteger3;
      Integer localInteger4 = Integer.valueOf(2);
      arrayOfObject[3] = localInteger4;
      localRequest.setData(arrayOfObject);
      PreinstallListActivity.8 local8 = new PreinstallListActivity.8(this, localRequest);
      localRequest.addObserver(local8);
      this.mCurrentRequest = localRequest;
      int k = this.startIndex;
      this.oldStartIndex = k;
      this.mMarketService.getAppList(localRequest);
    }
  }

  private void initHandlerInUIThread()
  {
    PreinstallListActivity.7 local7 = new PreinstallListActivity.7(this);
    this.mHandler = local7;
  }

  private boolean isAppInstalled(int paramInt)
  {
    int i = this.mListAdapter.getCount();
    int j = 0;
    while (true)
    {
      int k;
      if (j >= i)
        k = 0;
      while (true)
      {
        return k;
        Asset localAsset = (Asset)this.mListAdapter.getItem(j);
        if (localAsset._id != paramInt)
          break;
        if (localAsset.installed == 1)
        {
          k = 1;
          continue;
        }
        k = 0;
      }
      j += 1;
    }
  }

  private boolean itemChecked()
  {
    if (this.mStatusMap.size() > 0);
    for (int i = 1; ; i = 0)
      return i;
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

  private void selectItem(int paramInt, boolean paramBoolean)
  {
    if (paramInt >= 0)
    {
      int i = this.mListAdapter.getCount();
      if (paramInt < i)
      {
        if (!paramBoolean)
          break label69;
        HashMap localHashMap1 = this.mStatusMap;
        Integer localInteger1 = Integer.valueOf(paramInt);
        Integer localInteger2 = Integer.valueOf((int)this.mListAdapter.getItemId(paramInt));
        localHashMap1.put(localInteger1, localInteger2);
      }
    }
    while (true)
    {
      this.mListAdapter.notifyDataSetChanged();
      setIndicator();
      return;
      label69: HashMap localHashMap2 = this.mStatusMap;
      Integer localInteger3 = Integer.valueOf(paramInt);
      if (!localHashMap2.containsKey(localInteger3))
        continue;
      HashMap localHashMap3 = this.mStatusMap;
      Integer localInteger4 = Integer.valueOf(paramInt);
      localHashMap3.remove(localInteger4);
    }
  }

  private void setIndicator()
  {
    int i = this.mStatusMap.size();
    if (i > 0)
    {
      String str1 = String.valueOf("");
      StringBuilder localStringBuilder1 = new StringBuilder(str1);
      String str2 = getString(2131296283);
      String str3 = String.valueOf(str2);
      String str4 = String.valueOf(str3 + " " + i);
      StringBuilder localStringBuilder2 = new StringBuilder(str4).append(" ");
      String str5 = getString(2131296284);
      String str6 = str5;
      this.mHeaderSelectIndicator.setText(str6);
      this.mHeaderSelectIndicator.setVisibility(0);
    }
    while (true)
    {
      return;
      this.mHeaderSelectIndicator.setText("");
      this.mHeaderSelectIndicator.setVisibility(8);
    }
  }

  private void setupViews()
  {
    View localView1 = findViewById(2131427390);
    PreinstallListActivity.3 local3 = new PreinstallListActivity.3(this);
    localView1.setOnClickListener(local3);
    TextView localTextView1 = (TextView)findViewById(2131427392);
    String str = this.category_label;
    localTextView1.setText(str);
    TextView localTextView2 = (TextView)findViewById(2131427395);
    this.mHeaderSelectIndicator = localTextView2;
    View localView2 = findViewById(2131427351);
    this.mToolbar = localView2;
    Button localButton1 = (Button)this.mToolbar.findViewById(2131427492);
    this.mSelectAllButton = localButton1;
    this.mSelectAllButton.setVisibility(8);
    Button localButton2 = (Button)this.mToolbar.findViewById(2131427493);
    this.mSelectNoneButton = localButton2;
    Button localButton3 = (Button)this.mToolbar.findViewById(2131427359);
    this.mInstallButton = localButton3;
    this.mSelectAllButton.setOnClickListener(this);
    this.mSelectNoneButton.setOnClickListener(this);
    this.mInstallButton.setOnClickListener(this);
    View localView3 = findViewById(2131427468);
    this.mProgressIndicator = localView3;
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
      ListView localListView3 = this.mListView;
      View localView4 = findViewById(2131427408);
      localListView3.setEmptyView(localView4);
      TextView localTextView3 = (TextView)findViewById(2131427409);
      PreinstallListActivity.4 local4 = new PreinstallListActivity.4(this);
      localTextView3.setOnClickListener(local4);
      ListView localListView4 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView4.setOnScrollListener(localOnScrollListener);
      View localView5 = LayoutInflater.from(this).inflate(2130903056, null);
      this.mFooterView = localView5;
      Button localButton4 = (Button)this.mFooterView.findViewById(2131427414);
      PreinstallListActivity.5 local5 = new PreinstallListActivity.5(this);
      localButton4.setOnClickListener(local5);
      ListView localListView5 = this.mListView;
      View localView6 = this.mFooterView;
      localListView5.addFooterView(localView6);
      this.mListView.setOnItemClickListener(this);
      this.mListView.setSelector(17170445);
      inflateAppList();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
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

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 != 100) || (paramInt2 != -1) || (paramIntent == null));
    while (true)
    {
      return;
      Bundle localBundle = paramIntent.getExtras();
      int i = localBundle.getInt("position", -1);
      if (i == -1)
        continue;
      int j = (int)this.mListAdapter.getItemId(i);
      if (isAppInstalled(j))
        continue;
      boolean bool = localBundle.getBoolean("select", 0);
      selectItem(i, bool);
    }
  }

  public void onClick(View paramView)
  {
    Button localButton1 = this.mSelectAllButton;
    int j;
    if (paramView == localButton1)
    {
      int i = this.mListAdapter.getCount();
      j = 0;
      if (j >= i)
      {
        setIndicator();
        this.mSelectAllButton.setVisibility(8);
        this.mSelectNoneButton.setVisibility(0);
      }
    }
    while (true)
    {
      return;
      if (((Asset)this.mListAdapter.getItem(j)).installed != 1)
      {
        HashMap localHashMap = this.mStatusMap;
        Integer localInteger1 = Integer.valueOf(j);
        Integer localInteger2 = Integer.valueOf((int)this.mListAdapter.getItemId(j));
        localHashMap.put(localInteger1, localInteger2);
        this.mListAdapter.notifyDataSetChanged();
      }
      j += 1;
      break;
      Button localButton2 = this.mSelectNoneButton;
      if (paramView == localButton2)
      {
        if (this.mStatusMap.size() > 0)
        {
          this.mStatusMap.clear();
          this.mListAdapter.notifyDataSetChanged();
        }
        this.mSelectNoneButton.setVisibility(8);
        this.mSelectAllButton.setVisibility(0);
        this.mHeaderSelectIndicator.setText("");
        this.mHeaderSelectIndicator.setVisibility(8);
        continue;
      }
      Button localButton3 = this.mInstallButton;
      if (paramView != localButton3)
        continue;
      downloadAndInstallApps();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    getWindow().setFormat(1);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    String str1;
    if (paramBundle == null)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
      {
        int i = localBundle.getInt("_id", 0);
        this.category_id = i;
        if (localBundle == null)
          break label126;
        str1 = localBundle.getString("label");
      }
    }
    label68: label126: String str3;
    for (this.category_label = str1; ; this.category_label = str3)
    {
      requestWindowFeature(1);
      setTheme(16973834);
      setContentView(2130903091);
      Handler localHandler = WifiDownloadManager.initHandlerInUIThread(this);
      this.mWifiHandler = localHandler;
      initHandlerInUIThread();
      setupViews();
      registerIntentReceivers();
      return;
      int j = -1;
      break;
      String str2 = "";
      break label68;
      int k = paramBundle.getInt("_id");
      this.category_id = k;
      str3 = paramBundle.getString("label");
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
    PreinstallListActivity.10 local10;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local10).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      PreinstallListActivity.9 local9 = new PreinstallListActivity.9(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local9);
      local10 = new PreinstallListActivity.10(this);
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
    localIntent.putExtra("preinstall", 1);
    Asset localAsset = (Asset)paramAdapterView.getItemAtPosition(paramInt);
    if (localAsset != null)
    {
      int i = localAsset._id;
      localIntent.putExtra("_id", i);
      localIntent.putExtra("position", paramInt);
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
      StringBuilder localStringBuilder = new StringBuilder("Preinstall");
      int n = this.category_id;
      String str5 = n;
      localIntent.putExtra("from", str5);
      startActivityForResult(localIntent, 100);
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
    MobclickAgent.onPause(this);
  }

  public void onResume()
  {
    onResume();
    MobclickAgent.onResume(this);
    if (this.mListAdapter != null)
      this.mListAdapter.notifyDataSetChanged();
    addPageViewLogRequest();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = this.category_id;
    paramBundle.putInt("_id", i);
    String str = this.category_label;
    paramBundle.putString("label", str);
    onSaveInstanceState(paramBundle);
  }

  @Signature({"Landroid/widget/ArrayAdapter", "<", "Lcom/yingyonghui/market/model/Asset;", ">;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;"})
  public class PreinstallAppListAdapter extends ArrayAdapter
    implements CompoundButton.OnCheckedChangeListener
  {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    @Signature({"(", "Landroid/content/Context;", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;)V"})
    public PreinstallAppListAdapter(Context paramArrayList, ArrayList arg3)
    {
      super(0, localList);
      LayoutInflater localLayoutInflater = (LayoutInflater)paramArrayList.getSystemService("layout_inflater");
      this.mLayoutInflater = localLayoutInflater;
      this.mContext = paramArrayList;
    }

    public long getItemId(int paramInt)
    {
      return ((Asset)getItem(paramInt))._id;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      PreinstallListActivity.ViewHolder localViewHolder;
      label166: Asset localAsset;
      if (paramView == null)
      {
        paramView = this.mLayoutInflater.inflate(2130903059, paramViewGroup, 0);
        localViewHolder = new PreinstallListActivity.ViewHolder();
        LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427421);
        localViewHolder.snippet = localLinearLayout;
        ImageView localImageView1 = (ImageView)paramView.findViewById(2131427373);
        localViewHolder.thumbnail = localImageView1;
        TextView localTextView1 = (TextView)paramView.findViewById(2131427417);
        localViewHolder.title = localTextView1;
        TextView localTextView2 = (TextView)paramView.findViewById(2131427423);
        localViewHolder.installed = localTextView2;
        TextView localTextView3 = (TextView)paramView.findViewById(2131427395);
        localViewHolder.description = localTextView3;
        CheckBox localCheckBox1 = (CheckBox)paramView.findViewById(2131427422);
        localViewHolder.checked = localCheckBox1;
        localViewHolder.checked.setOnCheckedChangeListener(this);
        paramView.setTag(localViewHolder);
        if (paramInt % 2 != 1)
          break label455;
        localViewHolder.snippet.setBackgroundResource(2130837594);
        localAsset = (Asset)getItem(paramInt);
        Context localContext = this.mContext;
        String str1 = localAsset.pkgName;
        int i = localAsset.versionCode;
        int j = localAsset._id;
        int k = PackageInstallInfo.getPackageInstalledStatus(localContext, str1, i, j);
        localAsset.installed = k;
        if (localAsset.installed != 1)
          break label467;
        localViewHolder.installed.setText(2131296358);
        localViewHolder.installed.setVisibility(0);
        HashMap localHashMap1 = PreinstallListActivity.this.mStatusMap;
        Integer localInteger1 = Integer.valueOf(paramInt);
        if (localHashMap1.containsKey(localInteger1))
        {
          HashMap localHashMap2 = PreinstallListActivity.this.mStatusMap;
          Integer localInteger2 = Integer.valueOf(paramInt);
          localHashMap2.remove(localInteger2);
        }
        label299: ImageView localImageView2 = localViewHolder.thumbnail;
        PreinstallListActivity localPreinstallListActivity = PreinstallListActivity.this;
        int m = localAsset._id;
        Drawable localDrawable = localPreinstallListActivity.getThumbnail(paramInt, m);
        localImageView2.setImageDrawable(localDrawable);
        TextView localTextView4 = localViewHolder.title;
        String str2 = localAsset.title;
        localTextView4.setText(str2);
        if (localAsset.shortDescription != null)
        {
          TextView localTextView5 = localViewHolder.description;
          String str3 = localAsset.shortDescription;
          localTextView5.setText(str3);
        }
        CheckBox localCheckBox2 = localViewHolder.checked;
        Integer localInteger3 = Integer.valueOf(paramInt);
        localCheckBox2.setTag(localInteger3);
        HashMap localHashMap3 = PreinstallListActivity.this.mStatusMap;
        Integer localInteger4 = Integer.valueOf(paramInt);
        if (!localHashMap3.containsKey(localInteger4))
          break label521;
        localViewHolder.checked.setChecked(1);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (PreinstallListActivity.ViewHolder)paramView.getTag();
        break;
        label455: localViewHolder.snippet.setBackgroundDrawable(null);
        break label166;
        label467: if (localAsset.installed == 2)
        {
          localViewHolder.installed.setText(2131296363);
          localViewHolder.installed.setVisibility(0);
          break label299;
        }
        localViewHolder.installed.setText("");
        localViewHolder.installed.setVisibility(8);
        break label299;
        label521: localViewHolder.checked.setChecked(0);
      }
    }

    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      int i = ((Integer)paramCompoundButton.getTag()).intValue();
      int j = (int)getItemId(i);
      if ((paramBoolean) && (PreinstallListActivity.this.checkAppDownloading(j)))
      {
        Toast.makeText(this.mContext, 2131296351, 1).show();
        paramCompoundButton.setChecked(0);
      }
      while (true)
      {
        return;
        if (!paramBoolean)
          break;
        if (((Asset)getItem(i)).installed == 1)
        {
          paramCompoundButton.setChecked(0);
          Toast.makeText(PreinstallListActivity.this, 2131296402, 0).show();
          continue;
        }
        PreinstallListActivity localPreinstallListActivity1 = PreinstallListActivity.this;
        int k = localPreinstallListActivity1.mTotalBytesNeeded;
        int m = ((Asset)getItem(i)).size + k;
        localPreinstallListActivity1.mTotalBytesNeeded = m;
        HashMap localHashMap1 = PreinstallListActivity.this.mStatusMap;
        Integer localInteger1 = Integer.valueOf(i);
        Integer localInteger2 = Integer.valueOf(j);
        localHashMap1.put(localInteger1, localInteger2);
      }
      while (true)
      {
        PreinstallListActivity.this.setIndicator();
        break;
        HashMap localHashMap2 = PreinstallListActivity.this.mStatusMap;
        Integer localInteger3 = Integer.valueOf(i);
        if (!localHashMap2.containsKey(localInteger3))
          continue;
        PreinstallListActivity localPreinstallListActivity2 = PreinstallListActivity.this;
        int n = localPreinstallListActivity2.mTotalBytesNeeded;
        int i1 = ((Asset)getItem(i)).size;
        int i2 = n - i1;
        localPreinstallListActivity2.mTotalBytesNeeded = i2;
        HashMap localHashMap3 = PreinstallListActivity.this.mStatusMap;
        Integer localInteger4 = Integer.valueOf(i);
        localHashMap3.remove(localInteger4);
      }
    }
  }

  class ViewHolder
  {
    CheckBox checked;
    TextView description;
    TextView installed;
    LinearLayout snippet;
    ImageView thumbnail;
    TextView title;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.PreinstallListActivity
 * JD-Core Version:    0.6.0
 */