package com.skeqi.mes.mapper.dzb.report;

import com.skeqi.mes.pojo.CMesLineT;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:10
 * @Description TODO
 */
public interface MonthOutputReportDao {
    List<Map> listLine();
    List<Map> listMonthSnCountByYearAndLineId(@Param("year") Integer year,@Param("lineId") Integer lineId);
    Map okNgOutputByYearAndLineIdAndMonth(@Param("year") Integer year,@Param("month") Integer month,@Param("lineId") Integer lineId);
    Map snCountByYearAndLineId(@Param("year") Integer year,@Param("lineId") Integer lineId);

    //查询某年每月的下线数量
    List<Map> listSnCountByYear(@Param("year") Integer year,@Param("lineId") Integer lineId);
    //查询某年每月的工单数量
    List<Map> listOrderCountByYear(@Param("year") Integer year,@Param("lineId") Integer lineId);
    //根据年份，月份，产线查询ok和ng数
    List<Map> okNgCountByYearAndLineIdAndMonth(@Param("year") Integer year,@Param("month") Integer month,@Param("lineId") Integer lineId);
}
