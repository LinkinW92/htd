package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.CompleteStatisticDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/10 15:29
 * @Description TODO
 */
@Service("CompleteStatisticService")
public class CompleteStatisticService implements CompleteStatisticServiceI{

    @SuppressWarnings("all")
    @Autowired
    public CompleteStatisticDao dao;

    public JSONObject listLine(){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data",mapList);
        return out;
    }

    public JSONObject listStationPass(int lineId,Date date,Integer dayNum){
        if(dayNum == null) dayNum = 7;
        JSONObject out = new JSONObject();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,-dayNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List dateList = new ArrayList();
        List stPassList = new ArrayList();
        List stList = dao.listStation(lineId);
        for(int i = 0 ; i < dayNum ; i ++){
            c.add(Calendar.DATE,1);
            dateList.add(sdf.format(c.getTime()));
            stPassList.add(dao.listStationPass(lineId,c.getTime()));
        }
        Map data = new HashMap();
        data.put("stList",stList);
        data.put("stPassList",stPassList);
        data.put("dateList",dateList);
        out.put("data",data);
        return out;
    }
}
