package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RelativeLayout extends AppCompatActivity implements View.OnClickListener {

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
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        JSONObject jsonObject = new JSONObject(body);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.76:7001")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        service.login(jsonObject).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.i("AjaxResult: ", response.toString());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
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