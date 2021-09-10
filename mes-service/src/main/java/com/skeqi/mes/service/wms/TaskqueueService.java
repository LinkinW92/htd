package com.skeqi.mes.service.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 队列维护
 * @date 2020-12-9
 */
public interface TaskqueueService {

	/**
	 * @explain 查询队列集合
	 * @param type
	 * @return
	 */
	public List<JSONObject> list(int type);

	/**
	 * @explain 删除队列
	 * @param type
	 * @param id
	 */
	public void delete(int type, int condition, int id, String listNo, int locationId) throws Exception;

	/**
	 * @explain 查询库位集合
	 * @return
	 */
	public List<JSONObject> findLocation();

	/**
	 * @explain 手动模式
	 * @param tray
	 * @param Goods_Size
	 * @param From_Row
	 * @param From_List
	 * @param From_Layer
	 * @param To_Row
	 * @param To_List
	 * @param To_Layer
	 */
	public void ControlStock(JSONObject json) throws Exception;

}
