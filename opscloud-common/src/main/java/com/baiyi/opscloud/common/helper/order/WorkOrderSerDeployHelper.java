package com.baiyi.opscloud.common.helper.order;

import com.baiyi.opscloud.common.util.NewTimeUtil;
import com.baiyi.opscloud.common.util.StringFormatter;
import com.baiyi.opscloud.domain.model.WorkOrderToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author baiyi
 * @Date 2023/6/14 19:04
 * @Version 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WorkOrderSerDeployHelper extends BaseWorkOrderToken<WorkOrderToken.SerDeployToken> {

    /**
     * 有效期 2Hour
     */
    private static final long CACHE_MAX_TIME = NewTimeUtil.HOUR_TIME / 1000 * 2;

    private static final String KEY = "OC4:V0:WORKORDER:SER:DEPLOY:APPID:{}";

    @Override
    public WorkOrderToken.SerDeployToken getToken(Integer applicationId) {
        return (WorkOrderToken.SerDeployToken) redisUtil.get(getKey(applicationId));
    }

    @Override
    protected String getKey(Integer id) {
        return StringFormatter.format(KEY, id);
    }

    @Override
    protected long getPeriodOfValidity(){
        return CACHE_MAX_TIME;
    }

}
