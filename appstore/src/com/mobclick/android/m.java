package com.mobclick.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import javax.microedition.khronos.opengles.GL10;

public class m
{
  // ERROR //
  public static String a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 16	java/io/FileReader
    //   5: dup
    //   6: ldc 18
    //   8: invokespecial 21	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +28 -> 41
    //   16: new 23	java/io/BufferedReader
    //   19: dup
    //   20: aload_1
    //   21: sipush 1024
    //   24: invokespecial 26	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 29	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore_0
    //   33: aload_2
    //   34: invokevirtual 32	java/io/BufferedReader:close	()V
    //   37: aload_1
    //   38: invokevirtual 33	java/io/FileReader:close	()V
    //   41: aload_0
    //   42: ifnull +18 -> 60
    //   45: aload_0
    //   46: bipush 58
    //   48: invokevirtual 39	java/lang/String:indexOf	(I)I
    //   51: iconst_1
    //   52: iadd
    //   53: istore_3
    //   54: aload_0
    //   55: iload_3
    //   56: invokevirtual 43	java/lang/String:substring	(I)Ljava/lang/String;
    //   59: astore_0
    //   60: aload_0
    //   61: invokevirtual 46	java/lang/String:trim	()Ljava/lang/String;
    //   64: areturn
    //   65: astore 4
    //   67: aload_0
    //   68: astore_1
    //   69: aload 4
    //   71: astore_0
    //   72: ldc 48
    //   74: ldc 50
    //   76: aload_0
    //   77: invokestatic 56	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   80: pop
    //   81: aload_1
    //   82: astore_0
    //   83: goto -42 -> 41
    //   86: astore 5
    //   88: aload_0
    //   89: astore_1
    //   90: aload 5
    //   92: astore_0
    //   93: ldc 48
    //   95: ldc 58
    //   97: aload_0
    //   98: invokestatic 56	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   101: pop
    //   102: aload_1
    //   103: astore_0
    //   104: goto -63 -> 41
    //   107: astore 6
    //   109: aload_0
    //   110: astore_1
    //   111: aload 6
    //   113: astore_0
    //   114: goto -21 -> 93
    //   117: astore_0
    //   118: goto -25 -> 93
    //   121: astore 7
    //   123: aload_0
    //   124: astore_1
    //   125: aload 7
    //   127: astore_0
    //   128: goto -56 -> 72
    //
    // Exception table:
    //   from	to	target	type
    //   16	33	65	java/io/IOException
    //   2	12	86	java/io/FileNotFoundException
    //   16	33	86	java/io/FileNotFoundException
    //   33	41	107	java/io/FileNotFoundException
    //   72	81	117	java/io/FileNotFoundException
    //   33	41	121	java/io/IOException
  }

  public static boolean a(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    String str = paramContext.getPackageName();
    if (localPackageManager.checkPermission(paramString, str) != 0);
    for (int i = 0; ; i = 1)
      return i;
  }

  public static String[] a(GL10 paramGL10)
  {
    try
    {
      arrayOfString = new String[2];
      String str1 = paramGL10.glGetString(7936);
      String str2 = paramGL10.glGetString(7937);
      arrayOfString[0] = str1;
      arrayOfString[1] = str2;
      return arrayOfString;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.e("MobclickAgent", "Could not read gpu infor:", localException);
        String[] arrayOfString = new String[0];
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.mobclick.android.m
 * JD-Core Version:    0.6.0
 */