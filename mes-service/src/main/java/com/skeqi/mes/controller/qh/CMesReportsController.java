package com.skeqi.mes.controller.qh;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.fqz.RecipeVersionService;
import com.skeqi.mes.service.yin.BomService;
import com.skeqi.mes.service.yin.ProductionService;
import com.skeqi.mes.service.yin.ReportService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 电子报表
 *
 * @ClassName: CMesReportController
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "电子报表", description = "电子报表", produces = MediaType.APPLICATION_JSON)
public class CMesReportsController {
    @Autowired
    ReportService reportService;

    @ResponseBody
    @RequestMapping(value = "/report/firstSn", method = RequestMethod.POST)
    public JSONObject findFirstSn(HttpServletRequest request){
        JSONObject out = new JSONObject();
        try {
            out = reportService.firstSn();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 电子报表列表
     */
    @RequestMapping(value = "/report/findList", method = RequestMethod.POST)
    @ApiOperation(value = "电子报表列表", notes = "电子报表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "string"),
    })
    @ResponseBody
    public JSONObject findList(HttpServletRequest request, String sn) throws ServicesException {
        JSONObject json = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);
//		map.put("status", 0);
        try {
            List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
            if (ptrackingList.size() > 0) {
                json.put("startDate", ptrackingList.get(0).getDt());//上线时间
                json.put("endDate", ptrackingList.get(0).getOfflineDt());//下线时间
                json.put("typeName", ptrackingList.get(0).getProductionName());//产品类型
                json.put("productionName",reportService.getProductionName(ptrackingList.get(0).getProductionId()));
                json.put("repairMark",ptrackingList.get(0).getReworkFlag());
            } else {
                List<RTrackingT> rtrackingList = reportService.rtrackingList(map);
                if (rtrackingList.size() > 0) {
                    json.put("startDate", rtrackingList.get(0).getDT());//上线时间
                    json.put("typeName", rtrackingList.get(0).getProductionName());//产品类型
                    json.put("productionName",reportService.getProductionName(rtrackingList.get(0).getProductionId()));
                    json.put("repairMark", ptrackingList.get(0).getReworkFlag());
                }
            }

            //气密性
            List<PMesLeakageT> leakageList = reportService.leakageList(map);
            if (leakageList.size() > 0) {
                json.put("leakageMsg", leakageList);
            } else {
                //气密性信息 临时表
                List<RMesLeakage> rleakageList = reportService.rleakageList(map);
                json.put("leakageMsg", rleakageList);
            }
            //物料信息
            List<PMesKeypartT> keypartList = reportService.keypartList(map);
            if (keypartList.size() > 0) {
                json.put("materialMsg", keypartList);
            } else {
                //物料信息
                List<RMesKeypart> rkeypartList = reportService.rkeypartList(map);
                json.put("materialMsg", rkeypartList);
            }
            List<PMesBoltT> boltList = reportService.boltList(map);
            if (boltList.size() > 0) {
                //螺栓信息
                json.put("boltMsg", boltList);
            } else {
                //螺栓信息
                List<RMesBolt> rboltList = reportService.rboltList(map);
                json.put("boltMsg", rboltList);
            }
            //产线电检测
            List<RAsmElectricDetection> rAsmElectricDetectionList = reportService.rAsmElectricDetectionList(map);
            json.put("asmElectricMsg", rAsmElectricDetectionList);
            //充放电检测
            List<PMesDischargeT> findAllDischargeT = reportService.findAllDischargeT(sn);
            json.put("dischargeMsg", findAllDischargeT);
            //EOL
            List<PMesEolT> findAllEol = reportService.findAllEol(sn);

            //称重
            List<PMesWeightT> allWeight = reportService.findAllWeight(sn);
            json.put("allWeight", allWeight);

            List<PMesStationPassT> allStationPass = reportService.findAllStationPass(sn);
            json.put("allStationPass", allStationPass);

            json.put("eolMsg", findAllEol);
            json.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", 1);
            json.put("msg", "未知错误");
        }

        return json;
    }


}
