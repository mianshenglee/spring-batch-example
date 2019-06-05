package me.mason.springbatch.example.console.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * 任务执行完成监听器
 * @author  mason
 * @since  2019/6/1
 **/
@Slf4j
public class ConsoleJobEndListener extends JobExecutionListenerSupport {
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug("console batch job complete!");
        }
    }
}
