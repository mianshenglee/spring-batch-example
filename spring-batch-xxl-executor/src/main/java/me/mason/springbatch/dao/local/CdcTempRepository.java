package me.mason.springbatch.dao.local;

import me.mason.springbatch.entity.CdcTemp;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description cdc读写类
 * @Author Mason
 * @Since 2019/4/26
 **/
@Repository
public interface CdcTempRepository extends BaseMapper<CdcTemp> {
}
