package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherStationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 工位
 * @author yinp
 * @date 2021年8月19日
 */
@RestController
@RequestMapping("/api/wms/other/OtherStation")
public class OtherStationController {

	@Autowired
	OtherStationService service;

	/**
	 * 通过产线查询工位
	 * @param request
	 * @return
	 */
	@RequestMapping("/findStationByLineId")
	public Rjson findStationByLineId(HttpServletRequest request) {
		try {
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线ID", true);

			List<JSONObject> list = service.findStationByLineId(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
