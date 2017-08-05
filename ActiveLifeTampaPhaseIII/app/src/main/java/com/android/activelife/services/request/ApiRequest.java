package com.android.activelife.services.request;

import com.android.activelife.services.response.LocationData.LocationDataResponse;
import com.android.activelife.services.response.ReserveScheduleData;
import com.android.activelife.services.response.classdata.ClassDataResponse;
import com.android.activelife.services.response.instructordata.InstructorDataResponse;
import com.android.activelife.services.response.messagesdata.MessagesDataResponse;
import com.android.activelife.services.response.scheduledatedata.ScheduleDateDataResponse;
import com.android.activelife.services.response.schedulesdata.SchedulesDataResponse;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


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
    @GET("schedule-types")
    Call<List<SchedulesDataResponse>> getSchedules();

    /**
     * Messages API
     **/
    @GET("messages/{offset}")
    Call<List<MessagesDataResponse>> getMessages(@Path("offset") int offset, @Query("location") int location);

    /**
     * Reserve Schedule API
     **/
    @PUT("reserve/{sessionID}/{date}")
    Call<ReserveScheduleData> reserveSchedule(@Path("sessionID") String sessionID, @Path("date") String date, @Body JsonElement reserveSchedule);

}
