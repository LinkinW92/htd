package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.ReportDao;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportDao reportDao;

	@Override
	public List<PTrackingT> ptrackingList(Map<String, Object> map) {
		return reportDao.ptrackingList(map);
	}
	@Override
	public String getProductionName(int production_id) {
		return reportDao.getProductionName(production_id);
	}



	@Override
	public List<RTrackingT> rtrackingList(Map<String, Object> map) {
		return reportDao.rtrackingList(map);
	}

	@Override
	public List<PMesBoltT> boltList(Map<String, Object> map) {
		return reportDao.boltList(map);
	}

	@Override
	public List<PMesKeypartT> keypartList(Map<String, Object> map) {
		return reportDao.keypartList(map);
	}

	@Override
	public List<PMesLeakageT> leakageList(Map<String, Object> map) {
		return reportDao.leakageList(map);
	}

	@Override
	public List<CMesRecipeDatilT> recipeDatilLists(Map<String, Object> map) {
		return reportDao.recipeDatilLists(map);
	}

	@Override
	public List<CMesProductionT> getProductionVr() {
		return reportDao.getProductionVr();
	}
	@Override
	public void saveRecipeDatil(Map<String, Object> map) {
		reportDao.saveRecipeDatil(map);
	}

	@Override
	public List<CMesRecipeT> getRecipeIdByName(Map<String, Object> map) {
		return reportDao.getRecipeIdByName(map);
	}

	@Override
	public List<CMesStationT> getStationByName(Map<String, Object> map) {
		return reportDao.getStationByName(map);
	}

	@Override
	public List<CMesProductionT> getProductionByName(Map<String, Object> map) {
		return reportDao.getProductionByName(map);
	}

	@Override
	public List<CMesProductionT> getProductionByPackPn(Map<String, Object> map) {
		return reportDao.getProductionByPackPn(map);
	}

	@Override
	public int countStation(String stationName) {
		return reportDao.countStation(stationName);
	}

	@Override
	public int countPackPn(String packPn) {
		return reportDao.countPackPn(packPn);
	}

	@Override
	public int countProductionName(String productionName) {
		return reportDao.countProductionName(productionName);
	}
	@Override
	public void saveProductionRecipe(Map<String, Object> map) {
		reportDao.saveProductionRecipe(map);
	}

	@Override
	public List<CMesProductionRecipeT> productionRecipe(Map<String, Object> map) {
		return reportDao.productionRecipe(map);
	}
	@Override
	public void createRecipe(Map<String, Object> map) {
		reportDao.createRecipe(map);
	}
	@Override
	public void removeRecipeDatilByRecipeId(Map<String, Object> map) {
		reportDao.removeRecipeDatilByRecipeId(map);
	}
	@Override
	public void createProduction(Map<String, Object> map) {
		reportDao.createProduction(map);
	}

	@Override
	public List<CMesRecipeTypeT> getRecipeTypeNameByStepCategory(Map<String, Object> map) {
		return reportDao.getRecipeTypeNameByStepCategory(map);
	}

	@Override
	public List<RUploadDataT> uploadDataList(Map<String, Object> map) {
		return reportDao.uploadDataList(map);
	}

	@Override
	public List<RMesBolt> boltListR(Map<String, Object> map) {
		return reportDao.boltListR(map);
	}

	@Override
	public List<RMesKeypart> keypartListR(Map<String, Object> map) {
		return reportDao.keypartListR(map);
	}

	@Override
	public List<RMesLeakage> leakageListR(Map<String, Object> map) {
		return reportDao.leakageListR(map);
	}

	@Override
	public List<RAsmElectricDetection> rAsmElectricDetectionList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.rAsmElectricDetectionList(map);
	}

	@Override
	public List<PChargedischarge> pChargedischargeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.pChargedischargeList(map);
	}

	@Override
	public List<RAsmEol> rAsmEolList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.rAsmEolList(map);
	}

	@Override
	public List<CMesXXT> getXXT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.getXXT(map);
	}

	@Override
	public List<RMesBolt> rboltList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.rboltList(map);
	}

	@Override
	public List<RMesKeypart> rkeypartList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.rkeypartList(map);
	}

	@Override
	public List<RMesLeakage> rleakageList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDao.rleakageList(map);
	}

	@Override
	public String getSn(String materialName) {
		// TODO Auto-generated method stub
		return reportDao.getSn(materialName);
	}

	@Override
	public String getPSn(String materialName) {
		// TODO Auto-generated method stub
		return reportDao.getPSn(materialName);
	}

	@Override
	public List<PMesEolT> findAllEol(String sn) {
		// TODO Auto-generated method stub
		return reportDao.findAllEol(sn);
	}

	@Override
	public List<PMesDischargeT> findAllDischargeT(String sn) {
		// TODO Auto-generated method stub
		return reportDao.findAllDischargeT(sn);
	}

	@Override
	public List<PMesWeightT> findAllWeight(String sn) {
		return reportDao.findAllWeight(sn);
	}

	@Override
	public List<PMesStationPassT> findAllStationPass(String sn) {
		return reportDao.findAllStationPass(sn);
	}

	@Override
	public JSONObject firstSn() {
		JSONObject jo = new JSONObject();
		String sn = reportDao.firstSn();
		jo.put("data",sn);
		return jo;
	}

	//	@Override
	//	public List<CMesProductionT> findProductionIdByName(Map<String, Object> map) {
	//		return reportDao.findProductionIdByName(map);
	//	}
	//
	//	@Override
	//	public List<CMesStationT> findStationIdByName(Map<String, Object> map) {
	//		return reportDao.findStationIdByName(map);
	//	}


}
