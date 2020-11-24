package me.mason.springbatch.example.param.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.entity.origin.User;
import me.mason.springbatch.entity.target.TargetUser;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * 处理类
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class ParamItemProcessor implements ItemProcessor<User,TargetUser> {

    @Override
    public TargetUser process(User user) {
        String title = user.getTitle();
        if(Objects.nonNull(title)){
            user.setTitle(title.toUpperCase());
        }
        log.info(LogConstants.LOG_TAG + "db item process: " +user.getName());
        TargetUser targetUser = new TargetUser();
        BeanUtils.copyProperties(user,targetUser);
        return targetUser;
    }
}
