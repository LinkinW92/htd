package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.mapper.yp.oa.DingtalkDao;
import com.skeqi.mes.service.yp.oa.DingtalkService;

@Service
public class DingtalkServiceImpl implements DingtalkService {

	@Autowired
	DingtalkDao dao;

	/**
	 * 绑定用户
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public JSONObject bindingUsers(JSONObject json) throws Exception {
		String mobile = json.getString("mobile");
		String appId = json.getString("appId");

		// 通过appID查询用户信息
		List<JSONObject> userList = dao.findUserByAppId(appId);
		if (userList != null && userList.size() == 1) {
			// 老用户，直接退出方法
			return userList.get(0);
		}

		// 新用户
		// 通过手机号码查询用户信息
		userList = dao.findUserByMobile(mobile);

		// 只查出了一个手机号码匹配的用户
		if (userList != null && userList.size() == 1) {
			json.put("userId", userList.get(0).getInteger("id"));
			// 更新用户信息
			dao.updateUser(json);
			// 绑定用户成功，直接退出方法
			return userList.get(0);
		}

		throw new Exception("未匹配到用户进行自动绑定");
	}

	/**
	 * 更新用户信息
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void updateUser(JSONObject json) throws Exception {
		String userName = json.getString("userName");
		String passWord = json.getString("passWord");

		String ciphertextPassWord = Encryption.getPassWord(passWord + userName + 666666 + passWord, 555);

		// 通过用户名查询用户
		JSONObject user = dao.findUserByName(userName);
		if (user == null || user.getString("passWord") == null) {
			throw new Exception("用户不存在");
		}

		if(!user.getString("passWord").equals(ciphertextPassWord)) {
			throw new Exception("密码校验失败");
		}

		if(user.getString("appId")!=null && !user.getString("appId").equals("")) {
			throw new Exception("该账号已被绑定");
		}

		json.put("userId", user.getInteger("id"));

		if (dao.updateUser(json) != 1) {
			throw new Exception("更新失败");
		}
	}

}
