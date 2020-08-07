/*
 * description
 */
package com.ztk.dynamic.datasource.provider;

import com.ztk.dynamic.datasource.auto.DataSourceProperty;
import com.ztk.dynamic.datasource.auto.DynamicDataSourceProperties;

import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DefaultDynamicDataSourceProvider extends AbstractDataSourceProvider {

    private DynamicDataSourceProperties properties;

    public DefaultDynamicDataSourceProvider(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    protected Map<String, DataSourceProperty> loadDataSources() {
        return properties.getDatasources();
    }

}
