package com.yingyonghui.market;

import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import dalvik.annotation.EnclosingMethod;
import java.math.BigDecimal;

@EnclosingMethod
class AddCommentActivity$1
  implements RatingBar.OnRatingBarChangeListener
{
  public void onRatingChanged(RatingBar paramRatingBar, float paramFloat, boolean paramBoolean)
  {
    if (paramFloat <= 0.0F)
    {
      this.val$ratingIndicator.setText("");
      return;
    }
    double d = paramFloat;
    int i = new BigDecimal(d).setScale(0, 4).intValue();
    if (i > 5)
      i = 5;
    while (true)
    {
      TextView localTextView = this.val$ratingIndicator;
      String[] arrayOfString = AddCommentActivity.access$0(this.this$0);
      int j = i - 1;
      String str = arrayOfString[j];
      localTextView.setText(str);
      break;
      if (i >= 1)
        continue;
      i = 1;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AddCommentActivity.1
 * JD-Core Version:    0.6.0
 */