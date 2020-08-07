/*
 * description
 */
package com.ztk.dynamic.datasource.mybatis;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import org.springframework.context.annotation.Bean;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class EnableDynamicInterceptor {

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor(DynamicDataSourceContext.of());
    }

}
