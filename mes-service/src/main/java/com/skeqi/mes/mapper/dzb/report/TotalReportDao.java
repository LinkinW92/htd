package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/9 16:17
 * @Description TODO
 */
public interface TotalReportDao {

    List<Map> data(@Param("groupList") String[] groupList, @Param("whereList") Map whereList, @Param("start") String start, @Param("end") String end);


}
