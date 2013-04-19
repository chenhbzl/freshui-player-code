package com.yingyonghui.market.util;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public final class SSLUtils
{
  private static HostnameVerifier _hostnameVerifier;
  private static TrustManager[] _trustManagers;

  private static void _trustAllHostnames()
  {
    if (_hostnameVerifier == null)
      _hostnameVerifier = new FakeHostnameVerifier();
    HttpsURLConnection.setDefaultHostnameVerifier(_hostnameVerifier);
  }

  private static void _trustAllHttpsCertificates()
  {
    if (_trustManagers == null)
    {
      TrustManager[] arrayOfTrustManager1 = new TrustManager[1];
      FakeX509TrustManager localFakeX509TrustManager = new FakeX509TrustManager();
      arrayOfTrustManager1[0] = localFakeX509TrustManager;
      _trustManagers = arrayOfTrustManager1;
    }
    String str;
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      TrustManager[] arrayOfTrustManager2 = _trustManagers;
      SecureRandom localSecureRandom = new SecureRandom();
      localSSLContext.init(null, arrayOfTrustManager2, localSecureRandom);
      HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
      return;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      str = localGeneralSecurityException.getMessage();
    }
    throw new IllegalStateException(str);
  }

  public static void trustAllHostnames()
  {
    _trustAllHostnames();
  }

  public static void trustAllHttpsCertificates()
  {
    _trustAllHttpsCertificates();
  }

  public class FakeHostnameVerifier
    implements HostnameVerifier
  {
    public boolean verify(String paramString, SSLSession paramSSLSession)
    {
      return true;
    }
  }

  public class FakeX509TrustManager
    implements X509TrustManager
  {
    private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[0];

    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
    }

    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
    }

    public X509Certificate[] getAcceptedIssuers()
    {
      return _AcceptedIssuers;
    }
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.util.SSLUtils
 * JD-Core Version:    0.6.0
 */