package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.ActivationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 激活
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/api/activation")
public class ActivationController {

	@Autowired
	ActivationService service;

	/**
	 * @explain 查询激活记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/findList")
	public Rjson findList(HttpServletRequest request) {
		try {
			String dt = EqualsUtil.string(request, "dt", "时间", false);
			String activationCode = EqualsUtil.string(request, "activationCode", "激活码", false);
			String days = EqualsUtil.string(request, "days", "天数", false);

			JSONObject json = new JSONObject();
			json.put("dt", dt);
			json.put("activationCode", activationCode);
			json.put("days", days);

			List<JSONObject> list = service.findList(json);

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 激活
	 * @param request
	 * @return
	 */
	@RequestMapping("/activation")
	@Transactional
	public Rjson activation(HttpServletRequest request) {
		try {
			String activationCode = EqualsUtil.string(request, "activationCode", "激活码", true);

			service.check(activationCode);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Rjson().error(e.getMessage());
		}
	}

}
