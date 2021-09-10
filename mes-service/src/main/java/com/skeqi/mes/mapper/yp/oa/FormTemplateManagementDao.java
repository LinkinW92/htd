package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单模板管理
 *
 * @author yinp
 * @data 2021年5月5日
 *
 */
public interface FormTemplateManagementDao {

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
	 * 查询当前输入的名称是否存在
	 *
	 * @param name
	 * @param id
	 * @return
	 */
	public int queryWhetherTheCurrentEnteredNameExists(@Param("name") String name, @Param("typeId") int typeId,
			@Param("id") Integer id);

	/**
	 * 通过表单模板id删除表单模板明细
	 *
	 * @param formTemplateId
	 * @return
	 */
	public int deleteTemplateDetailed(@Param("formTemplateId") int formTemplateId);

	/**
	 * 新增表单模板明细
	 *
	 * @param formTemplateId
	 * @return
	 */
	public int addTemplateDetailed(JSONObject json);

	/**
	 * 查询模板
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findTemplateById(@Param("id") int id);

	/**
	 * 通过表单模板id删除表单模板表格
	 *
	 * @param formTemplateId
	 * @return
	 */
	public int deleteTemplateTable(@Param("formTemplateId") int formTemplateId,@Param("group")String group);

	/**
	 * 新增表单模板表格
	 *
	 * @param json
	 * @return
	 */
	public int addTemplateTable(JSONObject json);

	/**
	 * 查询表单模板表格
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findFormTemplateTable(@Param("formTemplateId") int formTemplateId,@Param("group")String group);

	/**
	 * 更新activiti的里流程name
	 *
	 * @param key
	 * @param name
	 * @return
	 */
	public int updateActivitiName(@Param("key") String key, @Param("name") String name);

	/**
	 * 更新表单模板与流程关联表 name
	 *
	 * @param key
	 * @param name
	 * @return
	 */
	public int updateActivitiFormTemplate(@Param("key") String key, @Param("name") String name);

	/**
	 * 查询表单模板与流程关联表
	 *
	 * @param formTemplateId
	 * @return
	 */
	public JSONObject findActivitiFormTemplate(@Param("formTemplateId") int formTemplateId);

	/**
	 * 删除表单模板与流程关联表
	 *
	 * @param key
	 * @return
	 */
	public int deleteActivitiFormTemplate(@Param("key") String key);

	/**
	 * 更新申请单状态
	 *
	 * @param key
	 * @return
	 */
	public int updateApprovalRecordState(@Param("key") String key);

	/**
	 * 查询表单模板类型
	 *
	 * @return
	 */
	public List<JSONObject> findFormTemplateType();

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
	 * 查询组
	 * @param formTemplateId
	 * @return
	 */
	public List<JSONObject> findGroup(@Param("formTemplateId")int formTemplateId);

	/**
	 * 删除组
	 * @param formTemplateId
	 * @param group
	 */
	public void deleteGroup(@Param("formTemplateId")int formTemplateId,@Param("group")String group);

	/**
	 * 修改状态
	 * @param id
	 * @param state
	 */
	public int updateState(@Param("id")int id,@Param("state")String state);

	/**
	 * 删除草稿
	 * @param formTemplateId
	 * @return
	 */
	public int deleteDraft(@Param("formTemplateId")Integer formTemplateId);

	/**
	 * 查询编号的数量
	 * @param code
	 * @return
	 */
	public int findCodeConut(@Param("code")String code,@Param("id")Integer id);

}
