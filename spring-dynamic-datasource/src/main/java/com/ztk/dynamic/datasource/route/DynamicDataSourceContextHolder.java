/*
 * description
 */
package com.ztk.dynamic.datasource.route;

import org.springframework.util.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicDataSourceContextHolder {

    private final static ThreadLocal<Deque<String>> LOOKUP_KEY_HOLDER = ThreadLocal.withInitial(ArrayDeque::new);
    private final static ThreadLocal<Boolean> SHARDING_DATA_SOURCE = ThreadLocal.withInitial(() -> false);

    private DynamicDataSourceContextHolder() {
    }

    public static String peek() {
        return LOOKUP_KEY_HOLDER.get().peek();
    }

    public static void push(String key) {
        LOOKUP_KEY_HOLDER.get().push(StringUtils.isEmpty(key) ? "" : key);
    }

    public static void poll() {
        Deque<String> deque = LOOKUP_KEY_HOLDER.get();
        deque.poll();
        if (deque.isEmpty()) {
            LOOKUP_KEY_HOLDER.remove();
        }
    }

    public static void clear() {
        LOOKUP_KEY_HOLDER.remove();
    }

    public static void sharding() {
        SHARDING_DATA_SOURCE.set(true);
    }

    public static boolean isSharding(){
        return SHARDING_DATA_SOURCE.get();
    }

    public static void unsharding() {
        SHARDING_DATA_SOURCE.set(false);
    }

}
