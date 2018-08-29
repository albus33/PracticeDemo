package com.example.longfootrefresh;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private HandlerThread handlerThread;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerThread = new HandlerThread("");
        handlerThread.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    imageView.invalidate();
                    return false;
                }
            });
        }
    }
}
