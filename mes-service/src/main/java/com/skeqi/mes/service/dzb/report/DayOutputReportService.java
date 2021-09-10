package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.DayOutputReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:00
 * @Description TODO
 */
@Service("DayOutputReportService")
public class DayOutputReportService implements DayOutputReportServiceI {

    @SuppressWarnings("all")
    @Autowired
    public DayOutputReportDao dao;

    public JSONObject listLine(){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data",mapList);
        return out;
    }

    public JSONObject dayOutput(Integer year,Integer month,Integer lineId){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listDaySnCountByYearAndMonthAndLineId(year,month, lineId);
        out.put("data",mapList);
        return out;
    }

    public JSONObject okRate(Integer year,Integer month,Integer day,Integer lineId){
        JSONObject out = new JSONObject();
        Map map = dao.okNgOutputByYearAndMonthAndDayAndLineId(year,month,day, lineId);
        out.put("data",map);
        return out;
    }

    public JSONObject monthOutput(Integer year,Integer month,Integer lineId){
        JSONObject out = new JSONObject();
        Map map = dao.snCountByYearAndMonthAndLineId(year,month, lineId);
        out.put("data",map);
        return out;
    }
}
