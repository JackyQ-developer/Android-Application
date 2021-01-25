package com.example.testapplication.api;

import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;
import utils.http.ObjectService;
import utils.http.RetrofitServiceManager;

public class userService extends ObjectService {
    public interface UserInterface {
        @POST("/login")
        Observable<JsonObject> login(@Body JsonObject jsonObject);
    }

    private UserInterface mUserInterface;

    public userService() {
        mUserInterface = RetrofitServiceManager.getInstance().create(UserInterface.class);
    }

    public Observable<Object> login(JsonObject jsonObject) {
        return observe(mUserInterface.login(jsonObject)).map(new Func1<Object, Object>(){
            @Override
            public Object call(Object o) {
                return o;
            }
        });
    }
}

