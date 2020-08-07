/*
 * description
 */
package com.ztk.dynamic.datasource.utils;

import java.util.function.Consumer;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class AssignUtil {

    public static <T> void setValueOrDefault(T value, Consumer<T> consumer, T... defaults) {
        if (null != value) {
            consumer.accept(value);
        } else {
            for (T def : defaults) {
                if (null != def) {
                    consumer.accept(def);
                    return;
                }
            }

        }
    }
}
