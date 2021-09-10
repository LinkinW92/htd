package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 库存
 * @date 2021-07-12
 */
public interface WmsStockDao {

	/**
	 * 查询
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 删除
	 * @param json
	 * @return
	 */
	public int delete(JSONObject json);

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 通过单件码跟包装id查询库存
	 * @return
	 */
	public JSONObject findStockByUnitCodeAndPackingId(@Param("unitCode")String unitCode,@Param("packingId")String packingId);

	/**
	 * 通过工厂id跟库位id查询库存
	 * @param factoryId
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findStockByFactoryIdAndLocationId(@Param("factoryId")Integer factoryId,@Param("locationId")Integer locationId);

}
