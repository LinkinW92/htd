package com.skeqi.mes.util;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 客户端接口使用工具类
 * @author Administrator
 *
 */
public class ClientApiUseUtil {

	//客户端接口地址
	private static final String url = "http://127.0.0.1/FISClientService.asmx?wsdl";
	//包
	private static final String namespace = "http://tempuri.org/";
	//接口名称
	private static String methodName = "";
	//请求地址
	private static String soapActionURI = namespace;

	public synchronized static JSONObject UseApi(JSONObject json) throws Exception {

			methodName = json.getString("methodName");
			soapActionURI = namespace+methodName;

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
			}else if(json.getBoolean("remark")==false){
				throw new Exception(json.getString("reason"));
			}

			return json;

	}

}
