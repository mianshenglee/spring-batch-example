package me.mason.springbatch.config;

import com.ibeetl.starter.BeetlSqlMutipleSourceCustomize;
import me.mason.springbatch.interceptor.BeetlSqlDebugInterceptor;
import org.beetl.sql.core.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 * @author mason
 * @date 2019/6/1
 **/
@Configuration
public class DataSourceConfig {
    @Bean("datasource")
    @ConfigurationProperties(prefix="spring.datasource")
    @Primary
    public DataSource batchDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("originDatasource")
    @ConfigurationProperties(prefix="spring.origin-datasource")
    public DataSource originDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("targetDatasource")
    @ConfigurationProperties(prefix="spring.target-datasource")
    public DataSource targetDatasource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 自定义sqlManager
     * @return
     */
    @Bean
    public BeetlSqlMutipleSourceCustomize beetlSqlCustomize() {
        return (dataSource, sqlManager) -> {
            Interceptor[] interceptors = new Interceptor[1];
            interceptors[0] = new BeetlSqlDebugInterceptor();
            sqlManager.setInters(interceptors);
        };
    }
}
