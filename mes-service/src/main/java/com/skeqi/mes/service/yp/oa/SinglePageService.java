package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 单据信息
 * @author yinp
 * @data 2021年5月21日
 *
 */
public interface SinglePageService {

	/**
	 * 查询
	 * @return
	 */
	public JSONObject query(String listNo);

	/**
	 * 查询明细
	 * @return
	 */
	public List<JSONObject> queryDetails(String listNo);

	/**
	 * 查询审批备注
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(String listNo);

	/**
	 * 查询审批记录表表格
	 * @param approvalRecordId
	 * @return
	 */
	public JSONObject findApprovalRecordTable(String listNo);

}
