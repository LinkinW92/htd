package com.skeqi.mes.service.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.qh.CQhAuthorityInterfaceT;
import com.skeqi.mes.pojo.qh.CQhMenuT;

/**
 * @author yinp
 * @explain 模块接口
 * @date 2020-9-9
 */
public interface CQhAuthorityInterfaceService {

	/**
	 * @explain 查询模块接口集合
	 * @param json
	 * @return
	 */
	public List<CQhAuthorityInterfaceT> queryAuthorityInterfaceList(JSONObject json);

	/**
	 * @explain 通过上级id查询菜单
	 * @param json
	 * @return
	 */
	public List<CQhMenuT> findMenuBySuperiorMenuIdList(Integer superiorMenuId);

	/**
	 * @explain 新增模块接口
	 * @param dx
	 * @return
	 */
	public Integer addAuthorityInterface(JSONObject json);

	/**
	 * @explain 更新模块接口
	 * @param dx
	 * @return
	 */
	public Integer updateAuthorityInterface(JSONObject json);

	/**
	 * @explain 删除模块接口
	 * @param id
	 * @return
	 */
	public Integer deleteAuthorityInterface(Integer id);

	/**
	 * @explain 查询所有菜单
	 * @return
	 */
	public List<CQhMenuT> findMenuAll();

}
