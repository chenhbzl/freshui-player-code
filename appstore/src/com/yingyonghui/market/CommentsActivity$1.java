package com.yingyonghui.market;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.yingyonghui.market.model.AssetDetail;
import com.yingyonghui.market.util.PackageInstallInfo;
import com.yingyonghui.market.util.StringUtil;

class CommentsActivity$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    if (str1.equals("send_comment_detail"))
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        CommentsActivity localCommentsActivity1 = this.this$0;
        AssetDetail localAssetDetail1 = new AssetDetail();
        CommentsActivity.access$0(localCommentsActivity1, localAssetDetail1);
        AssetDetail localAssetDetail2 = CommentsActivity.access$1(this.this$0);
        int i = localBundle.getInt("_id");
        localAssetDetail2._id = i;
        AssetDetail localAssetDetail3 = CommentsActivity.access$1(this.this$0);
        String str2 = localBundle.getString("pkgName");
        localAssetDetail3.pkgName = str2;
        AssetDetail localAssetDetail4 = CommentsActivity.access$1(this.this$0);
        int j = localBundle.getInt("versionCode");
        localAssetDetail4.versionCode = j;
        AssetDetail localAssetDetail5 = CommentsActivity.access$1(this.this$0);
        CommentsActivity localCommentsActivity2 = this.this$0;
        String str3 = CommentsActivity.access$1(this.this$0).pkgName;
        int k = CommentsActivity.access$1(this.this$0).versionCode;
        int m = CommentsActivity.access$1(this.this$0)._id;
        int n = PackageInstallInfo.getPackageInstalledStatus(localCommentsActivity2, str3, k, m);
        localAssetDetail5.installed = n;
        CommentsActivity.access$2(this.this$0);
        CommentsActivity.access$3(this.this$0);
      }
    }
    while (true)
    {
      return;
      if ((!str1.equals("android.intent.action.PACKAGE_ADDED")) && (!str1.equals("android.intent.action.PACKAGE_REMOVED")))
        break;
      String str4 = paramIntent.getData().getEncodedSchemeSpecificPart();
      String str5 = CommentsActivity.access$1(this.this$0).pkgName;
      if (!StringUtil.equals(str4, str5))
        continue;
      if (!str1.equals("android.intent.action.PACKAGE_REMOVED"))
        break label350;
    }
    label350: for (CommentsActivity.access$1(this.this$0).installed = 0; ; CommentsActivity.access$1(this.this$0).installed = 1)
    {
      if (CommentsActivity.access$4(this.this$0))
      {
        CommentsActivity.access$5(this.this$0, 0);
        ListView localListView = CommentsActivity.access$6(this.this$0);
        View localView = CommentsActivity.access$7(this.this$0);
        localListView.addFooterView(localView, null, 0);
      }
      CommentsActivity.access$8(this.this$0, null);
      CommentsActivity.access$9(this.this$0, 0);
      CommentsActivity.access$3(this.this$0);
      break;
      break;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.CommentsActivity.1
 * JD-Core Version:    0.6.0
 */