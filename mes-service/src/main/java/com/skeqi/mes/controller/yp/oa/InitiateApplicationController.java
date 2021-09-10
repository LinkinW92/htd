package com.skeqi.mes.controller.yp.oa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.InitiateApplicationService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.FileUtil;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 发起审批
 *
 * @author yinp
 * @data 2021年6月17日
 */
@RestController
@RequestMapping("/api/oa/initiateApplication_")
public class InitiateApplicationController {

	@Autowired
	InitiateApplicationService service;

	/**
	 * 查询审批流程
	 *
	 * @return
	 */
	@RequestMapping("/queryApprovalProcess")
	public Rjson queryApprovalProcess() {
		try {
			List<JSONObject> list = service.queryApprovalProcess();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过Id查询表单
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findFormById")
	public Rjson findFormById(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "表单id", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);

			JSONObject json = service.findFormById(id, userId);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询部门
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDepartment")
	public Rjson findDepartment(HttpServletRequest request) {
		try {
			Integer superiorDepartmentId = EqualsUtil.integer(request, "superiorDepartmentId", "上级部门ID", false);

			List<JSONObject> list = service.findDepartment(superiorDepartmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询部门跟用户
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDepartmentAndUser")
	public Rjson findDepartmentAndUser(HttpServletRequest request) {
		try {
			Integer superiorDepartmentId = EqualsUtil.integer(request, "superiorDepartmentId", "上级部门ID", false);

			List<JSONObject> list = service.findDepartmentAndUser(superiorDepartmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 获取流程
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/acquisitionProcess")
	public synchronized Rjson acquisitionProcess(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String details = EqualsUtil.string(request, "details", "详情", true);
			String process = EqualsUtil.string(request, "process", "流程", true);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("details", details);
			json.put("process", process);

			List<JSONObject> list = service.acquisitionProcess(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	@Value(value = "${fileName.ApprovalFilePath}")
	public String fileUrl;
	/**
	 * 附件上传
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	public Rjson upload(@RequestBody MultipartFile file) {
		try {

			String fileName = System.currentTimeMillis() + TokenUtil.randomToken(10);

			String filepath = fileUrl + "/" + DateUtil.getNian() + "/" + DateUtil.getYue() + "/" + DateUtil.getRi();

			FileUtil dx = FileUtil.uploadFile(file, filepath, fileName);

			JSONObject json = new JSONObject();
			json.put("name", fileName);
			json.put("oldName", file.getOriginalFilename());
			json.put("size", dx.getFileSizeCompany());
			json.put("path", DateUtil.getNian() + "/" + DateUtil.getYue() + "/" + DateUtil.getRi());
			json.put("url", json.getString("path") + "/" + fileName);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除附件
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("/deleteEnclosure")
	public Rjson deleteEnclosure(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "文件名称", true);
			String path = EqualsUtil.string(request, "path", "路径", true);

			String filepath = fileUrl + "/" + path + "/" + name;

			FileUtil.deleteFile(filepath);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 获取地点
	 *
	 * @return
	 */
	@RequestMapping("/findPlaces")
	public Rjson findPlaces() {
		try {

			List<JSONObject> list = service.findPlaces();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 获取外部联系人
	 *
	 * @return
	 */
	@RequestMapping("/findExternalContacts")
	public Rjson findExternalContacts() {
		try {

			List<JSONObject> list = service.findExternalContacts();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 校验身份证
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/CheckIdCard")
	public Rjson CheckIdCard(HttpServletRequest request) {
		try {

			String IdCard = EqualsUtil.string(request, "IdCard", "身份证", true);

			if (!com.skeqi.mes.util.yp.CheckIdCard.validate(IdCard)) {
				throw new Exception("身份证格式有误");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新草稿
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDraft")
	public Rjson updateDraft(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "草稿id", true);
			String details = EqualsUtil.string(request, "details", "详情", true);

			service.updateDraft(id, details);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 发布
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/release")
	public Rjson release(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "发布用户", true);
			Integer formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单id", true);
			String step = EqualsUtil.string(request, "step", "审批步骤", true);
			String formData = EqualsUtil.string(request, "formData", "表单数据", true);
			String name = EqualsUtil.string(request, "name", "审批名称",true);
			String url = EqualsUtil.string(request, "url", "url",false);
			String parameter = EqualsUtil.string(request, "parameter", "参数",false);
			String urgent = EqualsUtil.string(request, "urgent", "加急",false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("formTemplateId", formTemplateId);
			json.put("step", step);
			json.put("formData", formData);
			json.put("name", name);
			json.put("url", url);
			json.put("parameter", parameter);
			json.put("urgent", urgent);

			if(urgent==null || urgent.equals("")) {
				json.put("urgent", false);
			}

			String listNo = service.release(json);

			return Rjson.success(listNo);
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
