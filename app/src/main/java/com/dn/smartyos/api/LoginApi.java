package com.dn.smartyos.api;

import retrofit.http.GET;
import retrofit.http.Query;
import com.dn.smartyos.model.LoginModel;
import retrofit.Call;


public interface LoginApi {
    @GET("loginuser")
    Call<LoginModel> getDataLogin(
            @Query("username") String username,
            @Query("password") String password);

}