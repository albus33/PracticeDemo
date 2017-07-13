package com.example.ijkplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TableLayout;

import com.example.ijkplayerdemo.content.RecentMediaStorage;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.player.widget.media.IjkVideoView;

public class PlayActivity extends AppCompatActivity {
    IjkVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        initViews();
        initListener();
        initData();
    }

    private void initViews() {
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mHudView = (TableLayout) findViewById(R.id.hud_view);
    }

    private void initListener() {

    }

    private TableLayout mHudView;
    private AndroidMediaController mMediaController;

    private void initData() {
        Intent intent = getIntent();
        String path = intent.getStringExtra("video_path");


        if (!TextUtils.isEmpty(path)) {
            new RecentMediaStorage(this).saveUrlAsync(path);
        }
        mMediaController = new AndroidMediaController(this, false);

        mVideoView.setHudView(mHudView);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }

    @Override
    public void onBackPressed() {
        if (mVideoView.canPause()) {
            mVideoView.stopPlayback();
        } else {
            super.onBackPressed();
        }
    }
}
