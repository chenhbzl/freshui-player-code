package com.yingyonghui.market;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;

class SearchActivity$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    String str1;
    if (paramView.getId() == 2131427506)
    {
      str1 = SearchActivity.access$0(this.this$0).getText().toString().trim();
      if (str1.length() != 0);
    }
    while (true)
    {
      return;
      String str2 = str1.replace("'", "").replaceAll("\"", "");
      SearchActivity.access$1(this.this$0, str2);
      SearchActivity.access$2(this.this$0, str2);
      continue;
      if (paramView.getId() != 2131427508)
        continue;
      SearchActivity.access$0(this.this$0).setText("");
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.SearchActivity.1
 * JD-Core Version:    0.6.0
 */