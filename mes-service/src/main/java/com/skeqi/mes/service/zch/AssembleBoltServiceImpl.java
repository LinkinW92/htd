package com.skeqi.mes.service.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.AssembleBoltDao;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.LineSideLibraryApiDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.util.ToolUtils;

@Service
public class AssembleBoltServiceImpl implements AssembleBoltService {
	@Autowired
	AssembleBoltDao assembleBoltDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	LineSideLibraryApiDao lineSideLibraryApiDao;

	@Override
	public JSONObject getBolt(String snBarcode, String lineName, Integer stepNo, String stationName) {
		JSONObject jo = new JSONObject();

		Object productionId = null;
		Object totalRecipeId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Map<String, Object> workorderMap = null;

		String boltName = "";
		Integer boltNumber = 0;
		Integer remainNumber = 0;

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
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(snBarcode);

		if(trackingMap == null){
			return errJson("109", "该总成未上线");
		}

        // 判断产线工位是否与条码匹配
        if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
            return errJson("120", "传入条码与产线不匹配");
        }

		workorderId = trackingMap.get("WORK_ORDER_ID");

		if(workorderId == null) {
			// 不存在，离线生产
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
			// 获取默认配方
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if(totalRecipeMap == null) {
				return errJson("104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
		}else {
			// 存在，在线生产
			// 获取工单信息
			workorderMap = checkSnDao.getWorkorderById(workorderId);
			if(workorderMap == null) {
				return errJson("105", "工单未开始或状态异常");
			}
			// 获取总配方id和工艺路线
			totalRecipeId = workorderMap.get("TOTAL_RECIPE_ID");
		}
		if(trackingMap.get("TOTAL_RECIPE_ID") != null) {
			totalRecipeId = trackingMap.get("TOTAL_RECIPE_ID");
		}

		Map<String, Object> recipeDetailMap = assembleBoltDao.getRecipeDetail(totalRecipeId, stationId, stepNo, 7);

		if(recipeDetailMap == null) {
			return errJson("111", "当前步序未查到配方明细");
		}

		Object materialName = ToolUtils.replaceSpecialStr(recipeDetailMap.get("MATERIAL_NAME").toString());

		Map<String, Object> boltMap = assembleBoltDao.getBoltUnuse(snBarcode, stationName, materialName);

		if(boltMap == null) {
			return errJson("112", "没有可装配的螺栓");
		}
		boltName = boltMap.get("BOLT_NAME").toString();
		boltNumber = Integer.parseInt(boltMap.get("BOLT_NUM").toString());
		remainNumber = assembleBoltDao.getRemainBoltCount(snBarcode, stationName, materialName);

		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		jo.put("boltName", boltName);
		jo.put("boltNumber", boltNumber);
		jo.put("remainNumber", remainNumber);
		return jo;
	}

	@Override
	public JSONObject assembleBolt(String snBarcode, String aValues, String tValues, String rValues, String lineName,
			Integer stepNo, String stationName, String emp, Object batchCode) {
		JSONObject jo = new JSONObject();
		jo.put("code", "");
		jo.put("errMsg", "");

		Object productionId = null;
		Object totalRecipeId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Object materialName = null;
		Object materialNo = null;
		Object reworkTimes = null;
		Map<String, Object> workorderMap = null;
		Map<String, Object> totalRecipeMap = null;
		Map<String, Object> boltMap = null;

//		Object reworkTimesFlag = null;
		Object remainNumber = null;
		Object boltName = null;

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
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(snBarcode);

		if(trackingMap == null){
			return errJsonAss("109", "该总成未上线");
		}

        // 判断产线工位是否与条码匹配
        if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
            return errJson("120", "传入条码与产线不匹配");
        }

		workorderId = trackingMap.get("WORK_ORDER_ID");

		if(workorderId == null) {
			// 不存在，离线生产
			List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
			if(productionList.size() == 0){
				return errJsonAss("102", "系统中未配置离线产品");
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
				return errJsonAss("103", "未找到匹配条码的产品");
			}
			// 获取默认配方
			totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if(totalRecipeMap == null) {
				return errJsonAss("104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
		}else {
			// 存在，在线生产
			// 获取工单信息
			workorderMap = checkSnDao.getWorkorderById(workorderId);
			if(workorderMap == null) {
				return errJsonAss("105", "工单未开始或状态异常");
			}
			// 获取总配方id和工艺路线
			totalRecipeId = workorderMap.get("TOTAL_RECIPE_ID");
		}
		// 总成运行表关联总配方
		if(trackingMap.get("TOTAL_RECIPE_ID") != null) {
			totalRecipeId = trackingMap.get("TOTAL_RECIPE_ID");
		}

		// 获取工位配方明细
		Map<String, Object> recipeDetailMap = assembleBoltDao.getRecipeDetail(totalRecipeId, stationId, stepNo, 7);

		if(recipeDetailMap == null) {
			return errJsonAss("111", "当前步序未查到配方明细");
		}

		materialName = ToolUtils.replaceSpecialStr(recipeDetailMap.get("MATERIAL_NAME").toString());
		reworkTimes = recipeDetailMap.get("REWORKTIMES");
		materialNo = recipeDetailMap.get("");

		// 获取未装配或NG螺栓
		boltMap = assembleBoltDao.getBoltUnuse(snBarcode, stationName, materialName);
		if(boltMap == null) {
			return errJsonAss("112", "系统没有初始化该螺栓信息或已装配完成");
		}
		// 获取螺栓名称
		boltName = boltMap.get("BOLT_NAME");

		// 螺栓信息赋值
		boltMap.put("R", rValues);
		boltMap.put("A", aValues);
		boltMap.put("T", tValues);
		boltMap.put("WID", emp);
		// 查询剩余数量
		remainNumber = assembleBoltDao.getRemainBoltCount(snBarcode, stationName, materialName);
		// 查询已NG个数
		Integer ngCount = assembleBoltDao.getNgCount(snBarcode, stationName, materialName);

		// 线边仓动态扣料
		if(!StringUtils.isEmpty(batchCode)) {
			Map<String, Object> inventoryMap = lineSideLibraryApiDao.getInventoryByBatchCode(batchCode);
			lineSideLibraryApiDao.inventoryDeductionBatch(batchCode, 1);
			// 物料消耗追溯
			if(inventoryMap != null) {
				materialNo = inventoryMap.get("MATERIALPN");
				Object rockId = inventoryMap.get("rockId");
				Integer un = lineSideLibraryApiDao.updateRetrospect(materialNo, snBarcode, stationId);
				if(un <= 0) {
					lineSideLibraryApiDao.insertRetrospect(materialNo, snBarcode, stationId, emp, rockId);
				}
			}
		}

		// 拧紧状态NG
		if("NG".equals(rValues)){

			if(ngCount >= Integer.parseInt(reworkTimes.toString())) {
				jo.put("isSuccess", false);
				jo.put("code", 113);
				jo.put("errMsg", "拧紧不合格，当前螺栓ng且达到返工次数");
				jo.put("reworkTimesFlag", 1);
				jo.put("remainNumber", remainNumber);
				jo.put("boltName", "");
				return jo;
			}else {
				boltMap.put("Y", 0);
				// 插入一个NG螺栓记录
				assembleBoltDao.insertBoltNg(boltMap);

				jo.put("isSuccess", false);
				jo.put("code", 114);
				jo.put("errMsg", "拧紧不合格");
				jo.put("reworkTimesFlag", 0);
				jo.put("remainNumber", remainNumber);
				jo.put("boltName", "");
				return jo;
			}
		}
		// 拧紧状态OK

		if(ngCount != 0 && ngCount >= Integer.parseInt(reworkTimes.toString())) {
			jo.put("isSuccess", false);
			jo.put("code", 113);
			jo.put("errMsg", "当前螺栓ng且达到返工次数");
			jo.put("reworkTimesFlag", 1);
			jo.put("remainNumber", remainNumber);
			jo.put("boltName", "");
			return jo;
		}

		boltMap.put("Y", 1);
		assembleBoltDao.updateBolt(boltMap);
		assembleBoltDao.updateBoltY(boltMap);

		Integer remainNum = Integer.parseInt(remainNumber.toString()) - 1;
		// 获取剩余螺栓
//		Integer remainNum = assembleBoltDao.getRemainBoltCount(snBarcode, stationName, materialName);

		// 更新步序表
		if(remainNum <= 0) { // 剩余螺栓为0
			checkSnDao.updateStep(snBarcode, lineName, stationName, stepNo);
		} else {
			// 获取线边库剩余数量
			Map<String, Object> map = new HashMap<>();
			map.put("productId", productionId);
			map.put("lineId", lineId);
			map.put("stationId", stationId);
			map.put("batchCode", batchCode);
			Integer rockRemainNum = lineSideLibraryApiDao.getRockRemainNum(map);
			if(rockRemainNum < remainNum) {
				jo.put("code", "136");
				jo.put("errMsg", "线边库库存不足");
			}
		}

		jo.put("isSuccess", true);
		jo.put("reworkTimesFlag", 0);
		jo.put("remainNumber", remainNum);
		jo.put("boltName", boltName);

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
		jo.put("boltName", "");
		jo.put("boltNumber", 0);
		jo.put("remainNumber", 0);
		return jo;
	}

	/**
	 * 返回错误信息json
	 * @param code
	 * @param errMsg
	 * @return
	 */
	private JSONObject errJsonAss(String code, String errMsg) {
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		jo.put("reworkTimesFlag", "");
		jo.put("remainNumber", "");
		jo.put("boltName", "");
		return jo;
	}

}
