package com.ztk.dynamic.datasource.strategy;

import com.ztk.dynamic.datasource.exception.DynamicException;

import java.lang.reflect.InvocationHandler;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public abstract class AbstractInvocation<T extends RoutingStrategy> implements InvocationHandler {

    protected Class<T> target;
    protected T proxy;

    protected AbstractInvocation(Class target) {
        this.createProxy(target);
    }

    @SuppressWarnings("unchecked")
    private T createProxy(Class<T> target) {
        this.target = target;
        try {
            this.proxy = target.newInstance();
        } catch (Exception e) {
            throw new DynamicException("[dynamic data source] error when create new instance of [{}] with non param", target);
        }
        return proxy;
    }

}