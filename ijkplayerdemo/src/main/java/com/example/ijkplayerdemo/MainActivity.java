package com.example.ijkplayerdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/7/6.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_chose_video_path;
    EditText edt_video_path;
    Button btn_play;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_video_path = (EditText) findViewById(R.id.edt_video_path);
        btn_chose_video_path = (Button) findViewById(R.id.btn_chose_video_path);

        btn_play = (Button) findViewById(R.id.btn_play);
        btn_chose_video_path.setOnClickListener(this);
        btn_play.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chose_video_path:
                Intent intent = new Intent();
        /* 开启Pictures画面Type设定为image */
                //intent.setType("image/*");
                // intent.setType("audio/*"); //选择音频
                intent.setType("video/*"); //选择视频 （mp4 3gp 是android支持的视频格式）

                // intent.setType("video/*;image/*");//同时选择视频和图片

        /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
        /* 取得相片后返回本画面 */
                startActivityForResult(intent, PICK_VIDEO);
                break;
            case R.id.btn_play:
                String video_path = edt_video_path.getText().toString();
                if (TextUtils.isEmpty(video_path)) {
                    Toast.makeText(this, "请先选择视频", Toast.LENGTH_SHORT).show();
                } else {
                    Intent videoIntent = new Intent(this, PlayActivity.class);
                    videoIntent.putExtra("video_path", video_path);
                    startActivity(videoIntent);
                }
                break;
        }
    }

    public static final int PICK_VIDEO = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PICK_VIDEO) {
            Uri uri = intent.getData();
            Cursor cursor = getContentResolver().query(uri, null, null,
                    null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                // String imgNo = cursor.getString(0); // 图片编号
                String v_path = cursor.getString(1); // 图片文件路径
                String v_name = cursor.getString(2); // 图片大小
                String v_size = cursor.getString(3); // 图片文件名
                Log.e("v_path=", v_path);
                Log.e("v_size=", v_size);
                Log.e("v_name=", v_name);
                edt_video_path.setText(v_path);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
}
