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
import com.skeqi.mes.pojo.qh.CQhAuthorityInterfaceT;
import com.skeqi.mes.pojo.qh.CQhMenuT;
import com.skeqi.mes.service.qh.CQhAuthorityInterfaceService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 模块接口
 * @date 2020-9-9
 */
@RestController
@RequestMapping("/api/authorityInterface")
public class CQhAuthorityInterfaceController {

	@Autowired
	CQhAuthorityInterfaceService service;

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
	 * @explain 查询所有菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findMenuAll", method = RequestMethod.POST)
	public Rjson findMenuAll(HttpServletRequest request) {
		try {
			List<CQhMenuT> list = service.findMenuAll();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 查询模块接口集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryAuthorityInterfaceList", method = RequestMethod.POST)
	public Rjson queryAuthorityInterfaceList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Integer menuId = EqualsUtil.integer(request, "menuId", "菜单Id", false);
			Integer operationType = EqualsUtil.integer(request, "operationType", "操作类型", false);
			String path = EqualsUtil.string(request, "path", "路径", false);
			String describe = EqualsUtil.string(request, "describe", "描述 ", false);

			JSONObject json = new JSONObject();
			json.put("menuId", menuId);
			json.put("operationType", operationType);
			json.put("path", path);
			json.put("describe", describe);

			PageHelper.startPage(pageNumber, pageSize);
			List<CQhAuthorityInterfaceT> list = service.queryAuthorityInterfaceList(json);
			PageInfo<CQhAuthorityInterfaceT> pageInfo = new PageInfo<CQhAuthorityInterfaceT>(list);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 新增模块接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addAuthorityInterface", method = RequestMethod.POST)
	public Rjson addAuthorityInterface(HttpServletRequest request) {
		try {
			Integer menuId = EqualsUtil.integer(request, "menuId", "菜单id", true);
			String operationType = EqualsUtil.string(request, "operationType", "操作类型", true);
			String operationTypeCode = EqualsUtil.string(request, "operationTypeCode", "操作类型代码	", true);
			String path = EqualsUtil.string(request, "path", "路径", true);
			String describe = EqualsUtil.string(request, "describe", "描述", false);

			JSONObject json = new JSONObject();
			json.put("menuId", menuId);
			json.put("operationType", operationType);
			json.put("operationTypeCode", operationTypeCode);
			json.put("path", path);
			json.put("describe", describe);

			service.addAuthorityInterface(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 更新模块接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateAuthorityInterface", method = RequestMethod.POST)
	public Rjson updateAuthorityInterface(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer menuId = EqualsUtil.integer(request, "menuId", "菜单id", true);
			String operationType = EqualsUtil.string(request, "operationType", "操作类型", true);
			String operationTypeCode = EqualsUtil.string(request, "operationTypeCode", "操作类型代码	", true);
			String path = EqualsUtil.string(request, "path", "路径", true);
			String describe = EqualsUtil.string(request, "describe", "描述", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("menuId", menuId);
			json.put("operationType", operationType);
			json.put("operationTypeCode", operationTypeCode);
			json.put("path", path);
			json.put("describe", describe);

			service.updateAuthorityInterface(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 删除模块接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteAuthorityInterface", method = RequestMethod.POST)
	public Rjson deleteAuthorityInterface(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteAuthorityInterface(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
