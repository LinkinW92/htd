package com.skeqi.mes.controller.wms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.wms.ConfigService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 *
 * @author yinp
 * @date 2021年4月25日
 *
 */
@RestController
@RequestMapping("/wms/config")
public class ConfigController {

	@Autowired
	ConfigService service;

	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("/select")
	public Rjson select() {
		try {
			JSONObject json = service.select();

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int timeOut = EqualsUtil.integer(request, "timeOut", "超时时间", true);

			JSONObject json = new JSONObject();
			json.put("timeOut", timeOut);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
