package com.skeqi.mes.service.wms;

import java.util.List;

import com.skeqi.mes.pojo.wms.InventoryDetailT;

/**
 * @date 2020年2月24日
 * @author YinP 库存盘点详情
 */
public interface InventoryDetailService {

	/**
	 * 查询
	 * @param inventoryDetail
	 * @return
	 */
	public List<InventoryDetailT> findInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 通过id查询
	 * @param inventoryDetailId
	 * @return
	 */
	public InventoryDetailT findInventoryDetailById(Integer inventoryDetailId);

	/**
	 * 新增
	 * @param inventoryDetail
	 * @return
	 */
	public boolean addInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 更新
	 * @param inventoryDetail
	 * @return
	 */
	public boolean updateInventoryDetail(InventoryDetailT inventoryDetail);

	/**
	 * 删除
	 * @param inventoryDetailId
	 * @return
	 */
	public boolean deleteInventoryDetail(Integer inventoryDetailId);
}
