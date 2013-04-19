package com.yingyonghui.market;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.model.Comment;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.MarketService;
import com.yingyonghui.market.online.Request;
import com.yingyonghui.market.util.DateUtil;
import com.yingyonghui.market.util.DeviceUtil;
import com.yingyonghui.market.util.StringUtil;
import dalvik.annotation.Signature;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends Activity
  implements View.OnClickListener
{
  private static final int ACTION_LIST_COMMENTS = 0;
  private static final int ACTION_NETWORK_ERROR = 1;
  private static final int ACTIVITY_ADD_COMMENT = 300;
  private static final int COUNT_PER_TIME = 5;
  private static final int DIALOG_NETWORK_ERROR = 100;
  private boolean isShowCommentTip;
  private final BroadcastReceiver mApplicationsReceiver;
  private AssetDetail mAssetDetail;
  private int mAssetId;
  private Button mBtnAddComment;
  private Button mBtnRemoveComment;
  private Button mBtnReportError;
  private boolean mBusy;
  private Request mCurrentRequest;
  private Button mDummyButton;
  private View mEmpty;
  private View mFooterView;
  private Handler mHandler;
  private View mHeaderView;
  private ReviewAdapter mListAdapter;
  private ListView mListView;
  private View mLoadingIndicator;
  private IMarketService mMarketService;
  private Comment mMyComment;
  private TextView mNotInstall;
  private boolean mReachEnd = 0;
  private AbsListView.OnScrollListener scrollListener;
  private int startIndex = 0;

  public CommentsActivity()
  {
    CommentsActivity.1 local1 = new CommentsActivity.1(this);
    this.mApplicationsReceiver = local1;
    CommentsActivity.2 local2 = new CommentsActivity.2(this);
    this.scrollListener = local2;
    this.isShowCommentTip = 0;
  }

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

  private void inflateCommentList()
  {
    if (this.mReachEnd);
    while (true)
    {
      return;
      Request localRequest = new Request(0L, 65540);
      Object[] arrayOfObject = new Object[5];
      Integer localInteger1 = Integer.valueOf(this.mAssetId);
      arrayOfObject[0] = localInteger1;
      Integer localInteger2 = Integer.valueOf(this.startIndex);
      arrayOfObject[1] = localInteger2;
      Integer localInteger3 = Integer.valueOf(5);
      arrayOfObject[2] = localInteger3;
      Integer localInteger4 = Integer.valueOf(DeviceUtil.getSDKVersionInt());
      arrayOfObject[3] = localInteger4;
      String str = DeviceUtil.getIMEI(this);
      arrayOfObject[4] = str;
      localRequest.setData(arrayOfObject);
      CommentsActivity.5 local5 = new CommentsActivity.5(this, localRequest);
      localRequest.addObserver(local5);
      this.mCurrentRequest = localRequest;
      this.mMarketService.getCategory(localRequest);
    }
  }

  private void initHandlerInUIThread()
  {
    CommentsActivity.4 local4 = new CommentsActivity.4(this);
    this.mHandler = local4;
  }

  private void registerIntentReceivers()
  {
    IntentFilter localIntentFilter1 = new IntentFilter();
    localIntentFilter1.addAction("send_comment_detail");
    BroadcastReceiver localBroadcastReceiver1 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver1, localIntentFilter1);
    IntentFilter localIntentFilter2 = new IntentFilter();
    localIntentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter2.addDataScheme("package");
    BroadcastReceiver localBroadcastReceiver2 = this.mApplicationsReceiver;
    registerReceiver(localBroadcastReceiver2, localIntentFilter2);
  }

  private void requestCommentDetail()
  {
    Intent localIntent = new Intent("request_comment_detail");
    sendBroadcast(localIntent);
  }

  private void setupReview()
  {
    if ((this.mAssetDetail != null) && (this.mListView != null))
    {
      TextView localTextView = (TextView)findViewById(2131427437);
      this.mNotInstall = localTextView;
      if (this.mAssetDetail.installed != 0)
        break label49;
      this.mNotInstall.setVisibility(0);
    }
    while (true)
    {
      return;
      label49: this.mNotInstall.setVisibility(8);
    }
  }

  private void setupViews()
  {
    ListView localListView1 = (ListView)findViewById(16908298);
    this.mListView = localListView1;
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    Button localButton1 = (Button)findViewById(2131427331);
    this.mDummyButton = localButton1;
    Button localButton2 = (Button)findViewById(2131427356);
    this.mBtnAddComment = localButton2;
    Button localButton3 = (Button)findViewById(2131427357);
    this.mBtnRemoveComment = localButton3;
    Button localButton4 = (Button)findViewById(2131427365);
    this.mBtnReportError = localButton4;
    this.mBtnAddComment.setOnClickListener(this);
    this.mBtnRemoveComment.setOnClickListener(this);
    this.mBtnReportError.setOnClickListener(this);
    if (DeviceUtil.getSDKVersionInt() > 4);
    try
    {
      Class localClass1 = this.mListView.getClass();
      Class[] arrayOfClass = new Class[1];
      Class localClass2 = Boolean.TYPE;
      arrayOfClass[0] = localClass2;
      Method localMethod = localClass1.getMethod("setScrollbarFadingEnabled", arrayOfClass);
      ListView localListView2 = this.mListView;
      Object[] arrayOfObject = new Object[1];
      Boolean localBoolean = Boolean.valueOf(1);
      arrayOfObject[0] = localBoolean;
      localMethod.invoke(localListView2, arrayOfObject);
      View localView1 = LayoutInflater.from(this).inflate(2130903065, null);
      this.mHeaderView = localView1;
      this.mHeaderView.setEnabled(1);
      ListView localListView3 = this.mListView;
      View localView2 = this.mHeaderView;
      localListView3.addHeaderView(localView2);
      View localView3 = findViewById(2131427468);
      this.mLoadingIndicator = localView3;
      View localView4 = findViewById(2131427407);
      this.mEmpty = localView4;
      TextView localTextView = (TextView)findViewById(2131427402);
      localTextView.setText(2131296410);
      localTextView.setTextSize(25.0F);
      View localView5 = LayoutInflater.from(this).inflate(2130903057, null);
      this.mFooterView = localView5;
      Button localButton5 = (Button)this.mFooterView.findViewById(2131427414);
      CommentsActivity.3 local3 = new CommentsActivity.3(this);
      localButton5.setOnClickListener(local3);
      ListView localListView4 = this.mListView;
      View localView6 = this.mFooterView;
      localListView4.addFooterView(localView6, null, 0);
      ListView localListView5 = this.mListView;
      AbsListView.OnScrollListener localOnScrollListener = this.scrollListener;
      localListView5.setOnScrollListener(localOnScrollListener);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private void showSendCommentTip()
  {
    if ((((AssetInfoActivity)getParent()).getCurrentTab() == 1) && (!this.isShowCommentTip))
    {
      this.isShowCommentTip = 1;
      Toast localToast = new Toast(this);
      ImageView localImageView = new ImageView(this);
      localImageView.setBackgroundResource(2130837684);
      localToast.setView(localImageView);
      float f = DeviceUtil.getScreenDensity(this);
      int i = (int)(50.0F * f);
      localToast.setGravity(81, 0, i);
      localToast.setDuration(0);
      localToast.show();
    }
  }

  private void unregisterIntentReceivers()
  {
    BroadcastReceiver localBroadcastReceiver = this.mApplicationsReceiver;
    unregisterReceiver(localBroadcastReceiver);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 300:
    case 400:
    case 401:
    case 402:
    }
    while (true)
    {
      return;
      if ((this.mAssetDetail == null) || (paramInt2 != -1))
        continue;
      setupReview();
      continue;
      if (paramInt2 != -1)
        continue;
      if (this.mReachEnd)
      {
        this.mReachEnd = 0;
        ListView localListView = this.mListView;
        View localView = this.mFooterView;
        localListView.addFooterView(localView, null, 0);
      }
      this.mListAdapter = null;
      this.startIndex = 0;
      inflateCommentList();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131427365:
    case 2131427356:
    case 2131427357:
    }
    while (true)
    {
      return;
      Intent localIntent1 = new Intent(this, ReportErrorActivity.class);
      int i = this.mAssetId;
      localIntent1.putExtra("_id", i);
      startActivityForResult(localIntent1, 400);
      continue;
      Intent localIntent2 = new Intent(this, AddCommentActivity.class);
      int j = this.mAssetId;
      localIntent2.putExtra("_id", j);
      startActivityForResult(localIntent2, 401);
      continue;
      if (this.mMyComment == null)
        continue;
      Request localRequest = new Request(0L, 300);
      Object[] arrayOfObject = new Object[3];
      Integer localInteger = Integer.valueOf(this.mMyComment.commentId);
      arrayOfObject[0] = localInteger;
      String str1 = DeviceUtil.getDeviceModel();
      arrayOfObject[1] = str1;
      String str2 = DeviceUtil.getIMEI(this);
      arrayOfObject[2] = str2;
      localRequest.setData(arrayOfObject);
      CommentsActivity.8 local8 = new CommentsActivity.8(this, localRequest);
      localRequest.addObserver(local8);
      this.mCurrentRequest = localRequest;
      this.mMarketService.deleteComment(localRequest);
      showDialog(200);
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
    setContentView(2130903066);
    initHandlerInUIThread();
    setupViews();
    requestCommentDetail();
    registerIntentReceivers();
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
      CommentsActivity.6 local6 = new CommentsActivity.6(this);
      AlertDialog.Builder localBuilder2 = localBuilder1.setPositiveButton(2131296379, local6);
      CommentsActivity.7 local7 = new CommentsActivity.7(this);
      localObject = localBuilder2.setNegativeButton(2131296373, local7).create();
      continue;
      ProgressDialog localProgressDialog = new ProgressDialog(this);
      localProgressDialog.setTitle(null);
      String str = getString(2131296289);
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

  protected void onDestroy()
  {
    unregisterIntentReceivers();
    onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = onOptionsItemSelected(paramMenuItem);
    OptionsMenu.onOptionsItemSelected(this, paramMenuItem);
    return bool;
  }

  @Signature({"Landroid/widget/ArrayAdapter", "<", "Lcom/yingyonghui/market/model/Comment;", ">;"})
  public class ReviewAdapter extends ArrayAdapter
  {
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener mListener;

    @Signature({"(", "Landroid/content/Context;", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Comment;", ">;)V"})
    public ReviewAdapter(Context paramArrayList, ArrayList arg3)
    {
      super(0, localList);
      CommentsActivity.ReviewAdapter.1 local1 = new CommentsActivity.ReviewAdapter.1(this);
      this.mListener = local1;
      LayoutInflater localLayoutInflater = (LayoutInflater)paramArrayList.getSystemService("layout_inflater");
      this.mLayoutInflater = localLayoutInflater;
    }

    private View fillCommentReply(Comment paramComment, int paramInt1, int paramInt2, int paramInt3)
    {
      View localView = this.mLayoutInflater.inflate(2130903096, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131427428);
      String str2;
      StringBuilder localStringBuilder2;
      if (paramComment.isMine)
      {
        StringBuilder localStringBuilder1 = new StringBuilder("<font color=\"#01457e\">");
        String str1 = CommentsActivity.this.getString(2131296519);
        str2 = str1 + "</font>";
        String str3 = String.valueOf(str2);
        localStringBuilder2 = new StringBuilder(str3).append("<font color=\"#999999\">").append("(");
        if (!StringUtil.isEmptyOrWhitespace(paramComment.deviceName))
          break label352;
      }
      label352: for (String str4 = CommentsActivity.this.getString(2131296518); ; str4 = paramComment.deviceName)
      {
        StringBuilder localStringBuilder3 = localStringBuilder2.append(str4).append(") ");
        String str5 = CommentsActivity.this.getString(2131296510);
        Spanned localSpanned = Html.fromHtml(str5 + "</font>");
        localTextView1.setText(localSpanned);
        TextView localTextView2 = (TextView)localView.findViewById(2131427432);
        CommentsActivity localCommentsActivity = CommentsActivity.this;
        String str6 = paramComment.comment;
        String str7 = localCommentsActivity.getComment(str6);
        localTextView2.setText(str7);
        TextView localTextView3 = (TextView)localView.findViewById(2131427430);
        String str8 = DateUtil.format(paramComment.commentTime, "yyyy-MM-dd HH:mm");
        localTextView3.setText(str8);
        TextView localTextView4 = (TextView)localView.findViewById(2131427431);
        View.OnClickListener localOnClickListener = this.mListener;
        localTextView4.setOnClickListener(localOnClickListener);
        String str9 = String.valueOf(paramInt1);
        String str10 = str9 + "-" + paramInt2 + "-" + paramInt3;
        localTextView4.setTag(str10);
        return localView;
        StringBuilder localStringBuilder4 = new StringBuilder("<font color=\"black\">");
        String str11 = paramComment.author;
        str2 = str11 + "</font>";
        break;
      }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Comment localComment1 = (Comment)getItem(paramInt);
      CommentsActivity.ViewHolder localViewHolder1;
      label238: String str5;
      label493: ArrayList localArrayList;
      label711: int i11;
      int i12;
      if (paramView == null)
      {
        String str1 = localComment1.type;
        String str2 = Comment.TYPE_ERROR;
        if (StringUtil.equals(str1, str2))
        {
          LayoutInflater localLayoutInflater1 = this.mLayoutInflater;
          int i = 2130903063;
          ViewGroup localViewGroup1 = paramViewGroup;
          int j = 0;
          paramView = localLayoutInflater1.inflate(i, localViewGroup1, j);
          localViewHolder1 = new CommentsActivity.ViewHolder();
          View localView1 = paramView;
          int k = 2131427428;
          TextView localTextView1 = (TextView)localView1.findViewById(k);
          localViewHolder1.author = localTextView1;
          View localView2 = paramView;
          int m = 2131427430;
          TextView localTextView2 = (TextView)localView2.findViewById(m);
          localViewHolder1.time = localTextView2;
          View localView3 = paramView;
          int n = 2131427432;
          TextView localTextView3 = (TextView)localView3.findViewById(n);
          localViewHolder1.comment = localTextView3;
          View localView4 = paramView;
          int i1 = 2131427433;
          LinearLayout localLinearLayout1 = (LinearLayout)localView4.findViewById(i1);
          localViewHolder1.replyContainer = localLinearLayout1;
          View localView5 = paramView;
          int i2 = 2131427431;
          TextView localTextView4 = (TextView)localView5.findViewById(i2);
          localViewHolder1.btnReply = localTextView4;
          TextView localTextView5 = localViewHolder1.btnReply;
          View.OnClickListener localOnClickListener1 = this.mListener;
          localTextView5.setOnClickListener(localOnClickListener1);
          View localView6 = paramView;
          CommentsActivity.ViewHolder localViewHolder2 = localViewHolder1;
          localView6.setTag(localViewHolder2);
          TextView localTextView6 = localViewHolder1.btnReply;
          String str3 = String.valueOf(localComment1.commentId);
          StringBuilder localStringBuilder1 = new StringBuilder(str3).append("-");
          int i3 = paramInt;
          String str4 = i3;
          localTextView6.setTag(str4);
          localViewHolder1 = (CommentsActivity.ViewHolder)paramView.getTag();
          localViewHolder1.replyContainer.removeAllViews();
          str5 = null;
          if (!localComment1.isMine)
            break label1359;
          StringBuilder localStringBuilder2 = new StringBuilder("<font color=\"#01457e\">");
          String str6 = CommentsActivity.this.getString(2131296519);
          str5 = str6 + "</font>";
          localViewHolder1.comment.setTextColor(-16777216);
          localViewHolder1.comment.setCompoundDrawables(null, null, null, null);
          String str7 = localComment1.type;
          String str8 = Comment.TYPE_COMMENT;
          if (!StringUtil.equals(str7, str8))
            break label1549;
          RatingBar localRatingBar1 = localViewHolder1.rating;
          float f = (float)localComment1.rating;
          localRatingBar1.setRating(f);
          localViewHolder1.author.setTextColor(-16777216);
          localTextView7 = localViewHolder1.author;
          String str9 = String.valueOf(str5);
          localStringBuilder3 = new StringBuilder(str9).append("<font color=\"#999999\">").append("(");
          if (!StringUtil.isEmptyOrWhitespace(localComment1.deviceName))
            break label1416;
          str10 = CommentsActivity.this.getString(2131296518);
          StringBuilder localStringBuilder4 = localStringBuilder3.append(str10).append(") ");
          String str11 = CommentsActivity.this.getString(2131296512);
          Spanned localSpanned1 = Html.fromHtml(str11 + "</font>");
          localTextView7.setText(localSpanned1);
          if (!localComment1.isDelete)
            break label1426;
          TextView localTextView8 = localViewHolder1.comment;
          int i4 = CommentsActivity.this.getResources().getColor(2131230726);
          localTextView8.setTextColor(i4);
          TextView localTextView9 = localViewHolder1.comment;
          String str12 = CommentsActivity.this.getString(2131296517);
          localTextView9.setText(str12);
          Drawable localDrawable1 = CommentsActivity.this.getResources().getDrawable(2130837536);
          int i5 = localDrawable1.getIntrinsicWidth();
          int i6 = localDrawable1.getIntrinsicHeight();
          Drawable localDrawable2 = localDrawable1;
          int i7 = 0;
          int i8 = 0;
          int i9 = i5;
          int i10 = i6;
          localDrawable2.setBounds(i7, i8, i9, i10);
          TextView localTextView10 = localViewHolder1.comment;
          Drawable localDrawable3 = localDrawable1;
          Drawable localDrawable4 = null;
          Drawable localDrawable5 = null;
          Drawable localDrawable6 = null;
          localTextView10.setCompoundDrawables(localDrawable3, localDrawable4, localDrawable5, localDrawable6);
          localViewHolder1.rating.setVisibility(8);
          localArrayList = localComment1.children;
          if ((localArrayList != null) && (localArrayList.size() > 0))
          {
            i11 = 0;
            i12 = localArrayList.size();
            label741: if (i11 < i12)
              break label1474;
          }
        }
      }
      label1359: String str20;
      label1416: label1426: label1474: String str21;
      label1549: 
      do
      {
        TextView localTextView11 = localViewHolder1.time;
        String str13 = DateUtil.format(localComment1.commentTime, "yyyy-MM-dd HH:mm");
        localTextView11.setText(str13);
        return paramView;
        LayoutInflater localLayoutInflater2 = this.mLayoutInflater;
        int i13 = 2130903064;
        ViewGroup localViewGroup2 = paramViewGroup;
        int i14 = 0;
        paramView = localLayoutInflater2.inflate(i13, localViewGroup2, i14);
        localViewHolder1 = new CommentsActivity.ViewHolder();
        View localView7 = paramView;
        int i15 = 2131427377;
        RatingBar localRatingBar2 = (RatingBar)localView7.findViewById(i15);
        localViewHolder1.rating = localRatingBar2;
        break;
        String str14 = localComment1.type;
        String str15 = Comment.TYPE_ERROR;
        if ((StringUtil.equals(str14, str15)) && (paramView.getId() != 2131427427))
        {
          LayoutInflater localLayoutInflater3 = this.mLayoutInflater;
          int i16 = 2130903063;
          ViewGroup localViewGroup3 = paramViewGroup;
          int i17 = 0;
          paramView = localLayoutInflater3.inflate(i16, localViewGroup3, i17);
          localViewHolder1 = new CommentsActivity.ViewHolder();
          View localView8 = paramView;
          int i18 = 2131427428;
          TextView localTextView12 = (TextView)localView8.findViewById(i18);
          localViewHolder1.author = localTextView12;
          View localView9 = paramView;
          int i19 = 2131427430;
          TextView localTextView13 = (TextView)localView9.findViewById(i19);
          localViewHolder1.time = localTextView13;
          View localView10 = paramView;
          int i20 = 2131427432;
          TextView localTextView14 = (TextView)localView10.findViewById(i20);
          localViewHolder1.comment = localTextView14;
          View localView11 = paramView;
          int i21 = 2131427433;
          LinearLayout localLinearLayout2 = (LinearLayout)localView11.findViewById(i21);
          localViewHolder1.replyContainer = localLinearLayout2;
          View localView12 = paramView;
          int i22 = 2131427431;
          TextView localTextView15 = (TextView)localView12.findViewById(i22);
          localViewHolder1.btnReply = localTextView15;
          TextView localTextView16 = localViewHolder1.btnReply;
          View.OnClickListener localOnClickListener2 = this.mListener;
          localTextView16.setOnClickListener(localOnClickListener2);
          View localView13 = paramView;
          CommentsActivity.ViewHolder localViewHolder3 = localViewHolder1;
          localView13.setTag(localViewHolder3);
          break label238;
        }
        String str16 = localComment1.type;
        String str17 = Comment.TYPE_COMMENT;
        if ((StringUtil.equals(str16, str17)) && (paramView.getId() != 2131427434))
        {
          LayoutInflater localLayoutInflater4 = this.mLayoutInflater;
          int i23 = 2130903064;
          ViewGroup localViewGroup4 = paramViewGroup;
          int i24 = 0;
          paramView = localLayoutInflater4.inflate(i23, localViewGroup4, i24);
          localViewHolder1 = new CommentsActivity.ViewHolder();
          View localView14 = paramView;
          int i25 = 2131427428;
          TextView localTextView17 = (TextView)localView14.findViewById(i25);
          localViewHolder1.author = localTextView17;
          View localView15 = paramView;
          int i26 = 2131427430;
          TextView localTextView18 = (TextView)localView15.findViewById(i26);
          localViewHolder1.time = localTextView18;
          View localView16 = paramView;
          int i27 = 2131427432;
          TextView localTextView19 = (TextView)localView16.findViewById(i27);
          localViewHolder1.comment = localTextView19;
          View localView17 = paramView;
          int i28 = 2131427377;
          RatingBar localRatingBar3 = (RatingBar)localView17.findViewById(i28);
          localViewHolder1.rating = localRatingBar3;
          View localView18 = paramView;
          int i29 = 2131427433;
          LinearLayout localLinearLayout3 = (LinearLayout)localView18.findViewById(i29);
          localViewHolder1.replyContainer = localLinearLayout3;
          View localView19 = paramView;
          int i30 = 2131427431;
          TextView localTextView20 = (TextView)localView19.findViewById(i30);
          localViewHolder1.btnReply = localTextView20;
          TextView localTextView21 = localViewHolder1.btnReply;
          View.OnClickListener localOnClickListener3 = this.mListener;
          localTextView21.setOnClickListener(localOnClickListener3);
          View localView20 = paramView;
          CommentsActivity.ViewHolder localViewHolder4 = localViewHolder1;
          localView20.setTag(localViewHolder4);
          break label238;
        }
        localViewHolder1 = (CommentsActivity.ViewHolder)paramView.getTag();
        break label238;
        if (StringUtil.isEmptyOrWhitespace(str5));
        for (str5 = CommentsActivity.this.getString(2131296381); ; str5 = localComment1.author)
        {
          str5 = "<font color=\"black\">" + str5 + "</font>";
          break;
        }
        str10 = localComment1.deviceName;
        break label493;
        TextView localTextView22 = localViewHolder1.comment;
        CommentsActivity localCommentsActivity1 = CommentsActivity.this;
        String str18 = localComment1.comment;
        String str19 = localCommentsActivity1.getComment(str18);
        localTextView22.setText(str19);
        localViewHolder1.rating.setVisibility(0);
        break label711;
        Comment localComment2 = (Comment)localArrayList.get(i11);
        LinearLayout localLinearLayout4 = localViewHolder1.replyContainer;
        int i31 = localComment1.commentId;
        ReviewAdapter localReviewAdapter1 = this;
        Comment localComment3 = localComment2;
        int i32 = i31;
        int i33 = paramInt;
        int i34 = i11;
        View localView21 = localReviewAdapter1.fillCommentReply(localComment3, i32, i33, i34);
        localLinearLayout4.addView(localView21);
        i11 += 1;
        break label741;
        str20 = localComment1.type;
        str21 = Comment.TYPE_ERROR;
      }
      while (!StringUtil.equals(str20, str21));
      TextView localTextView7 = localViewHolder1.author;
      String str22 = String.valueOf(str5);
      StringBuilder localStringBuilder3 = new StringBuilder(str22).append("<font color=\"#999999\">").append("(");
      if (StringUtil.isEmptyOrWhitespace(localComment1.deviceName));
      for (String str10 = CommentsActivity.this.getString(2131296518); ; str10 = localComment1.deviceName)
      {
        StringBuilder localStringBuilder5 = localStringBuilder3.append(str10).append(") ");
        String str23 = CommentsActivity.this.getString(2131296511);
        Spanned localSpanned2 = Html.fromHtml(str23 + "</font>");
        localTextView7.setText(localSpanned2);
        TextView localTextView23 = localViewHolder1.comment;
        CommentsActivity localCommentsActivity2 = CommentsActivity.this;
        int i35 = localComment1.errorType;
        String str24 = String.valueOf(localCommentsActivity2.getErrorType(i35));
        StringBuilder localStringBuilder6 = new StringBuilder(str24).append(":");
        CommentsActivity localCommentsActivity3 = CommentsActivity.this;
        String str25 = localComment1.comment;
        String str26 = localCommentsActivity3.getComment(str25);
        String str27 = str26;
        localTextView23.setText(str27);
        localArrayList = localComment1.children;
        if ((localArrayList == null) || (localArrayList.size() <= 0))
          break;
        i11 = 0;
        i12 = localArrayList.size();
        while (i11 < i12)
        {
          Comment localComment4 = (Comment)localArrayList.get(i11);
          LinearLayout localLinearLayout5 = localViewHolder1.replyContainer;
          int i36 = localComment1.commentId;
          ReviewAdapter localReviewAdapter2 = this;
          Comment localComment5 = localComment4;
          int i37 = i36;
          int i38 = paramInt;
          int i39 = i11;
          View localView22 = localReviewAdapter2.fillCommentReply(localComment5, i37, i38, i39);
          localLinearLayout5.addView(localView22);
          i11 += 1;
        }
        break;
      }
    }
  }

  class ViewHolder
  {
    TextView author;
    TextView btnReply;
    TextView comment;
    RatingBar rating;
    LinearLayout replyContainer;
    TextView time;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommentsActivity
 * JD-Core Version:    0.6.0
 */