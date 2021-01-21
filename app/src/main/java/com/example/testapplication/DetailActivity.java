package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle myBundle = intent.getExtras();
        String author = (String) myBundle.getString("author");
        System.out.println("From MainActivity: author-" + author);

        myBundle.putString("Result", "From DetailActivity");
        intent.putExtras(myBundle);
        this.setResult(RESULT_OK, intent);
    }
}