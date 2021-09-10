package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.qh.CQhRoleT;
import com.skeqi.mes.service.qh.CQhRoleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 角色管理
 * @date 2020-9-3
 */
@RestController
@RequestMapping("/api/CQhRole")
public class CQhRoleController {

	@Autowired
	CQhRoleService service;

	/**
	 * @explain 查询角色集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findRoleList",method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "角色编号", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "roleName", value = "角色名称", required = false, paramType = "query", dataType = "String")
	})
	public Rjson findRoleList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			Integer id = EqualsUtil.integer(request, "id", "角色编号", false);
			String roleName = EqualsUtil.string(request, "roleName", "角色名称", false);

			PageHelper.startPage(pageNumber, pageSize);
			List<CQhRoleT> list = service.queryRoleList(roleName,id);
			PageInfo<CQhRoleT> pageInfo = new PageInfo<CQhRoleT>(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 新增角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addRole",method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="角色管理", method="添加角色")
	public Rjson addRole(HttpServletRequest request) {
		try {
			String roleName = EqualsUtil.string(request, "roleName", "角色名称", true);
			String describe = EqualsUtil.string(request, "describe", "描述", false);

			CQhRoleT role = new CQhRoleT();
			role.setRoleName(roleName);
			role.setDescribe(describe);

			service.addRole(role);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 更新角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateRole",method = RequestMethod.POST)
	public Rjson updateRole(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String roleName = EqualsUtil.string(request, "roleName", "角色名称", true);
			String describe = EqualsUtil.string(request, "describe", "描述", false);

			CQhRoleT role = new CQhRoleT();
			role.setId(id);
			role.setRoleName(roleName);
			role.setDescribe(describe);

			service.updateRole(role);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 删除角色
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteRole",method = RequestMethod.POST)
	public Rjson deleteRole(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteRole(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
