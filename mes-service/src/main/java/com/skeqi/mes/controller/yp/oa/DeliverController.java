package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.DeliverService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 转交
 *
 * @author yinp
 * @data 2021年6月29日
 */
@RestController
@RequestMapping("/api/oa/Deliver")
public class DeliverController {

	@Autowired
	DeliverService service;

	/**
	 * 转交
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/sub")
	public Rjson sub(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "审批人", true);
			Integer deliverUserId = EqualsUtil.integer(request, "deliverUserId", "转交用户", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			service.sub(listNo, userId, deliverUserId,dis);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
