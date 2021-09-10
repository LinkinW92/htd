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
import com.skeqi.mes.service.yp.wms.WmsProductionRequisitionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 生产领用
 * @date 2021-08-9
 */
@RestController
@RequestMapping("/api/wms/wmsProductionRequisition")
public class WmsProductionRequisitionController {

	@Autowired
	WmsProductionRequisitionService service;

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
			String workOrderNo = EqualsUtil.string(request, "workOrderNo", "工单编号", false);
			Integer userId = EqualsUtil.integer(request, "userId", "创建人", false);
			Integer handleUserId = EqualsUtil.integer(request, "handleUserId", "处理人", false);
			String handleDt = EqualsUtil.string(request, "handleDt", "处理时间", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("workOrderNo", workOrderNo);
			json.put("userId", userId);
			json.put("handleUserId", handleUserId);

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
			String factoryId = EqualsUtil.string(request, "factoryId", "工厂ID", true);
			String workOrderNo = EqualsUtil.string(request, "workOrderNo", "工单编号", true);
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", true);
			Integer rockNo = EqualsUtil.integer(request, "rockNo", "线边库位", true);
			Integer stationId = EqualsUtil.integer(request, "stationId", "工位", false);
			String type = EqualsUtil.string(request, "type", "类型", true);
			Integer userId = EqualsUtil.integer(request, "userId", "创建用户ID", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("factoryId", factoryId);
			json.put("workOrderNo", workOrderNo);
			json.put("lineId", lineId);
			json.put("rockNo", rockNo);
			json.put("stationId", stationId);
			json.put("type", type);
			json.put("userId", userId);
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
			String rockNo = EqualsUtil.string(request, "rockNo", "料格", true);
			String type = EqualsUtil.string(request, "type", "类型", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("rockNo", rockNo);
			json.put("type", type);
			json.put("dis", dis);
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
	 * 新增详情表
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addD")
	public Rjson addD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer rId = EqualsUtil.integer(request, "rId", "行id", true);
			String packingId = EqualsUtil.string(request, "packingId", "包装id", false);
			String code = EqualsUtil.string(request, "code", "单件码", false);

			if (packingId == null || packingId == "") {
				if (code == null || code == "") {
					throw new Exception("包装ID或单件码其中一项不能为空");
				}
			}

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("rId", rId);
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
