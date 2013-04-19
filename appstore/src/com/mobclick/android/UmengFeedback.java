package com.mobclick.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.reflect.Field;
import org.json.JSONObject;

public class UmengFeedback extends Activity
{
  private static e e;
  private static Context f;
  private Spinner a;
  private Spinner b;
  private EditText c;
  private String d = "error";
  private JSONObject g;

  private int a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String str1 = String.valueOf(paramContext.getPackageName());
      Field localField = Class.forName(str1 + ".R$" + paramString1).getField(paramString2);
      String str2 = localField.getName();
      i = Integer.parseInt(localField.get(str2).toString());
      return i;
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str3 = localException.getMessage();
        Log.e("getIdByReflection error", str3);
        int i = 0;
      }
    }
  }

  private JSONObject a(int paramInt1, int paramInt2, String paramString)
  {
    try
    {
      localJSONObject = new JSONObject();
      localJSONObject.put("age", paramInt1);
      localJSONObject.put("gender", paramInt2);
      localJSONObject.put("content", paramString);
      return localJSONObject;
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str1 = this.d;
        String str2 = localException.getMessage();
        Log.e(str1, str2);
        JSONObject localJSONObject = null;
      }
    }
  }

  public static void a(Context paramContext)
  {
    f = paramContext;
  }

  public static void a(e parame)
  {
    e = parame;
  }

  private void b()
  {
    Context localContext1 = f;
    int i = a(localContext1, "id", "feedback_umeng_title");
    TextView localTextView1 = (TextView)findViewById(i);
    String str1 = k.a(this);
    localTextView1.setText(str1);
    Context localContext2 = f;
    int j = a(localContext2, "id", "feedback_title");
    TextView localTextView2 = (TextView)findViewById(j);
    String str2 = k.b(this);
    localTextView2.setText(str2);
    Context localContext3 = f;
    int k = a(localContext3, "id", "feedback_submit");
    Button localButton = (Button)findViewById(k);
    String str3 = k.d(this);
    localButton.setText(str3);
  }

  public void onCreate(Bundle paramBundle)
  {
    onCreate(paramBundle);
    requestWindowFeature(1);
    Context localContext1 = f;
    int i = a(localContext1, "layout", "umeng_feedback");
    setContentView(i);
    Context localContext2 = f;
    int j = a(localContext2, "id", "umengBannerTop");
    ImageView localImageView = (ImageView)findViewById(j);
    GradientDrawable.Orientation localOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
    int[] arrayOfInt = new int[4];
    int k = Color.rgb(135, 135, 135);
    arrayOfInt[0] = k;
    int m = Color.rgb(90, 90, 90);
    arrayOfInt[1] = m;
    int n = Color.rgb(52, 52, 52);
    arrayOfInt[2] = n;
    int i1 = Color.rgb(0, 0, 0);
    arrayOfInt[3] = i1;
    GradientDrawable localGradientDrawable = new GradientDrawable(localOrientation, arrayOfInt);
    localImageView.setBackgroundDrawable(localGradientDrawable);
    b();
    Context localContext3 = f;
    int i2 = a(localContext3, "id", "feedback_content");
    EditText localEditText1 = (EditText)findViewById(i2);
    this.c = localEditText1;
    EditText localEditText2 = this.c;
    String str = k.c(this);
    localEditText2.setHint(str);
    Context localContext4 = f;
    int i3 = a(localContext4, "id", "feedback_age_spinner");
    Spinner localSpinner1 = (Spinner)findViewById(i3);
    this.a = localSpinner1;
    String[] arrayOfString1 = k.f(this);
    ArrayAdapter localArrayAdapter1 = new ArrayAdapter(this, 17367048, arrayOfString1);
    localArrayAdapter1.setDropDownViewResource(17367049);
    this.a.setAdapter(localArrayAdapter1);
    Context localContext5 = f;
    int i4 = a(localContext5, "id", "feedback_gender_spinner");
    Spinner localSpinner2 = (Spinner)findViewById(i4);
    this.b = localSpinner2;
    String[] arrayOfString2 = k.g(this);
    ArrayAdapter localArrayAdapter2 = new ArrayAdapter(this, 17367048, arrayOfString2);
    localArrayAdapter2.setDropDownViewResource(17367049);
    this.b.setAdapter(localArrayAdapter2);
    Context localContext6 = f;
    int i5 = a(localContext6, "id", "feedback_submit");
    Button localButton = (Button)findViewById(i5);
    l locall = new l(this);
    localButton.setOnClickListener(locall);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.UmengFeedback
 * JD-Core Version:    0.6.0
 */