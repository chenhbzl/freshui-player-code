package com.mobclick.android;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobclickAgent
  implements e
{
  private static final MobclickAgent a = new MobclickAgent();
  private static final String b = "MobclickAgent";
  private static final String c = "Android";
  private static final String d = "Android";
  private static final String e = "2.1";
  private static final long f = 30000L;
  private static final String g = "http://www.umeng.com/app_logs";
  private static final String h = "http://www.umeng.com/api/check_app_update";
  private static final int i = 8;
  private static int j = 1;
  private static String m = "";
  private static String n = "";
  private static boolean o = 1;
  private static UmengUpdateListener p = null;
  private static UmengFeedbackListener q = null;
  private static boolean r = 1;
  private Context k;
  private final Handler l;

  private MobclickAgent()
  {
    HandlerThread localHandlerThread = new HandlerThread("MobclickAgent");
    localHandlerThread.start();
    Looper localLooper = localHandlerThread.getLooper();
    Handler localHandler = new Handler(localLooper);
    this.l = localHandler;
  }

  private static String a(Context paramContext)
  {
    try
    {
      String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return str;
    }
    catch (Exception localException)
    {
      while (true)
        Log.i("MobclickAgent", "Could not read MAC, forget to include ACCESS_WIFI_STATE permission?", localException);
    }
  }

  private String a(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    Long localLong = Long.valueOf(System.currentTimeMillis());
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    long l1 = localLong.longValue();
    localEditor.putLong("start_millis", l1);
    localEditor.putLong("end_millis", 65535L);
    localEditor.commit();
    return paramSharedPreferences.getString("session_id", null);
  }

  private String a(Context paramContext, String paramString, SharedPreferences paramSharedPreferences)
  {
    c(paramContext, paramSharedPreferences);
    long l1 = System.currentTimeMillis();
    String str1 = String.valueOf(paramString);
    StringBuilder localStringBuilder = new StringBuilder(str1);
    String str2 = String.valueOf(l1);
    String str3 = str2;
    SharedPreferences.Editor localEditor = paramSharedPreferences.edit();
    localEditor.putString("appkey", paramString);
    localEditor.putString("session_id", str3);
    localEditor.putLong("start_millis", l1);
    localEditor.putLong("end_millis", 65535L);
    localEditor.putLong("duration", 0L);
    localEditor.putString("activities", "");
    localEditor.commit();
    b(paramContext, paramSharedPreferences);
    return str3;
  }

  // ERROR //
  private static String a(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 206	java/io/InputStreamReader
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 209	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   8: astore_1
    //   9: new 211	java/io/BufferedReader
    //   12: dup
    //   13: aload_1
    //   14: sipush 8192
    //   17: invokespecial 214	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   20: astore_2
    //   21: new 176	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   28: astore_3
    //   29: aload_2
    //   30: invokevirtual 218	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   33: astore 4
    //   35: aload 4
    //   37: ifnonnull +14 -> 51
    //   40: aload_0
    //   41: invokevirtual 223	java/io/InputStream:close	()V
    //   44: aload_3
    //   45: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: astore_2
    //   49: aload_2
    //   50: areturn
    //   51: aload 4
    //   53: invokestatic 174	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   56: astore 5
    //   58: new 176	java/lang/StringBuilder
    //   61: dup
    //   62: aload 5
    //   64: invokespecial 177	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   67: ldc 225
    //   69: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: astore 6
    //   77: aload_3
    //   78: aload 6
    //   80: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: goto -55 -> 29
    //   87: astore_2
    //   88: ldc 12
    //   90: ldc 227
    //   92: aload_2
    //   93: invokestatic 229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   96: pop
    //   97: aload_0
    //   98: invokevirtual 223	java/io/InputStream:close	()V
    //   101: aconst_null
    //   102: astore_2
    //   103: goto -54 -> 49
    //   106: astore 7
    //   108: ldc 12
    //   110: ldc 227
    //   112: aload 7
    //   114: invokestatic 229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   117: pop
    //   118: aconst_null
    //   119: astore_2
    //   120: goto -71 -> 49
    //   123: astore_2
    //   124: aload_0
    //   125: invokevirtual 223	java/io/InputStream:close	()V
    //   128: aload_2
    //   129: athrow
    //   130: astore 8
    //   132: ldc 12
    //   134: ldc 227
    //   136: aload 8
    //   138: invokestatic 229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   141: pop
    //   142: aconst_null
    //   143: astore_2
    //   144: goto -95 -> 49
    //   147: astore 9
    //   149: ldc 12
    //   151: ldc 227
    //   153: aload 9
    //   155: invokestatic 229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   158: pop
    //   159: aconst_null
    //   160: astore_2
    //   161: goto -112 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   29	35	87	java/io/IOException
    //   51	84	87	java/io/IOException
    //   97	101	106	java/io/IOException
    //   29	35	123	finally
    //   51	84	123	finally
    //   88	97	123	finally
    //   124	128	130	java/io/IOException
    //   40	44	147	java/io/IOException
  }

  private static String a(JSONObject paramJSONObject, String paramString)
  {
    String str1 = paramJSONObject.toString();
    Log.i("MobclickAgent", str1);
    Object localObject = new HttpPost(paramString);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
    try
    {
      ArrayList localArrayList = new ArrayList(1);
      String str2 = paramJSONObject.toString();
      BasicNameValuePair localBasicNameValuePair = new BasicNameValuePair("content", str2);
      localArrayList.add(localBasicNameValuePair);
      UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "UTF-8");
      ((HttpPost)localObject).setEntity(localUrlEncodedFormEntity);
      localObject = localDefaultHttpClient.execute((HttpUriRequest)localObject);
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 200)
      {
        String str3 = "Sent message to " + paramString;
        Log.i("MobclickAgent", str3);
        localObject = ((HttpResponse)localObject).getEntity();
        if (localObject != null)
          localObject = a(((HttpEntity)localObject).getContent());
      }
      while (true)
      {
        return localObject;
        localObject = null;
        continue;
        Log.i("MobclickAgent", "Failed to send message.");
        localObject = null;
      }
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      while (true)
      {
        Log.i("MobclickAgent", "ClientProtocolException,Failed to send message.", localClientProtocolException);
        localObject = null;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Log.i("MobclickAgent", "IOException,Failed to send message.", localIOException);
        localObject = null;
      }
    }
  }

  private void a(Context paramContext, SharedPreferences paramSharedPreferences, String paramString1, String paramString2, int paramInt)
  {
    String str1 = paramSharedPreferences.getString("session_id", "");
    String str2 = b();
    String str3 = str2.split(" ")[0];
    String str4 = str2.split(" ")[1];
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "event");
      localJSONObject.put("session_id", str1);
      localJSONObject.put("date", str3);
      localJSONObject.put("time", str4);
      localJSONObject.put("tag", paramString1);
      localJSONObject.put("label", paramString2);
      localJSONObject.put("acc", paramInt);
      Handler localHandler = this.l;
      j localj = new j(this, paramContext, localJSONObject);
      localHandler.post(localj);
      label153: return;
    }
    catch (JSONException localJSONException)
    {
      break label153;
    }
  }

  /** @deprecated */
  // ERROR //
  private void a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 234	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 337	org/json/JSONObject:<init>	()V
    //   9: astore_3
    //   10: aload_3
    //   11: ldc_w 339
    //   14: ldc_w 370
    //   17: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   20: pop
    //   21: aload_3
    //   22: ldc 189
    //   24: aload_2
    //   25: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   28: pop
    //   29: aload_1
    //   30: invokevirtual 374	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   33: astore 4
    //   35: aload_1
    //   36: invokevirtual 377	android/content/Context:getPackageName	()Ljava/lang/String;
    //   39: astore 5
    //   41: aload 4
    //   43: aload 5
    //   45: iconst_0
    //   46: invokevirtual 383	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   49: getfield 388	android/content/pm/PackageInfo:versionCode	I
    //   52: istore 6
    //   54: aload_1
    //   55: invokevirtual 377	android/content/Context:getPackageName	()Ljava/lang/String;
    //   58: astore 7
    //   60: aload_3
    //   61: ldc_w 390
    //   64: iload 6
    //   66: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   69: pop
    //   70: aload_3
    //   71: ldc_w 392
    //   74: aload 7
    //   76: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   79: pop
    //   80: aload_0
    //   81: getfield 90	com/mobclick/android/MobclickAgent:l	Landroid/os/Handler;
    //   84: astore 8
    //   86: new 360	com/mobclick/android/j
    //   89: dup
    //   90: aload_0
    //   91: aload_1
    //   92: aload_3
    //   93: invokespecial 363	com/mobclick/android/j:<init>	(Lcom/mobclick/android/MobclickAgent;Landroid/content/Context;Lorg/json/JSONObject;)V
    //   96: astore 9
    //   98: aload 8
    //   100: aload 9
    //   102: invokevirtual 367	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   105: pop
    //   106: aload_0
    //   107: monitorexit
    //   108: return
    //   109: astore 10
    //   111: aload_0
    //   112: monitorexit
    //   113: aload 10
    //   115: athrow
    //   116: astore 11
    //   118: goto -12 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	109	finally
    //   10	80	109	finally
    //   80	106	109	finally
    //   10	80	116	java/lang/Exception
  }

  /** @deprecated */
  private void a(Context paramContext, String paramString1, String paramString2)
  {
    monitorenter;
    while (true)
    {
      SharedPreferences localSharedPreferences;
      try
      {
        this.k = paramContext;
        localSharedPreferences = m(paramContext);
        if (localSharedPreferences == null)
          return;
        if (a(localSharedPreferences))
        {
          String str1 = a(paramContext, paramString1, localSharedPreferences);
          String str2 = "Start new session: " + str1;
          Log.i("MobclickAgent", str2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      String str3 = a(paramContext, localSharedPreferences);
      String str4 = "Extend current session: " + str3;
      Log.i("MobclickAgent", str4);
    }
  }

  /** @deprecated */
  private void a(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    monitorenter;
    try
    {
      SharedPreferences localSharedPreferences = m(paramContext);
      if (localSharedPreferences == null);
      while (true)
      {
        return;
        MobclickAgent localMobclickAgent = this;
        Context localContext = paramContext;
        String str1 = paramString2;
        String str2 = paramString3;
        int i1 = paramInt;
        localMobclickAgent.a(localContext, localSharedPreferences, str1, str2, i1);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  private void a(Context paramContext, JSONObject paramJSONObject)
  {
    monitorenter;
    if (paramJSONObject != null);
    try
    {
      b(paramContext, paramJSONObject);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private boolean a(SharedPreferences paramSharedPreferences)
  {
    long l1 = paramSharedPreferences.getLong("end_millis", 65535L);
    if (System.currentTimeMillis() - l1 > 30000L);
    for (int i1 = 1; ; i1 = 0)
      return i1;
  }

  private static boolean a(String paramString, Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str1 = paramContext.getPackageName();
    boolean bool;
    if ((localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", str1) == 0) && (!p(paramContext)))
      bool = false;
    while (true)
    {
      return bool;
      if ((paramString == "update") || (paramString == "feedback"))
      {
        bool = true;
        continue;
      }
      if (j == 3)
      {
        if (paramString == "flush")
        {
          bool = true;
          continue;
        }
      }
      else
      {
        if (paramString == "error")
        {
          bool = true;
          continue;
        }
        if ((j == 1) && (paramString == "launch"))
        {
          bool = true;
          continue;
        }
        if ((j == 2) && (paramString == "terminate"))
        {
          bool = true;
          continue;
        }
        if (j == 0)
        {
          bool = true;
          continue;
        }
        if (j == 4)
        {
          SharedPreferences localSharedPreferences = l(paramContext);
          String str2 = k.A();
          String str3 = localSharedPreferences.getString(str2, "false");
          String str4 = "Log has been sent today: " + str3 + ";type:" + paramString;
          Log.i("MobclickAgent", str4);
          if ((str3.equals("true")) || (!paramString.equals("launch")))
          {
            bool = false;
            continue;
          }
          bool = true;
          continue;
        }
        if (j == 5)
        {
          bool = q(paramContext)[0].equals("Wi-Fi");
          continue;
        }
      }
      bool = false;
    }
  }

  private static String b()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localDate = new Date();
    return localSimpleDateFormat.format(localDate);
  }

  private static String b(Context paramContext)
  {
    Object localObject1 = null;
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      Object localObject2 = localPackageManager.getApplicationInfo(str, 128);
      if (localObject2 != null)
      {
        localObject2 = ((ApplicationInfo)localObject2).metaData.getString("UMENG_APPKEY");
        if (localObject2 == null)
          break label53;
        localObject1 = localObject2;
      }
      while (true)
      {
        return localObject1.trim();
        label53: Log.i("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.i("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", localException);
    }
  }

  private void b(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    String str1 = paramSharedPreferences.getString("session_id", null);
    if (str1 == null)
      Log.e("MobclickAgent", "Missing session_id, ignore message");
    while (true)
    {
      return;
      String str2 = b();
      String str3 = str2.split(" ")[0];
      String str4 = str2.split(" ")[1];
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("type", "launch");
        localJSONObject.put("session_id", str1);
        localJSONObject.put("date", str3);
        localJSONObject.put("time", str4);
        Handler localHandler = this.l;
        j localj = new j(this, paramContext, localJSONObject);
        localHandler.post(localj);
      }
      catch (JSONException localJSONException)
      {
      }
    }
  }

  /** @deprecated */
  private void b(Context paramContext, String paramString)
  {
    monitorenter;
    try
    {
      String str = d(paramContext);
      if (str != "")
      {
        int i1 = str.length();
        if (i1 <= 10240)
          break label30;
      }
      while (true)
      {
        return;
        label30: c(paramContext, str);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(Context paramContext, JSONObject paramJSONObject)
  {
    String str1 = b();
    String str2 = str1.split(" ")[0];
    String str3 = str1.split(" ")[1];
    try
    {
      paramJSONObject.put("type", "feedback");
      paramJSONObject.put("date", str2);
      paramJSONObject.put("time", str3);
      Handler localHandler = this.l;
      j localj = new j(this, paramContext, paramJSONObject);
      localHandler.post(localj);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        UmengFeedbackListener localUmengFeedbackListener = q;
        int i1 = FeedbackStatus.FAILED;
        localUmengFeedbackListener.onFeedbackReturned(i1);
      }
    }
  }

  private static String c(Context paramContext)
  {
    Object localObject1 = "Unknown";
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      Object localObject2 = localPackageManager.getApplicationInfo(str, 128);
      if ((localObject2 != null) && (((ApplicationInfo)localObject2).metaData != null))
      {
        localObject2 = ((ApplicationInfo)localObject2).metaData.getString("UMENG_CHANNEL");
        if (localObject2 == null)
          break label60;
        localObject1 = localObject2;
      }
      while (true)
      {
        return localObject1;
        label60: Log.i("MobclickAgent", "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.i("MobclickAgent", "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.", localException);
    }
  }

  private void c(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = null;
    Object localObject1 = paramSharedPreferences.getString("session_id", null);
    if (localObject1 == null)
      Log.w("MobclickAgent", "Missing session_id, ignore message");
    while (true)
    {
      return;
      Object localObject2 = Long.valueOf(paramSharedPreferences.getLong("duration", 65535L));
      if (((Long)localObject2).longValue() <= 0L)
        localObject2 = Long.valueOf(0L);
      String str1 = b();
      String str2 = str1.split(" ")[0];
      String str3 = str1.split(" ")[1];
      localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("type", "terminate");
        localJSONObject.put("session_id", localObject1);
        localJSONObject.put("date", str2);
        localJSONObject.put("time", str3);
        String str4 = String.valueOf(((Long)localObject2).longValue() / 1000L);
        localJSONObject.put("duration", str4);
        if (o)
        {
          localObject1 = paramSharedPreferences.getString("activities", "").split(";");
          localObject2 = new JSONArray();
          str3 = null;
        }
        while (true)
        {
          int i2 = localObject1.length;
          if (str3 >= i2)
          {
            localJSONObject.put("activities", localObject2);
            Handler localHandler = this.l;
            j localj = new j(this, paramContext, localJSONObject);
            localHandler.post(localj);
            break;
          }
          String str5 = localObject1[str3];
          JSONArray localJSONArray = new JSONArray(str5);
          ((JSONArray)localObject2).put(localJSONArray);
          int i1 = str3 + 1;
        }
      }
      catch (JSONException localJSONException)
      {
      }
    }
  }

  private void c(Context paramContext, String paramString)
  {
    String str1 = b();
    String str2 = str1.split(" ")[0];
    String str3 = str1.split(" ")[1];
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "error");
      localJSONObject.put("context", paramString);
      localJSONObject.put("date", str2);
      localJSONObject.put("time", str3);
      Handler localHandler = this.l;
      j localj = new j(this, paramContext, localJSONObject);
      localHandler.post(localj);
      label106: return;
    }
    catch (JSONException localJSONException)
    {
      break label106;
    }
  }

  private void c(Context paramContext, JSONObject paramJSONObject)
  {
    if (a("update", paramContext))
    {
      String str1 = a(paramJSONObject, "http://www.umeng.com/api/check_app_update");
      String str2 = "return message from " + str1;
      Log.i("MobclickAgent", str2);
      if (str1 != null)
        d(paramContext, str1);
    }
    while (true)
    {
      return;
      if (p != null)
      {
        UmengUpdateListener localUmengUpdateListener1 = p;
        int i1 = UpdateStatus.Timeout;
        localUmengUpdateListener1.onUpdateReturned(i1);
        continue;
        if (p == null)
          continue;
        UmengUpdateListener localUmengUpdateListener2 = p;
        int i2 = UpdateStatus.No;
        localUmengUpdateListener2.onUpdateReturned(i2);
        continue;
      }
    }
  }

  private AlertDialog d(Context paramContext, JSONObject paramJSONObject)
  {
    try
    {
      String str1 = paramJSONObject.getString("version");
      String str2 = paramJSONObject.getString("update_log");
      String str3 = paramJSONObject.getString("path");
      AlertDialog.Builder localBuilder1 = new AlertDialog.Builder(paramContext);
      String str4 = k.h(paramContext);
      AlertDialog.Builder localBuilder2 = localBuilder1.setTitle(str4);
      String str5 = String.valueOf(k.i(paramContext));
      String str6 = str5 + str1 + "\n" + str2;
      AlertDialog.Builder localBuilder3 = localBuilder2.setMessage(str6).setCancelable(0);
      String str7 = k.j(paramContext);
      g localg = new g(this, paramContext, str3);
      AlertDialog.Builder localBuilder4 = localBuilder3.setPositiveButton(str7, localg);
      String str8 = k.m(paramContext);
      h localh = new h(this);
      localBuilder4.setNegativeButton(str8, localh);
      localAlertDialog = localBuilder1.create();
      localAlertDialog.setCancelable(1);
      return localAlertDialog;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("MobclickAgent", "Fail to create update dialog box.", localException);
        AlertDialog localAlertDialog = null;
      }
    }
  }

  private static String d(Context paramContext)
  {
    Object localObject1 = "";
    try
    {
      String str1 = paramContext.getPackageName();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add("logcat");
      localArrayList.add("-d");
      localArrayList.add("-v");
      localArrayList.add("raw");
      localArrayList.add("-s");
      localArrayList.add("AndroidRuntime:E");
      localArrayList.add("-p");
      localArrayList.add(str1);
      Runtime localRuntime = Runtime.getRuntime();
      String[] arrayOfString1 = new String[localArrayList.size()];
      String[] arrayOfString2 = (String[])localArrayList.toArray(arrayOfString1);
      InputStream localInputStream = localRuntime.exec(arrayOfString2).getInputStream();
      InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream);
      BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader, 1024);
      String str2 = localBufferedReader.readLine();
      localObject2 = 0;
      int i2 = 0;
      String str3 = "";
      String str4 = str2;
      int i3 = i2;
      while (true)
      {
        int i1;
        if (str4 == null)
        {
          i1 = str3.length();
          if ((i1 > 0) && (i3 != 0) && (localObject2 != 0))
            localObject1 = str3;
        }
        try
        {
          Runtime.getRuntime().exec("logcat -c");
          return localObject1;
          if (str4.indexOf("thread attach failed") < 0)
          {
            String str5 = String.valueOf(str3);
            String str6 = str5 + str4 + 10;
          }
          if ((i3 == 0) && (str4.toLowerCase().indexOf("exception") >= 0))
            i3 = 1;
          if ((localObject2 == 0) && (str4.indexOf(i1) >= 0))
          {
            Object localObject3 = 1;
            String str7 = localBufferedReader.readLine();
            localObject2 = localObject3;
            localObject4 = str7;
          }
        }
        catch (Exception localException1)
        {
          while (true)
            Log.e("MobclickAgent", "Failed to clear log");
        }
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        Object localObject2;
        Log.e("MobclickAgent", "Failed to catch error log");
        continue;
        Object localObject4 = localObject2;
      }
    }
  }

  private void d(Context paramContext, String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (!localJSONObject.getString("update").equals("Yes"))
        if (p != null)
        {
          UmengUpdateListener localUmengUpdateListener1 = p;
          int i1 = UpdateStatus.No;
          localUmengUpdateListener1.onUpdateReturned(i1);
        }
      while (true)
      {
        label50: return;
        if (p != null)
        {
          UmengUpdateListener localUmengUpdateListener2 = p;
          int i2 = UpdateStatus.Yes;
          localUmengUpdateListener2.onUpdateReturned(i2);
        }
        d(paramContext, localJSONObject).show();
      }
    }
    catch (Exception localException)
    {
      break label50;
    }
  }

  /** @deprecated */
  private void e(Context paramContext)
  {
    monitorenter;
    while (true)
    {
      Object localObject1;
      try
      {
        if (this.k == paramContext)
          continue;
        Log.e("MobclickAgent", "onPause() called without context from corresponding onResume()");
        return;
        this.k = paramContext;
        localObject1 = m(paramContext);
        if (localObject1 == null)
          continue;
        l1 = ((SharedPreferences)localObject1).getLong("start_millis", 65535L);
        if (l1 == 65535L)
        {
          Log.e("MobclickAgent", "onEndSession called before onStartSession");
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      long l2 = System.currentTimeMillis();
      long l1 = l2 - l1;
      long l3 = ((SharedPreferences)localObject1).getLong("duration", 0L);
      SharedPreferences.Editor localEditor = ((SharedPreferences)localObject1).edit();
      if (o)
      {
        localObject1 = ((SharedPreferences)localObject1).getString("activities", "");
        String str1 = paramContext.getClass().getName();
        if (!"".equals(localObject1))
        {
          String str2 = String.valueOf(localObject1);
          localObject1 = str2 + ";";
        }
        String str3 = String.valueOf(localObject1);
        StringBuilder localStringBuilder = new StringBuilder(str3).append("[").append(str1).append(",");
        long l4 = l1 / 1000L;
        String str4 = l4 + "]";
        localEditor.remove("activities");
        localEditor.putString("activities", str4);
      }
      localEditor.putLong("start_millis", 65535L);
      localEditor.putLong("end_millis", l2);
      long l5 = l1 + l3;
      localEditor.putLong("duration", l5);
      localEditor.commit();
    }
  }

  private void e(Context paramContext, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject1 = j(paramContext);
    if (localJSONObject1 == null)
      Log.e("MobclickAgent", "Fail to construct message header");
    JSONObject localJSONObject2;
    String str1;
    while (true)
    {
      return;
      localJSONObject2 = h(paramContext);
      JSONObject localJSONObject3 = new JSONObject();
      while (true)
      {
        try
        {
          str1 = paramJSONObject.getString("type");
          if (str1 == null)
            break;
          if (str1 == "flush")
            continue;
          paramJSONObject.remove("type");
          if (localJSONObject2 == null)
            break label155;
          if (!localJSONObject2.isNull(str1))
            break label140;
          JSONArray localJSONArray1 = new JSONArray();
          localJSONArray1.put(paramJSONObject);
          localJSONObject2.put(str1, localJSONArray1);
          if (localJSONObject2 != null)
            break label193;
          Log.w("MobclickAgent", "No cache message to flush");
        }
        catch (JSONException localJSONException)
        {
          Log.e("MobclickAgent", "Fail to construct json message.");
          i(paramContext);
        }
        break;
        label140: localJSONObject2.getJSONArray(str1).put(paramJSONObject);
        continue;
        label155: localJSONObject2 = new JSONObject();
        JSONArray localJSONArray2 = new JSONArray();
        localJSONArray2.put(paramJSONObject);
        localJSONObject2.put(str1, localJSONArray2);
      }
      label193: localJSONObject3.put("header", localJSONObject1);
      localJSONObject3.put("body", localJSONObject2);
      if (a(str1, paramContext))
      {
        if (a(localJSONObject3, "http://www.umeng.com/app_logs") != null)
        {
          Log.i("MobclickAgent", "send message succeed, clear cache");
          if (str1.equals("feedback"))
          {
            UmengFeedbackListener localUmengFeedbackListener1 = q;
            int i1 = FeedbackStatus.SUCCEED;
            localUmengFeedbackListener1.onFeedbackReturned(i1);
          }
          i(paramContext);
          if (j != 4)
            continue;
          SharedPreferences.Editor localEditor = l(paramContext).edit();
          String str2 = k.A();
          localEditor.putString(str2, "true");
          localEditor.commit();
          continue;
        }
        if (!str1.equals("feedback"))
          break;
        UmengFeedbackListener localUmengFeedbackListener2 = q;
        int i2 = FeedbackStatus.FAILED;
        localUmengFeedbackListener2.onFeedbackReturned(i2);
      }
    }
    while (true)
    {
      f(paramContext, localJSONObject2);
      break;
      if (!str1.equals("feedback"))
        continue;
      UmengFeedbackListener localUmengFeedbackListener3 = q;
      int i3 = FeedbackStatus.DISCONNECT;
      localUmengFeedbackListener3.onFeedbackReturned(i3);
    }
  }

  public static void enterPage(Context paramContext, String paramString)
  {
    onEvent(paramContext, "_PAGE_", paramString);
  }

  /** @deprecated */
  private void f(Context paramContext)
  {
    monitorenter;
    try
    {
      g(paramContext);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private static void f(Context paramContext, JSONObject paramJSONObject)
  {
    String str = o(paramContext);
    try
    {
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(str, 0);
      byte[] arrayOfByte = paramJSONObject.toString().getBytes();
      localFileOutputStream.write(arrayOfByte);
      localFileOutputStream.close();
      label31: return;
    }
    catch (IOException localIOException)
    {
      break label31;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      break label31;
    }
  }

  public static void flush(Context paramContext)
  {
    if (paramContext == null);
    try
    {
      Log.e("MobclickAgent", "unexpected null context");
      a.f(paramContext);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("MobclickAgent", "Exception occurred in Mobclick.flush(). ");
    }
  }

  private void g(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "flush");
      Handler localHandler = this.l;
      j localj = new j(this, paramContext, localJSONObject);
      localHandler.post(localj);
      label43: return;
    }
    catch (JSONException localJSONException)
    {
      break label43;
    }
  }

  private static JSONObject h(Context paramContext)
  {
    Object localObject1 = o(paramContext);
    try
    {
      localObject1 = paramContext.openFileInput((String)localObject1);
      String str1 = "";
      Object localObject2 = new byte[16384];
      int i1 = ((FileInputStream)localObject1).read(localObject2);
      if (i1 == -1)
      {
        if (str1.length() != 0)
          break label89;
        localObject1 = null;
      }
      while (true)
      {
        return localObject1;
        String str2 = String.valueOf(str1);
        StringBuilder localStringBuilder = new StringBuilder(str2);
        String str3 = new String(localObject2, 0, i1);
        str1 = str3;
        break;
        try
        {
          label89: localObject2 = new JSONObject(str1);
          localObject1 = localObject2;
        }
        catch (JSONException localJSONException)
        {
          ((FileInputStream)localObject1).close();
          i(paramContext);
          localObject1 = null;
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        localObject1 = null;
    }
    catch (IOException localIOException)
    {
      while (true)
        localObject1 = null;
    }
  }

  private static void i(Context paramContext)
  {
    String str1 = n(paramContext);
    paramContext.deleteFile(str1);
    String str2 = o(paramContext);
    paramContext.deleteFile(str2);
  }

  // ERROR //
  private static JSONObject j(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 832	com/mobclick/android/MobclickAgent:k	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore_1
    //   5: new 234	org/json/JSONObject
    //   8: dup
    //   9: invokespecial 337	org/json/JSONObject:<init>	()V
    //   12: astore_2
    //   13: aload_0
    //   14: ldc_w 834
    //   17: invokevirtual 102	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   20: checkcast 836	android/telephony/TelephonyManager
    //   23: astore_3
    //   24: aload_3
    //   25: ifnonnull +16 -> 41
    //   28: ldc 12
    //   30: ldc_w 838
    //   33: invokestatic 544	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: aconst_null
    //   38: astore_3
    //   39: aload_3
    //   40: areturn
    //   41: ldc 57
    //   43: astore 4
    //   45: aload_0
    //   46: ldc_w 840
    //   49: invokestatic 845	com/mobclick/android/m:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   52: ifeq +9 -> 61
    //   55: aload_3
    //   56: invokevirtual 848	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   59: astore 4
    //   61: aload 4
    //   63: invokestatic 854	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   66: ifeq +37 -> 103
    //   69: ldc 12
    //   71: ldc_w 838
    //   74: invokestatic 544	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   77: pop
    //   78: aload_0
    //   79: invokestatic 856	com/mobclick/android/MobclickAgent:a	(Landroid/content/Context;)Ljava/lang/String;
    //   82: astore 4
    //   84: aload 4
    //   86: ifnonnull +17 -> 103
    //   89: ldc 12
    //   91: ldc_w 858
    //   94: invokestatic 544	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   97: pop
    //   98: aconst_null
    //   99: astore_3
    //   100: goto -61 -> 39
    //   103: aload_2
    //   104: ldc_w 860
    //   107: aload 4
    //   109: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   112: pop
    //   113: getstatic 865	android/os/Build:MODEL	Ljava/lang/String;
    //   116: astore 5
    //   118: aload_2
    //   119: ldc_w 867
    //   122: aload 5
    //   124: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   127: pop
    //   128: aload_0
    //   129: invokestatic 869	com/mobclick/android/MobclickAgent:b	(Landroid/content/Context;)Ljava/lang/String;
    //   132: astore 4
    //   134: aload 4
    //   136: ifnonnull +17 -> 153
    //   139: ldc 12
    //   141: ldc_w 871
    //   144: invokestatic 516	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   147: pop
    //   148: aconst_null
    //   149: astore_3
    //   150: goto -111 -> 39
    //   153: aload_2
    //   154: ldc 189
    //   156: aload 4
    //   158: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   161: pop
    //   162: aload_0
    //   163: invokestatic 873	com/mobclick/android/MobclickAgent:c	(Landroid/content/Context;)Ljava/lang/String;
    //   166: astore 6
    //   168: aload_2
    //   169: ldc_w 875
    //   172: aload 6
    //   174: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload_0
    //   179: invokevirtual 374	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   182: astore 7
    //   184: aload_0
    //   185: invokevirtual 377	android/content/Context:getPackageName	()Ljava/lang/String;
    //   188: astore 8
    //   190: aload 7
    //   192: aload 8
    //   194: iconst_0
    //   195: invokevirtual 383	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   198: astore 9
    //   200: aload 9
    //   202: getfield 878	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   205: astore 10
    //   207: aload 9
    //   209: getfield 388	android/content/pm/PackageInfo:versionCode	I
    //   212: istore 11
    //   214: aload_2
    //   215: ldc_w 880
    //   218: aload 10
    //   220: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   223: pop
    //   224: aload_2
    //   225: ldc_w 390
    //   228: iload 11
    //   230: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   233: pop
    //   234: aload_2
    //   235: ldc_w 882
    //   238: ldc 15
    //   240: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   243: pop
    //   244: aload_2
    //   245: ldc_w 884
    //   248: ldc 19
    //   250: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   253: pop
    //   254: aload_2
    //   255: ldc_w 886
    //   258: ldc 15
    //   260: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   263: pop
    //   264: getstatic 891	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   267: astore 12
    //   269: aload_2
    //   270: ldc_w 893
    //   273: aload 12
    //   275: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   278: pop
    //   279: new 895	android/content/res/Configuration
    //   282: dup
    //   283: invokespecial 896	android/content/res/Configuration:<init>	()V
    //   286: astore 4
    //   288: aload_0
    //   289: invokevirtual 900	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   292: aload 4
    //   294: invokestatic 906	android/provider/Settings$System:getConfiguration	(Landroid/content/ContentResolver;Landroid/content/res/Configuration;)V
    //   297: aload 4
    //   299: ifnull +493 -> 792
    //   302: aload 4
    //   304: getfield 910	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   307: ifnull +485 -> 792
    //   310: aload 4
    //   312: getfield 910	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   315: invokevirtual 915	java/util/Locale:getCountry	()Ljava/lang/String;
    //   318: astore 13
    //   320: aload_2
    //   321: ldc_w 917
    //   324: aload 13
    //   326: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   329: pop
    //   330: aload 4
    //   332: getfield 910	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   335: invokevirtual 918	java/util/Locale:toString	()Ljava/lang/String;
    //   338: astore 14
    //   340: aload_2
    //   341: ldc_w 920
    //   344: aload 14
    //   346: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   349: pop
    //   350: aload 4
    //   352: getfield 910	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   355: invokestatic 926	java/util/Calendar:getInstance	(Ljava/util/Locale;)Ljava/util/Calendar;
    //   358: astore 4
    //   360: aload 4
    //   362: ifnull +417 -> 779
    //   365: aload 4
    //   367: invokevirtual 930	java/util/Calendar:getTimeZone	()Ljava/util/TimeZone;
    //   370: astore 4
    //   372: aload 4
    //   374: ifnull +374 -> 748
    //   377: aload 4
    //   379: invokevirtual 935	java/util/TimeZone:getRawOffset	()I
    //   382: ldc_w 936
    //   385: idiv
    //   386: istore 15
    //   388: aload_2
    //   389: ldc_w 938
    //   392: iload 15
    //   394: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   397: pop
    //   398: new 940	android/util/DisplayMetrics
    //   401: dup
    //   402: invokespecial 941	android/util/DisplayMetrics:<init>	()V
    //   405: astore 16
    //   407: aload_0
    //   408: ldc_w 943
    //   411: invokevirtual 102	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   414: checkcast 945	android/view/WindowManager
    //   417: invokeinterface 949 1 0
    //   422: aload 16
    //   424: invokevirtual 955	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   427: aload 16
    //   429: getfield 958	android/util/DisplayMetrics:widthPixels	I
    //   432: istore 17
    //   434: aload 16
    //   436: getfield 961	android/util/DisplayMetrics:heightPixels	I
    //   439: invokestatic 964	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   442: invokestatic 174	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   445: astore 18
    //   447: new 176	java/lang/StringBuilder
    //   450: dup
    //   451: aload 18
    //   453: invokespecial 177	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   456: ldc_w 966
    //   459: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   462: astore 19
    //   464: iload 17
    //   466: invokestatic 964	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   469: astore 20
    //   471: aload 19
    //   473: aload 20
    //   475: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   481: astore 21
    //   483: aload_2
    //   484: ldc_w 968
    //   487: aload 21
    //   489: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   492: pop
    //   493: aload_0
    //   494: invokestatic 474	com/mobclick/android/MobclickAgent:q	(Landroid/content/Context;)[Ljava/lang/String;
    //   497: astore 4
    //   499: aload 4
    //   501: iconst_0
    //   502: aaload
    //   503: astore 22
    //   505: aload_2
    //   506: ldc_w 970
    //   509: aload 22
    //   511: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   514: pop
    //   515: aload 4
    //   517: iconst_0
    //   518: aaload
    //   519: ldc_w 972
    //   522: invokevirtual 471	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   525: ifeq +19 -> 544
    //   528: aload 4
    //   530: iconst_1
    //   531: aaload
    //   532: astore 23
    //   534: aload_2
    //   535: ldc_w 974
    //   538: aload 23
    //   540: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   543: pop
    //   544: aload_3
    //   545: invokevirtual 977	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   548: astore 24
    //   550: aload_2
    //   551: ldc_w 979
    //   554: aload 24
    //   556: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   559: pop
    //   560: aload_0
    //   561: invokestatic 982	com/mobclick/android/MobclickAgent:r	(Landroid/content/Context;)Landroid/location/Location;
    //   564: astore_3
    //   565: aload_3
    //   566: ifnull +309 -> 875
    //   569: aload_3
    //   570: invokevirtual 988	android/location/Location:getLatitude	()D
    //   573: invokestatic 991	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   576: astore 25
    //   578: aload_2
    //   579: ldc_w 993
    //   582: aload 25
    //   584: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   587: pop
    //   588: aload_3
    //   589: invokevirtual 996	android/location/Location:getLongitude	()D
    //   592: invokestatic 991	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   595: astore 26
    //   597: aload_2
    //   598: ldc_w 998
    //   601: aload 26
    //   603: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   606: pop
    //   607: invokestatic 1000	com/mobclick/android/m:a	()Ljava/lang/String;
    //   610: astore 27
    //   612: aload_2
    //   613: ldc_w 1002
    //   616: aload 27
    //   618: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   621: pop
    //   622: getstatic 59	com/mobclick/android/MobclickAgent:m	Ljava/lang/String;
    //   625: ldc 57
    //   627: invokevirtual 471	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   630: ifne +18 -> 648
    //   633: getstatic 59	com/mobclick/android/MobclickAgent:m	Ljava/lang/String;
    //   636: astore 28
    //   638: aload_2
    //   639: ldc_w 1004
    //   642: aload 28
    //   644: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   647: pop
    //   648: getstatic 61	com/mobclick/android/MobclickAgent:n	Ljava/lang/String;
    //   651: ldc 57
    //   653: invokevirtual 471	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   656: ifne +18 -> 674
    //   659: getstatic 61	com/mobclick/android/MobclickAgent:n	Ljava/lang/String;
    //   662: astore 29
    //   664: aload_2
    //   665: ldc_w 1006
    //   668: aload 29
    //   670: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   673: pop
    //   674: aload_1
    //   675: invokeinterface 140 1 0
    //   680: astore 30
    //   682: aload_2
    //   683: invokevirtual 235	org/json/JSONObject:toString	()Ljava/lang/String;
    //   686: astore 31
    //   688: aload 30
    //   690: ldc_w 762
    //   693: aload 31
    //   695: invokeinterface 193 3 0
    //   700: pop
    //   701: aload 30
    //   703: invokeinterface 159 1 0
    //   708: pop
    //   709: aload_2
    //   710: astore_3
    //   711: goto -672 -> 39
    //   714: astore 32
    //   716: aload_2
    //   717: ldc_w 880
    //   720: ldc_w 1008
    //   723: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   726: pop
    //   727: aload_2
    //   728: ldc_w 390
    //   731: ldc_w 1008
    //   734: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   737: pop
    //   738: goto -504 -> 234
    //   741: astore 33
    //   743: aconst_null
    //   744: astore_3
    //   745: goto -706 -> 39
    //   748: aload_2
    //   749: ldc_w 938
    //   752: bipush 8
    //   754: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   757: pop
    //   758: goto -360 -> 398
    //   761: astore 34
    //   763: ldc 12
    //   765: ldc_w 1010
    //   768: aload 34
    //   770: invokestatic 229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   773: pop
    //   774: aconst_null
    //   775: astore_3
    //   776: goto -737 -> 39
    //   779: aload_2
    //   780: ldc_w 938
    //   783: bipush 8
    //   785: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   788: pop
    //   789: goto -391 -> 398
    //   792: aload_2
    //   793: ldc_w 917
    //   796: ldc_w 537
    //   799: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   802: pop
    //   803: aload_2
    //   804: ldc_w 920
    //   807: ldc_w 537
    //   810: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   813: pop
    //   814: aload_2
    //   815: ldc_w 938
    //   818: bipush 8
    //   820: invokevirtual 358	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   823: pop
    //   824: goto -426 -> 398
    //   827: astore 35
    //   829: aload_2
    //   830: ldc_w 968
    //   833: ldc_w 537
    //   836: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   839: pop
    //   840: goto -347 -> 493
    //   843: astore 36
    //   845: aload_2
    //   846: ldc_w 970
    //   849: ldc_w 537
    //   852: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   855: pop
    //   856: goto -312 -> 544
    //   859: astore 37
    //   861: aload_2
    //   862: ldc_w 979
    //   865: ldc_w 537
    //   868: invokevirtual 345	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   871: pop
    //   872: goto -312 -> 560
    //   875: aload_2
    //   876: ldc_w 993
    //   879: ldc2_w 1011
    //   882: invokevirtual 1015	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   885: pop
    //   886: aload_2
    //   887: ldc_w 998
    //   890: ldc2_w 1011
    //   893: invokevirtual 1015	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   896: pop
    //   897: goto -290 -> 607
    //   900: astore 38
    //   902: goto -841 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   178	234	714	android/content/pm/PackageManager$NameNotFoundException
    //   13	45	741	org/json/JSONException
    //   45	61	741	org/json/JSONException
    //   61	178	741	org/json/JSONException
    //   178	234	741	org/json/JSONException
    //   234	398	741	org/json/JSONException
    //   398	493	741	org/json/JSONException
    //   493	544	741	org/json/JSONException
    //   544	560	741	org/json/JSONException
    //   560	674	741	org/json/JSONException
    //   716	758	741	org/json/JSONException
    //   779	897	741	org/json/JSONException
    //   13	45	761	java/lang/SecurityException
    //   45	61	761	java/lang/SecurityException
    //   61	178	761	java/lang/SecurityException
    //   178	234	761	java/lang/SecurityException
    //   234	398	761	java/lang/SecurityException
    //   398	493	761	java/lang/SecurityException
    //   493	544	761	java/lang/SecurityException
    //   544	560	761	java/lang/SecurityException
    //   560	674	761	java/lang/SecurityException
    //   716	758	761	java/lang/SecurityException
    //   779	897	761	java/lang/SecurityException
    //   398	493	827	java/lang/Exception
    //   493	544	843	java/lang/Exception
    //   544	560	859	java/lang/Exception
    //   45	61	900	java/lang/Exception
  }

  private static SharedPreferences k(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    String str2 = "mobclick_agent_header_" + str1;
    return paramContext.getSharedPreferences(str2, 0);
  }

  private static SharedPreferences l(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    String str2 = "mobclick_agent_update_" + str1;
    return paramContext.getSharedPreferences(str2, 0);
  }

  private static SharedPreferences m(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    String str2 = "mobclick_agent_state_" + str1;
    return paramContext.getSharedPreferences(str2, 0);
  }

  private static String n(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return "mobclick_agent_header_" + str;
  }

  private static String o(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return "mobclick_agent_cached_" + str;
  }

  public static void onError(Context paramContext)
  {
    try
    {
      str = b(paramContext);
      if ((str == null) || (str.length() == 0))
        Log.e("MobclickAgent", "unexpected empty appkey");
      while (true)
      {
        return;
        if (paramContext != null)
          break;
        Log.e("MobclickAgent", "unexpected null context");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str;
        Log.e("MobclickAgent", "Exception occurred in Mobclick.onError()");
        continue;
        new i(paramContext, str, 2).start();
      }
    }
  }

  public static void onEvent(Context paramContext, String paramString)
  {
    onEvent(paramContext, paramString, 1);
  }

  public static void onEvent(Context paramContext, String paramString, int paramInt)
  {
    onEvent(paramContext, paramString, paramString, paramInt);
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2)
  {
    onEvent(paramContext, paramString1, paramString2, 1);
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    try
    {
      str1 = b(paramContext);
      if ((str1 == null) || (str1.length() == 0))
        Log.e("MobclickAgent", "unexpected empty appkey");
      while (true)
      {
        return;
        if (paramContext != null)
          break;
        Log.e("MobclickAgent", "unexpected null context");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str1;
        Log.e("MobclickAgent", "Exception occurred in Mobclick.onEvent(). ");
        continue;
        if ((paramString1 == null) || (paramString1 == ""))
        {
          Log.e("MobclickAgent", "tag is null or empty");
          continue;
        }
        if ((paramString2 == null) || (paramString2 == ""))
        {
          Log.e("MobclickAgent", "label is null or empty");
          continue;
        }
        if (paramInt <= 0)
        {
          Log.e("MobclickAgent", "Illegal value of acc");
          continue;
        }
        Context localContext = paramContext;
        String str2 = paramString1;
        String str3 = paramString2;
        int i1 = paramInt;
        new i(localContext, str1, str2, str3, i1, 3).start();
      }
    }
  }

  public static void onPause(Context paramContext)
  {
    if (paramContext == null);
    try
    {
      Log.e("MobclickAgent", "unexpected null context");
      while (true)
      {
        return;
        new i(paramContext, 0).start();
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("MobclickAgent", "Exception occurred in Mobclick.onRause(). ");
    }
  }

  public static void onResume(Context paramContext)
  {
    String str1 = b(paramContext);
    String str2 = c(paramContext);
    onResume(paramContext, str1, str2);
  }

  public static void onResume(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null);
    try
    {
      Log.e("MobclickAgent", "unexpected null context");
      while (true)
      {
        return;
        if ((paramString1 != null) && (paramString1.length() != 0))
          break;
        Log.e("MobclickAgent", "unexpected empty appkey");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("MobclickAgent", "Exception occurred in Mobclick.onResume(). ");
        continue;
        new i(paramContext, paramString1, paramString2, 1).start();
      }
    }
  }

  public static void openActivityDurationTrack(boolean paramBoolean)
  {
    o = paramBoolean;
  }

  public static void openFeedbackActivity(Context paramContext)
  {
    UmengFeedback.a(paramContext);
    UmengFeedback.a(a);
    Intent localIntent = new Intent(paramContext, UmengFeedback.class);
    paramContext.startActivity(localIntent);
  }

  private static boolean p(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    int i1;
    if (paramContext == null)
      i1 = 0;
    while (true)
    {
      return i1;
      NetworkInfo.State localState1 = paramContext.getNetworkInfo(0).getState();
      NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
      if (localState1 == localState2)
      {
        i1 = 1;
        continue;
      }
      NetworkInfo.State localState3 = paramContext.getNetworkInfo(1).getState();
      NetworkInfo.State localState4 = NetworkInfo.State.CONNECTED;
      if (localState3 == localState4)
      {
        i1 = 1;
        continue;
      }
      i1 = 0;
    }
  }

  private static String[] q(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str1 = paramContext.getPackageName();
    if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", str1) != 0)
      arrayOfString[0] = "Unknown";
    while (true)
    {
      return arrayOfString;
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        continue;
      }
      NetworkInfo.State localState1 = paramContext.getNetworkInfo(1).getState();
      NetworkInfo.State localState2 = NetworkInfo.State.CONNECTED;
      if (localState1 == localState2)
      {
        arrayOfString[0] = "Wi-Fi";
        continue;
      }
      NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(0);
      NetworkInfo.State localState3 = localNetworkInfo.getState();
      NetworkInfo.State localState4 = NetworkInfo.State.CONNECTED;
      if (localState3 != localState4)
        continue;
      arrayOfString[0] = "2G/3G";
      String str2 = localNetworkInfo.getSubtypeName();
      arrayOfString[1] = str2;
    }
  }

  private static Location r(Context paramContext)
  {
    return new f(paramContext).a();
  }

  public static void setFeedbackListener(UmengFeedbackListener paramUmengFeedbackListener)
  {
    q = paramUmengFeedbackListener;
  }

  public static void setOpenGLContext(GL10 paramGL10)
  {
    if (paramGL10 != null)
    {
      String[] arrayOfString = m.a(paramGL10);
      if (arrayOfString.length == 2)
      {
        m = arrayOfString[0];
        n = arrayOfString[1];
      }
    }
  }

  public static void setReportPolicy(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 5))
      Log.e("MobclickAgent", "Illegal value of report policy");
    while (true)
    {
      return;
      j = paramInt;
    }
  }

  public static void setUpdateListener(UmengUpdateListener paramUmengUpdateListener)
  {
    p = paramUmengUpdateListener;
  }

  public static void setUpdateOnlyWifi(boolean paramBoolean)
  {
    r = paramBoolean;
  }

  public static void update(Context paramContext)
  {
    try
    {
      if ((r) && (!q(paramContext)[0].equals("Wi-Fi")))
      {
        UmengUpdateListener localUmengUpdateListener1 = p;
        int i1 = UpdateStatus.NoneWifi;
        localUmengUpdateListener1.onUpdateReturned(i1);
      }
      while (true)
      {
        return;
        if (paramContext != null)
          break;
        UmengUpdateListener localUmengUpdateListener2 = p;
        int i2 = UpdateStatus.No;
        localUmengUpdateListener2.onUpdateReturned(i2);
        Log.e("MobclickAgent", "unexpected null context");
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringBuilder localStringBuilder = new StringBuilder("Exception occurred in Mobclick.update(). ");
        String str1 = localException.getMessage();
        String str2 = str1;
        Log.e("MobclickAgent", str2);
        continue;
        MobclickAgent localMobclickAgent = a;
        String str3 = b(paramContext);
        localMobclickAgent.a(paramContext, str3);
      }
    }
  }

  public void onFeedbackReturned(JSONObject paramJSONObject)
  {
    try
    {
      if (this.k == null);
      while (true)
      {
        return;
        MobclickAgent localMobclickAgent = a;
        Context localContext = this.k;
        localMobclickAgent.a(localContext, paramJSONObject);
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("MobclickAgent", "Exception occurred while sending feedback.");
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.MobclickAgent
 * JD-Core Version:    0.6.0
 */