package com.ztk.dynamic.datasource.creator.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.ztk.dynamic.datasource.utils.AssignUtil;

import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class HikariCpConfig {

    private String username;
    private String password;
    private String driverClassName;
    private String jdbcUrl;
    private String poolName;

    private String catalog;
    private Long connectionTimeout;
    private Long validationTimeout;
    private Long idleTimeout;
    private Long leakDetectionThreshold;
    private Long maxLifetime;
    private Integer maxPoolSize;
    private Integer minIdle;

    private Long initializationFailTimeout;
    private String connectionInitSql;
    private String connectionTestQuery;
    private String dataSourceClassName;
    private String dataSourceJndiName;
    private String schema;
    private String transactionIsolationName;
    private Boolean isAutoCommit;
    private Boolean isReadOnly;
    private Boolean isIsolateInternalQueries;
    private Boolean isRegisterMbeans;
    private Boolean isAllowPoolSuspension;
    private Properties dataSourceProperties;
    private Properties healthCheckProperties;

    public HikariConfig toHikariConfig(HikariCpConfig globalConfig) {
        HikariConfig config = new HikariConfig();
        AssignUtil.setValueOrDefault(globalConfig.getSchema(), config::setSchema, schema);
        AssignUtil.setValueOrDefault(globalConfig.getCatalog(), config::setCatalog, catalog);
        AssignUtil.setValueOrDefault(globalConfig.getConnectionTimeout(), config::setConnectionTimeout, connectionTimeout);
        AssignUtil.setValueOrDefault(globalConfig.getValidationTimeout(), config::setValidationTimeout, validationTimeout);
        AssignUtil.setValueOrDefault(globalConfig.getIdleTimeout(), config::setIdleTimeout, idleTimeout);
        AssignUtil.setValueOrDefault(globalConfig.getLeakDetectionThreshold(), config::setLeakDetectionThreshold, leakDetectionThreshold);
        AssignUtil.setValueOrDefault(globalConfig.getMaxLifetime(), config::setMaxLifetime, maxLifetime);
        AssignUtil.setValueOrDefault(globalConfig.getMaxPoolSize(), config::setMaximumPoolSize, maxPoolSize);
        AssignUtil.setValueOrDefault(globalConfig.getMinIdle(), config::setMinimumIdle, minIdle);
        AssignUtil.setValueOrDefault(globalConfig.getInitializationFailTimeout(), config::setInitializationFailTimeout, initializationFailTimeout);
        AssignUtil.setValueOrDefault(globalConfig.getConnectionInitSql(), config::setConnectionInitSql, connectionInitSql);
        AssignUtil.setValueOrDefault(globalConfig.getConnectionTestQuery(), config::setConnectionTestQuery, connectionTestQuery);
        AssignUtil.setValueOrDefault(globalConfig.getDataSourceClassName(), config::setDataSourceClassName, dataSourceClassName);
        AssignUtil.setValueOrDefault(globalConfig.getDataSourceJndiName(), config::setDataSourceJNDI, dataSourceJndiName);
        AssignUtil.setValueOrDefault(globalConfig.getTransactionIsolationName(), config::setTransactionIsolation, transactionIsolationName);
        AssignUtil.setValueOrDefault(globalConfig.getIsAutoCommit(), config::setAutoCommit, isAutoCommit);
        AssignUtil.setValueOrDefault(globalConfig.getIsReadOnly(), config::setReadOnly, isReadOnly);
        AssignUtil.setValueOrDefault(globalConfig.getIsIsolateInternalQueries(), config::setIsolateInternalQueries, isIsolateInternalQueries);
        AssignUtil.setValueOrDefault(globalConfig.getIsRegisterMbeans(), config::setRegisterMbeans, isRegisterMbeans);
        AssignUtil.setValueOrDefault(globalConfig.getIsAllowPoolSuspension(), config::setAllowPoolSuspension, isAllowPoolSuspension);
        AssignUtil.setValueOrDefault(globalConfig.getDataSourceProperties(), config::setDataSourceProperties, dataSourceProperties);
        AssignUtil.setValueOrDefault(globalConfig.getHealthCheckProperties(), config::setHealthCheckProperties, healthCheckProperties);
        return config;
    }

    public String getUsername() {
        return username;
    }

    public HikariCpConfig setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public HikariCpConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public HikariCpConfig setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public HikariCpConfig setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public String getPoolName() {
        return poolName;
    }

    public HikariCpConfig setPoolName(String poolName) {
        this.poolName = poolName;
        return this;
    }

    public String getCatalog() {
        return catalog;
    }

    public HikariCpConfig setCatalog(String catalog) {
        this.catalog = catalog;
        return this;
    }

    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    public HikariCpConfig setConnectionTimeout(Long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public Long getValidationTimeout() {
        return validationTimeout;
    }

    public HikariCpConfig setValidationTimeout(Long validationTimeout) {
        this.validationTimeout = validationTimeout;
        return this;
    }

    public Long getIdleTimeout() {
        return idleTimeout;
    }

    public HikariCpConfig setIdleTimeout(Long idleTimeout) {
        this.idleTimeout = idleTimeout;
        return this;
    }

    public Long getLeakDetectionThreshold() {
        return leakDetectionThreshold;
    }

    public HikariCpConfig setLeakDetectionThreshold(Long leakDetectionThreshold) {
        this.leakDetectionThreshold = leakDetectionThreshold;
        return this;
    }

    public Long getMaxLifetime() {
        return maxLifetime;
    }

    public HikariCpConfig setMaxLifetime(Long maxLifetime) {
        this.maxLifetime = maxLifetime;
        return this;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public HikariCpConfig setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public HikariCpConfig setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public Long getInitializationFailTimeout() {
        return initializationFailTimeout;
    }

    public HikariCpConfig setInitializationFailTimeout(Long initializationFailTimeout) {
        this.initializationFailTimeout = initializationFailTimeout;
        return this;
    }

    public String getConnectionInitSql() {
        return connectionInitSql;
    }

    public HikariCpConfig setConnectionInitSql(String connectionInitSql) {
        this.connectionInitSql = connectionInitSql;
        return this;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public HikariCpConfig setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
        return this;
    }

    public String getDataSourceClassName() {
        return dataSourceClassName;
    }

    public HikariCpConfig setDataSourceClassName(String dataSourceClassName) {
        this.dataSourceClassName = dataSourceClassName;
        return this;
    }

    public String getDataSourceJndiName() {
        return dataSourceJndiName;
    }

    public HikariCpConfig setDataSourceJndiName(String dataSourceJndiName) {
        this.dataSourceJndiName = dataSourceJndiName;
        return this;
    }

    public String getSchema() {
        return schema;
    }

    public HikariCpConfig setSchema(String schema) {
        this.schema = schema;
        return this;
    }

    public String getTransactionIsolationName() {
        return transactionIsolationName;
    }

    public HikariCpConfig setTransactionIsolationName(String transactionIsolationName) {
        this.transactionIsolationName = transactionIsolationName;
        return this;
    }

    public Boolean getIsAutoCommit() {
        return isAutoCommit;
    }

    public HikariCpConfig setIsAutoCommit(Boolean isAutoCommit) {
        this.isAutoCommit = isAutoCommit;
        return this;
    }

    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    public HikariCpConfig setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
        return this;
    }

    public Boolean getIsIsolateInternalQueries() {
        return isIsolateInternalQueries;
    }

    public HikariCpConfig setIsIsolateInternalQueries(Boolean isIsolateInternalQueries) {
        this.isIsolateInternalQueries = isIsolateInternalQueries;
        return this;
    }

    public Boolean getIsRegisterMbeans() {
        return isRegisterMbeans;
    }

    public HikariCpConfig setIsRegisterMbeans(Boolean isRegisterMbeans) {
        this.isRegisterMbeans = isRegisterMbeans;
        return this;
    }

    public Boolean getIsAllowPoolSuspension() {
        return isAllowPoolSuspension;
    }

    public HikariCpConfig setIsAllowPoolSuspension(Boolean isAllowPoolSuspension) {
        this.isAllowPoolSuspension = isAllowPoolSuspension;
        return this;
    }

    public Properties getDataSourceProperties() {
        return dataSourceProperties;
    }

    public HikariCpConfig setDataSourceProperties(Properties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
        return this;
    }

    public Properties getHealthCheckProperties() {
        return healthCheckProperties;
    }

    public HikariCpConfig setHealthCheckProperties(Properties healthCheckProperties) {
        this.healthCheckProperties = healthCheckProperties;
        return this;
    }
}
