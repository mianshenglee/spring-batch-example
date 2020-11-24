package me.mason.springbatch.service.target;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.mason.springbatch.entity.target.TargetUser;
import me.mason.springbatch.mapper.target.TargetUserMapper;
import org.springframework.stereotype.Service;

/**
 * 目标User服务
 *
 * @author: mason
 * @date: 2020/11/20
 **/
@Service
public class TargetUserService extends ServiceImpl<TargetUserMapper, TargetUser> {
}
