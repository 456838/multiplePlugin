package com.salton123.musicplugin;

import android.app.Application;

import com.salton123.base.ApplicationBase;
import com.salton123.common.image.FrescoImageLoader;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/2 21:38
 * ModifyTime: 21:38
 * Description:
 */
public class SaltonApplication extends ApplicationBase {
    @Override
    public void onCreate() {
        super.onCreate();
        FrescoImageLoader.Init(this);
    }
}
