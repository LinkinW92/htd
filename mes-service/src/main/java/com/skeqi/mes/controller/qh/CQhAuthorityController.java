package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.CQhAuthorityServer;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 琦航权限
 * @date 2020-10-8
 */
@RestController
@RequestMapping("/api/CQhAuthority")
public class CQhAuthorityController
{

	@Autowired
	CQhAuthorityServer server;

	/**
	 * @explain 查询角色权限
	 * @param request
	 * @return
	 */
	@RequestMapping("findAuthorityInterfaceByRoleId")
	public Rjson findAuthorityInterfaceByRoleId(HttpServletRequest request)
	{
		try
		{
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色Id", true);
			List<JSONObject> list = server.findAuthorityInterfaceByRoleId(roleId);
			return new Rjson().success(list);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		List<String> s = new ArrayList<String>();
		s.add("a");
		s.add("b");
		s.add("c");
		for (String string : s) {
			s.remove(string);
		}
		System.out.println(s);
	}

}
