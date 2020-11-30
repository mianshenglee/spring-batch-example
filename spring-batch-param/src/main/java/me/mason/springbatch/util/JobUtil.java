package me.mason.springbatch.util;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 * 作业工具类
 * @author  mason
 * @since  2019/6/1
 **/
public class JobUtil {

    /**
     * 以当前时间作为参数，构建JobParameters
     * @return
     */
    public static JobParameters makeJobParameters() {
        return makeJobParameters(System.currentTimeMillis());
    }

    /**
     * 以当前时间作为参数，构建JobParameters
     * @return
     */
    public static JobParameters makeJobParameters(long timMillis) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",timMillis)
                .toJobParameters();
        return jobParameters;
    }
}
