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
@Service("CompleteStatisticService3")
public class CompleteStatisticService3 extends CompleteStatisticService {

    public JSONObject listStationPass(int lineId, Date date, Integer dayNum) {
        if (dayNum == null) dayNum = 7;
        JSONObject out = new JSONObject();
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.add(Calendar.DATE, -dayNum+1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(start.getTime());
        String endDateStr = sdf.format(end.getTime());
        long millis = start.getTimeInMillis();
        int nowDays =(int)((millis+1000*60*60*8)  / 1000 / 60 / 60 / 24)+719528;
        List dateList = new ArrayList();
        List stPassList = new ArrayList();
        //初始化
        List stList = dao.listStation(lineId);
        for (int i = 0; i < dayNum; i++) {
            dateList.add(sdf.format(start.getTime()));
            List stpass = new ArrayList();
            for (int j = 0; j < stList.size(); j++) {
                stpass.add(0);
            }
            stPassList.add(stpass);
            start.add(Calendar.DATE, 1);
        }

        List<Map> list1 = dao.listStationPass2(lineId, startDateStr,endDateStr);
        //解析数据
        for (int i = 0; i < list1.size(); i++) {
            Map map = list1.get(i);
            Integer days = (Integer)map.get("days");
            if(days != null){
                int dataIndex = days-nowDays;
                List l = (List)stPassList.get(dataIndex);
                int index =stList.indexOf(map.get("ST"));
                if(index != -1)
                    l.set(index,map.get("count"));
            }
        }
        Map data = new HashMap();
        data.put("stList", stList   );
        data.put("stPassList", stPassList);
        data.put("dateList", dateList);
        out.put("data", data);
        return out;
    }
}
