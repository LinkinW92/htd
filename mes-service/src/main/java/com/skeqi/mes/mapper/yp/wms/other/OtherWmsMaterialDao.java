package com.skeqi.mes.mapper.yp.wms.other;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 物料
 * @date 2021-07-16
 */
public interface OtherWmsMaterialDao {

	/**
	 * 通过工厂id跟物料id查询工厂物料
	 * @param id
	 * @param factoryId
	 * @return
	 */
	public JSONObject findFactoryMaterialByIdAndFactoryId(@Param("id") Integer id,
			@Param("factoryId") Integer factoryId);
	
	/**
	 * 通过物料id查询物料
	 * @param id
	 * @return
	 */
	public JSONObject findMaterialById(@Param("id")Integer id);
	
	/**
	 * 通过物料编号查询物料
	 * @param materialNo
	 * @return
	 */
	public JSONObject findMaterialByNo(@Param("materialNo")String materialNo);

	/**
	 * 查询所有物料
	 * @return
	 */
	public List<JSONObject> findMaterialAll();

}
