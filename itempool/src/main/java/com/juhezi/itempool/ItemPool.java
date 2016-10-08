package com.juhezi.itempool;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qiao1 on 2016/10/8.
 */
public class ItemPool extends ArrayList<Object> {

    private static String TAG = "ItemPool";
    //所有的ItemType中的TYPE_ID都是从这个数加上去的
    private static final AtomicInteger ID_BASE = new AtomicInteger(0);

    private final HashMap<Class, ItemType> typeMap = new HashMap<>();
    private final SparseArray<Class<? extends Item>> itemClassMap = new SparseArray<>();
    private final ItemAdapter internalAdapter = new ItemAdapter(this);

    /**
     * 添加不同类型的Item
     *
     * @param itemClass
     */
    public void addType(@NonNull Class<? extends Item> itemClass) {
        //获取待带有泛型的父类
        ParameterizedType parameterizedType =
                (ParameterizedType) itemClass.getGenericSuperclass();
        //获取泛型的Class
        Class dataClass = (Class) parameterizedType.getActualTypeArguments()[0];

        ItemType type = new ItemType(itemClass);
        typeMap.put(dataClass, type);
        itemClassMap.put(type.TYPE_ID, itemClass);
    }

    /**
     * 设置监听事件
     *
     * @param handler
     */
    public void onEvent(ItemEventHandler handler) {
        Collection<ItemType> itemTypes = typeMap.values();  //获取所有的Type

        for (ItemType type : itemTypes) {
            type.handler = handler;
        }
    }

    public void attachTo(RecyclerView recyclerView) {
        recyclerView.setAdapter(internalAdapter);
    }

    public void addWithAction(Object o, Action zero2Nonzero) {
        add(o);
        if (size() == 1) {   //长度从0变到1
            zero2Nonzero.onAction();
        }
        notifyItemInserted(size());   //显示插入动画
    }

    public void removeWithAction(int index, Action nonzero2Zero) {
        remove(index);
        if (size() == 0) {
            nonzero2Zero.onAction();
        }
        notifyItemInserted(index);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return super.addAll(c);
    }

    public void notifyDataSetChanged() {
        internalAdapter.notifyDataSetChanged();
    }

    public void notifyItemChanged(int position) {
        internalAdapter.notifyItemRangeChanged(position, 1);
    }

    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        internalAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    public void notifyItemInserted(int position) {
        internalAdapter.notifyItemRangeInserted(position, 1);
    }

    public void notifyItemMoved(int fromPosition, int toPosition) {
        internalAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        internalAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    public void notifyItemRemoved(int position) {
        internalAdapter.notifyItemRangeRemoved(position, 1);
    }

    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        internalAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

    protected int getItemType(int index) {
        Class dataClass = get(index).getClass();
        ItemType type = typeMap.get(dataClass);
        if (type == null) {
            throw new RuntimeException("No item set for the data type: " + dataClass.getSimpleName());
        }
        return type.TYPE_ID;
    }

    protected Class<? extends Item> getItemClass(int typeId) {
        return itemClassMap.get(typeId);
    }

    protected Pair getItemClass(Class dataClass) {
        ItemType type = typeMap.get(dataClass);
        return new Pair(type.itemClass, type.handler);
    }

    private static class ItemType {
        private final int TYPE_ID;  //Adapter中表示ViewHolder的类别
        private final Class<? extends Item> itemClass;  //Item的实现类
        private ItemEventHandler handler;

        private ItemType(Class<? extends Item> itemClass) {
            TYPE_ID = ID_BASE.getAndIncrement();
            this.itemClass = itemClass;
        }

    }

}
