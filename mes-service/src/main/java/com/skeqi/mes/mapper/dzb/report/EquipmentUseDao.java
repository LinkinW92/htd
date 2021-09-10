package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/10 9:26
 * @Description TODO
 */
public interface EquipmentUseDao {
    List<Map> listLine();

    int countSnByDayAndLineId(@Param("lineId") int lineId, @Param("date") Date date);
    List<Map> countSnGroupSt(@Param("lineId") int lineId, @Param("date") Date date);
    //根据时间段，产线id查询每天的下线数量
    List<Map> countSnByDateRangeAndLineId(@Param("start")String start,@Param("end")String end,@Param("lineId")Integer lineId);

    List<Map> countSnGroupSt2(@Param("lineId") int lineId, @Param("start")String start,@Param("end")String end);
}
