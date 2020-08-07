/*
 * description
 */
package com.ztk.dynamic.datasource.creator;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import com.ztk.dynamic.datasource.auto.DataSourceProperty;
import com.ztk.dynamic.datasource.contants.DynamicContants;

import javax.sql.DataSource;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public abstract class AbstractDataSourceCreator implements DataSourceCreator {

    private final DynamicDataSourceContext context = DynamicDataSourceContext.of();

    /**
     * create data source
     *
     * @param dataSourceProperty
     * @return
     */
    public abstract DataSource create(DataSourceProperty dataSourceProperty);

    @Override
    public final DataSource createDataSource(DataSourceProperty dataSourceProperty) {
        DataSource dataSource = create(dataSourceProperty);
        String group = dataSourceProperty.getGroup();
        String poolName = dataSourceProperty.getPollName();
        if (null == group) {
            group = DynamicContants.POOL_GROUP_DEFAULT;
        }
        if (null == poolName) {
            poolName = DynamicContants.POOL_DEFAULT;
        }
        context.register(group, poolName, dataSource);
        return dataSource;
    }


}
