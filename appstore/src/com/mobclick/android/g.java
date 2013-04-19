package com.mobclick.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class g
  implements DialogInterface.OnClickListener
{
  g(MobclickAgent paramMobclickAgent, Context paramContext, String paramString)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Context localContext = this.b;
    String str1 = this.c;
    String str2 = k.k(this.b);
    String str3 = k.l(this.b);
    new a(localContext, str1, str2, str3, "").b();
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.g
 * JD-Core Version:    0.6.0
 */