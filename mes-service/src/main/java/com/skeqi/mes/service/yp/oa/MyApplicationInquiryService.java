package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的申请
 *
 * @author yinp
 * @data 2021年5月10日
 */
public interface MyApplicationInquiryService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询明细
	 *
	 * @return
	 */
	public List<JSONObject> queryDetails(JSONObject json);

	/**
	 * 查询审批备注
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(int id);

	/**
	 * 重新发起申请
	 *
	 * @param json
	 */
	public void reInitiateApplication(JSONObject json) throws Exception;

	/**
	 * 查询审批记录表表格
	 *
	 * @param approvalRecordId
	 * @return
	 */
	public JSONObject findApprovalRecordTable(int approvalRecordId);

	/**
	 * 撤销
	 *
	 * @param id
	 */
	public void revoke(String listNo, Integer userId) throws Exception;

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
	 * 加急
	 * @param listNo
	 * @return
	 */
	public void urgent(String listNo);

}
