package com.yingyonghui.market;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.yingyonghui.market.model.AssetDetail;

class AssetInfoDetailActivity$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("send_asset_detail"))
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        AssetInfoDetailActivity localAssetInfoDetailActivity = this.this$0;
        AssetDetail localAssetDetail1 = new AssetDetail();
        AssetInfoDetailActivity.access$0(localAssetInfoDetailActivity, localAssetDetail1);
        AssetDetail localAssetDetail2 = AssetInfoDetailActivity.access$1(this.this$0);
        int i = localBundle.getInt("_id");
        localAssetDetail2._id = i;
        AssetDetail localAssetDetail3 = AssetInfoDetailActivity.access$1(this.this$0);
        String str1 = localBundle.getString("title");
        localAssetDetail3.title = str1;
        AssetDetail localAssetDetail4 = AssetInfoDetailActivity.access$1(this.this$0);
        String str2 = localBundle.getString("author");
        localAssetDetail4.author = str2;
        AssetDetail localAssetDetail5 = AssetInfoDetailActivity.access$1(this.this$0);
        float f = localBundle.getFloat("rating");
        localAssetDetail5.rating = f;
        AssetDetail localAssetDetail6 = AssetInfoDetailActivity.access$1(this.this$0);
        int j = localBundle.getInt("ratingNumbers");
        localAssetDetail6.ratingNumbers = j;
        AssetDetail localAssetDetail7 = AssetInfoDetailActivity.access$1(this.this$0);
        String str3 = localBundle.getString("pkgName");
        localAssetDetail7.pkgName = str3;
        AssetDetail localAssetDetail8 = AssetInfoDetailActivity.access$1(this.this$0);
        int k = localBundle.getInt("size");
        localAssetDetail8.size = k;
        AssetDetail localAssetDetail9 = AssetInfoDetailActivity.access$1(this.this$0);
        String str4 = localBundle.getString("version");
        localAssetDetail9.version = str4;
        AssetDetail localAssetDetail10 = AssetInfoDetailActivity.access$1(this.this$0);
        String str5 = localBundle.getString("lastUpdate");
        localAssetDetail10.lastUpdate = str5;
        AssetDetail localAssetDetail11 = AssetInfoDetailActivity.access$1(this.this$0);
        int m = localBundle.getInt("versionCode");
        localAssetDetail11.versionCode = m;
        AssetDetail localAssetDetail12 = AssetInfoDetailActivity.access$1(this.this$0);
        int n = localBundle.getInt("downloadsNumber");
        localAssetDetail12.downloadsNumber = n;
        AssetDetail localAssetDetail13 = AssetInfoDetailActivity.access$1(this.this$0);
        String str6 = localBundle.getString("description");
        localAssetDetail13.description = str6;
        AssetDetail localAssetDetail14 = AssetInfoDetailActivity.access$1(this.this$0);
        String str7 = localBundle.getString("email");
        localAssetDetail14.email = str7;
        AssetDetail localAssetDetail15 = AssetInfoDetailActivity.access$1(this.this$0);
        String str8 = localBundle.getString("myComment");
        localAssetDetail15.myComment = str8;
        AssetDetail localAssetDetail16 = AssetInfoDetailActivity.access$1(this.this$0);
        long l = localBundle.getLong("myCommentDate");
        localAssetDetail16.myCommentDate = l;
        AssetDetail localAssetDetail17 = AssetInfoDetailActivity.access$1(this.this$0);
        int i1 = localBundle.getInt("myRating");
        localAssetDetail17.myRating = i1;
        AssetDetail localAssetDetail18 = AssetInfoDetailActivity.access$1(this.this$0);
        String str9 = localBundle.getString("devLogin");
        localAssetDetail18.devLogin = str9;
        AssetDetail localAssetDetail19 = AssetInfoDetailActivity.access$1(this.this$0);
        int i2 = localBundle.getInt("installed");
        localAssetDetail19.installed = i2;
        AssetInfoDetailActivity.access$2(this.this$0);
        AssetInfoDetailActivity.access$3(this.this$0).setVisibility(0);
        AssetInfoDetailActivity.access$4(this.this$0).setVisibility(8);
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AssetInfoDetailActivity.1
 * JD-Core Version:    0.6.0
 */