/*
 * description
 */
package com.ztk.dynamic.datasource.auto;

import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.creator.hikari.HikariCpConfig;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DataSourceProperty {

    private String group = DynamicContants.POOL_GROUP_DEFAULT;

    private String pollName;

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    @NestedConfigurationProperty
    private HikariCpConfig hikari = new HikariCpConfig();

    public String getGroup() {
        return group;
    }

    public DataSourceProperty setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getPollName() {
        return pollName;
    }

    public DataSourceProperty setPollName(String pollName) {
        this.pollName = pollName;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DataSourceProperty setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceProperty setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceProperty setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceProperty setPassword(String password) {
        this.password = password;
        return this;
    }

    public HikariCpConfig getHikari() {
        return hikari;
    }

}
