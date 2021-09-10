package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/11 19:59
 * @Description TODO
 */
public interface NgReportDao {

    List<Map> data(@Param("col") String col, @Param("whereList") Map whereList, @Param("start") String start, @Param("end") String end);

}
