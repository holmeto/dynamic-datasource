/*
 * description
 */
package com.ztk.dynamic.datasource.scanner.auto;

import com.ztk.dynamic.datasource.DynamicDataSourceContext;
import com.ztk.dynamic.datasource.exception.DynamicException;
import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicScanner extends ClassPathBeanDefinitionScanner {

    private final DynamicDataSourceContext dynamicDataSourceContext = DynamicDataSourceContext.of();

    public DynamicScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        if (annotationMetadata.hasAnnotation(DynamicDataSource.class.getName())) {
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            mapping(classMetadata);
            return true;
        }
        return super.isCandidateComponent(metadataReader);
    }

    private void mapping(ClassMetadata classMetadata) {
        String beanClassName = classMetadata.getClassName();
        try {
            Class<?> clazz = Class.forName(beanClassName);
            DynamicDataSource clazzDynamicDataSource = clazz.getDeclaredAnnotation(DynamicDataSource.class);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                String id = buildId(beanClassName, method.getName());
                DynamicDataSource dynamicDataSource = method.getDeclaredAnnotation(DynamicDataSource.class);
                if (null == dynamicDataSource) {
                    dynamicDataSourceContext.mapping(id, clazzDynamicDataSource);
                } else {
                    dynamicDataSourceContext.mapping(id, dynamicDataSource);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new DynamicException("[dynamic data source] error when scan mapper with @DynamicDataSource", e);
        }
    }

    private String buildId(String beanName, String methodName) {
        return beanName + "." + methodName;
    }

}