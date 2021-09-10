package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherLineEdgeLibraryService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 线边库
 * @author yinp
 * @date 2021年8月19日
 */
@RestController
@RequestMapping("/api/wms/other/OtherLineEdgeLibrary")
public class OtherLineEdgeLibraryController {

	@Autowired
	OtherLineEdgeLibraryService service;

	/**
	 * 查询集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/findList")
	public Rjson findList(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "货架", false);

			JSONObject json = new JSONObject();
			json.put("name", name);

			List<JSONObject> list = service.findList(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
