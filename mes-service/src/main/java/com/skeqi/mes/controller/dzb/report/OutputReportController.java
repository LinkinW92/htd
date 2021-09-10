package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.NgReportService;
import com.skeqi.mes.service.dzb.report.OutputReportService;
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
@RequestMapping("report/OutputReport")
public class OutputReportController {

    @Resource(name = "OutputReportService")
    private OutputReportService service;

    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Object data(HttpServletRequest request,
                       String[] group, Map where, String start, String end, Integer pageSize, Integer pageNum) {
        JSONObject out = new JSONObject();
        try {
            out = service.data(group, where, start, end, pageSize, pageNum);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "listLine", method = RequestMethod.POST)
    public Object listLine(HttpServletRequest request, String[] plantCode) {
        JSONObject out = new JSONObject();
        try {
            out = service.listLine(plantCode);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "listSt", method = RequestMethod.POST)
    public Object listSt(HttpServletRequest request,
                         Integer[] lineId) {
        JSONObject out = new JSONObject();
        try {
            out = service.listSt(lineId);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "listPlant", method = RequestMethod.POST)
    public Object listPlant(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.listPlant();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
    @RequestMapping(value = "listInitData", method = RequestMethod.POST)
    public Object listInitData(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.listInitData();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}
