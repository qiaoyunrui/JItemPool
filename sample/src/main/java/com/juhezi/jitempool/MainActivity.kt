package com.juhezi.jitempool

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.juhezi.itempool.Action
import com.juhezi.itempool.ItemPool

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private var mRvList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRvList = findViewById(R.id.rv_main_list) as RecyclerView
        mRvList?.layoutManager = LinearLayoutManager(this)
        var itemPool = ItemPool()
        itemPool?.addType(TestItem::class.java)
        itemPool?.addType(HeaderItem::class.java)

        itemPool?.addWithAction(2, object : Action {
            override fun onAction() {
                Log.i(TAG, "HEHEHE")
            }
        })
        itemPool?.addWithAction("HelloWorld1", {
            Log.i(TAG,"What?")
        })
        itemPool?.add("HelloWorld2")
        itemPool?.add("HelloWorld3")
        itemPool?.add("HelloWorld4")
        itemPool?.add("HelloWorld5")
        itemPool?.add(3)
        itemPool?.attachTo(mRvList)

    }
}
