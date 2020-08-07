package com.ztk.dynamic.datasource.scanner.auto;

import com.ztk.dynamic.datasource.aop.EnableAutoAopConfiguration;
import com.ztk.dynamic.datasource.auto.DynamicManageType;
import com.ztk.dynamic.datasource.auto.EnableDynamicDataSource;
import com.ztk.dynamic.datasource.exception.DynamicException;
import com.ztk.dynamic.datasource.mybatis.EnableDynamicInterceptor;
import com.ztk.dynamic.datasource.sharding.auto.DynamicShardingConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.util.Map;
import java.util.Set;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicScanConfiguration implements ImportBeanDefinitionRegistrar, BaseConfiguration {

    private final Class ANNOTATION_IMPORT = EnableDynamicDataSource.class;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (!annotationMetadata.hasAnnotation(ANNOTATION_IMPORT.getName())) {
            throw new DynamicException("error when import class by @EnableDynamicDataSource !");
        }
        Set<String> annons = annotationMetadata.getAnnotationTypes();
        if (annons.size() > 0) {
            for (String annoName : annons) {
                if (annoName.equals(ANNOTATION_IMPORT.getName())) {
                    Map<String, Object> annoFieldMap = annotationMetadata.getAnnotationAttributes(ANNOTATION_IMPORT.getName());
                    DynamicManageType type = (DynamicManageType) annoFieldMap.get(TYPE);
                    if (null != type) {
                        // scan classes
                        dynamicDataSourceScan(annotationMetadata, beanDefinitionRegistry);
                        if (DynamicManageType.AUTO.equals(type)) {
                            // register auto aop
                            autoPointcut(beanDefinitionRegistry);
                        } else if (DynamicManageType.PLUGIN.equals(type)) {
                            // register mybatis plugin
                            mybatisPlugin(beanDefinitionRegistry);
                        } else if (DynamicManageType.MANUAL.equals(type)) {
                            // manual management
                        }
                    }

                    // enable sharding by sharding jdbc
                    boolean enableSharding = (boolean) annoFieldMap.get(ENABLE_SHARDING);
                    if (enableSharding) {
                        enableSharding(beanDefinitionRegistry);
                    }
                }
            }
        }
    }

    private void dynamicDataSourceScan(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<String, Object> annoFieldMap = annotationMetadata.getAnnotationAttributes(ANNOTATION_IMPORT.getName());
        String[] dsPackages = (String[]) annoFieldMap.get(BASE_PACKAGES);
        if (null != dsPackages && dsPackages.length > 0) {
            setAttributes(dsPackages);
        } else {
            Class[] dsPackageClasses = (Class[]) annoFieldMap.get(BASE_PACKAGE_CLASSES);
            if (dsPackageClasses == null || dsPackageClasses.length == 0) {
                if (annotationMetadata instanceof StandardAnnotationMetadata) {
                    Class<?> introspectedClass = ((StandardAnnotationMetadata) annotationMetadata).getIntrospectedClass();
                    setAttributes(introspectedClass);
                } else {
                    throw new DynamicException("error when import class by @EnableDynamicDataSource ! factoryPackages is null!");
                }
            } else {
                setAttributes(dsPackageClasses);
            }
        }
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) beanDefinitionRegistry;
        DynamicScanner scanner = new DynamicScanner(factory);
        scanner.doScan((String[]) getAttributes());
    }

    private void autoPointcut(BeanDefinitionRegistry beanDefinitionRegistry) {
        register(beanDefinitionRegistry, EnableAutoAopConfiguration.class);
    }

    private void mybatisPlugin(BeanDefinitionRegistry beanDefinitionRegistry) {
        register(beanDefinitionRegistry, EnableDynamicInterceptor.class);
    }

    private void enableSharding(BeanDefinitionRegistry beanDefinitionRegistry) {
        register(beanDefinitionRegistry, DynamicShardingConfiguration.class);
    }

    private void register(BeanDefinitionRegistry beanDefinitionRegistry, Class clazz) {
        String beanName = clazz.getName();
        if (beanDefinitionRegistry.isBeanNameInUse(beanName)) {
            return;
        }
        BeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(clazz)
                .setLazyInit(true)
                .getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
    }

}