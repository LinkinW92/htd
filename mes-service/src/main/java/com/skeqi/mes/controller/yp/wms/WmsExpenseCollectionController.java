package com.skeqi.mes.controller.yp.wms;

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
import com.skeqi.mes.service.yp.wms.WmsExpenseCollectionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 费用化领用
 * @date 2021-08-20
 */
@RestController
@RequestMapping("/api/wms/wmsExpenseCollection")
public class WmsExpenseCollectionController {

	@Autowired
	WmsExpenseCollectionService service;
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂Id", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位Id", false);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门Id", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目Id", false);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户Id", false);
			Integer handleUserId = EqualsUtil.integer(request, "handleUserId", "处理用户Id", false);
			String handleDt = EqualsUtil.string(request, "handleDt", "处理时间", false);
			String status = EqualsUtil.string(request, "status", "状态", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
			json.put("establishUserId", establishUserId);
			json.put("handleUserId", handleUserId);
			json.put("handleDt", handleDt);
			json.put("status", status);
			
			if (handleDt != null && handleDt.split(",").length == 2) {
				json.put("startHandleDt", handleDt.split(",")[0]);
				json.put("endHandleDt", handleDt.split(",")[1]);
			}

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);
			
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 新增
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {

			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂Id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位Id", true);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门Id", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目Id", true);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户Id", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
			json.put("establishUserId", establishUserId);
			json.put("status", status);
			json.put("dis", dis);
			
			service.add(json);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 更新
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门Id", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目Id", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("departmentId", departmentId);
			json.put("projectId", projectId);
			json.put("dis", dis);
			
			service.update(json);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			
			service.delete(listNo);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 新增D
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String packingId = EqualsUtil.string(request, "packingId", "包装id", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			
			if (packingId == null || packingId == "") {
				if (code == null || code == "") {
					throw new Exception("包装ID或单件码其中一项不能为空");
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("packingId", packingId);
			json.put("code", code);
			
			service.addD(json);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 删除行表
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteR")
	public Rjson deleteR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			
			service.deleteR(id);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 删除详情表
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			
			service.deleteD(id);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 查询R跟D表
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRAndD")
	public Rjson listR(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			
			List<JSONObject> list = service.findRAndD(listNo);
			
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 过账
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/guoZhang")
	public Rjson guoZhang(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			
			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("userId", userId);
			
			service.guoZhang(json);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
}
