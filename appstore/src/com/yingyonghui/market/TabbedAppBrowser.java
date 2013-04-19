package com.yingyonghui.market;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TabbedAppBrowser extends TabActivity
  implements TabHost.OnTabChangeListener
{
  private static final String TAB_ALL = "all";
  private static final String TAB_APP = "app";
  private static final String TAB_GAME = "game";
  private static final int TAB_ID_ALL = 0;
  private static final int TAB_ID_APP = 1;
  private static final int TAB_ID_GAME = 2;
  private int category_id;
  private String category_label;
  private String currentTab;
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
    View localView = findViewById(2131427390);
    TabbedAppBrowser.1 local1 = new TabbedAppBrowser.1(this);
    localView.setOnClickListener(local1);
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec("all");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView.setBackgroundResource(2130837750);
    localTextView.getBackground().setCallback(null);
    localTextView.setText(2131296315);
    float f1 = this.labelTextSize;
    localTextView.setTextSize(f1);
    try
    {
      Class[] arrayOfClass1 = new Class[1];
      arrayOfClass1[0] = View.class;
      Method localMethod1 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass1);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localTextView;
      localMethod1.invoke(localTabSpec, arrayOfObject1);
      label162: this.tabViews.add(localTextView);
      Intent localIntent1 = new Intent();
      String str1 = FilteredAppListActivity.class.getName();
      localIntent1.setClassName(this, str1);
      int i = this.category_id;
      localIntent1.putExtra("_id", i);
      String str2 = this.category_label;
      localIntent1.putExtra("label", str2);
      localIntent1.putExtra("ranking_type", 0);
      localIntent1.putExtra("header", 0);
      localIntent1.addFlags(268435456);
      localTabSpec.setContent(localIntent1);
      this.mTabHost.addTab(localTabSpec);
      localTabSpec = this.mTabHost.newTabSpec("app");
      localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
      localTextView.setBackgroundResource(2130837750);
      localTextView.getBackground().setCallback(null);
      localTextView.setText(2131296317);
      float f2 = this.labelTextSize;
      localTextView.setTextSize(f2);
      try
      {
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = View.class;
        Method localMethod2 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass2);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localTextView;
        localMethod2.invoke(localTabSpec, arrayOfObject2);
        label378: this.tabViews.add(localTextView);
        Intent localIntent2 = new Intent();
        String str3 = FilteredAppListActivity.class.getName();
        localIntent2.setClassName(this, str3);
        int j = this.category_id;
        localIntent2.putExtra("_id", j);
        String str4 = this.category_label;
        localIntent2.putExtra("label", str4);
        localIntent2.putExtra("ranking_type", 1);
        localIntent2.putExtra("header", 0);
        localTabSpec.setContent(localIntent2);
        this.mTabHost.addTab(localTabSpec);
        localTabSpec = this.mTabHost.newTabSpec("game");
        localTextView = (TextView)localLayoutInflater.inflate(2130903112, null);
        localTextView.setBackgroundResource(2130837750);
        localTextView.getBackground().setCallback(null);
        localTextView.setText(2131296319);
        float f3 = this.labelTextSize;
        localTextView.setTextSize(f3);
        try
        {
          Class[] arrayOfClass3 = new Class[1];
          arrayOfClass3[0] = View.class;
          Method localMethod3 = TabHost.TabSpec.class.getMethod("setIndicator", arrayOfClass3);
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = localTextView;
          localMethod3.invoke(localTabSpec, arrayOfObject3);
          label586: this.tabViews.add(localTextView);
          Intent localIntent3 = new Intent();
          String str5 = FilteredAppListActivity.class.getName();
          localIntent3.setClassName(this, str5);
          int k = this.category_id;
          localIntent3.putExtra("_id", k);
          String str6 = this.category_label;
          localIntent3.putExtra("label", str6);
          localIntent3.putExtra("ranking_type", 2);
          localIntent3.putExtra("header", 0);
          localTabSpec.setContent(localIntent3);
          this.mTabHost.addTab(localTabSpec);
          this.mTabHost.setOnTabChangedListener(this);
          setCurrentTab(0);
          return;
        }
        catch (Exception localException1)
        {
          break label586;
        }
      }
      catch (Exception localException2)
      {
        break label378;
      }
    }
    catch (Exception localException3)
    {
      break label162;
    }
  }

  private void initTabsLow()
  {
    View localView = findViewById(2131427390);
    TabbedAppBrowser.2 local2 = new TabbedAppBrowser.2(this);
    localView.setOnClickListener(local2);
    TabHost localTabHost = getTabHost();
    this.mTabHost = localTabHost;
    TabHost.TabSpec localTabSpec1 = this.mTabHost.newTabSpec("all");
    ArrayList localArrayList = new ArrayList();
    this.tabViews = localArrayList;
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    TextView localTextView1 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView1.setBackgroundResource(2130837750);
    localTextView1.getBackground().setCallback(null);
    localTextView1.setText(2131296315);
    float f1 = this.labelTextSize;
    localTextView1.setTextSize(f1);
    String str1 = getResources().getString(2131296315);
    localTabSpec1.setIndicator(str1);
    this.tabViews.add(localTextView1);
    Intent localIntent1 = new Intent();
    String str2 = FilteredAppListActivity.class.getName();
    localIntent1.setClassName(this, str2);
    int i = this.category_id;
    localIntent1.putExtra("_id", i);
    String str3 = this.category_label;
    localIntent1.putExtra("label", str3);
    localIntent1.putExtra("ranking_type", 0);
    localIntent1.putExtra("header", 0);
    localIntent1.addFlags(268435456);
    localTabSpec1.setContent(localIntent1);
    this.mTabHost.addTab(localTabSpec1);
    TabHost.TabSpec localTabSpec2 = this.mTabHost.newTabSpec("app");
    TextView localTextView2 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView2.setBackgroundResource(2130837750);
    localTextView2.getBackground().setCallback(null);
    localTextView2.setText(2131296317);
    float f2 = this.labelTextSize;
    localTextView2.setTextSize(f2);
    String str4 = getResources().getString(2131296317);
    localTabSpec2.setIndicator(str4);
    this.tabViews.add(localTextView2);
    Intent localIntent2 = new Intent();
    String str5 = FilteredAppListActivity.class.getName();
    localIntent2.setClassName(this, str5);
    int j = this.category_id;
    localIntent2.putExtra("_id", j);
    String str6 = this.category_label;
    localIntent2.putExtra("label", str6);
    localIntent2.putExtra("ranking_type", 1);
    localIntent2.putExtra("header", 0);
    localTabSpec2.setContent(localIntent2);
    this.mTabHost.addTab(localTabSpec2);
    TabHost.TabSpec localTabSpec3 = this.mTabHost.newTabSpec("game");
    TextView localTextView3 = (TextView)localLayoutInflater.inflate(2130903112, null);
    localTextView3.setBackgroundResource(2130837750);
    localTextView3.getBackground().setCallback(null);
    localTextView3.setText(2131296319);
    float f3 = this.labelTextSize;
    localTextView3.setTextSize(f3);
    String str7 = getResources().getString(2131296319);
    localTabSpec3.setIndicator(str7);
    this.tabViews.add(localTextView3);
    Intent localIntent3 = new Intent();
    String str8 = FilteredAppListActivity.class.getName();
    localIntent3.setClassName(this, str8);
    int k = this.category_id;
    localIntent3.putExtra("_id", k);
    String str9 = this.category_label;
    localIntent3.putExtra("label", str9);
    localIntent3.putExtra("ranking_type", 2);
    localIntent3.putExtra("header", 0);
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
    }
    while (true)
    {
      localView2.setBackgroundDrawable(null);
      localView3.setBackgroundDrawable(null);
      localView1.getBackground().setCallback(null);
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
        localView1.setBackgroundResource(2130837729);
        continue;
      }
      localView1 = (View)this.tabViews.get(2);
      localView2 = (View)this.tabViews.get(0);
      localView3 = (View)this.tabViews.get(1);
      localView1.setBackgroundResource(2130837729);
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
      localRelativeLayout2.setBackgroundDrawable(null);
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
        Drawable localDrawable2 = getResources().getDrawable(2130837729);
        localRelativeLayout2.setBackgroundDrawable(localDrawable2);
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
      localRelativeLayout2.setBackgroundDrawable(null);
      Drawable localDrawable3 = getResources().getDrawable(2130837729);
      localRelativeLayout3.setBackgroundDrawable(localDrawable3);
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
    initTabs();
  }

  private void setupViewsLow()
  {
    initTabsLow();
    int i = 0;
    while (true)
    {
      int j = this.tw.getChildCount();
      if (i >= j)
        return;
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
        TextView localTextView = (TextView)((RelativeLayout)this.tw.getChildAt(i)).getChildAt(1);
        if (localTextView != null)
        {
          float f1 = AssetBrowser.density;
          int k = (int)(6.0F * f1);
          localTextView.setPadding(0, 0, 0, k);
        }
        ViewGroup.LayoutParams localLayoutParams = this.tw.getChildAt(i).getLayoutParams();
        float f2 = this.height;
        float f3 = AssetBrowser.density;
        int m = (int)(f2 * f3);
        localLayoutParams.height = m;
        i += 1;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    if (paramBundle == null)
    {
      Bundle localBundle = getIntent().getExtras();
      if (localBundle != null)
      {
        int i = localBundle.getInt("_id");
        this.category_id = i;
        if (localBundle == null)
          break label107;
        String str1 = localBundle.getString("label");
        label44: this.category_label = str1;
        label49: requestWindowFeature(1);
        setContentView(2130903093);
        TabHost localTabHost = getTabHost();
        this.mTabHost = localTabHost;
        TabWidget localTabWidget = this.mTabHost.getTabWidget();
        this.tw = localTabWidget;
        if (DeviceUtil.getSDKVersionInt() != 3)
          break label145;
        setupViewsLow();
      }
    }
    while (true)
    {
      return;
      int j = -1;
      break;
      label107: String str2 = "";
      break label44;
      int k = paramBundle.getInt("_id");
      this.category_id = k;
      String str3 = paramBundle.getString("label");
      this.category_label = str3;
      break label49;
      label145: setupViews();
    }
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
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = this.category_id;
    paramBundle.putInt("_id", i);
    String str = this.category_label;
    paramBundle.putString("label", str);
    onSaveInstanceState(paramBundle);
  }

  public void onTabChanged(String paramString)
  {
    this.currentTab = paramString;
    String str1 = this.currentTab;
    int i;
    if ("all".equals(str1))
    {
      i = 0;
      if (DeviceUtil.getSDKVersionInt() != 3)
        break label60;
      setCurrentTabLow(i);
    }
    while (true)
    {
      return;
      String str2 = this.currentTab;
      if ("app".equals(str2))
      {
        i = 1;
        break;
      }
      i = 2;
      break;
      label60: setCurrentTab(i);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.TabbedAppBrowser
 * JD-Core Version:    0.6.0
 */