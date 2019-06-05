package me.mason.springbatch.example.beetlsql.config;

import cn.hutool.core.collection.CollUtil;
import me.mason.springbatch.entity.User;
import me.mason.springbatch.example.beetlsql.listener.BeetlsqlJobEndListener;
import me.mason.springbatch.example.beetlsql.step.UserItemProcessor;
import me.mason.springbatch.example.beetlsql.step.UserItemReader;
import me.mason.springbatch.example.beetlsql.step.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * beetlsql Batch配置类
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class BeetlsqlBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job beetlsqlJob(Step beetlsqlStep,JobExecutionListener beetlsqlListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(beetlsqlListener)
                .flow(beetlsqlStep)
                .end().build();
    }
    @Bean
    public Step beetlsqlStep(ItemReader beetlsqlItemReader ,ItemProcessor beetlsqlProcessor
            ,ItemWriter beetlsqlWriter){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User,User>chunk(10)
                .reader(beetlsqlItemReader)
                .processor(beetlsqlProcessor)
                .writer(beetlsqlWriter)
                .build();
    }

    @Bean
    public ItemReader beetlsqlItemReader() {
        UserItemReader userItemReader = new UserItemReader();
        //设置参数，当前示例可不设置参数
        Map<String,Object> params = CollUtil.newHashMap();
        userItemReader.setParams(params);

        return userItemReader;
    }

    @Bean
    public ItemWriter beetlsqlWriter() {
        return new UserItemWriter();
    }

    @Bean
    public ItemProcessor beetlsqlProcessor(){
        return new UserItemProcessor();
    }

    @Bean
    public JobExecutionListener beetlsqlListener(){
        return new BeetlsqlJobEndListener();
    }
}
