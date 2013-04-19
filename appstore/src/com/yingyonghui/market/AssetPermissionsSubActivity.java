package com.yingyonghui.market;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.StringUtil;
import com.yingyonghui.market.widget.AppSecurityPermissions;

public class AssetPermissionsSubActivity extends Activity
  implements View.OnClickListener
{
  private int mAssetId;
  private Button mCancelButton;
  private Handler mHandler;
  private IMarketService mMarketService;
  private Button mOkButton;
  private String[] mPermissionRequestList;
  private ImageView mThumnailView;

  private void addDownloadAndInstallRequest()
  {
    Request localRequest1 = new Request(0L, 65541);
    Intent localIntent = getIntent();
    int i = localIntent.getIntExtra("size", 0);
    String str1 = localIntent.getStringExtra("package");
    String str2 = localIntent.getStringExtra("title");
    Object[] arrayOfObject1 = new Object[4];
    Integer localInteger1 = Integer.valueOf(this.mAssetId);
    arrayOfObject1[0] = localInteger1;
    Integer localInteger2 = Integer.valueOf(i);
    arrayOfObject1[1] = localInteger2;
    arrayOfObject1[2] = str1;
    arrayOfObject1[3] = str2;
    localRequest1.setData(arrayOfObject1);
    Handler localHandler = this.mHandler;
    localRequest1.addWifiObserver(localHandler);
    this.mMarketService.getAppContentStream(localRequest1);
    Request localRequest2 = new Request(0L, 65553);
    Object[] arrayOfObject2 = new Object[3];
    arrayOfObject2[0] = "update";
    arrayOfObject2[1] = "AssetPermissions";
    Integer localInteger3 = Integer.valueOf(this.mAssetId);
    arrayOfObject2[2] = localInteger3;
    localRequest2.setData(arrayOfObject2);
    MarketService.getServiceInstance(this).getInstallUpdateLog(localRequest2);
  }

  private void initPermissionViews()
  {
    String[] arrayOfString1 = getIntent().getStringArrayExtra("permission");
    this.mPermissionRequestList = arrayOfString1;
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131427438);
    String[] arrayOfString2 = this.mPermissionRequestList;
    AppSecurityPermissions localAppSecurityPermissions = new AppSecurityPermissions(this, arrayOfString2, null);
    LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.findViewById(2131427439);
    View localView = localAppSecurityPermissions.getPermissionsView();
    localLinearLayout2.addView(localView);
  }

  private void setupViews()
  {
    Button localButton1 = (Button)findViewById(2131427330);
    this.mOkButton = localButton1;
    Button localButton2 = (Button)findViewById(2131427332);
    this.mCancelButton = localButton2;
    this.mOkButton.setVisibility(0);
    this.mCancelButton.setVisibility(0);
    this.mOkButton.setOnClickListener(this);
    this.mCancelButton.setOnClickListener(this);
    TextView localTextView = (TextView)findViewById(2131427392);
    String str = getIntent().getStringExtra("title");
    localTextView.setText(str);
    ImageButton localImageButton = (ImageButton)findViewById(2131427390);
    AssetPermissionsSubActivity.1 local1 = new AssetPermissionsSubActivity.1(this);
    localImageButton.setOnClickListener(local1);
    initPermissionViews();
  }

  public void onClick(View paramView)
  {
    Button localButton1 = this.mOkButton;
    if (paramView == localButton1)
    {
      addDownloadAndInstallRequest();
      setResult(-1);
      finish();
    }
    while (true)
    {
      return;
      Button localButton2 = this.mCancelButton;
      if (paramView != localButton2)
        continue;
      setResult(0);
      finish();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    requestWindowFeature(1);
    setTheme(16973834);
    int i = getIntent().getIntExtra("_id", 0);
    this.mAssetId = i;
    String str1 = getIntent().getStringExtra("package");
    String str2 = getPackageName();
    if (StringUtil.equals(str1, str2))
    {
      addDownloadAndInstallRequest();
      setResult(-1);
      finish();
    }
    setContentView(2130903068);
    setupViews();
    Handler localHandler = WifiDownloadManager.initHandlerInUIThread(this);
    this.mHandler = localHandler;
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
 * Qualified Name:     com.yingyonghui.market.AssetPermissionsSubActivity
 * JD-Core Version:    0.6.0
 */