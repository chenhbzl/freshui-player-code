package com.yingyonghui.market.install;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.yingyonghui.market.OptionsMenu;
import com.yingyonghui.market.util.GlobalUtil;

public class UninstallerActivity extends Activity
  implements View.OnClickListener, DialogInterface.OnCancelListener
{
  private static final int DLG_APP_NOT_FOUND = 1;
  private static final int DLG_BASE = 0;
  private static final int DLG_UNINSTALL_FAILED = 2;
  private static final String TAG = "UninstallerActivity";
  private ApplicationInfo mAppInfo;
  private int mAssetId;
  private Button mCancel;
  private Button mOk;
  PackageManager mPm;

  private void initSnippetForInstalledApp()
  {
    TextView localTextView = (TextView)findViewById(2131427392);
    String str = getIntent().getStringExtra("title");
    localTextView.setText(str);
    ImageButton localImageButton = (ImageButton)findViewById(2131427390);
    UninstallerActivity.3 local3 = new UninstallerActivity.3(this);
    localImageButton.setOnClickListener(local3);
  }

  private void startUninstallProgress()
  {
    if (GlobalUtil.isSystemApp(getPackageManager()))
    {
      Intent localIntent1 = new Intent("android.intent.action.VIEW");
      Intent localIntent2 = getIntent();
      int i = this.mAssetId;
      localIntent1.putExtra("_id", i);
      String str1 = localIntent2.getStringExtra("title");
      localIntent1.putExtra("title", str1);
      ApplicationInfo localApplicationInfo = this.mAppInfo;
      localIntent1.putExtra("application_info", localApplicationInfo);
      localIntent1.setClass(this, UninstallAppProgress.class);
      startActivity(localIntent1);
    }
    while (true)
    {
      finish();
      return;
      String str2 = this.mAppInfo.packageName;
      Uri localUri = Uri.fromParts("package", str2, null);
      Intent localIntent3 = new Intent("android.intent.action.DELETE", localUri);
      try
      {
        startActivity(localIntent3);
        setResult(-1);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
      }
    }
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    finish();
  }

  public void onClick(View paramView)
  {
    Button localButton1 = this.mOk;
    if (paramView == localButton1)
      startUninstallProgress();
    while (true)
    {
      return;
      Button localButton2 = this.mCancel;
      if (paramView != localButton2)
        continue;
      finish();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    Intent localIntent = getIntent();
    int i = localIntent.getIntExtra("_id", 0);
    this.mAssetId = i;
    String str1 = localIntent.getStringExtra("package");
    if ((str1 == null) || (this.mAssetId == 0))
    {
      String str2 = "Invalid package name:" + str1;
      Log.e("UninstallerActivity", str2);
      showDialog(1);
    }
    TextView localTextView;
    while (true)
    {
      return;
      PackageManager localPackageManager = getPackageManager();
      this.mPm = localPackageManager;
      int j = 0;
      try
      {
        ApplicationInfo localApplicationInfo = this.mPm.getApplicationInfo(str1, 8192);
        this.mAppInfo = localApplicationInfo;
        if ((this.mAppInfo == null) || (j != 0))
        {
          String str3 = "Invalid application:" + str1;
          Log.e("UninstallerActivity", str3);
          showDialog(1);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
          j = 1;
        requestWindowFeature(1);
        setTheme(16973834);
        setContentView(2130903116);
        localTextView = (TextView)findViewById(2131427539);
        if (this.mAppInfo.flags == 0)
          break label269;
      }
    }
    localTextView.setText(2131296431);
    while (true)
    {
      initSnippetForInstalledApp();
      Button localButton1 = (Button)findViewById(2131427330);
      this.mOk = localButton1;
      Button localButton2 = (Button)findViewById(2131427332);
      this.mCancel = localButton2;
      this.mOk.setOnClickListener(this);
      this.mCancel.setOnClickListener(this);
      break;
      label269: localTextView.setText(2131296433);
    }
  }

  public Dialog onCreateDialog(int paramInt)
  {
    AlertDialog localAlertDialog;
    switch (paramInt)
    {
    default:
      localAlertDialog = null;
    case 1:
    case 2:
    }
    while (true)
    {
      return localAlertDialog;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setTitle(2131296429).setIcon(17301543).setMessage(2131296430);
      String str1 = getString(2131296428);
      UninstallerActivity.1 local1 = new UninstallerActivity.1(this);
      localAlertDialog = localBuilder1.setNeutralButton(str1, local1).create();
      continue;
      PackageManager localPackageManager = this.mPm;
      ApplicationInfo localApplicationInfo = this.mAppInfo;
      CharSequence localCharSequence = localPackageManager.getApplicationLabel(localApplicationInfo);
      Object[] arrayOfObject = new Object[1];
      String str2 = localCharSequence.toString();
      arrayOfObject[0] = str2;
      String str3 = getString(2131296436, arrayOfObject);
      AlertDialog.Builder localBuilder2 = new AlertDialog.Builder(this).setTitle(2131296435).setIcon(17301543).setMessage(str3);
      String str4 = getString(2131296428);
      UninstallerActivity.2 local2 = new UninstallerActivity.2(this);
      localAlertDialog = localBuilder2.setNeutralButton(str4, local2).create();
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    boolean bool = onCreateOptionsMenu(paramMenu);
    if (bool)
      OptionsMenu.onCreateOptionsMenu(paramMenu);
    return bool;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.UninstallerActivity
 * JD-Core Version:    0.6.0
 */