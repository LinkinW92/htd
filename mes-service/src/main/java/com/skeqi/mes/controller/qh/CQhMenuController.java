package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.qh.CQhMenuT;
import com.skeqi.mes.service.qh.CQhMenuService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 菜单
 * @date 2020-9-7
 */
@RestController
@RequestMapping("/api/CQhMenu")
public class CQhMenuController {

	@Autowired
	CQhMenuService service;

	/**
	 * @explain 通过上级id查询菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findMenuBySuperiorMenuIdList", method = RequestMethod.POST)
	public Rjson findMenuBySuperiorMenuIdList(HttpServletRequest request) {
		try {
			Integer superiorMenuId = EqualsUtil.integer(request, "superiorMenuId", "上级 id", false);

			List<CQhMenuT> list = service.findMenuBySuperiorMenuIdList(superiorMenuId);

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 查询菜单集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findMenuList", method = RequestMethod.POST)
	public Rjson findMenuList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			Integer menuGrade = EqualsUtil.integer(request, "menuGrade", "等级", false);
			Integer superiorMenuId = EqualsUtil.integer(request, "superiorMenuId", "上级 id", false);
			String menuName = EqualsUtil.string(request, "menuName", "菜单名称", false);
			String parentMenuName = EqualsUtil.string(request, "parentMenuName", "父级菜单名称", false);

			JSONObject json = new JSONObject();
			json.put("menuGrade", menuGrade);
			json.put("superiorMenuId", superiorMenuId);
			json.put("menuName", menuName);
			json.put("parentMenuName", parentMenuName);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.findMenuList(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 新增菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addMenu", method = RequestMethod.POST)
	public Rjson addMenu(HttpServletRequest request) {
		try {
			Integer menuGrade = EqualsUtil.integer(request, "menuGrade", "等级", true);
			Integer superiorMenuId = EqualsUtil.integer(request, "superiorMenuId", "上级 id", true);
			String menuName = EqualsUtil.string(request, "menuName", "菜单名称", true);
			String ifEnable = EqualsUtil.string(request, "ifEnable", "是否启用", true);
			String path = EqualsUtil.string(request, "path", "路径", true);
			String icon = EqualsUtil.string(request, "icon", "icon", true);
			Integer order = EqualsUtil.integer(request, "order", "顺序", true);

			JSONObject json = new JSONObject();
			json.put("menuGrade", menuGrade);
			json.put("superiorMenuId", superiorMenuId);
			json.put("menuName", menuName);
			json.put("ifEnable", ifEnable);
			json.put("path", path);
			json.put("icon", icon);
			json.put("order", order);

			service.addMenu(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 更新菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateMenu", method = RequestMethod.POST)
	public Rjson updateMenu(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer menuGrade = EqualsUtil.integer(request, "menuGrade", "等级", true);
			Integer superiorMenuId = EqualsUtil.integer(request, "superiorMenuId", "上级 id", true);
			String menuName = EqualsUtil.string(request, "menuName", "菜单名称", true);
			String ifEnable = EqualsUtil.string(request, "ifEnable", "是否启用", true);
			String path = EqualsUtil.string(request, "path", "路径", true);
			String icon = EqualsUtil.string(request, "icon", "icon", true);
			Integer order = EqualsUtil.integer(request, "order", "顺序", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("menuGrade", menuGrade);
			json.put("superiorMenuId", superiorMenuId);
			json.put("menuName", menuName);
			json.put("ifEnable", ifEnable);
			json.put("path", path);
			json.put("icon", icon);
			json.put("order", order);

			service.updateMenu(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 删除菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteMenu", method = RequestMethod.POST)
	public Rjson deleteMenu(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteMenu(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
