package com.skeqi.mes.controller.yp.wms.other;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.other.OtherPurchaseOrderService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 采购单
 * @date 2021-07-15
 */
@RestController
@RequestMapping("/api/wms/other/purchaseOrder")
public class OtherPurchaseOrderController {

	@Autowired
	OtherPurchaseOrderService service;

	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			List<JSONObject> list = service.list(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询行
	 * @param request
	 * @return
	 */
	@RequestMapping("/listR")
	public Rjson listR(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);

			List<JSONObject> list = service.listR(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}


}
