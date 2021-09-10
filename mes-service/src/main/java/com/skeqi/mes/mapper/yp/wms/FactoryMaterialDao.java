package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 工厂物料
 * @date 2021-07-06
 */
public interface FactoryMaterialDao {

	/**
	 * 查询集合
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
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") Integer id);

	/**
	 * 查询所有物料
	 *
	 * @return
	 */
	public List<JSONObject> findMaterialAll();

	/**
	 * 查询所有工厂
	 *
	 * @return
	 */
	public List<JSONObject> findFactoryAll();

	/**
	 * 查询物料id存在的数量
	 * @param factoryId
	 * @param materialId
	 * @return
	 */
	public int findMaterialIdCount(@Param("factoryId") Integer factoryId, @Param("materialId") Integer materialId);

}
