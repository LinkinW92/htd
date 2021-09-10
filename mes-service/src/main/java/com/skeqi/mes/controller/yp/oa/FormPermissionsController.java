package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.FormPermissionsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 表单权限
 *
 * @author yinp
 * @data 2021年6月10日
 *
 */
@RestController
@RequestMapping("/api/oa/formPermissions")
public class FormPermissionsController {

	@Autowired
	FormPermissionsService service;

	/**
	 * 查询公司
	 * @return
	 */
	@RequestMapping("/findCompany")
	public Rjson findCompany() {
		try {
			List<JSONObject> list = service.findCompany();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询角色
	 * @return
	 */
	@RequestMapping("/findRole")
	public Rjson findRole() {
		try {
			List<JSONObject> list = service.findRole();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询下级
	 * @return
	 */
	@RequestMapping("/findSubordinate")
	public Rjson findSubordinate(HttpServletRequest request) {
		try {
			Integer companyId = EqualsUtil.integer(request, "companyId", "公司Id", false);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门Id", false);

			List<JSONObject> list = service.findSubordinate(companyId, departmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 提交修改
	 * @return
	 */
	@RequestMapping("/submitUpdate")
	public Rjson submitUpdate(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "表单模板Id", true);
			String selectData = EqualsUtil.string(request, "selectData", "选择的数据", true);

			service.submitUpdate(id, selectData);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
