package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/24 15:30
 * @Description TODO
 */
public interface QualityReportDao {

    Map listQualityInfo(@Param("date")Date date);
}
