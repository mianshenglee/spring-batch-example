package me.mason.springbatch.service;

import me.mason.springbatch.dao.target.TargetUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * increment服务
 *
 * @author mason
 * @since 2019/5/31
 */
@Service
public class IncrementService extends CommonService {
    @Autowired
    private TargetUserRepository targetUserRepository;

    @Override
    public Date selectMaxUpdateTime() {
        return targetUserRepository.selectMaxUpdateTime();
    }

    @Override
    public int getCdcTempId() {
        return 1;
    }
}
