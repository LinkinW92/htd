package com.skeqi.mes.controller.authorization;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.service.qh.ToGrantAuthorizationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

/**
 * @author yinp
 * @explain 授权验证
 * @date 2020-11-10
 */
@RestController
@RequestMapping("/api/ToGrantAuthorization")
public class ToGrantAuthorizationController {

	@Autowired
	ToGrantAuthorizationService service;

	/**
	 * @explain 校验
	 * @return
	 */
	@RequestMapping("/check")
	public Rjson check(HttpServletRequest request) {
		try {
			service.check();

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
