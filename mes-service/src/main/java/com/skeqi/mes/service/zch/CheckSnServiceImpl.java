package com.skeqi.mes.service.zch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.AssembleBoltDao;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.mapper.zch.WorkorderDao;

@Service
public class CheckSnServiceImpl implements CheckSnService {
	Logger log = Logger.getLogger(CheckSnServiceImpl.class);

	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	WorkorderDao workorderDao;
	@Autowired
	AssembleBoltDao assembleBoltDao;
	@Autowired
	HookService hookService;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public JSONObject checkSN(String snBarcode, String station, String line, Boolean getStationRecipe) {

		Object productionId = null;
		Object totalRecipeId = null;
		Object routingId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Object stationIndex = null;
		Object num = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson("101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson("101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");
		stationIndex = stationMap.get("STATION_INDEX");

		// Hook
		Map<String, Object> checkMap = new HashMap<>();
		checkMap.put("stationId", stationId);
		checkMap.put("sn", snBarcode);
		checkMap.put("inOut", 1);
		try {
			if(!hookService.checkJob(checkMap)) {
				return errJson("151", "关联校验未通过");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取打印记录
		Map<String, Object> printMap = checkSnDao.getPrintBySN(snBarcode,lineId);

		// 在线标记 1 在线 0 离线
		int onlineFlag;

		// 判断是否存在打印记录
		if(printMap == null){
			// 不存在，离线生产
			onlineFlag = 0;
			List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
			if(productionList.size() == 0){
				return errJson("102", "系统中未配置离线产品");
			}
			// 校验条码
			Pattern pattern = null;
			Matcher matcher = null;
			for (Map<String, Object> productionMap : productionList) {
				pattern = Pattern.compile(productionMap.get("PRODUCTION_VR").toString());
				matcher = pattern.matcher(snBarcode);
				if(matcher.find()) {
					productionId = productionMap.get("ID");
					break;
				}
			}
			// 未找到匹配条码的产品
			if(productionId == null){
				return errJson("103", "未找到匹配条码的产品");
			}
			// 获取默认工艺路线、配方
			Map<String, Object> routingMap = checkSnDao.getDefaultRouting(productionId, lineId);
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if(routingMap == null || totalRecipeMap == null) {
				return errJson("104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
			routingId = routingMap.get("ID");

		} else {
			//存在，在线生产

			// 判断是否产线传入有误
			if(!lineId.toString().equals(printMap.get("LINE_ID").toString())) {
				return errJson("120", "传入条码与产线不匹配");
			}

			onlineFlag = 1;
			workorderId = printMap.get("WORK_ORDER_ID");
			// 数量
			num = printMap.get("quantity");
			// 获取工单信息
			Map<String, Object> workorderMap = checkSnDao.getWorkorderById(workorderId);
			if(workorderMap == null) {
				return errJson("105", "工单未开始或状态异常");
			}
			// 获取总配方id和工艺路线
			totalRecipeId = workorderMap.get("TOTAL_RECIPE_ID");
			routingId = workorderMap.get("ROUTING_ID");
			productionId = workorderMap.get("PRODUCT_ID");
		}

		// 获取工艺路线工位最小序号
		Integer serialMin = checkSnDao.getSerialMin(routingId);
		// 当前工位所在序号
		Integer serialNo = checkSnDao.getSerialNoByStation(routingId, stationId);

		if(serialMin == null) {
			return errJson("106", "工艺路线不存在");
		}
		if(serialNo==null){
			return errJson("128", "传入工位不在工艺路线内");
		}

		// 获取该条码上线下线表数量
		Integer trackingCountP = checkSnDao.getTrackingCountP(snBarcode, lineId);
		Integer trackingCountR = checkSnDao.getTrackingCountR(snBarcode, lineId);

		if(trackingCountP > 0) {
			return errJson("107", "此产品已正常生产且下线");
		}

		if(serialNo == serialMin) {
			// 首站
			if(trackingCountR > 0) {
				// 上线表存在该总成
				// 查询过程表中首站是否下线
				Integer flagCount = checkSnDao.getFlagCount(snBarcode, serialMin);
				if(flagCount > 0) {
					return errJson("108", "该总成在此工位已下线");
				}
			}else {
				// 插入过程表
				Integer serialFlagCount = checkSnDao.getSerialFlagCount(snBarcode);
				if(serialFlagCount == 0) {
					List<Map<String, Object>> stationWayList = checkSnDao.queryStationWayList(snBarcode, routingId);
					checkSnDao.insertStationDerialFlag(stationWayList);
				}
				// 插入上线表
				checkSnDao.insertTracking(station, snBarcode, stationIndex, productionId, lineId, workorderId, totalRecipeId);
				// 插入过站信息表
				checkSnDao.insertStationPass(snBarcode, station, stationId);
				// 在线生产 更新工单上线数量
				if(onlineFlag == 1){
					checkSnDao.updateOnlineNumber(workorderId);
				}
			}
			// 初始化该产线上自动站数据
			List<String> offlineStationNames = checkSnDao.findOfflineStationName(routingId);
			for (String offlineStationName : offlineStationNames) {
				Integer findBolt = checkSnDao.findBoltCount(offlineStationName, snBarcode);
				List<Map<String, Object>> recipeList = checkSnDao.findRecipeDetails(totalRecipeId, stationId);
				for (Map<String, Object> recipeDetail : recipeList) {
					Object cRecipesStepCategory = recipeDetail.get("STEP_CATEGORY");
					Object cRecipesMaterialName = recipeDetail.get("MATERIAL_NAME");
					Object cRecipesNumbers = recipeDetail.get("NUMBERS");
					Object cRecipesTLimit = recipeDetail.get("T_LIMIT");
					Object cRecipesALimit = recipeDetail.get("A_LIMIT");
					String step = recipeDetail.get("STEPNO").toString();

					if("7".equals(cRecipesStepCategory) && findBolt <= 0) {
						// 拧紧 ( 自动站仅初始化螺栓 )
						Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId("螺栓");
						List<BoltInfo> boltInfos = new ArrayList<>();
						Integer whileTemp = 1;
						while (whileTemp <= Integer.parseInt(cRecipesNumbers.toString())) {
							String tempParamartersName = cRecipesMaterialName + "_" + whileTemp;
							BoltInfo boltInfo = new BoltInfo(snBarcode, station, cRecipesTLimit.toString(), cRecipesALimit.toString(),
									tempParamartersName, whileTemp.toString(),step, MATERIAL_INSTANCE_ID);
							boltInfos.add(boltInfo);
							whileTemp++;
						}
						checkSnDao.insertBoltData(boltInfos);
						continue;
					}
				}
			}

		}else {
			// 非首站
			if(trackingCountR == 0){
				return errJson("109", "该总成未上线");
			}
			// 获取前序号工位是否完成
			Integer serialTotalCount = checkSnDao.getSerialTotalCount(snBarcode, serialNo);
			Integer serialCompleteCount = checkSnDao.getSerialCompleteCount(snBarcode, serialNo);

			if(serialTotalCount != 0 && serialTotalCount != serialCompleteCount) {
				return errJson("110", "前工位未完成");
			}
			// 插入过站信息表
			checkSnDao.insertStationPass(snBarcode, station, stationId);
		}
		// 初始化当前工位数据
		// 获取工位配方明细列表
		List<Map<String, Object>> recipeList = checkSnDao.findRecipeDetails(totalRecipeId, stationId);
		// 获取物料、螺栓、称重、气密表数据数量
		Integer findKeypart = checkSnDao.findKeypartCount(station, snBarcode);
		Integer findBolt = checkSnDao.findBoltCount(station, snBarcode);
		Integer findWeight = checkSnDao.findWeightCount(station, snBarcode);
		Integer findleakage = checkSnDao.findleakageCount(station, snBarcode);
		for (Map<String, Object> recipeDetail : recipeList) {
			String cRecipesStepCategory = recipeDetail.get("STEP_CATEGORY").toString();
			Object cRecipesMaterialName = recipeDetail.get("MATERIAL_NAME");
			Object cRecipesMaterialpn = recipeDetail.get("MATERIALPN");
			Object cRecipesNumbers = recipeDetail.get("NUMBERS");
			Object cRecipesTLimit = recipeDetail.get("T_LIMIT");
			Object cRecipesALimit = recipeDetail.get("A_LIMIT");
			String step = recipeDetail.get("STEPNO").toString();

			if("7".equals(cRecipesStepCategory) && findBolt <= 0) { // 拧紧
				Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId("螺栓");
				List<BoltInfo> boltInfos = new ArrayList<>();
				Integer whileTemp = 1;
				while (whileTemp <= Integer.parseInt(cRecipesNumbers.toString())) {
					String tempParamartersName = cRecipesMaterialName + "_" + whileTemp;
					BoltInfo boltInfo = new BoltInfo(snBarcode, station, cRecipesTLimit.toString(), cRecipesALimit.toString(),
							tempParamartersName, whileTemp.toString(),step, MATERIAL_INSTANCE_ID);
					boltInfos.add(boltInfo);
					whileTemp++;
				}
				checkSnDao.insertBoltData(boltInfos);
				continue;
			}
			if ("3".equals(cRecipesStepCategory) && findKeypart<=0) {  //扫描物料
				checkSnDao.insertMaterial(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn,step);
				continue;
			}
			if ("4".equals(cRecipesStepCategory) && findKeypart<=0) {  //扫描物料(唯一编码)
				checkSnDao.insertMaterial(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn,step);
				continue;
			}
			if ("12".equals(cRecipesStepCategory) && findKeypart<=0) {  //用户录入
				checkSnDao.insertUserInput(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn);
				continue;
			}
			if ("8".equals(cRecipesStepCategory) && findleakage<=0) {  //气密
				checkSnDao.insertLeakage(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn,step);
				continue;
			}
			if ("15".equals(cRecipesStepCategory) && findWeight<=0) {  //称重
				checkSnDao.insertWeight(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn);
				continue;
			}
			if ("16".equals(cRecipesStepCategory) && findKeypart<=0) {  //扫描二级总成
				Integer MATERIAL_INSTANCE_ID = getMaterialInstanceId(cRecipesMaterialName.toString());
				checkSnDao.insertSecondary(station, snBarcode, cRecipesMaterialName, cRecipesMaterialpn, MATERIAL_INSTANCE_ID);
				continue;
			}
		}

		// 添加步序缓存表
		Map<String, Object> mapStep =  checkSnDao.getStep(snBarcode, line, station);
		if(mapStep == null) {
			checkSnDao.insertStep(snBarcode, line, station);
		}

		// 返回配方步序
		List<Map<String, Object>> stepList = new ArrayList<>();
		if(getStationRecipe) {
			if (onlineFlag == 0) { // 离线
				stepList = checkSnDao.findStepListOffline(snBarcode, station, productionId);
			} else { // 在线
				stepList = checkSnDao.findStepListOnline(snBarcode, station, workorderId);
			}
		}

		JSONObject jo = new JSONObject();
		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		jo.put("productionId", productionId);
		jo.put("stepList", stepList);
		return jo;
	}

	@Override
	public JSONObject scanEmp(String emp, String lineName, String stationName, String sn, Integer stepNo) {
		JSONObject jo = new JSONObject();

		Object productionId = null;
		Object totalRecipeId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Map<String, Object> workorderMap = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(lineName);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson("101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(stationName, lineId);
		if(stationMap == null) {
			return errJson("101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");

		// 获取运行表
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(sn);

		if(trackingMap == null){
			return errJson("109", "该总成未上线");
		}

        // 判断产线工位是否与条码匹配
        if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
            return errJson("120", "传入条码与产线不匹配");
        }

		workorderId = trackingMap.get("WORK_ORDER_ID");

		if (workorderId == null) {
			// 不存在，离线生产
			List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
			if (productionList.size() == 0) {
				return errJson("102", "系统中未配置离线产品");
			}
			// 校验条码
			Pattern pattern = null;
			Matcher matcher = null;
			for (Map<String, Object> productionMap : productionList) {
				pattern = Pattern.compile(productionMap.get("PRODUCTION_VR").toString());
				matcher = pattern.matcher(sn);
				if (matcher.find()) {
					productionId = productionMap.get("ID");
					break;
				}
			}
			// 未找到匹配条码的产品
			if (productionId == null) {
				return errJson("103", "未找到匹配条码的产品");
			}
			// 获取默认配方
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if (totalRecipeMap == null) {
				return errJson("104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
		} else {
			// 存在，在线生产
			// 获取工单信息
			workorderMap = checkSnDao.getWorkorderById(workorderId);
			if (workorderMap == null) {
				return errJson("105", "工单未开始或状态异常");
			}
			// 获取总配方id和工艺路线
			totalRecipeId = workorderMap.get("TOTAL_RECIPE_ID");
		}

		// 获取工位配方明细
		Map<String, Object> recipeDetailMap = assembleBoltDao.getRecipeDetail(totalRecipeId, stationId, stepNo, 2);

		if(recipeDetailMap == null) {
			return errJson("111", "当前步序未查到配方明细");
		}

		checkSnDao.updateStepEmp(sn, lineName, stationName, stepNo, emp);

		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		return jo;
	}

	/**
	 * 返回错误信息json
	 * @param code
	 * @param errMsg
	 * @return
	 */
	private JSONObject errJson(String code, String errMsg) {
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		jo.put("productionId", "");
		jo.put("stepList", "");
		return jo;
	}

	public static class BoltInfo {
		public String snIni, tempStationName, cRecipesTLimit, cRecipesALimit, tempParamartersName, whileTemp,step;
		public Integer MATERIAL_INSTANCE_ID;
		public BoltInfo(String snIni, String tempStationName, String cRecipesTLimit, String cRecipesALimit,
				String tempParamartersName, String whileTemp, String step, Integer mATERIAL_INSTANCE_ID) {
			super();
			this.snIni = snIni;
			this.tempStationName = tempStationName;
			this.cRecipesTLimit = cRecipesTLimit;
			this.cRecipesALimit = cRecipesALimit;
			this.tempParamartersName = tempParamartersName;
			this.whileTemp = whileTemp;
			this.step = step;
			MATERIAL_INSTANCE_ID = mATERIAL_INSTANCE_ID;
		}

	}

	private Integer getMaterialInstanceId(String materialName) {
		Integer MATERIAL_INSTANCE_ID = checkSnDao.getUnUsedMaterial(materialName);
		if(MATERIAL_INSTANCE_ID != null && MATERIAL_INSTANCE_ID > 0) {
			checkSnDao.updateStateByID(MATERIAL_INSTANCE_ID);
		}else{
			System.out.println(materialName+ "库存不足！");
		}
		return MATERIAL_INSTANCE_ID;
	}

	@Override
	public void test() {
//		List<MesBoltT> list = updateSnDao.findBoltList("1");
//		updateSnDao.insertBoltBatch(list);
//		workorderDao.insertBolt(176);
	}

}
