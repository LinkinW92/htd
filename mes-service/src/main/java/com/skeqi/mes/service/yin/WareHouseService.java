package com.skeqi.mes.service.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesInWareHouse;
import com.skeqi.mes.pojo.CMesLibraryPositionT;
import com.skeqi.mes.pojo.CMesMaterialMessage;
import com.skeqi.mes.pojo.CMesOutWareHouse;
import com.skeqi.mes.pojo.CMesWareHouseT;

public interface WareHouseService {
	/**
	 * 入库列表
	 */
	List<CMesInWareHouse> inWareHouseList(Map<String, Object> map);
	/**
	 * 添加入库信息
	 */
	void addPurchase(Map<String, Object> map);
	/**
	 * 修改入库信息
	 */
	void editPurchase(HashMap<String, Object> map);
	/**
	 * 删除入库物料信息
	 */
	void delPurchase(HashMap<String, Object> map);
	/**
	 * 出库列表
	 */
	List<CMesOutWareHouse> outWareHouseList(Map<String, Object> map);
	/**
	 * 添加出库信息
	 */
	void addShiments(Map<String, Object> map);
	/**
	 * 修改出库信息
	 */
	void editShipments(HashMap<String, Object> map);
	/**
	 * 删除出库信息
	 */
	void delShipments(HashMap<String, Object> map);
	/**
	 * 库存信息
	 */
	List<CMesMaterialMessage> stockManager(Map<String, Object> map);
	/**
	 * 添加到库存表
	 */
	void addStockManager(Map<String, Object> map);
	/**
	 * 修改库存数量
	 */
	void editStockManager(Map<String, Object> map);

	void delStockManager(HashMap<String, Object> map);

	/**
	 * 库位信息
	 */
	List<CMesLibraryPositionT> libraryPositionList(Map<String, Object> map);

	void editLibraryPosition(Map<String, Object> map);
	void editLibraryPosition2(HashMap<String, Object> map);
	List<CMesLibraryPositionT> repositoryManagement(Map<String, Object> map);
	List<CMesWareHouseT> wareHouseList(Map<String, Object> map);
	void addRepositoryManagement(Map<String, Object> map);
	void editRepositoryManagement(Map<String, Object> map);
	void delRepositoryManagement(HashMap<String, Object> map);
}
