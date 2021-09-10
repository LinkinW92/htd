package com.skeqi.mes.service.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 草稿箱
 *
 * @author yinp
 * @data 2021年6月2日
 *
 */
public interface DraftsService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询现有的单据类型
	 *
	 * @return
	 */
	public List<JSONObject> findType();

	/**
	 * 查询单据
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalRecordDrafts(String listNo);

	/**
	 * 查询单据详情
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findApprovalRecordDetailedDrafts(String listNo);

	/**
	 * 查询公司
	 *
	 * @return
	 */
	public List<JSONObject> findCompany();

	/**
	 * 通过公司ID查询部门
	 *
	 * @param companyId
	 * @return
	 */
	public List<JSONObject> findDepartmentByCompanyId(int companyId);

	/**
	 * 通过部门ID查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(int departmentId);

	/**
	 * 通过id查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public JSONObject findUserById(int id);

	/**
	 * 保存草稿
	 *
	 * @param json
	 */
	public void saveDraft(JSONObject json, HttpServletRequest request) throws Exception;

	/**
	 * 发布
	 *
	 * @param json
	 * @param request
	 * @throws Exception
	 */
	public void release(JSONObject json, HttpServletRequest request) throws Exception;

	/**
	 * 查询表格明细
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findTableDrafts(String listNo);

	/**
	 * 直接发布
	 * @param listNo
	 * @throws Exception
	 */
	public void directRelease(String listNo) throws Exception;

}
