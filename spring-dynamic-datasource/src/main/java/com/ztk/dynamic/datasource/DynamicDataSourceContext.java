/*
 * description
 */
package com.ztk.dynamic.datasource;

import com.ztk.dynamic.datasource.scanner.DynamicDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicDataSourceContext {

    private final Map<String, DataSource> POOL_DATA_SOURCES = new ConcurrentHashMap<>(16);
    private final Map<String, Map<String, DataSource>> GROUP_POOL_DATA_SOURCES = new ConcurrentHashMap<>(16);

    private final Map<String, DynamicDataSource> SWAP_MAPPERS = new ConcurrentHashMap<>(16);

    private DataSource shardingDataSource;

    private DynamicDataSourceContext() {
    }

    private static DynamicDataSourceContext dynamicDataSourceContext = new DynamicDataSourceContext();

    public static DynamicDataSourceContext of() {
        return dynamicDataSourceContext;
    }

    public synchronized DynamicDataSourceContext register(String groupName, String poolName, DataSource datasource) {
        POOL_DATA_SOURCES.putIfAbsent(poolName, datasource);
        Map<String, DataSource> groups = GROUP_POOL_DATA_SOURCES.get(groupName);
        if (null == groups) {
            groups = new ConcurrentHashMap<>(16);
            GROUP_POOL_DATA_SOURCES.put(groupName, groups);
        }
        groups.putIfAbsent(poolName, datasource);
        return this;
    }

    public synchronized DynamicDataSourceContext registerSharding(DataSource datasource) {
        this.shardingDataSource = datasource;
        return this;
    }

    public synchronized DynamicDataSourceContext mapping(String id, DynamicDataSource dynamicDataSource) {
        SWAP_MAPPERS.putIfAbsent(id, dynamicDataSource);
        return this;
    }

    public DataSource getPoolDataSource(String poolName) {
        return POOL_DATA_SOURCES.get(poolName);
    }

    public Map<String, DataSource> getPoolDataSource() {
        return POOL_DATA_SOURCES;
    }

    public Map<String, DataSource> getGroupDataSource(String groupName) {
        return GROUP_POOL_DATA_SOURCES.get(groupName);
    }

    public Map<String, Map<String, DataSource>> getGroupDataSource() {
        return GROUP_POOL_DATA_SOURCES;
    }

    public DataSource getShardingDataSource(){
        return this.shardingDataSource;
    }

    public DynamicDataSource getMapper(String id) {
        return SWAP_MAPPERS.get(id);
    }
}
