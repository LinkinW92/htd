package com.skeqi.mes.mapper.yp.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 职级
 *
 * @author yinp
 * @data 2021年7月13日
 */
public interface RankDao {

	/**
	 * 查询
	 * @return
	 */
	public List<JSONObject> list();

	/**
	 * 新增
	 * @param json
	 */
	public void add(JSONObject json);

	/**
	 * 更新
	 * @param json
	 */
	public void update(JSONObject json);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(@Param("id") Integer id);

	/**
	 * 查询职级名称数量
	 * @param name
	 * @return
	 */
	public int findCountByName(@Param("name")String name,@Param("id")Integer id);

}
