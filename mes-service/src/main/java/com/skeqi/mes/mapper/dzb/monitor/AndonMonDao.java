package com.skeqi.mes.mapper.dzb.monitor;

import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/4/22 16:00
 * @Description TODO
 */
public interface AndonMonDao {

    //andon类别统计
    Map listAndonTypeTime();

    //运行状况
    Map listOperationCondition();

    //质检
    Map listQualityInspection();

    //设备异常监控
    Map listEquipmentError();
}
