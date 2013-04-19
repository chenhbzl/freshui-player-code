package com.yingyonghui.market;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import dalvik.annotation.EnclosingMethod;
import dalvik.annotation.Signature;

@EnclosingMethod
@Signature({"Ljava/lang/Object;", "Landroid/widget/AdapterView$OnItemSelectedListener;"})
class AssetInfoDetailActivity$4
  implements AdapterView.OnItemSelectedListener
{
  @Signature({"(", "Landroid/widget/AdapterView", "<*>;", "Landroid/view/View;", "IJ)V"})
  public void onItemSelected(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    TextView localTextView = this.val$imageIndicator;
    String str1 = String.valueOf(paramInt + 1);
    StringBuilder localStringBuilder = new StringBuilder(str1).append("/");
    int i = AssetInfoDetailActivity.access$8(this.this$0).getCount();
    String str2 = i;
    localTextView.setText(str2);
  }

  @Signature({"(", "Landroid/widget/AdapterView", "<*>;)V"})
  public void onNothingSelected(AdapterView paramAdapterView)
  {
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoDetailActivity.4
 * JD-Core Version:    0.6.0
 */