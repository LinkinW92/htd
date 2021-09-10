package com.skeqi.mes.service.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月14日10:05:57
 * 仓库
 */
public interface WmsWarehouseService {

	/**
	 * 查询仓库集合
	 * @param warehouse
	 * @return
	 */
	public List<WarehouseT> findWarehouseList(WarehouseT warehouse) throws ServicesException;

	/**
	 * 新增仓库
	 * @param warehouse
	 * @return
	 */
	public int addWarehouse(WarehouseT warehouse) throws Exception;

	/**
	 * 删除仓库
	 * @param warehouseId
	 * @return
	 */
	public int deleteWarehouse(Integer warehouseId) throws ServicesException;

	/**
	 * 更新仓库
	 * @param warehouse
	 * @return
	 */
	public int updateWarehouse(WarehouseT warehouse) throws Exception;

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<JSONObject> findUserAll();

}
