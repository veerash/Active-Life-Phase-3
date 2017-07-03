package com.android.activelife.tampa.services.request;

import android.content.Context;
import android.util.Log;

import com.android.activelife.tampa.services.response.LocationData.LocationDataResponse;
import com.android.activelife.tampa.services.response.classdata.ClassDataResponse;
import com.android.activelife.tampa.services.response.instructordata.InstructorDataResponse;
import com.android.activelife.tampa.services.response.messagesdata.MessagesDataResponse;
import com.android.activelife.tampa.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.tampa.services.response.schedulesdata.SchedulesDataResponse;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.android.activelife.tampa.constants.StaticValuesConstants.BASE_URL;


/**
 * Created by vsatrasala on 2/8/2017.
 */

/**
 * class used to create a Retrofit Builder to send network requests to an API.
 */

public interface ApiRequest {


    /**
     * Location API
     **/
    @GET("locations")
    Call<List<LocationDataResponse>> getLocations();

    /**
     * Classes API
     **/
    @GET("classes")
    Call<List<ClassDataResponse>> getClasses();

    /**
     * Instructors API
     **/
    @GET("instructors")
    Call<List<InstructorDataResponse>> getInstructors();

    /**
     * Schedule Date API
     **/
    @GET("schedules/{date}")
    Call<List<ScheduleDateDataResponse>> getScheduleDate(@Path("date") String date);

    /**
     * Schedules API
     **/
    @GET("schedules")
    Call<List<SchedulesDataResponse>> getSchedules();

    /**
     * Messages API
     **/
    @GET("messages/{offset}")
    Call<List<MessagesDataResponse>> getMessages(@Path("offset") String offset, @Query("location") int location);

}
