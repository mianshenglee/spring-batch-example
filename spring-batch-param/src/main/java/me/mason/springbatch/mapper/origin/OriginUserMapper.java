package me.mason.springbatch.mapper.origin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.mason.springbatch.entity.origin.User;
import org.springframework.stereotype.Repository;

/**
 * 源User Mapper类
 *
 * @author: mason
 * @date: 2020/11/20
 **/
@Repository
public interface OriginUserMapper extends BaseMapper<User> {
}
