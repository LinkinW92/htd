package com.skeqi.mes.controller.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.wms.StorageDetailService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 库存详情
 *
 * @author yinp
 * @date 2020年3月9日
 *
 */
@RestController
@RequestMapping(value = "wms/storageDetail", produces = MediaType.APPLICATION_JSON)
@Api(value = "库存详情", description = "库存详情", produces = MediaType.APPLICATION_JSON)
public class StorageDetailController {

	@Autowired
	StorageDetailService service;

	/**
	 * 查询详情集合
	 *
	 * @param request
	 */
	@RequestMapping(value = "exportExcel", method = RequestMethod.POST)
	public Rjson exportExcel(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String ksDate = EqualsUtil.string(request, "ksDate", "开始时间", false);
			String jsDate = EqualsUtil.string(request, "jsDate", "结束时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("materialName", materialName);
			json.put("projectId", projectId);
			json.put("locationId", locationId);
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
	 * 查询所有库位的Id+name
	 */
	@ApiOperation(value = "查询所有库位的Id+name", notes = "查询所有库位的Id+name")
	@RequestMapping(value = "findLocationIdName", method = RequestMethod.POST)
	public Rjson findLocationIdName(HttpServletRequest request) {
		try {
			List<CWmsLocationT> list = service.findLocationIdName();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有项目号的id+name
	 */
	@ApiOperation(value = "查询所有项目号的id+name", notes = "查询所有项目号的id+name")
	@RequestMapping(value = "findProjectIdName", method = RequestMethod.POST)
	public Rjson findProjectIdName(HttpServletRequest request) {
		try {
			List<CWmsProject> list = service.findProjectIdName();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询详情集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询详情集合", notes = "查询详情集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "ksDate", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "jsDate", value = "结束时间", required = false, paramType = "query") })
	@RequestMapping(value = "findStorageDetailList", method = RequestMethod.POST)
	public Rjson findStorageDetailList(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String ksDate = EqualsUtil.string(request, "ksDate", "开始时间", false);
			String jsDate = EqualsUtil.string(request, "jsDate", "结束时间", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("materialName", materialName);
			map.put("projectId", projectId);
			map.put("locationId", locationId);
			map.put("ksDate", ksDate);
			map.put("jsDate", jsDate);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsStorageDetailT> list = service.findStorageDetailList(map);
			PageInfo<CWmsStorageDetailT> pageInfo = new PageInfo<CWmsStorageDetailT>(list, 5);

			return Rjson.success(pageInfo);
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
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "issueOrReceipt", value = "出库还是入库 0：出库 1：入库", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			Integer issueOrReceipt = EqualsUtil.integer(request, "issueOrReceipt", "出库还是入库", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			CWmsStorageDetailT dx = new CWmsStorageDetailT();
			dx.setListNo(listNo);
			dx.setLocationId(locationId);
			dx.setIssueOrReceipt(issueOrReceipt);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsStorageDetailT> list = service.findStorageDetail(dx);
			PageInfo<CWmsStorageDetailT> pageInfo = new PageInfo<CWmsStorageDetailT>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存详情R表集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库存详情R表集合", notes = "查询库存详情R表集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@RequestMapping(value = "findPList", method = RequestMethod.POST)
	public Rjson findPList(HttpServletRequest request) {
		try {
			CWmsStorageDetailT dx = new CWmsStorageDetailT();

			String listNo = EqualsUtil.string(request, "listNo", "单号", true); // 单号
			dx.setListNo(listNo);

			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsStorageDetailT> list = service.findPStorageDetail(dx);
			PageInfo<CWmsStorageDetailT> pageInfo = new PageInfo<CWmsStorageDetailT>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询单据号下的物料条码
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询单据号下的物料条码", notes = "查询单据号下的物料条码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@RequestMapping(value = "findBarCode", method = RequestMethod.POST)
	public Rjson findBarCode(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true); // 单号
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);// 物料id
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);// 物料id
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);// 物料id

			List<JSONObject> list = service.findBarCode(listNo, materialId, locationId, projectId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
