package com.juhezi.itempool;

import android.support.v7.widget.RecyclerView;

/**
 * Created by qiao1 on 2016/10/8.
 */
public class ItemEvent {
    private static String TAG = "ItemEvent";
    public static final int ITEM_CLICK = 0x123;
    public static final int ITEM_LONGCLICK = 0x124;

    public int action;
    public Object data;
    public RecyclerView.ViewHolder holder;

    public ItemEvent(int action, Object data, RecyclerView.ViewHolder holder) {
        this.action = action;
        this.data = data;
        this.holder = holder;
    }
}
