package com.salton123.musicplugin

import android.Manifest
import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.mvp.util.RxUtil
import com.salton123.xm.fm.IndexFragment
import com.salton123.xm.wrap.XmlyInitializer
import com.salton123.xm.wrapper.XmAdsStatusAdapter
import com.salton123.xm.wrapper.XmPlayerStatusAdapter
import com.tbruyelle.rxpermissions2.RxPermissions
import com.ximalaya.ting.android.opensdk.util.BaseUtil
import io.reactivex.Observable
import io.reactivex.functions.Predicate

class MainActivity : BaseSupportActivity() {
    override fun GetLayout(): Int {
        return R.layout.salton_fm_container
    }

    override fun InitViewAndData() {
        loadRootFragment(R.id.fl_container, IndexFragment.newInstance(IndexFragment::class.java))
    }

    override fun InitListener() {
    }

    override fun InitVariable(savedInstanceState: Bundle?) {
        requestPermission()!!.compose(RxUtil.rxSchedulerHelper()).subscribe {  }
        initXm()
    }

    private fun requestPermission(): Observable<Boolean>? {
        return RxPermissions(this@MainActivity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
                .all(Predicate { result: Boolean -> result }).flatMapObservable { s -> Observable.just(s) }
    }

    fun initXm() {
        if (BaseUtil.isMainProcess(this)) {
            XmlyInitializer.getInstance().attch(SaltonApplication.mInstance).defaultDownloadManager().notify(MainActivity::class.java, XmPlayerStatusAdapter(), XmAdsStatusAdapter()).businessHandle().init()
        }
    }
}
