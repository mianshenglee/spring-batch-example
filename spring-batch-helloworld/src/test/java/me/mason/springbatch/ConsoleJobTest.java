package me.mason.springbatch;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.example.console.config.ConsoleBatchConfig;
import me.mason.springbatch.listener.CommonStepListener;
import me.mason.springbatch.service.ConsoleService;
import me.mason.springbatch.service.batch.JobLauncherService;
import me.mason.springbatch.util.JobUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * console测试类
 * @author mason
 * @since 2019/6/1
 **/

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ConsoleBatchConfig.class
        ,JobLauncherService.class
        ,ConsoleService.class
        ,CommonStepListener.class})
//@SpringBootTest(classes = {MainBootApplication.class,ConsoleBatchConfig.class})
@Slf4j
public class ConsoleJobTest {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private Job consoleJob;

    @Test
    public void testConsoleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = JobUtil.makeJobParameters();
        Map<String, Object> stringObjectMap = jobLauncherService.startJob(consoleJob, jobParameters);
        Assert.assertEquals(ExitStatus.COMPLETED,stringObjectMap.get(SyncConstants.STR_RETURN_EXITSTATUS));
    }
}
