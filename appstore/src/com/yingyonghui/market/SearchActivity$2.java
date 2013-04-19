package com.yingyonghui.market;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.AutoCompleteTextView;
import com.yingyonghui.market.util.DeviceUtil;

class SearchActivity$2 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("show_search_result"))
      if (DeviceUtil.getSDKVersionInt() == 3)
        SearchActivity.access$3(this.this$0, 1);
    while (true)
    {
      return;
      SearchActivity.access$4(this.this$0, 1);
      continue;
      if (!paramIntent.getAction().equals("broadcast_search_request"))
        continue;
      SearchActivity localSearchActivity = this.this$0;
      String str1 = paramIntent.getStringExtra("keyword");
      SearchActivity.access$5(localSearchActivity, str1);
      if (paramIntent.getIntExtra("source", -1) != 0)
        continue;
      AutoCompleteTextView localAutoCompleteTextView = SearchActivity.access$0(this.this$0);
      String str2 = SearchActivity.access$6(this.this$0);
      localAutoCompleteTextView.setText(str2);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchActivity.2
 * JD-Core Version:    0.6.0
 */