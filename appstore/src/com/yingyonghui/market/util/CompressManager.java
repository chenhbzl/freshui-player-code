package com.yingyonghui.market.util;

public class CompressManager
{
  // ERROR //
  public static byte[] compress(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: newarray byte
    //   3: astore_1
    //   4: new 17	java/util/zip/Deflater
    //   7: dup
    //   8: invokespecial 18	java/util/zip/Deflater:<init>	()V
    //   11: astore_2
    //   12: aload_2
    //   13: invokevirtual 21	java/util/zip/Deflater:reset	()V
    //   16: aload_2
    //   17: aload_0
    //   18: invokevirtual 25	java/util/zip/Deflater:setInput	([B)V
    //   21: aload_2
    //   22: invokevirtual 28	java/util/zip/Deflater:finish	()V
    //   25: aload_0
    //   26: arraylength
    //   27: istore_3
    //   28: new 30	java/io/ByteArrayOutputStream
    //   31: dup
    //   32: iload_3
    //   33: invokespecial 33	java/io/ByteArrayOutputStream:<init>	(I)V
    //   36: astore 4
    //   38: sipush 1024
    //   41: newarray byte
    //   43: astore 5
    //   45: aload_2
    //   46: invokevirtual 37	java/util/zip/Deflater:finished	()Z
    //   49: ifeq +22 -> 71
    //   52: aload 4
    //   54: invokevirtual 41	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   57: astore 6
    //   59: aload 4
    //   61: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   64: aload_2
    //   65: invokevirtual 47	java/util/zip/Deflater:end	()V
    //   68: aload 6
    //   70: areturn
    //   71: aload_2
    //   72: aload 5
    //   74: invokevirtual 51	java/util/zip/Deflater:deflate	([B)I
    //   77: istore 7
    //   79: aload 4
    //   81: aload 5
    //   83: iconst_0
    //   84: iload 7
    //   86: invokevirtual 55	java/io/ByteArrayOutputStream:write	([BII)V
    //   89: goto -44 -> 45
    //   92: astore 8
    //   94: aload_0
    //   95: astore 6
    //   97: aload 8
    //   99: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   102: aload 4
    //   104: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   107: goto -43 -> 64
    //   110: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   113: goto -49 -> 64
    //   116: astore 9
    //   118: aload 4
    //   120: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   123: aload 9
    //   125: athrow
    //   126: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   129: goto -6 -> 123
    //   132: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   135: goto -71 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   38	59	92	java/lang/Exception
    //   71	89	92	java/lang/Exception
    //   102	107	110	java/io/IOException
    //   38	59	116	finally
    //   71	89	116	finally
    //   97	102	116	finally
    //   118	123	126	java/io/IOException
    //   59	64	132	java/io/IOException
  }

  // ERROR //
  public static byte[] decompress(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: newarray byte
    //   3: astore_1
    //   4: new 62	java/util/zip/Inflater
    //   7: dup
    //   8: invokespecial 63	java/util/zip/Inflater:<init>	()V
    //   11: astore_2
    //   12: aload_2
    //   13: invokevirtual 64	java/util/zip/Inflater:reset	()V
    //   16: aload_2
    //   17: aload_0
    //   18: invokevirtual 65	java/util/zip/Inflater:setInput	([B)V
    //   21: aload_0
    //   22: arraylength
    //   23: istore_3
    //   24: new 30	java/io/ByteArrayOutputStream
    //   27: dup
    //   28: iload_3
    //   29: invokespecial 33	java/io/ByteArrayOutputStream:<init>	(I)V
    //   32: astore 4
    //   34: sipush 1024
    //   37: newarray byte
    //   39: astore 5
    //   41: aload_2
    //   42: invokevirtual 66	java/util/zip/Inflater:finished	()Z
    //   45: ifeq +22 -> 67
    //   48: aload 4
    //   50: invokevirtual 41	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   53: astore 6
    //   55: aload 4
    //   57: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   60: aload_2
    //   61: invokevirtual 67	java/util/zip/Inflater:end	()V
    //   64: aload 6
    //   66: areturn
    //   67: aload_2
    //   68: aload 5
    //   70: invokevirtual 70	java/util/zip/Inflater:inflate	([B)I
    //   73: istore 7
    //   75: aload 4
    //   77: aload 5
    //   79: iconst_0
    //   80: iload 7
    //   82: invokevirtual 55	java/io/ByteArrayOutputStream:write	([BII)V
    //   85: goto -44 -> 41
    //   88: astore 8
    //   90: aload_0
    //   91: astore 6
    //   93: aload 8
    //   95: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   98: aload 4
    //   100: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   103: goto -43 -> 60
    //   106: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   109: goto -49 -> 60
    //   112: astore 9
    //   114: aload 4
    //   116: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   119: aload 9
    //   121: athrow
    //   122: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   125: goto -6 -> 119
    //   128: invokevirtual 59	java/io/IOException:printStackTrace	()V
    //   131: goto -71 -> 60
    //
    // Exception table:
    //   from	to	target	type
    //   34	55	88	java/lang/Exception
    //   67	85	88	java/lang/Exception
    //   98	103	106	java/io/IOException
    //   34	55	112	finally
    //   67	85	112	finally
    //   93	98	112	finally
    //   114	119	122	java/io/IOException
    //   55	60	128	java/io/IOException
  }

  // ERROR //
  public byte[] compressData(java.io.OutputStream paramOutputStream)
    throws java.io.IOException
  {
    // Byte code:
    //   0: iconst_0
    //   1: newarray byte
    //   3: astore_2
    //   4: new 17	java/util/zip/Deflater
    //   7: dup
    //   8: invokespecial 18	java/util/zip/Deflater:<init>	()V
    //   11: astore_3
    //   12: aconst_null
    //   13: astore 4
    //   15: new 76	java/util/zip/DeflaterOutputStream
    //   18: dup
    //   19: aload_1
    //   20: aload_3
    //   21: invokespecial 79	java/util/zip/DeflaterOutputStream:<init>	(Ljava/io/OutputStream;Ljava/util/zip/Deflater;)V
    //   24: astore 5
    //   26: aload 5
    //   28: aload_2
    //   29: invokevirtual 81	java/util/zip/DeflaterOutputStream:write	([B)V
    //   32: aload_1
    //   33: invokevirtual 84	java/io/OutputStream:close	()V
    //   36: aload 5
    //   38: invokevirtual 85	java/util/zip/DeflaterOutputStream:close	()V
    //   41: aload 5
    //   43: astore 6
    //   45: aload_2
    //   46: areturn
    //   47: astore 7
    //   49: iconst_0
    //   50: istore 8
    //   52: aload 8
    //   54: checkcast 87	[B
    //   57: astore_2
    //   58: aload 7
    //   60: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   63: aload_1
    //   64: invokevirtual 84	java/io/OutputStream:close	()V
    //   67: aload 4
    //   69: invokevirtual 85	java/util/zip/DeflaterOutputStream:close	()V
    //   72: goto -27 -> 45
    //   75: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   78: goto -33 -> 45
    //   81: astore 8
    //   83: aload_1
    //   84: invokevirtual 84	java/io/OutputStream:close	()V
    //   87: aload 4
    //   89: invokevirtual 85	java/util/zip/DeflaterOutputStream:close	()V
    //   92: aload 8
    //   94: athrow
    //   95: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   98: goto -6 -> 92
    //   101: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   104: aload 5
    //   106: astore 9
    //   108: goto -63 -> 45
    //   111: astore 8
    //   113: aload 5
    //   115: astore 4
    //   117: goto -34 -> 83
    //   120: astore 7
    //   122: aload 5
    //   124: astore 4
    //   126: goto -77 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   15	26	47	java/lang/Exception
    //   63	72	75	java/lang/Throwable
    //   15	26	81	finally
    //   52	63	81	finally
    //   83	92	95	java/lang/Throwable
    //   32	41	101	java/lang/Throwable
    //   26	32	111	finally
    //   26	32	120	java/lang/Exception
  }

  // ERROR //
  public byte[] decompressData(java.io.InputStream paramInputStream)
    throws java.io.IOException
  {
    // Byte code:
    //   0: iconst_0
    //   1: newarray byte
    //   3: astore_2
    //   4: aconst_null
    //   5: astore_3
    //   6: aconst_null
    //   7: astore 4
    //   9: new 92	java/util/zip/InflaterInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 95	java/util/zip/InflaterInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore 5
    //   19: new 30	java/io/ByteArrayOutputStream
    //   22: dup
    //   23: sipush 512
    //   26: invokespecial 33	java/io/ByteArrayOutputStream:<init>	(I)V
    //   29: astore 6
    //   31: aload 5
    //   33: invokevirtual 99	java/util/zip/InflaterInputStream:read	()I
    //   36: istore 7
    //   38: iload 7
    //   40: bipush 255
    //   42: if_icmpne +31 -> 73
    //   45: aload 6
    //   47: invokevirtual 41	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   50: astore 8
    //   52: aload 5
    //   54: invokevirtual 100	java/util/zip/InflaterInputStream:close	()V
    //   57: aload 6
    //   59: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   62: aload 6
    //   64: astore 9
    //   66: aload 5
    //   68: astore 10
    //   70: aload 8
    //   72: areturn
    //   73: aload 6
    //   75: iload 7
    //   77: invokevirtual 102	java/io/ByteArrayOutputStream:write	(I)V
    //   80: goto -49 -> 31
    //   83: astore 11
    //   85: aload 6
    //   87: astore 4
    //   89: aload 5
    //   91: astore_3
    //   92: iconst_0
    //   93: istore 12
    //   95: aload 12
    //   97: checkcast 87	[B
    //   100: astore 8
    //   102: aload 11
    //   104: invokevirtual 58	java/lang/Exception:printStackTrace	()V
    //   107: aload_3
    //   108: invokevirtual 100	java/util/zip/InflaterInputStream:close	()V
    //   111: aload 4
    //   113: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   116: goto -46 -> 70
    //   119: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   122: goto -52 -> 70
    //   125: astore 12
    //   127: aload_3
    //   128: invokevirtual 100	java/util/zip/InflaterInputStream:close	()V
    //   131: aload 4
    //   133: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   136: aload 12
    //   138: athrow
    //   139: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   142: goto -6 -> 136
    //   145: invokevirtual 88	java/lang/Throwable:printStackTrace	()V
    //   148: aload 6
    //   150: astore 13
    //   152: aload 5
    //   154: astore 14
    //   156: goto -86 -> 70
    //   159: astore 12
    //   161: aload 5
    //   163: astore_3
    //   164: goto -37 -> 127
    //   167: astore 12
    //   169: aload 6
    //   171: astore 4
    //   173: aload 5
    //   175: astore_3
    //   176: goto -49 -> 127
    //   179: astore 11
    //   181: goto -89 -> 92
    //   184: astore 11
    //   186: aload 5
    //   188: astore_3
    //   189: goto -97 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   31	52	83	java/lang/Exception
    //   73	80	83	java/lang/Exception
    //   107	116	119	java/lang/Throwable
    //   9	19	125	finally
    //   95	107	125	finally
    //   127	136	139	java/lang/Throwable
    //   52	62	145	java/lang/Throwable
    //   19	31	159	finally
    //   31	52	167	finally
    //   73	80	167	finally
    //   9	19	179	java/lang/Exception
    //   19	31	184	java/lang/Exception
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.CompressManager
 * JD-Core Version:    0.6.0
 */