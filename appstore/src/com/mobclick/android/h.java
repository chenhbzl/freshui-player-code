package com.mobclick.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class h
  implements DialogInterface.OnClickListener
{
  h(MobclickAgent paramMobclickAgent)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.h
 * JD-Core Version:    0.6.0
 */