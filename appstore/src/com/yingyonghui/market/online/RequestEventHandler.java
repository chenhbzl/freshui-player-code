package com.yingyonghui.market.online;

public class RequestEventHandler extends Thread
{
  private MarketServiceAgent agent = null;
  private DownloadService mDownloadService;
  public boolean running = 1;
  private MarketService service = null;
  private int threadID = 0;

  public RequestEventHandler(MarketService paramMarketService, int paramInt)
  {
    this.service = paramMarketService;
    MarketServiceAgent localMarketServiceAgent = paramMarketService.getServiceAgent();
    this.agent = localMarketServiceAgent;
    this.threadID = paramInt;
    DownloadService localDownloadService = DownloadService.getInstance(paramMarketService.mContext);
    this.mDownloadService = localDownloadService;
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 29	com/yingyonghui/market/online/RequestEventHandler:running	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield 22	com/yingyonghui/market/online/RequestEventHandler:service	Lcom/yingyonghui/market/online/MarketService;
    //   12: astore_1
    //   13: aload_0
    //   14: getfield 26	com/yingyonghui/market/online/RequestEventHandler:threadID	I
    //   17: istore_2
    //   18: aload_1
    //   19: iload_2
    //   20: invokevirtual 58	com/yingyonghui/market/online/MarketService:popRequest	(I)Ljava/lang/Object;
    //   23: checkcast 60	com/yingyonghui/market/online/Request
    //   26: astore_3
    //   27: aload_3
    //   28: ifnull -28 -> 0
    //   31: aload_3
    //   32: invokevirtual 64	com/yingyonghui/market/online/Request:getType	()I
    //   35: lookupswitch	default:+273->308, 300:+276->311, 65537:+420->455, 65538:+514->549, 65539:+3639->3674, 65540:+1655->1690, 65541:+5139->5174, 65542:+3354->3389, 65544:+1084->1119, 65545:+3915->3950, 65546:+1359->1394, 65547:+1817->1852, 65548:+2184->2219, 65550:+3143->3178, 65551:+1232->1267, 65552:+665->700, 65553:+4043->4078, 65555:+816->851, 65556:+950->985, 65557:+4409->4444, 65558:+4844->4879, 65559:+4920->4955, 65560:+4937->4972, 65561:+1518->1553, 65562:+4954->4989, 65563:+2349->2384, 65564:+2968->3003, 65565:+2455->2490, 65566:+2591->2626, 65567:+2786->2821, 65568:+4768->4803, 65569:+3498->3533, 268500997:+3805->3840, 268500998:+1993->2028
    //   309: impdep1
    //   310: <illegal opcode>
    //   311: aload_3
    //   312: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   315: checkcast 70	[Ljava/lang/Object;
    //   318: astore 4
    //   320: aload 4
    //   322: ifnull +8 -> 330
    //   325: aload 4
    //   327: arraylength
    //   328: istore 5
    //   330: aload_0
    //   331: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   334: astore 6
    //   336: aload 4
    //   338: iconst_0
    //   339: aaload
    //   340: checkcast 72	java/lang/Integer
    //   343: invokevirtual 75	java/lang/Integer:intValue	()I
    //   346: istore 7
    //   348: aload 4
    //   350: iconst_1
    //   351: aaload
    //   352: checkcast 77	java/lang/String
    //   355: astore 8
    //   357: aload 4
    //   359: iconst_2
    //   360: aaload
    //   361: checkcast 77	java/lang/String
    //   364: astore 9
    //   366: aload 6
    //   368: iload 7
    //   370: aload 8
    //   372: aload 9
    //   374: invokevirtual 83	com/yingyonghui/market/online/MarketServiceAgent:deleteComment	(ILjava/lang/String;Ljava/lang/String;)Z
    //   377: istore 10
    //   379: aload_3
    //   380: astore 11
    //   382: ldc 84
    //   384: istore 12
    //   386: aload 11
    //   388: iload 12
    //   390: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   393: iload 10
    //   395: invokestatic 94	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   398: astore 13
    //   400: aload_3
    //   401: astore 14
    //   403: aload 13
    //   405: astore 15
    //   407: aload 14
    //   409: aload 15
    //   411: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   414: goto -414 -> 0
    //   417: astore 16
    //   419: aload_3
    //   420: astore 17
    //   422: ldc 99
    //   424: istore 18
    //   426: aload 17
    //   428: iload 18
    //   430: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   433: aload_3
    //   434: astore 19
    //   436: aconst_null
    //   437: astore 20
    //   439: aload 19
    //   441: aload 20
    //   443: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   446: goto -446 -> 0
    //   449: invokevirtual 102	java/lang/InterruptedException:printStackTrace	()V
    //   452: goto -452 -> 0
    //   455: aload_3
    //   456: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   459: checkcast 72	java/lang/Integer
    //   462: invokevirtual 75	java/lang/Integer:intValue	()I
    //   465: istore 21
    //   467: aload_0
    //   468: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   471: astore 22
    //   473: iload 21
    //   475: istore 23
    //   477: aload 22
    //   479: iload 23
    //   481: invokevirtual 106	com/yingyonghui/market/online/MarketServiceAgent:getCategory	(I)Ljava/util/Vector;
    //   484: astore 24
    //   486: aload_3
    //   487: astore 25
    //   489: ldc 84
    //   491: istore 26
    //   493: aload 25
    //   495: iload 26
    //   497: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   500: aload_3
    //   501: astore 27
    //   503: aload 24
    //   505: astore 28
    //   507: aload 27
    //   509: aload 28
    //   511: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   514: goto -514 -> 0
    //   517: astore 29
    //   519: aload_3
    //   520: astore 30
    //   522: ldc 99
    //   524: istore 31
    //   526: aload 30
    //   528: iload 31
    //   530: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   533: aload_3
    //   534: astore 32
    //   536: aconst_null
    //   537: astore 33
    //   539: aload 32
    //   541: aload 33
    //   543: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   546: goto -546 -> 0
    //   549: aload_3
    //   550: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   553: checkcast 70	[Ljava/lang/Object;
    //   556: astore 4
    //   558: aload 4
    //   560: ifnull +8 -> 568
    //   563: aload 4
    //   565: arraylength
    //   566: istore 34
    //   568: aload_0
    //   569: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   572: astore 35
    //   574: aload 4
    //   576: iconst_0
    //   577: aaload
    //   578: checkcast 72	java/lang/Integer
    //   581: invokevirtual 75	java/lang/Integer:intValue	()I
    //   584: istore 36
    //   586: aload 4
    //   588: iconst_1
    //   589: aaload
    //   590: checkcast 72	java/lang/Integer
    //   593: invokevirtual 75	java/lang/Integer:intValue	()I
    //   596: istore 37
    //   598: aload 4
    //   600: iconst_2
    //   601: aaload
    //   602: checkcast 72	java/lang/Integer
    //   605: invokevirtual 75	java/lang/Integer:intValue	()I
    //   608: istore 38
    //   610: aload 4
    //   612: iconst_3
    //   613: aaload
    //   614: checkcast 72	java/lang/Integer
    //   617: invokevirtual 75	java/lang/Integer:intValue	()I
    //   620: istore 39
    //   622: aload 35
    //   624: iload 36
    //   626: iload 37
    //   628: iload 38
    //   630: iload 39
    //   632: invokevirtual 110	com/yingyonghui/market/online/MarketServiceAgent:getAppList	(IIII)Ljava/util/ArrayList;
    //   635: astore 40
    //   637: aload_3
    //   638: astore 41
    //   640: ldc 84
    //   642: istore 42
    //   644: aload 41
    //   646: iload 42
    //   648: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   651: aload_3
    //   652: astore 43
    //   654: aload 40
    //   656: astore 44
    //   658: aload 43
    //   660: aload 44
    //   662: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   665: goto -665 -> 0
    //   668: astore 45
    //   670: aload_3
    //   671: astore 46
    //   673: ldc 99
    //   675: istore 47
    //   677: aload 46
    //   679: iload 47
    //   681: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   684: aload_3
    //   685: astore 48
    //   687: aconst_null
    //   688: astore 49
    //   690: aload 48
    //   692: aload 49
    //   694: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   697: goto -697 -> 0
    //   700: aload_3
    //   701: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   704: checkcast 70	[Ljava/lang/Object;
    //   707: astore 4
    //   709: aload 4
    //   711: ifnull +8 -> 719
    //   714: aload 4
    //   716: arraylength
    //   717: istore 50
    //   719: aload_0
    //   720: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   723: astore 51
    //   725: aload 4
    //   727: iconst_0
    //   728: aaload
    //   729: checkcast 72	java/lang/Integer
    //   732: invokevirtual 75	java/lang/Integer:intValue	()I
    //   735: istore 52
    //   737: aload 4
    //   739: iconst_1
    //   740: aaload
    //   741: checkcast 72	java/lang/Integer
    //   744: invokevirtual 75	java/lang/Integer:intValue	()I
    //   747: istore 53
    //   749: aload 4
    //   751: iconst_2
    //   752: aaload
    //   753: checkcast 72	java/lang/Integer
    //   756: invokevirtual 75	java/lang/Integer:intValue	()I
    //   759: istore 54
    //   761: aload 4
    //   763: iconst_3
    //   764: aaload
    //   765: checkcast 72	java/lang/Integer
    //   768: invokevirtual 75	java/lang/Integer:intValue	()I
    //   771: istore 55
    //   773: aload 51
    //   775: iload 52
    //   777: iload 53
    //   779: iload 54
    //   781: iload 55
    //   783: invokevirtual 113	com/yingyonghui/market/online/MarketServiceAgent:getAppRankingList	(IIII)Ljava/util/ArrayList;
    //   786: astore 56
    //   788: aload_3
    //   789: astore 57
    //   791: ldc 84
    //   793: istore 58
    //   795: aload 57
    //   797: iload 58
    //   799: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   802: aload_3
    //   803: astore 59
    //   805: aload 56
    //   807: astore 60
    //   809: aload 59
    //   811: aload 60
    //   813: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   816: goto -816 -> 0
    //   819: astore 61
    //   821: aload_3
    //   822: astore 62
    //   824: ldc 99
    //   826: istore 63
    //   828: aload 62
    //   830: iload 63
    //   832: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   835: aload_3
    //   836: astore 64
    //   838: aconst_null
    //   839: astore 65
    //   841: aload 64
    //   843: aload 65
    //   845: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   848: goto -848 -> 0
    //   851: aload_3
    //   852: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   855: checkcast 70	[Ljava/lang/Object;
    //   858: astore 4
    //   860: aload 4
    //   862: ifnull +8 -> 870
    //   865: aload 4
    //   867: arraylength
    //   868: istore 66
    //   870: aload_0
    //   871: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   874: astore 67
    //   876: aload 4
    //   878: iconst_0
    //   879: aaload
    //   880: checkcast 115	[Ljava/lang/String;
    //   883: astore 68
    //   885: aload 4
    //   887: iconst_1
    //   888: aaload
    //   889: checkcast 72	java/lang/Integer
    //   892: invokevirtual 75	java/lang/Integer:intValue	()I
    //   895: istore 69
    //   897: aload 4
    //   899: iconst_2
    //   900: aaload
    //   901: checkcast 72	java/lang/Integer
    //   904: invokevirtual 75	java/lang/Integer:intValue	()I
    //   907: istore 70
    //   909: aload 67
    //   911: aload 68
    //   913: iload 69
    //   915: iload 70
    //   917: invokevirtual 119	com/yingyonghui/market/online/MarketServiceAgent:getAppLikeList	([Ljava/lang/String;II)Ljava/util/ArrayList;
    //   920: astore 71
    //   922: aload_3
    //   923: astore 72
    //   925: ldc 84
    //   927: istore 73
    //   929: aload 72
    //   931: iload 73
    //   933: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   936: aload_3
    //   937: astore 74
    //   939: aload 71
    //   941: astore 75
    //   943: aload 74
    //   945: aload 75
    //   947: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   950: goto -950 -> 0
    //   953: astore 76
    //   955: aload_3
    //   956: astore 77
    //   958: ldc 99
    //   960: istore 78
    //   962: aload 77
    //   964: iload 78
    //   966: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   969: aload_3
    //   970: astore 79
    //   972: aconst_null
    //   973: astore 80
    //   975: aload 79
    //   977: aload 80
    //   979: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   982: goto -982 -> 0
    //   985: aload_3
    //   986: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   989: checkcast 70	[Ljava/lang/Object;
    //   992: astore 4
    //   994: aload 4
    //   996: ifnull +8 -> 1004
    //   999: aload 4
    //   1001: arraylength
    //   1002: istore 81
    //   1004: aload_0
    //   1005: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1008: astore 82
    //   1010: aload 4
    //   1012: iconst_0
    //   1013: aaload
    //   1014: checkcast 77	java/lang/String
    //   1017: astore 83
    //   1019: aload 4
    //   1021: iconst_1
    //   1022: aaload
    //   1023: checkcast 72	java/lang/Integer
    //   1026: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1029: istore 84
    //   1031: aload 4
    //   1033: iconst_2
    //   1034: aaload
    //   1035: checkcast 72	java/lang/Integer
    //   1038: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1041: istore 85
    //   1043: aload 82
    //   1045: aload 83
    //   1047: iload 84
    //   1049: iload 85
    //   1051: invokevirtual 123	com/yingyonghui/market/online/MarketServiceAgent:getAppRelativeList	(Ljava/lang/String;II)Ljava/util/ArrayList;
    //   1054: astore 86
    //   1056: aload_3
    //   1057: astore 87
    //   1059: ldc 84
    //   1061: istore 88
    //   1063: aload 87
    //   1065: iload 88
    //   1067: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1070: aload_3
    //   1071: astore 89
    //   1073: aload 86
    //   1075: astore 90
    //   1077: aload 89
    //   1079: aload 90
    //   1081: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1084: goto -1084 -> 0
    //   1087: astore 91
    //   1089: aload_3
    //   1090: astore 92
    //   1092: ldc 99
    //   1094: istore 93
    //   1096: aload 92
    //   1098: iload 93
    //   1100: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1103: aload_3
    //   1104: astore 94
    //   1106: aconst_null
    //   1107: astore 95
    //   1109: aload 94
    //   1111: aload 95
    //   1113: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1116: goto -1116 -> 0
    //   1119: aload_3
    //   1120: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1123: checkcast 70	[Ljava/lang/Object;
    //   1126: astore 4
    //   1128: aload 4
    //   1130: ifnull +8 -> 1138
    //   1133: aload 4
    //   1135: arraylength
    //   1136: istore 96
    //   1138: aload_0
    //   1139: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1142: astore 97
    //   1144: aload 4
    //   1146: iconst_0
    //   1147: aaload
    //   1148: checkcast 115	[Ljava/lang/String;
    //   1151: astore 98
    //   1153: aload 4
    //   1155: iconst_1
    //   1156: aaload
    //   1157: checkcast 72	java/lang/Integer
    //   1160: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1163: istore 99
    //   1165: aload 4
    //   1167: iconst_2
    //   1168: aaload
    //   1169: checkcast 72	java/lang/Integer
    //   1172: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1175: istore 100
    //   1177: aload 4
    //   1179: iconst_3
    //   1180: aaload
    //   1181: checkcast 72	java/lang/Integer
    //   1184: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1187: istore 101
    //   1189: aload 97
    //   1191: aload 98
    //   1193: iload 99
    //   1195: iload 100
    //   1197: iload 101
    //   1199: invokevirtual 127	com/yingyonghui/market/online/MarketServiceAgent:getDownloadedAppList	([Ljava/lang/String;III)Ljava/util/ArrayList;
    //   1202: astore 102
    //   1204: aload_3
    //   1205: astore 103
    //   1207: ldc 84
    //   1209: istore 104
    //   1211: aload 103
    //   1213: iload 104
    //   1215: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1218: aload_3
    //   1219: astore 105
    //   1221: aload 102
    //   1223: astore 106
    //   1225: aload 105
    //   1227: aload 106
    //   1229: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1232: goto -1232 -> 0
    //   1235: astore 107
    //   1237: aload_3
    //   1238: astore 108
    //   1240: ldc 99
    //   1242: istore 109
    //   1244: aload 108
    //   1246: iload 109
    //   1248: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1251: aload_3
    //   1252: astore 110
    //   1254: aconst_null
    //   1255: astore 111
    //   1257: aload 110
    //   1259: aload 111
    //   1261: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1264: goto -1264 -> 0
    //   1267: aload_3
    //   1268: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1271: checkcast 70	[Ljava/lang/Object;
    //   1274: astore 4
    //   1276: aload 4
    //   1278: ifnull +8 -> 1286
    //   1281: aload 4
    //   1283: arraylength
    //   1284: istore 112
    //   1286: aload_0
    //   1287: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1290: astore 113
    //   1292: aload 4
    //   1294: iconst_0
    //   1295: aaload
    //   1296: checkcast 115	[Ljava/lang/String;
    //   1299: astore 114
    //   1301: aload 4
    //   1303: iconst_1
    //   1304: aaload
    //   1305: checkcast 72	java/lang/Integer
    //   1308: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1311: istore 115
    //   1313: aload 113
    //   1315: aload 114
    //   1317: iload 115
    //   1319: invokevirtual 131	com/yingyonghui/market/online/MarketServiceAgent:getUpdateAvaliableNum	([Ljava/lang/String;I)I
    //   1322: istore 116
    //   1324: aload_3
    //   1325: astore 117
    //   1327: ldc 84
    //   1329: istore 118
    //   1331: aload 117
    //   1333: iload 118
    //   1335: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1338: iload 116
    //   1340: invokestatic 134	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1343: astore 119
    //   1345: aload_3
    //   1346: astore 120
    //   1348: aload 119
    //   1350: astore 121
    //   1352: aload 120
    //   1354: aload 121
    //   1356: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1359: goto -1359 -> 0
    //   1362: astore 122
    //   1364: aload_3
    //   1365: astore 123
    //   1367: ldc 99
    //   1369: istore 124
    //   1371: aload 123
    //   1373: iload 124
    //   1375: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1378: aload_3
    //   1379: astore 125
    //   1381: aconst_null
    //   1382: astore 126
    //   1384: aload 125
    //   1386: aload 126
    //   1388: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1391: goto -1391 -> 0
    //   1394: aload_3
    //   1395: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1398: checkcast 70	[Ljava/lang/Object;
    //   1401: astore 4
    //   1403: aload 4
    //   1405: ifnull +8 -> 1413
    //   1408: aload 4
    //   1410: arraylength
    //   1411: istore 127
    //   1413: aload_0
    //   1414: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1417: astore 128
    //   1419: aload 4
    //   1421: iconst_0
    //   1422: aaload
    //   1423: checkcast 77	java/lang/String
    //   1426: astore 129
    //   1428: aload 4
    //   1430: iconst_1
    //   1431: aaload
    //   1432: checkcast 72	java/lang/Integer
    //   1435: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1438: istore 130
    //   1440: aload 4
    //   1442: iconst_2
    //   1443: aaload
    //   1444: checkcast 72	java/lang/Integer
    //   1447: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1450: istore 131
    //   1452: aload 4
    //   1454: iconst_3
    //   1455: aaload
    //   1456: checkcast 72	java/lang/Integer
    //   1459: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1462: istore 132
    //   1464: aload 4
    //   1466: iconst_4
    //   1467: aaload
    //   1468: checkcast 77	java/lang/String
    //   1471: astore 133
    //   1473: aload 128
    //   1475: aload 129
    //   1477: iload 130
    //   1479: iload 131
    //   1481: iload 132
    //   1483: aload 133
    //   1485: invokevirtual 138	com/yingyonghui/market/online/MarketServiceAgent:getSearchAppList	(Ljava/lang/String;IIILjava/lang/String;)Ljava/util/ArrayList;
    //   1488: astore 134
    //   1490: aload_3
    //   1491: astore 135
    //   1493: ldc 84
    //   1495: istore 136
    //   1497: aload 135
    //   1499: iload 136
    //   1501: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1504: aload_3
    //   1505: astore 137
    //   1507: aload 134
    //   1509: astore 138
    //   1511: aload 137
    //   1513: aload 138
    //   1515: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1518: goto -1518 -> 0
    //   1521: astore 139
    //   1523: aload_3
    //   1524: astore 140
    //   1526: ldc 99
    //   1528: istore 141
    //   1530: aload 140
    //   1532: iload 141
    //   1534: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1537: aload_3
    //   1538: astore 142
    //   1540: aconst_null
    //   1541: astore 143
    //   1543: aload 142
    //   1545: aload 143
    //   1547: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1550: goto -1550 -> 0
    //   1553: aload_3
    //   1554: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1557: checkcast 70	[Ljava/lang/Object;
    //   1560: astore 4
    //   1562: aload 4
    //   1564: ifnull +8 -> 1572
    //   1567: aload 4
    //   1569: arraylength
    //   1570: istore 144
    //   1572: aload_0
    //   1573: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1576: astore 145
    //   1578: aload 4
    //   1580: iconst_0
    //   1581: aaload
    //   1582: checkcast 72	java/lang/Integer
    //   1585: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1588: istore 146
    //   1590: aload 4
    //   1592: iconst_1
    //   1593: aaload
    //   1594: checkcast 72	java/lang/Integer
    //   1597: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1600: istore 147
    //   1602: aload 4
    //   1604: iconst_2
    //   1605: aaload
    //   1606: checkcast 72	java/lang/Integer
    //   1609: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1612: istore 148
    //   1614: aload 145
    //   1616: iload 146
    //   1618: iload 147
    //   1620: iload 148
    //   1622: invokevirtual 142	com/yingyonghui/market/online/MarketServiceAgent:getTopKeywords	(III)Ljava/util/ArrayList;
    //   1625: astore 149
    //   1627: aload_3
    //   1628: astore 150
    //   1630: ldc 84
    //   1632: istore 151
    //   1634: aload 150
    //   1636: iload 151
    //   1638: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1641: aload_3
    //   1642: astore 152
    //   1644: aload 149
    //   1646: astore 153
    //   1648: aload 152
    //   1650: aload 153
    //   1652: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1655: goto -1655 -> 0
    //   1658: astore 154
    //   1660: aload_3
    //   1661: astore 155
    //   1663: ldc 99
    //   1665: istore 156
    //   1667: aload 155
    //   1669: iload 156
    //   1671: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1674: aload_3
    //   1675: astore 157
    //   1677: aconst_null
    //   1678: astore 158
    //   1680: aload 157
    //   1682: aload 158
    //   1684: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1687: goto -1687 -> 0
    //   1690: aload_3
    //   1691: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1694: checkcast 70	[Ljava/lang/Object;
    //   1697: astore 4
    //   1699: aload 4
    //   1701: ifnull +8 -> 1709
    //   1704: aload 4
    //   1706: arraylength
    //   1707: istore 159
    //   1709: aload_0
    //   1710: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1713: astore 160
    //   1715: aload 4
    //   1717: iconst_0
    //   1718: aaload
    //   1719: checkcast 72	java/lang/Integer
    //   1722: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1725: istore 161
    //   1727: aload 4
    //   1729: iconst_1
    //   1730: aaload
    //   1731: checkcast 72	java/lang/Integer
    //   1734: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1737: istore 162
    //   1739: aload 4
    //   1741: iconst_2
    //   1742: aaload
    //   1743: checkcast 72	java/lang/Integer
    //   1746: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1749: istore 163
    //   1751: aload 4
    //   1753: iconst_3
    //   1754: aaload
    //   1755: checkcast 72	java/lang/Integer
    //   1758: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1761: istore 164
    //   1763: aload 4
    //   1765: iconst_4
    //   1766: aaload
    //   1767: checkcast 77	java/lang/String
    //   1770: astore 165
    //   1772: aload 160
    //   1774: iload 161
    //   1776: iload 162
    //   1778: iload 163
    //   1780: iload 164
    //   1782: aload 165
    //   1784: invokevirtual 146	com/yingyonghui/market/online/MarketServiceAgent:getAppComments	(IIIILjava/lang/String;)[Ljava/lang/Object;
    //   1787: astore 166
    //   1789: aload_3
    //   1790: astore 167
    //   1792: ldc 84
    //   1794: istore 168
    //   1796: aload 167
    //   1798: iload 168
    //   1800: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1803: aload_3
    //   1804: astore 169
    //   1806: aload 166
    //   1808: astore 170
    //   1810: aload 169
    //   1812: aload 170
    //   1814: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1817: goto -1817 -> 0
    //   1820: astore 171
    //   1822: aload_3
    //   1823: astore 172
    //   1825: ldc 99
    //   1827: istore 173
    //   1829: aload 172
    //   1831: iload 173
    //   1833: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1836: aload_3
    //   1837: astore 174
    //   1839: aconst_null
    //   1840: astore 175
    //   1842: aload 174
    //   1844: aload 175
    //   1846: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1849: goto -1849 -> 0
    //   1852: aload_3
    //   1853: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   1856: checkcast 70	[Ljava/lang/Object;
    //   1859: astore 4
    //   1861: aload 4
    //   1863: ifnull +8 -> 1871
    //   1866: aload 4
    //   1868: arraylength
    //   1869: istore 176
    //   1871: aload_0
    //   1872: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   1875: astore 177
    //   1877: aload 4
    //   1879: iconst_0
    //   1880: aaload
    //   1881: checkcast 72	java/lang/Integer
    //   1884: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1887: istore 178
    //   1889: aload 4
    //   1891: iconst_1
    //   1892: aaload
    //   1893: checkcast 77	java/lang/String
    //   1896: astore 179
    //   1898: aload 4
    //   1900: iconst_2
    //   1901: aaload
    //   1902: checkcast 72	java/lang/Integer
    //   1905: invokevirtual 75	java/lang/Integer:intValue	()I
    //   1908: istore 180
    //   1910: aload 4
    //   1912: iconst_3
    //   1913: aaload
    //   1914: checkcast 77	java/lang/String
    //   1917: astore 181
    //   1919: aload 4
    //   1921: iconst_4
    //   1922: aaload
    //   1923: checkcast 77	java/lang/String
    //   1926: astore 182
    //   1928: aload 4
    //   1930: iconst_5
    //   1931: aaload
    //   1932: checkcast 77	java/lang/String
    //   1935: astore 183
    //   1937: aload 177
    //   1939: iload 178
    //   1941: aload 179
    //   1943: iload 180
    //   1945: ldc 147
    //   1947: aload 181
    //   1949: aload 182
    //   1951: aload 183
    //   1953: invokevirtual 151	com/yingyonghui/market/online/MarketServiceAgent:sendComment	(ILjava/lang/String;IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   1956: istore 184
    //   1958: aload_3
    //   1959: astore 185
    //   1961: ldc 84
    //   1963: istore 186
    //   1965: aload 185
    //   1967: iload 186
    //   1969: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   1972: iload 184
    //   1974: invokestatic 94	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   1977: astore 187
    //   1979: aload_3
    //   1980: astore 188
    //   1982: aload 187
    //   1984: astore 189
    //   1986: aload 188
    //   1988: aload 189
    //   1990: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   1993: goto -1993 -> 0
    //   1996: astore 190
    //   1998: aload_3
    //   1999: astore 191
    //   2001: ldc 99
    //   2003: istore 192
    //   2005: aload 191
    //   2007: iload 192
    //   2009: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2012: aload_3
    //   2013: astore 193
    //   2015: aconst_null
    //   2016: astore 194
    //   2018: aload 193
    //   2020: aload 194
    //   2022: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2025: goto -2025 -> 0
    //   2028: aload_3
    //   2029: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2032: checkcast 70	[Ljava/lang/Object;
    //   2035: astore 4
    //   2037: aload 4
    //   2039: ifnull +8 -> 2047
    //   2042: aload 4
    //   2044: arraylength
    //   2045: istore 195
    //   2047: aload_0
    //   2048: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2051: astore 196
    //   2053: aload 4
    //   2055: iconst_0
    //   2056: aaload
    //   2057: checkcast 72	java/lang/Integer
    //   2060: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2063: istore 197
    //   2065: aload 4
    //   2067: iconst_1
    //   2068: aaload
    //   2069: checkcast 72	java/lang/Integer
    //   2072: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2075: istore 198
    //   2077: aload 4
    //   2079: iconst_2
    //   2080: aaload
    //   2081: checkcast 77	java/lang/String
    //   2084: astore 199
    //   2086: aload 4
    //   2088: iconst_3
    //   2089: aaload
    //   2090: checkcast 72	java/lang/Integer
    //   2093: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2096: istore 200
    //   2098: aload 4
    //   2100: iconst_4
    //   2101: aaload
    //   2102: checkcast 77	java/lang/String
    //   2105: astore 201
    //   2107: aload 4
    //   2109: iconst_5
    //   2110: aaload
    //   2111: checkcast 77	java/lang/String
    //   2114: astore 202
    //   2116: aload 4
    //   2118: bipush 6
    //   2120: aaload
    //   2121: checkcast 77	java/lang/String
    //   2124: astore 203
    //   2126: aload 196
    //   2128: iload 197
    //   2130: iload 198
    //   2132: aload 199
    //   2134: iload 200
    //   2136: ldc 147
    //   2138: aload 201
    //   2140: aload 202
    //   2142: aload 203
    //   2144: invokevirtual 155	com/yingyonghui/market/online/MarketServiceAgent:sendCommentReply	(IILjava/lang/String;IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   2147: istore 204
    //   2149: aload_3
    //   2150: astore 205
    //   2152: ldc 84
    //   2154: istore 206
    //   2156: aload 205
    //   2158: iload 206
    //   2160: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2163: iload 204
    //   2165: invokestatic 94	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2168: astore 207
    //   2170: aload_3
    //   2171: astore 208
    //   2173: aload 207
    //   2175: astore 209
    //   2177: aload 208
    //   2179: aload 209
    //   2181: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2184: goto -2184 -> 0
    //   2187: astore 210
    //   2189: aload_3
    //   2190: astore 211
    //   2192: ldc 99
    //   2194: istore 212
    //   2196: aload 211
    //   2198: iload 212
    //   2200: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2203: aload_3
    //   2204: astore 213
    //   2206: aconst_null
    //   2207: astore 214
    //   2209: aload 213
    //   2211: aload 214
    //   2213: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2216: goto -2216 -> 0
    //   2219: aload_3
    //   2220: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2223: checkcast 70	[Ljava/lang/Object;
    //   2226: astore 4
    //   2228: aload 4
    //   2230: ifnull +8 -> 2238
    //   2233: aload 4
    //   2235: arraylength
    //   2236: istore 215
    //   2238: aload_0
    //   2239: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2242: astore 216
    //   2244: aload 4
    //   2246: iconst_0
    //   2247: aaload
    //   2248: checkcast 72	java/lang/Integer
    //   2251: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2254: istore 217
    //   2256: aload 4
    //   2258: iconst_1
    //   2259: aaload
    //   2260: checkcast 72	java/lang/Integer
    //   2263: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2266: istore 218
    //   2268: aload 4
    //   2270: iconst_2
    //   2271: aaload
    //   2272: checkcast 77	java/lang/String
    //   2275: astore 219
    //   2277: aload 4
    //   2279: iconst_3
    //   2280: aaload
    //   2281: checkcast 77	java/lang/String
    //   2284: astore 220
    //   2286: aload 4
    //   2288: iconst_4
    //   2289: aaload
    //   2290: checkcast 77	java/lang/String
    //   2293: astore 221
    //   2295: aload 216
    //   2297: iload 217
    //   2299: iload 218
    //   2301: aload 219
    //   2303: aload 220
    //   2305: aload 221
    //   2307: ldc 147
    //   2309: invokevirtual 159	com/yingyonghui/market/online/MarketServiceAgent:reportError	(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Z
    //   2312: istore 222
    //   2314: aload_3
    //   2315: astore 223
    //   2317: ldc 84
    //   2319: istore 224
    //   2321: aload 223
    //   2323: iload 224
    //   2325: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2328: iload 222
    //   2330: invokestatic 94	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   2333: astore 225
    //   2335: aload_3
    //   2336: astore 226
    //   2338: aload 225
    //   2340: astore 227
    //   2342: aload 226
    //   2344: aload 227
    //   2346: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2349: goto -2349 -> 0
    //   2352: astore 228
    //   2354: aload_3
    //   2355: astore 229
    //   2357: ldc 99
    //   2359: istore 230
    //   2361: aload 229
    //   2363: iload 230
    //   2365: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2368: aload_3
    //   2369: astore 231
    //   2371: aconst_null
    //   2372: astore 232
    //   2374: aload 231
    //   2376: aload 232
    //   2378: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2381: goto -2381 -> 0
    //   2384: aload_3
    //   2385: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2388: checkcast 70	[Ljava/lang/Object;
    //   2391: astore 4
    //   2393: aload 4
    //   2395: ifnull +8 -> 2403
    //   2398: aload 4
    //   2400: arraylength
    //   2401: istore 233
    //   2403: aload_0
    //   2404: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2407: astore 234
    //   2409: aload 4
    //   2411: iconst_0
    //   2412: aaload
    //   2413: checkcast 77	java/lang/String
    //   2416: astore 235
    //   2418: aload 234
    //   2420: aload 235
    //   2422: invokevirtual 163	com/yingyonghui/market/online/MarketServiceAgent:getNewsContent	(Ljava/lang/String;)Lcom/yingyonghui/market/model/NewsContent;
    //   2425: astore 236
    //   2427: aload_3
    //   2428: astore 237
    //   2430: ldc 84
    //   2432: istore 238
    //   2434: aload 237
    //   2436: iload 238
    //   2438: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2441: aload_3
    //   2442: astore 239
    //   2444: aload 236
    //   2446: astore 240
    //   2448: aload 239
    //   2450: aload 240
    //   2452: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2455: goto -2455 -> 0
    //   2458: astore 241
    //   2460: aload_3
    //   2461: astore 242
    //   2463: ldc 99
    //   2465: istore 243
    //   2467: aload 242
    //   2469: iload 243
    //   2471: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2474: aload_3
    //   2475: astore 244
    //   2477: aconst_null
    //   2478: astore 245
    //   2480: aload 244
    //   2482: aload 245
    //   2484: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2487: goto -2487 -> 0
    //   2490: aload_3
    //   2491: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2494: checkcast 70	[Ljava/lang/Object;
    //   2497: astore 4
    //   2499: aload 4
    //   2501: ifnull +8 -> 2509
    //   2504: aload 4
    //   2506: arraylength
    //   2507: istore 246
    //   2509: aload_0
    //   2510: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2513: astore 247
    //   2515: aload 4
    //   2517: iconst_0
    //   2518: aaload
    //   2519: checkcast 77	java/lang/String
    //   2522: astore 248
    //   2524: aload 4
    //   2526: iconst_1
    //   2527: aaload
    //   2528: checkcast 72	java/lang/Integer
    //   2531: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2534: istore 249
    //   2536: aload 247
    //   2538: aload 248
    //   2540: iload 249
    //   2542: invokevirtual 167	com/yingyonghui/market/online/MarketServiceAgent:getNewsImage	(Ljava/lang/String;I)Lcom/yingyonghui/market/model/Image2;
    //   2545: astore 250
    //   2547: aload_3
    //   2548: astore 251
    //   2550: ldc 84
    //   2552: istore 252
    //   2554: aload 251
    //   2556: iload 252
    //   2558: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2561: aload_3
    //   2562: astore 253
    //   2564: aload 250
    //   2566: astore 254
    //   2568: aload 253
    //   2570: aload 254
    //   2572: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2575: goto -2575 -> 0
    //   2578: astore 255
    //   2580: aload_3
    //   2581: wide
    //   2585: ldc 99
    //   2587: wide
    //   2591: wide
    //   2595: wide
    //   2599: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2602: aload_3
    //   2603: wide
    //   2607: aconst_null
    //   2608: wide
    //   2612: wide
    //   2616: wide
    //   2620: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2623: goto -2623 -> 0
    //   2626: aload_3
    //   2627: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2630: checkcast 70	[Ljava/lang/Object;
    //   2633: astore 4
    //   2635: aload 4
    //   2637: ifnull +10 -> 2647
    //   2640: aload 4
    //   2642: arraylength
    //   2643: wide
    //   2647: aload_0
    //   2648: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2651: wide
    //   2655: aload 4
    //   2657: iconst_0
    //   2658: aaload
    //   2659: checkcast 72	java/lang/Integer
    //   2662: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2665: wide
    //   2669: wide
    //   2673: wide
    //   2677: ldc 169
    //   2679: invokevirtual 173	com/yingyonghui/market/online/MarketServiceAgent:getAppDetail	(ILjava/lang/String;)Lcom/yingyonghui/market/model/AssetDetail;
    //   2682: wide
    //   2686: aload_3
    //   2687: wide
    //   2691: ldc 84
    //   2693: wide
    //   2697: wide
    //   2701: wide
    //   2705: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2708: iconst_2
    //   2709: anewarray 175	java/lang/Object
    //   2712: wide
    //   2716: wide
    //   2720: iconst_0
    //   2721: wide
    //   2725: aastore
    //   2726: aload 4
    //   2728: iconst_1
    //   2729: aaload
    //   2730: wide
    //   2734: wide
    //   2738: iconst_1
    //   2739: wide
    //   2743: aastore
    //   2744: aload_3
    //   2745: wide
    //   2749: wide
    //   2753: wide
    //   2757: wide
    //   2761: wide
    //   2765: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2768: goto -2768 -> 0
    //   2771: wide
    //   2775: aload_3
    //   2776: wide
    //   2780: ldc 99
    //   2782: wide
    //   2786: wide
    //   2790: wide
    //   2794: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2797: aload_3
    //   2798: wide
    //   2802: aconst_null
    //   2803: wide
    //   2807: wide
    //   2811: wide
    //   2815: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2818: goto -2818 -> 0
    //   2821: aload_3
    //   2822: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   2825: checkcast 70	[Ljava/lang/Object;
    //   2828: astore 4
    //   2830: aload 4
    //   2832: ifnull +10 -> 2842
    //   2835: aload 4
    //   2837: arraylength
    //   2838: wide
    //   2842: aload_0
    //   2843: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   2846: wide
    //   2850: aload 4
    //   2852: iconst_0
    //   2853: aaload
    //   2854: checkcast 72	java/lang/Integer
    //   2857: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2860: wide
    //   2864: wide
    //   2868: wide
    //   2872: invokevirtual 179	com/yingyonghui/market/online/MarketServiceAgent:getAppIcon2	(I)Lcom/yingyonghui/market/model/Image2;
    //   2875: wide
    //   2879: aload 4
    //   2881: iconst_1
    //   2882: aaload
    //   2883: checkcast 72	java/lang/Integer
    //   2886: invokevirtual 75	java/lang/Integer:intValue	()I
    //   2889: wide
    //   2893: wide
    //   2897: wide
    //   2901: putfield 184	com/yingyonghui/market/model/Image2:_id	I
    //   2904: aload_3
    //   2905: wide
    //   2909: ldc 84
    //   2911: wide
    //   2915: wide
    //   2919: wide
    //   2923: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2926: aload_3
    //   2927: wide
    //   2931: wide
    //   2935: wide
    //   2939: wide
    //   2943: wide
    //   2947: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   2950: goto -2950 -> 0
    //   2953: wide
    //   2957: aload_3
    //   2958: wide
    //   2962: ldc 99
    //   2964: wide
    //   2968: wide
    //   2972: wide
    //   2976: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   2979: aload_3
    //   2980: wide
    //   2984: aconst_null
    //   2985: wide
    //   2989: wide
    //   2993: wide
    //   2997: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3000: goto -3000 -> 0
    //   3003: aload_3
    //   3004: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3007: checkcast 70	[Ljava/lang/Object;
    //   3010: astore 4
    //   3012: aload 4
    //   3014: ifnull +10 -> 3024
    //   3017: aload 4
    //   3019: arraylength
    //   3020: wide
    //   3024: aload_0
    //   3025: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3028: wide
    //   3032: aload 4
    //   3034: iconst_0
    //   3035: aaload
    //   3036: checkcast 72	java/lang/Integer
    //   3039: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3042: wide
    //   3046: aload 4
    //   3048: iconst_1
    //   3049: aaload
    //   3050: checkcast 72	java/lang/Integer
    //   3053: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3056: wide
    //   3060: wide
    //   3064: wide
    //   3068: wide
    //   3072: invokevirtual 188	com/yingyonghui/market/online/MarketServiceAgent:getNewsList	(II)Ljava/util/ArrayList;
    //   3075: wide
    //   3079: aload_3
    //   3080: wide
    //   3084: ldc 84
    //   3086: wide
    //   3090: wide
    //   3094: wide
    //   3098: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3101: aload_3
    //   3102: wide
    //   3106: wide
    //   3110: wide
    //   3114: wide
    //   3118: wide
    //   3122: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3125: goto -3125 -> 0
    //   3128: wide
    //   3132: aload_3
    //   3133: wide
    //   3137: ldc 99
    //   3139: wide
    //   3143: wide
    //   3147: wide
    //   3151: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3154: aload_3
    //   3155: wide
    //   3159: aconst_null
    //   3160: wide
    //   3164: wide
    //   3168: wide
    //   3172: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3175: goto -3175 -> 0
    //   3178: aload_3
    //   3179: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3182: checkcast 70	[Ljava/lang/Object;
    //   3185: astore 4
    //   3187: aload 4
    //   3189: ifnull +10 -> 3199
    //   3192: aload 4
    //   3194: arraylength
    //   3195: wide
    //   3199: aload_0
    //   3200: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3203: wide
    //   3207: aload 4
    //   3209: iconst_0
    //   3210: aaload
    //   3211: checkcast 72	java/lang/Integer
    //   3214: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3217: wide
    //   3221: aload 4
    //   3223: iconst_1
    //   3224: aaload
    //   3225: checkcast 72	java/lang/Integer
    //   3228: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3231: wide
    //   3235: aload 4
    //   3237: iconst_2
    //   3238: aaload
    //   3239: checkcast 72	java/lang/Integer
    //   3242: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3245: wide
    //   3249: aload 4
    //   3251: iconst_3
    //   3252: aaload
    //   3253: checkcast 72	java/lang/Integer
    //   3256: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3259: wide
    //   3263: wide
    //   3267: wide
    //   3271: wide
    //   3275: wide
    //   3279: wide
    //   3283: invokevirtual 110	com/yingyonghui/market/online/MarketServiceAgent:getAppList	(IIII)Ljava/util/ArrayList;
    //   3286: wide
    //   3290: aload_3
    //   3291: wide
    //   3295: ldc 84
    //   3297: wide
    //   3301: wide
    //   3305: wide
    //   3309: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3312: aload_3
    //   3313: wide
    //   3317: wide
    //   3321: wide
    //   3325: wide
    //   3329: wide
    //   3333: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3336: goto -3336 -> 0
    //   3339: wide
    //   3343: aload_3
    //   3344: wide
    //   3348: ldc 99
    //   3350: wide
    //   3354: wide
    //   3358: wide
    //   3362: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3365: aload_3
    //   3366: wide
    //   3370: aconst_null
    //   3371: wide
    //   3375: wide
    //   3379: wide
    //   3383: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3386: goto -3386 -> 0
    //   3389: aload_3
    //   3390: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3393: checkcast 72	java/lang/Integer
    //   3396: wide
    //   3400: aload_0
    //   3401: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3404: wide
    //   3408: wide
    //   3412: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3415: wide
    //   3419: wide
    //   3423: wide
    //   3427: invokevirtual 179	com/yingyonghui/market/online/MarketServiceAgent:getAppIcon2	(I)Lcom/yingyonghui/market/model/Image2;
    //   3430: wide
    //   3434: aload_3
    //   3435: wide
    //   3439: ldc 84
    //   3441: wide
    //   3445: wide
    //   3449: wide
    //   3453: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3456: aload_3
    //   3457: wide
    //   3461: wide
    //   3465: wide
    //   3469: wide
    //   3473: wide
    //   3477: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3480: goto -3480 -> 0
    //   3483: wide
    //   3487: aload_3
    //   3488: wide
    //   3492: ldc 99
    //   3494: wide
    //   3498: wide
    //   3502: wide
    //   3506: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3509: aload_3
    //   3510: wide
    //   3514: aconst_null
    //   3515: wide
    //   3519: wide
    //   3523: wide
    //   3527: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3530: goto -3530 -> 0
    //   3533: aload_3
    //   3534: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3537: checkcast 77	java/lang/String
    //   3540: wide
    //   3544: aload_0
    //   3545: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3548: wide
    //   3552: wide
    //   3556: wide
    //   3560: wide
    //   3564: wide
    //   3568: invokevirtual 192	com/yingyonghui/market/online/MarketServiceAgent:getThumb	(Ljava/lang/String;)Lcom/yingyonghui/market/model/ImageURL;
    //   3571: wide
    //   3575: aload_3
    //   3576: wide
    //   3580: ldc 84
    //   3582: wide
    //   3586: wide
    //   3590: wide
    //   3594: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3597: aload_3
    //   3598: wide
    //   3602: wide
    //   3606: wide
    //   3610: wide
    //   3614: wide
    //   3618: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3621: goto -3621 -> 0
    //   3624: wide
    //   3628: aload_3
    //   3629: wide
    //   3633: ldc 99
    //   3635: wide
    //   3639: wide
    //   3643: wide
    //   3647: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3650: aload_3
    //   3651: wide
    //   3655: aconst_null
    //   3656: wide
    //   3660: wide
    //   3664: wide
    //   3668: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3671: goto -3671 -> 0
    //   3674: aload_3
    //   3675: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3678: checkcast 70	[Ljava/lang/Object;
    //   3681: wide
    //   3685: aload_0
    //   3686: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3689: wide
    //   3693: wide
    //   3697: iconst_0
    //   3698: aaload
    //   3699: checkcast 72	java/lang/Integer
    //   3702: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3705: wide
    //   3709: wide
    //   3713: iconst_1
    //   3714: aaload
    //   3715: checkcast 77	java/lang/String
    //   3718: wide
    //   3722: wide
    //   3726: wide
    //   3730: wide
    //   3734: invokevirtual 173	com/yingyonghui/market/online/MarketServiceAgent:getAppDetail	(ILjava/lang/String;)Lcom/yingyonghui/market/model/AssetDetail;
    //   3737: wide
    //   3741: aload_3
    //   3742: wide
    //   3746: ldc 84
    //   3748: wide
    //   3752: wide
    //   3756: wide
    //   3760: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3763: aload_3
    //   3764: wide
    //   3768: wide
    //   3772: wide
    //   3776: wide
    //   3780: wide
    //   3784: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3787: goto -3787 -> 0
    //   3790: wide
    //   3794: aload_3
    //   3795: wide
    //   3799: ldc 99
    //   3801: wide
    //   3805: wide
    //   3809: wide
    //   3813: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3816: aload_3
    //   3817: wide
    //   3821: aconst_null
    //   3822: wide
    //   3826: wide
    //   3830: wide
    //   3834: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3837: goto -3837 -> 0
    //   3840: aload_0
    //   3841: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3844: invokevirtual 196	com/yingyonghui/market/online/MarketServiceAgent:getTestUser	()Lcom/yingyonghui/market/model/TestUser;
    //   3847: wide
    //   3851: aload_3
    //   3852: wide
    //   3856: ldc 84
    //   3858: wide
    //   3862: wide
    //   3866: wide
    //   3870: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3873: aload_3
    //   3874: wide
    //   3878: wide
    //   3882: wide
    //   3886: wide
    //   3890: wide
    //   3894: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3897: goto -3897 -> 0
    //   3900: wide
    //   3904: aload_3
    //   3905: wide
    //   3909: ldc 99
    //   3911: wide
    //   3915: wide
    //   3919: wide
    //   3923: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   3926: aload_3
    //   3927: wide
    //   3931: aconst_null
    //   3932: wide
    //   3936: wide
    //   3940: wide
    //   3944: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   3947: goto -3947 -> 0
    //   3950: aload_3
    //   3951: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   3954: checkcast 72	java/lang/Integer
    //   3957: wide
    //   3961: aload_0
    //   3962: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   3965: wide
    //   3969: wide
    //   3973: invokevirtual 75	java/lang/Integer:intValue	()I
    //   3976: wide
    //   3980: wide
    //   3984: wide
    //   3988: invokevirtual 200	com/yingyonghui/market/online/MarketServiceAgent:getAppScreenShorts	(I)Ljava/util/ArrayList;
    //   3991: wide
    //   3995: aload_3
    //   3996: wide
    //   4000: ldc 84
    //   4002: wide
    //   4006: wide
    //   4010: wide
    //   4014: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   4017: aload_3
    //   4018: wide
    //   4022: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   4025: goto -4025 -> 0
    //   4028: wide
    //   4032: aload_3
    //   4033: wide
    //   4037: ldc 99
    //   4039: wide
    //   4043: wide
    //   4047: wide
    //   4051: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   4054: aload_3
    //   4055: wide
    //   4059: aconst_null
    //   4060: wide
    //   4064: wide
    //   4068: wide
    //   4072: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   4075: goto -4075 -> 0
    //   4078: aload_3
    //   4079: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   4082: checkcast 70	[Ljava/lang/Object;
    //   4085: astore 4
    //   4087: aload 4
    //   4089: ifnull -4089 -> 0
    //   4092: aload 4
    //   4094: arraylength
    //   4095: iconst_3
    //   4096: if_icmpne +76 -> 4172
    //   4099: aload_0
    //   4100: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4103: wide
    //   4107: aload 4
    //   4109: iconst_0
    //   4110: aaload
    //   4111: checkcast 77	java/lang/String
    //   4114: wide
    //   4118: aload 4
    //   4120: iconst_1
    //   4121: aaload
    //   4122: checkcast 77	java/lang/String
    //   4125: wide
    //   4129: aload 4
    //   4131: iconst_2
    //   4132: aaload
    //   4133: checkcast 72	java/lang/Integer
    //   4136: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4139: wide
    //   4143: wide
    //   4147: wide
    //   4151: wide
    //   4155: wide
    //   4159: invokevirtual 204	com/yingyonghui/market/online/MarketServiceAgent:sendInstallUpdateLog	(Ljava/lang/String;Ljava/lang/String;I)V
    //   4162: goto -4162 -> 0
    //   4165: wide
    //   4169: goto -4169 -> 0
    //   4172: aload 4
    //   4174: arraylength
    //   4175: bipush 6
    //   4177: if_icmpne +123 -> 4300
    //   4180: aload_0
    //   4181: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4184: wide
    //   4188: aload 4
    //   4190: iconst_0
    //   4191: aaload
    //   4192: checkcast 77	java/lang/String
    //   4195: wide
    //   4199: aload 4
    //   4201: iconst_1
    //   4202: aaload
    //   4203: checkcast 77	java/lang/String
    //   4206: wide
    //   4210: aload 4
    //   4212: iconst_2
    //   4213: aaload
    //   4214: checkcast 72	java/lang/Integer
    //   4217: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4220: wide
    //   4224: aload 4
    //   4226: iconst_3
    //   4227: aaload
    //   4228: checkcast 72	java/lang/Integer
    //   4231: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4234: wide
    //   4238: aload 4
    //   4240: iconst_4
    //   4241: aaload
    //   4242: checkcast 72	java/lang/Integer
    //   4245: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4248: wide
    //   4252: aload 4
    //   4254: iconst_5
    //   4255: aaload
    //   4256: checkcast 72	java/lang/Integer
    //   4259: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4262: wide
    //   4266: wide
    //   4270: wide
    //   4274: wide
    //   4278: wide
    //   4282: wide
    //   4286: wide
    //   4290: wide
    //   4294: invokevirtual 207	com/yingyonghui/market/online/MarketServiceAgent:sendInstallUpdateLog	(Ljava/lang/String;Ljava/lang/String;IIII)V
    //   4297: goto -4297 -> 0
    //   4300: aload 4
    //   4302: arraylength
    //   4303: bipush 7
    //   4305: if_icmpne -4305 -> 0
    //   4308: aload_0
    //   4309: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4312: wide
    //   4316: aload 4
    //   4318: iconst_0
    //   4319: aaload
    //   4320: checkcast 77	java/lang/String
    //   4323: wide
    //   4327: aload 4
    //   4329: iconst_1
    //   4330: aaload
    //   4331: checkcast 77	java/lang/String
    //   4334: wide
    //   4338: aload 4
    //   4340: iconst_2
    //   4341: aaload
    //   4342: checkcast 72	java/lang/Integer
    //   4345: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4348: wide
    //   4352: aload 4
    //   4354: iconst_3
    //   4355: aaload
    //   4356: checkcast 72	java/lang/Integer
    //   4359: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4362: wide
    //   4366: aload 4
    //   4368: iconst_4
    //   4369: aaload
    //   4370: checkcast 72	java/lang/Integer
    //   4373: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4376: wide
    //   4380: aload 4
    //   4382: iconst_5
    //   4383: aaload
    //   4384: checkcast 72	java/lang/Integer
    //   4387: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4390: wide
    //   4394: aload 4
    //   4396: bipush 6
    //   4398: aaload
    //   4399: checkcast 77	java/lang/String
    //   4402: wide
    //   4406: wide
    //   4410: wide
    //   4414: wide
    //   4418: wide
    //   4422: wide
    //   4426: wide
    //   4430: wide
    //   4434: wide
    //   4438: invokevirtual 210	com/yingyonghui/market/online/MarketServiceAgent:sendInstallUpdateLog	(Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;)V
    //   4441: goto -4441 -> 0
    //   4444: aload_3
    //   4445: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   4448: checkcast 70	[Ljava/lang/Object;
    //   4451: astore 4
    //   4453: aload 4
    //   4455: ifnull -4455 -> 0
    //   4458: aload 4
    //   4460: arraylength
    //   4461: iconst_1
    //   4462: if_icmpne +43 -> 4505
    //   4465: aload_0
    //   4466: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4469: wide
    //   4473: aload 4
    //   4475: iconst_0
    //   4476: aaload
    //   4477: checkcast 77	java/lang/String
    //   4480: wide
    //   4484: wide
    //   4488: wide
    //   4492: invokevirtual 214	com/yingyonghui/market/online/MarketServiceAgent:sendPageViewLog	(Ljava/lang/String;)V
    //   4495: goto -4495 -> 0
    //   4498: wide
    //   4502: goto -4502 -> 0
    //   4505: aload 4
    //   4507: arraylength
    //   4508: iconst_2
    //   4509: if_icmpne +54 -> 4563
    //   4512: aload_0
    //   4513: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4516: wide
    //   4520: aload 4
    //   4522: iconst_0
    //   4523: aaload
    //   4524: checkcast 77	java/lang/String
    //   4527: wide
    //   4531: aload 4
    //   4533: iconst_1
    //   4534: aaload
    //   4535: checkcast 72	java/lang/Integer
    //   4538: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4541: wide
    //   4545: wide
    //   4549: wide
    //   4553: wide
    //   4557: invokevirtual 217	com/yingyonghui/market/online/MarketServiceAgent:sendPageViewLog	(Ljava/lang/String;I)V
    //   4560: goto -4560 -> 0
    //   4563: aload 4
    //   4565: arraylength
    //   4566: iconst_5
    //   4567: if_icmpne +108 -> 4675
    //   4570: aload_0
    //   4571: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4574: wide
    //   4578: aload 4
    //   4580: iconst_0
    //   4581: aaload
    //   4582: checkcast 77	java/lang/String
    //   4585: wide
    //   4589: aload 4
    //   4591: iconst_1
    //   4592: aaload
    //   4593: checkcast 72	java/lang/Integer
    //   4596: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4599: wide
    //   4603: aload 4
    //   4605: iconst_2
    //   4606: aaload
    //   4607: checkcast 72	java/lang/Integer
    //   4610: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4613: wide
    //   4617: aload 4
    //   4619: iconst_3
    //   4620: aaload
    //   4621: checkcast 72	java/lang/Integer
    //   4624: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4627: wide
    //   4631: aload 4
    //   4633: iconst_4
    //   4634: aaload
    //   4635: checkcast 72	java/lang/Integer
    //   4638: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4641: wide
    //   4645: wide
    //   4649: wide
    //   4653: wide
    //   4657: wide
    //   4661: wide
    //   4665: wide
    //   4669: invokevirtual 220	com/yingyonghui/market/online/MarketServiceAgent:sendPageViewLog	(Ljava/lang/String;IIII)V
    //   4672: goto -4672 -> 0
    //   4675: aload 4
    //   4677: arraylength
    //   4678: bipush 6
    //   4680: if_icmpne -4680 -> 0
    //   4683: aload_0
    //   4684: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4687: wide
    //   4691: aload 4
    //   4693: iconst_0
    //   4694: aaload
    //   4695: checkcast 77	java/lang/String
    //   4698: wide
    //   4702: aload 4
    //   4704: iconst_1
    //   4705: aaload
    //   4706: checkcast 72	java/lang/Integer
    //   4709: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4712: wide
    //   4716: aload 4
    //   4718: iconst_2
    //   4719: aaload
    //   4720: checkcast 72	java/lang/Integer
    //   4723: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4726: wide
    //   4730: aload 4
    //   4732: iconst_3
    //   4733: aaload
    //   4734: checkcast 72	java/lang/Integer
    //   4737: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4740: wide
    //   4744: aload 4
    //   4746: iconst_4
    //   4747: aaload
    //   4748: checkcast 72	java/lang/Integer
    //   4751: invokevirtual 75	java/lang/Integer:intValue	()I
    //   4754: wide
    //   4758: aload 4
    //   4760: iconst_5
    //   4761: aaload
    //   4762: checkcast 77	java/lang/String
    //   4765: wide
    //   4769: wide
    //   4773: wide
    //   4777: wide
    //   4781: wide
    //   4785: wide
    //   4789: wide
    //   4793: wide
    //   4797: invokevirtual 223	com/yingyonghui/market/online/MarketServiceAgent:sendPageViewLog	(Ljava/lang/String;IIIILjava/lang/String;)V
    //   4800: goto -4800 -> 0
    //   4803: aload_3
    //   4804: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   4807: checkcast 70	[Ljava/lang/Object;
    //   4810: astore 4
    //   4812: aload 4
    //   4814: ifnull -4814 -> 0
    //   4817: aload 4
    //   4819: arraylength
    //   4820: iconst_2
    //   4821: if_icmpne -4821 -> 0
    //   4824: aload_0
    //   4825: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4828: wide
    //   4832: aload 4
    //   4834: iconst_0
    //   4835: aaload
    //   4836: checkcast 77	java/lang/String
    //   4839: wide
    //   4843: aload 4
    //   4845: iconst_1
    //   4846: aaload
    //   4847: checkcast 72	java/lang/Integer
    //   4850: wide
    //   4854: wide
    //   4858: wide
    //   4862: wide
    //   4866: invokevirtual 227	com/yingyonghui/market/online/MarketServiceAgent:sendNewsPageViewLog	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   4869: goto -4869 -> 0
    //   4872: wide
    //   4876: goto -4876 -> 0
    //   4879: aload_3
    //   4880: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   4883: checkcast 70	[Ljava/lang/Object;
    //   4886: astore 4
    //   4888: aload 4
    //   4890: ifnull +10 -> 4900
    //   4893: aload 4
    //   4895: arraylength
    //   4896: wide
    //   4900: aload_0
    //   4901: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4904: wide
    //   4908: aload 4
    //   4910: iconst_0
    //   4911: aaload
    //   4912: checkcast 77	java/lang/String
    //   4915: wide
    //   4919: aload 4
    //   4921: iconst_1
    //   4922: aaload
    //   4923: checkcast 77	java/lang/String
    //   4926: wide
    //   4930: wide
    //   4934: wide
    //   4938: wide
    //   4942: invokevirtual 231	com/yingyonghui/market/online/MarketServiceAgent:sendSearchLog	(Ljava/lang/String;Ljava/lang/String;)V
    //   4945: goto -4945 -> 0
    //   4948: wide
    //   4952: goto -4952 -> 0
    //   4955: aload_0
    //   4956: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4959: invokevirtual 234	com/yingyonghui/market/online/MarketServiceAgent:sendWidgetEnableLog	()V
    //   4962: goto -4962 -> 0
    //   4965: wide
    //   4969: goto -4969 -> 0
    //   4972: aload_0
    //   4973: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   4976: invokevirtual 237	com/yingyonghui/market/online/MarketServiceAgent:sendWidgetDisableLog	()V
    //   4979: goto -4979 -> 0
    //   4982: wide
    //   4986: goto -4986 -> 0
    //   4989: aload_3
    //   4990: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   4993: checkcast 70	[Ljava/lang/Object;
    //   4996: astore 4
    //   4998: aload 4
    //   5000: ifnull +10 -> 5010
    //   5003: aload 4
    //   5005: arraylength
    //   5006: wide
    //   5010: aload_0
    //   5011: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   5014: wide
    //   5018: aload 4
    //   5020: iconst_0
    //   5021: aaload
    //   5022: checkcast 77	java/lang/String
    //   5025: wide
    //   5029: wide
    //   5033: wide
    //   5037: invokevirtual 241	com/yingyonghui/market/online/MarketServiceAgent:sendInstallLog	(Ljava/lang/String;)I
    //   5040: wide
    //   5044: sipush 200
    //   5047: wide
    //   5051: wide
    //   5055: wide
    //   5059: if_icmpne +82 -> 5141
    //   5062: aload_3
    //   5063: wide
    //   5067: ldc 84
    //   5069: wide
    //   5073: wide
    //   5077: wide
    //   5081: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5084: aload_3
    //   5085: invokevirtual 243	com/yingyonghui/market/online/Request:notifyObservers	()V
    //   5088: goto -5088 -> 0
    //   5091: wide
    //   5095: aload_3
    //   5096: wide
    //   5100: ldc 99
    //   5102: wide
    //   5106: wide
    //   5110: wide
    //   5114: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5117: aload_3
    //   5118: wide
    //   5122: aconst_null
    //   5123: wide
    //   5127: wide
    //   5131: wide
    //   5135: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   5138: goto -5138 -> 0
    //   5141: ldc 244
    //   5143: wide
    //   5147: aload_3
    //   5148: wide
    //   5152: wide
    //   5156: wide
    //   5160: wide
    //   5164: wide
    //   5168: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5171: goto -87 -> 5084
    //   5174: aload_3
    //   5175: invokevirtual 68	com/yingyonghui/market/online/Request:getData	()Ljava/lang/Object;
    //   5178: checkcast 70	[Ljava/lang/Object;
    //   5181: astore 4
    //   5183: aload 4
    //   5185: iconst_1
    //   5186: aaload
    //   5187: checkcast 72	java/lang/Integer
    //   5190: invokevirtual 75	java/lang/Integer:intValue	()I
    //   5193: wide
    //   5197: ldc 27
    //   5199: wide
    //   5203: aload 4
    //   5205: arraylength
    //   5206: iconst_5
    //   5207: if_icmpne +17 -> 5224
    //   5210: aload 4
    //   5212: iconst_4
    //   5213: aaload
    //   5214: checkcast 90	java/lang/Boolean
    //   5217: invokevirtual 248	java/lang/Boolean:booleanValue	()Z
    //   5220: wide
    //   5224: aload_0
    //   5225: getfield 22	com/yingyonghui/market/online/RequestEventHandler:service	Lcom/yingyonghui/market/online/MarketService;
    //   5228: getfield 39	com/yingyonghui/market/online/MarketService:mContext	Landroid/content/Context;
    //   5231: ldc 250
    //   5233: iconst_0
    //   5234: invokevirtual 256	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   5237: wide
    //   5241: wide
    //   5245: wide
    //   5249: ldc_w 258
    //   5252: wide
    //   5256: ldc 27
    //   5258: wide
    //   5262: wide
    //   5266: wide
    //   5270: wide
    //   5274: invokeinterface 264 3 0
    //   5279: wide
    //   5283: wide
    //   5287: wide
    //   5291: ldc_w 266
    //   5294: wide
    //   5298: ldc_w 268
    //   5301: wide
    //   5305: wide
    //   5309: wide
    //   5313: wide
    //   5317: invokeinterface 272 3 0
    //   5322: invokestatic 275	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   5325: wide
    //   5329: aload_0
    //   5330: getfield 22	com/yingyonghui/market/online/RequestEventHandler:service	Lcom/yingyonghui/market/online/MarketService;
    //   5333: getfield 39	com/yingyonghui/market/online/MarketService:mContext	Landroid/content/Context;
    //   5336: ldc_w 277
    //   5339: invokevirtual 281	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   5342: checkcast 283	android/net/wifi/WifiManager
    //   5345: invokevirtual 286	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   5348: ifeq +193 -> 5541
    //   5351: iconst_1
    //   5352: wide
    //   5356: wide
    //   5360: ifeq +452 -> 5812
    //   5363: wide
    //   5367: ifeq +379 -> 5746
    //   5370: wide
    //   5374: ifeq +175 -> 5549
    //   5377: iconst_1
    //   5378: wide
    //   5382: wide
    //   5386: ifeq -5386 -> 0
    //   5389: getstatic 289	com/yingyonghui/market/online/DownloadService:flagCancelling	Z
    //   5392: ifne +428 -> 5820
    //   5395: aload_0
    //   5396: getfield 47	com/yingyonghui/market/online/RequestEventHandler:mDownloadService	Lcom/yingyonghui/market/online/DownloadService;
    //   5399: wide
    //   5403: aload 4
    //   5405: iconst_0
    //   5406: aaload
    //   5407: checkcast 72	java/lang/Integer
    //   5410: invokevirtual 75	java/lang/Integer:intValue	()I
    //   5413: wide
    //   5417: aload_0
    //   5418: getfield 24	com/yingyonghui/market/online/RequestEventHandler:agent	Lcom/yingyonghui/market/online/MarketServiceAgent;
    //   5421: wide
    //   5425: aload 4
    //   5427: iconst_1
    //   5428: aaload
    //   5429: checkcast 72	java/lang/Integer
    //   5432: invokevirtual 75	java/lang/Integer:intValue	()I
    //   5435: wide
    //   5439: aload 4
    //   5441: iconst_2
    //   5442: aaload
    //   5443: checkcast 77	java/lang/String
    //   5446: wide
    //   5450: aload 4
    //   5452: iconst_3
    //   5453: aaload
    //   5454: checkcast 77	java/lang/String
    //   5457: wide
    //   5461: wide
    //   5465: wide
    //   5469: wide
    //   5473: wide
    //   5477: wide
    //   5481: wide
    //   5485: invokevirtual 293	com/yingyonghui/market/online/DownloadService:newTask	(ILcom/yingyonghui/market/online/MarketServiceAgent;ILjava/lang/String;Ljava/lang/String;)V
    //   5488: goto -5488 -> 0
    //   5491: wide
    //   5495: aload_3
    //   5496: wide
    //   5500: ldc 99
    //   5502: wide
    //   5506: wide
    //   5510: wide
    //   5514: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5517: aload_3
    //   5518: wide
    //   5522: aconst_null
    //   5523: wide
    //   5527: wide
    //   5531: wide
    //   5535: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   5538: goto -5538 -> 0
    //   5541: iconst_0
    //   5542: wide
    //   5546: goto -190 -> 5356
    //   5549: wide
    //   5553: sipush 1024
    //   5556: imul
    //   5557: sipush 1024
    //   5560: imul
    //   5561: wide
    //   5565: wide
    //   5569: wide
    //   5573: wide
    //   5577: wide
    //   5581: wide
    //   5585: wide
    //   5589: if_icmple +149 -> 5738
    //   5592: iconst_0
    //   5593: wide
    //   5597: aload_3
    //   5598: wide
    //   5602: bipush 11
    //   5604: wide
    //   5608: wide
    //   5612: wide
    //   5616: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5619: iconst_4
    //   5620: anewarray 175	java/lang/Object
    //   5623: wide
    //   5627: aload 4
    //   5629: iconst_0
    //   5630: aaload
    //   5631: checkcast 72	java/lang/Integer
    //   5634: wide
    //   5638: wide
    //   5642: iconst_0
    //   5643: wide
    //   5647: aastore
    //   5648: aload 4
    //   5650: iconst_1
    //   5651: aaload
    //   5652: checkcast 72	java/lang/Integer
    //   5655: wide
    //   5659: wide
    //   5663: iconst_1
    //   5664: wide
    //   5668: aastore
    //   5669: aload 4
    //   5671: iconst_2
    //   5672: aaload
    //   5673: checkcast 77	java/lang/String
    //   5676: wide
    //   5680: wide
    //   5684: iconst_2
    //   5685: wide
    //   5689: aastore
    //   5690: aload 4
    //   5692: iconst_3
    //   5693: aaload
    //   5694: checkcast 77	java/lang/String
    //   5697: wide
    //   5701: wide
    //   5705: iconst_3
    //   5706: wide
    //   5710: aastore
    //   5711: aload_3
    //   5712: wide
    //   5716: wide
    //   5720: wide
    //   5724: wide
    //   5728: wide
    //   5732: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   5735: goto -353 -> 5382
    //   5738: iconst_1
    //   5739: wide
    //   5743: goto -361 -> 5382
    //   5746: wide
    //   5750: wide
    //   5754: wide
    //   5758: ifne -376 -> 5382
    //   5761: iconst_0
    //   5762: wide
    //   5766: aload_3
    //   5767: wide
    //   5771: bipush 10
    //   5773: wide
    //   5777: wide
    //   5781: wide
    //   5785: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5788: aload_3
    //   5789: wide
    //   5793: aconst_null
    //   5794: wide
    //   5798: wide
    //   5802: wide
    //   5806: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   5809: goto -427 -> 5382
    //   5812: iconst_1
    //   5813: wide
    //   5817: goto -435 -> 5382
    //   5820: aload_3
    //   5821: wide
    //   5825: bipush 14
    //   5827: wide
    //   5831: wide
    //   5835: wide
    //   5839: invokevirtual 88	com/yingyonghui/market/online/Request:setStatus	(I)V
    //   5842: aload_3
    //   5843: wide
    //   5847: aconst_null
    //   5848: wide
    //   5852: wide
    //   5856: wide
    //   5860: invokevirtual 98	com/yingyonghui/market/online/Request:notifyObservers	(Ljava/lang/Object;)V
    //   5863: goto -5863 -> 0
    //
    // Exception table:
    //   from	to	target	type
    //   311	414	417	java/net/SocketException
    //   8	27	449	java/lang/InterruptedException
    //   455	514	517	java/net/SocketException
    //   549	665	668	java/net/SocketException
    //   700	816	819	java/net/SocketException
    //   851	950	953	java/net/SocketException
    //   985	1084	1087	java/net/SocketException
    //   1119	1232	1235	java/net/SocketException
    //   1267	1359	1362	java/net/SocketException
    //   1394	1518	1521	java/net/SocketException
    //   1553	1655	1658	java/net/SocketException
    //   1690	1817	1820	java/net/SocketException
    //   1852	1993	1996	java/net/SocketException
    //   2028	2184	2187	java/net/SocketException
    //   2219	2349	2352	java/net/SocketException
    //   2384	2455	2458	java/net/SocketException
    //   2490	2575	2578	java/net/SocketException
    //   2626	2768	2771	java/net/SocketException
    //   2821	2950	2953	java/net/SocketException
    //   3003	3125	3128	java/net/SocketException
    //   3178	3336	3339	java/net/SocketException
    //   3389	3480	3483	java/net/SocketException
    //   3533	3621	3624	java/net/SocketException
    //   3674	3787	3790	java/net/SocketException
    //   3840	3897	3900	java/lang/Exception
    //   3950	4025	4028	java/net/SocketException
    //   4078	4441	4165	java/net/SocketException
    //   4444	4800	4498	java/net/SocketException
    //   4803	4869	4872	java/net/SocketException
    //   4879	4945	4948	java/net/SocketException
    //   4955	4962	4965	java/net/SocketException
    //   4972	4979	4982	java/net/SocketException
    //   4989	5088	5091	java/net/SocketException
    //   5147	5171	5091	java/net/SocketException
    //   5395	5488	5491	java/net/SocketException
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.RequestEventHandler
 * JD-Core Version:    0.6.0
 */