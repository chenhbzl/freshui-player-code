package com.yingyonghui.market.online;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.json.JsonParams;
import com.yingyonghui.market.util.DeviceUtil;
import dalvik.annotation.Signature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class MarketServiceAgent
{
  protected static final String API = "market.PhoneMarket";
  protected static String CONNECT_KEY;
  private static final String TIME_STAMP = "top_recommend.timestamp";
  private static MarketServiceAgent instance;
  private static final String mTop4URL = "http://img.yingyonghui.com/grape/banner.jpg";
  private HttpClient httpClientForImage;
  private HttpClient httpClientForList;
  private HttpClient httpClientForOther;
  private Context mContext = null;
  private float mDensity = 1.0F;
  protected final String mGetDownloadURL;
  protected final String mGetIconURL;
  protected final String mGetSnapshotURL;
  protected final String mGetURL = "http://img.yingyonghui.com/";
  protected final String mHostURL = "http://mobile.yingyonghui.com/market/";
  protected final String mPostHostURL = "http://mobile.yingyonghui.com/market/api";
  private String mResolution;
  private String mUUIDString = null;
  protected final String mUpdateHostURL = "http://update.yingyonghui.com/market/";
  protected final String mUpdateURL = "http://update.yingyonghui.com/market/download.jsp";

  private MarketServiceAgent(Context paramContext)
  {
    this.mContext = paramContext;
    float f = this.mContext.getResources().getDisplayMetrics().density;
    this.mDensity = f;
    if (this.mDensity > 1.0F);
    for (this.mGetIconURL = "http://img.yingyonghui.com/icon/72/"; ; this.mGetIconURL = "http://img.yingyonghui.com/icon/48/")
    {
      this.mGetSnapshotURL = "http://mobile.yingyonghui.com/market/download.jsp";
      this.mGetDownloadURL = "http://mobile.yingyonghui.com/market/d/";
      return;
    }
  }

  private String getHardwareCode()
  {
    return Build.MODEL;
  }

  public static MarketServiceAgent getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new MarketServiceAgent(paramContext);
    return instance;
  }

  private String getResolution()
  {
    if (this.mResolution == null)
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      String str1 = String.valueOf(i);
      String str2 = str1 + "x" + j;
      this.mResolution = str2;
    }
    return this.mResolution;
  }

  private String getUUIDString()
  {
    String str1;
    if (this.mUUIDString != null)
    {
      str1 = this.mUUIDString;
      return str1;
    }
    String str2 = PreferenceManager.getDefaultSharedPreferences(this.mContext).getString("uuid", null);
    if (str2 != null);
    String str4;
    for (this.mUUIDString = str2; ; this.mUUIDString = str4)
    {
      str1 = this.mUUIDString;
      break;
      UUID localUUID = UUID.randomUUID();
      SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this.mContext).edit();
      String str3 = localUUID.toString();
      localEditor.putString("uuid", str3);
      localEditor.commit();
      str4 = localUUID.toString();
    }
  }

  private String getVersionName()
  {
    try
    {
      String str = this.mContext.getPackageManager().getPackageInfo("com.yingyonghui.market", 8192).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  private String intToIp(int paramInt)
  {
    String str = String.valueOf(paramInt & 0xFF);
    StringBuilder localStringBuilder1 = new StringBuilder(str).append(".");
    int i = paramInt >> 8 & 0xFF;
    StringBuilder localStringBuilder2 = localStringBuilder1.append(i).append(".");
    int j = paramInt >> 16 & 0xFF;
    StringBuilder localStringBuilder3 = localStringBuilder2.append(j).append(".");
    int k = paramInt >> 24 & 0xFF;
    return k;
  }

  public static void setConnectKey(String paramString)
  {
    CONNECT_KEY = paramString;
  }

  // ERROR //
  public boolean deleteComment(int paramInt, String paramString1, String paramString2)
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   7: ifnonnull +26 -> 33
    //   10: aload_0
    //   11: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   14: astore 5
    //   16: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   19: dup
    //   20: aload 5
    //   22: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   25: astore 6
    //   27: aload_0
    //   28: aload 6
    //   30: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   33: new 242	org/apache/http/client/methods/HttpPost
    //   36: astore 7
    //   38: aload 7
    //   40: astore 8
    //   42: ldc 56
    //   44: astore 9
    //   46: aload 8
    //   48: aload 9
    //   50: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   53: new 245	java/util/ArrayList
    //   56: astore 10
    //   58: aload 10
    //   60: astore 11
    //   62: iconst_4
    //   63: istore 12
    //   65: aload 11
    //   67: iload 12
    //   69: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   72: aload_0
    //   73: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   76: astore 13
    //   78: new 253	org/apache/http/message/BasicNameValuePair
    //   81: dup
    //   82: ldc 255
    //   84: aload 13
    //   86: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: astore 14
    //   91: aload 10
    //   93: astore 15
    //   95: aload 14
    //   97: astore 16
    //   99: aload 15
    //   101: aload 16
    //   103: invokeinterface 264 2 0
    //   108: pop
    //   109: aload_0
    //   110: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   113: astore 17
    //   115: new 253	org/apache/http/message/BasicNameValuePair
    //   118: dup
    //   119: ldc_w 268
    //   122: aload 17
    //   124: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   127: astore 18
    //   129: aload 10
    //   131: astore 19
    //   133: aload 18
    //   135: astore 20
    //   137: aload 19
    //   139: aload 20
    //   141: invokeinterface 264 2 0
    //   146: pop
    //   147: new 253	org/apache/http/message/BasicNameValuePair
    //   150: dup
    //   151: ldc_w 270
    //   154: ldc 9
    //   156: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   159: astore 21
    //   161: aload 10
    //   163: astore 22
    //   165: aload 21
    //   167: astore 23
    //   169: aload 22
    //   171: aload 23
    //   173: invokeinterface 264 2 0
    //   178: pop
    //   179: aload_0
    //   180: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   183: astore 24
    //   185: iload_1
    //   186: istore 25
    //   188: aload 24
    //   190: astore 26
    //   192: aload_2
    //   193: astore 27
    //   195: aload_3
    //   196: astore 28
    //   198: iload 25
    //   200: aload 26
    //   202: aload 27
    //   204: aload 28
    //   206: invokestatic 278	com/yingyonghui/market/json/JsonParams:getDeleteCommentParams	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   209: astore 29
    //   211: new 253	org/apache/http/message/BasicNameValuePair
    //   214: astore 30
    //   216: aload 30
    //   218: astore 31
    //   220: ldc_w 280
    //   223: astore 32
    //   225: aload 29
    //   227: astore 33
    //   229: aload 31
    //   231: aload 32
    //   233: aload 33
    //   235: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   238: aload 10
    //   240: astore 34
    //   242: aload 30
    //   244: astore 35
    //   246: aload 34
    //   248: aload 35
    //   250: invokeinterface 264 2 0
    //   255: pop
    //   256: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   259: astore 36
    //   261: aload 36
    //   263: astore 37
    //   265: aload 10
    //   267: astore 38
    //   269: ldc_w 284
    //   272: astore 39
    //   274: aload 37
    //   276: aload 38
    //   278: aload 39
    //   280: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   283: aload 7
    //   285: astore 40
    //   287: aload 36
    //   289: astore 41
    //   291: aload 40
    //   293: aload 41
    //   295: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   298: aload_0
    //   299: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   302: astore 42
    //   304: aload 7
    //   306: astore 43
    //   308: aload 42
    //   310: aload 43
    //   312: invokeinterface 297 2 0
    //   317: invokeinterface 303 1 0
    //   322: invokeinterface 309 1 0
    //   327: astore 44
    //   329: new 311	java/io/BufferedReader
    //   332: astore 45
    //   334: new 313	java/io/InputStreamReader
    //   337: astore 46
    //   339: aload 46
    //   341: astore 47
    //   343: aload 44
    //   345: astore 48
    //   347: aload 47
    //   349: aload 48
    //   351: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   354: aload 45
    //   356: astore 49
    //   358: aload 46
    //   360: astore 50
    //   362: aload 49
    //   364: aload 50
    //   366: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   369: ldc_w 321
    //   372: astore 51
    //   374: aload 45
    //   376: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   379: astore 52
    //   381: aload 52
    //   383: ifnonnull +91 -> 474
    //   386: aload 51
    //   388: astore 53
    //   390: aload 53
    //   392: invokevirtual 328	java/lang/String:length	()I
    //   395: ifle +66 -> 461
    //   398: aload 53
    //   400: astore 54
    //   402: iconst_0
    //   403: istore 55
    //   405: aload 54
    //   407: iload 55
    //   409: invokevirtual 332	java/lang/String:charAt	(I)C
    //   412: istore 56
    //   414: iload 56
    //   416: istore 57
    //   418: ldc_w 333
    //   421: istore 58
    //   423: iload 57
    //   425: iload 58
    //   427: if_icmplt +34 -> 461
    //   430: iload 56
    //   432: istore 59
    //   434: ldc_w 334
    //   437: istore 60
    //   439: iload 59
    //   441: iload 60
    //   443: if_icmpgt +18 -> 461
    //   446: aload 53
    //   448: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   451: istore 61
    //   453: iload 61
    //   455: ifne +6 -> 461
    //   458: iconst_1
    //   459: istore 4
    //   461: aload 44
    //   463: ifnull +8 -> 471
    //   466: aload 44
    //   468: invokevirtual 345	java/io/InputStream:close	()V
    //   471: iload 4
    //   473: ireturn
    //   474: aload 51
    //   476: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   479: astore 62
    //   481: new 149	java/lang/StringBuilder
    //   484: dup
    //   485: aload 62
    //   487: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   490: astore 63
    //   492: aload 52
    //   494: astore 64
    //   496: aload 63
    //   498: aload 64
    //   500: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   506: astore 51
    //   508: goto -134 -> 374
    //   511: astore 65
    //   513: aload 65
    //   515: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   518: aload 65
    //   520: instanceof 233
    //   523: ifne +11 -> 534
    //   526: aload 65
    //   528: instanceof 350
    //   531: ifeq +35 -> 566
    //   534: aload 65
    //   536: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   539: astore 66
    //   541: new 233	java/net/SocketException
    //   544: dup
    //   545: aload 66
    //   547: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   550: athrow
    //   551: astore 67
    //   553: aload 44
    //   555: ifnull +8 -> 563
    //   558: aload 44
    //   560: invokevirtual 345	java/io/InputStream:close	()V
    //   563: aload 67
    //   565: athrow
    //   566: aload 44
    //   568: ifnull -97 -> 471
    //   571: aload 44
    //   573: invokevirtual 345	java/io/InputStream:close	()V
    //   576: goto -105 -> 471
    //   579: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   582: goto -111 -> 471
    //   585: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   588: goto -25 -> 563
    //   591: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   594: goto -123 -> 471
    //
    // Exception table:
    //   from	to	target	type
    //   53	453	511	java/lang/Throwable
    //   474	508	511	java/lang/Throwable
    //   53	453	551	finally
    //   474	508	551	finally
    //   513	551	551	finally
    //   571	576	579	java/io/IOException
    //   558	563	585	java/io/IOException
    //   466	471	591	java/io/IOException
  }

  // ERROR //
  public Object[] getAppComments(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: checkcast 359	[Ljava/lang/Object;
    //   4: astore 6
    //   6: aload_0
    //   7: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   10: ifnonnull +26 -> 36
    //   13: aload_0
    //   14: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   17: astore 7
    //   19: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   22: dup
    //   23: aload 7
    //   25: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   28: astore 8
    //   30: aload_0
    //   31: aload 8
    //   33: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   36: new 242	org/apache/http/client/methods/HttpPost
    //   39: dup
    //   40: ldc 56
    //   42: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   45: astore 9
    //   47: aload 9
    //   49: ldc_w 363
    //   52: ldc_w 365
    //   55: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: ldc_w 321
    //   61: astore 10
    //   63: new 245	java/util/ArrayList
    //   66: astore 11
    //   68: aload 11
    //   70: astore 12
    //   72: iconst_4
    //   73: istore 13
    //   75: aload 12
    //   77: iload 13
    //   79: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   82: aload_0
    //   83: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   86: astore 14
    //   88: new 253	org/apache/http/message/BasicNameValuePair
    //   91: dup
    //   92: ldc 255
    //   94: aload 14
    //   96: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   99: astore 15
    //   101: aload 11
    //   103: astore 16
    //   105: aload 15
    //   107: astore 17
    //   109: aload 16
    //   111: aload 17
    //   113: invokeinterface 264 2 0
    //   118: pop
    //   119: aload_0
    //   120: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   123: astore 18
    //   125: new 253	org/apache/http/message/BasicNameValuePair
    //   128: dup
    //   129: ldc_w 268
    //   132: aload 18
    //   134: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: astore 19
    //   139: aload 11
    //   141: astore 20
    //   143: aload 19
    //   145: astore 21
    //   147: aload 20
    //   149: aload 21
    //   151: invokeinterface 264 2 0
    //   156: pop
    //   157: new 253	org/apache/http/message/BasicNameValuePair
    //   160: dup
    //   161: ldc_w 270
    //   164: ldc 9
    //   166: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   169: astore 22
    //   171: aload 11
    //   173: astore 23
    //   175: aload 22
    //   177: astore 24
    //   179: aload 23
    //   181: aload 24
    //   183: invokeinterface 264 2 0
    //   188: pop
    //   189: aload_0
    //   190: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   193: astore 25
    //   195: iload_1
    //   196: istore 26
    //   198: iload_2
    //   199: istore 27
    //   201: iload_3
    //   202: istore 28
    //   204: iload 4
    //   206: istore 29
    //   208: aload 5
    //   210: astore 30
    //   212: iload 26
    //   214: iload 27
    //   216: iload 28
    //   218: aload 25
    //   220: iload 29
    //   222: aload 30
    //   224: invokestatic 372	com/yingyonghui/market/json/JsonParams:getAppCommentsParams	(IIILjava/lang/String;ILjava/lang/String;)Ljava/lang/String;
    //   227: astore 31
    //   229: new 253	org/apache/http/message/BasicNameValuePair
    //   232: astore 32
    //   234: aload 32
    //   236: astore 33
    //   238: ldc_w 280
    //   241: astore 34
    //   243: aload 31
    //   245: astore 35
    //   247: aload 33
    //   249: aload 34
    //   251: aload 35
    //   253: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   256: aload 11
    //   258: astore 36
    //   260: aload 32
    //   262: astore 37
    //   264: aload 36
    //   266: aload 37
    //   268: invokeinterface 264 2 0
    //   273: pop
    //   274: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   277: astore 38
    //   279: aload 38
    //   281: astore 39
    //   283: aload 11
    //   285: astore 40
    //   287: ldc_w 284
    //   290: astore 41
    //   292: aload 39
    //   294: aload 40
    //   296: aload 41
    //   298: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   301: aload 9
    //   303: aload 38
    //   305: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   308: aload_0
    //   309: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   312: aload 9
    //   314: invokeinterface 297 2 0
    //   319: astore 42
    //   321: aload 42
    //   323: invokeinterface 303 1 0
    //   328: invokeinterface 309 1 0
    //   333: astore 43
    //   335: aload 42
    //   337: astore 44
    //   339: ldc_w 374
    //   342: astore 45
    //   344: aload 44
    //   346: aload 45
    //   348: invokeinterface 378 2 0
    //   353: astore 46
    //   355: aload 46
    //   357: ifnull +12 -> 369
    //   360: aload 46
    //   362: invokeinterface 383 1 0
    //   367: astore 10
    //   369: ldc_w 321
    //   372: astore 47
    //   374: aload 10
    //   376: ldc_w 365
    //   379: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   382: ifeq +77 -> 459
    //   385: new 311	java/io/BufferedReader
    //   388: astore 48
    //   390: new 388	java/util/zip/GZIPInputStream
    //   393: dup
    //   394: aload 43
    //   396: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   399: astore 49
    //   401: new 313	java/io/InputStreamReader
    //   404: dup
    //   405: aload 49
    //   407: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   410: astore 50
    //   412: aload 48
    //   414: astore 51
    //   416: aload 50
    //   418: astore 52
    //   420: aload 51
    //   422: aload 52
    //   424: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   427: aload 48
    //   429: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   432: astore 53
    //   434: aload 53
    //   436: ifnonnull +57 -> 493
    //   439: aload 47
    //   441: invokestatic 395	com/yingyonghui/market/json/JsonUtils:getCommentObject	(Ljava/lang/String;)[Ljava/lang/Object;
    //   444: astore 6
    //   446: aload 43
    //   448: ifnull +8 -> 456
    //   451: aload 43
    //   453: invokevirtual 345	java/io/InputStream:close	()V
    //   456: aload 6
    //   458: areturn
    //   459: new 311	java/io/BufferedReader
    //   462: astore 48
    //   464: new 313	java/io/InputStreamReader
    //   467: dup
    //   468: aload 43
    //   470: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   473: astore 54
    //   475: aload 48
    //   477: astore 55
    //   479: aload 54
    //   481: astore 56
    //   483: aload 55
    //   485: aload 56
    //   487: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   490: goto -63 -> 427
    //   493: aload 47
    //   495: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   498: astore 57
    //   500: new 149	java/lang/StringBuilder
    //   503: dup
    //   504: aload 57
    //   506: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   509: astore 58
    //   511: aload 53
    //   513: astore 59
    //   515: aload 58
    //   517: aload 59
    //   519: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   525: astore 47
    //   527: goto -100 -> 427
    //   530: astore 60
    //   532: aload 60
    //   534: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   537: aload 60
    //   539: instanceof 233
    //   542: ifne +11 -> 553
    //   545: aload 60
    //   547: instanceof 350
    //   550: ifeq +35 -> 585
    //   553: aload 60
    //   555: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   558: astore 61
    //   560: new 233	java/net/SocketException
    //   563: dup
    //   564: aload 61
    //   566: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   569: athrow
    //   570: astore 62
    //   572: aload 43
    //   574: ifnull +8 -> 582
    //   577: aload 43
    //   579: invokevirtual 345	java/io/InputStream:close	()V
    //   582: aload 62
    //   584: athrow
    //   585: aload 43
    //   587: ifnull -131 -> 456
    //   590: aload 43
    //   592: invokevirtual 345	java/io/InputStream:close	()V
    //   595: goto -139 -> 456
    //   598: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   601: goto -145 -> 456
    //   604: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   607: goto -25 -> 582
    //   610: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   613: goto -157 -> 456
    //
    // Exception table:
    //   from	to	target	type
    //   63	446	530	java/lang/Throwable
    //   459	527	530	java/lang/Throwable
    //   63	446	570	finally
    //   459	527	570	finally
    //   532	570	570	finally
    //   590	595	598	java/io/IOException
    //   577	582	604	java/io/IOException
    //   451	456	610	java/io/IOException
  }

  // ERROR //
  public ApkContentInputstream getAppContentStream(int paramInt, boolean paramBoolean, long paramLong, String paramString)
    throws SocketException
  {
    // Byte code:
    //   0: iload_1
    //   1: istore 5
    //   3: sipush 9999
    //   6: istore 6
    //   8: iload 5
    //   10: iload 6
    //   12: if_icmpne +722 -> 734
    //   15: iconst_1
    //   16: istore 7
    //   18: ldc_w 321
    //   21: astore 8
    //   23: iload 7
    //   25: ifeq +715 -> 740
    //   28: aload 8
    //   30: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   33: astore 9
    //   35: new 149	java/lang/StringBuilder
    //   38: dup
    //   39: aload 9
    //   41: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: ldc 64
    //   46: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: ldc_w 399
    //   52: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   61: astore 10
    //   63: new 149	java/lang/StringBuilder
    //   66: dup
    //   67: aload 10
    //   69: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   72: ldc_w 401
    //   75: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   84: astore 11
    //   86: new 149	java/lang/StringBuilder
    //   89: dup
    //   90: aload 11
    //   92: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   95: ldc_w 403
    //   98: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   107: astore 12
    //   109: new 149	java/lang/StringBuilder
    //   112: dup
    //   113: aload 12
    //   115: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   118: ldc_w 405
    //   121: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: astore 13
    //   126: iload_1
    //   127: istore 14
    //   129: aload 13
    //   131: iload 14
    //   133: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   136: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   142: astore 15
    //   144: new 149	java/lang/StringBuilder
    //   147: dup
    //   148: aload 15
    //   150: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   153: ldc_w 407
    //   156: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: astore 16
    //   161: aload_0
    //   162: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   165: astore 17
    //   167: aload 16
    //   169: aload 17
    //   171: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   180: astore 18
    //   182: new 149	java/lang/StringBuilder
    //   185: dup
    //   186: aload 18
    //   188: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   191: ldc_w 409
    //   194: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: astore 19
    //   199: aload_0
    //   200: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   203: astore 20
    //   205: aload 19
    //   207: aload 20
    //   209: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: astore 8
    //   217: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   220: astore 21
    //   222: aload_0
    //   223: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   226: astore 22
    //   228: aload 21
    //   230: astore 23
    //   232: aload 22
    //   234: astore 24
    //   236: aload 23
    //   238: aload 24
    //   240: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   243: new 411	org/apache/http/client/methods/HttpGet
    //   246: dup
    //   247: aload 8
    //   249: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   252: astore 25
    //   254: new 414	org/apache/http/params/BasicHttpParams
    //   257: dup
    //   258: invokespecial 415	org/apache/http/params/BasicHttpParams:<init>	()V
    //   261: astore 26
    //   263: ldc_w 416
    //   266: invokestatic 421	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   269: astore 27
    //   271: aload 26
    //   273: astore 28
    //   275: ldc_w 423
    //   278: astore 29
    //   280: aload 27
    //   282: astore 30
    //   284: aload 28
    //   286: aload 29
    //   288: aload 30
    //   290: invokeinterface 429 3 0
    //   295: pop
    //   296: aload 25
    //   298: aload 26
    //   300: invokevirtual 433	org/apache/http/client/methods/HttpGet:setParams	(Lorg/apache/http/params/HttpParams;)V
    //   303: new 435	com/yingyonghui/market/online/ApkContentInputstream
    //   306: dup
    //   307: invokespecial 436	com/yingyonghui/market/online/ApkContentInputstream:<init>	()V
    //   310: astore 31
    //   312: ldc_w 416
    //   315: istore 32
    //   317: aload 31
    //   319: iload 32
    //   321: putfield 440	com/yingyonghui/market/online/ApkContentInputstream:repickOutput	Z
    //   324: aconst_null
    //   325: astore 33
    //   327: aload 21
    //   329: aload 25
    //   331: invokevirtual 441	com/yingyonghui/market/online/IWHttpsClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   334: astore 34
    //   336: aload 34
    //   338: invokeinterface 445 1 0
    //   343: astore 35
    //   345: aload 35
    //   347: ifnull +537 -> 884
    //   350: aload 35
    //   352: invokeinterface 450 1 0
    //   357: istore 36
    //   359: iload 36
    //   361: istore 37
    //   363: sipush 302
    //   366: istore 38
    //   368: iload 37
    //   370: iload 38
    //   372: if_icmpne +412 -> 784
    //   375: aload 34
    //   377: astore 39
    //   379: ldc_w 452
    //   382: astore 40
    //   384: aload 39
    //   386: aload 40
    //   388: invokeinterface 378 2 0
    //   393: invokeinterface 383 1 0
    //   398: astore 41
    //   400: new 411	org/apache/http/client/methods/HttpGet
    //   403: dup
    //   404: aload 41
    //   406: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   409: astore 42
    //   411: lload_3
    //   412: invokestatic 457	java/lang/Long:toString	(J)Ljava/lang/String;
    //   415: astore 43
    //   417: new 149	java/lang/StringBuilder
    //   420: dup
    //   421: ldc_w 459
    //   424: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   427: astore 44
    //   429: aload 43
    //   431: astore 45
    //   433: aload 44
    //   435: aload 45
    //   437: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: ldc_w 461
    //   443: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   449: astore 46
    //   451: aload 42
    //   453: astore 47
    //   455: ldc_w 463
    //   458: astore 48
    //   460: aload 46
    //   462: astore 49
    //   464: aload 47
    //   466: aload 48
    //   468: aload 49
    //   470: invokevirtual 466	org/apache/http/client/methods/HttpGet:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   473: aload 21
    //   475: aload 42
    //   477: invokevirtual 441	com/yingyonghui/market/online/IWHttpsClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   480: astore 34
    //   482: aload 34
    //   484: invokeinterface 445 1 0
    //   489: astore 35
    //   491: aload 35
    //   493: ifnull +223 -> 716
    //   496: aload 35
    //   498: invokeinterface 450 1 0
    //   503: istore 36
    //   505: iload 36
    //   507: istore 50
    //   509: sipush 200
    //   512: istore 51
    //   514: iload 50
    //   516: iload 51
    //   518: if_icmpeq +19 -> 537
    //   521: iload 36
    //   523: istore 52
    //   525: sipush 206
    //   528: istore 53
    //   530: iload 52
    //   532: iload 53
    //   534: if_icmpne +182 -> 716
    //   537: aload 34
    //   539: astore 54
    //   541: ldc_w 468
    //   544: astore 55
    //   546: aload 54
    //   548: aload 55
    //   550: invokeinterface 378 2 0
    //   555: invokeinterface 383 1 0
    //   560: astore 56
    //   562: lload_3
    //   563: ldc2_w 469
    //   566: lcmp
    //   567: ifeq +112 -> 679
    //   570: aload_0
    //   571: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   574: astore 57
    //   576: aload 34
    //   578: astore 58
    //   580: ldc_w 468
    //   583: astore 59
    //   585: aload 58
    //   587: aload 59
    //   589: invokeinterface 378 2 0
    //   594: invokeinterface 383 1 0
    //   599: astore 60
    //   601: aload 57
    //   603: astore 61
    //   605: aload 4
    //   607: astore 62
    //   609: aload 60
    //   611: astore 63
    //   613: aload 61
    //   615: aload 62
    //   617: aload 63
    //   619: invokestatic 476	com/yingyonghui/market/FileManager:timestampEqual	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   622: ifne +57 -> 679
    //   625: aload 42
    //   627: astore 64
    //   629: ldc_w 463
    //   632: astore 65
    //   634: aload 64
    //   636: aload 65
    //   638: invokevirtual 479	org/apache/http/client/methods/HttpGet:removeHeaders	(Ljava/lang/String;)V
    //   641: aload 21
    //   643: aload 42
    //   645: invokevirtual 441	com/yingyonghui/market/online/IWHttpsClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   648: astore 34
    //   650: aload_0
    //   651: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   654: astore 66
    //   656: aload 4
    //   658: astore 67
    //   660: aload 66
    //   662: aload 67
    //   664: invokestatic 483	com/yingyonghui/market/FileManager:removeTempFile	(Landroid/content/Context;Ljava/lang/String;)V
    //   667: ldc_w 484
    //   670: istore 68
    //   672: aload 31
    //   674: iload 68
    //   676: putfield 440	com/yingyonghui/market/online/ApkContentInputstream:repickOutput	Z
    //   679: aload_0
    //   680: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   683: astore 69
    //   685: aload 4
    //   687: astore 70
    //   689: aload 56
    //   691: astore 71
    //   693: aload 69
    //   695: aload 70
    //   697: aload 71
    //   699: invokestatic 488	com/yingyonghui/market/FileManager:addApkTempFileTimestampRecord	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   702: aload 34
    //   704: invokeinterface 303 1 0
    //   709: invokeinterface 309 1 0
    //   714: astore 33
    //   716: aload 31
    //   718: aload 33
    //   720: putfield 492	com/yingyonghui/market/online/ApkContentInputstream:input	Ljava/io/InputStream;
    //   723: aload 42
    //   725: astore 72
    //   727: aload 31
    //   729: astore 73
    //   731: aload 73
    //   733: areturn
    //   734: iconst_0
    //   735: istore 7
    //   737: goto -719 -> 18
    //   740: aload_0
    //   741: getfield 97	com/yingyonghui/market/online/MarketServiceAgent:mGetDownloadURL	Ljava/lang/String;
    //   744: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   747: astore 74
    //   749: new 149	java/lang/StringBuilder
    //   752: dup
    //   753: aload 74
    //   755: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   758: astore 75
    //   760: iload_1
    //   761: istore 76
    //   763: aload 75
    //   765: iload 76
    //   767: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   770: ldc_w 494
    //   773: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   779: astore 8
    //   781: goto -564 -> 217
    //   784: iload 36
    //   786: istore 77
    //   788: sipush 200
    //   791: istore 78
    //   793: iload 77
    //   795: iload 78
    //   797: if_icmpeq +19 -> 816
    //   800: iload 36
    //   802: istore 79
    //   804: sipush 206
    //   807: istore 80
    //   809: iload 79
    //   811: iload 80
    //   813: if_icmpne +71 -> 884
    //   816: aload 34
    //   818: invokeinterface 303 1 0
    //   823: invokeinterface 309 1 0
    //   828: astore 81
    //   830: aload 31
    //   832: aload 81
    //   834: putfield 492	com/yingyonghui/market/online/ApkContentInputstream:input	Ljava/io/InputStream;
    //   837: aload 31
    //   839: astore 73
    //   841: goto -110 -> 731
    //   844: astore 82
    //   846: aload 82
    //   848: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   851: aload 82
    //   853: instanceof 233
    //   856: ifne +11 -> 867
    //   859: aload 82
    //   861: instanceof 350
    //   864: ifeq +20 -> 884
    //   867: aload 82
    //   869: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   872: astore 83
    //   874: new 233	java/net/SocketException
    //   877: dup
    //   878: aload 83
    //   880: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   883: athrow
    //   884: aconst_null
    //   885: astore 73
    //   887: goto -156 -> 731
    //   890: astore 82
    //   892: aload 42
    //   894: astore 84
    //   896: goto -50 -> 846
    //
    // Exception table:
    //   from	to	target	type
    //   327	411	844	java/lang/Throwable
    //   816	837	844	java/lang/Throwable
    //   411	723	890	java/lang/Throwable
  }

  // ERROR //
  public com.yingyonghui.market.model.AssetDetail getAppDetail(int paramInt, String paramString)
    throws SocketException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   6: ifnonnull +26 -> 32
    //   9: aload_0
    //   10: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   13: astore 4
    //   15: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   18: dup
    //   19: aload 4
    //   21: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   24: astore 5
    //   26: aload_0
    //   27: aload 5
    //   29: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   32: new 242	org/apache/http/client/methods/HttpPost
    //   35: astore 6
    //   37: aload 6
    //   39: astore 7
    //   41: ldc 56
    //   43: astore 8
    //   45: aload 7
    //   47: aload 8
    //   49: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   52: aload 6
    //   54: astore 9
    //   56: ldc_w 363
    //   59: astore 10
    //   61: ldc_w 365
    //   64: astore 11
    //   66: aload 9
    //   68: aload 10
    //   70: aload 11
    //   72: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   75: ldc_w 321
    //   78: astore 12
    //   80: new 245	java/util/ArrayList
    //   83: astore 13
    //   85: aload 13
    //   87: astore 14
    //   89: iconst_4
    //   90: istore 15
    //   92: aload 14
    //   94: iload 15
    //   96: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   99: aload_0
    //   100: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   103: astore 16
    //   105: new 253	org/apache/http/message/BasicNameValuePair
    //   108: dup
    //   109: ldc 255
    //   111: aload 16
    //   113: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   116: astore 17
    //   118: aload 13
    //   120: astore 18
    //   122: aload 17
    //   124: astore 19
    //   126: aload 18
    //   128: aload 19
    //   130: invokeinterface 264 2 0
    //   135: pop
    //   136: aload_0
    //   137: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   140: astore 20
    //   142: new 253	org/apache/http/message/BasicNameValuePair
    //   145: dup
    //   146: ldc_w 268
    //   149: aload 20
    //   151: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: astore 21
    //   156: aload 13
    //   158: astore 22
    //   160: aload 21
    //   162: astore 23
    //   164: aload 22
    //   166: aload 23
    //   168: invokeinterface 264 2 0
    //   173: pop
    //   174: new 253	org/apache/http/message/BasicNameValuePair
    //   177: dup
    //   178: ldc_w 270
    //   181: ldc 9
    //   183: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   186: astore 24
    //   188: aload 13
    //   190: astore 25
    //   192: aload 24
    //   194: astore 26
    //   196: aload 25
    //   198: aload 26
    //   200: invokeinterface 264 2 0
    //   205: pop
    //   206: aload_0
    //   207: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   210: astore 27
    //   212: iload_1
    //   213: istore 28
    //   215: aload_2
    //   216: astore 29
    //   218: aload 27
    //   220: astore 30
    //   222: iload 28
    //   224: aload 29
    //   226: aload 30
    //   228: invokestatic 500	com/yingyonghui/market/json/JsonParams:getAppDetailParams	(ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   231: astore 31
    //   233: new 253	org/apache/http/message/BasicNameValuePair
    //   236: astore 32
    //   238: aload 32
    //   240: astore 33
    //   242: ldc_w 280
    //   245: astore 34
    //   247: aload 31
    //   249: astore 35
    //   251: aload 33
    //   253: aload 34
    //   255: aload 35
    //   257: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   260: aload 13
    //   262: astore 36
    //   264: aload 32
    //   266: astore 37
    //   268: aload 36
    //   270: aload 37
    //   272: invokeinterface 264 2 0
    //   277: pop
    //   278: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   281: astore 38
    //   283: aload 38
    //   285: astore 39
    //   287: aload 13
    //   289: astore 40
    //   291: ldc_w 284
    //   294: astore 41
    //   296: aload 39
    //   298: aload 40
    //   300: aload 41
    //   302: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   305: aload 6
    //   307: astore 42
    //   309: aload 38
    //   311: astore 43
    //   313: aload 42
    //   315: aload 43
    //   317: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   320: aload_0
    //   321: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   324: astore 44
    //   326: aload 6
    //   328: astore 45
    //   330: aload 44
    //   332: aload 45
    //   334: invokeinterface 297 2 0
    //   339: astore 46
    //   341: aload 46
    //   343: invokeinterface 303 1 0
    //   348: invokeinterface 309 1 0
    //   353: astore 47
    //   355: aload 46
    //   357: astore 48
    //   359: ldc_w 374
    //   362: astore 49
    //   364: aload 48
    //   366: aload 49
    //   368: invokeinterface 378 2 0
    //   373: astore 50
    //   375: aload 50
    //   377: ifnull +12 -> 389
    //   380: aload 50
    //   382: invokeinterface 383 1 0
    //   387: astore 12
    //   389: new 502	java/lang/StringBuffer
    //   392: dup
    //   393: invokespecial 503	java/lang/StringBuffer:<init>	()V
    //   396: astore 51
    //   398: aload 12
    //   400: astore 52
    //   402: ldc_w 365
    //   405: astore 53
    //   407: aload 52
    //   409: aload 53
    //   411: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   414: ifeq +132 -> 546
    //   417: new 311	java/io/BufferedReader
    //   420: astore 54
    //   422: new 388	java/util/zip/GZIPInputStream
    //   425: astore 55
    //   427: aload 55
    //   429: astore 56
    //   431: aload 47
    //   433: astore 57
    //   435: aload 56
    //   437: aload 57
    //   439: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   442: new 313	java/io/InputStreamReader
    //   445: dup
    //   446: aload 55
    //   448: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   451: astore 58
    //   453: aload 54
    //   455: astore 59
    //   457: aload 58
    //   459: astore 60
    //   461: aload 59
    //   463: aload 60
    //   465: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   468: sipush 1024
    //   471: newarray char
    //   473: astore 61
    //   475: aload 61
    //   477: arraylength
    //   478: istore 62
    //   480: aload 54
    //   482: astore 63
    //   484: aload 61
    //   486: astore 64
    //   488: iconst_0
    //   489: istore 65
    //   491: iload 62
    //   493: istore 66
    //   495: aload 63
    //   497: aload 64
    //   499: iload 65
    //   501: iload 66
    //   503: invokevirtual 507	java/io/BufferedReader:read	([CII)I
    //   506: istore 67
    //   508: iload 67
    //   510: ifgt +79 -> 589
    //   513: aload_0
    //   514: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   517: astore 68
    //   519: aload 51
    //   521: invokevirtual 508	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   524: astore 69
    //   526: aload 68
    //   528: aload 69
    //   530: invokestatic 512	com/yingyonghui/market/json/JsonUtils:getAssetDetailObject	(Landroid/content/Context;Ljava/lang/String;)Lcom/yingyonghui/market/model/AssetDetail;
    //   533: astore_3
    //   534: aload 47
    //   536: ifnull +8 -> 544
    //   539: aload 47
    //   541: invokevirtual 345	java/io/InputStream:close	()V
    //   544: aload_3
    //   545: areturn
    //   546: new 311	java/io/BufferedReader
    //   549: astore 54
    //   551: new 313	java/io/InputStreamReader
    //   554: astore 70
    //   556: aload 70
    //   558: astore 71
    //   560: aload 47
    //   562: astore 72
    //   564: aload 71
    //   566: aload 72
    //   568: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   571: aload 54
    //   573: astore 73
    //   575: aload 70
    //   577: astore 74
    //   579: aload 73
    //   581: aload 74
    //   583: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   586: goto -118 -> 468
    //   589: aload 51
    //   591: astore 75
    //   593: aload 61
    //   595: astore 76
    //   597: iconst_0
    //   598: istore 77
    //   600: iload 67
    //   602: istore 78
    //   604: aload 75
    //   606: aload 76
    //   608: iload 77
    //   610: iload 78
    //   612: invokevirtual 515	java/lang/StringBuffer:append	([CII)Ljava/lang/StringBuffer;
    //   615: pop
    //   616: goto -141 -> 475
    //   619: astore 79
    //   621: aload 79
    //   623: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   626: aload 79
    //   628: instanceof 233
    //   631: ifne +11 -> 642
    //   634: aload 79
    //   636: instanceof 350
    //   639: ifeq +35 -> 674
    //   642: aload 79
    //   644: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   647: astore 80
    //   649: new 233	java/net/SocketException
    //   652: dup
    //   653: aload 80
    //   655: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   658: athrow
    //   659: astore 81
    //   661: aload 47
    //   663: ifnull +8 -> 671
    //   666: aload 47
    //   668: invokevirtual 345	java/io/InputStream:close	()V
    //   671: aload 81
    //   673: athrow
    //   674: aload 47
    //   676: ifnull -132 -> 544
    //   679: aload 47
    //   681: invokevirtual 345	java/io/InputStream:close	()V
    //   684: goto -140 -> 544
    //   687: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   690: goto -146 -> 544
    //   693: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   696: goto -25 -> 671
    //   699: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   702: goto -158 -> 544
    //
    // Exception table:
    //   from	to	target	type
    //   80	534	619	java/lang/Throwable
    //   546	616	619	java/lang/Throwable
    //   80	534	659	finally
    //   546	616	659	finally
    //   621	659	659	finally
    //   679	684	687	java/io/IOException
    //   666	671	693	java/io/IOException
    //   539	544	699	java/io/IOException
  }

  // ERROR //
  public com.yingyonghui.market.model.Image2 getAppIcon2(int paramInt)
    throws SocketException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   4: ldc_w 519
    //   7: iconst_0
    //   8: invokevirtual 523	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   11: ldc_w 525
    //   14: ldc_w 484
    //   17: invokeinterface 529 3 0
    //   22: ifne +25 -> 47
    //   25: new 531	com/yingyonghui/market/model/Image2
    //   28: dup
    //   29: invokespecial 532	com/yingyonghui/market/model/Image2:<init>	()V
    //   32: astore_2
    //   33: aload_2
    //   34: iload_1
    //   35: putfield 535	com/yingyonghui/market/model/Image2:_id	I
    //   38: aload_2
    //   39: aconst_null
    //   40: putfield 539	com/yingyonghui/market/model/Image2:icon	Landroid/graphics/drawable/Drawable;
    //   43: aload_2
    //   44: astore_3
    //   45: aload_3
    //   46: areturn
    //   47: aconst_null
    //   48: astore 4
    //   50: aload_0
    //   51: getfield 89	com/yingyonghui/market/online/MarketServiceAgent:mGetIconURL	Ljava/lang/String;
    //   54: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   57: astore 5
    //   59: new 149	java/lang/StringBuilder
    //   62: dup
    //   63: aload 5
    //   65: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   68: iload_1
    //   69: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   72: ldc_w 541
    //   75: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: astore 6
    //   83: aload_0
    //   84: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   87: ifnonnull +26 -> 113
    //   90: aload_0
    //   91: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   94: astore 7
    //   96: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   99: dup
    //   100: aload 7
    //   102: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   105: astore 8
    //   107: aload_0
    //   108: aload 8
    //   110: putfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   113: new 411	org/apache/http/client/methods/HttpGet
    //   116: dup
    //   117: aload 6
    //   119: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   122: astore 9
    //   124: aload_0
    //   125: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   128: aload 9
    //   130: invokeinterface 297 2 0
    //   135: invokeinterface 303 1 0
    //   140: invokeinterface 309 1 0
    //   145: astore 10
    //   147: new 531	com/yingyonghui/market/model/Image2
    //   150: dup
    //   151: invokespecial 532	com/yingyonghui/market/model/Image2:<init>	()V
    //   154: astore_3
    //   155: aload_3
    //   156: iload_1
    //   157: putfield 535	com/yingyonghui/market/model/Image2:_id	I
    //   160: aload 10
    //   162: aconst_null
    //   163: invokestatic 549	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   166: astore 11
    //   168: aload_3
    //   169: aload 11
    //   171: putfield 539	com/yingyonghui/market/model/Image2:icon	Landroid/graphics/drawable/Drawable;
    //   174: aload 10
    //   176: ifnull +100 -> 276
    //   179: aload 10
    //   181: invokevirtual 345	java/io/InputStream:close	()V
    //   184: aload_3
    //   185: astore 4
    //   187: aload 4
    //   189: astore_3
    //   190: goto -145 -> 45
    //   193: astore 12
    //   195: aload 12
    //   197: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   200: aload 12
    //   202: instanceof 233
    //   205: ifne +11 -> 216
    //   208: aload 12
    //   210: instanceof 350
    //   213: ifeq +35 -> 248
    //   216: aload 12
    //   218: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   221: astore 13
    //   223: new 233	java/net/SocketException
    //   226: dup
    //   227: aload 13
    //   229: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   232: athrow
    //   233: astore 14
    //   235: aload 10
    //   237: ifnull +8 -> 245
    //   240: aload 10
    //   242: invokevirtual 345	java/io/InputStream:close	()V
    //   245: aload 14
    //   247: athrow
    //   248: aload 10
    //   250: ifnull -63 -> 187
    //   253: aload 10
    //   255: invokevirtual 345	java/io/InputStream:close	()V
    //   258: goto -71 -> 187
    //   261: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   264: goto -77 -> 187
    //   267: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   270: goto -25 -> 245
    //   273: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   276: aload_3
    //   277: astore 4
    //   279: goto -92 -> 187
    //   282: astore 14
    //   284: aload_3
    //   285: astore 15
    //   287: goto -52 -> 235
    //   290: astore 12
    //   292: aload_3
    //   293: astore 4
    //   295: goto -100 -> 195
    //
    // Exception table:
    //   from	to	target	type
    //   124	155	193	java/lang/Throwable
    //   124	155	233	finally
    //   195	233	233	finally
    //   253	258	261	java/io/IOException
    //   240	245	267	java/io/IOException
    //   179	184	273	java/io/IOException
    //   155	174	282	finally
    //   155	174	290	java/lang/Throwable
  }

  // ERROR //
  @Signature({"([", "Ljava/lang/String;", "II)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getAppLikeList(String[] paramArrayOfString, int paramInt1, int paramInt2)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   13: ifnonnull +26 -> 39
    //   16: aload_0
    //   17: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   20: astore 5
    //   22: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   25: dup
    //   26: aload 5
    //   28: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   31: astore 6
    //   33: aload_0
    //   34: aload 6
    //   36: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   39: new 242	org/apache/http/client/methods/HttpPost
    //   42: dup
    //   43: ldc 56
    //   45: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: astore 7
    //   50: aload 7
    //   52: ldc_w 363
    //   55: ldc_w 365
    //   58: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: ldc_w 321
    //   64: astore 8
    //   66: new 245	java/util/ArrayList
    //   69: astore 9
    //   71: aload 9
    //   73: astore 10
    //   75: iconst_4
    //   76: istore 11
    //   78: aload 10
    //   80: iload 11
    //   82: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   85: aload_0
    //   86: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   89: astore 12
    //   91: new 253	org/apache/http/message/BasicNameValuePair
    //   94: dup
    //   95: ldc 255
    //   97: aload 12
    //   99: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: astore 13
    //   104: aload 9
    //   106: astore 14
    //   108: aload 13
    //   110: astore 15
    //   112: aload 14
    //   114: aload 15
    //   116: invokeinterface 264 2 0
    //   121: pop
    //   122: aload_0
    //   123: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   126: astore 16
    //   128: new 253	org/apache/http/message/BasicNameValuePair
    //   131: dup
    //   132: ldc_w 268
    //   135: aload 16
    //   137: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: astore 17
    //   142: aload 9
    //   144: astore 18
    //   146: aload 17
    //   148: astore 19
    //   150: aload 18
    //   152: aload 19
    //   154: invokeinterface 264 2 0
    //   159: pop
    //   160: new 253	org/apache/http/message/BasicNameValuePair
    //   163: dup
    //   164: ldc_w 270
    //   167: ldc 9
    //   169: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: astore 20
    //   174: aload 9
    //   176: astore 21
    //   178: aload 20
    //   180: astore 22
    //   182: aload 21
    //   184: aload 22
    //   186: invokeinterface 264 2 0
    //   191: pop
    //   192: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   195: istore 23
    //   197: aload_0
    //   198: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   201: astore 24
    //   203: aload_0
    //   204: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   207: astore 25
    //   209: aload_0
    //   210: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   213: astore 26
    //   215: aload_1
    //   216: astore 27
    //   218: iload_2
    //   219: istore 28
    //   221: iload_3
    //   222: istore 29
    //   224: aload 27
    //   226: iload 28
    //   228: iload 29
    //   230: iload 23
    //   232: aload 24
    //   234: aload 25
    //   236: aload 26
    //   238: invokestatic 573	com/yingyonghui/market/json/JsonParams:getAppLikeListParams	([Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   241: astore 30
    //   243: new 253	org/apache/http/message/BasicNameValuePair
    //   246: astore 31
    //   248: aload 31
    //   250: astore 32
    //   252: ldc_w 280
    //   255: astore 33
    //   257: aload 30
    //   259: astore 34
    //   261: aload 32
    //   263: aload 33
    //   265: aload 34
    //   267: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   270: aload 9
    //   272: astore 35
    //   274: aload 31
    //   276: astore 36
    //   278: aload 35
    //   280: aload 36
    //   282: invokeinterface 264 2 0
    //   287: pop
    //   288: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   291: astore 37
    //   293: aload 37
    //   295: astore 38
    //   297: aload 9
    //   299: astore 39
    //   301: ldc_w 284
    //   304: astore 40
    //   306: aload 38
    //   308: aload 39
    //   310: aload 40
    //   312: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   315: aload 7
    //   317: aload 37
    //   319: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   322: aload_0
    //   323: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   326: aload 7
    //   328: invokeinterface 297 2 0
    //   333: astore 41
    //   335: aload 41
    //   337: invokeinterface 303 1 0
    //   342: invokeinterface 309 1 0
    //   347: astore 42
    //   349: aload 41
    //   351: astore 43
    //   353: ldc_w 374
    //   356: astore 44
    //   358: aload 43
    //   360: aload 44
    //   362: invokeinterface 378 2 0
    //   367: astore 45
    //   369: aload 45
    //   371: ifnull +12 -> 383
    //   374: aload 45
    //   376: invokeinterface 383 1 0
    //   381: astore 8
    //   383: ldc_w 321
    //   386: astore 46
    //   388: aload 8
    //   390: ldc_w 365
    //   393: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifeq +89 -> 485
    //   399: new 311	java/io/BufferedReader
    //   402: astore 47
    //   404: new 388	java/util/zip/GZIPInputStream
    //   407: dup
    //   408: aload 42
    //   410: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   413: astore 48
    //   415: new 313	java/io/InputStreamReader
    //   418: dup
    //   419: aload 48
    //   421: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   424: astore 49
    //   426: aload 47
    //   428: astore 50
    //   430: aload 49
    //   432: astore 51
    //   434: aload 50
    //   436: aload 51
    //   438: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   441: aload 47
    //   443: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   446: astore 52
    //   448: aload 52
    //   450: ifnonnull +69 -> 519
    //   453: aload_0
    //   454: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   457: astore 53
    //   459: aload 46
    //   461: astore 54
    //   463: aload 53
    //   465: aload 54
    //   467: invokestatic 577	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   470: astore 4
    //   472: aload 42
    //   474: ifnull +8 -> 482
    //   477: aload 42
    //   479: invokevirtual 345	java/io/InputStream:close	()V
    //   482: aload 4
    //   484: areturn
    //   485: new 311	java/io/BufferedReader
    //   488: astore 47
    //   490: new 313	java/io/InputStreamReader
    //   493: dup
    //   494: aload 42
    //   496: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   499: astore 55
    //   501: aload 47
    //   503: astore 56
    //   505: aload 55
    //   507: astore 57
    //   509: aload 56
    //   511: aload 57
    //   513: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   516: goto -75 -> 441
    //   519: aload 46
    //   521: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   524: astore 58
    //   526: new 149	java/lang/StringBuilder
    //   529: dup
    //   530: aload 58
    //   532: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   535: astore 59
    //   537: aload 52
    //   539: astore 60
    //   541: aload 59
    //   543: aload 60
    //   545: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   551: astore 46
    //   553: goto -112 -> 441
    //   556: astore 61
    //   558: aload 61
    //   560: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   563: aload 61
    //   565: instanceof 233
    //   568: ifne +11 -> 579
    //   571: aload 61
    //   573: instanceof 350
    //   576: ifeq +35 -> 611
    //   579: aload 61
    //   581: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   584: astore 62
    //   586: new 233	java/net/SocketException
    //   589: dup
    //   590: aload 62
    //   592: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   595: athrow
    //   596: astore 63
    //   598: aload 42
    //   600: ifnull +8 -> 608
    //   603: aload 42
    //   605: invokevirtual 345	java/io/InputStream:close	()V
    //   608: aload 63
    //   610: athrow
    //   611: aload 42
    //   613: ifnull -131 -> 482
    //   616: aload 42
    //   618: invokevirtual 345	java/io/InputStream:close	()V
    //   621: goto -139 -> 482
    //   624: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   627: goto -145 -> 482
    //   630: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   633: goto -25 -> 608
    //   636: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   639: goto -157 -> 482
    //
    // Exception table:
    //   from	to	target	type
    //   66	472	556	java/lang/Throwable
    //   485	553	556	java/lang/Throwable
    //   66	472	596	finally
    //   485	553	596	finally
    //   558	596	596	finally
    //   616	621	624	java/io/IOException
    //   603	608	630	java/io/IOException
    //   477	482	636	java/io/IOException
  }

  // ERROR //
  @Signature({"(IIII)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getAppList(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   13: ifnonnull +26 -> 39
    //   16: aload_0
    //   17: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   20: astore 6
    //   22: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   25: dup
    //   26: aload 6
    //   28: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   31: astore 7
    //   33: aload_0
    //   34: aload 7
    //   36: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   39: new 242	org/apache/http/client/methods/HttpPost
    //   42: dup
    //   43: ldc 56
    //   45: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: astore 8
    //   50: aload 8
    //   52: ldc_w 363
    //   55: ldc_w 365
    //   58: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: ldc_w 321
    //   64: astore 9
    //   66: aconst_null
    //   67: astore 10
    //   69: new 245	java/util/ArrayList
    //   72: astore 11
    //   74: aload 11
    //   76: astore 12
    //   78: iconst_4
    //   79: istore 13
    //   81: aload 12
    //   83: iload 13
    //   85: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   88: aload_0
    //   89: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   92: astore 14
    //   94: new 253	org/apache/http/message/BasicNameValuePair
    //   97: dup
    //   98: ldc 255
    //   100: aload 14
    //   102: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   105: astore 15
    //   107: aload 11
    //   109: astore 16
    //   111: aload 15
    //   113: astore 17
    //   115: aload 16
    //   117: aload 17
    //   119: invokeinterface 264 2 0
    //   124: pop
    //   125: aload_0
    //   126: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   129: astore 18
    //   131: new 253	org/apache/http/message/BasicNameValuePair
    //   134: dup
    //   135: ldc_w 268
    //   138: aload 18
    //   140: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   143: astore 19
    //   145: aload 11
    //   147: astore 20
    //   149: aload 19
    //   151: astore 21
    //   153: aload 20
    //   155: aload 21
    //   157: invokeinterface 264 2 0
    //   162: pop
    //   163: new 253	org/apache/http/message/BasicNameValuePair
    //   166: dup
    //   167: ldc_w 270
    //   170: ldc 9
    //   172: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   175: astore 22
    //   177: aload 11
    //   179: astore 23
    //   181: aload 22
    //   183: astore 24
    //   185: aload 23
    //   187: aload 24
    //   189: invokeinterface 264 2 0
    //   194: pop
    //   195: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   198: istore 25
    //   200: aload_0
    //   201: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   204: astore 26
    //   206: aload_0
    //   207: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   210: astore 27
    //   212: aload_0
    //   213: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   216: astore 28
    //   218: iload_2
    //   219: ifne +18 -> 237
    //   222: iload_1
    //   223: istore 29
    //   225: sipush 9291
    //   228: istore 30
    //   230: iload 29
    //   232: iload 30
    //   234: if_icmpeq +307 -> 541
    //   237: iload_1
    //   238: istore 31
    //   240: iload_2
    //   241: istore 32
    //   243: iload_3
    //   244: istore 33
    //   246: iload 4
    //   248: istore 34
    //   250: iload 31
    //   252: iload 32
    //   254: iload 33
    //   256: iload 25
    //   258: iload 34
    //   260: aload 26
    //   262: aload 27
    //   264: aload 28
    //   266: invokestatic 584	com/yingyonghui/market/json/JsonParams:getAppListParams	(IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   269: astore 35
    //   271: new 253	org/apache/http/message/BasicNameValuePair
    //   274: astore 36
    //   276: aload 36
    //   278: astore 37
    //   280: ldc_w 280
    //   283: astore 38
    //   285: aload 35
    //   287: astore 39
    //   289: aload 37
    //   291: aload 38
    //   293: aload 39
    //   295: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   298: aload 11
    //   300: astore 40
    //   302: aload 36
    //   304: astore 41
    //   306: aload 40
    //   308: aload 41
    //   310: invokeinterface 264 2 0
    //   315: pop
    //   316: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   319: astore 42
    //   321: aload 42
    //   323: astore 43
    //   325: aload 11
    //   327: astore 44
    //   329: ldc_w 284
    //   332: astore 45
    //   334: aload 43
    //   336: aload 44
    //   338: aload 45
    //   340: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   343: aload 8
    //   345: aload 42
    //   347: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   350: aload_0
    //   351: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   354: aload 8
    //   356: invokeinterface 297 2 0
    //   361: astore 46
    //   363: aload 46
    //   365: invokeinterface 303 1 0
    //   370: invokeinterface 309 1 0
    //   375: astore 10
    //   377: aload 46
    //   379: astore 47
    //   381: ldc_w 374
    //   384: astore 48
    //   386: aload 47
    //   388: aload 48
    //   390: invokeinterface 378 2 0
    //   395: astore 49
    //   397: aload 49
    //   399: ifnull +12 -> 411
    //   402: aload 49
    //   404: invokeinterface 383 1 0
    //   409: astore 9
    //   411: ldc_w 321
    //   414: astore 50
    //   416: aload 9
    //   418: ldc_w 365
    //   421: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   424: ifeq +254 -> 678
    //   427: new 311	java/io/BufferedReader
    //   430: astore 51
    //   432: new 388	java/util/zip/GZIPInputStream
    //   435: astore 52
    //   437: aload 52
    //   439: astore 53
    //   441: aload 10
    //   443: astore 54
    //   445: aload 53
    //   447: aload 54
    //   449: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   452: new 313	java/io/InputStreamReader
    //   455: dup
    //   456: aload 52
    //   458: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   461: astore 55
    //   463: aload 51
    //   465: astore 56
    //   467: aload 55
    //   469: astore 57
    //   471: aload 56
    //   473: aload 57
    //   475: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   478: aload 51
    //   480: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   483: astore 58
    //   485: aload 58
    //   487: ifnonnull +234 -> 721
    //   490: iload_2
    //   491: ifne +18 -> 509
    //   494: iload_1
    //   495: istore 59
    //   497: sipush 9291
    //   500: istore 60
    //   502: iload 59
    //   504: iload 60
    //   506: if_icmpeq +252 -> 758
    //   509: aload_0
    //   510: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   513: astore 61
    //   515: aload 50
    //   517: astore 62
    //   519: aload 61
    //   521: aload 62
    //   523: invokestatic 577	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   526: astore 5
    //   528: aload 10
    //   530: ifnull +8 -> 538
    //   533: aload 10
    //   535: invokevirtual 345	java/io/InputStream:close	()V
    //   538: aload 5
    //   540: areturn
    //   541: iload_1
    //   542: istore 63
    //   544: iload_2
    //   545: istore 64
    //   547: iload_3
    //   548: istore 65
    //   550: iload 4
    //   552: istore 66
    //   554: iload 63
    //   556: iload 64
    //   558: iload 65
    //   560: iload 25
    //   562: iload 66
    //   564: aload 26
    //   566: aload 27
    //   568: aload 28
    //   570: invokestatic 587	com/yingyonghui/market/json/JsonParams:getBannerAndAppListParams	(IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   573: astore 67
    //   575: new 253	org/apache/http/message/BasicNameValuePair
    //   578: astore 68
    //   580: aload 68
    //   582: astore 69
    //   584: ldc_w 280
    //   587: astore 70
    //   589: aload 67
    //   591: astore 71
    //   593: aload 69
    //   595: aload 70
    //   597: aload 71
    //   599: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   602: aload 11
    //   604: astore 72
    //   606: aload 68
    //   608: astore 73
    //   610: aload 72
    //   612: aload 73
    //   614: invokeinterface 264 2 0
    //   619: pop
    //   620: goto -304 -> 316
    //   623: astore 74
    //   625: aload 74
    //   627: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   630: aload 74
    //   632: instanceof 233
    //   635: ifne +11 -> 646
    //   638: aload 74
    //   640: instanceof 350
    //   643: ifeq +137 -> 780
    //   646: aload 74
    //   648: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   651: astore 75
    //   653: new 233	java/net/SocketException
    //   656: dup
    //   657: aload 75
    //   659: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   662: athrow
    //   663: astore 63
    //   665: aload 10
    //   667: ifnull +8 -> 675
    //   670: aload 10
    //   672: invokevirtual 345	java/io/InputStream:close	()V
    //   675: aload 63
    //   677: athrow
    //   678: new 311	java/io/BufferedReader
    //   681: astore 51
    //   683: new 313	java/io/InputStreamReader
    //   686: astore 76
    //   688: aload 76
    //   690: astore 77
    //   692: aload 10
    //   694: astore 78
    //   696: aload 77
    //   698: aload 78
    //   700: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   703: aload 51
    //   705: astore 79
    //   707: aload 76
    //   709: astore 80
    //   711: aload 79
    //   713: aload 80
    //   715: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   718: goto -240 -> 478
    //   721: aload 50
    //   723: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   726: astore 81
    //   728: new 149	java/lang/StringBuilder
    //   731: dup
    //   732: aload 81
    //   734: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   737: astore 82
    //   739: aload 58
    //   741: astore 83
    //   743: aload 82
    //   745: aload 83
    //   747: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   753: astore 50
    //   755: goto -277 -> 478
    //   758: aload_0
    //   759: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   762: astore 84
    //   764: aload 50
    //   766: astore 85
    //   768: aload 84
    //   770: aload 85
    //   772: invokestatic 590	com/yingyonghui/market/json/JsonUtils:getAssetObjectWithBanner	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   775: astore 5
    //   777: goto -249 -> 528
    //   780: aload 10
    //   782: ifnull -244 -> 538
    //   785: aload 10
    //   787: invokevirtual 345	java/io/InputStream:close	()V
    //   790: goto -252 -> 538
    //   793: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   796: goto -258 -> 538
    //   799: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   802: goto -127 -> 675
    //   805: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   808: goto -270 -> 538
    //
    // Exception table:
    //   from	to	target	type
    //   69	528	623	java/lang/Throwable
    //   554	620	623	java/lang/Throwable
    //   678	777	623	java/lang/Throwable
    //   69	528	663	finally
    //   554	620	663	finally
    //   625	663	663	finally
    //   678	777	663	finally
    //   785	790	793	java/io/IOException
    //   670	675	799	java/io/IOException
    //   533	538	805	java/io/IOException
  }

  // ERROR //
  @Signature({"(IIII)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getAppRankingList(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   13: ifnonnull +26 -> 39
    //   16: aload_0
    //   17: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   20: astore 6
    //   22: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   25: dup
    //   26: aload 6
    //   28: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   31: astore 7
    //   33: aload_0
    //   34: aload 7
    //   36: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   39: new 242	org/apache/http/client/methods/HttpPost
    //   42: dup
    //   43: ldc 56
    //   45: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: astore 8
    //   50: aload 8
    //   52: ldc_w 363
    //   55: ldc_w 365
    //   58: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: ldc_w 321
    //   64: astore 9
    //   66: new 245	java/util/ArrayList
    //   69: astore 10
    //   71: aload 10
    //   73: astore 11
    //   75: iconst_4
    //   76: istore 12
    //   78: aload 11
    //   80: iload 12
    //   82: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   85: aload_0
    //   86: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   89: astore 13
    //   91: new 253	org/apache/http/message/BasicNameValuePair
    //   94: dup
    //   95: ldc 255
    //   97: aload 13
    //   99: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: astore 14
    //   104: aload 10
    //   106: astore 15
    //   108: aload 14
    //   110: astore 16
    //   112: aload 15
    //   114: aload 16
    //   116: invokeinterface 264 2 0
    //   121: pop
    //   122: aload_0
    //   123: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   126: astore 17
    //   128: new 253	org/apache/http/message/BasicNameValuePair
    //   131: dup
    //   132: ldc_w 268
    //   135: aload 17
    //   137: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: astore 18
    //   142: aload 10
    //   144: astore 19
    //   146: aload 18
    //   148: astore 20
    //   150: aload 19
    //   152: aload 20
    //   154: invokeinterface 264 2 0
    //   159: pop
    //   160: new 253	org/apache/http/message/BasicNameValuePair
    //   163: dup
    //   164: ldc_w 270
    //   167: ldc 9
    //   169: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: astore 21
    //   174: aload 10
    //   176: astore 22
    //   178: aload 21
    //   180: astore 23
    //   182: aload 22
    //   184: aload 23
    //   186: invokeinterface 264 2 0
    //   191: pop
    //   192: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   195: istore 24
    //   197: aload_0
    //   198: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   201: astore 25
    //   203: aload_0
    //   204: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   207: astore 26
    //   209: aload_0
    //   210: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   213: astore 27
    //   215: iload_1
    //   216: istore 28
    //   218: iload_2
    //   219: istore 29
    //   221: iload_3
    //   222: istore 30
    //   224: iload 4
    //   226: istore 31
    //   228: iload 28
    //   230: iload 29
    //   232: iload 30
    //   234: iload 31
    //   236: iload 24
    //   238: aload 25
    //   240: aload 26
    //   242: aload 27
    //   244: invokestatic 594	com/yingyonghui/market/json/JsonParams:getAppRankingListParams	(IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   247: astore 32
    //   249: new 253	org/apache/http/message/BasicNameValuePair
    //   252: astore 33
    //   254: aload 33
    //   256: astore 34
    //   258: ldc_w 280
    //   261: astore 35
    //   263: aload 32
    //   265: astore 36
    //   267: aload 34
    //   269: aload 35
    //   271: aload 36
    //   273: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   276: aload 10
    //   278: astore 37
    //   280: aload 33
    //   282: astore 38
    //   284: aload 37
    //   286: aload 38
    //   288: invokeinterface 264 2 0
    //   293: pop
    //   294: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   297: astore 39
    //   299: aload 39
    //   301: astore 40
    //   303: aload 10
    //   305: astore 41
    //   307: ldc_w 284
    //   310: astore 42
    //   312: aload 40
    //   314: aload 41
    //   316: aload 42
    //   318: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   321: aload 8
    //   323: aload 39
    //   325: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   328: aload_0
    //   329: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   332: aload 8
    //   334: invokeinterface 297 2 0
    //   339: astore 43
    //   341: aload 43
    //   343: invokeinterface 303 1 0
    //   348: invokeinterface 309 1 0
    //   353: astore 44
    //   355: aload 43
    //   357: astore 45
    //   359: ldc_w 374
    //   362: astore 46
    //   364: aload 45
    //   366: aload 46
    //   368: invokeinterface 378 2 0
    //   373: astore 47
    //   375: aload 47
    //   377: ifnull +12 -> 389
    //   380: aload 47
    //   382: invokeinterface 383 1 0
    //   387: astore 9
    //   389: ldc_w 321
    //   392: astore 48
    //   394: aload 9
    //   396: ldc_w 365
    //   399: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   402: ifeq +98 -> 500
    //   405: new 311	java/io/BufferedReader
    //   408: astore 49
    //   410: new 388	java/util/zip/GZIPInputStream
    //   413: astore 50
    //   415: aload 50
    //   417: astore 51
    //   419: aload 44
    //   421: astore 52
    //   423: aload 51
    //   425: aload 52
    //   427: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   430: new 313	java/io/InputStreamReader
    //   433: dup
    //   434: aload 50
    //   436: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   439: astore 53
    //   441: aload 49
    //   443: astore 54
    //   445: aload 53
    //   447: astore 55
    //   449: aload 54
    //   451: aload 55
    //   453: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   456: aload 49
    //   458: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   461: astore 56
    //   463: aload 56
    //   465: ifnonnull +78 -> 543
    //   468: aload_0
    //   469: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   472: astore 57
    //   474: aload 48
    //   476: astore 58
    //   478: aload 57
    //   480: aload 58
    //   482: invokestatic 577	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   485: astore 5
    //   487: aload 44
    //   489: ifnull +8 -> 497
    //   492: aload 44
    //   494: invokevirtual 345	java/io/InputStream:close	()V
    //   497: aload 5
    //   499: areturn
    //   500: new 311	java/io/BufferedReader
    //   503: astore 49
    //   505: new 313	java/io/InputStreamReader
    //   508: astore 59
    //   510: aload 59
    //   512: astore 60
    //   514: aload 44
    //   516: astore 61
    //   518: aload 60
    //   520: aload 61
    //   522: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   525: aload 49
    //   527: astore 62
    //   529: aload 59
    //   531: astore 63
    //   533: aload 62
    //   535: aload 63
    //   537: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   540: goto -84 -> 456
    //   543: aload 48
    //   545: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   548: astore 64
    //   550: new 149	java/lang/StringBuilder
    //   553: dup
    //   554: aload 64
    //   556: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   559: astore 65
    //   561: aload 56
    //   563: astore 66
    //   565: aload 65
    //   567: aload 66
    //   569: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   575: astore 48
    //   577: goto -121 -> 456
    //   580: astore 67
    //   582: aload 67
    //   584: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   587: aload 67
    //   589: instanceof 233
    //   592: ifne +11 -> 603
    //   595: aload 67
    //   597: instanceof 350
    //   600: ifeq +35 -> 635
    //   603: aload 67
    //   605: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   608: astore 68
    //   610: new 233	java/net/SocketException
    //   613: dup
    //   614: aload 68
    //   616: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   619: athrow
    //   620: astore 69
    //   622: aload 44
    //   624: ifnull +8 -> 632
    //   627: aload 44
    //   629: invokevirtual 345	java/io/InputStream:close	()V
    //   632: aload 69
    //   634: athrow
    //   635: aload 44
    //   637: ifnull -140 -> 497
    //   640: aload 44
    //   642: invokevirtual 345	java/io/InputStream:close	()V
    //   645: goto -148 -> 497
    //   648: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   651: goto -154 -> 497
    //   654: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   657: goto -25 -> 632
    //   660: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   663: goto -166 -> 497
    //
    // Exception table:
    //   from	to	target	type
    //   66	487	580	java/lang/Throwable
    //   500	577	580	java/lang/Throwable
    //   66	487	620	finally
    //   500	577	620	finally
    //   582	620	620	finally
    //   640	645	648	java/io/IOException
    //   627	632	654	java/io/IOException
    //   492	497	660	java/io/IOException
  }

  // ERROR //
  @Signature({"(", "Ljava/lang/String;", "II)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getAppRelativeList(String paramString, int paramInt1, int paramInt2)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   13: ifnonnull +26 -> 39
    //   16: aload_0
    //   17: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   20: astore 5
    //   22: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   25: dup
    //   26: aload 5
    //   28: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   31: astore 6
    //   33: aload_0
    //   34: aload 6
    //   36: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   39: new 242	org/apache/http/client/methods/HttpPost
    //   42: dup
    //   43: ldc 56
    //   45: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: astore 7
    //   50: aload 7
    //   52: ldc_w 363
    //   55: ldc_w 365
    //   58: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: ldc_w 321
    //   64: astore 8
    //   66: new 245	java/util/ArrayList
    //   69: astore 9
    //   71: aload 9
    //   73: astore 10
    //   75: iconst_4
    //   76: istore 11
    //   78: aload 10
    //   80: iload 11
    //   82: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   85: aload_0
    //   86: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   89: astore 12
    //   91: new 253	org/apache/http/message/BasicNameValuePair
    //   94: dup
    //   95: ldc 255
    //   97: aload 12
    //   99: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: astore 13
    //   104: aload 9
    //   106: astore 14
    //   108: aload 13
    //   110: astore 15
    //   112: aload 14
    //   114: aload 15
    //   116: invokeinterface 264 2 0
    //   121: pop
    //   122: aload_0
    //   123: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   126: astore 16
    //   128: new 253	org/apache/http/message/BasicNameValuePair
    //   131: dup
    //   132: ldc_w 268
    //   135: aload 16
    //   137: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   140: astore 17
    //   142: aload 9
    //   144: astore 18
    //   146: aload 17
    //   148: astore 19
    //   150: aload 18
    //   152: aload 19
    //   154: invokeinterface 264 2 0
    //   159: pop
    //   160: new 253	org/apache/http/message/BasicNameValuePair
    //   163: dup
    //   164: ldc_w 270
    //   167: ldc 9
    //   169: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: astore 20
    //   174: aload 9
    //   176: astore 21
    //   178: aload 20
    //   180: astore 22
    //   182: aload 21
    //   184: aload 22
    //   186: invokeinterface 264 2 0
    //   191: pop
    //   192: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   195: istore 23
    //   197: aload_0
    //   198: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   201: astore 24
    //   203: aload_0
    //   204: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   207: astore 25
    //   209: aload_0
    //   210: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   213: astore 26
    //   215: aload_1
    //   216: astore 27
    //   218: iload_2
    //   219: istore 28
    //   221: iload_3
    //   222: istore 29
    //   224: aload 27
    //   226: iload 28
    //   228: iload 29
    //   230: iload 23
    //   232: aload 24
    //   234: aload 25
    //   236: aload 26
    //   238: invokestatic 601	com/yingyonghui/market/json/JsonParams:getAppRelativeListParams	(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   241: astore 30
    //   243: new 253	org/apache/http/message/BasicNameValuePair
    //   246: astore 31
    //   248: aload 31
    //   250: astore 32
    //   252: ldc_w 280
    //   255: astore 33
    //   257: aload 30
    //   259: astore 34
    //   261: aload 32
    //   263: aload 33
    //   265: aload 34
    //   267: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   270: aload 9
    //   272: astore 35
    //   274: aload 31
    //   276: astore 36
    //   278: aload 35
    //   280: aload 36
    //   282: invokeinterface 264 2 0
    //   287: pop
    //   288: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   291: astore 37
    //   293: aload 37
    //   295: astore 38
    //   297: aload 9
    //   299: astore 39
    //   301: ldc_w 284
    //   304: astore 40
    //   306: aload 38
    //   308: aload 39
    //   310: aload 40
    //   312: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   315: aload 7
    //   317: aload 37
    //   319: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   322: aload_0
    //   323: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   326: aload 7
    //   328: invokeinterface 297 2 0
    //   333: astore 41
    //   335: aload 41
    //   337: invokeinterface 303 1 0
    //   342: invokeinterface 309 1 0
    //   347: astore 42
    //   349: aload 41
    //   351: astore 43
    //   353: ldc_w 374
    //   356: astore 44
    //   358: aload 43
    //   360: aload 44
    //   362: invokeinterface 378 2 0
    //   367: astore 45
    //   369: aload 45
    //   371: ifnull +12 -> 383
    //   374: aload 45
    //   376: invokeinterface 383 1 0
    //   381: astore 8
    //   383: ldc_w 321
    //   386: astore 46
    //   388: aload 8
    //   390: ldc_w 365
    //   393: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifeq +89 -> 485
    //   399: new 311	java/io/BufferedReader
    //   402: astore 47
    //   404: new 388	java/util/zip/GZIPInputStream
    //   407: dup
    //   408: aload 42
    //   410: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   413: astore 48
    //   415: new 313	java/io/InputStreamReader
    //   418: dup
    //   419: aload 48
    //   421: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   424: astore 49
    //   426: aload 47
    //   428: astore 50
    //   430: aload 49
    //   432: astore 51
    //   434: aload 50
    //   436: aload 51
    //   438: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   441: aload 47
    //   443: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   446: astore 52
    //   448: aload 52
    //   450: ifnonnull +69 -> 519
    //   453: aload_0
    //   454: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   457: astore 53
    //   459: aload 46
    //   461: astore 54
    //   463: aload 53
    //   465: aload 54
    //   467: invokestatic 577	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/ArrayList;
    //   470: astore 4
    //   472: aload 42
    //   474: ifnull +8 -> 482
    //   477: aload 42
    //   479: invokevirtual 345	java/io/InputStream:close	()V
    //   482: aload 4
    //   484: areturn
    //   485: new 311	java/io/BufferedReader
    //   488: astore 47
    //   490: new 313	java/io/InputStreamReader
    //   493: dup
    //   494: aload 42
    //   496: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   499: astore 55
    //   501: aload 47
    //   503: astore 56
    //   505: aload 55
    //   507: astore 57
    //   509: aload 56
    //   511: aload 57
    //   513: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   516: goto -75 -> 441
    //   519: aload 46
    //   521: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   524: astore 58
    //   526: new 149	java/lang/StringBuilder
    //   529: dup
    //   530: aload 58
    //   532: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   535: astore 59
    //   537: aload 52
    //   539: astore 60
    //   541: aload 59
    //   543: aload 60
    //   545: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   551: astore 46
    //   553: goto -112 -> 441
    //   556: astore 61
    //   558: aload 61
    //   560: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   563: aload 61
    //   565: instanceof 233
    //   568: ifne +11 -> 579
    //   571: aload 61
    //   573: instanceof 350
    //   576: ifeq +35 -> 611
    //   579: aload 61
    //   581: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   584: astore 62
    //   586: new 233	java/net/SocketException
    //   589: dup
    //   590: aload 62
    //   592: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   595: athrow
    //   596: astore 63
    //   598: aload 42
    //   600: ifnull +8 -> 608
    //   603: aload 42
    //   605: invokevirtual 345	java/io/InputStream:close	()V
    //   608: aload 63
    //   610: athrow
    //   611: aload 42
    //   613: ifnull -131 -> 482
    //   616: aload 42
    //   618: invokevirtual 345	java/io/InputStream:close	()V
    //   621: goto -139 -> 482
    //   624: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   627: goto -145 -> 482
    //   630: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   633: goto -25 -> 608
    //   636: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   639: goto -157 -> 482
    //
    // Exception table:
    //   from	to	target	type
    //   66	472	556	java/lang/Throwable
    //   485	553	556	java/lang/Throwable
    //   66	472	596	finally
    //   485	553	596	finally
    //   558	596	596	finally
    //   616	621	624	java/io/IOException
    //   603	608	630	java/io/IOException
    //   477	482	636	java/io/IOException
  }

  // ERROR //
  @Signature({"(I)", "Ljava/util/ArrayList", "<", "Landroid/graphics/drawable/Drawable;", ">;"})
  public ArrayList getAppScreenShorts(int paramInt)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   12: ifnonnull +24 -> 36
    //   15: aload_0
    //   16: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   19: astore_3
    //   20: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   23: dup
    //   24: aload_3
    //   25: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   28: astore 4
    //   30: aload_0
    //   31: aload 4
    //   33: putfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   36: new 242	org/apache/http/client/methods/HttpPost
    //   39: astore 5
    //   41: aload 5
    //   43: astore 6
    //   45: ldc 56
    //   47: astore 7
    //   49: aload 6
    //   51: aload 7
    //   53: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   56: new 245	java/util/ArrayList
    //   59: astore 8
    //   61: aload 8
    //   63: astore 9
    //   65: iconst_4
    //   66: istore 10
    //   68: aload 9
    //   70: iload 10
    //   72: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   75: aload_0
    //   76: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   79: astore 11
    //   81: new 253	org/apache/http/message/BasicNameValuePair
    //   84: dup
    //   85: ldc 255
    //   87: aload 11
    //   89: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: astore 12
    //   94: aload 8
    //   96: astore 13
    //   98: aload 12
    //   100: astore 14
    //   102: aload 13
    //   104: aload 14
    //   106: invokeinterface 264 2 0
    //   111: pop
    //   112: aload_0
    //   113: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   116: astore 15
    //   118: new 253	org/apache/http/message/BasicNameValuePair
    //   121: dup
    //   122: ldc_w 268
    //   125: aload 15
    //   127: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: astore 16
    //   132: aload 8
    //   134: astore 17
    //   136: aload 16
    //   138: astore 18
    //   140: aload 17
    //   142: aload 18
    //   144: invokeinterface 264 2 0
    //   149: pop
    //   150: new 253	org/apache/http/message/BasicNameValuePair
    //   153: dup
    //   154: ldc_w 270
    //   157: ldc 9
    //   159: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: astore 19
    //   164: aload 8
    //   166: astore 20
    //   168: aload 19
    //   170: astore 21
    //   172: aload 20
    //   174: aload 21
    //   176: invokeinterface 264 2 0
    //   181: pop
    //   182: aload_0
    //   183: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   186: astore 22
    //   188: iload_1
    //   189: istore 23
    //   191: aload 22
    //   193: astore 24
    //   195: iload 23
    //   197: aload 24
    //   199: invokestatic 610	com/yingyonghui/market/json/JsonParams:getAppSnapsizeParams	(ILjava/lang/String;)Ljava/lang/String;
    //   202: astore 25
    //   204: new 253	org/apache/http/message/BasicNameValuePair
    //   207: astore 26
    //   209: aload 26
    //   211: astore 27
    //   213: ldc_w 280
    //   216: astore 28
    //   218: aload 25
    //   220: astore 29
    //   222: aload 27
    //   224: aload 28
    //   226: aload 29
    //   228: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   231: aload 8
    //   233: astore 30
    //   235: aload 26
    //   237: astore 31
    //   239: aload 30
    //   241: aload 31
    //   243: invokeinterface 264 2 0
    //   248: pop
    //   249: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   252: astore 32
    //   254: aload 32
    //   256: astore 33
    //   258: aload 8
    //   260: astore 34
    //   262: ldc_w 284
    //   265: astore 35
    //   267: aload 33
    //   269: aload 34
    //   271: aload 35
    //   273: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   276: aload 5
    //   278: astore 36
    //   280: aload 32
    //   282: astore 37
    //   284: aload 36
    //   286: aload 37
    //   288: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   291: aload_0
    //   292: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   295: astore 38
    //   297: aload 5
    //   299: astore 39
    //   301: aload 38
    //   303: aload 39
    //   305: invokeinterface 297 2 0
    //   310: invokeinterface 303 1 0
    //   315: invokeinterface 309 1 0
    //   320: astore 40
    //   322: new 502	java/lang/StringBuffer
    //   325: dup
    //   326: invokespecial 503	java/lang/StringBuffer:<init>	()V
    //   329: astore 41
    //   331: new 313	java/io/InputStreamReader
    //   334: astore 42
    //   336: aload 42
    //   338: astore 43
    //   340: aload 40
    //   342: astore 44
    //   344: aload 43
    //   346: aload 44
    //   348: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   351: sipush 1024
    //   354: newarray char
    //   356: astore 45
    //   358: aload 45
    //   360: arraylength
    //   361: istore 46
    //   363: aload 42
    //   365: astore 47
    //   367: aload 45
    //   369: astore 48
    //   371: iconst_0
    //   372: istore 49
    //   374: iload 46
    //   376: istore 50
    //   378: aload 47
    //   380: aload 48
    //   382: iload 49
    //   384: iload 50
    //   386: invokevirtual 611	java/io/InputStreamReader:read	([CII)I
    //   389: istore 51
    //   391: iload 51
    //   393: ifgt +212 -> 605
    //   396: aload 41
    //   398: invokevirtual 508	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   401: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   404: istore 52
    //   406: iload 52
    //   408: ifle +185 -> 593
    //   411: aload_0
    //   412: getfield 93	com/yingyonghui/market/online/MarketServiceAgent:mGetSnapshotURL	Ljava/lang/String;
    //   415: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   418: astore 53
    //   420: new 149	java/lang/StringBuilder
    //   423: dup
    //   424: aload 53
    //   426: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   429: ldc_w 399
    //   432: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   435: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   438: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   441: astore 54
    //   443: new 149	java/lang/StringBuilder
    //   446: dup
    //   447: aload 54
    //   449: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   452: ldc_w 613
    //   455: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   461: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   464: astore 55
    //   466: new 149	java/lang/StringBuilder
    //   469: dup
    //   470: aload 55
    //   472: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   475: ldc_w 403
    //   478: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   481: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   484: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   487: astore 56
    //   489: new 149	java/lang/StringBuilder
    //   492: dup
    //   493: aload 56
    //   495: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   498: ldc_w 405
    //   501: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   504: astore 57
    //   506: iload_1
    //   507: istore 58
    //   509: aload 57
    //   511: iload 58
    //   513: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   516: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   519: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   522: astore 59
    //   524: new 149	java/lang/StringBuilder
    //   527: dup
    //   528: aload 59
    //   530: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   533: ldc_w 615
    //   536: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   542: astore 60
    //   544: new 617	android/graphics/BitmapFactory$Options
    //   547: dup
    //   548: invokespecial 618	android/graphics/BitmapFactory$Options:<init>	()V
    //   551: astore 61
    //   553: ldc_w 416
    //   556: istore 62
    //   558: aload 61
    //   560: iload 62
    //   562: putfield 621	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   565: iconst_1
    //   566: istore 63
    //   568: aload 61
    //   570: iload 63
    //   572: putfield 624	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   575: iconst_0
    //   576: istore 64
    //   578: iload 64
    //   580: istore 65
    //   582: iload 52
    //   584: istore 66
    //   586: iload 65
    //   588: iload 66
    //   590: if_icmplt +104 -> 694
    //   593: aload 40
    //   595: ifnull +8 -> 603
    //   598: aload 40
    //   600: invokevirtual 345	java/io/InputStream:close	()V
    //   603: aload_2
    //   604: areturn
    //   605: iconst_0
    //   606: istore 67
    //   608: aload 41
    //   610: astore 68
    //   612: aload 45
    //   614: astore 69
    //   616: iload 67
    //   618: istore 70
    //   620: iload 51
    //   622: istore 71
    //   624: aload 68
    //   626: aload 69
    //   628: iload 70
    //   630: iload 71
    //   632: invokevirtual 515	java/lang/StringBuffer:append	([CII)Ljava/lang/StringBuffer;
    //   635: pop
    //   636: goto -278 -> 358
    //   639: astore 72
    //   641: aload 72
    //   643: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   646: aload 72
    //   648: instanceof 233
    //   651: ifne +11 -> 662
    //   654: aload 72
    //   656: instanceof 350
    //   659: ifeq +380 -> 1039
    //   662: aload 72
    //   664: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   667: astore 73
    //   669: new 233	java/net/SocketException
    //   672: dup
    //   673: aload 73
    //   675: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   678: athrow
    //   679: astore 67
    //   681: aload 40
    //   683: ifnull +8 -> 691
    //   686: aload 40
    //   688: invokevirtual 345	java/io/InputStream:close	()V
    //   691: aload 67
    //   693: athrow
    //   694: aload 60
    //   696: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   699: astore 74
    //   701: new 149	java/lang/StringBuilder
    //   704: dup
    //   705: aload 74
    //   707: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   710: astore 75
    //   712: iload 64
    //   714: istore 76
    //   716: aload 75
    //   718: iload 76
    //   720: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   723: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   726: astore 77
    //   728: new 411	org/apache/http/client/methods/HttpGet
    //   731: astore 78
    //   733: aload 78
    //   735: astore 79
    //   737: aload 77
    //   739: astore 80
    //   741: aload 79
    //   743: aload 80
    //   745: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   748: ldc_w 626
    //   751: astore 81
    //   753: aload 77
    //   755: astore 82
    //   757: aload 81
    //   759: aload 82
    //   761: invokestatic 632	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   764: pop
    //   765: aload_0
    //   766: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   769: astore 83
    //   771: aload 78
    //   773: astore 84
    //   775: aload 83
    //   777: aload 84
    //   779: invokeinterface 297 2 0
    //   784: astore 85
    //   786: aload 85
    //   788: invokeinterface 303 1 0
    //   793: invokeinterface 309 1 0
    //   798: astore 86
    //   800: new 634	android/graphics/drawable/BitmapDrawable
    //   803: astore 87
    //   805: aload 87
    //   807: astore 88
    //   809: aload 86
    //   811: astore 89
    //   813: aload 88
    //   815: aload 89
    //   817: invokespecial 635	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   820: aload 86
    //   822: invokevirtual 345	java/io/InputStream:close	()V
    //   825: aload 87
    //   827: invokevirtual 639	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   830: astore 90
    //   832: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   835: istore 91
    //   837: iconst_3
    //   838: istore 92
    //   840: iload 91
    //   842: iload 92
    //   844: if_icmpne +56 -> 900
    //   847: new 634	android/graphics/drawable/BitmapDrawable
    //   850: astore 93
    //   852: aload 93
    //   854: astore 94
    //   856: aload 90
    //   858: astore 95
    //   860: aload 94
    //   862: aload 95
    //   864: invokespecial 642	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   867: aload 93
    //   869: astore 96
    //   871: aload 96
    //   873: ifnull +18 -> 891
    //   876: aload_2
    //   877: astore 97
    //   879: aload 96
    //   881: astore 98
    //   883: aload 97
    //   885: aload 98
    //   887: invokevirtual 643	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   890: pop
    //   891: iload 64
    //   893: iconst_1
    //   894: iadd
    //   895: istore 64
    //   897: goto -319 -> 578
    //   900: ldc_w 645
    //   903: invokestatic 651	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   906: astore 99
    //   908: iconst_2
    //   909: anewarray 647	java/lang/Class
    //   912: astore 100
    //   914: aload 100
    //   916: iconst_0
    //   917: ldc 76
    //   919: aastore
    //   920: aload 100
    //   922: iconst_1
    //   923: ldc_w 653
    //   926: aastore
    //   927: aload 99
    //   929: aload 100
    //   931: invokevirtual 657	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   934: astore 101
    //   936: iconst_2
    //   937: anewarray 4	java/lang/Object
    //   940: astore 102
    //   942: aload_0
    //   943: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   946: invokevirtual 74	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   949: astore 103
    //   951: aload 102
    //   953: iconst_0
    //   954: aload 103
    //   956: aastore
    //   957: aload 102
    //   959: iconst_1
    //   960: aload 90
    //   962: aastore
    //   963: aload 101
    //   965: astore 104
    //   967: aload 102
    //   969: astore 105
    //   971: aload 104
    //   973: aload 105
    //   975: invokevirtual 663	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   978: checkcast 545	android/graphics/drawable/Drawable
    //   981: astore 96
    //   983: goto -112 -> 871
    //   986: astore 72
    //   988: new 149	java/lang/StringBuilder
    //   991: dup
    //   992: ldc_w 665
    //   995: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   998: astore 106
    //   1000: iload_1
    //   1001: istore 107
    //   1003: aload 106
    //   1005: iload 107
    //   1007: invokevirtual 161	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1010: ldc_w 667
    //   1013: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1016: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1019: astore 108
    //   1021: aload 72
    //   1023: invokevirtual 670	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   1026: astore 109
    //   1028: aload 108
    //   1030: aload 109
    //   1032: invokestatic 673	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1035: pop
    //   1036: goto -145 -> 891
    //   1039: aload 40
    //   1041: ifnull -438 -> 603
    //   1044: aload 40
    //   1046: invokevirtual 345	java/io/InputStream:close	()V
    //   1049: goto -446 -> 603
    //   1052: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   1055: goto -452 -> 603
    //   1058: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   1061: goto -370 -> 691
    //   1064: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   1067: goto -464 -> 603
    //   1070: astore 110
    //   1072: goto -201 -> 871
    //
    // Exception table:
    //   from	to	target	type
    //   56	575	639	java/lang/Throwable
    //   608	636	639	java/lang/Throwable
    //   694	786	639	java/lang/Throwable
    //   988	1036	639	java/lang/Throwable
    //   56	575	679	finally
    //   608	636	679	finally
    //   641	679	679	finally
    //   694	786	679	finally
    //   786	891	679	finally
    //   900	983	679	finally
    //   988	1036	679	finally
    //   786	891	986	java/lang/Throwable
    //   900	983	986	java/lang/Throwable
    //   1044	1049	1052	java/io/IOException
    //   686	691	1058	java/io/IOException
    //   598	603	1064	java/io/IOException
    //   900	983	1070	java/lang/Exception
  }

  // ERROR //
  @Signature({"(I)", "Ljava/util/Vector", "<", "Lcom/yingyonghui/market/model/Category;", ">;"})
  public java.util.Vector getCategory(int paramInt)
    throws SocketException
  {
    // Byte code:
    //   0: new 679	java/util/Vector
    //   3: dup
    //   4: invokespecial 680	java/util/Vector:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   12: ifnonnull +24 -> 36
    //   15: aload_0
    //   16: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   19: astore_3
    //   20: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   23: dup
    //   24: aload_3
    //   25: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   28: astore 4
    //   30: aload_0
    //   31: aload 4
    //   33: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   36: new 242	org/apache/http/client/methods/HttpPost
    //   39: astore 5
    //   41: aload 5
    //   43: astore 6
    //   45: ldc 56
    //   47: astore 7
    //   49: aload 6
    //   51: aload 7
    //   53: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   56: aload 5
    //   58: astore 8
    //   60: ldc_w 363
    //   63: astore 9
    //   65: ldc_w 365
    //   68: astore 10
    //   70: aload 8
    //   72: aload 9
    //   74: aload 10
    //   76: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: ldc_w 321
    //   82: astore 11
    //   84: new 245	java/util/ArrayList
    //   87: astore 12
    //   89: aload 12
    //   91: astore 13
    //   93: iconst_4
    //   94: istore 14
    //   96: aload 13
    //   98: iload 14
    //   100: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   103: aload_0
    //   104: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   107: astore 15
    //   109: new 253	org/apache/http/message/BasicNameValuePair
    //   112: dup
    //   113: ldc 255
    //   115: aload 15
    //   117: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: astore 16
    //   122: aload 12
    //   124: astore 17
    //   126: aload 16
    //   128: astore 18
    //   130: aload 17
    //   132: aload 18
    //   134: invokeinterface 264 2 0
    //   139: pop
    //   140: aload_0
    //   141: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   144: astore 19
    //   146: new 253	org/apache/http/message/BasicNameValuePair
    //   149: dup
    //   150: ldc_w 268
    //   153: aload 19
    //   155: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   158: astore 20
    //   160: aload 12
    //   162: astore 21
    //   164: aload 20
    //   166: astore 22
    //   168: aload 21
    //   170: aload 22
    //   172: invokeinterface 264 2 0
    //   177: pop
    //   178: new 253	org/apache/http/message/BasicNameValuePair
    //   181: dup
    //   182: ldc_w 270
    //   185: ldc 9
    //   187: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   190: astore 23
    //   192: aload 12
    //   194: astore 24
    //   196: aload 23
    //   198: astore 25
    //   200: aload 24
    //   202: aload 25
    //   204: invokeinterface 264 2 0
    //   209: pop
    //   210: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   213: istore 26
    //   215: aload_0
    //   216: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   219: astore 27
    //   221: aload_0
    //   222: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   225: astore 28
    //   227: aload_0
    //   228: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   231: astore 29
    //   233: iload 26
    //   235: istore 30
    //   237: aload 27
    //   239: astore 31
    //   241: aload 28
    //   243: astore 32
    //   245: iload_1
    //   246: istore 33
    //   248: aload 29
    //   250: astore 34
    //   252: iload 30
    //   254: aload 31
    //   256: aload 32
    //   258: iload 33
    //   260: aload 34
    //   262: invokestatic 684	com/yingyonghui/market/json/JsonParams:getAppCategoryParams2	(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;
    //   265: astore 35
    //   267: new 253	org/apache/http/message/BasicNameValuePair
    //   270: astore 36
    //   272: aload 36
    //   274: astore 37
    //   276: ldc_w 280
    //   279: astore 38
    //   281: aload 35
    //   283: astore 39
    //   285: aload 37
    //   287: aload 38
    //   289: aload 39
    //   291: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   294: aload 12
    //   296: astore 40
    //   298: aload 36
    //   300: astore 41
    //   302: aload 40
    //   304: aload 41
    //   306: invokeinterface 264 2 0
    //   311: pop
    //   312: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   315: astore 42
    //   317: aload 42
    //   319: astore 43
    //   321: aload 12
    //   323: astore 44
    //   325: ldc_w 284
    //   328: astore 45
    //   330: aload 43
    //   332: aload 44
    //   334: aload 45
    //   336: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   339: aload 5
    //   341: astore 46
    //   343: aload 42
    //   345: astore 47
    //   347: aload 46
    //   349: aload 47
    //   351: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   354: aload_0
    //   355: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   358: astore 48
    //   360: aload 5
    //   362: astore 49
    //   364: aload 48
    //   366: aload 49
    //   368: invokeinterface 297 2 0
    //   373: astore 50
    //   375: aload 50
    //   377: invokeinterface 303 1 0
    //   382: invokeinterface 309 1 0
    //   387: astore 51
    //   389: aload 50
    //   391: astore 52
    //   393: ldc_w 374
    //   396: astore 53
    //   398: aload 52
    //   400: aload 53
    //   402: invokeinterface 378 2 0
    //   407: astore 54
    //   409: aload 54
    //   411: ifnull +12 -> 423
    //   414: aload 54
    //   416: invokeinterface 383 1 0
    //   421: astore 11
    //   423: ldc_w 321
    //   426: astore 55
    //   428: aload 11
    //   430: astore 56
    //   432: ldc_w 365
    //   435: astore 57
    //   437: aload 56
    //   439: aload 57
    //   441: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   444: ifeq +84 -> 528
    //   447: new 311	java/io/BufferedReader
    //   450: astore 58
    //   452: new 388	java/util/zip/GZIPInputStream
    //   455: astore 59
    //   457: aload 59
    //   459: astore 60
    //   461: aload 51
    //   463: astore 61
    //   465: aload 60
    //   467: aload 61
    //   469: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   472: new 313	java/io/InputStreamReader
    //   475: dup
    //   476: aload 59
    //   478: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   481: astore 62
    //   483: aload 58
    //   485: astore 63
    //   487: aload 62
    //   489: astore 64
    //   491: aload 63
    //   493: aload 64
    //   495: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   498: aload 58
    //   500: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   503: astore 65
    //   505: aload 65
    //   507: ifnonnull +64 -> 571
    //   510: aload 55
    //   512: invokestatic 688	com/yingyonghui/market/json/JsonUtils:getCategoryObject	(Ljava/lang/String;)Ljava/util/Vector;
    //   515: astore_2
    //   516: aload 51
    //   518: ifnull +8 -> 526
    //   521: aload 51
    //   523: invokevirtual 345	java/io/InputStream:close	()V
    //   526: aload_2
    //   527: areturn
    //   528: new 311	java/io/BufferedReader
    //   531: astore 58
    //   533: new 313	java/io/InputStreamReader
    //   536: astore 66
    //   538: aload 66
    //   540: astore 67
    //   542: aload 51
    //   544: astore 68
    //   546: aload 67
    //   548: aload 68
    //   550: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   553: aload 58
    //   555: astore 69
    //   557: aload 66
    //   559: astore 70
    //   561: aload 69
    //   563: aload 70
    //   565: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   568: goto -70 -> 498
    //   571: aload 55
    //   573: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   576: astore 71
    //   578: new 149	java/lang/StringBuilder
    //   581: dup
    //   582: aload 71
    //   584: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   587: astore 72
    //   589: aload 65
    //   591: astore 73
    //   593: aload 72
    //   595: aload 73
    //   597: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   600: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   603: astore 55
    //   605: goto -107 -> 498
    //   608: astore 74
    //   610: aload 74
    //   612: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   615: aload 74
    //   617: instanceof 233
    //   620: ifne +11 -> 631
    //   623: aload 74
    //   625: instanceof 350
    //   628: ifeq +35 -> 663
    //   631: aload 74
    //   633: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   636: astore 75
    //   638: new 233	java/net/SocketException
    //   641: dup
    //   642: aload 75
    //   644: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   647: athrow
    //   648: astore 76
    //   650: aload 51
    //   652: ifnull +8 -> 660
    //   655: aload 51
    //   657: invokevirtual 345	java/io/InputStream:close	()V
    //   660: aload 76
    //   662: athrow
    //   663: aload 51
    //   665: ifnull -139 -> 526
    //   668: aload 51
    //   670: invokevirtual 345	java/io/InputStream:close	()V
    //   673: goto -147 -> 526
    //   676: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   679: goto -153 -> 526
    //   682: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   685: goto -25 -> 660
    //   688: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   691: goto -165 -> 526
    //
    // Exception table:
    //   from	to	target	type
    //   84	516	608	java/lang/Throwable
    //   528	605	608	java/lang/Throwable
    //   84	516	648	finally
    //   528	605	648	finally
    //   610	648	648	finally
    //   668	673	676	java/io/IOException
    //   655	660	682	java/io/IOException
    //   521	526	688	java/io/IOException
  }

  public String getConnectKey()
  {
    if (CONNECT_KEY == null);
    for (String str = ""; ; str = CONNECT_KEY)
      return str;
  }

  // ERROR //
  @Signature({"([", "Ljava/lang/String;", "III)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getDownloadedAppList(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
    throws SocketException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   7: ifnonnull +26 -> 33
    //   10: aload_0
    //   11: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   14: astore 6
    //   16: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   19: dup
    //   20: aload 6
    //   22: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   25: astore 7
    //   27: aload_0
    //   28: aload 7
    //   30: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   33: new 242	org/apache/http/client/methods/HttpPost
    //   36: dup
    //   37: ldc 56
    //   39: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   42: astore 8
    //   44: aload 8
    //   46: ldc_w 363
    //   49: ldc_w 365
    //   52: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   55: ldc_w 321
    //   58: astore 9
    //   60: new 245	java/util/ArrayList
    //   63: astore 10
    //   65: aload 10
    //   67: astore 11
    //   69: iconst_4
    //   70: istore 12
    //   72: aload 11
    //   74: iload 12
    //   76: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   79: aload_0
    //   80: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   83: astore 13
    //   85: new 253	org/apache/http/message/BasicNameValuePair
    //   88: dup
    //   89: ldc 255
    //   91: aload 13
    //   93: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   96: astore 14
    //   98: aload 10
    //   100: astore 15
    //   102: aload 14
    //   104: astore 16
    //   106: aload 15
    //   108: aload 16
    //   110: invokeinterface 264 2 0
    //   115: pop
    //   116: aload_0
    //   117: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   120: astore 17
    //   122: new 253	org/apache/http/message/BasicNameValuePair
    //   125: dup
    //   126: ldc_w 268
    //   129: aload 17
    //   131: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   134: astore 18
    //   136: aload 10
    //   138: astore 19
    //   140: aload 18
    //   142: astore 20
    //   144: aload 19
    //   146: aload 20
    //   148: invokeinterface 264 2 0
    //   153: pop
    //   154: new 253	org/apache/http/message/BasicNameValuePair
    //   157: dup
    //   158: ldc_w 270
    //   161: ldc 9
    //   163: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: astore 21
    //   168: aload 10
    //   170: astore 22
    //   172: aload 21
    //   174: astore 23
    //   176: aload 22
    //   178: aload 23
    //   180: invokeinterface 264 2 0
    //   185: pop
    //   186: aload_0
    //   187: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   190: astore 24
    //   192: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   195: istore 25
    //   197: aload_0
    //   198: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   201: astore 26
    //   203: aload_0
    //   204: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   207: astore 27
    //   209: aload_1
    //   210: astore 28
    //   212: iload_2
    //   213: istore 29
    //   215: iload_3
    //   216: istore 30
    //   218: iload 4
    //   220: istore 31
    //   222: aload 28
    //   224: iload 29
    //   226: iload 30
    //   228: iload 31
    //   230: iload 25
    //   232: aload 24
    //   234: aload 26
    //   236: aload 27
    //   238: invokestatic 695	com/yingyonghui/market/json/JsonParams:getDowloadsParams	([Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   241: astore 32
    //   243: new 253	org/apache/http/message/BasicNameValuePair
    //   246: astore 33
    //   248: aload 33
    //   250: astore 34
    //   252: ldc_w 280
    //   255: astore 35
    //   257: aload 32
    //   259: astore 36
    //   261: aload 34
    //   263: aload 35
    //   265: aload 36
    //   267: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   270: aload 10
    //   272: astore 37
    //   274: aload 33
    //   276: astore 38
    //   278: aload 37
    //   280: aload 38
    //   282: invokeinterface 264 2 0
    //   287: pop
    //   288: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   291: astore 39
    //   293: aload 39
    //   295: astore 40
    //   297: aload 10
    //   299: astore 41
    //   301: ldc_w 284
    //   304: astore 42
    //   306: aload 40
    //   308: aload 41
    //   310: aload 42
    //   312: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   315: aload 8
    //   317: aload 39
    //   319: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   322: aload_0
    //   323: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   326: aload 8
    //   328: invokeinterface 297 2 0
    //   333: astore 43
    //   335: aload 43
    //   337: invokeinterface 303 1 0
    //   342: invokeinterface 309 1 0
    //   347: astore 44
    //   349: aload 43
    //   351: astore 45
    //   353: ldc_w 374
    //   356: astore 46
    //   358: aload 45
    //   360: aload 46
    //   362: invokeinterface 378 2 0
    //   367: astore 47
    //   369: aload 47
    //   371: ifnull +12 -> 383
    //   374: aload 47
    //   376: invokeinterface 383 1 0
    //   381: astore 9
    //   383: ldc_w 321
    //   386: astore 48
    //   388: aload 9
    //   390: ldc_w 365
    //   393: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifeq +105 -> 501
    //   399: new 311	java/io/BufferedReader
    //   402: astore 49
    //   404: new 388	java/util/zip/GZIPInputStream
    //   407: astore 50
    //   409: aload 50
    //   411: astore 51
    //   413: aload 44
    //   415: astore 52
    //   417: aload 51
    //   419: aload 52
    //   421: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   424: new 313	java/io/InputStreamReader
    //   427: dup
    //   428: aload 50
    //   430: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   433: astore 53
    //   435: aload 49
    //   437: astore 54
    //   439: aload 53
    //   441: astore 55
    //   443: aload 54
    //   445: aload 55
    //   447: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   450: aload 49
    //   452: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   455: astore 56
    //   457: aload 56
    //   459: ifnonnull +85 -> 544
    //   462: aload_0
    //   463: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   466: astore 57
    //   468: aload 48
    //   470: astore 58
    //   472: ldc_w 416
    //   475: istore 59
    //   477: aload 57
    //   479: aload 58
    //   481: iload 59
    //   483: invokestatic 698	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/util/ArrayList;
    //   486: astore 5
    //   488: aload 44
    //   490: ifnull +8 -> 498
    //   493: aload 44
    //   495: invokevirtual 345	java/io/InputStream:close	()V
    //   498: aload 5
    //   500: areturn
    //   501: new 311	java/io/BufferedReader
    //   504: astore 49
    //   506: new 313	java/io/InputStreamReader
    //   509: astore 60
    //   511: aload 60
    //   513: astore 61
    //   515: aload 44
    //   517: astore 62
    //   519: aload 61
    //   521: aload 62
    //   523: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   526: aload 49
    //   528: astore 63
    //   530: aload 60
    //   532: astore 64
    //   534: aload 63
    //   536: aload 64
    //   538: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   541: goto -91 -> 450
    //   544: aload 48
    //   546: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   549: astore 65
    //   551: new 149	java/lang/StringBuilder
    //   554: dup
    //   555: aload 65
    //   557: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   560: astore 66
    //   562: aload 56
    //   564: astore 67
    //   566: aload 66
    //   568: aload 67
    //   570: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: astore 48
    //   578: goto -128 -> 450
    //   581: astore 68
    //   583: aload 68
    //   585: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   588: aload 68
    //   590: instanceof 233
    //   593: ifne +11 -> 604
    //   596: aload 68
    //   598: instanceof 350
    //   601: ifeq +35 -> 636
    //   604: aload 68
    //   606: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   609: astore 69
    //   611: new 233	java/net/SocketException
    //   614: dup
    //   615: aload 69
    //   617: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   620: athrow
    //   621: astore 70
    //   623: aload 44
    //   625: ifnull +8 -> 633
    //   628: aload 44
    //   630: invokevirtual 345	java/io/InputStream:close	()V
    //   633: aload 70
    //   635: athrow
    //   636: aload 44
    //   638: ifnull -140 -> 498
    //   641: aload 44
    //   643: invokevirtual 345	java/io/InputStream:close	()V
    //   646: goto -148 -> 498
    //   649: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   652: goto -154 -> 498
    //   655: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   658: goto -25 -> 633
    //   661: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   664: goto -166 -> 498
    //
    // Exception table:
    //   from	to	target	type
    //   60	488	581	java/lang/Throwable
    //   501	578	581	java/lang/Throwable
    //   60	488	621	finally
    //   501	578	621	finally
    //   583	621	621	finally
    //   641	646	649	java/io/IOException
    //   628	633	655	java/io/IOException
    //   493	498	661	java/io/IOException
  }

  public int getEventId(String paramString)
  {
    int i;
    if (paramString == "install")
      i = 1;
    while (true)
    {
      return i;
      if (paramString == "update")
      {
        i = 2;
        continue;
      }
      i = 0;
    }
  }

  @Signature({"(", "Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;)", "Ljava/lang/String;"})
  public String getHeadersString(Map paramMap)
  {
    if (paramMap != null);
    while (true)
    {
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = paramMap.keySet().iterator();
        if (localIterator.hasNext())
          continue;
        str1 = localStringBuilder.toString();
        return str1;
        String str2 = (String)localIterator.next();
        localStringBuilder.append(str2);
        localStringBuilder.append("=");
        String str3 = URLEncoder.encode((String)paramMap.get(str2), "UTF-8");
        localStringBuilder.append(str3);
        if (!localIterator.hasNext())
          continue;
        localStringBuilder.append("&");
        continue;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        Log.e("HTTP_Request", "Unsupported Encoding Exception");
      }
      String str1 = null;
    }
  }

  public String getIpAddress()
  {
    try
    {
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      break label30;
      label4: if (!localEnumeration1.hasMoreElements());
      label30: InetAddress localInetAddress;
      for (String str1 = null; ; str1 = localInetAddress.getHostAddress().toString())
      {
        return str1;
        Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        if (!localEnumeration2.hasMoreElements())
          break label4;
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
        if (localInetAddress.isLoopbackAddress())
          break;
      }
    }
    catch (SocketException localSocketException)
    {
      while (true)
      {
        String str2 = localSocketException.toString();
        Log.e("WifiPreference IpAddress", str2);
      }
    }
  }

  // ERROR //
  public com.yingyonghui.market.model.NewsContent getNewsContent(String paramString)
    throws SocketException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   6: ifnonnull +24 -> 30
    //   9: aload_0
    //   10: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   13: astore_3
    //   14: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   17: dup
    //   18: aload_3
    //   19: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   22: astore 4
    //   24: aload_0
    //   25: aload 4
    //   27: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   30: new 242	org/apache/http/client/methods/HttpPost
    //   33: astore 5
    //   35: aload 5
    //   37: astore 6
    //   39: ldc 56
    //   41: astore 7
    //   43: aload 6
    //   45: aload 7
    //   47: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   50: new 245	java/util/ArrayList
    //   53: astore 8
    //   55: aload 8
    //   57: astore 9
    //   59: iconst_4
    //   60: istore 10
    //   62: aload 9
    //   64: iload 10
    //   66: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   69: aload_0
    //   70: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   73: astore 11
    //   75: new 253	org/apache/http/message/BasicNameValuePair
    //   78: dup
    //   79: ldc 255
    //   81: aload 11
    //   83: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: astore 12
    //   88: aload 8
    //   90: astore 13
    //   92: aload 12
    //   94: astore 14
    //   96: aload 13
    //   98: aload 14
    //   100: invokeinterface 264 2 0
    //   105: pop
    //   106: aload_0
    //   107: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   110: astore 15
    //   112: new 253	org/apache/http/message/BasicNameValuePair
    //   115: dup
    //   116: ldc_w 268
    //   119: aload 15
    //   121: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: astore 16
    //   126: aload 8
    //   128: astore 17
    //   130: aload 16
    //   132: astore 18
    //   134: aload 17
    //   136: aload 18
    //   138: invokeinterface 264 2 0
    //   143: pop
    //   144: new 253	org/apache/http/message/BasicNameValuePair
    //   147: dup
    //   148: ldc_w 270
    //   151: ldc 9
    //   153: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: astore 19
    //   158: aload 8
    //   160: astore 20
    //   162: aload 19
    //   164: astore 21
    //   166: aload 20
    //   168: aload 21
    //   170: invokeinterface 264 2 0
    //   175: pop
    //   176: aload_0
    //   177: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   180: astore 22
    //   182: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   185: istore 23
    //   187: aload_0
    //   188: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   191: astore 24
    //   193: aload_0
    //   194: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   197: astore 25
    //   199: aload_1
    //   200: astore 26
    //   202: iload 23
    //   204: istore 27
    //   206: aload 22
    //   208: astore 28
    //   210: aload 24
    //   212: astore 29
    //   214: aload 25
    //   216: astore 30
    //   218: aload 26
    //   220: iload 27
    //   222: aload 28
    //   224: aload 29
    //   226: aload 30
    //   228: invokestatic 786	com/yingyonghui/market/json/JsonParams:getNewsContentParams	(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   231: astore 31
    //   233: ldc_w 626
    //   236: astore 32
    //   238: aload 31
    //   240: astore 33
    //   242: aload 32
    //   244: aload 33
    //   246: invokestatic 632	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   249: pop
    //   250: new 253	org/apache/http/message/BasicNameValuePair
    //   253: astore 34
    //   255: aload 34
    //   257: astore 35
    //   259: ldc_w 280
    //   262: astore 36
    //   264: aload 31
    //   266: astore 37
    //   268: aload 35
    //   270: aload 36
    //   272: aload 37
    //   274: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: aload 8
    //   279: astore 38
    //   281: aload 34
    //   283: astore 39
    //   285: aload 38
    //   287: aload 39
    //   289: invokeinterface 264 2 0
    //   294: pop
    //   295: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   298: astore 40
    //   300: aload 40
    //   302: astore 41
    //   304: aload 8
    //   306: astore 42
    //   308: ldc_w 284
    //   311: astore 43
    //   313: aload 41
    //   315: aload 42
    //   317: aload 43
    //   319: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   322: aload 5
    //   324: astore 44
    //   326: aload 40
    //   328: astore 45
    //   330: aload 44
    //   332: aload 45
    //   334: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   337: aload_0
    //   338: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   341: astore 46
    //   343: aload 5
    //   345: astore 47
    //   347: aload 46
    //   349: aload 47
    //   351: invokeinterface 297 2 0
    //   356: invokeinterface 303 1 0
    //   361: invokeinterface 309 1 0
    //   366: astore 48
    //   368: new 311	java/io/BufferedReader
    //   371: astore 49
    //   373: new 313	java/io/InputStreamReader
    //   376: astore 50
    //   378: aload 50
    //   380: astore 51
    //   382: aload 48
    //   384: astore 52
    //   386: aload 51
    //   388: aload 52
    //   390: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   393: aload 49
    //   395: astore 53
    //   397: aload 50
    //   399: astore 54
    //   401: aload 53
    //   403: aload 54
    //   405: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   408: ldc_w 321
    //   411: astore 55
    //   413: aload 49
    //   415: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   418: astore 56
    //   420: aload 56
    //   422: ifnonnull +21 -> 443
    //   425: aload 55
    //   427: invokestatic 789	com/yingyonghui/market/json/JsonUtils:getNewsContentObject	(Ljava/lang/String;)Lcom/yingyonghui/market/model/NewsContent;
    //   430: astore_2
    //   431: aload 48
    //   433: ifnull +8 -> 441
    //   436: aload 48
    //   438: invokevirtual 345	java/io/InputStream:close	()V
    //   441: aload_2
    //   442: areturn
    //   443: aload 55
    //   445: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   448: astore 57
    //   450: new 149	java/lang/StringBuilder
    //   453: dup
    //   454: aload 57
    //   456: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   459: astore 58
    //   461: aload 56
    //   463: astore 59
    //   465: aload 58
    //   467: aload 59
    //   469: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   475: astore 55
    //   477: goto -64 -> 413
    //   480: astore 60
    //   482: aload 60
    //   484: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   487: aload 60
    //   489: instanceof 233
    //   492: ifne +11 -> 503
    //   495: aload 60
    //   497: instanceof 350
    //   500: ifeq +35 -> 535
    //   503: aload 60
    //   505: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   508: astore 61
    //   510: new 233	java/net/SocketException
    //   513: dup
    //   514: aload 61
    //   516: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   519: athrow
    //   520: astore 62
    //   522: aload 48
    //   524: ifnull +8 -> 532
    //   527: aload 48
    //   529: invokevirtual 345	java/io/InputStream:close	()V
    //   532: aload 62
    //   534: athrow
    //   535: aload 48
    //   537: ifnull -96 -> 441
    //   540: aload 48
    //   542: invokevirtual 345	java/io/InputStream:close	()V
    //   545: goto -104 -> 441
    //   548: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   551: goto -110 -> 441
    //   554: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   557: goto -25 -> 532
    //   560: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   563: goto -122 -> 441
    //
    // Exception table:
    //   from	to	target	type
    //   50	431	480	java/lang/Throwable
    //   443	477	480	java/lang/Throwable
    //   50	431	520	finally
    //   443	477	520	finally
    //   482	520	520	finally
    //   540	545	548	java/io/IOException
    //   527	532	554	java/io/IOException
    //   436	441	560	java/io/IOException
  }

  // ERROR //
  public com.yingyonghui.market.model.Image2 getNewsImage(String paramString, int paramInt)
    throws SocketException
  {
    // Byte code:
    //   0: new 531	com/yingyonghui/market/model/Image2
    //   3: dup
    //   4: invokespecial 532	com/yingyonghui/market/model/Image2:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: iload_2
    //   10: putfield 535	com/yingyonghui/market/model/Image2:_id	I
    //   13: aload_0
    //   14: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   17: ifnonnull +26 -> 43
    //   20: aload_0
    //   21: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   24: astore 4
    //   26: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   29: dup
    //   30: aload 4
    //   32: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   35: astore 5
    //   37: aload_0
    //   38: aload 5
    //   40: putfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   43: new 411	org/apache/http/client/methods/HttpGet
    //   46: dup
    //   47: aload_1
    //   48: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   51: astore 6
    //   53: aload_0
    //   54: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   57: aload 6
    //   59: invokeinterface 297 2 0
    //   64: invokeinterface 303 1 0
    //   69: invokeinterface 309 1 0
    //   74: astore 7
    //   76: new 634	android/graphics/drawable/BitmapDrawable
    //   79: dup
    //   80: aload 7
    //   82: invokespecial 635	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   85: invokevirtual 639	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   88: astore 8
    //   90: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   93: iconst_3
    //   94: if_icmpne +32 -> 126
    //   97: new 634	android/graphics/drawable/BitmapDrawable
    //   100: dup
    //   101: aload 8
    //   103: invokespecial 642	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   106: astore 9
    //   108: aload_3
    //   109: aload 9
    //   111: putfield 539	com/yingyonghui/market/model/Image2:icon	Landroid/graphics/drawable/Drawable;
    //   114: aload 7
    //   116: ifnull +8 -> 124
    //   119: aload 7
    //   121: invokevirtual 345	java/io/InputStream:close	()V
    //   124: aload_3
    //   125: areturn
    //   126: ldc_w 645
    //   129: invokestatic 651	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   132: astore 10
    //   134: iconst_2
    //   135: anewarray 647	java/lang/Class
    //   138: astore 11
    //   140: aload 11
    //   142: iconst_0
    //   143: ldc 76
    //   145: aastore
    //   146: aload 11
    //   148: iconst_1
    //   149: ldc_w 653
    //   152: aastore
    //   153: aload 10
    //   155: aload 11
    //   157: invokevirtual 657	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   160: astore 12
    //   162: iconst_2
    //   163: anewarray 4	java/lang/Object
    //   166: astore 13
    //   168: aload_0
    //   169: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   172: invokevirtual 74	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   175: astore 14
    //   177: aload 13
    //   179: iconst_0
    //   180: aload 14
    //   182: aastore
    //   183: aload 13
    //   185: iconst_1
    //   186: aload 8
    //   188: aastore
    //   189: aload 12
    //   191: aload 13
    //   193: invokevirtual 663	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   196: checkcast 545	android/graphics/drawable/Drawable
    //   199: astore 15
    //   201: aload_3
    //   202: aload 15
    //   204: putfield 539	com/yingyonghui/market/model/Image2:icon	Landroid/graphics/drawable/Drawable;
    //   207: goto -93 -> 114
    //   210: astore 16
    //   212: goto -98 -> 114
    //   215: astore 17
    //   217: aload 17
    //   219: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   222: aload 17
    //   224: instanceof 233
    //   227: ifne +11 -> 238
    //   230: aload 17
    //   232: instanceof 350
    //   235: ifeq +35 -> 270
    //   238: aload 17
    //   240: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   243: astore 18
    //   245: new 233	java/net/SocketException
    //   248: dup
    //   249: aload 18
    //   251: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   254: athrow
    //   255: astore 19
    //   257: aload 7
    //   259: ifnull +8 -> 267
    //   262: aload 7
    //   264: invokevirtual 345	java/io/InputStream:close	()V
    //   267: aload 19
    //   269: athrow
    //   270: aload 7
    //   272: ifnull -148 -> 124
    //   275: aload 7
    //   277: invokevirtual 345	java/io/InputStream:close	()V
    //   280: goto -156 -> 124
    //   283: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   286: goto -162 -> 124
    //   289: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   292: goto -25 -> 267
    //   295: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   298: goto -174 -> 124
    //
    // Exception table:
    //   from	to	target	type
    //   126	207	210	java/lang/Exception
    //   43	114	215	java/lang/Throwable
    //   126	207	215	java/lang/Throwable
    //   43	114	255	finally
    //   126	207	255	finally
    //   217	255	255	finally
    //   275	280	283	java/io/IOException
    //   262	267	289	java/io/IOException
    //   119	124	295	java/io/IOException
  }

  // ERROR //
  @Signature({"(II)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/News;", ">;"})
  public ArrayList getNewsList(int paramInt1, int paramInt2)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   12: ifnonnull +26 -> 38
    //   15: aload_0
    //   16: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   19: astore 4
    //   21: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   24: dup
    //   25: aload 4
    //   27: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   30: astore 5
    //   32: aload_0
    //   33: aload 5
    //   35: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   38: new 242	org/apache/http/client/methods/HttpPost
    //   41: dup
    //   42: ldc 56
    //   44: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   47: astore 6
    //   49: new 245	java/util/ArrayList
    //   52: dup
    //   53: iconst_4
    //   54: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   57: astore 7
    //   59: aload_0
    //   60: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   63: astore 8
    //   65: new 253	org/apache/http/message/BasicNameValuePair
    //   68: dup
    //   69: ldc 255
    //   71: aload 8
    //   73: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: astore 9
    //   78: aload 7
    //   80: aload 9
    //   82: invokeinterface 264 2 0
    //   87: pop
    //   88: aload_0
    //   89: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   92: astore 10
    //   94: new 253	org/apache/http/message/BasicNameValuePair
    //   97: dup
    //   98: ldc_w 268
    //   101: aload 10
    //   103: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: astore 11
    //   108: aload 7
    //   110: aload 11
    //   112: invokeinterface 264 2 0
    //   117: pop
    //   118: new 253	org/apache/http/message/BasicNameValuePair
    //   121: dup
    //   122: ldc_w 270
    //   125: ldc 9
    //   127: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: astore 12
    //   132: aload 7
    //   134: aload 12
    //   136: invokeinterface 264 2 0
    //   141: pop
    //   142: aload_0
    //   143: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   146: astore 13
    //   148: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   151: istore 14
    //   153: aload_0
    //   154: invokespecial 569	com/yingyonghui/market/online/MarketServiceAgent:getHardwareCode	()Ljava/lang/String;
    //   157: astore 15
    //   159: aload_0
    //   160: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   163: astore 16
    //   165: iload_1
    //   166: istore 17
    //   168: iload_2
    //   169: istore 18
    //   171: iload 14
    //   173: aload 13
    //   175: aload 15
    //   177: aload 16
    //   179: iload 17
    //   181: iload 18
    //   183: invokestatic 799	com/yingyonghui/market/json/JsonParams:getNewsListParams	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;II)Ljava/lang/String;
    //   186: astore 19
    //   188: ldc_w 626
    //   191: aload 19
    //   193: invokestatic 632	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   196: pop
    //   197: new 253	org/apache/http/message/BasicNameValuePair
    //   200: dup
    //   201: ldc_w 280
    //   204: aload 19
    //   206: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: astore 20
    //   211: aload 7
    //   213: aload 20
    //   215: invokeinterface 264 2 0
    //   220: pop
    //   221: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   224: dup
    //   225: aload 7
    //   227: ldc_w 284
    //   230: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   233: astore 21
    //   235: aload 6
    //   237: aload 21
    //   239: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   242: aload_0
    //   243: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   246: aload 6
    //   248: invokeinterface 297 2 0
    //   253: invokeinterface 303 1 0
    //   258: invokeinterface 309 1 0
    //   263: astore 22
    //   265: new 311	java/io/BufferedReader
    //   268: astore 23
    //   270: new 313	java/io/InputStreamReader
    //   273: dup
    //   274: aload 22
    //   276: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   279: astore 24
    //   281: aload 23
    //   283: astore 25
    //   285: aload 24
    //   287: astore 26
    //   289: aload 25
    //   291: aload 26
    //   293: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   296: ldc_w 321
    //   299: astore 27
    //   301: aload 23
    //   303: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   306: astore 28
    //   308: aload 28
    //   310: ifnonnull +21 -> 331
    //   313: aload 27
    //   315: invokestatic 803	com/yingyonghui/market/json/JsonUtils:getNewsListObject	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   318: astore_3
    //   319: aload 22
    //   321: ifnull +8 -> 329
    //   324: aload 22
    //   326: invokevirtual 345	java/io/InputStream:close	()V
    //   329: aload_3
    //   330: areturn
    //   331: aload 27
    //   333: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   336: astore 29
    //   338: new 149	java/lang/StringBuilder
    //   341: dup
    //   342: aload 29
    //   344: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   347: aload 28
    //   349: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: astore 27
    //   357: goto -56 -> 301
    //   360: astore 30
    //   362: aload 30
    //   364: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   367: aload 30
    //   369: instanceof 233
    //   372: ifne +11 -> 383
    //   375: aload 30
    //   377: instanceof 350
    //   380: ifeq +35 -> 415
    //   383: aload 30
    //   385: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   388: astore 31
    //   390: new 233	java/net/SocketException
    //   393: dup
    //   394: aload 31
    //   396: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   399: athrow
    //   400: astore 32
    //   402: aload 22
    //   404: ifnull +8 -> 412
    //   407: aload 22
    //   409: invokevirtual 345	java/io/InputStream:close	()V
    //   412: aload 32
    //   414: athrow
    //   415: aload 22
    //   417: ifnull -88 -> 329
    //   420: aload 22
    //   422: invokevirtual 345	java/io/InputStream:close	()V
    //   425: goto -96 -> 329
    //   428: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   431: goto -102 -> 329
    //   434: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   437: goto -25 -> 412
    //   440: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   443: goto -114 -> 329
    //
    // Exception table:
    //   from	to	target	type
    //   49	319	360	java/lang/Throwable
    //   331	357	360	java/lang/Throwable
    //   49	319	400	finally
    //   331	357	400	finally
    //   362	400	400	finally
    //   420	425	428	java/io/IOException
    //   407	412	434	java/io/IOException
    //   324	329	440	java/io/IOException
  }

  @Signature({"(III)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getRecommendApp(int paramInt1, int paramInt2, int paramInt3)
    throws SocketException
  {
    try
    {
      localArrayList = getAppList(paramInt1, paramInt2, paramInt3, 2);
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        if (((localThrowable instanceof SocketException)) || ((localThrowable instanceof SocketTimeoutException)))
        {
          String str = localThrowable.getMessage();
          throw new SocketException(str);
        }
        ArrayList localArrayList = null;
      }
    }
  }

  // ERROR //
  @Signature({"(", "Ljava/lang/String;", "III", "Ljava/lang/String;", ")", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getSearchAppList(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   12: istore 7
    //   14: aload_0
    //   15: invokespecial 567	com/yingyonghui/market/online/MarketServiceAgent:getResolution	()Ljava/lang/String;
    //   18: astore 8
    //   20: aload_0
    //   21: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   24: astore 9
    //   26: iload 4
    //   28: istore 10
    //   30: iconst_1
    //   31: istore 11
    //   33: iload 10
    //   35: iload 11
    //   37: if_icmpne +467 -> 504
    //   40: aload_1
    //   41: astore 12
    //   43: iload_2
    //   44: istore 13
    //   46: iload_3
    //   47: istore 14
    //   49: aload 5
    //   51: astore 15
    //   53: aload 12
    //   55: iload 13
    //   57: iload 14
    //   59: aload 15
    //   61: iload 7
    //   63: aload 8
    //   65: aload 9
    //   67: invokestatic 816	com/yingyonghui/market/json/JsonParams:getSearchByDeveloperParams	(Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   70: astore 16
    //   72: aload_0
    //   73: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   76: ifnonnull +26 -> 102
    //   79: aload_0
    //   80: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   83: astore 17
    //   85: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   88: dup
    //   89: aload 17
    //   91: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   94: astore 18
    //   96: aload_0
    //   97: aload 18
    //   99: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   102: new 242	org/apache/http/client/methods/HttpPost
    //   105: dup
    //   106: ldc 56
    //   108: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   111: astore 19
    //   113: aload 19
    //   115: ldc_w 363
    //   118: ldc_w 365
    //   121: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: ldc_w 321
    //   127: astore 20
    //   129: new 245	java/util/ArrayList
    //   132: astore 21
    //   134: aload 21
    //   136: astore 22
    //   138: iconst_4
    //   139: istore 23
    //   141: aload 22
    //   143: iload 23
    //   145: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   148: aload_0
    //   149: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   152: astore 24
    //   154: new 253	org/apache/http/message/BasicNameValuePair
    //   157: dup
    //   158: ldc 255
    //   160: aload 24
    //   162: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   165: astore 25
    //   167: aload 21
    //   169: astore 26
    //   171: aload 25
    //   173: astore 27
    //   175: aload 26
    //   177: aload 27
    //   179: invokeinterface 264 2 0
    //   184: pop
    //   185: aload_0
    //   186: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   189: astore 28
    //   191: new 253	org/apache/http/message/BasicNameValuePair
    //   194: dup
    //   195: ldc_w 268
    //   198: aload 28
    //   200: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: astore 29
    //   205: aload 21
    //   207: astore 30
    //   209: aload 29
    //   211: astore 31
    //   213: aload 30
    //   215: aload 31
    //   217: invokeinterface 264 2 0
    //   222: pop
    //   223: new 253	org/apache/http/message/BasicNameValuePair
    //   226: dup
    //   227: ldc_w 270
    //   230: ldc 9
    //   232: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   235: astore 32
    //   237: aload 21
    //   239: astore 33
    //   241: aload 32
    //   243: astore 34
    //   245: aload 33
    //   247: aload 34
    //   249: invokeinterface 264 2 0
    //   254: pop
    //   255: new 253	org/apache/http/message/BasicNameValuePair
    //   258: astore 35
    //   260: aload 35
    //   262: astore 36
    //   264: ldc_w 280
    //   267: astore 37
    //   269: aload 16
    //   271: astore 38
    //   273: aload 36
    //   275: aload 37
    //   277: aload 38
    //   279: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   282: aload 21
    //   284: astore 39
    //   286: aload 35
    //   288: astore 40
    //   290: aload 39
    //   292: aload 40
    //   294: invokeinterface 264 2 0
    //   299: pop
    //   300: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   303: astore 41
    //   305: aload 41
    //   307: astore 42
    //   309: aload 21
    //   311: astore 43
    //   313: ldc_w 284
    //   316: astore 44
    //   318: aload 42
    //   320: aload 43
    //   322: aload 44
    //   324: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   327: aload 19
    //   329: aload 41
    //   331: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   334: aload_0
    //   335: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   338: aload 19
    //   340: invokeinterface 297 2 0
    //   345: astore 45
    //   347: aload 45
    //   349: invokeinterface 303 1 0
    //   354: invokeinterface 309 1 0
    //   359: astore 46
    //   361: aload 45
    //   363: astore 47
    //   365: ldc_w 374
    //   368: astore 48
    //   370: aload 47
    //   372: aload 48
    //   374: invokeinterface 378 2 0
    //   379: astore 49
    //   381: aload 49
    //   383: ifnull +12 -> 395
    //   386: aload 49
    //   388: invokeinterface 383 1 0
    //   393: astore 20
    //   395: ldc_w 321
    //   398: astore 50
    //   400: aload 20
    //   402: ldc_w 365
    //   405: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   408: ifeq +217 -> 625
    //   411: new 311	java/io/BufferedReader
    //   414: astore 51
    //   416: new 388	java/util/zip/GZIPInputStream
    //   419: dup
    //   420: aload 46
    //   422: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   425: astore 52
    //   427: new 313	java/io/InputStreamReader
    //   430: dup
    //   431: aload 52
    //   433: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   436: astore 53
    //   438: aload 51
    //   440: astore 54
    //   442: aload 53
    //   444: astore 55
    //   446: aload 54
    //   448: aload 55
    //   450: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   453: aload 51
    //   455: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   458: astore 56
    //   460: aload 56
    //   462: ifnonnull +197 -> 659
    //   465: aload_0
    //   466: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   469: astore 57
    //   471: aload 50
    //   473: astore 58
    //   475: ldc_w 416
    //   478: istore 59
    //   480: aload 57
    //   482: aload 58
    //   484: iload 59
    //   486: invokestatic 698	com/yingyonghui/market/json/JsonUtils:getAssetObject	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/util/ArrayList;
    //   489: astore 6
    //   491: aload 46
    //   493: ifnull +8 -> 501
    //   496: aload 46
    //   498: invokevirtual 345	java/io/InputStream:close	()V
    //   501: aload 6
    //   503: areturn
    //   504: iload 4
    //   506: istore 60
    //   508: iconst_2
    //   509: istore 61
    //   511: iload 60
    //   513: iload 61
    //   515: if_icmpne +35 -> 550
    //   518: aload_1
    //   519: astore 62
    //   521: iload_2
    //   522: istore 63
    //   524: iload_3
    //   525: istore 64
    //   527: aload 62
    //   529: iload 63
    //   531: iload 64
    //   533: ldc_w 484
    //   536: iload 7
    //   538: aload 8
    //   540: aload 9
    //   542: invokestatic 820	com/yingyonghui/market/json/JsonParams:getSearchByPkgnameParams	(Ljava/lang/String;IIZILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   545: astore 16
    //   547: goto -475 -> 72
    //   550: iload 4
    //   552: istore 65
    //   554: iconst_4
    //   555: istore 66
    //   557: iload 65
    //   559: iload 66
    //   561: if_icmpne +35 -> 596
    //   564: aload_1
    //   565: astore 67
    //   567: iload_2
    //   568: istore 68
    //   570: iload_3
    //   571: istore 69
    //   573: aload 67
    //   575: iload 68
    //   577: iload 69
    //   579: ldc_w 416
    //   582: iload 7
    //   584: aload 8
    //   586: aload 9
    //   588: invokestatic 820	com/yingyonghui/market/json/JsonParams:getSearchByPkgnameParams	(Ljava/lang/String;IIZILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   591: astore 16
    //   593: goto -521 -> 72
    //   596: aload_1
    //   597: astore 70
    //   599: iload_2
    //   600: istore 71
    //   602: iload_3
    //   603: istore 72
    //   605: aload 70
    //   607: iload 71
    //   609: iload 72
    //   611: iload 7
    //   613: aload 8
    //   615: aload 9
    //   617: invokestatic 824	com/yingyonghui/market/json/JsonParams:getSearchByKeywordsParams	(Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   620: astore 16
    //   622: goto -550 -> 72
    //   625: new 311	java/io/BufferedReader
    //   628: astore 51
    //   630: new 313	java/io/InputStreamReader
    //   633: dup
    //   634: aload 46
    //   636: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   639: astore 73
    //   641: aload 51
    //   643: astore 74
    //   645: aload 73
    //   647: astore 75
    //   649: aload 74
    //   651: aload 75
    //   653: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   656: goto -203 -> 453
    //   659: aload 50
    //   661: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   664: astore 76
    //   666: new 149	java/lang/StringBuilder
    //   669: dup
    //   670: aload 76
    //   672: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   675: astore 77
    //   677: aload 56
    //   679: astore 78
    //   681: aload 77
    //   683: aload 78
    //   685: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   691: astore 50
    //   693: goto -240 -> 453
    //   696: astore 79
    //   698: aload 79
    //   700: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   703: aload 79
    //   705: instanceof 233
    //   708: ifne +11 -> 719
    //   711: aload 79
    //   713: instanceof 350
    //   716: ifeq +35 -> 751
    //   719: aload 79
    //   721: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   724: astore 80
    //   726: new 233	java/net/SocketException
    //   729: dup
    //   730: aload 80
    //   732: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   735: athrow
    //   736: astore 81
    //   738: aload 46
    //   740: ifnull +8 -> 748
    //   743: aload 46
    //   745: invokevirtual 345	java/io/InputStream:close	()V
    //   748: aload 81
    //   750: athrow
    //   751: aload 46
    //   753: ifnull -252 -> 501
    //   756: aload 46
    //   758: invokevirtual 345	java/io/InputStream:close	()V
    //   761: goto -260 -> 501
    //   764: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   767: goto -266 -> 501
    //   770: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   773: goto -25 -> 748
    //   776: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   779: goto -278 -> 501
    //
    // Exception table:
    //   from	to	target	type
    //   129	491	696	java/lang/Throwable
    //   625	693	696	java/lang/Throwable
    //   129	491	736	finally
    //   625	693	736	finally
    //   698	736	736	finally
    //   756	761	764	java/io/IOException
    //   743	748	770	java/io/IOException
    //   496	501	776	java/io/IOException
  }

  // ERROR //
  public com.yingyonghui.market.model.TestUser getTestUser()
    throws SocketException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   6: ifnonnull +22 -> 28
    //   9: aload_0
    //   10: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   13: astore_2
    //   14: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   17: dup
    //   18: aload_2
    //   19: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   22: astore_3
    //   23: aload_0
    //   24: aload_3
    //   25: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   28: new 242	org/apache/http/client/methods/HttpPost
    //   31: dup
    //   32: ldc 56
    //   34: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   37: astore 4
    //   39: aload 4
    //   41: ldc_w 363
    //   44: ldc_w 365
    //   47: invokevirtual 368	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   50: ldc_w 321
    //   53: astore 5
    //   55: new 245	java/util/ArrayList
    //   58: dup
    //   59: iconst_4
    //   60: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   63: astore 6
    //   65: aload_0
    //   66: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   69: astore 7
    //   71: new 253	org/apache/http/message/BasicNameValuePair
    //   74: dup
    //   75: ldc 255
    //   77: aload 7
    //   79: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   82: astore 8
    //   84: aload 6
    //   86: aload 8
    //   88: invokeinterface 264 2 0
    //   93: pop
    //   94: aload_0
    //   95: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   98: astore 9
    //   100: new 253	org/apache/http/message/BasicNameValuePair
    //   103: dup
    //   104: ldc_w 268
    //   107: aload 9
    //   109: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   112: astore 10
    //   114: aload 6
    //   116: aload 10
    //   118: invokeinterface 264 2 0
    //   123: pop
    //   124: new 253	org/apache/http/message/BasicNameValuePair
    //   127: dup
    //   128: ldc_w 270
    //   131: ldc 9
    //   133: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: astore 11
    //   138: aload 6
    //   140: aload 11
    //   142: invokeinterface 264 2 0
    //   147: pop
    //   148: invokestatic 829	com/yingyonghui/market/json/JsonParams:getTestUserParams	()Ljava/lang/String;
    //   151: astore 12
    //   153: new 253	org/apache/http/message/BasicNameValuePair
    //   156: dup
    //   157: ldc_w 280
    //   160: aload 12
    //   162: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   165: astore 13
    //   167: aload 6
    //   169: aload 13
    //   171: invokeinterface 264 2 0
    //   176: pop
    //   177: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   180: dup
    //   181: aload 6
    //   183: ldc_w 284
    //   186: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   189: astore 14
    //   191: aload 4
    //   193: aload 14
    //   195: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   198: aload_0
    //   199: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   202: aload 4
    //   204: invokeinterface 297 2 0
    //   209: astore 15
    //   211: aload 15
    //   213: invokeinterface 303 1 0
    //   218: invokeinterface 309 1 0
    //   223: astore 16
    //   225: aload 15
    //   227: ldc_w 374
    //   230: invokeinterface 378 2 0
    //   235: astore 17
    //   237: aload 17
    //   239: ifnull +12 -> 251
    //   242: aload 17
    //   244: invokeinterface 383 1 0
    //   249: astore 5
    //   251: new 502	java/lang/StringBuffer
    //   254: dup
    //   255: invokespecial 503	java/lang/StringBuffer:<init>	()V
    //   258: astore 18
    //   260: aload 5
    //   262: ldc_w 365
    //   265: invokevirtual 386	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   268: ifeq +98 -> 366
    //   271: new 388	java/util/zip/GZIPInputStream
    //   274: dup
    //   275: aload 16
    //   277: invokespecial 389	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   280: astore 19
    //   282: new 313	java/io/InputStreamReader
    //   285: dup
    //   286: aload 19
    //   288: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   291: astore 20
    //   293: new 311	java/io/BufferedReader
    //   296: dup
    //   297: aload 20
    //   299: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   302: astore 21
    //   304: sipush 1024
    //   307: newarray char
    //   309: astore 22
    //   311: aload 22
    //   313: arraylength
    //   314: istore 23
    //   316: aload 21
    //   318: aload 22
    //   320: iconst_0
    //   321: iload 23
    //   323: invokevirtual 507	java/io/BufferedReader:read	([CII)I
    //   326: istore 24
    //   328: iload 24
    //   330: ifgt +61 -> 391
    //   333: aload_0
    //   334: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   337: astore 25
    //   339: aload 18
    //   341: invokevirtual 508	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   344: astore 26
    //   346: aload 25
    //   348: aload 26
    //   350: invokestatic 833	com/yingyonghui/market/json/JsonUtils:getTestUserObject	(Landroid/content/Context;Ljava/lang/String;)Lcom/yingyonghui/market/model/TestUser;
    //   353: astore_1
    //   354: aload 16
    //   356: ifnull +8 -> 364
    //   359: aload 16
    //   361: invokevirtual 345	java/io/InputStream:close	()V
    //   364: aload_1
    //   365: areturn
    //   366: new 313	java/io/InputStreamReader
    //   369: dup
    //   370: aload 16
    //   372: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   375: astore 27
    //   377: new 311	java/io/BufferedReader
    //   380: dup
    //   381: aload 27
    //   383: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   386: astore 21
    //   388: goto -84 -> 304
    //   391: aload 18
    //   393: aload 22
    //   395: iconst_0
    //   396: iload 24
    //   398: invokevirtual 515	java/lang/StringBuffer:append	([CII)Ljava/lang/StringBuffer;
    //   401: pop
    //   402: goto -91 -> 311
    //   405: astore 28
    //   407: aload 28
    //   409: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   412: aload 28
    //   414: instanceof 233
    //   417: ifne +11 -> 428
    //   420: aload 28
    //   422: instanceof 350
    //   425: ifeq +35 -> 460
    //   428: aload 28
    //   430: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   433: astore 29
    //   435: new 233	java/net/SocketException
    //   438: dup
    //   439: aload 29
    //   441: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   444: athrow
    //   445: astore 30
    //   447: aload 16
    //   449: ifnull +8 -> 457
    //   452: aload 16
    //   454: invokevirtual 345	java/io/InputStream:close	()V
    //   457: aload 30
    //   459: athrow
    //   460: aload 16
    //   462: ifnull -98 -> 364
    //   465: aload 16
    //   467: invokevirtual 345	java/io/InputStream:close	()V
    //   470: goto -106 -> 364
    //   473: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   476: goto -112 -> 364
    //   479: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   482: goto -25 -> 457
    //   485: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   488: goto -124 -> 364
    //
    // Exception table:
    //   from	to	target	type
    //   55	354	405	java/lang/Throwable
    //   366	402	405	java/lang/Throwable
    //   55	354	445	finally
    //   366	402	445	finally
    //   407	445	445	finally
    //   465	470	473	java/io/IOException
    //   452	457	479	java/io/IOException
    //   359	364	485	java/io/IOException
  }

  // ERROR //
  public com.yingyonghui.market.model.ImageURL getThumb(String paramString)
    throws SocketException
  {
    // Byte code:
    //   0: new 837	com/yingyonghui/market/model/ImageURL
    //   3: dup
    //   4: invokespecial 838	com/yingyonghui/market/model/ImageURL:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_1
    //   10: putfield 841	com/yingyonghui/market/model/ImageURL:url	Ljava/lang/String;
    //   13: aload_0
    //   14: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   17: ifnonnull +24 -> 41
    //   20: aload_0
    //   21: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   24: astore_3
    //   25: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   28: dup
    //   29: aload_3
    //   30: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   33: astore 4
    //   35: aload_0
    //   36: aload 4
    //   38: putfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   41: new 411	org/apache/http/client/methods/HttpGet
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   49: astore 5
    //   51: aload_0
    //   52: getfield 543	com/yingyonghui/market/online/MarketServiceAgent:httpClientForImage	Lorg/apache/http/client/HttpClient;
    //   55: aload 5
    //   57: invokeinterface 297 2 0
    //   62: invokeinterface 303 1 0
    //   67: invokeinterface 309 1 0
    //   72: astore 6
    //   74: new 634	android/graphics/drawable/BitmapDrawable
    //   77: dup
    //   78: aload 6
    //   80: invokespecial 635	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   83: invokevirtual 639	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   86: astore 7
    //   88: invokestatic 565	com/yingyonghui/market/util/DeviceUtil:getSDKVersionInt	()I
    //   91: iconst_3
    //   92: if_icmpne +32 -> 124
    //   95: new 634	android/graphics/drawable/BitmapDrawable
    //   98: dup
    //   99: aload 7
    //   101: invokespecial 642	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   104: astore 8
    //   106: aload_2
    //   107: aload 8
    //   109: putfield 842	com/yingyonghui/market/model/ImageURL:icon	Landroid/graphics/drawable/Drawable;
    //   112: aload 6
    //   114: ifnull +8 -> 122
    //   117: aload 6
    //   119: invokevirtual 345	java/io/InputStream:close	()V
    //   122: aload_2
    //   123: areturn
    //   124: ldc_w 645
    //   127: invokestatic 651	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   130: astore 9
    //   132: iconst_2
    //   133: anewarray 647	java/lang/Class
    //   136: astore 10
    //   138: aload 10
    //   140: iconst_0
    //   141: ldc 76
    //   143: aastore
    //   144: aload 10
    //   146: iconst_1
    //   147: ldc_w 653
    //   150: aastore
    //   151: aload 9
    //   153: aload 10
    //   155: invokevirtual 657	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   158: astore 11
    //   160: iconst_2
    //   161: anewarray 4	java/lang/Object
    //   164: astore 12
    //   166: aload_0
    //   167: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   170: invokevirtual 74	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   173: astore 13
    //   175: aload 12
    //   177: iconst_0
    //   178: aload 13
    //   180: aastore
    //   181: aload 12
    //   183: iconst_1
    //   184: aload 7
    //   186: aastore
    //   187: aload 11
    //   189: aload 12
    //   191: invokevirtual 663	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   194: checkcast 545	android/graphics/drawable/Drawable
    //   197: astore 14
    //   199: aload_2
    //   200: aload 14
    //   202: putfield 842	com/yingyonghui/market/model/ImageURL:icon	Landroid/graphics/drawable/Drawable;
    //   205: goto -93 -> 112
    //   208: astore 15
    //   210: goto -98 -> 112
    //   213: astore 16
    //   215: aload 16
    //   217: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   220: aload 16
    //   222: instanceof 233
    //   225: ifne +11 -> 236
    //   228: aload 16
    //   230: instanceof 350
    //   233: ifeq +35 -> 268
    //   236: aload 16
    //   238: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   241: astore 17
    //   243: new 233	java/net/SocketException
    //   246: dup
    //   247: aload 17
    //   249: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   252: athrow
    //   253: astore 18
    //   255: aload 6
    //   257: ifnull +8 -> 265
    //   260: aload 6
    //   262: invokevirtual 345	java/io/InputStream:close	()V
    //   265: aload 18
    //   267: athrow
    //   268: aload 6
    //   270: ifnull -148 -> 122
    //   273: aload 6
    //   275: invokevirtual 345	java/io/InputStream:close	()V
    //   278: goto -156 -> 122
    //   281: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   284: goto -162 -> 122
    //   287: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   290: goto -25 -> 265
    //   293: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   296: goto -174 -> 122
    //
    // Exception table:
    //   from	to	target	type
    //   124	205	208	java/lang/Exception
    //   51	112	213	java/lang/Throwable
    //   124	205	213	java/lang/Throwable
    //   51	112	253	finally
    //   124	205	253	finally
    //   215	253	253	finally
    //   273	278	281	java/io/IOException
    //   260	265	287	java/io/IOException
    //   117	122	293	java/io/IOException
  }

  // ERROR //
  public boolean getTopFourApp()
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   6: ldc 13
    //   8: invokestatic 847	com/yingyonghui/market/FileManager:readTimeStampFromFileSystem	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   16: ldc_w 849
    //   19: invokestatic 853	com/yingyonghui/market/FileManager:getFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   22: astore_3
    //   23: aload_0
    //   24: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   27: ifnonnull +26 -> 53
    //   30: aload_0
    //   31: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   34: astore 4
    //   36: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   39: dup
    //   40: aload 4
    //   42: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   45: astore 5
    //   47: aload_0
    //   48: aload 5
    //   50: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   53: new 411	org/apache/http/client/methods/HttpGet
    //   56: dup
    //   57: ldc 18
    //   59: invokespecial 412	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   62: astore 6
    //   64: aload_3
    //   65: ifnull +23 -> 88
    //   68: aload_3
    //   69: invokevirtual 858	java/io/File:length	()J
    //   72: ldc2_w 469
    //   75: lcmp
    //   76: ifle +12 -> 88
    //   79: aload 6
    //   81: ldc_w 860
    //   84: aload_2
    //   85: invokevirtual 861	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   88: aconst_null
    //   89: astore 7
    //   91: aload_0
    //   92: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   95: aload 6
    //   97: invokeinterface 297 2 0
    //   102: astore 8
    //   104: aload 8
    //   106: invokeinterface 445 1 0
    //   111: invokeinterface 450 1 0
    //   116: istore 9
    //   118: iload 9
    //   120: sipush 200
    //   123: if_icmpne +70 -> 193
    //   126: aload 8
    //   128: ldc_w 468
    //   131: invokeinterface 378 2 0
    //   136: invokeinterface 383 1 0
    //   141: astore 10
    //   143: aload_0
    //   144: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   147: ldc 13
    //   149: aload 10
    //   151: invokestatic 864	com/yingyonghui/market/FileManager:writeTimeStampToFileSystem	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload 8
    //   156: invokeinterface 303 1 0
    //   161: invokeinterface 309 1 0
    //   166: astore 7
    //   168: aload_0
    //   169: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   172: ldc_w 849
    //   175: aload 7
    //   177: invokestatic 868	com/yingyonghui/market/FileManager:writeStreamToFileSystem	(Landroid/content/Context;Ljava/lang/String;Ljava/io/InputStream;)Z
    //   180: istore_1
    //   181: aload 7
    //   183: ifnull +8 -> 191
    //   186: aload 7
    //   188: invokevirtual 345	java/io/InputStream:close	()V
    //   191: iload_1
    //   192: ireturn
    //   193: iload 9
    //   195: sipush 304
    //   198: if_icmpne -17 -> 181
    //   201: aload 8
    //   203: ldc_w 468
    //   206: invokeinterface 378 2 0
    //   211: invokeinterface 383 1 0
    //   216: astore 11
    //   218: aload_0
    //   219: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   222: ldc 13
    //   224: aload 11
    //   226: invokestatic 864	com/yingyonghui/market/FileManager:writeTimeStampToFileSystem	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   229: iconst_1
    //   230: istore_1
    //   231: goto -50 -> 181
    //   234: astore 12
    //   236: aload 12
    //   238: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   241: aload 12
    //   243: instanceof 233
    //   246: ifne +11 -> 257
    //   249: aload 12
    //   251: instanceof 350
    //   254: ifeq +35 -> 289
    //   257: aload 12
    //   259: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   262: astore 13
    //   264: new 233	java/net/SocketException
    //   267: dup
    //   268: aload 13
    //   270: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   273: athrow
    //   274: astore 14
    //   276: aload 7
    //   278: ifnull +8 -> 286
    //   281: aload 7
    //   283: invokevirtual 345	java/io/InputStream:close	()V
    //   286: aload 14
    //   288: athrow
    //   289: aload 7
    //   291: ifnull -100 -> 191
    //   294: aload 7
    //   296: invokevirtual 345	java/io/InputStream:close	()V
    //   299: goto -108 -> 191
    //   302: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   305: goto -114 -> 191
    //   308: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   311: goto -25 -> 286
    //   314: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   317: goto -126 -> 191
    //
    // Exception table:
    //   from	to	target	type
    //   91	181	234	java/lang/Throwable
    //   201	229	234	java/lang/Throwable
    //   91	181	274	finally
    //   201	229	274	finally
    //   236	274	274	finally
    //   294	299	302	java/io/IOException
    //   281	286	308	java/io/IOException
    //   186	191	314	java/io/IOException
  }

  // ERROR //
  @Signature({"(III)", "Ljava/util/ArrayList", "<", "Ljava/lang/String;", ">;"})
  public ArrayList getTopKeywords(int paramInt1, int paramInt2, int paramInt3)
    throws SocketException
  {
    // Byte code:
    //   0: new 245	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 560	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   13: ifnonnull +26 -> 39
    //   16: aload_0
    //   17: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   20: astore 5
    //   22: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   25: dup
    //   26: aload 5
    //   28: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   31: astore 6
    //   33: aload_0
    //   34: aload 6
    //   36: putfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   39: new 242	org/apache/http/client/methods/HttpPost
    //   42: dup
    //   43: ldc 56
    //   45: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   48: astore 7
    //   50: new 245	java/util/ArrayList
    //   53: dup
    //   54: iconst_4
    //   55: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   58: astore 8
    //   60: aload_0
    //   61: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   64: astore 9
    //   66: new 253	org/apache/http/message/BasicNameValuePair
    //   69: dup
    //   70: ldc 255
    //   72: aload 9
    //   74: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   77: astore 10
    //   79: aload 8
    //   81: aload 10
    //   83: invokeinterface 264 2 0
    //   88: pop
    //   89: aload_0
    //   90: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   93: astore 11
    //   95: new 253	org/apache/http/message/BasicNameValuePair
    //   98: dup
    //   99: ldc_w 268
    //   102: aload 11
    //   104: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: astore 12
    //   109: aload 8
    //   111: aload 12
    //   113: invokeinterface 264 2 0
    //   118: pop
    //   119: new 253	org/apache/http/message/BasicNameValuePair
    //   122: dup
    //   123: ldc_w 270
    //   126: ldc 9
    //   128: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: astore 13
    //   133: aload 8
    //   135: aload 13
    //   137: invokeinterface 264 2 0
    //   142: pop
    //   143: aload_0
    //   144: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   147: astore 14
    //   149: iload_1
    //   150: istore 15
    //   152: iload_2
    //   153: istore 16
    //   155: iload_3
    //   156: istore 17
    //   158: aload 14
    //   160: astore 18
    //   162: iload 15
    //   164: iload 16
    //   166: iload 17
    //   168: aload 18
    //   170: invokestatic 873	com/yingyonghui/market/json/JsonParams:getTopKeywordsParams	(IIILjava/lang/String;)Ljava/lang/String;
    //   173: astore 19
    //   175: new 253	org/apache/http/message/BasicNameValuePair
    //   178: astore 20
    //   180: aload 20
    //   182: astore 21
    //   184: ldc_w 280
    //   187: astore 22
    //   189: aload 19
    //   191: astore 23
    //   193: aload 21
    //   195: aload 22
    //   197: aload 23
    //   199: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   202: aload 8
    //   204: aload 20
    //   206: invokeinterface 264 2 0
    //   211: pop
    //   212: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   215: astore 24
    //   217: aload 24
    //   219: astore 25
    //   221: aload 8
    //   223: astore 26
    //   225: ldc_w 284
    //   228: astore 27
    //   230: aload 25
    //   232: aload 26
    //   234: aload 27
    //   236: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   239: aload 7
    //   241: aload 24
    //   243: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   246: aload_0
    //   247: getfield 361	com/yingyonghui/market/online/MarketServiceAgent:httpClientForList	Lorg/apache/http/client/HttpClient;
    //   250: aload 7
    //   252: invokeinterface 297 2 0
    //   257: invokeinterface 303 1 0
    //   262: invokeinterface 309 1 0
    //   267: astore 28
    //   269: new 313	java/io/InputStreamReader
    //   272: dup
    //   273: aload 28
    //   275: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   278: astore 29
    //   280: new 311	java/io/BufferedReader
    //   283: dup
    //   284: aload 29
    //   286: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   289: astore 30
    //   291: ldc_w 321
    //   294: astore 31
    //   296: aload 30
    //   298: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   301: astore 32
    //   303: aload 32
    //   305: ifnonnull +23 -> 328
    //   308: aload 31
    //   310: invokestatic 876	com/yingyonghui/market/json/JsonUtils:getKeywordObject	(Ljava/lang/String;)Ljava/util/ArrayList;
    //   313: astore 4
    //   315: aload 28
    //   317: ifnull +8 -> 325
    //   320: aload 28
    //   322: invokevirtual 345	java/io/InputStream:close	()V
    //   325: aload 4
    //   327: areturn
    //   328: aload 31
    //   330: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   333: astore 33
    //   335: new 149	java/lang/StringBuilder
    //   338: dup
    //   339: aload 33
    //   341: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   344: aload 32
    //   346: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: astore 31
    //   354: goto -58 -> 296
    //   357: astore 34
    //   359: aload 34
    //   361: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   364: aload 34
    //   366: instanceof 233
    //   369: ifne +11 -> 380
    //   372: aload 34
    //   374: instanceof 350
    //   377: ifeq +35 -> 412
    //   380: aload 34
    //   382: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   385: astore 35
    //   387: new 233	java/net/SocketException
    //   390: dup
    //   391: aload 35
    //   393: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   396: athrow
    //   397: astore 36
    //   399: aload 28
    //   401: ifnull +8 -> 409
    //   404: aload 28
    //   406: invokevirtual 345	java/io/InputStream:close	()V
    //   409: aload 36
    //   411: athrow
    //   412: aload 28
    //   414: ifnull -89 -> 325
    //   417: aload 28
    //   419: invokevirtual 345	java/io/InputStream:close	()V
    //   422: goto -97 -> 325
    //   425: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   428: goto -103 -> 325
    //   431: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   434: goto -25 -> 409
    //   437: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   440: goto -115 -> 325
    //
    // Exception table:
    //   from	to	target	type
    //   50	315	357	java/lang/Throwable
    //   328	354	357	java/lang/Throwable
    //   50	315	397	finally
    //   328	354	397	finally
    //   359	397	397	finally
    //   417	422	425	java/io/IOException
    //   404	409	431	java/io/IOException
    //   320	325	437	java/io/IOException
  }

  public int getUpdateAvaliableNum(String[] paramArrayOfString, int paramInt)
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    while (true)
    {
      InputStream localInputStream;
      try
      {
        ArrayList localArrayList = new ArrayList(4);
        String str1 = getConnectKey();
        BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
        localArrayList.add(localBasicNameValuePair1);
        String str2 = getUUIDString();
        BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
        localArrayList.add(localBasicNameValuePair2);
        BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
        localArrayList.add(localBasicNameValuePair3);
        String str3 = getResolution();
        int i = DeviceUtil.getSDKVersionInt();
        String str4 = getHardwareCode();
        String str5 = getVersionName();
        String[] arrayOfString = paramArrayOfString;
        int j = paramInt;
        String str6 = JsonParams.getUpdateAvaliableNumParams(arrayOfString, j, i, str3, str4, str5);
        BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str6);
        localArrayList.add(localBasicNameValuePair4);
        UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
        localHttpPost.setEntity(localUrlEncodedFormEntity);
        localInputStream = this.httpClientForOther.execute(localHttpPost).getEntity().getContent();
        InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream);
        BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
        String str7 = "";
        String str8 = localBufferedReader.readLine();
        if (str8 != null)
          continue;
        int k = Integer.parseInt(str7.replace("{", "").replace("}", ""));
        if (localInputStream == null)
          continue;
        try
        {
          localInputStream.close();
          return k;
          String str9 = String.valueOf(str7);
          str7 = str9 + str8;
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        if (((localThrowable instanceof SocketException)) || ((localThrowable instanceof SocketTimeoutException)))
        {
          String str10 = localThrowable.getMessage();
          throw new SocketException(str10);
        }
      }
      finally
      {
        if (localInputStream == null);
      }
      try
      {
        localInputStream.close();
        throw localObject;
        if (localInputStream != null);
        try
        {
          localInputStream.close();
          int m = 0;
        }
        catch (IOException localIOException2)
        {
          while (true)
            localIOException2.printStackTrace();
        }
      }
      catch (IOException localIOException3)
      {
        while (true)
          localIOException3.printStackTrace();
      }
    }
  }

  // ERROR //
  public boolean reportError(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: aload_0
    //   4: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   7: ifnonnull +26 -> 33
    //   10: aload_0
    //   11: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   14: astore 8
    //   16: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   19: dup
    //   20: aload 8
    //   22: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   25: astore 9
    //   27: aload_0
    //   28: aload 9
    //   30: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   33: new 242	org/apache/http/client/methods/HttpPost
    //   36: dup
    //   37: ldc 56
    //   39: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   42: astore 10
    //   44: new 245	java/util/ArrayList
    //   47: astore 11
    //   49: aload 11
    //   51: astore 12
    //   53: iconst_4
    //   54: istore 13
    //   56: aload 12
    //   58: iload 13
    //   60: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   63: aload_0
    //   64: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   67: astore 14
    //   69: new 253	org/apache/http/message/BasicNameValuePair
    //   72: dup
    //   73: ldc 255
    //   75: aload 14
    //   77: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   80: astore 15
    //   82: aload 11
    //   84: astore 16
    //   86: aload 15
    //   88: astore 17
    //   90: aload 16
    //   92: aload 17
    //   94: invokeinterface 264 2 0
    //   99: pop
    //   100: aload_0
    //   101: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   104: astore 18
    //   106: new 253	org/apache/http/message/BasicNameValuePair
    //   109: dup
    //   110: ldc_w 268
    //   113: aload 18
    //   115: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: astore 19
    //   120: aload 11
    //   122: astore 20
    //   124: aload 19
    //   126: astore 21
    //   128: aload 20
    //   130: aload 21
    //   132: invokeinterface 264 2 0
    //   137: pop
    //   138: new 253	org/apache/http/message/BasicNameValuePair
    //   141: dup
    //   142: ldc_w 270
    //   145: ldc 9
    //   147: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: astore 22
    //   152: aload 11
    //   154: astore 23
    //   156: aload 22
    //   158: astore 24
    //   160: aload 23
    //   162: aload 24
    //   164: invokeinterface 264 2 0
    //   169: pop
    //   170: aload_0
    //   171: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   174: astore 25
    //   176: iload_1
    //   177: istore 26
    //   179: iload_2
    //   180: istore 27
    //   182: aload_3
    //   183: astore 28
    //   185: aload 4
    //   187: astore 29
    //   189: aload 5
    //   191: astore 30
    //   193: iload 26
    //   195: iload 27
    //   197: aload 25
    //   199: aload 28
    //   201: aload 29
    //   203: aload 30
    //   205: invokestatic 896	com/yingyonghui/market/json/JsonParams:getReportErrorParams	(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   208: astore 31
    //   210: new 253	org/apache/http/message/BasicNameValuePair
    //   213: astore 32
    //   215: aload 32
    //   217: astore 33
    //   219: ldc_w 280
    //   222: astore 34
    //   224: aload 31
    //   226: astore 35
    //   228: aload 33
    //   230: aload 34
    //   232: aload 35
    //   234: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload 11
    //   239: astore 36
    //   241: aload 32
    //   243: astore 37
    //   245: aload 36
    //   247: aload 37
    //   249: invokeinterface 264 2 0
    //   254: pop
    //   255: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   258: astore 38
    //   260: aload 38
    //   262: astore 39
    //   264: aload 11
    //   266: astore 40
    //   268: ldc_w 284
    //   271: astore 41
    //   273: aload 39
    //   275: aload 40
    //   277: aload 41
    //   279: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   282: aload 10
    //   284: aload 38
    //   286: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   289: aload_0
    //   290: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   293: aload 10
    //   295: invokeinterface 297 2 0
    //   300: invokeinterface 303 1 0
    //   305: invokeinterface 309 1 0
    //   310: astore 42
    //   312: new 311	java/io/BufferedReader
    //   315: astore 43
    //   317: new 313	java/io/InputStreamReader
    //   320: dup
    //   321: aload 42
    //   323: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   326: astore 44
    //   328: aload 43
    //   330: astore 45
    //   332: aload 44
    //   334: astore 46
    //   336: aload 45
    //   338: aload 46
    //   340: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   343: ldc_w 321
    //   346: astore 47
    //   348: aload 43
    //   350: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   353: astore 48
    //   355: aload 48
    //   357: ifnonnull +67 -> 424
    //   360: aload 47
    //   362: astore 49
    //   364: aload 49
    //   366: invokevirtual 328	java/lang/String:length	()I
    //   369: ifle +42 -> 411
    //   372: aload 49
    //   374: iconst_0
    //   375: invokevirtual 332	java/lang/String:charAt	(I)C
    //   378: istore 50
    //   380: iload 50
    //   382: ldc_w 333
    //   385: if_icmplt +26 -> 411
    //   388: iload 50
    //   390: ldc_w 334
    //   393: if_icmpgt +18 -> 411
    //   396: aload 49
    //   398: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   401: istore 51
    //   403: iload 51
    //   405: ifne +6 -> 411
    //   408: iconst_1
    //   409: istore 7
    //   411: aload 42
    //   413: ifnull +8 -> 421
    //   416: aload 42
    //   418: invokevirtual 345	java/io/InputStream:close	()V
    //   421: iload 7
    //   423: ireturn
    //   424: aload 47
    //   426: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   429: astore 52
    //   431: new 149	java/lang/StringBuilder
    //   434: dup
    //   435: aload 52
    //   437: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   440: aload 48
    //   442: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   448: astore 47
    //   450: goto -102 -> 348
    //   453: astore 53
    //   455: aload 53
    //   457: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   460: aload 53
    //   462: instanceof 233
    //   465: ifne +11 -> 476
    //   468: aload 53
    //   470: instanceof 350
    //   473: ifeq +35 -> 508
    //   476: aload 53
    //   478: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   481: astore 54
    //   483: new 233	java/net/SocketException
    //   486: dup
    //   487: aload 54
    //   489: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   492: athrow
    //   493: astore 55
    //   495: aload 42
    //   497: ifnull +8 -> 505
    //   500: aload 42
    //   502: invokevirtual 345	java/io/InputStream:close	()V
    //   505: aload 55
    //   507: athrow
    //   508: aload 42
    //   510: ifnull -89 -> 421
    //   513: aload 42
    //   515: invokevirtual 345	java/io/InputStream:close	()V
    //   518: goto -97 -> 421
    //   521: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   524: goto -103 -> 421
    //   527: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   530: goto -25 -> 505
    //   533: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   536: goto -115 -> 421
    //
    // Exception table:
    //   from	to	target	type
    //   44	403	453	java/lang/Throwable
    //   424	450	453	java/lang/Throwable
    //   44	403	493	finally
    //   424	450	493	finally
    //   455	493	493	finally
    //   513	518	521	java/io/IOException
    //   500	505	527	java/io/IOException
    //   416	421	533	java/io/IOException
  }

  // ERROR //
  public boolean sendComment(int paramInt1, String paramString1, int paramInt2, boolean paramBoolean, String paramString2, String paramString3, String paramString4)
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 8
    //   3: aload_0
    //   4: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   7: ifnonnull +26 -> 33
    //   10: aload_0
    //   11: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   14: astore 9
    //   16: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   19: dup
    //   20: aload 9
    //   22: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   25: astore 10
    //   27: aload_0
    //   28: aload 10
    //   30: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   33: new 242	org/apache/http/client/methods/HttpPost
    //   36: dup
    //   37: ldc 56
    //   39: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   42: astore 11
    //   44: new 245	java/util/ArrayList
    //   47: astore 12
    //   49: aload 12
    //   51: astore 13
    //   53: iconst_4
    //   54: istore 14
    //   56: aload 13
    //   58: iload 14
    //   60: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   63: aload_0
    //   64: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   67: astore 15
    //   69: new 253	org/apache/http/message/BasicNameValuePair
    //   72: dup
    //   73: ldc 255
    //   75: aload 15
    //   77: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   80: astore 16
    //   82: aload 12
    //   84: astore 17
    //   86: aload 16
    //   88: astore 18
    //   90: aload 17
    //   92: aload 18
    //   94: invokeinterface 264 2 0
    //   99: pop
    //   100: aload_0
    //   101: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   104: astore 19
    //   106: new 253	org/apache/http/message/BasicNameValuePair
    //   109: dup
    //   110: ldc_w 268
    //   113: aload 19
    //   115: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: astore 20
    //   120: aload 12
    //   122: astore 21
    //   124: aload 20
    //   126: astore 22
    //   128: aload 21
    //   130: aload 22
    //   132: invokeinterface 264 2 0
    //   137: pop
    //   138: new 253	org/apache/http/message/BasicNameValuePair
    //   141: dup
    //   142: ldc_w 270
    //   145: ldc 9
    //   147: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: astore 23
    //   152: aload 12
    //   154: astore 24
    //   156: aload 23
    //   158: astore 25
    //   160: aload 24
    //   162: aload 25
    //   164: invokeinterface 264 2 0
    //   169: pop
    //   170: aload_0
    //   171: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   174: astore 26
    //   176: iload_1
    //   177: istore 27
    //   179: aload_2
    //   180: astore 28
    //   182: iload_3
    //   183: istore 29
    //   185: aload 5
    //   187: astore 30
    //   189: aload 6
    //   191: astore 31
    //   193: aload 7
    //   195: astore 32
    //   197: iload 27
    //   199: aload 28
    //   201: iload 29
    //   203: aload 30
    //   205: aload 26
    //   207: aload 31
    //   209: aload 32
    //   211: invokestatic 902	com/yingyonghui/market/json/JsonParams:getSendCommentParams	(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   214: astore 33
    //   216: new 253	org/apache/http/message/BasicNameValuePair
    //   219: astore 34
    //   221: aload 34
    //   223: astore 35
    //   225: ldc_w 280
    //   228: astore 36
    //   230: aload 33
    //   232: astore 37
    //   234: aload 35
    //   236: aload 36
    //   238: aload 37
    //   240: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   243: aload 12
    //   245: astore 38
    //   247: aload 34
    //   249: astore 39
    //   251: aload 38
    //   253: aload 39
    //   255: invokeinterface 264 2 0
    //   260: pop
    //   261: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   264: astore 40
    //   266: aload 40
    //   268: astore 41
    //   270: aload 12
    //   272: astore 42
    //   274: ldc_w 284
    //   277: astore 43
    //   279: aload 41
    //   281: aload 42
    //   283: aload 43
    //   285: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   288: aload 11
    //   290: aload 40
    //   292: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   295: aload_0
    //   296: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   299: aload 11
    //   301: invokeinterface 297 2 0
    //   306: invokeinterface 303 1 0
    //   311: invokeinterface 309 1 0
    //   316: astore 44
    //   318: new 311	java/io/BufferedReader
    //   321: astore 45
    //   323: new 313	java/io/InputStreamReader
    //   326: dup
    //   327: aload 44
    //   329: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   332: astore 46
    //   334: aload 45
    //   336: astore 47
    //   338: aload 46
    //   340: astore 48
    //   342: aload 47
    //   344: aload 48
    //   346: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   349: ldc_w 321
    //   352: astore 49
    //   354: aload 45
    //   356: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   359: astore 50
    //   361: aload 50
    //   363: ifnonnull +67 -> 430
    //   366: aload 49
    //   368: astore 51
    //   370: aload 51
    //   372: invokevirtual 328	java/lang/String:length	()I
    //   375: ifle +42 -> 417
    //   378: aload 51
    //   380: iconst_0
    //   381: invokevirtual 332	java/lang/String:charAt	(I)C
    //   384: istore 52
    //   386: iload 52
    //   388: ldc_w 333
    //   391: if_icmplt +26 -> 417
    //   394: iload 52
    //   396: ldc_w 334
    //   399: if_icmpgt +18 -> 417
    //   402: aload 51
    //   404: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   407: istore 53
    //   409: iload 53
    //   411: ifne +6 -> 417
    //   414: iconst_1
    //   415: istore 8
    //   417: aload 44
    //   419: ifnull +8 -> 427
    //   422: aload 44
    //   424: invokevirtual 345	java/io/InputStream:close	()V
    //   427: iload 8
    //   429: ireturn
    //   430: aload 49
    //   432: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   435: astore 54
    //   437: new 149	java/lang/StringBuilder
    //   440: dup
    //   441: aload 54
    //   443: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   446: astore 55
    //   448: aload 50
    //   450: astore 56
    //   452: aload 55
    //   454: aload 56
    //   456: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   459: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   462: astore 49
    //   464: goto -110 -> 354
    //   467: astore 57
    //   469: aload 57
    //   471: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   474: aload 57
    //   476: instanceof 233
    //   479: ifne +11 -> 490
    //   482: aload 57
    //   484: instanceof 350
    //   487: ifeq +35 -> 522
    //   490: aload 57
    //   492: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   495: astore 58
    //   497: new 233	java/net/SocketException
    //   500: dup
    //   501: aload 58
    //   503: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   506: athrow
    //   507: astore 59
    //   509: aload 44
    //   511: ifnull +8 -> 519
    //   514: aload 44
    //   516: invokevirtual 345	java/io/InputStream:close	()V
    //   519: aload 59
    //   521: athrow
    //   522: aload 44
    //   524: ifnull -97 -> 427
    //   527: aload 44
    //   529: invokevirtual 345	java/io/InputStream:close	()V
    //   532: goto -105 -> 427
    //   535: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   538: goto -111 -> 427
    //   541: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   544: goto -25 -> 519
    //   547: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   550: goto -123 -> 427
    //
    // Exception table:
    //   from	to	target	type
    //   44	409	467	java/lang/Throwable
    //   430	464	467	java/lang/Throwable
    //   44	409	507	finally
    //   430	464	507	finally
    //   469	507	507	finally
    //   527	532	535	java/io/IOException
    //   514	519	541	java/io/IOException
    //   422	427	547	java/io/IOException
  }

  // ERROR //
  public boolean sendCommentReply(int paramInt1, int paramInt2, String paramString1, int paramInt3, boolean paramBoolean, String paramString2, String paramString3, String paramString4)
    throws SocketException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 9
    //   3: aload_0
    //   4: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   7: ifnonnull +26 -> 33
    //   10: aload_0
    //   11: getfield 43	com/yingyonghui/market/online/MarketServiceAgent:mContext	Landroid/content/Context;
    //   14: astore 10
    //   16: new 239	com/yingyonghui/market/online/IWHttpsClient
    //   19: dup
    //   20: aload 10
    //   22: invokespecial 240	com/yingyonghui/market/online/IWHttpsClient:<init>	(Landroid/content/Context;)V
    //   25: astore 11
    //   27: aload_0
    //   28: aload 11
    //   30: putfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   33: new 242	org/apache/http/client/methods/HttpPost
    //   36: dup
    //   37: ldc 56
    //   39: invokespecial 243	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   42: astore 12
    //   44: new 245	java/util/ArrayList
    //   47: astore 13
    //   49: aload 13
    //   51: astore 14
    //   53: iconst_4
    //   54: istore 15
    //   56: aload 14
    //   58: iload 15
    //   60: invokespecial 248	java/util/ArrayList:<init>	(I)V
    //   63: aload_0
    //   64: invokevirtual 251	com/yingyonghui/market/online/MarketServiceAgent:getConnectKey	()Ljava/lang/String;
    //   67: astore 16
    //   69: new 253	org/apache/http/message/BasicNameValuePair
    //   72: dup
    //   73: ldc 255
    //   75: aload 16
    //   77: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   80: astore 17
    //   82: aload 13
    //   84: astore 18
    //   86: aload 17
    //   88: astore 19
    //   90: aload 18
    //   92: aload 19
    //   94: invokeinterface 264 2 0
    //   99: pop
    //   100: aload_0
    //   101: invokespecial 266	com/yingyonghui/market/online/MarketServiceAgent:getUUIDString	()Ljava/lang/String;
    //   104: astore 20
    //   106: new 253	org/apache/http/message/BasicNameValuePair
    //   109: dup
    //   110: ldc_w 268
    //   113: aload 20
    //   115: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: astore 21
    //   120: aload 13
    //   122: astore 22
    //   124: aload 21
    //   126: astore 23
    //   128: aload 22
    //   130: aload 23
    //   132: invokeinterface 264 2 0
    //   137: pop
    //   138: new 253	org/apache/http/message/BasicNameValuePair
    //   141: dup
    //   142: ldc_w 270
    //   145: ldc 9
    //   147: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   150: astore 24
    //   152: aload 13
    //   154: astore 25
    //   156: aload 24
    //   158: astore 26
    //   160: aload 25
    //   162: aload 26
    //   164: invokeinterface 264 2 0
    //   169: pop
    //   170: aload_0
    //   171: invokespecial 272	com/yingyonghui/market/online/MarketServiceAgent:getVersionName	()Ljava/lang/String;
    //   174: astore 27
    //   176: iload_1
    //   177: istore 28
    //   179: iload_2
    //   180: istore 29
    //   182: aload_3
    //   183: astore 30
    //   185: iload 4
    //   187: istore 31
    //   189: aload 6
    //   191: astore 32
    //   193: aload 7
    //   195: astore 33
    //   197: aload 8
    //   199: astore 34
    //   201: iload 28
    //   203: iload 29
    //   205: aload 30
    //   207: iload 31
    //   209: aload 32
    //   211: aload 27
    //   213: aload 33
    //   215: aload 34
    //   217: invokestatic 908	com/yingyonghui/market/json/JsonParams:getSendCommentReplyParams	(IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   220: astore 35
    //   222: new 253	org/apache/http/message/BasicNameValuePair
    //   225: astore 36
    //   227: aload 36
    //   229: astore 37
    //   231: ldc_w 280
    //   234: astore 38
    //   236: aload 35
    //   238: astore 39
    //   240: aload 37
    //   242: aload 38
    //   244: aload 39
    //   246: invokespecial 258	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   249: aload 13
    //   251: astore 40
    //   253: aload 36
    //   255: astore 41
    //   257: aload 40
    //   259: aload 41
    //   261: invokeinterface 264 2 0
    //   266: pop
    //   267: new 282	org/apache/http/client/entity/UrlEncodedFormEntity
    //   270: astore 42
    //   272: aload 42
    //   274: astore 43
    //   276: aload 13
    //   278: astore 44
    //   280: ldc_w 284
    //   283: astore 45
    //   285: aload 43
    //   287: aload 44
    //   289: aload 45
    //   291: invokespecial 287	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   294: aload 12
    //   296: aload 42
    //   298: invokevirtual 291	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   301: aload_0
    //   302: getfield 237	com/yingyonghui/market/online/MarketServiceAgent:httpClientForOther	Lorg/apache/http/client/HttpClient;
    //   305: aload 12
    //   307: invokeinterface 297 2 0
    //   312: invokeinterface 303 1 0
    //   317: invokeinterface 309 1 0
    //   322: astore 46
    //   324: new 311	java/io/BufferedReader
    //   327: astore 47
    //   329: new 313	java/io/InputStreamReader
    //   332: dup
    //   333: aload 46
    //   335: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   338: astore 48
    //   340: aload 47
    //   342: astore 49
    //   344: aload 48
    //   346: astore 50
    //   348: aload 49
    //   350: aload 50
    //   352: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   355: ldc_w 321
    //   358: astore 51
    //   360: aload 47
    //   362: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   365: astore 52
    //   367: aload 52
    //   369: ifnonnull +67 -> 436
    //   372: aload 51
    //   374: astore 53
    //   376: aload 53
    //   378: invokevirtual 328	java/lang/String:length	()I
    //   381: ifle +42 -> 423
    //   384: aload 53
    //   386: iconst_0
    //   387: invokevirtual 332	java/lang/String:charAt	(I)C
    //   390: istore 54
    //   392: iload 54
    //   394: ldc_w 333
    //   397: if_icmplt +26 -> 423
    //   400: iload 54
    //   402: ldc_w 334
    //   405: if_icmpgt +18 -> 423
    //   408: aload 53
    //   410: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   413: istore 55
    //   415: iload 55
    //   417: ifne +6 -> 423
    //   420: iconst_1
    //   421: istore 9
    //   423: aload 46
    //   425: ifnull +8 -> 433
    //   428: aload 46
    //   430: invokevirtual 345	java/io/InputStream:close	()V
    //   433: iload 9
    //   435: ireturn
    //   436: aload 51
    //   438: invokestatic 348	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   441: astore 56
    //   443: new 149	java/lang/StringBuilder
    //   446: dup
    //   447: aload 56
    //   449: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   452: astore 57
    //   454: aload 52
    //   456: astore 58
    //   458: aload 57
    //   460: aload 58
    //   462: invokevirtual 158	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: astore 51
    //   470: goto -110 -> 360
    //   473: astore 59
    //   475: aload 59
    //   477: invokevirtual 223	java/lang/Throwable:printStackTrace	()V
    //   480: aload 59
    //   482: instanceof 233
    //   485: ifne +11 -> 496
    //   488: aload 59
    //   490: instanceof 350
    //   493: ifeq +35 -> 528
    //   496: aload 59
    //   498: invokevirtual 353	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   501: astore 60
    //   503: new 233	java/net/SocketException
    //   506: dup
    //   507: aload 60
    //   509: invokespecial 354	java/net/SocketException:<init>	(Ljava/lang/String;)V
    //   512: athrow
    //   513: astore 61
    //   515: aload 46
    //   517: ifnull +8 -> 525
    //   520: aload 46
    //   522: invokevirtual 345	java/io/InputStream:close	()V
    //   525: aload 61
    //   527: athrow
    //   528: aload 46
    //   530: ifnull -97 -> 433
    //   533: aload 46
    //   535: invokevirtual 345	java/io/InputStream:close	()V
    //   538: goto -105 -> 433
    //   541: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   544: goto -111 -> 433
    //   547: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   550: goto -25 -> 525
    //   553: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   556: goto -123 -> 433
    //
    // Exception table:
    //   from	to	target	type
    //   44	415	473	java/lang/Throwable
    //   436	470	473	java/lang/Throwable
    //   44	415	513	finally
    //   436	470	513	finally
    //   475	513	513	finally
    //   533	538	541	java/io/IOException
    //   520	525	547	java/io/IOException
    //   428	433	553	java/io/IOException
  }

  public int sendInstallLog(String paramString)
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    String str6;
    try
    {
      ArrayList localArrayList = new ArrayList(4);
      String str1 = getConnectKey();
      BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
      localArrayList.add(localBasicNameValuePair1);
      String str2 = getUUIDString();
      BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
      localArrayList.add(localBasicNameValuePair2);
      BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
      localArrayList.add(localBasicNameValuePair3);
      String str3 = DeviceUtil.getIMEI(this.mContext);
      String str4 = getIpAddress();
      String str5 = JsonParams.getInstallLogParams(str3, str4, paramString);
      BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str5);
      localArrayList.add(localBasicNameValuePair4);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
      localHttpPost.setEntity(localUrlEncodedFormEntity);
      int i = this.httpClientForOther.execute(localHttpPost).getStatusLine().getStatusCode();
      MobclickAgent.onEvent(this.mContext, "install_market_log", paramString);
      return i;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)));
      str6 = localThrowable.getMessage();
    }
    throw new SocketException(str6);
  }

  public void sendInstallUpdateLog(String paramString1, String paramString2)
    throws SocketException
  {
    String str;
    try
    {
      sendInstallUpdateLog(paramString1, paramString2, 55537);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str = localThrowable.getMessage();
    }
    throw new SocketException(str);
  }

  public void sendInstallUpdateLog(String paramString1, String paramString2, int paramInt)
    throws SocketException
  {
    MarketServiceAgent localMarketServiceAgent = this;
    String str1 = paramString1;
    String str2 = paramString2;
    int i = paramInt;
    String str3;
    try
    {
      localMarketServiceAgent.sendInstallUpdateLog(str1, str2, 55537, 55537, 55537, i);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str3 = localThrowable.getMessage();
    }
    throw new SocketException(str3);
  }

  public void sendInstallUpdateLog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws SocketException
  {
    String str3;
    try
    {
      MarketServiceAgent localMarketServiceAgent = this;
      String str1 = paramString1;
      String str2 = paramString2;
      int i = paramInt1;
      int j = paramInt2;
      int k = paramInt3;
      int m = paramInt4;
      localMarketServiceAgent.sendInstallUpdateLog(str1, str2, i, j, k, m, "");
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str3 = localThrowable.getMessage();
    }
    throw new SocketException(str3);
  }

  public void sendInstallUpdateLog(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString3)
    throws SocketException
  {
    String str1 = "jiangxb";
    String str2 = paramString1;
    Log.v(str1, str2);
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    while (true)
    {
      try
      {
        ArrayList localArrayList1 = new java/util/ArrayList;
        ArrayList localArrayList2 = localArrayList1;
        int i = 4;
        localArrayList2.<init>(i);
        String str3 = getConnectKey();
        BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str3);
        ArrayList localArrayList3 = localArrayList1;
        BasicNameValuePair localBasicNameValuePair2 = localBasicNameValuePair1;
        localArrayList3.add(localBasicNameValuePair2);
        String str4 = getUUIDString();
        BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("referer", str4);
        ArrayList localArrayList4 = localArrayList1;
        BasicNameValuePair localBasicNameValuePair4 = localBasicNameValuePair3;
        localArrayList4.add(localBasicNameValuePair4);
        BasicNameValuePair localBasicNameValuePair5 = new BasicNameValuePair("api", "market.PhoneMarket");
        ArrayList localArrayList5 = localArrayList1;
        BasicNameValuePair localBasicNameValuePair6 = localBasicNameValuePair5;
        localArrayList5.add(localBasicNameValuePair6);
        String str5 = DeviceUtil.getIMEI(this.mContext);
        String str6 = getIpAddress();
        String str7 = paramString1;
        String str8 = paramString2;
        int j = paramInt1;
        int k = paramInt2;
        int m = paramInt3;
        int n = paramInt4;
        String str9 = JsonParams.getInstallUpdateLogParams(str5, str6, str7, 0, str8, j, k, m, n);
        BasicNameValuePair localBasicNameValuePair7 = new org/apache/http/message/BasicNameValuePair;
        BasicNameValuePair localBasicNameValuePair8 = localBasicNameValuePair7;
        String str10 = "param";
        String str11 = str9;
        localBasicNameValuePair8.<init>(str10, str11);
        ArrayList localArrayList6 = localArrayList1;
        BasicNameValuePair localBasicNameValuePair9 = localBasicNameValuePair7;
        localArrayList6.add(localBasicNameValuePair9);
        UrlEncodedFormEntity localUrlEncodedFormEntity1 = new org/apache/http/client/entity/UrlEncodedFormEntity;
        UrlEncodedFormEntity localUrlEncodedFormEntity2 = localUrlEncodedFormEntity1;
        ArrayList localArrayList7 = localArrayList1;
        String str12 = "utf-8";
        localUrlEncodedFormEntity2.<init>(localArrayList7, str12);
        localHttpPost.setEntity(localUrlEncodedFormEntity1);
        this.httpClientForOther.execute(localHttpPost);
        str13 = paramString2;
        String str14 = paramString2;
        String str15 = "Category";
        if (str14 != str15)
          break label565;
        if ((paramString3 == null) || (paramString3.length() < 2))
          continue;
        String str16 = paramString3;
        int i1 = 0;
        int i2 = 2;
        str13 = str16.substring(i1, i2);
        int i3 = paramInt2;
        int i4 = 55537;
        if (i3 == i4)
          continue;
        switch (paramInt2)
        {
        default:
          str17 = "";
          String str18 = String.valueOf(str13);
          StringBuilder localStringBuilder1 = new StringBuilder(str18).append(":");
          String str19 = str17;
          str13 = str19;
          String str20 = paramString1;
          String str21 = "update";
          if (str20 != str21)
            break label739;
          MobclickAgent.onEvent(this.mContext, "update_log", str13);
          return;
        case 0:
        case 1:
        case 2:
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        if ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)))
          continue;
        String str22 = localThrowable.getMessage();
        throw new SocketException(str22);
      }
      String str17 = "download";
      continue;
      str17 = "rank";
      continue;
      str17 = "date";
      continue;
      label565: String str23 = paramString2;
      String str24 = "Ranking";
      if (str23 == str24)
      {
        int i5 = paramInt3;
        int i6 = 55537;
        if (i5 == i6)
          continue;
        switch (paramInt3)
        {
        case 2:
        case 3:
        default:
          str17 = "";
        case 1:
        case 4:
        case 5:
        }
        while (true)
        {
          String str25 = String.valueOf(str13);
          StringBuilder localStringBuilder2 = new StringBuilder(str25).append(":");
          String str26 = str17;
          str13 = str26;
          break;
          str17 = "week";
          continue;
          str17 = "month";
          continue;
          str17 = "all";
        }
      }
      String str27 = paramString2;
      String str28 = "Preinstall";
      if ((str27 != str28) || (paramString3 == null) || (paramString3.length() <= 0))
        continue;
      String str13 = paramString3;
      continue;
      label739: MobclickAgent.onEvent(this.mContext, "install_log", str13);
    }
  }

  public void sendNewsPageViewLog(String paramString, Integer paramInteger)
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    String str7;
    try
    {
      ArrayList localArrayList = new ArrayList(4);
      String str1 = getConnectKey();
      BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
      localArrayList.add(localBasicNameValuePair1);
      String str2 = getUUIDString();
      BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
      localArrayList.add(localBasicNameValuePair2);
      BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
      localArrayList.add(localBasicNameValuePair3);
      String str3 = DeviceUtil.getIMEI(this.mContext);
      String str4 = getIpAddress();
      int i = paramInteger.intValue();
      String str5 = JsonParams.getNewsPageViewLogParams(str3, str4, 0, paramString, i);
      BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str5);
      localArrayList.add(localBasicNameValuePair4);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
      localHttpPost.setEntity(localUrlEncodedFormEntity);
      this.httpClientForOther.execute(localHttpPost);
      String str6 = paramString;
      MobclickAgent.onEvent(this.mContext, "page_view_log", str6);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)));
      str7 = localThrowable.getMessage();
    }
    throw new SocketException(str7);
  }

  public void sendPageViewLog(String paramString)
    throws SocketException
  {
    String str;
    try
    {
      sendPageViewLog(paramString, 55537);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str = localThrowable.getMessage();
    }
    throw new SocketException(str);
  }

  public void sendPageViewLog(String paramString, int paramInt)
    throws SocketException
  {
    MarketServiceAgent localMarketServiceAgent = this;
    String str1 = paramString;
    int i = paramInt;
    String str2;
    try
    {
      localMarketServiceAgent.sendPageViewLog(str1, 55537, 55537, 55537, i);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str2 = localThrowable.getMessage();
    }
    throw new SocketException(str2);
  }

  public void sendPageViewLog(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws SocketException
  {
    String str2;
    try
    {
      MarketServiceAgent localMarketServiceAgent = this;
      String str1 = paramString;
      int i = paramInt1;
      int j = paramInt2;
      int k = paramInt3;
      int m = paramInt4;
      localMarketServiceAgent.sendPageViewLog(str1, i, j, k, m, "");
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while (!(localThrowable instanceof SocketException));
      str2 = localThrowable.getMessage();
    }
    throw new SocketException(str2);
  }

  public void sendPageViewLog(String paramString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString2)
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    while (true)
    {
      try
      {
        ArrayList localArrayList = new ArrayList(4);
        String str1 = getConnectKey();
        BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
        localArrayList.add(localBasicNameValuePair1);
        String str2 = getUUIDString();
        BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
        localArrayList.add(localBasicNameValuePair2);
        BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
        localArrayList.add(localBasicNameValuePair3);
        String str3 = DeviceUtil.getIMEI(this.mContext);
        String str4 = getIpAddress();
        String str5 = paramString1;
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        int m = paramInt4;
        String str6 = JsonParams.getPageViewLogParams(str3, str4, 0, str5, i, j, k, m);
        BasicNameValuePair localBasicNameValuePair4 = new org/apache/http/message/BasicNameValuePair;
        BasicNameValuePair localBasicNameValuePair5 = localBasicNameValuePair4;
        String str7 = "param";
        String str8 = str6;
        localBasicNameValuePair5.<init>(str7, str8);
        localArrayList.add(localBasicNameValuePair4);
        UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
        localHttpPost.setEntity(localUrlEncodedFormEntity);
        this.httpClientForOther.execute(localHttpPost);
        str9 = paramString1;
        String str10 = paramString1;
        String str11 = "Category";
        if (str10 != str11)
          break label474;
        if ((paramString2 == null) || (paramString2.length() < 2))
          continue;
        String str12 = paramString2;
        int n = 0;
        int i1 = 2;
        str9 = str12.substring(n, i1);
        int i2 = paramInt2;
        int i3 = 55537;
        if (i2 == i3)
          continue;
        switch (paramInt2)
        {
        default:
          str13 = "";
          String str14 = String.valueOf(str9);
          StringBuilder localStringBuilder1 = new StringBuilder(str14).append(":");
          String str15 = str13;
          str9 = str15;
          MobclickAgent.onEvent(this.mContext, "page_view_log", str9);
          return;
        case 0:
        case 1:
        case 2:
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        if ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)))
          continue;
        String str16 = localThrowable.getMessage();
        throw new SocketException(str16);
      }
      String str13 = "download";
      continue;
      str13 = "rank";
      continue;
      str13 = "date";
      continue;
      label474: String str17 = paramString1;
      String str18 = "Ranking";
      if (str17 == str18)
      {
        int i4 = paramInt3;
        int i5 = 55537;
        if (i4 == i5)
          continue;
        switch (paramInt3)
        {
        case 2:
        case 3:
        default:
          str13 = "";
        case 1:
        case 4:
        case 5:
        }
        while (true)
        {
          String str19 = String.valueOf(str9);
          StringBuilder localStringBuilder2 = new StringBuilder(str19).append(":");
          String str20 = str13;
          str9 = str20;
          break;
          str13 = "week";
          continue;
          str13 = "month";
          continue;
          str13 = "all";
        }
      }
      String str21 = paramString1;
      String str22 = "Preinstall";
      if ((str21 != str22) || (paramString2 == null) || (paramString2.length() <= 0))
        continue;
      String str9 = paramString2;
    }
  }

  public void sendSearchLog(String paramString1, String paramString2)
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    String str6;
    try
    {
      ArrayList localArrayList = new ArrayList(4);
      String str1 = getConnectKey();
      BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
      localArrayList.add(localBasicNameValuePair1);
      String str2 = getUUIDString();
      BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
      localArrayList.add(localBasicNameValuePair2);
      BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
      localArrayList.add(localBasicNameValuePair3);
      String str3 = DeviceUtil.getIMEI(this.mContext);
      String str4 = getIpAddress();
      String str5 = JsonParams.getSearchLogParams(str3, str4, paramString2, paramString1);
      BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str5);
      localArrayList.add(localBasicNameValuePair4);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
      localHttpPost.setEntity(localUrlEncodedFormEntity);
      this.httpClientForOther.execute(localHttpPost);
      MobclickAgent.onEvent(this.mContext, "search_log", paramString1);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)));
      str6 = localThrowable.getMessage();
    }
    throw new SocketException(str6);
  }

  public void sendWidgetDisableLog()
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    String str6;
    try
    {
      ArrayList localArrayList = new ArrayList(4);
      String str1 = getConnectKey();
      BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
      localArrayList.add(localBasicNameValuePair1);
      String str2 = getUUIDString();
      BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
      localArrayList.add(localBasicNameValuePair2);
      BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
      localArrayList.add(localBasicNameValuePair3);
      String str3 = DeviceUtil.getIMEI(this.mContext);
      String str4 = getIpAddress();
      String str5 = JsonParams.getWidgetDisableLogParams(str3, str4);
      BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str5);
      localArrayList.add(localBasicNameValuePair4);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
      localHttpPost.setEntity(localUrlEncodedFormEntity);
      this.httpClientForOther.execute(localHttpPost);
      MobclickAgent.onEvent(this.mContext, "widget_disable_log");
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)));
      str6 = localThrowable.getMessage();
    }
    throw new SocketException(str6);
  }

  public void sendWidgetEnableLog()
    throws SocketException
  {
    if (this.httpClientForOther == null)
    {
      Context localContext = this.mContext;
      IWHttpsClient localIWHttpsClient = new IWHttpsClient(localContext);
      this.httpClientForOther = localIWHttpsClient;
    }
    HttpPost localHttpPost = new HttpPost("http://mobile.yingyonghui.com/market/api");
    String str6;
    try
    {
      ArrayList localArrayList = new ArrayList(4);
      String str1 = getConnectKey();
      BasicNameValuePair localBasicNameValuePair1 = new BasicNameValuePair("key", str1);
      localArrayList.add(localBasicNameValuePair1);
      String str2 = getUUIDString();
      BasicNameValuePair localBasicNameValuePair2 = new BasicNameValuePair("referer", str2);
      localArrayList.add(localBasicNameValuePair2);
      BasicNameValuePair localBasicNameValuePair3 = new BasicNameValuePair("api", "market.PhoneMarket");
      localArrayList.add(localBasicNameValuePair3);
      String str3 = DeviceUtil.getIMEI(this.mContext);
      String str4 = getIpAddress();
      String str5 = JsonParams.getWidgetEnableLogParams(str3, str4);
      BasicNameValuePair localBasicNameValuePair4 = new BasicNameValuePair("param", str5);
      localArrayList.add(localBasicNameValuePair4);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "utf-8");
      localHttpPost.setEntity(localUrlEncodedFormEntity);
      this.httpClientForOther.execute(localHttpPost);
      MobclickAgent.onEvent(this.mContext, "widget_enable_log");
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        localThrowable.printStackTrace();
      while ((!(localThrowable instanceof SocketException)) && (!(localThrowable instanceof SocketTimeoutException)));
      str6 = localThrowable.getMessage();
    }
    throw new SocketException(str6);
  }

  // ERROR //
  public void testHttpsConnection()
  {
    // Byte code:
    //   0: invokestatic 1021	com/yingyonghui/market/util/SSLUtils:trustAllHostnames	()V
    //   3: invokestatic 1024	com/yingyonghui/market/util/SSLUtils:trustAllHttpsCertificates	()V
    //   6: new 1026	java/net/URL
    //   9: dup
    //   10: ldc_w 1028
    //   13: invokespecial 1029	java/net/URL:<init>	(Ljava/lang/String;)V
    //   16: invokevirtual 1033	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   19: checkcast 1035	javax/net/ssl/HttpsURLConnection
    //   22: astore_1
    //   23: aload_1
    //   24: ldc_w 1037
    //   27: invokevirtual 1040	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   30: new 1042	java/util/HashMap
    //   33: dup
    //   34: invokespecial 1043	java/util/HashMap:<init>	()V
    //   37: astore_2
    //   38: aload_2
    //   39: ldc_w 270
    //   42: ldc_w 1045
    //   45: invokeinterface 1049 3 0
    //   50: pop
    //   51: aload_2
    //   52: ldc 255
    //   54: ldc_w 321
    //   57: invokeinterface 1049 3 0
    //   62: pop
    //   63: aload_2
    //   64: ldc_w 280
    //   67: ldc_w 1051
    //   70: invokeinterface 1049 3 0
    //   75: pop
    //   76: aload_1
    //   77: ldc_w 484
    //   80: invokevirtual 1055	javax/net/ssl/HttpsURLConnection:setDoOutput	(Z)V
    //   83: aload_1
    //   84: ldc_w 484
    //   87: invokevirtual 1058	javax/net/ssl/HttpsURLConnection:setDoInput	(Z)V
    //   90: aload_1
    //   91: invokevirtual 1061	javax/net/ssl/HttpsURLConnection:connect	()V
    //   94: aload_0
    //   95: aload_2
    //   96: invokevirtual 1063	com/yingyonghui/market/online/MarketServiceAgent:getHeadersString	(Ljava/util/Map;)Ljava/lang/String;
    //   99: invokevirtual 1067	java/lang/String:getBytes	()[B
    //   102: astore_3
    //   103: aload_1
    //   104: invokevirtual 1071	javax/net/ssl/HttpsURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   107: astore 4
    //   109: aload_3
    //   110: arraylength
    //   111: istore 5
    //   113: aload 4
    //   115: aload_3
    //   116: iconst_0
    //   117: iload 5
    //   119: invokevirtual 1077	java/io/OutputStream:write	([BII)V
    //   122: aload 4
    //   124: invokevirtual 1080	java/io/OutputStream:flush	()V
    //   127: aload 4
    //   129: invokevirtual 1081	java/io/OutputStream:close	()V
    //   132: aload_1
    //   133: invokevirtual 1084	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   136: astore 6
    //   138: new 313	java/io/InputStreamReader
    //   141: dup
    //   142: aload 6
    //   144: invokespecial 316	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   147: astore 7
    //   149: new 311	java/io/BufferedReader
    //   152: dup
    //   153: aload 7
    //   155: invokespecial 319	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   158: astore 8
    //   160: aload 8
    //   162: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   165: astore 9
    //   167: aload 9
    //   169: ifnonnull +14 -> 183
    //   172: aload 6
    //   174: ifnull +8 -> 182
    //   177: aload 6
    //   179: invokevirtual 345	java/io/InputStream:close	()V
    //   182: return
    //   183: ldc_w 1086
    //   186: aload 9
    //   188: invokestatic 673	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   191: pop
    //   192: goto -32 -> 160
    //   195: astore 10
    //   197: aload 10
    //   199: invokevirtual 355	java/io/IOException:printStackTrace	()V
    //   202: aload 6
    //   204: ifnull -22 -> 182
    //   207: aload 6
    //   209: invokevirtual 345	java/io/InputStream:close	()V
    //   212: goto -30 -> 182
    //   215: astore 11
    //   217: goto -35 -> 182
    //   220: astore 12
    //   222: aload 6
    //   224: ifnull +8 -> 232
    //   227: aload 6
    //   229: invokevirtual 345	java/io/InputStream:close	()V
    //   232: aload 12
    //   234: athrow
    //   235: astore 13
    //   237: goto -5 -> 232
    //   240: astore 14
    //   242: goto -60 -> 182
    //
    // Exception table:
    //   from	to	target	type
    //   6	167	195	java/io/IOException
    //   183	192	195	java/io/IOException
    //   207	212	215	java/io/IOException
    //   6	167	220	finally
    //   183	192	220	finally
    //   197	202	220	finally
    //   227	232	235	java/io/IOException
    //   177	182	240	java/io/IOException
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.MarketServiceAgent
 * JD-Core Version:    0.6.0
 */