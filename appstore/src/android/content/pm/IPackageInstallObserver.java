package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IPackageInstallObserver extends IInterface
{
  public abstract void packageInstalled(String paramString, int paramInt)
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IPackageInstallObserver
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageInstallObserver";
    static final int TRANSACTION_packageInstalled = 1;

    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageInstallObserver");
    }

    public static IPackageInstallObserver asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageInstallObserver");
        if ((localIInterface != null) && ((localIInterface instanceof IPackageInstallObserver)))
        {
          localObject = (IPackageInstallObserver)localIInterface;
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
        bool = onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
      case 1:
      }
      while (true)
      {
        return bool;
        paramParcel2.writeString("android.content.pm.IPackageInstallObserver");
        bool = true;
        continue;
        paramParcel1.enforceInterface("android.content.pm.IPackageInstallObserver");
        String str = paramParcel1.readString();
        int i = paramParcel1.readInt();
        packageInstalled(str, i);
        bool = true;
      }
    }

    class Proxy
      implements IPackageInstallObserver
    {
      Proxy()
      {
      }

      public IBinder asBinder()
      {
        return IPackageInstallObserver.Stub.this;
      }

      public String getInterfaceDescriptor()
      {
        return "android.content.pm.IPackageInstallObserver";
      }

      public void packageInstalled(String paramString, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("android.content.pm.IPackageInstallObserver");
          localParcel.writeString(paramString);
          localParcel.writeInt(paramInt);
          IPackageInstallObserver.Stub.this.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
        throw localObject;
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     android.content.pm.IPackageInstallObserver
 * JD-Core Version:    0.6.0
 */