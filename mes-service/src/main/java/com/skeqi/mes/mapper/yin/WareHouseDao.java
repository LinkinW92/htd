package com.skeqi.mes.mapper.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skeqi.mes.pojo.CMesInWareHouse;
import com.skeqi.mes.pojo.CMesLibraryPositionT;
import com.skeqi.mes.pojo.CMesMaterialMessage;
import com.skeqi.mes.pojo.CMesOutWareHouse;
import com.skeqi.mes.pojo.CMesWareHouseT;
@Repository
public interface WareHouseDao {

	List<CMesInWareHouse> inWareHouseList(Map<String, Object> map);

	void addPurchase(Map<String, Object> map);

	void editPurchase(HashMap<String, Object> map);

	void delPurchase(HashMap<String, Object> map);

	List<CMesOutWareHouse> outWareHouseList(Map<String, Object> map);

	void addShiments(Map<String, Object> map);

	void editShipments(HashMap<String, Object> map);

	void delShipments(HashMap<String, Object> map);

	List<CMesMaterialMessage> stockManager(Map<String, Object> map);

	void addStockManager(Map<String, Object> map);

	void editStockManager(Map<String, Object> map);

	void delStockManager(HashMap<String, Object> map);

	List<CMesLibraryPositionT> libraryPositionList(Map<String, Object> map);
	List<CMesLibraryPositionT> repositoryManagement(Map<String, Object> map);

	void editLibraryPosition(Map<String, Object> map);

	void editLibraryPosition2(HashMap<String, Object> map);

	List<CMesWareHouseT> wareHouseList(Map<String, Object> map);

	void addRepositoryManagement(Map<String, Object> map);

	void editRepositoryManagement(Map<String, Object> map);

	void delRepositoryManagement(HashMap<String, Object> map);


}
