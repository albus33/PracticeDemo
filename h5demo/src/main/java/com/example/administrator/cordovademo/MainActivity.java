package com.example.administrator.cordovademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView webview;
    Button btn_function_js;
    Button btn_function_none;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_function_js = (Button) findViewById(R.id.btn_function_value);
        btn_function_none = (Button) findViewById(R.id.btn_function_none);
        btn_function_none.setOnClickListener(this);
        btn_function_js.setOnClickListener(this);

        webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("file:///android_asset/test.html");
        webview.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = webview.getSettings();
        //设置为可调用js方法
        webSettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JsInteration(), "android");


    }
    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //判断url拦截事件
            if (url.equals("file:///android_asset/test2.html")) {
                //不让webview加载url，activity自己处理。
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                return true;
            } else {
                webview.loadUrl(url);
                return false;
            }
        }
    }
    public class JsInteration {
        @JavascriptInterface
        public String back() {
            System.out.println("js触发了Android的方法");
            return "MainActivity--js触发了Android的方法";
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_function_value:
                webview.evaluateJavascript("value(6,2)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(MainActivity.this, "android调用js的值"+value, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_function_none:

                webview.loadUrl("javascript:none()");
                break;
        }

    }

    @Override
    public void finish() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.finish();
        }
    }

}
