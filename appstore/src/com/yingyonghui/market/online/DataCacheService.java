package com.yingyonghui.market.online;

import android.content.Context;

public class DataCacheService
{
  public static int THREAD_DETAIL;
  public static int THREAD_IMAGE;
  public static int THREAD_LIST = 0;
  private static int[] handlerIDs;
  private static DataCacheService instance;
  private DataCacheServiceAgent agent = null;
  protected Context mContext;
  private DataCacheEventHandler[] requestHandler;
  private CacheQueue requestQueueDetail;
  private CacheQueue requestQueueImage;
  private CacheQueue requestQueueList;

  static
  {
    THREAD_IMAGE = 1;
    THREAD_DETAIL = 2;
    int[] arrayOfInt = new int[3];
    int i = THREAD_LIST;
    arrayOfInt[0] = i;
    int j = THREAD_IMAGE;
    arrayOfInt[1] = j;
    int k = THREAD_DETAIL;
    arrayOfInt[2] = k;
    handlerIDs = arrayOfInt;
    instance = null;
  }

  private DataCacheService(Context paramContext)
  {
    CacheQueue localCacheQueue1 = new CacheQueue();
    this.requestQueueList = localCacheQueue1;
    CacheQueue localCacheQueue2 = new CacheQueue();
    this.requestQueueImage = localCacheQueue2;
    CacheQueue localCacheQueue3 = new CacheQueue();
    this.requestQueueDetail = localCacheQueue3;
    this.mContext = null;
    DataCacheEventHandler[] arrayOfDataCacheEventHandler = new DataCacheEventHandler[handlerIDs.length];
    this.requestHandler = arrayOfDataCacheEventHandler;
    DataCacheServiceAgent localDataCacheServiceAgent = DataCacheServiceAgent.getInstance(paramContext);
    this.agent = localDataCacheServiceAgent;
    this.mContext = paramContext;
    init();
  }

  public static DataCacheService getServiceInstance(Context paramContext)
  {
    if (instance == null)
      instance = new DataCacheService(paramContext);
    return instance;
  }

  public void destroy()
  {
    int i = 0;
    while (true)
    {
      int j = handlerIDs.length;
      if (i >= j)
        return;
      this.requestHandler[i].running = 0;
      this.requestHandler[i].interrupt();
      i += 1;
    }
  }

  public void finalize()
  {
    destroy();
    try
    {
      finalize();
      label8: return;
    }
    catch (Throwable localThrowable)
    {
      break label8;
    }
  }

  public void getAppDetail(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getAppList(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getAppListByKeywords(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getCategory(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getDownloadedAppList(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getTopFourApp(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void getUpdateAvaliableNum(Request paramRequest)
  {
    this.requestQueueList.pushRequest(paramRequest);
  }

  public void init()
  {
    if (this.requestHandler[0] != 0)
      return;
    int i = 0;
    while (true)
    {
      int j = handlerIDs.length;
      if (i >= j)
        break;
      DataCacheEventHandler[] arrayOfDataCacheEventHandler = this.requestHandler;
      int k = handlerIDs[i];
      DataCacheEventHandler localDataCacheEventHandler = new DataCacheEventHandler(this, k);
      arrayOfDataCacheEventHandler[i] = localDataCacheEventHandler;
      this.requestHandler[i].start();
      i += 1;
    }
  }

  public Object popRequest(int paramInt)
    throws InterruptedException
  {
    int i = THREAD_LIST;
    Object localObject;
    if (paramInt == i)
      localObject = this.requestQueueList.popRequest(0L);
    while (true)
    {
      return localObject;
      int j = THREAD_IMAGE;
      if (paramInt == j)
      {
        localObject = this.requestQueueImage.popRequest(0L);
        continue;
      }
      localObject = this.requestQueueDetail.popRequest(0L);
    }
  }

  public void setAppContext(Context paramContext)
  {
    if (this.mContext != paramContext)
      this.mContext = paramContext;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DataCacheService
 * JD-Core Version:    0.6.0
 */