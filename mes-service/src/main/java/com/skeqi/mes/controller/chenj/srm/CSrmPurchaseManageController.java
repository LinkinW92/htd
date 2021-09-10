package com.skeqi.mes.controller.chenj.srm;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.redis.RedisCache;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.controller.chenj.srm.timer.SrmPoOrderTimer;
import com.skeqi.mes.controller.chenj.srm.timer.SrmPoRequestTimer;
import com.skeqi.mes.controller.chenj.srm.timer.SrmSupplierTimer;
import com.skeqi.mes.finals.SrmFinal;
import com.skeqi.mes.mapper.chenj.srm.*;
import com.skeqi.mes.mapper.gmg.UserDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.chenj.srm.CSrmFileUploading;
import com.skeqi.mes.pojo.chenj.srm.CSrmLinkman;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.pojo.chenj.srm.kthree.*;
import com.skeqi.mes.pojo.chenj.srm.req.*;
import com.skeqi.mes.service.chenj.srm.*;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.chenj.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 采购需求管理
 *
 * @ClassName: CSrmPurchaseManageController
 */
@RestController
@RequestMapping(value = "cSrmPurchase", produces = MediaType.APPLICATION_JSON)
@Api(value = "采购需求管理", description = "采购需求管理", produces = MediaType.APPLICATION_JSON)
@Slf4j
public class CSrmPurchaseManageController {

	@Resource
	CSrmPurchaseDemandHService cSrmPurchaseDemandHService;

	@Resource
	CSrmContractHService contractHService;

	@Resource
	CSrmPurchaseOrderHService srmPurchaseOrderHService;

	@Resource
	CSrmSendCommodityHService commodityHService;

	@Resource
	CSrmMakeOutAnInvoiceService cSrmMakeOutAnInvoiceService;

	@Resource
	CSrmInvoiceReceivableService cSrmInvoiceReceivableService;

	@Resource
	CSrmTheNumberAuditHService cSrmTheNumberAuditHService;

	@Resource
	CSrmContractTemplateService contractTemplateService;


	// -- 供应商不需要定时抓取 所以此类作为定时器容器使用
	@Resource
	private SrmSupplierTimer srmSupplierTimer;

	@Autowired
	private RedisCache redisCache;

	@Value(value = "${servicePush.push}")
	private Boolean push;

	@RequestMapping(value = "/createPurchaseDemand", method = RequestMethod.POST)
	@ApiOperation(value = "采购需求创建", notes = "采购需求创建")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.审批中3.待分配4.已分配5.已删除6.已询价7.部分转单8.全部转单9.无需转单)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "proposer", value = "申请人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "applicationDate", value = "申请日期", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectCode", value = "项目号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "applyForDepartment", value = "申请部门", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "buyer", value = "采购员", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purList", value = "采购行数据", required = false, paramType = "query", dataType = "Array"),
		@ApiImplicitParam(name = "businessType", value = "业务类型(可输入任意值)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "(1.创建 2.修改 3.状态变更)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购需求创建", method = "采购需求创建")
	public Rjson createPurchaseDemand(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) {
		try {

			if (("1").equals(cSrmPurchaseDemandHReq.getOperationSign())) {
				// 参数校验
				EqualsPoJoUtil.integer(cSrmPurchaseDemandHReq.getStatus(), "状态", true);
				EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getProposer(), "申请人");
				EqualsPoJoUtil.dateYmd(cSrmPurchaseDemandHReq.getApplicationDate(), "申请日期");
//                EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getProjectCode(), "项目号");
				// 校验项目号是否包含非法字符
//                FormatUtils.validateLegalString(cSrmPurchaseDemandHReq.getProjectCode(), "项目号");
//                EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getProjectName(), "项目名称");
				// 校验项目名称是否包含非法字符
//                FormatUtils.validateLegalString(cSrmPurchaseDemandHReq.getProjectName(), "项目名称");
				EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getApplyForDepartment(), "申请部门");
				// 校验申请部门是否包含非法字符
				FormatUtils.validateLegalString(cSrmPurchaseDemandHReq.getApplyForDepartment(), "申请部门");
				EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getBuyer(), "采购员");
				// 校验采购员是否包含非法字符
				FormatUtils.validateLegalString(cSrmPurchaseDemandHReq.getBuyer(), "采购员");
			} else if (("2").equals(cSrmPurchaseDemandHReq.getOperationSign()) || ("3").equals(cSrmPurchaseDemandHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getRequestCode(), "申请单号");
			}

			return cSrmPurchaseDemandHService.createPurchaseDemand(cSrmPurchaseDemandHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/allocationPurchaseDemand", method = RequestMethod.POST)
	@ApiOperation(value = "采购需求分配", notes = "采购需求分配")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCodeList", value = "申请单号集合", required = true, paramType = "query", dataType = "Array"),
		@ApiImplicitParam(name = "buyer", value = "采购员", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购需求分配", method = "采购需求分配")
	public Rjson allocationPurchaseDemand(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) {
		try {
			// 参数校验
			if (CollectionUtils.isEmpty(cSrmPurchaseDemandHReq.getRequestCodeList())) {
				return Rjson.error("采购申请单号不能为空");
			}
			EqualsPoJoUtil.string(cSrmPurchaseDemandHReq.getBuyer(), "采购员");
			return cSrmPurchaseDemandHService.allocationPurchaseDemand(cSrmPurchaseDemandHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseDemandH", method = RequestMethod.POST)
	@ApiOperation(value = "采购需求头查询", notes = "采购需求头查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "preparedBy", value = "制单人", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "buyer", value = "采购员", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.审批中3.待分配4.已分配5.已删除)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购需求查询", method = "采购需求查询")
	public Rjson findPurchaseDemandH(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) {
		try {
			cSrmPurchaseDemandHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmPurchaseDemandHReq.getPageNum()));
			cSrmPurchaseDemandHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmPurchaseDemandHReq.getPageSize()));
//            EqualsPoJoUtil.integer(cSrmPurchaseDemandHReq.getStatus(), "状态", true);
			return cSrmPurchaseDemandHService.findPurchaseDemandH(cSrmPurchaseDemandHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findPurchaseDemandR", method = RequestMethod.POST)
	@ApiOperation(value = "采购需求行查询", notes = "采购需求行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购需求查询", method = "采购需求查询")
	public Rjson findPurchaseDemandR(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHReq cSrmPurchaseDemandHReq) {
		try {
			cSrmPurchaseDemandHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmPurchaseDemandHReq.getPageNum()));
			cSrmPurchaseDemandHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmPurchaseDemandHReq.getPageSize()));
			return cSrmPurchaseDemandHService.findPurchaseDemandR(cSrmPurchaseDemandHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseDemandHRList", method = RequestMethod.POST)
	@ApiOperation(value = "采购需求单转寻源单/采购订单查询", notes = "采购需求单转寻源单/采购订单查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "reqList", value = "申请单号集合", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购需求查询", method = "采购需求查询")
	public Rjson findPurchaseDemandHRList(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHRListReq req) {
		try {
			return cSrmPurchaseDemandHService.findPurchaseDemandHRList(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/delPurchaseDemand", method = RequestMethod.POST)
	@ApiOperation(value = "删除采购申请单", notes = "删除采购申请单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "requestCode", value = "申请单号", required = true, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "采购", module2 = "删除采购需求单", method = "删除采购需求单")
	public Rjson delPurchaseDemand(HttpServletRequest request, @RequestBody CSrmPurchaseDemandHRListReq req) {
		try {
			EqualsPoJoUtil.string(req.getRequestCode(), "申请单号");
			return cSrmPurchaseDemandHService.delPurchaseDemand(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updatePurchaseContract", method = RequestMethod.POST)
	@ApiOperation(value = "采购合同编辑", notes = "采购合同编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "合同编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contractName", value = "合同名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contractCharacter", value = "合同性质(1.普通合同2.附件合同)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyCode", value = "公司代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "effectiveDateOfContract", value = "合同生效日期", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "dateOfTermination", value = "合同终止日期", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "usingTemplateNumbers", value = "使用模板编号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "count", value = "数量", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "unit", value = "单位", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currency", value = "币种(1.CNY2.USD)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "taxRate", value = "税率", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待审批3.待签署4.待存档5.已存档)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "buyer", value = "采购员", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "masterContract", value = "主合同", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sourceOfTheContract", value = "合同来源(1.手动创建2.采购申请转换3.寻源结果引用)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contractRental", value = "合同总额", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purchasingOrganization", value = "采购组织", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "remark", value = "备注", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "objectOfContractOrValue", value = "合同条款对象及对象值", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.创建2.修改3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购合同编辑", method = "采购合同编辑")
	public Rjson updatePurchaseContract(HttpServletRequest request, @RequestBody CSrmContractHReq cSrmContractHReq) {
		try {
			// 参数校验
			if (("3").equals(cSrmContractHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmContractHReq.getContractNo(), "合同编号");
				EqualsPoJoUtil.string(cSrmContractHReq.getSupplierCode(), "供应商代码");
				EqualsPoJoUtil.integer(cSrmContractHReq.getStatus(), "状态", true);
			} else if (("2").equals(cSrmContractHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmContractHReq.getContractNo(), "合同编号");
			}
			// 1、2、都需要校验参数
			if (("1").equals(cSrmContractHReq.getOperationSign()) || ("2").equals(cSrmContractHReq.getOperationSign())) {

				EqualsPoJoUtil.string(cSrmContractHReq.getContractName(), "合同名称");
				cSrmContractHReq.setCreator(ToolUtils.getCookieUserName(request));
				EqualsPoJoUtil.string(cSrmContractHReq.getContractCharacter(), "合同性质");
				EqualsPoJoUtil.string(cSrmContractHReq.getCompanyCode(), "公司代码");
				EqualsPoJoUtil.string(cSrmContractHReq.getSupplierCode(), "供应商代码");
				EqualsPoJoUtil.dateYmd(cSrmContractHReq.getEffectiveDateOfContract(), "合同生效日期");
				EqualsPoJoUtil.dateYmd(cSrmContractHReq.getDateOfTermination(), "合同终止日期");
				EqualsPoJoUtil.string(cSrmContractHReq.getUsingTemplateNumbers(), "使用模板编号");
				EqualsPoJoUtil.integer(cSrmContractHReq.getStatus(), "状态", true);

				if (new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getEffectiveDateOfContract()).getTime() > new SimpleDateFormat("yyyy-MM-dd").parse(cSrmContractHReq.getDateOfTermination()).getTime()) {
					return Rjson.error(400, "合同终止日期不能小于合同生效日期");
				} else if (Integer.parseInt(cSrmContractHReq.getContractCharacter()) > 2) {
					return Rjson.error(400, "'contractCharacter'超出范围值");
				}
			}
			EqualsPoJoUtil.integer(cSrmContractHReq.getOperationSign(), "操作标识", true);
			if (Integer.parseInt(cSrmContractHReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			} else if (Integer.parseInt(cSrmContractHReq.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			return contractHService.updatePurchaseContract(cSrmContractHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/delPurchaseContract", method = RequestMethod.POST)
	@ApiOperation(value = "删除采购合同", notes = "删除采购合同")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "合同编号", required = true, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "采购", module2 = "删除采购合同", method = "删除采购合同")
	public Rjson delPurchaseContract(HttpServletRequest request, @RequestBody CSrmContractHReq cSrmContractHReq) {
		try {
			EqualsPoJoUtil.string(cSrmContractHReq.getContractNo(), "合同编号");
			return contractHService.delPurchaseContract(cSrmContractHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseContractH", method = RequestMethod.POST)
	@ApiOperation(value = "采购合同头查询", notes = "采购合同头查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "合同编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购合同查询", method = "采购合同头查询")
	public Rjson findPurchaseContractH(HttpServletRequest request, @RequestBody CSrmContractHReq cSrmContractHReq) {
		try {
			return contractHService.findPurchaseContractH(cSrmContractHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseContractAll", method = RequestMethod.POST)
	@ApiOperation(value = "查询合同编码及名称", notes = "查询合同编码及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "合同编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contractName", value = "合同名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "查询合同编码及名称", method = "查询合同编码及名称")
	public Rjson findPurchaseContractAll(HttpServletRequest request, @RequestBody CSrmContractHReq cSrmContractHReq) {
		try {
			cSrmContractHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmContractHReq.getPageNum()));
			cSrmContractHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmContractHReq.getPageSize()));
			return contractHService.findPurchaseContractAll(cSrmContractHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseContractHR", method = RequestMethod.POST)
	@ApiOperation(value = "采购合同头行查询", notes = "采购合同头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "合同编号", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购合同查询", method = "采购合同查询")
	public Rjson findPurchaseContractHR(HttpServletRequest request, @RequestBody CSrmContractHReq cSrmContractHReq) {
		try {
			EqualsPoJoUtil.string(cSrmContractHReq.getContractNo(), "合同编号");
			return contractHService.findPurchaseContractHR(cSrmContractHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updatePurchaseOrder", method = RequestMethod.POST)
	@ApiOperation(value = "采购订单编辑", notes = "采购订单编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderType", value = "订单类型(1.办公用品采购单)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "creator", value = "创建人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "buyer", value = "采购员", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "company", value = "公司", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "currency", value = "币种(1.CNY2.USD)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待审批3.待确认4.已确认5.已拒绝)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "paymentMethod", value = "付款方式()", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "paymentClause", value = "付款条件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "remark", value = "备注", required = false, paramType = "query", dataType = "String"),
		// 行数据
		@ApiImplicitParam(name = "lineItemNo", value = "行项目号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "count", value = "数量", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "unit", value = "单位", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "unitPrice", value = "单价", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "taxRate", value = "税率", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "shippingAddress", value = "收货地址", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "expectedDateOfArrival", value = "预计到货日期", required = false, paramType = "query", dataType = "Date"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "specification", value = "规格", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "project", value = "项目", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "station", value = "工位", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purchaseRequestNo", value = "采购申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "rowProjectNumber", value = "采购申请单行号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purList", value = "采购订单行入参", required = true, paramType = "query", dataType = "Array"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.新建2.修改3.状态变更)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "采购订单编辑", method = "采购订单编辑")
	public Rjson updatePurchaseOrder(HttpServletRequest request, @RequestBody CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) {
		try {
			EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getOperationSign(), "操作标识", true);
			// 参数校验
			if (("3").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getOrderNumber(), "订单号");
				EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getStatus(), "状态", true);
			} else if (("2").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getOrderNumber(), "订单号");
			}
			// 1、2、都需要校验参数
			if (("1").equals(cSrmPurchaseOrderHReq.getOperationSign()) || ("2").equals(cSrmPurchaseOrderHReq.getOperationSign())) {
				EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getOrderType(), "订单类型", true);
				cSrmPurchaseOrderHReq.setCreator(ToolUtils.getCookieUserName(request));
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getBuyer(), "采购员");
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getCompany(), "公司");
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getSupplierCode(), "供应商");
				EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getCurrency(), "币种", true);
				EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getStatus(), "状态", true);
				// 获取当前采购员的所处部门
//                cSrmPurchaseOrderHReq.setDepartment(String.valueOf(ToolUtils.getCookieUser(request).get("department")));
				EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getPaymentMethod(), "付款方式");
//                EqualsPoJoUtil.string(cSrmPurchaseOrderHReq.getContractNo(), "合同编号");
				// 校验预计到货日期是否小于创建时间
				if (Integer.parseInt(cSrmPurchaseOrderHReq.getCurrency()) > 2) {
					return Rjson.error(400, "'currency'超出范围值");
				}
			}
//            if (Integer.parseInt(cSrmPurchaseOrderHReq.getStatus()) > 5) {
//                return Rjson.error(400, "'status'超出范围值");
//            } else if (Integer.parseInt(cSrmPurchaseOrderHReq.getOperationSign()) > 3) {
//                return Rjson.error(400, "'operationSign'超出范围值");
//            }
			return srmPurchaseOrderHService.updatePurchaseOrder(cSrmPurchaseOrderHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseOrderHR", method = RequestMethod.POST)
	@ApiOperation(value = "采购订单头行查询", notes = "采购订单头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "采购订单查询", method = "采购订单查询")
	public Rjson findPurchaseOrderHR(HttpServletRequest request, @RequestBody CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) {
		try {
			cSrmPurchaseOrderHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmPurchaseOrderHReq.getPageNum()));
			cSrmPurchaseOrderHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmPurchaseOrderHReq.getPageSize()));
			EqualsPoJoUtil.integer(cSrmPurchaseOrderHReq.getBuyerType(), "采购员类型", true);
			return srmPurchaseOrderHService.findPurchaseOrderHR(cSrmPurchaseOrderHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findPurchaseOrderH", method = RequestMethod.POST)
	@ApiOperation(value = "采购订单头查询", notes = "采购订单头查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待审批3.待确认4.已确认5.已拒绝)", required = false, paramType = "query", dataType = "List<Integer>"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "采购订单头查询", method = "采购订单头查询")
	public Rjson findPurchaseOrderH(HttpServletRequest request, @RequestBody CSrmPurchaseOrderHFindReq purchaseOrderHFindReq) {
		try {
			purchaseOrderHFindReq.setPageNum(EqualsPoJoUtil.pageNum(purchaseOrderHFindReq.getPageNum()));
			purchaseOrderHFindReq.setPageSize(EqualsPoJoUtil.pageSize(purchaseOrderHFindReq.getPageSize()));
//            EqualsPoJoUtil.string(purchaseOrderHFindReq.getSupplier(), "供应商代码");
			return srmPurchaseOrderHService.findPurchaseOrderH(purchaseOrderHFindReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findPurchaseOrderR", method = RequestMethod.POST)
	@ApiOperation(value = "采购订单行查询", notes = "采购订单行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "采购订单行查询", method = "采购订单行查询")
	public Rjson findPurchaseOrderR(HttpServletRequest request, @RequestBody CSrmPurchaseOrderHFindReq purchaseOrderHFindReq) {
		try {
			if (CollectionUtils.isEmpty(purchaseOrderHFindReq.getOrderNumberList())) {
				return Rjson.error("订单号不能为空");
			}
			purchaseOrderHFindReq.setPageNum(EqualsPoJoUtil.pageNum(purchaseOrderHFindReq.getPageNum()));
			purchaseOrderHFindReq.setPageSize(EqualsPoJoUtil.pageSize(purchaseOrderHFindReq.getPageSize()));
			return srmPurchaseOrderHService.findPurchaseOrderR(purchaseOrderHFindReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/delPurchaseOrder", method = RequestMethod.POST)
	@ApiOperation(value = "采购订单或送货单删除", notes = "采购订单或送货单删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderNumberList", value = "订单号集合", required = false, paramType = "query", dataType = "Array"),
	})
//    @OptionalLog(module = "采购", module2 = "采购订单删除", method = "采购订单删除")
	public Rjson delPurchaseOrder(HttpServletRequest request, @RequestBody CSrmPurchaseOrderHReq cSrmPurchaseOrderHReq) {
		try {
			return srmPurchaseOrderHService.delPurchaseOrder(cSrmPurchaseOrderHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateDeliverySlip", method = RequestMethod.POST)
	@ApiOperation(value = "送货单编辑", notes = "送货单编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "typeOfDeliveryNote", value = "送货单类型(1.标准送货单)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "deliveryDate", value = "发货日期", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "expectedDateOfArrival", value = "预计到货日期", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "placeOfReceipt", value = "收货地点", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pointOfDeparture", value = "发货地点", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "receivingOrganization", value = "收货组织", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "client", value = "客户", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "creator", value = "创建人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待确认3.待发货4.待收货5.已完成)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "deList", value = "送货单行数据", required = true, paramType = "query", dataType = "List"),
		@ApiImplicitParam(name = "wlList", value = "物流行数据", required = true, paramType = "query", dataType = "List"),
		@ApiImplicitParam(name = "trackingNumber", value = "物流单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "logisticsCompanyInformation", value = "物流公司信息", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "contactWay", value = "联系方式", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "shippingMethod", value = "送货方式 ( 1.人工,2.物流)", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.新建2.修改3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "送货单编辑", method = "送货单编辑")

	public Rjson updateDeliverySlip(HttpServletRequest request, @RequestBody CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		try {
			// 参数校验
			if (("3").equals(cSrmSendCommodityHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getDeliveryNumber(), "送货单号");
				EqualsPoJoUtil.integer(cSrmSendCommodityHReq.getStatus(), "状态", true);
			} else if (("2").equals(cSrmSendCommodityHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getDeliveryNumber(), "送货单号");
				// 赋值服务开关值
				cSrmSendCommodityHReq.setPush(push);
			}

			// 1、都需要校验参数
			if (("1").equals(cSrmSendCommodityHReq.getOperationSign())) {
				EqualsPoJoUtil.integer(cSrmSendCommodityHReq.getTypeOfDeliveryNote(), "送货单类型", true);
				EqualsPoJoUtil.dateYmd(cSrmSendCommodityHReq.getDeliveryDate(), "发货日期");
				EqualsPoJoUtil.dateYmd(cSrmSendCommodityHReq.getExpectedDateOfArrival(), "预计到货日期");
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getPlaceOfReceipt(), "收货地点");
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getPointOfDeparture(), "发货地点");
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getReceivingOrganization(), "收货组织");
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getClient(), "客户");
				EqualsPoJoUtil.string(cSrmSendCommodityHReq.getSupplierCode(), "供应商编码");
				cSrmSendCommodityHReq.setCreator(ToolUtils.getCookieUserName(request));
				EqualsPoJoUtil.integer(cSrmSendCommodityHReq.getStatus(), "状态", true);
				if (CollectionUtils.isEmpty(cSrmSendCommodityHReq.getDeList())) {
					return Rjson.error("'送货单行数据'不能为空");
				}
				// 校验发货日期是否小于创建日期
				if (new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getDeliveryDate()).getTime() < new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime())).getTime()) {
					return Rjson.error(400, "发货日期不能小于创建日期");
					// 校验预计到货日期是否小于创建日期
				} else if (new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getExpectedDateOfArrival()).getTime() < new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime())).getTime()) {
					return Rjson.error(400, "预计到货日期不能小于创建日期");
					// 校验预计到货日期是否小于发货日期
				} else if (new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getExpectedDateOfArrival()).getTime() < new SimpleDateFormat("yyyy-MM-dd").parse(cSrmSendCommodityHReq.getDeliveryDate()).getTime()) {
					return Rjson.error(400, "预计到货日期不能小于发货日期");
				}
			}
			EqualsPoJoUtil.integer(cSrmSendCommodityHReq.getOperationSign(), "操作标识", true);
			if (Integer.parseInt(cSrmSendCommodityHReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			} else if (Integer.parseInt(cSrmSendCommodityHReq.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			return commodityHService.updateDeliverySlip(cSrmSendCommodityHReq, srmSupplierTimer);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findDeliverySlipHR", method = RequestMethod.POST)
	@ApiOperation(value = "送货单头行查询", notes = "送货单头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplier", value = "供应商", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purchaseOrderNo", value = "采购订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "送货单查询", method = "送货单查询")

	public Rjson findDeliverySlipHR(HttpServletRequest request, @RequestBody CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		try {
			EqualsPoJoUtil.string(cSrmSendCommodityHReq.getDeliveryNumber(), "送货单号");
			cSrmSendCommodityHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmSendCommodityHReq.getPageNum()));
			cSrmSendCommodityHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmSendCommodityHReq.getPageSize()));
			return commodityHService.findDeliverySlipHR(cSrmSendCommodityHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findDeliverySlipH", method = RequestMethod.POST)
	@ApiOperation(value = "送货单头查询", notes = "送货单头查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purchaseOrderNo", value = "采购订单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "送货单查询", method = "送货单查询")

	public Rjson findDeliverySlipH(HttpServletRequest request, @RequestBody CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		try {
			cSrmSendCommodityHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmSendCommodityHReq.getPageNum()));
			cSrmSendCommodityHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmSendCommodityHReq.getPageSize()));
			return commodityHService.findDeliverySlipH(cSrmSendCommodityHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/delDeliverySlip", method = RequestMethod.POST)
	@ApiOperation(value = "送货单删除或对账开票单", notes = "送货单删除或对账开票单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderNumberList", value = "采购订单集合", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "送货单删除", method = "送货单删除")
	public Rjson delDeliverySlip(HttpServletRequest request, @RequestBody CSrmSendCommodityHReq cSrmSendCommodityHReq) {
		try {
			return commodityHService.delDeliverySlip(cSrmSendCommodityHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findNoConsignmentInvoiceR", method = RequestMethod.POST)
	@ApiOperation(value = "创建对账申请单列表查询", notes = "创建对账申请单列表查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "purchaseRequestNo", value = "采购申请号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderNumber", value = "采购单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "lineItemNo", value = "行项目号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "receivingGoodsDtStart", value = "交货时间从", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "receivingGoodsDtStop", value = "交货时间至", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "创建对账申请单列表查询", method = "创建对账申请单列表查询")
	public Rjson findNoConsignmentInvoiceR(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			cSrmTheNumberAuditHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTheNumberAuditHReq.getPageNum()));
			cSrmTheNumberAuditHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTheNumberAuditHReq.getPageSize()));
			EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getSupplierCode(), "供应商代码");
			return cSrmTheNumberAuditHService.findNoConsignmentInvoiceR(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findNoConsignmentInvoiceHR", method = RequestMethod.POST)
	@ApiOperation(value = "创建对账申请单新建头行查询", notes = "创建对账申请单新建头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplication", value = "开票申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplier", value = "供应商代码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyCode", value = "公司", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderNumber", value = "采购单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "lineItemNo", value = "行项目号", required = true, paramType = "query", dataType = "Array"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "创建对账申请单新建头行查询", method = "创建对账申请单新建头行查询")
	public Rjson findNoConsignmentInvoiceHR(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			cSrmTheNumberAuditHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTheNumberAuditHReq.getPageNum()));
			cSrmTheNumberAuditHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTheNumberAuditHReq.getPageSize()));
			EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getOrderNumber(), "采购订单号");
			return cSrmTheNumberAuditHService.findNoConsignmentInvoiceHR(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateInvoicingApplication", method = RequestMethod.POST)
	@ApiOperation(value = "对账申请编辑", notes = "对账申请编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplication", value = "开票申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplier", value = "供应商", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待确认3.已完成)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "creator", value = "创建人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "confirmationP", value = "确认人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "confirmationTime", value = "确认时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "reqList", value = "开票申请行数据", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.新建2.修改3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "对账申请编辑", method = "对账申请编辑")
	public Rjson updateInvoicingApplication(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			// 参数校验
			if (("3").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication(), "开票申请单号");
				EqualsPoJoUtil.integer(cSrmTheNumberAuditHReq.getStatus(), "状态", true);
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getSupplierCode(), "供应商编码");
			} else if (("2").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication(), "开票申请单号");
			}

			// 1、2、都需要校验参数
			if (("1").equals(cSrmTheNumberAuditHReq.getOperationSign()) || ("2").equals(cSrmTheNumberAuditHReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getSupplierCode(), "供应商编码");
				EqualsPoJoUtil.integer(cSrmTheNumberAuditHReq.getStatus(), "状态", true);
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getCreator(), "创建人");
				// 校验创建人是否包含特殊字符
				FormatUtils.validateLegalString(cSrmTheNumberAuditHReq.getCreator(), "创建人");
				EqualsPoJoUtil.date(cSrmTheNumberAuditHReq.getCreateTime(), "创建时间");
				EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getDeliveryNumber(), "送货单号");
			}

			EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getOperationSign(), "操作标识");
			if (Integer.parseInt(cSrmTheNumberAuditHReq.getStatus()) > 3) {
				return Rjson.error(400, "'status'超出范围值");
			} else if (Integer.parseInt(cSrmTheNumberAuditHReq.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			return cSrmTheNumberAuditHService.updateInvoicingApplication(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findInvoicingApplication", method = RequestMethod.POST)
	@ApiOperation(value = "对账信息展示查询", notes = "对账信息展示查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplication", value = "开票申请单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "当前页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "条数", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "status", value = "状态(1.新建2.待确认3.已完成4.审批失败)", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "对账信息展示查询", method = "对账信息展示查询")
	public Rjson findInvoicingApplication(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			cSrmTheNumberAuditHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTheNumberAuditHReq.getPageNum()));
			cSrmTheNumberAuditHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTheNumberAuditHReq.getPageSize()));
			return cSrmTheNumberAuditHService.findInvoicingApplication(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findNoInvoiceMaintenanceSave", method = RequestMethod.POST)
	@ApiOperation(value = "对账申请头行查询", notes = "对账申请头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplication", value = "开票申请单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),

	})
//    @OptionalLog(module = "采购", module2 = "对账申请头行查询", method = 对账申请头行查询
	public Rjson findNoInvoiceMaintenanceSave(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			cSrmTheNumberAuditHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTheNumberAuditHReq.getPageNum()));
			cSrmTheNumberAuditHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTheNumberAuditHReq.getPageSize()));
			EqualsPoJoUtil.string(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplication(), "开票申请单号");
			return cSrmTheNumberAuditHService.findNoInvoiceMaintenanceSave(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/findNoConsignmentInvoiceBatchHR", method = RequestMethod.POST)
	@ApiOperation(value = "查询对账申请转应收发票数据", notes = "查询对账申请转应收发票数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplicationList", value = "开票申请单号集合", required = false, paramType = "query", dataType = "List<String>"),
	})
//    @OptionalLog(module = "采购", module2 = "查询对账申请转应收发票数据", method = "查询对账申请转应收发票数据")
	public Rjson findNoConsignmentInvoiceBatchHR(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq cSrmTheNumberAuditHReq) {
		try {
			cSrmTheNumberAuditHReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmTheNumberAuditHReq.getPageNum()));
			cSrmTheNumberAuditHReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmTheNumberAuditHReq.getPageSize()));
			if (CollectionUtils.isEmpty(cSrmTheNumberAuditHReq.getTheNumberOfHeInvoiceApplicationList())) {
				return Rjson.success();
			}
			return cSrmTheNumberAuditHService.findNoConsignmentInvoiceBatchHR(cSrmTheNumberAuditHReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/delInvoicingApplication", method = RequestMethod.POST)
	@ApiOperation(value = "对账申请单或应收应付单删除", notes = "对账申请单或应收应付单删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "theNumberOfHeInvoiceApplication", value = "对账申请号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "deliveryNumber", value = "送货单号", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "对账申请单删除", method = "对账申请单删除")
	public Rjson delInvoicingApplication(HttpServletRequest request, @RequestBody CSrmTheNumberAuditHReq req) {
		try {
			return cSrmTheNumberAuditHService.delInvoicingApplication(req);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateInvoiceReceivable", method = RequestMethod.POST)
	@ApiOperation(value = "应收/付发票编辑", notes = "应收/付发票编辑")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "invoiceReceivableNo", value = "应收发票编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "dateOfIssue", value = "开票日期", required = true, paramType = "query", dataType = "Date"),
		@ApiImplicitParam(name = "taxInvoiceCode", value = "税务发票代码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商编码", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceGrossAmount", value = "发票总额", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierName", value = "供应商名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "taxMoneyCountMoney", value = "含税总额(系统)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "theInvoiceAmount", value = "发票税额", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "address", value = "供应商地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sumTax", value = "税额(系统)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "开具状态(1.新建2.待审核3.待付款4.待收款5.已完成)", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "remark", value = "备注", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceReceivableNoList", value = "行数据", required = true, paramType = "query", dataType = "List"),
//            @ApiImplicitParam(name = "creator", value = "创建人", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "createTime", value = "创建时间", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "payer", value = "付款人", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "payTime", value = "付款时间", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "acknowledgingTime", value = "确认时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "operationSign", value = "操作标识(1.新建2.修改3.变更状态)", required = true, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "应收/付发票编辑", method = "应收/付发票编辑")
	public Rjson updateInvoiceReceivable(HttpServletRequest request, @RequestBody CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {
		try {
			// 参数校验
			if (("3").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceReceivableNo(), "应收发票编号");
				EqualsPoJoUtil.integer(cSrmInvoiceReceivableReq.getStatus(), "状态", true);
				// 赋值服务开关值
				cSrmInvoiceReceivableReq.setPush(push);
			} else if (("2").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceReceivableNo(), "应收发票编号");
			}

			// 1、2、都需要校验参数
			if (("1").equals(cSrmInvoiceReceivableReq.getOperationSign())) {
				EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getSupplierCode(), "供应商代码");
//                EqualsPoJoUtil.dateYmd(cSrmInvoiceReceivableReq.getDateOfIssue(), "开票日期");
//                EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getTaxInvoiceCode(), "税务发票代码");
//                EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceGrossAmount(), "发票总额");
//                EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getTheInvoiceAmount(), "发票税额");
//                EqualsPoJoUtil.integer(cSrmInvoiceReceivableReq.getStatus(), "状态", true);
				// 创建人
				cSrmInvoiceReceivableReq.setCreator(ToolUtils.getCookieUserName(request));
//                EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getTheInvoiceAmount(), "发票税额");
			}
			EqualsPoJoUtil.integer(cSrmInvoiceReceivableReq.getOperationSign(), "操作标识", true);
			if (Integer.parseInt(cSrmInvoiceReceivableReq.getStatus()) > 5) {
				return Rjson.error(400, "'status'超出范围值");
			} else if (Integer.parseInt(cSrmInvoiceReceivableReq.getOperationSign()) > 3) {
				return Rjson.error(400, "'operationSign'超出范围值");
			}
			return cSrmInvoiceReceivableService.updateInvoiceReceivable(cSrmInvoiceReceivableReq, srmSupplierTimer);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findInvoiceReceivableH", method = RequestMethod.POST)
	@ApiOperation(value = "应收/付发票头查询", notes = "应收/付发票头查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "invoiceReceivableNo", value = "应收发票编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "应收/付发票查询", method = "应收/付发票查询")
	public Rjson findInvoiceReceivableH(HttpServletRequest request, @RequestBody CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {
		try {
			cSrmInvoiceReceivableReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvoiceReceivableReq.getPageNum()));
			cSrmInvoiceReceivableReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvoiceReceivableReq.getPageSize()));
			return cSrmInvoiceReceivableService.findInvoiceReceivableH(cSrmInvoiceReceivableReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findInvoiceReceivableHR", method = RequestMethod.POST)
	@ApiOperation(value = "应收/付发票头行查询", notes = "应收/付发票头行查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "invoiceReceivableNo", value = "应收发票编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "应收/付发票查询", method = "应收/付发票查询")
	public Rjson findInvoiceReceivableHR(HttpServletRequest request, @RequestBody CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {
		try {
			cSrmInvoiceReceivableReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmInvoiceReceivableReq.getPageNum()));
			cSrmInvoiceReceivableReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmInvoiceReceivableReq.getPageSize()));
			EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceReceivableNo(), "应收发票编号");
			return cSrmInvoiceReceivableService.findInvoiceReceivableHR(cSrmInvoiceReceivableReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/delInvoiceReceivable", method = RequestMethod.POST)
	@ApiOperation(value = "应收/付发票删除", notes = "应收/付发票删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "invoiceReceivableNo", value = "应收发票编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceApplicationNumber", value = "对账申请号", required = false, paramType = "query", dataType = "String"),
	})
//    @OptionalLog(module = "采购", module2 = "应收/付发票删除", method = "应收/付发票删除")
	public Rjson delInvoiceReceivable(HttpServletRequest request, @RequestBody CSrmInvoiceReceivableReq cSrmInvoiceReceivableReq) {
		try {
			EqualsPoJoUtil.string(cSrmInvoiceReceivableReq.getInvoiceReceivableNo(), "应收发票编号");
			return cSrmInvoiceReceivableService.delInvoiceReceivable(cSrmInvoiceReceivableReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/createContractTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "合同模板创建", notes = "合同模板创建")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateNumber", value = "合同模板编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateName", value = "合同模板名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "adapterCompany", value = "分配适用公司", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "formWorkAccessory", value = "模板文件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "isStart", value = "是否启用(1.禁用2.启用)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateType", value = "合同类型(1.普通合同)", required = false, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "采购", module2 = "合同模板编辑", method = "合同模板编辑")
	public Rjson createContractTemplate(HttpServletRequest request, @RequestBody List<CSrmContractTemplateReq> cSrmContractTemplateReq) {
		try {
			return contractTemplateService.createContractTemplate(cSrmContractTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateContractTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "合同模板修改", notes = "合同模板修改")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "templateNumber", value = "合同模板编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateName", value = "合同模板名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "adapterCompany", value = "分配适用公司", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "formWorkAccessory", value = "模板文件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "isStart", value = "是否启用(1.禁用2.启用)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateType", value = "合同类型(1.普通合同)", required = false, paramType = "query", dataType = "String")
	})
//    @OptionalLog(module = "采购", module2 = "合同模板修改", method = "合同模板修改")
	public Rjson updateContractTemplate(HttpServletRequest request, @RequestBody List<CSrmContractTemplateReq> cSrmContractTemplateReq) {
		try {
			return contractTemplateService.updateContractTemplate(cSrmContractTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@RequestMapping(value = "/findContractTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "合同模板查询", notes = "合同模板查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateNumber", value = "合同模板编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateName", value = "合同模板名称", required = false, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "adapterCompany", value = "分配适用公司", required = false, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "formWorkAccessory", value = "模板文件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "isStart", value = "是否启用(1.禁用2.启用)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateType", value = "合同类型(1.普通合同)", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "合同模板查询", method = "合同模板查询")
	public Rjson findContractTemplate(HttpServletRequest request, @RequestBody CSrmContractTemplateReq cSrmContractTemplateReq) {
		try {
			cSrmContractTemplateReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmContractTemplateReq.getPageNum()));
			cSrmContractTemplateReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmContractTemplateReq.getPageSize()));
			return contractTemplateService.findContractTemplate(cSrmContractTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}

	}

	@RequestMapping(value = "/findByPrimaryList", method = RequestMethod.POST)
	@ApiOperation(value = "查询合同模板编号及名称", notes = "查询合同模板编号及名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "templateNumber", value = "合同模板编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "templateName", value = "合同模板名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "查询合同模板编号及名称", method = "查询合同模板编号及名称")
	public Rjson findByPrimaryList(HttpServletRequest request, @RequestBody CSrmContractTemplateReq cSrmContractTemplateReq) {
		try {
			cSrmContractTemplateReq.setPageNum(EqualsPoJoUtil.pageNum(cSrmContractTemplateReq.getPageNum()));
			cSrmContractTemplateReq.setPageSize(EqualsPoJoUtil.pageSize(cSrmContractTemplateReq.getPageSize()));
			return contractTemplateService.findByPrimaryList(cSrmContractTemplateReq);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/delContractTemplate", method = RequestMethod.POST)
	@ApiOperation(value = "合同模板删除", notes = "合同模板删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query", dataType = "Integer"),
	})
//    @OptionalLog(module = "采购", module2 = "合同模板删除", method = "合同模板删除")
	public Rjson delContractTemplate(HttpServletRequest request, @RequestBody List<Integer> id) {
		try {
			return contractTemplateService.delContractTemplate(id);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}

	@Resource
	private CSrmFileUploadingService cSrmFileUploadingService;

	/**
	 * 文件上传路径
	 */
	@Value(value = "${fileName.srmName}")
	private String pathFile;

	@RequestMapping(value = "/FileUpload", method = RequestMethod.POST)
	@ApiOperation(value = "文件上传", notes = "文件上传")
	@ApiImplicitParams({
//            @ApiImplicitParam(name = "fileName", value = "存放服务器的文件夹名称(合同：contract,采购：purchase,寻源/招投标：searchSource)", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "id", value = "主键", required = false, paramType = "query", dataType = "Integer"),
//            @ApiImplicitParam(name = "filePath", value = "文件路径", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "flag", value = "标记(1.采购文件,2供应商文件)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "lineNumber", value = "行号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "String"),
	})
	public Rjson FileUpload(HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] file) throws Exception {
//        EqualsPoJoUtil.string(fileName, "文件夹名称");
		// 校验文件格式
		String fileName = request.getParameter("fileName");
		String orderNumber = request.getParameter("orderNumber");
		int flag = Integer.parseInt(request.getParameter("flag"));
		String lineNumber = request.getParameter("lineNumber");
		String supplierCode = request.getParameter("supplierCode");
		EqualsPoJoUtil.string(fileName, "文件名");
		EqualsPoJoUtil.string(orderNumber, "订单号");
		EqualsPoJoUtil.integer(String.valueOf(flag), "标记", true);
		// flag标识为供应商文件时有供应商代码
		if (2 == flag) {
			EqualsPoJoUtil.string(supplierCode, "供应商代码");
		}
		JSONObject json = FileUploadUtils.upLoadFile(file, fileName, orderNumber, lineNumber, flag, supplierCode, pathFile);
		JSONArray objectsAdd = JSONArray.parseArray(json.getString("addList"));
		JSONArray objectsUpdate = JSONArray.parseArray(json.getString("updateList"));
		List<CSrmFileUploading> addList = null;
		List<CSrmFileUploading> updateList = null;
		if (null != objectsAdd) {
			addList = objectsAdd.toJavaList(CSrmFileUploading.class);
		}
		if (null != objectsUpdate) {
			updateList = objectsUpdate.toJavaList(CSrmFileUploading.class);
		}

		if (!CollectionUtils.isEmpty(addList)) {
			// 保存新文件记录
			cSrmFileUploadingService.batchInsert(addList);
		}
		if (!CollectionUtils.isEmpty(updateList)) {
			updateList.forEach(item -> {
				// 更新文件创建日期
				item.setCreateTime(new Date());
				cSrmFileUploadingService.updateByPrimaryKeySelective(item);
			});
		}
		return Rjson.success();
	}

	@RequestMapping(value = "/FileDownload", method = RequestMethod.POST)
	@ApiOperation(value = "文件下载", notes = "文件下载")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "filePath", value = "文件路径", required = true, paramType = "query", dataType = "String"),
	})
	public Rjson FileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String filePath = request.getParameter("filePath");
			EqualsPoJoUtil.string(filePath, "文件路径");
//            EqualsPoJoUtil.string(fileName, "存放服务器的文件名");
//            EqualsPoJoUtil.integer(openMode, "显示方式", true);
//            if (Integer.parseInt(openMode) > 2) {
//                return Rjson.error("'openMode'超出范围值");
//            }
//            File file = new File(request.getServletContext().getRealPath("/"));
//            filePath = file.getParentFile().getParent() + "/fileManagement/" + fileName + "/" + filePath;

			// 判断，该路径是否存在
//            if (!new File(filePath).exists()) {
//                return Rjson.error("出现异常，'" + fileName + "'文件不存在");
//            }

			FileDownloadUtils.downloadStream(response, filePath);
		} catch (Exception e) {
//            return Rjson.error(e.getMessage());
			System.err.println("文件下载异常：" + e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/FileShow", method = RequestMethod.POST)
	@ApiOperation(value = "文件展示", notes = "文件展示")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNumber", value = "订单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "lineNumber", value = "行号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "flag", value = "标记(1.采购文件,2供应商文件)", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "supplierCode", value = "供应商代码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "onlyPurShow", value = "仅采购可看权限( 0.不可见、1.可见)", required = false, paramType = "query", dataType = "Integer"),
	})
	public Rjson FileShow(HttpServletRequest request, @RequestBody CSrmFileUploadingReq req) throws Exception {
		try {
			EqualsPoJoUtil.string(req.getOrderNumber(), "订单号");
			EqualsPoJoUtil.integer(String.valueOf(req.getFlag()), "标记", true);
			// 添加供应商查看采购文件的权限控制
			if (req.getFlag().equals(1) && StringUtil.eqNu(req.getSupplierCode())) {
				// 添加查询可见文件条件
				req.setOnlyPurShow(1);
				// 清除供应商值
				req.setSupplierCode("");
			}
			return Rjson.success(cSrmFileUploadingService.selectByPrimaryKey(req));
		} catch (Exception e) {
			return Rjson.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/FileDel", method = RequestMethod.POST)
	@ApiOperation(value = "文件删除", notes = "文件删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "文件ID", required = true, paramType = "query", dataType = "String"),
	})
	public Rjson FileDel(HttpServletRequest request, @RequestBody CSrmFileUploadingReq req) throws Exception {
		try {
			if (CollectionUtils.isEmpty(req.getDelList())) {
				return Rjson.success();
			}
			return Rjson.success(cSrmFileUploadingService.deleteByPrimaryKey(req.getDelList()));
		} catch (Exception e) {
			return Rjson.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateFileOnlyPurShow", method = RequestMethod.POST)
	@ApiOperation(value = "修改文件可见性", notes = "修改文件可见性")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "文件ID", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "onlyPurShow", value = "仅采购可看权限( 0.不可见、1.可见)", required = false, paramType = "query", dataType = "String"),
	})
	public Rjson updateFileOnlyPurShow(HttpServletRequest request, @RequestBody CSrmFileUploadingReq req) throws Exception {
		try {
			EqualsPoJoUtil.integer(String.valueOf(req.getId()), "文件ID", true);
			EqualsPoJoUtil.integer(String.valueOf(req.getOnlyPurShow()), "权限标识", false);
			CSrmFileUploading cSrmFileUploading = new CSrmFileUploading();
			cSrmFileUploading.setId(req.getId());
			cSrmFileUploading.setOnlyPurShow(req.getOnlyPurShow());
			cSrmFileUploading.setUpdateTime(new Date());
			cSrmFileUploadingService.updateByPrimaryKeySelective(cSrmFileUploading);
			return Rjson.success();
		} catch (Exception e) {
			return Rjson.error(e.getMessage());
		}
	}


	@Autowired
	private SrmPoRequestTimer srmPoRequestTimer;

	@Autowired
	private SrmPoOrderTimer srmPoOrderTimer;

//    @Resource
//    private SrmSupplierTimer srmSupplierTimer;

	@Resource
	private CSrmPurchaseDemandRMapper cSrmPurchaseDemandRMapper;

	@Resource
	private CSrmPurchaseDemandHMapper cSrmPurchaseDemandHMapper;

	@Resource
	private CSrmPurchaseOrderHMapper cSrmPurchaseOrderHMapper;

	@Resource
	private CSrmPurchaseOrderRMapper cSrmPurchaseOrderRMapper;


	@RequestMapping(value = "/getOrderInfoInit", method = RequestMethod.POST)
	@ApiOperation(value = "初始化采购申请、采购订单数据初始化", notes = "初始化采购申请、采购订单数据初始化")
	public Rjson getOrderInfoInit(HttpServletRequest request) {
		try {
			KThreePORequestResult requestResultRsp = JSONObject.parseObject(srmPoRequestTimer.sendPost(""), KThreePORequestResult.class);
			// 根据ID去重
			ArrayList<KThreePORequest> collect = requestResultRsp.getData().parallelStream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
				Comparator.comparing(KThreePORequest::getID)
			)), ArrayList::new));
			// 批量新增头数据
			cSrmPurchaseDemandHMapper.batchInsertKThree(collect);
			// 批量新增行数据
			cSrmPurchaseDemandRMapper.batchInsertKThree(requestResultRsp.getData());

			KThreePOOrderResult orderResult = JSONObject.parseObject(srmPoOrderTimer.sendPost(""), KThreePOOrderResult.class);
			// 根据ID去重
			ArrayList<KThreePOOrder> collects = orderResult.getData().parallelStream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
				Comparator.comparing(KThreePOOrder::getID)
			)), ArrayList::new));
			// 批量新增头数据
			cSrmPurchaseOrderHMapper.batchInsertKThree(collects);
			// 批量新增行数据
			cSrmPurchaseOrderRMapper.batchInsertKThree(orderResult.getData());
			return Rjson.success("初始化成功", null);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@Resource
	private CSrmSupplierMapper cSrmSupplierMapper;

	@Resource
	private CSrmBankMapper cSrmBankMapper;

	@Resource
	private CSrmCompanyMapper cSrmCompanyMapper;

	@Resource
	private UserDao userDao;

	@Resource
	private CSrmLinkmanMapper cSrmLinkmanMapper;

	@Autowired
	ISysUserServiceFeignClient iSysUserServiceFeignClient;

	@RequestMapping(value = "/getSupplierInfoInit", method = RequestMethod.POST)
	@ApiOperation(value = "获取K3供应商信息初始化", notes = "获取K3供应商信息初始化")
	public Rjson getSupplierInfoInit(HttpServletRequest request) {
		try {
			int count = 0;
			// 封装请求参数
			Map<String, Object> map = new HashMap<>();
			map.put("jktype", "Supplier");
			map.put("method", "view");
			map.put("filter", "");
			KThreeSupplierResult supplierResult = JSONObject.parseObject(srmSupplierTimer.sendPost(map), KThreeSupplierResult.class);
			List<String> suppliers = new ArrayList<>();
			for (KThreeSupplier d : supplierResult.getData()) {
				// 根据供应商代码过滤供应商处理
//                if (d.getSupplierCode().equals("1336") || d.getSupplierCode().equals("909") || d.getSupplierCode().equals("1412") || d.getSupplierCode().equals("935")) {
//                    count++;
//                    continue;
//                }
				d.setPassword(Encryption.getPassWord("123456" + d.getAccount() + 666666 + "123456", 555));
				CSrmSupplierReq req = new CSrmSupplierReq();
				BeanUtils.copyProperties(d, req);

				// 查询供应商账号是否存在
				CMesUserT user = userDao.findByName(req.getAccount());
				if (null == user) {
					// 校验供应商名称是否存在
					String supplierCode = "";
					Long roleId = 0L;
					CSrmSupplier cSrmSupplier = new CSrmSupplier();
					cSrmSupplier.setName(req.getName());
					List<CSrmSupplier> selectByPrimaryKey = cSrmSupplierMapper.selectByPrimaryKey(cSrmSupplier);
					if (!CollectionUtils.isEmpty(selectByPrimaryKey)) {
						supplierCode = selectByPrimaryKey.get(0).getSupplierCode();
						cSrmSupplier.setSupplierCode(supplierCode);
						// 该供应商所属公司已完成认证
						// 设置已认证状态及权限
						cSrmSupplier.setStatus(3);
						roleId=Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_AUTHENTICATED, "SRM已认证权限"));
					} else {
						// 设置已认证权限
						cSrmSupplier.setStatus(3);
						roleId=Long.parseLong(CommonUtils.getRedisValue(redisCache, SrmFinal.SRM_AUTHENTICATED, "SRM已认证权限"));
						BeanUtils.copyProperties(req, cSrmSupplier);
						cSrmSupplier.setSupplierCode(d.getSupplierCode());
						cSrmSupplier.setCreateTime(new Date());
						// 不存储账号、密码
						cSrmSupplier.setAccount("");
						cSrmSupplier.setPassword("");
						cSrmSupplier.setName(d.getName());
						cSrmSupplierMapper.insertSelective(cSrmSupplier);
					}
					// 新增用户表
//					CMesUserTReq reqs = new CMesUserTReq();
//					reqs.setUserName(req.getAccount());
//					reqs.setUserPwd(req.getPassword());
//					reqs.setViewmode(1);
//					reqs.setRoleId(roleId);
//					// 0.冻结 1.正常
//					reqs.setStatus("1");
//					reqs.setSupplierCode(cSrmSupplier.getSupplierCode());
//					reqs.setName(d.getContactPerson());
//					reqs.setMobile(d.getContactNumber());
//					userDao.insertSelective(reqs);


					SysUser sysUser = new SysUser();
					sysUser.setUserName(req.getAccount());
					sysUser.setStatus("1");
					sysUser.setRoleIds(new Long[]{roleId});
					sysUser.setDeptId(Long.parseLong(CommonUtils.getRedisValue(redisCache,SrmFinal.SUPPLIER_DEPARTMENT,"SRM供应商部门id")));
					sysUser.setUserCode(cSrmSupplier.getSupplierCode());
					sysUser.setRankName(cSrmSupplier.getContactPerson());
					sysUser.setPwd(req.getPassword());
					sysUser.setSupplierCode(cSrmSupplier.getSupplierCode());
					AjaxResult result = iSysUserServiceFeignClient.addUserInfo(sysUser);
					if (HttpStatus.OK.value() != result.getCode()) {
						throw new CustomException(result.getMsg());
					}
					log.info("【供应商注册出参】[{}]", JSONUtil.toJsonStr(result));

					// 联系人表
					if (StringUtil.eqNu(req.getContactPerson())) {
						CSrmLinkman linkman = new CSrmLinkman();
						linkman.setName(req.getContactPerson());
						// 校验联系人名称是否已经存在
						if (null == cSrmLinkmanMapper.selectByPrimaryKeyList(linkman)) {
							linkman.setPhone(req.getContactNumber());
							linkman.setEmail(req.getContactEmail());
							linkman.setName(req.getContactPerson());
							linkman.setSupplierCode(cSrmSupplier.getSupplierCode());
							linkman.setCreateTime(new Date());
							cSrmLinkmanMapper.insertSelective(linkman);
						}
					}
				} else {
					suppliers.add(req.getAccount());
				}
			}

			// -------------------------------------供应商银行信息表-------------------------------------------
			// 开户行、银行账号、税号
			cSrmBankMapper.batchInsertKThree(supplierResult.getData());
			// -------------------------------------公司表-------------------------------------------
			cSrmCompanyMapper.batchInsertKThree(supplierResult.getData());
			System.err.println("已存在的账号" + suppliers);
			System.err.println("已过滤次数" + count);
			return Rjson.success("初始化成功", null);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


	@RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST)
	@ApiOperation(value = "批量更改用户密码为123456", notes = "批量更改用户密码为123456")
	public Rjson updateUserPwd(HttpServletRequest request) {
		try {
			// 获取需要更改数据
			List<CMesUserTReq> list = userDao.findUpdateData();
			// 加密
			list.forEach(item -> {
				item.setUserPwd(Encryption.getPassWord("123456" + item.getUserName() + 666666 + "123456", 555));
			});
			userDao.updateBatchSelective(list);
			return Rjson.success("修改成功", null);
		} catch (Exception e) {
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(400, e.getMessage());
		}
	}


}
