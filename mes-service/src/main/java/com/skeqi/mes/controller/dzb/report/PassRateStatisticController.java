package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.PassRateStatisticService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Created by DZB
 * @Date 2021/3/17 13:58
 * @Description 整体合格率统计
 */
@RestController
@RequestMapping("report/passRateStatistic")
public class PassRateStatisticController {

    @Resource(name = "PassRateStatisticService2")
    private PassRateStatisticService service;

    @RequestMapping(value = "listLine", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="整体合格率统计", method="查询产线")
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

    @RequestMapping(value = "okRate", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="整体合格率统计", method="查询合格率")
    public Object okRate(HttpServletRequest request,
                         @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         @RequestParam("lineId") Integer lineId) {
        JSONObject out = new JSONObject();
        try {
            out = service.dayOutput(date, lineId);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

}
