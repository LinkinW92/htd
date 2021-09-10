package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.EquipmentUseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/10 9:23
 * @Description TODO
 */
@Service("EquipmentUseService2")
public class EquipmentUseService2 extends EquipmentUseService {


    @SuppressWarnings("all")
    @Autowired
    private EquipmentUseDao dao;

    public JSONObject queryEquipmentUse(int lineId, Date date, Integer dayNum) {
        JSONObject out = new JSONObject();
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.add(Calendar.DATE, -dayNum+1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //查询数据库
        String startDate = sdf.format(start.getTime());
        String endDate = sdf.format(end.getTime());
        List<Map> countSnList = dao.countSnByDateRangeAndLineId(startDate, endDate, lineId);
        long millis=start.getTimeInMillis();
        int startDay =  (int)((millis+1000*60*60*8)  / 1000 / 60 / 60 / 24)+719528;
                //初始化数据
        List<Map> equipmentUse = new ArrayList();
        for (int i = 0; i < dayNum; i++) {
            Map map = new HashMap();
            String dateStr = sdf.format(start.getTime());
            map.put("date", dateStr);
            map.put("value", 0);
            equipmentUse.add(map);
            start.add(Calendar.DATE, 1);
        }
        //解析数据
        for(Map countSn : countSnList){
            int day = (Integer) countSn.get("days");
            int index = day-startDay;
            equipmentUse.get(index).put("value",countSn.get("snCount"));
        }
        out.put("data", equipmentUse);
        return out;
    }

    public JSONObject queryEquipmentUseSt(int lineId, Date date) {
        JSONObject out = new JSONObject();
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.add(Calendar.DATE,1);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String startDateStr = sdf.format(date);
        String endDateStr = sdf.format(end.getTime());
        List<Map> equipmentUseSt = dao.countSnGroupSt2(lineId, startDateStr,endDateStr);
        out.put("data", equipmentUseSt);
        return out;
    }
}
