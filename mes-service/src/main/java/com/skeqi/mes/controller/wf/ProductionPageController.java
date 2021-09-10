package com.skeqi.mes.controller.wf;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.wf.ProductionPageService;
import com.skeqi.mes.service.wf.SchemePageService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * 生产首页
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "production", produces = MediaType.APPLICATION_JSON)
@Api(value = "生产首页", description = "生产首页", produces = MediaType.APPLICATION_JSON)
public class ProductionPageController {
    @Autowired
    private ProductionPageService service;

    @RequestMapping(value = "/findProduction", method = RequestMethod.POST)
    @ApiOperation(value = "产线状态列表", notes = "产线状态列表")
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

    @RequestMapping(value = "/findOnlineWorkOrder", method = RequestMethod.POST)
    @ApiOperation(value = "在线的生产工单", notes = "在线的生产工单")
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

    @RequestMapping(value = "/findEvent", method = RequestMethod.POST)
    @ApiOperation(value = "生产事件", notes = "生产事件")
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

    @RequestMapping(value = "/findProductionYield", method = RequestMethod.POST)
    @ApiOperation(value = "产品良率", notes = "产品良率")
    @ResponseBody
    public Rjson findProductionYield(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findProductionYield();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findOnlineShiftTeam", method = RequestMethod.POST)
    @ApiOperation(value = "在线班次", notes = "在线班次")
    @ResponseBody
    public Rjson findOnlineShiftTeam(HttpServletRequest request) throws ServicesException {
        List<Map<String, Object>> list = null;
        try {
            list = service.findOnlineShiftTeam();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }
}
