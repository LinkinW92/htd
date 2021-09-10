package com.skeqi.mes.controller.crm;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.crm.QuotationManagementHServiceImpl;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;




import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "报价管理页面", description = "报价管理页面", produces = MediaType.APPLICATION_JSON)
public class QuotationManagementHController {

    @Autowired
    private QuotationManagementHServiceImpl service;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    SimpleDateFormat dfex = new SimpleDateFormat("yyyyMMddHHmmss");//单号设置日期格式

//===========头数据处理=============

    @RequestMapping(value = "/showQuotationInfoH", method = RequestMethod.POST)
    @ApiOperation(value = "查询报价管理头信息", notes = "查询报价管理头信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "quotationRecordNo", value = "报价记录号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "companyCode", value = "公司代码", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "customerID", value = "客户ID", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "materialCode", value = "物料名称", required = false, paramType = "query", dataType = "string"),
    })
    public Rjson showQuotationInfoH(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
//		System.out.println(map);
        Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
        String quotationRecordNo;
        if (map.get("quotationRecordNo") == null) {
            quotationRecordNo = "";
        } else {
            quotationRecordNo = String.valueOf(map.get("quotationRecordNo"));
        }
        String customerID;
        if (map.get("customerID") == null) {
            customerID = "";
        } else {
            customerID = String.valueOf(map.get("customerID"));
        }
        String companyCode;
        if (map.get("companyCode") == null) {
            companyCode = "";
        } else {
            companyCode = String.valueOf(map.get("companyCode"));
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> listH = service.showQuotationHInfo(quotationRecordNo, companyCode, customerID);
        PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(listH);
        return Rjson.success(pageInfo);
    }


    @RequestMapping(value = "/showQuotationInfoHR", method = RequestMethod.POST)
    @ApiOperation(value = "查询报价管理头+行信息", notes = "查询报价管理头+行信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "quotationRecordNo", value = "报价记录号", required = false, paramType = "query", dataType = "string"),
    })
    public Rjson showQuotationInfoHR(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
        System.out.println(map);

        String quotationRecordNo;
        if (map.get("quotationRecordNo") == null) {
            quotationRecordNo = "";
        } else {
            quotationRecordNo = String.valueOf(map.get("quotationRecordNo"));
        }
        List<Map<String, Object>> listH = service.showQuotationHInfoByCode(quotationRecordNo);
        List<Map<String, Object>> listR = service.showQuotationRInfoByCode(quotationRecordNo);
        Map<String, Object> infoDataByCode = new HashMap<>();
        infoDataByCode.put("listH", listH);
        infoDataByCode.put("listR", listR);
        System.out.println("listH:" + listH + "," + "listR:" + listR);
        return Rjson.success(infoDataByCode);
    }


    @RequestMapping(value = "/addQuotationHRInfo", method = RequestMethod.POST)
    @ApiOperation(value = "新增报价管理（头+行）数据", notes = "新增报价管理")
    @ApiImplicitParams({
    })
    public Rjson addQuotationHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
        String customerID = String.valueOf(map.get("customerID"));
        String companyCode = String.valueOf(map.get("companyCode"));
//        System.out.println("123===================================");

//		System.out.println("状态:"+map.get("status"));
        String quotationRecordNo = "";
        String creationTime = "";
        String revisionTime="";
        String founder = "";
        String reviser = "";
        String lineNumber = "1";
        String materialCode = "";
        String materialName = "";
        String costPrice = "";
        String offer = "";
        String priceUnit = "";

        List<Map<String,Object>> list =  (List)map.get("listR");
        List<Map<String,Object>> list1 = service.showQuotationRInfoByCode(String.valueOf(map.get("quotationRecordNo")));
        Integer countNum = 0;
        for (Map map1:list1
             ) {
            countNum = 0;
            for (Map map2:list
                 ) {
               if(map2.get("lineNumber")==null){
                   countNum++;
                   continue;
               }else{
//                   System.out.println(map1.get("lineNumber"));
//                   System.out.println(map2.get("lineNumber"));
                   if(!String.valueOf(map1.get("lineNumber")).equals(String.valueOf(map2.get("lineNumber")))){
                       countNum++;
                   }
               }
            }
            if(countNum==list.size()){
//                System.out.println("存在查找不到的数据，输出："+String.valueOf(map1.get("lineNumber")));
                service.delQuotationRDataByLineNum(String.valueOf(map1.get("quotationRecordNo")),String.valueOf(map1.get("lineNumber")));
            }
        }
        if (map.get("status") == null) {
            quotationRecordNo = "BJD" + dfex.format(new Date());
            creationTime = df.format(new Date());
            founder = ToolUtils.getCookieUserName(request);
            reviser = "";
            map.put("quotationRecordNo", quotationRecordNo);
            map.put("creationTime", creationTime);
            map.put("founder", founder);
            map.put("status", "1");
            service.addQuotationHInfo(quotationRecordNo, customerID, creationTime, founder, reviser, companyCode);

        } else if (map.get("status").equals("1")||map.get("status").equals("2")) {
            revisionTime = df.format(new Date());
            reviser = ToolUtils.getCookieUserName(request);
            map.put("revisionTime",revisionTime);
            map.put("reviser",reviser);
            quotationRecordNo = String.valueOf(map.get("quotationRecordNo"));
            //更新头表修改时间
            String status = String.valueOf(map.get("status"));
            service.updateQuotationHInfoH(reviser,revisionTime,quotationRecordNo,customerID,companyCode,status);

            for (Map<String, Object> mapList : list) {
                quotationRecordNo = String.valueOf(mapList.get("quotationRecordNo"));
                if(service.showLineNumber(quotationRecordNo)==null){
                    lineNumber="1";
                    materialCode = String.valueOf(mapList.get("materialCode"));
                    materialName = String.valueOf(mapList.get("materialName"));
                    costPrice = String.valueOf(mapList.get("costPrice"));
                    offer = String.valueOf(mapList.get("offer"));
                    priceUnit = String.valueOf(mapList.get("priceUnit"));
                    service.addQuotationRInfo(quotationRecordNo, lineNumber, materialCode, materialName, costPrice, offer,
                            priceUnit);
                    mapList.put("effectiveDate",df.format(new Date()));
                }else{
                    if(mapList.get("lineNumber")==null||mapList.get("lineNumber")==""){
                        Integer num =  Integer.parseInt(service.showLineNumber(quotationRecordNo)) + 1;
                        lineNumber =  String.valueOf((num));
                        materialCode = String.valueOf(mapList.get("materialCode"));
                        materialName = String.valueOf(mapList.get("materialName"));
                        costPrice = String.valueOf(mapList.get("costPrice"));
                        offer = String.valueOf(mapList.get("offer"));
                        priceUnit = String.valueOf(mapList.get("priceUnit"));
                        service.addQuotationRInfo(quotationRecordNo, lineNumber, materialCode, materialName, costPrice, offer,
                                priceUnit);
                        mapList.put("effectiveDate",df.format(new Date()));
                    }else{
                        lineNumber = String.valueOf(mapList.get("lineNumber"));
                        materialCode = String.valueOf(mapList.get("materialCode"));
                        materialName = String.valueOf(mapList.get("materialName"));
                        costPrice = String.valueOf(mapList.get("costPrice"));
                        offer = String.valueOf(mapList.get("offer"));
                        priceUnit = String.valueOf(mapList.get("priceUnit"));
                        quotationRecordNo = String.valueOf(mapList.get("quotationRecordNo"));

                        service.updateQuotationHInfoR(materialCode,materialName,costPrice,offer,priceUnit,quotationRecordNo,lineNumber);
                    }
                }
                mapList.put("lineNumber",lineNumber);
            }

            map.put("listR",list);
        }
        return Rjson.success(map);

    }


    @RequestMapping(value = "/delQuotationHRInfo", method = RequestMethod.POST)
    @ApiOperation(value = "删除报价管理（头+行）数据", notes = "删除报价管理")
    @ApiImplicitParams({
    })
    public Rjson delQuotationHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
        String quotationRecordNo = String.valueOf(map.get("quotationRecordNo"));
        String quotationRecordNos="";
        service.delQuotationHData(quotationRecordNo);//删除头数据
        List<Map<String,Object>> list = service.showQuotationRInfoByCode(quotationRecordNo);
        for (Map mapx:list
             ) {
            quotationRecordNos=String.valueOf(mapx.get("quotationRecordNo"));//删除行表数据
            service.delQuotationRData(quotationRecordNos);
        }
        return Rjson.success();
    }



}
