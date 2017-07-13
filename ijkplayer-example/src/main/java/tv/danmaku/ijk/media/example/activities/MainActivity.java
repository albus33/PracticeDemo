package tv.danmaku.ijk.media.example.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TableLayout;
import android.widget.Toast;


import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.content.RecentMediaStorage;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.Settings;
import tv.danmaku.ijk.media.player.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.player.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity  {
    IjkVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    Settings mSettings;
    private TableLayout mHudView;
    private AndroidMediaController mMediaController;
    private void initData() {
        Toast.makeText(this, "请先copy一个视频到SD卡的Movies文件夹", Toast.LENGTH_LONG).show();
        mSettings = new Settings(this);
        String path = "/storage/emulated/0/Movies/五月天 - 入阵曲（正式版）.mp4";
        if (!TextUtils.isEmpty(path)) {
            new RecentMediaStorage(this).saveUrlAsync(path);
        }
//        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
//        mMediaController.setSupportActionBar(actionBar);

        mVideoView.setHudView(mHudView);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setVideoPath(path);


//        Uri uri = Uri.parse("http://sohu.vodnew.lxdns.com/164/186/Pnpi84JWmioQAxQFLHyYP2.flv");
//        mVideoView.setVideoURI(uri);
        mVideoView.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
