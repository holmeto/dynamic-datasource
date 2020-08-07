/*
 * description
 */
package com.ztk.dynamic.datasource.aop;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class EnableAutoAopConfiguration {

    @Bean
    @ConditionalOnMissingBean(DynamicDataSourcePointcutAdvisor.class)
    public DynamicDataSourcePointcutAdvisor dynamicDataSourcePointcutAdvisor() {
        DynamicDataSourceAnnotationInterceptor iterator = new DynamicDataSourceAnnotationInterceptor();
        return new DynamicDataSourcePointcutAdvisor(iterator);
    }

}
