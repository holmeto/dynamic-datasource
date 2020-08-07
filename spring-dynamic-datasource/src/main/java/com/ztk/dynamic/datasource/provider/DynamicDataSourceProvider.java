/*
 * description
 */
package com.ztk.dynamic.datasource.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public interface DynamicDataSourceProvider {

    /**
     * load all data sources
     *
     * @return
     */
    Map<String, DataSource> load();

}
