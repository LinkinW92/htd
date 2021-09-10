package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.NgReportService;
import com.skeqi.mes.service.dzb.report.TotalReportService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("report/TotalReport")
public class TotalReportController {

    @Resource(name = "TotalReportService")
    private TotalReportService service;

    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Object data(HttpServletRequest request,
                       String[] group, Map where, String start, String end, Integer pageSize, Integer pageNum) {
        JSONObject out = new JSONObject();
        try {
            out = service.data(group,where,start,end,pageSize,pageNum);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}
