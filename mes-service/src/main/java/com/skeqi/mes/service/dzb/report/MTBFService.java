package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.MTTRDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/26 15:12
 * @Description TODO
 */
@Service
public class MTBFService implements MTBFServiceI{

    @SuppressWarnings("all")
    @Autowired
    private MTTRDao dao;

    public JSONObject listEqu(){
        JSONObject jo = new JSONObject();
        List<Map> maps = dao.listEqu();
        jo.put("data",maps);
        return jo;
    }

    public JSONObject listAndon(Integer equId, Date start,Date end){
        JSONObject jo = new JSONObject();
        List<Map> maps = dao.listAndon2(equId, start, end);
        jo.put("data",maps);
        return jo;
    }
}
