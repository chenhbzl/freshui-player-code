package com.yingyonghui.market;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.yingyonghui.market.model.Category;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

@Signature({"Landroid/app/ListActivity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class FilteredCategoryListActivity extends ListActivity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 1;
  private static final int ACTION_LIST_CATEGORY = 0;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private int category_id;
  private View headerView;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  private boolean mBusy = 0;
  private CategoriesWithAppsAdapter mCatAdapter;
  private boolean mContainsHeader = 1;
  private Request mCurrentRequest;
  private Handler mHandler;
  private ListView mListView;
  private IMarketService mMarketService;
  private View mProgressIndicator;
  private AbsListView.OnScrollListener scrollListener;

  public FilteredCategoryListActivity()
  {
    FilteredCategoryListActivity.1 local1 = new FilteredCategoryListActivity.1(this);
    this.scrollListener = local1;
    Hashtable localHashtable = new Hashtable();
    this.iconStatusMap = localHashtable;
  }

  private void addThumbnailRequest(int paramInt1, int paramInt2)
  {
    Request localRequest = new Request(0L, 65542);
    Integer localInteger = Integer.valueOf(paramInt2);
    localRequest.setData(localInteger);
    FilteredCategoryListActivity.3 local3 = new FilteredCategoryListActivity.3(this, localRequest);
    localRequest.addObserver(local3);
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

  private void inflateCategoryList()
  {
    Request localRequest = new Request(0L, 65537);
    Integer localInteger = Integer.valueOf(this.category_id);
    localRequest.setData(localInteger);
    FilteredCategoryListActivity.5 local5 = new FilteredCategoryListActivity.5(this, localRequest);
    localRequest.addObserver(local5);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getCategory(localRequest);
  }

  private void initHandlerInUIThread()
  {
    FilteredCategoryListActivity.4 local4 = new FilteredCategoryListActivity.4(this);
    this.mHandler = local4;
  }

  private void setupListViews()
  {
    View localView1 = findViewById(2131427468);
    this.mProgressIndicator = localView1;
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
        View localView2 = LayoutInflater.from(this).inflate(2130903098, null);
        this.headerView = localView2;
        this.headerView.setEnabled(0);
        ListView localListView3 = this.mListView;
        View localView3 = this.headerView;
        localListView3.addHeaderView(localView3, null, 0);
      }
      ListView localListView4 = this.mListView;
      View localView4 = findViewById(2131427408);
      localListView4.setEmptyView(localView4);
      TextView localTextView = (TextView)findViewById(2131427409);
      FilteredCategoryListActivity.2 local2 = new FilteredCategoryListActivity.2(this);
      localTextView.setOnClickListener(local2);
      this.mListView.setOnItemClickListener(this);
      ListView localListView5 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView5.setOnScrollListener(localOnScrollListener);
      inflateCategoryList();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
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
    boolean bool1;
    if (paramBundle == null)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
      {
        int i = localBundle.getInt("_id");
        this.category_id = i;
        if (localBundle == null)
          break label88;
        bool1 = localBundle.getBoolean("header");
      }
    }
    label59: label88: boolean bool2;
    for (this.mContainsHeader = bool1; ; this.mContainsHeader = bool2)
    {
      setContentView(2130903055);
      initHandlerInUIThread();
      setupListViews();
      return;
      int j = -1;
      break;
      j = 0;
      break label59;
      int k = paramBundle.getInt("_id");
      this.category_id = k;
      bool2 = paramBundle.getBoolean("header");
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
    FilteredCategoryListActivity.7 local7;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local7).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      FilteredCategoryListActivity.6 local6 = new FilteredCategoryListActivity.6(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local6);
      local7 = new FilteredCategoryListActivity.7(this);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = onCreateOptionsMenu(paramMenu);
    if (bool)
      OptionsMenu.onCreateOptionsMenu(paramMenu);
    return bool;
  }

  @Signature({"(", "Landroid/widget/AdapterView", "<*>;", "Landroid/view/View;", "IJ)V"})
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Category localCategory = (Category)paramAdapterView.getItemAtPosition(paramInt);
    String str1;
    int i;
    if (localCategory != null)
    {
      str1 = localCategory.cat_name;
      i = localCategory._id;
      if (this.category_id != 8)
        break label93;
      Intent localIntent1 = new Intent();
      String str2 = PreinstallListActivity.class.getName();
      localIntent1.setClassName(this, str2);
      localIntent1.putExtra("_id", i);
      localIntent1.putExtra("label", str1);
      startActivity(localIntent1);
    }
    while (true)
    {
      return;
      label93: Intent localIntent2 = new Intent();
      String str3 = TabbedAppBrowser.class.getName();
      localIntent2.setClassName(this, str3);
      localIntent2.putExtra("_id", i);
      localIntent2.putExtra("label", str1);
      startActivity(localIntent2);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = this.category_id;
    paramBundle.putInt("_id", i);
    boolean bool = this.mContainsHeader;
    paramBundle.putBoolean("header", bool);
    onSaveInstanceState(paramBundle);
  }

  @Signature({"Landroid/widget/ArrayAdapter", "<", "Lcom/yingyonghui/market/model/Category;", ">;"})
  class CategoriesWithAppsAdapter extends ArrayAdapter
  {
    private final LayoutInflater mInflater;

    @Signature({"(", "Landroid/content/Context;", "Ljava/util/Vector", "<", "Lcom/yingyonghui/market/model/Category;", ">;)V"})
    public CategoriesWithAppsAdapter(Context paramVector, Vector arg3)
    {
      super(0, localList);
      LayoutInflater localLayoutInflater = LayoutInflater.from(paramVector);
      this.mInflater = localLayoutInflater;
    }

    public long getTopAppId(int paramInt)
    {
      return ((Category)getItem(paramInt)).topId;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Category localCategory = (Category)getItem(paramInt);
      FilteredCategoryListActivity.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903062, paramViewGroup, 0);
        localViewHolder = new FilteredCategoryListActivity.ViewHolder();
        LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427425);
        localViewHolder.asset_category = localLinearLayout;
        TextView localTextView1 = (TextView)paramView.findViewById(2131427426);
        localViewHolder.category = localTextView1;
        ImageView localImageView1 = (ImageView)paramView.findViewById(2131427373);
        localViewHolder.thumbnail = localImageView1;
        paramView.setTag(localViewHolder);
        if (paramInt % 2 != 1)
          break label185;
        localViewHolder.asset_category.setBackgroundResource(2130837594);
      }
      while (true)
      {
        TextView localTextView2 = localViewHolder.category;
        String str = localCategory.cat_name;
        localTextView2.setText(str);
        ImageView localImageView2 = localViewHolder.thumbnail;
        FilteredCategoryListActivity localFilteredCategoryListActivity = FilteredCategoryListActivity.this;
        int i = localCategory.topId;
        Drawable localDrawable = localFilteredCategoryListActivity.getThumbnail(paramInt, i);
        localImageView2.setImageDrawable(localDrawable);
        return paramView;
        localViewHolder = (FilteredCategoryListActivity.ViewHolder)paramView.getTag();
        break;
        label185: localViewHolder.asset_category.setBackgroundDrawable(null);
      }
    }
  }

  class ViewHolder
  {
    LinearLayout asset_category;
    TextView category;
    ImageView thumbnail;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FilteredCategoryListActivity
 * JD-Core Version:    0.6.0
 */