package com.bangladesh.tourism.api.services;



import com.bangladesh.tourism.models.Login;
import com.bangladesh.tourism.models.Make;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Zakir on 06/03/2016.
 */
public interface LoginService {

    @POST("get-vehicle-make")
    Call<Make> testApi();


}
