/*
 * description
 */
package com.ztk.dynamic.datasource.scanner;


import com.ztk.dynamic.datasource.contants.DynamicContants;
import com.ztk.dynamic.datasource.strategy.RandomStrategy;
import com.ztk.dynamic.datasource.strategy.RoutingStrategy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {

    String name() default "";

    /**
     * will use group to routing data source if group is not null
     *
     * @return
     * @see DynamicDataSource#poolName()
     */
    String group() default "";

    /**
     * will use pools to routing data source if group is null
     *
     * @return
     * @see DynamicDataSource#group()
     */
    String[] poolName() default DynamicContants.POOL_DEFAULT;

    /**
     * strategy of routing
     * <p>
     * if sharding = false
     * than use it to route data source
     * else use sharding jdbc
     *
     * @return
     */
    Class<? extends RoutingStrategy> strategy() default RandomStrategy.class;

    /**
     * use sharding jdbc to route data source
     * <p>
     * if sharding = false than use strategy
     *
     * @return
     */
    boolean sharding() default false;

}
