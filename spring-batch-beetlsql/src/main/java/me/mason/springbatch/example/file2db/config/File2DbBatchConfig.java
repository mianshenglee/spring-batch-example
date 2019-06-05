package me.mason.springbatch.example.file2db.config;

import me.mason.springbatch.entity.User;
import me.mason.springbatch.example.file2db.listener.File2DbJobEndListener;
import me.mason.springbatch.example.file2db.mapper.UserFieldSetMapper;
import me.mason.springbatch.example.file2db.step.File2DbItemProcessor;
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
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * file2db Batch配置类
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class File2DbBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job file2DbJob(Step file2DbStep,JobExecutionListener file2DbListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(file2DbListener)
                .flow(file2DbStep)
                .end().build();
    }
    @Bean
    public Step file2DbStep(ItemReader file2DbItemReader , ItemProcessor file2DbProcessor
            ,ItemWriter file2DbWriter){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User,User>chunk(10)
                .reader(file2DbItemReader)
                .processor(file2DbProcessor)
                .writer(file2DbWriter)
                .build();
    }

    @Bean
    public ItemReader file2DbItemReader(){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return new FlatFileItemReaderBuilder<User>()
                .name(funcName)
                .resource(new ClassPathResource("user-data.csv"))
//                .linesToSkip(1)
                .delimited()
                .names(new String[]{"id","name","phone","title","email","gender","date_of_birth","sys_create_time","sys_create_user","sys_update_time","sys_update_user"})
                .fieldSetMapper(new UserFieldSetMapper())
                .build();
    }

    @Bean
    public ItemProcessor file2DbProcessor(){
        return new File2DbItemProcessor();
    }

    @Bean
    public ItemWriter file2DbWriter(@Qualifier("targetDatasource") DataSource datasource){
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO test_user(id,name,phone,title,email,gender,date_of_birth,sys_create_time,sys_create_user,sys_update_time,sys_update_user) " +
                        "VALUES (:id,:name,:phone,:title,:email,:gender,:dateOfBirth,:sysCreateTime,:sysCreateUser,:sysUpdateTime,:sysUpdateUser)")
                .dataSource(datasource)
                .build();
    }

    @Bean
    public JobExecutionListener file2DbListener(){
        return new File2DbJobEndListener();
    }
}
