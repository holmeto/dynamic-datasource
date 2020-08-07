/*
 * description
 */
package com.ztk.dynamic.datasource.sharding.auto;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.exception.DynamicException;
import com.ztk.dynamic.datasource.route.DynamicRoutingDataSource;
import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@EnableConfigurationProperties({DynamicShardingProperties.class})
@AutoConfigureBefore({DynamicRoutingDataSource.class})
public class DynamicShardingConfiguration {

    @Autowired
    private DynamicShardingProperties dynamicShardingProperties;

    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    @Bean(DynamicContants.DYNAMIC_SHARDING_DATA_SOURCE)
    public DataSource dynamicShardingDataSource(DynamicDataSourceContext context) throws SQLException {
        Map<String, DataSource> dataSourceMap = context.getPoolDataSource();
        if (dataSourceMap.isEmpty()) {
            throw new DynamicException("[dynamic data source] error when create sharding data source by empty data source pool");
        }
        DataSource shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
                new ShardingRuleConfigurationYamlSwapper().swap(dynamicShardingProperties.getSharding()), dynamicShardingProperties.getProps());
        dynamicRoutingDataSource.registerShardingDataSource(shardingDataSource);
        return shardingDataSource;
    }

}
