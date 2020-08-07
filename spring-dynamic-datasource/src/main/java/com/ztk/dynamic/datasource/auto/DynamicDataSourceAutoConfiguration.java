/*
 * description
 */
package com.ztk.dynamic.datasource.auto;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.creator.DataSourceCreator;
import com.ztk.dynamic.datasource.creator.HikariDataSourceCreator;
import com.ztk.dynamic.datasource.provider.DefaultDynamicDataSourceProvider;
import com.ztk.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.ztk.dynamic.datasource.route.DynamicRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@ConditionalOnProperty(prefix = DynamicContants.AUTO_CONFIGURATION_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DynamicDataSourceAutoConfiguration {

    @Autowired
    private DynamicDataSourceProperties properties;

    @Bean
    @ConditionalOnMissingBean(DynamicRoutingDataSource.class)
    public DynamicDataSourceContext dynamicDataSourceContext() {
        return DynamicDataSourceContext.of();
    }

    @Bean
    @ConditionalOnMissingBean(DynamicDataSourceProvider.class)
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new DefaultDynamicDataSourceProvider(properties);
    }

    @Bean
    @ConditionalOnMissingBean(DataSourceCreator.class)
    public DataSourceCreator dataSourceCreator() {
        return new HikariDataSourceCreator(properties.getHikari());
    }

    @Bean(DynamicContants.DYNAMIC_ROUTING_DATA_SOURCE)
    @ConditionalOnMissingBean(DynamicRoutingDataSource.class)
    public DataSource dynamicRoutingDataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        return new DynamicRoutingDataSource(dynamicDataSourceProvider);
    }

    @Bean(DynamicContants.DYNAMIC_SQL_SESSION_FACTORY)
    @Primary
    @ConditionalOnMissingBean(name = {DynamicContants.DYNAMIC_SQL_SESSION_FACTORY})
    public SqlSessionFactory dynamicDataSourceFactoryBean(
            @Autowired @Qualifier(value = DynamicContants.DYNAMIC_ROUTING_DATA_SOURCE) final DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=pageNumKey;pageSize=pageSizeKey;");
        return bean.getObject();
    }

}
