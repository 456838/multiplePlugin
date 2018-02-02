package com.salton123.multipleplugin

import android.os.Bundle
import android.view.View
import android.view.Window
import com.gyf.barlibrary.BarHide
import com.gyf.barlibrary.ImmersionBar
import com.qihoo360.replugin.RePlugin
import com.salton123.base.BaseSupportActivity
import com.salton123.base.BaseSupportFragment
import com.salton123.multipleplugin.ui.fm.BannerMenuComponent
import com.salton123.util.EventUtil
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseSupportActivity() {
    internal lateinit var mImmersionBar: ImmersionBar
    override fun GetLayout(): Int {
        return R.layout.salton_fm_container
    }

    override fun InitViewAndData() {
        loadRootFragment(R.id.fl_container, BaseSupportFragment.newInstance(BannerMenuComponent::class.java))
    }

    override fun InitListener() {

    }

    override fun InitVariable(savedInstanceState: Bundle?) {
        EventUtil.register(this)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)//去掉标题栏
        mImmersionBar = ImmersionBar.with(this).transparentBar().hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        mImmersionBar.init()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receiveMsg(view: View) {
        when (view.id) {
            R.id.iv_page_one_01 -> openPluginOne()
            R.id.iv_page_one_02 -> openPluginOne()
            R.id.iv_page_one_03 -> openPluginOne()
            R.id.iv_page_one_04 -> openPluginOne()
        }
    }

    fun openPluginOne() {
        RePlugin.startActivity(this@MainActivity, RePlugin.createIntent("com.salton123.videoplugin", "com.salton123.videoplugin.VideoPluginActivity"))
    }

    override fun onDestroy() {
        super.onDestroy()
        EventUtil.unregister(this)
        if (mImmersionBar != null) {
            mImmersionBar.destroy()
        }
    }

//    private fun requestPermission(): Observable<Boolean> {
//        val arr = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
//        return RxPermissions(this@MainActivity).request(arr).all(object : Predicate<Boolean> {
//            @Throws(Exception::class)
//            override fun test(@NonNull aBoolean: Boolean?): Boolean {
//                return aBoolean!!
//            }
//        }).flatMapObservable(object : Function<Boolean, ObservableSource<Boolean>> {
//            @Throws(Exception::class)
//            override fun apply(aBoolean: Boolean?): ObservableSource<Boolean> {
//                return Observable.just(aBoolean!!)
//            }
//        })
//    }

}
