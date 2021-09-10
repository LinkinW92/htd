package com.skeqi.mes.service.yp.weixin.impl;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.yp.WeiXin;
import com.skeqi.mes.service.yp.weixin.WeiXinConfigService;
import com.skeqi.mes.util.yp.HttpRequestUtil;
import com.skeqi.mes.util.yp.TokenUtil;
import com.skeqi.mes.util.yp.WeiXin.WeiXinConfig;

/**
 * 微信配置
 *
 * @author yinp
 * @date 2021年6月11日
 *
 */
@Service
public class WeiXinConfigServiceImpl implements WeiXinConfigService {

	// 获取token
	@Override
	public void getAccessToken() throws Exception {
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
		url = url + "?corpid=" + WeiXin.CORPID;
		url = url + "&corpsecret=" + WeiXin.CORPSECRET;
		String result = HttpRequestUtil.doGet(url);
		JSONObject resultJson = JSONObject.parseObject(result);
		if (resultJson.getInteger("errcode") == 0) {

			// 保存返回结果时间
			WeiXin.setRETURN_TIME(System.currentTimeMillis() / 1000);
			// 保存凭证的有效时间（秒）
			WeiXin.setEXPIRES_IN(resultJson.getLong("expires_in"));
			// 保存获取到的凭证
			WeiXin.setACCESS_TOKEN(resultJson.getString("access_token"));

		}else {
			WeiXinConfig.getError(resultJson.getInteger("errcode").toString());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String token = "Kh0TGsPr26DQMqaOAdXLqxoGiWU-mXe8DCHZnlG4zro8AqZ4qDX1XU4QKiEExkQZi_mfOP5IZQt4h8jGVoLpZ1nE1Cytum_IC5lRRhx-m5IBE6qhbaGrzSPVxJaHUDi1YFapsUwghPGWmmiaYYIh2cfNDffVvhNMzWnXuv1D0wA_GIlzKXpTUul-onJTjH4JRTORO_zNFRp1tuoXbVvQYA";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/list";
		url = url + "?access_token=" + token;
		String result = HttpRequestUtil.doGet(url);
		System.out.println(result);
		JSONObject resultJson = JSONObject.parseObject(result);
		Iterator it = resultJson.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry)it.next();
			System.out.println("key=" + entry.getKey() + " val=" + entry.getValue());
		}
	}

}
