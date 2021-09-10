package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.ScrewDownDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/10 15:29
 * @Description TODO
 */
@Service("ScrewDownService")
public class ScrewDownService implements ScrewDownServiceI{

    @SuppressWarnings("all")
    @Autowired
    public ScrewDownDao dao;

    public JSONObject listLine(){
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data",mapList);
        return out;
    }

    public JSONObject countBoltGroupSt(int lineId, Date startDate,Date endDate){
        JSONObject out = new JSONObject();
        if(endDate == null){
            Calendar c = Calendar.getInstance();
            c.setTime(startDate);
            c.add(Calendar.DATE,1);
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            endDate = c.getTime();
        }
        List<Map> mapList = dao.countBoltGroupSt(lineId,startDate,endDate);
        out.put("data",mapList);
        return out;
    }
}
