package com.skeqi.mes.service.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 领用出库
 *
 * @author yinp
 * @date 2020年3月18日
 *
 */
public interface LeadOutService {

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
	public List<JSONObject> findMaterialByName(String materialName);

	/**
	 * 通过名称查询项目id、NAME
	 *
	 * @param projectName
	 * @return
	 */
	public List<JSONObject> findProjectByName(String projectName);

	/**
	 * 查询库位id、NAME
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
	public List<JSONObject> findStock(Integer materialId, Integer projectId, Integer locationId);

	/**
	 * 提交
	 * @param json
	 */
	public void sub(JSONObject json) throws Exception;

}
