package com.skeqi.mes.controller.yp.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.WmsBarcodeTemplateService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 条码模板
 * @date 2021-07-14
 */
@RestController
@RequestMapping("/api/wms/wmsBarcodeTemplate")
public class WmsBarcodeTemplateController {

	@Autowired
	WmsBarcodeTemplateService service;

	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			String startTime = EqualsUtil.string(request, "startTime", "开始时间", false);
			String endTime = EqualsUtil.string(request, "endTime", "结束时间", false);
			String title = EqualsUtil.string(request, "title", "标题", false);
			String name = EqualsUtil.string(request, "name", "模板名称", false);
			Integer userId = EqualsUtil.integer(request, "userId", "创建人", false);

			JSONObject json = new JSONObject();
			json.put("startTime", startTime);
			json.put("endTime", endTime);
			json.put("title", title);
			json.put("name", name);
			json.put("userId", userId);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String title = EqualsUtil.string(request, "title", "标题", true);
			String name = EqualsUtil.string(request, "name", "模板名称", true);
			Integer userId = EqualsUtil.integer(request, "userId", "创建人", true);

			JSONObject json = new JSONObject();
			json.put("title", title);
			json.put("name", name);
			json.put("userId", userId);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String title = EqualsUtil.string(request, "title", "标题", true);
			String name = EqualsUtil.string(request, "name", "模板名称", true);
			Integer userId = EqualsUtil.integer(request, "userId", "创建人", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("title", title);
			json.put("name", name);
			json.put("userId", userId);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/listDetailed")
	public Rjson listDetailed(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			String key = EqualsUtil.string(request, "key", "变量", false);
			String value = EqualsUtil.string(request, "value", "值", false);
			Integer templateId = EqualsUtil.integer(request, "templateId", "模板id", false);
			String type = EqualsUtil.string(request, "type", "获取方式", false);

			JSONObject json = new JSONObject();
			json.put("key", key);
			json.put("value", value);
			json.put("templateId", templateId);
			json.put("type", type);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.listDetailed(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新详情
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateDetailed")
	public Rjson updateDetailed(HttpServletRequest request) {
		try {
			String templateId = EqualsUtil.string(request, "templateId", "模板id", true);
			String list = EqualsUtil.string(request, "list", "集合", true);

			JSONObject json = new JSONObject();
			json.put("templateId", templateId);
			json.put("list", list);

			service.updateDetailed(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}


}
