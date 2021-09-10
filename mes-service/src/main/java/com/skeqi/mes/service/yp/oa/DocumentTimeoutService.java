package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 单据超时
 * @author yinp
 * @date 2021年8月12日
 */
public interface DocumentTimeoutService {

	/**
	 * 查询审批单据
	 */
	public List<JSONObject> findApprovalRecord();

	/**
	 * 查询待审批人
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> queryApprover(String listNo);

	/**
	 * 更新已超时列
	 * @param listNo
	 * @return
	 */
	public void updateTimedOut(String listNo);

	/**
	 * 查询待审批数据
	 * @return
	 */
	public List<JSONObject> findPendingApproval();

	/**
	 * 更新待审批已超时列
	 * @param id
	 * @return
	 */
	public void updateTimedOutWith(Integer id);

}
