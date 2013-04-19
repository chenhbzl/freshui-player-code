package com.yingyonghui.market.online;

import dalvik.annotation.Signature;
import java.util.LinkedList;

public class CacheQueue
{

  @Signature({"Ljava/util/LinkedList", "<", "Ljava/lang/Object;", ">;"})
  private LinkedList queue;

  public CacheQueue()
  {
    LinkedList localLinkedList = new LinkedList();
    this.queue = localLinkedList;
  }

  /** @deprecated */
  public void clearPendingRequest()
  {
    monitorenter;
    try
    {
      this.queue.clear();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public int countPending()
  {
    monitorenter;
    try
    {
      int i = this.queue.size();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  /** @deprecated */
  public boolean isEmpty()
  {
    monitorenter;
    try
    {
      int i = this.queue.size();
      if (i == 0)
      {
        i = 1;
        return i;
      }
      i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  /** @deprecated */
  public Object peekFirstRequest()
  {
    monitorenter;
    try
    {
      if (this.queue.size() > 0)
      {
        localObject1 = this.queue.getFirst();
        return localObject1;
      }
      Object localObject1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  /** @deprecated */
  public Object popRequest(long paramLong)
    throws InterruptedException
  {
    monitorenter;
    try
    {
      while (true)
      {
        if (this.queue.size() > 0)
        {
          Object localObject1 = this.queue.removeFirst();
          return localObject1;
        }
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          throw localInterruptedException;
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject2;
  }

  /** @deprecated */
  public void pushRequest(Object paramObject)
  {
    monitorenter;
    try
    {
      this.queue.addLast(paramObject);
      notify();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.CacheQueue
 * JD-Core Version:    0.6.0
 */