package com.mobclick.android;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

final class j
  implements Runnable
{
  private static final Object a = new Object();
  private MobclickAgent b;
  private Context c;
  private JSONObject d;

  j(MobclickAgent paramMobclickAgent, Context paramContext, JSONObject paramJSONObject)
  {
    MobclickAgent localMobclickAgent = MobclickAgent.a();
    this.b = localMobclickAgent;
    this.c = paramContext;
    this.d = paramJSONObject;
  }

  public void run()
  {
    try
    {
      if (this.d.getString("type") == "update")
      {
        MobclickAgent localMobclickAgent1 = this.b;
        Context localContext1 = this.c;
        JSONObject localJSONObject1 = this.d;
        MobclickAgent.a(localMobclickAgent1, localContext1, localJSONObject1);
      }
      while (true)
      {
        return;
        synchronized (a)
        {
          MobclickAgent localMobclickAgent2 = this.b;
          Context localContext2 = this.c;
          JSONObject localJSONObject2 = this.d;
          MobclickAgent.b(localMobclickAgent2, localContext2, localJSONObject2);
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("MobclickAgent", "Exception occurred when sending message.");
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.j
 * JD-Core Version:    0.6.0
 */