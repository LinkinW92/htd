package com.skeqi.mes.controller.yp.weixin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.weixin.WeiXinDepartmentService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 微信部门
 *
 * @author yinp
 * @date 2021年6月11日
 */
@RestController
@RequestMapping("/api/weixin/department")
public class WeiXinDepartmentController {

	@Autowired
	WeiXinDepartmentService service;

	/**
	 * 获取部门列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "部门Id", false);

			List<JSONObject> list = service.list(id);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 创建部门
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "部门名称", true);
			Integer parentid = EqualsUtil.integer(request, "parentid", "上级部门Id", true);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("parentid", parentid);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除部门
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "部门Id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改部门
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "部门Id", true);
			String name = EqualsUtil.string(request, "name", "部门名称", true);
			Integer parentid = EqualsUtil.integer(request, "parentid", "上级部门Id", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("parentid", parentid);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
