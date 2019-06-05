package me.mason.springbatch.dao.target;

import me.mason.springbatch.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 目标数据库读写
 *
 * @author mason
 * @since 2019/5/31
 */
@Repository
public interface TargetUserRepository extends BaseMapper<User> {
}
