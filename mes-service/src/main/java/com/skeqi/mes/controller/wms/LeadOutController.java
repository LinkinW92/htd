package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;
import com.skeqi.mes.service.wms.LeadOutService;
import com.skeqi.mes.util.*;
import com.skeqi.mes.util.yp.EqualsUtil;


/**
 * 领用出库
 *
 * @author yinp
 * @date 2020年3月18日
 *
 */
@RestController
@RequestMapping("wms/leadOut")
public class LeadOutController {

	@Autowired
	LeadOutService service;

	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.list(null);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过名称查询物料id、NAME
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("findMaterialByName")
	public Rjson findMaterialByName(HttpServletRequest request) {
		try {
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
			List<JSONObject> list = service.findMaterialByName(materialName);
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过名称查询项目id、NAME
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("findProjectByName")
	public Rjson findProjectByName(HttpServletRequest request) {

		try {
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			List<JSONObject> list = service.findProjectByName(projectName);
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询项目id、NAME
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("findLocationAll")
	public Rjson findLocationAll(HttpServletRequest request) {

		try {
			List<JSONObject> list = service.findLocationAll();
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("findStock")
	public Rjson findStock(HttpServletRequest request) {

		try {
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目号", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位", false);

			if(materialId == null && projectId == null && locationId ==null) {
				throw new Exception("物料、项目号、库位最少选一样");
			}

			List<JSONObject> list = service.findStock(materialId, projectId,locationId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 提交
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("sub")
	public synchronized Rjson sub(HttpServletRequest request) {
		try {
			String string = EqualsUtil.string(request, "string", "参数", true);
			JSONObject json = null;

			try {
				json = JSONObject.parseObject(string);
			} catch (Exception e1) {
				throw new Exception("参数格式有误");
			}

			service.sub(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
