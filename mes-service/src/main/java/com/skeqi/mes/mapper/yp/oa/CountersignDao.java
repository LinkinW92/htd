package com.skeqi.mes.mapper.yp.oa;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 加签
 *
 * @author yinp
 * @data 2021年6月29日
 */
public interface CountersignDao {

	/**
	 * 查询审批详情
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalDetailed(@Param("listNo") String listNo);

	/**
	 * 更新审批详情步骤
	 * @param listNo
	 * @param step
	 * @return
	 */
	public int updateApprovalDetailedStep(@Param("listNo")String listNo,@Param("step")String step);

	/**
	 * 新增审批记录
	 * @param json
	 * @return
	 */
	public int addAapprovalNote(JSONObject json);

	/**
	 * 删除待审批记录
	 * @param userId
	 * @param listNo
	 * @return
	 */
	public int deleteApprovalWith(@Param("userId")Integer userId,@Param("listNo")String listNo);

	/**
	 * 新增待审批记录
	 * @param json
	 * @return
	 */
	public int addApprovalWith(JSONObject json);

}
