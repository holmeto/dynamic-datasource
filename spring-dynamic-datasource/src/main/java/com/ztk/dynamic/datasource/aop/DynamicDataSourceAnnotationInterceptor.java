/*
 * description
 */
package com.ztk.dynamic.datasource.aop;

import com.ztk.dynamic.datasource.route.DynamicDataSourceContextHolder;
import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import com.ztk.dynamic.datasource.strategy.RoutingStrategy;
import com.ztk.dynamic.datasource.strategy.StrategyFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        DynamicDataSource dynamicDataSource = detection(invocation);
        boolean sharding = check(dynamicDataSource);
        try {
            if (sharding) {
                DynamicDataSourceContextHolder.sharding();
            } else {
                DynamicDataSourceContextHolder.push(determineDatasource(dynamicDataSource));
            }
            return invocation.proceed();
        } finally {
            if (sharding) {
                DynamicDataSourceContextHolder.unsharding();
            } else {
                DynamicDataSourceContextHolder.poll();
            }
        }
    }

    private DynamicDataSource detection(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        return method.isAnnotationPresent(DynamicDataSource.class)
                ? method.getAnnotation(DynamicDataSource.class)
                : AnnotationUtils.findAnnotation(target.getClass(), DynamicDataSource.class);
    }

    private boolean check(DynamicDataSource dynamicDataSource) {
        return dynamicDataSource.sharding();
    }

    private String determineDatasource(DynamicDataSource dynamicDataSource) {
        String group = dynamicDataSource.group();
        Class strategyType = dynamicDataSource.strategy();
        RoutingStrategy routingStrategy = StrategyFactory.build(strategyType, true);
        return routingStrategy.route(group);
    }


}
