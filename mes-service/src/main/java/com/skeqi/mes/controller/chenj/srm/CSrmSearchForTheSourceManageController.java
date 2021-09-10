package com.skeqi.mes.controller.chenj.srm;


import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryForBidsTSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmInvitationForBidsScoreTHReq;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryForBidsTSupplierService;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryInvitationForBidsTHService;
import com.skeqi.mes.service.chenj.srm.CSrmInvitationForBidsScoreTHService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
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

//import sun.plugin2.util.PojoUtil;


/**
 * 寻源/招标管理
 *
 * @ClassName: CSrmSearchForTheSourceManageController
 */
@RestController
@RequestMapping(value = "cSrmSearchForTheSource", produces = MediaType.APPLICATION_JSON)
@Api(value = "寻源/招标管理", description = "寻源/招标管理", produces = MediaType.APPLICATION_JSON)
public class CSrmSearchForTheSourceManageController {

    @Resource
	CSrmInvitationForBidsScoreTHService cSrmInvitationForBidsScoreTHService;

    @Resource
	CSrmEnquiryInvitationForBidsTHService srmEnquiryInvitationForBidsTHService;

    @Resource
	CSrmEnquiryForBidsTSupplierService cSrmEnquiryForBidsTSupplierService;


    @RequestMapping(value = "/createIndexScore", method = RequestMethod.POST)
    @ApiOperation(value = "招标评分创建", notes = "招标评分创建")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateName", value = "模板名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型(1.专家评分2.系统评分)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1.已禁用2.已启用)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "index", value = "指标", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "weight", value = "权重", required = true, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = "招标评分创建", method = "招标评分创建")
    public Rjson createIndexScore(HttpServletRequest request, @RequestBody CSrmInvitationForBidsScoreTHReq cSrmInvitationForBidsScoreTHReq) {
        try {

            // 参数校验
            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getTemplateName(), "模板名称");
            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getType(), "类型");
            EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getStatus(), "状态", true);
            EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getWeight(), "权重", true);
            EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getIndex(), "指标", true);

            if (Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getStatus()) > 2) {
                return Rjson.error(400, "'status'超出范围值");
            } else if (Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getType()) > 2) {
                return Rjson.error(400, "'type'超出范围值");
            }

            return cSrmInvitationForBidsScoreTHService.createIndexScore(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findIndexScore", method = RequestMethod.POST)
    @ApiOperation(value = "招标评分查询", notes = "招标评分查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateNumber", value = "模板编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "templateName", value = "模板名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
    })
//    @OptionalLog(module = "采购", module2 = "招标评分查询", method = "招标评分查询")
    public Rjson findIndexScore(HttpServletRequest request, @RequestBody CSrmInvitationForBidsScoreTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            cSrmInvitationForBidsScoreTHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvitationForBidsScoreTHReq.getPageNum()));
            cSrmInvitationForBidsScoreTHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvitationForBidsScoreTHReq.getPageSize()));
            return cSrmInvitationForBidsScoreTHService.findIndexScore(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/createForQuotationOrCallForBids", method = RequestMethod.POST)
    @ApiOperation(value = "询报价/招标编辑", notes = "询报价/招标编辑")
    @ApiImplicitParams({
            // 创建招投标单头 基础信息
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题/招标事项", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "purchasingOrganization", value = "采购组织名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "wayOfQuoting", value = "报价方式(1.线上报价，2.线下报价)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "wayToFindTheSource", value = "寻源类型/寻源方式(1.询价2.招标)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "companyCode", value = "公司代码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "quotationStartTime", value = "报价/投表开始时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "quotationStopTime", value = "报价/投表截止时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningTime", value = "开标时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "buyer", value = "采购员", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "clarificationDeadline", value = "澄清截止时间", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "theBidOpeningPeople", value = "开标人", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "selectionOfPeople", value = "定标人", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "biddingTechnicalDocuments", value = "招标技术文件", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "biddingBusinessDocuments", value = "招标商务文件", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "budget", value = "预算金额", required = true, paramType = "query", dataType = "String"),
            // 创建招投标单头 其他信息
            @ApiImplicitParam(name = "itemNumber", value = "项目编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "itemName", value = "项目名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "itemAddress", value = "项目地址", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "currency", value = "币种(1.CNY,2.USD)", required = false, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "exchangeRate", value = "汇率", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "paymentMethod", value = "付款方式(1.线上支付2.线下支付)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "termsOfPayment", value = "付款条款", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningPlace", value = "开标地点", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "purchasingContact", value = "采购联系人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "联系人电话", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "contactEmail", value = "联系人邮箱", required = false, paramType = "query", dataType = "String"),
            // 创建招投标单行 数据
            @ApiImplicitParam(name = "businessEntity", value = "业务实体", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "inventoryOrganization", value = "库存组织", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "materialType", value = "物料类别", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "demandedDate", value = "需求日期", required = false, paramType = "query", dataType = "String"),
            // 创建招投标单行 评分要素
            @ApiImplicitParam(name = "scoreElementsAndScores", value = "评分要素", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "分值", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "scorer", value = "评分专家", required = false, paramType = "query", dataType = "String"),
            // 供应商列表
            @ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "Array"),
            // 其他字段
            @ApiImplicitParam(name = "status", value = "状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "wayToFindTheSource", value = "寻源方式(1.邀请2.公开)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "principal", value = "负责人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rfqList", value = "行数据", required = false, paramType = "query", dataType = "Array"),
            @ApiImplicitParam(name = "supList", value = "报价/评分/行数据", required = false, paramType = "query", dataType = "Array"),
            @ApiImplicitParam(name = "operationSign", value = "操作标识(1.新增2.修改3.变更状态)", required = true, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = "询报价/招标", method = "询报价/招标")
    public Rjson createForQuotationOrCallForBids(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getOperationSign(), "操作标识", true);
            // 参数校验
            if (("2").equals(cSrmInvitationForBidsScoreTHReq.getOperationSign())) {
                EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
                EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getStatus(), "状态", true);
            } else if (("1").equals(cSrmInvitationForBidsScoreTHReq.getOperationSign())) {
                // 询价
                if (("1").equals(cSrmInvitationForBidsScoreTHReq.getSourcingType())) {
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getTitle(), "标题");
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getStatus(), "状态", true);
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getSourcingType(), "寻源类型", true);
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getWayOfQuoting(), "报价方式", true);
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getWayToFindTheSource(), "寻源方式", true);
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getCompanyCode(), "公司");
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getCurrency(), "币种", true);
                    EqualsPoJoUtil.date(cSrmInvitationForBidsScoreTHReq.getQuotationStartTime(), "报价开始时间");
                    EqualsPoJoUtil.date(cSrmInvitationForBidsScoreTHReq.getQuotationStopTime(), "报价截止时间");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getPrincipal(), "负责人");
                    // 获取创建人
                    cSrmInvitationForBidsScoreTHReq.setCreator(ToolUtils.getCookieUserName(request));
                    if (CollectionUtils.isEmpty(cSrmInvitationForBidsScoreTHReq.getSupplierCode())) {
                        return Rjson.error("供应商不能为空");
                    }
                } else if (("2").equals(cSrmInvitationForBidsScoreTHReq.getSourcingType())) {
                    EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getStatus(), "状态", true);
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getTitle(), "标题/招标事项");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getPurchasingOrganization(), "采购组织名称");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getWayOfQuoting(), "报价方式");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getWayToFindTheSource(), "寻源类型");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getCompanyCode(), "公司代码");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getQuotationStartTime(), "投标开始时间");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getQuotationStopTime(), "投标截止时间");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getBidOpeningTime(), "开标时间");
                    // 获取创建人
                    cSrmInvitationForBidsScoreTHReq.setCreator(ToolUtils.getCookieUserName(request));
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getBuyer(), "采购员");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getClarificationDeadline(), "澄清截止时间");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getTheBidOpeningPeople(), "开标人");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getSelectionOfPeople(), "定标人");
//                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getBiddingTechnicalDocuments(), "招标技术文件");
//                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getBiddingBusinessDocuments(), "招标商务文件");
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getBudget(), "预算金额");
                } else if (("3").equals(cSrmInvitationForBidsScoreTHReq.getSourcingType())) {
                    EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
                }


//                if(Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getSourcingType())>2){
//                    return Rjson.error(400,"'sourcingType'超出范围值");
//                }else if(Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getWayOfQuoting())>2){
//                    return Rjson.error(400,"'wayOfQuoting'超出范围值");
//                }else if(Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getWayToFindTheSource())>2){
//                    return Rjson.error(400,"'wayToFindTheSource'超出范围值");
//                }else if(Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getCurrency())>2){
//                    return Rjson.error(400,"'currency'超出范围值");
//                }else if(Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getWhetherToRecommend())>2){
//                    return Rjson.error(400,"'whetherToRecommend'超出范围值");
//                }

            }

            if (null != cSrmInvitationForBidsScoreTHReq.getStatus() && Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getStatus()) > 11) {
                return Rjson.error(400, "'status'超出范围值");
            } else if (Integer.parseInt(cSrmInvitationForBidsScoreTHReq.getOperationSign()) > 3) {
                return Rjson.error(400, "'operationSign'超出范围值");
            }

            return srmEnquiryInvitationForBidsTHService.createForQuotationOrCallForBids(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findForQuotationOrCallForBidsH", method = RequestMethod.POST)
    @ApiOperation(value = "查询询报价头数据", notes = "查询询报价头数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码(RFX单号)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "wayToFindTheSource", value = "寻源方式(1.邀请2.公开)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sourcingType", value = "寻源类型(1.询价2.招标)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "searchSourceConStatus", value = "多个状态", required = false, paramType = "query", dataType = "List<Integer>"),
            @ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "principal ", value = " 负责人", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "buyer", value = "采购员", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
    })
//    @OptionalLog(module = "采购", module2 = "查询询报价(寻源大厅、供应商报价、寻源过程控制、核价", method = "查询询报价(寻源大厅、供应商报价、寻源过程控制、核价")
    public Rjson findForQuotationOrCallForBidsH(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getSourcingType(), "寻源类型", true);
            cSrmInvitationForBidsScoreTHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvitationForBidsScoreTHReq.getPageNum()));
            cSrmInvitationForBidsScoreTHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvitationForBidsScoreTHReq.getPageSize()));
            return srmEnquiryInvitationForBidsTHService.findForQuotationOrCallForBidsH(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findForQuotationOrCallForBidsHR", method = RequestMethod.POST)
    @ApiOperation(value = "查询询报价/招投标头行数据", notes = "查询询报价头行数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "wayToFindTheSource", value = "寻源方式(1.邀请2.公开)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sourcingType", value = "寻源类型(1.询价2.招标)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
    })
//    @OptionalLog(module = "采购", module2 = "查询询报价/招标(核价下方行数据)", method = "查询询报价/招标(核价下方行数据)"
    public Rjson findForQuotationOrCallForBidsHR(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
            cSrmInvitationForBidsScoreTHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvitationForBidsScoreTHReq.getPageNum()));
            cSrmInvitationForBidsScoreTHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvitationForBidsScoreTHReq.getPageSize()));
            return srmEnquiryInvitationForBidsTHService.findForQuotationOrCallForBidsHR(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }

      // 与前端对接  向多家供应商询价时放开
//    @RequestMapping(value = "/updateWhetherRecommendations", method = RequestMethod.POST)
//    @ApiOperation(value = "核价时变更是否推荐", notes = "核价时变更是否推荐")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码(RFX单号)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "lineNumber", value = "行号", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "List<String>"),
//            @ApiImplicitParam(name = "whetherToRecommend", value = "是否推荐(1.是2.否)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "chooseReason", value = "选用理由", required = true, paramType = "query", dataType = "String"),
//    })
////    @OptionalLog(module = "核价时变更是否推荐", module2 = "核价时变更是否推荐")
//    public Rjson updateWhetherRecommendations(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
//        try {
//            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
//            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getLineNumber(), "行号");
//            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getSupplierCode().stream().findFirst().orElse(null), "供应商编码");
//            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getWhetherToRecommend(), "是否推荐");
//            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getChooseReason(), "选用理由");
//            return srmEnquiryInvitationForBidsTHService.updateWhetherRecommendations(cSrmInvitationForBidsScoreTHReq);
//        } catch (Exception e) {
//            ToolUtils.errorLog(this, e, request);
//            return Rjson.error(400, e.getMessage());
//        }
//    }


    @RequestMapping(value = "/findUpdateCall", method = RequestMethod.POST)
    @ApiOperation(value = "招标变更查询", notes = "招标变更查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码(RFX单号)", required = true, paramType = "query", dataType = "String")
    })
//    @OptionalLog(module = "采购", module2 = "招标变更")
    public Rjson findUpdateCall(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
            cSrmInvitationForBidsScoreTHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvitationForBidsScoreTHReq.getPageNum()));
            cSrmInvitationForBidsScoreTHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvitationForBidsScoreTHReq.getPageSize()));
            return srmEnquiryInvitationForBidsTHService.findUpdateCall(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findExamineNumber", method = RequestMethod.POST)
    @ApiOperation(value = "获取OA审批单号", notes = "获取OA审批单号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码(RFX单号)", required = false, paramType = "query", dataType = "String")
    })
//    @OptionalLog(module = "采购", module2 = "获取OA审批单号")
    public Rjson findExamineNumber(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq req) {
        try {
            return srmEnquiryInvitationForBidsTHService.findExamineNumber(req);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/updateCall", method = RequestMethod.POST)
    @ApiOperation(value = "询报价/招投标变更修改", notes = "询报价/招投标变更修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价/招标单编码(RFX单号)", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "quotationStartTime", value = "投标开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "quotationStopTime", value = "投标截止时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningTime", value = "开标时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "bidOpeningPlace", value = "开标地点", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sourcingType", value = "寻源类型(1.询报价2.招投标)", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "updateExplain", value = "变更说明", required = false, paramType = "query", dataType = "String"),
    })
//    @OptionalLog(module = "采购", module2 = "询报价/招投标变更修改")
    public Rjson updateCall(HttpServletRequest request, @RequestBody CSrmEnquiryInvitationForBidsTHReq cSrmInvitationForBidsScoreTHReq) {
        try {
            EqualsPoJoUtil.string(cSrmInvitationForBidsScoreTHReq.getRfqOrTenderFormCode(), "询价/招标单编码");
            if (StringUtil.eqNu(cSrmInvitationForBidsScoreTHReq.getName())) {
                EqualsPoJoUtil.integer(cSrmInvitationForBidsScoreTHReq.getSourcingType(), "寻源类型", true);
            }

            return srmEnquiryInvitationForBidsTHService.updateCall(cSrmInvitationForBidsScoreTHReq);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findSupplierNewList", method = RequestMethod.POST)
    @ApiOperation(value = "查询供应商最新报价信息", notes = "查询供应商最新报价信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

    })
//    @OptionalLog(module = "门户", module2 = "查询物料编码及名称", method = "查询物料编码及名称")
    public Rjson findSupplierNewList(HttpServletRequest request, @RequestBody CSrmEnquiryForBidsTSupplierReq req) {
        try {
            req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
            req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
            EqualsPoJoUtil.string(req.getMaterialCode(), "物料编码");
            return cSrmEnquiryForBidsTSupplierService.findSupplierNewList(req);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }

    }

    @RequestMapping(value = "/findSupplierList", method = RequestMethod.POST)
    @ApiOperation(value = "查询供应商报价信息", notes = "查询供应商报价信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "rfqOrTenderFormCode", value = "询价单编码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

    })
//    @OptionalLog(module = "门户", module2 = "查询物料编码及名称", method = "查询物料编码及名称")
    public Rjson findSupplierList(HttpServletRequest request, @RequestBody CSrmEnquiryForBidsTSupplierReq req) {
        try {
            req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
            req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
            EqualsPoJoUtil.string(req.getMaterialCode(), "物料编码");
            EqualsPoJoUtil.string(req.getRfqOrTenderFormCode(), "询价单编码");
            return cSrmEnquiryForBidsTSupplierService.findSupplierList(req);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


    @RequestMapping(value = "/findHistorySupplierList", method = RequestMethod.POST)
    @ApiOperation(value = "根据物料查询历史询价及订单的供应商", notes = "根据物料查询历史询价及订单的供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCodeList", value = "物料编码", required = true, paramType = "query", dataType = "Array"),
//            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

    })
//    @OptionalLog(module = "门户", module2 = "根据物料查询历史询价及订单的供应商", method = "根据物料查询历史询价及订单的供应商")
    public Rjson findHistorySupplierList(HttpServletRequest request, @RequestBody CSrmEnquiryForBidsTSupplierReq req) {
        try {
            req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
            req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
            return cSrmEnquiryForBidsTSupplierService.findHistorySupplierList(req);
        } catch (Exception e) {
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(400, e.getMessage());
        }
    }


}
