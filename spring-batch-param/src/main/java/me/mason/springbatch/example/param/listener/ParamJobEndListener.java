package me.mason.springbatch.example.param.listener;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.origin.User;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import java.util.Date;
import java.util.List;

/**
 * 结束监听器
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class ParamJobEndListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.debug(LogConstants.LOG_TAG + jobExecution.toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        Integer readNum = (Integer)executionContext.get(SyncConstants.PASS_PARAM_READ_NUM);
        Integer writeNum = (Integer)executionContext.get(SyncConstants.PASS_PARAM_WRITE_NUM);
        Date datetime = (Date)executionContext.get(SyncConstants.PASS_PARAM_DATETIME);
        log.info(LogConstants.LOG_TAG + "readNum:{},writeNum:{},datetime:{}",readNum,writeNum,datetime);

        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug(LogConstants.LOG_TAG + " batch job complete!");
        }
    }
}
