package com.lind.lib_base.net

import com.css.base.keep.KeepClass

/**
 * @author chanPal
 * @date 2021/5/25
 */
class CommonResponse<T> : KeepClass {
    var status: Int = 0
    var message: String? = null
    var data: T? = null
}