package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 流程定义
 *
 * @author yinp
 * @data 2021年5月10日
 */
public interface ProcessDefinitionDao {

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
	public int addActivitiFormTemplate(JSONObject json);

	/**
	 * 通过id查询表单模板
	 *
	 * @param id
	 * @return
	 */
	public int findFormTemplateById(@Param("id") int id);

	/**
	 * 通过模板id查询模板是否被流程绑定
	 *
	 * @param formTemplateId
	 * @return
	 */
	public int findActivitiFormTemplateByFormTemplateId(@Param("formTemplateId") int formTemplateId);

	/**
	 * 查询表单模板
	 *
	 * @return
	 */
	public List<JSONObject> queryFormTemplate();

	/**
	 * 查询用户信息
	 *
	 * @return
	 */
	public List<JSONObject> queryUser();

	/**
	 * 查询模板明细
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findTemplateDetailed(@Param("id") int id);

	/**
	 * 查询流程xml
	 *
	 * @param key
	 * @return
	 */
	public JSONObject inquiryProcessXML(@Param("key") String key);

	/**
	 * 查询当前流程是否还有实例未完成
	 *
	 * @param key
	 * @return
	 */
	public int findApprovalRecordCount(@Param("key") String key);

	/**
	 * 查询部门
	 *
	 * @return
	 */
	public List<JSONObject> findDepartment();

	/**
	 * 查询职位
	 *
	 * @return
	 */
	public List<JSONObject> findPositionid();

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
	 * 更新表单模板与流程关联表职位
	 *
	 * @param json
	 * @return
	 */
	public int updateActivitiFormTemplatePositionId(@Param("positionIdJson") String positionIdJson, @Param("key") String key);

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
	 * 查询模板类型
	 * @param name
	 * @return
	 */
	public JSONObject findFormTemplateType(@Param("name")String name);

	/**
	 * 新增权限接口
	 * @param json
	 * @return
	 */
	public int addAuthorityInterface(JSONObject json);

	/**
	 * 通过菜单名称查询菜单count数量
	 * @param name
	 * @return
	 */
	public int findMenuCountByName(@Param("name")String name);

}
