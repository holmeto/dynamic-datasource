/*
 * description
 */
package com.ztk.dynamic.datasource.route;

import com.ztk.dynamic.datasource.provider.DynamicDataSourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource implements InitializingBean, DisposableBean {

    private final Logger log = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    private DynamicDataSourceProvider dynamicDataSourceProvider;

    private Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();

    protected DataSource shardingDataSource;

    public DynamicRoutingDataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        this.dynamicDataSourceProvider = dynamicDataSourceProvider;
    }

    @Override
    protected DataSource determineDataSource() {
        if(DynamicDataSourceContextHolder.isSharding()) {
            return shardingDataSource;
        }
        String poolName = DynamicDataSourceContextHolder.peek();
        if (log.isDebugEnabled()) {
            log.debug("[dynamic data source] choose pool named {}", poolName);
        }
        return getDataSource(poolName);
    }

    public DataSource getDataSource(String poolName) {
        if (StringUtils.isEmpty(poolName) || !dataSourceMap.containsKey(poolName)) {
            throw new RuntimeException("[dynamic data source] pool named " + poolName + " is lost");
        }
        return dataSourceMap.get(poolName);
    }

    public synchronized void addDataSource(String poolName, DataSource dataSource) {
        dataSourceMap.put(poolName, dataSource);
    }

    public synchronized void registerShardingDataSource(DataSource dataSource) {
        this.shardingDataSource = dataSource;
    }

    public synchronized void removeDataSource(String poolName) {
        if (dataSourceMap.containsKey(poolName)) {
            DataSource dataSource = dataSourceMap.get(poolName);
            dataSourceMap.remove(poolName);
            log.info("[dynamic data source] remove the data source named [{}] success", poolName);
        } else {
            log.warn("[dynamic data source] could not find a data source named [{}]", poolName);
        }
    }


    @Override
    public void destroy() throws Exception {
        log.info("[dynamic data source] start closing data sources ...");
        for (Map.Entry<String, DataSource> item : dataSourceMap.entrySet()) {
            DataSource dataSource = item.getValue();
            Class<? extends DataSource> clazz = dataSource.getClass();
            try {
                Method closeMethod = clazz.getDeclaredMethod("close");
                closeMethod.invoke(dataSource);
            } catch (NoSuchMethodException e) {
                log.warn("[dynamic data source] data source named [{}] close failed", item.getKey());
            }
        }
        log.info("[dynamic data source] data sources close success!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> dataSources = dynamicDataSourceProvider.load();
        for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
            addDataSource(dsItem.getKey(), dsItem.getValue());
        }
    }
}
