package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.StringUtil;

public class ReportErrorActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int ACTION_REPORT_ERROR = 0;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final int DIALOG_PROGRESS_SENDING = 200;
  private int mAssetId;
  private Request mCurrentRequest;
  private TextView mErrorContent;
  private RadioGroup mErrorTypeGroup;
  private Handler mHandler;
  private IMarketService mMarketService;

  private void initHandlerInUIThread()
  {
    ReportErrorActivity.2 local2 = new ReportErrorActivity.2(this);
    this.mHandler = local2;
  }

  private void reportError()
  {
    int i = this.mErrorTypeGroup.getCheckedRadioButtonId();
    if (i < 0)
      GlobalUtil.longToast(this, 2131296299);
    while (true)
    {
      return;
      int j;
      if (i == 2131427497)
        j = 0;
      String str1;
      while (true)
      {
        str1 = StringUtil.strip(this.mErrorContent.getText().toString());
        if ((j != 5) || (!StringUtil.isEmpty(str1)))
          break label113;
        GlobalUtil.longToast(this, 2131296505);
        break;
        if (i == 2131427498)
        {
          j = 1;
          continue;
        }
        if (i == 2131427499)
        {
          j = 2;
          continue;
        }
        if (i == 2131427500)
        {
          j = 3;
          continue;
        }
        if (i == 2131427501)
        {
          j = 4;
          continue;
        }
        j = 5;
      }
      label113: String str2 = DeviceUtil.getIMEI(this);
      String str3 = DeviceUtil.getDeviceModel();
      Request localRequest = new Request(0L, 65548);
      Object[] arrayOfObject = new Object[5];
      Integer localInteger1 = Integer.valueOf(this.mAssetId);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(j);
      arrayOfObject[1] = localInteger2;
      arrayOfObject[2] = str3;
      arrayOfObject[3] = str2;
      arrayOfObject[4] = str1;
      localRequest.setData(arrayOfObject);
      ReportErrorActivity.1 local1 = new ReportErrorActivity.1(this, localRequest);
      localRequest.addObserver(local1);
      this.mCurrentRequest = localRequest;
      this.mMarketService.reportError(localRequest);
      showDialog(200);
    }
  }

  private void setupViews()
  {
    ((TextView)findViewById(2131427392)).setText(2131296344);
    TextView localTextView = (TextView)findViewById(2131427503);
    this.mErrorContent = localTextView;
    RadioGroup localRadioGroup = (RadioGroup)findViewById(2131427496);
    this.mErrorTypeGroup = localRadioGroup;
    findViewById(2131427390).setOnClickListener(this);
    findViewById(2131427330).setOnClickListener(this);
    findViewById(2131427332).setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131427330:
    case 2131427332:
    case 2131427390:
    }
    while (true)
    {
      return;
      reportError();
      continue;
      finish();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    setTheme(16973834);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    int i = getIntent().getIntExtra("_id", 0);
    this.mAssetId = i;
    setContentView(2130903097);
    initHandlerInUIThread();
    setupViews();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    Object localObject;
    switch (paramInt)
    {
    default:
      localObject = null;
    case 100:
    case 200:
    }
    while (true)
    {
      return localObject;
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(this).setIcon(17301543).setTitle(2131296369).setMessage(2131296370);
      ReportErrorActivity.3 local3 = new ReportErrorActivity.3(this);
      AlertDialog.Builder localBuilder2 = localBuilder1.setPositiveButton(2131296379, local3);
      ReportErrorActivity.4 local4 = new ReportErrorActivity.4(this);
      localObject = localBuilder2.setNegativeButton(2131296373, local4).create();
      continue;
      ProgressDialog localProgressDialog = new ProgressDialog(this);
      localProgressDialog.setTitle(null);
      String str = getString(2131296286);
      localProgressDialog.setMessage(str);
      localProgressDialog.setIndeterminate(1);
      localProgressDialog.setCancelable(1);
      localProgressDialog.setOnCancelListener(null);
      localObject = localProgressDialog;
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
 * Qualified Name:     com.yingyonghui.market.ReportErrorActivity
 * JD-Core Version:    0.6.0
 */