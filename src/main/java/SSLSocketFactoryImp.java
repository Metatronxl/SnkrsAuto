import com.google.common.collect.Lists;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.*;


public class SSLSocketFactoryImp extends SSLSocketFactory {
    private SSLContext sslContext = SSLContext.getInstance("SSL");
    private TrustManager trustManager = null;


    public SSLContext getSSLContext() {
        return sslContext;
    }

    public X509TrustManager getTrustManager() {
        return (X509TrustManager)trustManager;
    }

    public SSLSocketFactoryImp(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException {
        trustManager = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                //注意这里不能返回null，否则会报错,如下面错误[1]
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        sslContext.init(null, new TrustManager[]{trustManager}, null);
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }

    @Override
    public Socket createSocket(Socket socket, String host, int post, boolean autoClose) throws IOException {
        return sslContext.getSocketFactory().createSocket(socket, host, post, autoClose);
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        return null;
    }

    /**
     * @dec 获取可以忽略SSL认证的OkHttpClient
     */

    private static OkHttpClient.Builder builder = null;

    public static OkHttpClient getClient(Interceptor... interceptor) {

        if (builder == null) {
            try {
                builder = new OkHttpClient.Builder();
                //ssl verifier
                KeyStore trustStore;
                trustStore = KeyStore.getInstance(KeyStore
                        .getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactoryImp ssl = new SSLSocketFactoryImp(KeyStore.getInstance(KeyStore.getDefaultType()));

                HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                builder.sslSocketFactory(ssl.getSSLContext().getSocketFactory(), ssl.getTrustManager())
                        .hostnameVerifier(DO_NOT_VERIFY)
                        .retryOnConnectionFailure(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return builder.build();
        }
        return builder.build();
    }
}