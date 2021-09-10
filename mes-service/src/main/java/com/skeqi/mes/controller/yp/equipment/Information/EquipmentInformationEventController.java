package com.skeqi.mes.controller.yp.equipment.Information;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationEventService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 设备事件
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/api/EquipmentInformationEvent")
public class EquipmentInformationEventController {

	@Autowired
	EquipmentInformationEventService service;

	/**
	 * @explain 查询设备事件
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNumber = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);
			int parentId = EqualsUtil.integer(request, "parentId", "设备id", false);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.list(parentId);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 新增事件
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			int parentId = EqualsUtil.integer(request, "parentId", "设备id", true);
			int event = EqualsUtil.integer(request, "event", "事件 ", true);

			service.add(parentId, event);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
