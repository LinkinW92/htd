package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.MTTRService;
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
 * @Date 2021/3/25 11:22
 * @Description MTBF平均无故障工作时间
 */
@RestController
@RequestMapping("report/mtbf")
public class MTBFController {

    @Autowired
    private MTTRService service;

    @RequestMapping(value = "listEqu", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="平均无故障工作时间", method="查询设备")
    public Object listEqu(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.listEqu();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "mtbfInfo", method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="平均无故障工作时间", method="mttrInfo")
    public Object mtbfInfo(HttpServletRequest request,
                              @RequestParam(value = "start",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
                              @RequestParam(value = "end",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end,
                              @RequestParam("equId") Integer lineId) {
        JSONObject out = new JSONObject();
        try {
            out = service.listAndon(lineId, start,end);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }


}
