package me.mason.springbatch.util;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.CdcTemp;
import me.mason.springbatch.service.CommonService;
import me.mason.springbatch.service.batch.JobLauncherService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.util.Map;

/**
 * 作业工具类
 * @author  mason
 * @since  2019/6/1
 **/
public class JobUtil {

    /**
     * 构建JobParameters
     * @return
     */
    public static JobParameters makeJobParameters() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        return jobParameters;
    }

    /**
     * 以当前时间作为参数，构建JobParameters
     * @return
     */
    public static JobParameters makeJobParameters(CdcTemp currentCdcTemp) {
        String lastUpdateTime = DateUtil.formatDateTime(currentCdcTemp.getLastUpdateTime());
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString(SyncConstants.STR_LAST_UPDATE_TIME,lastUpdateTime)
                .toJobParameters();

        return jobParameters;
    }

    /**
     * 执行xxl的任务
     * @param jobName
     * @param commonService
     * @param jobLauncherService
     * @param job
     * @return
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    public static ReturnT<String> runJob4Executor(String jobName, CommonService commonService
            , JobLauncherService jobLauncherService, Job job) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        XxlJobLogger.log("start sync "+jobName +" data");
        JobParameters jobParameters = commonService.initJobParam();
        Map<String, Object> resultMap = jobLauncherService.startJob(job, jobParameters);
        String resultStr = (String)resultMap.get(SyncConstants.STR_RETURN_RESULT);
        XxlJobLogger.log("end sync "+jobName +" data, result: "+System.lineSeparator()+resultStr);

        ExitStatus exitStatus = (ExitStatus)resultMap.get(SyncConstants.STR_RETURN_EXITSTATUS);
        String exitCode = exitStatus.getExitCode();
        //若成功，返回SUCCESS
        if(ExitStatus.COMPLETED.getExitCode().equals(exitCode)){
            return IJobHandler.SUCCESS;
        }else{
            return IJobHandler.FAIL;
        }
    }
}
