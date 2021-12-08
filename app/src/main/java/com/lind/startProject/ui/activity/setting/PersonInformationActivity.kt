package com.lind.startProject.ui.activity.setting

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.css.pickerview.builder.OptionsPickerBuilder
import com.css.pickerview.listener.CustomListener
import com.css.pickerview.view.OptionsPickerView
import com.lind.startProject.R
import com.lind.startProject.databinding.ActivityPersonInformationBinding
import com.lind.lib_base.uibase.BaseActivity
import com.lind.lib_base.uibase.viewmodel.DefaultViewModel
import com.lind.lib_service.data.UserData
import com.lind.lib_service.utils.AgedBuyCache

class PersonInformationActivity :
    BaseActivity<DefaultViewModel, ActivityPersonInformationBinding>(),
    View.OnClickListener {
    var mSexPickerDialog: OptionsPickerView<String>? = null
    var mAgePickerDialog: OptionsPickerView<String>? = null
    var mStaturePickerDialog: OptionsPickerView<String>? = null
    var mTargetWeightPickerDialog: OptionsPickerView<String>? = null
    var mTargetStepPickerDialog: OptionsPickerView<String>? = null
    var mSexList = ArrayList<String>()
    var mAgeList = ArrayList<String>()
    var mStatureList = ArrayList<String>()
    var mTargetWeightList = ArrayList<String>()
    var mTargetStepList = ArrayList<String>()
    lateinit var mUserData: UserData

    companion object {
        fun starActivity(context: Context) {
            val intent = Intent(context, PersonInformationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun enabledVisibleToolBar(): Boolean {
        return true
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

       setToolBarLeftTitle("个人信息")
        mViewBinding.rlSex.setOnClickListener(this)
        mViewBinding.rlAge.setOnClickListener(this)
        mViewBinding.rlStature.setOnClickListener(this)
        mViewBinding.rlTargetWeight.setOnClickListener(this)
        mViewBinding.rlTargetStep.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        super.initData()
        mUserData = AgedBuyCache.getUserInfo()
        mViewBinding.tvTargetWeight.text = mUserData.targetWeight + "kg"
        mViewBinding.tvTargetStep.text = mUserData.targetStep + "步"
        mViewBinding.tvStature.text = mUserData.stature + "cm"
        mViewBinding.tvAge.text = mUserData.age + "岁"
        mViewBinding.tvSex.text = mUserData.sex

    }

    override fun initViewModel(): DefaultViewModel =
        ViewModelProvider(this).get(DefaultViewModel::class.java)

    override fun initViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ActivityPersonInformationBinding =
        ActivityPersonInformationBinding.inflate(layoutInflater, parent, false)

    override fun onClick(v: View) {
        when (v.id) {
            R.id.rl_sex -> showSexDialog()
            R.id.rl_age -> showAgeDialog()
            R.id.rl_stature -> showStatureDialog()
            R.id.rl_target_weight -> showTargetWeightDialog()
            R.id.rl_target_step -> showTargetStepDialog()
        }
    }

    private fun showSexDialog() {
        if (mSexPickerDialog == null) {
            mSexList.add("男")
            mSexList.add("女")
            mSexPickerDialog = OptionsPickerBuilder(
                this
            ) { options1, options2, options3, v ->
                var str = mSexList[options1]
                mViewBinding.tvSex.text = str
                mUserData = AgedBuyCache.getUserInfo()
                mUserData.sex = str
                AgedBuyCache.saveUserInfo(mUserData)

            }.setLayoutRes(R.layout.dialog_person_info_setting, object : CustomListener {
                override fun customLayout(v: View?) {
                    var title = v?.findViewById<TextView>(R.id.tv_title)
                    var cancel = v?.findViewById<TextView>(R.id.btn_cancel)
                    var submit = v?.findViewById<TextView>(R.id.btn_submit)
                    title?.text = "性别"
                    cancel?.setOnClickListener {
                        mSexPickerDialog?.dismiss()
                    }
                    submit?.setOnClickListener {
                        mSexPickerDialog?.returnData()
                        mSexPickerDialog?.dismiss()
                    }
                }

            })
                .setSelectOptions( AgedBuyCache.getUserInfo().sexLocation)  //设置默认选中项
                .setOutSideCancelable(true)//点击外部dismiss default true
                .setTextColorCenter(Color.parseColor("#F2682A"))
                .isDialog(true)//是否显示为对话框样式
                .build()
            mSexPickerDialog?.setPicker(mSexList)
            val lp: FrameLayout.LayoutParams =
                mSexPickerDialog!!.dialogContainerLayout.layoutParams as FrameLayout.LayoutParams
            lp.leftMargin = 0
            lp.rightMargin = 0
            mSexPickerDialog!!.dialog.window?.setGravity(Gravity.BOTTOM)
            mSexPickerDialog!!.dialog.window?.setWindowAnimations(R.style.picker_view_slide_anim)
            mSexPickerDialog?.show()
        } else {
            mSexPickerDialog?.show()
        }
    }

    private fun showAgeDialog() {
        if (mAgePickerDialog == null) {
            for (index in 100 downTo 1) {
                mAgeList.add(index.toString())
            }
            mAgePickerDialog = OptionsPickerBuilder(
                this
            ) { options1, options2, options3, v ->
                var str = mAgeList[options1]
                mViewBinding.tvAge.text = str + "岁"
                mUserData = AgedBuyCache.getUserInfo()
                mUserData.age = str
                mUserData.ageLocation = options1
                AgedBuyCache.saveUserInfo(mUserData)

            }.setLayoutRes(R.layout.dialog_person_info_setting, object : CustomListener {
                override fun customLayout(v: View?) {
                    var title = v?.findViewById<TextView>(R.id.tv_title)
                    var cancel = v?.findViewById<TextView>(R.id.btn_cancel)
                    var submit = v?.findViewById<TextView>(R.id.btn_submit)
                    title?.text = "年龄"
                    cancel?.setOnClickListener {
                        mAgePickerDialog?.dismiss()
                    }
                    submit?.setOnClickListener {
                        mAgePickerDialog?.returnData()
                        mAgePickerDialog?.dismiss()
                    }
                }

            }).setLabels("岁", "", "")
                .isCenterLabel(true)
                .setSelectOptions( AgedBuyCache.getUserInfo().ageLocation)
                .setLineSpacingMultiplier(3.0F)
                .setTextColorCenter(Color.parseColor("#F2682A"))
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build()
            mAgePickerDialog?.setPicker(mAgeList)
            val lp: FrameLayout.LayoutParams =
                mAgePickerDialog!!.dialogContainerLayout.layoutParams as FrameLayout.LayoutParams
            lp.leftMargin = 0
            lp.rightMargin = 0
            mAgePickerDialog!!.dialog.window?.setGravity(Gravity.BOTTOM)
            mAgePickerDialog!!.dialog.window?.setWindowAnimations(R.style.picker_view_slide_anim)
            mAgePickerDialog?.show()
        } else {
            mAgePickerDialog?.show()
        }
    }

    private fun showStatureDialog() {
        if (mStaturePickerDialog == null) {
            for (index in 250 downTo 100) {
                mStatureList.add(index.toString())
            }
            mStaturePickerDialog = OptionsPickerBuilder(
                this
            ) { options1, options2, options3, v ->
                var str = mStatureList[options1]
                mViewBinding.tvStature.text = str + "cm"
                mUserData = AgedBuyCache.getUserInfo()
                mUserData.stature = str
                mUserData.statureLocation = options1
                AgedBuyCache.saveUserInfo(mUserData)

            }.setLayoutRes(R.layout.dialog_person_info_setting, object : CustomListener {
                override fun customLayout(v: View?) {
                    var title = v?.findViewById<TextView>(R.id.tv_title)
                    var cancel = v?.findViewById<TextView>(R.id.btn_cancel)
                    var submit = v?.findViewById<TextView>(R.id.btn_submit)
                    title?.text = "身高"
                    cancel?.setOnClickListener {
                        mStaturePickerDialog?.dismiss()
                    }
                    submit?.setOnClickListener {
                        mStaturePickerDialog?.returnData()
                        mStaturePickerDialog?.dismiss()
                    }
                }

            }).setLabels("cm", "", "")
                .isCenterLabel(true)
                .setSelectOptions( AgedBuyCache.getUserInfo().statureLocation)
                .setLineSpacingMultiplier(3.0F)
                .setTextColorCenter(Color.parseColor("#F2682A"))
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build()
            mStaturePickerDialog?.setPicker(mStatureList)
            val lp: FrameLayout.LayoutParams =
                mStaturePickerDialog!!.dialogContainerLayout.layoutParams as FrameLayout.LayoutParams
            lp.leftMargin = 0
            lp.rightMargin = 0
            mStaturePickerDialog!!.dialog.window?.setGravity(Gravity.BOTTOM)
            mStaturePickerDialog!!.dialog.window?.setWindowAnimations(R.style.picker_view_slide_anim)
            mStaturePickerDialog?.show()
        } else {
            mStaturePickerDialog?.show()
        }
    }

    private fun showTargetWeightDialog() {
        if (mTargetWeightPickerDialog == null) {
            for (index in 150 downTo 30) {
                mTargetWeightList.add(index.toString())
            }
            mTargetWeightPickerDialog = OptionsPickerBuilder(
                this
            ) { options1, options2, options3, v ->
                var str = mTargetWeightList[options1]
                mViewBinding.tvTargetWeight.text = str + "kg"
                mUserData = AgedBuyCache.getUserInfo()
                mUserData.targetWeight = str
                mUserData.targetWeightLocation = options1
                AgedBuyCache.saveUserInfo(mUserData)

            }.setLayoutRes(R.layout.dialog_person_info_setting, object : CustomListener {
                override fun customLayout(v: View?) {
                    var title = v?.findViewById<TextView>(R.id.tv_title)
                    var cancel = v?.findViewById<TextView>(R.id.btn_cancel)
                    var submit = v?.findViewById<TextView>(R.id.btn_submit)
                    title?.text = "目标体重"
                    cancel?.setOnClickListener {
                        mTargetWeightPickerDialog?.dismiss()
                    }
                    submit?.setOnClickListener {
                        mTargetWeightPickerDialog?.returnData()
                        mTargetWeightPickerDialog?.dismiss()
                    }
                }

            }).setLabels("kg", "", "")
                .isCenterLabel(true)
                .setSelectOptions( AgedBuyCache.getUserInfo().targetWeightLocation)
                .setLineSpacingMultiplier(3.0F)
                .setTextColorCenter(Color.parseColor("#F2682A"))
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build()
            mTargetWeightPickerDialog?.setPicker(mTargetWeightList)
            val lp: FrameLayout.LayoutParams =
                mTargetWeightPickerDialog!!.dialogContainerLayout.layoutParams as FrameLayout.LayoutParams
            lp.leftMargin = 0
            lp.rightMargin = 0
            mTargetWeightPickerDialog!!.dialog.window?.setGravity(Gravity.BOTTOM)
            mTargetWeightPickerDialog!!.dialog.window?.setWindowAnimations(R.style.picker_view_slide_anim)
            mTargetWeightPickerDialog?.show()
        } else {
            mTargetWeightPickerDialog?.show()
        }
    }

    private fun showTargetStepDialog() {
        if (mTargetStepPickerDialog == null) {
            for (index in 100000 downTo 1000) {
                if (index % 1000 == 0) {
                    mTargetStepList.add(index.toString())
                }
            }
            mTargetStepPickerDialog = OptionsPickerBuilder(
                this
            ) { options1, options2, options3, v ->
                var str = mTargetStepList[options1]
                mViewBinding.tvTargetStep.text = str + "步"
                mUserData = AgedBuyCache.getUserInfo()
                mUserData.targetStep = str
                mUserData.targetStepLocation = options1
                AgedBuyCache.saveUserInfo(mUserData)

            }.setLayoutRes(R.layout.dialog_person_info_setting, object : CustomListener {
                override fun customLayout(v: View?) {
                    var title = v?.findViewById<TextView>(R.id.tv_title)
                    var cancel = v?.findViewById<TextView>(R.id.btn_cancel)
                    var submit = v?.findViewById<TextView>(R.id.btn_submit)
                    title?.text = "目标步数"
                    cancel?.setOnClickListener {
                        mTargetStepPickerDialog?.dismiss()
                    }
                    submit?.setOnClickListener {
                        mTargetStepPickerDialog?.returnData()
                        mTargetStepPickerDialog?.dismiss()
                    }
                }

            }).setLabels("步", "", "")
                .isCenterLabel(true)
                .setSelectOptions( AgedBuyCache.getUserInfo().targetStepLocation)
                .setLineSpacingMultiplier(3.0F)
                .setTextColorCenter(Color.parseColor("#F2682A"))
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .build()
            mTargetStepPickerDialog?.setPicker(mTargetStepList)
            val lp: FrameLayout.LayoutParams =
                mTargetStepPickerDialog !!.dialogContainerLayout.layoutParams as FrameLayout.LayoutParams
            lp.leftMargin = 0
            lp.rightMargin = 0
            mTargetStepPickerDialog!!.dialog.window?.setGravity(Gravity.BOTTOM)
            mTargetStepPickerDialog!!.dialog.window?.setWindowAnimations(R.style.picker_view_slide_anim)
            mTargetStepPickerDialog?.show()
        } else {
            mTargetStepPickerDialog?.show()
        }
    }

}