package com.lind.agedbuy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lind.agedbuy.databinding.FragmentTermsServiceBinding
import com.lind.lib_base.uibase.BaseFragment
import com.lind.lib_base.uibase.viewmodel.DefaultViewModel

class TermsServiceFragment : BaseFragment<DefaultViewModel, FragmentTermsServiceBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): FragmentTermsServiceBinding = FragmentTermsServiceBinding.inflate(inflater, parent, false)

    override fun initViewModel(): DefaultViewModel =
        ViewModelProvider(this).get(DefaultViewModel::class.java)

    override fun enabledVisibleToolBar(): Boolean {
        return true
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setToolBarLeftTitle("服务条款")
    }
}