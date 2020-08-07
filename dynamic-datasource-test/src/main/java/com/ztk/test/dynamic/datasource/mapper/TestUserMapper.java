package com.ztk.test.dynamic.datasource.mapper;

import com.ztk.dynamic.datasource.scanner.DynamicDataSource;
import com.ztk.dynamic.datasource.strategy.RandomStrategy;
import com.ztk.test.dynamic.datasource.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
@DynamicDataSource(name = "testUserMapper", group = "group1", strategy = RandomStrategy.class)
public interface TestUserMapper {

    User select();

    User getById(@Param("id") int id);

}
