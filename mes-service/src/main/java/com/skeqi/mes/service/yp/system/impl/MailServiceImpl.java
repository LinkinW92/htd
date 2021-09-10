package com.skeqi.mes.service.yp.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.system.MailDao;
import com.skeqi.mes.service.yp.system.MailService;
import com.skeqi.mes.util.yp.EamilUtil;

/**
 * 邮件服务
 *
 * @author yinp
 * @date 2021年8月11日
 */
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	MailDao dao;

	/**
	 * 发送
	 *
	 * @param mailbox 邮箱
	 * @param title   邮件标题
	 * @param content 内容
	 */
	@Override
	public void sendOut(String mailbox, String title, String content) throws Exception {
		JSONObject json = dao.findEmailConfig();

		// 设置邮件参数
		EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
		EamilUtil.THE_SERVER = json.getString("theServer");
		EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

		EamilUtil.sendEamil(mailbox, title, content);

	}

}
