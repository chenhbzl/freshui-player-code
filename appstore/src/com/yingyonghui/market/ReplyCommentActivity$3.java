package com.yingyonghui.market;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.yingyonghui.market.online.IMarketService;
import com.yingyonghui.market.online.Request;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class ReplyCommentActivity$3
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (ReplyCommentActivity.access$2(this.this$0) != null)
    {
      IMarketService localIMarketService = ReplyCommentActivity.access$3(this.this$0);
      Request localRequest = ReplyCommentActivity.access$2(this.this$0);
      localIMarketService.sendComment(localRequest);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.ReplyCommentActivity.3
 * JD-Core Version:    0.6.0
 */