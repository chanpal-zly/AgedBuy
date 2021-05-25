package com.lind.lib_uibase.uibase.inner

import androidx.annotation.StringRes

/**
 * ViewModel与 View 的契约接口
 * @author chanPal
 * @date 2021/5/25
 */
interface ICoreView {

    /**
     * 吐司
     */
    fun showToast(msg: String?)

    /**
     * 长吐司
     */
    fun showLongToast(msg: String?)

    /**
     * 吐司
     */
    fun showToast(@StringRes resId: Int)

    /**
     * 长吐司
     */
    fun showLongToast(@StringRes resId: Int)

    /**
     * 显示在屏幕中间的吐司
     */
    fun showCenterToast(msg: String?)

    /**
     * 显示在屏幕中间的长吐司
     */
    fun showCenterLongToast(msg: String?)

    /**
     * 显示在屏幕中间的吐司
     */
    fun showCenterToast(@StringRes resId: Int)

    /**
     * 显示在屏幕中间的长吐司
     */
    fun showCenterLongToast(@StringRes resId: Int)

    /**
     * 关闭Activity
     */
    fun finishAc()

}