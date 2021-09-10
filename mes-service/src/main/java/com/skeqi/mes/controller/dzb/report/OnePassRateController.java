package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.OnePassRateService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Created by DZB
 * @Date 2021/3/18 8:34
 * @Description 一次性通过率
 */
@RestController
@RequestMapping("report/onePassRate")
public class OnePassRateController {
    @Autowired
    private OnePassRateService service;

    @RequestMapping(value = "listLine", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="一次性通过率", method="查询产线")
    public Object listLine(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.listLine();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "onePassRate", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="一次性通过率", method="查询通过率")
    public Object onePassRate(HttpServletRequest request,
                         @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         @RequestParam("lineId") Integer lineId) {
        JSONObject out = new JSONObject();
        try {
            out = service.onePassRate(date, lineId);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

}
