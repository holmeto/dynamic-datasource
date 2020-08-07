/*
 * description
 */
package com.ztk.test.dynamic.datasource.config;

import com.ztk.test.dynamic.datasource.mapper.MapperPK;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 类的描述
 *
 * @author sunyue
 * @date 2020/7/31 下午5:14
 */
@Configuration
@MapperScan(basePackageClasses = {MapperPK.class})
public class InitConfig {


}
