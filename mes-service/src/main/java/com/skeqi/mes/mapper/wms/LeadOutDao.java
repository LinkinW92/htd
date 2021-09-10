package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 领用出库
 *
 * @author yinp
 * @date 2020年3月18日
 *
 */
public interface LeadOutDao {

	/**
	 * 查询
	 * @param josn
	 * @return
	 */
	public List<JSONObject> list(JSONObject josn);

	/**
	 * 通过名称查询物料id、NAME
	 *
	 * @param materialName
	 * @return
	 */
	public List<JSONObject> findMaterialByName(@Param("materialName") String materialName);

	/**
	 * 通过名称查询项目id、NAME
	 *
	 * @param projectName
	 * @return
	 */
	public List<JSONObject> findProjectByName(@Param("projectName") String projectName);

	/**
	 * 查询库位id、NAME
	 *
	 * @return
	 */
	public List<JSONObject> findLocationAll();

	/**
	 * 通过物料、项目查询库存
	 *
	 * @param materialId
	 * @param projectId
	 * @return
	 */
	public List<JSONObject> findStock(@Param("materialId") Integer materialId, @Param("projectId") Integer projectId,
			@Param("locationId") Integer locationId);

	/**
	 * 新增临时库存详情表记录
	 *
	 * @param sql
	 * @return
	 */
	public int addRStorageDetail(@Param("sql") String sql);

	/**
	 * 更新物料库存待出库字段
	 *
	 * @param id
	 * @param selectedNumber
	 * @return
	 */
	public int updateStockNumber(@Param("id") int id, @Param("selectedNumber") int selectedNumber);

	/**
	 * 新增临时出库队列
	 * @param sql
	 * @return
	 */
	public int addROutTaskqueue(@Param("sql") String sql);

	/**
	 * 新增审批表
	 * @param json
	 * @return
	 */
	public int addApproval(JSONObject json);

}
