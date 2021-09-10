package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产领用
 * @date 2021-08-9
 */
public interface WmsProductionRequisitionDao {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 查询BOM
	 *
	 * @param workOrderNo
	 * @param stationId
	 * @return
	 */
	public List<JSONObject> findBom(@Param("workOrderNo") String workOrderNo, @Param("stationId") String stationId);

	/**
	 * 新增R
	 *
	 * @param json
	 * @return
	 */
	public int addR(JSONObject json);

	/**
	 * 查询R跟D表
	 *
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo") String listNo);

	/**
	 * 通过工单编号查询已出的物料数量
	 *
	 * @param workOrderNo
	 * @return
	 */
	public List<JSONObject> queryTheIssuedMaterialQuantityByWorkOrderNo(@Param("workOrderNo") String workOrderNo,
			@Param("materialNo") String materialNo);

	/**
	 * 新增D
	 *
	 * @param json
	 * @return
	 */
	public int addD(JSONObject json);

	/**
	 * 通过id查询R表
	 * @param id
	 * @return
	 */
	public JSONObject findRById(@Param("id")Integer id);

	/**
	 * 更新行表
	 * @param json
	 * @return
	 */
	public int updateR(JSONObject json);

	/**
	 * 查询详情表
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 删除行表
	 * @param id
	 * @return
	 */
	public int deleteR(@Param("id")Integer id);

	/**
	 * 删除详情表
	 * @param id
	 * @return
	 */
	public int deleteD(@Param("id")Integer id);

	/**
	 * 通过行ID删除详情表
	 * @param id
	 * @return
	 */
	public int deleteDByRId(@Param("rId")Integer rId);

	/**
	 * 查询需过账数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> queryDataToBePosted(@Param("listNo")String listNo);

}
