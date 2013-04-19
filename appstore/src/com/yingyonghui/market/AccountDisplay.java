package com.yingyonghui.market;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountDisplay extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    setContentView(2130903080);
    ListView localListView = (ListView)findViewById(2131427471);
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    localHashMap.put("Text", "Account display!");
    Integer localInteger = Integer.valueOf(2130837558);
    localHashMap.put("Icon", localInteger);
    localArrayList.add(localHashMap);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Text";
    arrayOfString[1] = "Icon";
    int[] arrayOfInt = { 2131427517, 2131427518 };
    AccountDisplay localAccountDisplay = this;
    SimpleAdapter localSimpleAdapter = new SimpleAdapter(localAccountDisplay, localArrayList, 2130903103, arrayOfString, arrayOfInt);
    localListView.setAdapter(localSimpleAdapter);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.AccountDisplay
 * JD-Core Version:    0.6.0
 */