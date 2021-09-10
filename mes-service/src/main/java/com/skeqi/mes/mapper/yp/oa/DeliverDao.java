package com.skeqi.mes.mapper.yp.oa;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

public interface DeliverDao {

	/**
	 * 查询审批详情
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalDetailed(@Param("listNo") String listNo);

	/**
	 * 通过id查询用户
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findUserById(@Param("id") Integer id);

	/**
	 * 更新审批详情
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public int updateApprovalDetailed(@Param("listNo") String listNo, @Param("step") String step);

	/**
	 * 删除待审批记录
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public int deleteApprovalWith(@Param("listNo") String listNo, @Param("userId") Integer userId);

	/**
	 * 新增待审批记录
	 * @param json
	 * @return
	 */
	public int addApprovalWith(JSONObject json);

	/**
	 * 新增审批记录
	 * @param json
	 * @return
	 */
	public int addApprovalNote(JSONObject json);

}
