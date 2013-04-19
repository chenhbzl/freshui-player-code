package com.yingyonghui.market;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class OptionsMenu
{
  private static final int ABOUT_ID = 4;
  private static final int ACCOUNT_ID = 2;
  private static final int FEEDBACK_ID = 3;
  private static final int SETTING_ID = 1;

  public static boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 1, 0, 2131296439).setIcon(2130837685);
    paramMenu.add(0, 2, 0, 2131296440).setIcon(2130837508).setEnabled(0);
    paramMenu.add(0, 3, 0, 2131296441).setIcon(2130837522);
    paramMenu.add(0, 4, 0, 2131296451).setIcon(2130837504);
    return true;
  }

  public static boolean onOptionsItemSelected(Activity paramActivity, MenuItem paramMenuItem)
  {
    Intent localIntent = new Intent();
    switch (paramMenuItem.getItemId())
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      paramActivity.startActivity(localIntent);
      return true;
      localIntent.setClass(paramActivity, SettingActivity.class);
      continue;
      localIntent.setClass(paramActivity, AccountDisplay.class);
      continue;
      localIntent.setClass(paramActivity, FeedbackDialog.class);
      continue;
      localIntent.setClass(paramActivity, AboutDialog.class);
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.OptionsMenu
 * JD-Core Version:    0.6.0
 */