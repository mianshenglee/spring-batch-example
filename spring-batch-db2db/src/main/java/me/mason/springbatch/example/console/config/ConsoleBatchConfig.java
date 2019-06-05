package me.mason.springbatch.example.console.config;

import me.mason.springbatch.example.console.listener.ConsoleJobEndListener;
import me.mason.springbatch.example.console.step.ConsoleWriter;
import me.mason.springbatch.example.console.step.ConvertProcessor;
import me.mason.springbatch.example.console.step.StringReader;
import me.mason.springbatch.listener.CommonStepListener;
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

/**
 * console batch任务配置
 * @author  mason
 * @since  2019/6/1
 **/
@Configuration
@EnableBatchProcessing
public class ConsoleBatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job consoleJob(Step consoleStep,JobExecutionListener consoleListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return jobBuilderFactory.get(funcName)
                .listener(consoleListener)
                .flow(consoleStep)
                .end().build();
    }


    @Bean
    public Step consoleStep(ItemReader stringReader,ItemProcessor convertProcessor
            ,ItemWriter consoleWriter, CommonStepListener commonStepListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        return stepBuilderFactory.get(funcName)
                .listener(commonStepListener)
                .<String,String>chunk(3)
                .reader(stringReader)
                .processor(convertProcessor)
                .writer(consoleWriter)
                .build();
    }

    @Bean
    public ItemReader stringReader(){
        return new StringReader();
    }

    @Bean
    public ItemWriter consoleWriter(){
        return new ConsoleWriter();
    }

    @Bean
    public ItemProcessor convertProcessor(){
        return new ConvertProcessor();
    }

    @Bean
    public JobExecutionListener consoleListener(){
        return new ConsoleJobEndListener();
    }

}
