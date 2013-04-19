package com.yingyonghui.market.util;

import dalvik.annotation.Signature;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil
{

  @Signature({"Ljava/util/Set", "<", "Ljava/lang/Character$UnicodeBlock;", ">;"})
  private static final Set CJK_BLOCKS;
  public static final String EMPTY_STRING = "";
  public static final String LINE_BREAKS = "\r\n";
  private static final String[] UNSAFE_TAGS;
  public static final String WHITE_SPACES = " \r\n\t銆��";
  private static final Pattern characterReferencePattern;
  private static final Pattern dbSpecPattern;

  @Signature({"Ljava/util/Map", "<", "Ljava/lang/String;", "Ljava/lang/Character;", ">;"})
  static Map escapeStrings;
  private static char[] hexChars;
  private static final Pattern htmlTagPattern;

  // ERROR //
  static
  {
    // Byte code:
    //   0: ldc 2
    //   2: invokevirtual 46	java/lang/Class:desiredAssertionStatus	()Z
    //   5: ifne +9088 -> 9093
    //   8: ldc 47
    //   10: istore_0
    //   11: iload_0
    //   12: putstatic 49	com/yingyonghui/market/util/StringUtil:$assertionsDisabled	Z
    //   15: ldc 51
    //   17: invokestatic 57	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   20: putstatic 59	com/yingyonghui/market/util/StringUtil:htmlTagPattern	Ljava/util/regex/Pattern;
    //   23: ldc 61
    //   25: invokestatic 57	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   28: putstatic 63	com/yingyonghui/market/util/StringUtil:characterReferencePattern	Ljava/util/regex/Pattern;
    //   31: ldc 65
    //   33: invokestatic 57	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   36: putstatic 67	com/yingyonghui/market/util/StringUtil:dbSpecPattern	Ljava/util/regex/Pattern;
    //   39: new 69	java/util/HashMap
    //   42: dup
    //   43: sipush 252
    //   46: invokespecial 73	java/util/HashMap:<init>	(I)V
    //   49: putstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   52: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   55: astore_1
    //   56: new 77	java/lang/Character
    //   59: dup
    //   60: ldc 78
    //   62: invokespecial 81	java/lang/Character:<init>	(C)V
    //   65: astore_2
    //   66: aload_1
    //   67: ldc 83
    //   69: aload_2
    //   70: invokeinterface 89 3 0
    //   75: pop
    //   76: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   79: astore_3
    //   80: new 77	java/lang/Character
    //   83: dup
    //   84: ldc 90
    //   86: invokespecial 81	java/lang/Character:<init>	(C)V
    //   89: astore 4
    //   91: aload_3
    //   92: ldc 92
    //   94: aload 4
    //   96: invokeinterface 89 3 0
    //   101: pop
    //   102: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   105: astore 5
    //   107: new 77	java/lang/Character
    //   110: dup
    //   111: ldc 93
    //   113: invokespecial 81	java/lang/Character:<init>	(C)V
    //   116: astore 6
    //   118: aload 5
    //   120: ldc 95
    //   122: aload 6
    //   124: invokeinterface 89 3 0
    //   129: pop
    //   130: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   133: astore 7
    //   135: new 77	java/lang/Character
    //   138: dup
    //   139: ldc 96
    //   141: invokespecial 81	java/lang/Character:<init>	(C)V
    //   144: astore 8
    //   146: aload 7
    //   148: ldc 98
    //   150: aload 8
    //   152: invokeinterface 89 3 0
    //   157: pop
    //   158: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   161: astore 9
    //   163: new 77	java/lang/Character
    //   166: dup
    //   167: ldc 99
    //   169: invokespecial 81	java/lang/Character:<init>	(C)V
    //   172: astore 10
    //   174: aload 9
    //   176: ldc 101
    //   178: aload 10
    //   180: invokeinterface 89 3 0
    //   185: pop
    //   186: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   189: astore 11
    //   191: new 77	java/lang/Character
    //   194: dup
    //   195: ldc 102
    //   197: invokespecial 81	java/lang/Character:<init>	(C)V
    //   200: astore 12
    //   202: aload 11
    //   204: ldc 104
    //   206: aload 12
    //   208: invokeinterface 89 3 0
    //   213: pop
    //   214: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   217: astore 13
    //   219: new 77	java/lang/Character
    //   222: dup
    //   223: ldc 105
    //   225: invokespecial 81	java/lang/Character:<init>	(C)V
    //   228: astore 14
    //   230: aload 13
    //   232: ldc 107
    //   234: aload 14
    //   236: invokeinterface 89 3 0
    //   241: pop
    //   242: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   245: astore 15
    //   247: new 77	java/lang/Character
    //   250: dup
    //   251: ldc 108
    //   253: invokespecial 81	java/lang/Character:<init>	(C)V
    //   256: astore 16
    //   258: aload 15
    //   260: ldc 110
    //   262: aload 16
    //   264: invokeinterface 89 3 0
    //   269: pop
    //   270: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   273: astore 17
    //   275: new 77	java/lang/Character
    //   278: dup
    //   279: ldc 111
    //   281: invokespecial 81	java/lang/Character:<init>	(C)V
    //   284: astore 18
    //   286: aload 17
    //   288: ldc 113
    //   290: aload 18
    //   292: invokeinterface 89 3 0
    //   297: pop
    //   298: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   301: astore 19
    //   303: new 77	java/lang/Character
    //   306: dup
    //   307: ldc 114
    //   309: invokespecial 81	java/lang/Character:<init>	(C)V
    //   312: astore 20
    //   314: aload 19
    //   316: ldc 116
    //   318: aload 20
    //   320: invokeinterface 89 3 0
    //   325: pop
    //   326: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   329: astore 21
    //   331: new 77	java/lang/Character
    //   334: dup
    //   335: ldc 117
    //   337: invokespecial 81	java/lang/Character:<init>	(C)V
    //   340: astore 22
    //   342: aload 21
    //   344: ldc 119
    //   346: aload 22
    //   348: invokeinterface 89 3 0
    //   353: pop
    //   354: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   357: astore 23
    //   359: new 77	java/lang/Character
    //   362: dup
    //   363: ldc 120
    //   365: invokespecial 81	java/lang/Character:<init>	(C)V
    //   368: astore 24
    //   370: aload 23
    //   372: ldc 122
    //   374: aload 24
    //   376: invokeinterface 89 3 0
    //   381: pop
    //   382: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   385: astore 25
    //   387: new 77	java/lang/Character
    //   390: dup
    //   391: ldc 123
    //   393: invokespecial 81	java/lang/Character:<init>	(C)V
    //   396: astore 26
    //   398: aload 25
    //   400: ldc 125
    //   402: aload 26
    //   404: invokeinterface 89 3 0
    //   409: pop
    //   410: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   413: astore 27
    //   415: new 77	java/lang/Character
    //   418: dup
    //   419: ldc 126
    //   421: invokespecial 81	java/lang/Character:<init>	(C)V
    //   424: astore 28
    //   426: aload 27
    //   428: ldc 128
    //   430: aload 28
    //   432: invokeinterface 89 3 0
    //   437: pop
    //   438: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   441: astore 29
    //   443: new 77	java/lang/Character
    //   446: dup
    //   447: ldc 129
    //   449: invokespecial 81	java/lang/Character:<init>	(C)V
    //   452: astore 30
    //   454: aload 29
    //   456: ldc 131
    //   458: aload 30
    //   460: invokeinterface 89 3 0
    //   465: pop
    //   466: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   469: astore 31
    //   471: new 77	java/lang/Character
    //   474: dup
    //   475: ldc 132
    //   477: invokespecial 81	java/lang/Character:<init>	(C)V
    //   480: astore 32
    //   482: aload 31
    //   484: ldc 134
    //   486: aload 32
    //   488: invokeinterface 89 3 0
    //   493: pop
    //   494: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   497: astore 33
    //   499: new 77	java/lang/Character
    //   502: dup
    //   503: ldc 135
    //   505: invokespecial 81	java/lang/Character:<init>	(C)V
    //   508: astore 34
    //   510: aload 33
    //   512: ldc 137
    //   514: aload 34
    //   516: invokeinterface 89 3 0
    //   521: pop
    //   522: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   525: astore 35
    //   527: new 77	java/lang/Character
    //   530: dup
    //   531: ldc 138
    //   533: invokespecial 81	java/lang/Character:<init>	(C)V
    //   536: astore 36
    //   538: aload 35
    //   540: ldc 140
    //   542: aload 36
    //   544: invokeinterface 89 3 0
    //   549: pop
    //   550: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   553: astore 37
    //   555: new 77	java/lang/Character
    //   558: dup
    //   559: ldc 141
    //   561: invokespecial 81	java/lang/Character:<init>	(C)V
    //   564: astore 38
    //   566: aload 37
    //   568: ldc 143
    //   570: aload 38
    //   572: invokeinterface 89 3 0
    //   577: pop
    //   578: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   581: astore 39
    //   583: new 77	java/lang/Character
    //   586: dup
    //   587: ldc 144
    //   589: invokespecial 81	java/lang/Character:<init>	(C)V
    //   592: astore 40
    //   594: aload 39
    //   596: ldc 146
    //   598: aload 40
    //   600: invokeinterface 89 3 0
    //   605: pop
    //   606: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   609: astore 41
    //   611: new 77	java/lang/Character
    //   614: dup
    //   615: ldc 147
    //   617: invokespecial 81	java/lang/Character:<init>	(C)V
    //   620: astore 42
    //   622: aload 41
    //   624: ldc 149
    //   626: aload 42
    //   628: invokeinterface 89 3 0
    //   633: pop
    //   634: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   637: astore 43
    //   639: new 77	java/lang/Character
    //   642: dup
    //   643: ldc 150
    //   645: invokespecial 81	java/lang/Character:<init>	(C)V
    //   648: astore 44
    //   650: aload 43
    //   652: ldc 152
    //   654: aload 44
    //   656: invokeinterface 89 3 0
    //   661: pop
    //   662: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   665: astore 45
    //   667: new 77	java/lang/Character
    //   670: dup
    //   671: ldc 153
    //   673: invokespecial 81	java/lang/Character:<init>	(C)V
    //   676: astore 46
    //   678: aload 45
    //   680: ldc 155
    //   682: aload 46
    //   684: invokeinterface 89 3 0
    //   689: pop
    //   690: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   693: astore 47
    //   695: new 77	java/lang/Character
    //   698: dup
    //   699: ldc 156
    //   701: invokespecial 81	java/lang/Character:<init>	(C)V
    //   704: astore 48
    //   706: aload 47
    //   708: ldc 158
    //   710: aload 48
    //   712: invokeinterface 89 3 0
    //   717: pop
    //   718: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   721: astore 49
    //   723: new 77	java/lang/Character
    //   726: dup
    //   727: ldc 159
    //   729: invokespecial 81	java/lang/Character:<init>	(C)V
    //   732: astore 50
    //   734: aload 49
    //   736: ldc 161
    //   738: aload 50
    //   740: invokeinterface 89 3 0
    //   745: pop
    //   746: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   749: astore 51
    //   751: new 77	java/lang/Character
    //   754: dup
    //   755: ldc 162
    //   757: invokespecial 81	java/lang/Character:<init>	(C)V
    //   760: astore 52
    //   762: aload 51
    //   764: ldc 164
    //   766: aload 52
    //   768: invokeinterface 89 3 0
    //   773: pop
    //   774: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   777: astore 53
    //   779: new 77	java/lang/Character
    //   782: dup
    //   783: ldc 165
    //   785: invokespecial 81	java/lang/Character:<init>	(C)V
    //   788: astore 54
    //   790: aload 53
    //   792: ldc 167
    //   794: aload 54
    //   796: invokeinterface 89 3 0
    //   801: pop
    //   802: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   805: astore 55
    //   807: new 77	java/lang/Character
    //   810: dup
    //   811: ldc 168
    //   813: invokespecial 81	java/lang/Character:<init>	(C)V
    //   816: astore 56
    //   818: aload 55
    //   820: ldc 170
    //   822: aload 56
    //   824: invokeinterface 89 3 0
    //   829: pop
    //   830: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   833: astore 57
    //   835: new 77	java/lang/Character
    //   838: dup
    //   839: ldc 171
    //   841: invokespecial 81	java/lang/Character:<init>	(C)V
    //   844: astore 58
    //   846: aload 57
    //   848: ldc 173
    //   850: aload 58
    //   852: invokeinterface 89 3 0
    //   857: pop
    //   858: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   861: astore 59
    //   863: new 77	java/lang/Character
    //   866: dup
    //   867: ldc 174
    //   869: invokespecial 81	java/lang/Character:<init>	(C)V
    //   872: astore 60
    //   874: aload 59
    //   876: ldc 176
    //   878: aload 60
    //   880: invokeinterface 89 3 0
    //   885: pop
    //   886: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   889: astore 61
    //   891: new 77	java/lang/Character
    //   894: dup
    //   895: ldc 177
    //   897: invokespecial 81	java/lang/Character:<init>	(C)V
    //   900: astore 62
    //   902: aload 61
    //   904: ldc 179
    //   906: aload 62
    //   908: invokeinterface 89 3 0
    //   913: pop
    //   914: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   917: astore 63
    //   919: new 77	java/lang/Character
    //   922: dup
    //   923: ldc 180
    //   925: invokespecial 81	java/lang/Character:<init>	(C)V
    //   928: astore 64
    //   930: aload 63
    //   932: ldc 182
    //   934: aload 64
    //   936: invokeinterface 89 3 0
    //   941: pop
    //   942: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   945: astore 65
    //   947: new 77	java/lang/Character
    //   950: dup
    //   951: ldc 183
    //   953: invokespecial 81	java/lang/Character:<init>	(C)V
    //   956: astore 66
    //   958: aload 65
    //   960: ldc 185
    //   962: aload 66
    //   964: invokeinterface 89 3 0
    //   969: pop
    //   970: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   973: astore 67
    //   975: new 77	java/lang/Character
    //   978: dup
    //   979: ldc 186
    //   981: invokespecial 81	java/lang/Character:<init>	(C)V
    //   984: astore 68
    //   986: aload 67
    //   988: ldc 188
    //   990: aload 68
    //   992: invokeinterface 89 3 0
    //   997: pop
    //   998: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1001: astore 69
    //   1003: new 77	java/lang/Character
    //   1006: dup
    //   1007: ldc 189
    //   1009: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1012: astore 70
    //   1014: aload 69
    //   1016: ldc 191
    //   1018: aload 70
    //   1020: invokeinterface 89 3 0
    //   1025: pop
    //   1026: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1029: astore 71
    //   1031: new 77	java/lang/Character
    //   1034: dup
    //   1035: ldc 192
    //   1037: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1040: astore 72
    //   1042: aload 71
    //   1044: ldc 194
    //   1046: aload 72
    //   1048: invokeinterface 89 3 0
    //   1053: pop
    //   1054: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1057: astore 73
    //   1059: new 77	java/lang/Character
    //   1062: dup
    //   1063: ldc 195
    //   1065: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1068: astore 74
    //   1070: aload 73
    //   1072: ldc 197
    //   1074: aload 74
    //   1076: invokeinterface 89 3 0
    //   1081: pop
    //   1082: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1085: astore 75
    //   1087: new 77	java/lang/Character
    //   1090: dup
    //   1091: ldc 198
    //   1093: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1096: astore 76
    //   1098: aload 75
    //   1100: ldc 200
    //   1102: aload 76
    //   1104: invokeinterface 89 3 0
    //   1109: pop
    //   1110: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1113: astore 77
    //   1115: new 77	java/lang/Character
    //   1118: dup
    //   1119: ldc 201
    //   1121: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1124: astore 78
    //   1126: aload 77
    //   1128: ldc 203
    //   1130: aload 78
    //   1132: invokeinterface 89 3 0
    //   1137: pop
    //   1138: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1141: astore 79
    //   1143: new 77	java/lang/Character
    //   1146: dup
    //   1147: ldc 204
    //   1149: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1152: astore 80
    //   1154: aload 79
    //   1156: ldc 206
    //   1158: aload 80
    //   1160: invokeinterface 89 3 0
    //   1165: pop
    //   1166: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1169: astore 81
    //   1171: new 77	java/lang/Character
    //   1174: dup
    //   1175: ldc 207
    //   1177: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1180: astore 82
    //   1182: aload 81
    //   1184: ldc 209
    //   1186: aload 82
    //   1188: invokeinterface 89 3 0
    //   1193: pop
    //   1194: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1197: astore 83
    //   1199: new 77	java/lang/Character
    //   1202: dup
    //   1203: ldc 210
    //   1205: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1208: astore 84
    //   1210: aload 83
    //   1212: ldc 212
    //   1214: aload 84
    //   1216: invokeinterface 89 3 0
    //   1221: pop
    //   1222: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1225: astore 85
    //   1227: new 77	java/lang/Character
    //   1230: dup
    //   1231: ldc 213
    //   1233: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1236: astore 86
    //   1238: aload 85
    //   1240: ldc 215
    //   1242: aload 86
    //   1244: invokeinterface 89 3 0
    //   1249: pop
    //   1250: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1253: astore 87
    //   1255: new 77	java/lang/Character
    //   1258: dup
    //   1259: ldc 216
    //   1261: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1264: astore 88
    //   1266: aload 87
    //   1268: ldc 218
    //   1270: aload 88
    //   1272: invokeinterface 89 3 0
    //   1277: pop
    //   1278: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1281: astore 89
    //   1283: new 77	java/lang/Character
    //   1286: dup
    //   1287: ldc 219
    //   1289: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1292: astore 90
    //   1294: aload 89
    //   1296: ldc 221
    //   1298: aload 90
    //   1300: invokeinterface 89 3 0
    //   1305: pop
    //   1306: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1309: astore 91
    //   1311: new 77	java/lang/Character
    //   1314: dup
    //   1315: ldc 222
    //   1317: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1320: astore 92
    //   1322: aload 91
    //   1324: ldc 224
    //   1326: aload 92
    //   1328: invokeinterface 89 3 0
    //   1333: pop
    //   1334: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1337: astore 93
    //   1339: new 77	java/lang/Character
    //   1342: dup
    //   1343: ldc 225
    //   1345: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1348: astore 94
    //   1350: aload 93
    //   1352: ldc 227
    //   1354: aload 94
    //   1356: invokeinterface 89 3 0
    //   1361: pop
    //   1362: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1365: astore 95
    //   1367: new 77	java/lang/Character
    //   1370: dup
    //   1371: ldc 228
    //   1373: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1376: astore 96
    //   1378: aload 95
    //   1380: ldc 230
    //   1382: aload 96
    //   1384: invokeinterface 89 3 0
    //   1389: pop
    //   1390: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1393: astore 97
    //   1395: new 77	java/lang/Character
    //   1398: dup
    //   1399: ldc 231
    //   1401: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1404: astore 98
    //   1406: aload 97
    //   1408: ldc 233
    //   1410: aload 98
    //   1412: invokeinterface 89 3 0
    //   1417: pop
    //   1418: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1421: astore 99
    //   1423: new 77	java/lang/Character
    //   1426: dup
    //   1427: ldc 234
    //   1429: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1432: astore 100
    //   1434: aload 99
    //   1436: ldc 236
    //   1438: aload 100
    //   1440: invokeinterface 89 3 0
    //   1445: pop
    //   1446: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1449: astore 101
    //   1451: new 77	java/lang/Character
    //   1454: dup
    //   1455: ldc 237
    //   1457: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1460: astore 102
    //   1462: aload 101
    //   1464: ldc 239
    //   1466: aload 102
    //   1468: invokeinterface 89 3 0
    //   1473: pop
    //   1474: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1477: astore 103
    //   1479: new 77	java/lang/Character
    //   1482: dup
    //   1483: ldc 240
    //   1485: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1488: astore 104
    //   1490: aload 103
    //   1492: ldc 242
    //   1494: aload 104
    //   1496: invokeinterface 89 3 0
    //   1501: pop
    //   1502: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1505: astore 105
    //   1507: new 77	java/lang/Character
    //   1510: dup
    //   1511: ldc 243
    //   1513: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1516: astore 106
    //   1518: aload 105
    //   1520: ldc 245
    //   1522: aload 106
    //   1524: invokeinterface 89 3 0
    //   1529: pop
    //   1530: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1533: astore 107
    //   1535: new 77	java/lang/Character
    //   1538: dup
    //   1539: ldc 246
    //   1541: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1544: astore 108
    //   1546: aload 107
    //   1548: ldc 248
    //   1550: aload 108
    //   1552: invokeinterface 89 3 0
    //   1557: pop
    //   1558: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1561: astore 109
    //   1563: new 77	java/lang/Character
    //   1566: dup
    //   1567: ldc 249
    //   1569: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1572: astore 110
    //   1574: aload 109
    //   1576: ldc 251
    //   1578: aload 110
    //   1580: invokeinterface 89 3 0
    //   1585: pop
    //   1586: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1589: astore 111
    //   1591: new 77	java/lang/Character
    //   1594: dup
    //   1595: ldc 252
    //   1597: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1600: astore 112
    //   1602: aload 111
    //   1604: ldc 254
    //   1606: aload 112
    //   1608: invokeinterface 89 3 0
    //   1613: pop
    //   1614: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1617: astore 113
    //   1619: new 77	java/lang/Character
    //   1622: dup
    //   1623: ldc 255
    //   1625: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1628: astore 114
    //   1630: aload 113
    //   1632: ldc_w 257
    //   1635: aload 114
    //   1637: invokeinterface 89 3 0
    //   1642: pop
    //   1643: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1646: astore 115
    //   1648: new 77	java/lang/Character
    //   1651: dup
    //   1652: ldc_w 258
    //   1655: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1658: astore 116
    //   1660: aload 115
    //   1662: ldc_w 260
    //   1665: aload 116
    //   1667: invokeinterface 89 3 0
    //   1672: pop
    //   1673: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1676: astore 117
    //   1678: new 77	java/lang/Character
    //   1681: dup
    //   1682: ldc_w 261
    //   1685: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1688: astore 118
    //   1690: aload 117
    //   1692: ldc_w 263
    //   1695: aload 118
    //   1697: invokeinterface 89 3 0
    //   1702: pop
    //   1703: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1706: astore 119
    //   1708: new 77	java/lang/Character
    //   1711: dup
    //   1712: ldc_w 264
    //   1715: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1718: astore 120
    //   1720: aload 119
    //   1722: ldc_w 266
    //   1725: aload 120
    //   1727: invokeinterface 89 3 0
    //   1732: pop
    //   1733: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1736: astore 121
    //   1738: new 77	java/lang/Character
    //   1741: dup
    //   1742: ldc_w 267
    //   1745: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1748: astore 122
    //   1750: aload 121
    //   1752: ldc_w 269
    //   1755: aload 122
    //   1757: invokeinterface 89 3 0
    //   1762: pop
    //   1763: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1766: astore 123
    //   1768: new 77	java/lang/Character
    //   1771: dup
    //   1772: ldc_w 270
    //   1775: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1778: astore 124
    //   1780: aload 123
    //   1782: ldc_w 272
    //   1785: aload 124
    //   1787: invokeinterface 89 3 0
    //   1792: pop
    //   1793: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1796: astore 125
    //   1798: new 77	java/lang/Character
    //   1801: dup
    //   1802: ldc_w 273
    //   1805: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1808: astore 126
    //   1810: aload 125
    //   1812: ldc_w 275
    //   1815: aload 126
    //   1817: invokeinterface 89 3 0
    //   1822: pop
    //   1823: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1826: astore 127
    //   1828: new 77	java/lang/Character
    //   1831: dup
    //   1832: ldc_w 276
    //   1835: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1838: astore 128
    //   1840: aload 127
    //   1842: ldc_w 278
    //   1845: aload 128
    //   1847: invokeinterface 89 3 0
    //   1852: pop
    //   1853: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1856: astore 129
    //   1858: new 77	java/lang/Character
    //   1861: dup
    //   1862: ldc_w 279
    //   1865: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1868: astore 130
    //   1870: aload 129
    //   1872: ldc_w 281
    //   1875: aload 130
    //   1877: invokeinterface 89 3 0
    //   1882: pop
    //   1883: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1886: astore 131
    //   1888: new 77	java/lang/Character
    //   1891: dup
    //   1892: ldc_w 282
    //   1895: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1898: astore 132
    //   1900: aload 131
    //   1902: ldc_w 284
    //   1905: aload 132
    //   1907: invokeinterface 89 3 0
    //   1912: pop
    //   1913: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1916: astore 133
    //   1918: new 77	java/lang/Character
    //   1921: dup
    //   1922: ldc_w 285
    //   1925: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1928: astore 134
    //   1930: aload 133
    //   1932: ldc_w 287
    //   1935: aload 134
    //   1937: invokeinterface 89 3 0
    //   1942: pop
    //   1943: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1946: astore 135
    //   1948: new 77	java/lang/Character
    //   1951: dup
    //   1952: ldc_w 288
    //   1955: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1958: astore 136
    //   1960: aload 135
    //   1962: ldc_w 290
    //   1965: aload 136
    //   1967: invokeinterface 89 3 0
    //   1972: pop
    //   1973: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   1976: astore 137
    //   1978: new 77	java/lang/Character
    //   1981: dup
    //   1982: ldc_w 291
    //   1985: invokespecial 81	java/lang/Character:<init>	(C)V
    //   1988: astore 138
    //   1990: aload 137
    //   1992: ldc_w 293
    //   1995: aload 138
    //   1997: invokeinterface 89 3 0
    //   2002: pop
    //   2003: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2006: astore 139
    //   2008: new 77	java/lang/Character
    //   2011: dup
    //   2012: ldc_w 294
    //   2015: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2018: astore 140
    //   2020: aload 139
    //   2022: ldc_w 296
    //   2025: aload 140
    //   2027: invokeinterface 89 3 0
    //   2032: pop
    //   2033: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2036: astore 141
    //   2038: new 77	java/lang/Character
    //   2041: dup
    //   2042: ldc_w 297
    //   2045: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2048: astore 142
    //   2050: aload 141
    //   2052: ldc_w 299
    //   2055: aload 142
    //   2057: invokeinterface 89 3 0
    //   2062: pop
    //   2063: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2066: astore 143
    //   2068: new 77	java/lang/Character
    //   2071: dup
    //   2072: ldc_w 300
    //   2075: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2078: astore 144
    //   2080: aload 143
    //   2082: ldc_w 302
    //   2085: aload 144
    //   2087: invokeinterface 89 3 0
    //   2092: pop
    //   2093: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2096: astore 145
    //   2098: new 77	java/lang/Character
    //   2101: dup
    //   2102: ldc_w 303
    //   2105: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2108: astore 146
    //   2110: aload 145
    //   2112: ldc_w 305
    //   2115: aload 146
    //   2117: invokeinterface 89 3 0
    //   2122: pop
    //   2123: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2126: astore 147
    //   2128: new 77	java/lang/Character
    //   2131: dup
    //   2132: ldc_w 306
    //   2135: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2138: astore 148
    //   2140: aload 147
    //   2142: ldc_w 308
    //   2145: aload 148
    //   2147: invokeinterface 89 3 0
    //   2152: pop
    //   2153: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2156: astore 149
    //   2158: new 77	java/lang/Character
    //   2161: dup
    //   2162: ldc_w 309
    //   2165: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2168: astore 150
    //   2170: aload 149
    //   2172: ldc_w 311
    //   2175: aload 150
    //   2177: invokeinterface 89 3 0
    //   2182: pop
    //   2183: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2186: astore 151
    //   2188: new 77	java/lang/Character
    //   2191: dup
    //   2192: ldc_w 312
    //   2195: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2198: astore 152
    //   2200: aload 151
    //   2202: ldc_w 314
    //   2205: aload 152
    //   2207: invokeinterface 89 3 0
    //   2212: pop
    //   2213: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2216: astore 153
    //   2218: new 77	java/lang/Character
    //   2221: dup
    //   2222: ldc_w 315
    //   2225: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2228: astore 154
    //   2230: aload 153
    //   2232: ldc_w 317
    //   2235: aload 154
    //   2237: invokeinterface 89 3 0
    //   2242: pop
    //   2243: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2246: astore 155
    //   2248: new 77	java/lang/Character
    //   2251: dup
    //   2252: ldc_w 318
    //   2255: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2258: astore 156
    //   2260: aload 155
    //   2262: ldc_w 320
    //   2265: aload 156
    //   2267: invokeinterface 89 3 0
    //   2272: pop
    //   2273: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2276: astore 157
    //   2278: new 77	java/lang/Character
    //   2281: dup
    //   2282: ldc_w 321
    //   2285: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2288: astore 158
    //   2290: aload 157
    //   2292: ldc_w 323
    //   2295: aload 158
    //   2297: invokeinterface 89 3 0
    //   2302: pop
    //   2303: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2306: astore 159
    //   2308: new 77	java/lang/Character
    //   2311: dup
    //   2312: ldc_w 324
    //   2315: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2318: astore 160
    //   2320: aload 159
    //   2322: ldc_w 326
    //   2325: aload 160
    //   2327: invokeinterface 89 3 0
    //   2332: pop
    //   2333: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2336: astore 161
    //   2338: new 77	java/lang/Character
    //   2341: dup
    //   2342: ldc_w 327
    //   2345: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2348: astore 162
    //   2350: aload 161
    //   2352: ldc_w 329
    //   2355: aload 162
    //   2357: invokeinterface 89 3 0
    //   2362: pop
    //   2363: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2366: astore 163
    //   2368: new 77	java/lang/Character
    //   2371: dup
    //   2372: ldc_w 330
    //   2375: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2378: astore 164
    //   2380: aload 163
    //   2382: ldc_w 332
    //   2385: aload 164
    //   2387: invokeinterface 89 3 0
    //   2392: pop
    //   2393: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2396: astore 165
    //   2398: new 77	java/lang/Character
    //   2401: dup
    //   2402: ldc_w 333
    //   2405: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2408: astore 166
    //   2410: aload 165
    //   2412: ldc_w 335
    //   2415: aload 166
    //   2417: invokeinterface 89 3 0
    //   2422: pop
    //   2423: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2426: astore 167
    //   2428: new 77	java/lang/Character
    //   2431: dup
    //   2432: ldc_w 336
    //   2435: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2438: astore 168
    //   2440: aload 167
    //   2442: ldc_w 338
    //   2445: aload 168
    //   2447: invokeinterface 89 3 0
    //   2452: pop
    //   2453: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2456: astore 169
    //   2458: new 77	java/lang/Character
    //   2461: dup
    //   2462: ldc_w 339
    //   2465: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2468: astore 170
    //   2470: aload 169
    //   2472: ldc_w 341
    //   2475: aload 170
    //   2477: invokeinterface 89 3 0
    //   2482: pop
    //   2483: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2486: astore 171
    //   2488: new 77	java/lang/Character
    //   2491: dup
    //   2492: ldc_w 342
    //   2495: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2498: astore 172
    //   2500: aload 171
    //   2502: ldc_w 344
    //   2505: aload 172
    //   2507: invokeinterface 89 3 0
    //   2512: pop
    //   2513: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2516: astore 173
    //   2518: new 77	java/lang/Character
    //   2521: dup
    //   2522: ldc_w 345
    //   2525: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2528: astore 174
    //   2530: aload 173
    //   2532: ldc_w 347
    //   2535: aload 174
    //   2537: invokeinterface 89 3 0
    //   2542: pop
    //   2543: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2546: astore 175
    //   2548: new 77	java/lang/Character
    //   2551: dup
    //   2552: ldc_w 348
    //   2555: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2558: astore 176
    //   2560: aload 175
    //   2562: ldc_w 350
    //   2565: aload 176
    //   2567: invokeinterface 89 3 0
    //   2572: pop
    //   2573: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2576: astore 177
    //   2578: new 77	java/lang/Character
    //   2581: dup
    //   2582: ldc_w 351
    //   2585: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2588: astore 178
    //   2590: aload 177
    //   2592: ldc_w 353
    //   2595: aload 178
    //   2597: invokeinterface 89 3 0
    //   2602: pop
    //   2603: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2606: astore 179
    //   2608: new 77	java/lang/Character
    //   2611: dup
    //   2612: ldc_w 354
    //   2615: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2618: astore 180
    //   2620: aload 179
    //   2622: ldc_w 356
    //   2625: aload 180
    //   2627: invokeinterface 89 3 0
    //   2632: pop
    //   2633: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2636: astore 181
    //   2638: new 77	java/lang/Character
    //   2641: dup
    //   2642: ldc_w 357
    //   2645: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2648: astore 182
    //   2650: aload 181
    //   2652: ldc_w 359
    //   2655: aload 182
    //   2657: invokeinterface 89 3 0
    //   2662: pop
    //   2663: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2666: astore 183
    //   2668: new 77	java/lang/Character
    //   2671: dup
    //   2672: ldc_w 360
    //   2675: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2678: astore 184
    //   2680: aload 183
    //   2682: ldc_w 362
    //   2685: aload 184
    //   2687: invokeinterface 89 3 0
    //   2692: pop
    //   2693: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2696: astore 185
    //   2698: new 77	java/lang/Character
    //   2701: dup
    //   2702: ldc_w 363
    //   2705: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2708: astore 186
    //   2710: aload 185
    //   2712: ldc_w 365
    //   2715: aload 186
    //   2717: invokeinterface 89 3 0
    //   2722: pop
    //   2723: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2726: astore 187
    //   2728: new 77	java/lang/Character
    //   2731: dup
    //   2732: ldc_w 366
    //   2735: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2738: astore 188
    //   2740: aload 187
    //   2742: ldc_w 368
    //   2745: aload 188
    //   2747: invokeinterface 89 3 0
    //   2752: pop
    //   2753: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2756: astore 189
    //   2758: new 77	java/lang/Character
    //   2761: dup
    //   2762: ldc_w 369
    //   2765: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2768: astore 190
    //   2770: aload 189
    //   2772: ldc_w 371
    //   2775: aload 190
    //   2777: invokeinterface 89 3 0
    //   2782: pop
    //   2783: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2786: astore 191
    //   2788: new 77	java/lang/Character
    //   2791: dup
    //   2792: ldc_w 372
    //   2795: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2798: astore 192
    //   2800: aload 191
    //   2802: ldc_w 374
    //   2805: aload 192
    //   2807: invokeinterface 89 3 0
    //   2812: pop
    //   2813: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2816: astore 193
    //   2818: new 77	java/lang/Character
    //   2821: dup
    //   2822: ldc_w 375
    //   2825: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2828: astore 194
    //   2830: aload 193
    //   2832: ldc_w 377
    //   2835: aload 194
    //   2837: invokeinterface 89 3 0
    //   2842: pop
    //   2843: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2846: astore 195
    //   2848: new 77	java/lang/Character
    //   2851: dup
    //   2852: ldc_w 378
    //   2855: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2858: astore 196
    //   2860: aload 195
    //   2862: ldc_w 380
    //   2865: aload 196
    //   2867: invokeinterface 89 3 0
    //   2872: pop
    //   2873: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2876: astore 197
    //   2878: new 77	java/lang/Character
    //   2881: dup
    //   2882: ldc_w 381
    //   2885: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2888: astore 198
    //   2890: aload 197
    //   2892: ldc_w 383
    //   2895: aload 198
    //   2897: invokeinterface 89 3 0
    //   2902: pop
    //   2903: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2906: astore 199
    //   2908: new 77	java/lang/Character
    //   2911: dup
    //   2912: ldc_w 384
    //   2915: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2918: astore 200
    //   2920: aload 199
    //   2922: ldc_w 386
    //   2925: aload 200
    //   2927: invokeinterface 89 3 0
    //   2932: pop
    //   2933: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2936: astore 201
    //   2938: new 77	java/lang/Character
    //   2941: dup
    //   2942: ldc_w 387
    //   2945: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2948: astore 202
    //   2950: aload 201
    //   2952: ldc_w 389
    //   2955: aload 202
    //   2957: invokeinterface 89 3 0
    //   2962: pop
    //   2963: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2966: astore 203
    //   2968: new 77	java/lang/Character
    //   2971: dup
    //   2972: ldc_w 390
    //   2975: invokespecial 81	java/lang/Character:<init>	(C)V
    //   2978: astore 204
    //   2980: aload 203
    //   2982: ldc_w 392
    //   2985: aload 204
    //   2987: invokeinterface 89 3 0
    //   2992: pop
    //   2993: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   2996: astore 205
    //   2998: new 77	java/lang/Character
    //   3001: dup
    //   3002: ldc_w 393
    //   3005: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3008: astore 206
    //   3010: aload 205
    //   3012: ldc_w 395
    //   3015: aload 206
    //   3017: invokeinterface 89 3 0
    //   3022: pop
    //   3023: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3026: astore 207
    //   3028: new 77	java/lang/Character
    //   3031: dup
    //   3032: ldc_w 396
    //   3035: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3038: astore 208
    //   3040: aload 207
    //   3042: ldc_w 398
    //   3045: aload 208
    //   3047: invokeinterface 89 3 0
    //   3052: pop
    //   3053: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3056: astore 209
    //   3058: new 77	java/lang/Character
    //   3061: dup
    //   3062: ldc_w 399
    //   3065: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3068: astore 210
    //   3070: aload 209
    //   3072: ldc_w 401
    //   3075: aload 210
    //   3077: invokeinterface 89 3 0
    //   3082: pop
    //   3083: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3086: astore 211
    //   3088: new 77	java/lang/Character
    //   3091: dup
    //   3092: ldc_w 402
    //   3095: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3098: astore 212
    //   3100: aload 211
    //   3102: ldc_w 404
    //   3105: aload 212
    //   3107: invokeinterface 89 3 0
    //   3112: pop
    //   3113: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3116: astore 213
    //   3118: new 77	java/lang/Character
    //   3121: dup
    //   3122: ldc_w 405
    //   3125: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3128: astore 214
    //   3130: aload 213
    //   3132: ldc_w 407
    //   3135: aload 214
    //   3137: invokeinterface 89 3 0
    //   3142: pop
    //   3143: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3146: astore 215
    //   3148: new 77	java/lang/Character
    //   3151: dup
    //   3152: ldc_w 408
    //   3155: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3158: astore 216
    //   3160: aload 215
    //   3162: ldc_w 410
    //   3165: aload 216
    //   3167: invokeinterface 89 3 0
    //   3172: pop
    //   3173: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3176: astore 217
    //   3178: new 77	java/lang/Character
    //   3181: dup
    //   3182: ldc_w 411
    //   3185: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3188: astore 218
    //   3190: aload 217
    //   3192: ldc_w 413
    //   3195: aload 218
    //   3197: invokeinterface 89 3 0
    //   3202: pop
    //   3203: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3206: astore 219
    //   3208: new 77	java/lang/Character
    //   3211: dup
    //   3212: ldc_w 414
    //   3215: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3218: astore 220
    //   3220: aload 219
    //   3222: ldc_w 416
    //   3225: aload 220
    //   3227: invokeinterface 89 3 0
    //   3232: pop
    //   3233: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3236: astore 221
    //   3238: new 77	java/lang/Character
    //   3241: dup
    //   3242: ldc_w 417
    //   3245: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3248: astore 222
    //   3250: aload 221
    //   3252: ldc_w 419
    //   3255: aload 222
    //   3257: invokeinterface 89 3 0
    //   3262: pop
    //   3263: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3266: astore 223
    //   3268: new 77	java/lang/Character
    //   3271: dup
    //   3272: ldc_w 420
    //   3275: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3278: astore 224
    //   3280: aload 223
    //   3282: ldc_w 422
    //   3285: aload 224
    //   3287: invokeinterface 89 3 0
    //   3292: pop
    //   3293: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3296: astore 225
    //   3298: new 77	java/lang/Character
    //   3301: dup
    //   3302: ldc_w 423
    //   3305: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3308: astore 226
    //   3310: aload 225
    //   3312: ldc_w 425
    //   3315: aload 226
    //   3317: invokeinterface 89 3 0
    //   3322: pop
    //   3323: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3326: astore 227
    //   3328: new 77	java/lang/Character
    //   3331: dup
    //   3332: ldc_w 426
    //   3335: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3338: astore 228
    //   3340: aload 227
    //   3342: ldc_w 428
    //   3345: aload 228
    //   3347: invokeinterface 89 3 0
    //   3352: pop
    //   3353: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3356: astore 229
    //   3358: new 77	java/lang/Character
    //   3361: dup
    //   3362: ldc_w 429
    //   3365: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3368: astore 230
    //   3370: aload 229
    //   3372: ldc_w 431
    //   3375: aload 230
    //   3377: invokeinterface 89 3 0
    //   3382: pop
    //   3383: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3386: astore 231
    //   3388: new 77	java/lang/Character
    //   3391: dup
    //   3392: ldc_w 432
    //   3395: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3398: astore 232
    //   3400: aload 231
    //   3402: ldc_w 434
    //   3405: aload 232
    //   3407: invokeinterface 89 3 0
    //   3412: pop
    //   3413: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3416: astore 233
    //   3418: new 77	java/lang/Character
    //   3421: dup
    //   3422: ldc_w 435
    //   3425: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3428: astore 234
    //   3430: aload 233
    //   3432: ldc_w 437
    //   3435: aload 234
    //   3437: invokeinterface 89 3 0
    //   3442: pop
    //   3443: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3446: astore 235
    //   3448: new 77	java/lang/Character
    //   3451: dup
    //   3452: ldc_w 438
    //   3455: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3458: astore 236
    //   3460: aload 235
    //   3462: ldc_w 440
    //   3465: aload 236
    //   3467: invokeinterface 89 3 0
    //   3472: pop
    //   3473: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3476: astore 237
    //   3478: new 77	java/lang/Character
    //   3481: dup
    //   3482: ldc_w 441
    //   3485: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3488: astore 238
    //   3490: aload 237
    //   3492: ldc_w 443
    //   3495: aload 238
    //   3497: invokeinterface 89 3 0
    //   3502: pop
    //   3503: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3506: astore 239
    //   3508: new 77	java/lang/Character
    //   3511: dup
    //   3512: ldc_w 444
    //   3515: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3518: astore 240
    //   3520: aload 239
    //   3522: ldc_w 446
    //   3525: aload 240
    //   3527: invokeinterface 89 3 0
    //   3532: pop
    //   3533: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3536: astore 241
    //   3538: new 77	java/lang/Character
    //   3541: dup
    //   3542: ldc_w 447
    //   3545: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3548: astore 242
    //   3550: aload 241
    //   3552: ldc_w 449
    //   3555: aload 242
    //   3557: invokeinterface 89 3 0
    //   3562: pop
    //   3563: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3566: astore 243
    //   3568: new 77	java/lang/Character
    //   3571: dup
    //   3572: ldc_w 450
    //   3575: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3578: astore 244
    //   3580: aload 243
    //   3582: ldc_w 452
    //   3585: aload 244
    //   3587: invokeinterface 89 3 0
    //   3592: pop
    //   3593: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3596: astore 245
    //   3598: new 77	java/lang/Character
    //   3601: dup
    //   3602: ldc_w 453
    //   3605: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3608: astore 246
    //   3610: aload 245
    //   3612: ldc_w 455
    //   3615: aload 246
    //   3617: invokeinterface 89 3 0
    //   3622: pop
    //   3623: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3626: astore 247
    //   3628: new 77	java/lang/Character
    //   3631: dup
    //   3632: ldc_w 456
    //   3635: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3638: astore 248
    //   3640: aload 247
    //   3642: ldc_w 458
    //   3645: aload 248
    //   3647: invokeinterface 89 3 0
    //   3652: pop
    //   3653: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3656: astore 249
    //   3658: new 77	java/lang/Character
    //   3661: dup
    //   3662: ldc_w 459
    //   3665: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3668: astore 250
    //   3670: aload 249
    //   3672: ldc_w 461
    //   3675: aload 250
    //   3677: invokeinterface 89 3 0
    //   3682: pop
    //   3683: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3686: astore 251
    //   3688: new 77	java/lang/Character
    //   3691: dup
    //   3692: ldc_w 462
    //   3695: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3698: astore 252
    //   3700: aload 251
    //   3702: ldc_w 464
    //   3705: aload 252
    //   3707: invokeinterface 89 3 0
    //   3712: pop
    //   3713: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3716: astore 253
    //   3718: new 77	java/lang/Character
    //   3721: dup
    //   3722: ldc_w 465
    //   3725: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3728: astore 254
    //   3730: aload 253
    //   3732: ldc_w 467
    //   3735: aload 254
    //   3737: invokeinterface 89 3 0
    //   3742: pop
    //   3743: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3746: astore 255
    //   3748: new 77	java/lang/Character
    //   3751: dup
    //   3752: ldc_w 468
    //   3755: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3758: wide
    //   3762: aload 255
    //   3764: ldc_w 470
    //   3767: wide
    //   3771: invokeinterface 89 3 0
    //   3776: pop
    //   3777: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3780: wide
    //   3784: new 77	java/lang/Character
    //   3787: dup
    //   3788: ldc_w 471
    //   3791: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3794: wide
    //   3798: wide
    //   3802: ldc_w 473
    //   3805: wide
    //   3809: invokeinterface 89 3 0
    //   3814: pop
    //   3815: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3818: wide
    //   3822: new 77	java/lang/Character
    //   3825: dup
    //   3826: ldc_w 474
    //   3829: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3832: wide
    //   3836: wide
    //   3840: ldc_w 476
    //   3843: wide
    //   3847: invokeinterface 89 3 0
    //   3852: pop
    //   3853: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3856: wide
    //   3860: new 77	java/lang/Character
    //   3863: dup
    //   3864: ldc_w 477
    //   3867: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3870: wide
    //   3874: wide
    //   3878: ldc_w 479
    //   3881: wide
    //   3885: invokeinterface 89 3 0
    //   3890: pop
    //   3891: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3894: wide
    //   3898: new 77	java/lang/Character
    //   3901: dup
    //   3902: ldc_w 480
    //   3905: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3908: wide
    //   3912: wide
    //   3916: ldc_w 482
    //   3919: wide
    //   3923: invokeinterface 89 3 0
    //   3928: pop
    //   3929: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3932: wide
    //   3936: new 77	java/lang/Character
    //   3939: dup
    //   3940: ldc_w 483
    //   3943: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3946: wide
    //   3950: wide
    //   3954: ldc_w 485
    //   3957: wide
    //   3961: invokeinterface 89 3 0
    //   3966: pop
    //   3967: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   3970: wide
    //   3974: new 77	java/lang/Character
    //   3977: dup
    //   3978: ldc_w 486
    //   3981: invokespecial 81	java/lang/Character:<init>	(C)V
    //   3984: wide
    //   3988: wide
    //   3992: ldc_w 488
    //   3995: wide
    //   3999: invokeinterface 89 3 0
    //   4004: pop
    //   4005: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4008: wide
    //   4012: new 77	java/lang/Character
    //   4015: dup
    //   4016: ldc_w 489
    //   4019: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4022: wide
    //   4026: wide
    //   4030: ldc_w 491
    //   4033: wide
    //   4037: invokeinterface 89 3 0
    //   4042: pop
    //   4043: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4046: wide
    //   4050: new 77	java/lang/Character
    //   4053: dup
    //   4054: ldc_w 492
    //   4057: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4060: wide
    //   4064: wide
    //   4068: ldc_w 494
    //   4071: wide
    //   4075: invokeinterface 89 3 0
    //   4080: pop
    //   4081: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4084: wide
    //   4088: new 77	java/lang/Character
    //   4091: dup
    //   4092: ldc_w 495
    //   4095: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4098: wide
    //   4102: wide
    //   4106: ldc_w 497
    //   4109: wide
    //   4113: invokeinterface 89 3 0
    //   4118: pop
    //   4119: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4122: wide
    //   4126: new 77	java/lang/Character
    //   4129: dup
    //   4130: ldc_w 498
    //   4133: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4136: wide
    //   4140: wide
    //   4144: ldc_w 500
    //   4147: wide
    //   4151: invokeinterface 89 3 0
    //   4156: pop
    //   4157: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4160: wide
    //   4164: new 77	java/lang/Character
    //   4167: dup
    //   4168: ldc_w 501
    //   4171: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4174: wide
    //   4178: wide
    //   4182: ldc_w 503
    //   4185: wide
    //   4189: invokeinterface 89 3 0
    //   4194: pop
    //   4195: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4198: wide
    //   4202: new 77	java/lang/Character
    //   4205: dup
    //   4206: ldc_w 504
    //   4209: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4212: wide
    //   4216: wide
    //   4220: ldc_w 506
    //   4223: wide
    //   4227: invokeinterface 89 3 0
    //   4232: pop
    //   4233: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4236: wide
    //   4240: new 77	java/lang/Character
    //   4243: dup
    //   4244: ldc_w 507
    //   4247: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4250: wide
    //   4254: wide
    //   4258: ldc_w 509
    //   4261: wide
    //   4265: invokeinterface 89 3 0
    //   4270: pop
    //   4271: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4274: wide
    //   4278: new 77	java/lang/Character
    //   4281: dup
    //   4282: ldc_w 510
    //   4285: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4288: wide
    //   4292: wide
    //   4296: ldc_w 512
    //   4299: wide
    //   4303: invokeinterface 89 3 0
    //   4308: pop
    //   4309: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4312: wide
    //   4316: new 77	java/lang/Character
    //   4319: dup
    //   4320: ldc_w 513
    //   4323: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4326: wide
    //   4330: wide
    //   4334: ldc_w 515
    //   4337: wide
    //   4341: invokeinterface 89 3 0
    //   4346: pop
    //   4347: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4350: wide
    //   4354: new 77	java/lang/Character
    //   4357: dup
    //   4358: ldc_w 516
    //   4361: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4364: wide
    //   4368: wide
    //   4372: ldc_w 518
    //   4375: wide
    //   4379: invokeinterface 89 3 0
    //   4384: pop
    //   4385: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4388: wide
    //   4392: new 77	java/lang/Character
    //   4395: dup
    //   4396: ldc_w 519
    //   4399: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4402: wide
    //   4406: wide
    //   4410: ldc_w 521
    //   4413: wide
    //   4417: invokeinterface 89 3 0
    //   4422: pop
    //   4423: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4426: wide
    //   4430: new 77	java/lang/Character
    //   4433: dup
    //   4434: ldc_w 522
    //   4437: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4440: wide
    //   4444: wide
    //   4448: ldc_w 524
    //   4451: wide
    //   4455: invokeinterface 89 3 0
    //   4460: pop
    //   4461: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4464: wide
    //   4468: new 77	java/lang/Character
    //   4471: dup
    //   4472: ldc_w 525
    //   4475: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4478: wide
    //   4482: wide
    //   4486: ldc_w 527
    //   4489: wide
    //   4493: invokeinterface 89 3 0
    //   4498: pop
    //   4499: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4502: wide
    //   4506: new 77	java/lang/Character
    //   4509: dup
    //   4510: ldc_w 528
    //   4513: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4516: wide
    //   4520: wide
    //   4524: ldc_w 530
    //   4527: wide
    //   4531: invokeinterface 89 3 0
    //   4536: pop
    //   4537: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4540: wide
    //   4544: new 77	java/lang/Character
    //   4547: dup
    //   4548: ldc_w 531
    //   4551: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4554: wide
    //   4558: wide
    //   4562: ldc_w 533
    //   4565: wide
    //   4569: invokeinterface 89 3 0
    //   4574: pop
    //   4575: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4578: wide
    //   4582: new 77	java/lang/Character
    //   4585: dup
    //   4586: ldc_w 534
    //   4589: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4592: wide
    //   4596: wide
    //   4600: ldc_w 536
    //   4603: wide
    //   4607: invokeinterface 89 3 0
    //   4612: pop
    //   4613: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4616: wide
    //   4620: new 77	java/lang/Character
    //   4623: dup
    //   4624: ldc_w 537
    //   4627: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4630: wide
    //   4634: wide
    //   4638: ldc_w 539
    //   4641: wide
    //   4645: invokeinterface 89 3 0
    //   4650: pop
    //   4651: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4654: wide
    //   4658: new 77	java/lang/Character
    //   4661: dup
    //   4662: ldc_w 540
    //   4665: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4668: wide
    //   4672: wide
    //   4676: ldc_w 542
    //   4679: wide
    //   4683: invokeinterface 89 3 0
    //   4688: pop
    //   4689: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4692: wide
    //   4696: new 77	java/lang/Character
    //   4699: dup
    //   4700: ldc_w 543
    //   4703: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4706: wide
    //   4710: wide
    //   4714: ldc_w 545
    //   4717: wide
    //   4721: invokeinterface 89 3 0
    //   4726: pop
    //   4727: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4730: wide
    //   4734: new 77	java/lang/Character
    //   4737: dup
    //   4738: ldc_w 546
    //   4741: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4744: wide
    //   4748: wide
    //   4752: ldc_w 548
    //   4755: wide
    //   4759: invokeinterface 89 3 0
    //   4764: pop
    //   4765: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4768: wide
    //   4772: new 77	java/lang/Character
    //   4775: dup
    //   4776: ldc_w 549
    //   4779: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4782: wide
    //   4786: wide
    //   4790: ldc_w 551
    //   4793: wide
    //   4797: invokeinterface 89 3 0
    //   4802: pop
    //   4803: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4806: wide
    //   4810: new 77	java/lang/Character
    //   4813: dup
    //   4814: ldc_w 552
    //   4817: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4820: wide
    //   4824: wide
    //   4828: ldc_w 554
    //   4831: wide
    //   4835: invokeinterface 89 3 0
    //   4840: pop
    //   4841: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4844: wide
    //   4848: new 77	java/lang/Character
    //   4851: dup
    //   4852: ldc_w 555
    //   4855: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4858: wide
    //   4862: wide
    //   4866: ldc_w 557
    //   4869: wide
    //   4873: invokeinterface 89 3 0
    //   4878: pop
    //   4879: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4882: wide
    //   4886: new 77	java/lang/Character
    //   4889: dup
    //   4890: ldc_w 558
    //   4893: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4896: wide
    //   4900: wide
    //   4904: ldc_w 560
    //   4907: wide
    //   4911: invokeinterface 89 3 0
    //   4916: pop
    //   4917: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4920: wide
    //   4924: new 77	java/lang/Character
    //   4927: dup
    //   4928: ldc_w 561
    //   4931: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4934: wide
    //   4938: wide
    //   4942: ldc_w 563
    //   4945: wide
    //   4949: invokeinterface 89 3 0
    //   4954: pop
    //   4955: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4958: wide
    //   4962: new 77	java/lang/Character
    //   4965: dup
    //   4966: ldc_w 564
    //   4969: invokespecial 81	java/lang/Character:<init>	(C)V
    //   4972: wide
    //   4976: wide
    //   4980: ldc_w 566
    //   4983: wide
    //   4987: invokeinterface 89 3 0
    //   4992: pop
    //   4993: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   4996: wide
    //   5000: new 77	java/lang/Character
    //   5003: dup
    //   5004: ldc_w 567
    //   5007: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5010: wide
    //   5014: wide
    //   5018: ldc_w 569
    //   5021: wide
    //   5025: invokeinterface 89 3 0
    //   5030: pop
    //   5031: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5034: wide
    //   5038: new 77	java/lang/Character
    //   5041: dup
    //   5042: ldc_w 570
    //   5045: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5048: wide
    //   5052: wide
    //   5056: ldc_w 572
    //   5059: wide
    //   5063: invokeinterface 89 3 0
    //   5068: pop
    //   5069: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5072: wide
    //   5076: new 77	java/lang/Character
    //   5079: dup
    //   5080: ldc_w 573
    //   5083: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5086: wide
    //   5090: wide
    //   5094: ldc_w 575
    //   5097: wide
    //   5101: invokeinterface 89 3 0
    //   5106: pop
    //   5107: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5110: wide
    //   5114: new 77	java/lang/Character
    //   5117: dup
    //   5118: ldc_w 576
    //   5121: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5124: wide
    //   5128: wide
    //   5132: ldc_w 578
    //   5135: wide
    //   5139: invokeinterface 89 3 0
    //   5144: pop
    //   5145: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5148: wide
    //   5152: new 77	java/lang/Character
    //   5155: dup
    //   5156: ldc_w 579
    //   5159: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5162: wide
    //   5166: wide
    //   5170: ldc_w 581
    //   5173: wide
    //   5177: invokeinterface 89 3 0
    //   5182: pop
    //   5183: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5186: wide
    //   5190: new 77	java/lang/Character
    //   5193: dup
    //   5194: ldc_w 582
    //   5197: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5200: wide
    //   5204: wide
    //   5208: ldc_w 584
    //   5211: wide
    //   5215: invokeinterface 89 3 0
    //   5220: pop
    //   5221: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5224: wide
    //   5228: new 77	java/lang/Character
    //   5231: dup
    //   5232: ldc_w 585
    //   5235: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5238: wide
    //   5242: wide
    //   5246: ldc_w 587
    //   5249: wide
    //   5253: invokeinterface 89 3 0
    //   5258: pop
    //   5259: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5262: wide
    //   5266: new 77	java/lang/Character
    //   5269: dup
    //   5270: ldc_w 588
    //   5273: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5276: wide
    //   5280: wide
    //   5284: ldc_w 590
    //   5287: wide
    //   5291: invokeinterface 89 3 0
    //   5296: pop
    //   5297: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5300: wide
    //   5304: new 77	java/lang/Character
    //   5307: dup
    //   5308: ldc_w 591
    //   5311: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5314: wide
    //   5318: wide
    //   5322: ldc_w 593
    //   5325: wide
    //   5329: invokeinterface 89 3 0
    //   5334: pop
    //   5335: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5338: wide
    //   5342: new 77	java/lang/Character
    //   5345: dup
    //   5346: ldc_w 594
    //   5349: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5352: wide
    //   5356: wide
    //   5360: ldc_w 596
    //   5363: wide
    //   5367: invokeinterface 89 3 0
    //   5372: pop
    //   5373: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5376: wide
    //   5380: new 77	java/lang/Character
    //   5383: dup
    //   5384: ldc_w 597
    //   5387: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5390: wide
    //   5394: wide
    //   5398: ldc_w 599
    //   5401: wide
    //   5405: invokeinterface 89 3 0
    //   5410: pop
    //   5411: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5414: wide
    //   5418: new 77	java/lang/Character
    //   5421: dup
    //   5422: ldc_w 600
    //   5425: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5428: wide
    //   5432: wide
    //   5436: ldc_w 602
    //   5439: wide
    //   5443: invokeinterface 89 3 0
    //   5448: pop
    //   5449: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5452: wide
    //   5456: new 77	java/lang/Character
    //   5459: dup
    //   5460: ldc_w 603
    //   5463: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5466: wide
    //   5470: wide
    //   5474: ldc_w 605
    //   5477: wide
    //   5481: invokeinterface 89 3 0
    //   5486: pop
    //   5487: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5490: wide
    //   5494: new 77	java/lang/Character
    //   5497: dup
    //   5498: ldc_w 606
    //   5501: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5504: wide
    //   5508: wide
    //   5512: ldc_w 608
    //   5515: wide
    //   5519: invokeinterface 89 3 0
    //   5524: pop
    //   5525: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5528: wide
    //   5532: new 77	java/lang/Character
    //   5535: dup
    //   5536: ldc_w 609
    //   5539: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5542: wide
    //   5546: wide
    //   5550: ldc_w 611
    //   5553: wide
    //   5557: invokeinterface 89 3 0
    //   5562: pop
    //   5563: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5566: wide
    //   5570: new 77	java/lang/Character
    //   5573: dup
    //   5574: ldc_w 612
    //   5577: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5580: wide
    //   5584: wide
    //   5588: ldc_w 614
    //   5591: wide
    //   5595: invokeinterface 89 3 0
    //   5600: pop
    //   5601: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5604: wide
    //   5608: new 77	java/lang/Character
    //   5611: dup
    //   5612: ldc_w 615
    //   5615: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5618: wide
    //   5622: wide
    //   5626: ldc_w 617
    //   5629: wide
    //   5633: invokeinterface 89 3 0
    //   5638: pop
    //   5639: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5642: wide
    //   5646: new 77	java/lang/Character
    //   5649: dup
    //   5650: ldc_w 618
    //   5653: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5656: wide
    //   5660: wide
    //   5664: ldc_w 620
    //   5667: wide
    //   5671: invokeinterface 89 3 0
    //   5676: pop
    //   5677: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5680: wide
    //   5684: new 77	java/lang/Character
    //   5687: dup
    //   5688: ldc_w 621
    //   5691: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5694: wide
    //   5698: wide
    //   5702: ldc_w 623
    //   5705: wide
    //   5709: invokeinterface 89 3 0
    //   5714: pop
    //   5715: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5718: wide
    //   5722: new 77	java/lang/Character
    //   5725: dup
    //   5726: ldc_w 624
    //   5729: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5732: wide
    //   5736: wide
    //   5740: ldc_w 626
    //   5743: wide
    //   5747: invokeinterface 89 3 0
    //   5752: pop
    //   5753: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5756: wide
    //   5760: new 77	java/lang/Character
    //   5763: dup
    //   5764: ldc_w 627
    //   5767: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5770: wide
    //   5774: wide
    //   5778: ldc_w 629
    //   5781: wide
    //   5785: invokeinterface 89 3 0
    //   5790: pop
    //   5791: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5794: wide
    //   5798: new 77	java/lang/Character
    //   5801: dup
    //   5802: ldc_w 630
    //   5805: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5808: wide
    //   5812: wide
    //   5816: ldc_w 632
    //   5819: wide
    //   5823: invokeinterface 89 3 0
    //   5828: pop
    //   5829: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5832: wide
    //   5836: new 77	java/lang/Character
    //   5839: dup
    //   5840: ldc_w 633
    //   5843: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5846: wide
    //   5850: wide
    //   5854: ldc_w 635
    //   5857: wide
    //   5861: invokeinterface 89 3 0
    //   5866: pop
    //   5867: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5870: wide
    //   5874: new 77	java/lang/Character
    //   5877: dup
    //   5878: ldc_w 636
    //   5881: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5884: wide
    //   5888: wide
    //   5892: ldc_w 638
    //   5895: wide
    //   5899: invokeinterface 89 3 0
    //   5904: pop
    //   5905: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5908: wide
    //   5912: new 77	java/lang/Character
    //   5915: dup
    //   5916: ldc_w 639
    //   5919: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5922: wide
    //   5926: wide
    //   5930: ldc_w 641
    //   5933: wide
    //   5937: invokeinterface 89 3 0
    //   5942: pop
    //   5943: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5946: wide
    //   5950: new 77	java/lang/Character
    //   5953: dup
    //   5954: ldc_w 642
    //   5957: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5960: wide
    //   5964: wide
    //   5968: ldc_w 644
    //   5971: wide
    //   5975: invokeinterface 89 3 0
    //   5980: pop
    //   5981: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   5984: wide
    //   5988: new 77	java/lang/Character
    //   5991: dup
    //   5992: ldc_w 645
    //   5995: invokespecial 81	java/lang/Character:<init>	(C)V
    //   5998: wide
    //   6002: wide
    //   6006: ldc_w 647
    //   6009: wide
    //   6013: invokeinterface 89 3 0
    //   6018: pop
    //   6019: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6022: wide
    //   6026: new 77	java/lang/Character
    //   6029: dup
    //   6030: ldc_w 648
    //   6033: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6036: wide
    //   6040: wide
    //   6044: ldc_w 650
    //   6047: wide
    //   6051: invokeinterface 89 3 0
    //   6056: pop
    //   6057: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6060: wide
    //   6064: new 77	java/lang/Character
    //   6067: dup
    //   6068: ldc_w 651
    //   6071: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6074: wide
    //   6078: wide
    //   6082: ldc_w 653
    //   6085: wide
    //   6089: invokeinterface 89 3 0
    //   6094: pop
    //   6095: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6098: wide
    //   6102: new 77	java/lang/Character
    //   6105: dup
    //   6106: ldc_w 654
    //   6109: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6112: wide
    //   6116: wide
    //   6120: ldc_w 656
    //   6123: wide
    //   6127: invokeinterface 89 3 0
    //   6132: pop
    //   6133: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6136: wide
    //   6140: new 77	java/lang/Character
    //   6143: dup
    //   6144: ldc_w 657
    //   6147: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6150: wide
    //   6154: wide
    //   6158: ldc_w 659
    //   6161: wide
    //   6165: invokeinterface 89 3 0
    //   6170: pop
    //   6171: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6174: wide
    //   6178: new 77	java/lang/Character
    //   6181: dup
    //   6182: ldc_w 660
    //   6185: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6188: wide
    //   6192: wide
    //   6196: ldc_w 662
    //   6199: wide
    //   6203: invokeinterface 89 3 0
    //   6208: pop
    //   6209: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6212: wide
    //   6216: new 77	java/lang/Character
    //   6219: dup
    //   6220: ldc_w 663
    //   6223: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6226: wide
    //   6230: wide
    //   6234: ldc_w 665
    //   6237: wide
    //   6241: invokeinterface 89 3 0
    //   6246: pop
    //   6247: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6250: wide
    //   6254: new 77	java/lang/Character
    //   6257: dup
    //   6258: ldc_w 666
    //   6261: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6264: wide
    //   6268: wide
    //   6272: ldc_w 668
    //   6275: wide
    //   6279: invokeinterface 89 3 0
    //   6284: pop
    //   6285: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6288: wide
    //   6292: new 77	java/lang/Character
    //   6295: dup
    //   6296: ldc_w 669
    //   6299: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6302: wide
    //   6306: wide
    //   6310: ldc_w 671
    //   6313: wide
    //   6317: invokeinterface 89 3 0
    //   6322: pop
    //   6323: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6326: wide
    //   6330: new 77	java/lang/Character
    //   6333: dup
    //   6334: ldc_w 672
    //   6337: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6340: wide
    //   6344: wide
    //   6348: ldc_w 674
    //   6351: wide
    //   6355: invokeinterface 89 3 0
    //   6360: pop
    //   6361: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6364: wide
    //   6368: new 77	java/lang/Character
    //   6371: dup
    //   6372: ldc_w 675
    //   6375: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6378: wide
    //   6382: wide
    //   6386: ldc_w 677
    //   6389: wide
    //   6393: invokeinterface 89 3 0
    //   6398: pop
    //   6399: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6402: wide
    //   6406: new 77	java/lang/Character
    //   6409: dup
    //   6410: ldc_w 678
    //   6413: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6416: wide
    //   6420: wide
    //   6424: ldc_w 680
    //   6427: wide
    //   6431: invokeinterface 89 3 0
    //   6436: pop
    //   6437: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6440: wide
    //   6444: new 77	java/lang/Character
    //   6447: dup
    //   6448: ldc_w 681
    //   6451: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6454: wide
    //   6458: wide
    //   6462: ldc_w 683
    //   6465: wide
    //   6469: invokeinterface 89 3 0
    //   6474: pop
    //   6475: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6478: wide
    //   6482: new 77	java/lang/Character
    //   6485: dup
    //   6486: ldc_w 684
    //   6489: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6492: wide
    //   6496: wide
    //   6500: ldc_w 686
    //   6503: wide
    //   6507: invokeinterface 89 3 0
    //   6512: pop
    //   6513: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6516: wide
    //   6520: new 77	java/lang/Character
    //   6523: dup
    //   6524: ldc_w 687
    //   6527: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6530: wide
    //   6534: wide
    //   6538: ldc_w 689
    //   6541: wide
    //   6545: invokeinterface 89 3 0
    //   6550: pop
    //   6551: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6554: wide
    //   6558: new 77	java/lang/Character
    //   6561: dup
    //   6562: ldc_w 690
    //   6565: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6568: wide
    //   6572: wide
    //   6576: ldc_w 692
    //   6579: wide
    //   6583: invokeinterface 89 3 0
    //   6588: pop
    //   6589: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6592: wide
    //   6596: new 77	java/lang/Character
    //   6599: dup
    //   6600: ldc_w 693
    //   6603: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6606: wide
    //   6610: wide
    //   6614: ldc_w 695
    //   6617: wide
    //   6621: invokeinterface 89 3 0
    //   6626: pop
    //   6627: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6630: wide
    //   6634: new 77	java/lang/Character
    //   6637: dup
    //   6638: ldc_w 696
    //   6641: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6644: wide
    //   6648: wide
    //   6652: ldc_w 698
    //   6655: wide
    //   6659: invokeinterface 89 3 0
    //   6664: pop
    //   6665: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6668: wide
    //   6672: new 77	java/lang/Character
    //   6675: dup
    //   6676: ldc_w 699
    //   6679: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6682: wide
    //   6686: wide
    //   6690: ldc_w 701
    //   6693: wide
    //   6697: invokeinterface 89 3 0
    //   6702: pop
    //   6703: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6706: wide
    //   6710: new 77	java/lang/Character
    //   6713: dup
    //   6714: ldc_w 702
    //   6717: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6720: wide
    //   6724: wide
    //   6728: ldc_w 704
    //   6731: wide
    //   6735: invokeinterface 89 3 0
    //   6740: pop
    //   6741: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6744: wide
    //   6748: new 77	java/lang/Character
    //   6751: dup
    //   6752: ldc_w 705
    //   6755: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6758: wide
    //   6762: wide
    //   6766: ldc_w 707
    //   6769: wide
    //   6773: invokeinterface 89 3 0
    //   6778: pop
    //   6779: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6782: wide
    //   6786: new 77	java/lang/Character
    //   6789: dup
    //   6790: ldc_w 708
    //   6793: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6796: wide
    //   6800: wide
    //   6804: ldc_w 710
    //   6807: wide
    //   6811: invokeinterface 89 3 0
    //   6816: pop
    //   6817: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6820: wide
    //   6824: new 77	java/lang/Character
    //   6827: dup
    //   6828: ldc_w 711
    //   6831: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6834: wide
    //   6838: wide
    //   6842: ldc_w 713
    //   6845: wide
    //   6849: invokeinterface 89 3 0
    //   6854: pop
    //   6855: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6858: wide
    //   6862: new 77	java/lang/Character
    //   6865: dup
    //   6866: ldc_w 714
    //   6869: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6872: wide
    //   6876: wide
    //   6880: ldc_w 716
    //   6883: wide
    //   6887: invokeinterface 89 3 0
    //   6892: pop
    //   6893: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6896: wide
    //   6900: new 77	java/lang/Character
    //   6903: dup
    //   6904: ldc_w 717
    //   6907: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6910: wide
    //   6914: wide
    //   6918: ldc_w 719
    //   6921: wide
    //   6925: invokeinterface 89 3 0
    //   6930: pop
    //   6931: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6934: wide
    //   6938: new 77	java/lang/Character
    //   6941: dup
    //   6942: ldc_w 720
    //   6945: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6948: wide
    //   6952: wide
    //   6956: ldc_w 722
    //   6959: wide
    //   6963: invokeinterface 89 3 0
    //   6968: pop
    //   6969: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   6972: wide
    //   6976: new 77	java/lang/Character
    //   6979: dup
    //   6980: ldc_w 723
    //   6983: invokespecial 81	java/lang/Character:<init>	(C)V
    //   6986: wide
    //   6990: wide
    //   6994: ldc_w 725
    //   6997: wide
    //   7001: invokeinterface 89 3 0
    //   7006: pop
    //   7007: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7010: wide
    //   7014: new 77	java/lang/Character
    //   7017: dup
    //   7018: ldc_w 726
    //   7021: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7024: wide
    //   7028: wide
    //   7032: ldc_w 728
    //   7035: wide
    //   7039: invokeinterface 89 3 0
    //   7044: pop
    //   7045: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7048: wide
    //   7052: new 77	java/lang/Character
    //   7055: dup
    //   7056: ldc_w 729
    //   7059: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7062: wide
    //   7066: wide
    //   7070: ldc_w 731
    //   7073: wide
    //   7077: invokeinterface 89 3 0
    //   7082: pop
    //   7083: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7086: wide
    //   7090: new 77	java/lang/Character
    //   7093: dup
    //   7094: ldc_w 732
    //   7097: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7100: wide
    //   7104: wide
    //   7108: ldc_w 734
    //   7111: wide
    //   7115: invokeinterface 89 3 0
    //   7120: pop
    //   7121: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7124: wide
    //   7128: new 77	java/lang/Character
    //   7131: dup
    //   7132: ldc_w 735
    //   7135: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7138: wide
    //   7142: wide
    //   7146: ldc_w 737
    //   7149: wide
    //   7153: invokeinterface 89 3 0
    //   7158: pop
    //   7159: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7162: wide
    //   7166: new 77	java/lang/Character
    //   7169: dup
    //   7170: ldc_w 738
    //   7173: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7176: wide
    //   7180: wide
    //   7184: ldc_w 740
    //   7187: wide
    //   7191: invokeinterface 89 3 0
    //   7196: pop
    //   7197: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7200: wide
    //   7204: new 77	java/lang/Character
    //   7207: dup
    //   7208: ldc_w 741
    //   7211: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7214: wide
    //   7218: wide
    //   7222: ldc_w 743
    //   7225: wide
    //   7229: invokeinterface 89 3 0
    //   7234: pop
    //   7235: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7238: wide
    //   7242: new 77	java/lang/Character
    //   7245: dup
    //   7246: ldc_w 744
    //   7249: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7252: wide
    //   7256: wide
    //   7260: ldc_w 746
    //   7263: wide
    //   7267: invokeinterface 89 3 0
    //   7272: pop
    //   7273: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7276: wide
    //   7280: new 77	java/lang/Character
    //   7283: dup
    //   7284: ldc_w 747
    //   7287: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7290: wide
    //   7294: wide
    //   7298: ldc_w 749
    //   7301: wide
    //   7305: invokeinterface 89 3 0
    //   7310: pop
    //   7311: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7314: wide
    //   7318: new 77	java/lang/Character
    //   7321: dup
    //   7322: ldc_w 750
    //   7325: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7328: wide
    //   7332: wide
    //   7336: ldc_w 752
    //   7339: wide
    //   7343: invokeinterface 89 3 0
    //   7348: pop
    //   7349: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7352: wide
    //   7356: new 77	java/lang/Character
    //   7359: dup
    //   7360: ldc_w 753
    //   7363: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7366: wide
    //   7370: wide
    //   7374: ldc_w 755
    //   7377: wide
    //   7381: invokeinterface 89 3 0
    //   7386: pop
    //   7387: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7390: wide
    //   7394: new 77	java/lang/Character
    //   7397: dup
    //   7398: ldc_w 756
    //   7401: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7404: wide
    //   7408: wide
    //   7412: ldc_w 758
    //   7415: wide
    //   7419: invokeinterface 89 3 0
    //   7424: pop
    //   7425: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7428: wide
    //   7432: new 77	java/lang/Character
    //   7435: dup
    //   7436: ldc_w 759
    //   7439: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7442: wide
    //   7446: wide
    //   7450: ldc_w 761
    //   7453: wide
    //   7457: invokeinterface 89 3 0
    //   7462: pop
    //   7463: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7466: wide
    //   7470: new 77	java/lang/Character
    //   7473: dup
    //   7474: ldc_w 762
    //   7477: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7480: wide
    //   7484: wide
    //   7488: ldc_w 764
    //   7491: wide
    //   7495: invokeinterface 89 3 0
    //   7500: pop
    //   7501: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7504: wide
    //   7508: new 77	java/lang/Character
    //   7511: dup
    //   7512: ldc_w 765
    //   7515: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7518: wide
    //   7522: wide
    //   7526: ldc_w 767
    //   7529: wide
    //   7533: invokeinterface 89 3 0
    //   7538: pop
    //   7539: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7542: wide
    //   7546: new 77	java/lang/Character
    //   7549: dup
    //   7550: ldc_w 768
    //   7553: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7556: wide
    //   7560: wide
    //   7564: ldc_w 770
    //   7567: wide
    //   7571: invokeinterface 89 3 0
    //   7576: pop
    //   7577: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7580: wide
    //   7584: new 77	java/lang/Character
    //   7587: dup
    //   7588: ldc_w 771
    //   7591: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7594: wide
    //   7598: wide
    //   7602: ldc_w 773
    //   7605: wide
    //   7609: invokeinterface 89 3 0
    //   7614: pop
    //   7615: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7618: wide
    //   7622: new 77	java/lang/Character
    //   7625: dup
    //   7626: ldc_w 774
    //   7629: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7632: wide
    //   7636: wide
    //   7640: ldc_w 776
    //   7643: wide
    //   7647: invokeinterface 89 3 0
    //   7652: pop
    //   7653: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7656: wide
    //   7660: new 77	java/lang/Character
    //   7663: dup
    //   7664: ldc_w 777
    //   7667: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7670: wide
    //   7674: wide
    //   7678: ldc_w 779
    //   7681: wide
    //   7685: invokeinterface 89 3 0
    //   7690: pop
    //   7691: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7694: wide
    //   7698: new 77	java/lang/Character
    //   7701: dup
    //   7702: ldc_w 780
    //   7705: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7708: wide
    //   7712: wide
    //   7716: ldc_w 782
    //   7719: wide
    //   7723: invokeinterface 89 3 0
    //   7728: pop
    //   7729: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7732: wide
    //   7736: new 77	java/lang/Character
    //   7739: dup
    //   7740: ldc_w 783
    //   7743: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7746: wide
    //   7750: wide
    //   7754: ldc_w 785
    //   7757: wide
    //   7761: invokeinterface 89 3 0
    //   7766: pop
    //   7767: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7770: wide
    //   7774: new 77	java/lang/Character
    //   7777: dup
    //   7778: ldc_w 786
    //   7781: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7784: wide
    //   7788: wide
    //   7792: ldc_w 788
    //   7795: wide
    //   7799: invokeinterface 89 3 0
    //   7804: pop
    //   7805: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7808: wide
    //   7812: new 77	java/lang/Character
    //   7815: dup
    //   7816: ldc_w 789
    //   7819: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7822: wide
    //   7826: wide
    //   7830: ldc_w 791
    //   7833: wide
    //   7837: invokeinterface 89 3 0
    //   7842: pop
    //   7843: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7846: wide
    //   7850: new 77	java/lang/Character
    //   7853: dup
    //   7854: ldc_w 792
    //   7857: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7860: wide
    //   7864: wide
    //   7868: ldc_w 794
    //   7871: wide
    //   7875: invokeinterface 89 3 0
    //   7880: pop
    //   7881: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7884: wide
    //   7888: new 77	java/lang/Character
    //   7891: dup
    //   7892: ldc_w 795
    //   7895: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7898: wide
    //   7902: wide
    //   7906: ldc_w 797
    //   7909: wide
    //   7913: invokeinterface 89 3 0
    //   7918: pop
    //   7919: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7922: wide
    //   7926: new 77	java/lang/Character
    //   7929: dup
    //   7930: ldc_w 798
    //   7933: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7936: wide
    //   7940: wide
    //   7944: ldc_w 800
    //   7947: wide
    //   7951: invokeinterface 89 3 0
    //   7956: pop
    //   7957: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7960: wide
    //   7964: new 77	java/lang/Character
    //   7967: dup
    //   7968: ldc_w 801
    //   7971: invokespecial 81	java/lang/Character:<init>	(C)V
    //   7974: wide
    //   7978: wide
    //   7982: ldc_w 803
    //   7985: wide
    //   7989: invokeinterface 89 3 0
    //   7994: pop
    //   7995: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   7998: wide
    //   8002: new 77	java/lang/Character
    //   8005: dup
    //   8006: ldc_w 804
    //   8009: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8012: wide
    //   8016: wide
    //   8020: ldc_w 806
    //   8023: wide
    //   8027: invokeinterface 89 3 0
    //   8032: pop
    //   8033: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8036: wide
    //   8040: new 77	java/lang/Character
    //   8043: dup
    //   8044: ldc_w 807
    //   8047: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8050: wide
    //   8054: wide
    //   8058: ldc_w 809
    //   8061: wide
    //   8065: invokeinterface 89 3 0
    //   8070: pop
    //   8071: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8074: wide
    //   8078: new 77	java/lang/Character
    //   8081: dup
    //   8082: ldc_w 810
    //   8085: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8088: wide
    //   8092: wide
    //   8096: ldc_w 812
    //   8099: wide
    //   8103: invokeinterface 89 3 0
    //   8108: pop
    //   8109: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8112: wide
    //   8116: new 77	java/lang/Character
    //   8119: dup
    //   8120: ldc_w 813
    //   8123: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8126: wide
    //   8130: wide
    //   8134: ldc_w 815
    //   8137: wide
    //   8141: invokeinterface 89 3 0
    //   8146: pop
    //   8147: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8150: wide
    //   8154: new 77	java/lang/Character
    //   8157: dup
    //   8158: ldc_w 816
    //   8161: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8164: wide
    //   8168: wide
    //   8172: ldc_w 818
    //   8175: wide
    //   8179: invokeinterface 89 3 0
    //   8184: pop
    //   8185: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8188: wide
    //   8192: new 77	java/lang/Character
    //   8195: dup
    //   8196: ldc_w 819
    //   8199: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8202: wide
    //   8206: wide
    //   8210: ldc_w 821
    //   8213: wide
    //   8217: invokeinterface 89 3 0
    //   8222: pop
    //   8223: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8226: wide
    //   8230: new 77	java/lang/Character
    //   8233: dup
    //   8234: ldc_w 822
    //   8237: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8240: wide
    //   8244: wide
    //   8248: ldc_w 824
    //   8251: wide
    //   8255: invokeinterface 89 3 0
    //   8260: pop
    //   8261: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8264: wide
    //   8268: new 77	java/lang/Character
    //   8271: dup
    //   8272: ldc_w 825
    //   8275: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8278: wide
    //   8282: wide
    //   8286: ldc_w 827
    //   8289: wide
    //   8293: invokeinterface 89 3 0
    //   8298: pop
    //   8299: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8302: wide
    //   8306: new 77	java/lang/Character
    //   8309: dup
    //   8310: ldc_w 828
    //   8313: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8316: wide
    //   8320: wide
    //   8324: ldc_w 830
    //   8327: wide
    //   8331: invokeinterface 89 3 0
    //   8336: pop
    //   8337: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8340: wide
    //   8344: new 77	java/lang/Character
    //   8347: dup
    //   8348: ldc_w 831
    //   8351: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8354: wide
    //   8358: wide
    //   8362: ldc_w 833
    //   8365: wide
    //   8369: invokeinterface 89 3 0
    //   8374: pop
    //   8375: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8378: wide
    //   8382: new 77	java/lang/Character
    //   8385: dup
    //   8386: ldc_w 834
    //   8389: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8392: wide
    //   8396: wide
    //   8400: ldc_w 836
    //   8403: wide
    //   8407: invokeinterface 89 3 0
    //   8412: pop
    //   8413: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8416: wide
    //   8420: new 77	java/lang/Character
    //   8423: dup
    //   8424: ldc_w 837
    //   8427: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8430: wide
    //   8434: wide
    //   8438: ldc_w 839
    //   8441: wide
    //   8445: invokeinterface 89 3 0
    //   8450: pop
    //   8451: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   8454: wide
    //   8458: new 77	java/lang/Character
    //   8461: dup
    //   8462: ldc_w 840
    //   8465: invokespecial 81	java/lang/Character:<init>	(C)V
    //   8468: wide
    //   8472: wide
    //   8476: ldc_w 842
    //   8479: wide
    //   8483: invokeinterface 89 3 0
    //   8488: pop
    //   8489: iconst_5
    //   8490: anewarray 844	java/lang/String
    //   8493: wide
    //   8497: wide
    //   8501: iconst_0
    //   8502: ldc_w 846
    //   8505: aastore
    //   8506: wide
    //   8510: iconst_1
    //   8511: ldc_w 848
    //   8514: aastore
    //   8515: wide
    //   8519: iconst_2
    //   8520: ldc_w 850
    //   8523: aastore
    //   8524: wide
    //   8528: iconst_3
    //   8529: ldc_w 852
    //   8532: aastore
    //   8533: wide
    //   8537: iconst_4
    //   8538: ldc_w 854
    //   8541: aastore
    //   8542: wide
    //   8546: putstatic 856	com/yingyonghui/market/util/StringUtil:UNSAFE_TAGS	[Ljava/lang/String;
    //   8549: new 858	java/util/HashSet
    //   8552: dup
    //   8553: invokespecial 860	java/util/HashSet:<init>	()V
    //   8556: wide
    //   8560: getstatic 865	java/lang/Character$UnicodeBlock:HANGUL_JAMO	Ljava/lang/Character$UnicodeBlock;
    //   8563: wide
    //   8567: wide
    //   8571: wide
    //   8575: invokeinterface 871 2 0
    //   8580: pop
    //   8581: getstatic 874	java/lang/Character$UnicodeBlock:CJK_RADICALS_SUPPLEMENT	Ljava/lang/Character$UnicodeBlock;
    //   8584: wide
    //   8588: wide
    //   8592: wide
    //   8596: invokeinterface 871 2 0
    //   8601: pop
    //   8602: getstatic 877	java/lang/Character$UnicodeBlock:KANGXI_RADICALS	Ljava/lang/Character$UnicodeBlock;
    //   8605: wide
    //   8609: wide
    //   8613: wide
    //   8617: invokeinterface 871 2 0
    //   8622: pop
    //   8623: getstatic 880	java/lang/Character$UnicodeBlock:CJK_SYMBOLS_AND_PUNCTUATION	Ljava/lang/Character$UnicodeBlock;
    //   8626: wide
    //   8630: wide
    //   8634: wide
    //   8638: invokeinterface 871 2 0
    //   8643: pop
    //   8644: getstatic 883	java/lang/Character$UnicodeBlock:HIRAGANA	Ljava/lang/Character$UnicodeBlock;
    //   8647: wide
    //   8651: wide
    //   8655: wide
    //   8659: invokeinterface 871 2 0
    //   8664: pop
    //   8665: getstatic 886	java/lang/Character$UnicodeBlock:KATAKANA	Ljava/lang/Character$UnicodeBlock;
    //   8668: wide
    //   8672: wide
    //   8676: wide
    //   8680: invokeinterface 871 2 0
    //   8685: pop
    //   8686: getstatic 889	java/lang/Character$UnicodeBlock:BOPOMOFO	Ljava/lang/Character$UnicodeBlock;
    //   8689: wide
    //   8693: wide
    //   8697: wide
    //   8701: invokeinterface 871 2 0
    //   8706: pop
    //   8707: getstatic 892	java/lang/Character$UnicodeBlock:HANGUL_COMPATIBILITY_JAMO	Ljava/lang/Character$UnicodeBlock;
    //   8710: wide
    //   8714: wide
    //   8718: wide
    //   8722: invokeinterface 871 2 0
    //   8727: pop
    //   8728: getstatic 895	java/lang/Character$UnicodeBlock:KANBUN	Ljava/lang/Character$UnicodeBlock;
    //   8731: wide
    //   8735: wide
    //   8739: wide
    //   8743: invokeinterface 871 2 0
    //   8748: pop
    //   8749: getstatic 898	java/lang/Character$UnicodeBlock:BOPOMOFO_EXTENDED	Ljava/lang/Character$UnicodeBlock;
    //   8752: wide
    //   8756: wide
    //   8760: wide
    //   8764: invokeinterface 871 2 0
    //   8769: pop
    //   8770: getstatic 901	java/lang/Character$UnicodeBlock:KATAKANA_PHONETIC_EXTENSIONS	Ljava/lang/Character$UnicodeBlock;
    //   8773: wide
    //   8777: wide
    //   8781: wide
    //   8785: invokeinterface 871 2 0
    //   8790: pop
    //   8791: getstatic 904	java/lang/Character$UnicodeBlock:ENCLOSED_CJK_LETTERS_AND_MONTHS	Ljava/lang/Character$UnicodeBlock;
    //   8794: wide
    //   8798: wide
    //   8802: wide
    //   8806: invokeinterface 871 2 0
    //   8811: pop
    //   8812: getstatic 907	java/lang/Character$UnicodeBlock:CJK_COMPATIBILITY	Ljava/lang/Character$UnicodeBlock;
    //   8815: wide
    //   8819: wide
    //   8823: wide
    //   8827: invokeinterface 871 2 0
    //   8832: pop
    //   8833: getstatic 910	java/lang/Character$UnicodeBlock:CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A	Ljava/lang/Character$UnicodeBlock;
    //   8836: wide
    //   8840: wide
    //   8844: wide
    //   8848: invokeinterface 871 2 0
    //   8853: pop
    //   8854: getstatic 913	java/lang/Character$UnicodeBlock:CJK_UNIFIED_IDEOGRAPHS	Ljava/lang/Character$UnicodeBlock;
    //   8857: wide
    //   8861: wide
    //   8865: wide
    //   8869: invokeinterface 871 2 0
    //   8874: pop
    //   8875: getstatic 916	java/lang/Character$UnicodeBlock:HANGUL_SYLLABLES	Ljava/lang/Character$UnicodeBlock;
    //   8878: wide
    //   8882: wide
    //   8886: wide
    //   8890: invokeinterface 871 2 0
    //   8895: pop
    //   8896: getstatic 919	java/lang/Character$UnicodeBlock:CJK_COMPATIBILITY_IDEOGRAPHS	Ljava/lang/Character$UnicodeBlock;
    //   8899: wide
    //   8903: wide
    //   8907: wide
    //   8911: invokeinterface 871 2 0
    //   8916: pop
    //   8917: getstatic 922	java/lang/Character$UnicodeBlock:CJK_COMPATIBILITY_FORMS	Ljava/lang/Character$UnicodeBlock;
    //   8920: wide
    //   8924: wide
    //   8928: wide
    //   8932: invokeinterface 871 2 0
    //   8937: pop
    //   8938: getstatic 925	java/lang/Character$UnicodeBlock:HALFWIDTH_AND_FULLWIDTH_FORMS	Ljava/lang/Character$UnicodeBlock;
    //   8941: wide
    //   8945: wide
    //   8949: wide
    //   8953: invokeinterface 871 2 0
    //   8958: pop
    //   8959: getstatic 928	java/lang/Character$UnicodeBlock:CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B	Ljava/lang/Character$UnicodeBlock;
    //   8962: wide
    //   8966: wide
    //   8970: wide
    //   8974: invokeinterface 871 2 0
    //   8979: pop
    //   8980: getstatic 931	java/lang/Character$UnicodeBlock:CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT	Ljava/lang/Character$UnicodeBlock;
    //   8983: wide
    //   8987: wide
    //   8991: wide
    //   8995: invokeinterface 871 2 0
    //   9000: pop
    //   9001: wide
    //   9005: invokestatic 937	java/util/Collections:unmodifiableSet	(Ljava/util/Set;)Ljava/util/Set;
    //   9008: putstatic 939	com/yingyonghui/market/util/StringUtil:CJK_BLOCKS	Ljava/util/Set;
    //   9011: bipush 16
    //   9013: newarray char
    //   9015: dup
    //   9016: iconst_0
    //   9017: aconst_null
    //   9018: sastore
    //   9019: dup
    //   9020: iconst_1
    //   9021: aconst_null
    //   9022: sastore
    //   9023: dup
    //   9024: iconst_2
    //   9025: aconst_null
    //   9026: sastore
    //   9027: dup
    //   9028: iconst_3
    //   9029: aconst_null
    //   9030: sastore
    //   9031: dup
    //   9032: iconst_4
    //   9033: aconst_null
    //   9034: sastore
    //   9035: dup
    //   9036: iconst_5
    //   9037: aconst_null
    //   9038: sastore
    //   9039: dup
    //   9040: bipush 6
    //   9042: aconst_null
    //   9043: sastore
    //   9044: dup
    //   9045: bipush 7
    //   9047: aconst_null
    //   9048: sastore
    //   9049: dup
    //   9050: bipush 8
    //   9052: aconst_null
    //   9053: sastore
    //   9054: dup
    //   9055: bipush 9
    //   9057: aconst_null
    //   9058: sastore
    //   9059: dup
    //   9060: bipush 10
    //   9062: aconst_null
    //   9063: sastore
    //   9064: dup
    //   9065: bipush 11
    //   9067: aconst_null
    //   9068: sastore
    //   9069: dup
    //   9070: bipush 12
    //   9072: aconst_null
    //   9073: sastore
    //   9074: dup
    //   9075: bipush 13
    //   9077: aconst_null
    //   9078: sastore
    //   9079: dup
    //   9080: bipush 14
    //   9082: aconst_null
    //   9083: sastore
    //   9084: dup
    //   9085: bipush 15
    //   9087: aconst_null
    //   9088: sastore
    //   9089: putstatic 941	com/yingyonghui/market/util/StringUtil:hexChars	[C
    //   9092: return
    //   9093: ldc 8
    //   9095: istore_0
    //   9096: goto -9085 -> 11
  }

  public static String abbreviate(String paramString, int paramInt)
  {
    return abbreviate(paramString, 0, paramInt);
  }

  public static String abbreviate(String paramString, int paramInt1, int paramInt2)
  {
    String str1;
    if (paramString == null)
      str1 = null;
    while (true)
    {
      return str1;
      if (paramInt2 < 4)
        throw new IllegalArgumentException("Minimum abbreviation width is 4");
      if (paramString.length() <= paramInt2)
      {
        str1 = paramString;
        continue;
      }
      int i = paramString.length();
      if (paramInt1 > i)
        paramInt1 = paramString.length();
      int j = paramString.length() - paramInt1;
      int k = paramInt2 - 3;
      if (j < k)
      {
        int m = paramString.length();
        int n = paramInt2 - 3;
        paramInt1 = m - n;
      }
      if (paramInt1 <= 4)
      {
        int i1 = paramInt2 - 3;
        String str2 = String.valueOf(paramString.substring(0, i1));
        str1 = str2 + "...";
        continue;
      }
      if (paramInt2 < 7)
        throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
      int i2 = paramInt2 - 3 + paramInt1;
      int i3 = paramString.length();
      if (i2 < i3)
      {
        StringBuilder localStringBuilder1 = new StringBuilder("...");
        String str3 = paramString.substring(paramInt1);
        int i4 = paramInt2 - 3;
        String str4 = abbreviate(str3, i4);
        str1 = str4;
        continue;
      }
      StringBuilder localStringBuilder2 = new StringBuilder("...");
      int i5 = paramString.length();
      int i6 = paramInt2 - 3;
      int i7 = i5 - i6;
      String str5 = paramString.substring(i7);
      str1 = str5;
    }
  }

  public static boolean allAscii(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    while (true)
    {
      if (j >= i);
      for (int k = 1; ; k = 0)
      {
        return k;
        if ((paramString.charAt(j) & 0xFF80) == 0)
          break;
      }
      j += 1;
    }
  }

  public static void appendHexJavaScriptRepresentation(StringBuilder paramStringBuilder, char paramChar)
  {
    paramStringBuilder.append("\\u");
    String str = Integer.toHexString(paramChar);
    int i = str.length();
    while (true)
    {
      if (i >= 4)
      {
        paramStringBuilder.append(str);
        return;
      }
      paramStringBuilder.append(48);
      i += 1;
    }
  }

  @Signature({"(", "Ljava/util/Map", "<", "Ljava/lang/String;", "[", "Ljava/lang/String;", ">;", "Ljava/lang/String;", "Ljava/lang/String;", ")", "Ljava/lang/String;"})
  public static String arrayMap2String(Map paramMap, String paramString1, String paramString2)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    StringWriter localStringWriter = new StringWriter();
    if (!localIterator.hasNext())
      return localStringWriter.toString();
    Map.Entry localEntry = (Map.Entry)localIterator.next();
    String str1 = (String)localEntry.getKey();
    String[] arrayOfString = (String[])localEntry.getValue();
    int i = 0;
    while (true)
    {
      int j = arrayOfString.length;
      if (i >= j)
      {
        if (!localIterator.hasNext())
          break;
        localStringWriter.write(paramString2);
        break;
      }
      String str2 = String.valueOf((String)localEntry.getKey());
      StringBuilder localStringBuilder = new StringBuilder(str2).append(paramString1);
      String str3 = arrayOfString[i];
      String str4 = str3;
      localStringWriter.write(str4);
      int k = arrayOfString.length - 1;
      if (i < k)
        localStringWriter.write(paramString2);
      i += 1;
    }
  }

  private static String bytesToEncoding(byte[] paramArrayOfByte, String paramString)
  {
    String str1;
    if (paramArrayOfByte == null)
      str1 = null;
    String str3;
    while (true)
    {
      return str1;
      try
      {
        str1 = new String(paramArrayOfByte, paramString);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        String str2 = String.valueOf(paramString);
        str3 = str2 + " not supported! Original exception: " + localUnsupportedEncodingException;
      }
    }
    throw new Error(str3);
  }

  public static String bytesToHexString(byte[] paramArrayOfByte)
  {
    return bytesToHexString(paramArrayOfByte, null);
  }

  public static String bytesToHexString(byte[] paramArrayOfByte, Character paramCharacter)
  {
    StringBuffer localStringBuffer = new java/lang/StringBuffer;
    int i = paramArrayOfByte.length;
    int j;
    int m;
    if (paramCharacter == null)
    {
      j = 2;
      int k = i * j;
      localStringBuffer.<init>(k);
      m = 0;
    }
    while (true)
    {
      int n = paramArrayOfByte.length;
      if (m >= n)
      {
        return localStringBuffer.toString();
        j = 3;
        break;
      }
      int i1 = paramArrayOfByte[m] >>> 4 & 0xF;
      int i2 = paramArrayOfByte[m] & 0xF;
      if ((m > 0) && (paramCharacter != null))
      {
        char c1 = paramCharacter.charValue();
        localStringBuffer.append(c1);
      }
      char c2 = hexChars[i1];
      localStringBuffer.append(c2);
      char c3 = hexChars[i2];
      localStringBuffer.append(c3);
      m += 1;
    }
  }

  public static String bytesToLatin1(byte[] paramArrayOfByte)
  {
    return bytesToEncoding(paramArrayOfByte, "ISO-8859-1");
  }

  // ERROR //
  @Signature({"([B)", "Ljava/util/List", "<", "Ljava/lang/String;", ">;"})
  public static List bytesToStringList(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 1088	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 1089	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: ifnonnull +5 -> 14
    //   12: aload_1
    //   13: areturn
    //   14: new 1091	java/io/ByteArrayInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 1094	java/io/ByteArrayInputStream:<init>	([B)V
    //   22: astore_2
    //   23: new 1096	java/io/InputStreamReader
    //   26: dup
    //   27: aload_2
    //   28: ldc_w 1098
    //   31: invokespecial 1101	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   34: astore_3
    //   35: new 1103	java/io/BufferedReader
    //   38: dup
    //   39: aload_3
    //   40: invokespecial 1106	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   43: astore 4
    //   45: aload 4
    //   47: invokevirtual 1109	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   50: astore 5
    //   52: aload 5
    //   54: ifnonnull +35 -> 89
    //   57: aload 4
    //   59: invokevirtual 1112	java/io/BufferedReader:close	()V
    //   62: goto -50 -> 12
    //   65: astore 6
    //   67: new 1114	java/lang/RuntimeException
    //   70: dup
    //   71: aload 6
    //   73: invokespecial 1117	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   76: athrow
    //   77: astore 7
    //   79: new 1114	java/lang/RuntimeException
    //   82: dup
    //   83: aload 7
    //   85: invokespecial 1117	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   88: athrow
    //   89: aload_1
    //   90: aload 5
    //   92: invokeinterface 1120 2 0
    //   97: pop
    //   98: aload 4
    //   100: invokevirtual 1109	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   103: astore 5
    //   105: goto -53 -> 52
    //   108: astore 8
    //   110: aload 4
    //   112: invokevirtual 1112	java/io/BufferedReader:close	()V
    //   115: aload 8
    //   117: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   57	62	65	java/io/IOException
    //   110	118	65	java/io/IOException
    //   14	45	77	java/io/UnsupportedEncodingException
    //   45	52	108	finally
    //   89	105	108	finally
  }

  public static String bytesToUtf8(byte[] paramArrayOfByte)
  {
    return bytesToEncoding(paramArrayOfByte, "UTF8");
  }

  public static String capitalize(String paramString)
  {
    String str1;
    if (paramString.length() == 0)
      str1 = paramString;
    while (true)
    {
      return str1;
      char c1 = paramString.charAt(0);
      char c2 = Character.toUpperCase(c1);
      if (c1 == c2)
      {
        str1 = paramString;
        continue;
      }
      String str2 = String.valueOf(c2);
      StringBuilder localStringBuilder = new StringBuilder(str2);
      String str3 = paramString.substring(1);
      str1 = str3;
    }
  }

  public static String collapse(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null);
    StringBuilder localStringBuilder;
    int i;
    int j;
    for (String str = null; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      i = 0;
      j = 0;
      int k = paramString1.length();
      if (j < k)
        break;
    }
    char c = paramString1.charAt(j);
    if (paramString2.indexOf(c) != -1)
      if (i == 0);
    while (true)
    {
      j += 1;
      break;
      i = 1;
      localStringBuilder.append(paramString3);
      continue;
      i = 0;
      localStringBuilder.append(c);
    }
  }

  public static String collapseWhitespace(String paramString)
  {
    return collapse(paramString, " \r\n\t銆��", " ");
  }

  @Deprecated
  @Signature({"(", "Ljava/util/Collection", "<*>;", "Ljava/lang/String;", ")", "Ljava/lang/String;"})
  public static String collection2String(Collection paramCollection, String paramString)
  {
    if (paramCollection == null);
    for (String str = null; ; str = iterator2String(paramCollection.iterator(), paramString))
      return str;
  }

  public static boolean contains(String paramString, char paramChar)
  {
    int i;
    if (isEmpty(paramString))
      i = 0;
    while (true)
    {
      return i;
      if (paramString.indexOf(paramChar) >= 0)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public static boolean contains(String paramString1, String paramString2)
  {
    int i;
    if ((paramString1 == null) || (paramString2 == null))
      i = 0;
    while (true)
    {
      return i;
      if (paramString1.indexOf(paramString2) >= 0)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public static boolean containsCharRef(String paramString)
  {
    return characterReferencePattern.matcher(paramString).find();
  }

  public static boolean containsIgnoreCase(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String str1;
    String str2;
    for (boolean bool = false; ; bool = contains(str1, str2))
    {
      return bool;
      str1 = paramString1.toUpperCase();
      str2 = paramString2.toUpperCase();
    }
  }

  @Deprecated
  public static String convertEOLToCRLF(String paramString)
  {
    return paramString.replaceAll("(\r\n|\r|\n)", "\r\n");
  }

  public static String convertEOLToLF(String paramString)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = 0;
    if (k >= j)
      if (0 != 0)
        break label120;
    for (String str = paramString; ; str = localStringBuilder.toString())
    {
      return str;
      if (arrayOfChar[k] == '\r')
      {
        int m = k - 0;
        localStringBuilder.append(arrayOfChar, 0, m);
        localStringBuilder.append(10);
        if (k + 1 < j)
        {
          int n = k + 1;
          if (arrayOfChar[n] == '\n')
            k += 1;
        }
        int i1 = k + 1;
      }
      k += 1;
      break;
      label120: int i2 = j - 0;
      localStringBuilder.append(arrayOfChar, 0, i2);
    }
  }

  public static void copyStreams(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    if ((paramInputStream == null) || (paramOutputStream == null))
      throw new IllegalArgumentException();
    byte[] arrayOfByte = new byte[4096];
    while (true)
    {
      int i = arrayOfByte.length;
      int j = paramInputStream.read(arrayOfByte, 0, i);
      if (-1 == j)
        return;
      paramOutputStream.write(arrayOfByte, 0, j);
    }
  }

  public static String cropBetween(String paramString, char paramChar)
  {
    char[] arrayOfChar = new char[1];
    arrayOfChar[0] = paramChar;
    String str = String.valueOf(arrayOfChar);
    return cropBetween(paramString, str);
  }

  public static String cropBetween(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString2.length();
    int k = 1;
    int m = paramString1.indexOf(paramString2, i);
    if (m < 0)
    {
      if (k != 0)
      {
        String str1 = paramString1.substring(i);
        localStringBuilder.append(str1);
      }
      return localStringBuilder.toString();
    }
    if (k != 0)
    {
      String str2 = paramString1.substring(i, m);
      localStringBuilder.append(str2);
    }
    if (k != 0);
    for (k = 0; ; k = 1)
    {
      i = m + j;
      break;
    }
  }

  public static int displayWidth(char paramChar)
  {
    if ((paramChar <= 'ӹ') || (paramChar == '־') || ((paramChar >= 'א') && (paramChar <= 'ת')) || (paramChar == '׳') || (paramChar == '״') || ((paramChar >= '฀') && (paramChar <= '๿')) || ((paramChar >= 'Ḁ') && (paramChar <= '₯')) || ((paramChar >= '℀') && (paramChar <= '℺')) || ((paramChar >= 65377) && (paramChar <= 65500)));
    for (int i = 1; ; i = 2)
      return i;
  }

  public static int displayWidth(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    int k = 0;
    while (true)
    {
      if (k >= j)
        return i;
      int m = displayWidth(paramString.charAt(k));
      i += m;
      k += 1;
    }
  }

  public static byte[] encodingToBytes(String paramString1, String paramString2)
  {
    byte[] arrayOfByte;
    if (paramString1 == null)
      arrayOfByte = null;
    String str2;
    while (true)
    {
      return arrayOfByte;
      try
      {
        arrayOfByte = paramString1.getBytes(paramString2);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        String str1 = String.valueOf(paramString2);
        str2 = str1 + " not supported! Original exception: " + localUnsupportedEncodingException;
      }
    }
    throw new Error(str2);
  }

  public static boolean equals(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString1 == paramString2)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramString1 != null) && (paramString2 != null))
      {
        bool = paramString1.equals(paramString2);
        continue;
      }
      bool = false;
    }
  }

  public static boolean equalsIgnoreCase(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString1 == paramString2)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramString1 != null) && (paramString2 != null))
      {
        bool = paramString1.equalsIgnoreCase(paramString2);
        continue;
      }
      bool = false;
    }
  }

  public static String escapeHTML(String paramString)
  {
    StringBuilder localStringBuilder = null;
    int i = 0;
    int j = paramString.length();
    if (i >= j)
    {
      if (0 > 0)
      {
        String str1 = paramString.substring(0);
        localStringBuilder.append(str1);
      }
      if (localStringBuilder == null)
        break label207;
    }
    label207: for (String str2 = localStringBuilder.toString(); ; str2 = paramString)
    {
      return str2;
      String str3;
      switch (paramString.charAt(i))
      {
      default:
        str3 = null;
      case '"':
      case '&':
      case '<':
      case '>':
      }
      while (true)
      {
        if (str3 != null)
        {
          if (localStringBuilder == null)
          {
            int k = paramString.length();
            int m = str3.length();
            int n = k + m - 1;
            localStringBuilder = new StringBuilder(n);
          }
          if (i > 0)
          {
            String str4 = paramString.substring(0, i);
            localStringBuilder.append(str4);
          }
          localStringBuilder.append(str3);
          int i1 = i + 1;
        }
        i += 1;
        break;
        str3 = "&quot;";
        continue;
        str3 = "&amp;";
        continue;
        str3 = "&lt;";
        continue;
        str3 = "&gt;";
      }
    }
  }

  public static String escapeJava(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    default:
      localStringBuilder.append(c);
    case '\n':
    case '\r':
    case '\t':
    case '\\':
    case '"':
    case '&':
    case '<':
    case '>':
    case '\'':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("\\n");
      continue;
      localStringBuilder.append("\\r");
      continue;
      localStringBuilder.append("\\t");
      continue;
      localStringBuilder.append("\\\\");
      continue;
      localStringBuilder.append("\\\"");
      continue;
      localStringBuilder.append("&amp;");
      continue;
      localStringBuilder.append("&lt;");
      continue;
      localStringBuilder.append("&gt;");
      continue;
      localStringBuilder.append("\\'");
    }
  }

  public static String escapeJavaScript(String paramString)
  {
    return javaScriptEscapeHelper(paramString, 0);
  }

  public static String escapeJavaScriptToAscii(String paramString)
  {
    return javaScriptEscapeHelper(paramString, 1);
  }

  public static String escapeJavaUtilRegex(String paramString)
  {
    String str;
    if (paramString.indexOf("\\E") == -1)
    {
      str = "\\Q" + paramString + "\\E";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      int j = paramString.length();
      if (i >= j)
      {
        str = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(92);
      char c = paramString.charAt(i);
      localStringBuilder.append(c);
      i += 1;
    }
  }

  public static String escapeJavaWithinAttribute(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    default:
      localStringBuilder.append(c);
    case '\n':
    case '\r':
    case '\t':
    case '\\':
    case '"':
    case '&':
    case '<':
    case '>':
    case '\'':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("\\n");
      continue;
      localStringBuilder.append("\\r");
      continue;
      localStringBuilder.append("\\t");
      continue;
      localStringBuilder.append("\\\\");
      continue;
      localStringBuilder.append("&quot;");
      continue;
      localStringBuilder.append("&amp;");
      continue;
      localStringBuilder.append("&lt;");
      continue;
      localStringBuilder.append("&gt;");
      continue;
      localStringBuilder.append("\\'");
    }
  }

  public static String escapeRegex(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    if ("()|*+?.{}[]$^\\".indexOf(c) != -1)
    {
      localStringBuilder.append(92);
      localStringBuilder.append(c);
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append(c);
    }
  }

  public static String escapeRegexReplacement(String paramString)
  {
    StringBuilder localStringBuilder = null;
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      if (localStringBuilder != null)
        break label111;
    label111: for (String str1 = paramString; ; str1 = localStringBuilder.toString())
    {
      return str1;
      char c = paramString.charAt(i);
      switch (c)
      {
      default:
      case '$':
      case '\\':
      }
      while (true)
      {
        if (localStringBuilder != null)
          localStringBuilder.append(c);
        i += 1;
        break;
        if (localStringBuilder == null)
        {
          String str2 = paramString.substring(0, i);
          localStringBuilder = new StringBuilder(str2);
        }
        localStringBuilder.append(92);
      }
    }
  }

  public static String escapeSql(String paramString)
  {
    if (paramString == null);
    for (String str = null; ; str = replace(paramString, "'", "''"))
      return str;
  }

  public static String escapeXML(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    default:
      localStringBuilder.append(c);
    case '\000':
    case '"':
    case '&':
    case '\'':
    case '<':
    case '>':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("&quot;");
      continue;
      localStringBuilder.append("&amp;");
      continue;
      localStringBuilder.append("&apos;");
      continue;
      localStringBuilder.append("&lt;");
      continue;
      localStringBuilder.append("&gt;");
    }
  }

  public static String escapeXmlContent(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    case '\t':
    case '\n':
    case '\r':
    case ' ':
    case '!':
    case '"':
    case '#':
    case '$':
    case '%':
    case '\'':
    case '(':
    case ')':
    case '*':
    case '+':
    case ',':
    case '-':
    case '.':
    case '/':
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    case '5':
    case '6':
    case '7':
    case '8':
    case '9':
    case ':':
    case ';':
    default:
      localStringBuilder.append(c);
    case '\000':
    case '\001':
    case '\002':
    case '\003':
    case '\004':
    case '\005':
    case '\006':
    case '\007':
    case '\b':
    case '\013':
    case '\f':
    case '\016':
    case '\017':
    case '\020':
    case '\021':
    case '\022':
    case '\023':
    case '\024':
    case '\025':
    case '\026':
    case '\027':
    case '\030':
    case '\031':
    case '\032':
    case '\033':
    case '\034':
    case '\035':
    case '\036':
    case '\037':
    case '&':
    case '<':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("&amp;");
      continue;
      localStringBuilder.append("&lt;");
    }
  }

  public static String escapeXmlSingleQuoted(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    case '\t':
    case '\r':
    case ' ':
    case '!':
    case '"':
    case '#':
    case '$':
    case '%':
    case '(':
    case ')':
    case '*':
    case '+':
    case ',':
    case '-':
    case '.':
    case '/':
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    case '5':
    case '6':
    case '7':
    case '8':
    case '9':
    case ':':
    case ';':
    default:
      localStringBuilder.append(c);
    case '\000':
    case '\001':
    case '\002':
    case '\003':
    case '\004':
    case '\005':
    case '\006':
    case '\007':
    case '\b':
    case '\013':
    case '\f':
    case '\016':
    case '\017':
    case '\020':
    case '\021':
    case '\022':
    case '\023':
    case '\024':
    case '\025':
    case '\026':
    case '\027':
    case '\030':
    case '\031':
    case '\032':
    case '\033':
    case '\034':
    case '\035':
    case '\036':
    case '\037':
    case '\'':
    case '&':
    case '<':
    case '\n':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("&quot;");
      continue;
      localStringBuilder.append("&amp;");
      continue;
      localStringBuilder.append("&lt;");
      continue;
      localStringBuilder.append("&#xA;");
    }
  }

  public static String expandShardNames(String paramString)
    throws IllegalArgumentException, IllegalStateException
  {
    Matcher localMatcher = dbSpecPattern.matcher(paramString);
    String str2;
    int i4;
    String str3;
    StringBuilder localStringBuilder;
    int i7;
    if (localMatcher.find())
    {
      int i = null;
      int i1;
      try
      {
        int j = localMatcher.start(i);
        int k = localMatcher.end(1);
        str2 = paramString.substring(j, k);
        int m = localMatcher.start(2);
        int n = localMatcher.end(2);
        i1 = Integer.parseInt(paramString.substring(m, n));
        int i2 = localMatcher.start(3);
        int i3 = localMatcher.end(3);
        i4 = Integer.parseInt(paramString.substring(i2, i3));
        int i5 = localMatcher.start(4);
        int i6 = localMatcher.end(4);
        str3 = paramString.substring(i5, i6);
        if (i1 > i4)
          throw new IllegalArgumentException("Maximum shard must be greater than or equal to the minimum shard");
      }
      catch (NumberFormatException localNumberFormatException)
      {
        String str4 = "Malformed DB specification component: " + paramString;
        throw new IllegalArgumentException(str4);
      }
      localStringBuilder = new StringBuilder();
      i7 = i1;
      if (i7 <= i4);
    }
    for (String str1 = localStringBuilder.toString(); ; str1 = paramString)
    {
      return str1;
      localStringBuilder.append(str2).append(i7).append(str3);
      if (i7 != i4)
        localStringBuilder.append(",");
      i7 += 1;
      break;
    }
  }

  public static String fixedWidth(String paramString, int paramInt)
  {
    return fixedWidth(split(paramString, "\n"), paramInt);
  }

  public static String fixedWidth(String[] paramArrayOfString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramArrayOfString.length;
    if (i >= j)
      return localStringBuilder.toString();
    int k = 0;
    if (i != 0)
      localStringBuilder.append("\n");
    if (paramArrayOfString[i].length() <= paramInt)
    {
      String str1 = paramArrayOfString[i];
      localStringBuilder.append(str1);
    }
    String[] arrayOfString;
    int m;
    int n;
    do
    {
      i += 1;
      break;
      arrayOfString = splitAndTrim(paramArrayOfString[i], " \r\n\t銆��");
      m = 0;
      n = arrayOfString.length;
    }
    while (m >= n);
    if ((k == 0) || (arrayOfString[m].length() + k < paramInt))
    {
      if (k != 0)
      {
        localStringBuilder.append(" ");
        k += 1;
      }
      int i1 = arrayOfString[m].length();
      k += i1;
      String str2 = arrayOfString[m];
      localStringBuilder.append(str2);
    }
    while (true)
    {
      m += 1;
      break;
      localStringBuilder.append("\n");
      k = arrayOfString[m].length();
      String str3 = arrayOfString[m];
      localStringBuilder.append(str3);
    }
  }

  public static byte[] hexToBytes(String paramString)
  {
    byte[] arrayOfByte = new byte[(paramString.length() + 1) / 2];
    if (paramString.length() == 0);
    int i;
    int j;
    int k;
    do
    {
      return arrayOfByte;
      arrayOfByte[0] = 0;
      i = paramString.length() % 2;
      j = 0;
      k = paramString.length();
    }
    while (j >= k);
    char c = paramString.charAt(j);
    if (!isHex(c))
      throw new IllegalArgumentException("string contains non-hex chars");
    if (i % 2 == 0)
    {
      int m = i >> 1;
      int n = (byte)(hexValue(c) << 4);
      arrayOfByte[m] = n;
    }
    while (true)
    {
      i += 1;
      j += 1;
      break;
      int i1 = i >> 1;
      int i2 = arrayOfByte[i1];
      int i3 = (byte)hexValue(c);
      int i4 = (byte)(i2 + i3);
      arrayOfByte[i1] = i4;
    }
  }

  private static int hexValue(char paramChar)
  {
    int i;
    if ((paramChar >= '0') && (paramChar <= '9'))
      i = paramChar - '0';
    while (true)
    {
      return i;
      if ((paramChar >= 'a') && (paramChar <= 'f'))
      {
        i = paramChar - 'a' + 10;
        continue;
      }
      i = paramChar - 'A' + 10;
    }
  }

  public static String indent(String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n");
    int i = 0;
    while (true)
    {
      if (i >= paramInt)
      {
        String str = localStringBuilder.toString();
        return replace(paramString, "\n", str);
      }
      localStringBuilder.append("  ");
      i += 1;
    }
  }

  public static int indexOfChars(String paramString1, String paramString2)
  {
    return indexOfChars(paramString1, paramString2, 0);
  }

  public static int indexOfChars(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString1.length();
    int j = paramInt;
    while (true)
    {
      if (j >= i);
      for (int k = -1; ; k = j)
      {
        return k;
        int m = paramString1.charAt(j);
        if (paramString2.indexOf(m) < 0)
          break;
      }
      j += 1;
    }
  }

  public static String insertBreakingWhitespace(int paramInt, String paramString)
  {
    if ((paramString == null) || (paramInt <= 0))
      throw new IllegalArgumentException();
    int i = paramString.length();
    String str1;
    if (i <= paramInt)
    {
      str1 = paramString;
      return str1;
    }
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      if (i - j <= paramInt)
      {
        String str2 = paramString.substring(j, i);
        localStringBuilder.append(str2);
        str1 = localStringBuilder.toString();
        break;
      }
      int k = j + paramInt;
      String str3 = paramString.substring(j, k);
      localStringBuilder.append(str3);
      j += paramInt;
      localStringBuilder.append(" ");
    }
  }

  public static boolean isCjk(char paramChar)
  {
    return isCjk(paramChar);
  }

  public static boolean isCjk(int paramInt)
  {
    if ((paramInt & 0xFFFFFF00) == 0);
    Set localSet;
    Character.UnicodeBlock localUnicodeBlock;
    boolean bool;
    for (int i = 0; ; bool = localSet.contains(localUnicodeBlock))
    {
      return i;
      localSet = CJK_BLOCKS;
      localUnicodeBlock = Character.UnicodeBlock.of(paramInt);
    }
  }

  public static boolean isCjk(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    while (true)
    {
      if (j >= i);
      for (int k = 0; ; k = 1)
      {
        return k;
        if (!isCjk(paramString.codePointAt(j)))
          break;
      }
      j += 1;
    }
  }

  public static boolean isEmpty(String paramString)
  {
    if (makeSafe(paramString).length() == 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isEmptyOrWhitespace(String paramString)
  {
    paramString = makeSafe(paramString);
    int i = 0;
    int j = paramString.length();
    while (true)
    {
      if (i >= j);
      for (int k = 1; ; k = 0)
      {
        return k;
        if (Character.isWhitespace(paramString.charAt(i)))
          break;
      }
      i += 1;
    }
  }

  public static boolean isHebrew(int paramInt)
  {
    Character.UnicodeBlock localUnicodeBlock1 = Character.UnicodeBlock.HEBREW;
    Character.UnicodeBlock localUnicodeBlock2 = Character.UnicodeBlock.of(paramInt);
    return localUnicodeBlock1.equals(localUnicodeBlock2);
  }

  public static boolean isHebrew(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    while (true)
    {
      if (j >= i);
      for (int k = 0; ; k = 1)
      {
        return k;
        if (!isHebrew(paramString.codePointAt(j)))
          break;
      }
      j += 1;
    }
  }

  private static boolean isHex(char paramChar)
  {
    if (((paramChar < '0') || (paramChar > '9')) && ((paramChar < 'a') || (paramChar > 'f')) && ((paramChar < 'A') || (paramChar > 'F')));
    for (int i = 0; ; i = 1)
      return i;
  }

  public static boolean isNotEmpty(String paramString)
  {
    if (isEmpty(paramString));
    for (int i = 0; ; i = 1)
      return i;
  }

  private static boolean isOctal(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '7'));
    for (int i = 1; ; i = 0)
      return i;
  }

  @Deprecated
  @Signature({"(", "Ljava/util/Iterator", "<*>;", "Ljava/lang/String;", ")", "Ljava/lang/String;"})
  public static String iterator2String(Iterator paramIterator, String paramString)
  {
    String str1;
    if (paramIterator == null)
    {
      str1 = null;
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      if (!paramIterator.hasNext())
      {
        str1 = localStringBuilder.toString();
        break;
      }
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(paramString);
      String str2 = paramIterator.next().toString();
      localStringBuilder.append(str2);
    }
  }

  private static String javaScriptEscapeHelper(String paramString, boolean paramBoolean)
  {
    int i = paramString.length() * 9 / 8;
    StringBuilder localStringBuilder = new StringBuilder(i);
    int j = 0;
    int k = paramString.length();
    if (j >= k)
      return localStringBuilder.toString();
    int m = paramString.charAt(j);
    switch (m)
    {
    default:
      if ((m < 128) || (!paramBoolean))
        break;
      appendHexJavaScriptRepresentation(localStringBuilder, m);
    case 10:
    case 13:
    case 9:
    case 92:
    case 34:
    case 39:
    case 61:
    case 47:
    case 60:
    case 62:
    case 8232:
    case 8233:
    }
    while (true)
    {
      j += 1;
      break;
      localStringBuilder.append("\\n");
      continue;
      localStringBuilder.append("\\r");
      continue;
      localStringBuilder.append("\\t");
      continue;
      localStringBuilder.append("\\\\");
      continue;
      localStringBuilder.append("\\\"");
      continue;
      localStringBuilder.append("\\'");
      continue;
      appendHexJavaScriptRepresentation(localStringBuilder, m);
      continue;
      int n = 0;
      String[] arrayOfString = UNSAFE_TAGS;
      int i1 = arrayOfString.length;
      int i2 = 0;
      while (true)
      {
        if (i2 >= i1);
        while (true)
        {
          if (n == 0)
            break label346;
          appendHexJavaScriptRepresentation(localStringBuilder, m);
          break;
          String str = arrayOfString[i2];
          int i3 = j + 1;
          int i4 = str.length();
          if (!paramString.regionMatches(1, i3, str, 0, i4))
            break label337;
          n = 1;
        }
        label337: i2 += 1;
      }
      label346: localStringBuilder.append(m);
      continue;
      if (localStringBuilder.length() > 0)
      {
        int i5 = localStringBuilder.length() - 1;
        if (localStringBuilder.charAt(i5) == 45)
          localStringBuilder.append(92);
      }
      localStringBuilder.append(m);
      continue;
      localStringBuilder.append("\\u2028");
      continue;
      localStringBuilder.append("\\u2029");
      continue;
      localStringBuilder.append(m);
    }
  }

  // ERROR //
  private static int javaScriptUnescapeHelper(String paramString, int paramInt, StringBuilder paramStringBuilder)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 958	java/lang/String:length	()I
    //   4: istore_3
    //   5: iload_1
    //   6: iload_3
    //   7: if_icmplt +38 -> 45
    //   10: new 968	java/lang/StringBuilder
    //   13: dup
    //   14: ldc_w 1392
    //   17: invokespecial 969	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: aload_0
    //   21: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: ldc_w 1394
    //   27: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 979	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: astore 4
    //   35: new 949	java/lang/IllegalArgumentException
    //   38: dup
    //   39: aload 4
    //   41: invokespecial 954	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   44: athrow
    //   45: iload_1
    //   46: iconst_1
    //   47: iadd
    //   48: istore 5
    //   50: aload_0
    //   51: iload_1
    //   52: invokevirtual 992	java/lang/String:charAt	(I)C
    //   55: istore 6
    //   57: iload 6
    //   59: lookupswitch	default:+73->132, 34:+171->230, 39:+171->230, 62:+171->230, 92:+171->230, 110:+130->189, 114:+143->202, 116:+157->216, 117:+184->243
    //   133: iconst_0
    //   134: goto_w 89 19 5 116
    //   139: invokespecial 969	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   142: iload 6
    //   144: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   147: ldc_w 1398
    //   150: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: iload 5
    //   155: invokevirtual 1306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   158: ldc_w 1400
    //   161: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload_0
    //   165: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: ldc_w 1394
    //   171: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 979	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: astore 7
    //   179: new 949	java/lang/IllegalArgumentException
    //   182: dup
    //   183: aload 7
    //   185: invokespecial 954	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   188: athrow
    //   189: aload_2
    //   190: ldc_w 1197
    //   193: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   196: pop
    //   197: iload 5
    //   199: istore_1
    //   200: iload_1
    //   201: ireturn
    //   202: aload_2
    //   203: ldc_w 1401
    //   206: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: iload 5
    //   212: istore_1
    //   213: goto -13 -> 200
    //   216: aload_2
    //   217: ldc_w 1402
    //   220: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: iload 5
    //   226: istore_1
    //   227: goto -27 -> 200
    //   230: aload_2
    //   231: iload 6
    //   233: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   236: pop
    //   237: iload 5
    //   239: istore_1
    //   240: goto -40 -> 200
    //   243: iload 5
    //   245: iconst_4
    //   246: iadd
    //   247: istore 8
    //   249: aload_0
    //   250: iload 5
    //   252: iload 8
    //   254: invokevirtual 962	java/lang/String:substring	(II)Ljava/lang/String;
    //   257: astore 9
    //   259: bipush 16
    //   261: istore 8
    //   263: aload 9
    //   265: iload 8
    //   267: invokestatic 1404	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   270: istore 10
    //   272: iload 10
    //   274: i2c
    //   275: istore 11
    //   277: aload_2
    //   278: iload 11
    //   280: invokevirtual 1006	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   283: pop
    //   284: iload 5
    //   286: iconst_4
    //   287: iadd
    //   288: istore_1
    //   289: goto -89 -> 200
    //   292: astore 12
    //   294: new 968	java/lang/StringBuilder
    //   297: dup
    //   298: ldc_w 1406
    //   301: invokespecial 969	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   304: astore 13
    //   306: aload_0
    //   307: iload 5
    //   309: invokevirtual 984	java/lang/String:substring	(I)Ljava/lang/String;
    //   312: astore 14
    //   314: aload 13
    //   316: aload 14
    //   318: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: ldc_w 1398
    //   324: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: iload 5
    //   329: invokevirtual 1306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   332: ldc_w 1400
    //   335: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: aload_0
    //   339: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: ldc_w 1394
    //   345: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 979	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: astore 15
    //   353: new 949	java/lang/IllegalArgumentException
    //   356: dup
    //   357: aload 15
    //   359: invokespecial 954	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   362: athrow
    //   363: astore 16
    //   365: new 968	java/lang/StringBuilder
    //   368: dup
    //   369: ldc_w 1406
    //   372: invokespecial 969	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   375: aload 9
    //   377: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: ldc_w 1398
    //   383: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: iload 5
    //   388: invokevirtual 1306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   391: ldc_w 1400
    //   394: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: aload_0
    //   398: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: ldc_w 1394
    //   404: invokevirtual 975	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: invokevirtual 979	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   410: astore 17
    //   412: new 949	java/lang/IllegalArgumentException
    //   415: dup
    //   416: aload 17
    //   418: invokespecial 954	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   421: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   249	259	292	java/lang/IndexOutOfBoundsException
    //   263	272	363	java/lang/NumberFormatException
  }

  @Deprecated
  public static String join(Collection paramCollection, String paramString)
  {
    return join(paramCollection.toArray(), paramString);
  }

  @Deprecated
  public static String join(Object[] paramArrayOfObject, String paramString)
  {
    String str1;
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0))
    {
      str1 = "";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      int j = paramArrayOfObject.length;
      if (i >= j)
      {
        str1 = localStringBuilder.toString();
        break;
      }
      if ((i > 0) && (paramString != null))
        localStringBuilder.append(paramString);
      if (paramArrayOfObject[i] != 0)
      {
        String str2 = paramArrayOfObject[i].toString();
        localStringBuilder.append(str2);
      }
      i += 1;
    }
  }

  public static String joinInts(int[] paramArrayOfInt, String paramString)
  {
    String str1;
    if (paramArrayOfInt == null)
    {
      str1 = "";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      int j = paramArrayOfInt.length;
      if (i >= j)
      {
        str1 = localStringBuilder.toString();
        break;
      }
      if ((i > 0) && (paramString != null))
        localStringBuilder.append(paramString);
      String str2 = String.valueOf(paramArrayOfInt[i]);
      localStringBuilder.append(str2);
      i += 1;
    }
  }

  public static String joinLongs(long[] paramArrayOfLong, String paramString)
  {
    String str1;
    if (paramArrayOfLong == null)
    {
      str1 = "";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (true)
    {
      int j = paramArrayOfLong.length;
      if (i >= j)
      {
        str1 = localStringBuilder.toString();
        break;
      }
      if ((i > 0) && (paramString != null))
        localStringBuilder.append(paramString);
      String str2 = String.valueOf(paramArrayOfLong[i]);
      localStringBuilder.append(str2);
      i += 1;
    }
  }

  public static int lastIndexNotOf(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString1.length() - 1;
    int j = Math.min(paramInt, i);
    while (true)
    {
      if (j < 0);
      for (int k = -1; ; k = j)
      {
        return k;
        int m = paramString1.charAt(j);
        if (paramString2.indexOf(m) >= 0)
          break;
      }
      j += -1;
    }
  }

  public static String lastToken(String paramString1, String paramString2)
  {
    String[] arrayOfString = split(paramString1, paramString2);
    if (arrayOfString.length == 0);
    int i;
    for (String str = ""; ; str = arrayOfString[i])
    {
      return str;
      i = arrayOfString.length - 1;
    }
  }

  public static byte[] latin1ToBytes(String paramString)
  {
    return encodingToBytes(paramString, "ISO-8859-1");
  }

  @Deprecated
  @Signature({"(", "Ljava/util/Collection", "<*>;", "Ljava/lang/String;", ")", "Ljava/lang/String;"})
  public static String list2String(Collection paramCollection, String paramString)
  {
    return collection2String(paramCollection, paramString);
  }

  @Signature({"<V:", "Ljava/lang/Object;", ">(", "Ljava/util/Map", "<", "Ljava/lang/String;", "TV;>;)", "Ljava/util/Map;"})
  public static Map lowercaseKeys(Map paramMap)
  {
    int i = paramMap.size();
    HashMap localHashMap = new HashMap(i);
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localHashMap;
      String str1 = (String)localIterator.next();
      String str2 = str1.toLowerCase();
      if (localHashMap.containsKey(str2))
        throw new IllegalArgumentException("Duplicate string key in map when lower casing");
      String str3 = str1.toLowerCase();
      Object localObject = paramMap.get(str1);
      localHashMap.put(str3, localObject);
    }
  }

  public static String lstrip(String paramString)
  {
    return megastrip(paramString, 1, 0, " \r\n\t銆��");
  }

  public static String makeSafe(String paramString)
  {
    if (paramString == null);
    for (String str = ""; ; str = paramString)
      return str;
  }

  @Signature({"<K:", "Ljava/lang/Object;", "V:", "Ljava/lang/Object;", ">(", "Ljava/util/Map", "<TK;TV;>;", "Ljava/lang/String;", "Ljava/lang/String;", ")", "Ljava/lang/String;"})
  public static String map2String(Map paramMap, String paramString1, String paramString2)
  {
    String str1;
    if (paramMap == null)
    {
      str1 = null;
      return str1;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        str1 = localStringBuilder1.toString();
        break;
      }
      if (localStringBuilder1.length() > 0)
        localStringBuilder1.append(paramString2);
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      StringBuilder localStringBuilder2 = new StringBuilder();
      Object localObject1 = localEntry.getKey();
      StringBuilder localStringBuilder3 = localStringBuilder2.append(localObject1).append(paramString1);
      Object localObject2 = localEntry.getValue();
      String str2 = localObject2;
      localStringBuilder1.append(str2);
    }
  }

  public static String maskLeft(String paramString, int paramInt, char paramChar)
  {
    String str1;
    if (paramInt <= 0)
    {
      str1 = paramString;
      return str1;
    }
    int i = paramString.length();
    paramInt = Math.min(paramInt, i);
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 0;
    while (true)
    {
      if (j >= paramInt)
      {
        String str2 = paramString.substring(paramInt);
        localStringBuilder.append(str2);
        str1 = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(paramChar);
      j += 1;
    }
  }

  public static String maskRight(String paramString, int paramInt, char paramChar)
  {
    String str1;
    if (paramInt <= 0)
    {
      str1 = paramString;
      return str1;
    }
    int i = paramString.length();
    paramInt = Math.min(paramInt, i);
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length() - paramInt;
    String str2 = paramString.substring(0, j);
    localStringBuilder.append(str2);
    int k = 0;
    while (true)
    {
      if (k >= paramInt)
      {
        str1 = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(paramChar);
      k += 1;
    }
  }

  public static String megastrip(String paramString1, boolean paramBoolean1, boolean paramBoolean2, String paramString2)
  {
    String str;
    if (paramString1 == null)
    {
      str = null;
      return str;
    }
    int i = 0;
    int j = paramString1.length() - 1;
    label21: if ((paramBoolean1) && (i <= j))
    {
      int k = paramString1.charAt(i);
      if (paramString2.indexOf(k) >= 0)
        break label96;
    }
    while (true)
    {
      if ((paramBoolean2) && (j >= i))
      {
        int m = paramString1.charAt(j);
        if (paramString2.indexOf(m) >= 0);
      }
      else
      {
        int n = j + 1;
        str = paramString1.substring(i, n);
        break;
        label96: i += 1;
        break label21;
      }
      j += -1;
    }
  }

  public static int numSharedChars(String paramString1, String paramString2)
  {
    int i;
    if ((paramString1 == null) || (paramString2 == null))
    {
      i = 0;
      return i;
    }
    int j = 0;
    int k = -1;
    while (true)
    {
      int m = k + 1;
      k = indexOfChars(paramString1, paramString2, m);
      if (k == -1)
      {
        i = j;
        break;
      }
      j += 1;
    }
  }

  public static String padLeft(String paramString, int paramInt, char paramChar)
  {
    String str;
    if (paramString.length() >= paramInt)
    {
      str = paramString;
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.length();
    int j = paramInt - i;
    int k = 0;
    while (true)
    {
      if (k >= j)
      {
        localStringBuilder.append(paramString);
        str = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(paramChar);
      k += 1;
    }
  }

  public static String padRight(String paramString, int paramInt, char paramChar)
  {
    String str;
    if (paramString.length() >= paramInt)
    {
      str = paramString;
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramString.length();
    int j = paramInt - i;
    localStringBuilder.append(paramString);
    int k = 0;
    while (true)
    {
      if (k >= j)
      {
        str = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(paramChar);
      k += 1;
    }
  }

  public static String[] parseDelimitedList(String paramString, char paramChar)
  {
    String str1 = paramChar;
    String str2 = String.valueOf(paramString);
    String str3 = str2 + str1 + " ";
    StringTokenizer localStringTokenizer = new StringTokenizer(str3, str1, 1);
    ArrayList localArrayList = new ArrayList();
    Object localObject = "";
    if (!localStringTokenizer.hasMoreTokens())
    {
      String[] arrayOfString = new String[0];
      return (String[])localArrayList.toArray(arrayOfString);
    }
    String str4 = localStringTokenizer.nextToken();
    if (localObject != null)
    {
      if (!str4.equals(str1))
        break label161;
      String str5 = String.valueOf("");
      String str6 = str5 + (String)localObject;
      if (((String)localObject).equals(str1))
        str4 = null;
    }
    while (true)
    {
      localObject = str4;
      break;
      label161: if (!"".equals(""))
        localArrayList.add("");
    }
  }

  public static String pythonEscape(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramString.length();
    if (i >= j)
      return localStringBuilder.toString();
    char c = paramString.charAt(i);
    switch (c)
    {
    default:
      localStringBuilder.append(c);
    case '\n':
    case '\r':
    case '\t':
    case '\\':
    case '"':
    case '\'':
    }
    while (true)
    {
      i += 1;
      break;
      localStringBuilder.append("\\n");
      continue;
      localStringBuilder.append("\\r");
      continue;
      localStringBuilder.append("\\t");
      continue;
      localStringBuilder.append("\\\\");
      continue;
      localStringBuilder.append("\\\"");
      continue;
      localStringBuilder.append("\\'");
    }
  }

  public static String removeChars(String paramString1, String paramString2)
  {
    int i = indexOfChars(paramString1, paramString2);
    if (i == -1);
    StringBuilder localStringBuilder;
    for (String str1 = paramString1; ; str1 = localStringBuilder.toString())
    {
      return str1;
      localStringBuilder = new StringBuilder();
      int j = 0;
      do
      {
        String str2 = paramString1.substring(j, i);
        localStringBuilder.append(str2);
        j = i + 1;
        i = indexOfChars(paramString1, paramString2, j);
      }
      while (i != -1);
      int k = paramString1.length();
      if (j >= k)
        continue;
      String str3 = paramString1.substring(j);
      localStringBuilder.append(str3);
    }
  }

  public static List removeEmpty(List paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Object localObject = localIterator.next();
      if (localObject == null)
        continue;
      localArrayList.add(localObject);
    }
  }

  public static String repeat(String paramString, int paramInt)
  {
    if (paramInt < 1);
    for (String str = ""; ; str = paramString)
    {
      return str;
      if (paramInt != 1)
        break;
    }
    int i = paramString.length() * paramInt;
    StringBuilder localStringBuilder = new StringBuilder(i);
    while (true)
    {
      if (paramInt <= 0)
      {
        str = localStringBuilder.toString();
        break;
      }
      localStringBuilder.append(paramString);
      paramInt += -1;
    }
  }

  @Deprecated
  public static String replace(String paramString1, String paramString2, String paramString3)
  {
    assert (paramString2.length() > 0);
    return paramString1.replace(paramString2, paramString3);
  }

  public static String replaceChars(String paramString1, String paramString2, char paramChar)
  {
    int i = indexOfChars(paramString1, paramString2);
    if (i == -1);
    StringBuilder localStringBuilder;
    for (String str = paramString1; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder(paramString1);
      do
      {
        localStringBuilder.setCharAt(i, paramChar);
        int j = i + 1;
        i = indexOfChars(paramString1, paramString2, j);
      }
      while (i != -1);
    }
  }

  public static String replaceSmartQuotes(String paramString)
  {
    return replaceChars(replaceChars(paramString, "聭聮", 39), "聯聰", 34);
  }

  public static String retainAllChars(String paramString1, String paramString2)
  {
    int i = indexOfChars(paramString1, paramString2);
    if (i == -1);
    StringBuilder localStringBuilder;
    for (String str = ""; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      do
      {
        char c = paramString1.charAt(i);
        localStringBuilder.append(c);
        int j = i + 1;
        i = indexOfChars(paramString1, paramString2, j);
      }
      while (i != -1);
    }
  }

  public static String reverse(String paramString)
  {
    if (paramString == null);
    for (String str = null; ; str = new StringBuffer(paramString).reverse().toString())
      return str;
  }

  public static String rstrip(String paramString)
  {
    return megastrip(paramString, 0, 1, " \r\n\t銆��");
  }

  public static String set2String(Set paramSet, String paramString)
  {
    if (paramSet == null);
    for (String str = null; ; str = iterator2String(paramSet.iterator(), paramString))
      return str;
  }

  public static String[] split(String paramString1, String paramString2)
  {
    return split(paramString1, paramString2, 0);
  }

  public static String[] split(String paramString1, String paramString2, boolean paramBoolean)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
    int i = localStringTokenizer.countTokens();
    String[] arrayOfString = new String[i];
    int j = 0;
    if (j >= i)
      return arrayOfString;
    if (paramBoolean)
    {
      String str1 = localStringTokenizer.nextToken().trim();
      arrayOfString[j] = str1;
    }
    while (true)
    {
      j += 1;
      break;
      String str2 = localStringTokenizer.nextToken();
      arrayOfString[j] = str2;
    }
  }

  public static String[] splitAndTrim(String paramString1, String paramString2)
  {
    return split(paramString1, paramString2, 1);
  }

  public static int[] splitInts(String paramString)
    throws IllegalArgumentException
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    int i = localStringTokenizer.countTokens();
    int[] arrayOfInt = new int[i];
    int j = 0;
    while (true)
    {
      if (j >= i)
        return arrayOfInt;
      int k = Integer.parseInt(localStringTokenizer.nextToken());
      arrayOfInt[j] = k;
      j += 1;
    }
  }

  public static long[] splitLongs(String paramString)
    throws IllegalArgumentException
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    int i = localStringTokenizer.countTokens();
    int j = i;
    int k = 0;
    while (true)
    {
      if (k >= i)
        return j;
      long l = Long.parseLong(localStringTokenizer.nextToken());
      j[k] = l;
      k += 1;
    }
  }

  public static String stream2String(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4096];
    StringWriter localStringWriter = new StringWriter();
    int i = 0;
    int j = 0;
    do
    {
      String str = new String(arrayOfByte, 0, j);
      localStringWriter.write(str);
      i += j;
      int k = arrayOfByte.length;
      j = paramInputStream.read(arrayOfByte, 0, k);
    }
    while (((-1 == paramInt) || (i < paramInt)) && (j != -1));
    return localStringWriter.toString();
  }

  @Signature({"(", "Ljava/lang/String;", "Ljava/lang/String;", "Z", "Ljava/util/Collection", "<", "Ljava/lang/String;", ">;)", "Ljava/util/Collection", "<", "Ljava/lang/String;", ">;"})
  public static Collection string2Collection(String paramString1, String paramString2, boolean paramBoolean, Collection paramCollection)
  {
    if (paramString1 == null);
    for (Collection localCollection = null; ; localCollection = paramCollection)
    {
      return localCollection;
      if (paramCollection == null)
        paramCollection = new ArrayList();
      if ((paramString2 != null) && (paramString2.length() != 0))
        break;
      paramCollection.add(paramString1);
    }
    int j;
    int k;
    for (int i = 0; ; i = j + k)
    {
      j = paramString1.indexOf(paramString2, i);
      if (j < 0)
      {
        str = paramString1.substring(i);
        if (paramBoolean)
          str = strip(str);
        if ((!paramBoolean) || (str.length() > 0))
          paramCollection.add(str);
        localCollection = paramCollection;
        break;
      }
      String str = paramString1.substring(i, j);
      if (paramBoolean)
        str = strip(str);
      if ((!paramBoolean) || (str.length() > 0))
        paramCollection.add(str);
      k = paramString2.length();
    }
  }

  @Signature({"(", "Ljava/lang/String;", "Ljava/lang/String;", "Z)", "Ljava/util/LinkedList", "<", "Ljava/lang/String;", ">;"})
  public static LinkedList string2List(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null);
    LinkedList localLinkedList;
    for (Object localObject = null; ; localObject = localLinkedList)
    {
      return localObject;
      localLinkedList = new LinkedList();
      string2Collection(paramString1, paramString2, paramBoolean, localLinkedList);
    }
  }

  @Signature({"(", "Ljava/lang/String;", "Ljava/lang/String;", "Ljava/lang/String;", "Z)", "Ljava/util/HashMap", "<", "Ljava/lang/String;", "Ljava/lang/String;", ">;"})
  public static HashMap string2Map(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (paramString1 == null);
    HashMap localHashMap;
    for (Object localObject = null; ; localObject = localHashMap)
    {
      return localObject;
      localHashMap = new HashMap();
      if ((!isEmpty(paramString2)) && (!isEmpty(paramString3)))
        break;
      String str1 = strip(paramString1);
      localHashMap.put(str1, "");
    }
    Iterator localIterator = string2List(paramString1, paramString2, 0).iterator();
    int i = paramString3.length();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localObject = localHashMap;
        break;
      }
      String str2 = (String)localIterator.next();
      int j = str2.indexOf(paramString3);
      if (j > 0)
      {
        int k = j + i;
        String str3 = str2.substring(k);
        if (paramBoolean)
          str3 = strip(str3);
        String str4 = strip(str2.substring(0, j));
        localHashMap.put(str4, str3);
        continue;
      }
      String str5 = strip(str2);
      localHashMap.put(str5, "");
    }
  }

  public static Set string2Set(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null);
    HashSet localHashSet;
    for (Object localObject = null; ; localObject = localHashSet)
    {
      return localObject;
      localHashSet = new HashSet();
      string2Collection(paramString1, paramString2, paramBoolean, localHashSet);
    }
  }

  public static String strip(String paramString)
  {
    return megastrip(paramString, 1, 1, " \r\n\t銆��");
  }

  public static String stripAndCollapse(String paramString)
  {
    return collapseWhitespace(strip(paramString));
  }

  public static String stripHtmlTags(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString)));
    for (String str = paramString; ; str = htmlTagPattern.matcher(paramString).replaceAll(""))
      return str;
  }

  public static String stripNonDigits(String paramString)
  {
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    char[] arrayOfChar = paramString.toCharArray();
    int j = arrayOfChar.length;
    int k = 0;
    while (true)
    {
      if (k >= j)
        return localStringBuffer.toString();
      char c = arrayOfChar[k];
      if (Character.isDigit(c))
        localStringBuffer.append(c);
      k += 1;
    }
  }

  public static String stripPrefix(String paramString1, String paramString2)
  {
    int i;
    if (paramString1.startsWith(paramString2))
      i = paramString2.length();
    for (String str = paramString1.substring(i); ; str = null)
      return str;
  }

  public static String stripPrefixIgnoreCase(String paramString1, String paramString2)
  {
    int i = paramString1.length();
    int j = paramString2.length();
    int m;
    if (i >= j)
    {
      int k = paramString2.length();
      if (paramString1.substring(0, k).equalsIgnoreCase(paramString2))
        m = paramString2.length();
    }
    for (String str = paramString1.substring(m); ; str = null)
      return str;
  }

  public static String toNullIfEmpty(String paramString)
  {
    if (isEmpty(paramString));
    for (String str = null; ; str = paramString)
      return str;
  }

  public static String toNullIfEmptyOrWhitespace(String paramString)
  {
    if (isEmptyOrWhitespace(paramString));
    for (String str = null; ; str = paramString)
      return str;
  }

  public static String toString(String paramString)
  {
    if (paramString == null);
    int i;
    for (String str = "NULL"; ; str = i + "'" + paramString + "'")
    {
      return str;
      i = paramString.length() + 2;
    }
  }

  public static String toString(float[] paramArrayOfFloat)
  {
    String str;
    if (paramArrayOfFloat == null)
    {
      str = "NULL";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    while (true)
    {
      int j = paramArrayOfFloat.length;
      if (i >= j)
      {
        localStringBuilder.append("]");
        str = localStringBuilder.toString();
        break;
      }
      int k = paramArrayOfFloat[i];
      localStringBuilder.append(k);
      int m = paramArrayOfFloat.length - 1;
      if (i != m)
        localStringBuilder.append(", ");
      i += 1;
    }
  }

  public static String toString(int[] paramArrayOfInt)
  {
    String str;
    if (paramArrayOfInt == null)
    {
      str = "NULL";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    while (true)
    {
      int j = paramArrayOfInt.length;
      if (i >= j)
      {
        localStringBuilder.append("]");
        str = localStringBuilder.toString();
        break;
      }
      int k = paramArrayOfInt[i];
      localStringBuilder.append(k);
      int m = paramArrayOfInt.length - 1;
      if (i != m)
        localStringBuilder.append(", ");
      i += 1;
    }
  }

  public static String toString(long[] paramArrayOfLong)
  {
    String str;
    if (paramArrayOfLong == null)
    {
      str = "NULL";
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    while (true)
    {
      int j = paramArrayOfLong.length;
      if (i >= j)
      {
        localStringBuilder.append("]");
        str = localStringBuilder.toString();
        break;
      }
      long l = paramArrayOfLong[i];
      localStringBuilder.append(l);
      int k = paramArrayOfLong.length - 1;
      if (i != k)
        localStringBuilder.append(", ");
      i += 1;
    }
  }

  public static String toString(Object[] paramArrayOfObject)
  {
    String str1;
    if (paramArrayOfObject == null)
    {
      str1 = "NULL";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    int i = 0;
    while (true)
    {
      int j = paramArrayOfObject.length;
      if (i >= j)
      {
        localStringBuilder.append("]");
        str1 = localStringBuilder.toString();
        break;
      }
      String str2 = paramArrayOfObject[i].toString();
      localStringBuilder.append(str2);
      int k = paramArrayOfObject.length - 1;
      if (i != k)
        localStringBuilder.append(",");
      i += 1;
    }
  }

  public static String toString(String[] paramArrayOfString)
  {
    String str1;
    if (paramArrayOfString == null)
    {
      str1 = "NULL";
      return str1;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("[");
    int i = 0;
    while (true)
    {
      int j = paramArrayOfString.length;
      if (i >= j)
      {
        localStringBuilder1.append("]");
        str1 = localStringBuilder1.toString();
        break;
      }
      StringBuilder localStringBuilder2 = localStringBuilder1.append("'");
      String str2 = paramArrayOfString[i];
      localStringBuilder2.append(str2).append("'");
      int k = paramArrayOfString.length - 1;
      if (i != k)
        localStringBuilder1.append(", ");
      i += 1;
    }
  }

  public static String toString(int[][] paramArrayOfInt)
  {
    if (paramArrayOfInt == null);
    StringBuilder localStringBuilder;
    int i;
    for (String str = "NULL"; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      i = 0;
      int j = paramArrayOfInt.length;
      if (i < j)
        break;
      localStringBuilder.append("]");
    }
    localStringBuilder.append("[");
    int k = 0;
    while (true)
    {
      int m = paramArrayOfInt[i].length;
      if (k >= m)
      {
        localStringBuilder.append("]");
        int n = paramArrayOfInt.length - 1;
        if (i != n)
          localStringBuilder.append(" ");
        i += 1;
        break;
      }
      int i1 = paramArrayOfInt[i][k];
      localStringBuilder.append(i1);
      int i2 = paramArrayOfInt[i].length - 1;
      if (k != i2)
        localStringBuilder.append(", ");
      k += 1;
    }
  }

  public static String toString(long[][] paramArrayOfLong)
  {
    if (paramArrayOfLong == null);
    StringBuilder localStringBuilder;
    int i;
    for (String str = "NULL"; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      i = 0;
      int j = paramArrayOfLong.length;
      if (i < j)
        break;
      localStringBuilder.append("]");
    }
    localStringBuilder.append("[");
    int k = 0;
    while (true)
    {
      int m = paramArrayOfLong[i].length;
      if (k >= m)
      {
        localStringBuilder.append("]");
        int n = paramArrayOfLong.length - 1;
        if (i != n)
          localStringBuilder.append(" ");
        i += 1;
        break;
      }
      long l = paramArrayOfLong[i][k];
      localStringBuilder.append(l);
      int i1 = paramArrayOfLong[i].length - 1;
      if (k != i1)
        localStringBuilder.append(", ");
      k += 1;
    }
  }

  public static InputStream toUTF8InputStream(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
      return localByteArrayInputStream;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError();
  }

  public static String toUpperCase(String paramString)
  {
    if (paramString == null);
    for (String str = null; ; str = paramString.toUpperCase())
      return str;
  }

  public static String unescapeCString(String paramString)
  {
    if (paramString.indexOf('\\') < 0);
    StringBuilder localStringBuilder;
    int i;
    int j;
    for (String str = paramString; ; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder();
      i = paramString.length();
      j = 0;
      if (j < i)
        break;
    }
    int k = j + 1;
    int m = paramString.charAt(j);
    if ((m == 92) && (k < i))
    {
      j = k + 1;
      m = paramString.charAt(k);
    }
    int i2;
    int n;
    switch (m)
    {
    default:
      if ((m != 120) || (j >= i) || (!isHex(paramString.charAt(j))))
        break;
      k = j + 1;
      i2 = hexValue(paramString.charAt(j));
      if ((k < i) && (isHex(paramString.charAt(k))))
      {
        int i3 = i2 * 16;
        int i4 = k + 1;
        int i5 = hexValue(paramString.charAt(k));
        i2 = i3 + i5;
        k = i4;
      }
      m = (char)i2;
    case 97:
    case 98:
    case 102:
    case 110:
    case 114:
    case 116:
    case 118:
    case 92:
    case 63:
    case 39:
    case 34:
      while (true)
      {
        localStringBuilder.append(m);
        j = k;
        break;
        n = 7;
        k = j;
        continue;
        n = 8;
        k = j;
        continue;
        n = 12;
        k = j;
        continue;
        n = 10;
        k = j;
        continue;
        n = 13;
        k = j;
        continue;
        n = 9;
        k = j;
        continue;
        n = 11;
        k = j;
        continue;
        n = 92;
        k = j;
        continue;
        n = 63;
        k = j;
        continue;
        n = 39;
        k = j;
        continue;
        n = 34;
        k = j;
      }
    }
    if (isOctal(n))
    {
      i2 = n - 48;
      if ((j < i) && (isOctal(paramString.charAt(j))))
      {
        int i6 = i2 * 8;
        int i7 = j + 1;
        int i8 = paramString.charAt(j) - '0';
        i2 = i6 + i8;
        j = i7;
      }
      if ((j >= i) || (!isOctal(paramString.charAt(j))))
        break label571;
      int i9 = i2 * 8;
      k = j + 1;
      int i10 = paramString.charAt(j) - '0';
      i2 = i9 + i10;
    }
    while (true)
    {
      int i1 = (char)i2;
      break;
      localStringBuilder.append(92);
      k = j;
      break;
      label571: k = j;
    }
  }

  // ERROR //
  public static final String unescapeHTML(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1193	java/lang/String:toCharArray	()[C
    //   4: astore_1
    //   5: aload_1
    //   6: arraylength
    //   7: newarray char
    //   9: astore_2
    //   10: iconst_0
    //   11: istore_3
    //   12: iconst_0
    //   13: istore 4
    //   15: aload_1
    //   16: arraylength
    //   17: istore 5
    //   19: iload_3
    //   20: iload 5
    //   22: if_icmplt +15 -> 37
    //   25: new 844	java/lang/String
    //   28: dup
    //   29: aload_2
    //   30: iconst_0
    //   31: iload 4
    //   33: invokespecial 1634	java/lang/String:<init>	([CII)V
    //   36: areturn
    //   37: aload_1
    //   38: iload_3
    //   39: caload
    //   40: bipush 38
    //   42: if_icmpeq +35 -> 77
    //   45: iload 4
    //   47: iconst_1
    //   48: iadd
    //   49: istore 6
    //   51: iload_3
    //   52: iconst_1
    //   53: iadd
    //   54: istore 7
    //   56: aload_1
    //   57: iload_3
    //   58: caload
    //   59: istore 8
    //   61: aload_2
    //   62: iload 4
    //   64: iload 8
    //   66: castore
    //   67: iload 7
    //   69: istore_3
    //   70: iload 6
    //   72: istore 4
    //   74: goto -59 -> 15
    //   77: iload_3
    //   78: iconst_1
    //   79: iadd
    //   80: istore 9
    //   82: aload_1
    //   83: arraylength
    //   84: istore 10
    //   86: iload 9
    //   88: iload 10
    //   90: if_icmpge +415 -> 505
    //   93: aload_1
    //   94: iload 9
    //   96: caload
    //   97: bipush 35
    //   99: if_icmpne +406 -> 505
    //   102: iload 9
    //   104: iconst_1
    //   105: iadd
    //   106: istore 11
    //   108: aload_1
    //   109: arraylength
    //   110: istore 12
    //   112: iload 11
    //   114: iload 12
    //   116: if_icmplt +200 -> 316
    //   119: iconst_0
    //   120: istore 13
    //   122: aload_1
    //   123: arraylength
    //   124: istore 14
    //   126: iload 11
    //   128: iload 14
    //   130: if_icmpge +360 -> 490
    //   133: aload_1
    //   134: iload 11
    //   136: caload
    //   137: bipush 59
    //   139: if_icmpne +351 -> 490
    //   142: iload_3
    //   143: iconst_1
    //   144: iadd
    //   145: istore 15
    //   147: aload_0
    //   148: iload 15
    //   150: invokevirtual 992	java/lang/String:charAt	(I)C
    //   153: ldc_w 1635
    //   156: if_icmpne +241 -> 397
    //   159: aconst_null
    //   160: astore 16
    //   162: iload_3
    //   163: iconst_2
    //   164: iadd
    //   165: istore 9
    //   167: aload_0
    //   168: iload 9
    //   170: invokevirtual 992	java/lang/String:charAt	(I)C
    //   173: istore 9
    //   175: iload 9
    //   177: ldc_w 1623
    //   180: if_icmpeq +11 -> 191
    //   183: iload 9
    //   185: ldc_w 1636
    //   188: if_icmpne +147 -> 335
    //   191: iload_3
    //   192: iconst_3
    //   193: iadd
    //   194: istore 17
    //   196: iload 11
    //   198: iload_3
    //   199: isub
    //   200: iconst_3
    //   201: isub
    //   202: istore 18
    //   204: new 844	java/lang/String
    //   207: dup
    //   208: aload_1
    //   209: iload 17
    //   211: iload 18
    //   213: invokespecial 1634	java/lang/String:<init>	([CII)V
    //   216: bipush 16
    //   218: invokestatic 1639	java/lang/Long:parseLong	(Ljava/lang/String;I)J
    //   221: lstore 19
    //   223: lload 19
    //   225: ldc2_w 1640
    //   228: lcmp
    //   229: ifle +243 -> 472
    //   232: lload 19
    //   234: ldc2_w 1642
    //   237: lcmp
    //   238: ifge +234 -> 472
    //   241: iload 4
    //   243: iconst_1
    //   244: iadd
    //   245: istore 21
    //   247: lload 19
    //   249: l2i
    //   250: i2c
    //   251: istore 22
    //   253: aload_2
    //   254: iload 4
    //   256: iload 22
    //   258: castore
    //   259: iload 21
    //   261: istore 16
    //   263: iconst_1
    //   264: istore 21
    //   266: iload 11
    //   268: iconst_1
    //   269: iadd
    //   270: istore 9
    //   272: iload 21
    //   274: ifne +32 -> 306
    //   277: iload 9
    //   279: iload_3
    //   280: isub
    //   281: istore 23
    //   283: aload_1
    //   284: iload_3
    //   285: aload_2
    //   286: iload 16
    //   288: iload 23
    //   290: invokestatic 1649	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   293: iload 9
    //   295: iload_3
    //   296: isub
    //   297: istore 24
    //   299: iload 16
    //   301: iload 24
    //   303: iadd
    //   304: istore 16
    //   306: iload 9
    //   308: istore_3
    //   309: iload 16
    //   311: istore 4
    //   313: goto -298 -> 15
    //   316: aload_1
    //   317: iload 11
    //   319: caload
    //   320: invokestatic 1652	java/lang/Character:isLetterOrDigit	(C)Z
    //   323: ifeq -204 -> 119
    //   326: iload 11
    //   328: iconst_1
    //   329: iadd
    //   330: istore 11
    //   332: goto -224 -> 108
    //   335: iload 9
    //   337: invokestatic 1592	java/lang/Character:isDigit	(C)Z
    //   340: ifeq +143 -> 483
    //   343: iload_3
    //   344: iconst_2
    //   345: iadd
    //   346: istore 25
    //   348: iload 11
    //   350: iload_3
    //   351: isub
    //   352: iconst_2
    //   353: isub
    //   354: istore 26
    //   356: new 844	java/lang/String
    //   359: dup
    //   360: aload_1
    //   361: iload 25
    //   363: iload 26
    //   365: invokespecial 1634	java/lang/String:<init>	([CII)V
    //   368: invokestatic 1552	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   371: lstore 19
    //   373: goto -150 -> 223
    //   376: astore 27
    //   378: aload 16
    //   380: astore 28
    //   382: iload 4
    //   384: istore 21
    //   386: iload 21
    //   388: istore 16
    //   390: iload 13
    //   392: istore 21
    //   394: goto -128 -> 266
    //   397: iload 11
    //   399: iload_3
    //   400: isub
    //   401: iconst_1
    //   402: iadd
    //   403: istore 29
    //   405: new 844	java/lang/String
    //   408: dup
    //   409: aload_1
    //   410: iload_3
    //   411: iload 29
    //   413: invokespecial 1634	java/lang/String:<init>	([CII)V
    //   416: astore 30
    //   418: getstatic 75	com/yingyonghui/market/util/StringUtil:escapeStrings	Ljava/util/Map;
    //   421: aload 30
    //   423: invokeinterface 1461 2 0
    //   428: checkcast 77	java/lang/Character
    //   431: astore 16
    //   433: aload 16
    //   435: ifnull +37 -> 472
    //   438: iload 4
    //   440: iconst_1
    //   441: iadd
    //   442: istore 31
    //   444: aload 16
    //   446: invokevirtual 1072	java/lang/Character:charValue	()C
    //   449: istore 32
    //   451: aload_2
    //   452: iload 4
    //   454: iload 32
    //   456: castore
    //   457: iconst_1
    //   458: istore 21
    //   460: iload 31
    //   462: istore 16
    //   464: goto -198 -> 266
    //   467: astore 33
    //   469: goto -83 -> 386
    //   472: iload 13
    //   474: istore 21
    //   476: iload 4
    //   478: istore 16
    //   480: goto -214 -> 266
    //   483: aload 16
    //   485: astore 9
    //   487: goto -264 -> 223
    //   490: iload 13
    //   492: istore 21
    //   494: iload 11
    //   496: istore 9
    //   498: iload 4
    //   500: istore 16
    //   502: goto -230 -> 272
    //   505: iload 9
    //   507: istore 11
    //   509: goto -401 -> 108
    //
    // Exception table:
    //   from	to	target	type
    //   167	223	376	java/lang/NumberFormatException
    //   335	373	376	java/lang/NumberFormatException
    //   253	259	467	java/lang/NumberFormatException
  }

  public static String unescapeJavaScript(String paramString)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    int j = 0;
    while (true)
    {
      int k = paramString.length();
      if (j >= k)
        return localStringBuilder.toString();
      int m = paramString.charAt(j);
      if (m == 92)
      {
        int n = j + 1;
        j = javaScriptUnescapeHelper(paramString, n, localStringBuilder);
        continue;
      }
      localStringBuilder.append(m);
      j += 1;
    }
  }

  public static String unescapeMySQLString(String paramString)
    throws IllegalArgumentException
  {
    char[] arrayOfChar = paramString.toCharArray();
    if (arrayOfChar.length >= 2)
    {
      int i = arrayOfChar[0];
      int j = arrayOfChar.length - 1;
      int k = arrayOfChar[j];
      if ((i == k) && ((arrayOfChar[0] == '\'') || (arrayOfChar[0] == '"')));
    }
    else
    {
      String str1 = "not a valid MySQL string: " + paramString;
      throw new IllegalArgumentException(str1);
    }
    int m = 1;
    int n = 0;
    int i1 = 1;
    int i2 = arrayOfChar.length - 1;
    if (i1 >= i2)
    {
      if (n != 0)
      {
        String str2 = "not a valid MySQL string: " + paramString;
        throw new IllegalArgumentException(str2);
      }
    }
    else
    {
      if (n == 0)
        if (arrayOfChar[i1] == '\\')
          n = 1;
      while (true)
      {
        i1 += 1;
        break;
        int i3 = arrayOfChar[i1];
        int i4 = arrayOfChar[0];
        if (i3 == i4)
        {
          n = 2;
          continue;
        }
        int i5 = m + 1;
        int i6 = arrayOfChar[i1];
        arrayOfChar[m] = i6;
        m = i5;
        continue;
        if (n == 1)
        {
          switch (arrayOfChar[i1])
          {
          default:
            int i7 = m + 1;
            int i8 = arrayOfChar[i1];
            arrayOfChar[m] = i8;
            m = i7;
          case '0':
          case '\'':
          case '"':
          case 'b':
          case 'n':
          case 'r':
          case 't':
          case 'z':
          case '\\':
          }
          while (true)
          {
            n = 0;
            break;
            int i9 = m + 1;
            arrayOfChar[m] = '\000';
            m = i9;
            continue;
            int i10 = m + 1;
            arrayOfChar[m] = '\'';
            m = i10;
            continue;
            int i11 = m + 1;
            arrayOfChar[m] = '"';
            m = i11;
            continue;
            int i12 = m + 1;
            arrayOfChar[m] = '\b';
            m = i12;
            continue;
            int i13 = m + 1;
            arrayOfChar[m] = '\n';
            m = i13;
            continue;
            int i14 = m + 1;
            arrayOfChar[m] = '\r';
            m = i14;
            continue;
            int i15 = m + 1;
            arrayOfChar[m] = '\t';
            m = i15;
            continue;
            int i16 = m + 1;
            arrayOfChar[m] = '\032';
            m = i16;
            continue;
            int i17 = m + 1;
            arrayOfChar[m] = '\\';
            m = i17;
          }
        }
        int i18 = arrayOfChar[i1];
        int i19 = arrayOfChar[0];
        if (i18 != i19)
        {
          String str3 = "not a valid MySQL string: " + paramString;
          throw new IllegalArgumentException(str3);
        }
        int i20 = m + 1;
        int i21 = arrayOfChar[0];
        arrayOfChar[m] = i21;
        n = 0;
        m = i20;
      }
    }
    int i22 = m - 1;
    return new String(arrayOfChar, 1, i22);
  }

  public static String unicodeEscape(String paramString)
  {
    String str1;
    if (allAscii(paramString))
    {
      str1 = paramString;
      return str1;
    }
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    int j = paramString.length();
    int k = 0;
    int m;
    while (true)
    {
      if (k >= j)
      {
        str1 = localStringBuilder.toString();
        break;
      }
      m = paramString.charAt(k);
      if (m > 127)
        break label81;
      localStringBuilder.append(m);
      k += 1;
    }
    label81: localStringBuilder.append("\\u");
    String str2 = Integer.toHexString(m);
    int n = str2.length();
    int i1 = 4 - n;
    int i2 = 0;
    while (true)
    {
      if (i2 >= i1)
      {
        localStringBuilder.append(str2);
        break;
      }
      localStringBuilder.append(48);
      i2 += 1;
    }
  }

  public static byte[] utf8ToBytes(String paramString)
  {
    return encodingToBytes(paramString, "UTF8");
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.StringUtil
 * JD-Core Version:    0.6.0
 */