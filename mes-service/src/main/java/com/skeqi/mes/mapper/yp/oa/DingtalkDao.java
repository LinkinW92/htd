package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 钉钉
 *
 * @author yinp
 *
 */
public interface DingtalkDao {

	/**
	 * 通过手机号码查询用户信息
	 *
	 * @param mobile
	 * @return
	 */
	public List<JSONObject> findUserByMobile(@Param("mobile") String mobile);

	/**
	 * 通过appID查询用户信息
	 *
	 * @param mobile
	 * @return
	 */
	public List<JSONObject> findUserByAppId(@Param("appId") String appId);

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public JSONObject findUserByName(@Param("userName") String userName);

	/**
	 * 更新用户信息
	 *
	 * @param json
	 * @return
	 */
	public int updateUser(JSONObject json);

	/**
	 * 通过用户名称查询count数量
	 * @param userName
	 * @return
	 */
	public int findUserCountByName(@Param("userName")String userName);

}
