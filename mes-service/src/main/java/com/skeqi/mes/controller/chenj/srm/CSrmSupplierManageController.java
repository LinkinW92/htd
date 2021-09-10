package com.skeqi.mes.controller.chenj.srm;


import cn.hutool.json.JSONUtil;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.pojo.chenj.srm.req.*;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.chenj.srm.*;
import com.skeqi.mes.service.gmg.UserService;
import com.skeqi.mes.service.wf.CMesFactoryTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import com.skeqi.mes.util.chenj.FormatUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;


/**
 * 供应商注册认证
 *
 * @ClassName: CSrmSupplierManageController
 */
@RestController
@RequestMapping(value = "cSrmSupplier", produces = MediaType.APPLICATION_JSON)
@Api(value = "供应商注册认证", description = "供应商注册认证", produces = MediaType.APPLICATION_JSON)
@Slf4j
public class CSrmSupplierManageController {
	@Resource
	UserService userService;

	@Resource
	CSrmSupplierService cSrmSupplierService;

	@Resource
	CSrmSupplierChangeRecordService changeRecordService;

	@Resource
	CSrmSupplierHService cSrmSupplierHService;

	@Resource
	CSrmSupplierRService cSrmSupplierRService;

	@Resource
	CSrmTemplateService cSrmTemplateService;

	@Resource
	CSrmSurveyService cSrmSurveyService;

	@Resource
	CSrmIndicatorsDimensionService cSrmIndicatorsDimensionService;

	@Resource
	CSrmAssessTemplateHService cSrmAssessTemplateHService;

	@Resource
	CSrmAssessRecordHService cSrmAssessRecordHService;

	@Resource
	CSrmCompanyService cSrmCompanyService;

	@Resource
	CMesMaterialService cMesMaterialService;


	@Resource
	CMesFactoryTService cMesFactoryTService;

	// -- 供应商不需要定时抓取 所以此类作为定时器容器使用
	@Resource
	private SrmSupplierTimer srmSupplierTimer;

	@Autowired
	ISysUserServiceFeignClient iSysUserServiceFeignClient;

	@Value(value = "${servicePush.push}")
	private Boolean push;


	@RequestMapping(value = "/registeredSupplier", method = RequestMethod.POST)
	@ApiOperation(value = "供应商注册", notes = "供应商注册")
	@ApiImplicitParams({
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "供应商名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contactPerson", value = "联系人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contactNumber", value = "联系电话", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "contactEmail", value = "联系邮箱", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "门户", module2 = "供应商注册", method = "供应商注册")
	public Rjson addSupplier(HttpServletRequest request, @RequestBody CSrmSupplierReq cSrmSupplierReq) {
		try {
			// 参数校验
//            EqualsPoJoUtil.string(cSrmSupplierReq.getCompanyName(), "公司名称");
			EqualsPoJoUtil.string(cSrmSupplierReq.getName(), "供应商名称");
			EqualsPoJoUtil.string(cSrmSupplierReq.getContactPerson(), "联系人");
			// 必须用String 电话号码11位已经超出Integer校验范围
			EqualsPoJoUtil.string(cSrmSupplierReq.getContactNumber(), "联系电话");
			// 校验联系电话格式
			FormatUtils.isPhone(cSrmSupplierReq.getContactNumber());
			EqualsPoJoUtil.string(cSrmSupplierReq.getContactEmail(), "联系邮箱");
			// 校验邮箱格式
			FormatUtils.isEmail(cSrmSupplierReq.getContactEmail());
			EqualsPoJoUtil.string(cSrmSupplierReq.getAccount(), "账号");
			EqualsPoJoUtil.string(cSrmSupplierReq.getPassword(), "密码");
			// 加密密码
			// cSrmSupplierReq.setPassword(Encryption.getPassWord(cSrmSupplierReq.getPassword() + cSrmSupplierReq.getAccount() + 666666 + cSrmSupplierReq.getPassword(), 555));
			return cSrmSupplierService.addSupplier(cSrmSupplierReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/checkSupplierCode", method = RequestMethod.POST)
	@ApiOperation(value = "获取供应商信息", notes = "获取供应商信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "门户", module2 = "供应商注册", method = "获取供应商信息")
	public Rjson checkSupplierCode(HttpServletRequest request, @RequestBody String supplierCode) {
		try {
			EqualsPoJoUtil.string(supplierCode, "供应商编码");
			return Rjson.success(cSrmSupplierService.checkSupplierCode(supplierCode, null));
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/addEnterprise", method = RequestMethod.POST)
	@ApiOperation(value = "企业认证创建", notes = "企业认证创建")
	@ApiImplicitParams({
		// 供应商代码
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		// 公司基础信息
		@ApiImplicitParam(name = "unifyCode", value = "统一社会信用代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "enterpriseType", value = "企业类型(1.国有企业 2.集体所有制企业 3.私营企 4.股份制企业 5.有限合伙企业 6.联营企业 7.外商投资企业 8.个人独资企业 9.港澳台企业 10.股份合作企业)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registeredAddress", value = "注册地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "particularAddress", value = "详细地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "legalRepresentative", value = "法定代表人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registeredCapital", value = "注册资本", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registerDate", value = "成立日期", required = true, paramType = "query", dataType = "Date"),
		@ApiImplicitParam(name = "uploadOfBusinessLicense", value = "营业执照上传", required = true, paramType = "query", dataType = "String"),
		// 联系人信息
		@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sex", value = "性别(1.男2.女)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "email", value = "邮箱", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "department", value = "部门", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "position", value = "职位", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "linkManList", value = "联系人信息数据行", required = false, paramType = "query", dataType = "String"),
		// 财务信息
		@ApiImplicitParam(name = "year", value = "年度", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "totalAssets", value = "总资产(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "grossLiability", value = "总负债(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currentAssets", value = "流动资产(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currentLiabilities", value = "流动负债(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operatingReceipt", value = "营业收入(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "retainedProfits", value = "净利润(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "financeList", value = "财务信息数据行", required = false, paramType = "query", dataType = "String"),
		// 银行\交易信息
		@ApiImplicitParam(name = "bankName", value = "开户银行名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "bankAccount", value = "银行账号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "accountTitle", value = "账户名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "receiverMailbox", value = "收票人邮箱", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "collectorTelephoneNumber", value = "收票人电话", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "bankList", value = "银行\\交易信息数据行", required = false, paramType = "query", dataType = "String"),
		// 主要产品\服务信息
//            @ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "productType", value = "产品类别(1.中草药材、2.棉花)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "businessNature", value = "经营性质(1.制造商 2.贸易商 3.服务商)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "productsOrServices", value = "产品/服务", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "client", value = "客户", required = true, paramType = "query", dataType = "String"),
		// 附件信息
//            @ApiImplicitParam(name = "theAttachmentDescribe", value = "附件描述", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "attachmentUploading", value = "附件上传", required = true, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "企业认证", module2 = "企业认证创建", method = "企业认证创建")
	public Rjson addEnterprise(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			// 供应商代码
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "供应商代码");
			// 公司基础信息
			// 公司编码
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getCompanyCode(), "公司编码");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getUnifyCode(), "统一社会信用代码");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getEnterpriseType(), "企业类型");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getRegisteredAddress(), "注册地址");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getParticularAddress(), "详细地址");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getLegalRepresentative(), "法定代表人");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getRegisteredCapital(), "注册资本");
			EqualsPoJoUtil.dateYmd(cSrmEnterpriseReq.getRegisterDate(), "成立日期");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getUploadOfBusinessLicense(), "营业执照上传");
			// 联系人信息
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getName(), "姓名");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getSex(), "性别");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getPhone(), "电话");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getEmail(), "邮箱");
			// 财务信息
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getYear(), "年度");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getTotalAssets(), "总资产(万元)");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getGrossLiability(), "总负债(万元)");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getCurrentAssets(), "流动资产(万元)");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getCurrentLiabilities(), "流动负债(万元)");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getOperatingReceipt(), "营业收入(万元)");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getRetainedProfits(), "净利润(万元)");
			// 银行\交易信息
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getBankName(), "开户银行名称");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getBankAccount(), "银行账号");
			// 校验银行账号格式
//            FormatUtils.checkBankCard(cSrmEnterpriseReq.getBankAccount());
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getAccountTitle(), "账户名称");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getReceiverMailbox(), "收票人邮箱");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getCollectorTelephoneNumber(), "收票人电话");
			// 主要产品\服务信息
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getProductName(), "产品名称");
//            EqualsPoJoUtil.integer(cSrmEnterpriseReq.getProductType(), "产品类别", true);
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getBusinessNature(), "经营性质");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getProductsOrServices(), "产品/服务");
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getClient(), "客户");
			// 附件信息
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getTheAttachmentDescribe(), "附件描述");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getAttachmentUploading(), "附件上传");
//            if (Integer.parseInt(cSrmEnterpriseReq.getProductType()) > 2) {
//                return Rjson.error(400, "'productType'超出范围值");
//            }
			return cSrmSupplierService.addEnterprise(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findSupplierEnterpriseInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询企业审批信息", notes = "查询企业审批信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "inPhase", value = "所处阶段(1.注册、2.潜在、3.合格、4.淘汰)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "(0.冻结1.未认证2.认证中3.已认证)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "submitStart", value = "提交时间从", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "submitStop", value = "提交时间至", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "企业认证", module2 = "查询企业审批信息", method = "查询企业审批信息")
	public Rjson findSupplierEnterpriseInfo(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			EqualsPoJoUtil.integer(cSrmEnterpriseReq.getStatus(), "状态", false);
			cSrmEnterpriseReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmEnterpriseReq.getPageNum()));
			cSrmEnterpriseReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmEnterpriseReq.getPageSize()));
			return cSrmSupplierService.findSupplierEnterpriseInfo(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findSupplierInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询供应商信息", notes = "查询供应商信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "企业认证", module2 = "查询企业审批信息", method = "查询企业审批信息")
	public Rjson findSupplierInfo(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			cSrmEnterpriseReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmEnterpriseReq.getPageNum()));
			cSrmEnterpriseReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmEnterpriseReq.getPageSize()));
			return cSrmSupplierService.findSupplierInfo(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findEnterpriseInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询企业认证信息", notes = "查询企业认证信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyCode", value = "公司编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "企业认证", module2 = "查询企业认证信息", method = "查询企业认证信息")
	public Rjson findEnterpriseInfo(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "供应商代码");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getCompanyCode(), "公司编码");
			return cSrmSupplierService.findEnterpriseInfo(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findSupplierStatus", method = RequestMethod.POST)
	@ApiOperation(value = "获取供应商认证状态", notes = "获取供应商认证状态")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "企业认证", module2 = "获取供应商认证状态", method = "获取供应商认证状态")
	public Rjson findSupplierStatus(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "供应商代码");
			return cSrmSupplierService.findSupplierStatus(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findSupplierAuthInfo", method = RequestMethod.POST)
	@ApiOperation(value = "获取供应商认证审批信息", notes = "获取供应商认证审批信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "认证状态(0.冻结1.未认证2.认证中3.已认证4.认证失败)", required = true, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "企业认证", module2 = "获取供应商认证审批信息", method = "获取供应商认证审批信息")
	public Rjson findSupplierAuthInfo(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "供应商代码");
			EqualsPoJoUtil.integer(cSrmEnterpriseReq.getStatus(), "状态", false);
			return cSrmSupplierService.findSupplierAuthInfo(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/supplierAudit", method = RequestMethod.POST)
	@ApiOperation(value = "企业认证审核", notes = "企业认证审核")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyCode", value = "公司编码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "认证状态(0.冻结1.未认证2.认证中3.已认证4.认证失败)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "serviceType", value = "服务类型(1.K3(10000))", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "企业认证", module2 = "企业认证审核", method = "企业认证审核")
	public Rjson supplierAudit(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "供应商编码");
//            EqualsPoJoUtil.string(cSrmEnterpriseReq.getSupplierCode(), "公司编码");
			// 赋值服务开关值
			cSrmEnterpriseReq.setPush(push);
			EqualsPoJoUtil.integer(cSrmEnterpriseReq.getStatus(), "状态", false);

			if (Integer.parseInt(cSrmEnterpriseReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			}
			return cSrmSupplierService.supplierAudit(cSrmEnterpriseReq, srmSupplierTimer);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/changeRecordEdit", method = RequestMethod.POST)
	@ApiOperation(value = "供应商信息变更保存", notes = "供应商信息变更保存")
	@ApiImplicitParams({
		// 供应商代码
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(0.已保存1.变更中2.已变更3.变更失败)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.保存 2.提交)", required = true, paramType = "query", dataType = "String"),
		// 公司基础信息
		@ApiImplicitParam(name = "unifyCode", value = "统一社会信用代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "enterpriseType", value = "企业类型(1.国有企业 2.集体所有制企业 3.私营企 4.股份制企业 5.有限合伙企业 6.联营企业 7.外商投资企业 8.个人独资企业 9.港澳台企业 10.股份合作企业)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registeredAddress", value = "注册地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "particularAddress", value = "详细地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "legalRepresentative", value = "法定代表人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registeredCapital", value = "注册资本", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "registerDate", value = "成立日期", required = true, paramType = "query", dataType = "Date"),
		@ApiImplicitParam(name = "uploadOfBusinessLicense", value = "营业执照上传", required = true, paramType = "query", dataType = "String"),
		// 联系人信息 多行
		@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sex", value = "性别(1.男2.女)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "phone", value = "电话", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "email", value = "邮箱", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "department", value = "部门", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "position", value = "职位", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "linkManList", value = "联系人信息数据行", required = false, paramType = "query", dataType = "List"),
		// 财务信息 多行
		@ApiImplicitParam(name = "year", value = "年度", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "totalAssets", value = "总资产(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "grossLiability", value = "总负债(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currentAssets", value = "流动资产(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currentLiabilities", value = "流动负债(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operatingReceipt", value = "营业收入(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "retainedProfits", value = "净利润(万元)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "financeList", value = "财务信息数据行", required = false, paramType = "query", dataType = "List"),
		// 银行\交易信息 多行
		@ApiImplicitParam(name = "bankName", value = "开户银行名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "bankAccount", value = "银行账号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "accountTitle", value = "账户名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "receiverMailbox", value = "收票人邮箱", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "collectorTelephoneNumber", value = "收票人电话", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "bankList", value = "银行\\交易信息数据行", required = false, paramType = "query", dataType = "List"),
		// 主要产品\服务信息
		@ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "productType", value = "产品类别(1.中草药材、2.棉花)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "businessNature", value = "经营性质(1.制造商 2.贸易商 3.服务商)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "productsOrServices", value = "产品/服务", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "client", value = "客户", required = true, paramType = "query", dataType = "String"),
		// 附件信息
//            @ApiImplicitParam(name = "theAttachmentDescribe", value = "附件描述", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "attachmentUploading", value = "附件上传", required = true, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商信息变更保存", method = "供应商信息变更保存")
	public Rjson changeRecordEdit(HttpServletRequest request, @RequestBody CSrmSupplierChangeRecordReq
		recordReq) {
		try {
			EqualsPoJoUtil.string(recordReq.getOperationSign(), "操作标识");
			// 供应商代码
			EqualsPoJoUtil.string(recordReq.getSupplierCode(), "供应商代码");
			// 公司基础信息
			// 公司编码
//            EqualsPoJoUtil.string(recordReq.getCompanyCode(), "公司编码");
//            EqualsPoJoUtil.string(recordReq.getUnifyCode(), "统一社会信用代码");
//            EqualsPoJoUtil.string(recordReq.getEnterpriseType(), "企业类型");
//            EqualsPoJoUtil.string(recordReq.getRegisteredAddress(), "注册地址");
//            EqualsPoJoUtil.string(recordReq.getParticularAddress(), "详细地址");
//            EqualsPoJoUtil.string(recordReq.getLegalRepresentative(), "法定代表人");
//            EqualsPoJoUtil.string(recordReq.getRegisteredCapital(), "注册资本");
//            EqualsPoJoUtil.dateYmd(recordReq.getRegisterDate(), "成立日期");
//            EqualsPoJoUtil.string(recordReq.getUploadOfBusinessLicense(), "营业执照上传");
			// 联系人信息
//            EqualsPoJoUtil.string(recordReq.getName(), "姓名");
//            EqualsPoJoUtil.string(recordReq.getSex(), "性别");
//            EqualsPoJoUtil.string(recordReq.getPhone(), "电话");
//            EqualsPoJoUtil.string(recordReq.getEmail(), "邮箱");
			// 财务信息
//            EqualsPoJoUtil.string(recordReq.getYear(), "年度");
//            EqualsPoJoUtil.string(recordReq.getTotalAssets(), "总资产(万元)");
//            EqualsPoJoUtil.string(recordReq.getGrossLiability(), "总负债(万元)");
//            EqualsPoJoUtil.string(recordReq.getCurrentAssets(), "流动资产(万元)");
//            EqualsPoJoUtil.string(recordReq.getCurrentLiabilities(), "流动负债(万元)");
//            EqualsPoJoUtil.string(recordReq.getOperatingReceipt(), "营业收入(万元)");
//            EqualsPoJoUtil.string(recordReq.getRetainedProfits(), "净利润(万元)");
			// 银行\交易信息
//            EqualsPoJoUtil.string(recordReq.getBankName(), "开户银行名称");
//            EqualsPoJoUtil.string(recordReq.getBankAccount(), "银行账号");
			// 校验银行账号格式
//            FormatUtils.checkBankCard(recordReq.getBankAccount());
//            EqualsPoJoUtil.string(recordReq.getAccountTitle(), "账户名称");
//            EqualsPoJoUtil.string(recordReq.getReceiverMailbox(), "收票人邮箱");
//            EqualsPoJoUtil.string(recordReq.getCollectorTelephoneNumber(), "收票人电话");
			// 主要产品\服务信息
//            EqualsPoJoUtil.string(recordReq.getProductName(), "产品名称");
//            EqualsPoJoUtil.integer(recordReq.getProductType(), "产品类别", true);
//            EqualsPoJoUtil.string(recordReq.getBusinessNature(), "经营性质");
//            EqualsPoJoUtil.string(recordReq.getProductsOrServices(), "产品/服务");
//            EqualsPoJoUtil.string(recordReq.getClient(), "客户");
			// 附件信息
//            EqualsPoJoUtil.string(recordReq.getTheAttachmentDescribe(), "附件描述");
//            EqualsPoJoUtil.string(recordReq.getAttachmentUploading(), "附件上传");
//            if (Integer.parseInt(recordReq.getProductType()) > 2) {
//                return Rjson.error(400, "'productType'超出范围值");
//            }
			return changeRecordService.changeRecordEdit(recordReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findChangeRecord", method = RequestMethod.POST)
	@ApiOperation(value = "供应商信息变更查询已保存/变更中数据", notes = "供应商信息变更查询已保存/变更中数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(0.已保存1.变更中2.已变更3.变更失败)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyCode", value = "公司编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商信息变更查询已保存数据", method = "供应商信息变更查询已保存数据")
	public Rjson findChangeRecord(HttpServletRequest request, @RequestBody CSrmSupplierChangeRecordReq
		supplierChangeRecordReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(supplierChangeRecordReq.getSupplierCode(), "供应商代码");
//            EqualsPoJoUtil.string(supplierChangeRecordReq.getCompanyCode(), "公司编码");
			EqualsPoJoUtil.integer(supplierChangeRecordReq.getStatus(), "状态", false);
			return changeRecordService.findChangeRecord(supplierChangeRecordReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/revocationChangeRecord", method = RequestMethod.POST)
	@ApiOperation(value = "撤销企业信息变更记录申请", notes = "撤销企业信息变更记录申请")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyCode", value = "公司编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "删除企业信息变更记录", method = "删除企业信息变更记录")
	public Rjson revocationChangeRecord(HttpServletRequest request, @RequestBody CSrmSupplierChangeRecordReq
		supplierChangeRecordReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(supplierChangeRecordReq.getSupplierCode(), "供应商代码");
//            EqualsPoJoUtil.string(supplierChangeRecordReq.getCompanyCode(), "公司编码");
			return changeRecordService.revocationChangeRecord(supplierChangeRecordReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createAnApplicationForm", method = RequestMethod.POST)
	@ApiOperation(value = "供应商升降级申请单创建", notes = "供应商升降级申请单创建")
	@ApiImplicitParams({
		// 头数据
		@ApiImplicitParam(name = "requestCode", value = "申请单编号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "供应商名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currentGeneration", value = "当前阶段(1.注册、2.潜在、3.合格、4.淘汰)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "targetPhase", value = "目标阶段(1.注册、2.潜在、3.合格、4.淘汰)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyCode", value = "公司编码", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(0.冻结1.未认证2.认证中3.已认证4.认证失败)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "sumScore", value = "总得分", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "grade", value = "等级", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "remark", value = "备注/说明", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "requestStatus", value = "申请状态(1.已创建2.申请中3.申请成功4.申请失败)", required = true, paramType = "query", dataType = "String"),
		// 行数据
//            @ApiImplicitParam(name = "evaluationItemNo", value = "评价项目编号", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "evaluationItem", value = "评价项目", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "evaluationCriterion", value = "评价标准", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "scoreIs", value = "评分方式(1.手工评分2.系统评分)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "scoreStart", value = "分值从", required = rue, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "scoreStop", value = "分值至", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "score", value = "得分", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "weight", value = "权重(%)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "gradingStaff", value = "评分人员/评分人信息", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "reqList", value = "升降级申请行数据", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "serviceType", value = "服务类型(1.K3(10000))", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.创建2.更新3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商升降级申请单创建", method = "供应商升降级申请单创建")
	public Rjson createAnApplicationForm(HttpServletRequest request, @RequestBody CSrmSupplierHReq cSrmSupplierHReq) {
		try {
			EqualsPoJoUtil.string(cSrmSupplierHReq.getOperationSign(), "操作标识");
			if (Integer.parseInt(cSrmSupplierHReq.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			// 必传参数
			EqualsPoJoUtil.integer(cSrmSupplierHReq.getStatus(), "状态", false);
			if (("1").equals(cSrmSupplierHReq.getOperationSign())) {
				// 参数校验
				EqualsPoJoUtil.string(cSrmSupplierHReq.getSupplierCode(), "供应商编码");
				EqualsPoJoUtil.integer(cSrmSupplierHReq.getRequestStatus(), "申请状态", true);
				EqualsPoJoUtil.string(cSrmSupplierHReq.getCurrentGeneration(), "当前阶段");
				EqualsPoJoUtil.string(cSrmSupplierHReq.getTargetPhase(), "目标阶段");
				EqualsPoJoUtil.date(cSrmSupplierHReq.getCreateTime(), "创建时间");
				// 获取创建人
				cSrmSupplierHReq.setCreator(ToolUtils.getCookieUserName(request));
				EqualsPoJoUtil.string(cSrmSupplierHReq.getTargetPhase(), "目标阶段");
//                EqualsPoJoUtil.string(cSrmSupplierHReq.getCompanyCode(), "公司编码");
//                EqualsPoJoUtil.string(cSrmSupplierHReq.getCompanyName(), "公司名称");
				EqualsPoJoUtil.string(cSrmSupplierHReq.getStatus(), "状态");
				EqualsPoJoUtil.string(cSrmSupplierHReq.getSumScore(), "总得分");
				EqualsPoJoUtil.string(cSrmSupplierHReq.getGrade(), "等级");
				EqualsPoJoUtil.string(cSrmSupplierHReq.getRemark(), "说明");

			} else if (("2").equals(cSrmSupplierHReq.getOperationSign()) || ("3").equals(cSrmSupplierHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmSupplierHReq.getRequestCode(), "申请单编号");
				EqualsPoJoUtil.integer(cSrmSupplierHReq.getRequestStatus(), "申请状态", true);
				// 赋值服务开关值
				cSrmSupplierHReq.setPush(push);
			}

			if (Integer.parseInt(cSrmSupplierHReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			}
			return cSrmSupplierHService.createAnApplicationForm(cSrmSupplierHReq, srmSupplierTimer);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findApplicationFormHR", method = RequestMethod.POST)
	@ApiOperation(value = "根据申请单编号获取头行数据", notes = "根据申请单编号获取头行数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "根据申请单编号获取头行数据", method = "根据申请单编号获取头行数据")
	public Rjson findApplicationFormHR(HttpServletRequest request, @RequestBody CSrmSupplierHRReq req) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(req.getRequestCode(), "申请单号");
			return cSrmSupplierHService.findApplicationFormHR(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findApplicationFormH", method = RequestMethod.POST)
	@ApiOperation(value = "根据申请单编号获取头数据", notes = "根据申请单编号获取头数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "根据申请单编号获取头数据", method = "根据申请单编号获取头数据")
	public Rjson findApplicationFormH(HttpServletRequest request, @RequestBody CSrmSupplierHRReq req) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(req.getSupplierCode(), "供应商编码");
			return cSrmSupplierHService.findApplicationFormH(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findApplicationFormR", method = RequestMethod.POST)
	@ApiOperation(value = "根据申请单编号获取行数据", notes = "根据申请单编号获取行数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "根据申请单编号获取行数据", method = "根据申请单编号获取行数据")
	public Rjson findApplicationFormR(HttpServletRequest request, @RequestBody CSrmSupplierHRReq req) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(req.getRequestCode(), "申请单号");
			return cSrmSupplierHService.findApplicationFormR(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/delApplicationForm", method = RequestMethod.POST)
	@ApiOperation(value = "删除升降级申请单", notes = "删除升降级申请单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "lineNumber", value = "行号", required = false, paramType = "query", dataType = "List<Integer>"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "根据申请单编号获取头行数据", method = "根据申请单编号获取头行数据")
	public Rjson delApplicationForm(HttpServletRequest request, @RequestBody CSrmSupplierHRDelReq req) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(req.getRequestCode(), "申请单号");
			return cSrmSupplierHService.delApplicationForm(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/supplierExamineAndScore", method = RequestMethod.POST)
	@ApiOperation(value = "供应商升降级申请单审批&评分", notes = "供应商升降级申请单审批&评分")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "gradingIndex", value = "评分指标", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "gradingStaff", value = "评分人员", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "examineSign", value = "审批标记(1.审批通过2.审批失败)", required = true, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商升降级申请单审批&评分", method = "供应商升降级申请单审批&评分")
	public Rjson supplierExamineAndScore(HttpServletRequest request, @RequestBody CSrmSupplierRReq cSrmSupplierRReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmSupplierRReq.getRequestCode(), "申请单号");
//            EqualsPoJoUtil.string(cSrmSupplierRReq.getGradingIndex(), "评分指标");
			EqualsPoJoUtil.string(cSrmSupplierRReq.getGradingStaff(), "评分人员");
//            EqualsPoJoUtil.integer(cSrmSupplierRReq.getExamineSign(), "审批标记", true);
//            if (Integer.parseInt(cSrmSupplierRReq.getExamineSign()) > 2) {
//                return Rjson.error(400, "'examineSign'超出范围值");
//            }
			return cSrmSupplierRService.supplierExamineAndScore(cSrmSupplierRReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "调查表模板创建", notes = "调查表模板创建")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateName", value = "模板名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "type", value = "类型(1.生产制造类)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建、2.审批中、3.待发布、4.待回复、5.已完成)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "version", value = "版本", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "auxiliaryType", value = "附属类别(1.银行信息2.产品信息)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "propertyField", value = "属性字段", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fieldName", value = "字段名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fieldType", value = "字段类型(1.int、2.string)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "调查表模板创建", method = "调查表模板创建")
	public Rjson createTemplate(HttpServletRequest request, @RequestBody CSrmTemplateReq cSrmTemplateReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmTemplateReq.getTemplateName(), "模板名称");
			EqualsPoJoUtil.integer(cSrmTemplateReq.getType(), "类型", true);
			EqualsPoJoUtil.integer(cSrmTemplateReq.getStatus(), "状态", true);
			EqualsPoJoUtil.string(cSrmTemplateReq.getVersion(), "版本");

			EqualsPoJoUtil.string(cSrmTemplateReq.getAuxiliaryType(), "附属类别");
			EqualsPoJoUtil.string(cSrmTemplateReq.getFieldType(), "字段类型");


			if (Integer.parseInt(cSrmTemplateReq.getType()) > 1) {
				return Rjson.error(400, "'type'超出范围值");
			} else if (Integer.parseInt(cSrmTemplateReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			} else if (Integer.parseInt(cSrmTemplateReq.getAuxiliaryType()) > 2) {
				return Rjson.error(400, "'auxiliaryType'超出范围值");
			}


			return cSrmTemplateService.createTemplate(cSrmTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "调查表模板查询", notes = "调查表模板查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "templateCode", value = "模板编号", required = true, paramType = "query", dataType = "String"),
//    })
//    @OptionalLog(module = "供应商信息", module2 = "调查表模板查询", method = "调查表模板查询")
	public Rjson findTemplate(HttpServletRequest request, @RequestBody CSrmTemplateReq cSrmTemplateReq) {
		try {
			cSrmTemplateReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTemplateReq.getPageNum()));
			cSrmTemplateReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTemplateReq.getPageSize()));
//            EqualsPoJoUtil.string(cSrmTemplateReq.getTemplateCode(), "模板编号");
			return cSrmTemplateService.findTemplate(cSrmTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createSurvey", method = RequestMethod.POST)
	@ApiOperation(value = "调查表创建", notes = "调查表创建")
	@ApiImplicitParams({
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "creator", value = "创建人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateCode", value = "模板编号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "auxiliaryType", value = "附属类别(1.银行信息2.产品信息)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "propertyField", value = "属性字段", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fieldValue", value = "字段值", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "调查表创建", method = "调查表创建")
	public Rjson createSurvey(HttpServletRequest request, @RequestBody CSrmSurveyReq cSrmSurveyReq) {
		try {
			// 参数校验
//            EqualsPoJoUtil.string(cSrmSurveyReq.getCompanyName(), "公司名称");
			EqualsPoJoUtil.string(cSrmSurveyReq.getCreator(), "创建人");
			EqualsPoJoUtil.string(cSrmSurveyReq.getTemplateCode(), "模板编号");
			EqualsPoJoUtil.integer(cSrmSurveyReq.getAuxiliaryType(), "附属类别", true);
			EqualsPoJoUtil.string(cSrmSurveyReq.getPropertyField(), "属性字段");
			EqualsPoJoUtil.string(cSrmSurveyReq.getFieldValue(), "字段值");
			EqualsPoJoUtil.string(cSrmSurveyReq.getSupplierCode(), "供应商代码");

			if (Integer.parseInt(cSrmSurveyReq.getAuxiliaryType()) > 2) {
				return Rjson.error(400, "'auxiliaryType'超出范围值");
			}
			return cSrmSurveyService.createSurvey(cSrmSurveyReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findCSrmSurveyData", method = RequestMethod.POST)
	@ApiOperation(value = "调查表查询", notes = "调查表查询")
	@ApiImplicitParams({
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "surveyFormNumber", value = "调查表单", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商准入及淘汰", module2 = "调查表查询", method = "调查表查询")
	public Rjson findCSrmSurveyData(HttpServletRequest request, @RequestBody CSrmSurveyReq cSrmSurveyReq) {
		try {
			cSrmSurveyReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmSurveyReq.getPageNum()));
			cSrmSurveyReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmSurveyReq.getPageSize()));
			return cSrmSurveyService.findCSrmSurveyData(cSrmSurveyReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findSupplierManagement", method = RequestMethod.POST)
	@ApiOperation(value = "供应商生命周期查询", notes = "供应商生命周期查询")
	@ApiImplicitParams({
//            @ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "供应商", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商准入及淘汰", module2 = "供应商生命周期查询", method = "供应商生命周期查询")
	public Rjson findSupplierManagement(HttpServletRequest request, @RequestBody CSrmEnterpriseReq cSrmEnterpriseReq) {
		try {
			cSrmEnterpriseReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmEnterpriseReq.getPageNum()));
			cSrmEnterpriseReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmEnterpriseReq.getPageSize()));
			return cSrmSupplierService.findSupplierManagement(cSrmEnterpriseReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/examineSurvey", method = RequestMethod.POST)
	@ApiOperation(value = "调查表审批&发布&回复", notes = "调查表审批&发布&回复")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "surveyFormNumber", value = "调查表单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "auxiliaryType", value = "附属类别(1.银行信息2.产品信息)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "propertyField", value = "属性字段", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fieldValue", value = "字段值", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "examineSign", value = "审批标记(1.待发布2.已拒绝3.已完成)", required = true, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "调查表审批&发布&回复", method = "调查表审批&发布&回复")
	public Rjson examineSurvey(HttpServletRequest request, @RequestBody CSrmSurveyReq cSrmSurveyReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmSurveyReq.getSurveyFormNumber(), "调查表单号");
			EqualsPoJoUtil.integer(cSrmSurveyReq.getAuxiliaryType(), "附属类别", true);
			EqualsPoJoUtil.string(cSrmSurveyReq.getPropertyField(), "属性字段");
			EqualsPoJoUtil.string(cSrmSurveyReq.getFieldValue(), "字段值");
			EqualsPoJoUtil.string(cSrmSurveyReq.getSupplierCode(), "供应商代码");
			EqualsPoJoUtil.integer(cSrmSurveyReq.getExamineSign(), "审批标记", true);
			if (Integer.parseInt(cSrmSurveyReq.getAuxiliaryType()) > 2) {
				return Rjson.error(400, "'auxiliaryType'超出范围值");
			} else if (Integer.parseInt(cSrmSurveyReq.getExamineSign()) > 3) {
				return Rjson.error(400, "'examineSign'超出范围值");
			}
			return cSrmSurveyService.examineSurvey(cSrmSurveyReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/createPerformanceEvaluation", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估指标创建", notes = "供应商绩效评估指标创建")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "indexCoding", value = "指标编码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "indexName", value = "指标名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "scoreIs", value = "评分方式(1.专家评分2.系统评分)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pointerType", value = "指标类型(1.专家评分2.系统评分)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "scoreStart", value = "分值起始值", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "scoreStop", value = "分值截止值", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估指标创建", method = "供应商绩效评估指标创建")
	public Rjson createPerformanceEvaluation(HttpServletRequest request, @RequestBody CSrmIndicatorsDimensionReq
		cSrmIndicatorsDimensionReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getIndexCoding(), "指标编码");
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getIndexName(), "指标名称");
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getScoreIs(), "评分方式");
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getPointerType(), "指标类型");
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getScoreStart(), "分值起始值");
			EqualsPoJoUtil.string(cSrmIndicatorsDimensionReq.getScoreStop(), "分值截止值");

			if (Integer.parseInt(cSrmIndicatorsDimensionReq.getScoreIs()) > 2) {
				return Rjson.error(400, "'scoreIs'超出范围值");
			} else if (Integer.parseInt(cSrmIndicatorsDimensionReq.getPointerType()) > 2) {
				return Rjson.error(400, "'pointerType'超出范围值");
			}
			return cSrmIndicatorsDimensionService.createPerformanceEvaluation(cSrmIndicatorsDimensionReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findPerformanceEvaluation", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估指标查询", notes = "供应商绩效评估指标查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估指标查询", method = "供应商绩效评估指标查询")
	public Rjson findPerformanceEvaluation(HttpServletRequest request, @RequestBody CSrmIndicatorsDimensionReq
		cSrmIndicatorsDimensionReq) {
		try {
			cSrmIndicatorsDimensionReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmIndicatorsDimensionReq.getPageNum()));
			cSrmIndicatorsDimensionReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmIndicatorsDimensionReq.getPageSize()));
			return cSrmIndicatorsDimensionService.findPerformanceEvaluation(cSrmIndicatorsDimensionReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createEvaluationTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估模板创建", notes = "供应商绩效评估模板创建")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateName", value = "模板名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "type", value = "类型(1.供应商考核2.供应商升级评审)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "adaptTheVendorCode", value = "适配供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "indexCode", value = "指标编码", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估模板创建", method = "供应商绩效评估模板创建")
	public Rjson createEvaluationTemplate(HttpServletRequest request, @RequestBody CSrmAssessTemplateHReq
		cSrmAssessTemplateHReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmAssessTemplateHReq.getTemplateName(), "模板名称");
			EqualsPoJoUtil.integer(cSrmAssessTemplateHReq.getType(), "类型", true);
			EqualsPoJoUtil.string(cSrmAssessTemplateHReq.getAdaptTheVendorCode(), "适配供应商代码");
			EqualsPoJoUtil.string(cSrmAssessTemplateHReq.getIndexCode(), "指标编码");
			if (Integer.parseInt(cSrmAssessTemplateHReq.getType()) > 2) {
				return Rjson.error(400, "'type'超出范围值");
			}
			return cSrmAssessTemplateHService.createEvaluationTemplate(cSrmAssessTemplateHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findEvaluationTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估模板查询", notes = "供应商绩效评估模板查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateName", value = "模板名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateCode", value = "模板编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "type", value = "模板类型(1.供应商考核2.供应商升级评审)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估模板查询", method = "供应商绩效评估模板查询")
	public Rjson findEvaluationTemplate(HttpServletRequest request, @RequestBody CSrmAssessTemplateHReq
		cSrmAssessTemplateHReq) {
		try {
			cSrmAssessTemplateHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmAssessTemplateHReq.getPageNum()));
			cSrmAssessTemplateHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmAssessTemplateHReq.getPageSize()));
			return cSrmAssessTemplateHService.findEvaluationTemplate(cSrmAssessTemplateHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateEvaluationTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估模板修改", notes = "供应商绩效评估模板修改")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateCode", value = "模板编号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "version", value = "版本号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "updateSign", value = "修改标记(1.新建2.禁用3.启用)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估模板创建", method = "供应商绩效评估模板创建")
	public Rjson updateEvaluationTemplate(HttpServletRequest request, @RequestBody CSrmAssessTemplateHReq cSrmAssessTemplateHReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmAssessTemplateHReq.getTemplateCode(), "模板编号");
			EqualsPoJoUtil.string(cSrmAssessTemplateHReq.getVersion(), "版本号");
			EqualsPoJoUtil.integer(cSrmAssessTemplateHReq.getUpdateSign(), "修改标记", true);
			if (Integer.parseInt(cSrmAssessTemplateHReq.getUpdateSign()) > 3) {
				return Rjson.error(400, "'updateSign'超出范围值");
			}
			return cSrmAssessTemplateHService.updateEvaluationTemplate(cSrmAssessTemplateHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createPerformanceEvaluationRecord", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估档案创建", notes = "供应商绩效评估档案创建")
	@ApiImplicitParams({
		// 头数据
		@ApiImplicitParam(name = "fileNumber", value = "档案编号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fileDescription", value = "档案描述", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "档案状态(1.新建2.评分中3.待发布4.已完成)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "evaluationTemplate", value = "考评模板", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "evaluationPeriod", value = "考评周期(1.月度2.季度3.半年度4.年度)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "appraisalLeader", value = "考评负责人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyCode", value = "考评公司", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "evaluationStartTime", value = "考评时间从", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "evaluationCutoffime", value = "考评时间至", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "appraisalWay", value = "考评方式", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "evaluationRuleExplain", value = "考评规则说明", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "evaluationExplain", value = "考评说明", required = true, paramType = "query", dataType = "String"),
		// 行数据
		@ApiImplicitParam(name = "scoringItems", value = "评分项", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "score", value = "分值", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "gradingStaff", value = "评分人员", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "reqList", value = "档案行数据", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.创建2.更新3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估档案创建", method = "供应商绩效评估档案创建")
	public Rjson createPerformanceEvaluationRecord(HttpServletRequest request, @RequestBody CSrmAssessRecordHReq
		req) {
		try {
			EqualsPoJoUtil.string(req.getOperationSign(), "操作标识");
			if (Integer.parseInt(req.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			// 必传参数
			EqualsPoJoUtil.integer(req.getStatus(), "档案状态", false);
			if (("1").equals(req.getOperationSign())) {
				// 参数校验
				EqualsPoJoUtil.string(req.getSupplierCode(), "考评对象");
				EqualsPoJoUtil.string(req.getFileDescription(), "档案描述");
//                    EqualsPoJoUtil.string(req.getEvaluationTemplate(), "考评模板");
				EqualsPoJoUtil.string(req.getEvaluationPeriod(), "考评周期");
				EqualsPoJoUtil.string(req.getCompanyCode(), "考评公司");
				EqualsPoJoUtil.string(req.getAppraisalLeader(), "考评负责人");
				EqualsPoJoUtil.date(req.getEvaluationStartTime(), "考评时间从");
				EqualsPoJoUtil.date(req.getEvaluationCutoffTime(), "考评时间至");
				EqualsPoJoUtil.string(req.getAppraisalWay(), "考评方式");
//                    EqualsPoJoUtil.string(req.getEvaluationRuleExplain(), "考评规则说明");
//                    EqualsPoJoUtil.string(req.getEvaluationExplain(), "考评说明");

			} else if (("2").equals(req.getOperationSign()) || ("3").equals(req.getOperationSign())) {
				EqualsPoJoUtil.string(req.getFileNumber(), "档案编号");
				EqualsPoJoUtil.integer(req.getStatus(), "状态", false);
			}

			if (Integer.parseInt(req.getStatus()) > 4) {
				return Rjson.error(400, "'status'超出范围值");
			}
			return cSrmAssessRecordHService.createPerformanceEvaluationRecord(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findPerformanceEvaluationRecordHR", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估档案头行查询", notes = "供应商绩效评估档案头行查询")
	@ApiImplicitParams({
		// 头数据
		@ApiImplicitParam(name = "fileNumber", value = "档案编号", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估档案头行查询", method = "供应商绩效评估档案头行查询")
	public Rjson findPerformanceEvaluationRecordHR(HttpServletRequest request, @RequestBody CSrmAssessRecordHReq
		req) {
		try {
			return cSrmAssessRecordHService.findPerformanceEvaluationRecordHR(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findPerformanceEvaluationRecordH", method = RequestMethod.POST)
	@ApiOperation(value = "供应商绩效评估档案头查询", notes = "供应商绩效评估档案头查询")
	@ApiImplicitParams({
		// 头数据
		@ApiImplicitParam(name = "fileNumber", value = "档案编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "档案状态(1.新建2.评分中3.待发布4.已完成)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估档案头行查询", method = "供应商绩效评估档案头行查询")
	public Rjson findPerformanceEvaluationRecordH(HttpServletRequest request, @RequestBody CSrmAssessRecordHReq
		req) {
		try {
			return cSrmAssessRecordHService.findPerformanceEvaluationRecordH(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findReceivedAssess", method = RequestMethod.POST)
	@ApiOperation(value = "已收评估结果查询", notes = "已收评估结果查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fileName", value = "档案名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fileNumber", value = "档案编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "供应商信息", module2 = "已收评估结果查询", method = "已收评估结果查询")
	public Rjson findReceivedAssess(HttpServletRequest request, @RequestBody CSrmAssessRecordHReq
		cSrmAssessRecordHReq) {
		try {
			cSrmAssessRecordHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmAssessRecordHReq.getPageNum()));
			cSrmAssessRecordHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmAssessRecordHReq.getPageSize()));
			return cSrmAssessRecordHService.findReceivedAssess(cSrmAssessRecordHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


//    @RequestMapping(value = "/updatePerformanceEvaluationRecord", method = RequestMethod.POST)
//    @ApiOperation(value = "供应商绩效评估档案修改", notes = "供应商绩效评估档案修改")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fileNumber", value = "档案编号", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "status", value = "状态(1.新建2.评分中3.待发布4.已完成)", required = true, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "operationSign", value = "操作标记(1.修改2.变更状态)", required = true, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "index", value = "指标", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "indexValue", value = "指标值", required = true, paramType = "query", dataType = "String"),
//    })
////    @OptionalLog(module = "供应商信息", module2 = "供应商绩效评估档案修改", method = "供应商绩效评估档案修改")
//    public Rjson updatePerformanceEvaluationRecord(HttpServletRequest request, @RequestBody CSrmAssessRecordHReq
//            cSrmAssessRecordHReq) {
//        try {
//            // 参数校验
//            if (("2").equals(cSrmAssessRecordHReq.getOperationSign())) {
//                EqualsPoJoUtil.string(cSrmAssessRecordHReq.getFileNumber(), "档案编号");
//                EqualsPoJoUtil.integer(cSrmAssessRecordHReq.getStatus(), "状态", true);
//            } else if (("1").equals(cSrmAssessRecordHReq.getOperationSign())) {
//                EqualsPoJoUtil.string(cSrmAssessRecordHReq.getFileNumber(), "档案编号");
//                EqualsPoJoUtil.integer(cSrmAssessRecordHReq.getStatus(), "状态", true);
//                EqualsPoJoUtil.string(cSrmAssessRecordHReq.getIndex(), "指标");
//                EqualsPoJoUtil.string(cSrmAssessRecordHReq.getIndexValue(), "指标值");
//            }
//            EqualsPoJoUtil.integer(cSrmAssessRecordHReq.getOperationSign(), "操作标记", true);
//            if (Integer.parseInt(cSrmAssessRecordHReq.getStatus()) > 4) {
//                return Rjson.error(400, "'status'超出范围值");
//            } else if (Integer.parseInt(cSrmAssessRecordHReq.getOperationSign()) > 2) {
//                return Rjson.error(400, "'operationSign'超出范围值");
//            }
//            return cSrmAssessRecordHService.updatePerformanceEvaluationRecord(cSrmAssessRecordHReq);
//        } catch (Exception e) {
//            ToolUtils.errorLog(this, e, request);
//            return Rjson.error(400, e.getMessage());
//        }
//    }


	@RequestMapping(value = "/checkSupplierAccount", method = RequestMethod.POST)
	@ApiOperation(value = "供应商名称校验", notes = "供应商名称校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "门户", module2 = "供应商注册", method = "供应商注册")
	public Rjson checkSupplierAccount(HttpServletRequest request, @RequestBody CSrmSupplierReq cSrmSupplierReq) {
		try {
			// 参数校验
			EqualsPoJoUtil.string(cSrmSupplierReq.getAccount(), "账号");
			// 查询供应商名称是否存在
			AjaxResult result = iSysUserServiceFeignClient.checkUserAccount(cSrmSupplierReq.getAccount());
			if (HttpStatus.OK.value() != result.getCode()) {
				return Rjson.error(result.getMsg());
			} else {
				log.info("【根据用户名查询账号是否已存在出参】[{}]", JSONUtil.toJsonStr(result));
				return Rjson.success();
			}

		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findCompanyList", method = RequestMethod.POST)
	@ApiOperation(value = "查询公司代码及名称", notes = "查询公司代码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "companyCode", value = "公司编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "门户", module2 = "查询公司代码及名称", method = "查询公司代码及名称")
	public Rjson findCompanyList(HttpServletRequest request, @RequestBody CSrmCompanyReq companyReq) {
		try {
			companyReq.setPageNum(EqualsPoJoUtil.pageNum(companyReq.getPageNum()));
			companyReq.setPageSize(EqualsPoJoUtil.pageSize(companyReq.getPageSize()));
			return cSrmCompanyService.selectCompanyList(companyReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findSupplierList", method = RequestMethod.POST)
	@ApiOperation(value = "查询供应商代码及名称", notes = "查询供应商代码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "供应商名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "门户", module2 = "查询供应商代码及名称", method = "查询供应商代码及名称")
	public Rjson findSupplierList(HttpServletRequest request, @RequestBody CSrmSupplierHReqs supplierHReqs) {
		try {
			supplierHReqs.setPageNum(EqualsPoJoUtil.pageNum(supplierHReqs.getPageNum()));
			supplierHReqs.setPageSize(EqualsPoJoUtil.pageSize(supplierHReqs.getPageSize()));
			return cSrmSupplierService.selectSupplierList(supplierHReqs);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findAllMaterialList", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料编码及名称", notes = "查询物料编码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bomId", value = "物料编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "物料名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "门户", module2 = "查询物料编码及名称", method = "查询物料编码及名称")
	public Rjson findAllMaterialList(HttpServletRequest request, @RequestBody CMesJlMaterialTReq req) {
		try {
			req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
			req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
			return cMesMaterialService.findAllMaterialList(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findFactoryList", method = RequestMethod.POST)
	@ApiOperation(value = "查询工厂编码及名称", notes = "查询工厂编码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "bomId", value = "物料编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "物料名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "门户", module2 = "查询工厂编码及名称", method = "查询工厂编码及名称")
	public Rjson findFactoryList(HttpServletRequest request, @RequestBody CMesFactoryTReq req) {
		try {
			req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
			req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
			return cMesFactoryTService.findFactoryList(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findUserList", method = RequestMethod.POST)
	@ApiOperation(value = "查询采购员编码及名称", notes = "查询采购员编码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "采购员编码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "userName", value = "采购员名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "门户", module2 = "查询工厂编码及名称", method = "查询工厂编码及名称")
	public Rjson findUserList(HttpServletRequest request, @RequestBody CMesUserTReq req) {
		try {
			req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
			req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
			return userService.findUserList(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findDepartmentData", method = RequestMethod.POST)
	@ApiOperation(value = "查询部门编码及名称", notes = "查询部门编码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dmNumber", value = "部门编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "dmName", value = "部门名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})   // c_wms_department_t
//    @OptionalLog(module = "门户", module2 = "查询工厂编码及名称", method = "查询工厂编码及名称")
	public Rjson findDepartmentData(HttpServletRequest request, @RequestBody CWmsDepartmentTReq req) {
		try {
			req.setPageNum(EqualsPoJoUtil.pageNum(req.getPageNum()));
			req.setPageSize(EqualsPoJoUtil.pageSize(req.getPageSize()));
			return cMesFactoryTService.findDepartmentData(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


}
