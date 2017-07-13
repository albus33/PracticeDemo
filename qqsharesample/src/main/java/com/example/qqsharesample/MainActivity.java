package com.example.qqsharesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_share_out;
    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_share_out = (Button) findViewById(R.id.btn_share_out);
        btn_share_out.setOnClickListener(this);
        if (mTencent == null) {
            mTencent = Tencent.createInstance(mAppid, this);
        }
    }

    String imageNetUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1495186634&di=5484f2d2f42dd7929dd27aebcc4c3df6&src=http://pic.58pic.com/58pic/16/00/52/11J58PICD3S_1024.jpg";//分享的小图标
    private int mExtarFlag = 0x00;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share_out:
                final Bundle params = new Bundle();
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "自制分享标题"); //标题
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com");//分享的链接地址。
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "摘要摘要");//分享的摘要文字，简述

                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageNetUrl);

                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "六国合纵");
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
                params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, mExtarFlag);


                if (shareType == QQShare.SHARE_TO_QQ_TYPE_AUDIO) {
                    params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, "音乐播放地址url");
                }
                if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN) != 0) {
                    showToast("在好友选择列表会自动打开分享到qzone的弹窗~~~");
                } else if ((mExtarFlag & QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE) != 0) {
                    showToast("在好友选择列表隐藏了qzone分享选项~~~");
                }
                doShareToQQ(params);
                break;
        }
    }

    public static String mAppid = "1106098541";
    public static Tencent mTencent;

    private void doShareToQQ(final Bundle params) {
        // QQ分享要在主线程做
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {

                if (null != MainActivity.mTencent) {
                    MainActivity.mTencent.shareToQQ(MainActivity.this, params, qqShareListener);
                }
            }
        });
    }

    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            if (shareType != QQShare.SHARE_TO_QQ_TYPE_IMAGE) {
                Util.toastMessage(MainActivity.this, "sharesample_onCancle ");
            }
        }

        @Override
        public void onComplete(Object response) {
            Util.toastMessage(MainActivity.this, "sharesample_onComplete: " + response.toString());
        }

        @Override
        public void onError(UiError e) {
            Util.toastMessage(MainActivity.this, "sharesample_onError: " + e.errorMessage, "e");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mTencent)
            mTencent.onActivityResultData(requestCode, resultCode, data,qqShareListener);
    }

    Toast mToast = null;

    private void showToast(String text) {
        if (mToast != null && !super.isFinishing()) {
            mToast.setText(text);
            mToast.show();
            return;
        }
        mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
