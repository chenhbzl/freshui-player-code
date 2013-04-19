package com.yingyonghui.market;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class AddCommentActivity$3 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    this.this$0.dismissDialog(200);
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
        Toast.makeText(this.this$0, 2131296288, 1).show();
        Intent localIntent = new Intent();
        int i = (int)AddCommentActivity.access$2(this.this$0).getRating();
        localIntent.putExtra("rating", i);
        String str = AddCommentActivity.access$3(this.this$0).getText().toString().trim();
        localIntent.putExtra("comment", str);
        this.this$0.setResult(-1, localIntent);
        this.this$0.finish();
        continue;
      }
      Toast.makeText(this.this$0, 2131296287, 1).show();
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
 * Qualified Name:     com.yingyonghui.market.AddCommentActivity.3
 * JD-Core Version:    0.6.0
 */