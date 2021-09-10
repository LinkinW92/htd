package com.skeqi.mes.service.yp.weixin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.yp.WeiXin;
import com.skeqi.mes.service.yp.weixin.WeiXinConfigService;
import com.skeqi.mes.service.yp.weixin.WeiXinOAService;
import com.skeqi.mes.util.yp.HttpRequestUtil;
import com.skeqi.mes.util.yp.WeiXin.WeiXinConfig;
import com.skeqi.mes.util.yp.WeiXin.WeiXinHttpClientUtil;

@Service
public class WeiXinOAServiceImpl implements WeiXinOAService {

	@Autowired
	WeiXinConfigService weiXinConfigService = new WeiXinConfigServiceImpl();

	@Override
	public JSONObject applyevent() throws Exception {
		if (WeiXin.getACCESS_TOKEN() == null) {
			weiXinConfigService.getAccessToken();
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/gettemplatedetail";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();

		JSONObject parames = new JSONObject();
		parames.put("template_id", "3TmmkNQAGP3GksUWBXY53FwEJkArZuuUrbX2LzG1");

		JSONObject resultJson = WeiXinHttpClientUtil.deviceRequest(url, parames);
		System.out.println(resultJson);
		if (resultJson.getInteger("errcode") != 0) {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}
		return resultJson;
	}

	public static void main(String[] args) throws Exception {
		WeiXinOAServiceImpl dx = new WeiXinOAServiceImpl();
		JSONObject result = dx.applyevent();

		JSONObject template_content = JSONObject.parseObject(result.getString("template_content"));

		List<JSONObject> contents = new ArrayList<JSONObject>();

		for (JSONObject string : JSONObject.parseArray(template_content.getString("controls"), JSONObject.class)) {

			JSONObject control = new JSONObject();
			control.put("control", JSONObject.parseObject(string.getString("property")).getString("control"));
			control.put("id", JSONObject.parseObject(string.getString("property")).getString("id"));
			JSONObject value = new JSONObject();
			switch (JSONObject.parseObject(string.getString("property")).getString("control")) {
			case "Money":
				value.put("new_money", "888");
				break;
			case "Text":
				value.put("text", "测试咯啊啊啊");
				break;
			}
			control.put("value", value);
			contents.add(control);
		}

		JSONObject parames = new JSONObject();
		parames.put("creator_userid", "YinPeng");
		parames.put("template_id", "3TmmkNQAGP3GksUWBXY53FwEJkArZuuUrbX2LzG1");
		parames.put("use_template_approver", 0);

		List<JSONObject> approvers = new ArrayList<JSONObject>();
		JSONObject approver = new JSONObject();
		String[] s = { "YinPeng" };
		approver.put("userid", s);
		approver.put("attr", 1);
		approvers.add(approver);

		parames.put("approver", approvers);

		parames.put("notifyer", s);
		parames.put("notify_type", 2);

		JSONObject js = new JSONObject();
		js.put("contents", contents);

		parames.put("apply_data", js);

		System.out.println(parames);

		String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();

		JSONObject resultJson = WeiXinHttpClientUtil.deviceRequest(url, parames);

		System.out.println(resultJson);
		if (resultJson.getInteger("errcode") != 0) {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}

	}

}
