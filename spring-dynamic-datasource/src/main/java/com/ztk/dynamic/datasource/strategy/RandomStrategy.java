/*
 * description
 */
package com.ztk.dynamic.datasource.strategy;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * polling strategy
 *
 * @author ztkool
 * @version v
 */
public class RandomStrategy implements RoutingStrategy {

    private final static AtomicInteger index = new AtomicInteger(0);

    @Override
    public String route(String group) {
        Map<String, Map<String, DataSource>> groupDataSource = dynamicDataSourceContext.getGroupDataSource();
        Map<String, DataSource> dataSources = groupDataSource.get(group);
        Object[] poolNames = dataSources.keySet().toArray();
        int choose = Math.abs(index.getAndIncrement()) % poolNames.length;
        return (String) poolNames[choose];
    }

}
