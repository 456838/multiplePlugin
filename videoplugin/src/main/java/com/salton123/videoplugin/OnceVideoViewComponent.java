package com.salton123.videoplugin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.salton123.base.BaseSupportFragment;
import com.salton123.util.EventUtil;
import com.salton123.util.log.MLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * User: newSalton@outlook.com
 * Date: 2017/12/14 0:48
 * ModifyTime: 0:48
 * Description:
 */
public class OnceVideoViewComponent extends BaseSupportFragment {
    private static final String TAG = "VideoPreviewComponent";
    public PhoneVideoView mVideoView;
    int currentPosition;
    String resNameWithSuffix;

    public static <T extends Fragment> T newInstance(Class<T> clz, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ITEM, value);
        T fragment = null;
        try {
            fragment = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int GetLayout() {
        return R.layout.component_once_video_view;
    }

    @Override
    public void InitVariable(Bundle bundle) {
        EventUtil.register(this);
        _mActivity.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        resNameWithSuffix = getArguments().getString(ARG_ITEM);
    }

    @Override
    public void InitViewAndData() {
        mVideoView = f(R.id.video_view);
        setVideoViewListener();
        play();
    }

    private void play() {
        mVideoView.setVideoURI(UriProvider.getVideoPathUri(resNameWithSuffix));
        mVideoView.start();
    }

    private Bitmap getFrameAtTime(int position) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(_mActivity, UriProvider.getVideoPathUri(resNameWithSuffix));
            Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(position);
            mediaMetadataRetriever.release();
            return bitmap;
        } catch (Throwable throwable) {
            Log.e(TAG, "getFrameAtTime: throwable=", throwable);
            return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
    }

    private void setVideoViewListener() {
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            mVideoView.setBackgroundColor(Color.TRANSPARENT);
                        }
                        return true;
                    }
                });

            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    mVideoView.setBackgroundColor(Color.TRANSPARENT);
                }
                return true;
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(_mActivity, "资源找不到", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

            }
        });
    }

    @Override
    public void InitListener() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
            currentPosition = mVideoView.getCurrentPosition();
        }
        MLog.info(TAG, "[onPause]");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveProductAction(ProductAction code) {
        if (code != null) {
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
                mVideoView.suspend();
            }
            this.resNameWithSuffix = code.resNameWithSuffix;
            MLog.info(TAG, "resNameWithSuffix=" + resNameWithSuffix);
            play();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventUtil.unregister(this);
        MLog.info(TAG, "[onDestroy]");
        if (mVideoView != null) {
            mVideoView.suspend();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MLog.info(TAG, "[onResume]");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        MLog.info(TAG, "[onSupportVisible]");
        if (mVideoView != null) {
            mVideoView.seekTo(currentPosition);
            mVideoView.start();
        }
    }
}
