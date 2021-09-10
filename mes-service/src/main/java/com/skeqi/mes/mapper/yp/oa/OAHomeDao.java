package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * OA首页
 *
 * @author yinp
 * @data 2021年7月09日
 */
public interface OAHomeDao {

	/**
	 * 按月查询我发起的申请数量
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	public int findMyApprovalNumber(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 按月查询与我相关的审批单数量
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	public int findItSAboutMeNumber(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 按月查询我审批的单据数量
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	public int findMyApprovalRecordNumber(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 按月查询待我审批的单据数量
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	public int findApprovalWithNumber(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 查询待办
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findDaiBan(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 查询已办
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findDone(@Param("userId") Integer userId, @Param("date") String date);

	/**
	 * 查询办结
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findToConclude(@Param("userId") Integer userId, @Param("date") String date);

}
