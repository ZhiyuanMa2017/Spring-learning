package com.squirrel.druidmultijpa.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidDBConfig {

    private final Logger LOGGER = LoggerFactory.getLogger(DruidDBConfig.class);

    @Autowired
    private DruidConfig druidOneConfig;
    @Autowired
    private DruidConfig druidTwoConfig;
    @Autowired
    private DruidConfig druidConfig;

    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource oneDataSource() {
        return initDruidDataSource(druidOneConfig);
    }

    @Bean(name = "secondaryDataSource")
    public DataSource twoDataSource() {
        return initDruidDataSource(druidTwoConfig);
    }


    private DruidDataSource initDruidDataSource(DruidConfig config) {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setInitialSize(config.getInitialSize());
        dataSource.setMinIdle(config.getMinIdle());
        dataSource.setMaxActive(config.getMaxActive());

        dataSource.setMaxWait(druidConfig.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidConfig.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        dataSource.setMaxEvictableIdleTimeMillis(druidConfig.getMaxEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidConfig.getValidationQuery());
        dataSource.setTestWhileIdle(druidConfig.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidConfig.isTestOnBorrow());
        dataSource.setTestOnReturn(druidConfig.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidConfig.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(
                druidConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dataSource.setFilters(druidConfig.getFilters());
        } catch (SQLException e) {
            LOGGER.error("druid config init filter : {0}", e);
        }
        dataSource.setConnectionProperties(druidConfig.getConnectionProperties());
        return dataSource;
    }
}
