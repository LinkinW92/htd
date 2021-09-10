package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:00
 * @Description TODO
 */
@Service("DayOutputReportService3")
public class DayOutputReportService3 extends DayOutputReportService2 {

    public JSONObject dayOutput(Integer year, Integer month, Integer lineId) {
        JSONObject out = new JSONObject();
        Calendar start = Calendar.getInstance();
        start.set(year, month - 1, 1);

        Calendar end = Calendar.getInstance();
        end.set(year, month, 1);
        //初始化数据
        long startMillis = start.getTimeInMillis();
        int startDay = (int)((startMillis+1000*60*60*8)  / 1000 / 60 / 60 / 24)+719528;
        long endMillis = end.getTimeInMillis();
        int endDay = (int)((endMillis+1000*60*60*8)  / 1000 / 60 / 60 / 24)+719528;
        List<Map> data = new ArrayList<Map>();
        int i = 1, count =endDay - startDay;
        for (; i <= count; i++) {
            Map m = new HashMap();
            m.put("day_num", i);
            m.put("snCount", 0);
            m.put("orderSum", 0);
            data.add(m);
        }
        //获取数据库
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String startDateStr = sdf.format(start.getTime());
        String endDateStr = sdf.format(end.getTime());
        List<Map> listSnCountByMonth = dao.listSnCountByMonth2(startDateStr, endDateStr, lineId);
        List<Map> listOrderCountByMonth = dao.listOrderCountByMonth(startDateStr, endDateStr, lineId);
        //解析数据库数据
        for (Map snCount : listSnCountByMonth) {
            int day = (Integer) snCount.get("days");
            int index = day-startDay;
            data.get(index).put("snCount",snCount.get("snCount"));
        }
        for (Map orderCount : listOrderCountByMonth) {
            int day = (Integer) orderCount.get("days");
            int index = day-startDay;
            data.get(index).put("orderSum",orderCount.get("orderCount"));
        }
        out.put("data",data);
        return out;
    }

    public JSONObject monthOutput(Integer year,Integer month,Integer lineId){
        JSONObject out = new JSONObject();
        Calendar start = Calendar.getInstance();
        start.set(year,month-1,1);
        Calendar end = Calendar.getInstance();
        end.set(year,month,1);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String startDateStr = sdf.format(start.getTime());
        String endDateStr = sdf.format(end.getTime());
        Map map = dao.snCountByYearAndMonthAndLineId2(startDateStr,endDateStr, lineId);
        out.put("data",map);
        return out;
    }
}
