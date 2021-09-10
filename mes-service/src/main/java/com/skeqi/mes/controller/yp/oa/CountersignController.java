package com.skeqi.mes.controller.yp.oa;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.service.yp.oa.CountersignService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 加签
 *
 * @author yinp
 * @data 2021年6月29日
 */
@RestController
@RequestMapping("/api/oa/Countersign")
public class CountersignController {

	@Autowired
	CountersignService service;

	/**
	 * 加签提交
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/sub")
	public Rjson sub(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String countersignUser = EqualsUtil.string(request, "countersignUser", "加签用户", true);
			String signatureMethod = EqualsUtil.string(request, "signatureMethod", "加签方式", false);
			String dis = EqualsUtil.string(request, "dis", "", false);

			service.sub(listNo, userId, countersignUser, signatureMethod, dis);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
