package com.skeqi.mes.controller.wms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.wms.ModuleService;
import com.skeqi.mes.util.Rjson;

/**
 * 模组Controller
 *
 * @author 73414
 */
@RestController
@RequestMapping("api")
public class ModuleController {


	@Autowired
	ModuleService service;

	/**
	 * 上线
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "goOnline", method = RequestMethod.POST)
	public Rjson goOnline(HttpServletRequest request) {
		try {
			String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			JSONObject json = JSONObject.parseObject(str);
			String sn = null;
			if (json.getString("sn") == null || json.getString("sn").equals("") || json.getString("sn").equals("0")) {
				throw new Exception("总成号不能为空");
			}
			sn = json.getString("sn");
			return service.goOnline(sn);

		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 下线
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "offline", method = RequestMethod.POST)
	public Rjson offline(HttpServletRequest request) {
		try {
			String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
			JSONObject json = JSONObject.parseObject(str);
			String sn = null;
			String result = null;
			if (json.getString("sn") == null || json.getString("sn").equals("")
					|| json.getString("sn").equals("0")) {
				throw new Exception("总成号计划id不能为空");
			}
			if (json.getString("result") == null || json.getString("result").equals("")
					|| json.getString("result").equals("0")) {
				throw new Exception("下线结果不能为空");
			}
			sn = json.getString("sn");
			result = json.getString("result");

			return service.offline(sn, result);

		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
