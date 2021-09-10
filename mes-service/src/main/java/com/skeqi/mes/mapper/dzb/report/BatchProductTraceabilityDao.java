package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/23 8:38
 * @Description TODO
 */
public interface BatchProductTraceabilityDao {

    List<Map> getAllProduction();

    List<Map> getPTrackByProductionId(@Param("productionId") Integer productionId, @Param("start") Date start, @Param("end") Date end);
}
