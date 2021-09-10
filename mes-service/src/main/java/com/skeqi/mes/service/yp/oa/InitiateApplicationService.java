package com.skeqi.mes.service.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 发起审批
 *
 * @author yinp
 * @data 2021年6月17日
 */
public interface InitiateApplicationService {

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
	public JSONObject findFormById(Integer id, Integer userId);

	/**
	 * 查询部门
	 *
	 * @param superiorDepartmentId
	 * @return
	 */
	public List<JSONObject> findDepartment(Integer superiorDepartmentId);

	/**
	 * 查询部门跟用户
	 *
	 * @param superiorDepartmentId
	 * @return
	 */
	public List<JSONObject> findDepartmentAndUser(Integer superiorDepartmentId);

	/**
	 * 获取流程
	 *
	 * @param details
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> acquisitionProcess(JSONObject json) throws Exception;

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
	 * 更新草稿
	 *
	 * @param id
	 * @param details
	 */
	public void updateDraft(Integer id,String details);

	/**
	 * 发布
	 * @param json
	 */
	public String release(JSONObject json) throws Exception;

}
