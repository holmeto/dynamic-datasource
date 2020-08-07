/*
 * description
 */
package com.ztk.dynamic.datasource.strategy;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class ShardingStrategy implements RoutingStrategy {

    @Override
    public String route(String group) {
        Map<String, DataSource> dataSourceMap = dynamicDataSourceContext.getGroupDataSource(group);
        return null;
    }

}
