package com.yingyonghui.market;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.yingyonghui.market.model.Comment;

class CommentsActivity$ReviewAdapter$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    String[] arrayOfString = ((String)paramView.getTag()).split("-");
    Comment localComment1;
    int j;
    if (arrayOfString != null)
    {
      if (arrayOfString.length != 3)
        break label295;
      CommentsActivity.ReviewAdapter localReviewAdapter1 = this.this$1;
      int i = Integer.parseInt(arrayOfString[1]);
      localComment1 = (Comment)localReviewAdapter1.getItem(i);
      j = Integer.parseInt(arrayOfString[2]);
    }
    label295: CommentsActivity.ReviewAdapter localReviewAdapter2;
    int i1;
    for (Comment localComment2 = localComment1.getChild(j); ; localComment2 = (Comment)localReviewAdapter2.getItem(i1))
    {
      CommentsActivity localCommentsActivity = CommentsActivity.ReviewAdapter.access$0(this.this$1);
      Intent localIntent = new Intent(localCommentsActivity, ReplyCommentActivity.class);
      int k = CommentsActivity.access$13(CommentsActivity.ReviewAdapter.access$0(this.this$1));
      localIntent.putExtra("_id", k);
      String str1 = localComment2.author;
      localIntent.putExtra("author", str1);
      String str2 = localComment2.deviceName;
      localIntent.putExtra("deviceName", str2);
      double d = localComment2.rating;
      localIntent.putExtra("rating", d);
      long l = localComment2.commentTime;
      localIntent.putExtra("commentTime", l);
      String str3 = localComment2.type;
      localIntent.putExtra("type", str3);
      String str4 = localComment2.comment;
      localIntent.putExtra("comment", str4);
      int m = localComment2.errorType;
      localIntent.putExtra("errorType", m);
      int n = Integer.parseInt(arrayOfString[0]);
      localIntent.putExtra("parentId", n);
      boolean bool1 = localComment2.isDelete;
      localIntent.putExtra("isDelete", bool1);
      boolean bool2 = localComment2.isMine;
      localIntent.putExtra("isMine", bool2);
      CommentsActivity.ReviewAdapter.access$0(this.this$1).startActivityForResult(localIntent, 402);
      return;
      localReviewAdapter2 = this.this$1;
      i1 = Integer.parseInt(arrayOfString[1]);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommentsActivity.ReviewAdapter.1
 * JD-Core Version:    0.6.0
 */