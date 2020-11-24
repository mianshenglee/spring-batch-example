package me.mason.springbatch.example.param.listener;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import java.util.Date;

/**
 * 结束监听器
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class ParamJobEndListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        Integer readNum = (Integer)executionContext.get(SyncConstants.PASS_PARAM_READ_NUM);
        Integer writeNum = (Integer)executionContext.get(SyncConstants.PASS_PARAM_WRITE_NUM);
        Date datetime = (Date)executionContext.get(SyncConstants.PASS_PARAM_DATETIME);
        log.info("readNum:{},writeNum:{},datetime:{}",readNum,writeNum,datetime);
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug(LogConstants.LOG_TAG + " batch job complete!");
        }
    }
}
