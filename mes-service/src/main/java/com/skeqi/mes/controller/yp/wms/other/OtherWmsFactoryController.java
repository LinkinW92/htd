package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherWmsFactoryService;
import com.skeqi.mes.util.Rjson;

/**
 * 工厂
 * @author yinp
 * @date 2021年7月26日
 */
@RestController
@RequestMapping("/api/wms/other/OtherWmsFactory")
public class OtherWmsFactoryController {

	@Autowired
	OtherWmsFactoryService service;

	/**
	 * 查询所有工厂
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAll")
	public Rjson findAll(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
