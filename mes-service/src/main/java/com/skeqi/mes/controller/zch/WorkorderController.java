package com.skeqi.mes.controller.zch;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.skeqi.mes.pojo.*;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.TechnologyService;
import com.skeqi.mes.service.lcy.GetReportFormsService;
import com.skeqi.mes.service.lcy.ProcessRouteService;
import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.service.rework.ReworkService;
import com.skeqi.mes.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.zch.OrderService;
import com.skeqi.mes.service.zch.WorkorderService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 报警信息管理
 *
 * @ClassName: WorkorderController
 */
@Controller
@RequestMapping(value = "wor", produces = MediaType.APPLICATION_JSON)
@Api(value = "工单管理", description = "工单管理", produces = MediaType.APPLICATION_JSON)
public class WorkorderController {
    Logger log = Logger.getLogger(WorkorderController.class);

    @Autowired
    private WorkorderService service;
    @Autowired
    private OrderService orderService;
    @Autowired
    EventService serviceEvent;

    @Autowired
    CMesLineTService cMesLineTService;

    @Autowired
    CMesProductionService cMesProductionService;
    @Autowired
    ProcessRouteService processRouteService;

    @Autowired
    GetReportFormsService getReportFormsService;


    @Autowired
    TechnologyService technologyService;
    @Autowired
    CMesBomService cMesBomService;
    @Autowired
    CMesRecipeService cMesRecipeService;
    @Autowired
    ReworkService reworkService;


    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    @ApiOperation(value = "查询工单列表", notes = "查询工单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "workorderId", value = "工单编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "int")
    })
    @ResponseBody
    public Rjson findList(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;

        Map<String, Object> map = new HashMap<>();

        try {
            map.put("lineId", request.getParameter("lineId"));
            map.put("workorderId", request.getParameter("workorderId"));
            map.put("status", request.getParameter("status"));

            Integer pageNum = 1;
            Integer pageSize = 6;
            if (request.getParameter("pageNum") != null) {
                pageNum = Integer.parseInt(request.getParameter("pageNum"));
            }
            if (request.getParameter("pageSize") != null) {
                pageSize = Integer.parseInt(request.getParameter("pageSize"));
            }

            PageHelper.startPage(pageNum, pageSize);
            list = service.findWorkorderList(map);
            PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            log.error(e);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findListAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有工单列表", notes = "查询所有工单列表")
    @ResponseBody
    public Rjson findListAll(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;
        try {
            list = service.findListAll();
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "/addWorkorder", method = RequestMethod.POST)
    @ApiOperation(value = "新增工单", notes = "新增工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workorderId", value = "工单编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "teamId", value = "班次id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "planStartTime", value = "计划开始时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "planEndTime", value = "计划结束时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "routingId", value = "工艺路线id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "barcodeRuleId", value = "条码规则Id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bomId", value = "BOM清单id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderNumber", value = "订单数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalRecipeId", value = "配方id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ORDER_ID", value = "订单id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ORDERRECORD_ID", value = "订单记录id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "workorderIdOld", value = "预设工单编号", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "工单管理", method = "新增工单")
    public Rjson addWorkorder(HttpServletRequest request) throws ServicesException {

        Map<String, Object> map = new HashMap<>();

        try {

            map.put("workorderId", request.getParameter("workorderId"));
            map.put("workorderIdOld", request.getParameter("workorderIdOld"));
            map.put("lineId", !"".equals(request.getParameter("lineId")) ? request.getParameter("lineId") : null);
            map.put("teamId", !"".equals(request.getParameter("teamId")) ? request.getParameter("teamId") : null);
            map.put("planId", !"".equals(request.getParameter("planId")) ? request.getParameter("planId") : null);
            map.put("planStartTime", request.getParameter("planStartTime"));
            map.put("planEndTime", request.getParameter("planEndTime"));
            map.put("routingId", !"".equals(request.getParameter("routingId")) ? request.getParameter("routingId") : null);
            map.put("barcodeRuleId", !"".equals(request.getParameter("barcodeRuleId")) ? request.getParameter("barcodeRuleId") : null);
            map.put("bomId", !"".equals(request.getParameter("bomId")) ? request.getParameter("bomId") : null);
            map.put("orderNumber", !"".equals(request.getParameter("orderNumber")) ? request.getParameter("orderNumber") : null);
            map.put("productId", !"".equals(request.getParameter("productId")) ? request.getParameter("productId") : null);
            map.put("totalRecipeId", !"".equals(request.getParameter("totalRecipeId")) ? request.getParameter("totalRecipeId") : null);
            map.put("ORDER_ID", !"".equals(request.getParameter("ORDER_ID")) ? request.getParameter("ORDER_ID") : null);
            System.err.println(map.get("ORDERRECORD_ID"));
            map.put("ORDERRECORD_ID", !"".equals(request.getParameter("ORDERRECORD_ID")) ? request.getParameter("ORDERRECORD_ID") : null);
            Integer count = service.getCountByWorkorderId(map);
            if (count > 0) {
                return Rjson.error("工单编号已存在！");
            }

            HttpSession session = request.getSession();
            String lineCode = request.getParameter("lineCode");
            // 产线Code
            if (StringUtil.eqNu(lineCode)) {
                CMesLineT line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    map.put("lineId", lineMap.get("ID"));
                    session.setAttribute("lineCode", lineCode);
                } else {
                    return Rjson.error("产线不存在");
                }

            } else {
                CMesLineT line = new CMesLineT();
                line.setId(Integer.parseInt((String) map.get("lineId")));
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else {
                    return Rjson.error("产线不存在");
                }

            }
            // 产品Code
            String proCode = request.getParameter("productCode");
            if (StringUtil.eqNu(proCode)) {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(null, proCode);
                if (null != productionT) {
                    map.put("productId", productionT.getId());
                    session.setAttribute("productCode", proCode);
                } else {
                    return Rjson.error("产品不存在");
                }

            } else {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(Integer.parseInt((String) map.get("productId")), null);
                if(null!=productionT){
                    session.setAttribute("productCode", productionT.getProductionName());
                }else {
                    return Rjson.error("班次不存在");
                }

            }

            // 工艺Code
            String routingCode = request.getParameter("routingCode");
            if (StringUtil.eqNu(routingCode)) {
                List<CMesRoutingT> routingByLineIDAndName = processRouteService.getRoutingByLineIDAndName(null, routingCode);
                // 工艺
                if (routingByLineIDAndName.size() > 0) {
                    map.put("routingId", routingByLineIDAndName.get(0).getId());
                    session.setAttribute("routingCode", routingCode);
                }else {
                    return Rjson.error("工艺不存在");
                }

            } else {
                List<CMesRoutingT> routingByLineIDAndName = processRouteService.getRoutingByLineIDAndName(String.valueOf(map.get("routingId")), null);
                if(routingByLineIDAndName.size()>0){
                    session.setAttribute("routingCode", routingByLineIDAndName.get(0).getName());
                }else {
                    return Rjson.error("工艺不存在");
                }

            }
            // 班次班组Code查询
            String teamName = request.getParameter("teamName");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (StringUtil.eqNu(teamName) && StringUtil.eqNu(startTime) && StringUtil.eqNu(endTime)) {
                CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
                cMesShiftsTeamT.setName(teamName);
                cMesShiftsTeamT.setStartTime(startTime);
                cMesShiftsTeamT.setEndTime(endTime);
                List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
                if (cMesShiftsTeamTS.size() > 0) {
                    map.put("teamId", cMesShiftsTeamTS.get(0).getId());
                    session.setAttribute("teamName", teamName);
                    session.setAttribute("startTime", startTime);
                    session.setAttribute("endTime", endTime);
                } else {
                    return Rjson.error("班次不存在");
                }

            } else {
                CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
                cMesShiftsTeamT.setId(Integer.parseInt(String.valueOf(map.get("teamId"))));
                List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
                if(cMesShiftsTeamTS.size()>0){
                    session.setAttribute("teamName", cMesShiftsTeamTS.get(0).getName());
                    session.setAttribute("startTime", cMesShiftsTeamTS.get(0).getStartTime());
                    session.setAttribute("endTime", cMesShiftsTeamTS.get(0).getEndTime());
                }else {
                    return Rjson.error("班次不存在");
                }

            }
            // 条码规则  barcodeRuleCode
            String barcodeRuleCode = request.getParameter("barcodeRuleCode");
            if (StringUtil.eqNu(barcodeRuleCode)) {
                // 标签id
                CMesLabelManagerT cMesLabelType = new CMesLabelManagerT();
                cMesLabelType.setLabelName(barcodeRuleCode);
                List<CMesLabelManagerT> allLabelManager = technologyService.findAllLabelManager(cMesLabelType);
                if (allLabelManager.size() > 0) {
                    map.put("barcodeRuleId", allLabelManager.get(0).getId());
                    session.setAttribute("barcodeRuleCode", barcodeRuleCode);
                } else {
                    return Rjson.error("标签不存在");
                }
            } else {
                CMesLabelManagerT cMesLabelType = new CMesLabelManagerT();
                cMesLabelType.setId(Integer.parseInt((String) map.get("barcodeRuleId")));
                List<CMesLabelManagerT> allLabelManager = technologyService.findAllLabelManager(cMesLabelType);
                if (null != allLabelManager) {
                    session.setAttribute("barcodeRuleCode", allLabelManager.get(0).getLabelName());
                }else {
                    return Rjson.error("标签不存在");
                }
            }
            // bomICode
            String bomCode = request.getParameter("bomCode");
            String bomId = request.getParameter("bomId");
            if (StringUtil.eqNu(bomCode)) {
                // bomId
                CMesMaterialListT allBomIdAndListName = cMesBomService.findAllBomIdAndListName(null, bomCode);
                if (null != allBomIdAndListName) {
                    map.put("bomId", allBomIdAndListName.getId());
                    session.setAttribute("bomCode", bomCode);
                } else {
                    return Rjson.error("料单不存在");
                }

            } else if(StringUtil.eqNu(bomId)) {
                CMesMaterialListT allBomIdAndListName = cMesBomService.findAllBomIdAndListName(Integer.parseInt((String) map.get("bomId")), null);
                if(null!=allBomIdAndListName){
                session.setAttribute("bomCode", allBomIdAndListName.getListName());
                }else {
                    return Rjson.error("料单不存在");
                }
            }

            // totalRecipeCode
            String totalRecipeCode = request.getParameter("totalRecipeCode");
            if (StringUtil.eqNu(totalRecipeCode)) {
                List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(totalRecipeCode, null);
                if (totalRecipeByParam.size() > 0) {
                    map.put("totalRecipeId", totalRecipeByParam.get(0).getId());
                    session.setAttribute("totalRecipeCode", totalRecipeByParam.get(0).getTotalRecipeName());
                } else {
                    return Rjson.error("总配方不存在");
                }

            } else {
                List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(null, Integer.parseInt((String) map.get("totalRecipeId")));
                if(totalRecipeByParam.size()>0){
                session.setAttribute("totalRecipeCode", totalRecipeByParam.get(0).getTotalRecipeName());
                }else {
                    return Rjson.error("总配方不存在");
                }
            }

            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            map.put("custom", jsonArray);

            service.addWorkorder(map);

            // 事件添加
            Map<String, Object> mapEvent = new HashMap<>();
            mapEvent.put("OBJECT_TYPE", "工单");
            if ("1".equals(request.getParameter("isScheduling"))) {
                mapEvent.put("OBJECT_TYPE", "排产");

                // 将订单状态改成已排产并生成排产事件
                Map<String, Object> map2 = new HashMap<>();
                map2.put("ID", map.get("ORDER_ID"));
                map2.put("STATUS", 4);
                Map<String, Object> mapOld = orderService.getByID(map2);
                if ("2".equals(mapOld.get("STATUS"))) {
                    orderService.updateOrder(map2);
                }
                Map<String, Object> mapEvent2 = new HashMap<>();
                mapEvent2.put("OBJECT_TYPE", "订单");
                mapEvent2.put("OBJECT_ID", mapOld.get("CODE"));
                mapEvent2.put("EVENT", "排产");
                mapEvent2.put("PARAMETER1", JSONObject.toJSONString(map2));
                mapEvent2.put("OPERATOR", ToolUtils.getCookieUserName(request));
                serviceEvent.addEvent(mapEvent2);
            }
            mapEvent.put("OBJECT_ID", request.getParameter("workorderId"));
            mapEvent.put("EVENT", "创建");
            mapEvent.put("PARAMETER1", JSONObject.toJSONString(map));
            mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
            serviceEvent.addEvent(mapEvent);

            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/updateWorkorder", method = RequestMethod.POST)
    @ApiOperation(value = "修改工单", notes = "修改工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "工单id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "workorderId", value = "工单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "teamId", value = "班次id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "planStartTime", value = "计划开始时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "planEndTime", value = "计划结束时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "routingId", value = "工艺路线id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "barcodeRuleId", value = "条码规则Id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "bomId", value = "BOM清单id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderNumber", value = "订单数量", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "totalRecipeId", value = "配方id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "levelNo", value = "优先级", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ORDER_ID", value = "订单id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ORDERRECORD_ID", value = "订单记录id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "workorderIdOld", value = "修改前工单编号", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "工单管理", method = "修改工单")
    public Rjson updateWorkorder(HttpServletRequest request) throws ServicesException {

        Map<String, Object> map = new HashMap<>();

        try {
            map.put("ID", request.getParameter("ID"));
            map.put("WORKORDER_ID", request.getParameter("workorderId"));
            map.put("workorderIdOld", request.getParameter("workorderIdOld"));
            map.put("LINE_ID", request.getParameter("lineId"));
            map.put("TEAM_ID", request.getParameter("teamId"));
            map.put("PLAN_ID", request.getParameter("planId"));
            map.put("PLAN_START_TIME", request.getParameter("planStartTime"));
            map.put("PLAN_END_TIME", request.getParameter("planEndTime"));
            map.put("ROUTING_ID", request.getParameter("routingId"));
            map.put("BARCODE_RULE_ID", request.getParameter("barcodeRuleId"));
            map.put("BOM_ID", request.getParameter("bomId"));
            map.put("ORDER_NUMBER", !"".equals(request.getParameter("orderNumber")) ? request.getParameter("orderNumber") : null);
            map.put("PRODUCT_ID", request.getParameter("productId"));
            map.put("TOTAL_RECIPE_ID", request.getParameter("totalRecipeId"));
            map.put("LEVEL_NO", request.getParameter("levelNo"));
            map.put("STATUS", request.getParameter("status"));
            map.put("ORDER_ID", request.getParameter("ORDER_ID"));
            map.put("ORDERRECORD_ID", request.getParameter("ORDERRECORD_ID"));
            System.err.println(request.getParameter("custom"));


            // 判断工单编号是否重复
            if (!StringUtils.isEmpty(map.get("WORKORDER_ID")) && !StringUtils.isEmpty(map.get("workorderIdOld")) && !map.get("WORKORDER_ID").toString().equals(map.get("workorderIdOld"))) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("workorderId", map.get("WORKORDER_ID"));
                int count = service.getCountByWorkorderId(map2);
                if (count > 0) {
                    return Rjson.error("工单编号已存在！");
                }
            }
            HttpSession session = request.getSession();

            // workOrderCode
            String workOrderCode = request.getParameter("workOrderCode");
            if (StringUtil.eqNu(workOrderCode)) {
                // workOrderCode
                List<Map<String, Object>> idAndWorkOrderId = reworkService.findIdAndWorkOrderId(null, workOrderCode);
                if (idAndWorkOrderId.size() > 0) {
                    map.put("ID", String.valueOf(idAndWorkOrderId.get(0).get("id")));
                    session.setAttribute("workOrderCode", workOrderCode);
                } else {
                    return Rjson.error("工单不存在");
                }

            } else {
                List<Map<String, Object>> idAndWorkOrderId = reworkService.findIdAndWorkOrderId(Integer.parseInt((String) map.get("ID")), null);
                if(idAndWorkOrderId.size()>0){
                session.setAttribute("workOrderCode", idAndWorkOrderId.get(0).get("workOrderId"));
                }else {
                    return Rjson.error("工单不存在");
                }
            }

            // 产线Code
            String lineCode = request.getParameter("lineCode");
            if (StringUtil.eqNu(lineCode)) {
                CMesLineT line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    map.put("LINE_ID", lineMap.get("ID"));
                    session.setAttribute("lineCode", lineCode);
                } else {
                    return Rjson.error("产线不存在");
                }

            } else {
                CMesLineT line = new CMesLineT();
                line.setId(Integer.parseInt((String) map.get("LINE_ID")));
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else {
                    return Rjson.error("产线不存在");
                }

            }
            // 产品Code
            String proCode = request.getParameter("productCode");
            if (StringUtil.eqNu(proCode)) {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(null, proCode);
                if (null != productionT) {
                    map.put("PRODUCT_ID", productionT.getId());
                    session.setAttribute("productCode", proCode);
                } else {
                    return Rjson.error("产品不存在");
                }

            } else {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(Integer.parseInt((String) map.get("PRODUCT_ID")), null);
                if(null!=productionT){
                session.setAttribute("productCode", productionT.getProductionName());
                }else {
                    return Rjson.error("产品不存在");
                }
            }

            // 工艺Code
            String routingCode = request.getParameter("routingCode");
            if (StringUtil.eqNu(routingCode)) {
                List<CMesRoutingT> routingByLineIDAndName = processRouteService.getRoutingByLineIDAndName(null, routingCode);
                // 工艺
                if (routingByLineIDAndName.size() > 0) {
                    map.put("ROUTING_ID", routingByLineIDAndName.get(0).getId());
                    session.setAttribute("routingCode", routingCode);
                }else {
                    return Rjson.error("工艺不存在");
                }

            } else {
                List<CMesRoutingT> routingByLineIDAndName = processRouteService.getRoutingByLineIDAndName(String.valueOf(map.get("ROUTING_ID")), null);
                if (routingByLineIDAndName.size() > 0) {
                    session.setAttribute("routingCode", routingByLineIDAndName.get(0).getName());
                }else {
                    return Rjson.error("工艺不存在");
                }

            }
            // 班次班组Code查询
            String teamName = request.getParameter("teamName");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (StringUtil.eqNu(teamName) && StringUtil.eqNu(startTime) && StringUtil.eqNu(endTime)) {
                CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
                cMesShiftsTeamT.setName(teamName);
                cMesShiftsTeamT.setStartTime(startTime);
                cMesShiftsTeamT.setEndTime(endTime);
                List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
                if (cMesShiftsTeamTS.size() > 0) {
                    map.put("TEAM_ID", cMesShiftsTeamTS.get(0).getId());
                    session.setAttribute("teamName", teamName);
                    session.setAttribute("startTime", startTime);
                    session.setAttribute("endTime", endTime);
                } else {
                    return Rjson.error("班次不存在");
                }

            } else {
                CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
                cMesShiftsTeamT.setId(Integer.parseInt((String) map.get("TEAM_ID")));
                List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
                if(cMesShiftsTeamTS.size()>0){
                    session.setAttribute("teamName", cMesShiftsTeamTS.get(0).getName());
                    session.setAttribute("startTime", cMesShiftsTeamTS.get(0).getStartTime());
                    session.setAttribute("endTime", cMesShiftsTeamTS.get(0).getEndTime());
                }else {
                    return Rjson.error("班次不存在");
                }
            }
            // 条码规则  barcodeRuleCode
            String barcodeRuleCode = request.getParameter("barcodeRuleCode");
            if (StringUtil.eqNu(barcodeRuleCode)) {
                // 标签id
                CMesLabelManagerT cMesLabelType = new CMesLabelManagerT();
                cMesLabelType.setLabelName(barcodeRuleCode);
                List<CMesLabelManagerT> allLabelManager = technologyService.findAllLabelManager(cMesLabelType);
                if (allLabelManager.size() > 0) {
                    map.put("BARCODE_RULE_ID", allLabelManager.get(0).getId());
                    session.setAttribute("barcodeRuleCode", barcodeRuleCode);
                } else {
                    return Rjson.error("标签不存在");
                }
            } else {
                CMesLabelManagerT cMesLabelType = new CMesLabelManagerT();
                cMesLabelType.setId(Integer.parseInt((String) map.get("BARCODE_RULE_ID")));
                List<CMesLabelManagerT> allLabelManager = technologyService.findAllLabelManager(cMesLabelType);
                if (null != allLabelManager) {
                    session.setAttribute("barcodeRuleCode", allLabelManager.get(0).getLabelName());
                }else {
                    return Rjson.error("标签不存在");
                }
            }

            // bomICode
            String bomCode = request.getParameter("bomCode");
            if (StringUtil.eqNu(bomCode)) {
                // bomId
                CMesMaterialListT allBomIdAndListName = cMesBomService.findAllBomIdAndListName(null, bomCode);
                if (null != allBomIdAndListName) {
                    map.put("BOM_ID", allBomIdAndListName.getId());
                    session.setAttribute("bomCode", bomCode);
                } else {
                    return Rjson.error("料单不存在");
                }

            } else {
                CMesMaterialListT allBomIdAndListName = cMesBomService.findAllBomIdAndListName(Integer.parseInt((String) map.get("BOM_ID")), null);
                if (null!=allBomIdAndListName){
                    session.setAttribute("bomCode", allBomIdAndListName.getListName());
                }else {
                    return Rjson.error("料单不存在");
                }

            }

            // totalRecipeCode
//            String totalRecipeCode = request.getParameter("totalRecipeCode");
//            if (StringUtil.eqNu(totalRecipeCode)) {
//                List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(totalRecipeCode, null);
//                map.put("totalRecipeId", totalRecipeByParam.get(0).getId());
//                session.setAttribute("totalRecipeCode", totalRecipeByParam.get(0).getTotalRecipeName());
//            } else {
//                List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(null, Integer.parseInt((String)map.get("totalRecipeId")));
//                session.setAttribute("totalRecipeCode", totalRecipeByParam.get(0).getTotalRecipeName());
//            }


            //修改事件
            Map<String, Object> mapOld = service.getByID(map);
            Map<String, Object> mapJson = Rjson.reservingDifferences(map, mapOld);

            Map<String, Object> mapEvent = new HashMap<>();
            mapEvent.put("OBJECT_TYPE", "工单");
            if ("1".equals(request.getParameter("isScheduling"))) {
                mapEvent.put("OBJECT_TYPE", "排产");
            }
            mapEvent.put("OBJECT_ID", request.getParameter("workorderId"));
            mapEvent.put("EVENT", "修改");
            mapEvent.put("PARAMETER1", mapJson.get("oMap"));
            mapEvent.put("PARAMETER2", mapJson.get("nMap"));
            mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
            serviceEvent.addEvent(mapEvent);

            map.put("LINE_ID", request.getParameter("lineId"));
            map.put("LEVEL_NO", request.getParameter("levelNo"));
            map.put("STATUS", request.getParameter("status"));

            //修改自定义属性值（内容）
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            map.put("custom", jsonArray);
            map.put("workorderId", mapOld.get("WORKORDER_ID"));

            if (request.getParameter("status").equals("3")) {  //计划完成，转移P表
                service.moveWork(Integer.parseInt(request.getParameter("ID")));
                return Rjson.success();
            }

            service.updateWorkorder(map);

            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/deleteWorkorder", method = RequestMethod.POST)
    @ApiOperation(value = "删除工单", notes = "删除工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "工单id", required = true, paramType = "query", dataType = "int")
    })
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "工单管理", method = "删除工单")
    public Rjson deleteWorkorder(HttpServletRequest request) throws ServicesException {

        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        try {
            map.put("ID", request.getParameter("ID"));
            String workOrderCode = request.getParameter("workOrderCode");
            if (StringUtil.eqNu(workOrderCode)) {
                // workOrderCode
                List<Map<String, Object>> idAndWorkOrderId = reworkService.findIdAndWorkOrderId(null, workOrderCode);
                if (idAndWorkOrderId.size() > 0) {
                    String id = String.valueOf(idAndWorkOrderId.get(0).get("id"));
                    map.put("ID", id);
                    session.setAttribute("workOrderCode", workOrderCode);
                } else {
                    return Rjson.error("工单不存在");
                }

            } else {
                List<Map<String, Object>> idAndWorkOrderId = reworkService.findIdAndWorkOrderId(Integer.parseInt((String) map.get("ID")), null);
                session.setAttribute("workOrderCode", idAndWorkOrderId.get(0).get("workOrderId"));
            }

            //删除事件
            Map<String, Object> mapOld = service.getByID(map);

            Map<String, Object> mapEvent = new HashMap<>();
            mapEvent.put("OBJECT_TYPE", "工单");
            if ("1".equals(request.getParameter("isScheduling"))) {
                mapEvent.put("OBJECT_TYPE", "排产");
            }
            mapEvent.put("OBJECT_ID", mapOld.get("WORKORDER_ID"));
            mapEvent.put("EVENT", "删除");
            mapEvent.put("PARAMETER1", JSONObject.toJSONString(Rjson.getMapByFormatTime(mapOld)));
            mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
            serviceEvent.addEvent(mapEvent);

            service.deleteWorkorder(map);

            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findListByProID", method = RequestMethod.POST)
    @ApiOperation(value = "根据产品查近期工单", notes = "根据产品查近期工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "PRODUCT_ID", value = "产品id", required = false, paramType = "query", dataType = "int")
    })
    @ResponseBody
    public Rjson findListByProID(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;

        Map<String, Object> map = new HashMap<>();

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -7);

            map.put("PRODUCT_ID", request.getParameter("PRODUCT_ID"));
            map.put("beginTime", c.getTime());
            map.put("endTime", new Date());

            Integer pageNum = 1;
            Integer pageSize = 6;
            if (request.getParameter("pageNum") != null) {
                pageNum = Integer.parseInt(request.getParameter("pageNum"));
            }
            if (request.getParameter("pageSize") != null) {
                pageSize = Integer.parseInt(request.getParameter("pageSize"));
            }

            PageHelper.startPage(pageNum, pageSize);
            list = service.findWorkorderList(map);
            PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findWorkorderByIdOrWID", method = RequestMethod.POST)
    @ApiOperation(value = "根据工单内部id或者工单编号查工单", notes = "根据工单内部id或者工单编号查工单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "WORKORDER_ID", value = "工单编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "ID", value = "工单内部id", required = false, paramType = "query", dataType = "int")
    })
    @ResponseBody
    public Rjson findWorkorderByIdOrWID(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;

        Map<String, Object> map = new HashMap<>();

        try {
            String workorder_id = EqualsUtil.string(request, "WORKORDER_ID", "工单编号", false);
            Integer id = EqualsUtil.integer(request, "ID", "工单内部id", false);
            map.put("WORKORDER_ID", workorder_id);
            map.put("ID", id);
            list = service.findWorkorderByIdOrWID(map);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findPWorkSn", method = RequestMethod.POST)
    @ApiOperation(value = "获取完成工单的条码", notes = "获取完成工单的条码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "工单id", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    public Rjson findPWorkSn(HttpServletRequest request, Integer id) throws ServicesException {
        try {
            List<Map<String, Object>> findWorkSn = service.findWorkSn(id);
            return Rjson.success(findWorkSn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/splitWorkorder", method = RequestMethod.POST)
    @ApiOperation(value = "拆批", notes = "拆批")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "工单id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "num1", value = "拆后数量1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "num2", value = "拆后数量2", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    public Rjson splitWorkorder(HttpServletRequest request) throws ServicesException {
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("ID", request.getParameter("ID"));
            map.put("num1", request.getParameter("num1"));
            map.put("num2", request.getParameter("num2"));

            return service.splitWorkorder(map);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/jointWorkorder", method = RequestMethod.POST)
    @ApiOperation(value = "合批", notes = "合批")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID1", value = "工单id1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "ID2", value = "工单id2", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    public Rjson jointWorkorder(HttpServletRequest request) throws ServicesException {
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("ID1", request.getParameter("ID1"));
            map.put("ID2", request.getParameter("ID2"));

            service.jointWorkorder(map);
            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

	@RequestMapping(value = "/findRecipeList", method = RequestMethod.POST)
	@ApiOperation(value = "查询工单配方列表", notes = "查询工单配方列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "recipeId", value = "工位配方id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson findRecipeList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findRecipeList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

    @RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
	@ApiOperation(value = "新增工单配方", notes = "新增工单配方")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "recipeDetailId", value = "配方明细id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "recipeDetailId", value = "配方明细id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "生产", module2 = "工单管理", method= "新增工单配方")
	public Rjson addRecipe(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.updateRecipe(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

    @RequestMapping(value = "/updateRecipe", method = RequestMethod.POST)
    @ApiOperation(value = "修改工单配方", notes = "修改工单配方")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "recipeDetailId", value = "配方明细id", required = true, paramType = "query"),
    	@ApiImplicitParam(name = "recipeDetailId", value = "配方明细id", required = false, paramType = "query"),
    })
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "工单管理", method= "修改工单配方")
    public Rjson updateRecipe(HttpServletRequest request) throws ServicesException {
    	Map<String, Object> map = new HashMap<>();

    	try {
    		map = ToolUtils.getParameterMap(request);

    		service.updateRecipe(map);

    		return Rjson.success();
    	} catch (Exception e) {
    		e.printStackTrace();
    		ToolUtils.errorLog(this, e, request);
    		return Rjson.error(Constant.INTERFACE_EXCEPTION);
    	}
    }

    @RequestMapping(value = "/deleteRecipe", method = RequestMethod.POST)
    @ApiOperation(value = "删除工单配方", notes = "删除工单配方")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "recipeDetailId", value = "配方明细id", required = true, paramType = "query"),
    })
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "工单管理", method= "删除工单配方")
    public Rjson deleteRecipe(HttpServletRequest request) throws ServicesException {
    	Map<String, Object> map = new HashMap<>();

    	try {
    		map = ToolUtils.getParameterMap(request);

    		service.updateRecipe(map);

    		return Rjson.success();
    	} catch (Exception e) {
    		e.printStackTrace();
    		ToolUtils.errorLog(this, e, request);
    		return Rjson.error(Constant.INTERFACE_EXCEPTION);
    	}
    }
}
