package com.yingyonghui.market;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.widget.AppSecurityPermissions;
import java.util.ArrayList;

public class WarningActivity extends Activity
  implements View.OnClickListener
{
  int category = 55537;
  String categoryLabel = "";
  String[] dangerousPermissions;
  String fromPage = "";
  Asset mAsset = null;
  private Handler mWifiHandler;
  int periodType = 55537;
  int rankingType = 55537;

  private void addDownloadAndInstallRequest()
  {
    Request localRequest2;
    String str3;
    if (this.mAsset != null)
    {
      Request localRequest1 = new Request(0L, 65541);
      Handler localHandler1 = WifiDownloadManager.initHandlerInUIThread(this);
      this.mWifiHandler = localHandler1;
      int i = this.mAsset.size;
      String str1 = this.mAsset.pkgName;
      String str2 = this.mAsset.title;
      Object[] arrayOfObject1 = new Object[4];
      Integer localInteger1 = Integer.valueOf(this.mAsset._id);
      arrayOfObject1[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject1[1] = localInteger2;
      arrayOfObject1[2] = str1;
      arrayOfObject1[3] = str2;
      localRequest1.setData(arrayOfObject1);
      Handler localHandler2 = this.mWifiHandler;
      localRequest1.addWifiObserver(localHandler2);
      MarketService.getServiceInstance(this).getAppContentStream(localRequest1);
      localRequest2 = new Request(0L, 65553);
      if (this.mAsset.installed != 2)
        break label284;
      str3 = "update";
      if (this.fromPage != "Category")
        break label291;
      Object[] arrayOfObject2 = new Object[7];
      arrayOfObject2[0] = str3;
      String str4 = this.fromPage;
      arrayOfObject2[1] = str4;
      Integer localInteger3 = Integer.valueOf(this.category);
      arrayOfObject2[2] = localInteger3;
      Integer localInteger4 = Integer.valueOf(this.rankingType);
      arrayOfObject2[3] = localInteger4;
      Integer localInteger5 = Integer.valueOf(55537);
      arrayOfObject2[4] = localInteger5;
      Integer localInteger6 = Integer.valueOf(this.mAsset._id);
      arrayOfObject2[5] = localInteger6;
      String str5 = this.categoryLabel;
      arrayOfObject2[6] = str5;
      localRequest2.setData(arrayOfObject2);
    }
    while (true)
    {
      MarketService.getServiceInstance(this).getInstallUpdateLog(localRequest2);
      return;
      label284: str3 = "install";
      break;
      label291: if (this.fromPage == "Ranking")
      {
        Object[] arrayOfObject3 = new Object[6];
        arrayOfObject3[0] = str3;
        String str6 = this.fromPage;
        arrayOfObject3[1] = str6;
        Integer localInteger7 = Integer.valueOf(55537);
        arrayOfObject3[2] = localInteger7;
        Integer localInteger8 = Integer.valueOf(55537);
        arrayOfObject3[3] = localInteger8;
        Integer localInteger9 = Integer.valueOf(this.periodType);
        arrayOfObject3[4] = localInteger9;
        Integer localInteger10 = Integer.valueOf(this.mAsset._id);
        arrayOfObject3[5] = localInteger10;
        localRequest2.setData(arrayOfObject3);
        continue;
      }
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = str3;
      String str7 = this.fromPage;
      arrayOfObject4[1] = str7;
      Integer localInteger11 = Integer.valueOf(this.mAsset._id);
      arrayOfObject4[2] = localInteger11;
      localRequest2.setData(arrayOfObject4);
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131427460)
    {
      addDownloadAndInstallRequest();
      MobclickAgent.onEvent(this, "PermissionWarning", "Continue");
      if (!((CheckBox)findViewById(2131427464)).isChecked())
      {
        SharedPreferences.Editor localEditor1 = getSharedPreferences("com.yingyonghui.market_preferences", 0).edit();
        localEditor1.putBoolean("checkbox_permission_alert", 0);
        localEditor1.commit();
      }
      finish();
    }
    while (true)
    {
      return;
      if (paramView.getId() == 2131427461)
      {
        MobclickAgent.onEvent(this, "PermissionWarning", "Cancel");
        if (!((CheckBox)findViewById(2131427464)).isChecked())
        {
          SharedPreferences.Editor localEditor2 = getSharedPreferences("com.yingyonghui.market_preferences", 0).edit();
          localEditor2.putBoolean("checkbox_permission_alert", 0);
          localEditor2.commit();
        }
        finish();
        continue;
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    WarningActivity localWarningActivity1 = this;
    int i = 2130903074;
    localWarningActivity1.setContentView(i);
    Asset localAsset1 = new Asset();
    this.mAsset = localAsset1;
    Bundle localBundle1 = getIntent().getExtras();
    Asset localAsset2 = this.mAsset;
    Bundle localBundle2 = localBundle1;
    String str1 = "permission";
    String[] arrayOfString1 = localBundle2.getStringArray(str1);
    localAsset2.permissions = arrayOfString1;
    Asset localAsset3 = this.mAsset;
    Bundle localBundle3 = localBundle1;
    String str2 = "_id";
    int j = localBundle3.getInt(str2);
    localAsset3._id = j;
    Asset localAsset4 = this.mAsset;
    Bundle localBundle4 = localBundle1;
    String str3 = "pkgName";
    String str4 = localBundle4.getString(str3);
    localAsset4.pkgName = str4;
    Asset localAsset5 = this.mAsset;
    Bundle localBundle5 = localBundle1;
    String str5 = "title";
    String str6 = localBundle5.getString(str5);
    localAsset5.title = str6;
    Asset localAsset6 = this.mAsset;
    Bundle localBundle6 = localBundle1;
    String str7 = "size";
    int k = localBundle6.getInt(str7);
    localAsset6.size = k;
    Asset localAsset7 = this.mAsset;
    Bundle localBundle7 = localBundle1;
    String str8 = "installed";
    int m = localBundle7.getInt(str8);
    localAsset7.installed = m;
    Bundle localBundle8 = localBundle1;
    String str9 = "from";
    String str10 = localBundle8.getString(str9);
    this.fromPage = str10;
    String str11 = this.fromPage;
    String str12 = "Category";
    ArrayList localArrayList1;
    int i3;
    if (str11 == str12)
    {
      Bundle localBundle9 = localBundle1;
      String str13 = "category";
      int n = localBundle9.getInt(str13);
      this.category = n;
      Bundle localBundle10 = localBundle1;
      String str14 = "rankingType";
      int i1 = localBundle10.getInt(str14);
      this.rankingType = i1;
      Bundle localBundle11 = localBundle1;
      String str15 = "category_label";
      String str16 = localBundle11.getString(str15);
      this.categoryLabel = str16;
      if ((this.mAsset.title != null) && (this.mAsset.title.length() > 0))
      {
        String str17 = getResources().getString(2131296491);
        Object[] arrayOfObject1 = new Object[1];
        String str18 = this.mAsset.title;
        arrayOfObject1[0] = str18;
        String str19 = str17;
        Object[] arrayOfObject2 = arrayOfObject1;
        String str20 = String.format(str19, arrayOfObject2);
        WarningActivity localWarningActivity2 = this;
        int i2 = 2131427458;
        TextView localTextView = (TextView)localWarningActivity2.findViewById(i2);
        String str21 = str20;
        localTextView.setText(str21);
      }
      localArrayList1 = new ArrayList();
      if (this.mAsset.permissions != null)
        i3 = 0;
    }
    while (true)
    {
      int i4 = this.mAsset.permissions.length;
      int i5 = i3;
      int i6 = i4;
      if (i5 >= i6)
      {
        String[] arrayOfString2 = new String[localArrayList1.size()];
        ArrayList localArrayList2 = localArrayList1;
        String[] arrayOfString3 = arrayOfString2;
        String[] arrayOfString4 = (String[])localArrayList2.toArray(arrayOfString3);
        this.dangerousPermissions = arrayOfString4;
        WarningActivity localWarningActivity3 = this;
        int i7 = 2131427462;
        LinearLayout localLinearLayout1 = (LinearLayout)localWarningActivity3.findViewById(i7);
        AppSecurityPermissions localAppSecurityPermissions1 = new com/yingyonghui/market/widget/AppSecurityPermissions;
        String[] arrayOfString5 = this.dangerousPermissions;
        AppSecurityPermissions localAppSecurityPermissions2 = localAppSecurityPermissions1;
        WarningActivity localWarningActivity4 = this;
        String[] arrayOfString6 = arrayOfString5;
        String str22 = null;
        localAppSecurityPermissions2.<init>(localWarningActivity4, arrayOfString6, str22);
        LinearLayout localLinearLayout2 = localLinearLayout1;
        int i8 = 2131427463;
        LinearLayout localLinearLayout3 = (LinearLayout)localLinearLayout2.findViewById(i8);
        View localView1 = localAppSecurityPermissions1.getPermissionsView();
        LinearLayout localLinearLayout4 = localLinearLayout3;
        View localView2 = localView1;
        localLinearLayout4.addView(localView2);
        WarningActivity localWarningActivity5 = this;
        int i9 = 2131427460;
        Button localButton1 = (Button)localWarningActivity5.findViewById(i9);
        WarningActivity localWarningActivity6 = this;
        int i10 = 2131427461;
        Button localButton2 = (Button)localWarningActivity6.findViewById(i10);
        Button localButton3 = localButton1;
        WarningActivity localWarningActivity7 = this;
        localButton3.setOnClickListener(localWarningActivity7);
        Button localButton4 = localButton2;
        WarningActivity localWarningActivity8 = this;
        localButton4.setOnClickListener(localWarningActivity8);
        return;
        String str23 = this.fromPage;
        String str24 = "Ranking";
        if (str23 != str24)
          break;
        Bundle localBundle12 = localBundle1;
        String str25 = "periodType";
        int i11 = localBundle12.getInt(str25);
        this.periodType = i11;
        break;
      }
      String str26 = this.mAsset.permissions[i3];
      String str27 = "android.permission.PROCESS_OUTGOING_CALLS";
      String str28 = str26;
      if (!str27.equals(str28))
      {
        String str29 = "android.permission.READ_CONTACTS";
        String str30 = str26;
        if (!str29.equals(str30))
        {
          String str31 = "android.permission.READ_SMS";
          String str32 = str26;
          if (!str31.equals(str32))
          {
            String str33 = "android.permission.SEND_SMS";
            String str34 = str26;
            if (!str33.equals(str34))
            {
              String str35 = "android.permission.CALL_PHONE";
              String str36 = str26;
              if (!str35.equals(str36))
              {
                String str37 = "android.permission.CALL_PRIVILEGED";
                String str38 = str26;
                if (!str37.equals(str38))
                {
                  String str39 = "android.permission.CHANGE_CONFIGURATION";
                  String str40 = str26;
                  if (!str39.equals(str40))
                    break label876;
                }
              }
            }
          }
        }
      }
      localArrayList1.add(str26);
      label876: i3 += 1;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.WarningActivity
 * JD-Core Version:    0.6.0
 */