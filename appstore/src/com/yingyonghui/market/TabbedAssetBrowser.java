package com.yingyonghui.market;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TabbedAssetBrowser extends TabActivity
  implements TabHost.OnTabChangeListener
{
  public static final int ID_LIST_APP_BY_TIME = 3;
  public static final int ID_LIST_APP_LIKE = 11;
  private static final int TAB_ID_LIKE = 1;
  private static final int TAB_ID_NEW = 2;
  private static final int TAB_ID_RECOMMEND = 0;
  private static final String TAB_LIKE = "like";
  private static final String TAB_NEW = "new";
  private static final String TAB_RECOMMEND = "recommend";
  private int category_id;
  private int height = 44;
  private float labelTextSize = 18.0F;
  private Field mBottomLeftStrip;
  private Field mBottomRightStrip;
  private TabHost mTabHost;

  @Signature({"Ljava/util/ArrayList", "<", "Landroid/view/View;", ">;"})
  private ArrayList tabViews;
  private TabWidget tw;

  private void initTabs()
  {
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("recommend");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView.setBackgroundResource(2130837729);
    localTextView.getBackground().setCallback(null);
    localTextView.setText(2131296320);
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
      label147: this.tabViews.add(localTextView);
      Intent localIntent1 = new Intent();
      String str1 = CommonAppListActivity.class.getName();
      localIntent1.setClassName(this, str1);
      localIntent1.putExtra("new", 1);
      localIntent1.putExtra("header", 1);
      localIntent1.addFlags(268435456);
      localTabSpec.setContent(localIntent1);
      this.mTabHost.addTab(localTabSpec);
      localTabSpec = this.mTabHost.newTabSpec("like");
      localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
      localTextView.setBackgroundResource(2130837731);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296323);
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
        label334: this.tabViews.add(localTextView);
        Intent localIntent2 = new Intent();
        String str2 = NewsListActivity.class.getName();
        localIntent2.setClassName(this, str2);
        localIntent2.putExtra("_id", 11);
        localIntent2.putExtra("ranking_type", 2);
        localIntent2.putExtra("header", 1);
        localTabSpec.setContent(localIntent2);
        this.mTabHost.addTab(localTabSpec);
        localTabSpec = this.mTabHost.newTabSpec("new");
        localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
        localTextView.setBackgroundResource(2130837729);
        localTextView.getBackground().setCallback(null);
        localTextView.setText(2131296321);
        float f3 = this.labelTextSize;
        localTextView.setTextSize(f3);
        localTextView.setLayoutParams(localLayoutParams);
        try
        {
          Class[] arrayOfClass3 = new Class[1];
          arrayOfClass3[0] = View.class;
          Method localMethod3 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass3);
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = localTextView;
          localMethod3.invoke(localTabSpec, arrayOfObject3);
          label522: this.tabViews.add(localTextView);
          Intent localIntent3 = new Intent();
          String str3 = FilteredAppListActivity.class.getName();
          localIntent3.setClassName(this, str3);
          localIntent3.putExtra("_id", 3);
          localIntent3.putExtra("ranking_type", 2);
          localIntent3.putExtra("list_item", 901);
          localTabSpec.setContent(localIntent3);
          this.mTabHost.addTab(localTabSpec);
          this.mTabHost.setOnTabChangedListener(this);
          setCurrentTab(0);
          return;
        }
        catch (Exception localException1)
        {
          break label522;
        }
      }
      catch (Exception localException2)
      {
        break label334;
      }
    }
    catch (Exception localException3)
    {
      break label147;
    }
  }

  private void initTabsLow()
  {
    TabHost.TabSpec localTabSpec1 = this.mTabHost.newTabSpec("recommend");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    TableLayout.LayoutParams localLayoutParams = new TableLayout.LayoutParams(-1, -1, 1.0F);
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView1 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView1.setBackgroundResource(2130837750);
    localTextView1.getBackground().setCallback(null);
    localTextView1.setText(2131296320);
    localTextView1.setLayoutParams(localLayoutParams);
    float f1 = this.labelTextSize;
    localTextView1.setTextSize(f1);
    String str1 = getResources().getString(2131296320);
    localTabSpec1.setIndicator(str1);
    this.tabViews.add(localTextView1);
    Intent localIntent1 = new Intent();
    String str2 = CommonAppListActivity.class.getName();
    localIntent1.setClassName(this, str2);
    localIntent1.putExtra("new", 1);
    localIntent1.putExtra("header", 1);
    localIntent1.addFlags(268435456);
    localTabSpec1.setContent(localIntent1);
    this.mTabHost.addTab(localTabSpec1);
    TabHost.TabSpec localTabSpec2 = this.mTabHost.newTabSpec("like");
    TextView localTextView2 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView2.setBackgroundResource(2130837750);
    localTextView2.getBackground().setCallback(null);
    localTextView2.setText(2131296323);
    localTextView2.setLayoutParams(localLayoutParams);
    float f2 = this.labelTextSize;
    localTextView2.setTextSize(f2);
    String str3 = getResources().getString(2131296323);
    localTabSpec2.setIndicator(str3);
    this.tabViews.add(localTextView2);
    Intent localIntent2 = new Intent();
    String str4 = NewsListActivity.class.getName();
    localIntent2.setClassName(this, str4);
    localIntent2.putExtra("_id", 11);
    localIntent2.putExtra("ranking_type", 2);
    localIntent2.putExtra("header", 1);
    localTabSpec2.setContent(localIntent2);
    this.mTabHost.addTab(localTabSpec2);
    TabHost.TabSpec localTabSpec3 = this.mTabHost.newTabSpec("new");
    TextView localTextView3 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView3.setBackgroundResource(2130837750);
    localTextView3.getBackground().setCallback(null);
    localTextView3.setText(2131296321);
    localTextView3.setLayoutParams(localLayoutParams);
    float f3 = this.labelTextSize;
    localTextView3.setTextSize(f3);
    String str5 = getResources().getString(2131296321);
    localTabSpec3.setIndicator(str5);
    this.tabViews.add(localTextView3);
    Intent localIntent3 = new Intent();
    String str6 = FilteredAppListActivity.class.getName();
    localIntent3.setClassName(this, str6);
    localIntent3.putExtra("_id", 3);
    localIntent3.putExtra("ranking_type", 2);
    localIntent3.putExtra("list_item", 901);
    localTabSpec3.setContent(localIntent3);
    this.mTabHost.addTab(localTabSpec3);
    this.mTabHost.setOnTabChangedListener(this);
    setCurrentTabLow(0);
  }

  private void setCurrentTab(int paramInt)
  {
    View localView1;
    View localView2;
    View localView3;
    if (paramInt == 0)
    {
      localView1 = (View)this.tabViews.get(0);
      localView2 = (View)this.tabViews.get(1);
      localView3 = (View)this.tabViews.get(2);
      localView1.setBackgroundResource(2130837729);
      localView1.getBackground().setCallback(null);
      localView2.setBackgroundResource(2130837731);
      localView2.getBackground().setCallback(null);
      localView3.setBackgroundDrawable(null);
    }
    while (true)
    {
      TextView localTextView1 = (TextView)localView1;
      Typeface localTypeface1 = Typeface.DEFAULT_BOLD;
      localTextView1.setTypeface(localTypeface1);
      localTextView1.setTextColor(-13027015);
      TextView localTextView2 = (TextView)localView2;
      Typeface localTypeface2 = Typeface.DEFAULT;
      localTextView2.setTypeface(localTypeface2);
      localTextView2.setTextColor(-1);
      TextView localTextView3 = (TextView)localView3;
      Typeface localTypeface3 = Typeface.DEFAULT;
      localTextView3.setTypeface(localTypeface3);
      localTextView3.setTextColor(-1);
      return;
      if (paramInt == 1)
      {
        localView1 = (View)this.tabViews.get(1);
        localView2 = (View)this.tabViews.get(0);
        localView3 = (View)this.tabViews.get(2);
        localView1.setBackgroundResource(2130837730);
        localView1.getBackground().setCallback(null);
        localView2.setBackgroundDrawable(null);
        localView3.setBackgroundDrawable(null);
        continue;
      }
      localView1 = (View)this.tabViews.get(2);
      localView2 = (View)this.tabViews.get(0);
      localView3 = (View)this.tabViews.get(1);
      localView1.setBackgroundResource(2130837729);
      localView2.setBackgroundDrawable(null);
      localView3.setBackgroundResource(2130837731);
      localView3.getBackground().setCallback(null);
    }
  }

  private void setCurrentTabLow(int paramInt)
  {
    RelativeLayout localRelativeLayout1 = (RelativeLayout)this.tw.getChildAt(0);
    RelativeLayout localRelativeLayout2 = (RelativeLayout)this.tw.getChildAt(1);
    RelativeLayout localRelativeLayout3 = (RelativeLayout)this.tw.getChildAt(2);
    int i;
    if (paramInt == 0)
    {
      Drawable localDrawable1 = getResources().getDrawable(2130837729);
      localRelativeLayout1.setBackgroundDrawable(localDrawable1);
      localRelativeLayout1.getBackground().setCallback(null);
      Drawable localDrawable2 = getResources().getDrawable(2130837731);
      localRelativeLayout2.setBackgroundDrawable(localDrawable2);
      localRelativeLayout2.getBackground().setCallback(null);
      localRelativeLayout3.setBackgroundDrawable(null);
      i = 1;
    }
    while (true)
    {
      try
      {
        ((TextView)localRelativeLayout1.getChildAt(i)).setTextColor(-13027015);
        ((TextView)localRelativeLayout2.getChildAt(1)).setTextColor(-1);
        ((TextView)localRelativeLayout3.getChildAt(1)).setTextColor(-1);
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
        continue;
      }
      if (paramInt == 1)
      {
        localRelativeLayout1.setBackgroundDrawable(null);
        Drawable localDrawable3 = getResources().getDrawable(2130837730);
        localRelativeLayout2.setBackgroundDrawable(localDrawable3);
        localRelativeLayout2.getBackground().setCallback(null);
        localRelativeLayout3.setBackgroundDrawable(null);
        i = 1;
        try
        {
          ((TextView)localRelativeLayout1.getChildAt(i)).setTextColor(-1);
          ((TextView)localRelativeLayout2.getChildAt(1)).setTextColor(-13027015);
          ((TextView)localRelativeLayout3.getChildAt(1)).setTextColor(-1);
        }
        catch (Throwable localThrowable2)
        {
          localThrowable2.printStackTrace();
        }
        continue;
      }
      localRelativeLayout1.setBackgroundDrawable(null);
      Drawable localDrawable4 = getResources().getDrawable(2130837731);
      localRelativeLayout2.setBackgroundDrawable(localDrawable4);
      localRelativeLayout2.getBackground().setCallback(null);
      Drawable localDrawable5 = getResources().getDrawable(2130837729);
      localRelativeLayout3.setBackgroundDrawable(localDrawable5);
      localRelativeLayout3.getBackground().setCallback(null);
      i = 1;
      try
      {
        ((TextView)localRelativeLayout1.getChildAt(i)).setTextColor(-1);
        ((TextView)localRelativeLayout2.getChildAt(1)).setTextColor(-1);
        ((TextView)localRelativeLayout3.getChildAt(1)).setTextColor(-13027015);
      }
      catch (Throwable localThrowable3)
      {
        localThrowable3.printStackTrace();
      }
    }
  }

  private void setupViews()
  {
    this.category_id = 3;
    initTabs();
  }

  private void setupViewsLow()
  {
    this.category_id = 3;
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
          float f1 = AssetBrowser.density;
          int k = (int)(6.0F * f1);
          localTextView.setPadding(0, 0, 0, k);
          ViewGroup.LayoutParams localLayoutParams = this.tw.getChildAt(i).getLayoutParams();
          float f2 = this.height;
          float f3 = AssetBrowser.density;
          int m = (int)(f2 * f3);
          localLayoutParams.height = m;
        }
        i += 1;
      }
    }
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
      switch (paramKeyEvent.getKeyCode())
      {
      default:
      case 84:
      }
    int i;
    for (boolean bool = dispatchKeyEvent(paramKeyEvent); ; i = 1)
      return bool;
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903043);
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    LinearLayout localLinearLayout = (LinearLayout)this.mTabHost.getChildAt(1);
    TabWidget localTabWidget = this.mTabHost.getTabWidget();
    this.tw = localTabWidget;
    if (DeviceUtil.getSDKVersionInt() == 3)
      setupViewsLow();
    while (true)
    {
      return;
      setupViews();
    }
  }

  public void onTabChanged(String paramString)
  {
    int i;
    if ("recommend".equals(paramString))
    {
      i = 0;
      if (DeviceUtil.getSDKVersionInt() != 3)
        break label43;
      setCurrentTabLow(i);
    }
    while (true)
    {
      return;
      if ("like".equals(paramString))
      {
        i = 1;
        break;
      }
      i = 2;
      break;
      label43: setCurrentTab(i);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.TabbedAssetBrowser
 * JD-Core Version:    0.6.0
 */