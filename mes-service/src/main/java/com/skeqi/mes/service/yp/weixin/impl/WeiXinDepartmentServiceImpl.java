package com.skeqi.mes.service.yp.weixin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.yp.WeiXin;
import com.skeqi.mes.service.yp.weixin.WeiXinConfigService;
import com.skeqi.mes.service.yp.weixin.WeiXinDepartmentService;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.WeiXin.WeiXinConfig;
import com.skeqi.mes.util.yp.WeiXin.WeiXinHttpClientUtil;

/**
 * 微信部门
 *
 * @author yinp
 * @date 2021年6月11日
 */
@Service
public class WeiXinDepartmentServiceImpl implements WeiXinDepartmentService {

	@Autowired
	WeiXinConfigService weiXinConfigService;

	// 获取部门列表
	@Override
	public List<JSONObject> list(Integer id) throws Exception {
		if (WeiXin.getACCESS_TOKEN() == null) {
			weiXinConfigService.getAccessToken();
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();

		if (id != null) {
			url = url + "&id=" + id;
		}

		JSONObject resultJson = WeiXinHttpClientUtil.doGet(url);

		if (resultJson.getInteger("errcode") == 0) {
			return JSONObject.parseArray(resultJson.getString("department"), JSONObject.class);
		} else {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
			return null;
		}

	}

	// 创建部门
	@Override
	public void add(JSONObject json) throws Exception {
		if (WeiXin.getACCESS_TOKEN() == null) {
			weiXinConfigService.getAccessToken();
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();

		// 发起请求
		JSONObject resultJson = WeiXinHttpClientUtil.deviceRequest(url, json);

		if (resultJson.getInteger("errcode") != 0) {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}

	}

	// 删除部门
	@Override
	public void delete(Integer id) throws Exception {
		if (WeiXin.getACCESS_TOKEN() == null) {
			weiXinConfigService.getAccessToken();
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();
		url = url + "&id=" + id;

		JSONObject json = new JSONObject();
		json.put("id", id);

		// 发起请求
		JSONObject resultJson = WeiXinHttpClientUtil.doGet(url);

		if (resultJson.getInteger("errcode") != 0) {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}
	}

	// 更新部门
	@Override
	public void update(JSONObject json) throws Exception {
		if (WeiXin.getACCESS_TOKEN() == null) {
			weiXinConfigService.getAccessToken();
		}

		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
		url = url + "?access_token=" + WeiXin.getACCESS_TOKEN();

		// 发起请求
		JSONObject resultJson = WeiXinHttpClientUtil.deviceRequest(url, json);

		if (resultJson.getInteger("errcode") != 0) {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}

	}

}
