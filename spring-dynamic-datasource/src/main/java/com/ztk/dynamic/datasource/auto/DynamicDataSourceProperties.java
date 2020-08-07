/*
 * description
 */
package com.ztk.dynamic.datasource.auto;

import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.creator.hikari.HikariCpConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@ConfigurationProperties(prefix = DynamicContants.AUTO_CONFIGURATION_PREFIX)
public class DynamicDataSourceProperties {

    @NestedConfigurationProperty
    private HikariCpConfig hikari = new HikariCpConfig();

    private Map<String, DataSourceProperty> datasources = new LinkedHashMap<>();

    public HikariCpConfig getHikari() {
        return hikari;
    }

    public Map<String, DataSourceProperty> getDatasources() {
        return datasources;
    }

}
