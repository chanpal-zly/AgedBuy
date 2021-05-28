package com.lind.agedbuy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lind.agedbuy.R
import com.lind.agedbuy.databinding.FragmentSettingBinding
import com.lind.agedbuy.ui.activity.setting.AboutUsActivity
import com.lind.agedbuy.ui.activity.setting.PersonInformationActivity
import com.lind.lib_base.uibase.BaseFragment
import com.lind.lib_base.uibase.viewmodel.DefaultViewModel
import com.lind.lib_service.utils.SystemBarHelper

class SettingFragment : BaseFragment<DefaultViewModel, FragmentSettingBinding>(),
    View.OnClickListener {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        SystemBarHelper.immersiveStatusBar(activity, 0f)
        SystemBarHelper.setHeightAndPadding(activity, mViewBinding?.topView)
        mViewBinding?.rlPersonInfo?.setOnClickListener(this)
        mViewBinding?.rlAboutUs?.setOnClickListener(this)
    }

    override fun initViewModel(): DefaultViewModel =
        ViewModelProvider(this).get(DefaultViewModel::class.java)

    override fun onClick(v: View) {
        when (v.id) {
            R.id.rl_person_info -> {
                activity?.let { PersonInformationActivity.starActivity(it) }
            }
            R.id.rl_about_us -> {
                activity?.let { AboutUsActivity.starActivity(it) }
            }
        }
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSettingBinding = FragmentSettingBinding.inflate(inflater, viewGroup, false)
}