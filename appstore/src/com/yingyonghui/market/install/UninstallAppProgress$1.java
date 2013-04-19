package com.yingyonghui.market.install;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

class UninstallAppProgress$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 2:
    }
    UninstallAppProgress localUninstallAppProgress = this.this$0;
    int i = paramMessage.arg1;
    UninstallAppProgress.access$0(localUninstallAppProgress, i);
    if (paramMessage.arg1 == 1)
      UninstallAppProgress.access$1(this.this$0).setText(2131296434);
    while (true)
    {
      UninstallAppProgress.access$2(this.this$0).setVisibility(4);
      UninstallAppProgress.access$3(this.this$0).setVisibility(0);
      break;
      UninstallAppProgress.access$1(this.this$0).setText(2131296435);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.UninstallAppProgress.1
 * JD-Core Version:    0.6.0
 */