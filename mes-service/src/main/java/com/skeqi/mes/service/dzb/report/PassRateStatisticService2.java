package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.PassRateStatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/17 14:01
 * @Description TODO
 */
@Service("PassRateStatisticService2")
public class PassRateStatisticService2 extends PassRateStatisticService {


    public JSONObject dayOutput(Date date, Integer lineId) {
        int day = 7;
        JSONObject out = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.add(Calendar.DATE, 1);
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.add(Calendar.DATE, -day + 1);
        String startDateStr = sdf.format(start.getTime());
        String endDateStr = sdf.format(end.getTime());
        int startDay = (int) (start.getTimeInMillis() / (1000 * 60 * 60 * 24)) + 719528;
        List<Map> okNgList = dao.okNgRange(startDateStr, endDateStr, lineId);
        //初始化
        List<Map> list = new ArrayList();
        for (int i = 0; i < day; i++) {
            Map map = new HashMap();
            map.put("date", sdf.format(start.getTime()));
            map.put("okSn", 0);
            map.put("ngSn", 0);
            list.add(map);
            start.add(Calendar.DATE, 1);
        }
        //解析数据库
        for(Map okNg : okNgList) {
            int days = (Integer) okNg.get("days");
            int index = days - startDay;
            String status = okNg.get("STATUS").toString();
            Integer snCount =Integer.parseInt(okNg.get("snCount").toString()) ;
            Map map = list.get(index);
            if (status.equals("OK")) {
                map.put("okSn",snCount);
            }else{
                map.put("ngSn",snCount);
            }
        }
        out.put("data", list);
        return out;
    }
}
