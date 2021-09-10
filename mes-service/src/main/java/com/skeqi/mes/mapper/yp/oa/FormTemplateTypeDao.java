package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单模板类型
 *
 * @author yinp
 * @date 2021年5月27日
 */
public interface FormTemplateTypeDao {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list();

	/**
	 * 新增
	 *
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除
	 *
	 * @return
	 */
	public int delete(@Param("id") int id);

	/**
	 * 查询nam的count
	 *
	 * @param name
	 * @param id
	 * @return
	 */
	public int findFormTemplateTypeCountByName(@Param("name") String name, @Param("id") Integer id);

	/**
	 * 通过类型id查询表单模板数量
	 *
	 * @param id
	 * @return
	 */
	public int findFormTemplateCountByType(@Param("typeId") int typeId);

	/**
	 * 通过名称查询菜单
	 *
	 * @param name
	 * @return
	 */
	public JSONObject findMenuByName(@Param("name") String name);

	/**
	 * 新增菜单
	 *
	 * @param json
	 * @return
	 */
	public int addMenu(JSONObject json);

	/**
	 * 更新菜单
	 *
	 * @param json
	 * @return
	 */
	public int updateMenu(@Param("name") String name, @Param("oldName") String oldName);

	/**
	 * 删除菜单
	 * @param name
	 * @return
	 */
	public int deleteMenu(@Param("name") String name);

	/**
	 * 通过菜单名称查询菜单count数量
	 * @param name
	 * @return
	 */
	public int findMenuCountByName(@Param("name")String name);

}
