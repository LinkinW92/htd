package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/8 16:50
 * @Description TODO
 */
public interface WorkOrderMonitoringDao {



    List<Map> data(@Param("whereList") Map whereList, @Param("start") String start, @Param("end") String end);


}
