package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.db.DictionaryOpenHelper;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    private SQLiteDatabase database = null;
    private ArrayList<Map<String, Object>> data = new ArrayList<>();
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DictionaryOpenHelper dictionaryOpenHelper = new DictionaryOpenHelper(this);
        database = dictionaryOpenHelper.getWritableDatabase();
        Button button = (Button) this.findViewById(R.id.submit);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("按键: ", String.valueOf(v.getId()));
        switch (v.getId()) {
            case R.id.submit:
                handleSubmitClick();
                break;
            default:
                break;
        }
    }

    public void handleSubmitClick() {
        final EditText nameEditText = (EditText) this.findViewById(R.id.name);
        final EditText pwdEditText = (EditText) this.findViewById(R.id.pwd);
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        saveData(name, pwd);
    }

    /**
     * 获取数据列表
     */
    public void getDataList() {
        data.clear();
        cursor = database.query("staffs", null, null, null, null, null, "_id ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            
        }
    }

    /**
     * 保存数据
     * @param name 姓名
     * @param pwd 密码
     */
    public void saveData(String name, String pwd) {
        if (database == null) return;
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pwd", pwd);
        database.insert("staffs", null, values);
    }
}