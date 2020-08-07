package com.ztk.dynamic.datasource.strategy;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public interface RoutingStrategy {

    DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.of();

    /**
     * route poolName by groupName
     *
     * @param group
     * @return
     */
    String route(String group);

}
