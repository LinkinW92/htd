package com.skeqi.mes.service.zch;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.parboiled.parserunners.ReportingParseRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.ModuleApiDao;
import com.skeqi.mes.mapper.zch.NextBarcodeDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.pojo.zch.MesBoltT;
import com.skeqi.mes.pojo.zch.MesKeypartT;
import com.skeqi.mes.pojo.zch.MesLeakageT;
import com.skeqi.mes.pojo.zch.MesPrintT;
import com.skeqi.mes.service.zch.CheckSnServiceImpl.BoltInfo;

@Service
public class ModuleApiServiceImpl implements ModuleApiService {
	@Autowired
	ModuleApiDao dao;
	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	NextBarcodeDao nextBarcodeDao;

	private JSONObject successJson(){
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", true);
		jo.put("code", "0");
		jo.put("errMsg", "成功");
		return jo;
	}

	@Override
	public JSONObject writeLog(Map<String, Object> map) {
		Integer ID = dao.writeLog(map);
		System.out.println("ID: " + ID);
		return successJson();
	}

	@Override
	public JSONObject findLog(Map<String, Object> map) {
		List<Map<String, Object>> list = dao.findLog(map);

		JSONObject jo = new JSONObject();
		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		jo.put("logList", list);

		return jo;
	}

	@Override
	public JSONObject dataCollect(Map<String, Object> map) {
		JSONObject jo = new JSONObject();
		Object lineId = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(map.get("lineName").toString());
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(map.get("stationName").toString(), lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入工位不存在");
		}
		Map<String, Object> productionMap = dao.getProductionByName(map);
		if(productionMap == null) {
			return errJson(jo, "301", "传入产品不存在");
		}

		List<Map<String, Object>> confList = dao.findConfList(map);

		int flag = 1;
		String defectPara = "";
		for (Map<String, Object> confMap : confList) {
			String keyName = confMap.get("keyName").toString();
			Object upperLimit = confMap.get("upperLimit"); // 上限值
			Object lowerLimit = confMap.get("lowerLimit"); // 下限值
			Object defaultValue = confMap.get("defaultValue"); // 默认值

			if(!StringUtils.isEmpty(defaultValue)) {
				confMap.put("value", defaultValue);
				continue;
			}

			if(map.get(keyName) == null || "".equals(map.get(keyName).toString())) {
				flag = 0;
				defectPara = defectPara + keyName + ",";
			} else {
				String value = map.get(keyName).toString();
				confMap.put("value", value);

				BigDecimal v = new BigDecimal(value);
				// 判断上下限
				if(!StringUtils.isEmpty(upperLimit)) {
					BigDecimal upper = new BigDecimal(upperLimit.toString());
					if(v.compareTo(upper) > 0) {
						return errJson(jo, "307", keyName + " 传入参数超出上下限");
					}
				}
				if(!StringUtils.isEmpty(lowerLimit)) {
					BigDecimal lower = new BigDecimal(lowerLimit.toString());
					if(v.compareTo(lower) < 0) {
						return errJson(jo, "307", keyName + " 传入参数超出上下限");
					}
				}
			}
		}
		if(flag == 0) {
			jo.put("isSuccess", false);
			jo.put("code", "302");
			jo.put("errMsg", "以下收束参数缺失：" + defectPara.substring(0, defectPara.length() - 1));
			return jo;
		}

		if(confList.size() > 0) {// 收束数据列表有内容
			dao.updateDataCollectValid(map);

			Integer ID = dao.insertDataCollect(map);

			for (Map<String, Object> confMap : confList) {
				confMap.put("collectId", ID);
			}
			dao.insertDataCollectDetailBatch(confList);
		}

		return successJson();
	}

	@Override
	public JSONObject inStation(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();
		String snBarcode = map.get("sn").toString();

		Object productionId = null;
		Object totalRecipeId = null;
		Object routingId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Object stationIndex = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");
		stationIndex = stationMap.get("STATION_INDEX");

		// 获取打印记录
		Map<String, Object> printMap = checkSnDao.getPrintBySN(snBarcode, lineId);

		// 在线标记 1 在线 0 离线
		int onlineFlag;

		// 判断是否存在打印记录
		if(printMap == null){
			// 不存在，离线生产
			onlineFlag = 0;
			List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
			if(productionList.size() == 0){
				return errJson(jo, "102", "系统中未配置离线产品");
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
				return errJson(jo, "103", "未找到匹配条码的产品");
			}
			// 获取默认工艺路线、配方
			Map<String, Object> routingMap = checkSnDao.getDefaultRouting(productionId, lineId);
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if(routingMap == null || totalRecipeMap == null) {
				return errJson(jo, "104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
			routingId = routingMap.get("ID");

		} else {
			//存在，在线生产

			// 判断是否产线传入有误
			if(!lineId.toString().equals(printMap.get("LINE_ID").toString())) {
				return errJson(jo, "120", "传入条码与产线不匹配");
			}

			onlineFlag = 1;
			workorderId = printMap.get("WORK_ORDER_ID");
			// 获取工单信息
			Map<String, Object> workorderMap = checkSnDao.getWorkorderById(workorderId);
			if(workorderMap == null) {
				return errJson(jo, "105", "工单未开始或状态异常");
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
			return errJson(jo, "106", "工艺路线不存在");
		}
		if(serialNo==null){
			return errJson(jo, "128", "传入工位不在工艺路线内");
		}

		// 获取该条码上线下线表数量
		Integer trackingCountP = checkSnDao.getTrackingCountP(snBarcode, lineId);
		Integer trackingCountR = checkSnDao.getTrackingCountR(snBarcode, lineId);

		if(trackingCountP > 0) {
			return errJson(jo, "107", "此产品已正常生产且下线");
		}

		if(serialNo == serialMin) {
			// 首站
			if(trackingCountR > 0) {
				// 上线表存在该总成
				// 查询过程表中首站是否下线
				Integer flagCount = checkSnDao.getFlagCount(snBarcode, serialMin);
				if(flagCount > 0) {
					return errJson(jo, "108", "该总成在此工位已下线");
				}
			}else {
				// 插入过程表
				Integer serialFlagCount = checkSnDao.getSerialFlagCount(snBarcode);
				if(serialFlagCount == 0) {
					List<Map<String, Object>> stationWayList = checkSnDao.queryStationWayList(snBarcode, routingId);
					checkSnDao.insertStationDerialFlag(stationWayList);
				}
				// 插入上线表
				checkSnDao.insertTracking(station, snBarcode, stationIndex, productionId, lineId, workorderId,totalRecipeId);
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
				return errJson(jo, "109", "该总成未上线");
			}
			// 获取前序号工位是否完成
			Integer serialTotalCount = checkSnDao.getSerialTotalCount(snBarcode, serialNo);
			Integer serialCompleteCount = checkSnDao.getSerialCompleteCount(snBarcode, serialNo);

			if(serialTotalCount != 0 && serialTotalCount != serialCompleteCount) {
				return errJson(jo, "110", "前工位未完成");
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
//		List<Map<String, Object>> stepList = new ArrayList<>();
//		if(getStationRecipe) {
//			if (onlineFlag == 0) { // 离线
//				stepList = checkSnDao.findStepListOffline(snBarcode, station, productionId);
//			} else { // 在线
//				stepList = checkSnDao.findStepListOnline(snBarcode, station, workorderId);
//			}
//		}

		return successJson();
	}

	@Override
	public JSONObject outStation(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String lineName = map.get("lineName").toString();
		String stationName = map.get("stationName").toString();
		String snBarcode = map.get("sn").toString();

		Object productionId = null;
		Object routingId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
//		Object stationIndex = null;
		Object trackingStatus = null;
		Integer ORDER_NUMBER = null;
		Integer OFFLINE_NUMBER = null;
		Map<String, Object> workorderMap = null;
		Object WORKORDER_ID = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(lineName);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(stationName, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");
//		stationIndex = stationMap.get("STATION_INDEX");

		// 获取下线表
		Integer trackingCountP = checkSnDao.getTrackingCountP(snBarcode, lineId);
		if(trackingCountP > 0) {
			return errJson(jo, "107", "此产品已正常生产且下线");
		}

		// 获取上线表
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(snBarcode);

		if(trackingMap == null){
			return errJson(jo, "109", "该总成未上线");
		}
		// 判断产线工位是否与条码匹配
		if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
			return errJson(jo, "120", "传入条码与产线不匹配");
		}

		workorderId = trackingMap.get("WORK_ORDER_ID");
		trackingStatus = trackingMap.get("STATUS");

		// 运行表状态是否ok
		if("OK".equals(trackingStatus)) {
			// 在线标记 1 在线 0 离线
			int onlineFlag;

			// 判断上线表里工单id是否存在
			if(workorderId == null) {
				// 不存在，离线生产
				onlineFlag = 0;
				List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
				if(productionList.size() == 0){
					return errJson(jo, "102", "系统中未配置离线产品");
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
					return errJson(jo, "103", "未找到匹配条码的产品");
				}
				// 获取默认工艺路线
				Map<String, Object> routingMap = checkSnDao.getDefaultRouting(productionId, lineId);

				// 判断是否配置默认工艺路线
				if(routingMap == null) {
					return errJson(jo, "104", "此产品产线未配置默认工艺路线或配方");
				}
				// 工艺路线id
				routingId = routingMap.get("ID");
			}else {
				//存在，在线生产
				onlineFlag = 1;
				// 获取工单信息
				workorderMap = checkSnDao.getWorkorderById(workorderId);
				if(workorderMap == null) {
					return errJson(jo, "105", "工单未开始或状态异常");
				}
				//获取产品id
				productionId = workorderMap.get("PRODUCT_ID");
				// 工单编号
				WORKORDER_ID = workorderMap.get("WORKORDER_ID");
				// 获取总配方id和工艺路线
				routingId = workorderMap.get("ROUTING_ID");
				ORDER_NUMBER = Integer.parseInt(workorderMap.get("ORDER_NUMBER").toString());
				OFFLINE_NUMBER = Integer.parseInt(workorderMap.get("OFFLINE_NUMBER").toString());
			}

			// 获取工艺路线工位最大序号
			Integer serialMax = updateSnDao.getSerialMax(routingId);
			// 当前工位所在序号
			Integer serialNo = checkSnDao.getSerialNoByStation(routingId, stationId);

			if(serialMax == null || serialNo == null) {
				return errJson(jo, "106", "工艺路线配置错误");
			}

			// 更新运行时表
//			updateSnDao.updateTrackingStation(stationName, stationIndex, snBarcode);
			// 更新过站信息
			updateSnDao.insertStationPass(snBarcode, stationName, lineId);
			// 删除步序缓存
			updateSnDao.deleteStep(stationName, snBarcode, lineName);
			// 更新过程表
			updateSnDao.updateSerialFlag(snBarcode, stationId);

			if(serialMax == serialNo) {
				// 末站

				// 转移螺栓、物料、气密等表数据到永久表
				// 查询
				List<MesBoltT> boltList = updateSnDao.findBoltList(snBarcode);
				List<MesKeypartT> keypartList = updateSnDao.findKeypartList(snBarcode);
				List<MesLeakageT> leakageList = updateSnDao.findLeakageList(snBarcode);

				// 插入P表 删除R表
				if(boltList.size() > 0){
					updateSnDao.insertBoltBatch(boltList);
					updateSnDao.deleteBolt(snBarcode);
				}
				if(keypartList.size() > 0){
					updateSnDao.insertKeypartBatch(keypartList);
					updateSnDao.deleteKeypart(snBarcode);
				}
				if(leakageList.size() > 0){
					updateSnDao.insertLeakageBatch(leakageList);
					updateSnDao.deleteLeakage(snBarcode);
				}

				if(onlineFlag == 1) {
					// 在线生产判断是否工单完成
					updateSnDao.updateOfflineNumber(workorderId);
					if(ORDER_NUMBER == OFFLINE_NUMBER + 1) {
						// 工单完成
						// 获取打印表临时表数据
						List<MesPrintT> printList = updateSnDao.findPrintList(workorderId);
						// 转移数据并删除临时表数据
						if(printList.size() > 0) {
							updateSnDao.insertPrintBatch(printList);
							updateSnDao.deletePrint(workorderId);
						}
						workorderMap = checkSnDao.getWorkorderById(workorderId);
						updateSnDao.insertWorkorder(workorderMap);
						updateSnDao.deleteWorkorder(workorderId);
					}
				}

				// 转移运行表
				updateSnDao.insertTracking(trackingMap);
				updateSnDao.deleteTracking(snBarcode);
				// 转移过程记录表
				List<Map<String, Object>> serialFlagList = updateSnDao.findSerialFlagList(snBarcode);
				if(serialFlagList.size() > 0) {
					updateSnDao.insertSerialFlagBatch(serialFlagList);
					updateSnDao.deleteSerialFlag(snBarcode);
				}

				// 产品
				Map<String, Object> productMap = updateSnDao.getProductByID(productionId);
				// 添加物料实例表
				updateSnDao.insertMaterialInstance(snBarcode, productMap.get("MATERIAL_CODE"), productMap.get("PRODUCTION_NAME"), WORKORDER_ID);
			}
		}else {
			return errJson(jo, "111", "此条码已NG");
		}

		return successJson();
	}

	@Override
	public JSONObject getProductionInformation(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();
//		String snBarcode = map.get("sn").toString();

		Object lineId = null;
//		Object workorderId = null;
		Map<String, Object> workorderMap = null;

		jo.put("orderNum", "");
		jo.put("productType", "");
		jo.put("lineName", "");
		jo.put("stationName", "");
		jo.put("productionNum", "");
		jo.put("passRate", "");
		jo.put("ngNum", "");
		jo.put("workorderCode", "");

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}

		// 查询高优先级开始状态工单
		workorderMap = nextBarcodeDao.getHighPriorityWorkorderByLineId(lineId);
		if(workorderMap == null) {
			return errJson(jo, "122", "当前没有开始的工单");
		}

		Map<String, Object> productMap = dao.getProduct(workorderMap);

		Integer okNum = Integer.parseInt(workorderMap.get("OK_NUMBER").toString());
		Integer productionNum = Integer.parseInt(workorderMap.get("COMPLETE_NUMBER").toString());
		Integer ngNum = dao.getNgNum(map);
		String passRate = productionNum == 0 ? "100%" : (float) okNum / (float) productionNum * 100 + "%";

		jo.put("orderNum", workorderMap.get("ORDER_NUMBER")); // 计划生产数量
		jo.put("productType", productMap.get("PRODUCTION_TYPE")); // 产品型号
		jo.put("lineName", line); // 产线
		jo.put("stationName", station); // 工位
		jo.put("productionNum", productionNum); // 实际生产数量
		jo.put("passRate", passRate); // 合格率
		jo.put("ngNum", ngNum); // 工位NG数量
		jo.put("workorderCode", workorderMap.get("WORKORDER_ID")); // 工单编号

		jo.put("isSuccess", true);
		jo.put("code", "0");
		jo.put("errMsg", "成功");
		return jo;
	}

	@Override
	public JSONObject login(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();
		String password = map.get("password").toString();
		map.put("password", password.toLowerCase());

		Object lineId = null;
		Object stationId = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");

		Map<String, Object> empMap = dao.getEmp(map);
		if(empMap == null) {
			return errJson(jo, "303", "用户或密码输入有误");
		}
		Integer IS_WHOLE = Integer.parseInt(empMap.get("IS_WHOLE").toString());
		if(IS_WHOLE == 1){ // 全班员工直接登录
			return successJson();
		}
		String lineIdStr = "," + empMap.get("LINE_ID").toString() + ",";
		String stationIdStr = "," + empMap.get("STATION_ID").toString() + ",";
		if(!lineIdStr.contains("," + lineId.toString() + ",")) {
			return errJson(jo, "304", "员工号没有该产线（ " + line + " ）权限");
		}
		if(!stationIdStr.contains("," + stationId.toString() + ",")) {
			return errJson(jo, "305", "员工号没有该工位（ " + station + " ）权限");
		}

		return successJson();
	}

	@Override
	public JSONObject deviceState(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();

		Object lineId = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}

		// 获取该工位最后设备状态
		Map<String, Object> stateMap = dao.getLastDeviceState(map);
		Integer originalState = null;
		if(stateMap != null) {
			originalState = Integer.parseInt(stateMap.get("presentState").toString());
		}
		Integer presentState = Integer.parseInt(map.get("state").toString());
		if(originalState != null && presentState == originalState){
			return errJson(jo, "306", "与设备原状态相同");
		}
		map.put("originalState", originalState);
		dao.insertDeviceState(map);

		return successJson();
	}

	@Override
	public JSONObject findStationMaterial(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();

		Object lineId = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}

		List<Map<String, Object>> materialList = dao.findMaterialList(map);

		jo = successJson();
		jo.put("materialList", materialList);

		return jo;
	}

	@Override
	public JSONObject unbundStationMaterial(Map<String, Object> map) {
		JSONObject jo = new JSONObject();

		jo = successJson();
		return jo;
	}

	@Override
	public JSONObject findDataCollectPara(Map<String, Object> map) {
		JSONObject jo = successJson();

		String line = map.get("lineName").toString();
		String station = map.get("stationName").toString();

		Object lineId = null;

		// 获取工位、产线信息
		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
		// 判断产线工位是否存在
		if(lineMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}

		// 查询高优先级开始状态工单
		Map<String, Object> workorderMap = nextBarcodeDao.getHighPriorityWorkorderByLineId(lineId);
		if(workorderMap == null) {
			return errJson(jo, "122", "当前没有开始的工单");
		}

		Map<String, Object> productMap = dao.getProduct(workorderMap);

		if(productMap == null) {
			return errJson(jo, "308", "未找到产品");
		}

		map.put("productName", productMap.get("PRODUCTION_NAME"));
		List<Map<String, Object>> paraList = dao.findParaList(map);

		jo.put("paraList", paraList);

		return jo;
	}

	private JSONObject errJson(JSONObject jo, String code, String errMsg) {
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		return jo;
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

}
