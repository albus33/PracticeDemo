package com.example.interviewpractice.imageframwork;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.interviewpractice.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2017/11/28.
 */

public class ImageFrameWorkActivity extends AppCompatActivity {
    ImageView iv_image;
    String url;
    SimpleDraweeView  iv_sdv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iv_frame);
        iv_image = findViewById(R.id.iv_image);

        iv_sdv = findViewById(R.id.iv_sdv);
        url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511870553655&di=8288c5ffd90f753ec906d12797613ef4&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fcrop%253D0%252C0%252C640%252C460%2Fsign%3D9f796bb105b30f2421d5b643f5a5fd73%2F962bd40735fae6cd9f796bb105b30f2443a70f15.jpg";

//        GlideImage();
//        VolleyImage();
        FrescoImage();


    }

    private void FrescoImage() {
//        Fresco.initialize(getApplicationContext());
        iv_sdv.setImageURI(Uri.parse(url));

    }

    private void VolleyImage() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(iv_image,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(imageRequest);
    }

    private void GlideImage() {
        Glide.with(this).load(url).into(iv_image);
    }
}
