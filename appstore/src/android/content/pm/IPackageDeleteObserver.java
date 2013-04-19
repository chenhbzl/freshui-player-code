package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IPackageDeleteObserver extends IInterface
{
  public abstract void packageDeleted(boolean paramBoolean)
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IPackageDeleteObserver
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageDeleteObserver";
    static final int TRANSACTION_packageDeleted = 1;

    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageDeleteObserver");
    }

    public static IPackageDeleteObserver asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageDeleteObserver");
        if ((localIInterface != null) && ((localIInterface instanceof IPackageDeleteObserver)))
        {
          localObject = (IPackageDeleteObserver)localIInterface;
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
          paramParcel2.writeString("android.content.pm.IPackageDeleteObserver");
        }
      case 1:
      }
      paramParcel1.enforceInterface("android.content.pm.IPackageDeleteObserver");
      if (paramParcel1.readInt() != 0);
      int j;
      for (int i = 1; ; j = 0)
      {
        packageDeleted(i);
        bool = true;
        break;
      }
    }

    class Proxy
      implements IPackageDeleteObserver
    {
      Proxy()
      {
      }

      public IBinder asBinder()
      {
        return IPackageDeleteObserver.Stub.this;
      }

      public String getInterfaceDescriptor()
      {
        return "android.content.pm.IPackageDeleteObserver";
      }

      public void packageDeleted(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("android.content.pm.IPackageDeleteObserver");
          if (paramBoolean)
          {
            i = 1;
            localParcel.writeInt(i);
            IPackageDeleteObserver.Stub.this.transact(1, localParcel, null, 1);
            return;
          }
          int i = 0;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     android.content.pm.IPackageDeleteObserver
 * JD-Core Version:    0.6.0
 */