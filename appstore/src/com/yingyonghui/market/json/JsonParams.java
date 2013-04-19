package com.yingyonghui.market.json;

import com.yingyonghui.market.util.StringUtil;

public class JsonParams
{
  public static String getAppCategoryParams(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    return "{type:category,sdkVersion:" + paramInt + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getAppCategoryParams2(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3)
  {
    return "{type:category2,sdkVersion:" + paramInt1 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",parent:\"" + paramInt2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getAppCommentsParams(int paramInt1, int paramInt2, int paramInt3, String paramString1, int paramInt4, String paramString2)
  {
    return "{type:comment,version:2,applicationId:" + paramInt1 + ",start:" + paramInt2 + ",size:" + paramInt3 + ",clientVersion:" + paramString1 + ",osVersion:" + paramInt4 + ",deviceId:\"" + paramString2 + "\"}";
  }

  public static String getAppDetailParams(int paramInt, String paramString1, String paramString2)
  {
    return "{type:appdetail,applicationId:" + paramInt + ",deviceId:\"" + paramString1 + "\",clientVersion:\"" + paramString2 + "\"}";
  }

  public static String getAppLikeListParams(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3)
  {
    String str1 = "";
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      String str2 = String.valueOf("");
      str1 = str2 + "[";
      i = 0;
    }
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
      {
        String str3 = String.valueOf(str1);
        str1 = str3 + "]";
        return "{type:guessyoulike,sdkVersion:" + paramInt3 + ",packages:" + str1 + ",start:" + paramInt1 + ",size:" + paramInt2 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
      }
      String str4 = paramArrayOfString[i];
      String str5 = str4;
      String str6 = "";
      int k = str4.indexOf(';');
      if (k != -1)
      {
        str5 = str4.substring(0, k);
        int m = k + 1;
        str6 = str4.substring(m);
      }
      if (i != 0)
      {
        String str7 = String.valueOf(str1);
        str1 = str7 + ",";
      }
      String str8 = String.valueOf(str1);
      String str9 = String.valueOf(str8 + "{");
      String str10 = String.valueOf(str9 + "\"package\"");
      String str11 = String.valueOf(str10 + ":");
      String str12 = String.valueOf(str11 + "\"");
      String str13 = String.valueOf(str12 + str5);
      String str14 = String.valueOf(str13 + "\",\"versionCode\":\"" + str6 + "\"");
      str1 = str14 + "}";
      i += 1;
    }
  }

  public static String getAppListParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2, String paramString3)
  {
    return "{type:appbycategory,sdkVersion:" + paramInt4 + ",categoryId:" + paramInt1 + ",start:" + paramInt2 + ",size:" + paramInt3 + ",sortType:" + paramInt5 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getAppRankingListParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2, String paramString3)
  {
    return "{type:appbycategory,sdkVersion:" + paramInt5 + ",categoryId:" + "1" + ",period:" + paramInt2 + ",start:" + paramInt3 + ",size:" + paramInt4 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getAppRelativeListParams(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2, String paramString3, String paramString4)
  {
    return "{type:relatedapk,sdkVersion:" + paramInt3 + ",packageName:" + paramString1 + ",start:" + paramInt1 + ",size:" + paramInt2 + ",resolution:\"" + paramString2 + "\",hardware:\"" + paramString3 + "\",clientVersion:\"" + paramString4 + "\"}";
  }

  public static String getAppSnapsizeParams(int paramInt, String paramString)
  {
    return "{type:snapshotsize,applicationId:" + paramInt + ",clientVersion:" + paramString + "}";
  }

  public static String getBannerAndAppListParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2, String paramString3)
  {
    return "{type:bannerwithfeatured,sdkVersion:" + paramInt4 + ",categoryId:" + paramInt1 + ",start:" + paramInt2 + ",size:" + paramInt3 + ",sortType:" + paramInt5 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getDeleteCommentParams(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    return "{type:delcomment,commentId:" + paramInt + ",clientVersion:" + paramString1 + ",deviceId:\"" + paramString3 + "\",deviceName:" + paramString2 + "}";
  }

  public static String getDowloadsParams(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2, String paramString3)
  {
    String str1 = "";
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      String str2 = String.valueOf("");
      str1 = str2 + "[";
      i = 0;
    }
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
      {
        String str3 = String.valueOf(str1);
        str1 = str3 + "]";
        return "{type:downhistory,sdkVersion:" + paramInt4 + ",packages:" + str1 + ",userId:" + paramInt1 + ",start:" + paramInt2 + ",size:" + paramInt3 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\",allPackage:true}";
      }
      String str4 = paramArrayOfString[i];
      String str5 = str4;
      String str6 = "";
      int k = str4.indexOf(';');
      if (k != -1)
      {
        str5 = str4.substring(0, k);
        int m = k + 1;
        str6 = str4.substring(m);
      }
      if (i != 0)
      {
        String str7 = String.valueOf(str1);
        str1 = str7 + ",";
      }
      String str8 = String.valueOf(str1);
      String str9 = String.valueOf(str8 + "{");
      String str10 = String.valueOf(str9 + "\"package\"");
      String str11 = String.valueOf(str10 + ":");
      String str12 = String.valueOf(str11 + "\"");
      String str13 = String.valueOf(str12 + str5);
      String str14 = String.valueOf(str13 + "\",\"versionCode\":\"" + str6 + "\"");
      str1 = str14 + "}";
      i += 1;
    }
  }

  public static String getInstallLogParams(String paramString1, String paramString2, String paramString3)
  {
    return "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:install_market,channel:" + paramString3 + "}}";
  }

  public static String getInstallUpdateLogParams(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    String str1 = "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:" + paramString3 + ",status:" + paramInt1 + ",data:{fromPage:\"" + paramString4 + "\"";
    if (paramInt5 != 55537)
    {
      String str2 = String.valueOf(str1);
      str1 = str2 + ",downloadAppId:" + paramInt5;
    }
    if (paramInt2 != 55537)
    {
      String str3 = String.valueOf(str1);
      str1 = str3 + ",categoryId:" + paramInt2;
    }
    if (paramInt3 != 55537)
    {
      String str4 = String.valueOf(str1);
      str1 = str4 + ",rankingType:" + paramInt3;
    }
    if (paramInt4 != 55537)
    {
      String str5 = String.valueOf(str1);
      str1 = str5 + ",periodType:" + paramInt4;
    }
    String str6 = String.valueOf(str1);
    return str6 + "}}}";
  }

  public static String getNewsContentParams(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    return "{type:newsContent,newsUrl:\"" + paramString1 + "\",sdkVersion:" + paramInt + ",resolution:\"" + paramString2 + "\",hardware:\"" + paramString3 + "\",clientVersion:\"" + paramString4 + "\"}";
  }

  public static String getNewsListParams(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, int paramInt3)
  {
    return "{type:newsList,sdkVersion:" + paramInt1 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\",start:" + paramInt2 + ",size:" + paramInt3 + "}";
  }

  public static String getNewsPageViewLogParams(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    return "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:pageview,status:" + paramInt1 + ",data:{fromPage:\"" + paramString3 + "\",news:" + paramInt2 + "}}}";
  }

  public static String getPageViewLogParams(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    String str1 = "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:pageview,status:" + paramInt1 + ",data:{fromPage:\"" + paramString3 + "\"";
    if (paramInt5 != 55537)
    {
      String str2 = String.valueOf(str1);
      str1 = str2 + ",ViewAppId:" + paramInt5;
    }
    if (paramInt2 != 55537)
    {
      String str3 = String.valueOf(str1);
      str1 = str3 + ",categoryId:" + paramInt2;
    }
    if (paramInt3 != 55537)
    {
      String str4 = String.valueOf(str1);
      str1 = str4 + ",rankingType:" + paramInt3;
    }
    if (paramInt4 != 55537)
    {
      String str5 = String.valueOf(str1);
      str1 = str5 + ",periodType:" + paramInt4;
    }
    String str6 = String.valueOf(str1);
    return str6 + "}}}";
  }

  public static String getReportErrorParams(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder("{type:addcomment,version:2,commentType:2,applicationId:").append(paramInt1).append(",errorType:").append(paramInt2).append(",clientVersion:").append(paramString1).append(",deviceId:\"").append(paramString3).append("\",deviceName:").append(paramString2).append(",addcmtversion:1").append(",commentText:\"");
    String str = StringUtil.escapeJavaScript(paramString4);
    return str + "\"}";
  }

  public static String getSearchByDeveloperParams(String paramString1, int paramInt1, int paramInt2, String paramString2, int paramInt3, String paramString3, String paramString4)
  {
    return "{type:appbydeveloper,developer:\"" + paramString2 + "\",devLogin:\"" + paramString1 + "\",start:" + paramInt1 + ",size:" + paramInt2 + ",sdkVersion:" + paramInt3 + ",resolution:\"" + paramString3 + "\",clientVersion:\"" + paramString4 + "\"}";
  }

  public static String getSearchByKeywordsParams(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2, String paramString3)
  {
    return "{type:appbykeywords,keywords:\"" + paramString1 + "\",start:" + paramInt1 + ",size:" + paramInt2 + ",sdkVersion:" + paramInt3 + ",resolution:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getSearchByPkgnameParams(String paramString1, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, String paramString2, String paramString3)
  {
    return "{type:appbypackage,packageName:\"" + paramString1 + "\",start:" + paramInt1 + ",size:" + paramInt2 + ",exactMatch:" + paramBoolean + ",sdkVersion:" + paramInt3 + ",resolution:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
  }

  public static String getSearchLogParams(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:search,data:{frompage:\"" + paramString4 + "\",keywords:" + paramString3 + "}}}";
  }

  public static String getSendCommentParams(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return "{type:addcomment,version:2,commentType:1,applicationId:" + paramInt1 + ",commentText:\"" + paramString1 + "\",score:" + paramInt2 + ", deviceId:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\",deviceName:\"" + paramString4 + "\",osVersion:" + paramString5 + "}";
  }

  public static String getSendCommentReplyParams(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return "{type:addcomment,version:2,commentType:3,applicationId:" + paramInt2 + ",commentText:\"" + paramString1 + "\",score:" + paramInt3 + ", deviceId:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\",deviceName:\"" + paramString4 + "\",osVersion:" + paramString5 + ",parentId:" + paramInt1 + "}";
  }

  public static String getTestUserParams()
  {
    return "{type:getFlags}";
  }

  public static String getTopKeywordsParams(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    return "{type:top_searchword,start:" + paramInt1 + ",size:" + paramInt2 + ",period:" + paramInt3 + ",clientVersion:" + paramString + "}";
  }

  public static String getUpdateAvaliableNumParams(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    String str1 = "";
    int i;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      String str2 = String.valueOf("");
      str1 = str2 + "[";
      i = 0;
    }
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
      {
        String str3 = String.valueOf(str1);
        str1 = str3 + "]";
        return "{type:checkupdatecount,sdkVersion:" + paramInt2 + ",packages:" + str1 + ",userId:" + paramInt1 + ",resolution:\"" + paramString1 + "\",hardware:\"" + paramString2 + "\",clientVersion:\"" + paramString3 + "\"}";
      }
      String str4 = paramArrayOfString[i];
      String str5 = str4;
      String str6 = "";
      int k = str4.indexOf(';');
      if (k != -1)
      {
        str5 = str4.substring(0, k);
        int m = k + 1;
        str6 = str4.substring(m);
      }
      if (i != 0)
      {
        String str7 = String.valueOf(str1);
        str1 = str7 + ",";
      }
      String str8 = String.valueOf(str1);
      String str9 = String.valueOf(str8 + "{");
      String str10 = String.valueOf(str9 + "\"package\"");
      String str11 = String.valueOf(str10 + ":");
      String str12 = String.valueOf(str11 + "\"");
      String str13 = String.valueOf(str12 + str5);
      String str14 = String.valueOf(str13 + "\",\"versionCode\":\"" + str6 + "\"");
      str1 = str14 + "}";
      i += 1;
    }
  }

  public static String getWidgetDisableLogParams(String paramString1, String paramString2)
  {
    return "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:widget_disable}}";
  }

  public static String getWidgetEnableLogParams(String paramString1, String paramString2)
  {
    return "{type:log,deviceId:\"" + paramString1 + "\",userIp:" + paramString2 + ",applicationId:" + 9999 + ",event:{type:widget_enable}}";
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.json.JsonParams
 * JD-Core Version:    0.6.0
 */