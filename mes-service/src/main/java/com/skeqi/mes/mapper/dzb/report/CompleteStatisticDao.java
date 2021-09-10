package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/10 16:32
 * @Description TODO
 */
public interface CompleteStatisticDao {
    List<Map> listLine();
    List<Integer> listStationPass(@Param("lineId")int lineId, @Param("date")Date date);
    List<String> listStation(@Param("lineId")int lineId);

    List<Map> list1(@Param("lineId")int lineId, @Param("date")Date date,@Param("dayNum")Integer dayNum);

    //根据时间段，查询某条产线的每个工位过站
    List<Map> listStationPass2(@Param("lineId")int lineId, @Param("start")String start,@Param("end")String end);
}
