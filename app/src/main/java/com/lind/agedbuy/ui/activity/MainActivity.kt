package com.lind.agedbuy.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.css.base.uibase.viewmodel.DefaultViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lind.agedbuy.BuildConfig
import com.lind.agedbuy.R
import com.lind.agedbuy.databinding.ActivityMainBinding
import com.lind.lib_base.uibase.base.AgedBaseActivity
import com.tencent.bugly.Bugly

class MainActivity : AgedBaseActivity<DefaultViewModel, ActivityMainBinding>() {
    private var mCurFragment: Fragment? = null
    private lateinit var mTabMainFragment: MainFragment
    lateinit var mTabCourseFragment: CourseFragment
    private lateinit var mTabMallFragment: MallFragment
    private lateinit var mTabSettingFragment: SettingFragment

    override fun initViewModel(): DefaultViewModel =
        ViewModelProvider(this).get(DefaultViewModel::class.java)

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Bugly.init(applicationContext, "718b817297", true)
        } else {
            Bugly.init(applicationContext, "718b817297", false)
        }
        initTablayout()
    }

    private fun initTablayout() {
            mTabMainFragment = MainFragment()
            mTabCourseFragment = CourseFragment()
            mTabMallFragment = MallFragment()
            mTabSettingFragment = SettingFragment()
            mViewBinding.tablayout.initTab(callback = {
                mViewBinding.tablayout.tag = it
//            val fragment = getFragment(it)
//            when (it) {
//                BaseInner.TabIndex.HOME -> {
//                    if (mCurFragment == fragment) {
//                        mTabMainFragment.scrollTopRefresh()
//                    }
//
//                }
//                BaseInner.TabIndex.MALL -> {
//                    if (curFragment == fragment) {
//                        tabMallFragment.scrollTopRefresh()
//                    }
//
//                }
//                BaseInner.TabIndex.COURSE -> {
//                    if (curFragment == fragment) {
//                        tabCartFragment.scrollTopRefresh()
//                    }
//
//                }
//                BaseInner.TabIndex.SETTING -> {
//                    if (curFragment == fragment) {
//                        tabMyFragment.scrollTopRefresh()
//                    }
//
//                }
//            }
                changeFragment(it)
            })
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater, parent, false)
}