package com.skeqi.mes.service.qh.impl;

import com.skeqi.mes.mapper.qh.CMesQhReportTDao;
import com.skeqi.mes.service.qh.CMesQhReportTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/23 16:50
 */
@Service
public class CMesQhReportTServiceImpl implements CMesQhReportTService {

    @Autowired
    CMesQhReportTDao dao;


    @Override
    public List<Map<String, Object>> findMonthSum(String year, Integer lineId) {
        return dao.findMonthSum(year,lineId);
    }

    @Override
    public List<Map<String, Object>> findDaySum(String year, String month, Integer lineId) {
        return dao.findDaySum(year,month,lineId);
    }

    @Override
    public List<Map<String, Object>> tightenPass(String startDt, String endDt) {
        return dao.tightenPass(startDt,endDt);
    }

    @Override
    public Integer findNgNums(String startDt, String endDt, String st) {
        return dao.findNgNums(startDt,endDt,st);
    }
}
