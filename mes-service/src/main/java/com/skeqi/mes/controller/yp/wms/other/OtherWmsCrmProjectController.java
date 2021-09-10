package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherWmsCrmProjectService;
import com.skeqi.mes.util.Rjson;

/**
 * @author yinp
 * @explain Crm项目
 * @date 2021-07-29
 */
@RestController
@RequestMapping("/api/wms/other/otherWmsCrmProject")
public class OtherWmsCrmProjectController {

	@Autowired
	OtherWmsCrmProjectService service;

	/**
	 * 查询所有项目
	 * @return
	 */
	@RequestMapping("/findAll")
	public Rjson findAll() {
		try {

			List<JSONObject> list = service.findAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
