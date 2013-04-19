package com.yingyonghui.market;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class WifiDownloadManager$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 14:
    default:
    case 12:
    case 13:
    case 15:
    }
    while (true)
    {
      return;
      Toast.makeText(this.val$mContext, 2131296471, 0).show();
      continue;
      Object[] arrayOfObject = (Object[])paramMessage.obj;
      if (arrayOfObject.length != 4)
        continue;
      int i = ((Integer)arrayOfObject[0]).intValue();
      int j = ((Integer)arrayOfObject[1]).intValue();
      String str1 = (String)arrayOfObject[2];
      String str2 = (String)arrayOfObject[3];
      Intent localIntent = new Intent();
      Context localContext = this.val$mContext;
      String str3 = TrafficWarningDialog.class.getName();
      localIntent.setClassName(localContext, str3);
      localIntent.putExtra("_id", i);
      localIntent.putExtra("pkgName", str1);
      localIntent.putExtra("size", j);
      localIntent.putExtra("title", str2);
      this.val$mContext.startActivity(localIntent);
      continue;
      Toast.makeText(this.val$mContext, "cancelling", 0).show();
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.WifiDownloadManager.1
 * JD-Core Version:    0.6.0
 */