package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;
import com.skeqi.mes.service.wms.ReservoirAreaService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author yp
 * @date 2020年3月6日 库区
 */
@RestController
@RequestMapping(value = "wms/reservoirArea", produces = MediaType.APPLICATION_JSON)
@Api(value = "库区", description = "库区", produces = MediaType.APPLICATION_JSON)
public class ReservoirAreaController {

	@Autowired
	ReservoirAreaService service;

	/**
	 * 查询所有仓库ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有仓库ID、NAME", notes = "查询所有仓库ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findWarehouseAll", method = RequestMethod.POST)
	public Rjson findAll(HttpServletRequest request) {
		try {
			List<WarehouseT> list = service.findWarehouseAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过仓库ID查询区域
	 *
	 * @param request
	 */
	@ApiOperation(value = "通过仓库ID查询区域", notes = "通过仓库ID查询区域")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "warehouseId", value = "仓库id", required = true, paramType = "query") })
	@RequestMapping(value = "findAreaIdAndNameByWarehouseId", method = RequestMethod.POST)
	public Rjson findAreaIdAndNameByWarehouseId(HttpServletRequest request) {
		try {
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);

			List<CWmsAreaT> dx = service.findAreaIdAndNameByWarehouseId(warehouseId);
			return Rjson.success(dx);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改库区viewMode状态 用于删除
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改库区viewMode状态 用于删除", notes = "修改库区viewMode状态 用于删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "reservoirAreaNo", value = "库区编号", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "viewMode", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库区管理", method="删除库区")
	public Rjson updatereservoirAreaViewMode(HttpServletRequest request) {
		CWmsReservoirAreaT dx = new CWmsReservoirAreaT();
		try {
			dx.setId(EqualsUtil.integer(request, "reservoirAreaId", "库区id", true));
			dx.setViewMode(0);
			int res = service.updatereservoirArea(dx);
			if (res==1) {
				return Rjson.success("删除成功");
			} else {
				throw new Exception("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库区集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询库区集合", notes = "查询库区集合")
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		CWmsReservoirAreaT dx = new CWmsReservoirAreaT();
		try {

			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsReservoirAreaT> lineList = service.findreservoirAreaList(dx);
			PageInfo<CWmsReservoirAreaT> pageInfo = new PageInfo<CWmsReservoirAreaT>(lineList, 5);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增库区
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "新增库区", notes = "新增库区")
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库区管理", method="新增库区")
	public Rjson addWarehouse(HttpServletRequest request) {
		try {
			CWmsReservoirAreaT dx = new CWmsReservoirAreaT();

			String raNo = EqualsUtil.string(request, "raNo", "库区编号", true);
			String raName = EqualsUtil.string(request, "raName", "库区名称", true);
			Integer raType = EqualsUtil.integer(request, "raType", "库区类型", true);
			Integer raLocationLimit = EqualsUtil.integer(request, "raLocationLimit", "库位上限", false);
			Integer raAlarmLimit = EqualsUtil.integer(request, "raAlarmLimit", "库区报警下限", false);
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域ID", true);

			dx.setRaNo(raNo);
			dx.setRaName(raName);
			dx.setRaType(raType);
			dx.setRaLocationLimit(raLocationLimit);
			dx.setRaAlarmLimit(raAlarmLimit);
			dx.setAreaId(areaId);

			int res = service.addreservoirArea(dx);
			if (res==1) {
				return Rjson.success("新增成功", true);

			} else {
				throw new Exception("新增失败");

			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新库区
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "新增库区", notes = "新增库区")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "raNo", value = "库区编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "raName", value = "库区名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "raType", value = "库区类型，0：立库 1：平库 2：OTHER", required = false, paramType = "query"),
			@ApiImplicitParam(name = "raLocationLimit", value = " 库位上限", required = false, paramType = "query"),
			@ApiImplicitParam(name = "raAlarmLimit", value = "库区报警下限", required = false, paramType = "query"),
			@ApiImplicitParam(name = "raPrintAdd", value = "库区打印机地址", required = false, paramType = "query"),
			@ApiImplicitParam(name = "areaId", value = "所属区域，区域ID", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库区管理", method="编辑库区")
	public Rjson updateWarehouse(HttpServletRequest request) {
		try {
			CWmsReservoirAreaT dx = new CWmsReservoirAreaT();

			Integer id = EqualsUtil.integer(request, "id", "库区id", true);
			String raNo = EqualsUtil.string(request, "raNo", "库区编号", true);
			String raName = EqualsUtil.string(request, "raName", "库区名称", true);
			Integer raType = EqualsUtil.integer(request, "raType", "库区类型", true);
			Integer raLocationLimit = EqualsUtil.integer(request, "raLocationLimit", "库位上限", false);
			Integer raAlarmLimit = EqualsUtil.integer(request, "raAlarmLimit", "库区报警下限", false);
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域ID", true);

			dx.setId(id);
			dx.setRaNo(raNo);
			dx.setRaName(raName);
			dx.setRaType(raType);
			dx.setRaLocationLimit(raLocationLimit);
			dx.setRaAlarmLimit(raAlarmLimit);
			dx.setAreaId(areaId);
			int res = service.updatereservoirArea(dx);
			if (res==1) {
				return Rjson.success(true);
			} else {
				throw new Exception("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 删除库区
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "删除库区", notes = "删除库区")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "reservoirAreaId", value = "库区id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库区管理", method="删除库区")
	public Rjson deleteWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		try {
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区Id", true);
			int res = service.deletereservoirArea(reservoirAreaId);
			if (res==1) {
				return Rjson.success("删除成功", true);
			} else {
				throw new Exception("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}

	}

}
