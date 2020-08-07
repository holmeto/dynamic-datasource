/*
 * description
 */
package com.ztk.dynamic.datasource.contants;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public interface DynamicContants {

    String AUTO_CONFIGURATION_PREFIX = "spring.datasource.dynamic";

    String POOL_GROUP_DEFAULT = "default_group";

    String POOL_DEFAULT = "default_pool";


    /**
     * bean name dynamicRoutingDataSource
     */

    String DYNAMIC_ROUTING_DATA_SOURCE = "dynamic_routing_data_source";

    /**
     * bean name dynamicDataSourceFactoryBean
     */
    String DYNAMIC_SQL_SESSION_FACTORY = "dynamic_data_source_factory_bean";

    /**
     * bean name dynamicShardingDataSource
     */
    String DYNAMIC_SHARDING_DATA_SOURCE = "dynamic_sharding_data_source";

}
