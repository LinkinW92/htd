package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.report.CompleteStatisticService;
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
 * @Date 2021/3/12 8:58
 * @Description 工位完成数量统计
 */
@RestController
@RequestMapping("report/completeStatistic")
public class CompleteStatisticController {

    @Resource( name = "CompleteStatisticService3" )
    private CompleteStatisticService service;

    @RequestMapping(value = "listLine",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="工位完成数量", method="查询产线")
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

    @RequestMapping(value = "listStationPass",method = RequestMethod.POST)
//    @OptionalLog(module="报表", module2="工位完成数量", method="查询完成数量")
    public Object listStationPass(HttpServletRequest request,
                                  Integer lineId,
                                  @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date,
                                  @RequestParam(value = "dayNum",required = false)Integer dayNum){
        JSONObject out =new JSONObject();
        try {
            out = service.listStationPass(lineId,date,dayNum);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

}
