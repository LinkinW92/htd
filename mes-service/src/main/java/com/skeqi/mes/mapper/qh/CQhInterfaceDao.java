package com.skeqi.mes.mapper.qh;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 菜单配置--接口操作
 * @date 2020-10-15
 */
public interface CQhInterfaceDao {

	/**
	 * @explain 新增接口
	 * @param json
	 * @return
	 */
	public int addInterface(JSONObject json);

	/**
	 * @explain 删除接口
	 * @param json
	 * @return
	 */
	public int deleteInterface(@Param("id")int id);

	/**
	 * @explain 更新接口
	 * @param json
	 * @return
	 */
	public int updateInterface(JSONObject json);

}
