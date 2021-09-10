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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;
import com.skeqi.mes.service.wms.AreaService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 区域管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value = "wms/area", produces = MediaType.APPLICATION_JSON)
@Api(value = "区域管理", description = "区域管理", produces = MediaType.APPLICATION_JSON)
public class AreaController {

	@Autowired
	AreaService service;

	/**
	 * 查询所有仓库ID、NAME
	 */
	@ApiOperation(value = "查询所有仓库ID、NAME", notes = "查询所有仓库ID、NAME")
	@RequestMapping(value = "findWarehouseAll", method = RequestMethod.POST)
	public Rjson findAll(){
		try {
			List<WarehouseT> list = service.findWarehouseAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改区域viewMode状态 用于删除
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除区域", notes = "删除区域")
	@ApiImplicitParams({ @ApiImplicitParam(name = "areaId", value = "区域id", required = true, paramType = "query")})
	@Transactional
	@RequestMapping(value = "updateAreaViewMode", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="区域管理", method="删除区域")
	public Rjson updateAreaViewMode(HttpServletRequest request){
		try {

			CWmsAreaT dx = new CWmsAreaT();
			dx.setId(EqualsUtil.integer(request, "areaId", "区域id", true));
			dx.setViewMode(0);

			int res = service.updateArea(dx);
			if (res==1) {
				return Rjson.success("删除成功", true);
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
	 * 查询区域集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询区域集合", notes = "查询区域集合")
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request){
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsAreaT> lineList = service.findAreaList(null);
			PageInfo<CWmsAreaT> pageInfo = new PageInfo<CWmsAreaT>(lineList, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}


	/**
	 * 新增区域
	 *
	 * @param request
	 */
	@ApiOperation(value = "新增区域", notes = "新增区域")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "areaNo", value = "区域编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaName", value = "区域名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaType", value = "区域类型，0：立库 1：平库 2：OTHER", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaLength", value = "区域长度，单位M", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaWidth", value = "区域宽度，单位M", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaHight", value = "区域高度，单位M", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaLimit", value = "区域上限，单位M", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaLoadbearing", value = "区域承重,单位KG", required = true, paramType = "query"),
		@ApiImplicitParam(name = "warehouseId", value = "仓库ID", required = true, paramType = "query"),
		})
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="区域管理", method="新增区域")
	public Rjson addWarehouse(HttpServletRequest request){
		try {
			CWmsAreaT dx = new CWmsAreaT();
			dx.setAreaNo(EqualsUtil.string(request, "areaNo", "区域编号", true));
			dx.setAreaName(EqualsUtil.string(request, "areaName", "区域名称", true));
			dx.setAreaType(EqualsUtil.integer(request, "areaType", "区域类型", true));
			dx.setAreaLength(EqualsUtil.Double(request, "areaLength", "区域长度", false));
			dx.setAreaWidth(EqualsUtil.Double(request, "areaWidth", "区域宽度", false));
			dx.setAreaHight(EqualsUtil.Double(request, "areaHight", "区域高度", false));
			dx.setAreaLoadbearing(EqualsUtil.Double(request, "areaLoadbearing", "区域承重", false));
			dx.setAreaLimit(EqualsUtil.Double(request, "areaLimit", "区域上限", false));
			dx.setWarehouseId(EqualsUtil.integer(request, "warehouseId", "仓库id", true));

			int res = service.addArea(dx);
			if (res==1) {
				return Rjson.success("新增成功", true);
			} else {
				return Rjson.error("新增失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 更新区域
	 *
	 * @param request
	 */
	@ApiOperation(value = "更新区域", notes = "更新区域")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "areaId", value = "区域Id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "areaNo", value = "区域编号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaName", value = "区域名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaType", value = "区域类型，0：立库 1：平库 2：OTHER", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaLength", value = "区域长度，单位M", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaWidth", value = "区域宽度，单位M", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaHight", value = "区域高度，单位M", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaLimit", value = "区域上限，单位M", required = false, paramType = "query"),
		@ApiImplicitParam(name = "areaLoadbearing", value = "区域承重,单位KG", required = false, paramType = "query"),
		@ApiImplicitParam(name = "warehouseId", value = "仓库ID", required = false, paramType = "query")
		})
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="区域管理", method="编辑区域")
	public Rjson updateWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			CWmsAreaT dx = new CWmsAreaT();
			dx.setId(EqualsUtil.integer(request, "areaId", "区域id", true));
			dx.setAreaNo(EqualsUtil.string(request, "areaNo", "区域编号", true));
			dx.setAreaName(EqualsUtil.string(request, "areaName", "区域名称", true));
			dx.setAreaType(EqualsUtil.integer(request, "areaType", "区域类型", true));
			dx.setAreaLength(EqualsUtil.Double(request, "areaLength", "区域长度", false));
			dx.setAreaWidth(EqualsUtil.Double(request, "areaWidth", "区域宽度", false));
			dx.setAreaHight(EqualsUtil.Double(request, "areaHight", "区域高度", false));
			dx.setAreaLoadbearing(EqualsUtil.Double(request, "areaLoadbearing", "区域承重", false));
			dx.setAreaLimit(EqualsUtil.Double(request, "areaLimit", "区域上限", false));
			dx.setWarehouseId(EqualsUtil.integer(request, "warehouseId", "仓库id", true));

			int res = service.updateArea(dx);
			if (res==1) {
				return Rjson.success("更新成功", true);
			} else {
				return Rjson.error("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 删除区域
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除区域", notes = "删除区域")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "areaId", value = "区域Id", required = true, paramType = "query")
		})
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="区域管理", method="删除区域")
	public Rjson deleteWarehouse(HttpServletRequest request){
		try {
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);

			int res = service.deleteArea(areaId);
			if (res==1) {
				return Rjson.success("删除成功", true);
			} else {
				return Rjson.error("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

}
