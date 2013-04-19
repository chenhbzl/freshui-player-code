package com.yingyonghui.market.online;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.yingyonghui.market.FileManager;
import dalvik.annotation.Signature;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class DataCacheServiceAgent
{
  private static DataCacheServiceAgent instance;

  @Signature({"Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  private ArrayList mAssetList;
  private Context mContext = null;
  private boolean mTopFourLoaded = 0;
  private MarketServiceAgent marketServiceAgent = null;

  private DataCacheServiceAgent(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    this.mAssetList = localArrayList;
    this.mContext = paramContext;
    MarketServiceAgent localMarketServiceAgent = MarketServiceAgent.getInstance(this.mContext);
    this.marketServiceAgent = localMarketServiceAgent;
  }

  public static DataCacheServiceAgent getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new DataCacheServiceAgent(paramContext);
    return instance;
  }

  public static void setInstanceNull()
  {
    instance = null;
  }

  /** @deprecated */
  @Signature({"(IIII)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public ArrayList getAppList(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws SocketException
  {
    monitorenter;
    if (paramInt1 == 9291);
    while (true)
    {
      ArrayList localArrayList2;
      try
      {
        if ((this.mAssetList != null) && (this.mAssetList.size() != 0))
          continue;
        ArrayList localArrayList1 = this.marketServiceAgent.getAppList(paramInt1, paramInt2, paramInt3, paramInt4);
        this.mAssetList = localArrayList1;
        localObject1 = this.mAssetList;
        return localObject1;
        if (paramInt1 == -1)
        {
          if ((this.mAssetList != null) && (this.mAssetList.size() >= 14))
            continue;
          localArrayList2 = this.marketServiceAgent.getAppList(paramInt1, paramInt2, paramInt3, paramInt4);
          localObject1 = localArrayList2;
          continue;
          localArrayList2 = new ArrayList();
          List localList1 = this.mAssetList.subList(4, 14);
          localArrayList2.addAll(localList1);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (paramInt1 == 91)
      {
        if ((this.mAssetList == null) || (this.mAssetList.size() < 14))
          localArrayList2 = this.marketServiceAgent.getAppList(paramInt1, paramInt2, paramInt3, paramInt4);
        while (true)
        {
          localObject1 = localArrayList2;
          break;
          localArrayList2 = new ArrayList();
          List localList2 = this.mAssetList.subList(0, 4);
          localArrayList2.addAll(localList2);
        }
      }
      Object localObject1 = null;
    }
  }

  public Drawable getTopFourApp()
    throws SocketException
  {
    Drawable localDrawable = null;
    if (!this.mTopFourLoaded)
    {
      boolean bool = this.marketServiceAgent.getTopFourApp();
      this.mTopFourLoaded = bool;
    }
    while (true)
    {
      return localDrawable;
      localDrawable = FileManager.readDrawableFromFileSystem(this.mContext, "top_recommend");
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.DataCacheServiceAgent
 * JD-Core Version:    0.6.0
 */