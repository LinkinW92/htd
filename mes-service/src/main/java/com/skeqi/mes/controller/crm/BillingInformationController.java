package com.skeqi.mes.controller.crm;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.skeqi.mes.common.crm.ConfigPathInfo;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.common.crm.Zxing;
import com.skeqi.mes.mapper.crm.BillingInformationDao;
import com.skeqi.mes.service.crm.BillingInformationService;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "客户开票信息", description = "客户开票信息", produces = MediaType.APPLICATION_JSON)
public class BillingInformationController {

	@Autowired
	private BillingInformationService service;
	@Autowired
	private CRMLogService services;

	@Value(value = "${fileName.QRCode}")
	String pathName;

	@Value(value = "${fileName.QRCode}")
	String QCPath;

	@RequestMapping(value = "/showBillingInformationList", method = RequestMethod.POST)
	@ApiOperation(value = "查询客户发票信息", notes = "查询客户发票信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="查询发票")
	public Rjson showBillingInformationList(HttpServletRequest request) throws ServicesException {
		try {
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			List<Map<String, Object>> billingInformationList = service.showBillingInformationList(customerId);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询客户开票信息");
			return Rjson.success(billingInformationList);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addBillingInformation", method = RequestMethod.POST)
	@ApiOperation(value = "添加客户发票信息", notes = "添加客户发票信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId", value = "客户基础资料ID", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "dutyParagraph", value = "税号", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "unitAddress", value = "单位地址", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "tel", value = "电话", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "bankOfDeposit", value = "开户银行", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "bankAccount", value = "银行账号", required = true, paramType = "query", dataType = "String"), })
	@ResponseBody
	public Rjson addBillingInformationList(HttpServletRequest request) throws ServicesException {
		try {
			// 配置文件的路径
			// ConfigPathInfo config=new ConfigPathInfo();
			// String pathName = config.obtainPath("QRCode");
//			Properties pps = new Properties();
			// pps.load(new
			// FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
			// pps.load(new
			// InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"),
			// "UTF-8"));
//			pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
			// pps.load(new
			// InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//			Enumeration enum1 = pps.propertyNames();// 得到配置文件的名字
//			while (enum1.hasMoreElements()) {
//				String strKey = (String) enum1.nextElement();
//				String strValue = pps.getProperty(strKey);
//				System.out.println(strKey + "=" + strValue);
//			}
			// String path = pps.getProperty("QRCode");
//			String pathName = pps.getProperty("QRCode");
			// String clearPath = pps.getProperty("CRMClear");

			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			String dutyParagraph = request.getParameter("dutyParagraph");
			String unitAddress = request.getParameter("unitAddress");
			String tel = request.getParameter("tel");
			String bankOfDeposit = request.getParameter("bankOfDeposit");
			String bankAccount = request.getParameter("bankAccount");
			List<Map<String, Object>> list = service.showBillingInformationList(customerId);
			String customerName = null;
			if (list.size() <= 0) {
				customerName = "";
			} else {
				customerName = String.valueOf(list.get(0).get("CUSTOMER_NAME"));
				if (customerName == null) {
					customerName = "";
				}
			}
			if (dutyParagraph == null) {
				dutyParagraph = "";
			}
			if (unitAddress == null) {
				unitAddress = "";
			}
			if (tel == null) {
				tel = "";
			}
			if (bankOfDeposit == null) {
				bankOfDeposit = "";
			}
			if (bankAccount == null) {
				bankAccount = "";
			}

			// File dir = new File("D:\\键盘\\二维码\\1.jpg");
			File dir = new File(pathName);
			if (!dir.exists()) { // 判断这个路径不存在
				dir.mkdirs(); // 如果不存在就创建
			}

			Zxing zxing = new Zxing();
			if (zxing.orCode("开票信息\n公司名称:" + customerName + "\n税号:" + dutyParagraph + "\n单位地址:" + unitAddress + "\n电话:"
					+ tel + "\n开户银行:" + bankOfDeposit + "\n银行账户:" + bankAccount + "\n", pathName)) {
				System.out.println("ok,二维码生成成功");
			} else {
				System.out.println("no,二维码生成失败");
			}

			// String QRCode = zxing.getImgFileToBase64("D:\\键盘\\二维码\\1.jpg");
			String QRCode = zxing.getImgFileToBase64(pathName);
			System.out.println("生成二维码base64:" + QRCode);
			service.addBillingInformationList(customerId, dutyParagraph, unitAddress, tel, bankOfDeposit, bankAccount,
					QRCode);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "新增客户开票信息");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editBillingInformation", method = RequestMethod.POST)
	@ApiOperation(value = "编辑客户发票信息", notes = "编辑客户发票信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "开票信息id", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "dutyParagraph", value = "税号", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "unitAddress", value = "单位地址", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "tel", value = "电话", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "bankOfDeposit", value = "开户银行", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "bankAccount", value = "银行账号", required = true, paramType = "query", dataType = "String"), })
	@ResponseBody
	public Rjson editBillingInformationList(HttpServletRequest request) throws ServicesException {
		try {
			// ConfigPathInfo config = new ConfigPathInfo();
			// String QCPath =config.obtainPath("QRCode");

//			Properties pps = new Properties();
			// pps.load(new
			// FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
			// pps.load(new
			// InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"),
			// "UTF-8"));
//			pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
			// pps.load(new
			// InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//			Enumeration enum1 = pps.propertyNames();// 得到配置文件的名字
//			while (enum1.hasMoreElements()) {
//				String strKey = (String) enum1.nextElement();
//				String strValue = pps.getProperty(strKey);
//				System.out.println(strKey + "=" + strValue);
//			}
			// String path = pps.getProperty("QRCode");
//			String QCPath = pps.getProperty("QRCode");
			// String clearPath = pps.getProperty("CRMClear");
			Integer id = Integer.parseInt(request.getParameter("id"));
			String dutyParagraph = request.getParameter("dutyParagraph");
			String unitAddress = request.getParameter("unitAddress");
			String tel = request.getParameter("tel");
			String bankOfDeposit = request.getParameter("bankOfDeposit");
			String bankAccount = request.getParameter("bankAccount");
			Map<String, Object> customer = service.showCustomerNameById(id);
			String customerName = null;
			if (customer == null) {
				customerName = "";
			} else {
				customerName = String.valueOf(customer.get("CUSTOMER_NAME"));
				if (customerName == null) {
					customerName = "";
				}
			}

			if (dutyParagraph == null) {
				dutyParagraph = "";
			}
			if (unitAddress == null) {
				unitAddress = "";
			}
			if (tel == null) {
				tel = "";
			}
			if (bankOfDeposit == null) {
				bankOfDeposit = "";
			}
			if (bankAccount == null) {
				bankAccount = "";
			}
			Zxing zxing = new Zxing();
			if (zxing.orCode("开票信息\n公司名称:" + customerName + "\n税号:" + dutyParagraph + "\n单位地址:" + unitAddress + "\n电话:"
					+ tel + "\n开户银行:" + bankOfDeposit + "\n银行账户:" + bankAccount + "\n", QCPath)) {
				System.out.println("ok,二维码生成成功");
			} else {
				System.out.println("no,二维码生成失败");
			}

			String QRCode = zxing.getImgFileToBase64(QCPath);
			System.out.println("生成二维码base64:" + QRCode);

			service.editBillingInformationList(id, dutyParagraph, unitAddress, tel, bankOfDeposit, bankAccount, QRCode);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "编辑客户开票信息");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delBillingInformation", method = RequestMethod.POST)
	@ApiOperation(value = "删除客户发票信息", notes = "删除客户发票信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "开票信息id", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	public Rjson delBillingInformationList(HttpServletRequest request) throws ServicesException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delBillingInformationList(id);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "删除客户开票信息");
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
