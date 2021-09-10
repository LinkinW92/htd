package com.skeqi.mes.mapper.yp.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 外部联系人标签类型
 *
 * @author yinp
 * @data 2021年6月8日
 */
public interface ExternalContactTagTypeDao {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list();

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
	 * 通过名称查询存在的数量
	 * @param name
	 * @param id
	 * @return
	 */
	public int findTagCountByName(@Param("name") String name, @Param("id") Integer id);

	/**
	 * 通过标签类型查询该类型下绑定的标签数量
	 * @param typeId
	 * @return
	 */
	public int findExternalContactTagByTypeId(@Param("typeId")int typeId);

}
