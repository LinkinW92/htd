package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.dzb.report.EquipmentOEEService;
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/19 8:26
 * @Description 设备oee
 */
@RestController
@RequestMapping("report/equipmentOEE")
public class EquipmentOEEController {

    @Autowired
    private EquipmentOEEService service;



    @RequestMapping(value = "listLine",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="设备oee", method="查询产线")
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

    @RequestMapping(value = "oeeInfo",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="设备oee", method="查询oee")
    public Object oeeInfo(HttpServletRequest request,
                         @RequestParam("lineId") Integer lineId,
                         @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam("date") Date date){
        JSONObject out =new JSONObject();
        try {
            out = service.queryEquipmentOEEValue(date,lineId);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }



}
