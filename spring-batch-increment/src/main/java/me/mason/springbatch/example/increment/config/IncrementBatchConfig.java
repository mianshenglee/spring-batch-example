package me.mason.springbatch.example.increment.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.User;
import me.mason.springbatch.example.increment.listener.IncrementJobEndListener;
import me.mason.springbatch.example.increment.step.IncrementUserItemProcessor;
import me.mason.springbatch.example.increment.step.IncrementUserItemReader;
import me.mason.springbatch.example.increment.step.IncrementUserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
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

import java.util.Map;

/**
 * increment Batch配置类
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class IncrementBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job incrementJob(Step incrementStep,JobExecutionListener incrementListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(incrementListener)
                .flow(incrementStep)
                .end().build();
    }
    @Bean
    public Step incrementStep(ItemReader incrementItemReader ,ItemProcessor incrementProcessor
            ,ItemWriter incrementWriter){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .<User,User>chunk(10)
                .reader(incrementItemReader)
                .processor(incrementProcessor)
                .writer(incrementWriter)
                .build();
    }

    /**
     * 根据传入的参数读取数据，直接使用字符串模板，注意需要添加{@link StepScope}注解,
     * @param lastUpdateTime sql参数，上次最后更新时间
     * @return
     */
    @Bean
    @StepScope
    public ItemReader incrementItemReader(@Value("#{jobParameters['lastUpdateTime']}") String lastUpdateTime) {
        IncrementUserItemReader userItemReader = new IncrementUserItemReader();
        //设置参数，当前示例可不设置参数
        Map<String,Object> params = CollUtil.newHashMap();
        params.put(SyncConstants.STR_LAST_UPDATE_TIME,lastUpdateTime);
        userItemReader.setParams(params);

        return userItemReader;
    }

    @Bean
    public ItemWriter incrementWriter() {
        return new IncrementUserItemWriter();
    }

    @Bean
    public ItemProcessor incrementProcessor(){
        return new IncrementUserItemProcessor();
    }

    @Bean
    public JobExecutionListener incrementListener(){
        return new IncrementJobEndListener();
    }
}
