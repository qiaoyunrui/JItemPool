package com.juhezi.jitempool

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.juhezi.itempool.Item
import com.juhezi.itempool.ItemEventHandler

/**
 * Created by qiao1 on 2016/10/8.
 */
class TestItem : Item<String>() {
    private var TAG = "TestItem"

    var mTvShow : TextView? = null

    override fun onBindItem(holder: RecyclerView.ViewHolder?, data: String?, eventHandler: ItemEventHandler?) {
        mTvShow?.text = data
    }

    override fun onCreateItemView(inflater: LayoutInflater?, parent: ViewGroup?): View {
        var itemView = inflater?.inflate(R.layout.test_item, parent, false)
        mTvShow = itemView?.findViewById(R.id.tv_test_item_show) as TextView
        return itemView!!
    }
}
