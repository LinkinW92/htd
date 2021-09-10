package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.MonthOutputReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:00
 * @Description TODO
 */
@Service("MonthOutputReportService")
public class MonthOutputReportService implements MonthOutputReportServiceI {

    @SuppressWarnings("all")
    @Autowired
    public MonthOutputReportDao dao;

    public JSONObject listLine(){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data",mapList);
        return out;
    }

    public JSONObject monthOutput(Integer year,Integer lineId){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listMonthSnCountByYearAndLineId(year, lineId);
        out.put("data",mapList);
        return out;
    }

    public JSONObject okRate(Integer year,Integer month,Integer lineId){
        JSONObject out = new JSONObject();
        Map map = dao.okNgOutputByYearAndLineIdAndMonth(year,month, lineId);
        out.put("data",map);
        return out;
    }

    public JSONObject yearOutput(Integer year,Integer lineId){
        JSONObject out = new JSONObject();
        Map map = dao.snCountByYearAndLineId(year, lineId);
        out.put("data",map);
        return out;
    }
}
