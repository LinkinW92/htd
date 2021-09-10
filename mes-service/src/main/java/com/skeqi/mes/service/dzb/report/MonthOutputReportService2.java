package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.MonthOutputReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:00
 * @Description TODO
 */
@Service("MonthOutputReportService2")
public class MonthOutputReportService2 extends MonthOutputReportService {


    public JSONObject monthOutput(Integer year,Integer lineId){
        JSONObject out = new JSONObject();
        //初始化数据
        List<Map> data = new ArrayList();
        for(int i = 1;i<=12;i++){
            Map m = new HashMap();
            m.put("month_num",i);
            m.put("snCount",0);
            m.put("orderSum",0);
            data.add(m);
        }
        //查询数据库
        List<Map> listSnCount = dao.listSnCountByYear(year, lineId);
        List<Map> listOrder = dao.listOrderCountByYear(year, lineId);
        for (Map snCount : listSnCount) {
            int month = (Integer) snCount.get("months");
            data.get(month-1).put("snCount",snCount.get("snCount"));
        }
        for (Map orderCount : listOrder) {
            int month = (Integer) orderCount.get("months");
            data.get(month-1).put("orderSum",orderCount.get("orderCount"));
        }
        //解析数据
        out.put("data",data);
        return out;
    }

    public JSONObject okRate(Integer year,Integer month,Integer lineId){
        JSONObject out = new JSONObject();
        List<Map> okngCountList = dao.okNgCountByYearAndLineIdAndMonth(year,month, lineId);
        Map data = new HashMap();
        data.put("okSn",0);
        data.put("ngSn",0);
        for(Map m : okngCountList){
            data.put(m.get("status").equals("OK")?"okSn":"ngSn",m.get("countSn"));
        }
        out.put("data",data);
        return out;
    }

}
