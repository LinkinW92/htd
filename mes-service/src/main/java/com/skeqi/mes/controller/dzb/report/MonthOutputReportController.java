package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.MonthOutputReportService;
import com.skeqi.mes.service.dzb.report.MonthOutputReportService2;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Created by DZB
 * @Date 2021/3/5 14:57
 * @Description 月产量统计
 */
@RestController
@RequestMapping("report/monthOutput")
public class MonthOutputReportController {

    @Resource(name = "MonthOutputReportService2")
    private MonthOutputReportService service;

    @RequestMapping(value = "listLine",method = RequestMethod.POST)
    @OptionalLog(module="报表", module2="月产量统计", method="查询产线")
    public Object listLine(HttpServletRequest request){
        JSONObject out =new JSONObject();
        try {
            out = service.listLine();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "monthOutput",method = RequestMethod.POST)
    @OptionalLog(module="报表", module2="月产量统计", method="查询每月产量")
    public Object monthOutput(HttpServletRequest request,Integer year,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.monthOutput(year,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }
    @RequestMapping(value = "okRate",method = RequestMethod.POST)
    @OptionalLog(module="报表", module2="月产量统计", method="查询月合格率")
    public Object okRate(HttpServletRequest request,Integer year,Integer month,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.okRate(year,month,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "yearOutput",method = RequestMethod.POST)
    @OptionalLog(module="报表", module2="月产量统计", method="查询月总产量")
    public Object yearOutput(HttpServletRequest request,Integer year,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.yearOutput(year,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }
}
