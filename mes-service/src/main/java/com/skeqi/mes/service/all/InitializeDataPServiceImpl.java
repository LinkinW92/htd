package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.controller.all.InitializeDataPController;
import org.apache.log4j.Logger;
import org.neo4j.cypher.internal.compiler.v2_2.docgen.logicalPlanDocGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.InitializeDataPDao;
import com.skeqi.mes.pojo.api.InitializeCurrentstepPT;
import com.skeqi.mes.pojo.api.InitializeDataPT;
import com.skeqi.mes.pojo.api.InitializeReworkDataPT;
import com.skeqi.mes.service.all.InitializeDataPService;

/**
 *
 * @name
 * @author Yinp
 * @date 2020年01月10日13:58:57
 *
 */
@Service
public class InitializeDataPServiceImpl implements InitializeDataPService {

	@Autowired
	InitializeDataPDao dao;

	Logger log = Logger.getLogger(InitializeDataPServiceImpl.class);

	@Override
	public List<InitializeDataPT> find1(Integer routingId) {
		// TODO Auto-generated method stub
		return dao.find1(routingId);
	}

	@Override
	public List<InitializeDataPT> find2(String tempStationRecipeId) {
		// TODO Auto-generated method stub
		return dao.find2(tempStationRecipeId);
	}

	@Override
	public InitializeDataPT find3(String cStationsStId,Integer totalRecipeId) {
		// TODO Auto-generated method stub
		return dao.find3(cStationsStId, totalRecipeId);
	}

	@Override
	public InitializeDataPT find4(String cStationsStId) {
		// TODO Auto-generated method stub
		return dao.find4(cStationsStId);
	}

	@Override
	public void insert1(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		dao.insert1(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
	}

	@Override
	public void insert2(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		dao.insert2(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
	}

	@Override
	public void insert3(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		dao.insert3(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
	}

	@Override
	public void insert4(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		dao.insert4(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
	}

	@Override
	public void insert5(String snIni, String tempStationName, String cRecipesTLimit, String cRecipesALimit,
			String tempParamartersName, String whileTemp) {
		// TODO Auto-generated method stub
		String cRecipesMaterialName = "螺栓";
		Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId(cRecipesMaterialName);
		dao.insert5(snIni, tempStationName, cRecipesTLimit, cRecipesALimit, tempParamartersName, whileTemp, MATERIAL_INSTANCE_ID);
		//
	}

	@Override
	public Integer getMaterialInstanceIdWrapper(String str) {
		return getMaterialInstanceId(str);
	}

	@Override
	public void insertBoltData(List<InitializeDataPController.BoltInfo> data) {
		dao.batchlyInsert(data);
	}

	@Override
	public void insert6(String tempStationName, String snIni, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.insert6(tempStationName, snIni, cRecipesMaterialName);
	}

	@Override
	public void insert7(String tempStationName, String snIni) {
		// TODO Auto-generated method stub
		dao.insert7(tempStationName, snIni);
	}

	@Override
	public void insert8(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId(cRecipesMaterialName);
		dao.insert8(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn, MATERIAL_INSTANCE_ID);
	}

	@Override
	public void insert9(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn) {
		// TODO Auto-generated method stub
		Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId(cRecipesMaterialName);
		dao.insert9(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn, MATERIAL_INSTANCE_ID);
	}

	private Integer getMaterialInstanceId(String materialName) {
		Integer MATERIAL_INSTANCE_ID = dao.getUnUsedMaterial(materialName);
		if(MATERIAL_INSTANCE_ID != null && MATERIAL_INSTANCE_ID > 0) {
			dao.updateStateByID(MATERIAL_INSTANCE_ID);
		}else{
			log.error(cRecipesMaterialName+ "库存不足！");
		}
		return MATERIAL_INSTANCE_ID;
	}

	/**
	 * 临时变量
	 */
	Integer whileTemp = 0;
	Integer tempStationRecipeId = 0;
	String tempParamartersName = "";
	String tempExceptionMsg = "";
	String tempStationName = "";

	String cRecipesStepCategory = "";
	String cRecipesMaterialName = "";
	String cRecipesMaterialpn = "";
	String cRecipesNumbers = "";
	String cRecipesTLimit = "";
	String cRecipesALimit = "";

	public Integer rIni;


	//初始化步序
	//
	//
	@Override
	public InitializeCurrentstepPT find1s(String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		return dao.find1s(serialnumber, station, line);
	}

	@Override
	public void insert1s(String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		dao.insert1s(serialnumber, station, line);
	}

	//
	//
	//
	//
	//初始化返修站配方
	@Override
	public List<InitializeReworkDataPT> finds1(String snRework) {
		// TODO Auto-generated method stub
		return dao.finds1(snRework);
	}

	@Override
	public List<InitializeReworkDataPT> finds2(String tempStationRecipeId) {
		// TODO Auto-generated method stub
		return dao.finds2(tempStationRecipeId);
	}

	@Override
	public InitializeReworkDataPT finds3(String cStationsStName, String productionId) {
		// TODO Auto-generated method stub
		return dao.finds3(cStationsStName, productionId);
	}

	@Override
	public void inserts1(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts1(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public void inserts2(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts2(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public void inserts3(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts3(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public void inserts4(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts4(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public void inserts5(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts5(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public void inserts6(String snRework, String cStationsStName, String cRecipesTLimit, String cRecipesALimit,
			String tempParamartersName) {
		// TODO Auto-generated method stub
		dao.inserts6(snRework, cStationsStName, cRecipesTLimit, cRecipesALimit, tempParamartersName);
	}

	@Override
	public void inserts7(String cStationsStName, String snRework, String cRecipesMaterialName) {
		// TODO Auto-generated method stub
		dao.inserts7(cStationsStName, snRework, cRecipesMaterialName);
	}

	@Override
	public Integer findKeypart(String staName, String sn) {
		// TODO Auto-generated method stub
		return dao.findKeypart(staName, sn);
	}

	@Override
	public Integer findBolt(String staName, String sn) {
		// TODO Auto-generated method stub
		return dao.findBolt(staName, sn);
	}

	@Override
	public Integer findWeight(String staName, String sn) {
		// TODO Auto-generated method stub
		return dao.findWeight(staName, sn);
	}

	@Override
	public Integer findleakage(String staName, String sn) {
		// TODO Auto-generated method stub
		return dao.findleakage(staName, sn);
	}

	@Override
	public void updateRKeypart(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.updateRKeypart(sn, stationName);
	}

	@Override
	public void updateRBolt(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.updateRBolt(sn, stationName);
	}

	@Override
	public void updateRLeakage(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.updateRLeakage(sn, stationName);
	}

	@Override
	public void updateRBoltNg(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.updateRBoltNg(sn, stationName);
	}

	@Override
	public void deleteRKeypart(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.deleteRKeypart(sn, stationName);
	}

	@Override
	public void deleteRBolt(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.deleteRBolt(sn, stationName);
	}

	@Override
	public void deleteRLeakage(String sn, String stationName) {
		// TODO Auto-generated method stub
		dao.deleteRLeakage(sn, stationName);
	}

}
