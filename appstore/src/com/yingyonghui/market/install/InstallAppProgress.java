package com.yingyonghui.market.install;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.IPackageDeleteObserver.Stub;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yingyonghui.market.OptionsMenu;

public class InstallAppProgress extends Activity
  implements View.OnClickListener
{
  public static final int FAILED = 0;
  public static final int SUCCEEDED = 1;
  private final int INSTALL_COMPLETE;
  private final String TAG;
  private int mAssetId;
  private Handler mHandler;
  private Button mOkButton;
  private View mOkPanel;
  private String mPackageName;
  private Uri mPackageURI;
  private ProgressBar mProgressBar;
  private volatile int mResultCode;
  private TextView mStatusTextView;

  public InstallAppProgress()
  {
    String str = InstallAppProgress.class.getSimpleName();
    this.TAG = str;
    this.mResultCode = -1;
    this.INSTALL_COMPLETE = 1;
    InstallAppProgress.1 local1 = new InstallAppProgress.1(this);
    this.mHandler = local1;
  }

  private void initSnippetForInstalledApp()
  {
    TextView localTextView = (TextView)findViewById(2131427392);
    String str = getIntent().getStringExtra("title");
    localTextView.setText(str);
    ImageButton localImageButton = (ImageButton)findViewById(2131427390);
    InstallAppProgress.2 local2 = new InstallAppProgress.2(this);
    localImageButton.setOnClickListener(local2);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyCode() == 4)
      if (this.mResultCode != -1);
    boolean bool;
    for (int i = 1; ; bool = dispatchKeyEvent(paramKeyEvent))
    {
      return i;
      int j = this.mResultCode;
      setResult(j);
    }
  }

  public void initView()
  {
    requestWindowFeature(1);
    setTheme(16973834);
    setContentView(2130903079);
    initSnippetForInstalledApp();
    TextView localTextView = (TextView)findViewById(2131427469);
    this.mStatusTextView = localTextView;
    this.mStatusTextView.setText(2131296276);
    ProgressBar localProgressBar = (ProgressBar)findViewById(2131427470);
    this.mProgressBar = localProgressBar;
    this.mProgressBar.setIndeterminate(1);
    View localView = findViewById(2131427329);
    this.mOkPanel = localView;
    Button localButton = (Button)findViewById(2131427330);
    this.mOkButton = localButton;
    this.mOkButton.setOnClickListener(this);
    this.mOkPanel.setVisibility(8);
    IWorksPackageManager localIWorksPackageManager = IWorksPackageManager.getInstance(this);
    Uri localUri = this.mPackageURI;
    String str = this.mPackageName;
    Handler localHandler = this.mHandler;
    int i = this.mAssetId;
    localIWorksPackageManager.installPackage(localUri, str, localHandler, i);
  }

  public void onClick(View paramView)
  {
    Button localButton = this.mOkButton;
    if (paramView == localButton)
    {
      int i = this.mResultCode;
      setResultAndFinish(i);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    Intent localIntent = getIntent();
    int i = localIntent.getIntExtra("_id", 0);
    this.mAssetId = i;
    String str = localIntent.getStringExtra("packageName");
    this.mPackageName = str;
    Uri localUri = localIntent.getData();
    this.mPackageURI = localUri;
    initView();
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

  void setResultAndFinish(int paramInt)
  {
    if (paramInt == 1)
      setResult(-1);
    finish();
  }

  class PackageDeleteObserver extends IPackageDeleteObserver.Stub
  {
    PackageDeleteObserver()
    {
    }

    public void packageDeleted(boolean paramBoolean)
    {
      Message localMessage = InstallAppProgress.this.mHandler.obtainMessage(1);
      if (paramBoolean);
      for (int i = 1; ; i = 0)
      {
        localMessage.arg1 = i;
        InstallAppProgress.this.mHandler.sendMessage(localMessage);
        return;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.InstallAppProgress
 * JD-Core Version:    0.6.0
 */