package me.mason.springbatch.service.origin;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.mason.springbatch.entity.origin.User;
import me.mason.springbatch.mapper.origin.OriginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 源User服务
 *
 * @author: mason
 * @date: 2020/11/20
 **/
@Service
public class OriginUserService {
    @Autowired
    private OriginUserMapper originUserMapper;

    public List<User> getUsers(){
        return originUserMapper.selectList(Wrappers.<User>lambdaQuery().isNotNull(User::getName));
    }
}
