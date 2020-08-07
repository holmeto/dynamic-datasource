/*
 * description
 */
package com.ztk.dynamic.datasource.strategy;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class StrategyFactory {

    private StrategyFactory(){}

    public static RoutingStrategy build(Class<RoutingStrategy> clazz) {
        StrategyFactoryBean strategyFactoryBean = StrategyFactoryBean.of(clazz, true);
        return strategyFactoryBean.getObject();
    }

    public static RoutingStrategy build(Class<RoutingStrategy> clazz, boolean isSingleton) {
        StrategyFactoryBean strategyFactoryBean = StrategyFactoryBean.of(clazz, isSingleton);
        return strategyFactoryBean.getObject();
    }
}
