package com.skeqi.mes.mapper.alarm;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmIpWhiteList;

public interface CAlarmUserTokenDao {
	//通过用户名查询用户
	public CMesUserT findUserByName(@Param("userName")String userName);
	//查询用户token
	public JSONObject findUserToken(@Param("userName")String userName);
	//通过token查询用户
	public CMesUserT findUserByToken(@Param("token")String token);
	//更新用户token
	public Integer updateUserToken(@Param("userName")String userName,@Param("token")String token);
	//查询ip白名单
	public CAlarmIpWhiteList findIpWhiteList(@Param("userName")String userName);
	//新增ip白名单
	public Integer addIpWhiteList(@Param("userId")Integer userId,@Param("ip")String ip);
	//更新ip白名单
	public Integer updateIpWhiteList(@Param("userId")Integer userId,@Param("ip")String ip);
}
