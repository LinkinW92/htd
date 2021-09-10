package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.QualityReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/24 15:30
 * @Description TODO
 */
@Service
public class QualityReportService implements QualityReportServiceI{

    @SuppressWarnings("all")
    @Autowired
    private QualityReportDao dao;

    public JSONObject qualityReport(Date date){
        int dayNum = 7;
        JSONObject jo = new JSONObject();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,-dayNum);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        List data = new ArrayList();
        for(int i=0;i<dayNum;i++){
            c.add(Calendar.DATE,1);
            Map map = dao.listQualityInfo(c.getTime());
            map.put("date",sdf.format(c.getTime()));
            data.add(map);
        }
        jo.put("data",data);
        return jo;
    }
}
