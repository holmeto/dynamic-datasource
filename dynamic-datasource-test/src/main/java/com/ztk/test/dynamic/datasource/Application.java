/*
 * description
 */
package com.ztk.test.dynamic.datasource;

import com.ztk.dynamic.datasource.auto.DynamicManageType;
import com.ztk.dynamic.datasource.auto.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@SpringBootApplication
@EnableDynamicDataSource(type = DynamicManageType.AUTO, sharding = true)
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
