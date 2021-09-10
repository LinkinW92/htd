package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/5 15:00
 * @Description TODO
 */
@Service("DayOutputReportService2")
public class DayOutputReportService2 extends DayOutputReportService {

    public JSONObject dayOutput(Integer year, Integer month, Integer lineId) {
        JSONObject out = new JSONObject();
        Calendar start = Calendar.getInstance();
        start.set(year, month - 1, 1);

        Calendar end = Calendar.getInstance();
        end.set(year, month, 1);
        //初始化数据
        long startMillis = start.getTimeInMillis();
        long startDay = startMillis / 1000 / 60 / 60 / 24 + 719528;
        long endMillis = end.getTimeInMillis();
        long endDay = endMillis / 1000 / 60 / 60 / 24 + 719528;
        List<Map> data = new ArrayList<Map>();
        int i = 1, count = (int) (endDay - startDay);
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
        List<Map> listSnCountByMonth = dao.listSnCountByMonth(startDateStr, endDateStr, lineId);
        List<Map> listOrderCountByMonth = dao.listOrderCountByMonth(startDateStr, endDateStr, lineId);
        //解析数据库数据
        for (Map snCount : listSnCountByMonth) {
            int day = (Integer) snCount.get("days");
            int index = day-(int)startDay;
            data.get(index).put("snCount",snCount.get("snCount"));
        }
        for (Map orderCount : listOrderCountByMonth) {
            int day = (Integer) orderCount.get("days");
            int index = day-(int)startDay;
            data.get(index).put("orderSum",orderCount.get("orderCount"));
        }
        out.put("data",data);
        return out;
    }

    public JSONObject okRate(Integer year,Integer month,Integer day,Integer lineId){
        JSONObject out = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar start = Calendar.getInstance();
        start.set(year,month-1,1);
        Calendar end = Calendar.getInstance();
        end.set(year,month-1,1);
        if(day == null) {
            end.add(Calendar.MONTH,1);
        }else{
            start.set(Calendar.DAY_OF_MONTH, day);
            end.set(Calendar.DAY_OF_MONTH, day);
            end.add(Calendar.DATE,1);
        }
        String startDate = sdf.format(start.getTime());
        String endDate = sdf.format(end.getTime());
        List<Map> okngCountList = dao.okNgCountByDateRangeAndLineId(startDate,endDate,lineId);
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
