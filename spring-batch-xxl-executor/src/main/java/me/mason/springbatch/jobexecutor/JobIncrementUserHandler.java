package me.mason.springbatch.jobexecutor;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import me.mason.springbatch.service.IncrementService;
import me.mason.springbatch.service.batch.JobLauncherService;
import me.mason.springbatch.util.JobUtil;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * incrementUser执行器
 *
 * @author mason
 * @since 2019/6/1
 */
@JobHandler(value="incrementUserJobHandler")
@Component
public class JobIncrementUserHandler extends IJobHandler {
    @Autowired
    private JobLauncherService jobLauncherService;

    @Autowired
    private IncrementService incrementService;

    @Autowired
    private Job incrementJob;
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        return JobUtil.runJob4Executor("incrementUser",incrementService,jobLauncherService,incrementJob);
    }
}
