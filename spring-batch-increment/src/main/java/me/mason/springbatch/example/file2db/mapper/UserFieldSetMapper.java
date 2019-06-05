package me.mason.springbatch.example.file2db.mapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.User;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * 字段转换到实体
 *
 * @author mason
 * @since 2019/5/31
 */
public class UserFieldSetMapper implements FieldSetMapper<User> {
    @Override
    public User mapFieldSet(FieldSet fieldSet) throws BindException {
        String patternYmd = "yyyy-MM-dd";
        String patternYmdHms = "yyyy-MM-dd HH:mm:ss";
        User user = new User();
        user.setId(fieldSet.readLong("id"));
        user.setName(fieldSet.readString("name"));
        user.setPhone(fieldSet.readString("phone"));
        user.setTitle(fieldSet.readString("title"));
        user.setEmail(fieldSet.readString("email"));
        user.setGender(fieldSet.readString("gender"));
        //此字段有可能为null
        String dataOfBirthStr = fieldSet.readString("date_of_birth");
        if(SyncConstants.STR_CSV_NULL.equals(dataOfBirthStr)){
            user.setDateOfBirth(null);
        }else{
            DateTime dateTime = DateUtil.parse(dataOfBirthStr, patternYmd);
            user.setDateOfBirth(dateTime.toJdkDate());
        }
        user.setSysCreateTime(fieldSet.readDate("sys_create_time",patternYmdHms));
        user.setSysCreateUser(fieldSet.readString("sys_create_user"));
        user.setSysUpdateTime(fieldSet.readDate("sys_update_time",patternYmdHms));
        user.setSysUpdateUser(fieldSet.readString("sys_update_user"));
        return user;
    }
}
