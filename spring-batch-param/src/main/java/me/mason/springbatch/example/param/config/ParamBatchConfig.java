package me.mason.springbatch.example.param.config;

import cn.hutool.core.collection.CollUtil;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.origin.User;
import me.mason.springbatch.example.param.listener.ParamJobEndListener;
import me.mason.springbatch.example.param.step.ParamItemProcessor;
import me.mason.springbatch.example.param.step.UserItemReader;
import me.mason.springbatch.example.param.step.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * param Batch配置类
 *
 * @author mason
 * @since 2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class ParamBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job paramJob(Step paramStep, JobExecutionListener paramListener) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(paramListener)
                .flow(paramStep)
                .end().build();
    }

    @Bean
    public Step paramStep(ItemReader paramItemReader, ItemProcessor paramProcessor
            , ItemWriter paramWriter) {
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User, User>chunk(3)
                .reader(paramItemReader)
                .processor(paramProcessor)
                .writer(paramWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader paramItemReader(@Value("#{stepExecution}") StepExecution stepExecution,
                                      @Value("#{jobParameters['time']}") Long timeParam) {
        UserItemReader userItemReader = new UserItemReader();
        //设置参数，当前示例可不设置参数
        Map<String, Object> params = CollUtil.newHashMap();
        Date datetime = new Date(timeParam);
        params.put(SyncConstants.PASS_PARAM_DATETIME, datetime);
        userItemReader.setParams(params);
        userItemReader.setStepExecution(stepExecution);

        return userItemReader;
    }

    @Bean
    public ItemWriter paramWriter() {
        return new UserItemWriter();
    }

    @Bean
    public ItemProcessor paramProcessor() {
        return new ParamItemProcessor();
    }

    @Bean
    public JobExecutionListener paramListener() {
        return new ParamJobEndListener();
    }
}
