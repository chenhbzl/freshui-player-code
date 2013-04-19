package com.mobclick.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

class b extends Handler
{
  b(a parama)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      StringBuilder localStringBuilder1 = new StringBuilder("file://");
      String str1 = a.a(this.a);
      StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append("/");
      String str2 = a.b(this.a);
      Uri localUri = Uri.parse(str2);
      localIntent.setDataAndType(localUri, "application/vnd.android.package-archive");
      a.c(this.a).startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str3 = localException.getMessage();
        Log.e("can not install", str3);
        a.a(this.a, 0);
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.b
 * JD-Core Version:    0.6.0
 */