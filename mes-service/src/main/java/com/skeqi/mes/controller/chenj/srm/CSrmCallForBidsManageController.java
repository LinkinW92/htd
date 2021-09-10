package com.skeqi.mes.controller.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmSourcingTemplateReq;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryInvitationForBidsTHService;
import com.skeqi.mes.service.chenj.srm.CSrmSourcingTemplateService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;


/**
 * 寻源管理
 *
 * @ClassName: CSrmCallForBidsManageController
 */
@RestController
@RequestMapping(value = "cSrmCallForBids", produces = MediaType.APPLICATION_JSON)
@Api(value = "寻源管理", description = "寻源管理", produces = MediaType.APPLICATION_JSON)
public class CSrmCallForBidsManageController {

    @Autowired
	CSrmSourcingTemplateService cSrmSourcingTemplateService;


    @Resource
	CSrmEnquiryInvitationForBidsTHService srmEnquiryInvitationForBidsTHService;


    @RequestMapping(value = "/createSourcingTemplateOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "寻源模板模板创建", notes = "寻源模板模板创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateCode", value = "模板编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateName", value = "模板名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型(1.询报价2.招投标)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1.已禁用2.已启用)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "accessoryCategories", value = "附属类别(1.银行信息2.产品信息)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "propertyField", value = "属性字段", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fieldName", value = "字段名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fieldValue", value = "字段值", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operationSign", value = "操作标识(1.新增2.修改3.新增版本4.状态变更)", required = true, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = "寻源模板模板创建", method = "寻源模板模板创建")
    public Rjson createSourcingTemplateOrUpdate(HttpServletRequest request, @RequestBody CSrmSourcingTemplateReq cSrmSourcingTemplateReq) {
        try {
            // 参数校验
            if (("4").equals(cSrmSourcingTemplateReq.getOperationSign()) || ("2").equals(cSrmSourcingTemplateReq.getOperationSign())) {
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getTemplateCode(), "模板编号");
                EqualsPoJoUtil.integer(cSrmSourcingTemplateReq.getStatus(), "状态",true);
            } else if (("3").equals(cSrmSourcingTemplateReq.getOperationSign()) || ("1").equals(cSrmSourcingTemplateReq.getOperationSign())) {
                if (("3").equals(cSrmSourcingTemplateReq.getOperationSign())) {
                    EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getTemplateCode(), "模板编号");
                }
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getTemplateName(), "模板名称");
                EqualsPoJoUtil.integer(cSrmSourcingTemplateReq.getType(), "类型",true);
                EqualsPoJoUtil.integer(cSrmSourcingTemplateReq.getStatus(), "状态",true);
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getVersion(), "版本");
                EqualsPoJoUtil.integer(cSrmSourcingTemplateReq.getAccessoryCategories(), "附属类别",true);
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getPropertyField(), "属性字段");
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getFieldName(), "字段名称");
                EqualsPoJoUtil.string(cSrmSourcingTemplateReq.getFieldValue(), "字段值");

                if (Integer.parseInt(cSrmSourcingTemplateReq.getType()) > 2) {
                    return Rjson.error(400, "'type'超出范围值");
                } else if (Integer.parseInt(cSrmSourcingTemplateReq.getAccessoryCategories()) > 2) {
                    return Rjson.error(400, "'accessoryCategories'超出范围值");
                }

            }
            EqualsPoJoUtil.integer(cSrmSourcingTemplateReq.getOperationSign(), "操作标识",true);
            if (Integer.parseInt(cSrmSourcingTemplateReq.getStatus()) > 2) {
                return Rjson.error(400, "'status'超出范围值");
            } else if (Integer.parseInt(cSrmSourcingTemplateReq.getOperationSign()) > 4) {
                return Rjson.error(400, "'operationSign'超出范围值");
            }
            return cSrmSourcingTemplateService.createSourcingTemplateOrUpdate(cSrmSourcingTemplateReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/updateSearchForTheSourceInfo", method = RequestMethod.POST)
    @ApiOperation(value = "寻源信息变更", notes = "寻源信息变更")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/报价单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "quotationStopTime", value = "报价/招标截止时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningTime", value = "开标时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningPlace", value = "开标地点", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operationSign", value = "操作标识(1.新增2.修改)", required = true, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = "寻源信息变更", method = "寻源信息变更")
    public Rjson updateSearchForTheSourceInfo(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmEnquiryInvitationForBidsTHReq) {
        try {
            // 参数校验
            EqualsPoJoUtil.string(cSrmEnquiryInvitationForBidsTHReq.getRfqOrTenderFormCode(), "询价/报价单号");
            EqualsPoJoUtil.date(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime(), "报价/招标截止时间");
//            EqualsPoJoUtil.string(cSrmEnquiryInvitationForBidsTHReq.getSupplierCode(), "供应商代码");
            EqualsPoJoUtil.date(cSrmEnquiryInvitationForBidsTHReq.getBidOpeningTime(), "开标时间");
            EqualsPoJoUtil.string(cSrmEnquiryInvitationForBidsTHReq.getBidOpeningPlace(), "开标地点");
            EqualsPoJoUtil.integer(cSrmEnquiryInvitationForBidsTHReq.getOperationSign(), "操作标识(1.新增2.修改)",true);
            if (Integer.parseInt(cSrmEnquiryInvitationForBidsTHReq.getOperationSign()) > 2) {
                return Rjson.error(400, "'operationSign'超出范围值");
                // 校验开标时间是否小于报价/招标截止时间
            } else if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getBidOpeningTime()).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cSrmEnquiryInvitationForBidsTHReq.getQuotationStopTime()).getTime()) {
                return Rjson.error(400, "开标时间不能小于报价/招标截止时间");
            }
            return srmEnquiryInvitationForBidsTHService.updateSearchForTheSourceInfo(cSrmEnquiryInvitationForBidsTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }

}
