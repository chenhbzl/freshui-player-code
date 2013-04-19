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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.News;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.CachedThumbnails;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Signature({"Landroid/app/ListActivity;", "Landroid/widget/AdapterView$OnItemClickListener;"})
public class NewsListActivity extends ListActivity
  implements AdapterView.OnItemClickListener
{
  private static final int ACTION_APP_ICON = 1;
  private static final int ACTION_LIST_NEWS = 0;
  private static final int ACTION_NETWORK_ERROR = 2;
  private static final int COUNT_PER_TIME = 10;
  private static final int DIALOG_NETWORK_ERROR = 100;

  @Signature({"Ljava/util/ArrayList", "<", "Ljava/lang/String;", ">;"})
  private static ArrayList mNewsURLList = new ArrayList();

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", ">;"})
  private Hashtable iconStatusMap;
  private boolean inflatingNewsList;
  private boolean mBusy = 0;
  private Request mCurrentRequest;
  private View mFooterView;
  private Handler mHandler;
  private ListView mListView;
  private IMarketService mMarketService;
  private NewsAdapter mNewsAdapter;
  private View mProgressIndicator;
  private boolean mReachEnd;
  private int oldStartIndex;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/String;", "Landroid/graphics/drawable/Drawable;", ">;"})
  private Hashtable thumbMap;

  public NewsListActivity()
  {
    NewsListActivity.1 local1 = new NewsListActivity.1(this);
    this.scrollListener = local1;
    Hashtable localHashtable1 = new Hashtable();
    this.iconStatusMap = localHashtable1;
    Hashtable localHashtable2 = new Hashtable();
    this.thumbMap = localHashtable2;
    this.startIndex = 0;
    this.oldStartIndex = -1;
    this.mReachEnd = 0;
    this.inflatingNewsList = 0;
  }

  private void addPageViewLogRequest()
  {
    Request localRequest = new Request(0L, 65557);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = "ZhuanlanList";
    localRequest.setData(arrayOfObject);
    MarketService.getServiceInstance(this).getPageviewLog(localRequest);
  }

  private void addThumbnailRequest(int paramInt, String paramString)
  {
    Request localRequest = new Request(0L, 65569);
    localRequest.setData(paramString);
    NewsListActivity.4 local4 = new NewsListActivity.4(this, localRequest);
    localRequest.addObserver(local4);
    this.mCurrentRequest = localRequest;
    this.mMarketService.getNewsIcon(localRequest);
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

  public static String getLastURL(String paramString)
  {
    String str;
    if (mNewsURLList == null)
      str = null;
    while (true)
    {
      return str;
      int i = mNewsURLList.indexOf(paramString);
      int j = mNewsURLList.size() - 1;
      if ((i > j) || (i <= 0))
      {
        str = null;
        continue;
      }
      ArrayList localArrayList = mNewsURLList;
      int k = i - 1;
      str = (String)localArrayList.get(k);
    }
  }

  public static String getNextURL(String paramString)
  {
    String str;
    if (mNewsURLList == null)
      str = null;
    while (true)
    {
      return str;
      int i = mNewsURLList.indexOf(paramString);
      int j = mNewsURLList.size() - 1;
      if ((i >= j) || (i < 0))
      {
        str = null;
        continue;
      }
      ArrayList localArrayList = mNewsURLList;
      int k = i + 1;
      str = (String)localArrayList.get(k);
    }
  }

  private void inflateNewsList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      if (!this.inflatingNewsList)
      {
        this.inflatingNewsList = 1;
        Request localRequest = new Request(0L, 65564);
        Object[] arrayOfObject = new Object[2];
        Integer localInteger1 = Integer.valueOf(this.startIndex);
        arrayOfObject[0] = localInteger1;
        Integer localInteger2 = Integer.valueOf(10);
        arrayOfObject[1] = localInteger2;
        localRequest.setData(arrayOfObject);
        NewsListActivity.6 local6 = new NewsListActivity.6(this, localRequest);
        localRequest.addObserver(local6);
        this.mCurrentRequest = localRequest;
        this.mMarketService.getNewsList(localRequest);
        continue;
      }
    }
  }

  private void initHandlerInUIThread()
  {
    NewsListActivity.5 local5 = new NewsListActivity.5(this);
    this.mHandler = local5;
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
      View localView2 = LayoutInflater.from(this).inflate(2130903056, null);
      this.mFooterView = localView2;
      Button localButton = (Button)this.mFooterView.findViewById(2131427414);
      NewsListActivity.2 local2 = new NewsListActivity.2(this);
      localButton.setOnClickListener(local2);
      ListView localListView3 = this.mListView;
      View localView3 = this.mFooterView;
      localListView3.addFooterView(localView3);
      ListView localListView4 = this.mListView;
      View localView4 = findViewById(2131427408);
      localListView4.setEmptyView(localView4);
      TextView localTextView = (TextView)findViewById(2131427409);
      NewsListActivity.3 local3 = new NewsListActivity.3(this);
      localTextView.setOnClickListener(local3);
      this.mListView.setOnItemClickListener(this);
      ListView localListView5 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView5.setOnScrollListener(localOnScrollListener);
      inflateNewsList();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  public Drawable getThumbnail(int paramInt, String paramString)
  {
    Object localObject;
    if (this.mBusy)
    {
      Hashtable localHashtable1 = this.iconStatusMap;
      Integer localInteger1 = Integer.valueOf(paramInt);
      if (!localHashtable1.containsKey(localInteger1))
        localObject = CachedThumbnails.getDefaultIcon(this);
    }
    while (true)
    {
      return localObject;
      Drawable localDrawable = (Drawable)this.thumbMap.get(paramString);
      if (localDrawable != null)
      {
        Hashtable localHashtable2 = this.iconStatusMap;
        Integer localInteger2 = Integer.valueOf(paramInt);
        Boolean localBoolean1 = Boolean.valueOf(1);
        localHashtable2.put(localInteger2, localBoolean1);
        localObject = localDrawable;
        continue;
      }
      Hashtable localHashtable3 = this.iconStatusMap;
      Integer localInteger3 = Integer.valueOf(paramInt);
      if (localHashtable3.containsKey(localInteger3))
      {
        Hashtable localHashtable4 = this.iconStatusMap;
        Integer localInteger4 = Integer.valueOf(paramInt);
        if (!((Boolean)localHashtable4.get(localInteger4)).booleanValue());
      }
      else
      {
        Hashtable localHashtable5 = this.iconStatusMap;
        Integer localInteger5 = Integer.valueOf(paramInt);
        Boolean localBoolean2 = Boolean.valueOf(0);
        localHashtable5.put(localInteger5, localBoolean2);
        addThumbnailRequest(paramInt, paramString);
      }
      localObject = CachedThumbnails.getDefaultIcon(this);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    setContentView(2130903055);
    initHandlerInUIThread();
    setupListViews();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 100:
    }
    AlertDialog.Builder localBuilder2;
    NewsListActivity.8 local8;
    for (AlertDialog localAlertDialog = null; ; localAlertDialog = localBuilder2.setNegativeButton(2131296373, local8).create())
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      NewsListActivity.7 local7 = new NewsListActivity.7(this);
      localBuilder2 = localBuilder1.setPositiveButton(2131296379, local7);
      local8 = new NewsListActivity.8(this);
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
    if (this.mNewsAdapter != null)
      this.mNewsAdapter.clear();
    if (this.mListView != null)
      this.mListView.setAdapter(null);
  }

  @Signature({"(", "Landroid/widget/AdapterView", "<*>;", "Landroid/view/View;", "IJ)V"})
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    News localNews = (News)paramAdapterView.getItemAtPosition(paramInt);
    if (localNews != null)
    {
      String str1 = localNews.url;
      Intent localIntent = new Intent();
      String str2 = NewsContentActivity.class.getName();
      localIntent.setClassName(this, str2);
      localIntent.putExtra("url", str1);
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
    addPageViewLogRequest();
    MobclickAgent.onEvent(this, "news_list");
  }

  @Signature({"Landroid/widget/ArrayAdapter", "<", "Lcom/yingyonghui/market/model/News;", ">;"})
  class NewsAdapter extends ArrayAdapter
  {
    private final LayoutInflater mInflater;

    @Signature({"(", "Landroid/content/Context;", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/News;", ">;)V"})
    public NewsAdapter(Context paramArrayList, ArrayList arg3)
    {
      super(0, localList);
      LayoutInflater localLayoutInflater = LayoutInflater.from(paramArrayList);
      this.mInflater = localLayoutInflater;
    }

    public String getThumbURL(int paramInt)
    {
      return ((News)getItem(paramInt)).thumb_url;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      News localNews = (News)getItem(paramInt);
      NewsListActivity.ViewHolder localViewHolder;
      label167: ImageView localImageView3;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903088, paramViewGroup, 0);
        localViewHolder = new NewsListActivity.ViewHolder();
        LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427487);
        localViewHolder.news_abstract = localLinearLayout;
        ImageView localImageView1 = (ImageView)paramView.findViewById(2131427373);
        localViewHolder.thumbnail = localImageView1;
        TextView localTextView1 = (TextView)paramView.findViewById(2131427489);
        localViewHolder.news_title = localTextView1;
        TextView localTextView2 = (TextView)paramView.findViewById(2131427490);
        localViewHolder.news_date = localTextView2;
        TextView localTextView3 = (TextView)paramView.findViewById(2131427491);
        localViewHolder.news_visit = localTextView3;
        ImageView localImageView2 = (ImageView)paramView.findViewById(2131427488);
        localViewHolder.news_hot = localImageView2;
        paramView.setTag(localViewHolder);
        if (paramInt % 2 != 1)
          break label330;
        localViewHolder.news_abstract.setBackgroundResource(2130837594);
        TextView localTextView4 = localViewHolder.news_title;
        String str1 = localNews.title;
        localTextView4.setText(str1);
        TextView localTextView5 = localViewHolder.news_date;
        String str2 = localNews.releaseDate;
        localTextView5.setText(str2);
        TextView localTextView6 = localViewHolder.news_visit;
        String str3 = String.valueOf(localNews.visits);
        String str4 = str3 + "娆�";
        localTextView6.setText(str4);
        localImageView3 = localViewHolder.news_hot;
        if (!localNews.hot)
          break label342;
      }
      label330: label342: for (int i = 0; ; i = 8)
      {
        localImageView3.setVisibility(i);
        NewsListActivity localNewsListActivity = NewsListActivity.this;
        String str5 = localNews.thumb_url;
        Drawable localDrawable = localNewsListActivity.getThumbnail(paramInt, str5);
        localViewHolder.thumbnail.setImageDrawable(localDrawable);
        localDrawable.setCallback(null);
        return paramView;
        localViewHolder = (NewsListActivity.ViewHolder)paramView.getTag();
        break;
        localViewHolder.news_abstract.setBackgroundDrawable(null);
        break label167;
      }
    }
  }

  class ViewHolder
  {
    LinearLayout news_abstract;
    TextView news_date;
    ImageView news_hot;
    TextView news_title;
    TextView news_visit;
    ImageView thumbnail;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsListActivity
 * JD-Core Version:    0.6.0
 */