package com.skeqi.mes.service.qh;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/23 16:14
 */
public interface CMesQhReportTService {

    //查询月产量
    List<Map<String,Object>> findMonthSum(String year,Integer lineId);

    //查询日产量
    List<Map<String,Object>> findDaySum(String year,String month,Integer lineId);

    //查询拧紧合格率
    List<Map<String,Object>> tightenPass(String startDt,String endDt);

    //查询合格数
    Integer findNgNums(String startDt,String endDt,String st);
}
