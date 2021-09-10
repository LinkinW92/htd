package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.NgReportService;
import com.skeqi.mes.service.dzb.report.OnePassRateService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/11 23:54
 * @Description TODO
 */
@RestController
@RequestMapping("report/ngReport")
public class NgReportController {

    @Resource(name = "NgReportService")
    private NgReportService service;

    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Object data(HttpServletRequest request,
                           Map where,
                           String start,
                           int type) {
        JSONObject out = new JSONObject();
        try {
            out = service.data(where,start,type);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}
