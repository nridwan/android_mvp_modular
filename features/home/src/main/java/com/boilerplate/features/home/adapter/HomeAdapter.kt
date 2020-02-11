package com.boilerplate.features.home.adapter

import coil.api.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.boilerplate.data.model.data.User
import com.boilerplate.features.home.R
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(data: List<User>): BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_home, data) {
    override fun convert(helper: BaseViewHolder?, item: User?) {
        if (helper != null && item != null) {
            helper.itemView.textView.text = item.login
            helper.itemView.imageView.load(item.avatarUrl)
        }
    }

}