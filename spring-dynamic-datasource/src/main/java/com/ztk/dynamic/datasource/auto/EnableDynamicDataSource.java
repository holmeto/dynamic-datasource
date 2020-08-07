package com.ztk.dynamic.datasource.auto;

import com.ztk.dynamic.datasource.scanner.auto.DynamicScanConfiguration;
import org.springframework.context.annotation.Import;

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
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DynamicScanConfiguration.class, DynamicDataSourceAutoConfiguration.class})
public @interface EnableDynamicDataSource {

    /**
     * manage type for dynamic data source
     * <p>
     * default by auto spring aop
     *
     * @return
     */
    DynamicManageType type() default DynamicManageType.AUTO;

    /**
     * required: type = DynamicManageType.AUTO
     * <p>
     * use basePackages when it is not null, else use basePackageClasses
     *
     * @return
     * @see EnableDynamicDataSource#basePackageClasses()
     */
    String[] basePackages() default {};

    /**
     * required: type = DynamicManageType.AUTO
     *
     * @return
     * @see EnableDynamicDataSource#basePackages()
     */
    Class<?>[] basePackageClasses() default {};

    /**
     * enable sharding jdbc
     *
     * @return
     */
    boolean sharding() default false;

}
