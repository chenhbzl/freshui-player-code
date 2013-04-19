package com.yingyonghui.market;

import android.view.View;
import android.view.View.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SearchAssetListActivity$4
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    IMarketService localIMarketService = SearchAssetListActivity.access$5(this.this$0);
    Request localRequest = SearchAssetListActivity.access$6(this.this$0);
    localIMarketService.getAppListByKeywords(localRequest);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchAssetListActivity.4
 * JD-Core Version:    0.6.0
 */