package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.BomDao;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;
@Service
public class BomServiceImpl implements BomService {
	@Autowired
	BomDao bomDao;

	@Override
	public List<CMesBomT> bomList(Map<String, Object> map) {
		return bomDao.bomList(map);
	}
	@Transactional
	@Override
	public void addBom(Map<String, Object> map) {
		bomDao.addBom(map);
	}
	@Transactional
	@Override
	public void delBom(Map<String, Object> map) {
		bomDao.delBom(map);
	}
	@Transactional
	@Override
	public void editBom(Map<String, Object> map) {
		bomDao.editBom(map);
	}
	@Override
	public List<CMesMaterialMsgT> materialMsgList(Map<String, Object> map) {
		return bomDao.materialMsgList(map);
	}
	@Transactional
	@Override
	public void addMaterialMsg(Map<String, Object> map) {
		bomDao.addMaterialMsg(map);
	}
	@Transactional
	@Override
	public void delMaterialMsg(Map<String, Object> map) {
		bomDao.delMaterialMsg(map);
	}
	@Transactional
	@Override
	public void editMaterialMsg(Map<String, Object> map) {
		bomDao.editMaterialMsg(map);
	}
	@Override
	public List<CMesBoltInfomationT> boltList(Map<String, Object> map) {
		return bomDao.boltList(map);
	}
	@Transactional
	@Override
	public void addBolt(Map<String, Object> map) {
		bomDao.addBolt(map);
	}
	@Transactional
	@Override
	public void delBolt(Map<String, Object> map) {
		bomDao.delBolt(map);
	}
	@Transactional
	@Override
	public void editBolt(Map<String, Object> map) {
		bomDao.editBolt(map);
	}
	@Override
	public List<CMesLeakageInfoT> leakageList(Map<String, Object> map) {
		return bomDao.leakageList(map);
	}
	@Transactional
	@Override
	public void addAirtightness(Map<String, Object> map) {
		bomDao.addAirtightness(map);
	}
	@Transactional
	@Override
	public void editAirtightness(Map<String, Object> map) {
		bomDao.editAirtightness(map);
	}
	@Transactional
	@Override
	public void delAirtightness(Map<String, Object> map) {
		bomDao.delAirtightness(map);
	}
	@Override
	public List<CMesOtherDataT> otherDataList(Map<String, Object> map) {
		return bomDao.otherDataList(map);
	}
	@Transactional
	@Override
	public void addOtherData(Map<String, Object> map) {
		bomDao.addOtherData(map);
	}
	@Transactional
	@Override
	public void delOtherData(Map<String, Object> map) {
		bomDao.delOtherData(map);
	}
	@Transactional
	@Override
	public void editOtherData(Map<String, Object> map) {
		bomDao.editOtherData(map);
	}
	@Override
	public List<CMesBomDetailT> bomDetailList(Map<String, Object> map) {
		return bomDao.bomDetailList(map);
	}
	@Transactional
	@Override
	public void addBomDetails(Map<String, Object> map) {
		bomDao.addBomDetails(map);
	}
	@Transactional
	@Override
	public void delBomDetails(Map<String, Object> map) {
		bomDao.delBomDetails(map);
	}
	@Transactional
	@Override
	public void editBomDetails(Map<String, Object> map) {
		bomDao.editBomDetails(map);
	}
	@Override
	public int countBolt(Map<String, Object> map) {
		return bomDao.countBolt(map);
	}
	@Override
	public int countBomDetailsByBomId(Map<String, Object> map) {
		return bomDao.countBomDetailsByBomId(map);
	}
	@Override
	public int countBomByName(Map<String, Object> map) {
		return bomDao.countBomByName(map);
	}
	@Override
	public int countBoltByNo(Map<String, Object> map) {
		return bomDao.countBoltByNo(map);
	}
	@Override
	public int countBoltByName(Map<String, Object> map) {
		return bomDao.countBoltByName(map);
	}
	@Override
	public int countOtherByName(Map<String, Object> map) {
		return bomDao.countOtherByName(map);
	}
	@Override
	public List<CMesLineT> findLine() {
		// TODO Auto-generated method stub
		return bomDao.findLine();
	}
	@Override
	public List<CMesStationT> findStation() {
		// TODO Auto-generated method stub
		return bomDao.findStation();
	}
}
