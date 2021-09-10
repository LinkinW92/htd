package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 激活
 * @date 2020-10-28
 */
public interface ActivationDao {

	/**
	 * @explain 激活记录查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> findList(JSONObject json);

	/**
	 * @explain 查询激活码是否使用过
	 * @param activationCode
	 * @return
	 */
	public int findCountByActivationCode(@Param("activationCode")String activationCode);

	/**
	 * @explain 新增激活记录
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * @explain 保存校验记录
	 * @param json
	 * @return
	 */
	public int addCheck(JSONObject json);

}
