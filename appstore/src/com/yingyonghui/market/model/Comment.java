package com.yingyonghui.market.model;

import dalvik.annotation.Signature;
import java.util.ArrayList;

public class Comment
{
  public static String TYPE_COMMENT = "comment";
  public static String TYPE_ERROR;
  public static String TYPE_REPLY = "reply";
  public String author;

  @Signature({"Ljava/util/ArrayList", "<", "Lcom/yingyonghui/market/model/Comment;", ">;"})
  public ArrayList children;
  public String comment;
  public int commentId;
  public long commentTime;
  public String deviceName;
  public int errorType;
  public boolean isDelete;
  public boolean isMine;
  public double rating;
  public String type;
  public int userId;

  static
  {
    TYPE_ERROR = "error";
  }

  public void addChild(Comment paramComment)
  {
    if (this.children == null)
    {
      ArrayList localArrayList = new ArrayList();
      this.children = localArrayList;
    }
    this.children.add(paramComment);
  }

  public Comment getChild(int paramInt)
  {
    return (Comment)this.children.get(paramInt);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.model.Comment
 * JD-Core Version:    0.6.0
 */