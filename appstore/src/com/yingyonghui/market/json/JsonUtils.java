package com.yingyonghui.market.json;

import android.content.Context;
import android.text.TextUtils;
import com.yingyonghui.market.model.Asset;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.model.Category;
import com.yingyonghui.market.model.Comment;
import com.yingyonghui.market.model.Image;
import com.yingyonghui.market.model.NewsContent;
import com.yingyonghui.market.model.NewsContent.NewsHeader;
import com.yingyonghui.market.model.NewsContent.SubNews;
import com.yingyonghui.market.model.TestUser;
import com.yingyonghui.market.util.DateUtil;
import com.yingyonghui.market.util.PackageInstallInfo;
import com.yingyonghui.market.util.StringUtil;
import dalvik.annotation.Signature;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{
  public static AssetDetail getAssetDetailObject(Context paramContext, String paramString)
  {
    AssetDetail localAssetDetail = new AssetDetail();
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      int i = localJSONObject.getInt("applicationId");
      localAssetDetail._id = i;
      String str1 = localJSONObject.getString("applicationName");
      localAssetDetail.title = str1;
      String str2 = localJSONObject.getString("developer");
      localAssetDetail.author = str2;
      float f1 = (float)localJSONObject.getDouble("price");
      localAssetDetail.price = f1;
      float f2 = (float)localJSONObject.getDouble("rating");
      localAssetDetail.rating = f2;
      String str3 = localJSONObject.getString("packageName");
      localAssetDetail.pkgName = str3;
      String str4 = localJSONObject.getString("versionName");
      localAssetDetail.version = str4;
      String str5 = localJSONObject.getString("lastUpdate");
      localAssetDetail.lastUpdate = str5;
      int j = localJSONObject.optInt("versionCode");
      localAssetDetail.versionCode = j;
      String str6 = localAssetDetail.pkgName;
      int k = localAssetDetail.versionCode;
      int m = localAssetDetail._id;
      int n = PackageInstallInfo.getPackageInstalledStatus(paramContext, str6, k, m);
      localAssetDetail.installed = n;
      localAssetDetail.installed = n;
      int i1 = localJSONObject.getInt("size");
      localAssetDetail.size = i1;
      String str7 = localJSONObject.getString("devLogin");
      localAssetDetail.devLogin = str7;
      int i2 = localJSONObject.getInt("downloadCount");
      localAssetDetail.downloadsNumber = i2;
      int i3 = localJSONObject.getInt("ratingCount");
      localAssetDetail.ratingNumbers = i3;
      String str8 = localJSONObject.getString("description");
      localAssetDetail.description = str8;
      String str9 = localJSONObject.getString("supportEmail");
      localAssetDetail.email = str9;
      String str10 = localJSONObject.getString("supportPage");
      localAssetDetail.hostUrl = str10;
      String str11 = localJSONObject.getString("myComment");
      localAssetDetail.myComment = str11;
      if (localJSONObject.has("myCommentDate"))
      {
        long l = localJSONObject.getLong("myCommentDate");
        localAssetDetail.myCommentDate = l;
      }
      int i4 = (int)localJSONObject.getDouble("myRating");
      localAssetDetail.myRating = i4;
      JSONArray localJSONArray = localJSONObject.getJSONArray("permission");
      int i5 = localJSONArray.length();
      String[] arrayOfString1 = new String[i5];
      localAssetDetail.permissions = arrayOfString1;
      int i6 = 0;
      while (true)
      {
        if (i6 >= i5)
          return localAssetDetail;
        String[] arrayOfString2 = localAssetDetail.permissions;
        String str12 = localJSONArray.getString(i6);
        arrayOfString2[i6] = str12;
        i6 += 1;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  @Signature({"(", "Landroid/content/Context;", "Ljava/lang/String;", ")", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public static ArrayList getAssetObject(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().length() == 2));
    while (true)
    {
      return localArrayList;
      try
      {
        JSONArray localJSONArray1 = new JSONArray(paramString);
        int i = localJSONArray1.length();
        int j = 0;
        if (j >= i)
          continue;
        JSONObject localJSONObject = localJSONArray1.getJSONObject(j);
        Asset localAsset = new Asset();
        int k = localJSONObject.getInt("applicationId");
        localAsset._id = k;
        String str1 = localJSONObject.getString("applicationName");
        localAsset.title = str1;
        String str2 = localJSONObject.getString("developer");
        localAsset.author = str2;
        float f1 = (float)localJSONObject.getDouble("price");
        localAsset.price = f1;
        float f2 = (float)localJSONObject.getDouble("rating");
        localAsset.rating = f2;
        String str3 = localJSONObject.getString("packageName");
        localAsset.pkgName = str3;
        String str4 = localJSONObject.getString("versionName");
        localAsset.version = str4;
        int m = localJSONObject.optInt("versionCode");
        localAsset.versionCode = m;
        int n = localJSONObject.getInt("size");
        localAsset.size = n;
        if (localJSONObject.has("shortDescription"))
        {
          String str5 = localJSONObject.getString("shortDescription");
          localAsset.shortDescription = str5;
        }
        if (localJSONObject.has("lastModifiedTime"))
        {
          String str6 = localJSONObject.getString("lastModifiedTime");
          localAsset.lastUpdate = str6;
        }
        JSONArray localJSONArray2;
        int i1;
        int i2;
        if (localJSONObject.has("permission"))
        {
          localJSONArray2 = localJSONObject.getJSONArray("permission");
          i1 = localJSONArray2.length();
          String[] arrayOfString1 = new String[i1];
          localAsset.permissions = arrayOfString1;
          i2 = 0;
        }
        while (true)
        {
          if (i2 >= i1)
          {
            if (localAsset.pkgName != null)
              localArrayList.add(localAsset);
            j += 1;
            break;
          }
          String[] arrayOfString2 = localAsset.permissions;
          String str7 = localJSONArray2.getString(i2);
          arrayOfString2[i2] = str7;
          i2 += 1;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  @Signature({"(", "Landroid/content/Context;", "Ljava/lang/String;", "Z)", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public static ArrayList getAssetObject(Context paramContext, String paramString, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().length() == 2))
      return localArrayList;
    while (true)
    {
      Asset localAsset;
      JSONArray localJSONArray2;
      int n;
      try
      {
        JSONArray localJSONArray1 = new JSONArray(paramString);
        int i = localJSONArray1.length();
        int j = 0;
        if (j >= i)
          break;
        JSONObject localJSONObject = localJSONArray1.getJSONObject(j);
        localAsset = new Asset();
        if (!localJSONObject.has("exist"))
          continue;
        localAsset.exist = 0;
        int k = localJSONObject.getInt("applicationId");
        localAsset._id = k;
        String str1 = localJSONObject.getString("packageName");
        localAsset.pkgName = str1;
        if ((!paramBoolean) || (!localJSONObject.has("shortDescription")))
          continue;
        String str2 = localJSONObject.getString("shortDescription");
        localAsset.shortDescription = str2;
        if (!localJSONObject.has("permission"))
          continue;
        localJSONArray2 = localJSONObject.getJSONArray("permission");
        int m = localJSONArray2.length();
        String[] arrayOfString1 = new String[m];
        localAsset.permissions = arrayOfString1;
        n = 0;
        if (n < m)
          break label353;
        if (localAsset.pkgName == null)
          continue;
        localArrayList.add(localAsset);
        j += 1;
        continue;
        String str3 = localJSONObject.getString("applicationName");
        localAsset.title = str3;
        String str4 = localJSONObject.getString("developer");
        localAsset.author = str4;
        float f1 = (float)localJSONObject.getDouble("price");
        localAsset.price = f1;
        float f2 = (float)localJSONObject.getDouble("rating");
        localAsset.rating = f2;
        String str5 = localJSONObject.getString("versionName");
        localAsset.version = str5;
        int i1 = localJSONObject.optInt("versionCode");
        localAsset.versionCode = i1;
        int i2 = localJSONObject.getInt("size");
        localAsset.size = i2;
        localAsset.exist = 1;
        continue;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      break;
      label353: String[] arrayOfString2 = localAsset.permissions;
      String str6 = localJSONArray2.getString(n);
      arrayOfString2[n] = str6;
      n += 1;
    }
  }

  @Signature({"(", "Landroid/content/Context;", "Ljava/lang/String;", ")", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Asset;", ">;"})
  public static ArrayList getAssetObjectWithBanner(Context paramContext, String paramString)
  {
    paramContext = new ArrayList();
    if ((paramString == null) || (paramString.trim().length() == 2));
    label90: Object localObject1;
    Asset localAsset;
    while (true)
    {
      return paramContext;
      try
      {
        JSONArray localJSONArray1 = new JSONArray(paramString);
        int i = localJSONArray1.length();
        int j = 0;
        if (j >= 2)
          continue;
        JSONArray localJSONArray2 = localJSONArray1.getJSONArray(j);
        if (j == 0);
        int m;
        for (int k = 4; ; k = 10)
        {
          m = 0;
          if (m < k)
            break label90;
          j += 1;
          break;
        }
        localObject1 = localJSONArray2.getJSONObject(m);
        localAsset = new Asset();
        String str7;
        if (!((JSONObject)localObject1).has("type"))
        {
          int n = ((JSONObject)localObject1).getInt("applicationId");
          localAsset._id = n;
          String str1 = ((JSONObject)localObject1).getString("applicationName");
          localAsset.title = str1;
          String str2 = ((JSONObject)localObject1).getString("developer");
          localAsset.author = str2;
          float f1 = (float)((JSONObject)localObject1).getDouble("price");
          localAsset.price = f1;
          float f2 = (float)((JSONObject)localObject1).getDouble("rating");
          localAsset.rating = f2;
          String str3 = ((JSONObject)localObject1).getString("packageName");
          localAsset.pkgName = str3;
          String str4 = ((JSONObject)localObject1).getString("versionName");
          localAsset.version = str4;
          int i1 = ((JSONObject)localObject1).optInt("versionCode");
          localAsset.versionCode = i1;
          int i2 = ((JSONObject)localObject1).getInt("size");
          localAsset.size = i2;
          if (((JSONObject)localObject1).has("shortDescription"))
          {
            String str5 = ((JSONObject)localObject1).getString("shortDescription");
            localAsset.shortDescription = str5;
          }
          if (((JSONObject)localObject1).has("lastModifiedTime"))
          {
            String str6 = ((JSONObject)localObject1).getString("lastModifiedTime");
            localAsset.lastUpdate = str6;
          }
          if (((JSONObject)localObject1).has("permission"))
          {
            localObject1 = ((JSONObject)localObject1).getJSONArray("permission");
            paramString = ((JSONArray)localObject1).length();
            String[] arrayOfString1 = new String[paramString];
            localAsset.permissions = arrayOfString1;
            str7 = 0;
            label357: if (str7 < paramString);
          }
          else if (localAsset.pkgName != null)
          {
            paramContext.add(localAsset);
          }
        }
        while (true)
        {
          m += 1;
          break;
          String[] arrayOfString2 = localAsset.permissions;
          String str9 = ((JSONArray)localObject1).getString(str7);
          arrayOfString2[str7] = str9;
          int i3;
          str7 += 1;
          break label357;
          if (((JSONObject)localObject1).getInt("type") != 1)
            break label485;
          int i5 = ((JSONObject)localObject1).getInt("id");
          localAsset._id = i5;
          String str10 = ((JSONObject)localObject1).getString("name");
          localAsset.title = str10;
          localAsset.pkgName = "jumptotheme";
          paramContext.add(localAsset);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    label485: paramString = ((JSONObject)localObject1).getString("detailinfo");
    Object localObject2 = null;
    if ((!"null".equals(paramString)) && (paramString != null));
    int i4;
    for (paramString = ((JSONObject)localObject1).getJSONObject("detailinfo"); ; paramString = i4)
    {
      if (paramString != null)
      {
        int i6 = paramString.getInt("applicationId");
        localAsset._id = i6;
        String str11 = paramString.getString("applicationName");
        localAsset.title = str11;
        String str12 = paramString.getString("developer");
        localAsset.author = str12;
        float f3 = (float)paramString.getDouble("price");
        localAsset.price = f3;
        float f4 = (float)paramString.getDouble("rating");
        localAsset.rating = f4;
        String str13 = paramString.getString("packageName");
        localAsset.pkgName = str13;
        String str14 = paramString.getString("versionName");
        localAsset.version = str14;
        int i7 = paramString.optInt("versionCode");
        localAsset.versionCode = i7;
        int i8 = paramString.getInt("size");
        localAsset.size = i8;
        if (paramString.has("shortDescription"))
        {
          String str15 = paramString.getString("shortDescription");
          localAsset.shortDescription = str15;
        }
        if (paramString.has("lastModifiedTime"))
        {
          String str16 = paramString.getString("lastModifiedTime");
          localAsset.lastUpdate = str16;
        }
        String str8;
        if (paramString.has("permission"))
        {
          localObject1 = paramString.getJSONArray("permission");
          paramString = ((JSONArray)localObject1).length();
          String[] arrayOfString3 = new String[paramString];
          localAsset.permissions = arrayOfString3;
          str8 = 0;
        }
        while (true)
        {
          if (str8 >= paramString)
          {
            if (localAsset.pkgName == null)
              break;
            paramContext.add(localAsset);
            break;
          }
          String[] arrayOfString4 = localAsset.permissions;
          String str17 = ((JSONArray)localObject1).getString(str8);
          arrayOfString4[str8] = str17;
          str8 += 1;
        }
      }
      localAsset._id = -1;
      paramContext.add(localAsset);
      break;
    }
  }

  @Signature({"(", "Ljava/lang/String;", ")", "Ljava/util/Vector", "<", "Lcom/yingyonghui/market/model/Category;", ">;"})
  public static Vector getCategoryObject(String paramString)
  {
    Vector localVector = new Vector();
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString);
      int i = localJSONArray.length();
      int j = 0;
      while (true)
      {
        if (j >= i)
          return localVector;
        JSONObject localJSONObject = localJSONArray.getJSONObject(j);
        Category localCategory = new Category();
        int k = localJSONObject.getInt("categoryId");
        localCategory._id = k;
        String str = localJSONObject.getString("categoryName");
        localCategory.cat_name = str;
        int m = localJSONObject.getInt("topAppId");
        localCategory.topId = m;
        localVector.add(localCategory);
        j += 1;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public static Object[] getCommentObject(String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    ArrayList localArrayList = new ArrayList();
    arrayOfObject[1] = localArrayList;
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString);
      Integer localInteger = Integer.valueOf(localJSONObject1.getInt("totalCount"));
      arrayOfObject[0] = localInteger;
      JSONArray localJSONArray = localJSONObject1.getJSONArray("list");
      int i = localJSONArray.length();
      localObject = null;
      int j = 0;
      if (j >= i)
        return arrayOfObject;
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(j);
      localComment1 = new Comment();
      int k = localJSONObject2.getInt("commentId");
      localComment1.commentId = k;
      String str1 = localJSONObject2.getString("deviceName");
      localComment1.deviceName = str1;
      boolean bool1 = localJSONObject2.getBoolean("isdelete");
      localComment1.isDelete = bool1;
      String str2 = localJSONObject2.getString("message");
      localComment1.comment = str2;
      long l = localJSONObject2.getLong("commentTime");
      localComment1.commentTime = l;
      String str3 = localJSONObject2.getString("type");
      localComment1.type = str3;
      int m = localJSONObject2.getInt("userId");
      localComment1.userId = m;
      String str4 = localJSONObject2.getString("userName");
      localComment1.author = str4;
      boolean bool2 = localJSONObject2.getBoolean("isMine");
      localComment1.isMine = bool2;
      String str5 = localComment1.type;
      String str6 = Comment.TYPE_COMMENT;
      if (StringUtil.equals(str5, str6))
      {
        double d = localJSONObject2.getDouble("score");
        localComment1.rating = d;
      }
      while (true)
      {
        String str7 = localComment1.type;
        String str8 = Comment.TYPE_COMMENT;
        if (!StringUtil.equals(str7, str8))
          break label373;
        Comment localComment2 = localComment1;
        localArrayList.add(localComment1);
        j += 1;
        break;
        String str9 = localComment1.type;
        String str10 = Comment.TYPE_ERROR;
        if (!StringUtil.equals(str9, str10))
          continue;
        int n = localJSONObject2.getInt("errorType");
        localComment1.errorType = n;
      }
    }
    catch (JSONException str13)
    {
      while (true)
      {
        Object localObject;
        Comment localComment1;
        localJSONException.printStackTrace();
        continue;
        label373: String str11 = localComment1.type;
        String str12 = Comment.TYPE_ERROR;
        if (StringUtil.equals(str11, str12))
        {
          Comment localComment3 = localComment1;
          localArrayList.add(localComment1);
          continue;
        }
        String str13 = localComment1.type;
        String str14 = Comment.TYPE_REPLY;
        if (!StringUtil.equals(str13, str14))
          continue;
        localObject.addChild(localComment1);
      }
    }
  }

  @Signature({"(", "Ljava/lang/String;", ")", "Ljava/util/Vector", "<", "Lcom/yingyonghui/market/model/Image;", ">;"})
  public static Vector getImageObject(String paramString)
  {
    Vector localVector = new Vector();
    try
    {
      JSONArray localJSONArray = new JSONArray(paramString);
      int i = localJSONArray.length();
      int j = 0;
      while (true)
      {
        if (j >= i)
          return localVector;
        JSONObject localJSONObject = localJSONArray.getJSONObject(j);
        Image localImage = new Image();
        int k = localJSONObject.getInt("applicationId");
        localImage._id = k;
        String str = localJSONObject.getString("base64Image");
        localImage.base64Image = str;
        localVector.add(localImage);
        j += 1;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  @Signature({"(", "Ljava/lang/String;", ")", "Ljava/util/ArrayList", "<", "Ljava/lang/String;", ">;"})
  public static ArrayList getKeywordObject(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      int i = paramString.length() - 2;
      String[] arrayOfString = paramString.substring(1, i).split(",");
      int j = 0;
      while (true)
      {
        int k = arrayOfString.length;
        if (j >= k)
          return localArrayList;
        String str = arrayOfString[j].replace("\"", "");
        localArrayList.add(str);
        j += 1;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  public static NewsContent getNewsContentObject(String paramString)
  {
    NewsContent localNewsContent = new NewsContent();
    Object localObject3;
    NewsContent.SubNews localSubNews1;
    JSONArray localJSONArray;
    while (true)
    {
      Object localObject1;
      JSONObject localJSONObject1;
      try
      {
        localObject1 = new JSONObject(paramString);
        localObject2 = ((JSONObject)localObject1).getJSONObject("newsheader");
        localObject3 = localNewsContent.getHeader();
        String str1 = ((JSONObject)localObject2).getString("news_url");
        ((NewsContent.NewsHeader)localObject3).url = str1;
        boolean bool1 = ((JSONObject)localObject2).getBoolean("hot");
        ((NewsContent.NewsHeader)localObject3).hot = bool1;
        paramString = ((JSONObject)localObject2).getString("date");
        boolean bool2 = TextUtils.isEmpty(paramString);
        if (bool2)
          continue;
        int k = 1;
        try
        {
          String[] arrayOfString = new String[k];
          arrayOfString[0] = "yyyyMMdd";
          paramString = DateUtil.format(DateUtil.parseDate(paramString, arrayOfString), "yyyy-MM-dd");
          ((NewsContent.NewsHeader)localObject3).date = paramString;
          String str2 = ((JSONObject)localObject2).getString("title");
          ((NewsContent.NewsHeader)localObject3).title = str2;
          int m = ((JSONObject)localObject2).getInt("visit");
          ((NewsContent.NewsHeader)localObject3).visit = m;
          localObject2 = ((JSONObject)localObject1).getJSONArray("subnews");
          int n = ((JSONArray)localObject2).length();
          localObject3 = null;
          Object localObject4 = null;
          int ? = 0;
          localObject1 = localObject4;
          if (localObject1 >= n)
            return localNewsContent;
        }
        catch (ParseException localParseException)
        {
          localParseException.printStackTrace();
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        Object localObject2;
        localJSONException.printStackTrace();
        continue;
        localJSONObject1 = ((JSONArray)localObject2).getJSONObject(localObject1);
        localSubNews1 = localNewsContent.createSubNews();
        String str3 = localJSONObject1.getString("subtitle");
        localSubNews1.subTitle = str3;
        localJSONArray = localJSONObject1.getJSONArray("blocks");
        localObject5 = localObject3;
        localObject3 = null;
      }
      int i1 = localJSONArray.length();
      if (localObject3 < i1)
        break;
      localNewsContent.addSubNews(localSubNews1);
      int i = localObject1 + 1;
      localObject3 = localObject5;
      NewsContent.SubNews localSubNews2 = localSubNews1;
      JSONObject localJSONObject2 = localJSONObject1;
    }
    Object localObject5 = localJSONArray.getJSONObject(localObject3);
    String str4 = ((JSONObject)localObject5).getString("type");
    paramString = ((JSONObject)localObject5).getString("content");
    if (str4.equals("text"))
      localSubNews1.addSubText(paramString);
    while (true)
    {
      int j = localObject3 + 1;
      break;
      if (str4.equals("image"))
      {
        localSubNews1.addSubImage(paramString);
        continue;
      }
      if (!str4.equals("package_id"))
        continue;
      localSubNews1.addSubApp(paramString);
    }
  }

  // ERROR //
  @Signature({"(", "Ljava/lang/String;", ")", "Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/News;", ">;"})
  public static ArrayList getNewsListObject(String paramString)
  {
    // Byte code:
    //   0: new 179	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 180	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: new 18	org/json/JSONObject
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 21	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   18: ldc_w 477
    //   21: invokevirtual 148	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   24: astore_3
    //   25: aload_3
    //   26: invokevirtual 154	org/json/JSONArray:length	()I
    //   29: istore 4
    //   31: iconst_0
    //   32: istore 5
    //   34: aconst_null
    //   35: astore 6
    //   37: iload 5
    //   39: iload 4
    //   41: if_icmplt +9 -> 50
    //   44: aload 6
    //   46: astore 7
    //   48: aload_1
    //   49: areturn
    //   50: aload_3
    //   51: iload 5
    //   53: invokevirtual 190	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   56: astore 8
    //   58: new 479	com/yingyonghui/market/model/News
    //   61: dup
    //   62: invokespecial 480	com/yingyonghui/market/model/News:<init>	()V
    //   65: astore_2
    //   66: aload 8
    //   68: ldc_w 397
    //   71: invokevirtual 37	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   74: astore 9
    //   76: aload_2
    //   77: aload 9
    //   79: putfield 481	com/yingyonghui/market/model/News:url	Ljava/lang/String;
    //   82: aload 8
    //   84: ldc_w 404
    //   87: invokevirtual 297	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   90: istore 10
    //   92: aload_2
    //   93: iload 10
    //   95: putfield 482	com/yingyonghui/market/model/News:hot	Z
    //   98: aload 8
    //   100: ldc_w 431
    //   103: invokevirtual 37	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   106: astore 11
    //   108: aload_2
    //   109: aload 11
    //   111: putfield 483	com/yingyonghui/market/model/News:title	Ljava/lang/String;
    //   114: aload 8
    //   116: ldc_w 408
    //   119: invokevirtual 37	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   122: astore 12
    //   124: aload 12
    //   126: invokestatic 414	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   129: istore 13
    //   131: iload 13
    //   133: ifne +36 -> 169
    //   136: ldc 223
    //   138: istore 13
    //   140: iload 13
    //   142: anewarray 156	java/lang/String
    //   145: astore 14
    //   147: aload 14
    //   149: iconst_0
    //   150: ldc_w 416
    //   153: aastore
    //   154: aload 12
    //   156: aload 14
    //   158: invokestatic 422	com/yingyonghui/market/util/DateUtil:parseDate	(Ljava/lang/String;[Ljava/lang/String;)Ljava/util/Date;
    //   161: ldc_w 424
    //   164: invokestatic 428	com/yingyonghui/market/util/DateUtil:format	(Ljava/util/Date;Ljava/lang/String;)Ljava/lang/String;
    //   167: astore 12
    //   169: aload_2
    //   170: aload 12
    //   172: putfield 486	com/yingyonghui/market/model/News:releaseDate	Ljava/lang/String;
    //   175: aload 8
    //   177: ldc_w 434
    //   180: invokevirtual 27	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   183: istore 15
    //   185: aload_2
    //   186: iload 15
    //   188: putfield 489	com/yingyonghui/market/model/News:visits	I
    //   191: aload 8
    //   193: ldc_w 491
    //   196: invokevirtual 37	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   199: astore 16
    //   201: aload_2
    //   202: aload 16
    //   204: putfield 494	com/yingyonghui/market/model/News:thumb_url	Ljava/lang/String;
    //   207: aload_1
    //   208: aload_2
    //   209: invokevirtual 214	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   212: pop
    //   213: iload 5
    //   215: iconst_1
    //   216: iadd
    //   217: istore 5
    //   219: aload_2
    //   220: astore 6
    //   222: goto -185 -> 37
    //   225: invokevirtual 439	java/text/ParseException:printStackTrace	()V
    //   228: goto -59 -> 169
    //   231: astore 17
    //   233: aload 17
    //   235: invokevirtual 166	org/json/JSONException:printStackTrace	()V
    //   238: goto -190 -> 48
    //   241: astore 17
    //   243: aload 6
    //   245: astore 18
    //   247: goto -14 -> 233
    //
    // Exception table:
    //   from	to	target	type
    //   140	169	225	java/text/ParseException
    //   10	31	231	org/json/JSONException
    //   66	131	231	org/json/JSONException
    //   140	169	231	org/json/JSONException
    //   169	228	231	org/json/JSONException
    //   50	66	241	org/json/JSONException
  }

  public static int getSnapshortCount(String paramString)
  {
    try
    {
      i = new JSONObject(paramString).getInt("size");
      return i;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        int i = 0;
      }
    }
  }

  public static TestUser getTestUserObject(Context paramContext, String paramString)
  {
    TestUser localTestUser = new TestUser();
    try
    {
      boolean bool = new JSONObject(paramString).getBoolean("a");
      localTestUser.mIsTestUser = bool;
      return localTestUser;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public static boolean isConnectKeyExpired(String paramString)
  {
    if (paramString.startsWith("{"));
    while (true)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if (localJSONObject.has("code"))
        {
          int i = localJSONObject.getInt("code");
          if (i != 2000)
            continue;
          j = 1;
          return j;
          j = 0;
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      int j = 0;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.json.JsonUtils
 * JD-Core Version:    0.6.0
 */