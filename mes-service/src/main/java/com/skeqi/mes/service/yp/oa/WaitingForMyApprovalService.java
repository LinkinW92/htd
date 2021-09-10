package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 待我审批
 *
 * @author yinp
 * @data 2021年5月10日
 */
public interface WaitingForMyApprovalService {

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
	 * @param json
	 * @return
	 */
	public JSONObject queryDetails(String listNo, Integer userId);

	/**
	 * 审批
	 *
	 * @param json
	 */
	public void Approved(JSONObject json) throws Exception;

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	public List<JSONObject> findUserAll();

	/**
	 * 修改表单
	 * @param json
	 */
	public void updateForm(JSONObject json) throws Exception;

}
