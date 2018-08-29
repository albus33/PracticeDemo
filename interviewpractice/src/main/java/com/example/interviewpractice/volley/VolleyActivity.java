package com.example.interviewpractice.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.interviewpractice.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/27.
 */

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_pass;
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        synchronized (this) {
            if (requestQueue == null) {
                HurlStack hurlStack = new HurlStack();
                Cache cache = new DiskBasedCache(getCacheDir(), 5 * 1024 * 1024);
                Network network = new BasicNetwork(hurlStack);
                requestQueue = new RequestQueue(cache, network);
//                requestQueue = Volley.newRequestQueue(this);
                requestQueue.start();
            }
        }
        initViews();
        initListener();
    }

    private void initListener() {
        btn_login.setOnClickListener(this);
    }

    Button btn_login;
    EditText edt_number;

    private void initViews() {

        edt_number = findViewById(R.id.edt_number);
        edt_pass = findViewById(R.id.edt_pass);

        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    public void onClick(View v) {
//        StringRequest();
//        JsonObjectRequest();
        CustomRequest();
    }

    private void CustomRequest() {
        String url = "https://eapi.ciwong.com/gateway/oauth/v2/token" ;

//                "clientId=100021&passWord=123456&phoneVersion=MI+5s&brandId=524726977&osVersion=7.0&appVersionId=363&userName=15012894730";
        Map<String,String> params = new HashMap<String,String>();
        params.put("clientId","100021");
        params.put("passWord","123456");
        params.put("phoneVersion","MI+5s");
        params.put("brandId","524726977");
        params.put("osVersion","7.0");
        params.put("appVersionId","363");
        params.put("userName","15012894730");



        CustomRequest<LoginBean> tCustomRequest = new CustomRequest<LoginBean>(Request.Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        }, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Log.i(TAG, response.toString());
                LoginBean loginBean = (LoginBean) response;
                Log.i(TAG,loginBean.toString());
            }
        }, LoginBean.class,params);

        requestQueue.add(tCustomRequest);
    }

    private void JsonObjectRequest() {
        String url = "https://eapi.ciwong.com/gateway/oauth/v2/token?clientId=100021&passWord=123456&phoneVersion=MI+5s&brandId=524726977&osVersion=7.0&appVersionId=363&userName=15012894730";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    RequestQueue requestQueue;

    private void StringRequest() {


        String number = edt_number.getText().toString();
        String pass = edt_pass.getText().toString();
        String url = "https://eapi.ciwong.com/gateway/oauth/v2/token?clientId=100021&passWord=123456&phoneVersion=MI+5s&brandId=524726977&osVersion=7.0&appVersionId=363&userName=15012894730";
        String url1 = "https://eapi.ciwong.com/gateway/v5/user/v2/get_service_state?clientId=100021&accessToken=b08267ca203e4020ae40cb7c3b29893818639cc1&client_id=100021&brandId=524726977&serviceTypeId=0&uIds=581112956";
//        String url = "https://www.baidu.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }
}
