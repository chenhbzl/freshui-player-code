package com.yingyonghui.market;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.yingyonghui.market.model.AssetDetail;
import dalvik.annotation.EnclosingMethod;

@EnclosingMethod
class NewsContentActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    Intent localIntent = new Intent();
    NewsContentActivity localNewsContentActivity = this.this$0;
    String str1 = AssetInfoActivity.class.getName();
    localIntent.setClassName(localNewsContentActivity, str1);
    int i = this.val$mDetail._id;
    localIntent.putExtra("_id", i);
    String str2 = this.val$mDetail.title;
    localIntent.putExtra("title", str2);
    int j = this.val$mDetail.installed;
    localIntent.putExtra("installed", j);
    String str3 = this.val$mDetail.pkgName;
    localIntent.putExtra("pkgName", str3);
    int k = this.val$mDetail.size;
    localIntent.putExtra("size", k);
    int m = this.val$mDetail.versionCode;
    localIntent.putExtra("versionCode", m);
    localIntent.putExtra("from", "Zhuanlan");
    this.this$0.startActivity(localIntent);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.NewsContentActivity.2
 * JD-Core Version:    0.6.0
 */