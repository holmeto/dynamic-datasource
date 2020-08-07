/*
 * description
 */
package com.ztk.dynamic.datasource.sharding;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicShardingKeyGenerator implements ShardingKeyGenerator {

    private Properties properties = new Properties();

    @Override
    public Comparable<?> generateKey() {
        return null;
    }

    @Override
    public final String getType() {
        return "DYNAMIC_DATA_SOURCE";
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
