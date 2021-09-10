package com.skeqi.mes.controller.zch;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.zch.AssembleBoltService;
import com.skeqi.mes.service.zch.AssembleMaterialService;
import com.skeqi.mes.service.zch.CheckSnService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.zch.NextBarcodeService;
import com.skeqi.mes.service.zch.UpdateSnService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.LogUtils;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.aop.OptionalLog;

@RestController
@RequestMapping(value = "qhapi", produces = MediaType.APPLICATION_JSON)
public class ApiController {

    @Autowired
    CheckSnService checkSnService;
    @Autowired
    EventService eventService;
//    @Autowired
//    CMesWebApiLogsService logsService;
    @Autowired
    UpdateSnService updateService;
    @Autowired
    AssembleBoltService assembleBoltService;
    @Autowired
    AssembleMaterialService assembleMaterialService;
    @Autowired
    NextBarcodeService nextBarcodeService;
    @Autowired
    CMesLineTService cMesLineService;


    @Transactional
    @RequestMapping(value = "checkSN", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "扫描总成")
    public synchronized void checkSN(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");
        JSONObject jo = new JSONObject();

        jo.put("productionId", "");
        jo.put("stepList", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("checkSN");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        try {
            // 将接受到的字符串转换为json数组
            JSONObject json = JSONObject.parseObject(str);
            // 获取总成码
            String sn = json.getString("sn");
            // 获取工位名称
            String station = json.getString("station");
            // 获取产线名称
            String line = json.getString("line");
            Boolean getStationRecipe = json.getBoolean("getStationRecipe");
            if (getStationRecipe == null) {
                getStationRecipe = false;
            }
            dx.setSn(sn);

            if (sn.isEmpty() || station.isEmpty() || line.isEmpty()) {
                jo.put("isSuccess", false);
                jo.put("code", "202");
                jo.put("errMsg", "参数缺失");
            } else {
                jo = checkSnService.checkSN(sn, station, line, getStationRecipe);

                System.out.println("resultJson: " + jo.toJSONString());

                // 事件添加
                if (jo.getBoolean("isSuccess")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("NAME", line);
                    Map<String, Object> mapResult = eventService.getLineCode(map);
                    Map<String, Object> mapEvent = new HashMap<>();
                    mapEvent.put("OBJECT_TYPE", "成品");
                    mapEvent.put("OBJECT_ID", sn);
                    mapEvent.put("EVENT", "扫码上线");
                    if (mapResult != null) {
                        mapEvent.put("PARAMETER1", mapResult.get("code"));
                    }
                    mapEvent.put("PARAMETER2", station);
                    eventService.addEvent(mapEvent);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", "出现异常");

        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            LogUtils.writeApiLog(dx);

            if (!jo.getBooleanValue("isSuccess")) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "updateSn", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "下线")
    public synchronized void updateSnP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String snBarconde;
        String stationname;
        String lineName;
        response.setContentType("application/json");
        JSONObject jo = new JSONObject();

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("updateSnP");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {
            snBarconde = json.get("sn").toString();
            stationname = json.get("station").toString();
            lineName = json.get("line").toString();
            dx.setSn(snBarconde);
        } catch (Exception e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = updateService.updateSN(snBarconde, stationname, lineName);

            // 事件添加
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "成品");
                mapEvent.put("OBJECT_ID", snBarconde);
                mapEvent.put("EVENT", "总成下线");
                eventService.addEvent(mapEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());

        } finally {

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            LogUtils.writeApiLog(dx);

            if (!jo.getBooleanValue("isSuccess")) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "getBolt", method = RequestMethod.POST)
    public synchronized void getBolt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String snBarcode;// 总成码
        String lineName;// 产线名称
        Integer stepNo;// 步序
        String stationBoltName;
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("getBolt");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        jo.put("boltName", "");
        jo.put("boltNumber", 0);
        jo.put("remainNumber", 0);

        try {

            snBarcode = json.get("sn").toString();
            lineName = json.get("line").toString();
            stepNo = Integer.parseInt(json.get("step").toString());
            stationBoltName = json.get("station").toString();
            dx.setSn(snBarcode);
        } catch (NullPointerException e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = assembleBoltService.getBolt(snBarcode, lineName, stepNo, stationBoltName);

            // 事件添加
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "成品");
                mapEvent.put("OBJECT_ID", snBarcode);
                mapEvent.put("EVENT", "检验螺栓");
                if (mapResult != null) {
                    mapEvent.put("PARAMETER1", mapResult.get("code"));
                }
                mapEvent.put("PARAMETER2", stationBoltName);
                mapEvent.put("PARAMETER3", stepNo);
                eventService.addEvent(mapEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "assembleBolt", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "拧紧")
    public synchronized void assembleBolt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        // 获取总称码
        String snBarcode;
        // 获取角度值
        String aValues;
        // 获取扭矩值
        String tValues;
        // 获取拧紧结果
        String rValues;
        // 获取产线名字
        String lineName;
        // 获取步序
        Integer stepNo;
        // 工位名称
        String stationBoltName;
        // 员工号
        String emp;
        // 批次号
        Object batchCode;
        //设备
//		String eqName;
        response.setContentType("application/json");

        jo.put("reworkTimesFlag", "");
        jo.put("remainNumber", "");
        jo.put("boltName", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("assembleBolt");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {
            snBarcode = json.get("sn").toString();
            aValues = json.get("a").toString();
            tValues = json.get("t").toString();
            rValues = json.get("r").toString();
            lineName = json.get("line").toString();
            stepNo = Integer.parseInt(json.get("step").toString());
            stationBoltName = json.get("station").toString();
            emp = json.get("emp").toString();
            batchCode = json.get("batchCode");

//			eqName = json.get("eqName").toString();
            dx.setSn(snBarcode);

        } catch (NullPointerException e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = assembleBoltService.assembleBolt(snBarcode, aValues, tValues, rValues, lineName, stepNo, stationBoltName, emp, batchCode);

            // 事件添加
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                map.put("snBarcode", snBarcode);
                map.put("stationName", stationBoltName);
                map.put("snBarcode", stationBoltName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapResultBolt = eventService.getMaterialId(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "成品");
                mapEvent.put("OBJECT_ID", snBarcode);
                mapEvent.put("EVENT", "拧紧螺栓");
                if (mapResult != null) {
                    mapEvent.put("PARAMETER1", mapResult.get("code"));
                }
                mapEvent.put("PARAMETER2", stationBoltName);
                mapEvent.put("PARAMETER3", stepNo);
                if (mapResultBolt != null) {
                    mapEvent.put("PARAMETER4", mapResultBolt.get("MATERIAL_INSTANCE_ID"));
                }
                eventService.addEvent(mapEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "checkMaterial", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "检验物料")
    public synchronized void checkMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String sn;// 总成码
        String lineName;// 产线名称
        Integer stepNo;// 步序
        String stationName;
        String barcode;
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("checkMaterial");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {

            sn = json.get("sn").toString();
            lineName = json.get("line").toString();
            stepNo = Integer.parseInt(json.get("step").toString());
            stationName = json.get("station").toString();
            barcode = json.get("barcode").toString();
            dx.setSn(sn);
        } catch (NullPointerException e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = assembleMaterialService.checkMaterial(sn, lineName, stepNo, stationName, barcode);

            // 事件添加
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "成品");
                mapEvent.put("OBJECT_ID", sn);
                mapEvent.put("EVENT", "检验物料");
                if (mapResult != null) {
                    mapEvent.put("PARAMETER1", mapResult.get("code"));
                }
                mapEvent.put("PARAMETER2", stationName);
                mapEvent.put("PARAMETER3", stepNo);
                eventService.addEvent(mapEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "assembleKeypart", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "装配物料")
    public synchronized void assembleKeypart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String sn;// 总成码
        String barcode; // 物料条码
        String stationName; // 工位名称
        String materialName;// 物料名称
        String emp; // 员工号
        String lineName;// 产线名称
        Integer stepNo;// 步序
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("assembleKeypart");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {

            // 获取总成号
            sn = json.getString("sn");
            // 获取物料条码
            barcode = json.getString("barcode");
            // 获取物料名称
            materialName = json.getString("materialName");
            // 获取工位名称
            stationName = json.getString("station");
            // 获取员工号
            emp = json.getString("emp");
            // 获取产线
            lineName = json.getString("line");
            // 获取步序
            stepNo = json.getInteger("step");
            dx.setSn(sn);
        } catch (NullPointerException e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = assembleMaterialService.assembleKeypart(sn, barcode, materialName, stationName, emp, lineName, stepNo);

            // 事件添加
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("sn", sn);
                map.put("materialName", materialName);
                map.put("station", stationName);
                Map<String, Object> mapResult = eventService.getKeyPartMaterialId(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "成品");
                mapEvent.put("OBJECT_ID", barcode);
                mapEvent.put("EVENT", "装配物料");
                mapEvent.put("PARAMETER2", stationName);
                if (mapResult != null) {
                    mapEvent.put("PARAMETER4", map.get("MATERIAL_INSTANCE_ID"));
                }
                eventService.addEvent(mapEvent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }
    }

    @Transactional
    @RequestMapping(value = "nextBarcode", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "生产模拟", method = "生成条码")
    public synchronized void nextBarcode(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        Integer lineId = null;//产线id
        Integer num = null;//数量
        String lineName = null;//产线名称
        String printFlag = null; //是否生成后直接打印 1 是 0 否

        jo.put("barcode", "");
        jo.put("barcodeNext", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("nextBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        HttpSession session = request.getSession();
        // 产线Code
        try {
            String lineCode = String.valueOf(json.get("lineCode"));
            CMesLineT line = null;
            if (StringUtil.eqNu(lineCode) && !lineCode.equals("null")) {
                line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineService.getLineByName(line);
                if (lineMap.size() > 0) {
                    json.put("lineId", lineMap.get("ID"));
                    session.setAttribute("lineCode", lineCode);
                }
            } else {
                line = new CMesLineT();
                line.setId(Integer.parseInt(json.getString("lineId")));
                List<CMesLineT> allLine = cMesLineService.findAllLine(line);
                if (allLine.size() > 0) {
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }
            }
        } catch (ServicesException e) {
            e.printStackTrace();

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            jo.put("isSuccess", false);
            jo.put("errMsg", "产线不存在");
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            /**
             * 生成日志
             */
            try {
                LogUtils.writeApiLog(dx);
            } catch (Exception e2) {
                e2.printStackTrace();
                jo.put("errMsg", jo.getString("errMsg") + "生成日志出错了!");
                System.err.println("生成日志出错了");
            }

            response.getWriter().append(jo.toJSONString());
            return;
        }


        lineId = json.getInteger("lineId");
        lineName = json.getString("lineName");
        printFlag = json.getString("printFlag");
        num = json.getInteger("num");

        try {
            // 调用方法主体
            jo = nextBarcodeService.nextBarcode(lineId, lineName, printFlag, num);

            // 事件添加
            Map<String, Object> map = new HashMap<>();
            map.put("lineId", lineId);
            Map<String, Object> mapResult = eventService.getLineCode(map);
            Map<String, Object> mapEvent = new HashMap<>();
            mapEvent.put("OBJECT_TYPE", "成品");
            if (jo != null && jo.get("result") != null) {
                mapEvent.put("OBJECT_ID", jo.getJSONObject("result").get("barcode"));
            }
            mapEvent.put("EVENT", "生成条码");
            if (mapResult != null) {
                mapEvent.put("PARAMETER1", mapResult.get("code"));
            }
            eventService.addEvent(mapEvent);

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }

    }

    /**
     * 打印条码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException
     */
    @Transactional
    @RequestMapping(value = "printBarcode", method = RequestMethod.POST)
    public synchronized void printBarcode(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        String barcode = null;//SN

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("printBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {
            // 获取总称码
            barcode = json.getString("barcode");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失！");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = nextBarcodeService.printBarcode(barcode);

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }

    }

    /**
     * 扫码员工号
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException
     */
    @Transactional
    @RequestMapping(value = "scanEmp", method = RequestMethod.POST)
    public synchronized void scanEmp(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        // 保存返回数据
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        String emp = null;//员工号
        String lineName = null;//产线名称
        String stationName = null;//工位名称
        String sn = null;//条码
        Integer stepNo = null;//步序

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("printBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // 将接受到的字符串转换为json数组
        JSONObject json = JSONObject.parseObject(str);

        try {
            // 获取总称码
            emp = json.getString("emp");
            lineName = json.getString("line");
            stationName = json.getString("station");
            sn = json.getString("sn");
            stepNo = json.getInteger("step");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失！");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        if (sn.isEmpty() || stationName.isEmpty() || lineName.isEmpty() || emp.isEmpty() || stepNo == null) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "参数缺失");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 调用方法主体
            jo = checkSnService.scanEmp(emp, lineName, stationName, sn, stepNo);

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // 生成日志
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }

    }

    @Transactional
    @RequestMapping(value = "test", method = RequestMethod.POST)
    public synchronized void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        checkSnService.test();
    }
}
