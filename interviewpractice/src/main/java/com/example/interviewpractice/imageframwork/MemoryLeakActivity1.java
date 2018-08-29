package com.example.interviewpractice.imageframwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.interviewpractice.R;

import java.lang.ref.SoftReference;

/**
 * Created by Administrator on 2017/11/29.
 */

public class MemoryLeakActivity1 extends AppCompatActivity {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        findViewById(R.id.btn_open_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoryLeakActivity1.this,MemoryLeakActivity.class);
                startActivity(intent);

            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },1000);
        SoftReference<String >stringSoftReference = new SoftReference<String>("abc");
        String s = stringSoftReference.get();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
