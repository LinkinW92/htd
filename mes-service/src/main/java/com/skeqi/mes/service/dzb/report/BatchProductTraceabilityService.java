package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.dzb.report.BatchProductTraceabilityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/22 11:37
 * @Description TODO
 */
@Service
public class BatchProductTraceabilityService implements BatchProductTraceabilityServiceI{

    @SuppressWarnings("all")
    @Autowired
    private BatchProductTraceabilityDao dao;


    public JSONObject getProduction(){
        JSONObject jo = new JSONObject();
        List<Map> allProduction = dao.getAllProduction();
        jo.put("data",allProduction);
        return jo;
    }
    public JSONObject getPTrackByProductionId(int productionId, Date start,Date end,Integer size,Integer page)
    {
        page = page==null?1:page;
        size = size==null?10:size;
        JSONObject jo = new JSONObject();
        PageHelper.startPage(page, size);
        List<Map> list = dao.getPTrackByProductionId(productionId, start, end);
        PageInfo<Map> pageInfo = new PageInfo<Map>(list, 5);
        jo.put("data",pageInfo);
        return jo;


    }
}
