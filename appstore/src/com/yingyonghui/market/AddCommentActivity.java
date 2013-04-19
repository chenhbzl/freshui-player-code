package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.StringUtil;
import java.lang.reflect.Method;
import java.net.URLEncoder;

public class AddCommentActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int ACTION_SEND_COMMENT = 0;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final int DIALOG_PROGRESS_SENDING = 200;
  private int mAssetId;
  private TextView mCommentText;
  private Request mCurrentRequest;
  private Handler mHandler;
  private IMarketService mMarketService;
  private RatingBar mRatingBar;
  private String[] mRatingText;

  private void initHandlerInUIThread()
  {
    AddCommentActivity.3 local3 = new AddCommentActivity.3(this);
    this.mHandler = local3;
  }

  private void sendComment()
  {
    int i = (int)this.mRatingBar.getRating();
    if (i == 0)
      GlobalUtil.longToast(this, 2131296279);
    while (true)
    {
      return;
      String str1 = URLEncoder.encode(StringUtil.strip(this.mCommentText.getText().toString()));
      Request localRequest = new Request(0L, 65547);
      Object[] arrayOfObject = new Object[6];
      Integer localInteger1 = Integer.valueOf(this.mAssetId);
      arrayOfObject[0] = localInteger1;
      arrayOfObject[1] = str1;
      Integer localInteger2 = Integer.valueOf(i);
      arrayOfObject[2] = localInteger2;
      String str2 = DeviceUtil.getIMEI(this);
      arrayOfObject[3] = str2;
      String str3 = DeviceUtil.getDeviceModel();
      arrayOfObject[4] = str3;
      String str4 = DeviceUtil.getSDKVersion();
      arrayOfObject[5] = str4;
      localRequest.setData(arrayOfObject);
      AddCommentActivity.2 local2 = new AddCommentActivity.2(this, localRequest);
      localRequest.addObserver(local2);
      this.mCurrentRequest = localRequest;
      this.mMarketService.sendComment(localRequest);
      showDialog(200);
    }
  }

  private void setupViews()
  {
    ((TextView)findViewById(2131427392)).setText(2131296406);
    View localView = findViewById(2131427333);
    if (DeviceUtil.getSDKVersionInt() > 4);
    try
    {
      Class localClass1 = localView.getClass();
      Class[] arrayOfClass1 = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass1[0] = localClass2;
      Method localMethod1 = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass1);
      Object[] arrayOfObject1 = new Object[1];
      Boolean localBoolean1 = Boolean.valueOf(1);
      arrayOfObject1[0] = localBoolean1;
      localMethod1.invoke(localView, arrayOfObject1);
      TextView localTextView1 = (TextView)findViewById(2131427336);
      this.mCommentText = localTextView1;
      if (DeviceUtil.getSDKVersionInt() <= 4);
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        Class localClass3 = this.mCommentText.getClass();
        Class[] arrayOfClass2 = new Class[1];
        Class localClass4 = Boolean.TYPE;
        arrayOfClass2[0] = localClass4;
        Method localMethod2 = localClass3.getMethod("setScrollbarFadingEnabled", arrayOfClass2);
        TextView localTextView2 = this.mCommentText;
        Object[] arrayOfObject2 = new Object[1];
        Boolean localBoolean2 = Boolean.valueOf(1);
        arrayOfObject2[0] = localBoolean2;
        localMethod2.invoke(localTextView2, arrayOfObject2);
        TextView localTextView3 = (TextView)findViewById(2131427335);
        RatingBar localRatingBar1 = (RatingBar)findViewById(2131427334);
        this.mRatingBar = localRatingBar1;
        RatingBar localRatingBar2 = this.mRatingBar;
        AddCommentActivity.1 local1 = new AddCommentActivity.1(this, localTextView3);
        localRatingBar2.setOnRatingBarChangeListener(local1);
        RatingBar localRatingBar3 = this.mRatingBar;
        float f = getIntent().getIntExtra("rating", 0);
        localRatingBar3.setRating(f);
        TextView localTextView4 = this.mCommentText;
        String str = getIntent().getStringExtra("comment");
        localTextView4.setText(str);
        ((ImageButton)findViewById(2131427390)).setOnClickListener(this);
        ((Button)findViewById(2131427330)).setOnClickListener(this);
        ((Button)findViewById(2131427332)).setOnClickListener(this);
        return;
        localThrowable1.printStackTrace();
      }
      catch (Throwable localThrowable2)
      {
        while (true)
          localThrowable2.printStackTrace();
      }
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131427332:
    case 2131427390:
    case 2131427330:
    }
    while (true)
    {
      return;
      finish();
      continue;
      sendComment();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    MarketService localMarketService = MarketService.getServiceInstance(this);
    this.mMarketService = localMarketService;
    int i = getIntent().getIntExtra("_id", 0);
    this.mAssetId = i;
    setContentView(2130903040);
    initHandlerInUIThread();
    String[] arrayOfString = getResources().getStringArray(2131165186);
    this.mRatingText = arrayOfString;
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
      AddCommentActivity.4 local4 = new AddCommentActivity.4(this);
      AlertDialog.Builder localBuilder2 = localBuilder1.setPositiveButton(2131296379, local4);
      AddCommentActivity.5 local5 = new AddCommentActivity.5(this);
      localObject = localBuilder2.setNegativeButton(2131296373, local5).create();
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
 * Qualified Name:     com.yingyonghui.market.AddCommentActivity
 * JD-Core Version:    0.6.0
 */