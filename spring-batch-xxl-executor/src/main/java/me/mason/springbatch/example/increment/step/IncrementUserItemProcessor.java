package me.mason.springbatch.example.increment.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.entity.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;

/**
 * User处理类
 *
 * @author mason
 * @since 2019/5/31
 */
@Slf4j
public class IncrementUserItemProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) throws Exception {
        String title = user.getTitle();
        if(Objects.nonNull(title)){
            user.setTitle(title.toUpperCase());
        }
        log.info(LogConstants.LOG_TAG + "increment item process: " +user.getName());
        return user;
    }
}
