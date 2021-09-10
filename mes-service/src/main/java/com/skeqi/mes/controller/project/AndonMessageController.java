package com.skeqi.mes.controller.project;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.AndonMessage;
import com.skeqi.mes.service.project.AndonMessageService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;


@RestController
@RequestMapping("/api/andon/message")

public class AndonMessageController {

	@Autowired
	AndonMessageService service;

	// 客户端接口地址
	private static final String url = "http://sms.sany.cn:8318/sms/services/SmsNewOperatoraddsubCode?wsdl";
	// 包
	private static final String namespace = "http://tempuri.org/";
	// 接口名称
	private static String methodName = "";
	// 请求地址
	private static String soapActionURI = namespace;

	public synchronized static JSONObject UseApi(JSONObject json) throws Exception {

		methodName = json.getString("methodName");
		soapActionURI = namespace + methodName;

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(url);
		call.setUseSOAPAction(true);
		call.setSOAPActionURI(soapActionURI);
		call.setOperationName(new QName(namespace, methodName));
		call.addParameter(new QName(namespace, "str"), XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_STRING);
		// 开始调用服务
		Object obj = null;

		try {
			obj = call.invoke(new String[] { JSON.toJSONString(json) });
		} catch (Exception e) {
			throw new Exception("与客户端通讯失败");
		}

		json = JSON.parseObject(obj.toString());

		if (json.getBoolean("remark") == null) {
			throw new Exception("客户端返回结果异常");
		}

		return json;
	}


	/**
	 * 查询集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson updateBarCode(HttpServletRequest request)
			throws IOException, ServicesException {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);

			PageHelper.startPage(pageNumber, 10);
			List<AndonMessage> lineList = service.findAndonMessageList();
			PageInfo<AndonMessage> pageInfo = new PageInfo<AndonMessage>(lineList, 5);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Rjson update(HttpServletRequest request)
			throws IOException, ServicesException {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer dt = EqualsUtil.integer(request, "dt", "异常时间", true);
			String phone = EqualsUtil.string(request, "phone", "手机号", true);

			AndonMessage dx = new AndonMessage();
			dx.setId(id);
			dx.setDt(dt);
			dx.setPhone(phone.replaceAll("\\s*", ""));

			Integer result = service.updateAndonMessage(dx);
			if(result<=0) {
				throw new Exception("更新失败");
			}

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 *  新增
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Rjson add(HttpServletRequest request)
			throws IOException, ServicesException {
		try {
			Integer dt = EqualsUtil.integer(request, "dt", "异常时间", true);
			String phone = EqualsUtil.string(request, "phone", "手机号", true);

			AndonMessage dx = new AndonMessage();
			dx.setDt(dt);
			dx.setPhone(phone.replaceAll("\\s*", ""));

			Integer result = service.addAndonMessage(dx);
			if(result<=0) {
				throw new Exception("新增失败");
			}

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 *  删除
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Rjson delete(HttpServletRequest request)
			throws IOException, ServicesException {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			Integer result = service.deleteAndonMessage(id);
			if(result<=0) {
				throw new Exception("删除失败");
			}

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
