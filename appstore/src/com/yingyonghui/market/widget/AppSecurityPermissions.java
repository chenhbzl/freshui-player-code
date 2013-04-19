package com.yingyonghui.market.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dalvik.annotation.Signature;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AppSecurityPermissions
  implements View.OnClickListener
{
  private static final String TAG = "AppSecurityPermissions";
  private boolean localLOGV = 0;
  private Context mContext;
  private State mCurrentState;
  private Drawable mDangerousIcon;
  private LinearLayout mDangerousList;

  @Signature({"Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;"})
  private Map mDangerousMap;
  private String mDefaultGrpLabel;
  private String mDefaultGrpName = "DefaultGrp";
  private boolean mExpanded;

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/String;", "Ljava/lang/CharSequence;", ">;"})
  private HashMap mGroupLabelCache;
  private LayoutInflater mInflater;
  private View mNoPermsView;
  private LinearLayout mNonDangerousList;
  private Drawable mNormalIcon;

  @Signature({"Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;"})
  private Map mNormalMap;
  private String mPermFormat;

  @Signature({"Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;"})
  private List mPermsList;
  private LinearLayout mPermsView;
  private PackageManager mPm;
  private Drawable mShowMaxIcon;
  private Drawable mShowMinIcon;
  private View mShowMore;
  private ImageView mShowMoreIcon;
  private TextView mShowMoreText;

  public AppSecurityPermissions(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    PackageManager localPackageManager = this.mContext.getPackageManager();
    this.mPm = localPackageManager;
    ArrayList localArrayList = new ArrayList();
    this.mPermsList = localArrayList;
    HashSet localHashSet = new HashSet();
    try
    {
      PackageInfo localPackageInfo = this.mPm.getPackageInfo(paramString, 4096);
      if ((localPackageInfo.applicationInfo != null) && (localPackageInfo.applicationInfo.uid != -1))
      {
        int i = localPackageInfo.applicationInfo.uid;
        getAllUsedPermissions(i, localHashSet);
      }
      localIterator = localHashSet.iterator();
      if (!localIterator.hasNext())
        return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        Iterator localIterator;
        String str = "Could'nt retrieve permissions for package:" + paramString;
        Log.w("AppSecurityPermissions", str);
        continue;
        PermissionInfo localPermissionInfo = (PermissionInfo)localIterator.next();
        this.mPermsList.add(localPermissionInfo);
      }
    }
  }

  @Signature({"(", "Landroid/content/Context;", "Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;)V"})
  public AppSecurityPermissions(Context paramContext, List paramList)
  {
    this.mContext = paramContext;
    PackageManager localPackageManager = this.mContext.getPackageManager();
    this.mPm = localPackageManager;
    this.mPermsList = paramList;
  }

  public AppSecurityPermissions(Context paramContext, String[] paramArrayOfString, String paramString)
  {
    this.mContext = paramContext;
    PackageManager localPackageManager = this.mContext.getPackageManager();
    this.mPm = localPackageManager;
    ArrayList localArrayList = new ArrayList();
    this.mPermsList = localArrayList;
    HashSet localHashSet = new HashSet();
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0) && (paramArrayOfString.length > 0))
      extractPerms(paramArrayOfString, localHashSet);
    Iterator localIterator = localHashSet.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      PermissionInfo localPermissionInfo = (PermissionInfo)localIterator.next();
      this.mPermsList.add(localPermissionInfo);
    }
  }

  @Signature({"(", "Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;>;", "Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;)V"})
  private void aggregateGroupDescs(Map paramMap1, Map paramMap2)
  {
    if (paramMap1 == null);
    do
      return;
    while (paramMap2 == null);
    Iterator localIterator1 = paramMap1.keySet().iterator();
    label21: String str1;
    String str2;
    Iterator localIterator2;
    while (localIterator1.hasNext())
    {
      str1 = null;
      str2 = (String)localIterator1.next();
      List localList = (List)paramMap1.get(str2);
      if (localList == null)
        continue;
      localIterator2 = localList.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        if (str1 == null)
          break label21;
        if (this.localLOGV)
        {
          StringBuilder localStringBuilder = new StringBuilder("Group:").append(str2).append(" description:");
          String str3 = str1.toString();
          String str4 = str3;
          Log.i("AppSecurityPermissions", str4);
        }
        String str5 = str1.toString();
        paramMap2.put(str2, str5);
        break label21;
        break;
      }
      PermissionInfo localPermissionInfo = (PermissionInfo)localIterator2.next();
      PackageManager localPackageManager = this.mPm;
      CharSequence localCharSequence = localPermissionInfo.loadLabel(localPackageManager);
      str1 = formatPermissions(str1, localCharSequence);
    }
  }

  private String canonicalizeGroupDesc(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    for (String str = null; ; str = paramString)
    {
      return str;
      int i = paramString.length();
      int j = i - 1;
      if (paramString.charAt(j) != 46)
        continue;
      int k = i - 1;
      paramString = paramString.substring(0, k);
    }
  }

  private void displayNoPermissions()
  {
    this.mNoPermsView.setVisibility(0);
  }

  private void displayPermissions(boolean paramBoolean)
  {
    Map localMap;
    LinearLayout localLinearLayout;
    label18: Iterator localIterator;
    if (paramBoolean)
    {
      localMap = this.mDangerousMap;
      if (!paramBoolean)
        break label54;
      localLinearLayout = this.mDangerousList;
      localLinearLayout.removeAllViews();
      localIterator = localMap.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        localMap = this.mNormalMap;
        break;
        label54: localLinearLayout = this.mNonDangerousList;
        break label18;
      }
      String str1 = (String)localIterator.next();
      CharSequence localCharSequence = getGroupLabel(str1);
      String str2 = (String)localMap.get(str1);
      View localView = getPermissionItemView(localCharSequence, str2, paramBoolean);
      localLinearLayout.addView(localView);
    }
  }

  @Signature({"([", "Ljava/lang/String;", "Ljava/util/Set", "<", "Landroid/content/pm/PermissionInfo;", ">;)V"})
  private void extractPerms(String[] paramArrayOfString, Set paramSet)
  {
    int i = 0;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      return;
    int j = paramArrayOfString.length;
    while (i < j)
    {
      String str1 = paramArrayOfString[i];
      try
      {
        PermissionInfo localPermissionInfo = this.mPm.getPermissionInfo(str1, 0);
        if (localPermissionInfo != null)
          paramSet.add(localPermissionInfo);
        i += 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          String str2 = "Ignoring unknown permission:" + str1;
          Log.i("AppSecurityPermissions", str2);
        }
      }
    }
  }

  private String formatPermissions(String paramString, CharSequence paramCharSequence)
  {
    String str1;
    if (paramString == null)
      if (paramCharSequence == null)
        str1 = null;
    while (true)
    {
      return str1;
      str1 = paramCharSequence.toString();
      continue;
      paramString = canonicalizeGroupDesc(paramString);
      if (paramCharSequence == null)
      {
        str1 = paramString;
        continue;
      }
      String str2 = this.mPermFormat;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      String str3 = paramCharSequence.toString();
      arrayOfObject[1] = str3;
      str1 = String.format(str2, arrayOfObject);
    }
  }

  @Signature({"(I", "Ljava/util/Set", "<", "Landroid/content/pm/PermissionInfo;", ">;)V"})
  private void getAllUsedPermissions(int paramInt, Set paramSet)
  {
    String[] arrayOfString = this.mPm.getPackagesForUid(paramInt);
    if ((arrayOfString == null) || (arrayOfString.length == 0));
    while (true)
    {
      return;
      int i = arrayOfString.length;
      int j = 0;
      while (j < i)
      {
        String str = arrayOfString[j];
        getPermissionsForPackage(str, paramSet);
        j += 1;
      }
    }
  }

  private CharSequence getGroupLabel(String paramString)
  {
    Object localObject;
    if (paramString == null)
      localObject = this.mDefaultGrpLabel;
    while (true)
    {
      return localObject;
      CharSequence localCharSequence = (CharSequence)this.mGroupLabelCache.get(paramString);
      if (localCharSequence != null)
      {
        localObject = localCharSequence;
        continue;
      }
      try
      {
        PermissionGroupInfo localPermissionGroupInfo = this.mPm.getPermissionGroupInfo(paramString, 0);
        PackageManager localPackageManager = this.mPm;
        String str1 = localPermissionGroupInfo.loadLabel(localPackageManager).toString();
        this.mGroupLabelCache.put(paramString, str1);
        localObject = str1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        String str2 = "Invalid group name:" + paramString;
        Log.i("AppSecurityPermissions", str2);
        localObject = null;
      }
    }
  }

  private View getPermissionItemView(CharSequence paramCharSequence, String paramString, boolean paramBoolean)
  {
    View localView = this.mInflater.inflate(2130903041, null);
    Drawable localDrawable;
    TextView localTextView1;
    TextView localTextView2;
    if (paramBoolean)
    {
      localDrawable = this.mDangerousIcon;
      localTextView1 = (TextView)localView.findViewById(2131427338);
      localTextView2 = (TextView)localView.findViewById(2131427339);
      if (paramBoolean)
      {
        Resources localResources = this.mContext.getResources();
        int i = localResources.getColor(2131230728);
        localTextView1.setTextColor(i);
        int j = localResources.getColor(2131230729);
        localTextView2.setTextColor(j);
      }
      ((ImageView)localView.findViewById(2131427337)).setImageDrawable(localDrawable);
      if (paramCharSequence == null)
        break label140;
      localTextView1.setText(paramCharSequence);
      localTextView2.setText(paramString);
    }
    while (true)
    {
      return localView;
      localDrawable = this.mNormalIcon;
      break;
      label140: localTextView1.setText(paramString);
      localTextView2.setVisibility(8);
    }
  }

  @Signature({"(", "Ljava/lang/String;", "Ljava/util/Set", "<", "Landroid/content/pm/PermissionInfo;", ">;)V"})
  private void getPermissionsForPackage(String paramString, Set paramSet)
  {
    try
    {
      PackageInfo localPackageInfo = this.mPm.getPackageInfo(paramString, 4096);
      if ((localPackageInfo != null) && (localPackageInfo.requestedPermissions != null))
      {
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        extractPerms(arrayOfString, paramSet);
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        String str = "Could'nt retrieve permissions for package:" + paramString;
        Log.w("AppSecurityPermissions", str);
      }
    }
  }

  private boolean isDisplayablePermission(PermissionInfo paramPermissionInfo)
  {
    if ((paramPermissionInfo.protectionLevel == 1) || (paramPermissionInfo.protectionLevel == 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  @Signature({"(", "Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;)V"})
  private void setPermissions(List paramList)
  {
    HashMap localHashMap1 = new HashMap();
    this.mGroupLabelCache = localHashMap1;
    HashMap localHashMap2 = this.mGroupLabelCache;
    String str1 = this.mDefaultGrpName;
    String str2 = this.mDefaultGrpLabel;
    localHashMap2.put(str1, str2);
    HashMap localHashMap3 = new HashMap();
    this.mDangerousMap = localHashMap3;
    HashMap localHashMap4 = new HashMap();
    this.mNormalMap = localHashMap4;
    HashMap localHashMap5 = new HashMap();
    HashMap localHashMap6 = new HashMap();
    PackageManager localPackageManager = this.mPm;
    PermissionInfoComparator localPermissionInfoComparator = new PermissionInfoComparator();
    Object localObject;
    if ((paramList != null) && (paramList.size() > 0))
    {
      localObject = paramList.iterator();
      if (!((Iterator)localObject).hasNext())
      {
        Map localMap1 = this.mDangerousMap;
        aggregateGroupDescs(localHashMap5, localMap1);
        Map localMap2 = this.mNormalMap;
        aggregateGroupDescs(localHashMap6, localMap2);
      }
    }
    else
    {
      State localState1 = State.NO_PERMS;
      this.mCurrentState = localState1;
      if (this.mDangerousMap.size() <= 0)
        break label639;
      if (this.mNormalMap.size() <= 0)
        break label631;
      localObject = State.BOTH;
      label203: this.mCurrentState = ((State)localObject);
    }
    while (true)
    {
      if (this.localLOGV)
      {
        StringBuilder localStringBuilder1 = new StringBuilder("mCurrentState=");
        State localState2 = this.mCurrentState;
        String str3 = localState2;
        Log.i("AppSecurityPermissions", str3);
      }
      showPermissions();
      return;
      PermissionInfo localPermissionInfo = (PermissionInfo)((Iterator)localObject).next();
      if (this.localLOGV)
      {
        StringBuilder localStringBuilder2 = new StringBuilder("Processing permission:");
        String str4 = localPermissionInfo.name;
        String str5 = str4;
        Log.i("AppSecurityPermissions", str5);
      }
      if (!isDisplayablePermission(localPermissionInfo))
      {
        if (!this.localLOGV)
          break;
        StringBuilder localStringBuilder3 = new StringBuilder("Permission:");
        String str6 = localPermissionInfo.name;
        String str7 = str6 + " is not displayable";
        Log.i("AppSecurityPermissions", str7);
        break;
      }
      HashMap localHashMap7;
      if (localPermissionInfo.protectionLevel == 1)
      {
        localHashMap7 = localHashMap5;
        label394: if (localPermissionInfo.group != null)
          break label525;
      }
      List localList;
      label525: for (String str8 = this.mDefaultGrpName; ; str8 = localPermissionInfo.group)
      {
        if (this.localLOGV)
        {
          StringBuilder localStringBuilder4 = new StringBuilder("Permission:");
          String str9 = localPermissionInfo.name;
          String str10 = str9 + " belongs to group:" + str8;
          Log.i("AppSecurityPermissions", str10);
        }
        localList = (List)localHashMap7.get(str8);
        if (localList != null)
          break label535;
        ArrayList localArrayList = new ArrayList();
        localHashMap7.put(str8, localArrayList);
        localArrayList.add(localPermissionInfo);
        break;
        localHashMap7 = localHashMap6;
        break label394;
      }
      label535: int i = Collections.binarySearch(localList, localPermissionInfo, localPermissionInfoComparator);
      if (this.localLOGV)
      {
        StringBuilder localStringBuilder5 = new StringBuilder("idx=").append(i).append(", list.size=");
        int j = localList.size();
        String str11 = j;
        Log.i("AppSecurityPermissions", str11);
      }
      if (i >= 0)
        break;
      int k = -i - 1;
      localList.add(k, localPermissionInfo);
      break;
      label631: localObject = State.DANGEROUS_ONLY;
      break label203;
      label639: if (this.mNormalMap.size() <= 0)
        continue;
      State localState3 = State.NORMAL_ONLY;
      this.mCurrentState = localState3;
    }
  }

  private void showPermissions()
  {
    int[] arrayOfInt = $SWITCH_TABLE$com$yingyonghui$market$widget$AppSecurityPermissions$State();
    int i = this.mCurrentState.ordinal();
    switch (arrayOfInt[i])
    {
    default:
    case 1:
    case 2:
    case 3:
      while (true)
      {
        return;
        displayNoPermissions();
        continue;
        displayPermissions(1);
        continue;
        displayPermissions(0);
      }
    case 4:
    }
    displayPermissions(1);
    if (this.mExpanded)
    {
      displayPermissions(0);
      ImageView localImageView1 = this.mShowMoreIcon;
      Drawable localDrawable1 = this.mShowMaxIcon;
      localImageView1.setImageDrawable(localDrawable1);
      this.mShowMoreText.setText(2131296396);
      this.mNonDangerousList.setVisibility(0);
    }
    while (true)
    {
      this.mShowMore.setVisibility(0);
      break;
      ImageView localImageView2 = this.mShowMoreIcon;
      Drawable localDrawable2 = this.mShowMinIcon;
      localImageView2.setImageDrawable(localDrawable2);
      this.mShowMoreText.setText(2131296397);
      this.mNonDangerousList.setVisibility(8);
    }
  }

  public int getPermissionCount()
  {
    return this.mPermsList.size();
  }

  public View getPermissionsView()
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    this.mInflater = localLayoutInflater;
    LinearLayout localLinearLayout1 = (LinearLayout)this.mInflater.inflate(2130903042, null);
    this.mPermsView = localLinearLayout1;
    View localView1 = this.mPermsView.findViewById(2131427342);
    this.mShowMore = localView1;
    ImageView localImageView = (ImageView)this.mShowMore.findViewById(2131427343);
    this.mShowMoreIcon = localImageView;
    TextView localTextView = (TextView)this.mShowMore.findViewById(2131427344);
    this.mShowMoreText = localTextView;
    LinearLayout localLinearLayout2 = (LinearLayout)this.mPermsView.findViewById(2131427341);
    this.mDangerousList = localLinearLayout2;
    LinearLayout localLinearLayout3 = (LinearLayout)this.mPermsView.findViewById(2131427345);
    this.mNonDangerousList = localLinearLayout3;
    View localView2 = this.mPermsView.findViewById(2131427340);
    this.mNoPermsView = localView2;
    this.mShowMore.setClickable(1);
    this.mShowMore.setOnClickListener(this);
    this.mShowMore.setFocusable(1);
    this.mShowMore.setBackgroundResource(17301602);
    String str1 = this.mContext.getString(2131296394);
    this.mDefaultGrpLabel = str1;
    String str2 = this.mContext.getString(2131296395);
    this.mPermFormat = str2;
    Drawable localDrawable1 = this.mContext.getResources().getDrawable(2130837556);
    this.mNormalIcon = localDrawable1;
    Drawable localDrawable2 = this.mContext.getResources().getDrawable(2130837534);
    this.mDangerousIcon = localDrawable2;
    Drawable localDrawable3 = this.mContext.getResources().getDrawable(2130837527);
    this.mShowMaxIcon = localDrawable3;
    Drawable localDrawable4 = this.mContext.getResources().getDrawable(2130837528);
    this.mShowMinIcon = localDrawable4;
    List localList = this.mPermsList;
    setPermissions(localList);
    return this.mPermsView;
  }

  public void onClick(View paramView)
  {
    if (this.localLOGV)
    {
      StringBuilder localStringBuilder = new StringBuilder("mExpanded=");
      boolean bool = this.mExpanded;
      String str = bool;
      Log.i("AppSecurityPermissions", str);
    }
    if (this.mExpanded);
    for (int i = 0; ; i = 1)
    {
      this.mExpanded = i;
      showPermissions();
      return;
    }
  }

  @Signature({"Ljava/lang/Object;", "Ljava/util/Comparator", "<", "Landroid/content/pm/PermissionInfo;", ">;"})
  class PermissionInfoComparator
    implements Comparator
  {
    private final Collator sCollator;

    PermissionInfoComparator()
    {
      Collator localCollator = Collator.getInstance();
      this.sCollator = localCollator;
    }

    public final int compare(PermissionInfo paramPermissionInfo1, PermissionInfo paramPermissionInfo2)
    {
      PackageManager localPackageManager1 = AppSecurityPermissions.this;
      CharSequence localCharSequence1 = paramPermissionInfo1.loadLabel(localPackageManager1);
      PackageManager localPackageManager2 = AppSecurityPermissions.this;
      CharSequence localCharSequence2 = paramPermissionInfo2.loadLabel(localPackageManager2);
      return this.sCollator.compare(localCharSequence1, localCharSequence2);
    }
  }

  @Signature({"Ljava/lang/Enum", "<", "Lcom/yingyonghui/market/widget/AppSecurityPermissions$State;", ">;"})
  enum State
  {
    static
    {
      DANGEROUS_ONLY = new State("DANGEROUS_ONLY", 1);
      NORMAL_ONLY = new State("NORMAL_ONLY", 2);
      BOTH = new State("BOTH", 3);
      State[] arrayOfState = new State[4];
      State localState1 = NO_PERMS;
      arrayOfState[0] = localState1;
      State localState2 = DANGEROUS_ONLY;
      arrayOfState[1] = localState2;
      State localState3 = NORMAL_ONLY;
      arrayOfState[2] = localState3;
      State localState4 = BOTH;
      arrayOfState[3] = localState4;
      ENUM$VALUES = arrayOfState;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.widget.AppSecurityPermissions
 * JD-Core Version:    0.6.0
 */