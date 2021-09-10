package com.skeqi.mes.controller.authorization;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.authorization.ApplicationForAuthorizationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.RuleChecking;

/**
 * @author yinp
 * @explain 申请授权
 * @date 2020-11-6
 */
@RestController
@RequestMapping("/api/ApplicationForAuthorization")
public class ApplicationForAuthorizationController {

	@Autowired
	ApplicationForAuthorizationService service;

	/**
	 * @explain 申请授权
	 * @param request
	 * @return
	 */
	@RequestMapping("/apply")
	public Rjson apply(HttpServletRequest request) {
		try {

			String applyCorporateName = EqualsUtil.string(request, "applyCorporateName", "申请公司名称", true);
			String applyApplicant = EqualsUtil.string(request, "applyApplicant", "申请人", true);
			String applyTelephone = EqualsUtil.string(request, "applyTelephone", "申请人电话", true);
			String applyMailbox = EqualsUtil.string(request, "applyMailbox", "申请人邮箱", true);

			RuleChecking.checkPhone(applyTelephone, "申请人电话");
			RuleChecking.checkEmail(applyMailbox, "申请人邮箱");

			JSONObject json = new JSONObject();
			json.put("applyCorporateName", applyCorporateName);
			json.put("applyApplicant", applyApplicant);
			json.put("applyTelephone", applyTelephone);
			json.put("applyMailbox", applyMailbox);

			String result = service.apply(json);

			return new Rjson().success(result);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}


	/**
	 * @explain 签名
	 * @param request
	 * @return
	 */
	@RequestMapping("/autograph")
	public Rjson autograph(HttpServletRequest request) {
		try {

			String code = EqualsUtil.string(request, "code", "激活码", true);

			JSONObject json = new JSONObject();
			json.put("code", code);

			service.autograph(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
