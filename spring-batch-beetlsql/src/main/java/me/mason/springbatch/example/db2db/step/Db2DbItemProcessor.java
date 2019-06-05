package me.mason.springbatch.example.db2db.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;

/**
 * 处理类
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class Db2DbItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
        String title = user.getTitle();
        if(Objects.nonNull(title)){
            user.setTitle(title.toUpperCase());
        }
        log.info(LogConstants.LOG_TAG + "db item process: " +user.getName());
        return user;
    }
}
