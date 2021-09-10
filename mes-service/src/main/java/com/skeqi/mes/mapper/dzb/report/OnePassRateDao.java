package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/18 9:29
 * @Description TODO
 */
public interface OnePassRateDao {
    List<Map> listLine();
    Map getNormalByDay(@Param("date") Date date, @Param("lineId") Integer lineId);

}
