package me.mason.springbatch.example.mysql2mongo.config;

import cn.hutool.core.collection.CollUtil;
import me.mason.springbatch.entity.User;
import me.mason.springbatch.entity.mongo.MongoUser;
import me.mason.springbatch.example.mysql2mongo.listener.MongoJobEndListener;
import me.mason.springbatch.example.mysql2mongo.step.UserItemProcessor;
import me.mason.springbatch.example.mysql2mongo.step.UserItemReader;
import me.mason.springbatch.example.mysql2mongo.step.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.Map;

/**
 * beetlsql Batch配置类
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class MongoBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job mongoJob(Step mongoStep,JobExecutionListener mongoListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(mongoListener)
                .flow(mongoStep)
                .end().build();
    }
    @Bean
    public Step mongoStep(ItemReader mongoItemReader ,ItemProcessor mongoProcessor
            ,ItemWriter mongoWriter){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User,User>chunk(10)
                .reader(mongoItemReader)
                .processor(mongoProcessor)
                .writer(mongoWriter)
                .build();
    }

    @Bean
    public ItemReader mongoItemReader() {
        UserItemReader userItemReader = new UserItemReader();
        //设置参数，当前示例可不设置参数
        Map<String,Object> params = CollUtil.newHashMap();
        userItemReader.setParams(params);

        return userItemReader;
    }

    @Bean
    public ItemWriter mongoWriter(MongoOperations mongoTemplate) {
        UserItemWriter userItemWriter = new UserItemWriter();
        userItemWriter.setTemplate(mongoTemplate);
        userItemWriter.setCollection("user");
        return userItemWriter;
    }

    @Bean
    public ItemProcessor mongoProcessor(){
        return new UserItemProcessor();
    }

    @Bean
    public JobExecutionListener mongoListener(){
        return new MongoJobEndListener();
    }
}
