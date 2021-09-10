package com.skeqi.mes.service.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.qh.CQhMenuT;

/**
 * @author yinp
 * @explain 菜单
 * @date 2020-9-7
 */
public interface CQhMenuService {

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
	public List<CQhMenuT> findMenuBySuperiorMenuIdList(Integer superiorMenuId);

	/**
	 * @explain 新增菜单
	 * @param dx
	 * @return
	 */
	public Integer addMenu(JSONObject dx) throws Exception ;

	/**
	 * @explain 更新菜单
	 * @param dx
	 * @return
	 */
	public Integer updateMenu(JSONObject dx) throws Exception ;

	/**
	 * @explain 删除菜单
	 * @param id
	 * @return
	 */
	public Integer deleteMenu(Integer id);
}
