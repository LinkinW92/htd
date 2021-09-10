package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.CQhRoleJurisdictionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 角色权限
 * @date 2020-9-3
 */
@RestController
@RequestMapping("/api/CQhRoleJurisdiction")
public class CQhRoleJurisdictionController {

	@Autowired
	CQhRoleJurisdictionService service;

	/**
	 * @explain 查询所有权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findJurisdictionList",method = RequestMethod.POST)
	public Rjson findJurisdictionList(HttpServletRequest request) {
		try {

			List<JSONObject> list = service.findJurisdictionList();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 查询角色权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findRoleJurisdiction",method = RequestMethod.POST)
	public Rjson findRoleJurisdiction(HttpServletRequest request) {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色id", true);

			List<JSONObject> list = service.findRoleJurisdiction(roleId);

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 新增角色权限
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addRoleJurisdiction",method = RequestMethod.POST)
	public Rjson addRoleJurisdiction(HttpServletRequest request) {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色id", true);
			String menu = EqualsUtil.string(request, "menu", "菜单", true);

			service.addRoleJurisdiction(roleId,menu.split(","));
			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
