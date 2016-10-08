package com.juhezi.jitempool

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juhezi.itempool.Item
import com.juhezi.itempool.ItemEventHandler

/**
 * Created by qiao1 on 2016/10/8.
 */
class HeaderItem : Item<Int>() {
    private var TAG = "HeaderItem"

    override fun onCreateItemView(inflater: LayoutInflater?, parent: ViewGroup?): View {
        var itemView= inflater?.inflate(R.layout.header_item, parent, false)
        return itemView!!
    }

    override fun onBindItem(holder: RecyclerView.ViewHolder?, data: Int?, eventHandler: ItemEventHandler?) {

    }
}
