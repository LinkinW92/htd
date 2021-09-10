package com.skeqi.mes.controller.wf.productionQuality.qualityInspectionRecord;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.service.qh.PMesTrackingTService;
import com.skeqi.mes.service.wf.productionQuality.qualityInspectionRecord.CMesQualityInspectionRecordService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * 质检历史记录管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "quality", produces = MediaType.APPLICATION_JSON)
@Api(value = "质检历史记录管理", description = "质检历史记录管理", produces = MediaType.APPLICATION_JSON)
public class QualityInspectionRecordController {
    @Resource
    private CMesQualityInspectionRecordService cMesQualityInspectionRecordService;

    @Resource
    private PMesTrackingTService mesTrackingTService;

    @Autowired
    CMesProductionService productionService;

    @ApiOperation(value = "查询质检记录", notes = "查询质检记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "QC_PERSONNEL", value = "质检人员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "SN", value = "总成号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "START", value = "状态", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "质检类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="查询质检记录")
    @RequestMapping(value = "findQuality", method = RequestMethod.POST)
    public Rjson findQuality(HttpServletRequest request){
        try {
            String qcPersonnel = EqualsUtil.string(request, "QC_PERSONNEL", "质检人员", false);
            String sn = EqualsUtil.string(request, "SN", "总成号", false);
            String type = EqualsUtil.string(request, "type", "质检类型", false);
            String START = EqualsUtil.string(request, "START", "状态", true);
            Integer pageNum = EqualsUtil.integer(request, "pageNum", "当前页码", false);
            Integer pageSize = EqualsUtil.integer(request, "pageSize", "页码大小", false);
            Map<String, Object> map = new HashMap<>();
            map.put("qcPersonnel",qcPersonnel);
            map.put("sn",sn);
            map.put("type",type);
            map.put("start",START);
            //查询组长检查结果不等于NG的质检记录
            map.put("resultLead","NG");
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String,Object>> list = cMesQualityInspectionRecordService.findQuality(map);
            PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按产线id查询sn", notes = "按产线id查询sn")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="按产线id查询sn")
    @RequestMapping(value = "findSNByLineId", method = RequestMethod.POST)
    public Rjson findSNByLineId(HttpServletRequest request){
        try {
            String lineId = EqualsUtil.string(request, "lineId", "产线id", true);
            List<Map<String,Object>> list = mesTrackingTService.findSNByLineId(lineId);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按sn查询清单内容", notes = "按sn查询清单内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "总成号SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "质检类型", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="按sn查询清单内容")
    @RequestMapping(value = "findCheckListDetailBySN", method = RequestMethod.POST)
    public Rjson findCheckListDetailBySN(HttpServletRequest request){
        try {
            String sn = EqualsUtil.string(request, "sn", "总成号SN", true);
            String type = EqualsUtil.string(request, "type", "质检类型", true);
            List<Map<String, Object>> checkListDetailBySN =cMesQualityInspectionRecordService.findCheckListDetailBySN(sn,type);
            return Rjson.success(checkListDetailBySN);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "新增质检记录", notes = "新增质检记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CODE", value = "质检编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "SN", value = "总成号SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "TYPE", value = "质检类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "QC_PERSONNEL", value = "检查人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "contentList", value = "检查内容集合", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="新增质检记录")
    @RequestMapping(value = "addQuality", method = RequestMethod.POST)
    public Rjson addQuality(HttpServletRequest request){
        try {
            String CODE = EqualsUtil.string(request, "CODE", "质检编号", true);
            String SN = EqualsUtil.string(request, "SN", "总成号SN", true);
            String TYPE = EqualsUtil.string(request, "TYPE", "质检类型", true);
            String QC_PERSONNEL = EqualsUtil.string(request, "QC_PERSONNEL", "检查人员", true);
            Integer contentListSize = EqualsUtil.integer(request, "contentListSize", "检查项总数", true);

            List<Map<String, Object>> list = new ArrayList<>();
            String start = "OK";
            for (int i = 0; i <contentListSize ; i++) {
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                String key = request.getParameter("contentList["+i+"][key]");
                String value = request.getParameter("contentList["+i+"][value]");
                String result = request.getParameter("contentList["+i+"][RESULT]");
                String eligibility = request.getParameter("contentList["+i+"][ELIGIBILITY]");
                stringObjectHashMap.put("STANDARD_NAME",key);
                stringObjectHashMap.put("STANDARD_VALUE",value);
                stringObjectHashMap.put("RESULT",result);
                stringObjectHashMap.put("ELIGIBILITY",eligibility);
                list.add(stringObjectHashMap);
                if ("NG".equals(eligibility)){
                    start = "NG";
                }
            }

            Map<String,Object> map = new HashMap<>(16);
            map.put("processingType", "0");
            map.put("code", CODE);
            map.put("sn", SN);
            map.put("type",TYPE);
            map.put("start",start);
            map.put("qcPersonnel", QC_PERSONNEL);
            map.put("dt", new Date());
            map.put("contentList",list);
            Rjson rjson = cMesQualityInspectionRecordService.addQuality(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询质检检查内容项", notes = "查询质检检查内容项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CODE", value = "质检编号", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="查询质检检查内容项")
    @RequestMapping(value = "findCheckContent", method = RequestMethod.POST)
    public Rjson findCheckContent(HttpServletRequest request){
        try {
            Integer pageNum = EqualsUtil.integer(request, "pageNum", "当前页码", false);
            Integer pageSize = EqualsUtil.integer(request, "pageSize", "页码大小", false);
            String CODE = EqualsUtil.string(request, "CODE", "质检编号", true);

            Map<String,Object> map = new HashMap<>(16);
            map.put("code", CODE);

            PageHelper.startPage(pageNum, pageSize);
            List<Map<String,Object>> list = cMesQualityInspectionRecordService.findCheckContent(map);
            PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "审核质检记录", notes = "审核质检记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PROCESS_TYPE", value = "已处理类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CODE", value = "质检编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "QC_LEAD", value = "质检组长名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "SN", value = "总成号SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "contentList", value = "检查内容集合", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="审核质检记录")
    @RequestMapping(value = "auditQuality", method = RequestMethod.POST)
    public Rjson auditQuality(HttpServletRequest request){
        try {
            String PROCESS_TYPE = EqualsUtil.string(request, "PROCESS_TYPE", "已处理类型", true);
            String CODE = EqualsUtil.string(request, "CODE", "质检编号", true);
            String SN = EqualsUtil.string(request, "SN", "总成号SN", true);
            String QC_LEAD = EqualsUtil.string(request, "QC_LEAD", "质检组长名称", true);
            Integer contentListSize = EqualsUtil.integer(request, "contentListSize", "检查项总数", true);

            String resultLead = "NG";
            if ("0".equals(PROCESS_TYPE)) {
                //合格
                resultLead = "OK";
            }

            List<Map<String, Object>> list = new ArrayList<>();
            for (int i = 0; i <contentListSize ; i++) {
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();
                String RESULT_LEAD = request.getParameter("contentList["+i+"][RESULT_LEAD]");
                String ELIGIBILITY_LEAD = request.getParameter("contentList["+i+"][ELIGIBILITY_LEAD]");
                String ID = request.getParameter("contentList["+i+"][ID]");
                stringObjectHashMap.put("ID",ID);
                stringObjectHashMap.put("RESULT_LEAD",RESULT_LEAD);
                stringObjectHashMap.put("ELIGIBILITY_LEAD",ELIGIBILITY_LEAD);
                list.add(stringObjectHashMap);
            }

            Map<String,Object> map = new HashMap<>(16);
            //已处理
            map.put("processingType","1");
            //组长检查结果
            map.put("resultLead",resultLead);
            //已处理类型
            map.put("processType",PROCESS_TYPE);

            map.put("code", CODE);
            map.put("sn", SN);
            map.put("qcLead",QC_LEAD);
            map.put("dtLead", new Date());
            map.put("contentList",list);
            Rjson rjson = cMesQualityInspectionRecordService.auditQuality(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "查询所有质检记录", notes = "查询所有质检记录")
    @OptionalLog(module="质量", module2="质检历史记录管理", method="查询所有质检记录")
    @RequestMapping(value = "findQualityAll", method = RequestMethod.POST)
    public Rjson findQualityAll(HttpServletRequest request){
        try {
            Map<String, Object> map = new HashMap<>();
            List<Map<String,Object>> list = cMesQualityInspectionRecordService.findQualityAll(map);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "查询待处理质检记录", notes = "查询待处理质检记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PROCESS_TYPE", value = "已处理类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "START", value = "状态", required = false, paramType = "query"),
            @ApiImplicitParam(name = "PROCESSING_TYPE", value = "是否处理", required = false, paramType = "query"),
            @ApiImplicitParam(name = "QC_PERSONNEL", value = "质检人员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "SN", value = "总成号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "质检类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="质检历史记录管理", method="查询待处理质检记录")
    @RequestMapping(value = "findDisposeQuality", method = RequestMethod.POST)
    public Rjson findDisposeQuality(HttpServletRequest request){
        try {
            String processType = EqualsUtil.string(request, "PROCESS_TYPE", "已处理类型", false);
            String start = EqualsUtil.string(request, "START", "状态", false);
            String processingType = EqualsUtil.string(request, "PROCESSING_TYPE", "是否处理", false);
            String qcPersonnel = EqualsUtil.string(request, "QC_PERSONNEL", "质检人员", false);
            String sn = EqualsUtil.string(request, "SN", "总成号", false);
            String type = EqualsUtil.string(request, "type", "质检类型", false);
            Integer pageNum = EqualsUtil.integer(request, "pageNum", "当前页码", false);
            Integer pageSize = EqualsUtil.integer(request, "pageSize", "页码大小", false);
            Map<String, Object> map = new HashMap<>();
            //已处理类型（0合格，1返厂，2报废）
            map.put("processType",processType);
            //是否处理（0待处理，1已处理）
            map.put("processingType",processingType);
            map.put("qcPersonnel",qcPersonnel);
            map.put("sn",sn);
            map.put("type",type);
            map.put("start",start);
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String,Object>> list = cMesQualityInspectionRecordService.findDisposeQuality(map);
            PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

}
