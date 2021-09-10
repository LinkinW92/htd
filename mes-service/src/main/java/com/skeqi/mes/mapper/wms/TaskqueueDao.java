package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 队列维护
 * @date 2020-12-9
 */
public interface TaskqueueDao {

	/**
	 * @explain 查询临时出库队列
	 * @return
	 */
	public List<JSONObject> findROutTaskqueue();

	/**
	 * @explain 查询临时入库队列
	 * @return
	 */
	public List<JSONObject> findRInTaskqueue();

	/**
	 * @explain 删除临时出库队列
	 * @param id
	 * @return
	 */
	public int deleteROutTaskqueue(@Param("id")int id);

	/**
	 * @explain 删除临时入库队列
	 * @param id
	 * @return
	 */
	public int deleteRInTaskqueue(@Param("id")int id);

	/**
	 * @explain 删除临时库存详情
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public int deleteRStorageDetail(@Param("listNo")String listNo, @Param("locationId")int locationId);

	/**
	 * @explain 查询库位集合
	 * @return
	 */
	public List<JSONObject> findLocation();

	/**
	 * @explain 查询库存详情
	 * @param listNo
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findRStorageDetail(@Param("listNo")String listNo, @Param("locationId")int locationId);

	/**
	 * @explain 查询待出库的物料
	 * @param locationId
	 * @param projectId
	 * @param materialId
	 * @return
	 */
	public List<JSONObject> findMaterialNumber(@Param("locationId")int locationId, @Param("projectId")int projectId, @Param("materialId")int materialId);

	/**
	 * @explain 修改库存即将出库数量
	 * @param id
	 * @return
	 */
	public int updateMaterialNumberLmminentRelease(@Param("materialNumber")Integer materialNumber, @Param("materialNumberId")Integer materialNumberId);
}
