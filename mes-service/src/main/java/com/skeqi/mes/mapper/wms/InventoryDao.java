package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.InventoryT;

/**
 * @date 2020年2月24日
 * @author yinp
 * 盘点单据
 */
public interface InventoryDao {

	/**
	 * 查询
	 * @param inventory
	 * @return
	 */
	public List<InventoryT> findInventory(InventoryT inventory);

	/**
	 * 新增
	 * @param inventory
	 * @return
	 */
	public Integer addInventory(InventoryT inventory);

	/**
	 * 更新
	 * @param inventory
	 * @return
	 */
	public Integer updateInventory(InventoryT inventory);

	/**
	 * 删除
	 * @param inventoryId
	 * @return
	 */
	public Integer deleteInventory(Integer inventoryId);

}
