package com.skeqi.mes.controller.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.service.crm.DepartmentMemberService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("crm")
@Controller
@Api(value = "客户成员信息", description = "客户成员信息", produces = MediaType.APPLICATION_JSON)
public class DepartmentMemberController {

	@Autowired
	private DepartmentMemberService service;
	@Autowired
	private CRMLogService services;


	@RequestMapping(value = "/dataInfo", method = RequestMethod.POST)
	@ApiOperation(value = "测试接收数据", notes = "测试接收数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "phone", value = "电话", required = false, paramType = "query", dataType = "int"),
//		@ApiImplicitParam(name = "dynamicItem", value = "数组", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson dataInfo(HttpServletRequest request, @RequestBody String dynamicItem) throws ServicesException {
		JSONArray jsonArray = JSONArray.fromObject(dynamicItem);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Object name = jsonObject.get("name");
			Object phone = jsonObject.get("phone");
			System.out.println("name:" + name + "," + "phone:" + phone);
		}
		try {
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showDepartmentMemberList", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户成员信息", notes = "查询客户成员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "departmentIName", value = "部门名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "memberName", value = "成员姓名", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showDepartmentMemberList(HttpServletRequest request) throws ServicesException {
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String customerName = request.getParameter("customerName");
		String departmentIName = request.getParameter("departmentIName");
		String memberName = request.getParameter("memberName");

		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			if (pageNum != null && pageSize != null) {
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
				list = service.showAllDepartmentMemberList(customerName, departmentIName, memberName);
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询客户成员信息");
				return Rjson.success(pageInfo);
			} else if (pageNum == null && pageSize == null) {
				list = service.showAllDepartmentMemberList(customerName, departmentIName, memberName);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询客户成员信息");
				return Rjson.success(210, list);
			} else {
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
				list = service.showAllDepartmentMemberList(customerName, departmentIName, memberName);
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询客户成员信息");
				return Rjson.success(pageInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showDepartmentMemberByCustomerId", method = RequestMethod.POST)
	@ApiOperation(value = "查询指定客户成员信息", notes = "查指定客户成员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "departmentIName", value = "部门名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "memberName", value = "成员姓名", required = false, paramType = "query", dataType = "string"),

	})
	@ResponseBody
	public Rjson showDepartmentMemberByCustomerId(HttpServletRequest request) throws ServicesException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		String customerName = request.getParameter("customerName");
		String departmentIName = request.getParameter("departmentIName");
		String memberName = request.getParameter("memberName");
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			if (pageNum != null && pageSize != null) {
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
				list = service.showDepartmentMemberByCustomerId(customerId, customerName, departmentIName, memberName);
				System.out.println("1234546");
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询指定客户成员信息");
				return Rjson.success(pageInfo);
			} else if (pageNum == null && pageSize == null) {
				list = service.showDepartmentMemberByCustomerId(customerId, customerName, departmentIName, memberName);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询指定客户成员信息");
				return Rjson.success(210, list);
			} else {
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
				list = service.showDepartmentMemberByCustomerId(customerId, customerName, departmentIName, memberName);
				System.out.println("1234546");
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询指定客户成员信息");
				return Rjson.success(pageInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showDepartmentListBycustomerId", method = RequestMethod.POST)
	@ApiOperation(value = "查询指定客户部门信息", notes = "查询指定客户部门信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showDepartmentListBycustomerId(HttpServletRequest request) throws ServicesException {
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		List<Map<String, Object>> list = null;
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		try {
			list = service.showDepartmentListBycustomerId(customerId);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName + "(" + ipInfo + ")", "查询指定客户部门信息");
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addDepartmentMemberInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增客户成员信息", notes = "新增客户成员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "departmentId", value = "部门ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "tel", value = "电话", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "address", value = "地址", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "mail", value = "邮件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "hobby", value = "爱好", required = false, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public Rjson addDepartmentMemberInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
//		Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
//		String name=request.getParameter("name");
//		String tel=request.getParameter("tel");
//		String address=request.getParameter("address");
//		String mail=request.getParameter("mail");
//		String hobby=request.getParameter("hobby");
//		String position=request.getParameter("position");
		Integer customerId = Integer.parseInt(String.valueOf(map.get("customerId")));
		Integer departmentId = Integer.parseInt(String.valueOf(map.get("departmentId")));
		String name = String.valueOf(map.get("name"));
		String tel = String.valueOf(map.get("tel"));
		String address = String.valueOf(map.get("address"));
		String mail = String.valueOf(map.get("mail"));
		String hobby = String.valueOf(map.get("hobby"));
		String position = String.valueOf(map.get("position"));
		try {
			service.addDepartmentMember(customerId, departmentId, name, tel, address, hobby, mail, position);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName + "(" + ipInfo + ")", "新增客户成员信息");

//			JSONArray jsonArray=JSONArray.fromObject(dynamicItem);
			List<Map<String, Object>> jsonArray = (List<Map<String, Object>>) map.get("dynamicItem");
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String, Object> jsonObject = jsonArray.get(i);
					Object telx = jsonObject.get("tel") == null ? "" : jsonObject.get("tel");
					Object addressx = jsonObject.get("address") == null ? "" : jsonObject.get("address");
					System.out.println("telx:" + telx + "," + "addressx:" + addressx);
					if (!telx.equals("") || !addressx.equals("")) {
						if (telx.equals("") && addressx.equals("")) {

						} else {
							service.addDepartmentMember(customerId, departmentId, name, telx.toString(), addressx.toString(), hobby, mail, position);
							services.addCRMLogInfo(userName + "(" + ipInfo + ")", "新增客户成员信息");
						}
					}
				}
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editDepartmentMemberInfo", method = RequestMethod.POST)
	@ApiOperation(value = "编辑客户成员信息", notes = "编辑客户成员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "成员ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "departmentId", value = "部门ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "tel", value = "电话", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "address", value = "地址", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "mail", value = "邮件", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "hobby", value = "爱好", required = false, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public Rjson editDepartmentMemberInfo(HttpServletRequest request) throws ServicesException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String mail = request.getParameter("mail");
		String hobby = request.getParameter("hobby");
		String position = request.getParameter("position");
		try {
			service.editDepartmentMember(id, customerId, departmentId, name, tel, address, hobby, mail, position);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName + "(" + ipInfo + ")", "编辑客户成员信息");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delDepartmentMemberInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除客户成员信息", notes = "删除客户成员信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "成员ID", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson delDepartmentMemberInfo(HttpServletRequest request) throws ServicesException {
		Integer id = Integer.parseInt(request.getParameter("id"));

		try {
			service.delDepartmentMember(id);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName + "(" + ipInfo + ")", "删除客户成员信息");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
