package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.InventoryT;

/**
 * @date 2020年2月24日
 * @author yinp
 * 盘点单据
 */
public interface InventoryService {

	/**
	 * 查询
	 * @param inventory
	 * @return
	 */
	public List<InventoryT> findInventory(InventoryT inventory);

	/**
	 * 通过id查询
	 * @param inventory
	 * @return
	 */
	public InventoryT findInventoryById(Integer inventoryId);

	/**
	 * 新增
	 * @param inventory
	 * @return
	 */
	public boolean addInventory(InventoryT inventory);

	/**
	 * 更新
	 * @param inventory
	 * @return
	 */
	public boolean updateInventory(InventoryT inventory);

	/**
	 * 删除
	 * @param inventoryId
	 * @return
	 */
	public boolean deleteInventory(Integer inventoryId);
}
