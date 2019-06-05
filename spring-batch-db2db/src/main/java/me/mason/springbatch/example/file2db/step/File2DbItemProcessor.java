package me.mason.springbatch.example.file2db.step;

import lombok.extern.slf4j.Slf4j;
import me.mason.springbatch.common.LogConstants;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * 处理类
 * @author mason
 * @since 2019/6/1
 **/
@Slf4j
public class File2DbItemProcessor implements ItemProcessor<User,User> {

    @Override
    public User process(User user) throws Exception {
        user.setPhone(checkStr(user.getPhone()));
        user.setTitle(checkStr(user.getTitle()));
        user.setEmail(checkStr(user.getEmail()));
        user.setGender(checkStr(user.getGender()));

        log.info(LogConstants.LOG_TAG + "item process: " +user.getName());
        return user;
    }

    public String checkStr(String dataToCheck){
        if(SyncConstants.STR_CSV_NULL.equals(dataToCheck)){
            return null;
        }
        return dataToCheck;
    }

}
