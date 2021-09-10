package com.skeqi.mes.controller.yp.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 系统通知
 * @author yinp
 * @date 2021年6月23日
 *
 */
@RestController
@RequestMapping("/api/system/new")
public class SystemNewsController {

	@Autowired
	SystemNewsService service;

	/**
	 * 查询通知
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
//			Integer userId = EqualsUtil.integer(request, "userId", "通知用户ID", true);
//			String state = EqualsUtil.string(request, "state", "状态", false);
			Map<String, Object> map = ToolUtils.getParameterMap(request);

			JSONObject json = new JSONObject();
			json.put("userId", map.get("userId"));
			json.put("state", map.get("state"));

			List<JSONObject> list = service.list(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}


	/**
	 * 发起通知
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/launch")
	public Rjson launch(HttpServletRequest request) {
		try {
			String type = EqualsUtil.string(request, "type", "类型", true);
			Integer userId = EqualsUtil.integer(request, "userId", "通知用户ID", true);
			String extend = EqualsUtil.string(request, "extend", "拓展数据", false);

			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("userId", userId);
			json.put("extend", extend);

			service.launch(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改标记
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateState")
	public Rjson updateState(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "通知ID", true);
			String state = EqualsUtil.string(request, "state", "标记状态", true);

			service.updateState(id,state);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 全部编辑已读
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/batch")
	public Rjson batch(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id", true);

			service.batch(userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除通知
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "通知ID", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
