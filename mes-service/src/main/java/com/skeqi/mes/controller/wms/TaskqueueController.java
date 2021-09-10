package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.wms.TaskqueueService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 队列维护
 * @date 2020-12-9
 */
@RestController
@RequestMapping("/wms/Taskqueue")
public class TaskqueueController {

	@Autowired
	TaskqueueService service;

	/**
	 * @explain 查询队列集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int type = EqualsUtil.integer(request, "type", "队列类型", true);

			List<JSONObject> list = service.list(type);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@Transactional
	@OptionalLog(module="仓管", module2="队列维护", method="删除队列")
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "队列id", true);
			int type = EqualsUtil.integer(request, "type", "队列类型", true);
			int condition = EqualsUtil.integer(request, "condition", "删除条件", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);

			service.delete(type, condition, id, listNo, locationId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}



	/**
	 * @explain 查询库位集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/findLocation")
	@Transactional
	public Rjson findLocation(HttpServletRequest request) {
		try {

			List<JSONObject> list = service.findLocation();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 手动模式
	 * @param request
	 * @return
	 */
	@RequestMapping("ControlStock")
	public Rjson ControlStock(HttpServletRequest request) {
		try {
			String tray = EqualsUtil.string(request, "tray", "托盘码", false);
			int Goods_Size = EqualsUtil.integer(request, "Goods_Size", "库位类型", true);
			int From_Row = EqualsUtil.integer(request, "From_Row", "取料X坐标", true);
			int From_List = EqualsUtil.integer(request, "From_List", "取料Y坐标", true);
			int From_Layer = EqualsUtil.integer(request, "From_Layer", "取料Z坐标", true);
			int To_Row = EqualsUtil.integer(request, "To_Row", "放料X坐标", true);
			int To_List = EqualsUtil.integer(request, "To_List", "放料Y坐标", true);
			int To_Layer = EqualsUtil.integer(request, "To_Layer", "放料Z坐标", true);

			JSONObject json = new JSONObject();
			json.put("tray", tray);
			json.put("Goods_Size", Goods_Size);
			json.put("From_Row", From_Row);
			json.put("From_List", From_List);
			json.put("From_Layer", From_Layer);
			json.put("To_Row", To_Row);
			json.put("To_List", To_List);
			json.put("To_Layer", To_Layer);

			service.ControlStock(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
