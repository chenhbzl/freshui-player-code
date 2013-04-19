package com.yingyonghui.market;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutDialog extends Activity
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131427443)
      finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    setContentView(2130903070);
    TextView localTextView = (TextView)findViewById(2131427442);
    try
    {
      String str = getPackageManager().getPackageInfo("com.yingyonghui.market", 8192).versionName;
      localTextView.setText(str);
      ((Button)findViewById(2131427443)).setOnClickListener(this);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localTextView.setText("");
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AboutDialog
 * JD-Core Version:    0.6.0
 */