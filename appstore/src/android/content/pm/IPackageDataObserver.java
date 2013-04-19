package android.content.pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IPackageDataObserver extends IInterface
{
  public abstract void onRemoveCompleted(String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract class Stub extends Binder
    implements IPackageDataObserver
  {
    private static final String DESCRIPTOR = "android.content.pm.IPackageDataObserver";
    static final int TRANSACTION_onRemoveCompleted = 1;

    public Stub()
    {
      attachInterface(this, "android.content.pm.IPackageDataObserver");
    }

    public static IPackageDataObserver asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null)
        localObject = null;
      while (true)
      {
        return localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("android.content.pm.IPackageDataObserver");
        if ((localIInterface != null) && ((localIInterface instanceof IPackageDataObserver)))
        {
          localObject = (IPackageDataObserver)localIInterface;
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
          paramParcel2.writeString("android.content.pm.IPackageDataObserver");
        }
      case 1:
      }
      paramParcel1.enforceInterface("android.content.pm.IPackageDataObserver");
      String str = paramParcel1.readString();
      if (paramParcel1.readInt() != 0);
      int j;
      for (int i = 1; ; j = 0)
      {
        onRemoveCompleted(str, i);
        bool = true;
        break;
      }
    }

    class Proxy
      implements IPackageDataObserver
    {
      Proxy()
      {
      }

      public IBinder asBinder()
      {
        return IPackageDataObserver.Stub.this;
      }

      public String getInterfaceDescriptor()
      {
        return "android.content.pm.IPackageDataObserver";
      }

      public void onRemoveCompleted(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("android.content.pm.IPackageDataObserver");
          localParcel.writeString(paramString);
          if (paramBoolean)
          {
            i = 1;
            localParcel.writeInt(i);
            IPackageDataObserver.Stub.this.transact(1, localParcel, null, 1);
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
 * Qualified Name:     android.content.pm.IPackageDataObserver
 * JD-Core Version:    0.6.0
 */