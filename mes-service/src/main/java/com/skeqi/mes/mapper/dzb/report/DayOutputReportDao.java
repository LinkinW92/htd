package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:10
 * @Description TODO
 */
public interface DayOutputReportDao {
    List<Map> listLine();
    List<Map> listDaySnCountByYearAndMonthAndLineId(@Param("year") Integer year,@Param("month") Integer month,@Param("lineId") Integer lineId);

    Map okNgOutputByYearAndMonthAndDayAndLineId(@Param("year") Integer year,@Param("month") Integer month,@Param("day") Integer day,@Param("lineId") Integer lineId);
    Map snCountByYearAndMonthAndLineId(@Param("year") Integer year,@Param("month") Integer month,@Param("lineId") Integer lineId);

    //查询某月每天的下线数量
    List<Map> listSnCountByMonth(@Param("start")String start,@Param("end")String end,@Param("lineId")Integer lineId);
    //查询某月每天的工单数量
    List<Map> listOrderCountByMonth(@Param("start")String start,@Param("end")String end,@Param("lineId")Integer lineId);
    //根据时间段查询ng和ok数量
    List<Map> okNgCountByDateRangeAndLineId(@Param("start") String start,@Param("end") String end,@Param("lineId") Integer lineId);


    //查询某月每天的下线数量
    List<Map> listSnCountByMonth2(@Param("start")String start,@Param("end")String end,@Param("lineId")Integer lineId);
    //根据时间段查询产量和计划数量
    Map snCountByYearAndMonthAndLineId2(@Param("start")String start,@Param("end")String end,@Param("lineId") Integer lineId);

}
