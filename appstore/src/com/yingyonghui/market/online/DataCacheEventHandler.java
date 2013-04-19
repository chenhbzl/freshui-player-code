package com.yingyonghui.market.online;

import android.graphics.drawable.Drawable;
import java.net.SocketException;
import java.util.ArrayList;

public class DataCacheEventHandler extends Thread
{
  public boolean running = 1;
  private DataCacheService service = null;
  private int threadID = 0;

  public DataCacheEventHandler(DataCacheService paramDataCacheService, int paramInt)
  {
    this.service = paramDataCacheService;
    this.threadID = paramInt;
  }

  public void run()
  {
    while (true)
    {
      if (!this.running)
        return;
      Request localRequest;
      Object[] arrayOfObject;
      try
      {
        DataCacheService localDataCacheService = this.service;
        int i = this.threadID;
        localRequest = (Request)localDataCacheService.popRequest(i);
        if (localRequest == null)
          continue;
        switch (localRequest.getType())
        {
        default:
          break;
        case 65538:
          try
          {
            arrayOfObject = (Object[])localRequest.getData();
            if (arrayOfObject != null)
              int j = arrayOfObject.length;
            DataCacheServiceAgent localDataCacheServiceAgent1 = DataCacheServiceAgent.getInstance(this.service.mContext);
            int k = ((Integer)arrayOfObject[0]).intValue();
            int m = ((Integer)arrayOfObject[1]).intValue();
            int n = ((Integer)arrayOfObject[2]).intValue();
            int i1 = ((Integer)arrayOfObject[3]).intValue();
            ArrayList localArrayList1 = localDataCacheServiceAgent1.getAppList(k, m, n, i1);
            localRequest.setStatus(196610);
            localRequest.notifyObservers(localArrayList1);
          }
          catch (SocketException localSocketException1)
          {
            localRequest.setStatus(196613);
            localRequest.notifyObservers(null);
          }
        case 65549:
        case 65550:
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
      continue;
      try
      {
        Drawable localDrawable = DataCacheServiceAgent.getInstance(this.service.mContext).getTopFourApp();
        localRequest.setStatus(196610);
        localRequest.notifyObservers(localDrawable);
      }
      catch (SocketException localSocketException2)
      {
        localRequest.setStatus(196613);
        localRequest.notifyObservers(null);
      }
      continue;
      try
      {
        arrayOfObject = (Object[])localRequest.getData();
        if (arrayOfObject != null)
          int i2 = arrayOfObject.length;
        DataCacheServiceAgent localDataCacheServiceAgent2 = DataCacheServiceAgent.getInstance(this.service.mContext);
        int i3 = ((Integer)arrayOfObject[0]).intValue();
        int i4 = ((Integer)arrayOfObject[1]).intValue();
        int i5 = ((Integer)arrayOfObject[2]).intValue();
        int i6 = ((Integer)arrayOfObject[3]).intValue();
        ArrayList localArrayList2 = localDataCacheServiceAgent2.getAppList(i3, i4, i5, i6);
        localRequest.setStatus(196610);
        localRequest.notifyObservers(localArrayList2);
      }
      catch (SocketException localSocketException3)
      {
        localRequest.setStatus(196613);
        localRequest.notifyObservers(null);
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DataCacheEventHandler
 * JD-Core Version:    0.6.0
 */