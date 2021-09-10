package com.skeqi.mes.controller.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.service.crm.DepartmentInformationService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("crm")
@Controller
@Api(value = "客户组织架构", description = "客户组织架构", produces = MediaType.APPLICATION_JSON)
public class DepartmentInformationController {

	@Autowired
	private DepartmentInformationService service;
	@Autowired
	private CRMLogService services;


	@RequestMapping(value = "/showDepartmentInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户组织架构", notes = "查询客户组织架构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerName", value = "客户名称", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson showDepartmentInfoList(HttpServletRequest request) throws ServicesException{
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		String customerName = request.getParameter("customerName");
//		System.out.println("1234567"+request.getParameter("pageNum"));
//		System.out.println("1234567"+request.getParameter("pageSize"));
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
		if(pageNum!=null&&pageSize!=null){
			PageHelper.startPage(pageNum, pageSize);
//			list  = service.showDepartmentInfo(customerId);
			list  = service.showDepartmentInfo(customerName);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户组织架构");
			return Rjson.success(pageInfo);
		}else if(pageNum==null&&pageSize==null){
			list  = service.showDepartmentInfo(customerName);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户组织架构");
			return Rjson.success(210,list);
		}else{
			PageHelper.startPage(pageNum, pageSize);
//			list  = service.showDepartmentInfo(customerId);
			list  = service.showDepartmentInfo(customerName);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户组织架构");
			return Rjson.success(pageInfo);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



	@RequestMapping(value = "/showDepartmentInfoById", method = RequestMethod.POST)
	@ApiOperation(value = "根据基础信息ID查询客户组织架构", notes = "根据基础信息ID查询客户组织架构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showDepartmentInfoById(HttpServletRequest request) throws ServicesException{
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		List<Map<String, Object>> list = null;

//		System.out.println("1234567"+request.getParameter("pageNum"));
//		System.out.println("1234567"+request.getParameter("pageSize"));

		try {
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			list  = service.showDepartmentInfoById(customerId);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "ID查询组织架构");
			return Rjson.success(list);

		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}




	@RequestMapping(value = "/showAllCustomerNameList", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户名称集合", notes = "查询客户名称集合")
	@ApiImplicitParams({

		 })
	@ResponseBody
	public Rjson showAllCustomerNameList(HttpServletRequest request) throws ServicesException{

		try {

	    List<Map<String,Object>> customerNameList = service.showAllCustomerNameList();
		return Rjson.success(customerNameList);}
		catch (Exception e) {
			e.printStackTrace();
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户名称集合");
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showAllCustomerNameLists", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户名称集合", notes = "查询客户名称集合")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showAllCustomerNameLists(HttpServletRequest request) throws ServicesException{

		try {
		Integer id  = Integer.parseInt(request.getParameter("id"));
	    List<Map<String,Object>> customerNameList = service.showAllCustomerNameListsById(id);
		return Rjson.success(customerNameList);}
		catch (Exception e) {
			e.printStackTrace();
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户名称集合");
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/addDepartmentInfos", method = RequestMethod.POST)
	@ApiOperation(value = "新增客户组织架构", notes = "新增客户组织架构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "departmentName", value = "部门名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "departmentDescription", value = "部门描述", required = false, paramType = "query", dataType = "String"),
		 })
	@ResponseBody
	public Rjson addDepartmentInfos(HttpServletRequest request) throws ServicesException{

		try {
		Integer  customerId= Integer.parseInt(request.getParameter("customerId"));
		String  departmentName= request.getParameter("departmentName");
		String  departmentDescription= request.getParameter("departmentDescription");
		List<String> showAllCustomerNameList =  service.showAllCustomerNameLists(customerId);
		for(String str:showAllCustomerNameList){
			if(str.equals(departmentName)){
				return Rjson.error(1, "该客户部门名称已经存在");
			}
		}
		service.addDepartmentInfo(customerId, departmentName, departmentDescription);
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "新增客户组织架构");
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editDepartmentInfos", method = RequestMethod.POST)
	@ApiOperation(value = "编辑客户组织架构", notes = "编辑客户组织架构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "客户组织架构ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "departmentName", value = "部门名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "departmentDescription", value = "部门描述", required = false, paramType = "query", dataType = "String"),
		 })
	@ResponseBody
	public Rjson editDepartmentInfos(HttpServletRequest request) throws ServicesException{

		try {
		Integer  id= Integer.parseInt(request.getParameter("id"));
		Integer  customerId= Integer.parseInt(request.getParameter("customerId"));
		String  departmentName= request.getParameter("departmentName");
		String  departmentDescription= request.getParameter("departmentDescription");
		String showAllCustomerNameListById  = service.showAllCustomerNameListById(id);
		List<String> showAllCustomerNameList = service.showAllCustomerNameLists(customerId);
		if(!showAllCustomerNameListById.equals(departmentName)){
			for(String str:showAllCustomerNameList){
				if(str.equals(departmentName)){
					return Rjson.error(1,"该客户部门名称已经存在");
				}
			}
		}
		service.editDepartmentInfo(id, customerId, departmentName, departmentDescription);
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑客户组织架构");
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delDepartmentInfos", method = RequestMethod.POST)
	@ApiOperation(value = "删除客户组织架构", notes = "删除客户组织架构")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "客户组织架构ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delDepartmentInfos(HttpServletRequest request) throws ServicesException{

		try {
		Integer  id= Integer.parseInt(request.getParameter("id"));
		if(service.countMemberNum(id)>0){
			return Rjson.error(202, "存在组织架构关联成员，无法删除");
		}

		service.delDepartmentInfo(id);

		String userName = ToolUtils.getCookieUserName(request);
		ShowIPInfo ip = new ShowIPInfo();
		String ipInfo = ip.getIpAddr(request);
		services.addCRMLogInfo(userName+"("+ipInfo+")", "删除客户组织架构");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



}
