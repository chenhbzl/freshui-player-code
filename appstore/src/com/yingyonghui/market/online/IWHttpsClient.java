package com.yingyonghui.market.online;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import com.yingyonghui.market.util.InternetManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class IWHttpsClient extends DefaultHttpClient
{
  public static final int CONNECTION_TIMEOUT = 180000;
  private Context context;

  public IWHttpsClient(Context paramContext)
  {
    this.context = paramContext;
    HttpParams localHttpParams = getParams();
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 180000);
    HttpConnectionParams.setSoTimeout(localHttpParams, 180000);
    ConnManagerParams.setTimeout(localHttpParams, -16608L);
    if ((Proxy.getDefaultHost() != null) && (!InternetManager.hasMoreThanOneConnection(paramContext)) && (!InternetManager.isWiFiActive(this.context)))
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      String str1 = null;
      if (localNetworkInfo != null)
        str1 = localNetworkInfo.getExtraInfo();
      if ((str1 != null) && ((str1.trim().toLowerCase().equals("cmwap")) || (str1.trim().toLowerCase().equals("uniwap")) || (str1.trim().toLowerCase().equals("ctnet")) || (str1.trim().toLowerCase().equals("ctwap"))))
      {
        String str2 = Proxy.getDefaultHost();
        int i = Proxy.getDefaultPort();
        HttpHost localHttpHost = new HttpHost(str2, i, "http");
        localHttpParams.setParameter("http.route.default-proxy", localHttpHost);
      }
    }
  }

  private SSLSocketFactory newSslSocketFactory()
  {
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance("BKS");
      InputStream localInputStream = this.context.getResources().openRawResource(2131099648);
      char[] arrayOfChar = "rintail".toCharArray();
      localKeyStore.load(localInputStream, arrayOfChar);
      localInputStream.close();
      localSSLSocketFactory = new SSLSocketFactory(localKeyStore);
      return localSSLSocketFactory;
    }
    catch (KeyStoreException localKeyStoreException)
    {
      while (true)
      {
        localKeyStoreException.printStackTrace();
        SSLSocketFactory localSSLSocketFactory = null;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        localNoSuchAlgorithmException.printStackTrace();
    }
    catch (CertificateException localCertificateException)
    {
      while (true)
        localCertificateException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    catch (KeyManagementException localKeyManagementException)
    {
      while (true)
        localKeyManagementException.printStackTrace();
    }
    catch (UnrecoverableKeyException localUnrecoverableKeyException)
    {
      while (true)
        localUnrecoverableKeyException.printStackTrace();
    }
  }

  protected ClientConnectionManager createClientConnectionManager()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    PlainSocketFactory localPlainSocketFactory = PlainSocketFactory.getSocketFactory();
    Scheme localScheme1 = new Scheme("http", localPlainSocketFactory, 80);
    localSchemeRegistry.register(localScheme1);
    SSLSocketFactory localSSLSocketFactory = newSslSocketFactory();
    Scheme localScheme2 = new Scheme("https", localSSLSocketFactory, 443);
    localSchemeRegistry.register(localScheme2);
    HttpParams localHttpParams = getParams();
    return new SingleClientConnManager(localHttpParams, localSchemeRegistry);
  }
}

/* Location:           D:\android_tools\dex2jar-0.0.7.4-SNAPSHOT\classes.dex.dex2jar.jar
 * Qualified Name:     com.yingyonghui.market.online.IWHttpsClient
 * JD-Core Version:    0.6.0
 */