package me.mason.springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * 通用作业步监听
 *
 * @author mason
 * @date 2019/5/23
 */
@Slf4j
@Component
public class CommonStepListener extends StepExecutionListenerSupport {
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        int readCount = stepExecution.getReadCount();
        int filterCount = stepExecution.getFilterCount();
        int commitCount = stepExecution.getCommitCount();
        int writeCount = stepExecution.getWriteCount();
        log.info(LogConstants.LOG_TAG + " read:" + readCount + ", filter:"+filterCount + ",commit:"
                + commitCount + ",write:"+writeCount);

        return stepExecution.getExitStatus();
    }
}
