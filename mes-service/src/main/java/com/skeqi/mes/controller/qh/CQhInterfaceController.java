package com.skeqi.mes.controller.qh;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.CQhInterfaceService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 菜单配置--接口操作
 * @date 2020-10-15
 */
@RestController
@RequestMapping("/api/CQhInterface")
public class CQhInterfaceController {

	@Autowired
	CQhInterfaceService service;

	/**
	 * @explain 新增接口
	 * @param request
	 * @return
	 */
	@RequestMapping("addInterface")
	public Rjson addInterface(HttpServletRequest request) {
		try {
			int authorityInterfaceId = EqualsUtil.integer(request, "authorityInterfaceId", "接口权限id", true);
			String InterfaceAddress = EqualsUtil.string(request, "InterfaceAddress", "接口地址 ", true);
			String describe = EqualsUtil.string(request, "describe", "描述", true);

			JSONObject json = new JSONObject();
			json.put("authorityInterfaceId", authorityInterfaceId);
			json.put("InterfaceAddress", InterfaceAddress);
			json.put("describe", describe);

			service.addInterface(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 删除接口
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteInterface")
	public Rjson deleteInterface(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteInterface(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 更新接口
	 * @param request
	 * @return
	 */
	@RequestMapping("updateInterface")
	public Rjson updateInterface(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String InterfaceAddress = EqualsUtil.string(request, "InterfaceAddress", "接口地址 ", true);
			String describe = EqualsUtil.string(request, "describe", "描述", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("InterfaceAddress", InterfaceAddress);
			json.put("describe", describe);

			service.updateInterface(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
