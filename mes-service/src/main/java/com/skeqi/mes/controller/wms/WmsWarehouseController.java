package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.WarehouseT;
import com.skeqi.mes.service.wms.WmsWarehouseService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 仓库管理
 *
 * @author zhangran
 *
 */
@RestController
@RequestMapping(value = "wms/warehouse", produces = MediaType.APPLICATION_JSON)
@Api(value = "仓库管理", description = "仓库管理", produces = MediaType.APPLICATION_JSON)
public class WmsWarehouseController {

	@Autowired
	WmsWarehouseService service;

	/**
	 * 查询所有用户ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有用户ID、NAME", notes = "查询所有用户ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findUserAll", method = RequestMethod.POST)
	public Rjson findUserAll(HttpServletResponse response) throws IOException {
		try {
			List<JSONObject> list = service.findUserAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询仓库集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询仓库集合", notes = "查询仓库集合")
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) throws IOException, ServicesException {
		try {

			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			PageHelper.startPage(pageNumber, pageSize);
			List<WarehouseT> lineList = service.findWarehouseList(null);
			PageInfo<WarehouseT> pageInfo = new PageInfo<WarehouseT>(lineList, 5);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.success(e.getMessage());
		}
	}

	/**
	 * 新增仓库
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "新增仓库", notes = "新增仓库")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "仓库名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "manager", value = "负责人，员工id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "operations", value = "操作员，存储员工ID", required = false, paramType = "query"),
			@ApiImplicitParam(name = "managerTele", value = "负责人电话", required = false, paramType = "query"),
			@ApiImplicitParam(name = "address", value = "仓库地址", required = false, paramType = "query"),
			@ApiImplicitParam(name = "note", value = "备注", required = false, paramType = "query"),
			@ApiImplicitParam(name = "defaultM", value = "默认仓库；0：否 1：是", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="仓库管理", method="新增仓库")
	public Rjson addWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			WarehouseT dx = new WarehouseT();

			dx.setName(EqualsUtil.string(request, "name", "仓库名称", true));
			dx.setManager(EqualsUtil.integer(request, "manager", "负责人Id", false));
			dx.setOperations(EqualsUtil.string(request, "operations", "操作员", false));
			dx.setManagerTele(EqualsUtil.string(request, "managerTele", "负责人电话", false));
			dx.setAddress(EqualsUtil.string(request, "address", "仓库地址", false));
			dx.setNote(EqualsUtil.string(request, "note", "备注", false));
			dx.setDefaultM(EqualsUtil.integer(request, "defaultM", "是否默认仓库", true));

			service.addWarehouse(dx);

			return Rjson.success(true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新仓库
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "更新仓库", notes = "更新仓库")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "仓库id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "name", value = "仓库名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "manager", value = "负责人，员工id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "operations", value = "操作员，存储员工ID", required = false, paramType = "query"),
			@ApiImplicitParam(name = "managerTele", value = "负责人电话", required = false, paramType = "query"),
			@ApiImplicitParam(name = "address", value = "仓库地址", required = false, paramType = "query"),
			@ApiImplicitParam(name = "note", value = "备注", required = false, paramType = "query"),
			@ApiImplicitParam(name = "defaultM", value = "默认仓库；0：否 1：是", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="仓库管理", method="编辑仓库")
	public Rjson updateWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			WarehouseT dx = new WarehouseT();
			dx.setId(EqualsUtil.integer(request, "id", "仓库Id", true));
			dx.setName(EqualsUtil.string(request, "name", "仓库名称", true));
			dx.setManager(EqualsUtil.integer(request, "manager", "负责人Id", false));
			dx.setOperations(EqualsUtil.string(request, "operations", "操作员", false));
			dx.setManagerTele(EqualsUtil.string(request, "managerTele", "负责人电话", false));
			dx.setAddress(EqualsUtil.string(request, "address", "仓库地址", false));
			dx.setNote(EqualsUtil.string(request, "note", "备注", false));
			dx.setDefaultM(EqualsUtil.integer(request, "defaultM", "是否默认仓库", true));

			int res = service.updateWarehouse(dx);
			if (res==1) {
				return Rjson.success("更新成功", true);
			} else {
				throw new Exception("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除仓库
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除仓库", notes = "删除仓库")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "仓库id", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="仓库管理", method="删除仓库")
	public Rjson deleteWarehouse(HttpServletRequest request) {
		try {
			Integer warehouseId = EqualsUtil.integer(request, "id", "仓库id", true);
			int res = service.deleteWarehouse(warehouseId);
			if (res == 1) {
				return Rjson.success(true);
			} else {
				throw new Exception("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 修改仓库viewMode状态
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改仓库viewMode状态", notes = "修改仓库viewMode状态")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "仓库id", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "viewMode", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="仓库管理", method="修改仓库状态")
	public Rjson viewMode(HttpServletRequest request){
		try {
			WarehouseT dx = new WarehouseT();
			dx.setId(EqualsUtil.integer(request, "id", "仓库id", true));
			dx.setViewMode(0);
			int res = service.updateWarehouse(dx);
			if (res == 1) {
				return Rjson.success(true);
			} else {
				throw new Exception("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

}
