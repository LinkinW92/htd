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
import com.skeqi.mes.service.yp.wms.WmsIncomingInspectionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 来料检验
 * @date 2021-07-16
 */
@RestController
@RequestMapping("/api/wms/wmsIncomingInspection")
public class WmsIncomingInspectionController {

	@Autowired
	WmsIncomingInspectionService service;

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
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂id", false);
			String checkDt = EqualsUtil.string(request, "checkDt", "检验日期", false);
			String receivingGoodsDt = EqualsUtil.string(request, "receivingGoodsDt", "收货日期", false);
			String consigneeName = EqualsUtil.string(request, "consigneeName", "收货人", false);
			String checkUserName = EqualsUtil.string(request, "checkUserName", "校验人", false);
			String status = EqualsUtil.string(request, "status", "状态", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("factoryId", factoryId);
			json.put("consigneeName", consigneeName);
			json.put("checkUserName", checkUserName);
			json.put("status", status);

			if(checkDt!=null && checkDt.split(",").length==2) {
				json.put("startCheckDt", checkDt.split(",")[0]);
				json.put("endCheckDt", checkDt.split(",")[1]);
			}

			if(receivingGoodsDt!=null && receivingGoodsDt.split(",").length==2) {
				json.put("startReceivingGoodsDt", receivingGoodsDt.split(",")[0]);
				json.put("endReceivingGoodsDt", receivingGoodsDt.split(",")[1]);
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
	 * 查询行
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/listRow")
	public Rjson listRow(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			List<JSONObject> list = service.listRow(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/listD")
	public Rjson listD(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String rowId = EqualsUtil.string(request, "rowId", "行ID", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("rowId", rowId);

			List<JSONObject> list = service.listD(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新详情
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateD")
	public Rjson updateD(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String InspectionMethod = EqualsUtil.string(request, "InspectionMethod", "校验方式", false);
			Integer checkNumber = EqualsUtil.integer(request, "checkNumber", "校验数量", true);
			Integer qualifiedNumber = EqualsUtil.integer(request, "qualifiedNumber", "合格数量", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("InspectionMethod", InspectionMethod);
			json.put("checkNumber", checkNumber);
			json.put("qualifiedNumber", qualifiedNumber);

			service.updateD(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 校验完成
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/verificationComplete")
	public Rjson verificationComplete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			service.verificationComplete(id,listNo,userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询R跟D表数据
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

}
