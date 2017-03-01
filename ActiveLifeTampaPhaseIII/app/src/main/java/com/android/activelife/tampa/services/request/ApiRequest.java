package com.android.activelife.tampa.services.request;

import android.content.Context;
import android.util.Log;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by vsatrasala on 2/8/2017.
 */

/**
 * class used to create a Retrofit Builder to send network requests to an API.
 */

public class ApiRequest {

    /**
     * it is basic URL of our API. We will use this URL for all network requests to an API
     */
public static String BASE_URL = "https://domain.com/";


    private static Retrofit retrofit = null;
    private static ApiInterface apiService = null;

    /**
     * Create the ApiInterface {Retrofit} instance using the configured values.
     * This ApiInterface handles server certification check also,
     * by default installing the all-trusting trust managers
     *
     * @return Retrofit ApiInterface
     */

    public static ApiInterface getApiInterface(Context context) throws Exception {

        if (apiService == null) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(getOkHttpClientWithTrustAllHosts())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            apiService = retrofit.create(ApiInterface.class);
        }
        return apiService;
    }

    /**
     * Trust every server - don't check for any certificate
     */
    private static OkHttpClient getOkHttpClientWithTrustAllHosts() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]
                {
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return new X509Certificate[]{};
                            }

                            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            }
                        }
                };

        /** Install the all-trusting trust manager*/
        OkHttpClient okHttpClient = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sc.getSocketFactory())
                    .addInterceptor(interceptor)
                    .readTimeout(180, TimeUnit.SECONDS)
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .writeTimeout(180, TimeUnit.SECONDS)
                    .build();
            return okHttpClient;

        } catch (Exception e) {
            Log.e("ApiRequest", "SSLContext Exception :" + e.toString());
        }

        return okHttpClient;
    }
}
