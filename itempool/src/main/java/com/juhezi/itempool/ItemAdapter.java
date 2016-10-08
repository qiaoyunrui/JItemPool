package com.juhezi.itempool;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiao1 on 2016/10/8.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = "ItemAdapter";

    private final ItemPool mItemPool;

    public ItemAdapter(ItemPool itemPool) {
        mItemPool = itemPool;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: " + mItemPool.getItemClass(viewType));
        Item item = newItem(mItemPool.getItemClass(viewType));
        return item.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Object data = mItemPool.get(position);
        Pair<Class<? extends Item>, ItemEventHandler> pair = mItemPool.getItemClass(data.getClass());
        final Item item = ((Item.InternalViewHolder) holder).item;
        final ItemEventHandler handler = pair.second;
        item.onBindItem(holder, data, handler);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handler != null) {
                    handler.onEvent(item.getClass(), new ItemEvent(ItemEvent.ITEM_CLICK, data, holder));
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (handler != null) {
                    handler.onEvent(item.getClass(), new ItemEvent(ItemEvent.ITEM_LONGCLICK, data, holder));
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return mItemPool.getItemType(position);
    }

    @Override
    public int getItemCount() {
        return mItemPool.size();
    }

    private static <CreatedItem extends Item> CreatedItem newItem(Class<CreatedItem> itemClass) {
        Item item;
        try {
            item = itemClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return (CreatedItem) item;
    }
}
