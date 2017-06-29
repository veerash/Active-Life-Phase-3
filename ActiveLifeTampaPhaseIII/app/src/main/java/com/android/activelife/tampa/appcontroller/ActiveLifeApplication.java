package com.android.activelife.tampa.appcontroller;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.android.activelife.tampa.constants.StaticValuesConstants;
import com.android.activelife.tampa.services.request.ApiRequest;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

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

import static com.android.activelife.tampa.constants.StaticValuesConstants.BASE_URL;

/**
 * Created by vsatrasala on 2/8/2017.
 */

public class ActiveLifeApplication extends Application {

    public Retrofit jRetrofit;
    public ApiRequest jApiRequestService;
    private static ActiveLifeApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {

        }else {
            builder.detectFileUriExposure();
        }
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
        jApiRequestService = getRetrofit().create(ApiRequest.class);
    }

    public Retrofit getRetrofit() {
        try {
            jRetrofit = new Retrofit.Builder()
                    .baseUrl(StaticValuesConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClientWithTrustAllHosts())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }return jRetrofit;
    }


    public ApiRequest getApiRequest() {
        if (jApiRequestService == null) {
            jApiRequestService = getRetrofit().create(ApiRequest.class);
        }
        return jApiRequestService;
    }

    public static ActiveLifeApplication getInstance() {
        return mInstance;

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
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .build();
            return okHttpClient;

        } catch (Exception e) {
        }

        return okHttpClient;
    }
}
