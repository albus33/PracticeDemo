package com.example.interviewpractice.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = "paul";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent oIntent  = new Intent("com.ubtech.nlu.serviceaction");
        oIntent.setPackage("com.ubtech.nul");
        context.bindService(oIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
