package com.example.interviewpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivityC extends AppCompatActivity {
private Handler handler = null ;
private HandlerThread handlerThread;
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView  = findViewById(R.id.iv_test);
        handlerThread = new HandlerThread("");//开启子线程
        handlerThread.start();
        handler = new MyHandler(this,handlerThread.getLooper(), new Handler.Callback() {//子线程中创建的handler
            @Override
            public boolean handleMessage(Message message) {
                imageView.invalidate();//主线程的View在子线程中进行了操作，线程不同步
                return false;
            }
        }){

        };
        handler.sendEmptyMessage(0);
    }
    private static class MyHandler extends Handler{
        WeakReference<MainActivityC> mainActivityCWeakReference;
        public MyHandler(MainActivityC mainActivityC, Looper looper, Callback callback){
            super(looper,callback);
            mainActivityCWeakReference = new WeakReference<MainActivityC>(mainActivityC);
        }
        @Override
        public void handleMessage(Message msg) {
            mainActivityCWeakReference.get().imageView .invalidate();
        }
    }
}