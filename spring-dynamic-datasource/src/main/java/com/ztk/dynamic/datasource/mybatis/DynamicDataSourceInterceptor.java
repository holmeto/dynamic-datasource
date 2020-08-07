/*
 *
 */
package com.ztk.dynamic.datasource.mybatis;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.exception.DynamicException;
import com.ztk.dynamic.datasource.route.DynamicDataSourceContextHolder;
import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import com.ztk.dynamic.datasource.strategy.RoutingStrategy;
import com.ztk.dynamic.datasource.strategy.StrategyFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    private DynamicDataSourceContext dynamicDataSourceContext;

    public DynamicDataSourceInterceptor(DynamicDataSourceContext dynamicDataSourceContext) {
        this.dynamicDataSourceContext = dynamicDataSourceContext;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        try {
            DynamicDataSourceContextHolder.push(getDataSource(ms));
            return invocation.proceed();
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    /**
     * choose data source
     *
     * @param mappedStatement
     * @return
     */
    public String getDataSource(MappedStatement mappedStatement) {
        String id = mappedStatement.getId();
        DynamicDataSource dynamicDataSource = dynamicDataSourceContext.getMapper(id);
        if (null == dynamicDataSource) {
            throw new DynamicException("[dynamic data source] error when get data source by id [{}]", id);
        }
        String group = dynamicDataSource.group();
        String[] poolName = dynamicDataSource.poolName();
        if (null == group) {
            if (null == poolName || poolName.length == 0) {
                return DynamicContants.POOL_DEFAULT;
            }
        }
        Class strategyType = dynamicDataSource.strategy();
        RoutingStrategy routingStrategy = StrategyFactory.build(strategyType);
        return routingStrategy.route(group);

    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
