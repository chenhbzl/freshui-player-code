package com.yingyonghui.market.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MarketStore
{
  public static final String AUTHORITY = "com.yingyonghui.provider.Market";
  public static final Uri CONTENT_URI = Uri.parse("content://com.yingyonghui.provider.Market");

  public final class Searchs
    implements BaseColumns
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.yingyonghui.provider.Market/search");
    public static final String DATE = "date";
    public static final String DEFAULT_SORT_ORDER = "date DESC";
    public static final String KEYWORD = "keyword";
    public static final String SEARCH_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.iworks.market";
    public static final String SEARCH_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.iworks.market";
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.MarketStore
 * JD-Core Version:    0.6.0
 */