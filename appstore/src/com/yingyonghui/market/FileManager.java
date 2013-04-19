package com.yingyonghui.market;

import android.content.Context;
import android.os.Environment;
import com.yingyonghui.market.model.Asset;
import dalvik.annotation.Signature;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class FileManager
{
  public static final int BUFFER_SIZE = 8192;
  public static final String ICON = "icon";
  public static final String SCREENSHOT = "screenshot";
  public static final String TOP_RECOMMEND = "top_recommend";

  @Signature({"Ljava/util/HashMap", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;"})
  private static HashMap modifyTimeRec = null;

  public static void addApkTempFileTimestampRecord(Context paramContext, String paramString1, String paramString2)
  {
    if (modifyTimeRec != null)
      modifyTimeRec.put(paramString1, paramString2);
    while (true)
    {
      return;
      readTempFileTimestampRecordFromFile(paramContext);
      modifyTimeRec.put(paramString1, paramString2);
    }
  }

  public static void deleteAllFiles(Context paramContext)
  {
    File[] arrayOfFile1 = paramContext.getFilesDir().listFiles();
    int i;
    File[] arrayOfFile2;
    if (arrayOfFile1 != null)
    {
      i = 0;
      int j = arrayOfFile1.length;
      if (i < j);
    }
    else if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile = Environment.getExternalStorageDirectory();
      arrayOfFile2 = new File(localFile, "Yingyonghui").listFiles();
      if (arrayOfFile2 != null)
        i = 0;
    }
    while (true)
    {
      int k = arrayOfFile2.length;
      if (i >= k)
      {
        return;
        arrayOfFile1[i].delete();
        i += 1;
        break;
      }
      arrayOfFile2[i].delete();
      i += 1;
    }
  }

  public static void deleteApkFiles(Context paramContext)
  {
    File[] arrayOfFile1 = paramContext.getFilesDir().listFiles();
    int i;
    File[] arrayOfFile2;
    if (arrayOfFile1 != null)
    {
      i = 0;
      int j = arrayOfFile1.length;
      if (i < j);
    }
    else if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile1 = Environment.getExternalStorageDirectory();
      arrayOfFile2 = new File(localFile1, "Yingyonghui").listFiles();
      if (arrayOfFile2 != null)
        i = 0;
    }
    while (true)
    {
      int k = arrayOfFile2.length;
      if (i >= k)
      {
        return;
        localFile2 = arrayOfFile1[i];
        if ((!localFile2.getName().startsWith("icon")) && (!localFile2.getName().startsWith("top_recommend")))
          localFile2.delete();
        i += 1;
        break;
      }
      File localFile2 = arrayOfFile2[i];
      if ((!localFile2.getName().startsWith("icon")) && (!localFile2.getName().startsWith("top_recommend")))
        localFile2.delete();
      i += 1;
    }
  }

  public static void deleteFile(Context paramContext, Asset paramAsset)
  {
    File[] arrayOfFile1 = paramContext.getFilesDir().listFiles();
    int i;
    File[] arrayOfFile2;
    if (arrayOfFile1 != null)
    {
      i = 0;
      int j = arrayOfFile1.length;
      if (i < j);
    }
    else if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile1 = Environment.getExternalStorageDirectory();
      arrayOfFile2 = new File(localFile1, "Yingyonghui").listFiles();
      if (arrayOfFile2 != null)
        i = 0;
    }
    while (true)
    {
      int k = arrayOfFile2.length;
      if (i >= k)
      {
        return;
        localFile2 = arrayOfFile1[i];
        String str1 = localFile2.getName();
        String str2 = String.valueOf(paramAsset.pkgName);
        if (str1.equals(str2))
          localFile2.delete();
        i += 1;
        break;
      }
      File localFile2 = arrayOfFile2[i];
      String str3 = localFile2.getName();
      String str4 = String.valueOf(paramAsset.pkgName);
      if (str3.equals(str4))
        localFile2.delete();
      i += 1;
    }
  }

  public static void deleteFile(Context paramContext, String paramString)
  {
    File[] arrayOfFile1 = paramContext.getFilesDir().listFiles();
    int i;
    File[] arrayOfFile2;
    if (arrayOfFile1 != null)
    {
      i = 0;
      int j = arrayOfFile1.length;
      if (i < j);
    }
    else if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile1 = Environment.getExternalStorageDirectory();
      arrayOfFile2 = new File(localFile1, "Yingyonghui").listFiles();
      if (arrayOfFile2 != null)
        i = 0;
    }
    while (true)
    {
      int k = arrayOfFile2.length;
      if (i >= k)
      {
        return;
        localFile2 = arrayOfFile1[i];
        if (localFile2.getName().equals(paramString))
          localFile2.delete();
        i += 1;
        break;
      }
      File localFile2 = arrayOfFile2[i];
      if (localFile2.getName().equals(paramString))
        localFile2.delete();
      i += 1;
    }
  }

  @Signature({"(", "Landroid/content/Context;", "I", "Ljava/lang/String;", "Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/lang/String;", ">;)", "Ljava/io/RandomAccessFile;"})
  public static RandomAccessFile getApkCacheStream(Context paramContext, int paramInt, String paramString, HashMap paramHashMap)
    throws FileNotFoundException
  {
    RandomAccessFile localRandomAccessFile;
    File localFile3;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile1 = Environment.getExternalStorageDirectory();
      File localFile2 = new File(localFile1, "Yingyonghui");
      if (!localFile2.exists())
        localFile2.mkdirs();
      String str1 = String.valueOf(Environment.getExternalStorageDirectory().toString());
      String str2 = str1 + "/Yingyonghui/" + paramString;
      localRandomAccessFile = new RandomAccessFile(str2, "rw");
      String str3 = String.valueOf(Environment.getExternalStorageDirectory().toString());
      String str4 = str3 + "/Yingyonghui";
      localFile3 = new File(str4, paramString);
    }
    while (true)
    {
      Integer localInteger = Integer.valueOf(paramInt);
      String str5 = localFile3.getPath();
      paramHashMap.put(localInteger, str5);
      return localRandomAccessFile;
      int i;
      if (!paramContext.getFileStreamPath(paramString).exists())
        i = 1;
      try
      {
        FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString, i);
        localFile3 = paramContext.getFileStreamPath(paramString);
        String str6 = localFile3.getPath();
        localRandomAccessFile = new RandomAccessFile(str6, "rw");
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
    }
  }

  public static File getFile(Context paramContext, Asset paramAsset)
  {
    Object localObject;
    if (paramAsset == null)
      localObject = null;
    while (true)
    {
      return localObject;
      try
      {
        String str1 = String.valueOf(paramContext.getFilesDir().getAbsolutePath());
        StringBuilder localStringBuilder1 = new StringBuilder(str1);
        String str2 = File.separator;
        StringBuilder localStringBuilder2 = localStringBuilder1.append(str2);
        String str3 = paramAsset.pkgName;
        String str4 = str3 + ".apk";
        File localFile1 = new File(str4);
        if ((localFile1.exists()) && (localFile1.isFile()))
        {
          long l1 = localFile1.length();
          long l2 = paramAsset.size;
          if (l1 == l2)
          {
            localObject = localFile1;
            continue;
          }
        }
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          String str5 = String.valueOf(Environment.getExternalStorageDirectory().toString());
          String str6 = str5 + "/Yingyonghui";
          String str7 = String.valueOf(paramAsset.pkgName);
          String str8 = str7 + ".apk";
          File localFile2 = new File(str6, str8);
          if ((localFile2.exists()) && (localFile2.isFile()))
          {
            long l3 = localFile2.length();
            int i = paramAsset.size;
            long l4 = i;
            if (l3 == l4)
              localObject = localFile2;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        localObject = null;
      }
    }
  }

  public static File getFile(Context paramContext, String paramString)
  {
    Object localObject1;
    if (paramString == "")
      localObject1 = null;
    while (true)
    {
      return localObject1;
      try
      {
        String str1 = String.valueOf(paramContext.getFilesDir().getAbsolutePath());
        StringBuilder localStringBuilder = new StringBuilder(str1);
        String str2 = File.separator;
        String str3 = str2 + paramString + ".apk";
        File localFile1 = new File(str3);
        if ((localFile1.exists()) && (localFile1.isFile()))
        {
          localObject1 = localFile1;
          continue;
        }
        if (Environment.getExternalStorageState().equals("mounted"))
        {
          String str4 = String.valueOf(Environment.getExternalStorageDirectory().toString());
          String str5 = str4 + "/Yingyonghui";
          String str6 = String.valueOf(paramString);
          String str7 = str6 + ".apk";
          File localFile2 = new File(str5, str7);
          if (localFile2.exists())
          {
            boolean bool = localFile2.isFile();
            if (bool)
              localObject2 = localFile2;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject2 = null;
      }
    }
  }

  public static FileOutputStream getOutputStream(Context paramContext, String paramString)
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      File localFile2;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        File localFile1 = Environment.getExternalStorageDirectory();
        new File(localFile1, "Yingyonghui").mkdirs();
        String str1 = String.valueOf(Environment.getExternalStorageDirectory().toString());
        String str2 = str1 + "/Yingyonghui";
        localFile2 = new File(str2, paramString);
      }
      for (localFileOutputStream = new FileOutputStream(localFile2); ; localFileOutputStream = paramContext.openFileOutput(paramString, 1))
        return localFileOutputStream;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        localThrowable.printStackTrace();
    }
  }

  // ERROR //
  @Signature({"(", "Landroid/content/Context;", "I", "Ljava/util/Hashtable", "<", "Ljava/lang/Integer;", "Landroid/graphics/drawable/Drawable;", ">;)", "Landroid/graphics/drawable/Drawable;"})
  public static android.graphics.drawable.Drawable lookupThumbnail(Context paramContext, int paramInt, java.util.Hashtable paramHashtable)
  {
    // Byte code:
    //   0: iload_1
    //   1: invokestatic 150	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   4: astore_3
    //   5: aload_2
    //   6: aload_3
    //   7: invokevirtual 210	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast 212	android/graphics/drawable/Drawable
    //   13: astore 4
    //   15: aload 4
    //   17: ifnonnull +67 -> 84
    //   20: new 125	java/lang/StringBuilder
    //   23: dup
    //   24: ldc 12
    //   26: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   29: iload_1
    //   30: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   33: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: astore 5
    //   38: aload_0
    //   39: aload 5
    //   41: invokevirtual 219	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   44: astore 6
    //   46: new 221	android/graphics/drawable/BitmapDrawable
    //   49: dup
    //   50: aload 6
    //   52: invokespecial 224	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   55: astore 7
    //   57: ldc 225
    //   59: istore 8
    //   61: aload_0
    //   62: iload_1
    //   63: aload 7
    //   65: iload 8
    //   67: invokestatic 231	com/yingyonghui/market/util/CachedThumbnails:cacheThumbnail	(Landroid/content/Context;ILandroid/graphics/drawable/Drawable;Z)V
    //   70: aload 6
    //   72: ifnull +196 -> 268
    //   75: aload 6
    //   77: invokevirtual 236	java/io/FileInputStream:close	()V
    //   80: aload 7
    //   82: astore 4
    //   84: aload 4
    //   86: areturn
    //   87: astore 9
    //   89: aload 6
    //   91: ifnull -7 -> 84
    //   94: aload 6
    //   96: invokevirtual 236	java/io/FileInputStream:close	()V
    //   99: goto -15 -> 84
    //   102: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   105: goto -21 -> 84
    //   108: astore 10
    //   110: aload 4
    //   112: astore 7
    //   114: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   117: ldc 65
    //   119: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   122: istore 8
    //   124: iload 8
    //   126: ifeq +186 -> 312
    //   129: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   132: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   135: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   138: astore 11
    //   140: new 125	java/lang/StringBuilder
    //   143: dup
    //   144: aload 11
    //   146: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   149: ldc 144
    //   151: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: astore 12
    //   159: new 125	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 12
    //   165: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: iload_1
    //   169: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   172: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: astore 13
    //   177: new 53	java/io/File
    //   180: dup
    //   181: aload 12
    //   183: aload 13
    //   185: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: astore 14
    //   190: new 233	java/io/FileInputStream
    //   193: dup
    //   194: aload 14
    //   196: invokespecial 237	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   199: astore 15
    //   201: new 221	android/graphics/drawable/BitmapDrawable
    //   204: dup
    //   205: aload 15
    //   207: invokespecial 224	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   210: astore 4
    //   212: ldc 225
    //   214: istore 8
    //   216: aload_0
    //   217: iload_1
    //   218: aload 4
    //   220: iload 8
    //   222: invokestatic 231	com/yingyonghui/market/util/CachedThumbnails:cacheThumbnail	(Landroid/content/Context;ILandroid/graphics/drawable/Drawable;Z)V
    //   225: aload 6
    //   227: ifnull -143 -> 84
    //   230: aload 6
    //   232: invokevirtual 236	java/io/FileInputStream:close	()V
    //   235: goto -151 -> 84
    //   238: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   241: goto -157 -> 84
    //   244: astore 8
    //   246: aload 6
    //   248: ifnull +8 -> 256
    //   251: aload 6
    //   253: invokevirtual 236	java/io/FileInputStream:close	()V
    //   256: aload 8
    //   258: athrow
    //   259: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   262: goto -6 -> 256
    //   265: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   268: aload 7
    //   270: astore 4
    //   272: goto -188 -> 84
    //   275: astore 8
    //   277: aload 7
    //   279: astore 16
    //   281: goto -35 -> 246
    //   284: astore 17
    //   286: aload 7
    //   288: astore 4
    //   290: goto -65 -> 225
    //   293: astore 18
    //   295: goto -70 -> 225
    //   298: astore 19
    //   300: goto -186 -> 114
    //   303: astore 20
    //   305: aload 7
    //   307: astore 4
    //   309: goto -220 -> 89
    //   312: aload 7
    //   314: astore 4
    //   316: goto -91 -> 225
    //
    // Exception table:
    //   from	to	target	type
    //   20	57	87	java/lang/OutOfMemoryError
    //   94	99	102	java/io/IOException
    //   20	57	108	java/io/FileNotFoundException
    //   230	235	238	java/io/IOException
    //   20	57	244	finally
    //   216	225	244	finally
    //   251	256	259	java/io/IOException
    //   75	80	265	java/io/IOException
    //   61	70	275	finally
    //   114	124	275	finally
    //   129	212	275	finally
    //   129	212	284	java/lang/Throwable
    //   216	225	293	java/lang/Throwable
    //   61	70	298	java/io/FileNotFoundException
    //   61	70	303	java/lang/OutOfMemoryError
  }

  public static File loopupScreenshot(Context paramContext, int paramInt)
  {
    String str1;
    if (Environment.getExternalStorageState().equals("mounted"))
      str1 = "screenshot" + paramInt + ".png";
    while (true)
    {
      try
      {
        String str2 = String.valueOf(Environment.getExternalStorageDirectory().toString());
        String str3 = str2 + "/Yingyonghui";
        File localFile1 = new File(str3, str1);
        boolean bool = localFile1.exists();
        if (!bool)
          continue;
        localFile2 = localFile1;
        return localFile2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localFile2 = null;
        continue;
      }
      File localFile2 = null;
    }
  }

  // ERROR //
  public static android.graphics.drawable.Drawable readDrawableFromFileSystem(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 219	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   7: astore_3
    //   8: new 221	android/graphics/drawable/BitmapDrawable
    //   11: dup
    //   12: aload_3
    //   13: invokespecial 224	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   16: astore 4
    //   18: aload_3
    //   19: ifnull +226 -> 245
    //   22: aload_3
    //   23: invokevirtual 236	java/io/FileInputStream:close	()V
    //   26: aload 4
    //   28: astore_2
    //   29: aload_2
    //   30: areturn
    //   31: astore 5
    //   33: aload_3
    //   34: ifnull -5 -> 29
    //   37: aload_3
    //   38: invokevirtual 236	java/io/FileInputStream:close	()V
    //   41: goto -12 -> 29
    //   44: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   47: goto -18 -> 29
    //   50: astore 6
    //   52: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   55: ldc 65
    //   57: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   60: istore 7
    //   62: iload 7
    //   64: ifeq +83 -> 147
    //   67: aconst_null
    //   68: astore 8
    //   70: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   73: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   76: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   79: astore 9
    //   81: new 125	java/lang/StringBuilder
    //   84: dup
    //   85: aload 9
    //   87: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   90: ldc 144
    //   92: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: astore 10
    //   100: new 53	java/io/File
    //   103: dup
    //   104: aload 10
    //   106: aload_1
    //   107: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   110: astore 11
    //   112: new 233	java/io/FileInputStream
    //   115: dup
    //   116: aload 11
    //   118: invokespecial 237	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   121: astore 12
    //   123: new 221	android/graphics/drawable/BitmapDrawable
    //   126: dup
    //   127: aload 12
    //   129: invokespecial 224	android/graphics/drawable/BitmapDrawable:<init>	(Ljava/io/InputStream;)V
    //   132: astore 4
    //   134: aload 12
    //   136: ifnull +94 -> 230
    //   139: aload 12
    //   141: invokevirtual 236	java/io/FileInputStream:close	()V
    //   144: aload 4
    //   146: astore_2
    //   147: aload_3
    //   148: ifnull -119 -> 29
    //   151: aload_3
    //   152: invokevirtual 236	java/io/FileInputStream:close	()V
    //   155: goto -126 -> 29
    //   158: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   161: goto -132 -> 29
    //   164: astore 13
    //   166: aload 8
    //   168: ifnull -21 -> 147
    //   171: aload 8
    //   173: invokevirtual 236	java/io/FileInputStream:close	()V
    //   176: goto -29 -> 147
    //   179: astore 14
    //   181: aload 14
    //   183: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   186: goto -39 -> 147
    //   189: astore 7
    //   191: aload_3
    //   192: ifnull +7 -> 199
    //   195: aload_3
    //   196: invokevirtual 236	java/io/FileInputStream:close	()V
    //   199: aload 7
    //   201: athrow
    //   202: astore 7
    //   204: aload 8
    //   206: ifnull +8 -> 214
    //   209: aload 8
    //   211: invokevirtual 236	java/io/FileInputStream:close	()V
    //   214: aload 7
    //   216: athrow
    //   217: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   220: goto -6 -> 214
    //   223: astore 14
    //   225: aload 14
    //   227: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   230: aload 4
    //   232: astore_2
    //   233: goto -86 -> 147
    //   236: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   239: goto -40 -> 199
    //   242: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   245: aload 4
    //   247: astore_2
    //   248: goto -219 -> 29
    //   251: astore 7
    //   253: aload 4
    //   255: astore 15
    //   257: goto -66 -> 191
    //   260: astore 7
    //   262: aload 12
    //   264: astore 8
    //   266: goto -62 -> 204
    //   269: astore 16
    //   271: aload 12
    //   273: astore 8
    //   275: goto -109 -> 166
    //
    // Exception table:
    //   from	to	target	type
    //   2	18	31	java/lang/OutOfMemoryError
    //   37	41	44	java/io/IOException
    //   2	18	50	java/io/FileNotFoundException
    //   151	155	158	java/io/IOException
    //   70	123	164	java/lang/Throwable
    //   171	176	179	java/io/IOException
    //   2	18	189	finally
    //   52	62	189	finally
    //   171	176	189	finally
    //   181	186	189	finally
    //   209	214	189	finally
    //   214	220	189	finally
    //   70	123	202	finally
    //   209	214	217	java/io/IOException
    //   139	144	223	java/io/IOException
    //   195	199	236	java/io/IOException
    //   22	26	242	java/io/IOException
    //   139	144	251	finally
    //   225	230	251	finally
    //   123	134	260	finally
    //   123	134	269	java/lang/Throwable
  }

  private static void readTempFileTimestampRecordFromFile(Context paramContext)
  {
    modifyTimeRec = new HashMap();
    File localFile2;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile1 = Environment.getExternalStorageDirectory();
      if (new File(localFile1, "Yingyonghui").exists())
      {
        String str1 = String.valueOf(Environment.getExternalStorageDirectory().toString());
        String str2 = str1 + "/Yingyonghui";
        localFile2 = new File(str2, "tempFileModifiedTimeStamps");
        if (!localFile2.exists());
      }
    }
    while (true)
    {
      BufferedReader localBufferedReader;
      String str3;
      try
      {
        FileInputStream localFileInputStream1 = new FileInputStream(localFile2);
        InputStreamReader localInputStreamReader1 = new InputStreamReader(localFileInputStream1);
        localBufferedReader = new BufferedReader(localInputStreamReader1);
        str3 = localBufferedReader.readLine();
        if (str3 == null)
          return;
        if (str3.trim() == "")
          continue;
        String[] arrayOfString1 = str3.split("#");
        HashMap localHashMap1 = modifyTimeRec;
        String str4 = arrayOfString1[0];
        String str5 = arrayOfString1[1];
        localHashMap1.put(str4, str5);
        str3 = localBufferedReader.readLine();
        continue;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
        continue;
      }
      localFile2 = paramContext.getFileStreamPath("tempFileModifiedTimeStamps");
      if ((localFile2 == null) || (!localFile2.exists()))
        continue;
      try
      {
        FileInputStream localFileInputStream2 = new FileInputStream(localFile2);
        InputStreamReader localInputStreamReader2 = new InputStreamReader(localFileInputStream2);
        localBufferedReader = new BufferedReader(localInputStreamReader2);
        for (str3 = localBufferedReader.readLine(); str3 != null; str3 = localBufferedReader.readLine())
        {
          if (str3.trim() == "")
            continue;
          String[] arrayOfString2 = str3.split("#");
          HashMap localHashMap2 = modifyTimeRec;
          String str6 = arrayOfString2[0];
          String str7 = arrayOfString2[1];
          localHashMap2.put(str6, str7);
        }
      }
      catch (Throwable localThrowable2)
      {
        localThrowable2.printStackTrace();
      }
    }
  }

  // ERROR //
  public static String readTimeStampFromFileSystem(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 190
    //   2: astore_2
    //   3: bipush 100
    //   5: newarray byte
    //   7: astore_3
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual 219	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   13: astore 4
    //   15: aload 4
    //   17: aload_3
    //   18: iconst_0
    //   19: bipush 100
    //   21: invokevirtual 275	java/io/FileInputStream:read	([BII)I
    //   24: istore 5
    //   26: new 67	java/lang/String
    //   29: dup
    //   30: aload_3
    //   31: iconst_0
    //   32: iload 5
    //   34: ldc_w 277
    //   37: invokespecial 280	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   40: astore 6
    //   42: aload 4
    //   44: ifnull +263 -> 307
    //   47: aload 4
    //   49: invokevirtual 236	java/io/FileInputStream:close	()V
    //   52: aload 6
    //   54: astore_2
    //   55: aload_2
    //   56: areturn
    //   57: astore 7
    //   59: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   62: ldc 65
    //   64: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   67: istore 8
    //   69: iload 8
    //   71: ifeq +108 -> 179
    //   74: aconst_null
    //   75: astore 9
    //   77: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   80: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   83: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   86: astore 10
    //   88: new 125	java/lang/StringBuilder
    //   91: dup
    //   92: aload 10
    //   94: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   97: ldc 144
    //   99: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: astore 11
    //   107: new 53	java/io/File
    //   110: dup
    //   111: aload 11
    //   113: aload_1
    //   114: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   117: astore 12
    //   119: new 233	java/io/FileInputStream
    //   122: dup
    //   123: aload 12
    //   125: invokespecial 237	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   128: astore 13
    //   130: ldc 225
    //   132: istore 8
    //   134: bipush 100
    //   136: istore 14
    //   138: aload 13
    //   140: aload_3
    //   141: iload 8
    //   143: iload 14
    //   145: invokevirtual 275	java/io/FileInputStream:read	([BII)I
    //   148: istore 15
    //   150: new 67	java/lang/String
    //   153: dup
    //   154: aload_3
    //   155: iconst_0
    //   156: iload 15
    //   158: ldc_w 277
    //   161: invokespecial 280	java/lang/String:<init>	([BIILjava/lang/String;)V
    //   164: astore 6
    //   166: aload 13
    //   168: ifnull +98 -> 266
    //   171: aload 13
    //   173: invokevirtual 236	java/io/FileInputStream:close	()V
    //   176: aload 6
    //   178: astore_2
    //   179: aload 4
    //   181: ifnull -126 -> 55
    //   184: aload 4
    //   186: invokevirtual 236	java/io/FileInputStream:close	()V
    //   189: goto -134 -> 55
    //   192: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   195: goto -140 -> 55
    //   198: astore 16
    //   200: aload 9
    //   202: ifnull -23 -> 179
    //   205: aload 9
    //   207: invokevirtual 236	java/io/FileInputStream:close	()V
    //   210: goto -31 -> 179
    //   213: astore 17
    //   215: aload 17
    //   217: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   220: goto -41 -> 179
    //   223: astore 8
    //   225: aload 4
    //   227: ifnull +8 -> 235
    //   230: aload 4
    //   232: invokevirtual 236	java/io/FileInputStream:close	()V
    //   235: aload 8
    //   237: athrow
    //   238: astore 8
    //   240: aload 9
    //   242: ifnull +8 -> 250
    //   245: aload 9
    //   247: invokevirtual 236	java/io/FileInputStream:close	()V
    //   250: aload 8
    //   252: athrow
    //   253: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   256: goto -6 -> 250
    //   259: astore 17
    //   261: aload 17
    //   263: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   266: aload 6
    //   268: astore_2
    //   269: goto -90 -> 179
    //   272: astore 18
    //   274: aload 18
    //   276: invokevirtual 198	java/lang/Throwable:printStackTrace	()V
    //   279: aload 4
    //   281: ifnull -226 -> 55
    //   284: aload 4
    //   286: invokevirtual 236	java/io/FileInputStream:close	()V
    //   289: goto -234 -> 55
    //   292: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   295: goto -240 -> 55
    //   298: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   301: goto -66 -> 235
    //   304: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   307: aload 6
    //   309: astore_2
    //   310: goto -255 -> 55
    //   313: astore 8
    //   315: aload 6
    //   317: astore 19
    //   319: goto -94 -> 225
    //   322: astore 8
    //   324: aload 13
    //   326: astore 9
    //   328: goto -88 -> 240
    //   331: astore 20
    //   333: aload 13
    //   335: astore 9
    //   337: goto -137 -> 200
    //
    // Exception table:
    //   from	to	target	type
    //   8	42	57	java/io/FileNotFoundException
    //   184	189	192	java/io/IOException
    //   77	130	198	java/lang/Throwable
    //   205	210	213	java/io/IOException
    //   8	42	223	finally
    //   59	69	223	finally
    //   205	210	223	finally
    //   215	220	223	finally
    //   245	250	223	finally
    //   250	256	223	finally
    //   274	279	223	finally
    //   77	130	238	finally
    //   245	250	253	java/io/IOException
    //   171	176	259	java/io/IOException
    //   8	42	272	java/lang/Throwable
    //   284	289	292	java/io/IOException
    //   230	235	298	java/io/IOException
    //   47	52	304	java/io/IOException
    //   171	176	313	finally
    //   261	266	313	finally
    //   138	166	322	finally
    //   138	166	331	java/lang/Throwable
  }

  public static void removeTempFile(Context paramContext, String paramString)
  {
    if (Environment.getExternalStorageState() == "mounted");
    while (true)
    {
      File localFile2;
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        File localFile1 = Environment.getExternalStorageDirectory();
        String str = localFile1 + "/Yingyonghui";
        localFile2 = new File(str, paramString);
        if (!localFile2.exists())
          continue;
        localFile2.delete();
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
        continue;
      }
      try
      {
        localFile2 = paramContext.getFileStreamPath(paramString);
        if (!localFile2.exists())
          continue;
        localFile2.delete();
      }
      catch (Throwable localThrowable2)
      {
        localThrowable2.printStackTrace();
      }
    }
  }

  public static void removeTimestampforApk(Context paramContext, String paramString)
  {
    if ((modifyTimeRec != null) && (modifyTimeRec.get(paramString) != null))
      modifyTimeRec.remove(paramString);
  }

  @Signature({"(", "Landroid/content/Context;", "I", "Ljava/lang/String;", "Ljava/util/HashMap", "<", "Ljava/lang/Integer;", "Ljava/lang/String;", ">;)V"})
  public static void renameFileToEndWithApk(Context paramContext, int paramInt, String paramString, HashMap paramHashMap)
    throws FileNotFoundException
  {
    try
    {
      Integer localInteger1 = Integer.valueOf(paramInt);
      String str1 = (String)paramHashMap.get(localInteger1);
      File localFile1 = new File(str1);
      String str2 = String.valueOf(str1);
      String str3 = str2 + ".apk";
      File localFile2 = new File(str3);
      if (localFile1.exists())
      {
        localFile1.renameTo(localFile2);
        Integer localInteger2 = Integer.valueOf(paramInt);
        paramHashMap.put(localInteger2, str3);
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static boolean timestampEqual(Context paramContext, String paramString1, String paramString2)
  {
    int i = 0;
    if (modifyTimeRec != null)
    {
      String str1 = (String)modifyTimeRec.get(paramString1);
      if (!paramString2.equals(str1));
    }
    for (i = 1; ; i = 1)
    {
      String str2;
      do
      {
        return i;
        readTempFileTimestampRecordFromFile(paramContext);
        str2 = (String)modifyTimeRec.get(paramString1);
      }
      while ((paramString2 == null) || (!paramString2.trim().equals(str2)));
    }
  }

  // ERROR //
  public static void writeDrawableToFileSystem(Context paramContext, String paramString, android.graphics.drawable.Drawable paramDrawable)
  {
    // Byte code:
    //   0: aload_2
    //   1: instanceof 221
    //   4: ifeq +133 -> 137
    //   7: aload_2
    //   8: checkcast 221	android/graphics/drawable/BitmapDrawable
    //   11: astore_3
    //   12: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   15: ldc 65
    //   17: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +187 -> 207
    //   23: aconst_null
    //   24: astore 4
    //   26: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   29: astore 5
    //   31: new 53	java/io/File
    //   34: dup
    //   35: aload 5
    //   37: ldc 76
    //   39: invokespecial 79	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   42: invokevirtual 120	java/io/File:mkdirs	()Z
    //   45: pop
    //   46: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   49: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   52: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   55: astore 6
    //   57: new 125	java/lang/StringBuilder
    //   60: dup
    //   61: aload 6
    //   63: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   66: ldc 144
    //   68: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: astore 7
    //   76: new 53	java/io/File
    //   79: dup
    //   80: aload 7
    //   82: aload_1
    //   83: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   86: astore 8
    //   88: new 194	java/io/FileOutputStream
    //   91: dup
    //   92: aload 8
    //   94: invokespecial 197	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   97: astore 9
    //   99: aload_3
    //   100: invokevirtual 305	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   103: astore 10
    //   105: getstatic 311	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   108: astore 11
    //   110: aload 10
    //   112: aload 11
    //   114: bipush 100
    //   116: aload 9
    //   118: invokevirtual 317	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   121: pop
    //   122: aload 9
    //   124: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   127: aload 9
    //   129: ifnull +8 -> 137
    //   132: aload 9
    //   134: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   137: return
    //   138: astore 12
    //   140: aload 4
    //   142: ifnull -5 -> 137
    //   145: aload 4
    //   147: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   150: goto -13 -> 137
    //   153: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   156: goto -19 -> 137
    //   159: astore 13
    //   161: aload 4
    //   163: ifnull -26 -> 137
    //   166: aload 4
    //   168: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   171: goto -34 -> 137
    //   174: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   177: goto -40 -> 137
    //   180: astore 14
    //   182: aload 4
    //   184: ifnull +8 -> 192
    //   187: aload 4
    //   189: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   192: aload 14
    //   194: athrow
    //   195: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   198: goto -6 -> 192
    //   201: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   204: goto -67 -> 137
    //   207: iconst_1
    //   208: istore 14
    //   210: aload_0
    //   211: aload_1
    //   212: iload 14
    //   214: invokevirtual 161	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   217: astore 15
    //   219: aload_3
    //   220: invokevirtual 305	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   223: astore 16
    //   225: getstatic 311	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   228: astore 17
    //   230: aload 16
    //   232: aload 17
    //   234: bipush 100
    //   236: aload 15
    //   238: invokevirtual 317	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   241: pop
    //   242: aload 15
    //   244: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   247: aload 15
    //   249: ifnull -112 -> 137
    //   252: aload 15
    //   254: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   257: goto -120 -> 137
    //   260: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   263: goto -126 -> 137
    //   266: astore 18
    //   268: aload 15
    //   270: ifnull -133 -> 137
    //   273: aload 15
    //   275: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   278: goto -141 -> 137
    //   281: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   284: goto -147 -> 137
    //   287: astore 19
    //   289: aload 15
    //   291: ifnull -154 -> 137
    //   294: aload 15
    //   296: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   299: goto -162 -> 137
    //   302: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   305: goto -168 -> 137
    //   308: astore 14
    //   310: aload 15
    //   312: ifnull +8 -> 320
    //   315: aload 15
    //   317: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   320: aload 14
    //   322: athrow
    //   323: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   326: goto -6 -> 320
    //   329: astore 14
    //   331: aload 9
    //   333: astore 4
    //   335: goto -153 -> 182
    //   338: astore 20
    //   340: aload 9
    //   342: astore 4
    //   344: goto -183 -> 161
    //   347: astore 21
    //   349: aload 9
    //   351: astore 4
    //   353: goto -213 -> 140
    //
    // Exception table:
    //   from	to	target	type
    //   26	99	138	java/io/FileNotFoundException
    //   145	150	153	java/io/IOException
    //   26	99	159	java/lang/Exception
    //   166	171	174	java/io/IOException
    //   26	99	180	finally
    //   187	192	195	java/io/IOException
    //   132	137	201	java/io/IOException
    //   252	257	260	java/io/IOException
    //   210	247	266	java/io/FileNotFoundException
    //   273	278	281	java/io/IOException
    //   210	247	287	java/io/IOException
    //   294	299	302	java/io/IOException
    //   210	247	308	finally
    //   315	320	323	java/io/IOException
    //   99	127	329	finally
    //   99	127	338	java/lang/Exception
    //   99	127	347	java/io/FileNotFoundException
  }

  // ERROR //
  public static void writeFirstScreetShotToFileSystem(Context paramContext, int paramInt, android.graphics.drawable.Drawable paramDrawable)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_2
    //   6: instanceof 221
    //   9: ifeq -5 -> 4
    //   12: aload_2
    //   13: checkcast 221	android/graphics/drawable/BitmapDrawable
    //   16: astore_3
    //   17: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   20: ldc 65
    //   22: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   25: ifeq -21 -> 4
    //   28: new 125	java/lang/StringBuilder
    //   31: dup
    //   32: ldc 15
    //   34: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   37: iload_1
    //   38: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: ldc 243
    //   43: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: astore 4
    //   51: aconst_null
    //   52: astore 5
    //   54: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   57: astore 6
    //   59: new 53	java/io/File
    //   62: dup
    //   63: aload 6
    //   65: ldc 76
    //   67: invokespecial 79	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   70: invokevirtual 120	java/io/File:mkdirs	()Z
    //   73: pop
    //   74: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   77: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   80: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   83: astore 7
    //   85: new 125	java/lang/StringBuilder
    //   88: dup
    //   89: aload 7
    //   91: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: ldc 144
    //   96: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: astore 8
    //   104: new 53	java/io/File
    //   107: dup
    //   108: aload 8
    //   110: aload 4
    //   112: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: astore 9
    //   117: new 194	java/io/FileOutputStream
    //   120: dup
    //   121: aload 9
    //   123: invokespecial 197	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   126: astore 10
    //   128: aload_3
    //   129: ifnull +31 -> 160
    //   132: aload 10
    //   134: ifnull +26 -> 160
    //   137: aload_3
    //   138: invokevirtual 305	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   141: astore 11
    //   143: getstatic 311	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   146: astore 12
    //   148: aload 11
    //   150: aload 12
    //   152: bipush 100
    //   154: aload 10
    //   156: invokevirtual 317	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   159: pop
    //   160: aload 10
    //   162: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   165: aload 10
    //   167: ifnull -163 -> 4
    //   170: aload 10
    //   172: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   175: goto -171 -> 4
    //   178: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   181: goto -177 -> 4
    //   184: astore 13
    //   186: aload 5
    //   188: ifnull -184 -> 4
    //   191: aload 5
    //   193: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   196: goto -192 -> 4
    //   199: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   202: goto -198 -> 4
    //   205: astore 14
    //   207: aload 14
    //   209: invokevirtual 198	java/lang/Throwable:printStackTrace	()V
    //   212: aload 5
    //   214: ifnull -210 -> 4
    //   217: aload 5
    //   219: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   222: goto -218 -> 4
    //   225: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   228: goto -224 -> 4
    //   231: astore 15
    //   233: aload 5
    //   235: ifnull +8 -> 243
    //   238: aload 5
    //   240: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   243: aload 15
    //   245: athrow
    //   246: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   249: goto -6 -> 243
    //   252: astore 15
    //   254: aload 10
    //   256: astore 5
    //   258: goto -25 -> 233
    //   261: astore 14
    //   263: aload 10
    //   265: astore 5
    //   267: goto -60 -> 207
    //   270: astore 16
    //   272: aload 10
    //   274: astore 5
    //   276: goto -90 -> 186
    //
    // Exception table:
    //   from	to	target	type
    //   170	175	178	java/io/IOException
    //   54	128	184	java/lang/NullPointerException
    //   191	196	199	java/io/IOException
    //   54	128	205	java/lang/Throwable
    //   217	222	225	java/io/IOException
    //   54	128	231	finally
    //   207	212	231	finally
    //   238	243	246	java/io/IOException
    //   137	165	252	finally
    //   137	165	261	java/lang/Throwable
    //   137	165	270	java/lang/NullPointerException
  }

  // ERROR //
  public static void writeIconToFileSystem(Context paramContext, int paramInt, android.graphics.drawable.Drawable paramDrawable)
  {
    // Byte code:
    //   0: aload_2
    //   1: instanceof 221
    //   4: ifeq +152 -> 156
    //   7: aload_2
    //   8: checkcast 221	android/graphics/drawable/BitmapDrawable
    //   11: astore_3
    //   12: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   15: ldc 65
    //   17: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +206 -> 226
    //   23: new 125	java/lang/StringBuilder
    //   26: dup
    //   27: ldc 12
    //   29: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   32: iload_1
    //   33: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: astore 4
    //   41: aconst_null
    //   42: astore 5
    //   44: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   47: astore 6
    //   49: new 53	java/io/File
    //   52: dup
    //   53: aload 6
    //   55: ldc 76
    //   57: invokespecial 79	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   60: invokevirtual 120	java/io/File:mkdirs	()Z
    //   63: pop
    //   64: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   67: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   70: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   73: astore 7
    //   75: new 125	java/lang/StringBuilder
    //   78: dup
    //   79: aload 7
    //   81: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   84: ldc 144
    //   86: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore 8
    //   94: new 53	java/io/File
    //   97: dup
    //   98: aload 8
    //   100: aload 4
    //   102: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   105: astore 9
    //   107: new 194	java/io/FileOutputStream
    //   110: dup
    //   111: aload 9
    //   113: invokespecial 197	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   116: astore 10
    //   118: aload_3
    //   119: invokevirtual 305	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   122: astore 11
    //   124: getstatic 329	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   127: astore 12
    //   129: aload 11
    //   131: aload 12
    //   133: bipush 100
    //   135: aload 10
    //   137: invokevirtual 317	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   140: pop
    //   141: aload 10
    //   143: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   146: aload 10
    //   148: ifnull +8 -> 156
    //   151: aload 10
    //   153: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   156: return
    //   157: astore 13
    //   159: aload 5
    //   161: ifnull -5 -> 156
    //   164: aload 5
    //   166: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   169: goto -13 -> 156
    //   172: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   175: goto -19 -> 156
    //   178: astore 14
    //   180: aload 5
    //   182: ifnull -26 -> 156
    //   185: aload 5
    //   187: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   190: goto -34 -> 156
    //   193: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   196: goto -40 -> 156
    //   199: astore 15
    //   201: aload 5
    //   203: ifnull +8 -> 211
    //   206: aload 5
    //   208: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   211: aload 15
    //   213: athrow
    //   214: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   217: goto -6 -> 211
    //   220: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   223: goto -67 -> 156
    //   226: new 125	java/lang/StringBuilder
    //   229: dup
    //   230: ldc 12
    //   232: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   235: iload_1
    //   236: invokevirtual 215	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   239: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: astore 16
    //   244: aload_0
    //   245: aload 16
    //   247: iconst_1
    //   248: invokevirtual 161	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   251: astore 17
    //   253: aload_3
    //   254: invokevirtual 305	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   257: astore 18
    //   259: getstatic 329	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   262: astore 19
    //   264: aload 18
    //   266: aload 19
    //   268: bipush 100
    //   270: aload 17
    //   272: invokevirtual 317	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   275: pop
    //   276: aload 17
    //   278: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   281: aload 17
    //   283: ifnull -127 -> 156
    //   286: aload 17
    //   288: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   291: goto -135 -> 156
    //   294: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   297: goto -141 -> 156
    //   300: astore 20
    //   302: aload 17
    //   304: ifnull -148 -> 156
    //   307: aload 17
    //   309: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   312: goto -156 -> 156
    //   315: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   318: goto -162 -> 156
    //   321: astore 21
    //   323: aload 17
    //   325: ifnull -169 -> 156
    //   328: aload 17
    //   330: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   333: goto -177 -> 156
    //   336: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   339: goto -183 -> 156
    //   342: astore 15
    //   344: aload 17
    //   346: ifnull +8 -> 354
    //   349: aload 17
    //   351: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   354: aload 15
    //   356: athrow
    //   357: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   360: goto -6 -> 354
    //   363: astore 15
    //   365: aload 10
    //   367: astore 5
    //   369: goto -168 -> 201
    //   372: astore 22
    //   374: aload 10
    //   376: astore 5
    //   378: goto -198 -> 180
    //   381: astore 23
    //   383: aload 10
    //   385: astore 5
    //   387: goto -228 -> 159
    //
    // Exception table:
    //   from	to	target	type
    //   44	118	157	java/io/FileNotFoundException
    //   164	169	172	java/io/IOException
    //   44	118	178	java/io/IOException
    //   185	190	193	java/io/IOException
    //   44	118	199	finally
    //   206	211	214	java/io/IOException
    //   151	156	220	java/io/IOException
    //   286	291	294	java/io/IOException
    //   226	281	300	java/io/FileNotFoundException
    //   307	312	315	java/io/IOException
    //   226	281	321	java/io/IOException
    //   328	333	336	java/io/IOException
    //   226	281	342	finally
    //   349	354	357	java/io/IOException
    //   118	146	363	finally
    //   118	146	372	java/io/IOException
    //   118	146	381	java/io/FileNotFoundException
  }

  // ERROR //
  public static boolean writeStreamToFileSystem(Context paramContext, String paramString, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokestatic 333	com/yingyonghui/market/FileManager:getOutputStream	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/FileOutputStream;
    //   5: astore_3
    //   6: aload_2
    //   7: ifnull +7 -> 14
    //   10: aload_3
    //   11: ifnonnull +9 -> 20
    //   14: iconst_0
    //   15: istore 4
    //   17: iload 4
    //   19: ireturn
    //   20: sipush 8192
    //   23: istore 4
    //   25: iload 4
    //   27: newarray byte
    //   29: astore 5
    //   31: aload_2
    //   32: aload 5
    //   34: iconst_0
    //   35: sipush 8192
    //   38: invokevirtual 336	java/io/InputStream:read	([BII)I
    //   41: istore 6
    //   43: iload 6
    //   45: bipush 255
    //   47: if_icmpne +29 -> 76
    //   50: aload_3
    //   51: invokevirtual 339	java/io/OutputStream:flush	()V
    //   54: aload_2
    //   55: ifnull +7 -> 62
    //   58: aload_2
    //   59: invokevirtual 340	java/io/InputStream:close	()V
    //   62: aload_3
    //   63: ifnull +7 -> 70
    //   66: aload_3
    //   67: invokevirtual 341	java/io/OutputStream:close	()V
    //   70: iconst_1
    //   71: istore 4
    //   73: goto -56 -> 17
    //   76: iconst_0
    //   77: istore 4
    //   79: aload_3
    //   80: aload 5
    //   82: iload 4
    //   84: iload 6
    //   86: invokevirtual 345	java/io/OutputStream:write	([BII)V
    //   89: goto -58 -> 31
    //   92: astore 7
    //   94: aload 7
    //   96: invokevirtual 198	java/lang/Throwable:printStackTrace	()V
    //   99: aload_2
    //   100: ifnull +7 -> 107
    //   103: aload_2
    //   104: invokevirtual 340	java/io/InputStream:close	()V
    //   107: aload_3
    //   108: ifnull +7 -> 115
    //   111: aload_3
    //   112: invokevirtual 341	java/io/OutputStream:close	()V
    //   115: iconst_0
    //   116: istore 4
    //   118: goto -101 -> 17
    //   121: astore 4
    //   123: aload_2
    //   124: ifnull +7 -> 131
    //   127: aload_2
    //   128: invokevirtual 340	java/io/InputStream:close	()V
    //   131: aload_3
    //   132: ifnull +7 -> 139
    //   135: aload_3
    //   136: invokevirtual 341	java/io/OutputStream:close	()V
    //   139: aload 4
    //   141: athrow
    //   142: astore 8
    //   144: goto -5 -> 139
    //   147: astore 9
    //   149: goto -34 -> 115
    //   152: astore 10
    //   154: goto -84 -> 70
    //
    // Exception table:
    //   from	to	target	type
    //   25	54	92	java/lang/Throwable
    //   79	89	92	java/lang/Throwable
    //   25	54	121	finally
    //   79	89	121	finally
    //   94	99	121	finally
    //   127	139	142	java/io/IOException
    //   103	115	147	java/io/IOException
    //   58	70	152	java/io/IOException
  }

  // ERROR //
  public static void writeTimeStampToFileSystem(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aconst_null
    //   6: astore_3
    //   7: aload_2
    //   8: ldc_w 277
    //   11: invokevirtual 350	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   14: astore 4
    //   16: aload 4
    //   18: arraylength
    //   19: istore 5
    //   21: invokestatic 63	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   24: ldc 65
    //   26: invokevirtual 71	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   29: istore 6
    //   31: iload 6
    //   33: ifeq +203 -> 236
    //   36: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   39: astore 7
    //   41: new 53	java/io/File
    //   44: dup
    //   45: aload 7
    //   47: ldc 76
    //   49: invokespecial 79	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   52: invokevirtual 120	java/io/File:mkdirs	()Z
    //   55: pop
    //   56: invokestatic 74	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   59: invokevirtual 123	java/io/File:toString	()Ljava/lang/String;
    //   62: invokestatic 102	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   65: astore 8
    //   67: new 125	java/lang/StringBuilder
    //   70: dup
    //   71: aload 8
    //   73: invokespecial 128	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   76: ldc 144
    //   78: invokevirtual 134	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 9
    //   86: new 53	java/io/File
    //   89: dup
    //   90: aload 9
    //   92: aload_1
    //   93: invokespecial 145	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   96: astore 10
    //   98: new 194	java/io/FileOutputStream
    //   101: dup
    //   102: aload 10
    //   104: invokespecial 197	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   107: astore 11
    //   109: ldc 225
    //   111: istore 6
    //   113: aload 11
    //   115: aload 4
    //   117: iload 6
    //   119: iload 5
    //   121: invokevirtual 351	java/io/FileOutputStream:write	([BII)V
    //   124: aload 11
    //   126: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   129: aload 11
    //   131: ifnull -127 -> 4
    //   134: aload 11
    //   136: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   139: goto -135 -> 4
    //   142: astore 12
    //   144: aload 12
    //   146: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   149: goto -145 -> 4
    //   152: astore 12
    //   154: aload 11
    //   156: astore 13
    //   158: aload 12
    //   160: invokevirtual 244	java/lang/Exception:printStackTrace	()V
    //   163: goto -159 -> 4
    //   166: astore 14
    //   168: aload_3
    //   169: ifnull -165 -> 4
    //   172: aload_3
    //   173: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   176: goto -172 -> 4
    //   179: astore 12
    //   181: aload 12
    //   183: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   186: goto -182 -> 4
    //   189: astore 12
    //   191: goto -33 -> 158
    //   194: astore 15
    //   196: aload_3
    //   197: ifnull -193 -> 4
    //   200: aload_3
    //   201: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   204: goto -200 -> 4
    //   207: astore 12
    //   209: aload 12
    //   211: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   214: goto -210 -> 4
    //   217: astore 6
    //   219: aload_3
    //   220: ifnull +7 -> 227
    //   223: aload_3
    //   224: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   227: aload 6
    //   229: athrow
    //   230: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   233: goto -6 -> 227
    //   236: aconst_null
    //   237: astore 6
    //   239: aload_0
    //   240: aload_1
    //   241: aload 6
    //   243: invokevirtual 161	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   246: astore_3
    //   247: aload_3
    //   248: aload 4
    //   250: iconst_0
    //   251: iload 5
    //   253: invokevirtual 351	java/io/FileOutputStream:write	([BII)V
    //   256: aload_3
    //   257: invokevirtual 320	java/io/FileOutputStream:flush	()V
    //   260: aload_3
    //   261: ifnull -257 -> 4
    //   264: aload_3
    //   265: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   268: goto -264 -> 4
    //   271: astore 12
    //   273: aload 12
    //   275: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   278: goto -274 -> 4
    //   281: astore 16
    //   283: aload_3
    //   284: ifnull -280 -> 4
    //   287: aload_3
    //   288: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   291: goto -287 -> 4
    //   294: astore 12
    //   296: aload 12
    //   298: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   301: goto -297 -> 4
    //   304: astore 17
    //   306: aload_3
    //   307: ifnull -303 -> 4
    //   310: aload_3
    //   311: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   314: goto -310 -> 4
    //   317: astore 12
    //   319: aload 12
    //   321: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   324: goto -320 -> 4
    //   327: astore 6
    //   329: aload_3
    //   330: ifnull +7 -> 337
    //   333: aload_3
    //   334: invokevirtual 321	java/io/FileOutputStream:close	()V
    //   337: aload 6
    //   339: athrow
    //   340: invokevirtual 164	java/io/IOException:printStackTrace	()V
    //   343: goto -6 -> 337
    //   346: astore 6
    //   348: aload 11
    //   350: astore_3
    //   351: goto -132 -> 219
    //   354: astore 18
    //   356: aload 11
    //   358: astore_3
    //   359: goto -163 -> 196
    //   362: astore 19
    //   364: aload 11
    //   366: astore_3
    //   367: goto -199 -> 168
    //
    // Exception table:
    //   from	to	target	type
    //   134	139	142	java/io/IOException
    //   134	139	152	java/lang/Exception
    //   144	149	152	java/lang/Exception
    //   36	109	166	java/io/FileNotFoundException
    //   172	176	179	java/io/IOException
    //   7	31	189	java/lang/Exception
    //   172	176	189	java/lang/Exception
    //   181	186	189	java/lang/Exception
    //   200	204	189	java/lang/Exception
    //   209	214	189	java/lang/Exception
    //   223	227	189	java/lang/Exception
    //   227	233	189	java/lang/Exception
    //   264	268	189	java/lang/Exception
    //   273	278	189	java/lang/Exception
    //   287	291	189	java/lang/Exception
    //   296	301	189	java/lang/Exception
    //   310	314	189	java/lang/Exception
    //   319	324	189	java/lang/Exception
    //   333	337	189	java/lang/Exception
    //   337	343	189	java/lang/Exception
    //   36	109	194	java/io/IOException
    //   200	204	207	java/io/IOException
    //   36	109	217	finally
    //   223	227	230	java/io/IOException
    //   264	268	271	java/io/IOException
    //   239	260	281	java/io/FileNotFoundException
    //   287	291	294	java/io/IOException
    //   239	260	304	java/io/IOException
    //   310	314	317	java/io/IOException
    //   239	260	327	finally
    //   333	337	340	java/io/IOException
    //   113	129	346	finally
    //   113	129	354	java/io/IOException
    //   113	129	362	java/io/FileNotFoundException
  }

  public static void writeTimestampRecordToFileSystem(Context paramContext)
  {
    if (modifyTimeRec == null);
    while (true)
    {
      return;
      String str1 = "tempFileModifiedTimeStamps";
      FileOutputStream localFileOutputStream;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        try
        {
          File localFile1 = Environment.getExternalStorageDirectory();
          File localFile2 = new File(localFile1, "Yingyonghui");
          if (!localFile2.exists())
            localFile2.mkdir();
          StringBuilder localStringBuilder1 = new StringBuilder();
          File localFile3 = Environment.getExternalStorageDirectory();
          String str2 = localFile3 + "/Yingyonghui";
          File localFile4 = new File(str2, str1);
          if (localFile4.exists())
            localFile4.delete();
          localFileOutputStream = new FileOutputStream(localFile4);
          Iterator localIterator1 = modifyTimeRec.entrySet().iterator();
          while (localIterator1.hasNext())
          {
            Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
            String str3 = String.valueOf(localEntry1.getKey().toString());
            StringBuilder localStringBuilder2 = new StringBuilder(str3).append("#");
            String str4 = localEntry1.getValue().toString();
            StringBuilder localStringBuilder3 = localStringBuilder2.append(str4);
            String str5 = System.getProperty("line.separator");
            byte[] arrayOfByte1 = str5.getBytes();
            localFileOutputStream.write(arrayOfByte1);
          }
        }
        catch (Throwable localThrowable1)
        {
          localThrowable1.printStackTrace();
        }
        continue;
      }
      if (paramContext.getFileStreamPath(str1).exists())
        paramContext.getFileStreamPath(str1).delete();
      int i = 1;
      try
      {
        localFileOutputStream = paramContext.openFileOutput(str1, i);
        Iterator localIterator2 = modifyTimeRec.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
          String str6 = String.valueOf(localEntry2.getKey().toString());
          StringBuilder localStringBuilder4 = new StringBuilder(str6).append("-");
          String str7 = localEntry2.getValue().toString();
          StringBuilder localStringBuilder5 = localStringBuilder4.append(str7);
          String str8 = System.getProperty("line.separator");
          byte[] arrayOfByte2 = str8.getBytes();
          localFileOutputStream.write(arrayOfByte2);
        }
      }
      catch (Throwable localThrowable2)
      {
        localThrowable2.printStackTrace();
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.FileManager
 * JD-Core Version:    0.6.0
 */