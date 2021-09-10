package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 职位
 *
 * @author yinp
 * @date 2021年5月20日
 *
 */
public interface PositionDao {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") int id);

	/**
	 * 通过id查询职位使用得人数
	 * @param positionId
	 * @return
	 */
	public int findUserPositionCount(@Param("positionId") Integer positionId);

	/**
	 * 查询名称得数量
	 *
	 * @param name
	 * @param id
	 * @return
	 */
	public int findCountByName(@Param("name") String name, @Param("id") Integer id);

}
