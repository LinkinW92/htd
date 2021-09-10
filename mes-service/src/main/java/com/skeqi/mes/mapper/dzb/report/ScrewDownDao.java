package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/10 11:46
 * @Description TODO
 */
public interface ScrewDownDao {

   List<Map> listLine();
   List<Map> countBoltGroupSt(@Param("lineId") int lineId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

   //根据产线，时间段：查询每个工位的产量
   List<Map> countBoltGroupSt2(@Param("lineId") int lineId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
