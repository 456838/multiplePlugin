package com.salton123.videoplugin

import android.graphics.PixelFormat
import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.util.EventUtil
import kotlinx.android.synthetic.main.fm_container.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/2 10:31
 * ModifyTime: 10:31
 * Description:
 */
class NormalVideoTestAty : BaseSupportActivity() {
    override fun GetLayout(): Int {
        return R.layout.fm_container
    }

    override fun InitViewAndData() {
        loadRootFragment(R.id.videoView, OnceVideoViewComponent.newInstance(OnceVideoViewComponent::class.java, "product_id_black_01.mp4"))
    }

    override fun InitListener() {
        touchLayer.setOnClickListener({ switchVideo() })
    }

    var index = 0
    private fun switchVideo() {
        var action = ProductAction()
        if (index == 0) {
            action.resNameWithSuffix = "product_id_black_01.mp4"
            EventUtil.sendEvent(action)
            index = 1
        } else {
            action.resNameWithSuffix = "product_id_black_02.mp4"
            EventUtil.sendEvent(action)
            index = 0
        }
    }

    override fun InitVariable(savedInstanceState: Bundle?) {
//        EventUtil.register(this)
        getWindow().setFormat(PixelFormat.TRANSLUCENT)
    }
}