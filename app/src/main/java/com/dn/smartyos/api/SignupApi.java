package com.dn.smartyos.api;

import com.dn.smartyos.model.SignupResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import com.dn.smartyos.model.SignupModel;
import com.dn.smartyos.model.SignupResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

        public interface SignupApi
        {
            @GET("signup")
            Call<SignupResponse> postRegister(@Query("fullname") String fullname,
                                              @Query("username") String username,
                                              @Query("password") String password,
                                              @Query("email")String email);
        }

