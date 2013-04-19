package com.mobclick.android;

import android.content.Context;
import android.util.Log;

final class i extends Thread
{
  private static final Object a = new Object();
  private Context b;
  private int c;
  private String d;
  private String e;
  private String f;
  private String g;
  private int h;

  i(Context paramContext, int paramInt)
  {
    this.b = paramContext;
    this.c = paramInt;
  }

  i(Context paramContext, String paramString, int paramInt)
  {
    this.b = paramContext;
    this.c = paramInt;
    this.d = paramString;
  }

  i(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    this.b = paramContext;
    this.c = paramInt;
    this.d = paramString1;
    this.e = paramString2;
  }

  i(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.b = paramContext;
    this.d = paramString1;
    this.f = paramString2;
    this.g = paramString3;
    this.h = paramInt1;
    this.c = paramInt2;
  }

  public void run()
  {
    try
    {
      synchronized (a)
      {
        int i = this.c;
        if (i != 0);
      }
    }
    catch (Exception localException1)
    {
      while (true)
      {
        try
        {
          if (this.b != null)
            continue;
          Log.e("MobclickAgent", "unexpected null context");
          monitorexit;
          return;
          MobclickAgent localMobclickAgent1 = MobclickAgent.a();
          Context localContext1 = this.b;
          MobclickAgent.a(localMobclickAgent1, localContext1);
          monitorexit;
          continue;
          localObject2 = finally;
          monitorexit;
          throw localObject2;
          localException1 = localException1;
          Log.e("MobclickAgent", "Exception occurred when recording usage.");
          continue;
        }
        catch (Exception localException2)
        {
          Log.e("MobclickAgent", "Exception occurred in Mobclick.onRause(). ");
          continue;
        }
        if (this.c == 1)
        {
          MobclickAgent localMobclickAgent2 = MobclickAgent.a();
          Context localContext2 = this.b;
          String str1 = this.d;
          String str2 = this.e;
          MobclickAgent.a(localMobclickAgent2, localContext2, str1, str2);
          continue;
        }
        if (this.c == 2)
        {
          MobclickAgent localMobclickAgent3 = MobclickAgent.a();
          Context localContext3 = this.b;
          String str3 = this.d;
          MobclickAgent.a(localMobclickAgent3, localContext3, str3);
          continue;
        }
        if (this.c != 3)
          continue;
        MobclickAgent localMobclickAgent4 = MobclickAgent.a();
        Context localContext4 = this.b;
        String str4 = this.d;
        String str5 = this.f;
        String str6 = this.g;
        int j = this.h;
        MobclickAgent.a(localMobclickAgent4, localContext4, str4, str5, str6, j);
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.i
 * JD-Core Version:    0.6.0
 */