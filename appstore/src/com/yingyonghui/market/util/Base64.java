package com.yingyonghui.market.util;

public class Base64
{
  static final int BASELENGTH = 255;
  static final int FOURBYTE = 4;
  static final byte PAD = 61;
  private static byte[] base64Alphabet = new byte['ÿ'];

  static
  {
    int i = 0;
    if (i >= 255)
    {
      i = 90;
      label20: if (i >= 65)
        break label75;
      i = 122;
      label29: if (i >= 97)
        break label97;
      i = 57;
    }
    while (true)
    {
      if (i < 48)
      {
        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        return;
        base64Alphabet[i] = -1;
        i += 1;
        break;
        label75: byte[] arrayOfByte1 = base64Alphabet;
        int j = (byte)(i - 65);
        arrayOfByte1[i] = j;
        i += -1;
        break label20;
        label97: byte[] arrayOfByte2 = base64Alphabet;
        int k = (byte)(i - 97 + 26);
        arrayOfByte2[i] = k;
        i += -1;
        break label29;
      }
      byte[] arrayOfByte3 = base64Alphabet;
      int m = (byte)(i - 48 + 52);
      arrayOfByte3[i] = m;
      i += -1;
    }
  }

  public static byte[] decodeBase64(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = discardNonBase64(paramArrayOfByte);
    if (paramArrayOfByte.length == 0)
      paramArrayOfByte = new byte[0];
    int j;
    int i1;
    int m;
    while (true)
    {
      return paramArrayOfByte;
      int i = paramArrayOfByte.length / 4;
      byte[] arrayOfByte1 = (byte[])0;
      j = 0;
      int k = paramArrayOfByte.length;
      do
      {
        int n = k - 1;
        byte[] arrayOfByte2;
        if (paramArrayOfByte[n] != 61)
        {
          arrayOfByte2 = new byte[k - i];
          i1 = 0;
          if (i1 < i)
            break label89;
          paramArrayOfByte = arrayOfByte2;
          break;
        }
        arrayOfByte2 += -1;
      }
      while (m != 0);
      paramArrayOfByte = new byte[0];
    }
    label89: int i2 = i1 * 4;
    int i3 = i2 + 2;
    int i4 = paramArrayOfByte[i3];
    int i5 = i2 + 3;
    int i6 = paramArrayOfByte[i5];
    byte[] arrayOfByte3 = base64Alphabet;
    int i7 = paramArrayOfByte[i2];
    int i8 = arrayOfByte3[i7];
    byte[] arrayOfByte4 = base64Alphabet;
    int i9 = i2 + 1;
    int i10 = paramArrayOfByte[i9];
    int i11 = arrayOfByte4[i10];
    if ((i4 != 61) && (i6 != 61))
    {
      int i12 = base64Alphabet[i4];
      int i13 = base64Alphabet[i6];
      int i14 = i8 << 2;
      int i15 = i11 >> 4;
      int i16 = (byte)(i14 | i15);
      m[j] = i16;
      int i17 = j + 1;
      int i18 = (i11 & 0xF) << 4;
      int i19 = i12 >> 2 & 0xF;
      int i20 = (byte)(i18 | i19);
      m[i17] = i20;
      int i21 = j + 2;
      int i22 = (byte)(i12 << 6 | i13);
      m[i21] = i22;
    }
    while (true)
    {
      j += 3;
      i1 += 1;
      break;
      if (i4 == 61)
      {
        int i23 = i8 << 2;
        int i24 = i11 >> 4;
        int i25 = (byte)(i23 | i24);
        m[j] = i25;
        continue;
      }
      if (i6 != 61)
        continue;
      int i26 = base64Alphabet[i4];
      int i27 = i8 << 2;
      int i28 = i11 >> 4;
      int i29 = (byte)(i27 | i28);
      m[j] = i29;
      int i30 = j + 1;
      int i31 = (i11 & 0xF) << 4;
      int i32 = i26 >> 2 & 0xF;
      int i33 = (byte)(i31 | i32);
      m[i30] = i33;
    }
  }

  static byte[] discardNonBase64(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length];
    int i = 0;
    while (true)
    {
      int j = paramArrayOfByte.length;
      if (i >= j)
      {
        byte[] arrayOfByte2 = new byte[0];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, 0);
        return arrayOfByte2;
      }
      if (isBase64(paramArrayOfByte[i]))
      {
        int k = 0 + 1;
        int m = paramArrayOfByte[i];
        arrayOfByte1[0] = m;
        int n = k;
      }
      i += 1;
    }
  }

  private static boolean isBase64(byte paramByte)
  {
    int i;
    if (paramByte == 61)
      i = 1;
    while (true)
    {
      return i;
      if (base64Alphabet[paramByte] == -1)
      {
        i = 0;
        continue;
      }
      i = 1;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.Base64
 * JD-Core Version:    0.6.0
 */