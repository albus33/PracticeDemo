package com.example.interviewpractice.lifecycle;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.interviewpractice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        Log.i("B生命周期B","onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("B生命周期B","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("B生命周期B","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("B生命周期B","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("B生命周期B","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("B生命周期B","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("B生命周期B","onDestroy");
    }
}
