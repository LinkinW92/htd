package com.skeqi.mes.mapper.yp.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统通知
 *
 * @author yinp
 * @date 2021年6月23日
 *
 */
public interface SystemNewsDao {

	/**
	 * 查询通知
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 发起通知
	 *
	 * @param json
	 * @return
	 */
	public int launch(JSONObject json);

	/**
	 * 修改标记
	 *
	 * @param id
	 * @return
	 */
	public int updateState(@Param("id") Integer id, @Param("state") String state);

	/**
	 * 全部编辑已读
	 *
	 * @param userId
	 * @return
	 */
	public int batch(@Param("userId") Integer userId);

	/**
	 * 删除通知
	 *
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") Integer id);

	/**
	 * 通过id查询用户
	 * @param userId
	 * @return
	 */
	public JSONObject findUserById(@Param("userId")Integer userId);

}
