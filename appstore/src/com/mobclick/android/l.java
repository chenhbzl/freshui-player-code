package com.mobclick.android;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.JSONObject;

class l
  implements View.OnClickListener
{
  l(UmengFeedback paramUmengFeedback)
  {
  }

  public void onClick(View paramView)
  {
    int i = UmengFeedback.a(this.a).getSelectedItemPosition();
    int j = UmengFeedback.b(this.a).getSelectedItemPosition();
    String str = UmengFeedback.c(this.a).getText().toString();
    UmengFeedback localUmengFeedback = this.a;
    JSONObject localJSONObject1 = UmengFeedback.a(this.a, i, j, str);
    UmengFeedback.a(localUmengFeedback, localJSONObject1);
    e locale = UmengFeedback.a();
    JSONObject localJSONObject2 = UmengFeedback.d(this.a);
    locale.onFeedbackReturned(localJSONObject2);
    this.a.finish();
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.l
 * JD-Core Version:    0.6.0
 */