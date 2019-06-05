package me.mason.springbatch.service;

import me.mason.springbatch.common.SyncConstants;
import me.mason.springbatch.entity.CdcTemp;
import me.mason.springbatch.util.JobUtil;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * 抽象服务类
 *
 * @author mason
 * @date 2019/5/27
 */
@Service
public abstract class CommonService {
    @Autowired
    private CdcTempService cdcTempService;
    /**
     * 初始化作业参数
     * @return
     */
    public JobParameters initJobParam(){
        CdcTemp currentCdcTemp = cdcTempService.getCurrentCdcTemp(getCdcTempId());
        //若未初始化，则先查询数据库中对应的最后时间
        if(SyncConstants.STR_STATUS_INIT.equals(currentCdcTemp.getStatus())
                || SyncConstants.STR_STATUS_FAILED.equals(currentCdcTemp.getStatus())){
            Date maxUpdateTime = selectMaxUpdateTime();
            //若没有数据，则按初始时间处理
            if(Objects.nonNull(maxUpdateTime)){
                currentCdcTemp.setLastUpdateTime(maxUpdateTime);
            }
        }
        return JobUtil.makeJobParameters(currentCdcTemp);
    }

    /**
     * 查询当前数据的最新时间
     * @return 日期
     */
    public abstract Date selectMaxUpdateTime();

    /**
     * 返回当前需要处理的cdcTemp的ID
     * @return
     */
    public abstract int getCdcTempId();
}
