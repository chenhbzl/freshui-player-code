package com.yingyonghui.market.install;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

class InstallAppProgress$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
    }
    InstallAppProgress localInstallAppProgress = this.this$0;
    int i = paramMessage.arg2;
    InstallAppProgress.access$0(localInstallAppProgress, i);
    if (InstallAppProgress.access$1(this.this$0) == 1)
      InstallAppProgress.access$2(this.this$0).setText(2131296437);
    while (true)
    {
      InstallAppProgress.access$3(this.this$0).setVisibility(4);
      InstallAppProgress.access$4(this.this$0).setVisibility(0);
      break;
      InstallAppProgress.access$2(this.this$0).setText(2131296438);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.InstallAppProgress.1
 * JD-Core Version:    0.6.0
 */