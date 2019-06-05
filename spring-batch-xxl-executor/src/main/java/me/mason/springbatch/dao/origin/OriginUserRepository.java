package me.mason.springbatch.dao.origin;

import me.mason.springbatch.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 源数据库读取
 *
 * @author mason
 * @since 2019/5/31
 */
@Repository
public interface OriginUserRepository extends BaseMapper<User> {
    List<User> getOriginUser(Map<String,Object> params);
    List<User> getOriginIncreUser(Map<String,Object> params);
}
