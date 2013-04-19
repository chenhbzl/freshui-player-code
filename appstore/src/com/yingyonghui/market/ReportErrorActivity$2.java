package com.yingyonghui.market;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class ReportErrorActivity$2 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      if (((Boolean)paramMessage.obj).booleanValue())
      {
        GlobalUtil.longToast(this.this$0, 2131296301);
        this.this$0.setResult(-1, null);
        this.this$0.finish();
        continue;
      }
      GlobalUtil.longToast(this.this$0, 2131296300);
      continue;
      try
      {
        this.this$0.showDialog(100);
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.ReportErrorActivity.2
 * JD-Core Version:    0.6.0
 */