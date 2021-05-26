package com.lind.lib_base.uibase.inner

import android.view.View
import com.lind.lib_base.view.ToolBarView

/**
 * @author chanPal
 * @date 2021/5/6
 */
interface OnToolBarClickListener {

    fun onClickToolBarView(view: View, event: ToolBarView.ViewType)
}