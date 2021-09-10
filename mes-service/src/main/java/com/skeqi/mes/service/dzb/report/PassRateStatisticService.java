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
@Service("PassRateStatisticService")
public class PassRateStatisticService implements PassRateStatisticServiceI {

    @SuppressWarnings("all")
    @Autowired
    public PassRateStatisticDao dao;

    public JSONObject listLine() {
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data", mapList);
        return out;
    }

    public JSONObject dayOutput(Date date, Integer lineId) {
        JSONObject out = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,-7);
        List list = new ArrayList();
        for (int i = 0; i < 7; i++) {
            Map mapList = dao.okNgOutputByDateAndLineId(c.getTime(), lineId);
            c.add(Calendar.DATE,1);
            int startDay = (int) (c.getTimeInMillis() / (1000 * 60 * 60 * 24)) + 719528;
            c.add(Calendar.DATE,1);
            mapList.put("date",sdf.format(c.getTime()));
            list.add(mapList);
        }
        out.put("data", list);
        return out;
    }
}
