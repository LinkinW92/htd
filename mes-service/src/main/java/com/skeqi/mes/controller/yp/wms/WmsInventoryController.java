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
import com.skeqi.mes.service.yp.wms.WmsInventoryService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 盘点
 * @date 2021-08-17
 */
@RestController
@RequestMapping("/api/wms/wmsInventory")
public class WmsInventoryController {

	@Autowired
	WmsInventoryService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂ID", false);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位ID", false);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户ID", false);
			String establishDt = EqualsUtil.string(request, "establishDt", "创建时间", false);
			Integer handleUserId = EqualsUtil.integer(request, "handleUserId", "处理用户ID", false);
			String handleDt = EqualsUtil.string(request, "handleDt", "处理时间", false);
			String status = EqualsUtil.string(request, "status", "状态", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("establishUserId", establishUserId);
			json.put("establishDt", establishDt);
			json.put("handleUserId", handleUserId);
			json.put("handleDt", handleDt);
			json.put("status", status);
			json.put("dis", dis);

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
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂ID", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位ID", true);
			Integer establishUserId = EqualsUtil.integer(request, "establishUserId", "创建用户ID", true);
			String type = EqualsUtil.string(request, "type", "盘点类型", true);
			String status = EqualsUtil.string(request, "status", "状态", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("locationId", locationId);
			json.put("establishUserId", establishUserId);
			json.put("type", type);
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
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String type = EqualsUtil.string(request, "type", "盘点类型", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("type", type);
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
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);

			service.delete(listNo);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除R
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteR")
	public Rjson deleteR(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "行ID", true);

			service.deleteR(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String packingId = EqualsUtil.string(request, "packingId", "包装ID", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);
			Integer packingActualNumber = EqualsUtil.integer(request, "packingActualNumber", "包装实际数量", true);
			Integer actualNumber = EqualsUtil.integer(request, "actualNumber", "单件实际数量", true);

			if (packingId == null || packingId.equals("")) {
				if (code == null || code.equals("")) {
					throw new Exception("包装ID跟单件码必须存在一种");
				}
			}

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("packingId", packingId);
			json.put("code", code);
			json.put("packingActualNumber", packingActualNumber);
			json.put("actualNumber", actualNumber);

			service.addD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateD")
	public Rjson updateD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer rId = EqualsUtil.integer(request, "rId", "行ID", true);
			Integer packingActualNumber = EqualsUtil.integer(request, "packingActualNumber", "包装实际数量", true);
			Integer actualNumber = EqualsUtil.integer(request, "actualNumber", "单件实际数量", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("rId", rId);
			json.put("packingActualNumber", packingActualNumber);
			json.put("actualNumber", actualNumber);

			service.updateD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除D
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteD")
	public Rjson deleteD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "行ID", true);

			service.deleteD(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询R跟D表数据
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRAndD")
	public Rjson findRAndD(HttpServletRequest request) {
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
	public Rjson guozhang(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("listNo", listNo);

			service.guozhang(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
