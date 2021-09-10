package com.skeqi.mes.service.all;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.all.InitializeDataPController;
import com.skeqi.mes.pojo.api.InitializeCurrentstepPT;
import com.skeqi.mes.pojo.api.InitializeDataPT;
import com.skeqi.mes.pojo.api.InitializeReworkDataPT;

/**
 *
 * @name
 * @author Yinp
 * @date 2020年01月10日13:58:57
 *
 */
public interface InitializeDataPService {

	public List<InitializeDataPT> find1(Integer routingId);

	public List<InitializeDataPT> find2(String tempStationRecipeId);

	public InitializeDataPT find3(String cStationsStId, Integer totalRecipeId);

	public InitializeDataPT find4(String cStationsStId);

	public void insert1(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public void insert2(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public void insert3(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public void insert4(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public void insert5(String snIni, String tempStationName, String cRecipesTLimit, String cRecipesALimit,
			String tempParamartersName, String whileTemp);

	public void insertBoltData(List<InitializeDataPController.BoltInfo> data);

	public Integer getMaterialInstanceIdWrapper(String str);

	public void insert6(String tempStationName, String snIni, String cRecipesMaterialName);

	public void insert7(String tempStationName, String snIni);

	public void insert8(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public void insert9(String tempStationName, String snIni, String cRecipesMaterialName, String cRecipesMaterialpn);

	public Integer findKeypart(String staName,String sn);

	public Integer findBolt(String staName,String sn);

	public Integer findWeight(String staName,String sn);

	public Integer findleakage(String staName,String sn);

//	public void main(JSONObject json);

	//初始化步序
	//
	//
	//

	public InitializeCurrentstepPT find1s(String serialnumber, String station, String line);

	public void insert1s(String serialnumber, String station, String line);

	//
	//
	//
	//初始化返修站配方
	public List<InitializeReworkDataPT> finds1(String snRework);

	public List<InitializeReworkDataPT> finds2(String tempStationRecipeId);

	public InitializeReworkDataPT finds3(String cStationsStName, String productionId);

	public void inserts1(String cStationsStName, String snRework, String cRecipesMaterialName);

	public void inserts2(String cStationsStName, String snRework, String cRecipesMaterialName);

	public void inserts3(String cStationsStName, String snRework, String cRecipesMaterialName);

	public void inserts4(String cStationsStName, String snRework, String cRecipesMaterialName);

	public void inserts5(String cStationsStName, String snRework, String cRecipesMaterialName);

	public void inserts6(String snRework, String cStationsStName, String cRecipesTLimit, String cRecipesALimit,
			String tempParamartersName);

	public void inserts7(String cStationsStName, String snRework, String cRecipesMaterialName);


	//修改keypart表数据
	public void updateRKeypart(String sn,String stationName);

	//修改bolt表数据
	public void updateRBolt(String sn,String stationName);

	//删除NG的拧紧数据
	public void updateRBoltNg(String sn,String stationName);

	//修改气密数据
	public void updateRLeakage(String sn,String stationName);


	//修改keypart表数据
	public void deleteRKeypart(String sn,String stationName);

	//修改bolt表数据
	public void deleteRBolt(String sn,String stationName);


	//修改气密数据
	public void deleteRLeakage(String sn,String stationName);



}
