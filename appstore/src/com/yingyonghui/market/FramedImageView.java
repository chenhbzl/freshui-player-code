package com.yingyonghui.market;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FramedImageView extends ImageView
{
  private static final int INSET = 1;

  public FramedImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Drawable localDrawable = paramContext.getResources().getDrawable(2130837511);
    int i = getLeft();
    int j = getTop();
    int k = getRight();
    int m = getBottom();
    localDrawable.setBounds(i, j, k, m);
    setBackgroundDrawable(localDrawable);
    localDrawable.setCallback(null);
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    Drawable localDrawable = paramDrawable;
    int i = 1;
    int j = 8;
    InsetDrawable localInsetDrawable = new InsetDrawable(localDrawable, 1, 8, i, j);
    setImageDrawable(localInsetDrawable);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FramedImageView
 * JD-Core Version:    0.6.0
 */