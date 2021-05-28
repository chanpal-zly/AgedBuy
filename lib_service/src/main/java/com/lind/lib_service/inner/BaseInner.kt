package com.lind.lib_service.inner

/**
 * @author chanPal
 * @date 2021/5/28
 */
interface BaseInner {

    annotation class TabIndex {
        companion object {
            const val HOME = 1//首页
            const val COURSE = 2//课程
            const val MALL = 3//商城
            const val SETTING = 4//设置
        }
    }
}