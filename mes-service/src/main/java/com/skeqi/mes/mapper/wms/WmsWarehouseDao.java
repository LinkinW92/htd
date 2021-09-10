package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * @package com.skeqi.mapper.wms
 * @author yp
 * @date 2020年2月14日10:05:57
 * 仓库
 */
public interface WmsWarehouseDao {

	/**
	 * 查询仓库集合
	 * @param warehouse
	 * @return
	 */
	public List<WarehouseT> findWarehouseList(WarehouseT warehouse);

	/**
	 *  通过仓库名称、仓库id 查询count
	 * @param warehouseName
	 * @return
	 */
	public int findWarehouseCount(@Param("warehouseName")String warehouseName,@Param("id")int id);

	/**
	 * 新增仓库
	 * @param warehouse
	 * @return
	 */
	public Integer addWarehouse(WarehouseT warehouse);

	/**
	 * 删除仓库
	 * @param warehouseId
	 * @return
	 */
	public Integer deleteWarehouse(Integer warehouseId);

	/**
	 * 更新仓库
	 * @param warehouse
	 * @return
	 */
	public Integer updateWarehouse(WarehouseT warehouse);

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<JSONObject> findUserAll();

}
