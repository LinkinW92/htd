package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 单据信息
 * @author yinp
 * @data 2021年5月21日
 *
 */
public interface SinglePageDao {

	/**
	 * 查询
	 * @return
	 */
	public JSONObject query(@Param("listNo")String listNo);

	/**
	 * 查询明细
	 * @return
	 */
	public List<JSONObject> queryDetails(@Param("listNo")String listNo);

	/**
	 * 查询审批备注
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findApprovalRecordNote(@Param("listNo")String listNo);

	/**
	 * 查询审批记录表表格
	 * @param approvalRecordId
	 * @return
	 */
	public JSONObject findApprovalRecordTable(@Param("listNo")String listNo);

}
