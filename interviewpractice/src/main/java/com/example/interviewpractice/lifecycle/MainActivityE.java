package com.example.interviewpractice.lifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.interviewpractice.R;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MainActivityE extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_d);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        Message message = handler.obtainMessage();
        message.setData(null);
        handler.sendMessageDelayed(message,0);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("");
                    }
                });
            }
        };
        thread.start();
    }
}
