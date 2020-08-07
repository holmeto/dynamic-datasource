package com.ztk.dynamic.datasource.creator;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.ztk.dynamic.datasource.auto.DataSourceProperty;
import com.ztk.dynamic.datasource.creator.hikari.HikariCpConfig;

import javax.sql.DataSource;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class HikariDataSourceCreator extends AbstractDataSourceCreator {

    private HikariCpConfig hikariCpConfig;

    public HikariDataSourceCreator(HikariCpConfig hikariCpConfig) {
        this.hikariCpConfig = hikariCpConfig;
    }

    @Override
    public DataSource create(DataSourceProperty dataSourceProperty) {
        HikariConfig config = dataSourceProperty.getHikari().toHikariConfig(hikariCpConfig);
        config.setPoolName(dataSourceProperty.getPollName());
        config.setUsername(dataSourceProperty.getUsername());
        config.setPassword(dataSourceProperty.getPassword());
        config.setJdbcUrl(dataSourceProperty.getUrl());
        config.setDriverClassName(dataSourceProperty.getDriverClassName());
        return new HikariDataSource(config);
    }
}