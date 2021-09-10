package com.skeqi.mes.controller.wf;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.wf.SchemePageService;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * 计划首页
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "scheme", produces = MediaType.APPLICATION_JSON)
@Api(value = "计划首页", description = "计划首页", produces = MediaType.APPLICATION_JSON)
public class SchemePageController {

    @Autowired
    private SchemePageService service;


    @RequestMapping(value = "/findOnlineOrder", method = RequestMethod.POST)
    @ApiOperation(value = "在线订单", notes = "在线订单")
    @ResponseBody
    public Rjson findOnlineOrder(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findOnlineOrder();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "/findOnlineWorkOrder", method = RequestMethod.POST)
    @ApiOperation(value = "在线工单", notes = "在线工单")
    @ResponseBody
    public Rjson findOnlineWorkOrder(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findOnlineWorkOrder();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findOnlineLine", method = RequestMethod.POST)
    @ApiOperation(value = "在线产线", notes = "在线产线")
    @ResponseBody
    public Rjson findOnlineLine(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findOnlineLine();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findLineOptimalRate", method = RequestMethod.POST)
    @ApiOperation(value = "产线优率", notes = "产线优率")
    @ResponseBody
    public Rjson findLineOptimalRate(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findLineOptimalRate();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }













    @RequestMapping(value = "/findOrder", method = RequestMethod.POST)
    @ApiOperation(value = "订单整体情况", notes = "订单整体情况")
    @ResponseBody
    public Rjson findOrder(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findOrder();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findBuyingSituation", method = RequestMethod.POST)
    @ApiOperation(value = "采购整体情况", notes = "采购整体情况")
    @ResponseBody
    public Rjson findBuyingSituation(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findBuyingSituation();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findProduction", method = RequestMethod.POST)
    @ApiOperation(value = "生产整体情况", notes = "生产整体情况")
    @ResponseBody
    public Rjson findProduction(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findProduction();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findEvent", method = RequestMethod.POST)
    @ApiOperation(value = "最近十条事件", notes = "最近十条事件")
    @ResponseBody
    public Rjson findEvent(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findEvent();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }
}
