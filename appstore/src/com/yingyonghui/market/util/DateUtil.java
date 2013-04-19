package com.yingyonghui.market.util;

import dalvik.annotation.Signature;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class DateUtil
{
  public static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String ISO_DATETIME_FORMAT_SORT = "yyyy-MM-dd HH:mm:ss";
  public static final String ISO_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
  public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
  public static final String ISO_DATE_TIME_ZONE_FORMAT = "yyyy-MM-ddZZ";
  public static final String ISO_TIME_FORMAT = "'T'HH:mm:ss";
  public static final String ISO_TIME_NO_T_FORMAT = "HH:mm:ss";
  public static final String ISO_TIME_NO_T_TIME_ZONE_FORMAT = "HH:mm:ssZZ";
  public static final String ISO_TIME_TIME_ZONE_FORMAT = "'T'HH:mm:ssZZ";
  public static final long MILLIS_PER_DAY = 86400000L;
  public static final long MILLIS_PER_HOUR = 3600000L;
  public static final long MILLIS_PER_MINUTE = 60000L;
  public static final long MILLIS_PER_SECOND = 1000L;
  public static final String SMTP_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

  @Signature({"Ljava/util/concurrent/ConcurrentHashMap", "<", "Ljava/lang/String;", "Ljava/text/SimpleDateFormat;", ">;"})
  private static ConcurrentHashMap cInstanceCache = new ConcurrentHashMap();

  public static Date add(Date paramDate, int paramInt1, int paramInt2)
  {
    if (paramDate == null)
      throw new IllegalArgumentException("The date must not be null");
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(paramInt1, paramInt2);
    return localCalendar.getTime();
  }

  public static Date addDays(Date paramDate, int paramInt)
  {
    return add(paramDate, 5, paramInt);
  }

  public static Date addHours(Date paramDate, int paramInt)
  {
    return add(paramDate, 11, paramInt);
  }

  public static Date addMilliseconds(Date paramDate, int paramInt)
  {
    return add(paramDate, 14, paramInt);
  }

  public static Date addMinutes(Date paramDate, int paramInt)
  {
    return add(paramDate, 12, paramInt);
  }

  public static Date addMonths(Date paramDate, int paramInt)
  {
    return add(paramDate, 2, paramInt);
  }

  public static Date addSeconds(Date paramDate, int paramInt)
  {
    return add(paramDate, 13, paramInt);
  }

  public static Date addWeeks(Date paramDate, int paramInt)
  {
    return add(paramDate, 3, paramInt);
  }

  public static Date addYears(Date paramDate, int paramInt)
  {
    return add(paramDate, 1, paramInt);
  }

  public static String format(long paramLong, String paramString)
  {
    String paramString;
    return format(new Date(paramLong), ???, null);
  }

  public static String format(Date paramDate, String paramString)
  {
    return format(paramDate, paramString, null);
  }

  public static String format(Date paramDate, String paramString, Locale paramLocale)
  {
    String str1;
    if (paramDate == null)
    {
      str1 = "";
      return str1;
    }
    SimpleDateFormat localSimpleDateFormat;
    if (paramLocale == null)
    {
      localSimpleDateFormat = (SimpleDateFormat)cInstanceCache.get(paramString);
      if (localSimpleDateFormat == null)
      {
        localSimpleDateFormat = new SimpleDateFormat(paramString);
        cInstanceCache.put(paramString, localSimpleDateFormat);
      }
    }
    while (true)
    {
      str1 = localSimpleDateFormat.format(paramDate);
      break;
      ConcurrentHashMap localConcurrentHashMap1 = cInstanceCache;
      String str2 = String.valueOf(paramString);
      String str3 = str2 + paramLocale;
      localSimpleDateFormat = (SimpleDateFormat)localConcurrentHashMap1.get(str3);
      if (localSimpleDateFormat != null)
        continue;
      localSimpleDateFormat = new SimpleDateFormat(paramString, paramLocale);
      ConcurrentHashMap localConcurrentHashMap2 = cInstanceCache;
      String str4 = String.valueOf(paramString);
      String str5 = str4 + paramLocale;
      localConcurrentHashMap2.put(str5, localSimpleDateFormat);
    }
  }

  public static boolean isSameDay(Calendar paramCalendar1, Calendar paramCalendar2)
  {
    if ((paramCalendar1 == null) || (paramCalendar2 == null))
      throw new IllegalArgumentException("The date must not be null");
    int i = paramCalendar1.get(0);
    int j = paramCalendar2.get(0);
    if (i == j)
    {
      int k = paramCalendar1.get(1);
      int m = paramCalendar2.get(1);
      if (k == m)
      {
        int n = paramCalendar1.get(6);
        int i1 = paramCalendar2.get(6);
        if (n != i1);
      }
    }
    for (int i2 = 1; ; i2 = 0)
      return i2;
  }

  public static boolean isSameDay(Date paramDate1, Date paramDate2)
  {
    if ((paramDate1 == null) || (paramDate2 == null))
      throw new IllegalArgumentException("The date must not be null");
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTime(paramDate1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTime(paramDate2);
    return isSameDay(localCalendar1, localCalendar2);
  }

  public static Date parseDate(String paramString, String[] paramArrayOfString)
    throws ParseException
  {
    if ((paramString == null) || (paramArrayOfString == null))
      throw new IllegalArgumentException("Date and Patterns must not be null");
    SimpleDateFormat localSimpleDateFormat = null;
    ParsePosition localParsePosition = new ParsePosition(0);
    int i = 0;
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
      {
        String str1 = "Unable to parse the date: " + paramString;
        throw new ParseException(str1, -1);
      }
      if (i == 0)
      {
        String str2 = paramArrayOfString[0];
        localSimpleDateFormat = new SimpleDateFormat(str2);
      }
      while (true)
      {
        localParsePosition.setIndex(0);
        Date localDate = localSimpleDateFormat.parse(paramString, localParsePosition);
        if (localDate == null)
          break;
        int k = localParsePosition.getIndex();
        int m = paramString.length();
        if (k != m)
          break;
        return localDate;
        String str3 = paramArrayOfString[i];
        localSimpleDateFormat.applyPattern(str3);
      }
      i += 1;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.DateUtil
 * JD-Core Version:    0.6.0
 */