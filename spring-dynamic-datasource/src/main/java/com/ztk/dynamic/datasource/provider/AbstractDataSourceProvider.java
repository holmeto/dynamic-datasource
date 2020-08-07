/*
 * description
 */
package com.ztk.dynamic.datasource.provider;

import com.ztk.dynamic.datasource.auto.DataSourceProperty;
import com.ztk.dynamic.datasource.creator.DataSourceCreator;
import com.ztk.dynamic.datasource.exception.DynamicException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public abstract class AbstractDataSourceProvider implements DynamicDataSourceProvider {

    @Autowired
    private DataSourceCreator dataSourceCreator;

    /**
     * init data sources property
     *
     * @return
     */
    protected abstract Map<String, DataSourceProperty> loadDataSources();

    @Override
    public final Map<String, DataSource> load() {
        Map<String, DataSourceProperty> dataSourcePropertiesMap = loadDataSources();
        return createDataSourceMap(dataSourcePropertiesMap);
    }

    public Map<String, DataSource> createDataSourceMap(Map<String, DataSourceProperty> dataSourcePropertiesMap) {
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() * 2);
        for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
            DataSourceProperty dataSourceProperty = item.getValue();
            String pollName = dataSourceProperty.getPollName();
            if (pollName == null || "".equals(pollName)) {
                pollName = item.getKey();
                if (dataSourceMap.containsKey(pollName)) {
                    throw new DynamicException("[dynamic data source] Duplicate pool name [{}]", pollName);
                }
            }
            dataSourceProperty.setPollName(pollName);
            dataSourceMap.put(pollName, dataSourceCreator.createDataSource(dataSourceProperty));
        }
        return dataSourceMap;
    }

}
