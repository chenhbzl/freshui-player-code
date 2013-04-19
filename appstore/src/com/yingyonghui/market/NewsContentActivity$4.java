package com.yingyonghui.market;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yingyonghui.market.util.GlobalUtil;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class NewsContentActivity$4 extends Handler
{
  /** @deprecated */
  public void handleMessage(Message paramMessage)
  {
    monitorenter;
    while (true)
    {
      int i;
      TextView localTextView;
      try
      {
        i = paramMessage.arg1;
        LinearLayout localLinearLayout = NewsContentActivity.access$13(this.this$0);
        String str = "app" + i;
        View localView = localLinearLayout.findViewWithTag(str);
        if (localView == null)
          return;
        j = 2131427420;
        localTextView = (TextView)localView.findViewById(j);
        switch (paramMessage.what)
        {
        default:
          break;
        case 1:
          if (paramMessage.arg2 != 1)
            break label351;
          localTextView.setText(2131296358);
          localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
          localTextView.setBackgroundResource(2130837576);
          localTextView.getBackground().setCallback(null);
          continue;
        case 100:
        case 200:
        case 300:
        }
      }
      finally
      {
        monitorexit;
      }
      int j = 2131296412;
      localTextView.setText(j);
      localTextView.setBackgroundResource(2130837576);
      localTextView.getBackground().setCallback(null);
      this.this$0.addDownloadLogRequest(i);
      continue;
      localTextView.setText(2131296412);
      localTextView.setBackgroundResource(2130837576);
      localTextView.getBackground().setCallback(null);
      continue;
      if (((Boolean)paramMessage.obj).booleanValue())
      {
        if (GlobalUtil.isSystemApp(this.this$0.getPackageManager()))
        {
          localTextView.setText(2131296276);
          localTextView.setBackgroundResource(2130837576);
          localTextView.getBackground().setCallback(null);
          continue;
        }
        localTextView.setText(2131296418);
        localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        localTextView.setBackgroundResource(2130837596);
        localTextView.getBackground().setCallback(null);
        continue;
      }
      localTextView.setText(2131296361);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837577);
      localTextView.getBackground().setCallback(null);
      continue;
      label351: localTextView.setText(2131296418);
      localTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView.setBackgroundResource(2130837596);
      localTextView.getBackground().setCallback(null);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity.4
 * JD-Core Version:    0.6.0
 */