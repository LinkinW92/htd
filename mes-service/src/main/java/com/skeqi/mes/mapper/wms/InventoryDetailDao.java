package com.skeqi.mes.mapper.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.InventoryDetailT;

/**
 * @date 2020年2月24日
 * @author YinP
 * 库存盘点详情
 */
public interface InventoryDetailDao {

	/**
	 * 查询
	 * @param inventoryDetail
	 * @return
	 */
	public List<InventoryDetailT> findInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 新增
	 * @param inventoryDetail
	 * @return
	 */
	public Integer addInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 更新
	 * @param inventoryDetail
	 * @return
	 */
	public Integer updateInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 删除
	 * @param inventoryDetailId
	 * @return
	 */
	public Integer deleteInventoryDetail(Integer inventoryDetailId);

}
