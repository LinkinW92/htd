package com.skeqi.mes.controller.crm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

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
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.service.crm.DepartmentMemberService;
import com.skeqi.mes.service.crm.FileTypeInformationService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping("crm")
@Controller
@Api(value = "文件类型管理", description = "文件类型管理", produces = MediaType.APPLICATION_JSON)
public class FileTypeInformationController {

	@Autowired
	private FileTypeInformationService service;
	@Autowired
	private CRMLogService services;



	@RequestMapping(value = "/showAllFileTypeInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示文件类型管理", notes = "展示文件类型管理")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showDepartmentMemberList(HttpServletRequest request) throws ServicesException{
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
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
				list  = service.showAllFileTypeInfo();
				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "展会文件类型管理");
				return Rjson.success(pageInfo);
			}else if(pageNum==null&&pageSize==null){
				list  = service.showAllFileTypeInfo();
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件类型管理");
				return Rjson.success(list);
			}else{
				PageHelper.startPage(pageNum, pageSize);
//				list  = service.showDepartmentInfo(customerId);
				list  = service.showAllFileTypeInfo();
				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件类型管理");
				return Rjson.success(pageInfo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/addFileTypeInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增文件类型", notes = "新增文件类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fileType", value = "文件类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "fileName", value = "类型名称", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson addFileTypeInfo(HttpServletRequest request) throws ServicesException{

		String fileType=request.getParameter("fileType");
		String fileName=request.getParameter("fileName");
		if(service.countFileTypeInfo(fileType, fileName,"")>0){
			return Rjson.error(202, "类型名称已经存在");
		}

		try {
		  service.addFileTypeInfo(fileType, fileName);
		  String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "新增文件类型");
		return Rjson.success();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editFileTypeInfo", method = RequestMethod.POST)
	@ApiOperation(value = "编辑文件类型", notes = "编辑文件类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "fileType", value = "文件类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "fileName", value = "类型名称", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson editFileTypeInfo(HttpServletRequest request) throws ServicesException{
		String id = request.getParameter("id");
		String fileType=request.getParameter("fileType");
		String fileName=request.getParameter("fileName");
		if(service.countFileTypeInfo(fileType, fileName,id)>0){
			return Rjson.error(202, "类型名称已经存在");
		}

		try {
		  service.editFileTypeInfo(id, fileType, fileName);
		  String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑文件类型");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delFileTypeInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除文件类型", notes = "删除文件类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "成员ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delFileTypeInfo(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));

		if(service.countTypeNum(id)>0){
			return Rjson.error(202, "文件类型存在关联文件项，请先删除文件项相关文件");
		}

		try {
		  service.delFileTypeInfo(id);
		  String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "删除文件类型");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showUserInfox", method = RequestMethod.POST)
	@ApiOperation(value = "展示用户信息", notes = "展示用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userName", value = "用户名称", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson showUserInfox(HttpServletRequest request) throws ServicesException{
		String userName = request.getParameter("userName");
		try {
			List<Map<String,Object>> showUserList = services.showUser(userName);
			if(showUserList.size()==1){
				return Rjson.success(showUserList);
			}else{
				return Rjson.error(202,"存在相同的用户名，不支持用户修改密码，请联系管理员");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



}
