package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/17 14:01
 * @Description TODO
 */
public interface PassRateStatisticDao {

    List<Map> listLine();
    Map okNgOutputByDateAndLineId(@Param("date")Date date, @Param("lineId") Integer lineId);

    //根据时间段，产线：查询每天的ok和ng数量
    List<Map> okNgRange(@Param("start")String start,@Param("end")String end, @Param("lineId") Integer lineId);
}
