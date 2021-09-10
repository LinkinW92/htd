package com.skeqi.mes.service.yp.alarm.serviceConfig.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.alarm.serviceConfig.EmailDao;
import com.skeqi.mes.service.yp.alarm.serviceConfig.EmailService;
import com.skeqi.mes.util.yp.EamilUtil;

/**
 * 邮箱配置
 *
 * @author yinp
 * @Data 2021年3月22日
 */
@Service
public class EmailConfigServiceImpl implements EmailService {

	@Autowired
	EmailDao dao;

	/**
	 * 查询邮箱配置
	 *
	 * @return
	 */
	@Override
	public JSONObject find() {
		return dao.find();
	}

	/**
	 * 更新邮箱配置
	 *
	 * @return
	 */
	@Override
	public void update(JSONObject json) {
		dao.update(json);
	}

	/**
	 * 测试邮箱配置
	 *
	 * @param json
	 */
	@Override
	public void test(JSONObject json) throws Exception {
		// 设置邮件参数
		EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
		EamilUtil.THE_SERVER = json.getString("theServer");
		EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

		EamilUtil.sendEamil(json.getString("addressee"), "琦航系统---测试邮件", "您好,这是一封测试邮件。");

	}

}
