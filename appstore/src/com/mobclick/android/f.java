package com.mobclick.android;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class f
{
  private LocationManager a;
  private Context b;

  public f(Context paramContext)
  {
    this.b = paramContext;
  }

  public Location a()
  {
    try
    {
      LocationManager localLocationManager = (LocationManager)this.b.getSystemService("location");
      this.a = localLocationManager;
      if (m.a(this.b, "android.permission.ACCESS_FINE_LOCATION"))
      {
        localLocation = this.a.getLastKnownLocation("gps");
        if (localLocation != null)
        {
          StringBuilder localStringBuilder1 = new StringBuilder("get location from gps:");
          double d1 = localLocation.getLatitude();
          StringBuilder localStringBuilder2 = localStringBuilder1.append(d1).append(",");
          double d2 = localLocation.getLongitude();
          String str1 = d2;
          Log.i("MobclickAgent", str1);
        }
      }
      while (true)
      {
        return localLocation;
        if (!m.a(this.b, "android.permission.ACCESS_COARSE_LOCATION"))
          break;
        localLocation = this.a.getLastKnownLocation("network");
        if (localLocation == null)
          break;
        StringBuilder localStringBuilder3 = new StringBuilder("get location from network:");
        double d3 = localLocation.getLatitude();
        StringBuilder localStringBuilder4 = localStringBuilder3.append(d3).append(",");
        double d4 = localLocation.getLongitude();
        String str2 = d4;
        Log.i("MobclickAgent", str2);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str3 = localException.getMessage();
        Log.e("MobclickAgent", str3);
        Location localLocation = null;
        continue;
        Log.i("MobclickAgent", "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
        localLocation = null;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.f
 * JD-Core Version:    0.6.0
 */