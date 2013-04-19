package com.yingyonghui.market.install;

import android.os.IBinder;
import dalvik.annotation.Signature;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceManager
{
  private static final String AS_INTERFACE = "asInterface";
  private static final String GET_SERVICE_METHOD = "getService";
  private static final String SERVICE_MANAGER = "android.os.ServiceManager";

  @Signature({"(", "Ljava/lang/Class", "<*>;)", "Ljava/lang/reflect/Method;"})
  private static final Method getAsInterfaceMethod(Class paramClass)
    throws SecurityException, NoSuchMethodException
  {
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = IBinder.class;
    return getDeclaredMethod(paramClass, "asInterface", arrayOfClass);
  }

  private static final ClassLoader getClassLoader()
  {
    return ServiceManager.class.getClassLoader();
  }

  @Signature({"(", "Ljava/lang/Class", "<*>;", "Ljava/lang/String;", "[", "Ljava/lang/Class", "<*>;)", "Ljava/lang/reflect/Method;"})
  private static final Method getDeclaredMethod(Class paramClass, String paramString, Class[] paramArrayOfClass)
    throws SecurityException, NoSuchMethodException
  {
    Method localMethod = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
    if (!localMethod.isAccessible())
      localMethod.setAccessible(1);
    return localMethod;
  }

  @Signature({"(", "Ljava/lang/Class", "<*>;)", "Ljava/lang/reflect/Method;"})
  private static final Method getGetServiceMethod(Class paramClass)
    throws SecurityException, NoSuchMethodException
  {
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = String.class;
    return getDeclaredMethod(paramClass, "getService", arrayOfClass);
  }

  @Signature({"()", "Ljava/lang/Class", "<*>;"})
  private static final Class getServiceManager()
    throws ClassNotFoundException
  {
    return getClassLoader().loadClass("android.os.ServiceManager");
  }

  @Signature({"(", "Ljava/lang/Object;", "Ljava/lang/Class", "<*>;)", "Ljava/lang/Object;"})
  private static final Object getServiceStub(Object paramObject, Class paramClass)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, ClassNotFoundException
  {
    Method localMethod = getAsInterfaceMethod(paramClass);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramObject;
    return localMethod.invoke(null, arrayOfObject);
  }

  /** @deprecated */
  // ERROR //
  public static final Object getServiceStub(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: invokestatic 95	com/yingyonghui/market/install/ServiceManager:getServiceStubInternal	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   8: astore_2
    //   9: ldc 2
    //   11: monitorexit
    //   12: aload_2
    //   13: areturn
    //   14: astore_3
    //   15: aconst_null
    //   16: astore_2
    //   17: goto -8 -> 9
    //   20: astore 4
    //   22: ldc 2
    //   24: monitorexit
    //   25: aload 4
    //   27: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   3	9	14	java/lang/Exception
    //   3	9	20	finally
  }

  @Signature({"(", "Ljava/lang/String;", ")", "Ljava/lang/Class", "<*>;"})
  private static final Class getServiceStubClass(String paramString)
    throws ClassNotFoundException
  {
    return getClassLoader().loadClass(paramString);
  }

  // ERROR //
  private static final Object getServiceStubInternal(String paramString1, String paramString2)
    throws java.lang.Exception
  {
    // Byte code:
    //   0: invokestatic 99	com/yingyonghui/market/install/ServiceManager:getServiceManager	()Ljava/lang/Class;
    //   3: invokestatic 101	com/yingyonghui/market/install/ServiceManager:getGetServiceMethod	(Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   6: astore_2
    //   7: iconst_1
    //   8: anewarray 4	java/lang/Object
    //   11: astore_3
    //   12: aload_3
    //   13: iconst_0
    //   14: aload_0
    //   15: aastore
    //   16: aload_2
    //   17: aconst_null
    //   18: aload_3
    //   19: invokevirtual 89	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   22: astore 4
    //   24: aload 4
    //   26: ifnonnull +38 -> 64
    //   29: aconst_null
    //   30: astore 5
    //   32: aload 5
    //   34: areturn
    //   35: astore 6
    //   37: aload 4
    //   39: ifnonnull +25 -> 64
    //   42: aconst_null
    //   43: astore 5
    //   45: goto -13 -> 32
    //   48: astore 5
    //   50: aload 4
    //   52: ifnonnull +9 -> 61
    //   55: aconst_null
    //   56: astore 5
    //   58: goto -26 -> 32
    //   61: aload 5
    //   63: athrow
    //   64: aload_1
    //   65: invokestatic 103	com/yingyonghui/market/install/ServiceManager:getServiceStubClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   68: astore 7
    //   70: aload 4
    //   72: aload 7
    //   74: invokestatic 105	com/yingyonghui/market/install/ServiceManager:getServiceStub	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
    //   77: astore 5
    //   79: goto -47 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   7	24	35	java/lang/Exception
    //   7	24	48	finally
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.install.ServiceManager
 * JD-Core Version:    0.6.0
 */