package me.mason.springbatch.example.db2db.config;

import me.mason.springbatch.entity.User;
import me.mason.springbatch.example.db2db.listener.Db2DbJobEndListener;
import me.mason.springbatch.example.db2db.mapper.UserRowMapper;
import me.mason.springbatch.example.db2db.step.Db2DbItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * db2db Batch配置类
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class Db2DbBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job db2DbJob(Step db2DbStep,JobExecutionListener db2DbListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(db2DbListener)
                .flow(db2DbStep)
                .end().build();
    }
    @Bean
    public Step db2DbStep(ItemReader db2DbItemReader ,ItemProcessor db2DbProcessor
            ,ItemWriter db2DbWriter){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User,User>chunk(10)
                .reader(db2DbItemReader)
                .processor(db2DbProcessor)
                .writer(db2DbWriter)
                .build();
    }

    @Bean
    public ItemReader db2DbItemReader(@Qualifier("originDatasource") DataSource originDatasource) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String readSql = " select * from test_user";
        return new JdbcCursorItemReaderBuilder<User>().name(funcName)
                .dataSource(originDatasource).sql(readSql)
                .verifyCursorPosition(false).rowMapper(new UserRowMapper())
                .build();
    }

    @Bean
    public ItemWriter db2DbWriter(@Qualifier("targetDatasource") DataSource targetDatasource) {
        String inserSql ="INSERT INTO test_user(id,name,phone,title,email,gender,date_of_birth,sys_create_time,sys_create_user,sys_update_time,sys_update_user) " +
                "VALUES (:id,:name,:phone,:title,:email,:gender,:dateOfBirth,:sysCreateTime,:sysCreateUser,:sysUpdateTime,:sysUpdateUser)";
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(inserSql)
                .dataSource(targetDatasource)
                .build();
    }

    @Bean
    public ItemProcessor db2DbProcessor(){
        return new Db2DbItemProcessor();
    }

    @Bean
    public JobExecutionListener db2DbListener(){
        return new Db2DbJobEndListener();
    }
}
