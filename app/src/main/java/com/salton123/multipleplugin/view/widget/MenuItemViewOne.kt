package com.salton123.multipleplugin.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.github.florent37.viewanimator.ViewAnimator
import com.salton123.multipleplugin.R
import com.salton123.util.EventUtil
import com.yy.mobile.memoryrecycle.views.YYLinearLayout

/**
 * User: newSalton@outlook.com
 * Date: 2018/1/11 0:24
 * ModifyTime: 0:24
 * Description:
 */
class MenuItemViewOne : YYLinearLayout, View.OnClickListener {
    constructor(context: Context) : super(context, null) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.menu_pager_one, this)
        setListener(R.id.iv_page_one_01, R.id.iv_page_one_02, R.id.iv_page_one_03, R.id.iv_page_one_04)
    }

    private fun setListener(vararg ids: Int) {
        for (id in ids) {
            findViewById<View>(id).setOnClickListener(this)
        }
    }


    override fun onClick(view: View) {
        EventUtil.sendEvent(view)
        ViewAnimator.animate(view).alpha(1f, 0.5f, 1.0f).scale(1f, 1.05f, 1.0f).duration(500).start();
    }

}
