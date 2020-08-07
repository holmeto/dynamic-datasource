/*
 * description
 */
package com.ztk.dynamic.datasource.route;

import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 类的描述
 *
 * @author ztkool
 * @version v
 */
public abstract class AbstractRoutingDataSource extends AbstractDataSource {

    /**
     * determine data source
     *
     * @return
     */
    protected abstract DataSource determineDataSource();

    @Override
    public Connection getConnection() throws SQLException {
        return determineDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineDataSource().getConnection(username, password);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return determineDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return iface.isInstance(this) || determineDataSource().isWrapperFor(iface);
    }
}
