package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.wms.StockTransferService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 库存转移
 *
 * @author yinp
 * @date 2021年4月28日
 *
 */
@RestController
@RequestMapping("/wms/stockTransfer")
public class StockTransferController {

	@Autowired
	StockTransferService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNum(request);
			int pageSize = EqualsUtil.pageSize(request);

			String listNo = EqualsUtil.string(request, "listNo", "单号", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询队列
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTaskqueue")
	public Rjson findTaskqueue(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			String taskqueueType = EqualsUtil.string(request, "taskqueueType", "队列类型", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("taskqueueType", taskqueueType);

			List<JSONObject> list = service.findTaskqueue(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findStock")
	public Rjson findStock(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			String state = EqualsUtil.string(request, "state", "状态", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("state", state);
			json.put("locationId", locationId);

			List<JSONObject> list = service.findStock(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 出库
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/chuku")
	public Rjson chuku(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位", true);

			service.chuku(listNo, locationId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新托盘码
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateTray")
	public Rjson updateTray(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "队列", true);
			String tray = EqualsUtil.string(request, "tray", "托盘码", true);

			service.updateTray(id, tray);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改入库队列动作标记
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateInTaskqueueFlag")
	public Rjson updateInTaskqueueFlag(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "队列", true);

			service.updateInTaskqueueFlag(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 直接入库
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/directWarehousing")
	public Rjson directWarehousing(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "队列", true);
			String dt = EqualsUtil.string(request, "dt", "时间", true);
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位", true);
			String locationName = EqualsUtil.string(request, "locationName", "库位", true);
			String tray = EqualsUtil.string(request, "tray", "托盘码", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("dt", dt);
			json.put("listNo", listNo);
			json.put("locationId", locationId);
			json.put("locationName", locationName);
			json.put("tray", tray);

			service.directWarehousing(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 直接出库
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/directDelivery")
	public Rjson directDelivery(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "队列", true);
			String dt = EqualsUtil.string(request, "dt", "时间", true);
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位", true);
			String locationName = EqualsUtil.string(request, "locationName", "库位", true);
			String tray = EqualsUtil.string(request, "tray", "托盘码", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("dt", dt);
			json.put("listNo", listNo);
			json.put("locationId", locationId);
			json.put("locationName", locationName);
			json.put("tray", tray);

			service.directDelivery(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
