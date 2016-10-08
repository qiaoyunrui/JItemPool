package com.juhezi.itempool;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiao1 on 2016/10/8.
 */
public abstract class Item<Data> {
    private static String TAG = "Item";

    protected class InternalViewHolder extends RecyclerView.ViewHolder {

        protected final Item item;

        public InternalViewHolder(View itemView) {
            super(itemView);
            this.item = Item.this;
        }
    }

    private InternalViewHolder mViewHolder;

    public abstract View onCreateItemView(
            LayoutInflater inflater,
            ViewGroup parent);

    public abstract void onBindItem(
            final RecyclerView.ViewHolder holder,
            final Data data,
            ItemEventHandler eventHandler);

    public ItemEvent event(int action, Object data) {
        return new ItemEvent(action, data, mViewHolder);
    }

    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = onCreateItemView(LayoutInflater.from(parent.getContext()), parent);
        mViewHolder = new InternalViewHolder(itemView);
        return mViewHolder;
    }


}
