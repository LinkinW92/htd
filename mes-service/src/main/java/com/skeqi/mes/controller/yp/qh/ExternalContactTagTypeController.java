package com.skeqi.mes.controller.yp.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.qh.ExternalContactTagTypeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 外部联系人标签类型
 *
 * @author yinp
 * @data 2021年6月8日
 */
@RestController
@RequestMapping("/api/externalContactTagType")
public class ExternalContactTagTypeController {

	@Autowired
	ExternalContactTagTypeService service;

	/**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list() {
		try {
			List<JSONObject> list = service.list();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增
	 *
	 * @参数1 request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "标签名称", true);
			String type = EqualsUtil.string(request, "type", "标签使用类型", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("type", type);
			json.put("dis", dis);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 编辑标签
	 *
	 * @参数1 request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);
			String name = EqualsUtil.string(request, "name", "标签名称", true);
			String type = EqualsUtil.string(request, "type", "标签使用类型", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("type", type);
			json.put("dis", dis);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除标签
	 *
	 * @参数1 request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
