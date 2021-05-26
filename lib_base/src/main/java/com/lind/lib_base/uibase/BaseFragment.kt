package com.lind.lib_base.uibase

import androidx.viewbinding.ViewBinding
import com.lind.lib_base.uibase.base.BaseWonderFragment
import com.lind.lib_base.uibase.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel,VB:ViewBinding> : BaseWonderFragment<VM, VB>() {

}