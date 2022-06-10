package com.example.testjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * 类名： JdbcConfig
 * 描述 TODO：
 * 创建时间： 2022/5/19 15:33
 * 创建人： Administrator
 */
@Configuration
public class JdbcConfig {

    /**
     * 装配事务管理器
     */
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * JDBC事务操作配置
     */
    @Bean(name = "txTemplate")
    public TransactionTemplate transactionTemplate (@Autowired DataSourceTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
}
