package com.lind.startProject.adapter

import com.lind.startProject.bean.ProductBean
import com.lind.startProject.R
import com.lind.startProject.databinding.ItemProductLayoutBinding

class MallProductAdapter(mData: List<ProductBean>) : BaseBindingAdapter<ProductBean, ItemProductLayoutBinding>(mData) {
    private var mItemClickListener: ((ProductBean) -> Unit)? = null

    fun setOnItemClickListener(listener: ((ProductBean) -> Unit)?) {
        mItemClickListener = listener
    }

    override fun getLayoutResId(viewType: Int): Int = R.layout.item_product_layout

    override fun onBindItem(binding: ItemProductLayoutBinding, item: ProductBean, position: Int) {
        binding.data = item
        binding.clItem.setOnClickListener {
            mItemClickListener?.invoke(item)
        }
        binding.executePendingBindings();
    }
}