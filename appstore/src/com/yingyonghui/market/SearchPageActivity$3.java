package com.yingyonghui.market;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class SearchPageActivity$3
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    SearchPageActivity localSearchPageActivity = this.this$0;
    String str1 = ((TextView)paramView).getText().toString();
    SearchPageActivity.access$6(localSearchPageActivity, str1);
    Intent localIntent = new Intent("broadcast_search_request");
    int i = SearchPageActivity.access$7(this.this$0);
    localIntent.putExtra("source", i);
    String str2 = SearchPageActivity.access$8(this.this$0);
    localIntent.putExtra("keyword", str2);
    String str3 = SearchPageActivity.access$9(this.this$0);
    localIntent.putExtra("title", str3);
    int j = SearchPageActivity.access$10(this.this$0);
    localIntent.putExtra("searchType", j);
    this.this$0.sendBroadcast(localIntent);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchPageActivity.3
 * JD-Core Version:    0.6.0
 */