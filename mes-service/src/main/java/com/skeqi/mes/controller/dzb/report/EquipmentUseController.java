package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.EquipmentUseService;
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
 * @Date 2021/3/5 14:57
 * @Description 设备使用率
 */
@RestController
@RequestMapping("report/equipmentUse")
public class EquipmentUseController {

    @Resource(name = "EquipmentUseService2")
    private EquipmentUseService service;

    @RequestMapping(value = "listLine", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="设备使用率", method="查询产线")
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

    @RequestMapping(value = "listEquUse", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="设备使用率", method="查询使用率")
    public Object listEquUse(HttpServletRequest request,
                             @RequestParam("lineId") Integer lineId,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date") Date date,
                             @RequestParam("dayNum") Integer dayNum) {
        JSONObject out = new JSONObject();
        try {
            out = service.queryEquipmentUse(lineId, date,dayNum);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "listEquUseGroupSn", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="设备使用率", method="查询工位使用率")
    public Object listEquUseGroupSn(HttpServletRequest request,
                                    @RequestParam("lineId") Integer lineId,
                                    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        JSONObject out = new JSONObject();
        try {
            out = service.queryEquipmentUseSt(lineId, date);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }


}
