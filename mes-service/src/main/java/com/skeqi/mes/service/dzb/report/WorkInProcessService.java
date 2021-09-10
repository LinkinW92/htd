package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.dzb.report.WorkInProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 在制品报表
 * @Created by DZB
 * @Date 2021/7/8 16:09
 * @Description TODO
 */
@Service("WorkInProcessService")
public class WorkInProcessService {

    @SuppressWarnings("all")
    @Autowired
    private WorkInProcessDao dao;


    //查询所有数据
    public JSONObject data(List group, Map where, String start, String end, Integer pageSize, Integer pageNum) {
        pageSize = pageSize == null ? 10 : pageSize;
        pageNum = pageNum == null ? 1 : pageNum;
        PageHelper.startPage(pageNum, pageSize);
        JSONObject out = new JSONObject();
        List<Map> data = dao.data(group, where, start, end);
        PageInfo pageInfo = new PageInfo<>(data);
        out.put("data", pageInfo);
        return out;
    }
}
