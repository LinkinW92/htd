package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 草稿箱
 *
 * @author yinp
 * @data 2021年6月2日
 *
 */
public interface DraftsDao {

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
	public JSONObject findApprovalRecordDrafts(@Param("listNo") String listNo);

	/**
	 * 查询单据详情
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findApprovalRecordDetailedDrafts(@Param("listNo") String listNo);

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
	public List<JSONObject> findDepartmentByCompanyId(@Param("companyId") int companyId);

	/**
	 * 通过部门ID查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(@Param("departmentId") int departmentId);

	/**
	 * 通过id查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public JSONObject findUserById(@Param("id") int id);

	/**
	 * 查询模板表格
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findTemplateTable(@Param("id") int id);

	/**
	 * 新增申请(草稿箱)
	 *
	 * @param json
	 */
	public int addDraft(JSONObject json);

	/**
	 * 新增明细(草稿箱)
	 *
	 * @param json
	 */
	public int addDetailedDraft(JSONObject json);

	/**
	 * 新增审批记录表格(草稿箱)
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalRecordTableDraft(JSONObject json);

	/**
	 * 通过单据号查询草稿
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findDraftsByListNo(@Param("listNo") String listNo);

	/**
	 * 通过单据号删除草稿
	 *
	 * @param listNo
	 * @return
	 */
	public int deleteDraftsByListNo(@Param("listNo") String listNo);

	/**
	 * 单据id删除草稿详情
	 *
	 * @param listNo
	 * @return
	 */
	public int deleteDraftsDetailedByApprovalRecordId(@Param("approvalRecordId") int approvalRecordId);

	/**
	 * 单据id删除草稿表格
	 *
	 * @param listNo
	 * @return
	 */
	public int deleteDraftsTableByApprovalRecordId(@Param("approvalRecordId") int approvalRecordId);

	/**
	 * 新增审批备注
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalRecordNote(JSONObject json);

	/**
	 * 通过key查询表单模板与流程关联表
	 *
	 * @param activitiKey
	 * @return
	 */
	public JSONObject findActivitiFormTemplateByActivitiKey(@Param("activitiKey") String activitiKey);

	/**
	 * 通过用户id查询用户部门信息
	 *
	 * @param userId
	 * @return
	 */
	public JSONObject findDepartmentByUserId(@Param("userId") int userId);


	/**
	 * 查询用户职级
	 *
	 * @param userId
	 * @return
	 */
	public Integer findUserRankID(@Param("userId") int userId);

	/**
	 * 通过部门查询职级
	 *
	 * @param departmentId
	 * @param rankID
	 * @return
	 */
	public JSONObject queryRankByDepartment(@Param("departmentId") int departmentId, @Param("rankID") int rankID);

	/**
	 * 新增申请
	 *
	 * @param json
	 */
	public int add(JSONObject json);

	/**
	 * 新增明细
	 *
	 * @param json
	 */
	public int addDetailed(JSONObject json);

	/**
	 * 新增审批记录表格
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalRecordTable(JSONObject json);

	/**
	 * 查询表格明细
	 * @param listNo
	 * @return
	 */
	public JSONObject findTableDrafts(@Param("listNo")String listNo);

	/**
	 * 查询详情
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findDetailedDraftsByListNo(@Param("listNo")String listNo);

	/**
	 * 查询表格详情
	 * @param listNo
	 * @return
	 */
	public JSONObject findTableDraftsByListNo(@Param("listNo")String listNo);

	/**
	 * 新增申请单详情
	 * @param json
	 * @return
	 */
	public int addApprovalRecordDetailed(JSONObject json);

}
