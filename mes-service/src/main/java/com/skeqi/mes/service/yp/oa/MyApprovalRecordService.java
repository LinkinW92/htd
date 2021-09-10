package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的审批记录
 *
 * @author yinp
 * @data 2021年5月13日
 *
 */
public interface MyApprovalRecordService {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询明细
	 * @param json
	 * @return
	 */
	public List<JSONObject> queryDetails(JSONObject json);

	/**
	 * 查询审批备注
	 * @param id
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(int id);

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

}
