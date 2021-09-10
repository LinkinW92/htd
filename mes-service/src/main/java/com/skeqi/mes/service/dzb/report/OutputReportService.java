package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.dzb.report.OutputReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产量报表
 *
 * @Created by DZB
 * @Date 2021/7/6 9:39
 * @Description TODO
 */
@Service("OutputReportService")
public class OutputReportService implements OutputReportServiceI {

    public List tableColumn;

    @SuppressWarnings("all")
    @Autowired
    public OutputReportDao dao;

    //初始化数据
    public JSONObject listInitData() {
        JSONObject out = new JSONObject();
        Map dateType = new HashMap();
        dateType.put("dayNo", "日期");
        dateType.put("weekNo", "自然周");
        dateType.put("monthNo", "月份");
        out.put("dateType", dateType);
        Map colList = new HashMap();
        colList.put("plantName", "车间");
        colList.put("lineName", "产线");
        colList.put("st", "工位");
        colList.put("productionName", "产品名称");
        colList.put("materialCode", "物料名称");
        out.put("colList", colList);
        return out;
    }

    //查询所有车间
    public JSONObject listPlant() {
        JSONObject out = new JSONObject();
        List list = dao.listPlant();
        out.put("data", list);
        return out;
    }

    //查询所有产线，条件，车间
    public JSONObject listLine(String[] plantCode) {
        JSONObject out = new JSONObject();
        List list = dao.listLine(plantCode);
        out.put("data", list);
        return out;
    }

    //查询所有工位，条件：产线
    public JSONObject listSt(Integer[] lineId) {
        JSONObject out = new JSONObject();
        List list = dao.listSt(lineId);
        out.put("data", list);
        return out;
    }

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
