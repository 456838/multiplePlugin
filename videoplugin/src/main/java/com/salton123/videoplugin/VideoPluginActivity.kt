package com.salton123.videoplugin

import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import kotlinx.android.synthetic.main.aty_video_plugin.*

class VideoPluginActivity : BaseSupportActivity() {
    override fun InitVariable(savedInstanceState: Bundle?) {
    }

    override fun GetLayout(): Int {
        return R.layout.aty_video_plugin
    }

    override fun InitViewAndData() {

    }

    override fun InitListener() {
        btn_normal.setOnClickListener({
            OpenActivity(NormalVideoTestAty::class.java, null)
        })
        btn_abnormal.setOnClickListener({
            OpenActivity(AbNormalVideoTestAty::class.java, null)
        })
    }


}
