package com.skeqi.mes.mapper.dzb.report;

import com.skeqi.mes.pojo.CMesShiftsTeamT;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/19 8:36
 * @Description TODO
 */
public interface EquipmentOEEDao {
    List<CMesShiftsTeamT> getShiftsTeamList(@Param("lineId")Integer lineId);
    Integer getTime(@Param("lineId")Integer lineId,@Param("date") Date date);
    Integer getSnCount(@Param("lineId")Integer lineId,@Param("date") Date date,@Param("status")String status);

    List<Map> listLine();
}
