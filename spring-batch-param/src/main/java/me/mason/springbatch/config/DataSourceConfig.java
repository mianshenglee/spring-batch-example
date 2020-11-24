package me.mason.springbatch.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据源配置
 * @author mason
 * @date 2019/6/1
 **/
@Configuration
public class DataSourceConfig {
    @Bean("batchDatasource")
    @ConfigurationProperties(prefix="spring.batch-datasource")
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
}
