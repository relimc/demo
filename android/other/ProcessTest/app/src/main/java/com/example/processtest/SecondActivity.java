package com.example.processtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = new Bundle();
    }

    public void goThirdActivity(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }

    public void getTestCode(View view) {
        Log.d("SecondActivity", String.valueOf(MainActivity.TEST_CODE));
    }
}