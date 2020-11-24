package me.mason.springbatch;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.example.param.config.ParamBatchConfig;
import me.mason.springbatch.service.batch.JobLauncherService;
import me.mason.springbatch.util.JobUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * param测试类
 * @author mason
 * @since 2019/6/1
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MainBootApplication.class, ParamBatchConfig.class})
@Slf4j
public class ParamJobTest {

    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private Job paramJob;

    @Test
    public void testParamJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        //构建job参数
        JobParameters jobParameters = JobUtil.makeJobParameters();
        //运行job
        Map<String, Object> stringObjectMap = jobLauncherService.startJob(paramJob, jobParameters);
        //测试结果
        Assert.assertEquals(ExitStatus.COMPLETED,stringObjectMap.get(SyncConstants.STR_RETURN_EXITSTATUS));
    }
}
