package com.yingyonghui.market.online;

import android.os.Handler;
import android.os.Message;
import com.yingyonghui.market.model.Asset;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class UpdateService$2 extends Handler
{
  /** @deprecated */
  public void handleMessage(Message paramMessage)
  {
    monitorenter;
    while (true)
    {
      try
      {
        int i = paramMessage.arg1;
        int j = UpdateService.access$0(this.this$0)._id;
        if (j != i)
          return;
        switch (paramMessage.what)
        {
        default:
          break;
        case 1:
          if (paramMessage.arg2 != 1)
            continue;
          if (UpdateService.access$0(this.this$0) == null)
            continue;
          UpdateService.access$0(this.this$0).installed = 1;
          UpdateService.access$3(this.this$0, 3);
          continue;
        case 300:
        }
      }
      finally
      {
        monitorexit;
      }
      if (!((Boolean)paramMessage.obj).booleanValue())
        continue;
      UpdateService.access$3(this.this$0, 2);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.UpdateService.2
 * JD-Core Version:    0.6.0
 */