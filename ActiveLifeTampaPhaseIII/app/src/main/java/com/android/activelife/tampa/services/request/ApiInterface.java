
package com.android.activelife.tampa.services.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Class used to define network requests Apis endpoints
 * using special retrofit annotations to encode details about the parameters and request method.
 * the return value is always a parameterized Call<T> object
 */

public interface ApiInterface {

    /**
     * Login API
     *
     * @param identifier user registered email address
     * @param password   user password
     * @param game       user game id
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> loginUserAPI(@Field("Name") String identifier,
                                    @Field("Email") String password,
                                    @Field("Password") String game);
}
