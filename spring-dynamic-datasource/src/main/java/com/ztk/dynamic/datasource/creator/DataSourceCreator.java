package com.ztk.dynamic.datasource.creator;

import com.ztk.dynamic.datasource.auto.DataSourceProperty;

import javax.sql.DataSource;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public interface DataSourceCreator {

    /**
     * create data source
     *
     * @param dataSourceProperty
     * @return
     */
    DataSource createDataSource(DataSourceProperty dataSourceProperty);
}
