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
import com.skeqi.mes.service.yp.wms.WmsScrapIssueService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 报废出库
 * @date 2021-08-25
 */
@RestController
@RequestMapping("/api/wms/wmsScrapIssue")
public class WmsScrapIssueController {
	
	@Autowired
	WmsScrapIssueService service;

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
			String orderNumber = EqualsUtil.string(request, "orderNumber", "订单号", false);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户Id", false);
			Integer handleUserId = EqualsUtil.integer(request, "handleUserId", "处理用户Id", false);
			String handleDt = EqualsUtil.string(request, "handleDt", "处理时间", false);
			String status = EqualsUtil.string(request, "status", "状态", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("orderNumber", orderNumber);
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
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户Id", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
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
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
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
	 * 查询R跟D表
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRAndD")
	public Rjson findRAndD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			
			List<JSONObject> list = service.findRAndD(listNo);
			
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 新增R
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addR")
	public Rjson addR(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String materialNo = EqualsUtil.string(request, "materialNo", "物料编号", true);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
			String unit = EqualsUtil.string(request, "unit", "单位", false);
			Integer quantity = EqualsUtil.integer(request, "quantity", "数量", true);
			
			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("materialNo", materialNo);
			json.put("materialName", materialName);
			json.put("unit", unit);
			json.put("quantity", quantity);
			
			service.addR(json);
			
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
			Integer rId = EqualsUtil.integer(request, "rId", "行ID", true);
			
			if (packingId == null || packingId == "") {
				if (code == null || code == "") {
					throw new Exception("包装ID或单件码其中一项不能为空");
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("rId", rId);
			
			service.addD(json);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 删除D
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			
			service.deleteD(id);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
	
	/**
	 * 删除R
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteR")
	public Rjson deleteR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			
			service.deleteR(id);
			
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
