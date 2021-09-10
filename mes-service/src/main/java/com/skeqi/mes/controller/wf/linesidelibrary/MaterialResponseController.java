package com.skeqi.mes.controller.wf.linesidelibrary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wf.linesidelibrary.MaterialResponseParams;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialResponseT;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialResponseTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "materialResponse", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库要料返回请求", description = "线边库要料返回请求", produces = MediaType.APPLICATION_JSON)
public class MaterialResponseController {

    @Resource
    private RLslMaterialResponseTService responseTService;

    @ApiOperation(value = "新增返回记录表", notes = "新增返回记录表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picker", value = "捡料人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "billNo", value = "请求单据号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "params", value = "要料返回请求参数", required = true, paramType = "query")
    })
    @RequestMapping(value = "addMaterialResponse", method = RequestMethod.POST)
    public Rjson addMaterialResponse (HttpServletRequest request){
        try {
            Rjson rjson = null;
            //捡料人员
            String picker = EqualsUtil.string(request,"picker","捡料人员",true);

            if (picker==null||picker.equals("")){
                return Rjson.error("捡料人员不能为空!");
            }
            //要料请求单据号
            String billNo = EqualsUtil.string(request,"billNo","请求单据号",true);
            if (billNo==null){
                return Rjson.error("要料请求单据号不能为空!");
            }
            //要料请求捡料信息
            String params = EqualsUtil.string(request,"params","要料返回请求参数",true);
            JSONArray jsonArray = JSONArray.parseArray(params);
            List<MaterialResponseParams> materialResponseParams = jsonArray.toJavaList(MaterialResponseParams.class);
            if (StringUtils.isEmpty(materialResponseParams)) {
                return Rjson.error("要料请求返回数据不能为空!");
            } else {
                //验证信息
                for (MaterialResponseParams responseParams : materialResponseParams) {
                    if (responseParams.getRequestDetailId()==null){
                        return Rjson.error("要料请求明细id不能为空!");
                    }
                    switch (responseParams.getTracesType()){
                        case 0:
                            if (responseParams.getDetails().size()<1){
                                return Rjson.error("要料请求返回数据不能为空!");
                            }
                            for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                                if (rLslMaterialResponseT.getMaterialBatch() == null || rLslMaterialResponseT.getMaterialBatch() == "") {
                                    rjson = Rjson.error("物料批次不能为空");
                                }
                                if (rLslMaterialResponseT.getQuantity() == null) {
                                    rjson = Rjson.error("实际数量不能为空");
                                }
                                if (rLslMaterialResponseT.getCodeList().size()<1) {
                                    rjson = Rjson.error("物料单个条码不能为空");
                                }
                                for (String s : rLslMaterialResponseT.getCodeList()) {
                                    if (s == null || s.equals("")) {
                                        rjson = Rjson.error("物料单个条码不能为空");
                                    }
                                }
                            }
                            break;
                        case 1:
                            if (responseParams.getDetails().size()<1){
                                return Rjson.error("要料请求返回数据不能为空!");
                            }
                            for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                                if (rLslMaterialResponseT.getMaterialBatch() == null || rLslMaterialResponseT.getMaterialBatch() == "") {
                                    rjson = Rjson.error("物料批次不能为空");
                                }
                                if (rLslMaterialResponseT.getQuantity() == null) {
                                    rjson = Rjson.error("实际数量不能为空");
                                }
                            }
                            break;
                        case 2:
                            if (responseParams.getDetails().size()<1){
                                return Rjson.error("要料请求返回数据不能为空!");
                            }
                            for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                                if (rLslMaterialResponseT.getMaterialCode() == null || rLslMaterialResponseT.getMaterialCode() == "") {
                                    rjson = Rjson.error("物料单个条码不能为空");
                                }
                                if (rLslMaterialResponseT.getQuantity() == null) {
                                    rjson = Rjson.error("实际数量不能为空");
                                }
                            }
                            break;
                        default:
                            return Rjson.error("要料请求返回数据追溯方式信息错误!");
                    }
                }
                if (rjson==null) {
                     rjson = responseTService.insertSelective(materialResponseParams,billNo,picker);
                }
            }
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "查询线边库返回记录详情信息", notes = "查询线边库返回记录详情信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name = "requestDetailId", value = "请求明细id", required = true, paramType = "query"),
    })
    @RequestMapping(value = "findMaterialResponseByRequestDetailId", method = RequestMethod.POST)
    public Rjson findMaterialResponseByRequestDetailId (HttpServletRequest request){
        try {
            Integer requestDetailId = EqualsUtil.integer(request,"requestDetailId","请求明细id",true);
            List<RLslMaterialResponseT> requestTS = responseTService.findMaterialResponseByRequestDetailId(requestDetailId);
            return Rjson.success(requestTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}
