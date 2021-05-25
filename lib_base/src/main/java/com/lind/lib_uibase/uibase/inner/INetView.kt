package com.lind.lib_uibase.uibase.inner

import com.lind.lib_uibase.net.CommonResponse
import kotlinx.coroutines.Job


/**
 * @author INetView
 * @date 2021/5/25
 */
interface INetView {

    fun launch(block: suspend () -> Unit, failed: suspend (Int, String?) -> Unit): Job

    fun <T> netLaunch(
        block: suspend () -> CommonResponse<T>,
        success: (msg: String?, d: T?) -> Unit,
        failed: (Int, String?, d: T?) -> Unit
    ): Job

    fun ioLaunch(block: suspend () -> Unit, failed: suspend (Int, String?) -> Unit): Job

    fun <T> ioNetLaunch(
        block: suspend () -> CommonResponse<T>,
        success: (msg: String?, d: T?) -> Unit,
        failed: (Int, String?, d: T?) -> Unit
    ): Job

}