package com.yingyonghui.market;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import java.text.NumberFormat;

public class TrafficWarningDialog extends Activity
  implements View.OnClickListener
{
  private static NumberFormat instance = NumberFormat.getInstance();
  Asset mAsset = null;
  private Handler mWifiHandler;

  private void addDownloadAndInstallRequest()
  {
    if (this.mAsset != null)
    {
      Request localRequest = new Request(0L, 65541);
      Handler localHandler1 = WifiDownloadManager.initHandlerInUIThread(this);
      this.mWifiHandler = localHandler1;
      int i = this.mAsset.size;
      String str1 = this.mAsset.pkgName;
      String str2 = this.mAsset.title;
      Object[] arrayOfObject = new Object[5];
      Integer localInteger1 = Integer.valueOf(this.mAsset._id);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject[1] = localInteger2;
      arrayOfObject[2] = str1;
      arrayOfObject[3] = str2;
      Boolean localBoolean = Boolean.valueOf(0);
      arrayOfObject[4] = localBoolean;
      localRequest.setData(arrayOfObject);
      Handler localHandler2 = this.mWifiHandler;
      localRequest.addWifiObserver(localHandler2);
      MarketService.getServiceInstance(this).getAppContentStream(localRequest);
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131427460)
    {
      addDownloadAndInstallRequest();
      MobclickAgent.onEvent(this, "PermissionWarning", "Continue");
      finish();
    }
    while (true)
    {
      return;
      if (paramView.getId() == 2131427461)
      {
        MobclickAgent.onEvent(this, "PermissionWarning", "Cancel");
        finish();
        continue;
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    setContentView(2130903073);
    Asset localAsset1 = new Asset();
    this.mAsset = localAsset1;
    Bundle localBundle = getIntent().getExtras();
    Asset localAsset2 = this.mAsset;
    int i = localBundle.getInt("_id");
    localAsset2._id = i;
    Asset localAsset3 = this.mAsset;
    String str1 = localBundle.getString("pkgName");
    localAsset3.pkgName = str1;
    Asset localAsset4 = this.mAsset;
    String str2 = localBundle.getString("title");
    localAsset4.title = str2;
    Asset localAsset5 = this.mAsset;
    int j = localBundle.getInt("size");
    localAsset5.size = j;
    Asset localAsset6 = this.mAsset;
    int k = localBundle.getInt("installed");
    localAsset6.installed = k;
    String str3;
    if ((this.mAsset.title != null) && (this.mAsset.title.length() > 0))
    {
      if (this.mAsset.size <= 1232896)
        break label325;
      instance.setMaximumFractionDigits(2);
      NumberFormat localNumberFormat1 = instance;
      double d = this.mAsset.size / 1048576.0F;
      str3 = String.valueOf(localNumberFormat1.format(d));
    }
    label325: String str8;
    for (String str4 = str3 + "M"; ; str4 = str8 + "K")
    {
      String str5 = getResources().getString(2131296499);
      Object[] arrayOfObject = new Object[2];
      String str6 = this.mAsset.title;
      arrayOfObject[0] = str6;
      arrayOfObject[1] = str4;
      String str7 = String.format(str5, arrayOfObject);
      ((TextView)findViewById(2131427458)).setText(str7);
      Button localButton1 = (Button)findViewById(2131427460);
      Button localButton2 = (Button)findViewById(2131427461);
      localButton1.setOnClickListener(this);
      localButton2.setOnClickListener(this);
      return;
      instance.setMaximumFractionDigits(0);
      NumberFormat localNumberFormat2 = instance;
      long l = this.mAsset.size / 1024;
      str8 = String.valueOf(localNumberFormat2.format(l));
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.TrafficWarningDialog
 * JD-Core Version:    0.6.0
 */