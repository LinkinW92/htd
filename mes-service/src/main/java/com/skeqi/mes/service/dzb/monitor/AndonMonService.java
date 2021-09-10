package com.skeqi.mes.service.dzb.monitor;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.monitor.AndonMonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/4/22 16:00
 * @Description TODO
 */
@Service("AndonMonService")
public class AndonMonService implements AndonMonServiceI {

    @SuppressWarnings("all")
    @Autowired
    public AndonMonDao dao;

    public JSONObject data1() {
        JSONObject jo = new JSONObject();
        Map map = dao.listEquipmentError();
        Map data = new HashMap();
        data.put("legendDatas",new String[]{"次数"});
        data.put("xData", new String[]{"设备保养", "设备点检"});
        List yData = new ArrayList();
        long[] y1 = {0, 0};
        y1[0] = (long) map.get("c1");
        y1[1] = (long) map.get("c2");
        yData.add(y1);
        data.put("yData", yData);
        jo.put("data", data);
        return jo;
    }

    public JSONObject data2() {
        JSONObject jo = new JSONObject();
        Map map = dao.listQualityInspection();
        Map data = new HashMap();
        data.put("legendDatas",new String[]{"次数"});
        data.put("xData", new String[]{"自检", "首检", "巡检"});
        List yData = new ArrayList();
        long[] y1 = {0, 0, 0};
        y1[0] = (long) map.get("c1");
        y1[1] = (long) map.get("c2");
        y1[2] = (long) map.get("c3");
        yData.add(y1);
        data.put("yData", yData);
        jo.put("data", data);
        return jo;
    }

    public JSONObject data3() {
        JSONObject jo = new JSONObject();
        Map data = new HashMap();
        Map map = dao.listAndonTypeTime();
        List yData = new ArrayList();
        long[] y1 = {0, 0, 0, 0};
        y1[0] = Long.parseLong(map.get("c1").toString());
        y1[1] = Long.parseLong(map.get("c2").toString());
        y1[2] = Long.parseLong(map.get("c3").toString());
        y1[3] = Long.parseLong(map.get("c4").toString());
        yData.add(y1);
        data.put("yData", yData);
        //indicator
        jo.put("data", data);
        return jo;
    }

    public JSONObject data4() {
        JSONObject jo = new JSONObject();
        Map data = new HashMap();
        List yData = new ArrayList();
        Map map = dao.listOperationCondition();
        Map m1 = new HashMap();
        Map m2 = new HashMap();
        Map m3 = new HashMap();
        Map m4 = new HashMap();
        m1.put("name","正常运行中");
        m2.put("name","正常休息");
        m3.put("name","异常运行中");
        m4.put("name","异常休息");

        m1.put("value",Long.parseLong(map.get("c1").toString()));
        m2.put("value",Long.parseLong(map.get("c2").toString()));
        m3.put("value",Long.parseLong(map.get("c3").toString()));
        m4.put("value",Long.parseLong(map.get("c4").toString()));
        yData.add(m1);
        yData.add(m2);
        yData.add(m3);
        yData.add(m4);
        data.put("seriesData", yData);
        jo.put("data", data);
        return jo;
    }
}
