package com.yingyonghui.market.model;

import dalvik.annotation.Signature;
import java.util.ArrayList;
import java.util.List;

public class NewsContent
{
  public static final int SUB_APP = 2;
  public static final int SUB_IMAGE = 1;
  public static final int SUB_TEXT;
  private NewsHeader header = null;

  @Signature({"Ljava/util/List", "<", "Lcom/yingyonghui/market/model/NewsContent$SubNews;", ">;"})
  private List subNewsList = null;

  public void addSubNews(SubNews paramSubNews)
  {
    if (this.subNewsList == null)
    {
      ArrayList localArrayList = new ArrayList();
      this.subNewsList = localArrayList;
    }
    this.subNewsList.add(paramSubNews);
  }

  public void clearContent()
  {
    if (this.header != null)
      this.header = null;
    if (this.subNewsList != null);
    while (true)
    {
      if (this.subNewsList.size() <= 0)
      {
        this.subNewsList = null;
        return;
      }
      removeSubNews(0);
    }
  }

  public SubNews createSubNews()
  {
    return new SubNews();
  }

  public NewsHeader getHeader()
  {
    if (this.header == null)
    {
      NewsHeader localNewsHeader = new NewsHeader();
      this.header = localNewsHeader;
    }
    return this.header;
  }

  @Signature({"()", "Ljava/util/List", "<", "Lcom/yingyonghui/market/model/NewsContent$SubNews;", ">;"})
  public List getSubNewsList()
  {
    if (this.subNewsList == null)
    {
      ArrayList localArrayList = new ArrayList();
      this.subNewsList = localArrayList;
    }
    return this.subNewsList;
  }

  public void removeSubNews(int paramInt)
  {
    if ((this.subNewsList == null) || (this.subNewsList.size() <= paramInt))
      return;
    SubNews localSubNews = (SubNews)this.subNewsList.get(paramInt);
    if ((localSubNews != null) && (localSubNews.blocksList != null));
    while (true)
    {
      if (localSubNews.blocksList.size() <= 0)
      {
        localSubNews.blocksList = null;
        this.subNewsList.remove(paramInt);
        break;
      }
      localSubNews.blocksList.remove(0);
    }
  }

  public class NewsHeader
  {
    public String date;
    public boolean hot;
    public String title;
    public String url;
    public int visit;

    public NewsHeader()
    {
    }
  }

  public class SubApp extends NewsContent.SubItem
  {
    public int applicationId;

    public SubApp()
    {
      super();
      this.itemType = 2;
    }
  }

  public class SubImage extends NewsContent.SubItem
  {
    public String url;

    public SubImage()
    {
      super();
      this.itemType = 1;
    }
  }

  public class SubItem
  {
    public int itemType;

    public SubItem()
    {
    }
  }

  public class SubNews
  {

    @Signature({"Ljava/util/List", "<", "Lcom/yingyonghui/market/model/NewsContent$SubItem;", ">;"})
    public List blocksList;
    public String subTitle = "";

    public SubNews()
    {
      ArrayList localArrayList = new ArrayList();
      this.blocksList = localArrayList;
    }

    public void addSubApp(String paramString)
    {
      NewsContent localNewsContent = NewsContent.this;
      NewsContent.SubApp localSubApp = new NewsContent.SubApp(localNewsContent);
      int i = Integer.parseInt(paramString);
      localSubApp.applicationId = i;
      if (this.blocksList != null)
        this.blocksList.add(localSubApp);
    }

    public void addSubImage(String paramString)
    {
      NewsContent localNewsContent = NewsContent.this;
      NewsContent.SubImage localSubImage = new NewsContent.SubImage(localNewsContent);
      localSubImage.url = paramString;
      if (this.blocksList != null)
        this.blocksList.add(localSubImage);
    }

    public void addSubText(String paramString)
    {
      NewsContent localNewsContent = NewsContent.this;
      NewsContent.SubText localSubText = new NewsContent.SubText(localNewsContent);
      localSubText.text = paramString;
      if (this.blocksList != null)
        this.blocksList.add(localSubText);
    }
  }

  public class SubText extends NewsContent.SubItem
  {
    public String text;

    public SubText()
    {
      super();
      this.itemType = 0;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.model.NewsContent
 * JD-Core Version:    0.6.0
 */