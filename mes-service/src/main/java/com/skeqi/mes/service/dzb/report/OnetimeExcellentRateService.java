package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.dzb.report.OnetimeExcellentRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/9 11:16
 * @Description TODO
 */
@Service("OnetimeExcellentRateService")
public class OnetimeExcellentRateService {

    @SuppressWarnings("all")
    @Autowired
    private OnetimeExcellentRateDao dao;

    //查询所有数据
    public JSONObject data(String[] group, Map where, String start, String end, Integer pageSize, Integer pageNum) {
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
