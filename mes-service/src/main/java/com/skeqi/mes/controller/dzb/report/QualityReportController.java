package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.BatchProductTraceabilityService;
import com.skeqi.mes.service.dzb.report.QualityReportService;
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
 * @Date 2021/3/12 8:58
 * @Description 质量报告
 */
@RestController
@RequestMapping("report/qualityReport")
public class QualityReportController {

    @Autowired
    private QualityReportService service;


    @RequestMapping(value = "qualityReport", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="质量报告", method="质量报告")
    public Object qualityReport(HttpServletRequest request,
                            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        JSONObject out = new JSONObject();
        try {
            out = service.qualityReport(date);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

}
