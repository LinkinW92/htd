package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.wms.other.OtherWorkOrderService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 工单
 * @author yinp
 * @date 2021年8月19日
 */
@RestController
@RequestMapping("/api/wms/other/OtherWorkOrder")
public class OtherWorkOrderController {

	@Autowired
	OtherWorkOrderService service;

	/**
	 * 查询工单
	 * @param request
	 * @return
	 */
	@RequestMapping("/findWorkOrder")
	public Rjson findWorkOrder(HttpServletRequest request) {
		try {
			String workOrderNo = EqualsUtil.string(request, "workOrderNo", "工单编号", false);

			JSONObject json = new JSONObject();
			json.put("workOrderNo", workOrderNo);

			List<JSONObject> list = service.findWorkOrder(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
