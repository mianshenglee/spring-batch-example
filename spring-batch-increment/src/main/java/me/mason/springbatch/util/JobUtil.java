package me.mason.springbatch.util;

import cn.hutool.core.date.DateUtil;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.CdcTemp;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

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
}
