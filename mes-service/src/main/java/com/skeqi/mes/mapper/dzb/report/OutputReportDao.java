package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/6 10:04
 * @Description TODO
 */
public interface OutputReportDao {
    //所有车间
    List<Map> listPlant();

    //所有产线
    List<Map> listLine(@Param("plantCode") String[] plantCode);

    //所有产线
    List<Map> listSt(@Param("lineId") Integer[] lineId);

    List<Map> data(@Param("groupList") String[] groupList, @Param("whereList") Map whereList, @Param("start") String start, @Param("end") String end);

}
