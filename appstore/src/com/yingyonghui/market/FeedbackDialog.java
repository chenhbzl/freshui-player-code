package com.yingyonghui.market;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FeedbackDialog extends Activity
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131427457)
      finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    setContentView(2130903071);
    ((Button)findViewById(2131427457)).setOnClickListener(this);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FeedbackDialog
 * JD-Core Version:    0.6.0
 */