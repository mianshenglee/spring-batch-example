package me.mason.springbatch.dao.target;

import me.mason.springbatch.entity.User;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 目标数据库读写
 *
 * @author mason
 * @since 2019/6/1
 */
@Repository
public interface TargetUserRepository extends BaseMapper<User> {
    /**
     * 查询数据最新时间
     * @return
     */
    @Sql(value="select max(sys_update_time) from test_user")
    Date selectMaxUpdateTime();
}
