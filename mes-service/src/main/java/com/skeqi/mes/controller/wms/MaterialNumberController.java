package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.service.wms.MaterialNumberService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 物料库存
 *
 * @author yinp
 * @date 2020年3月14日
 *
 */
@RestController
@RequestMapping(value = "wms/materialNumber", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料库存", description = "物料库存", produces = MediaType.APPLICATION_JSON)
public class MaterialNumberController {

	@Autowired
	MaterialNumberService service;

	/**
	 * 更新物料的条码
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "更新物料的条码", notes = "更新物料的条码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "originalBarCode", value = "原条码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "nowBarCode", value = "新条码", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "updateBarCode", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "物料库存", method = "更新物料的条码")
	public Rjson updateBarCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String presentBarCode = EqualsUtil.string(request, "nowBarCode", "新条码", true);

			boolean result = service.updateBarCode(id, presentBarCode);
			if (result) {
				return Rjson.success("更新成功");
			} else {
				return Rjson.success("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 批量删除库存
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "批量删除库存", notes = "批量删除库存")
	@ApiImplicitParams({ @ApiImplicitParam(name = "material", value = "物料id数组", required = true, paramType = "query"),
			@ApiImplicitParam(name = "project", value = "项目id数组", required = true, paramType = "query"),
			@ApiImplicitParam(name = "location", value = "库位id数组", required = true, paramType = "query"), })
	@Transactional
	@RequestMapping(value = "batchDelete", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "物料库存", method = "批量删除库存")
	public Rjson batchDelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			String ids = EqualsUtil.string(request, "ids", "id", true);
			String location = EqualsUtil.string(request, "location", "库位id数组", true);

			String[] id = ids.split(",");
			String[] location1 = location.split(",");
			for (int i = 0; i < id.length; i++) {
				// 删除库存
				boolean result = service.deleteMaterialNumber(Integer.parseInt(id[i]), Integer.parseInt(location1[i]));
				if (!result) {
					throw new Exception("删除失败");
				}
			}
			return Rjson.success("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		String a = "8277,8379,8063,8377,8089,8228,8231,8370,8371,8382";
		String[] b = a.split(",");
		System.out.println(b.length);
	}

	/**
	 * 删除库存
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "删除库存", notes = "删除库存")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialId", value = "物料id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "物料库存", method = "删除库存")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			// 删除库存
			boolean res = service.deleteMaterialNumber(id, locationId);
			if (!res) {
				throw new Exception("删除失败,未知异常");
			}
			return Rjson.success("删除成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询总库存
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询总库存", notes = "查询总库存")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "warehouseId", value = "仓库id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialId", value = "物料id", required = true, paramType = "query") })
	@RequestMapping(value = "findTotal", method = RequestMethod.POST)
	public Rjson findTotal(HttpServletRequest request) {
		try {
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);

			// 查询总库存
			Integer total = service.findTotal(projectId, materialId, warehouseId);
			return Rjson.success("查询成功", total);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询集合(导出)
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ksDate", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "jsDate", value = "结束时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "trayCode", value = "托盘码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query") })
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	public Rjson findAll(HttpServletRequest request) throws IOException, ServicesException {
		try {
			String ksDate = EqualsUtil.string(request, "ksDate", "开始时间", false);
			String jsDate = EqualsUtil.string(request, "jsDate", "结束时间", false);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);

			JSONObject json = new JSONObject();
			json.put("materialName", materialName);
			json.put("projectId", projectId);
			json.put("locationId", locationId);
			json.put("tray", trayCode);
			json.put("ksDate", ksDate);
			json.put("jsDate", jsDate);

			List<JSONObject> list = service.exportExcel(json);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ksDate", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "jsDate", value = "结束时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "trayCode", value = "托盘码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			String ksDate = EqualsUtil.string(request, "ksDate", "开始时间", false);
			String jsDate = EqualsUtil.string(request, "jsDate", "结束时间", false);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("materialName", materialName);
			map.put("projectId", projectId);
			map.put("locationId", locationId);
			map.put("tray", trayCode);
			map.put("ksDate", ksDate);
			map.put("jsDate", jsDate);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsMaterialNumberT> lineList = service.findMaterialNumberList(map);
			PageInfo<CWmsMaterialNumberT> pageInfo = new PageInfo<CWmsMaterialNumberT>(lineList, 5);
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有物料ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有物料ID、NAME", notes = "查询所有物料ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findMaterialAll", method = RequestMethod.POST)
	public Rjson findMaterialAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
			List<JSONObject> list = service.findMaterialAll(materialName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有项目ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有项目ID、NAME", notes = "查询所有项目ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findProjectAll", method = RequestMethod.POST)
	public Rjson findProjectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			List<CWmsProject> list = service.findProjectAll(projectName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有库位ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有库位ID、NAME", notes = "查询所有库位ID、NAME")
	@ApiImplicitParams({})
	@RequestMapping(value = "findLocationAll", method = RequestMethod.POST)
	public Rjson findLocationAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
	 * 查询条码
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询条码", notes = "查询条码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "materialId", value = "物料id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query") })
	@RequestMapping(value = "findBarCode", method = RequestMethod.POST)
	public Rjson findBarCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			List<Map<String, Object>> list = service.findBarCode(materialId, projectId, locationId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 一键转移
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/onekeyTransfer")
	public Rjson onekeyTransfer(HttpServletRequest request) {
		try {
			String str = EqualsUtil.string(request, "json", "物料", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			int userId = EqualsUtil.integer(request, "userId", "用户", true);

			service.onekeyTransfer(str, locationId, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
