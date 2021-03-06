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
    @OptionalLog(module = "??????", module2 = "????????????", method = "????????????")
    public synchronized void checkSN(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");
        JSONObject jo = new JSONObject();

        jo.put("productionId", "");
        jo.put("stepList", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("checkSN");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        try {
            // ?????????????????????????????????json??????
            JSONObject json = JSONObject.parseObject(str);
            // ???????????????
            String sn = json.getString("sn");
            // ??????????????????
            String station = json.getString("station");
            // ??????????????????
            String line = json.getString("line");
            Boolean getStationRecipe = json.getBoolean("getStationRecipe");
            if (getStationRecipe == null) {
                getStationRecipe = false;
            }
            dx.setSn(sn);

            if (sn.isEmpty() || station.isEmpty() || line.isEmpty()) {
                jo.put("isSuccess", false);
                jo.put("code", "202");
                jo.put("errMsg", "????????????");
            } else {
                jo = checkSnService.checkSN(sn, station, line, getStationRecipe);

                System.out.println("resultJson: " + jo.toJSONString());

                // ????????????
                if (jo.getBoolean("isSuccess")) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("NAME", line);
                    Map<String, Object> mapResult = eventService.getLineCode(map);
                    Map<String, Object> mapEvent = new HashMap<>();
                    mapEvent.put("OBJECT_TYPE", "??????");
                    mapEvent.put("OBJECT_ID", sn);
                    mapEvent.put("EVENT", "????????????");
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
            jo.put("errMsg", "????????????");

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
    @OptionalLog(module = "??????", module2 = "????????????", method = "??????")
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
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("updateSnP");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
        JSONObject json = JSONObject.parseObject(str);

        try {
            snBarconde = json.get("sn").toString();
            stationname = json.get("station").toString();
            lineName = json.get("line").toString();
            dx.setSn(snBarconde);
        } catch (Exception e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = updateService.updateSN(snBarconde, stationname, lineName);

            // ????????????
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "??????");
                mapEvent.put("OBJECT_ID", snBarconde);
                mapEvent.put("EVENT", "????????????");
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
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String snBarcode;// ?????????
        String lineName;// ????????????
        Integer stepNo;// ??????
        String stationBoltName;
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("getBolt");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
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
            jo.put("errMsg", "????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = assembleBoltService.getBolt(snBarcode, lineName, stepNo, stationBoltName);

            // ????????????
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "??????");
                mapEvent.put("OBJECT_ID", snBarcode);
                mapEvent.put("EVENT", "????????????");
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
    @OptionalLog(module = "??????", module2 = "????????????", method = "??????")
    public synchronized void assembleBolt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        // ???????????????
        String snBarcode;
        // ???????????????
        String aValues;
        // ???????????????
        String tValues;
        // ??????????????????
        String rValues;
        // ??????????????????
        String lineName;
        // ????????????
        Integer stepNo;
        // ????????????
        String stationBoltName;
        // ?????????
        String emp;
        // ?????????
        Object batchCode;
        //??????
//		String eqName;
        response.setContentType("application/json");

        jo.put("reworkTimesFlag", "");
        jo.put("remainNumber", "");
        jo.put("boltName", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("assembleBolt");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
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
            jo.put("errMsg", "????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = assembleBoltService.assembleBolt(snBarcode, aValues, tValues, rValues, lineName, stepNo, stationBoltName, emp, batchCode);

            // ????????????
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                map.put("snBarcode", snBarcode);
                map.put("stationName", stationBoltName);
                map.put("snBarcode", stationBoltName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapResultBolt = eventService.getMaterialId(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "??????");
                mapEvent.put("OBJECT_ID", snBarcode);
                mapEvent.put("EVENT", "????????????");
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
    @OptionalLog(module = "??????", module2 = "????????????", method = "????????????")
    public synchronized void checkMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String sn;// ?????????
        String lineName;// ????????????
        Integer stepNo;// ??????
        String stationName;
        String barcode;
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("checkMaterial");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
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
            jo.put("errMsg", "????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = assembleMaterialService.checkMaterial(sn, lineName, stepNo, stationName, barcode);

            // ????????????
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("NAME", lineName);
                Map<String, Object> mapResult = eventService.getLineCode(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "??????");
                mapEvent.put("OBJECT_ID", sn);
                mapEvent.put("EVENT", "????????????");
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
    @OptionalLog(module = "??????", module2 = "????????????", method = "????????????")
    public synchronized void assembleKeypart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        String sn;// ?????????
        String barcode; // ????????????
        String stationName; // ????????????
        String materialName;// ????????????
        String emp; // ?????????
        String lineName;// ????????????
        Integer stepNo;// ??????
        response.setContentType("application/json");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("assembleKeypart");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
        JSONObject json = JSONObject.parseObject(str);

        try {

            // ???????????????
            sn = json.getString("sn");
            // ??????????????????
            barcode = json.getString("barcode");
            // ??????????????????
            materialName = json.getString("materialName");
            // ??????????????????
            stationName = json.getString("station");
            // ???????????????
            emp = json.getString("emp");
            // ????????????
            lineName = json.getString("line");
            // ????????????
            stepNo = json.getInteger("step");
            dx.setSn(sn);
        } catch (NullPointerException e) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = assembleMaterialService.assembleKeypart(sn, barcode, materialName, stationName, emp, lineName, stepNo);

            // ????????????
            if (jo.getBoolean("isSuccess")) {
                Map<String, Object> map = new HashMap<>();
                map.put("sn", sn);
                map.put("materialName", materialName);
                map.put("station", stationName);
                Map<String, Object> mapResult = eventService.getKeyPartMaterialId(map);
                Map<String, Object> mapEvent = new HashMap<>();
                mapEvent.put("OBJECT_TYPE", "??????");
                mapEvent.put("OBJECT_ID", barcode);
                mapEvent.put("EVENT", "????????????");
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
    @OptionalLog(module = "??????", module2 = "????????????", method = "????????????")
    public synchronized void nextBarcode(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        Integer lineId = null;//??????id
        Integer num = null;//??????
        String lineName = null;//????????????
        String printFlag = null; //??????????????????????????? 1 ??? 0 ???

        jo.put("barcode", "");
        jo.put("barcodeNext", "");

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("nextBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
        JSONObject json = JSONObject.parseObject(str);

        HttpSession session = request.getSession();
        // ??????Code
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
            jo.put("errMsg", "???????????????");
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            /**
             * ????????????
             */
            try {
                LogUtils.writeApiLog(dx);
            } catch (Exception e2) {
                e2.printStackTrace();
                jo.put("errMsg", jo.getString("errMsg") + "?????????????????????!");
                System.err.println("?????????????????????");
            }

            response.getWriter().append(jo.toJSONString());
            return;
        }


        lineId = json.getInteger("lineId");
        lineName = json.getString("lineName");
        printFlag = json.getString("printFlag");
        num = json.getInteger("num");

        try {
            // ??????????????????
            jo = nextBarcodeService.nextBarcode(lineId, lineName, printFlag, num);

            // ????????????
            Map<String, Object> map = new HashMap<>();
            map.put("lineId", lineId);
            Map<String, Object> mapResult = eventService.getLineCode(map);
            Map<String, Object> mapEvent = new HashMap<>();
            mapEvent.put("OBJECT_TYPE", "??????");
            if (jo != null && jo.get("result") != null) {
                mapEvent.put("OBJECT_ID", jo.getJSONObject("result").get("barcode"));
            }
            mapEvent.put("EVENT", "????????????");
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
            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }

    }

    /**
     * ????????????
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
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        String barcode = null;//SN

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("printBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
        JSONObject json = JSONObject.parseObject(str);

        try {
            // ???????????????
            barcode = json.getString("barcode");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "???????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = nextBarcodeService.printBarcode(barcode);

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
        }

    }

    /**
     * ???????????????
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
        // ??????????????????
        JSONObject jo = new JSONObject();

        String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");

        String emp = null;//?????????
        String lineName = null;//????????????
        String stationName = null;//????????????
        String sn = null;//??????
        Integer stepNo = null;//??????

        if (str == null || str == "") {
            jo.put("isSuccess", false);
            jo.put("code", "201");
            jo.put("errMsg", "??????????????????");
            response.getWriter().append(jo.toJSONString());
        }

        CMesWebApiLogs dx = new CMesWebApiLogs();
        dx.setApiName("printBarcode");
        dx.setCallTime(DateUtil.getNowDate());
        dx.setParameter(str);

        // ?????????????????????????????????json??????
        JSONObject json = JSONObject.parseObject(str);

        try {
            // ???????????????
            emp = json.getString("emp");
            lineName = json.getString("line");
            stationName = json.getString("station");
            sn = json.getString("sn");
            stepNo = json.getInteger("step");
        } catch (NullPointerException e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "???????????????");

            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());

            // ????????????
            LogUtils.writeApiLog(dx);

            response.getWriter().append(jo.toJSONString());
            return;
        }

        if (sn.isEmpty() || stationName.isEmpty() || lineName.isEmpty() || emp.isEmpty() || stepNo == null) {
            jo.put("isSuccess", false);
            jo.put("code", "202");
            jo.put("errMsg", "????????????");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // ??????????????????
            jo = checkSnService.scanEmp(emp, lineName, stationName, sn, stepNo);

        } catch (Exception e) {
            e.printStackTrace();
            jo.put("isSuccess", false);
            jo.put("code", "999");
            jo.put("errMsg", e.getMessage());
        } finally {
            dx.setReturnResult(jo.toJSONString());
            dx.setReturnTime(DateUtil.getNowDate());
            // ????????????
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
