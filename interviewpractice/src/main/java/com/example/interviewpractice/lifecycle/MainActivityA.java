package com.example.interviewpractice.lifecycle;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.interviewpractice.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class MainActivityA extends AppCompatActivity implements View.OnClickListener {
    private int result = 0;
    private Manager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("A生命周期A","onCreate");
        if (manager == null) {
            manager = new Manager(this);
        }
        manager.doWork();
//        test();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("A生命周期A","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("A生命周期A","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("A生命周期A","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("A生命周期A","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("A生命周期A","onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("A生命周期A","onDestroy");
    }
    public void start_b(View v){
        startActivity(new Intent(this,MainActivityB.class));
    }
    private void test() {
        File file = new File(Environment.getExternalStorageDirectory(),"test.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int read = fileInputStream.read();
            Log.i("readtag",read+"");
        }catch (IOException e){
            Log.i("readtag",e.toString()+"");
        }finally {
            Log.i("readtag","finally"+"");
        }
    }

    @Override
    public void onClick(View view) {

    }

    private class Manager {
        WeakReference<MainActivityA> mainActivityAWeakReference ;
        public Manager(MainActivityA mainActivityA) {
            mainActivityAWeakReference  = new WeakReference<MainActivityA>(mainActivityA);
        }

        public void doWork() {
            MainActivityA mainActivityA = mainActivityAWeakReference.get();
            mainActivityA.result = mainActivityA.result++;
        }
    }
}
