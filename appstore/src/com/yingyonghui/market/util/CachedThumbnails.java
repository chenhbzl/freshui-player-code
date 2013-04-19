package com.yingyonghui.market.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.yingyonghui.market.FileManager;
import dalvik.annotation.Signature;
import java.util.ArrayList;
import java.util.Hashtable;

public class CachedThumbnails
{
  public static final String ICON = "icon";
  private static final int MAX_SIZE = 50;
  private static final int MIN_SIZE = 20;

  @Signature({"Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Landroid/graphics/drawable/Drawable;", ">;"})
  private static Hashtable cachedDrawables = new Hashtable();
  private static Drawable defaultIcon;

  @Signature({"Ljava/util/ArrayList", "<", "Ljava/lang/Integer;", ">;"})
  private static ArrayList keys = new ArrayList();

  public static void cacheThumbnail(Context paramContext, int paramInt, Drawable paramDrawable)
  {
    if (paramContext.getSharedPreferences("com.yingyonghui.market_preferences", 0).getBoolean("checkbox_buffer_icons", 1))
      cacheThumbnail(paramContext, paramInt, paramDrawable, 1);
    while (true)
    {
      return;
      cacheThumbnail(paramContext, paramInt, paramDrawable, 0);
    }
  }

  public static void cacheThumbnail(Context paramContext, int paramInt, Drawable paramDrawable, boolean paramBoolean)
  {
    ArrayList localArrayList1;
    int i;
    if (cachedDrawables.size() > 50)
    {
      localArrayList1 = new ArrayList();
      i = 0;
    }
    while (true)
    {
      if (i >= 30)
      {
        keys.removeAll(localArrayList1);
        Hashtable localHashtable1 = cachedDrawables;
        Integer localInteger1 = Integer.valueOf(paramInt);
        localHashtable1.put(localInteger1, paramDrawable);
        if (paramBoolean)
          FileManager.writeIconToFileSystem(paramContext, paramInt, paramDrawable);
        ArrayList localArrayList2 = keys;
        Integer localInteger2 = Integer.valueOf(paramInt);
        localArrayList2.add(localInteger2);
        return;
      }
      int j = ((Integer)keys.get(i)).intValue();
      Hashtable localHashtable2 = cachedDrawables;
      Integer localInteger3 = Integer.valueOf(j);
      localHashtable2.remove(localInteger3);
      Integer localInteger4 = Integer.valueOf(j);
      localArrayList1.add(localInteger4);
      i += 1;
    }
  }

  public static Drawable getDefaultIcon(Context paramContext)
  {
    if (defaultIcon == null)
      defaultIcon = paramContext.getResources().getDrawable(2130837557);
    return defaultIcon;
  }

  public static Drawable lookupThumbnail(Context paramContext, int paramInt)
  {
    Hashtable localHashtable = cachedDrawables;
    return FileManager.lookupThumbnail(paramContext, paramInt, localHashtable);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.CachedThumbnails
 * JD-Core Version:    0.6.0
 */