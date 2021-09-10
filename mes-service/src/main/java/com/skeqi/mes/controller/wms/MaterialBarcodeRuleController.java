package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsMaterialBarcodeRuleT;
import com.skeqi.mes.service.wms.MaterialBarcodeRuleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 物料条码规则
 *
 * @author Administrator
 *
 */
@RestController
@RequestMapping("wms/materialBarcodeRule")
public class MaterialBarcodeRuleController {

	@Autowired
	MaterialBarcodeRuleService service;

	/**
	 * 查询集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsMaterialBarcodeRuleT> lineList = service.findMaterialBarcodeRuleList(null);
			PageInfo<CWmsMaterialBarcodeRuleT> pageInfo = new PageInfo<CWmsMaterialBarcodeRuleT>(lineList, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增
	 *
	 * @param request
	 */
	@ApiOperation(value = "新增", notes = "新增")
	@ApiImplicitParams({ @ApiImplicitParam(name = "rule", value = "规则", required = true, paramType = "query"),
			@ApiImplicitParam(name = "condition", value = "条件", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="物料条码规则", method="新增物料条码规则")
	public Rjson add(HttpServletRequest request) {
		try {
			CWmsMaterialBarcodeRuleT dx = new CWmsMaterialBarcodeRuleT();
			dx.setRule(EqualsUtil.string(request, "rule", "规则", true));
			dx.setCondition(EqualsUtil.string(request, "condition", "条件", true));

			int res = service.addMaterialBarcodeRule(dx);
			if (res==1) {
				return Rjson.success();
			} else {
				throw new Exception("新增失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改", notes = "修改")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "rule", value = "规则", required = false, paramType = "query"),
			@ApiImplicitParam(name = "condition", value = "条件", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="物料条码规则", method="编辑物料条码规则")
	public Rjson update(HttpServletRequest request) {
		try {
			CWmsMaterialBarcodeRuleT dx = new CWmsMaterialBarcodeRuleT();
			dx.setId(EqualsUtil.integer(request, "id", "id", true));
			dx.setRule(EqualsUtil.string(request, "rule", "规则", true));
			dx.setCondition(EqualsUtil.string(request, "condition", "条件", true));

			int res = service.updateMaterialBarcodeRule(dx);
			if (res==1) {
				return Rjson.success();
			} else {
				throw new Exception("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="物料条码规则", method="删除物料条码规则")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			int res = service.deleteMaterialBarcodeRule(id);
			if (res==1) {
				return Rjson.success();
			} else {
				throw new Exception("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		String str = "物料.规格=1;";
		String string = str.substring(0, str.indexOf(";"));
		System.out.println(string);
	}

}
