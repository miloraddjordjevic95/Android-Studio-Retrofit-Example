package com.miloraddjordjevic.projekat.api;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerUser(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginUser(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("password/forgot")
    Call<ResponseBody> sendEmail(@FieldMap Map<String, String> fields);
}
