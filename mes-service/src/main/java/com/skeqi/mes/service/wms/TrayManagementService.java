package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsLocationT;


/**
 * 托盘管理
 * @author yinp
 *
 */
public interface TrayManagementService {

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public List<JSONObject> findList(JSONObject json);

	/**
	 * 查询库位id+name集合
	 * @return
	 */
	public List<CWmsLocationT> findLocationListIdAndName();

	/**
	 * 通过库位id查询物料库存
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findMaterialNumberList(Integer locationId);

	/**
	 * 放入空托盘
	 * @param json
	 * @return
	 */
	public void putInNullTray(JSONObject json)throws Exception;

	/**
	 * 移出空托盘
	 * @param json
	 * @return
	 */
	public void moveOutNullTray(int locationId)throws Exception;

}
