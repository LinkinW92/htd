package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的申请
 * @author yinp
 * @data 2021年5月10日
 */
public interface MyApplicationInquiryDao {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询明细
	 * @return
	 */
	public List<JSONObject> queryDetails(JSONObject json);

	/**
	 * 查询审批备注
	 * @param id
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(@Param("id")int id);

	/**
	 * 新增申请
	 * @param json
	 */
	public int addApprovalRecord(JSONObject json);

	/**
	 * 新增明细
	 * @param json
	 */
	public int addDetailed(JSONObject json);
	/**
	 * 新增审批备注
	 * @param json
	 * @return
	 */
	public int addApprovalRecordNote(JSONObject json);

	/**
	 * 查询审批记录表表格
	 * @param approvalRecordId
	 * @return
	 */
	public JSONObject findApprovalRecordTable(@Param("approvalRecordId")int approvalRecordId);

	/**
	 * 查询Execution
	 * @param businessKey
	 * @return
	 */
	public JSONObject findActRuExecution(@Param("businessKey")String businessKey);

	/**
	 * 更新审批记录表状态
	 * @param id
	 * @param state
	 * @return
	 */
	public int updateApprovalRecordState(@Param("id")int id,@Param("state")String state);

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<JSONObject> findUserAll();

	/**
	 * 查询现有的单据类型
	 * @return
	 */
	public List<JSONObject> findType();

	/**
	 * 通过单号查询单据信息
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalRecordByListNo(@Param("listNo")String listNo);

	/**
	 * 通过单号删除待审批记录
	 * @param listNo
	 * @return
	 */
	public int deleteApprovalRecordWithByListNo(@Param("listNo")String listNo);

	/**
	 * 加急
	 * @param listNo
	 * @return
	 */
	public int urgent(@Param("listNo")String listNo);

}
