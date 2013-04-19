package com.yingyonghui.market.online;

import android.os.Handler;
import java.util.Observable;

public class Request extends Observable
{
  private long _id;
  private boolean async = 1;
  private Object data;
  private String errorMessage = null;
  private Object extra;
  private int status;
  private int type;

  public Request(long paramLong, int paramInt)
  {
    this._id = paramLong;
    this.type = ???;
  }

  public Request(long paramLong, int paramInt, boolean paramBoolean)
  {
    this(paramLong, ???);
    this.async = paramInt;
  }

  public void addWifiObserver(Handler paramHandler)
  {
    Request.1 local1 = new Request.1(this, paramHandler);
    addObserver(local1);
  }

  public Object getData()
  {
    return this.data;
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public Object getExtra()
  {
    return this.extra;
  }

  public long getId()
  {
    return this._id;
  }

  public int getStatus()
  {
    return this.status;
  }

  public int getType()
  {
    return this.type;
  }

  public boolean isAsync()
  {
    return this.async;
  }

  public void notifyObservers(Object paramObject)
  {
    setChanged();
    notifyObservers(paramObject);
  }

  public void setData(Object paramObject)
  {
    this.data = paramObject;
  }

  public void setErrorMessage(String paramString)
  {
    this.errorMessage = paramString;
  }

  public void setExtra(Object paramObject)
  {
    this.extra = paramObject;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.Request
 * JD-Core Version:    0.6.0
 */