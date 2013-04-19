package com.yingyonghui.market.online;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Handler;
import com.yingyonghui.market.install.IWorksPackageManager;
import dalvik.annotation.EnclosingMethod;
import java.util.HashMap;

@EnclosingMethod
class DownloadService$1$1
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    HashMap localHashMap = DownloadService.access$2(DownloadService.1.access$0(this.this$1));
    Integer localInteger = Integer.valueOf(this.val$taskId);
    String str1 = (String)localHashMap.get(localInteger);
    Uri localUri = Uri.parse("file://" + str1);
    IWorksPackageManager localIWorksPackageManager = DownloadService.access$4(DownloadService.1.access$0(this.this$1));
    String str2 = this.val$packageName;
    Handler localHandler = DownloadService.access$5(DownloadService.1.access$0(this.this$1));
    int i = this.val$taskId;
    localIWorksPackageManager.installPackage(localUri, str2, localHandler, i);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DownloadService.1.1
 * JD-Core Version:    0.6.0
 */