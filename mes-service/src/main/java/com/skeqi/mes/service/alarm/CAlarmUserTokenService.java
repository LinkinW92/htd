package com.skeqi.mes.service.alarm;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmIpWhiteList;

public interface CAlarmUserTokenService {
	// 查询用户token
	public JSONObject findUserToken(String userName);

	// 通过token查询用户
	public CMesUserT findUserByToken(String token);

	// 更新用户token
	public Integer updateUserToken(String userName);

	// 查询ip白名单
	public CAlarmIpWhiteList findIpWhiteList(String userName);

	// 新增ip白名单
	public Integer addIpWhiteList(String userName, String ip) throws Exception;

	// 更新ip白名单
	public Integer updateIpWhiteList(String userName, String ip) throws Exception;
}
