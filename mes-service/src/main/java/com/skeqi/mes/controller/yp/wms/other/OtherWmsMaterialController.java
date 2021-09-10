package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherWmsMaterialService;
import com.skeqi.mes.util.Rjson;

/**
 * @author yinp
 * @explain 物料
 * @date 2021-07-28
 */
@RestController
@RequestMapping("/api/wms/other/otherWmsMaterial")
public class OtherWmsMaterialController {

	@Autowired
	OtherWmsMaterialService service;

	/**
	 * 查询所有物料
	 * @return
	 */
	@RequestMapping("/findMaterialAll")
	public Rjson findMaterialAll() {
		try {

			List<JSONObject> list = service.findMaterialAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
