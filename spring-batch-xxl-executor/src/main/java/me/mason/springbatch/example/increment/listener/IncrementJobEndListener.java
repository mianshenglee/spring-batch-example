package me.mason.springbatch.example.increment.listener;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * 结束监听器
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class IncrementJobEndListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug(LogConstants.LOG_TAG + " batch job complete!");
        }
    }
}
