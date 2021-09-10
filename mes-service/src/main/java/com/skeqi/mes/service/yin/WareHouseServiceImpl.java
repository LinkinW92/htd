package com.skeqi.mes.service.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.WareHouseDao;
import com.skeqi.mes.pojo.CMesInWareHouse;
import com.skeqi.mes.pojo.CMesLibraryPositionT;
import com.skeqi.mes.pojo.CMesMaterialMessage;
import com.skeqi.mes.pojo.CMesOutWareHouse;
import com.skeqi.mes.pojo.CMesWareHouseT;
@Service
public class WareHouseServiceImpl implements WareHouseService {
	@Autowired
	WareHouseDao wareHouseDao;

	@Override
	public List<CMesInWareHouse> inWareHouseList(Map<String, Object> map) {
		return wareHouseDao.inWareHouseList(map);
	}

	@Transactional
	@Override
	public void addPurchase(Map<String, Object> map) {
		wareHouseDao.addPurchase(map);
	}
	@Transactional
	@Override
	public void editPurchase(HashMap<String, Object> map) {
		wareHouseDao.editPurchase(map);
	}
	@Transactional
	@Override
	public void delPurchase(HashMap<String, Object> map) {
		wareHouseDao.delPurchase(map);
	}

	@Override
	public List<CMesOutWareHouse> outWareHouseList(Map<String, Object> map) {
		return wareHouseDao.outWareHouseList(map);
	}
	@Transactional
	@Override
	public void addShiments(Map<String, Object> map) {
		wareHouseDao.addShiments(map);
	}
	@Transactional
	@Override
	public void editShipments(HashMap<String, Object> map) {
		wareHouseDao.editShipments(map);
	}
	@Transactional
	@Override
	public void delShipments(HashMap<String, Object> map) {
		wareHouseDao.delShipments(map);
	}

	@Override
	public List<CMesMaterialMessage> stockManager(Map<String, Object> map) {
		return wareHouseDao.stockManager(map);
	}
	@Transactional
	@Override
	public void addStockManager(Map<String, Object> map) {
		wareHouseDao.addStockManager(map);
	}
	@Transactional
	@Override
	public void editStockManager(Map<String, Object> map) {
		wareHouseDao.editStockManager(map);
	}
	@Transactional
	@Override
	public void delStockManager(HashMap<String, Object> map) {
		wareHouseDao.delStockManager(map);
	}

	@Override
	public List<CMesLibraryPositionT> libraryPositionList(Map<String, Object> map) {
		return wareHouseDao.libraryPositionList(map);
	}
	@Transactional
	@Override
	public void editLibraryPosition(Map<String, Object> map) {
		wareHouseDao.editLibraryPosition(map);
	}
	@Transactional
	@Override
	public void editLibraryPosition2(HashMap<String, Object> map) {
		wareHouseDao.editLibraryPosition2(map);
	}

	@Override
	public List<CMesLibraryPositionT> repositoryManagement(Map<String, Object> map) {
		return wareHouseDao.repositoryManagement(map);
	}

	@Override
	public List<CMesWareHouseT> wareHouseList(Map<String, Object> map) {
		return wareHouseDao.wareHouseList(map);
	}

	@Transactional
	@Override
	public void addRepositoryManagement(Map<String, Object> map) {
		wareHouseDao.addRepositoryManagement(map);
	}
	@Transactional
	@Override
	public void editRepositoryManagement(Map<String, Object> map) {
		wareHouseDao.editRepositoryManagement(map);
	}
	@Transactional
	@Override
	public void delRepositoryManagement(HashMap<String, Object> map) {
		wareHouseDao.delRepositoryManagement(map);
	}
}
