package com.example.testapplication;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/login")
    Call<JSONObject> login(@Body JSONObject jsonObject);
}
