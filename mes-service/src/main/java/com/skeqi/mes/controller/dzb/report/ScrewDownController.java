package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.ScrewDownService;
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
 * @Date 2021/3/11 9:35
 * @Description 拧紧合格率
 */

@RestController
@RequestMapping("report/screwDown")
public class ScrewDownController {

    @Resource(name = "ScrewDownService")
    private ScrewDownService service;

    @RequestMapping(value = "queryScrewDown",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="拧紧合格率", method="查询拧紧数据")
    public Object queryScrewDown(HttpServletRequest request,
                                 @RequestParam("lineId") Integer lineId,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("startDate") Date startDate,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endDate",required = false) Date endDate){
        JSONObject out =new JSONObject();
        try {
            out = service.countBoltGroupSt(lineId,startDate,endDate);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    @RequestMapping(value = "listLine",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="拧紧合格率", method="查询产线")
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

}
