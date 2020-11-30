package me.mason.springbatch.example.param.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.target.TargetUser;
import me.mason.springbatch.service.target.TargetUserService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User写入类
 *
 * @author mason
 * @since 2019/5/31
 */
@Slf4j
public class UserItemWriter implements ItemWriter<TargetUser>, StepExecutionListener {
    @Autowired
    private TargetUserService targetUserService;

    private StepExecution stepExecution;

    @Override
    public void write(List<? extends TargetUser> items) {
        List<TargetUser> users = new ArrayList<>();
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        Object currentWriteNum = executionContext.get(SyncConstants.PASS_PARAM_WRITE_NUM);
        if (Objects.nonNull(currentWriteNum)) {
            log.info("currentWriteNum:{}", currentWriteNum);
            executionContext.put(SyncConstants.PASS_PARAM_WRITE_NUM, items.size()+(Integer)currentWriteNum);
        } else {
            executionContext.put(SyncConstants.PASS_PARAM_WRITE_NUM, items.size());
        }

        for (TargetUser item : items) {
            users.add(item);
        }
//        targetUserService.saveBatch(users);
        targetUserService.saveOrUpdateBatch(users);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
