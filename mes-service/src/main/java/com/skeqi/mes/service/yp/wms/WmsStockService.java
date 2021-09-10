package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 库存
 * @date 2021-07-12
 */
public interface WmsStockService {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @param json
	 * @return
	 */
	public void delete(JSONObject json) throws Exception;

	/**
	 * 通过单件码跟包装id查询库存
	 *
	 * @return
	 */
	public JSONObject findStockByUnitCodeAndPackingId(String unitCode, String packingId);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 通过工厂id跟库位id查询库存
	 * @param factoryId
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findStockByFactoryIdAndLocationId(Integer factoryId,Integer locationId);


}
