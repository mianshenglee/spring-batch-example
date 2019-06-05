package me.mason.springbatch.example.increment.listener;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.dao.target.TargetUserRepository;
import me.mason.springbatch.service.CdcTempService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 结束监听器
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class IncrementJobEndListener extends JobExecutionListenerSupport {

    @Autowired
    private CdcTempService cdcTempService;

    @Autowired
    private TargetUserRepository targetUserRepository;

    @Override
    public void afterJob(JobExecution jobExecution) {
        BatchStatus status = jobExecution.getStatus();
        Date latestDate  = targetUserRepository.selectMaxUpdateTime();
        cdcTempService.updateCdcTempAfterJob(SyncConstants.CDC_TEMP_ID_USER,status,latestDate);
    }
}
