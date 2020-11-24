package me.mason.springbatch.example.param.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.origin.User;
import me.mason.springbatch.service.origin.OriginUserService;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 读取User
 *
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class UserItemReader implements ItemReader<User> {
    protected List<User> items;

    protected Map<String, Object> params;
    @Autowired
    private OriginUserService originUserService;

    private StepExecution stepExecution;

    public StepExecution getStepExecution() {
        return stepExecution;
    }

    public void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public User read() {
        if (Objects.isNull(items)) {
            items = originUserService.getUsers();
            ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
            executionContext.put(SyncConstants.PASS_PARAM_READ_NUM, items.size());
            executionContext.put(SyncConstants.PASS_PARAM_DATETIME,params.get(SyncConstants.PASS_PARAM_DATETIME));
            if (items.size() > 0) {
                return items.remove(0);
            }
        } else {
            if (!items.isEmpty()) {
                return items.remove(0);
            }
        }
        return null;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        this.stepExecution = stepExecution;
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        return stepExecution.getExitStatus();
//    }
}
