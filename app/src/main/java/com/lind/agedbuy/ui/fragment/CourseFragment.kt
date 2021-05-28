package com.lind.agedbuy.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lind.agedbuy.R
import com.lind.agedbuy.databinding.FragmentCourseBinding
import com.lind.agedbuy.viewmodel.CourseViewModel
import com.lind.lib_base.uibase.BaseFragment
import com.lind.lib_base.uibase.viewmodel.DefaultViewModel
import com.lind.lib_service.utils.SystemBarHelper


class CourseFragment : BaseFragment<DefaultViewModel, FragmentCourseBinding>() {
    private val TAG = "CourseFragment"
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        SystemBarHelper.immersiveStatusBar(activity, 0f)
        SystemBarHelper.setHeightAndPadding(activity, mViewBinding?.topView)
        initRecycle()
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun initRecycle() {
        mViewBinding?.courseRecycle?.layoutManager = GridLayoutManager(context,2, RecyclerView.VERTICAL, false)
        val recyclerAdapter = RecycleAdapter()
        mViewBinding?.courseRecycle?.adapter = recyclerAdapter
    }

    override fun initViewBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentCourseBinding = FragmentCourseBinding.inflate(inflater, viewGroup, false)

    override fun initViewModel(): DefaultViewModel =
        ViewModelProvider(this).get(DefaultViewModel::class.java)

    inner class RecycleAdapter : RecyclerView.Adapter<RecycleAdapter.VideoHolder>() {

        inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val videoTitle: TextView = itemView.findViewById(R.id.video_index)
            val videoPoster: ImageView = itemView.findViewById(R.id.video_poster)

            init {
                itemView.setOnClickListener {
//                    val recyclerIntent = Intent(context, CoursePlayActivity::class.java)
//                    var bundle = Bundle()
//                    bundle.putInt("position", bindingAdapterPosition)
//                    recyclerIntent.putExtras(bundle)
//                    startActivity(recyclerIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
            return VideoHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_view_video,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount() = CourseViewModel.urls.size

        override fun onBindViewHolder(holder: VideoHolder, position: Int) {
            holder.run {
                videoPoster.setImageResource(CourseViewModel.picture[position])
                videoTitle.text = CourseViewModel.name[position]
            }
        }
    }
}