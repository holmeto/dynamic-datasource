/*
 * description
 */
package com.ztk.dynamic.datasource.sharding.auto;

import com.ztk.dynamic.datasource.contants.DynamicContants;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@ConfigurationProperties(prefix = DynamicContants.AUTO_CONFIGURATION_PREFIX)
public class DynamicShardingProperties {

    @NestedConfigurationProperty
    private YamlShardingRuleConfiguration sharding = new YamlShardingRuleConfiguration();

    private Properties props = new Properties();

    public YamlShardingRuleConfiguration getSharding() {
        return sharding;
    }

    public Properties getProps() {
        return props;
    }
}
