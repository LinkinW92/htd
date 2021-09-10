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
import com.skeqi.mes.service.crm.PostalAddressService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Controller
@Api(value = "通讯地址", description = "通讯地址", produces = MediaType.APPLICATION_JSON)
public class PostalAddressController {
	@Autowired
	PostalAddressService service;
	@Autowired
	private CRMLogService services;
	@RequestMapping(value = "/showPostalAddressList", method = RequestMethod.POST)
	@ApiOperation(value = "查询通讯地址", notes = "查询通讯地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = true, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showPostalAddressList(HttpServletRequest request) throws ServicesException{
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		List<Map<String, Object>> list = null;
		list=service.showPostalAddressList(customerId);
		try {
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询通讯地址");
		return Rjson.success(list);}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPostalAddress", method = RequestMethod.POST)
	@ApiOperation(value = "新增通讯地址", notes = "新增通讯地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerDialogId", value = "客户基础资料ID", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "eceivingAddress", value = "收货地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "revevingAddressContacts", value = "收发货联系人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceReceivingAddress", value = "收发票地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceReceivingAddressContacts", value = "发票联系人", required = true, paramType = "query", dataType = "String"),
		 })
	@ResponseBody
	public Rjson addPostalAddress(HttpServletRequest request) throws ServicesException{

		try {
			Integer customerId =Integer.parseInt(request.getParameter("customerDialogId"));
			String peceivingAddress =request.getParameter("peceivingAddress");
			String revevingAddressContacts =request.getParameter("revevingAddressContacts");
			String invoiceReceivingAddress =request.getParameter("invoiceReceivingAddress");
			String invoiceReceivingAddressContacts =request.getParameter("invoiceReceivingAddressContacts");
			service.addPostalAddress(customerId, peceivingAddress, revevingAddressContacts, invoiceReceivingAddress, invoiceReceivingAddressContacts);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "新增通讯地址");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/editPostalAddress", method = RequestMethod.POST)
	@ApiOperation(value = "编辑通讯地址", notes = "编辑通讯地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "id", value = "通讯地址ID", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "eceivingAddress", value = "收货地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "revevingAddressContacts", value = "收发货联系人", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceReceivingAddress", value = "收发票地址", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "invoiceReceivingAddressContacts", value = "发票联系人", required = true, paramType = "query", dataType = "String"),
		 })
	@ResponseBody
	public Rjson editPostalAddress(HttpServletRequest request) throws ServicesException{

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer customerId =Integer.parseInt(request.getParameter("customerId"));
			String peceivingAddress =request.getParameter("peceivingAddress");
			String revevingAddressContacts =request.getParameter("revevingAddressContacts");
			String invoiceReceivingAddress =request.getParameter("invoiceReceivingAddress");
			String invoiceReceivingAddressContacts =request.getParameter("invoiceReceivingAddressContacts");
			service.editPostalAddress(id, peceivingAddress, revevingAddressContacts, invoiceReceivingAddress, invoiceReceivingAddressContacts);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑通讯地址");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delPostalAddress", method = RequestMethod.POST)
	@ApiOperation(value = "删除通讯地址", notes = "删除通讯地址")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "通讯地址ID", required = true, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delPostalAddress(HttpServletRequest request) throws ServicesException{

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delPostalAddress(id);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "删除通讯地址");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
