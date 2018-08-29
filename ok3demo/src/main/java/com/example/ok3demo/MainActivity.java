package com.example.ok3demo;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Typeface.DEFAULT_BOLD;
        initView();
        initListener();
        initData();
    }

    private void initData() {
        Gson gson =new Gson();

        okHttpClient = new OkHttpClient();
    }

    private void initListener() {
        btn_request_get.setOnClickListener(this);
        btn_request_post.setOnClickListener(this);
    }
    Button btn_request_post;
    Button btn_request_get;
    private void initView() {

        btn_request_post = (Button) findViewById(R.id.btn_request_post);

        btn_request_get = (Button) findViewById(R.id.btn_request_get);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_request_get:
                requestNetGet();
                break;
            case R.id.btn_request_post:
                requestNetPost();
                break;
        }
    }

    private void requestNetPost() {
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("size", "10");
//        FormBody formBody = builder.build();
//        Request request = new Request.Builder()
//                .url("http://api.1-blog.com/biz/bizserver/article/list.do")
//                .post(formBody)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String str = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
        FormBody.Builder builder =new FormBody.Builder();
//        builder.add()





    }

    private void requestNetGet() {

        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= okHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("wangshu", "cache---" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.i("wangshu", "network---" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
