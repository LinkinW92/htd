package com.skeqi.mes.controller.yp.equipment.checkdata;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.equipment.checkdata.CheckDataDetailedService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 点检记录详情
 *
 * @date2021年3月11日
 * @author yinp
 */
@RestController
@RequestMapping("/api/equipment/checkDataDetailed")
public class CheckDataDetailedController {

	@Autowired
	CheckDataDetailedService service;

	/**
	 * @explain 查询集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int checkDataId = EqualsUtil.integer(request, "checkDataId", "点检记录", true);

			List<JSONObject> list = service.list(checkDataId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@Transactional
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			int result = EqualsUtil.integer(request, "result", "点检结果", true);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("result", result);
			json.put("remarks", remarks);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
