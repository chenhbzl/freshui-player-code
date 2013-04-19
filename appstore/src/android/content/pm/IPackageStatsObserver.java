package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IPackageStatsObserver extends IInterface
{
  public abstract void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IPackageStatsObserver
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageStatsObserver";
    static final int TRANSACTION_onGetStatsCompleted = 1;

    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageStatsObserver");
    }

    public static IPackageStatsObserver asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageStatsObserver");
        if ((localIInterface != null) && ((localIInterface instanceof IPackageStatsObserver)))
        {
          localObject = (IPackageStatsObserver)localIInterface;
          continue;
        }
        localObject = new Proxy();
      }
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default:
      case 1598968902:
        for (bool = onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); ; bool = true)
        {
          return bool;
          paramParcel2.writeString("android.content.pm.IPackageStatsObserver");
        }
      case 1:
      }
      paramParcel1.enforceInterface("android.content.pm.IPackageStatsObserver");
      PackageStats localPackageStats;
      if (paramParcel1.readInt() != 0)
      {
        localPackageStats = (PackageStats)PackageStats.CREATOR.createFromParcel(paramParcel1);
        label81: if (paramParcel1.readInt() == 0)
          break label112;
      }
      label112: int j;
      for (int i = 1; ; j = 0)
      {
        onGetStatsCompleted(localPackageStats, i);
        bool = true;
        break;
        localPackageStats = null;
        break label81;
      }
    }

    class Proxy
      implements IPackageStatsObserver
    {
      Proxy()
      {
      }

      public IBinder asBinder()
      {
        return IPackageStatsObserver.Stub.this;
      }

      public String getInterfaceDescriptor()
      {
        return "android.content.pm.IPackageStatsObserver";
      }

      public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        while (true)
        {
          try
          {
            localParcel.writeInterfaceToken("android.content.pm.IPackageStatsObserver");
            if (paramPackageStats == null)
              continue;
            localParcel.writeInt(1);
            paramPackageStats.writeToParcel(localParcel, 0);
            if (paramBoolean)
            {
              i = 1;
              localParcel.writeInt(i);
              IPackageStatsObserver.Stub.this.transact(1, localParcel, null, 1);
              return;
              i = 0;
              localParcel.writeInt(i);
              continue;
            }
          }
          finally
          {
            localParcel.recycle();
          }
          int i = 0;
        }
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     android.content.pm.IPackageStatsObserver
 * JD-Core Version:    0.6.0
 */