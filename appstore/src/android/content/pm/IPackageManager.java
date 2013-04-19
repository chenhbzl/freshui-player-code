package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import dalvik.annotation.Signature;
import java.util.ArrayList;
import java.util.List;

public abstract interface IPackageManager extends IInterface
{
  public abstract void addPackageToPreferred(String paramString)
    throws RemoteException;

  public abstract boolean addPermission(PermissionInfo paramPermissionInfo)
    throws RemoteException;

  public abstract void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    throws RemoteException;

  public abstract int checkPermission(String paramString1, String paramString2)
    throws RemoteException;

  public abstract int checkSignatures(String paramString1, String paramString2)
    throws RemoteException;

  public abstract int checkUidPermission(String paramString, int paramInt)
    throws RemoteException;

  public abstract int checkUidSignatures(int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver)
    throws RemoteException;

  public abstract void clearPackagePreferredActivities(String paramString)
    throws RemoteException;

  public abstract void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver)
    throws RemoteException;

  public abstract void deletePackage(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt)
    throws RemoteException;

  public abstract void enterSafeMode()
    throws RemoteException;

  public abstract void freeStorageAndNotify(long paramLong, IPackageDataObserver paramIPackageDataObserver)
    throws RemoteException;

  public abstract ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws RemoteException;

  @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PermissionGroupInfo;", ">;"})
  public abstract List getAllPermissionGroups(int paramInt)
    throws RemoteException;

  public abstract int getApplicationEnabledSetting(String paramString)
    throws RemoteException;

  public abstract ApplicationInfo getApplicationInfo(String paramString, int paramInt)
    throws RemoteException;

  public abstract int getComponentEnabledSetting(ComponentName paramComponentName)
    throws RemoteException;

  @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/ApplicationInfo;", ">;"})
  public abstract List getInstalledApplications(int paramInt)
    throws RemoteException;

  @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PackageInfo;", ">;"})
  public abstract List getInstalledPackages(int paramInt)
    throws RemoteException;

  public abstract String getInstallerPackageName(String paramString)
    throws RemoteException;

  public abstract InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
    throws RemoteException;

  public abstract String getNameForUid(int paramInt)
    throws RemoteException;

  public abstract int[] getPackageGids(String paramString)
    throws RemoteException;

  public abstract PackageInfo getPackageInfo(String paramString, int paramInt)
    throws RemoteException;

  public abstract void getPackageSizeInfo(String paramString, IPackageStatsObserver paramIPackageStatsObserver)
    throws RemoteException;

  public abstract int getPackageUid(String paramString)
    throws RemoteException;

  public abstract String[] getPackagesForUid(int paramInt)
    throws RemoteException;

  public abstract PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
    throws RemoteException;

  public abstract PermissionInfo getPermissionInfo(String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/ApplicationInfo;", ">;"})
  public abstract List getPersistentApplications(int paramInt)
    throws RemoteException;

  @Signature({"(", "Ljava/util/List", "<", "Landroid/content/IntentFilter;", ">;", "Ljava/util/List", "<", "Landroid/content/ComponentName;", ">;", "Ljava/lang/String;", ")I"})
  public abstract int getPreferredActivities(List paramList1, List paramList2, String paramString)
    throws RemoteException;

  @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PackageInfo;", ">;"})
  public abstract List getPreferredPackages(int paramInt)
    throws RemoteException;

  public abstract ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
    throws RemoteException;

  public abstract ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
    throws RemoteException;

  public abstract String[] getSystemSharedLibraryNames()
    throws RemoteException;

  public abstract int getUidForSharedUser(String paramString)
    throws RemoteException;

  public abstract boolean hasSystemFeature(String paramString)
    throws RemoteException;

  public abstract boolean hasSystemUidErrors()
    throws RemoteException;

  public abstract void installPackage(Uri paramUri, IPackageInstallObserver paramIPackageInstallObserver, int paramInt, String paramString)
    throws RemoteException;

  public abstract boolean isProtectedBroadcast(String paramString)
    throws RemoteException;

  public abstract boolean isSafeMode()
    throws RemoteException;

  public abstract boolean performDexOpt(String paramString)
    throws RemoteException;

  @Signature({"(", "Ljava/lang/String;", "II)", "Ljava/util/List", "<", "Landroid/content/pm/ProviderInfo;", ">;"})
  public abstract List queryContentProviders(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;

  @Signature({"(", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/InstrumentationInfo;", ">;"})
  public abstract List queryInstrumentation(String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
  public abstract List queryIntentActivities(Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Landroid/content/ComponentName;", "[", "Landroid/content/Intent;", "[", "Ljava/lang/String;", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
  public abstract List queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, String[] paramArrayOfString, Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
  public abstract List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
  public abstract List queryIntentServices(Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;"})
  public abstract List queryPermissionsByGroup(String paramString, int paramInt)
    throws RemoteException;

  @Signature({"(", "Ljava/util/List", "<", "Ljava/lang/String;", ">;", "Ljava/util/List", "<", "Landroid/content/pm/ProviderInfo;", ">;)V"})
  public abstract void querySyncProviders(List paramList1, List paramList2)
    throws RemoteException;

  public abstract void removePackageFromPreferred(String paramString)
    throws RemoteException;

  public abstract void removePermission(String paramString)
    throws RemoteException;

  public abstract void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
    throws RemoteException;

  public abstract ProviderInfo resolveContentProvider(String paramString, int paramInt)
    throws RemoteException;

  public abstract ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  public abstract ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt)
    throws RemoteException;

  public abstract void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
    throws RemoteException;

  public abstract void systemReady()
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IPackageManager
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageManager";
    static final int TRANSACTION_addPackageToPreferred = 39;
    static final int TRANSACTION_addPermission = 14;
    static final int TRANSACTION_addPreferredActivity = 42;
    static final int TRANSACTION_checkPermission = 12;
    static final int TRANSACTION_checkSignatures = 17;
    static final int TRANSACTION_checkUidPermission = 13;
    static final int TRANSACTION_checkUidSignatures = 18;
    static final int TRANSACTION_clearApplicationUserData = 52;
    static final int TRANSACTION_clearPackagePreferredActivities = 44;
    static final int TRANSACTION_deleteApplicationCacheFiles = 51;
    static final int TRANSACTION_deletePackage = 37;
    static final int TRANSACTION_enterSafeMode = 56;
    static final int TRANSACTION_freeStorageAndNotify = 50;
    static final int TRANSACTION_getActivityInfo = 9;
    static final int TRANSACTION_getAllPermissionGroups = 7;
    static final int TRANSACTION_getApplicationEnabledSetting = 49;
    static final int TRANSACTION_getApplicationInfo = 8;
    static final int TRANSACTION_getComponentEnabledSetting = 47;
    static final int TRANSACTION_getInstalledApplications = 29;
    static final int TRANSACTION_getInstalledPackages = 28;
    static final int TRANSACTION_getInstallerPackageName = 38;
    static final int TRANSACTION_getInstrumentationInfo = 34;
    static final int TRANSACTION_getNameForUid = 20;
    static final int TRANSACTION_getPackageGids = 3;
    static final int TRANSACTION_getPackageInfo = 1;
    static final int TRANSACTION_getPackageSizeInfo = 53;
    static final int TRANSACTION_getPackageUid = 2;
    static final int TRANSACTION_getPackagesForUid = 19;
    static final int TRANSACTION_getPermissionGroupInfo = 6;
    static final int TRANSACTION_getPermissionInfo = 4;
    static final int TRANSACTION_getPersistentApplications = 30;
    static final int TRANSACTION_getPreferredActivities = 45;
    static final int TRANSACTION_getPreferredPackages = 41;
    static final int TRANSACTION_getReceiverInfo = 10;
    static final int TRANSACTION_getServiceInfo = 11;
    static final int TRANSACTION_getSystemSharedLibraryNames = 54;
    static final int TRANSACTION_getUidForSharedUser = 21;
    static final int TRANSACTION_hasSystemFeature = 55;
    static final int TRANSACTION_hasSystemUidErrors = 59;
    static final int TRANSACTION_installPackage = 36;
    static final int TRANSACTION_isProtectedBroadcast = 16;
    static final int TRANSACTION_isSafeMode = 57;
    static final int TRANSACTION_performDexOpt = 60;
    static final int TRANSACTION_queryContentProviders = 33;
    static final int TRANSACTION_queryInstrumentation = 35;
    static final int TRANSACTION_queryIntentActivities = 23;
    static final int TRANSACTION_queryIntentActivityOptions = 24;
    static final int TRANSACTION_queryIntentReceivers = 25;
    static final int TRANSACTION_queryIntentServices = 27;
    static final int TRANSACTION_queryPermissionsByGroup = 5;
    static final int TRANSACTION_querySyncProviders = 32;
    static final int TRANSACTION_removePackageFromPreferred = 40;
    static final int TRANSACTION_removePermission = 15;
    static final int TRANSACTION_replacePreferredActivity = 43;
    static final int TRANSACTION_resolveContentProvider = 31;
    static final int TRANSACTION_resolveIntent = 22;
    static final int TRANSACTION_resolveService = 26;
    static final int TRANSACTION_setApplicationEnabledSetting = 48;
    static final int TRANSACTION_setComponentEnabledSetting = 46;
    static final int TRANSACTION_systemReady = 58;

    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageManager");
    }

    public static IPackageManager asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageManager");
        if ((localIInterface != null) && ((localIInterface instanceof IPackageManager)))
        {
          localObject = (IPackageManager)localIInterface;
          continue;
        }
        localObject = new Proxy();
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    // ERROR //
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      // Byte code:
      //   0: iload_1
      //   1: lookupswitch	default:+499->500, 1:+533->534, 2:+644->645, 3:+704->705, 4:+764->765, 5:+875->876, 6:+947->948, 7:+1058->1059, 8:+1118->1119, 9:+1229->1230, 10:+1369->1370, 11:+1509->1510, 12:+1649->1650, 13:+1721->1722, 14:+1793->1794, 15:+1896->1897, 16:+1940->1941, 17:+2014->2015, 18:+2086->2087, 19:+2158->2159, 20:+2218->2219, 21:+2278->2279, 22:+2338->2339, 23:+2490->2491, 24:+2623->2624, 25:+2861->2862, 26:+3026->3027, 27:+3246->3247, 28:+3411->3412, 29:+3503->3504, 30:+3595->3596, 31:+3687->3688, 32:+3854->3855, 33:+4013->4014, 34:+4145->4146, 35:+4345->4346, 36:+4457->4458, 37:+4617->4618, 38:+4724->4725, 39:+4816->4817, 40:+4880->4881, 41:+4944->4945, 42:+5036->5037, 43:+5262->5263, 44:+5488->5489, 45:+5552->5553, 46:+5738->5739, 47:+5875->5876, 48:+6000->6001, 49:+6104->6105, 50:+6196->6197, 51:+6283->6284, 52:+6370->6371, 53:+6457->6458, 54:+6544->6545, 55:+6608->6609, 56:+6714->6715, 57:+6750->6751, 58:+6828->6829, 59:+6864->6865, 60:+6942->6943, 1598968902:+513->514
      //   501: iload_1
      //   502: aload_2
      //   503: aload_3
      //   504: iload 4
      //   506: invokevirtual 163	android/os/Binder:onTransact	(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z
      //   509: istore 5
      //   511: iload 5
      //   513: ireturn
      //   514: aload_3
      //   515: astore 6
      //   517: ldc 15
      //   519: astore 7
      //   521: aload 6
      //   523: aload 7
      //   525: invokevirtual 169	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   528: iconst_1
      //   529: istore 5
      //   531: goto -20 -> 511
      //   534: aload_2
      //   535: astore 8
      //   537: ldc 15
      //   539: astore 9
      //   541: aload 8
      //   543: aload 9
      //   545: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   548: aload_2
      //   549: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   552: astore 10
      //   554: aload_2
      //   555: invokevirtual 180	android/os/Parcel:readInt	()I
      //   558: istore 11
      //   560: aload_0
      //   561: astore 12
      //   563: aload 10
      //   565: astore 13
      //   567: iload 11
      //   569: istore 14
      //   571: aload 12
      //   573: aload 13
      //   575: iload 14
      //   577: invokevirtual 184	android/content/pm/IPackageManager$Stub:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   580: astore 15
      //   582: aload_3
      //   583: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   586: aload 15
      //   588: ifnull +41 -> 629
      //   591: aload_3
      //   592: astore 16
      //   594: iconst_1
      //   595: istore 17
      //   597: aload 16
      //   599: iload 17
      //   601: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   604: aload 15
      //   606: astore 18
      //   608: aload_3
      //   609: astore 19
      //   611: iconst_1
      //   612: istore 20
      //   614: aload 18
      //   616: aload 19
      //   618: iload 20
      //   620: invokevirtual 197	android/content/pm/PackageInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   623: iconst_1
      //   624: istore 5
      //   626: goto -115 -> 511
      //   629: aload_3
      //   630: astore 21
      //   632: iconst_0
      //   633: istore 22
      //   635: aload 21
      //   637: iload 22
      //   639: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   642: goto -19 -> 623
      //   645: aload_2
      //   646: astore 23
      //   648: ldc 15
      //   650: astore 24
      //   652: aload 23
      //   654: aload 24
      //   656: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   659: aload_2
      //   660: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   663: astore 25
      //   665: aload_0
      //   666: astore 26
      //   668: aload 25
      //   670: astore 27
      //   672: aload 26
      //   674: aload 27
      //   676: invokevirtual 201	android/content/pm/IPackageManager$Stub:getPackageUid	(Ljava/lang/String;)I
      //   679: istore 28
      //   681: aload_3
      //   682: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   685: aload_3
      //   686: astore 29
      //   688: iload 28
      //   690: istore 30
      //   692: aload 29
      //   694: iload 30
      //   696: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   699: iconst_1
      //   700: istore 5
      //   702: goto -191 -> 511
      //   705: aload_2
      //   706: astore 31
      //   708: ldc 15
      //   710: astore 32
      //   712: aload 31
      //   714: aload 32
      //   716: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   719: aload_2
      //   720: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   723: astore 33
      //   725: aload_0
      //   726: astore 34
      //   728: aload 33
      //   730: astore 35
      //   732: aload 34
      //   734: aload 35
      //   736: invokevirtual 205	android/content/pm/IPackageManager$Stub:getPackageGids	(Ljava/lang/String;)[I
      //   739: astore 36
      //   741: aload_3
      //   742: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   745: aload_3
      //   746: astore 37
      //   748: aload 36
      //   750: astore 38
      //   752: aload 37
      //   754: aload 38
      //   756: invokevirtual 209	android/os/Parcel:writeIntArray	([I)V
      //   759: iconst_1
      //   760: istore 5
      //   762: goto -251 -> 511
      //   765: aload_2
      //   766: astore 39
      //   768: ldc 15
      //   770: astore 40
      //   772: aload 39
      //   774: aload 40
      //   776: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   779: aload_2
      //   780: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   783: astore 41
      //   785: aload_2
      //   786: invokevirtual 180	android/os/Parcel:readInt	()I
      //   789: istore 42
      //   791: aload_0
      //   792: astore 43
      //   794: aload 41
      //   796: astore 44
      //   798: iload 42
      //   800: istore 45
      //   802: aload 43
      //   804: aload 44
      //   806: iload 45
      //   808: invokevirtual 213	android/content/pm/IPackageManager$Stub:getPermissionInfo	(Ljava/lang/String;I)Landroid/content/pm/PermissionInfo;
      //   811: astore 15
      //   813: aload_3
      //   814: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   817: aload 15
      //   819: ifnull +41 -> 860
      //   822: aload_3
      //   823: astore 46
      //   825: iconst_1
      //   826: istore 47
      //   828: aload 46
      //   830: iload 47
      //   832: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   835: aload 15
      //   837: astore 48
      //   839: aload_3
      //   840: astore 49
      //   842: iconst_1
      //   843: istore 50
      //   845: aload 48
      //   847: aload 49
      //   849: iload 50
      //   851: invokevirtual 216	android/content/pm/PermissionInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   854: iconst_1
      //   855: istore 5
      //   857: goto -346 -> 511
      //   860: aload_3
      //   861: astore 51
      //   863: iconst_0
      //   864: istore 52
      //   866: aload 51
      //   868: iload 52
      //   870: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   873: goto -19 -> 854
      //   876: aload_2
      //   877: astore 53
      //   879: ldc 15
      //   881: astore 54
      //   883: aload 53
      //   885: aload 54
      //   887: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   890: aload_2
      //   891: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   894: astore 55
      //   896: aload_2
      //   897: invokevirtual 180	android/os/Parcel:readInt	()I
      //   900: istore 56
      //   902: aload_0
      //   903: astore 57
      //   905: aload 55
      //   907: astore 58
      //   909: iload 56
      //   911: istore 59
      //   913: aload 57
      //   915: aload 58
      //   917: iload 59
      //   919: invokevirtual 220	android/content/pm/IPackageManager$Stub:queryPermissionsByGroup	(Ljava/lang/String;I)Ljava/util/List;
      //   922: astore 60
      //   924: aload_3
      //   925: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   928: aload_3
      //   929: astore 61
      //   931: aload 60
      //   933: astore 62
      //   935: aload 61
      //   937: aload 62
      //   939: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   942: iconst_1
      //   943: istore 5
      //   945: goto -434 -> 511
      //   948: aload_2
      //   949: astore 63
      //   951: ldc 15
      //   953: astore 64
      //   955: aload 63
      //   957: aload 64
      //   959: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   962: aload_2
      //   963: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   966: astore 65
      //   968: aload_2
      //   969: invokevirtual 180	android/os/Parcel:readInt	()I
      //   972: istore 66
      //   974: aload_0
      //   975: astore 67
      //   977: aload 65
      //   979: astore 68
      //   981: iload 66
      //   983: istore 69
      //   985: aload 67
      //   987: aload 68
      //   989: iload 69
      //   991: invokevirtual 228	android/content/pm/IPackageManager$Stub:getPermissionGroupInfo	(Ljava/lang/String;I)Landroid/content/pm/PermissionGroupInfo;
      //   994: astore 15
      //   996: aload_3
      //   997: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1000: aload 15
      //   1002: ifnull +41 -> 1043
      //   1005: aload_3
      //   1006: astore 70
      //   1008: iconst_1
      //   1009: istore 71
      //   1011: aload 70
      //   1013: iload 71
      //   1015: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1018: aload 15
      //   1020: astore 72
      //   1022: aload_3
      //   1023: astore 73
      //   1025: iconst_1
      //   1026: istore 74
      //   1028: aload 72
      //   1030: aload 73
      //   1032: iload 74
      //   1034: invokevirtual 231	android/content/pm/PermissionGroupInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   1037: iconst_1
      //   1038: istore 5
      //   1040: goto -529 -> 511
      //   1043: aload_3
      //   1044: astore 75
      //   1046: iconst_0
      //   1047: istore 76
      //   1049: aload 75
      //   1051: iload 76
      //   1053: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1056: goto -19 -> 1037
      //   1059: aload_2
      //   1060: astore 77
      //   1062: ldc 15
      //   1064: astore 78
      //   1066: aload 77
      //   1068: aload 78
      //   1070: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1073: aload_2
      //   1074: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1077: istore 79
      //   1079: aload_0
      //   1080: astore 80
      //   1082: iload 79
      //   1084: istore 81
      //   1086: aload 80
      //   1088: iload 81
      //   1090: invokevirtual 235	android/content/pm/IPackageManager$Stub:getAllPermissionGroups	(I)Ljava/util/List;
      //   1093: astore 82
      //   1095: aload_3
      //   1096: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1099: aload_3
      //   1100: astore 83
      //   1102: aload 82
      //   1104: astore 84
      //   1106: aload 83
      //   1108: aload 84
      //   1110: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   1113: iconst_1
      //   1114: istore 5
      //   1116: goto -605 -> 511
      //   1119: aload_2
      //   1120: astore 85
      //   1122: ldc 15
      //   1124: astore 86
      //   1126: aload 85
      //   1128: aload 86
      //   1130: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1133: aload_2
      //   1134: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1137: astore 87
      //   1139: aload_2
      //   1140: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1143: istore 88
      //   1145: aload_0
      //   1146: astore 89
      //   1148: aload 87
      //   1150: astore 90
      //   1152: iload 88
      //   1154: istore 91
      //   1156: aload 89
      //   1158: aload 90
      //   1160: iload 91
      //   1162: invokevirtual 239	android/content/pm/IPackageManager$Stub:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   1165: astore 15
      //   1167: aload_3
      //   1168: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1171: aload 15
      //   1173: ifnull +41 -> 1214
      //   1176: aload_3
      //   1177: astore 92
      //   1179: iconst_1
      //   1180: istore 93
      //   1182: aload 92
      //   1184: iload 93
      //   1186: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1189: aload 15
      //   1191: astore 94
      //   1193: aload_3
      //   1194: astore 95
      //   1196: iconst_1
      //   1197: istore 96
      //   1199: aload 94
      //   1201: aload 95
      //   1203: iload 96
      //   1205: invokevirtual 242	android/content/pm/ApplicationInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   1208: iconst_1
      //   1209: istore 5
      //   1211: goto -700 -> 511
      //   1214: aload_3
      //   1215: astore 97
      //   1217: iconst_0
      //   1218: istore 98
      //   1220: aload 97
      //   1222: iload 98
      //   1224: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1227: goto -19 -> 1208
      //   1230: aload_2
      //   1231: astore 99
      //   1233: ldc 15
      //   1235: astore 100
      //   1237: aload 99
      //   1239: aload 100
      //   1241: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1244: aload_2
      //   1245: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1248: ifeq +100 -> 1348
      //   1251: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   1254: astore 101
      //   1256: aload_2
      //   1257: astore 102
      //   1259: aload 101
      //   1261: aload 102
      //   1263: invokeinterface 254 2 0
      //   1268: checkcast 244	android/content/ComponentName
      //   1271: astore 103
      //   1273: aload_2
      //   1274: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1277: istore 104
      //   1279: aload_0
      //   1280: astore 105
      //   1282: aload 103
      //   1284: astore 106
      //   1286: iload 104
      //   1288: istore 107
      //   1290: aload 105
      //   1292: aload 106
      //   1294: iload 107
      //   1296: invokevirtual 258	android/content/pm/IPackageManager$Stub:getActivityInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
      //   1299: astore 15
      //   1301: aload_3
      //   1302: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1305: aload 15
      //   1307: ifnull +47 -> 1354
      //   1310: aload_3
      //   1311: astore 108
      //   1313: iconst_1
      //   1314: istore 109
      //   1316: aload 108
      //   1318: iload 109
      //   1320: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1323: aload 15
      //   1325: astore 110
      //   1327: aload_3
      //   1328: astore 111
      //   1330: iconst_1
      //   1331: istore 112
      //   1333: aload 110
      //   1335: aload 111
      //   1337: iload 112
      //   1339: invokevirtual 261	android/content/pm/ActivityInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   1342: iconst_1
      //   1343: istore 5
      //   1345: goto -834 -> 511
      //   1348: aconst_null
      //   1349: astore 103
      //   1351: goto -78 -> 1273
      //   1354: aload_3
      //   1355: astore 113
      //   1357: iconst_0
      //   1358: istore 114
      //   1360: aload 113
      //   1362: iload 114
      //   1364: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1367: goto -25 -> 1342
      //   1370: aload_2
      //   1371: astore 115
      //   1373: ldc 15
      //   1375: astore 116
      //   1377: aload 115
      //   1379: aload 116
      //   1381: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1384: aload_2
      //   1385: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1388: ifeq +100 -> 1488
      //   1391: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   1394: astore 117
      //   1396: aload_2
      //   1397: astore 118
      //   1399: aload 117
      //   1401: aload 118
      //   1403: invokeinterface 254 2 0
      //   1408: checkcast 244	android/content/ComponentName
      //   1411: astore 103
      //   1413: aload_2
      //   1414: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1417: istore 119
      //   1419: aload_0
      //   1420: astore 120
      //   1422: aload 103
      //   1424: astore 121
      //   1426: iload 119
      //   1428: istore 122
      //   1430: aload 120
      //   1432: aload 121
      //   1434: iload 122
      //   1436: invokevirtual 264	android/content/pm/IPackageManager$Stub:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
      //   1439: astore 15
      //   1441: aload_3
      //   1442: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1445: aload 15
      //   1447: ifnull +47 -> 1494
      //   1450: aload_3
      //   1451: astore 123
      //   1453: iconst_1
      //   1454: istore 124
      //   1456: aload 123
      //   1458: iload 124
      //   1460: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1463: aload 15
      //   1465: astore 125
      //   1467: aload_3
      //   1468: astore 126
      //   1470: iconst_1
      //   1471: istore 127
      //   1473: aload 125
      //   1475: aload 126
      //   1477: iload 127
      //   1479: invokevirtual 261	android/content/pm/ActivityInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   1482: iconst_1
      //   1483: istore 5
      //   1485: goto -974 -> 511
      //   1488: aconst_null
      //   1489: astore 103
      //   1491: goto -78 -> 1413
      //   1494: aload_3
      //   1495: astore 128
      //   1497: iconst_0
      //   1498: istore 129
      //   1500: aload 128
      //   1502: iload 129
      //   1504: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1507: goto -25 -> 1482
      //   1510: aload_2
      //   1511: astore 130
      //   1513: ldc 15
      //   1515: astore 131
      //   1517: aload 130
      //   1519: aload 131
      //   1521: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1524: aload_2
      //   1525: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1528: ifeq +100 -> 1628
      //   1531: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   1534: astore 132
      //   1536: aload_2
      //   1537: astore 133
      //   1539: aload 132
      //   1541: aload 133
      //   1543: invokeinterface 254 2 0
      //   1548: checkcast 244	android/content/ComponentName
      //   1551: astore 103
      //   1553: aload_2
      //   1554: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1557: istore 134
      //   1559: aload_0
      //   1560: astore 135
      //   1562: aload 103
      //   1564: astore 136
      //   1566: iload 134
      //   1568: istore 137
      //   1570: aload 135
      //   1572: aload 136
      //   1574: iload 137
      //   1576: invokevirtual 268	android/content/pm/IPackageManager$Stub:getServiceInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ServiceInfo;
      //   1579: astore 15
      //   1581: aload_3
      //   1582: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1585: aload 15
      //   1587: ifnull +47 -> 1634
      //   1590: aload_3
      //   1591: astore 138
      //   1593: iconst_1
      //   1594: istore 139
      //   1596: aload 138
      //   1598: iload 139
      //   1600: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1603: aload 15
      //   1605: astore 140
      //   1607: aload_3
      //   1608: astore 141
      //   1610: iconst_1
      //   1611: istore 142
      //   1613: aload 140
      //   1615: aload 141
      //   1617: iload 142
      //   1619: invokevirtual 271	android/content/pm/ServiceInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   1622: iconst_1
      //   1623: istore 5
      //   1625: goto -1114 -> 511
      //   1628: aconst_null
      //   1629: astore 103
      //   1631: goto -78 -> 1553
      //   1634: aload_3
      //   1635: astore 143
      //   1637: iconst_0
      //   1638: istore 144
      //   1640: aload 143
      //   1642: iload 144
      //   1644: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1647: goto -25 -> 1622
      //   1650: aload_2
      //   1651: astore 145
      //   1653: ldc 15
      //   1655: astore 146
      //   1657: aload 145
      //   1659: aload 146
      //   1661: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1664: aload_2
      //   1665: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1668: astore 147
      //   1670: aload_2
      //   1671: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1674: astore 148
      //   1676: aload_0
      //   1677: astore 149
      //   1679: aload 147
      //   1681: astore 150
      //   1683: aload 148
      //   1685: astore 151
      //   1687: aload 149
      //   1689: aload 150
      //   1691: aload 151
      //   1693: invokevirtual 275	android/content/pm/IPackageManager$Stub:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
      //   1696: istore 152
      //   1698: aload_3
      //   1699: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1702: aload_3
      //   1703: astore 153
      //   1705: iload 152
      //   1707: istore 154
      //   1709: aload 153
      //   1711: iload 154
      //   1713: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1716: iconst_1
      //   1717: istore 5
      //   1719: goto -1208 -> 511
      //   1722: aload_2
      //   1723: astore 155
      //   1725: ldc 15
      //   1727: astore 156
      //   1729: aload 155
      //   1731: aload 156
      //   1733: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1736: aload_2
      //   1737: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1740: astore 157
      //   1742: aload_2
      //   1743: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1746: istore 158
      //   1748: aload_0
      //   1749: astore 159
      //   1751: aload 157
      //   1753: astore 160
      //   1755: iload 158
      //   1757: istore 161
      //   1759: aload 159
      //   1761: aload 160
      //   1763: iload 161
      //   1765: invokevirtual 279	android/content/pm/IPackageManager$Stub:checkUidPermission	(Ljava/lang/String;I)I
      //   1768: istore 162
      //   1770: aload_3
      //   1771: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1774: aload_3
      //   1775: astore 163
      //   1777: iload 162
      //   1779: istore 164
      //   1781: aload 163
      //   1783: iload 164
      //   1785: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1788: iconst_1
      //   1789: istore 5
      //   1791: goto -1280 -> 511
      //   1794: aload_2
      //   1795: astore 165
      //   1797: ldc 15
      //   1799: astore 166
      //   1801: aload 165
      //   1803: aload 166
      //   1805: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1808: aload_2
      //   1809: invokevirtual 180	android/os/Parcel:readInt	()I
      //   1812: ifeq +73 -> 1885
      //   1815: getstatic 280	android/content/pm/PermissionInfo:CREATOR	Landroid/os/Parcelable$Creator;
      //   1818: astore 167
      //   1820: aload_2
      //   1821: astore 168
      //   1823: aload 167
      //   1825: aload 168
      //   1827: invokeinterface 254 2 0
      //   1832: checkcast 215	android/content/pm/PermissionInfo
      //   1835: astore 103
      //   1837: aload_0
      //   1838: astore 169
      //   1840: aload 103
      //   1842: astore 170
      //   1844: aload 169
      //   1846: aload 170
      //   1848: invokevirtual 284	android/content/pm/IPackageManager$Stub:addPermission	(Landroid/content/pm/PermissionInfo;)Z
      //   1851: istore 171
      //   1853: aload_3
      //   1854: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1857: iload 171
      //   1859: ifeq +32 -> 1891
      //   1862: iconst_1
      //   1863: istore 5
      //   1865: aload_3
      //   1866: astore 172
      //   1868: iload 5
      //   1870: istore 173
      //   1872: aload 172
      //   1874: iload 173
      //   1876: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   1879: iconst_1
      //   1880: istore 5
      //   1882: goto -1371 -> 511
      //   1885: aconst_null
      //   1886: astore 103
      //   1888: goto -51 -> 1837
      //   1891: iconst_0
      //   1892: istore 5
      //   1894: goto -29 -> 1865
      //   1897: aload_2
      //   1898: astore 174
      //   1900: ldc 15
      //   1902: astore 175
      //   1904: aload 174
      //   1906: aload 175
      //   1908: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1911: aload_2
      //   1912: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1915: astore 176
      //   1917: aload_0
      //   1918: astore 177
      //   1920: aload 176
      //   1922: astore 178
      //   1924: aload 177
      //   1926: aload 178
      //   1928: invokevirtual 287	android/content/pm/IPackageManager$Stub:removePermission	(Ljava/lang/String;)V
      //   1931: aload_3
      //   1932: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1935: iconst_1
      //   1936: istore 5
      //   1938: goto -1427 -> 511
      //   1941: aload_2
      //   1942: astore 179
      //   1944: ldc 15
      //   1946: astore 180
      //   1948: aload 179
      //   1950: aload 180
      //   1952: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   1955: aload_2
      //   1956: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   1959: astore 181
      //   1961: aload_0
      //   1962: astore 182
      //   1964: aload 181
      //   1966: astore 183
      //   1968: aload 182
      //   1970: aload 183
      //   1972: invokevirtual 291	android/content/pm/IPackageManager$Stub:isProtectedBroadcast	(Ljava/lang/String;)Z
      //   1975: istore 184
      //   1977: aload_3
      //   1978: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   1981: iload 184
      //   1983: ifeq +26 -> 2009
      //   1986: iconst_1
      //   1987: istore 5
      //   1989: aload_3
      //   1990: astore 185
      //   1992: iload 5
      //   1994: istore 186
      //   1996: aload 185
      //   1998: iload 186
      //   2000: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2003: iconst_1
      //   2004: istore 5
      //   2006: goto -1495 -> 511
      //   2009: iconst_0
      //   2010: istore 5
      //   2012: goto -23 -> 1989
      //   2015: aload_2
      //   2016: astore 187
      //   2018: ldc 15
      //   2020: astore 188
      //   2022: aload 187
      //   2024: aload 188
      //   2026: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2029: aload_2
      //   2030: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2033: astore 189
      //   2035: aload_2
      //   2036: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2039: astore 190
      //   2041: aload_0
      //   2042: astore 191
      //   2044: aload 189
      //   2046: astore 192
      //   2048: aload 190
      //   2050: astore 193
      //   2052: aload 191
      //   2054: aload 192
      //   2056: aload 193
      //   2058: invokevirtual 294	android/content/pm/IPackageManager$Stub:checkSignatures	(Ljava/lang/String;Ljava/lang/String;)I
      //   2061: istore 194
      //   2063: aload_3
      //   2064: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2067: aload_3
      //   2068: astore 195
      //   2070: iload 194
      //   2072: istore 196
      //   2074: aload 195
      //   2076: iload 196
      //   2078: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2081: iconst_1
      //   2082: istore 5
      //   2084: goto -1573 -> 511
      //   2087: aload_2
      //   2088: astore 197
      //   2090: ldc 15
      //   2092: astore 198
      //   2094: aload 197
      //   2096: aload 198
      //   2098: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2101: aload_2
      //   2102: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2105: istore 199
      //   2107: aload_2
      //   2108: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2111: istore 200
      //   2113: aload_0
      //   2114: astore 201
      //   2116: iload 199
      //   2118: istore 202
      //   2120: iload 200
      //   2122: istore 203
      //   2124: aload 201
      //   2126: iload 202
      //   2128: iload 203
      //   2130: invokevirtual 298	android/content/pm/IPackageManager$Stub:checkUidSignatures	(II)I
      //   2133: istore 204
      //   2135: aload_3
      //   2136: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2139: aload_3
      //   2140: astore 205
      //   2142: iload 204
      //   2144: istore 206
      //   2146: aload 205
      //   2148: iload 206
      //   2150: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2153: iconst_1
      //   2154: istore 5
      //   2156: goto -1645 -> 511
      //   2159: aload_2
      //   2160: astore 207
      //   2162: ldc 15
      //   2164: astore 208
      //   2166: aload 207
      //   2168: aload 208
      //   2170: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2173: aload_2
      //   2174: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2177: istore 209
      //   2179: aload_0
      //   2180: astore 210
      //   2182: iload 209
      //   2184: istore 211
      //   2186: aload 210
      //   2188: iload 211
      //   2190: invokevirtual 302	android/content/pm/IPackageManager$Stub:getPackagesForUid	(I)[Ljava/lang/String;
      //   2193: astore 212
      //   2195: aload_3
      //   2196: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2199: aload_3
      //   2200: astore 213
      //   2202: aload 212
      //   2204: astore 214
      //   2206: aload 213
      //   2208: aload 214
      //   2210: invokevirtual 306	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
      //   2213: iconst_1
      //   2214: istore 5
      //   2216: goto -1705 -> 511
      //   2219: aload_2
      //   2220: astore 215
      //   2222: ldc 15
      //   2224: astore 216
      //   2226: aload 215
      //   2228: aload 216
      //   2230: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2233: aload_2
      //   2234: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2237: istore 217
      //   2239: aload_0
      //   2240: astore 218
      //   2242: iload 217
      //   2244: istore 219
      //   2246: aload 218
      //   2248: iload 219
      //   2250: invokevirtual 310	android/content/pm/IPackageManager$Stub:getNameForUid	(I)Ljava/lang/String;
      //   2253: astore 220
      //   2255: aload_3
      //   2256: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2259: aload_3
      //   2260: astore 221
      //   2262: aload 220
      //   2264: astore 222
      //   2266: aload 221
      //   2268: aload 222
      //   2270: invokevirtual 169	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   2273: iconst_1
      //   2274: istore 5
      //   2276: goto -1765 -> 511
      //   2279: aload_2
      //   2280: astore 223
      //   2282: ldc 15
      //   2284: astore 224
      //   2286: aload 223
      //   2288: aload 224
      //   2290: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2293: aload_2
      //   2294: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2297: astore 225
      //   2299: aload_0
      //   2300: astore 226
      //   2302: aload 225
      //   2304: astore 227
      //   2306: aload 226
      //   2308: aload 227
      //   2310: invokevirtual 313	android/content/pm/IPackageManager$Stub:getUidForSharedUser	(Ljava/lang/String;)I
      //   2313: istore 228
      //   2315: aload_3
      //   2316: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2319: aload_3
      //   2320: astore 229
      //   2322: iload 228
      //   2324: istore 230
      //   2326: aload 229
      //   2328: iload 230
      //   2330: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2333: iconst_1
      //   2334: istore 5
      //   2336: goto -1825 -> 511
      //   2339: aload_2
      //   2340: astore 231
      //   2342: ldc 15
      //   2344: astore 232
      //   2346: aload 231
      //   2348: aload 232
      //   2350: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2353: aload_2
      //   2354: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2357: ifeq +112 -> 2469
      //   2360: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   2363: astore 233
      //   2365: aload_2
      //   2366: astore 234
      //   2368: aload 233
      //   2370: aload 234
      //   2372: invokeinterface 254 2 0
      //   2377: checkcast 315	android/content/Intent
      //   2380: astore 103
      //   2382: aload_2
      //   2383: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2386: astore 235
      //   2388: aload_2
      //   2389: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2392: istore 236
      //   2394: aload_0
      //   2395: astore 237
      //   2397: aload 103
      //   2399: astore 238
      //   2401: aload 235
      //   2403: astore 239
      //   2405: iload 236
      //   2407: istore 240
      //   2409: aload 237
      //   2411: aload 238
      //   2413: aload 239
      //   2415: iload 240
      //   2417: invokevirtual 320	android/content/pm/IPackageManager$Stub:resolveIntent	(Landroid/content/Intent;Ljava/lang/String;I)Landroid/content/pm/ResolveInfo;
      //   2420: astore 15
      //   2422: aload_3
      //   2423: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2426: aload 15
      //   2428: ifnull +47 -> 2475
      //   2431: aload_3
      //   2432: astore 241
      //   2434: iconst_1
      //   2435: istore 242
      //   2437: aload 241
      //   2439: iload 242
      //   2441: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2444: aload 15
      //   2446: astore 243
      //   2448: aload_3
      //   2449: astore 244
      //   2451: iconst_1
      //   2452: istore 245
      //   2454: aload 243
      //   2456: aload 244
      //   2458: iload 245
      //   2460: invokevirtual 323	android/content/pm/ResolveInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   2463: iconst_1
      //   2464: istore 5
      //   2466: goto -1955 -> 511
      //   2469: aconst_null
      //   2470: astore 103
      //   2472: goto -90 -> 2382
      //   2475: aload_3
      //   2476: astore 246
      //   2478: iconst_0
      //   2479: istore 247
      //   2481: aload 246
      //   2483: iload 247
      //   2485: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   2488: goto -25 -> 2463
      //   2491: aload_2
      //   2492: astore 248
      //   2494: ldc 15
      //   2496: astore 249
      //   2498: aload 248
      //   2500: aload 249
      //   2502: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2505: aload_2
      //   2506: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2509: ifeq +109 -> 2618
      //   2512: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   2515: astore 250
      //   2517: aload_2
      //   2518: astore 251
      //   2520: aload 250
      //   2522: aload 251
      //   2524: invokeinterface 254 2 0
      //   2529: checkcast 315	android/content/Intent
      //   2532: astore 103
      //   2534: aload_2
      //   2535: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2538: astore 252
      //   2540: aload_2
      //   2541: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2544: istore 253
      //   2546: aload_0
      //   2547: astore 254
      //   2549: aload 103
      //   2551: astore 255
      //   2553: aload 252
      //   2555: wide
      //   2559: iload 253
      //   2561: wide
      //   2565: aload 254
      //   2567: aload 255
      //   2569: wide
      //   2573: wide
      //   2577: invokevirtual 327	android/content/pm/IPackageManager$Stub:queryIntentActivities	(Landroid/content/Intent;Ljava/lang/String;I)Ljava/util/List;
      //   2580: wide
      //   2584: aload_3
      //   2585: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2588: aload_3
      //   2589: wide
      //   2593: wide
      //   2597: wide
      //   2601: wide
      //   2605: wide
      //   2609: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   2612: iconst_1
      //   2613: istore 5
      //   2615: goto -2104 -> 511
      //   2618: aconst_null
      //   2619: astore 103
      //   2621: goto -87 -> 2534
      //   2624: aload_2
      //   2625: wide
      //   2629: ldc 15
      //   2631: wide
      //   2635: wide
      //   2639: wide
      //   2643: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2646: aload_2
      //   2647: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2650: ifeq +198 -> 2848
      //   2653: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   2656: wide
      //   2660: aload_2
      //   2661: wide
      //   2665: wide
      //   2669: wide
      //   2673: invokeinterface 254 2 0
      //   2678: checkcast 244	android/content/ComponentName
      //   2681: astore 103
      //   2683: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   2686: wide
      //   2690: aload_2
      //   2691: wide
      //   2695: wide
      //   2699: wide
      //   2703: wide
      //   2707: wide
      //   2711: invokevirtual 331	android/os/Parcel:createTypedArray	(Landroid/os/Parcelable$Creator;)[Ljava/lang/Object;
      //   2714: checkcast 333	[Landroid/content/Intent;
      //   2717: wide
      //   2721: aload_2
      //   2722: invokevirtual 337	android/os/Parcel:createStringArray	()[Ljava/lang/String;
      //   2725: wide
      //   2729: aload_2
      //   2730: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2733: ifeq +121 -> 2854
      //   2736: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   2739: wide
      //   2743: aload_2
      //   2744: wide
      //   2748: wide
      //   2752: wide
      //   2756: invokeinterface 254 2 0
      //   2761: checkcast 315	android/content/Intent
      //   2764: wide
      //   2768: aload_2
      //   2769: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2772: wide
      //   2776: aload_2
      //   2777: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2780: wide
      //   2784: aload_0
      //   2785: aload 103
      //   2787: wide
      //   2791: wide
      //   2795: wide
      //   2799: wide
      //   2803: wide
      //   2807: invokevirtual 341	android/content/pm/IPackageManager$Stub:queryIntentActivityOptions	(Landroid/content/ComponentName;[Landroid/content/Intent;[Ljava/lang/String;Landroid/content/Intent;Ljava/lang/String;I)Ljava/util/List;
      //   2810: wide
      //   2814: aload_3
      //   2815: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2818: aload_3
      //   2819: wide
      //   2823: wide
      //   2827: wide
      //   2831: wide
      //   2835: wide
      //   2839: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   2842: iconst_1
      //   2843: istore 5
      //   2845: goto -2334 -> 511
      //   2848: aconst_null
      //   2849: astore 103
      //   2851: goto -168 -> 2683
      //   2854: aconst_null
      //   2855: wide
      //   2859: goto -91 -> 2768
      //   2862: aload_2
      //   2863: wide
      //   2867: ldc 15
      //   2869: wide
      //   2873: wide
      //   2877: wide
      //   2881: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   2884: aload_2
      //   2885: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2888: ifeq +133 -> 3021
      //   2891: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   2894: wide
      //   2898: aload_2
      //   2899: wide
      //   2903: wide
      //   2907: wide
      //   2911: invokeinterface 254 2 0
      //   2916: checkcast 315	android/content/Intent
      //   2919: astore 103
      //   2921: aload_2
      //   2922: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   2925: wide
      //   2929: aload_2
      //   2930: invokevirtual 180	android/os/Parcel:readInt	()I
      //   2933: wide
      //   2937: aload_0
      //   2938: wide
      //   2942: aload 103
      //   2944: wide
      //   2948: wide
      //   2952: wide
      //   2956: wide
      //   2960: wide
      //   2964: wide
      //   2968: wide
      //   2972: wide
      //   2976: wide
      //   2980: invokevirtual 344	android/content/pm/IPackageManager$Stub:queryIntentReceivers	(Landroid/content/Intent;Ljava/lang/String;I)Ljava/util/List;
      //   2983: wide
      //   2987: aload_3
      //   2988: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   2991: aload_3
      //   2992: wide
      //   2996: wide
      //   3000: wide
      //   3004: wide
      //   3008: wide
      //   3012: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   3015: iconst_1
      //   3016: istore 5
      //   3018: goto -2507 -> 511
      //   3021: aconst_null
      //   3022: astore 103
      //   3024: goto -103 -> 2921
      //   3027: aload_2
      //   3028: wide
      //   3032: ldc 15
      //   3034: wide
      //   3038: wide
      //   3042: wide
      //   3046: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3049: aload_2
      //   3050: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3053: ifeq +164 -> 3217
      //   3056: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   3059: wide
      //   3063: aload_2
      //   3064: wide
      //   3068: wide
      //   3072: wide
      //   3076: invokeinterface 254 2 0
      //   3081: checkcast 315	android/content/Intent
      //   3084: astore 103
      //   3086: aload_2
      //   3087: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   3090: wide
      //   3094: aload_2
      //   3095: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3098: wide
      //   3102: aload_0
      //   3103: wide
      //   3107: aload 103
      //   3109: wide
      //   3113: wide
      //   3117: wide
      //   3121: wide
      //   3125: wide
      //   3129: wide
      //   3133: wide
      //   3137: wide
      //   3141: wide
      //   3145: invokevirtual 347	android/content/pm/IPackageManager$Stub:resolveService	(Landroid/content/Intent;Ljava/lang/String;I)Landroid/content/pm/ResolveInfo;
      //   3148: astore 15
      //   3150: aload_3
      //   3151: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3154: aload 15
      //   3156: ifnull +67 -> 3223
      //   3159: aload_3
      //   3160: wide
      //   3164: iconst_1
      //   3165: wide
      //   3169: wide
      //   3173: wide
      //   3177: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   3180: aload 15
      //   3182: wide
      //   3186: aload_3
      //   3187: wide
      //   3191: iconst_1
      //   3192: wide
      //   3196: wide
      //   3200: wide
      //   3204: wide
      //   3208: invokevirtual 323	android/content/pm/ResolveInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   3211: iconst_1
      //   3212: istore 5
      //   3214: goto -2703 -> 511
      //   3217: aconst_null
      //   3218: astore 103
      //   3220: goto -134 -> 3086
      //   3223: aload_3
      //   3224: wide
      //   3228: iconst_0
      //   3229: wide
      //   3233: wide
      //   3237: wide
      //   3241: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   3244: goto -33 -> 3211
      //   3247: aload_2
      //   3248: wide
      //   3252: ldc 15
      //   3254: wide
      //   3258: wide
      //   3262: wide
      //   3266: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3269: aload_2
      //   3270: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3273: ifeq +133 -> 3406
      //   3276: getstatic 316	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
      //   3279: wide
      //   3283: aload_2
      //   3284: wide
      //   3288: wide
      //   3292: wide
      //   3296: invokeinterface 254 2 0
      //   3301: checkcast 315	android/content/Intent
      //   3304: astore 103
      //   3306: aload_2
      //   3307: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   3310: wide
      //   3314: aload_2
      //   3315: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3318: wide
      //   3322: aload_0
      //   3323: wide
      //   3327: aload 103
      //   3329: wide
      //   3333: wide
      //   3337: wide
      //   3341: wide
      //   3345: wide
      //   3349: wide
      //   3353: wide
      //   3357: wide
      //   3361: wide
      //   3365: invokevirtual 350	android/content/pm/IPackageManager$Stub:queryIntentServices	(Landroid/content/Intent;Ljava/lang/String;I)Ljava/util/List;
      //   3368: wide
      //   3372: aload_3
      //   3373: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3376: aload_3
      //   3377: wide
      //   3381: wide
      //   3385: wide
      //   3389: wide
      //   3393: wide
      //   3397: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   3400: iconst_1
      //   3401: istore 5
      //   3403: goto -2892 -> 511
      //   3406: aconst_null
      //   3407: astore 103
      //   3409: goto -103 -> 3306
      //   3412: aload_2
      //   3413: wide
      //   3417: ldc 15
      //   3419: wide
      //   3423: wide
      //   3427: wide
      //   3431: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3434: aload_2
      //   3435: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3438: wide
      //   3442: aload_0
      //   3443: wide
      //   3447: wide
      //   3451: wide
      //   3455: wide
      //   3459: wide
      //   3463: invokevirtual 353	android/content/pm/IPackageManager$Stub:getInstalledPackages	(I)Ljava/util/List;
      //   3466: wide
      //   3470: aload_3
      //   3471: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3474: aload_3
      //   3475: wide
      //   3479: wide
      //   3483: wide
      //   3487: wide
      //   3491: wide
      //   3495: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   3498: iconst_1
      //   3499: istore 5
      //   3501: goto -2990 -> 511
      //   3504: aload_2
      //   3505: wide
      //   3509: ldc 15
      //   3511: wide
      //   3515: wide
      //   3519: wide
      //   3523: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3526: aload_2
      //   3527: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3530: wide
      //   3534: aload_0
      //   3535: wide
      //   3539: wide
      //   3543: wide
      //   3547: wide
      //   3551: wide
      //   3555: invokevirtual 356	android/content/pm/IPackageManager$Stub:getInstalledApplications	(I)Ljava/util/List;
      //   3558: wide
      //   3562: aload_3
      //   3563: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3566: aload_3
      //   3567: wide
      //   3571: wide
      //   3575: wide
      //   3579: wide
      //   3583: wide
      //   3587: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   3590: iconst_1
      //   3591: istore 5
      //   3593: goto -3082 -> 511
      //   3596: aload_2
      //   3597: wide
      //   3601: ldc 15
      //   3603: wide
      //   3607: wide
      //   3611: wide
      //   3615: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3618: aload_2
      //   3619: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3622: wide
      //   3626: aload_0
      //   3627: wide
      //   3631: wide
      //   3635: wide
      //   3639: wide
      //   3643: wide
      //   3647: invokevirtual 359	android/content/pm/IPackageManager$Stub:getPersistentApplications	(I)Ljava/util/List;
      //   3650: wide
      //   3654: aload_3
      //   3655: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3658: aload_3
      //   3659: wide
      //   3663: wide
      //   3667: wide
      //   3671: wide
      //   3675: wide
      //   3679: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   3682: iconst_1
      //   3683: istore 5
      //   3685: goto -3174 -> 511
      //   3688: aload_2
      //   3689: wide
      //   3693: ldc 15
      //   3695: wide
      //   3699: wide
      //   3703: wide
      //   3707: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3710: aload_2
      //   3711: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   3714: wide
      //   3718: aload_2
      //   3719: invokevirtual 180	android/os/Parcel:readInt	()I
      //   3722: wide
      //   3726: aload_0
      //   3727: wide
      //   3731: wide
      //   3735: wide
      //   3739: wide
      //   3743: wide
      //   3747: wide
      //   3751: wide
      //   3755: wide
      //   3759: invokevirtual 363	android/content/pm/IPackageManager$Stub:resolveContentProvider	(Ljava/lang/String;I)Landroid/content/pm/ProviderInfo;
      //   3762: astore 15
      //   3764: aload_3
      //   3765: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3768: aload 15
      //   3770: ifnull +61 -> 3831
      //   3773: aload_3
      //   3774: wide
      //   3778: iconst_1
      //   3779: wide
      //   3783: wide
      //   3787: wide
      //   3791: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   3794: aload 15
      //   3796: wide
      //   3800: aload_3
      //   3801: wide
      //   3805: iconst_1
      //   3806: wide
      //   3810: wide
      //   3814: wide
      //   3818: wide
      //   3822: invokevirtual 366	android/content/pm/ProviderInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   3825: iconst_1
      //   3826: istore 5
      //   3828: goto -3317 -> 511
      //   3831: aload_3
      //   3832: wide
      //   3836: iconst_0
      //   3837: wide
      //   3841: wide
      //   3845: wide
      //   3849: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   3852: goto -27 -> 3825
      //   3855: aload_2
      //   3856: wide
      //   3860: ldc 15
      //   3862: wide
      //   3866: wide
      //   3870: wide
      //   3874: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   3877: aload_2
      //   3878: invokevirtual 370	android/os/Parcel:createStringArrayList	()Ljava/util/ArrayList;
      //   3881: wide
      //   3885: getstatic 371	android/content/pm/ProviderInfo:CREATOR	Landroid/os/Parcelable$Creator;
      //   3888: wide
      //   3892: aload_2
      //   3893: wide
      //   3897: wide
      //   3901: wide
      //   3905: wide
      //   3909: wide
      //   3913: invokevirtual 375	android/os/Parcel:createTypedArrayList	(Landroid/os/Parcelable$Creator;)Ljava/util/ArrayList;
      //   3916: wide
      //   3920: aload_0
      //   3921: wide
      //   3925: wide
      //   3929: wide
      //   3933: wide
      //   3937: wide
      //   3941: wide
      //   3945: wide
      //   3949: wide
      //   3953: invokevirtual 379	android/content/pm/IPackageManager$Stub:querySyncProviders	(Ljava/util/List;Ljava/util/List;)V
      //   3956: aload_3
      //   3957: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   3960: aload_3
      //   3961: wide
      //   3965: wide
      //   3969: wide
      //   3973: wide
      //   3977: wide
      //   3981: invokevirtual 382	android/os/Parcel:writeStringList	(Ljava/util/List;)V
      //   3984: aload_3
      //   3985: wide
      //   3989: wide
      //   3993: wide
      //   3997: wide
      //   4001: wide
      //   4005: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   4008: iconst_1
      //   4009: istore 5
      //   4011: goto -3500 -> 511
      //   4014: aload_2
      //   4015: wide
      //   4019: ldc 15
      //   4021: wide
      //   4025: wide
      //   4029: wide
      //   4033: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4036: aload_2
      //   4037: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4040: wide
      //   4044: aload_2
      //   4045: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4048: wide
      //   4052: aload_2
      //   4053: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4056: wide
      //   4060: aload_0
      //   4061: wide
      //   4065: wide
      //   4069: wide
      //   4073: wide
      //   4077: wide
      //   4081: wide
      //   4085: wide
      //   4089: wide
      //   4093: wide
      //   4097: wide
      //   4101: wide
      //   4105: invokevirtual 386	android/content/pm/IPackageManager$Stub:queryContentProviders	(Ljava/lang/String;II)Ljava/util/List;
      //   4108: wide
      //   4112: aload_3
      //   4113: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4116: aload_3
      //   4117: wide
      //   4121: wide
      //   4125: wide
      //   4129: wide
      //   4133: wide
      //   4137: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   4140: iconst_1
      //   4141: istore 5
      //   4143: goto -3632 -> 511
      //   4146: aload_2
      //   4147: wide
      //   4151: ldc 15
      //   4153: wide
      //   4157: wide
      //   4161: wide
      //   4165: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4168: aload_2
      //   4169: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4172: ifeq +144 -> 4316
      //   4175: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   4178: wide
      //   4182: aload_2
      //   4183: wide
      //   4187: wide
      //   4191: wide
      //   4195: invokeinterface 254 2 0
      //   4200: checkcast 244	android/content/ComponentName
      //   4203: astore 103
      //   4205: aload_2
      //   4206: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4209: wide
      //   4213: aload_0
      //   4214: wide
      //   4218: aload 103
      //   4220: wide
      //   4224: wide
      //   4228: wide
      //   4232: wide
      //   4236: wide
      //   4240: wide
      //   4244: invokevirtual 390	android/content/pm/IPackageManager$Stub:getInstrumentationInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/InstrumentationInfo;
      //   4247: astore 15
      //   4249: aload_3
      //   4250: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4253: aload 15
      //   4255: ifnull +67 -> 4322
      //   4258: aload_3
      //   4259: wide
      //   4263: iconst_1
      //   4264: wide
      //   4268: wide
      //   4272: wide
      //   4276: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   4279: aload 15
      //   4281: wide
      //   4285: aload_3
      //   4286: wide
      //   4290: iconst_1
      //   4291: wide
      //   4295: wide
      //   4299: wide
      //   4303: wide
      //   4307: invokevirtual 393	android/content/pm/InstrumentationInfo:writeToParcel	(Landroid/os/Parcel;I)V
      //   4310: iconst_1
      //   4311: istore 5
      //   4313: goto -3802 -> 511
      //   4316: aconst_null
      //   4317: astore 103
      //   4319: goto -114 -> 4205
      //   4322: aload_3
      //   4323: wide
      //   4327: iconst_0
      //   4328: wide
      //   4332: wide
      //   4336: wide
      //   4340: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   4343: goto -33 -> 4310
      //   4346: aload_2
      //   4347: wide
      //   4351: ldc 15
      //   4353: wide
      //   4357: wide
      //   4361: wide
      //   4365: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4368: aload_2
      //   4369: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4372: wide
      //   4376: aload_2
      //   4377: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4380: wide
      //   4384: aload_0
      //   4385: wide
      //   4389: wide
      //   4393: wide
      //   4397: wide
      //   4401: wide
      //   4405: wide
      //   4409: wide
      //   4413: wide
      //   4417: invokevirtual 396	android/content/pm/IPackageManager$Stub:queryInstrumentation	(Ljava/lang/String;I)Ljava/util/List;
      //   4420: wide
      //   4424: aload_3
      //   4425: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4428: aload_3
      //   4429: wide
      //   4433: wide
      //   4437: wide
      //   4441: wide
      //   4445: wide
      //   4449: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   4452: iconst_1
      //   4453: istore 5
      //   4455: goto -3944 -> 511
      //   4458: aload_2
      //   4459: wide
      //   4463: ldc 15
      //   4465: wide
      //   4469: wide
      //   4473: wide
      //   4477: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4480: aload_2
      //   4481: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4484: ifeq +128 -> 4612
      //   4487: getstatic 399	android/net/Uri:CREATOR	Landroid/os/Parcelable$Creator;
      //   4490: wide
      //   4494: aload_2
      //   4495: wide
      //   4499: wide
      //   4503: wide
      //   4507: invokeinterface 254 2 0
      //   4512: checkcast 398	android/net/Uri
      //   4515: astore 103
      //   4517: aload_2
      //   4518: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   4521: invokestatic 407	android/content/pm/IPackageInstallObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageInstallObserver;
      //   4524: wide
      //   4528: aload_2
      //   4529: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4532: wide
      //   4536: aload_2
      //   4537: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4540: wide
      //   4544: aload_0
      //   4545: wide
      //   4549: aload 103
      //   4551: wide
      //   4555: wide
      //   4559: wide
      //   4563: wide
      //   4567: wide
      //   4571: wide
      //   4575: wide
      //   4579: wide
      //   4583: wide
      //   4587: wide
      //   4591: wide
      //   4595: wide
      //   4599: invokevirtual 411	android/content/pm/IPackageManager$Stub:installPackage	(Landroid/net/Uri;Landroid/content/pm/IPackageInstallObserver;ILjava/lang/String;)V
      //   4602: aload_3
      //   4603: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4606: iconst_1
      //   4607: istore 5
      //   4609: goto -4098 -> 511
      //   4612: aconst_null
      //   4613: astore 103
      //   4615: goto -98 -> 4517
      //   4618: aload_2
      //   4619: wide
      //   4623: ldc 15
      //   4625: wide
      //   4629: wide
      //   4633: wide
      //   4637: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4640: aload_2
      //   4641: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4644: wide
      //   4648: aload_2
      //   4649: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   4652: invokestatic 416	android/content/pm/IPackageDeleteObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageDeleteObserver;
      //   4655: wide
      //   4659: aload_2
      //   4660: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4663: wide
      //   4667: aload_0
      //   4668: wide
      //   4672: wide
      //   4676: wide
      //   4680: wide
      //   4684: wide
      //   4688: wide
      //   4692: wide
      //   4696: wide
      //   4700: wide
      //   4704: wide
      //   4708: wide
      //   4712: invokevirtual 420	android/content/pm/IPackageManager$Stub:deletePackage	(Ljava/lang/String;Landroid/content/pm/IPackageDeleteObserver;I)V
      //   4715: aload_3
      //   4716: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4719: iconst_1
      //   4720: istore 5
      //   4722: goto -4211 -> 511
      //   4725: aload_2
      //   4726: wide
      //   4730: ldc 15
      //   4732: wide
      //   4736: wide
      //   4740: wide
      //   4744: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4747: aload_2
      //   4748: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4751: wide
      //   4755: aload_0
      //   4756: wide
      //   4760: wide
      //   4764: wide
      //   4768: wide
      //   4772: wide
      //   4776: invokevirtual 424	android/content/pm/IPackageManager$Stub:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   4779: wide
      //   4783: aload_3
      //   4784: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4787: aload_3
      //   4788: wide
      //   4792: wide
      //   4796: wide
      //   4800: wide
      //   4804: wide
      //   4808: invokevirtual 169	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   4811: iconst_1
      //   4812: istore 5
      //   4814: goto -4303 -> 511
      //   4817: aload_2
      //   4818: wide
      //   4822: ldc 15
      //   4824: wide
      //   4828: wide
      //   4832: wide
      //   4836: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4839: aload_2
      //   4840: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4843: wide
      //   4847: aload_0
      //   4848: wide
      //   4852: wide
      //   4856: wide
      //   4860: wide
      //   4864: wide
      //   4868: invokevirtual 427	android/content/pm/IPackageManager$Stub:addPackageToPreferred	(Ljava/lang/String;)V
      //   4871: aload_3
      //   4872: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4875: iconst_1
      //   4876: istore 5
      //   4878: goto -4367 -> 511
      //   4881: aload_2
      //   4882: wide
      //   4886: ldc 15
      //   4888: wide
      //   4892: wide
      //   4896: wide
      //   4900: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4903: aload_2
      //   4904: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   4907: wide
      //   4911: aload_0
      //   4912: wide
      //   4916: wide
      //   4920: wide
      //   4924: wide
      //   4928: wide
      //   4932: invokevirtual 430	android/content/pm/IPackageManager$Stub:removePackageFromPreferred	(Ljava/lang/String;)V
      //   4935: aload_3
      //   4936: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   4939: iconst_1
      //   4940: istore 5
      //   4942: goto -4431 -> 511
      //   4945: aload_2
      //   4946: wide
      //   4950: ldc 15
      //   4952: wide
      //   4956: wide
      //   4960: wide
      //   4964: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   4967: aload_2
      //   4968: invokevirtual 180	android/os/Parcel:readInt	()I
      //   4971: wide
      //   4975: aload_0
      //   4976: wide
      //   4980: wide
      //   4984: wide
      //   4988: wide
      //   4992: wide
      //   4996: invokevirtual 433	android/content/pm/IPackageManager$Stub:getPreferredPackages	(I)Ljava/util/List;
      //   4999: wide
      //   5003: aload_3
      //   5004: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5007: aload_3
      //   5008: wide
      //   5012: wide
      //   5016: wide
      //   5020: wide
      //   5024: wide
      //   5028: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   5031: iconst_1
      //   5032: istore 5
      //   5034: goto -4523 -> 511
      //   5037: aload_2
      //   5038: wide
      //   5042: ldc 15
      //   5044: wide
      //   5048: wide
      //   5052: wide
      //   5056: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5059: aload_2
      //   5060: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5063: ifeq +186 -> 5249
      //   5066: getstatic 436	android/content/IntentFilter:CREATOR	Landroid/os/Parcelable$Creator;
      //   5069: wide
      //   5073: aload_2
      //   5074: wide
      //   5078: wide
      //   5082: wide
      //   5086: invokeinterface 254 2 0
      //   5091: checkcast 435	android/content/IntentFilter
      //   5094: astore 103
      //   5096: aload_2
      //   5097: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5100: wide
      //   5104: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5107: wide
      //   5111: aload_2
      //   5112: wide
      //   5116: wide
      //   5120: wide
      //   5124: wide
      //   5128: wide
      //   5132: invokevirtual 331	android/os/Parcel:createTypedArray	(Landroid/os/Parcelable$Creator;)[Ljava/lang/Object;
      //   5135: checkcast 438	[Landroid/content/ComponentName;
      //   5138: wide
      //   5142: aload_2
      //   5143: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5146: ifeq +109 -> 5255
      //   5149: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5152: wide
      //   5156: aload_2
      //   5157: wide
      //   5161: wide
      //   5165: wide
      //   5169: invokeinterface 254 2 0
      //   5174: checkcast 244	android/content/ComponentName
      //   5177: wide
      //   5181: aload_0
      //   5182: wide
      //   5186: aload 103
      //   5188: wide
      //   5192: wide
      //   5196: wide
      //   5200: wide
      //   5204: wide
      //   5208: wide
      //   5212: wide
      //   5216: wide
      //   5220: wide
      //   5224: wide
      //   5228: wide
      //   5232: wide
      //   5236: invokevirtual 442	android/content/pm/IPackageManager$Stub:addPreferredActivity	(Landroid/content/IntentFilter;I[Landroid/content/ComponentName;Landroid/content/ComponentName;)V
      //   5239: aload_3
      //   5240: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5243: iconst_1
      //   5244: istore 5
      //   5246: goto -4735 -> 511
      //   5249: aconst_null
      //   5250: astore 103
      //   5252: goto -156 -> 5096
      //   5255: aconst_null
      //   5256: wide
      //   5260: goto -79 -> 5181
      //   5263: aload_2
      //   5264: wide
      //   5268: ldc 15
      //   5270: wide
      //   5274: wide
      //   5278: wide
      //   5282: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5285: aload_2
      //   5286: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5289: ifeq +186 -> 5475
      //   5292: getstatic 436	android/content/IntentFilter:CREATOR	Landroid/os/Parcelable$Creator;
      //   5295: wide
      //   5299: aload_2
      //   5300: wide
      //   5304: wide
      //   5308: wide
      //   5312: invokeinterface 254 2 0
      //   5317: checkcast 435	android/content/IntentFilter
      //   5320: astore 103
      //   5322: aload_2
      //   5323: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5326: wide
      //   5330: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5333: wide
      //   5337: aload_2
      //   5338: wide
      //   5342: wide
      //   5346: wide
      //   5350: wide
      //   5354: wide
      //   5358: invokevirtual 331	android/os/Parcel:createTypedArray	(Landroid/os/Parcelable$Creator;)[Ljava/lang/Object;
      //   5361: checkcast 438	[Landroid/content/ComponentName;
      //   5364: wide
      //   5368: aload_2
      //   5369: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5372: ifeq +109 -> 5481
      //   5375: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5378: wide
      //   5382: aload_2
      //   5383: wide
      //   5387: wide
      //   5391: wide
      //   5395: invokeinterface 254 2 0
      //   5400: checkcast 244	android/content/ComponentName
      //   5403: wide
      //   5407: aload_0
      //   5408: wide
      //   5412: aload 103
      //   5414: wide
      //   5418: wide
      //   5422: wide
      //   5426: wide
      //   5430: wide
      //   5434: wide
      //   5438: wide
      //   5442: wide
      //   5446: wide
      //   5450: wide
      //   5454: wide
      //   5458: wide
      //   5462: invokevirtual 445	android/content/pm/IPackageManager$Stub:replacePreferredActivity	(Landroid/content/IntentFilter;I[Landroid/content/ComponentName;Landroid/content/ComponentName;)V
      //   5465: aload_3
      //   5466: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5469: iconst_1
      //   5470: istore 5
      //   5472: goto -4961 -> 511
      //   5475: aconst_null
      //   5476: astore 103
      //   5478: goto -156 -> 5322
      //   5481: aconst_null
      //   5482: wide
      //   5486: goto -79 -> 5407
      //   5489: aload_2
      //   5490: wide
      //   5494: ldc 15
      //   5496: wide
      //   5500: wide
      //   5504: wide
      //   5508: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5511: aload_2
      //   5512: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   5515: wide
      //   5519: aload_0
      //   5520: wide
      //   5524: wide
      //   5528: wide
      //   5532: wide
      //   5536: wide
      //   5540: invokevirtual 448	android/content/pm/IPackageManager$Stub:clearPackagePreferredActivities	(Ljava/lang/String;)V
      //   5543: aload_3
      //   5544: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5547: iconst_1
      //   5548: istore 5
      //   5550: goto -5039 -> 511
      //   5553: aload_2
      //   5554: wide
      //   5558: ldc 15
      //   5560: wide
      //   5564: wide
      //   5568: wide
      //   5572: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5575: new 450	java/util/ArrayList
      //   5578: dup
      //   5579: invokespecial 451	java/util/ArrayList:<init>	()V
      //   5582: wide
      //   5586: new 450	java/util/ArrayList
      //   5589: dup
      //   5590: invokespecial 451	java/util/ArrayList:<init>	()V
      //   5593: wide
      //   5597: aload_2
      //   5598: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   5601: wide
      //   5605: aload_0
      //   5606: wide
      //   5610: wide
      //   5614: wide
      //   5618: wide
      //   5622: wide
      //   5626: wide
      //   5630: wide
      //   5634: wide
      //   5638: wide
      //   5642: wide
      //   5646: wide
      //   5650: invokevirtual 455	android/content/pm/IPackageManager$Stub:getPreferredActivities	(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)I
      //   5653: wide
      //   5657: aload_3
      //   5658: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5661: aload_3
      //   5662: wide
      //   5666: wide
      //   5670: wide
      //   5674: wide
      //   5678: wide
      //   5682: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   5685: aload_3
      //   5686: wide
      //   5690: wide
      //   5694: wide
      //   5698: wide
      //   5702: wide
      //   5706: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   5709: aload_3
      //   5710: wide
      //   5714: wide
      //   5718: wide
      //   5722: wide
      //   5726: wide
      //   5730: invokevirtual 224	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
      //   5733: iconst_1
      //   5734: istore 5
      //   5736: goto -5225 -> 511
      //   5739: aload_2
      //   5740: wide
      //   5744: ldc 15
      //   5746: wide
      //   5750: wide
      //   5754: wide
      //   5758: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5761: aload_2
      //   5762: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5765: ifeq +105 -> 5870
      //   5768: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5771: wide
      //   5775: aload_2
      //   5776: wide
      //   5780: wide
      //   5784: wide
      //   5788: invokeinterface 254 2 0
      //   5793: checkcast 244	android/content/ComponentName
      //   5796: astore 103
      //   5798: aload_2
      //   5799: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5802: wide
      //   5806: aload_2
      //   5807: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5810: wide
      //   5814: aload_0
      //   5815: wide
      //   5819: aload 103
      //   5821: wide
      //   5825: wide
      //   5829: wide
      //   5833: wide
      //   5837: wide
      //   5841: wide
      //   5845: wide
      //   5849: wide
      //   5853: wide
      //   5857: invokevirtual 459	android/content/pm/IPackageManager$Stub:setComponentEnabledSetting	(Landroid/content/ComponentName;II)V
      //   5860: aload_3
      //   5861: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5864: iconst_1
      //   5865: istore 5
      //   5867: goto -5356 -> 511
      //   5870: aconst_null
      //   5871: astore 103
      //   5873: goto -75 -> 5798
      //   5876: aload_2
      //   5877: wide
      //   5881: ldc 15
      //   5883: wide
      //   5887: wide
      //   5891: wide
      //   5895: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   5898: aload_2
      //   5899: invokevirtual 180	android/os/Parcel:readInt	()I
      //   5902: ifeq +93 -> 5995
      //   5905: getstatic 248	android/content/ComponentName:CREATOR	Landroid/os/Parcelable$Creator;
      //   5908: wide
      //   5912: aload_2
      //   5913: wide
      //   5917: wide
      //   5921: wide
      //   5925: invokeinterface 254 2 0
      //   5930: checkcast 244	android/content/ComponentName
      //   5933: astore 103
      //   5935: aload_0
      //   5936: wide
      //   5940: aload 103
      //   5942: wide
      //   5946: wide
      //   5950: wide
      //   5954: invokevirtual 463	android/content/pm/IPackageManager$Stub:getComponentEnabledSetting	(Landroid/content/ComponentName;)I
      //   5957: wide
      //   5961: aload_3
      //   5962: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   5965: aload_3
      //   5966: wide
      //   5970: wide
      //   5974: wide
      //   5978: wide
      //   5982: wide
      //   5986: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   5989: iconst_1
      //   5990: istore 5
      //   5992: goto -5481 -> 511
      //   5995: aconst_null
      //   5996: astore 103
      //   5998: goto -63 -> 5935
      //   6001: aload_2
      //   6002: wide
      //   6006: ldc 15
      //   6008: wide
      //   6012: wide
      //   6016: wide
      //   6020: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6023: aload_2
      //   6024: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6027: wide
      //   6031: aload_2
      //   6032: invokevirtual 180	android/os/Parcel:readInt	()I
      //   6035: wide
      //   6039: aload_2
      //   6040: invokevirtual 180	android/os/Parcel:readInt	()I
      //   6043: wide
      //   6047: aload_0
      //   6048: wide
      //   6052: wide
      //   6056: wide
      //   6060: wide
      //   6064: wide
      //   6068: wide
      //   6072: wide
      //   6076: wide
      //   6080: wide
      //   6084: wide
      //   6088: wide
      //   6092: invokevirtual 467	android/content/pm/IPackageManager$Stub:setApplicationEnabledSetting	(Ljava/lang/String;II)V
      //   6095: aload_3
      //   6096: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6099: iconst_1
      //   6100: istore 5
      //   6102: goto -5591 -> 511
      //   6105: aload_2
      //   6106: wide
      //   6110: ldc 15
      //   6112: wide
      //   6116: wide
      //   6120: wide
      //   6124: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6127: aload_2
      //   6128: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6131: wide
      //   6135: aload_0
      //   6136: wide
      //   6140: wide
      //   6144: wide
      //   6148: wide
      //   6152: wide
      //   6156: invokevirtual 470	android/content/pm/IPackageManager$Stub:getApplicationEnabledSetting	(Ljava/lang/String;)I
      //   6159: wide
      //   6163: aload_3
      //   6164: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6167: aload_3
      //   6168: wide
      //   6172: wide
      //   6176: wide
      //   6180: wide
      //   6184: wide
      //   6188: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   6191: iconst_1
      //   6192: istore 5
      //   6194: goto -5683 -> 511
      //   6197: aload_2
      //   6198: wide
      //   6202: ldc 15
      //   6204: wide
      //   6208: wide
      //   6212: wide
      //   6216: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6219: aload_2
      //   6220: invokevirtual 474	android/os/Parcel:readLong	()J
      //   6223: wide
      //   6227: aload_2
      //   6228: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   6231: invokestatic 479	android/content/pm/IPackageDataObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageDataObserver;
      //   6234: wide
      //   6238: aload_0
      //   6239: wide
      //   6243: wide
      //   6247: wide
      //   6251: wide
      //   6255: wide
      //   6259: wide
      //   6263: wide
      //   6267: wide
      //   6271: invokevirtual 483	android/content/pm/IPackageManager$Stub:freeStorageAndNotify	(JLandroid/content/pm/IPackageDataObserver;)V
      //   6274: aload_3
      //   6275: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6278: iconst_1
      //   6279: istore 5
      //   6281: goto -5770 -> 511
      //   6284: aload_2
      //   6285: wide
      //   6289: ldc 15
      //   6291: wide
      //   6295: wide
      //   6299: wide
      //   6303: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6306: aload_2
      //   6307: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6310: wide
      //   6314: aload_2
      //   6315: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   6318: invokestatic 479	android/content/pm/IPackageDataObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageDataObserver;
      //   6321: wide
      //   6325: aload_0
      //   6326: wide
      //   6330: wide
      //   6334: wide
      //   6338: wide
      //   6342: wide
      //   6346: wide
      //   6350: wide
      //   6354: wide
      //   6358: invokevirtual 487	android/content/pm/IPackageManager$Stub:deleteApplicationCacheFiles	(Ljava/lang/String;Landroid/content/pm/IPackageDataObserver;)V
      //   6361: aload_3
      //   6362: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6365: iconst_1
      //   6366: istore 5
      //   6368: goto -5857 -> 511
      //   6371: aload_2
      //   6372: wide
      //   6376: ldc 15
      //   6378: wide
      //   6382: wide
      //   6386: wide
      //   6390: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6393: aload_2
      //   6394: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6397: wide
      //   6401: aload_2
      //   6402: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   6405: invokestatic 479	android/content/pm/IPackageDataObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageDataObserver;
      //   6408: wide
      //   6412: aload_0
      //   6413: wide
      //   6417: wide
      //   6421: wide
      //   6425: wide
      //   6429: wide
      //   6433: wide
      //   6437: wide
      //   6441: wide
      //   6445: invokevirtual 490	android/content/pm/IPackageManager$Stub:clearApplicationUserData	(Ljava/lang/String;Landroid/content/pm/IPackageDataObserver;)V
      //   6448: aload_3
      //   6449: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6452: iconst_1
      //   6453: istore 5
      //   6455: goto -5944 -> 511
      //   6458: aload_2
      //   6459: wide
      //   6463: ldc 15
      //   6465: wide
      //   6469: wide
      //   6473: wide
      //   6477: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6480: aload_2
      //   6481: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6484: wide
      //   6488: aload_2
      //   6489: invokevirtual 402	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
      //   6492: invokestatic 495	android/content/pm/IPackageStatsObserver$Stub:asInterface	(Landroid/os/IBinder;)Landroid/content/pm/IPackageStatsObserver;
      //   6495: wide
      //   6499: aload_0
      //   6500: wide
      //   6504: wide
      //   6508: wide
      //   6512: wide
      //   6516: wide
      //   6520: wide
      //   6524: wide
      //   6528: wide
      //   6532: invokevirtual 499	android/content/pm/IPackageManager$Stub:getPackageSizeInfo	(Ljava/lang/String;Landroid/content/pm/IPackageStatsObserver;)V
      //   6535: aload_3
      //   6536: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6539: iconst_1
      //   6540: istore 5
      //   6542: goto -6031 -> 511
      //   6545: aload_2
      //   6546: wide
      //   6550: ldc 15
      //   6552: wide
      //   6556: wide
      //   6560: wide
      //   6564: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6567: aload_0
      //   6568: invokevirtual 502	android/content/pm/IPackageManager$Stub:getSystemSharedLibraryNames	()[Ljava/lang/String;
      //   6571: wide
      //   6575: aload_3
      //   6576: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6579: aload_3
      //   6580: wide
      //   6584: wide
      //   6588: wide
      //   6592: wide
      //   6596: wide
      //   6600: invokevirtual 306	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
      //   6603: iconst_1
      //   6604: istore 5
      //   6606: goto -6095 -> 511
      //   6609: aload_2
      //   6610: wide
      //   6614: ldc 15
      //   6616: wide
      //   6620: wide
      //   6624: wide
      //   6628: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6631: aload_2
      //   6632: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6635: wide
      //   6639: aload_0
      //   6640: wide
      //   6644: wide
      //   6648: wide
      //   6652: wide
      //   6656: wide
      //   6660: invokevirtual 505	android/content/pm/IPackageManager$Stub:hasSystemFeature	(Ljava/lang/String;)Z
      //   6663: wide
      //   6667: aload_3
      //   6668: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6671: wide
      //   6675: ifeq +34 -> 6709
      //   6678: iconst_1
      //   6679: istore 5
      //   6681: aload_3
      //   6682: wide
      //   6686: iload 5
      //   6688: wide
      //   6692: wide
      //   6696: wide
      //   6700: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   6703: iconst_1
      //   6704: istore 5
      //   6706: goto -6195 -> 511
      //   6709: iconst_0
      //   6710: istore 5
      //   6712: goto -31 -> 6681
      //   6715: aload_2
      //   6716: wide
      //   6720: ldc 15
      //   6722: wide
      //   6726: wide
      //   6730: wide
      //   6734: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6737: aload_0
      //   6738: invokevirtual 508	android/content/pm/IPackageManager$Stub:enterSafeMode	()V
      //   6741: aload_3
      //   6742: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6745: iconst_1
      //   6746: istore 5
      //   6748: goto -6237 -> 511
      //   6751: aload_2
      //   6752: wide
      //   6756: ldc 15
      //   6758: wide
      //   6762: wide
      //   6766: wide
      //   6770: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6773: aload_0
      //   6774: invokevirtual 512	android/content/pm/IPackageManager$Stub:isSafeMode	()Z
      //   6777: wide
      //   6781: aload_3
      //   6782: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6785: wide
      //   6789: ifeq +34 -> 6823
      //   6792: iconst_1
      //   6793: istore 5
      //   6795: aload_3
      //   6796: wide
      //   6800: iload 5
      //   6802: wide
      //   6806: wide
      //   6810: wide
      //   6814: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   6817: iconst_1
      //   6818: istore 5
      //   6820: goto -6309 -> 511
      //   6823: iconst_0
      //   6824: istore 5
      //   6826: goto -31 -> 6795
      //   6829: aload_2
      //   6830: wide
      //   6834: ldc 15
      //   6836: wide
      //   6840: wide
      //   6844: wide
      //   6848: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6851: aload_0
      //   6852: invokevirtual 515	android/content/pm/IPackageManager$Stub:systemReady	()V
      //   6855: aload_3
      //   6856: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6859: iconst_1
      //   6860: istore 5
      //   6862: goto -6351 -> 511
      //   6865: aload_2
      //   6866: wide
      //   6870: ldc 15
      //   6872: wide
      //   6876: wide
      //   6880: wide
      //   6884: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6887: aload_0
      //   6888: invokevirtual 518	android/content/pm/IPackageManager$Stub:hasSystemUidErrors	()Z
      //   6891: wide
      //   6895: aload_3
      //   6896: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   6899: wide
      //   6903: ifeq +34 -> 6937
      //   6906: iconst_1
      //   6907: istore 5
      //   6909: aload_3
      //   6910: wide
      //   6914: iload 5
      //   6916: wide
      //   6920: wide
      //   6924: wide
      //   6928: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   6931: iconst_1
      //   6932: istore 5
      //   6934: goto -6423 -> 511
      //   6937: iconst_0
      //   6938: istore 5
      //   6940: goto -31 -> 6909
      //   6943: aload_2
      //   6944: wide
      //   6948: ldc 15
      //   6950: wide
      //   6954: wide
      //   6958: wide
      //   6962: invokevirtual 172	android/os/Parcel:enforceInterface	(Ljava/lang/String;)V
      //   6965: aload_2
      //   6966: invokevirtual 176	android/os/Parcel:readString	()Ljava/lang/String;
      //   6969: wide
      //   6973: aload_0
      //   6974: wide
      //   6978: wide
      //   6982: wide
      //   6986: wide
      //   6990: wide
      //   6994: invokevirtual 521	android/content/pm/IPackageManager$Stub:performDexOpt	(Ljava/lang/String;)Z
      //   6997: wide
      //   7001: aload_3
      //   7002: invokevirtual 187	android/os/Parcel:writeNoException	()V
      //   7005: wide
      //   7009: ifeq +34 -> 7043
      //   7012: iconst_1
      //   7013: istore 5
      //   7015: aload_3
      //   7016: wide
      //   7020: iload 5
      //   7022: wide
      //   7026: wide
      //   7030: wide
      //   7034: invokevirtual 191	android/os/Parcel:writeInt	(I)V
      //   7037: iconst_1
      //   7038: istore 5
      //   7040: goto -6529 -> 511
      //   7043: iconst_0
      //   7044: istore 5
      //   7046: goto -31 -> 7015
    }

    class Proxy
      implements IPackageManager
    {
      Proxy()
      {
      }

      public void addPackageToPreferred(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(39, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean addPermission(PermissionInfo paramPermissionInfo)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramPermissionInfo == null)
              continue;
            localParcel1.writeInt(1);
            paramPermissionInfo.writeToParcel(localParcel1, 0);
            IPackageManager.Stub.this.transact(14, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            if (i != 0)
            {
              j = 1;
              return j;
              i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          int j = 0;
        }
      }

      public void addPreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramIntentFilter == null)
              continue;
            localParcel1.writeInt(1);
            paramIntentFilter.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            localParcel1.writeTypedArray(paramArrayOfComponentName, 0);
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              IPackageManager.Stub.this.transact(42, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          int i = 0;
          localParcel1.writeInt(i);
        }
      }

      public IBinder asBinder()
      {
        return IPackageManager.Stub.this;
      }

      public int checkPermission(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          IPackageManager.Stub.this.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int checkSignatures(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          IPackageManager.Stub.this.transact(17, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int checkUidPermission(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(13, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int checkUidSignatures(int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          IPackageManager.Stub.this.transact(18, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void clearApplicationUserData(String paramString, IPackageDataObserver paramIPackageDataObserver)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          if (paramIPackageDataObserver != null)
          {
            localIBinder = paramIPackageDataObserver.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            IPackageManager.Stub.this.transact(52, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void clearPackagePreferredActivities(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(44, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void deleteApplicationCacheFiles(String paramString, IPackageDataObserver paramIPackageDataObserver)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          if (paramIPackageDataObserver != null)
          {
            localIBinder = paramIPackageDataObserver.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            IPackageManager.Stub.this.transact(51, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void deletePackage(String paramString, IPackageDeleteObserver paramIPackageDeleteObserver, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          if (paramIPackageDeleteObserver != null)
          {
            localIBinder = paramIPackageDeleteObserver.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(37, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void enterSafeMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          IPackageManager.Stub.this.transact(56, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void freeStorageAndNotify(long paramLong, IPackageDataObserver paramIPackageDataObserver)
        throws RemoteException
      {
        IPackageDataObserver paramIPackageDataObserver = Parcel.obtain();
        Parcel localParcel = Parcel.obtain();
        try
        {
          paramIPackageDataObserver.writeInterfaceToken("android.content.pm.IPackageManager");
          paramIPackageDataObserver.writeLong(paramLong);
          if (??? != null)
          {
            localIBinder = ???.asBinder();
            paramIPackageDataObserver.writeStrongBinder(localIBinder);
            IPackageManager.Stub.this.transact(50, paramIPackageDataObserver, localParcel, 0);
            localParcel.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel.recycle();
          paramIPackageDataObserver.recycle();
        }
      }

      public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramComponentName == null)
              continue;
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(9, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localActivityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
              return localActivityInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ActivityInfo localActivityInfo = null;
        }
      }

      @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PermissionGroupInfo;", ">;"})
      public List getAllPermissionGroups(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = PermissionGroupInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getApplicationEnabledSetting(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(49, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public ApplicationInfo getApplicationInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localApplicationInfo = (ApplicationInfo)ApplicationInfo.CREATOR.createFromParcel(localParcel2);
            return localApplicationInfo;
          }
          ApplicationInfo localApplicationInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getComponentEnabledSetting(ComponentName paramComponentName)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            IPackageManager.Stub.this.transact(47, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            return i;
            int j = 0;
            localParcel1.writeInt(j);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/ApplicationInfo;", ">;"})
      public List getInstalledApplications(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = ApplicationInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PackageInfo;", ">;"})
      public List getInstalledPackages(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(28, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = PackageInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String getInstallerPackageName(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(38, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public InstrumentationInfo getInstrumentationInfo(ComponentName paramComponentName, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramComponentName == null)
              continue;
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(34, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localInstrumentationInfo = (InstrumentationInfo)InstrumentationInfo.CREATOR.createFromParcel(localParcel2);
              return localInstrumentationInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          InstrumentationInfo localInstrumentationInfo = null;
        }
      }

      public String getInterfaceDescriptor()
      {
        return "android.content.pm.IPackageManager";
      }

      public String getNameForUid(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(20, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int[] getPackageGids(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int[] arrayOfInt = localParcel2.createIntArray();
          return arrayOfInt;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public PackageInfo getPackageInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localPackageInfo = (PackageInfo)PackageInfo.CREATOR.createFromParcel(localParcel2);
            return localPackageInfo;
          }
          PackageInfo localPackageInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void getPackageSizeInfo(String paramString, IPackageStatsObserver paramIPackageStatsObserver)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          if (paramIPackageStatsObserver != null)
          {
            localIBinder = paramIPackageStatsObserver.asBinder();
            localParcel1.writeStrongBinder(localIBinder);
            IPackageManager.Stub.this.transact(53, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
          IBinder localIBinder = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int getPackageUid(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public String[] getPackagesForUid(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String[] arrayOfString = localParcel2.createStringArray();
          return arrayOfString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public PermissionGroupInfo getPermissionGroupInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localPermissionGroupInfo = (PermissionGroupInfo)PermissionGroupInfo.CREATOR.createFromParcel(localParcel2);
            return localPermissionGroupInfo;
          }
          PermissionGroupInfo localPermissionGroupInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public PermissionInfo getPermissionInfo(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localPermissionInfo = (PermissionInfo)PermissionInfo.CREATOR.createFromParcel(localParcel2);
            return localPermissionInfo;
          }
          PermissionInfo localPermissionInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/ApplicationInfo;", ">;"})
      public List getPersistentApplications(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(30, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = ApplicationInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Ljava/util/List", "<", "Landroid/content/IntentFilter;", ">;", "Ljava/util/List", "<", "Landroid/content/ComponentName;", ">;", "Ljava/lang/String;", ")I"})
      public int getPreferredActivities(List paramList1, List paramList2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(45, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          Parcelable.Creator localCreator1 = IntentFilter.CREATOR;
          localParcel2.readTypedList(paramList1, localCreator1);
          Parcelable.Creator localCreator2 = ComponentName.CREATOR;
          localParcel2.readTypedList(paramList2, localCreator2);
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(I)", "Ljava/util/List", "<", "Landroid/content/pm/PackageInfo;", ">;"})
      public List getPreferredPackages(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(41, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = PackageInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public ActivityInfo getReceiverInfo(ComponentName paramComponentName, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramComponentName == null)
              continue;
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(10, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localActivityInfo = (ActivityInfo)ActivityInfo.CREATOR.createFromParcel(localParcel2);
              return localActivityInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ActivityInfo localActivityInfo = null;
        }
      }

      public ServiceInfo getServiceInfo(ComponentName paramComponentName, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramComponentName == null)
              continue;
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(11, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localServiceInfo = (ServiceInfo)ServiceInfo.CREATOR.createFromParcel(localParcel2);
              return localServiceInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ServiceInfo localServiceInfo = null;
        }
      }

      public String[] getSystemSharedLibraryNames()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          IPackageManager.Stub.this.transact(54, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String[] arrayOfString = localParcel2.createStringArray();
          return arrayOfString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public int getUidForSharedUser(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public boolean hasSystemFeature(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(55, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            j = 1;
            return j;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean hasSystemUidErrors()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          IPackageManager.Stub.this.transact(59, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            j = 1;
            return j;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void installPackage(Uri paramUri, IPackageInstallObserver paramIPackageInstallObserver, int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramUri == null)
              continue;
            localParcel1.writeInt(1);
            paramUri.writeToParcel(localParcel1, 0);
            if (paramIPackageInstallObserver != null)
            {
              IBinder localIBinder = paramIPackageInstallObserver.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              localParcel1.writeInt(paramInt);
              localParcel1.writeString(paramString);
              IPackageManager.Stub.this.transact(36, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          int i = 0;
        }
      }

      public boolean isProtectedBroadcast(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(16, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            j = 1;
            return j;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean isSafeMode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          IPackageManager.Stub.this.transact(57, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            j = 1;
            return j;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean performDexOpt(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(60, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
          {
            j = 1;
            return j;
          }
          int j = 0;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      @Signature({"(", "Ljava/lang/String;", "II)", "Ljava/util/List", "<", "Landroid/content/pm/ProviderInfo;", ">;"})
      public List queryContentProviders(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          IPackageManager.Stub.this.transact(33, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = ProviderInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/InstrumentationInfo;", ">;"})
      public List queryInstrumentation(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(35, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = InstrumentationInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
      public List queryIntentActivities(Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(23, localParcel1, localParcel2, 0);
            localParcel2.readException();
            Parcelable.Creator localCreator = ResolveInfo.CREATOR;
            ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
            return localArrayList;
            int i = 0;
            localParcel1.writeInt(i);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Landroid/content/ComponentName;", "[", "Landroid/content/Intent;", "[", "Ljava/lang/String;", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
      public List queryIntentActivityOptions(ComponentName paramComponentName, Intent[] paramArrayOfIntent, String[] paramArrayOfString, Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramComponentName == null)
              continue;
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
            localParcel1.writeTypedArray(paramArrayOfIntent, 0);
            localParcel1.writeStringArray(paramArrayOfString);
            if (paramIntent != null)
            {
              localParcel1.writeInt(1);
              paramIntent.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              localParcel1.writeInt(paramInt);
              IPackageManager.Stub.this.transact(24, localParcel1, localParcel2, 0);
              localParcel2.readException();
              Parcelable.Creator localCreator = ResolveInfo.CREATOR;
              ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
              return localArrayList;
              i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          int i = 0;
          localParcel1.writeInt(i);
        }
      }

      @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
      public List queryIntentReceivers(Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(25, localParcel1, localParcel2, 0);
            localParcel2.readException();
            Parcelable.Creator localCreator = ResolveInfo.CREATOR;
            ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
            return localArrayList;
            int i = 0;
            localParcel1.writeInt(i);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Landroid/content/Intent;", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/ResolveInfo;", ">;"})
      public List queryIntentServices(Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          if (paramIntent != null)
          {
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(27, localParcel1, localParcel2, 0);
            localParcel2.readException();
            Parcelable.Creator localCreator = ResolveInfo.CREATOR;
            ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
            return localArrayList;
            int i = 0;
            localParcel1.writeInt(i);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Ljava/lang/String;", "I)", "Ljava/util/List", "<", "Landroid/content/pm/PermissionInfo;", ">;"})
      public List queryPermissionsByGroup(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Parcelable.Creator localCreator = PermissionInfo.CREATOR;
          ArrayList localArrayList = localParcel2.createTypedArrayList(localCreator);
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      @Signature({"(", "Ljava/util/List", "<", "Ljava/lang/String;", ">;", "Ljava/util/List", "<", "Landroid/content/pm/ProviderInfo;", ">;)V"})
      public void querySyncProviders(List paramList1, List paramList2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeStringList(paramList1);
          localParcel1.writeTypedList(paramList2);
          IPackageManager.Stub.this.transact(32, localParcel1, localParcel2, 0);
          localParcel2.readException();
          localParcel2.readStringList(paramList1);
          Parcelable.Creator localCreator = ProviderInfo.CREATOR;
          localParcel2.readTypedList(paramList2, localCreator);
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void removePackageFromPreferred(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(40, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void removePermission(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          IPackageManager.Stub.this.transact(15, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void replacePreferredActivity(IntentFilter paramIntentFilter, int paramInt, ComponentName[] paramArrayOfComponentName, ComponentName paramComponentName)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramIntentFilter == null)
              continue;
            localParcel1.writeInt(1);
            paramIntentFilter.writeToParcel(localParcel1, 0);
            localParcel1.writeInt(paramInt);
            localParcel1.writeTypedArray(paramArrayOfComponentName, 0);
            if (paramComponentName != null)
            {
              localParcel1.writeInt(1);
              paramComponentName.writeToParcel(localParcel1, 0);
              IPackageManager.Stub.this.transact(43, localParcel1, localParcel2, 0);
              localParcel2.readException();
              return;
              i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          int i = 0;
          localParcel1.writeInt(i);
        }
      }

      public ProviderInfo resolveContentProvider(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          IPackageManager.Stub.this.transact(31, localParcel1, localParcel2, 0);
          localParcel2.readException();
          if (localParcel2.readInt() != 0)
          {
            localProviderInfo = (ProviderInfo)ProviderInfo.CREATOR.createFromParcel(localParcel2);
            return localProviderInfo;
          }
          ProviderInfo localProviderInfo = null;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public ResolveInfo resolveIntent(Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramIntent == null)
              continue;
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(22, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localResolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
              return localResolveInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ResolveInfo localResolveInfo = null;
        }
      }

      public ResolveInfo resolveService(Intent paramIntent, String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
            if (paramIntent == null)
              continue;
            localParcel1.writeInt(1);
            paramIntent.writeToParcel(localParcel1, 0);
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            IPackageManager.Stub.this.transact(26, localParcel1, localParcel2, 0);
            localParcel2.readException();
            if (localParcel2.readInt() != 0)
            {
              localResolveInfo = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(localParcel2);
              return localResolveInfo;
              int i = 0;
              localParcel1.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          ResolveInfo localResolveInfo = null;
        }
      }

      public void setApplicationEnabledSetting(String paramString, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          IPackageManager.Stub.this.transact(48, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void setComponentEnabledSetting(ComponentName paramComponentName, int paramInt1, int paramInt2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          if (paramComponentName != null)
          {
            localParcel1.writeInt(1);
            paramComponentName.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            IPackageManager.Stub.this.transact(46, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            int i = 0;
            localParcel1.writeInt(i);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }

      public void systemReady()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.content.pm.IPackageManager");
          IPackageManager.Stub.this.transact(58, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     android.content.pm.IPackageManager
 * JD-Core Version:    0.6.0
 */