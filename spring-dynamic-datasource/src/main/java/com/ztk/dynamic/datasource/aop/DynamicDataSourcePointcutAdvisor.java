/*
 * description
 */
package com.ztk.dynamic.datasource.aop;

import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.annotation.Annotation;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public class DynamicDataSourcePointcutAdvisor extends AbstractPointcutAdvisor {

    private Advice advice;
    private Pointcut pointcut;

    public DynamicDataSourcePointcutAdvisor(MethodInterceptor interceptor) {
        this.advice = interceptor;
        this.pointcut = buildPointcut(DynamicDataSource.class);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    private Pointcut buildPointcut(Class<? extends Annotation> annotationType) {
        ComposablePointcut result = null;
        Pointcut cpc = new AnnotationMatchingPointcut(annotationType, true);
        Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(annotationType);
        if (result == null) {
            result = new ComposablePointcut(cpc);
        }
        return result.union(mpc);
    }
}
