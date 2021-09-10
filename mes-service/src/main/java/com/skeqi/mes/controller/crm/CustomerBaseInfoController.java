package com.skeqi.mes.controller.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.mapper.crm.CustomerBaseInfoDao;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.service.crm.CustomerBaseInfoService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import scala.collection.mutable.HashMap;

/**
 * 客户基础资料
 *
 * @ClassName: CustomerBaseInfoController
 */
@Controller
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "客户基础资料", description = "客户基础资料", produces = MediaType.APPLICATION_JSON)
public class CustomerBaseInfoController {
	@Autowired
	private CustomerBaseInfoService service;
	@Autowired
	private CRMLogService services;
//	@Value(value = "${fileName.name}")
//	String pathX;

	@RequestMapping(value = "/showCustomerBaseInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户基础资料", notes = "查询客户基础资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "customerType", value = "客户类型", required = false, paramType = "query", dataType = "string"), })
	@ResponseBody
	public Rjson showCustomerBaseInfoList(HttpServletRequest request) throws ServicesException {


//		System.out.println("pathX:"+pathX);
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		String customerName = request.getParameter("customerName");
		String customerType = request.getParameter("customerType");
		String id = request.getParameter("id");
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			if (pageNum != null && pageSize != null) {
				PageHelper.startPage(pageNum, pageSize);
				list = service.showCustomerBaseInfoList(customerName, customerType,id);
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户基础资料");
				return Rjson.success(pageInfo);
			} else if (pageNum == null && pageSize == null) {
				list = service.showCustomerBaseInfoList(customerName, customerType,id);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户基础资料");
				return Rjson.success(210, list);
			} else {
				PageHelper.startPage(pageNum, pageSize);
				list = service.showCustomerBaseInfoList(customerName, customerType,id);
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户基础资料");
				return Rjson.success(pageInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showCustomerBaseAllInfoListById", method = RequestMethod.POST)
	@ApiOperation(value = "根据ID查询客户基础资料", notes = "根据ID查询客户基础资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "客户基础信息ID", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	public Rjson showCustomerBaseAllInfoListById(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			list = service.showCustomerBaseAllInfoListById(id);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "ID查询客户基础资料");
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addCustomerBaseInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增客户基础资料", notes = "新增客户基础资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerType", value = "客户类型", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerWebsite", value = "客户官网", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerProfile", value = "客户简介", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "businessPerson", value = "营业当担人", required = false, paramType = "query", dataType = "String"), })
	@ResponseBody
	public Rjson addCustomerBaseInfo(HttpServletRequest request) throws ServicesException {

		try {
			String customerName = request.getParameter("customerName");
			String customerType = request.getParameter("customerType");
			String customerWebsite = request.getParameter("customerWebsite");
			String customerProfile = request.getParameter("customerProfile");
			String businessPerson = request.getParameter("businessPerson");
			List<String> showAllCustomerNameList = service.showAllCustomerNameList();
			for (String str : showAllCustomerNameList) {

				if (customerName.equals(str)) {
					return Rjson.error(1, "客户名称已存在");
				}
			}

			service.addCustomerBaseInfo(customerName, customerType, customerWebsite, customerProfile, businessPerson);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "新增客户基础资料");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editCustomerBaseInfo", method = RequestMethod.POST)
	@ApiOperation(value = "编辑客户基础资料", notes = "编辑客户基础资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerType", value = "客户类型", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerWebsite", value = "客户官网", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "customerProfile", value = "客户简介", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "businessPerson", value = "营业当担人", required = false, paramType = "query", dataType = "String"), })
	@ResponseBody
	public Rjson editCustomerBaseInfo(HttpServletRequest request) throws ServicesException {

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String customerName = request.getParameter("customerName");
			String customerType = request.getParameter("customerType");
			String customerWebsite = request.getParameter("customerWebsite");
			String customerProfile = request.getParameter("customerProfile");
			String businessPerson = request.getParameter("businessPerson");
			List<String> showAllCustomerNameList = service.showAllCustomerNameList();
			if (!(service.showCustomerNameById(id).equals(customerName))) {
				for (String str : showAllCustomerNameList) {
					if (customerName.equals(str)) {
						return Rjson.error(1, "客户名称已经存在");
					}

				}
			}

			service.editCustomerBaseInfo(id, customerName, customerType, customerWebsite, customerProfile,
					businessPerson);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑客户基础资料");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delCustomerBaseInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除客户基础资料", notes = "删除客户基础资料")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ID", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	public Rjson delCustomerBaseInfo(HttpServletRequest request) throws ServicesException {

		try {
			Integer id = Integer.parseInt(request.getParameter("ID"));
			if (service.countDepartmentNum(id) > 0) {
				return Rjson.error(201, "该客户存在关联部门，请先删除关联部门");
			} else if (service.countMemberNum(id) > 0) {
				return Rjson.error(202, "该客户存在关联成员，请先删除关联成员");
			}
			service.delCustomerBaseInfo(id);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "删除客户基础资料");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
