package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 发起审批
 *
 * @author yinp
 * @data 2021年6月17日
 */
public interface InitiateApplicationDao {

	/**
	 * 查询审批流程
	 * @return
	 */
	public List<JSONObject> queryApprovalProcess();

	/**
	 * 通过Id查询表单
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findFormById(@Param("id") int id);

	/**
	 * 查询部门
	 *
	 * @param superiorDepartmentId
	 * @return
	 */
	public List<JSONObject> findDepartment(@Param("superiorDepartmentId") Integer superiorDepartmentId);

	/**
	 * 查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(@Param("departmentId") Integer departmentId);

	/**
	 * 获取地点
	 *
	 * @return
	 */
	public List<JSONObject> findPlaces();

	/**
	 * 获取外部联系人
	 *
	 * @return
	 */
	public List<JSONObject> findExternalContacts();

	/**
	 * 保存草稿
	 *
	 * @param userId
	 * @param details
	 * @param formTemplateId
	 */
	public void saveDraft(JSONObject json);

	/**
	 * 更新草稿
	 *
	 * @param id
	 * @param details
	 */
	public void updateDraft(@Param("id") Integer id, @Param("details") String details);

	/**
	 * 查询草稿
	 *
	 * @param userId
	 * @param formTemplateId
	 */
	public JSONObject findDraft(@Param("userId") Integer userId, @Param("formTemplateId") Integer formTemplateId);

	/**
	 * 查询部门主管信息
	 *
	 * @param userId
	 */
	public JSONObject findHeadOfDepartment(@Param("departmentId") Integer departmentId, @Param("roleId") Integer roleId);

	/**
	 * 通过用户id查询用户
	 * @param id
	 * @return
	 */
	public JSONObject findUserById(@Param("id")int id);

	/**
	 * 通过角色查询用户
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findUserByRole(@Param("roleId")int roleId);

	/**
	 * 查询用户
	 * @param json
	 * @return
	 */
	public List<JSONObject> findUser(JSONObject json);

	/**
	 * 查询今天表单发布的数量
	 * @return
	 */
	public int queryTheNumberOfFormsPublishedToday();

	/**
	 * 新增审批记录表
	 * @param json
	 * @return
	 */
	public int addApprovalRecord(JSONObject json);

	/**
	 * 新增审批记录详情表
	 * @param json
	 * @return
	 */
	public int addApprovalRecordDetailed(JSONObject json);

	/**
	 * 新增审批流程步序表
	 * @param json
	 * @return
	 */
	public int addApprovalRecordStep(JSONObject json);

	/**
	 * 新增待审批流程记录表
	 * @param json
	 * @return
	 */
	public int addApprovalRecordWith(JSONObject json);

	/**
	 * 删除草稿
	 * @param userId
	 * @param formTemplateId
	 * @return
	 */
	public int deleteDraft(@Param("userId")Integer userId,@Param("formTemplateId")Integer formTemplateId);

	/**
	 * 新增审批记录表
	 * @param json
	 * @return
	 */
	public int addApprovalNote(JSONObject json);

	/**
	 * 通过上级部门id查询部门
	 * @param superiorId
	 * @return
	 */
	public JSONObject findDepartmentById(@Param("id")Integer id);

	/**
	 * 通过职级id查询职级
	 * @param id
	 * @return
	 */
	public JSONObject findRankById(@Param("id")Integer id);

}
