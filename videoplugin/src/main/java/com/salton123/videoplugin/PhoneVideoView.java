package com.salton123.videoplugin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/1 21:12
 * ModifyTime: 21:12
 * Description:
 */
public class PhoneVideoView extends VideoView {
    public PhoneVideoView(Context context) {
        super(context);
    }

    public PhoneVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhoneVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //重新设置一下宽和高
        setMeasuredDimension(w, height);
    }
}
