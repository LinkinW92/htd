package com.skeqi.mes.mapper.qh;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/23 16:13
 */
public interface CMesQhReportTDao {

    //查询月产量
    List<Map<String,Object>> findMonthSum(@Param("year")String year, @Param("lineId")Integer lineId);

    //查询日产量
    List<Map<String,Object>> findDaySum(@Param("year")String year,@Param("month")String month,@Param("lineId") Integer lineId);

    //查询拧紧总数
    List<Map<String,Object>> tightenPass(@Param("startDt")String startDt,@Param("endDt")String endDt);

    //查询合格数
    Integer findNgNums(@Param("startDt")String startDt,@Param("endDt")String endDt,@Param("st")String st);
}
