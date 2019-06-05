package me.mason.springbatch.example.db2db.mapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * TODO
 *
 * @author mason
 * @since 2019/5/31
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPhone(resultSet.getString("phone"));
        user.setTitle(resultSet.getString("title"));
        user.setEmail(resultSet.getString("email"));
        user.setGender(resultSet.getString("gender"));
        Date dataOfBirth = resultSet.getDate("date_of_birth");
        user.setDateOfBirth(dataOfBirth);
        user.setSysCreateTime(resultSet.getDate("sys_create_time"));
        user.setSysCreateUser(resultSet.getString("sys_create_user"));
        user.setSysUpdateTime(resultSet.getDate("sys_update_time"));
        user.setSysUpdateUser(resultSet.getString("sys_update_user"));
        return user;
    }
}
