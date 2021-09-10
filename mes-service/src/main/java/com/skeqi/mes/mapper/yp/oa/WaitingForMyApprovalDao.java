package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 待我审批
 *
 * @author yinp
 * @data 2021年5月10日
 */
public interface WaitingForMyApprovalDao {

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
	public JSONObject queryDetails(@Param("listNo") String listNo);

	/**
	 * 查询审批人
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findApprovalRecordWith(@Param("listNo") String listNo);

	/**
	 * 查询详情
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalRecordDetailed(@Param("listNo") String listNo);

	/**
	 * 修改审批详情
	 *
	 * @param json
	 * @return
	 */
	public int updateApprovalRecordDetailed(JSONObject json);

	/**
	 * 修改单据状态
	 *
	 * @param listNo
	 * @param state
	 * @return
	 */
	public int updateApprovalRecordState(@Param("listNo") String listNo, @Param("state") String state);

	/**
	 * 新增审批记录表
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalRecordNote(JSONObject json);

	/**
	 * 删除待审批流程记录表
	 *
	 * @param userId
	 * @param listNo
	 * @return
	 */
	public int deleteApprovalRecordWith(@Param("userId") String userId, @Param("listNo") String listNo);

	/**
	 * 新增待审批流程记录表
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalRecordWith(JSONObject json);

	/**
	 * 查询审批记录
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(@Param("listNo") String listNo);

	/**
	 * 查询单据
	 *
	 * @param listNo
	 * @return
	 */
	public JSONObject findApprovalRecord(@Param("listNo") String listNo);

	/**
	 * 新增我的已批记录
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalBatchRecords(JSONObject json);

	/**
	 * 通过id查询用户
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findUserById(@Param("id") Integer id);

	/**
	 * 新增审批记录表
	 *
	 * @param json
	 * @return
	 */
	public int addApprovalNote(JSONObject json);

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	public List<JSONObject> findUserAll();
}
