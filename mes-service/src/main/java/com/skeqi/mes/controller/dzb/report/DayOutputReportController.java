package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.DayOutputReportService;
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
 * @Description 日产量统计
 */
@RestController
@RequestMapping("report/dayOutput")
public class DayOutputReportController {


    @Resource(name = "DayOutputReportService3")
    private DayOutputReportService service;

    @RequestMapping(value = "listLine",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="日产量统计", method="查询产线")
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

    @RequestMapping(value = "okRate",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="日产量统计", method="查询合格率")
    public Object okRate(HttpServletRequest request,Integer year,Integer month,Integer day,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.okRate(year,month,day,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "dayOutput",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="日产量统计", method="查询日产量")
    public Object dayOutput(HttpServletRequest request,Integer year,Integer month,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.dayOutput(year,month,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "monthOutput",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="日产量统计", method="查询日总产量")
    public Object monthOutput(HttpServletRequest request,Integer year,Integer month,Integer lineId){
        JSONObject out =new JSONObject();
        try {
            out = service.monthOutput(year,month,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }
}
