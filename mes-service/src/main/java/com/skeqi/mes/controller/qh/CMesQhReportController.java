package com.skeqi.mes.controller.qh;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.qh.CMesJounralT;
import com.skeqi.mes.service.qh.CMesJounralService;
import com.skeqi.mes.service.qh.CMesQhReportTService;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/23 16:12
 */
@Controller
@RequestMapping(value = "api")
@Api(value = "报表管理", description = "报表管理")
public class CMesQhReportController {

    @Autowired
    CMesQhReportTService service;

    @Autowired
    CMesJounralService jservice;


    @RequestMapping(value = "/report/findMonth",method = RequestMethod.POST)
    @ApiOperation(value = "查询月产量", notes = "查询月产量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")})
    @ResponseBody
    public JSONObject findMonth(HttpServletRequest request, String year,Integer lineId){
        JSONObject json = new JSONObject();
        json.put("code",100);
        try {
            Map<Integer, Object> months = this.findMonths();
            List<Map<String, Object>> monthSum = service.findMonthSum(year, lineId);
            for (Map<String,Object> map : monthSum){
                months.put(Integer.parseInt(map.get("months").toString()),map.get("nums"));
            }

            String nums = "";
            for (Integer day : months.keySet()){
                nums = nums + months.get(day)+",";
            }
            json.put("month",months.keySet());
            json.put("nums",nums.substring(0,nums.length()-1));
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",300);
            return json;
        }
    }

    @RequestMapping(value = "/report/findDay",method = RequestMethod.POST)
    @ApiOperation(value = "查询日产量",notes = "查询日产量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year", value = "年份", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "month", value = "月份", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")})
    @ResponseBody
    public JSONObject findDay(HttpServletRequest request, String year,String month,Integer lineId){
        JSONObject json = new JSONObject();
        json.put("code",100);
        try {
            Map<Integer,Object>  days1 = this.findDays(Integer.parseInt(year), Integer.parseInt(month));  //获取当月天数

            List<Map<String, Object>> daySum = service.findDaySum(year, month, lineId);
            for (Map<String,Object> map : daySum){
                days1.put(Integer.parseInt(map.get("days").toString()),map.get("nums"));
            }
            String nums = "";

            for (Integer maps : days1.keySet()){
                nums = nums + days1.get(maps)+",";
            }
            json.put("days",days1.keySet());
            json.put("nums",nums.substring(0,nums.length()-1));
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",300);
            return json;
        }
    }

    @RequestMapping(value = "/report/tightenPass",method = RequestMethod.POST)
    @ApiOperation(value = "查询拧紧合格率",notes = "查询拧紧合格率   ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDt", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDt", value = "结束时间", required = false, paramType = "query", dataType = "String")})
    @ResponseBody
    public JSONObject tightenPass(HttpServletRequest request, String startDt,String endDt){
        JSONObject json = new JSONObject();
        json.put("code",100);
        try {
            List<Map<String,Object>> list = new ArrayList<>();
            List<Map<String, Object>> maps = service.tightenPass(startDt, endDt);
            for (Map<String, Object> map : maps){
                Map<String,Object> m = new HashMap<>();
                m.put(map.get("st").toString(),service.findNgNums(startDt,endDt,map.get("st").toString()).doubleValue()/Double.parseDouble(map.get("nums").toString()));
                list.add(m);
            }
            json.put("list",list);
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
             json.put("code",300);
             return json;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/report/findAlljounral",method = RequestMethod.POST)
    @ApiOperation(value = "查询操作日志",notes = "查询操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sn", value = "总成", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "APi类型", required = false, paramType = "query", dataType = "String")})
    public JSONObject findAlljounral(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "50")Integer pageSize,String sn,String types){
        JSONObject json = new JSONObject();
        json.put("code",100);
        try {
            if(sn=="" && types==""){
                json.put("code",200);
                return json;
            }
            List<CMesWebApiLogs> allJounral = jservice.findAllJounral(sn, types);
            PageInfoUtil<CMesWebApiLogs> pageinfo = new PageInfoUtil<>(allJounral,5,pageSize,pageNum);
            json.put("list",pageinfo);
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",300);
            return json;
        }
    }


    public Map<Integer,Object> findMonths(){
        Map<Integer,Object> map = new HashMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        map.put(4,0);
        map.put(5,0);
        map.put(6,0);
        map.put(7,0);
        map.put(8,0);
        map.put(9,0);
        map.put(10,0);
        map.put(11,0);
        map.put(12,0);
        return map;
    }

    public  Map<Integer,Object>  findDays(Integer year,Integer month){
        int days = 0;
        boolean isLeapYear = false;
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 2:
                if (isLeapYear) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            default:
                System.out.println("error!!!");
                break;
        }
        Map<Integer,Object> json = new HashMap<>();
        for (int i=1;i<=days;i++){
            json.put(i,0);
        }
        return json;
    }
}
