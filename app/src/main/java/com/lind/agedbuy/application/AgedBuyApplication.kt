package com.lind.agedbuy.application

import android.app.Application
import com.tencent.bugly.Bugly

class AgedBuyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
//        Bugly.init(applicationContext, "27afe533e5", false)
    }
}