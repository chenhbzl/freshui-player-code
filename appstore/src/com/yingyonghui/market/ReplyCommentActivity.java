package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.TextView;
import com.yingyonghui.market.model.Comment;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DateUtil;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.GlobalUtil;
import com.yingyonghui.market.util.StringUtil;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ReplyCommentActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int ACTION_SEND_COMMENT = 0;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private static final int DIALOG_PROGRESS_SENDING = 200;
  private String author;
  private String comment;
  private long commentTime;
  private String deviceName;
  private int errorType;
  private int id;
  private boolean isDelete;
  private boolean isMine;
  private TextView mAuthor;
  private TextView mComment;
  private TextView mCommentText;
  private Request mCurrentRequest;
  private Handler mHandler;
  private IMarketService mMarketService;
  private RatingBar mRating;
  private int parentId;
  private double rating;
  private String type;

  private String getComment(String paramString)
  {
    if (paramString == null);
    try
    {
      for (str = ""; ; str = URLDecoder.decode(paramString))
        return str;
    }
    catch (Exception localException)
    {
      while (true)
        String str = StringUtil.makeSafe(paramString);
    }
  }

  private String getErrorType(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default:
      str = getString(2131296298);
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return str;
      str = getString(2131296293);
      continue;
      str = getString(2131296294);
      continue;
      str = getString(2131296295);
      continue;
      str = getString(2131296296);
      continue;
      str = getString(2131296297);
    }
  }

  private void initHandlerInUIThread()
  {
    ReplyCommentActivity.2 local2 = new ReplyCommentActivity.2(this);
    this.mHandler = local2;
  }

  private void sendComment()
  {
    String str1 = StringUtil.strip(this.mCommentText.getText().toString());
    if (StringUtil.isEmpty(str1))
      GlobalUtil.longToast(this, 2131296280);
    while (true)
    {
      return;
      String str2 = URLEncoder.encode(str1);
      Request localRequest = new Request(0L, 268500998);
      Object[] arrayOfObject = new Object[7];
      Integer localInteger1 = Integer.valueOf(this.parentId);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(this.id);
      arrayOfObject[1] = localInteger2;
      arrayOfObject[2] = str2;
      Integer localInteger3 = Integer.valueOf((int)this.rating);
      arrayOfObject[3] = localInteger3;
      String str3 = DeviceUtil.getIMEI(this);
      arrayOfObject[4] = str3;
      String str4 = DeviceUtil.getDeviceModel();
      arrayOfObject[5] = str4;
      String str5 = DeviceUtil.getSDKVersion();
      arrayOfObject[6] = str5;
      localRequest.setData(arrayOfObject);
      ReplyCommentActivity.1 local1 = new ReplyCommentActivity.1(this, localRequest);
      localRequest.addObserver(local1);
      this.mCurrentRequest = localRequest;
      this.mMarketService.sendComment(localRequest);
      showDialog(200);
    }
  }

  private void setupViews()
  {
    TextView localTextView1 = (TextView)findViewById(2131427428);
    this.mAuthor = localTextView1;
    this.mAuthor.setTextColor(-16777216);
    RatingBar localRatingBar1 = (RatingBar)findViewById(2131427377);
    this.mRating = localRatingBar1;
    TextView localTextView2 = (TextView)findViewById(2131427432);
    this.mComment = localTextView2;
    if (this.isMine)
    {
      StringBuilder localStringBuilder1 = new StringBuilder("<font color=\"#01457e\">");
      String str1 = getString(2131296519);
      String str2 = str1 + "</font>";
      this.author = str2;
    }
    while (true)
    {
      String str3 = this.type;
      String str4 = Comment.TYPE_COMMENT;
      TextView localTextView3;
      StringBuilder localStringBuilder2;
      String str6;
      label207: label370: View localView;
      if (StringUtil.equals(str3, str4))
      {
        RatingBar localRatingBar2 = this.mRating;
        float f = (float)this.rating;
        localRatingBar2.setRating(f);
        this.mRating.setVisibility(0);
        localTextView3 = this.mAuthor;
        String str5 = String.valueOf(this.author);
        localStringBuilder2 = new StringBuilder(str5).append("<font color=\"#999999\">").append("(");
        if (StringUtil.isEmptyOrWhitespace(this.deviceName))
        {
          str6 = getString(2131296518);
          StringBuilder localStringBuilder3 = localStringBuilder2.append(str6).append(") ");
          String str7 = getString(2131296512);
          Spanned localSpanned1 = Html.fromHtml(str7 + "</font>");
          localTextView3.setText(localSpanned1);
          if (!this.isDelete)
            break label683;
          TextView localTextView4 = this.mComment;
          int i = getResources().getColor(2131230726);
          localTextView4.setTextColor(i);
          TextView localTextView5 = this.mComment;
          String str8 = getString(2131296517);
          localTextView5.setText(str8);
          Drawable localDrawable = getResources().getDrawable(2130837536);
          int j = localDrawable.getIntrinsicWidth();
          int k = localDrawable.getIntrinsicHeight();
          localDrawable.setBounds(0, 0, j, k);
          this.mComment.setCompoundDrawables(localDrawable, null, null, null);
          this.mRating.setVisibility(8);
          TextView localTextView6 = (TextView)findViewById(2131427430);
          String str9 = DateUtil.format(this.commentTime, "yyyy-MM-dd HH:mm");
          localTextView6.setText(str9);
          ((TextView)findViewById(2131427392)).setText(2131296510);
          localView = findViewById(2131427333);
          if (DeviceUtil.getSDKVersionInt() <= 4);
        }
      }
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
        TextView localTextView7 = (TextView)findViewById(2131427336);
        this.mCommentText = localTextView7;
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
          TextView localTextView8 = this.mCommentText;
          Object[] arrayOfObject2 = new Object[1];
          Boolean localBoolean2 = Boolean.valueOf(1);
          arrayOfObject2[0] = localBoolean2;
          localMethod2.invoke(localTextView8, arrayOfObject2);
          findViewById(2131427390).setOnClickListener(this);
          findViewById(2131427330).setOnClickListener(this);
          findViewById(2131427332).setOnClickListener(this);
          return;
          StringBuilder localStringBuilder4 = new StringBuilder("<font color=\"black\">");
          String str10 = this.author;
          String str11 = str10 + "</font>";
          this.author = str11;
          continue;
          str6 = this.deviceName;
          break label207;
          label683: TextView localTextView9 = this.mComment;
          String str12 = this.comment;
          String str13 = getComment(str12);
          localTextView9.setText(str13);
          break label370;
          String str14 = this.type;
          String str15 = Comment.TYPE_ERROR;
          if (StringUtil.equals(str14, str15))
          {
            this.mRating.setVisibility(8);
            localTextView3 = this.mAuthor;
            String str16 = String.valueOf(this.author);
            localStringBuilder2 = new StringBuilder(str16).append("<font color=\"#999999\">").append("(");
            if (StringUtil.isEmptyOrWhitespace(this.deviceName));
            for (str6 = getString(2131296518); ; str6 = this.deviceName)
            {
              StringBuilder localStringBuilder5 = localStringBuilder2.append(str6).append(") ");
              String str17 = getString(2131296511);
              Spanned localSpanned2 = Html.fromHtml(str17 + "</font>");
              localTextView3.setText(localSpanned2);
              TextView localTextView10 = this.mComment;
              int m = this.errorType;
              String str18 = String.valueOf(getErrorType(m));
              StringBuilder localStringBuilder6 = new StringBuilder(str18).append(":");
              String str19 = this.comment;
              String str20 = getComment(str19);
              String str21 = str20;
              localTextView10.setText(str21);
              break;
            }
          }
          this.mRating.setVisibility(8);
          localTextView3 = this.mAuthor;
          String str22 = String.valueOf(this.author);
          localStringBuilder2 = new StringBuilder(str22).append("<font color=\"#999999\">").append("(");
          if (StringUtil.isEmptyOrWhitespace(this.deviceName));
          for (str6 = getString(2131296518); ; str6 = this.deviceName)
          {
            StringBuilder localStringBuilder7 = localStringBuilder2.append(str6).append(") ");
            String str23 = getString(2131296510);
            Spanned localSpanned3 = Html.fromHtml(str23 + "</font>");
            localTextView3.setText(localSpanned3);
            TextView localTextView11 = this.mComment;
            String str24 = this.comment;
            String str25 = getComment(str24);
            localTextView11.setText(str25);
            break;
          }
          localThrowable1.printStackTrace();
        }
        catch (Throwable localThrowable2)
        {
          while (true)
            localThrowable2.printStackTrace();
        }
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
    Intent localIntent = getIntent();
    int i = localIntent.getIntExtra("_id", 0);
    this.id = i;
    String str1 = localIntent.getStringExtra("author");
    this.author = str1;
    String str2 = localIntent.getStringExtra("deviceName");
    this.deviceName = str2;
    double d = localIntent.getDoubleExtra("rating", 0.0D);
    this.rating = d;
    long l = localIntent.getLongExtra("commentTime", 0L);
    this.commentTime = l;
    String str3 = localIntent.getStringExtra("type");
    this.type = str3;
    String str4 = localIntent.getStringExtra("comment");
    this.comment = str4;
    int j = localIntent.getIntExtra("errorType", 0);
    this.errorType = j;
    int k = localIntent.getIntExtra("parentId", 0);
    this.parentId = k;
    boolean bool1 = localIntent.getBooleanExtra("isDelete", 0);
    this.isDelete = bool1;
    boolean bool2 = localIntent.getBooleanExtra("isMine", 0);
    this.isMine = bool2;
    if (StringUtil.isEmptyOrWhitespace(this.author))
    {
      String str5 = getString(2131296381);
      this.author = str5;
    }
    setContentView(2130903095);
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
      ReplyCommentActivity.3 local3 = new ReplyCommentActivity.3(this);
      AlertDialog.Builder localBuilder2 = localBuilder1.setPositiveButton(2131296379, local3);
      ReplyCommentActivity.4 local4 = new ReplyCommentActivity.4(this);
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
 * Qualified Name:     com.yingyonghui.market.ReplyCommentActivity
 * JD-Core Version:    0.6.0
 */