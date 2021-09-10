package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.CompleteStatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/4/9 11:43
 * @Description TODO
 */
@Service("CompleteStatisticService2")
public class CompleteStatisticService2 extends CompleteStatisticService {


    public JSONObject listStationPass(int lineId, Date date, Integer dayNum) {
        if (dayNum == null) dayNum = 7;
        JSONObject out = new JSONObject();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -dayNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List dateList = new ArrayList();
        List stPassList = new ArrayList();
        //初始化
        List stList = dao.listStation(lineId);
        for (int i = 0; i < dayNum; i++) {
            c.add(Calendar.DATE, 1);
            dateList.add(sdf.format(c.getTime()));
            List stpass = new ArrayList();
            for (int j = 0; j < stList.size(); j++) {
                stpass.add(0);
            }
            stPassList.add(stpass);
        }

        List<Map> list1 = dao.list1(lineId, date, dayNum);
        long millis = date.getTime();
        long nowDays = millis  / 1000 / 60 / 60 / 24+719528-dayNum+1;
        for (int i = 0; i < list1.size(); i++) {
            Map map = list1.get(i);
            Integer days = (Integer)map.get("days");
            if(days != null){
                List l = (List)stPassList.get((int)(days-nowDays));
                l.set(stList.indexOf(map.get("st")),map.get("count"));
            }
        }
        Map data = new HashMap();
        data.put("stList", stList);
        data.put("stPassList", stPassList);
        data.put("dateList", dateList);
        out.put("data", data);
        return out;
    }
}
