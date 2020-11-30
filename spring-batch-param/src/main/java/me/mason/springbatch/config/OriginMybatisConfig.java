package me.mason.springbatch.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 源数据库配置
 *
 * @author: mason
 * @date: 2020/11/20
 **/
@Configuration
@MapperScan(basePackages = "me.mason.springbatch.mapper.origin", sqlSessionFactoryRef = "originSqlSessionFactory")
public class OriginMybatisConfig {

    @Bean("originSqlSessionFactory")
    public SqlSessionFactory originSqlSessionFactory(@Qualifier("originDatasource") DataSource dataSource) throws Exception {
        // 设置数据源
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mapper的xml文件位置
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        String locationPattern = "classpath*:/mapper/master/*.xml";
//        mybatisSqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));

        //对应数据库的entity位置
        String typeAliasesPackage = "me.mason.springbatch.entity.origin";
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return mybatisSqlSessionFactoryBean.getObject();
    }
}
