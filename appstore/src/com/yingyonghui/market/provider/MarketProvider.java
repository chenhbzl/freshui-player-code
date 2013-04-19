package com.yingyonghui.market.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import dalvik.annotation.Signature;
import java.util.HashMap;
import java.util.List;

public class MarketProvider extends ContentProvider
{
  private static final String DATABASE_NAME = "market.db";
  private static final int DATABASE_VERSION = 1;
  private static final int SEARCHS = 1;
  private static final int SEARCH_ID = 2;
  private static final String SEARCH_TABLE_NAME = "search";

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;"})
  private static HashMap sSearchsProjectionMap;
  private static final UriMatcher sUriMatcher = new UriMatcher(-1);
  private DatabaseHelper mOpenHelper;

  static
  {
    sUriMatcher.addURI("com.yingyonghui.provider.Market", "search", 1);
    sUriMatcher.addURI("com.yingyonghui.provider.Market", "search/#", 2);
    sSearchsProjectionMap = new HashMap();
    sSearchsProjectionMap.put("_id", "_id");
    sSearchsProjectionMap.put("date", "date");
    sSearchsProjectionMap.put("keyword", "keyword");
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getWritableDatabase();
    int i;
    switch (sUriMatcher.match(paramUri))
    {
    default:
      String str1 = "Unknown URI " + paramUri;
      throw new IllegalArgumentException(str1);
    case 1:
      i = localSQLiteDatabase.delete("search", paramString, paramArrayOfString);
      getContext().getContentResolver().notifyChange(paramUri, null);
      return i;
    case 2:
    }
    String str2 = (String)paramUri.getPathSegments().get(1);
    String str3 = "search";
    StringBuilder localStringBuilder = new StringBuilder("_id=").append(str2);
    if (!TextUtils.isEmpty(paramString));
    for (String str4 = " AND (" + paramString + 41; ; str4 = "")
    {
      String str5 = str4;
      i = localSQLiteDatabase.delete(str3, str5, paramArrayOfString);
      break;
    }
  }

  public String getType(Uri paramUri)
  {
    switch (sUriMatcher.match(paramUri))
    {
    default:
      String str1 = "Unknown URI " + paramUri;
      throw new IllegalArgumentException(str1);
    case 1:
    case 2:
    }
    for (String str2 = "vnd.android.cursor.dir/vnd.iworks.market"; ; str2 = "vnd.android.cursor.item/vnd.iworks.market")
      return str2;
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    if (sUriMatcher.match(paramUri) != 1)
    {
      String str1 = "Unknown URI " + paramUri;
      throw new IllegalArgumentException(str1);
    }
    if (paramContentValues != null);
    for (ContentValues localContentValues = new ContentValues(paramContentValues); ; localContentValues = new ContentValues())
    {
      Long localLong = Long.valueOf(System.currentTimeMillis());
      if (!localContentValues.containsKey("date"))
        localContentValues.put("date", localLong);
      long l = this.mOpenHelper.getWritableDatabase().insert("search", null, localContentValues);
      if (l <= 0L)
        break;
      Uri localUri = ContentUris.withAppendedId(MarketStore.Searchs.CONTENT_URI, l);
      getContext().getContentResolver().notifyChange(localUri, null);
      return localUri;
    }
    String str2 = "Failed to insert row into " + paramUri;
    throw new SQLException(str2);
  }

  public boolean onCreate()
  {
    Context localContext = getContext();
    DatabaseHelper localDatabaseHelper = new DatabaseHelper();
    this.mOpenHelper = localDatabaseHelper;
    return true;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    SQLiteQueryBuilder localSQLiteQueryBuilder = new SQLiteQueryBuilder();
    switch (sUriMatcher.match(paramUri))
    {
    default:
      String str1 = "Unknown URI " + paramUri;
      throw new IllegalArgumentException(str1);
    case 1:
      localSQLiteQueryBuilder.setTables("search");
      HashMap localHashMap1 = sSearchsProjectionMap;
      localSQLiteQueryBuilder.setProjectionMap(localHashMap1);
      if (!TextUtils.isEmpty(paramString2))
        break;
    case 2:
    }
    for (String str2 = "date DESC"; ; str2 = paramString2)
    {
      SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getReadableDatabase();
      String[] arrayOfString1 = paramArrayOfString1;
      String str3 = paramString1;
      String[] arrayOfString2 = paramArrayOfString2;
      String str4 = null;
      Cursor localCursor = localSQLiteQueryBuilder.query(localSQLiteDatabase, arrayOfString1, str3, arrayOfString2, null, str4, str2);
      ContentResolver localContentResolver = getContext().getContentResolver();
      localCursor.setNotificationUri(localContentResolver, paramUri);
      return localCursor;
      localSQLiteQueryBuilder.setTables("search");
      HashMap localHashMap2 = sSearchsProjectionMap;
      localSQLiteQueryBuilder.setProjectionMap(localHashMap2);
      StringBuilder localStringBuilder = new StringBuilder("_id=");
      String str5 = (String)paramUri.getPathSegments().get(1);
      String str6 = str5;
      localSQLiteQueryBuilder.appendWhere(str6);
      break;
    }
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = this.mOpenHelper.getWritableDatabase();
    int i;
    switch (sUriMatcher.match(paramUri))
    {
    default:
      String str1 = "Unknown URI " + paramUri;
      throw new IllegalArgumentException(str1);
    case 1:
      i = localSQLiteDatabase.update("search", paramContentValues, paramString, paramArrayOfString);
      getContext().getContentResolver().notifyChange(paramUri, null);
      return i;
    case 2:
    }
    String str2 = (String)paramUri.getPathSegments().get(1);
    String str3 = "search";
    StringBuilder localStringBuilder = new StringBuilder("_id=").append(str2);
    if (!TextUtils.isEmpty(paramString));
    for (String str4 = " AND (" + paramString + 41; ; str4 = "")
    {
      String str5 = str4;
      i = localSQLiteDatabase.update(str3, paramContentValues, str5, paramArrayOfString);
      break;
    }
  }

  class DatabaseHelper extends SQLiteOpenHelper
  {
    DatabaseHelper()
    {
      super("market.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE search (_id INTEGER PRIMARY KEY,date INTEGER,keyword TEXT);");
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.provider.MarketProvider
 * JD-Core Version:    0.6.0
 */