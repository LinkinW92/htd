package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;
import com.skeqi.mes.service.wms.LocationService;
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
 * @date 2020年3月6日 库位管理
 */
@RestController
@RequestMapping(value = "wms/location", produces = MediaType.APPLICATION_JSON)
@Api(value = "库位管理", description = "库位管理", produces = MediaType.APPLICATION_JSON)
public class LocationController {

	@Autowired
	LocationService service;

	/**
	 * 修改库位viewMode状态 用于删除
	 *
	 * @param request
	 */
	@ApiOperation(value = "库位删除", notes = "库位删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "viewMode", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库位管理", method="删除库位")
	public Rjson viewMode(HttpServletRequest request) {
		try {
			CWmsLocationT dx = new CWmsLocationT();
			dx.setId(EqualsUtil.integer(request, "locationId", "库位id", true));
			dx.setViewMode(0);
			int res = service.updateLocation(dx);
			if (res == 1) {
				return Rjson.success("删除成功", null);
			} else {
				throw new Exception("删除失败");
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
	 * 查询库位集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询库位集合", notes = "查询库位集合")
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			CWmsLocationT dx = new CWmsLocationT();
			dx.setId(locationId);
			dx.setTray(trayCode);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsLocationT> lineList = service.findLocationList(dx);
			PageInfo<CWmsLocationT> pageInfo = new PageInfo<CWmsLocationT>(lineList, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有库位ID、NAME
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询所有库位ID、NAME", notes = "查询所有库位ID、NAME")
	@RequestMapping(value = "findLocationAll", method = RequestMethod.POST)
	public Rjson findLocationAll(HttpServletRequest request) {
		try {
			List<CWmsLocationT> list = service.findLocationAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "新增库位", notes = "新增库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationNo", value = "库位编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationName", value = "库位名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationLength", value = "库位长度", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationWidth", value = "库位宽度", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationHight", value = "库位高度", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationVolume", value = "库位体积", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationType", value = "库位类型，0：立库 1：平库 2：OTHER", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationMark", value = "库位标识", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationType1", value = "库位种类，备用", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationCapacity", value = "库位容量", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationWeight", value = "库位载重量，单位KG", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationStatus", value = "库位状态，0：正常 1：满库 2：维修 3：禁用 4：报废", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationX", value = "库位横坐标，考虑小数点", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationY", value = "库位纵坐标，考虑小数点", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationZ", value = "库位Z坐标，考虑小数点", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationVr", value = "库位校验规则", required = true, paramType = "query"),
			@ApiImplicitParam(name = "reservoirAreaId", value = "库区ID", required = true, paramType = "query"),
			@ApiImplicitParam(name = "trayType", value = "可容纳托盘类型 1:矮、2:高", required = true, paramType = "query"),
			@ApiImplicitParam(name = "tray", value = "托盘码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "ptlNo", value = "PTL编号", required = false, paramType = "query"),

	})
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库位管理", method="新增库位")
	public Rjson addWarehouse(HttpServletRequest request) {
		try {

			String locationNo = EqualsUtil.string(request, "locationNo", "库位编号", true);
			String locationName = EqualsUtil.string(request, "locationName", "库位名称", true);
			Integer locationType = EqualsUtil.integer(request, "locationType", "库位类型", true);
			Integer locationStatus = EqualsUtil.integer(request, "locationStatus", "库位状态", true);
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区ID", true);
			Integer trayType = EqualsUtil.integer(request, "trayType", "可容纳托盘类型", true);
			String tray = EqualsUtil.string(request, "tray", "托盘", false);
			Integer locationLength = EqualsUtil.integer(request, "locationLength", "库位长度", false);
			Integer locationWidth = EqualsUtil.integer(request, "locationWidth", "库位宽度", false);
			Integer locationHight = EqualsUtil.integer(request, "locationHight", "库位高度", false);
			Integer locationVolume = EqualsUtil.integer(request, "locationVolume", "库位体积", false);
			String locationMark = EqualsUtil.string(request, "locationMark", "库位标识", false);
			Integer locationType1 = EqualsUtil.integer(request, "locationType1", "库位种类", false);
			String locationCapacity = EqualsUtil.string(request, "locationCapacity", "库位容量", false);
			Integer locationWeight = EqualsUtil.integer(request, "locationWeight", "库位载重量", false);
			String locationX = EqualsUtil.string(request, "locationX", "库位横坐标", true);
			String locationY = EqualsUtil.string(request, "locationY", "库位纵坐标", true);
			String locationZ = EqualsUtil.string(request, "locationZ", "库位Z坐标", true);
			String locationVr = EqualsUtil.string(request, "locationVr", "库位校验规则", false);
			String ptlNo = EqualsUtil.string(request, "ptlNo", "PTL编号", false);

			CWmsLocationT dx = new CWmsLocationT();
			dx.setLocationNo(locationNo);
			dx.setLocationName(locationName);
			dx.setLocationLength(locationLength);
			dx.setLocationWidth(locationWidth);
			dx.setLocationHight(locationHight);
			dx.setLocationVolume(locationVolume);
			dx.setLocationType(locationType);
			dx.setLocationMark(locationMark);
			dx.setLocationType1(locationType1);
			dx.setLocationCapacity(locationCapacity);
			dx.setLocationWeight(locationWeight);
			dx.setLocationStatus(locationStatus);
			dx.setLocationX(locationX);
			dx.setLocationY(locationY);
			dx.setLocationZ(locationZ);
			dx.setLocationVr(locationVr);
			dx.setReservoirAreaId(reservoirAreaId);
			dx.setTrayType(trayType);
			dx.setTray(tray);
			dx.setPtlNo(ptlNo);

			int res = service.addLocation(dx);
			if (res == 1) {
				return Rjson.success("新增成功", true);
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
	 * 更新库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改库位", notes = "修改库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationNo", value = "库位编号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationName", value = "库位名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationLength", value = "库位长度", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationWidth", value = "库位宽度", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationHight", value = "库位高度", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationVolume", value = "库位体积", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationType", value = "库位类型，0：立库 1：平库 2：OTHER", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationMark", value = "库位标识", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationType1", value = "库位种类，备用", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationCapacity", value = "库位容量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationWeight", value = "库位载重量，单位KG", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationStatus", value = "库位状态，0：正常 1：满库 2：维修 3：禁用 4：报废", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationX", value = "库位横坐标，考虑小数点", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationY", value = "库位纵坐标，考虑小数点", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationZ", value = "库位Z坐标，考虑小数点", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationVr", value = "库位校验规则", required = false, paramType = "query"),
			@ApiImplicitParam(name = "reservoirAreaId", value = "库区ID", required = false, paramType = "query"),
			@ApiImplicitParam(name = "trayType", value = "可容纳托盘类型 1:矮、2:高", required = false, paramType = "query"),
			@ApiImplicitParam(name = "tray", value = "托盘码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "ptlNo", value = "PTL编号", required = false, paramType = "query"),

	})
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库位管理", method="编辑库位")
	public Rjson updateWarehouse(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String locationNo = EqualsUtil.string(request, "locationNo", "库位编号", true);
			String locationName = EqualsUtil.string(request, "locationName", "库位名称", true);
			Integer locationType = EqualsUtil.integer(request, "locationType", "库位类型", true);
			Integer locationStatus = EqualsUtil.integer(request, "locationStatus", "库位状态", true);
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区ID", true);
			Integer trayType = EqualsUtil.integer(request, "trayType", "可容纳托盘类型", true);
			String tray = EqualsUtil.string(request, "tray", "托盘", false);
			Integer locationLength = EqualsUtil.integer(request, "locationLength", "库位长度", false);
			Integer locationWidth = EqualsUtil.integer(request, "locationWidth", "库位宽度", false);
			Integer locationHight = EqualsUtil.integer(request, "locationHight", "库位高度", false);
			Integer locationVolume = EqualsUtil.integer(request, "locationVolume", "库位体积", false);
			String locationMark = EqualsUtil.string(request, "locationMark", "库位标识", false);
			Integer locationType1 = EqualsUtil.integer(request, "locationType1", "库位种类", false);
			String locationCapacity = EqualsUtil.string(request, "locationCapacity", "库位容量", false);
			Integer locationWeight = EqualsUtil.integer(request, "locationWeight", "库位载重量", false);
			String locationX = EqualsUtil.string(request, "locationX", "库位横坐标", true);
			String locationY = EqualsUtil.string(request, "locationY", "库位纵坐标", true);
			String locationZ = EqualsUtil.string(request, "locationZ", "库位Z坐标", true);
			String locationVr = EqualsUtil.string(request, "locationVr", "库位校验规则", false);
			String ptlNo = EqualsUtil.string(request, "ptlNo", "PTL编号", false);

			CWmsLocationT dx = new CWmsLocationT();
			dx.setId(id);
			dx.setLocationNo(locationNo);
			dx.setLocationName(locationName);
			dx.setLocationLength(locationLength);
			dx.setLocationWidth(locationWidth);
			dx.setLocationHight(locationHight);
			dx.setLocationVolume(locationVolume);
			dx.setLocationType(locationType);
			dx.setLocationMark(locationMark);
			dx.setLocationType1(locationType1);
			dx.setLocationCapacity(locationCapacity);
			dx.setLocationWeight(locationWeight);
			dx.setLocationStatus(locationStatus);
			dx.setLocationX(locationX);
			dx.setLocationY(locationY);
			dx.setLocationZ(locationZ);
			dx.setLocationVr(locationVr);
			dx.setReservoirAreaId(reservoirAreaId);
			dx.setTrayType(trayType);
			dx.setTray(tray);
			dx.setPtlNo(ptlNo);

			int res = service.updateLocation(dx);
			if (res == 1) {
				return Rjson.success("更新成功", true);
			} else {
				throw new Exception("更新失败");
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
	 * 删除库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除库位", notes = "删除库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库位管理", method="删除库位")
	public Rjson deleteWarehouse(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			int res = service.deleteLocation(locationId);
			if (res == 1) {
				return Rjson.success("删除成功", true);
			} else {
				throw new Exception("删除失败");
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
	 * 查询所有仓库ID、NAME
	 */
	@ApiOperation(value = "查询所有仓库ID、NAME", notes = "查询所有仓库ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findWarehouseAll", method = RequestMethod.POST)
	public Rjson findWarehouseAll(HttpServletRequest request) {
		try {
			List<WarehouseT> list = service.findWarehouseAll();
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询仓库下所有区域ID、NAME
	 *
	 * @param response
	 */
	@ApiOperation(value = "查询仓库下所有区域ID、NAME", notes = "查询仓库下所有区域ID、NAME")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "warehouseId", value = "仓库id", required = true, paramType = "query") })
	@RequestMapping(value = "findAreaAll", method = RequestMethod.POST)
	public Rjson findAreaAll(HttpServletRequest request) {
		try {

			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);

			List<CWmsAreaT> list = service.findAreaAll(warehouseId);

			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询区域下所有库区ID、NAME
	 *
	 * @param response
	 */
	@ApiOperation(value = "查询区域下所有库区ID、NAME", notes = "查询区域下所有库区ID、NAME")
	@ApiImplicitParams({ @ApiImplicitParam(name = "areaId", value = "区域id", required = true, paramType = "query") })
	@RequestMapping(value = "findreservoirAreaAll", method = RequestMethod.POST)
	public Rjson findreservoirAreaAll(HttpServletRequest request) {
		try {

			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);

			List<CWmsReservoirAreaT> list = service.findreservoirAreaAll(areaId);

			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
