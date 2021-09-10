package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.tools.Tool;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.service.sqq.RouteLineService;
import com.skeqi.mes.util.StringUtil;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.lcy.ProcessRouteService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "工艺路线", description = "工艺路线", produces = MediaType.APPLICATION_JSON)
public class CMesProcessRouteController {

    @Autowired
    ProcessRouteService service;

    @Autowired
    CMesProductionService cMesProductionService;
    @Autowired
    CMesLineTService cMesLineTService;
    @Autowired
    RouteLineService routeLineService;
    @Autowired
    CMesStationTService stationService;

    // 初始化产线
    @RequestMapping(value = "processRouteLine", method = RequestMethod.POST)
    @ApiOperation(value = "初始化产线", notes = "初始化产线")
    public JSONObject getInitProcessRouteLine() {
        JSONObject jo = new JSONObject();
        List<CMesLineT> lineList = service.getLineList();
        jo.put("lineList", lineList);
        return jo;
    }

    // 初始化产品
    @RequestMapping(value = "getProduction", method = RequestMethod.POST)
    @ApiOperation(value = "初始化产品", notes = "初始化产品")
    public JSONObject getInitProcessProduction() {
        JSONObject jo = new JSONObject();

        List<CMesProductionT> productionList = service.getProductionByLineId();
        jo.put("productionList", productionList);
        return jo;
    }

    // 查询工艺ID和名称
    @RequestMapping(value = "processRouteName", method = RequestMethod.POST)
    @ApiOperation(value = "查询工艺ID和名称", notes = "查询工艺ID和名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processlineId", value = "产线Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query")})
    public JSONObject getRoutingBylineId(String processlineId, String productionId) {
        JSONObject jo = new JSONObject();
        List<CMesRoutingT> routingList = service.getRoutingByLineID(processlineId, productionId);
        jo.put("routingList", routingList);
        return jo;
    }

    @RequestMapping(value = "queryProcessRoute", method = RequestMethod.POST)
    @ApiOperation(value = "查询有无工艺路线", notes = "查询有无工艺路线")
    @ApiImplicitParams({@ApiImplicitParam(name = "lineId", value = "产线Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "routingId", value = "工艺路线id", required = false, paramType = "query")})
    public JSONObject queryProcessRoute(String lineId, String routingId, String productionId) {
        JSONObject jo = new JSONObject();

        // 根据产线和产品查询有无工艺路线
        // 查询工艺路线 routing 表
        int routingNumber = service.queryProcessRouteRoutingNumber(lineId, routingId, productionId);
        // 查询工艺路线 production_way 表
        int wayNumber = service.queryProcessRouteWayNumber(routingId);

        List<CMesStationT> stationList = service.queryStationList(lineId);
        if (stationList.size() == 0) {
            jo.put("flag", 2);
            jo.put("msg", "该产品没有工位,请添加后再配置");
            return jo;
        }
        if (routingNumber != 0 && wayNumber != 0) {

            jo.put("flag", 1);
            CMesRoutingT data = service.queryProcessRouteRoutingData(lineId, routingId);
            jo.put("data", data);
        } else if (routingNumber == 0 || wayNumber == 0) {
            jo.put("flag", 0);
            jo.put("msg", "该产品没有配置工艺路线");
            CMesRoutingT data = new CMesRoutingT();
            data.setRoute(getProcessRouteJson(stationList));
            jo.put("data", data);
        } else {
            jo.put("flag", 0);
            jo.put("msg", "该产品工艺路线存在问题,请重新配置");
            jo.put("data", getProcessRouteJson(stationList));
        }
        return jo;
    }

    @RequestMapping(value = "saveProcessRoute", method = RequestMethod.POST)
    @ApiOperation(value = "保存工艺路线", notes = "保存工艺路线")
    @ApiImplicitParams({@ApiImplicitParam(name = "lineId", value = "产线Id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productionCode", value = "产品Code", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "routingName", value = "工艺名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "routingNameOld", value = "工艺名称(修改前)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineList", value = "线列表", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nodeList", value = "节点列表", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stepList", value = "步序列表", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "routingId", value = "工艺路线id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "defaultRoute", value = "默认路线", required = false, paramType = "query", dataType = "int")
    })
    @OptionalLog(module = "生产", module2 = "工艺路线", method = "保存工艺路线")
    public Rjson saveProcessRoute(HttpServletRequest request) {
        Map<String, Object> mapResult = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        try {
            map.put("LINE_ID", request.getParameter("lineId"));
            map.put("LINE_CODE", request.getParameter("lineCode"));
            map.put("PRODUCTION_CODE", request.getParameter("productionCode"));
            map.put("PRODUCTION_ID", request.getParameter("productionId"));
            map.put("NAME", request.getParameter("routingName"));
            map.put("routingNameOld", request.getParameter("routingNameOld"));
            map.put("lineList", request.getParameter("lineList"));
            map.put("nodeList", request.getParameter("nodeList"));
            map.put("stepList", request.getParameter("stepList"));
            map.put("ID", request.getParameter("routingId"));
            map.put("defaultRoute", request.getParameter("defaultRoute"));
            // 产品Code
            String proCode = (String) map.get("PRODUCTION_CODE");
            if (StringUtil.eqNu(proCode)) {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(null, proCode);
                if (null != productionT) {
                    map.put("PRODUCTION_ID", productionT.getId());
                    session.setAttribute("productionCode", proCode);
                } else {
                    return Rjson.error("产品不存在");
                }
            } else {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(Integer.parseInt((String) map.get("PRODUCTION_ID")), null);
                if (null != productionT) {
                    session.setAttribute("productionCode", productionT.getProductionName());
                } else {
                    return Rjson.error("产品不存在");
                }
            }
            CMesLineT line = null;
            // 产线Code
            String lineCode = (String) map.get("LINE_CODE");
            System.err.println(lineCode);
            if (StringUtil.eqNu(lineCode)) {
                line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    map.put("LINE_ID", lineMap.get("ID"));
                    session.setAttribute("lineCode", lineCode);
                } else {
                    return Rjson.error("产线不存在");
                }

            } else {
                line = new CMesLineT();
                line.setId(Integer.parseInt((String) map.get("LINE_ID")));
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if (allLine.size() > 0) {
                    session.setAttribute("lineCode", allLine.get(0).getName());
                } else {
                    return Rjson.error("产线不存在");
                }
            }
            // 工位id外部标识
            String lineListCode = request.getParameter("lineListCode");
            String stepListCode = request.getParameter("stepListCode");
            String nodeListCode = request.getParameter("nodeListCode");
            if (StringUtil.eqNu(lineListCode) && StringUtil.eqNu(stepListCode) && StringUtil.eqNu(nodeListCode)) {

                // 先将nodeListCode中的name独立存储 防止被替换处理
                List<String> lists = new ArrayList<>();

                JSONArray nodeListCodes = JSONArray.parseArray(nodeListCode);
                for (int i = 2; i < nodeListCodes.size(); i++) {
                    JSONObject object = nodeListCodes.getJSONObject(i);
                    lists.add(String.valueOf(object.get("name")));
                }
                // 转换成stationId
                JSONArray objects = JSONArray.parseArray(lineListCode);
                // lineList
                // 控制循环第一次不进行重新获取工位名称
                int count = 0;
                // 存储to的值
                String toValue = "";
                for (int i = 0; i < objects.size() - 1; i++) {
                    String toName = "";
                    JSONObject object = objects.getJSONObject(i);
                    // 获取工位id的值 一致则替换  否则重新获取工位名称并替换
                    String to = object.getString("to");
                    // 过滤数字参数
                    if (ToolUtils.isNumeric(to)) {
                        continue;
                    }
                    if (count > 0) {
                        if (to.equals(toValue)) {
                            // 工位名相同
                            lineListCode = lineListCode.replaceAll(to, toName);
                        } else {

                            // 重新获取工位名称
                            CMesStationT cMesStationT = new CMesStationT();
                            cMesStationT.setStationName(to);
                            line = new CMesLineT();
                            line.setName(lineCode);
                            Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                            if (lineMap.size() > 0) {
                                cMesStationT.setLineId((Integer) lineMap.get("ID"));
                            } else {
                                return Rjson.error("产线不存在");
                            }
                            List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                            if (stationNameAndId.size() > 0) {
                                toName = String.valueOf(stationNameAndId.get(0).getId());
                                // 赋值最新工位id
                                toValue = to;
                                lineListCode = lineListCode.replaceAll(to, toName);
                            } else {
                                return Rjson.error("lineListCode数据中工位不存在");
                            }

                        }
                        // 这里else只有第一次执行
                    } else {
                        // 获取工位id
                        CMesStationT cMesStationT = new CMesStationT();
                        cMesStationT.setStationName(to);
                        line = new CMesLineT();
                        line.setName(lineCode);
                        Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                        if (lineMap.size() > 0) {
                            cMesStationT.setLineId((Integer) lineMap.get("ID"));
                        } else {
                            return Rjson.error("产线不存在");
                        }

                        List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                        if (stationNameAndId.size() > 0) {
                            toName = String.valueOf(stationNameAndId.get(0).getId());
                            // 赋值最新工位id
                            toValue = to;
                            lineListCode = lineListCode.replaceAll(to, toName);
                            count++;
                        } else {
                            return Rjson.error("lineListCode数据中工位不存在");
                        }

                    }
                }
                JSONArray objectStepList = JSONArray.parseArray(stepListCode);
                // stepList
                for (int i = 0; i < objectStepList.size(); i++) {
                    String stationCode = "";
                    JSONObject object = objectStepList.getJSONObject(i);
                    // 获取工位id的值
                    String stationId = object.getString("stationId");
                    // 获取工位名称
                    CMesStationT cMesStationT = new CMesStationT();
                    cMesStationT.setStationName(stationId);
                    line = new CMesLineT();
                    line.setName(lineCode);
                    Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                    if (lineMap.size() > 0) {
                        cMesStationT.setLineId((Integer) lineMap.get("ID"));
                    } else {
                        return Rjson.error("产线不存在");
                    }
                    List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                    if (stationNameAndId.size() > 0) {
                        stationCode = String.valueOf(stationNameAndId.get(0).getId());
                        stepListCode = stepListCode.replaceAll(stationId, stationCode);
                    } else {
                        return Rjson.error("stepListCode数据中工位不存在");
                    }

                }

                // nodeList
                JSONArray objectNodeList = JSONArray.parseArray(nodeListCode);
                // 控制循环第一次不进行重新获取工位名称
                count = 0;
                // 存储to的值
                String stationValue = "";
                for (int i = 2; i < objectNodeList.size(); i++) {
                    String toName = "";
                    JSONObject object = objectNodeList.getJSONObject(i);
                    // 获取工位id的值 一致则替换  否则重新获取工位名称并替换 "id":"376" "stationId":"376" "type":"node377"
                    String id = object.getString("id");
                    String stationId = object.getString("stationId");
                    String type = object.getString("type");
                    if (count > 0) {
                        if (id.equals(stationValue)) {
                            // 工位名相同
                            //  "id":"376"
                           String idV='"'+"id\":"+'"'+id+'"';
                           String idR='"'+"id\":"+'"'+toName+'"';
                           nodeListCode = nodeListCode.replaceAll(idV, idR);
                            //  "stationId":"376"
                            String stationIdV='"'+"stationId\":"+'"'+stationId+'"';
                            String stationIdR='"'+"stationId\":"+'"'+toName+'"';
                            System.out.println("stationIdV"+stationIdV);
                            System.out.println("stationIdR"+stationIdR);
                            nodeListCode = nodeListCode.replaceAll(stationIdV, stationIdR);
                            //  "type":"node377"
                            String typeV='"'+"type\":"+'"'+type+'"';
                            String typeR='"'+"type\":"+'"'+"node"+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(typeV, typeR);

//                            nodeListCode = nodeListCode.replaceAll(id, toName);
                            // name的值转换处理  "name":"OP_02"
                            String name = object.getString("name");
                            String nameValue='"'+"name\":"+'"'+name+'"';
                            String nameR='"'+"name\":"+'"'+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(nameR, nameValue);
                        } else {
                            // 重新获取工位名称
                            CMesStationT cMesStationT = new CMesStationT();
                            cMesStationT.setStationName(id);
                            line = new CMesLineT();
                            line.setName(lineCode);
                            Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                            if (lineMap.size() > 0) {
                                cMesStationT.setLineId((Integer) lineMap.get("ID"));
                            } else {
                                return Rjson.error("产线不存在");
                            }

                            List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                            if (stationNameAndId.size() > 0) {
                                toName = String.valueOf(stationNameAndId.get(0).getId());
                                // 赋值最新工位id
                                stationValue = id;
                                //  "id":"376"
                                String idV='"'+"id\":"+'"'+id+'"';
                                String idR='"'+"id\":"+'"'+toName+'"';
                                nodeListCode = nodeListCode.replaceAll(idV, idR);
                                //  "stationId":"376"
                                String stationIdV='"'+"stationId\":"+'"'+stationId+'"';
                                String stationIdR='"'+"stationId\":"+'"'+toName+'"';
                                nodeListCode = nodeListCode.replaceAll(stationIdV, stationIdR);
                                //  "type":"node377"
                                String typeV='"'+"type\":"+'"'+type+'"';
                                String typeR='"'+"type\":"+'"'+"node"+toName+'"';
                                nodeListCode = nodeListCode.replaceAll(typeV, typeR);
                                // name的值转换处理  "name":"OP_02"
                                String name = object.getString("name");
                                String  nameValue='"'+"name\":"+'"'+name+'"';
                                String nameR='"'+"name\":"+'"'+toName+'"';
                                nodeListCode = nodeListCode.replaceAll(nameR, nameValue);
                            } else {
                                return Rjson.error("nodeListCode数据中工位不存在");
                            }
                        }
                        // 这里else只有第一次执行
                    } else {
                        // 重新获取工位名称
                        CMesStationT cMesStationT = new CMesStationT();
                        cMesStationT.setStationName(id);
                        line = new CMesLineT();
                        line.setName(lineCode);
                        Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                        if (lineMap.size() > 0) {
                            cMesStationT.setLineId((Integer) lineMap.get("ID"));
                        } else {
                            return Rjson.error("产线不存在");
                        }
                        List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                        if (stationNameAndId.size() > 0) {
                            toName = String.valueOf(stationNameAndId.get(0).getId());
                            // 赋值最新工位id
                            stationValue = id;
                            //  "id":"376"
                            String idV='"'+"id\":"+'"'+id+'"';
                            String idR='"'+"id\":"+'"'+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(idV, idR);
                            //  "stationId":"376"
                            String stationIdV='"'+"stationId\":"+'"'+stationId+'"';
                            String stationIdR='"'+"stationId\":"+'"'+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(stationIdV, stationIdR);
                            //  "type":"node377"
                            String typeV='"'+"type\":"+'"'+type+'"';
                            String typeR='"'+"type\":"+'"'+"node"+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(typeV, typeR);
                            // name的值转换处理  "name":"OP_02"
                            String name = object.getString("name");
                            String nameValue='"'+"name\":"+'"'+name+'"';
                            String nameR='"'+"name\":"+'"'+toName+'"';
                            nodeListCode = nodeListCode.replaceAll(nameR, nameValue);
                            count++;
                        } else {
                            return Rjson.error("nodeListCode数据中工位不存在");
                        }

                    }
                }
                map.put("lineList", lineListCode);
                map.put("stepList", stepListCode);
                map.put("nodeList",nodeListCode);
                // 将转换后的id赋值
                session.setAttribute("lineListCode", request.getParameter("lineListCode"));
                session.setAttribute("nodeListCode", request.getParameter("nodeListCode"));
                session.setAttribute("stepListCode", request.getParameter("stepListCode"));
            } else {
                // 转换成stationName
                String lineList = (String) map.get("lineList");
                String stepList = (String) map.get("stepList");
                String nodeList = (String) map.get("nodeList");
                JSONArray objects = JSONArray.parseArray(lineList);
                // lineList
                // 控制循环第一次不进行重新获取工位名称
                int count = 0;
                // 存储to的值
                String toValue = "";
                for (int i = 0; i < objects.size() - 1; i++) {
                    String toName = "";
                    JSONObject object = objects.getJSONObject(i);
                    // 获取工位id的值 一致则替换  否则重新获取工位名称并替换
                    String to = object.getString("to");
                    if (!ToolUtils.isNumeric(to)) {
                        continue;
                    }
                    if (count > 0) {
                        if (to.equals(toValue)) {
                            // 工位名相同
                            lineList = lineList.replaceAll(to, toName);
                        } else {
                            // 重新获取工位名称
                            CMesStationT cMesStationT = new CMesStationT();
                            cMesStationT.setId(Integer.parseInt(to));
                            List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                            if (stationNameAndId.size() > 0) {
                                toName = stationNameAndId.get(0).getStationName();
                                // 赋值最新工位id
                                toValue = to;
                                lineList = lineList.replaceAll(to, toName);
                            } else {
                                return Rjson.error("lineListCode数据中工位不存在");
                            }

                        }
                        // 这里else只有第一次执行
                    } else {
                        // 重新获取工位名称
                        CMesStationT cMesStationT = new CMesStationT();
                        cMesStationT.setId(Integer.parseInt(to));
                        List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                        if (stationNameAndId.size() > 0) {
                            toName = stationNameAndId.get(0).getStationName();
                            // 赋值最新工位id
                            toValue = to;
                            lineList = lineList.replaceAll(to, toName);
                            count++;
                        } else {
                            return Rjson.error("lineListCode数据中工位不存在");
                        }

                    }
                }
                JSONArray objectStepList = JSONArray.parseArray(stepList);
                // stepList
                for (int i = 0; i < objectStepList.size(); i++) {
                    String stationCode = "";
                    JSONObject object = objectStepList.getJSONObject(i);
                    // 获取工位id的值
                    String stationId = object.getString("stationId");
                    // 获取工位名称
                    CMesStationT cMesStationT = new CMesStationT();
                    cMesStationT.setId(Integer.parseInt(stationId));
                    List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                    if (stationNameAndId.size() > 0) {
                        stationCode = stationNameAndId.get(0).getStationName();
                        stepList = stepList.replaceAll(stationId, stationCode);
                    } else {
                        return Rjson.error("stepListCode数据中工位不存在");
                    }

                }

                // nodeList
                JSONArray objectNodeList = JSONArray.parseArray(nodeList);
                // 控制循环第一次不进行重新获取工位名称
                count = 0;
                // 存储to的值
                String stationValue = "";
                for (int i = 2; i < objectNodeList.size(); i++) {
                    String toName = "";
                    JSONObject object = objectNodeList.getJSONObject(i);
                    // 获取工位id的值 一致则替换  否则重新获取工位名称并替换
                    String id = object.getString("id");
                    if (!ToolUtils.isNumeric(id)) {
                        continue;
                    }
                    if (count > 0) {
                        if (id.equals(stationValue)) {
                            // 工位名相同
                            nodeList = nodeList.replaceAll(id, toName);
                        } else {
                            // 重新获取工位名称
                            CMesStationT cMesStationT = new CMesStationT();
                            cMesStationT.setId(Integer.parseInt(id));
                            List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                            if (stationNameAndId.size() > 0) {
                                toName = stationNameAndId.get(0).getStationName();
                                // 赋值最新工位id
                                stationValue = id;
                                nodeList = nodeList.replaceAll(id, toName);
                            } else {
                                return Rjson.error("nodeListCode数据中工位不存在");
                            }

                        }
                        // 这里else只有第一次执行
                    } else {
                        // 重新获取工位名称
                        CMesStationT cMesStationT = new CMesStationT();
                        cMesStationT.setId(Integer.parseInt(id));
                        List<CMesStationT> stationNameAndId = stationService.findStationNameAndId(cMesStationT);
                        if (stationNameAndId.size() > 0) {
                            toName = stationNameAndId.get(0).getStationName();
                            // 赋值最新工位id
                            stationValue = id;
                            nodeList = nodeList.replaceAll(id, toName);
                            count++;
                        } else {
                            return Rjson.error("nodeListCode数据中工位不存在");
                        }

                    }
                }
                session.setAttribute("lineListCode", lineList);
                session.setAttribute("nodeListCode", nodeList);
                session.setAttribute("stepListCode", stepList);
            }


            if (StringUtils.isEmpty(map.get("ID"))) {
                if (StringUtils.isEmpty(map.get("PRODUCTION_ID"))) {
                    return Rjson.error("产品id不能为空");
                }
                if (StringUtils.isEmpty(map.get("LINE_ID"))) {
                    return Rjson.error("产线id不能为空");
                }
                Integer num = service.getRoutingCountByName(map);
                if (num > 0) {
                    return Rjson.error("工艺路线名称重复");
                }
                if (Integer.parseInt(map.get("defaultRoute").toString()) == 1 && service.findDefaultRouting(map) > 0) {
                    return Rjson.error("此产品和产线已存在默认工艺路线");
                }
                mapResult.put("routingId", service.addRouting(map));
                mapResult.put("requestType", "1");
            } else {
                if (!map.get("NAME").toString().equals(map.get("routingNameOld"))) {
                    Integer num = service.getRoutingCountByName(map);
                    if (num > 0) {
                        return Rjson.error("工艺路线名称重复");
                    }
                }
                Integer defaultRouting = service.findDefaultRouting(map);
                if (Integer.parseInt(map.get("defaultRoute").toString()) == 1 && defaultRouting > 0 && Integer.parseInt(map.get("ID").toString()) != defaultRouting) {
                    return Rjson.error("此产品和产线已存在默认工艺路线");
                }
                service.updateRouting(map);
                mapResult.put("routingId", map.get("ID"));
                mapResult.put("requestType", "2");
            }

            return Rjson.success(mapResult);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/getRouting", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取工艺路线", notes = "根据ID获取工艺路线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routingId", value = "工艺路线id", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    public Rjson getRouting(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("ID", request.getParameter("routingId"));
            list = service.getRouting(map);
            if (list == null || list.size() == 0) {
                return Rjson.error("未查到工艺路线！");
            } else {
                return Rjson.success(list.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "/findRoutingList", method = RequestMethod.POST)
    @ApiOperation(value = "查询工艺路线列表", notes = "查询工艺路线列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lineId", value = "产线Id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query", dataType = "int"),
    })
    @ResponseBody
    public Rjson findRoutingList(HttpServletRequest request) throws ServicesException {

        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("LINE_ID", request.getParameter("lineId"));
            map.put("PRODUCTION_ID", request.getParameter("productionId"));
            list = service.findRoutingList(map);

            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "deleteProcessRoute", method = RequestMethod.POST)
    @ApiOperation(value = "删除工艺路线", notes = "删除工艺路线")
    @ApiImplicitParams({@ApiImplicitParam(name = "routingId", value = "工艺id", required = false, paramType = "query", dataType = "int"),})
    @OptionalLog(module = "生产", module2 = "工艺路线", method = "删除工艺路线")
    public Rjson deleteProcessRoute(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("ID", request.getParameter("routingId"));
            HttpSession session = request.getSession();
            String name = request.getParameter("routingCode");
            String lineCode = request.getParameter("lineCode");
            // 获取工艺路线外部ID
            if (StringUtil.eqNu(name) && StringUtil.eqNu(lineCode)) {
                // 获取产线id
                CMesLineT line = new CMesLineT();
                line.setName(lineCode);
                CMesLineT lineByLineIdAndName = cMesLineTService.getLineByLineIdAndName(line);
                if (null != lineByLineIdAndName) {
                    List<JSONObject> routingIdAndName = routeLineService.findRoutingIdAndName(null, name, lineByLineIdAndName.getId());
                    if(routingIdAndName.size()>0){
                        map.put("ID", routingIdAndName.get(0).get("id"));
                        session.setAttribute("routingCode", name);
                        session.setAttribute("lineCode", lineCode);
                    }else {
                        return Rjson.error("产线不存在");
                    }

                } else {
                    return Rjson.error("产线不存在");
                }

            } else {
                List<JSONObject> routingIdAndName = routeLineService.findRoutingIdAndName(Integer.parseInt((String) map.get("ID")), null, null);
                if (routingIdAndName.size() > 0) {
                    session.setAttribute("routingCode", routingIdAndName.get(0).get("name"));
                    CMesLineT line = new CMesLineT();
                    line.setId(Integer.parseInt((String) routingIdAndName.get(0).get("lineId")));
                    List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                    session.setAttribute("lineCode", allLine.get(0).getName());
                } else {
                    return Rjson.error("产线不存在");
                }
            }
            service.deleteRouting(map);
            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    // 获取工位初始化字符串
    public static String getProcessRouteJson(List<CMesStationT> stationList) {
        StringBuilder states = new StringBuilder(
                "{states:{rect1:{type:'start',text:{text:'开始'}, attr:{ x:50, y:260, width:50, height:50}, props:{text:{value:'开始'},temp1:{value:'start'},temp2:{value:'工艺路线的开始'}}},");
        for (int i = 1; i <= stationList.size(); i++) {

            states.append("rect" + (i + 1) + ":{type:'task',text:{text:'" + stationList.get(i - 1).getStationName()
                    + "'}, attr:{ x:" + (150 + (((i - 1) % 7) * 150)) + ", y:" + (260 + (((i - 1) / 7) * 100))
                    + ", width:100, height:50}, props:{text:{value:'" + stationList.get(i - 1).getStationName()
                    + "'},assignee:{value:'" + stationList.get(i - 1).getId() + "'},form:{value:'"
                    + stationList.get(i - 1).getStationIndex() + "'},desc:{value:'普通工位'}}},");
        }
        states.append("rect" + (stationList.size() + 2)
                + ":{type:'end',text:{text:'结束'}, attr:{ x:50, y:360, width:50, height:50}, props:{text:{value:'结束'},temp1:{value:'end'},temp2:{value:'工艺路线的结束'}}}},paths:{},props:{props:{}}}");
        return states.toString();
    }

    @RequestMapping(value = "updateDefaultProcess", method = RequestMethod.POST)
    @ApiOperation(value = "修改工艺路线", notes = "修改工艺路线")
    @ApiImplicitParams({@ApiImplicitParam(name = "lineId", value = "产线Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "routingId", value = "工艺id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query"),
    })
    @OptionalLog(module = "生产", module2 = "工艺路线", method = "修改工艺路线")
    public JSONObject updateDefaultProcess(String routingId, String lineId, String productionId) {
        JSONObject jo = new JSONObject();
        try {
            if (routingId != null && !"".equals(routingId)) {
                service.updateDefaultProcess(routingId, lineId, productionId);
                jo.put("flag", 1);
                jo.put("msg", "更新默认路线成功");
            } else {
                jo.put("flag", 0);
                jo.put("msg", "更新默认路线失败,请重新查询后再进行更新");
            }
        } catch (Exception e) {
            jo.put("flag", 0);
            jo.put("msg", "更新默认路线失败");
        }
        return jo;
    }

}
