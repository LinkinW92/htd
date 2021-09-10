package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.OnePassRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/18 9:27
 * @Description TODO
 */
@Service
public class OnePassRateService implements OnePassRateServiceI{

    @SuppressWarnings("all")
    @Autowired
    private OnePassRateDao dao;

    public JSONObject listLine() {
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data", mapList);
        return out;
    }

    public JSONObject onePassRate(Date date, int lineId) {
        JSONObject out = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,-7);
        List list = new ArrayList();
        for (int i = 0; i < 7; i++) {
            Map mapList = dao.getNormalByDay(c.getTime(), lineId);
            c.add(Calendar.DATE,1);
            mapList.put("date",sdf.format(c.getTime()));
            list.add(mapList);
        }
        out.put("data", list);
        return out;
    }
}
