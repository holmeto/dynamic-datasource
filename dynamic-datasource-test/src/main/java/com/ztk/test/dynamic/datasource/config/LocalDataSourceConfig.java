/*
 * description
 */
package com.ztk.test.dynamic.datasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.ztk.test.dynamic.datasource.mapper2.Mapper2PK;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@Configuration
@MapperScan(basePackageClasses = {Mapper2PK.class}, sqlSessionFactoryRef = "localSqlSessionFactoryBean")
public class LocalDataSourceConfig {

    @Bean("localDataSource")
    public DataSource localDataSource() {
        final HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/local_1?useSSL=true");
        config.setUsername("root");
        config.setPassword("123456");
        final HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean("localSqlSessionFactoryBean")
    public SqlSessionFactory localSqlSessionFactoryBean(@Autowired @Qualifier(value = "localDataSource") final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
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
