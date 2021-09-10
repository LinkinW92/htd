package com.skeqi.mes.service.dzb.market;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.market.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/19 14:30
 * @Description TODO
 */
@Service("TableService")
public class TableService {

    @SuppressWarnings("all")
    @Autowired
    public TableDao dao;

    /**
     * 表信息
     *
     * @return
     */
    public JSONObject listTable() {
        JSONObject jo = new JSONObject();
        List<Map> data = dao.listTable();
        jo.put("data", data);
        return jo;
    }

}
