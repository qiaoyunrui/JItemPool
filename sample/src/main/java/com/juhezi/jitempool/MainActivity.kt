package com.juhezi.jitempool

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.juhezi.itempool.ItemPool

class MainActivity : AppCompatActivity() {

    private var mRvList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRvList = findViewById(R.id.rv_main_list) as RecyclerView
        mRvList?.layoutManager = LinearLayoutManager(this)
        var itemPool = ItemPool()
        itemPool?.addType(TestItem::class.java)
        itemPool?.addType(HeaderItem::class.java)

        itemPool?.add(2)
        itemPool?.add("HelloWorld1")
        itemPool?.add("HelloWorld2")
        itemPool?.add("HelloWorld3")
        itemPool?.add("HelloWorld4")
        itemPool?.add("HelloWorld5")
        itemPool?.add(3)
        itemPool?.attachTo(mRvList)

    }
}
