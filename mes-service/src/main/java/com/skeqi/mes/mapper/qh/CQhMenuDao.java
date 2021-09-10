package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.qh.CQhMenuT;

/**
 * @author yinp
 * @explain 菜单
 * @date 2020-9-7
 */
public interface CQhMenuDao {

	/**
	 * @explain 按条件查询菜单
	 * @param json
	 * @return
	 */
	public List<JSONObject> findMenuList(JSONObject json);

	/**
	 * @explain 通过上级id查询菜单
	 * @param json
	 * @return
	 */
	public List<CQhMenuT> findMenuBySuperiorMenuIdList(@Param("superiorMenuId")Integer superiorMenuId);

	/**
	 * @explain 通过id查询菜单
	 * @param json
	 * @return
	 */
	public CQhMenuT findMenuById(@Param("id")Integer id);

	/**
	 * @explain 通过id跟名称查询菜单是否存在
	 * @param id
	 * @param menuName
	 * @return
	 */
	public Integer findMenuBYIdAndName(@Param("id")Integer id,
			@Param("menuName")String menuName);

	/**
	 * @explain 新增菜单
	 * @param dx
	 * @return
	 */
	public Integer addMenu(JSONObject dx);

	/**
	 * @explain 更新菜单
	 * @param dx
	 * @return
	 */
	public Integer updateMenu(JSONObject dx);

	/**
	 * @explain 通过id删除菜单
	 * @param id
	 * @return
	 */
	public Integer deleteMenuById(@Param("id")Integer id);

	/**
	 * @explain 通过上级id删除菜单
	 * @param id
	 * @return
	 */
	public Integer deleteMenuBySuperiorMenuId(@Param("superiorMenuId")Integer superiorMenuId);

}
