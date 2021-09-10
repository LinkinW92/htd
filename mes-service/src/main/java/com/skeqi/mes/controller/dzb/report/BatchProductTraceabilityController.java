package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.BatchProductTraceabilityService;
import com.skeqi.mes.service.dzb.report.CompleteStatisticService;
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
 * @Description 批量产品追溯报告
 */
@RestController
@RequestMapping("report/batchProductTraceability")
public class BatchProductTraceabilityController {

    @Autowired
    private BatchProductTraceabilityService service;

    @RequestMapping(value = "getAllProduction", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="批量产品追溯报告", method="查询产品")
    public Object getAllProduction(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.getProduction();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "getPTrack", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="批量产品追溯报告", method="查询sn")
    public Object getPTrack(HttpServletRequest request,
                            @RequestParam("productId") Integer productionId,
                            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
                            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end,
                            @RequestParam(value = "pageSize", required = false) Integer size,
                            @RequestParam(value = "pageNum", required = false) Integer page) {
        JSONObject out = new JSONObject();
        try {
            out = service.getPTrackByProductionId(productionId,start,end,size,page);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

}
