package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.NgReportService;
import com.skeqi.mes.service.dzb.report.OnetimeExcellentRateService;
import com.skeqi.mes.util.ToolUtils;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("report/OnetimeExcellentRate")
public class OnetimeExcellentRateController {

    @Resource(name = "OnetimeExcellentRateService")
    private OnetimeExcellentRateService service;

    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Object data(HttpServletRequest request,
                       String[] group, Map where, String start, String end,
                       Integer pageSize, Integer pageNum) {
        JSONObject out = new JSONObject();
        try {
            JSONObject jo = service.data(group, where, start, end, pageSize, pageNum);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}
