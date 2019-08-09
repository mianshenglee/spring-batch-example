package me.mason.springbatch.example.mysql2mongo.step;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.entity.User;
import me.mason.springbatch.entity.mongo.MongoUser;
import org.springframework.batch.item.ItemProcessor;

/**
 * User处理类
 *
 * @author mason
 * @since 2019/5/31
 */
@Slf4j
public class UserItemProcessor implements ItemProcessor<User, MongoUser> {
    @Override
    public MongoUser process(User user) throws Exception {
        MongoUser mongoUser = MongoUser.builder().build();
        BeanUtil.copyProperties(user, mongoUser);
        log.info(LogConstants.LOG_TAG + "mongo item process: " + user.getName());
        return mongoUser;
    }
}
