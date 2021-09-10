package com.skeqi.mes.mapper.qh;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 授权
 * @date 2020-10-28
 */
public interface ToGrantAuthorizationDao {

	/**
	 * @explain查询大于当前时间的数据数量
	 * @param time
	 * @return
	 */
	public int findGreaterThanCurrentTimeCount(@Param("time") String time);

	/**
	 * @explain 查询最后一次登录校验的数据
	 * @return
	 */
	public JSONObject findMaxId();

	/**
	 * @explain 保存校验记录
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);
}
