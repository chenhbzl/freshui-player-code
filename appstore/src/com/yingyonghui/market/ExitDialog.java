package com.yingyonghui.market;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.mobclick.android.MobclickAgent;
import com.yingyonghui.market.online.DataCacheServiceAgent;

public class ExitDialog extends Activity
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    int i = paramView.getId();
    if (i == 2131427465)
      setResult(-1);
    while (true)
    {
      try
      {
        DataCacheServiceAgent.setInstanceNull();
        finish();
        FileManager.deleteApkFiles(this);
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        continue;
      }
      if (i != 2131427466)
        continue;
      setResult(0);
      MobclickAgent.onEvent(this, "OnExit", "Cancel");
      finish();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903075);
    Button localButton1 = (Button)findViewById(2131427465);
    Button localButton2 = (Button)findViewById(2131427466);
    localButton1.requestFocus();
    localButton1.setOnClickListener(this);
    localButton2.setOnClickListener(this);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.ExitDialog
 * JD-Core Version:    0.6.0
 */