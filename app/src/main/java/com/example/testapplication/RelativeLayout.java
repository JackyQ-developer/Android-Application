package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class relativeLayout extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_layout);

        Button button = (Button) this.findViewById(R.id.submitButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                handleSubmitClick();
                break;
            default:
                break;
        }
    }

    public void handleSubmitClick() {
        final EditText usernameEditText = (EditText) this.findViewById(R.id.username);
        final EditText passwordEditText = (EditText) this.findViewById(R.id.password);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String link = "http://192.168.3.76:7001/login";

        JsonObject data = new JsonObject();
        data.addProperty("username", username);
        data.addProperty("password", password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.76:7001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService service = retrofit.create(userService.class);
        service.login(data).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("AjaxResult: ", response.toString());
                Gson gson = new Gson();
                Map map = gson.fromJson(response.body(), Map.class);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("按键测试:", String.valueOf(keyCode));
        return super.onKeyDown(keyCode, event);
    }
}