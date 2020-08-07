package com.ztk.dynamic.datasource.strategy;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
class StrategyFactoryBean<T extends RoutingStrategy> {

    private Class<T> target;
    private boolean isSingleton;

    private static final Map<Class, StrategyFactoryBean> cache = new ConcurrentHashMap<>(16);
    private static final Map<Class, RoutingStrategy> instanceCache = new ConcurrentHashMap<>(16);

    private StrategyFactoryBean(Class<T> target, boolean isSingleton) {
        this.target = target;
        this.isSingleton = isSingleton;
    }

    public static <T extends RoutingStrategy> StrategyFactoryBean of(Class<T> clazz, boolean isSingleton) {
        if (isSingleton) {
            if (null == cache.get(clazz)) {
                StrategyFactoryBean strategyFactoryBean = new StrategyFactoryBean<>(clazz, isSingleton);
                cache.putIfAbsent(clazz, strategyFactoryBean);
            } else {
                return cache.get(clazz);
            }
        }
        return new StrategyFactoryBean<>(clazz, isSingleton);
    }

    public RoutingStrategy getObject() {
        if (isSingleton) {
            if (instanceCache.containsKey(target)) {
                return instanceCache.get(target);
            } else {
                RoutingStrategy instance = get();
                instanceCache.putIfAbsent(target, instance);
                return instance;
            }
        }
        return get();
    }

    private RoutingStrategy get() {
        return (RoutingStrategy) Proxy.newProxyInstance(target.getClassLoader(),
                new Class[]{RoutingStrategy.class},
                new StrategyHandler<>(target));
    }

}