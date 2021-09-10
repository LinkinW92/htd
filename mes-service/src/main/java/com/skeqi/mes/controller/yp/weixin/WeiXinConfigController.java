package com.skeqi.mes.controller.yp.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.service.yp.weixin.WeiXinConfigService;
import com.skeqi.mes.util.Rjson;

/**
 * 微信配置
 * @author yinp
 * @date 2021年6月11日
 *
 */
@RestController
@RequestMapping("/api/weixin/config")
public class WeiXinConfigController {

	@Autowired
	WeiXinConfigService service;

	/**
	 * 获取token
	 * @return
	 */
	@RequestMapping("/getAccessToken")
	public Rjson getAccessToken() {
		try {
			service.getAccessToken();

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}


}
