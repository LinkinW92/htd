package com.skeqi.mes.controller.chenj.srm;

import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvoiceReceivableReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmKThreePurchaseAbuttingReq;
import com.skeqi.mes.pojo.chenj.srm.req.ReceiveTheActualReceiptReq;
import com.skeqi.mes.service.chenj.srm.CSrmInvoiceReceivableService;
import com.skeqi.mes.service.chenj.srm.CSrmKThreePurchaseAbuttingService;
import com.skeqi.mes.service.chenj.srm.CSrmSendCommodityHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import com.skeqi.mes.util.chenj.FormatUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

/**
 * @author ChenJ
 * @date 2021/7/1
 * @Classname CSrmKThreePurchaseAbuttingController
 * @Description K3采购对接日志控制器
 */
@RestController
@RequestMapping(value = "cSrmPurchaseAbutting", produces = MediaType.APPLICATION_JSON)
@Api(value = "K3采购对接日志", description = "K3采购对接日志", produces = MediaType.APPLICATION_JSON)
public class CSrmKThreePurchaseAbuttingController {


    @Resource
    private CSrmKThreePurchaseAbuttingService cSrmKThreePurchaseAbuttingService;


    @Resource
    private CSrmSendCommodityHService commodityHService;

    @Resource
	private CSrmInvoiceReceivableService cSrmInvoiceReceivableService;

    @RequestMapping(value = "/addAlterRecord", method = RequestMethod.POST)
    @ApiOperation(value = "K3采购对接日志", notes = "K3采购对接日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "doNumber", value = "单据号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "doType", value = "单据类型((1.采购申请、2.采购订单、3.送货单))", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "alterNumber", value = "变更类型(1.创建、2.修改--不使用、3.删除、4.入库(送货单))", required = true, paramType = "query", dataType = "String")
    })
//    @OptionalLog(module = "采购", module2 = "K3采购对接日志", method = "K3采购对接日志")
    public Rjson addAlterRecord(HttpServletRequest request, @RequestBody CSrmKThreePurchaseAbuttingReq cSrmKThreePurchaseAbuttingReq) {
        try {

            EqualsPoJoUtil.string(cSrmKThreePurchaseAbuttingReq.getDoNumber(), "单据号");
            // 校验单据号是否为中文
            FormatUtils.checkCountName(cSrmKThreePurchaseAbuttingReq.getDoNumber(), "单据号");
            // 校验是否包含非法字符
            FormatUtils.validateLegalString(cSrmKThreePurchaseAbuttingReq.getDoNumber(), "单据号");
            EqualsPoJoUtil.integer(cSrmKThreePurchaseAbuttingReq.getDoType(), "单据类型", true);
            EqualsPoJoUtil.integer(cSrmKThreePurchaseAbuttingReq.getAlterType(), "变更类型", true);

            return cSrmKThreePurchaseAbuttingService.addAlterRecord(cSrmKThreePurchaseAbuttingReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findAlterRecord", method = RequestMethod.POST)
    @ApiOperation(value = "查询K3采购对接日志", notes = "查询K3采购对接日志")
    public Rjson findAlterRecord(HttpServletRequest request, CSrmKThreePurchaseAbuttingReq req) {
        try {
            return cSrmKThreePurchaseAbuttingService.findAlterRecord(req);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/addReceiveTheActualReceipt", method = RequestMethod.POST)
    @ApiOperation(value = "变更入库实收数", notes = "变更入库实收数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deliveryNumber", value = "入库单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "consigneeName", value = "收货人", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "disposeSign", value = "处理标记(1、已完成2、拒绝)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "materialLine", value = "物料行信息", required = true, paramType = "query", dataType = "List<CSrmSendCommodityR>"),
    })
    public Rjson ReceiveTheActualReceipt(HttpServletRequest request, @RequestBody ReceiveTheActualReceiptReq receiptReq) {
        try {
            EqualsPoJoUtil.string(receiptReq.getDeliveryNumber(), "入库单号");
            EqualsPoJoUtil.string(receiptReq.getConsigneeName(), "收货人");
            EqualsPoJoUtil.integer(receiptReq.getDisposeSign(), "处理标记", true);
            if (CollectionUtils.isEmpty(receiptReq.getMaterialLine())) {
                return Rjson.error("物料行信息不能为空");
            } else {
                // 校验行数据中必需参数是否右值  遍历每个对象中单号、行号、物料号是否都有值
                boolean anyMatch = receiptReq.getMaterialLine()
                        .parallelStream()
                        .anyMatch((item) -> !StringUtil.eqNu(item.getDeliveryNumber()) || !StringUtil.eqNu(item.getLineItemNo()) || !StringUtil.eqNu(item.getPaidInNumber()));
                if (anyMatch) {
                    return Rjson.error("物料行信息数据不完整");
                }
            }
            return commodityHService.ReceiveTheActualReceipt(receiptReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/updateInvoiceReceivableStatus", method = RequestMethod.POST)
    @ApiOperation(value = "变更应收/应付发票单号状态", notes = " 变更应收/应付发票单号状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "invoiceReceivableNo", value = "单据号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(4.待收款确认6.拒付)", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "payer", value = "付款人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "payTime", value = "付款时间", required = false, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = " 变更应收/应付发票单号状态", method = " 变更应收/应付发票单号状态")
    public Rjson updateInvoiceReceivableStatus(HttpServletRequest request, @RequestBody CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {
        try {
            EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceReceivableNo(), "单据号");
            EqualsPoJoUtil.integer(cSrmInvoiceReceivableReq.getStatus(), "状态", true);
            // 校验状态值合法性
            if (!("4").equals(cSrmInvoiceReceivableReq.getStatus()) && !("6").equals(cSrmInvoiceReceivableReq.getStatus())) {
                return Rjson.error(400, "'status'超出范围值");
            }
            // 已付款必填付款人、付款时间
            if (("4").equals(cSrmInvoiceReceivableReq.getStatus())) {
                EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getPayer(), "付款人");
                EqualsPoJoUtil.date(cSrmInvoiceReceivableReq.getPayTime(), "付款时间");
            }

            return cSrmInvoiceReceivableService.updateByPrimaryKeyKThree(cSrmInvoiceReceivableReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


}


