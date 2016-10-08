package com.juhezi.itempool;

import android.support.annotation.NonNull;

/**
 * Created by qiao1 on 2016/10/8.
 */
public interface ItemEventHandler {

    void onEvent(@NonNull Class<? extends Item> clazz,
                 @NonNull ItemEvent event);

}
