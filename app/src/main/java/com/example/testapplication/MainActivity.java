package com.example.testapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

public class mainActivity extends AppCompatActivity {
    final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//        Bundle myBundle = new Bundle();
//        myBundle.putString("author", "JackyQ");
//        intent.putExtras(myBundle);
//        startActivity(intent);

        Button goLinearButton = (Button) this.findViewById(R.id.goLinear);
        bindingBtn(goLinearButton, detailActivity.class);
        Button goRelativeButton = (Button) this.findViewById(R.id.goRelative);
        bindingBtn(goRelativeButton, relativeLayout.class);
        Button goListButton = (Button) this.findViewById(R.id.goList);
        bindingBtn(goListButton, ListActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == this.REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle b = intent.getExtras();
            String str = b.getString("Result"); //获取 Result 中的值，为 "from Activity2"
        }
    }

    /**
     * 绑定按钮事件
     * @param button 按钮
     * @param targetActivity 目标Activity
     */
    private void bindingBtn(Button button, final Class targetActivity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity.this, targetActivity);
                Bundle myBundle = new Bundle();
                myBundle.putString("author", "JackyQ");
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });
    }
}