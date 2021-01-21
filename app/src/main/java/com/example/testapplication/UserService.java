package com.example.testapplication;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface userService {
    @POST("/login")
    Call<JsonObject> login(@Body JsonObject jsonObject);
}
