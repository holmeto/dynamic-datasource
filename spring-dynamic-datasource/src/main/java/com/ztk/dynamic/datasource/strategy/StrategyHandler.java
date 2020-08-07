package com.ztk.dynamic.datasource.strategy;

import java.lang.reflect.Method;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class StrategyHandler<T extends RoutingStrategy> extends AbstractInvocation<T> {

    public StrategyHandler(Class<T> target) {
        super(target);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.proxy, args);
    }

}