package com.yingyonghui.market.online;

public abstract interface IMarketService
{
  public abstract void buyByAlipay(Request paramRequest);

  public abstract void buyByBean(Request paramRequest);

  public abstract void buyByYeepayCard(Request paramRequest);

  public abstract void deleteComment(Request paramRequest);

  public abstract void destroy();

  public abstract void getAccountBalance(Request paramRequest);

  public abstract void getAppComments(Request paramRequest);

  public abstract void getAppContentStream(Request paramRequest);

  public abstract void getAppDetail(Request paramRequest);

  public abstract void getAppIcon(Request paramRequest);

  public abstract void getAppList(Request paramRequest);

  public abstract void getAppListByKeywords(Request paramRequest);

  public abstract void getAppScreenShorts(Request paramRequest);

  public abstract void getCategory(Request paramRequest);

  public abstract void getDownloadedAppList(Request paramRequest);

  public abstract void getNewsAppInfo(Request paramRequest);

  public abstract void getNewsContent(Request paramRequest);

  public abstract void getNewsIcon(Request paramRequest);

  public abstract void getNewsImage(Request paramRequest);

  public abstract void getNewsList(Request paramRequest);

  public abstract void getNewsThumb(Request paramRequest);

  public abstract void getSearchLog(Request paramRequest);

  public abstract void getTestUserAvailable(Request paramRequest);

  public abstract void getTopFourApp(Request paramRequest);

  public abstract void getTopKeywords(Request paramRequest);

  public abstract void getUpdateAvaliableNum(Request paramRequest);

  public abstract void init();

  public abstract void isAppBuyed(Request paramRequest);

  public abstract void loginToServer(Request paramRequest);

  public abstract void reportError(Request paramRequest);

  public abstract void sendComment(Request paramRequest);
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.IMarketService
 * JD-Core Version:    0.6.0
 */