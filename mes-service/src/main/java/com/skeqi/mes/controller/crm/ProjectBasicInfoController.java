package com.skeqi.mes.controller.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.controller.fqz.GetPro;
import com.skeqi.mes.service.crm.BillingInformationService;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.service.crm.ProjectBasicInfoService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "项目基础信息", description = "项目基础信息", produces = MediaType.APPLICATION_JSON)
public class ProjectBasicInfoController {
	@Autowired
	private ProjectBasicInfoService service;
	@Autowired
	private CRMLogService services;

	@RequestMapping(value = "/showProjectBasicInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询项目基础信息", notes = "查询项目基础信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "id", value = "文件对应的项目ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectNo", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "schemeNo", value = "方案号", required = false, paramType = "query", dataType = "string"),

		 })
	@ResponseBody
	public Rjson showProjectBasicInfo(HttpServletRequest request) throws ServicesException{
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		String projectName= request.getParameter("projectName");
		String projectNo= request.getParameter("projectNo");
		String schemeNo= request.getParameter("schemeNo");
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("abc"));
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("pageSize") != null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			if(pageNum!=null&&pageSize!=null){
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
//				list  = service.showProjectBasicInfo();
//				Integer id = Integer.parseInt(request.getParameter("id"));
				String id = request.getParameter("id");
				String ids = request.getParameter("ids");
				list  = service.showProjectBasicInfoById(id,projectName,projectNo,schemeNo,ids);
				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询项目基础信息");
				return Rjson.success(pageInfo);
			}else if(pageNum==null&&pageSize==null){
//				Integer id = Integer.parseInt(request.getParameter("id"));
				String id = request.getParameter("id");
				String ids = request.getParameter("ids");
				list  = service.showProjectBasicInfoById(id,projectName,projectNo,schemeNo,ids);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询项目基础信息");
				return Rjson.success(210,list);
			}else{
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
//				list  = service.showProjectBasicInfo();
//				Integer id = Integer.parseInt(request.getParameter("id"));
				String id = request.getParameter("id");
				String ids = request.getParameter("ids");
				list  = service.showProjectBasicInfoById(id,projectName,projectNo,schemeNo,ids);
				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "查询项目基础信息");
				return Rjson.success(pageInfo);
			}

	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}

	@RequestMapping(value = "/addProjectBasicInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增项目基础信息", notes = "新增项目基础信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectNo", value = "项目号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "schemeNo", value = "方案号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectAddress", value = "项目地址", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectManager", value = "项目经理", required = false, paramType = "query", dataType = "String"),
	 })
		@ResponseBody
	public Rjson addProjectBasicInfo(HttpServletRequest request) throws ServicesException{


		String projectName=request.getParameter("projectName");
		String projectNo=request.getParameter("projectNo");
		String schemeNo=request.getParameter("schemeNo");
		String projectAddress=request.getParameter("projectAddress");
		String projectManager=request.getParameter("projectManager");
		String customerId=request.getParameter("customerId");
		try {
			if((service.countProjectNOById(projectNo,""))>0){
				return Rjson.error(202, "项目名已经存在");
			}else{
				service.addProjectBasicInfo(projectName, projectNo, schemeNo, projectAddress, projectManager,customerId);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "新增项目基础信息");
				return Rjson.success();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editProjectBasicInfo", method = RequestMethod.POST)
	@ApiOperation(value = "编辑项目基础信息", notes = "编辑项目基础信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "项目基础信息ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectNo", value = "项目号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "schemeNo", value = "方案号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectAddress", value = "项目地址", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "projectManager", value = "项目经理", required = false, paramType = "query", dataType = "String"),
		 })
	@ResponseBody
	public Rjson editProjectBasicInfo(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String projectName=request.getParameter("projectName");
		String projectNo=request.getParameter("projectNo");
		String schemeNo=request.getParameter("schemeNo");
		String projectAddress=request.getParameter("projectAddress");
		String projectManager=request.getParameter("projectManager");
		String customerId=request.getParameter("customerId");
		try {
			if((service.countProjectNOById(projectNo,String.valueOf(id)))>0){
				return Rjson.error(202, "项目名已经存在");
			}
		  service.editProjectBasicInfo(id, projectName, projectNo, schemeNo, projectAddress, projectManager,customerId);
		  String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑项目基础信息");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delProjectBasicInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除项目基础信息", notes = "删除项目基础信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "项目基础信息ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delProjectBasicInfo(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
		  service.delProjectBasicInfo(id);
		  String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "删除项目基础信息");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	}
