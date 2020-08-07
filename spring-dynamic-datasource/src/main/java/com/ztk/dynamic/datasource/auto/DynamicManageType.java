/*
 * description
 */
package com.ztk.dynamic.datasource.auto;

import com.ztk.dynamic.datasource.route.DynamicDataSourceContextHolder;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public enum DynamicManageType {

    /**
     * scan by spring-dynamic-data-source
     */
    AUTO,

    /**
     * manual management
     *
     * @see DynamicDataSourceContextHolder#peek()
     * @see DynamicDataSourceContextHolder#push(String)
     * @see DynamicDataSourceContextHolder#poll()
     * @see DynamicDataSourceContextHolder#clear()
     */
    MANUAL,

    /**
     * routing by mybatis plugin interceptor
     */
    PLUGIN;
}
