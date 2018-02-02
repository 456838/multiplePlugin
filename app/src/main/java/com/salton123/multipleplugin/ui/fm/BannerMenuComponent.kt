package com.salton123.multipleplugin.ui.fm

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.salton123.base.BaseSupportFragment
import com.salton123.multipleplugin.R
import com.salton123.util.ScreenUtils
import com.tmall.ultraviewpager.UltraViewPager
import java.util.*


/**
 * User: newSalton@outlook.com
 * Date: 2018/1/14 21:43
 * ModifyTime: 21:43
 * Description:
 */
class BannerMenuComponent : BaseSupportFragment() {
    internal lateinit var menuBanner: UltraViewPager
    internal var list: MutableList<Int> = ArrayList()

    override fun GetLayout(): Int {
        return R.layout.cp_banner_menu
    }

    override fun InitVariable(savedInstanceState: Bundle?) {
        list.add(R.layout.view_menu_item_one)
//        list.add(R.layout.view_menu_item_two)
//        list.add(R.layout.view_menu_item_three)
    }

    override fun InitViewAndData() {
        menuBanner = f(R.id.menuBanner)
        menuBanner.setInfiniteLoop(true)
        menuBanner.setAdapter(GalleryPagerAdapter())
        menuBanner.initIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                .setFocusColor(Color.parseColor("#96bedf")).setNormalColor(Color.parseColor("#cce2f5"))
                .setMargin(0, 0, 0, ScreenUtils.dpToPxInt(_mActivity, 15f))
                .setRadius(ScreenUtils.dpToPxInt(_mActivity, 3f))
                .build()
    }

    override fun InitListener() {

    }

    private inner class GalleryPagerAdapter : PagerAdapter() {
        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val linearLayout = LayoutInflater.from(container.context).inflate(list[position], null) as LinearLayout
            container.addView(linearLayout)
            return linearLayout
        }

    }
}
