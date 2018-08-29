package com.example.interviewpractice.volley;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/27.
 */

public class CustomRequest<LoginBeanT> extends Request<LoginBeanT> {
    private Response.ErrorListener errorListener;
    private Response.Listener listener;
    private Class<?> clazz;
    private Map<String, String> params;

    public CustomRequest(int method, String url, Response.ErrorListener errorListener, Response.Listener listener,Class<?> clazz,Map<String,String> params) {
        super(method, url, errorListener);
        this.errorListener = errorListener;
        this.listener = listener;
        this.clazz = clazz;
        this.params = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        LoginBean loginBean = null;
        Object obj = null;
        try {

            String data  = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Gson gson =  new Gson();


            obj = gson.fromJson(data, clazz);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return   Response.success(obj, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }

}
