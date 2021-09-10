package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.AssembleBoltDao;
import com.skeqi.mes.mapper.zch.AssembleMaterialDao;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.LineSideLibraryApiDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;

@Service
public class AssembleMaterialServiceImpl implements AssembleMaterialService {
	@Autowired
	AssembleMaterialDao assembleMaterialDao;
	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	AssembleBoltDao assembleBoltDao;
	@Autowired
	LineSideLibraryApiDao lineSideLibraryApiDao;

	@Override
	public JSONObject checkMaterial(String sn, String lineName, Integer stepNo, String stationName, String barcode) {
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
				matcher = pattern.matcher(sn);
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

		// 获取工位配方明细
		Map<String, Object> recipeDetailMap = assembleBoltDao.getMaterialRecipeDetail(totalRecipeId, stationId, stepNo);

		if(recipeDetailMap == null) {
			return errJson("111", "当前步序未查到配方明细");
		}

		// 是否校验
		Object CHEKORNO = recipeDetailMap.get("CHEKORNO");
		// 校验规则
		Object FEACODE = recipeDetailMap.get("FEACODE");
		// 物料类别 0：批次追溯 1：精确追溯
		Object EXACTORNO = recipeDetailMap.get("EXACTORNO");

		if("1".equals(CHEKORNO)){
			// 校验
			if(StringUtils.isEmpty(FEACODE)) {
				return errJson("115", "校验规则不存在");
			}
			//匹配条码
            Pattern compile = Pattern.compile(FEACODE.toString());
            Matcher matcher = compile.matcher(barcode);
            if(!matcher.find()){
                return errJson("116", "物料校验不通过");
            }

          //查询批追还是精追
            if("1".equals(EXACTORNO)) {
            	//精追,判断是否被装配
                Integer pKeypart = assembleMaterialDao.findPKeypart(barcode);
                Integer rKeypart = assembleMaterialDao.findRKeypart(barcode);
                if(pKeypart>0 || rKeypart>0){
                	return errJson("117", "物料已被装配");
                }
            }
		}

		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		return jo;
	}

	@Override
	public JSONObject assembleKeypart(String sn, String barcode, String materialName, String stationName, String emp, String lineName, Integer stepNo) {
		JSONObject jo = new JSONObject();

		Object lineId = null;
		Object stationId = null;

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

		if(trackingMap == null) {
			return errJson("109", "该总成未上线");
		}

		// 获取R表该物料数据
		Map<String, Object> keypartMap = assembleMaterialDao.getKerpartRByMaterialName(sn, stationName, materialName);
		if(keypartMap == null) {
			return errJson("118", "系统没有初始化该物料信息");
		}
		Object ID = keypartMap.get("ID");
		// 物料编号
		Object KEYPART_NUM = keypartMap.get("KEYPART_NUM");
		// 类型
		Object KEYPART_ID = keypartMap.get("KEYPART_ID");
		// 二级总成
		Object SECOND_NUM = keypartMap.get("SECOND_NUM");

		if(KEYPART_NUM != null) { // 物料编码不为空
			if(!"4".equals(KEYPART_ID)){ // 不是二级总成类型
				return errJson("119", "物料已经装配");
			}

			if("".equals(SECOND_NUM)) {
				return errJson("119", "物料已经装配");
			}
		}

		// 线边仓动态扣料
		try {
			Map<String, Object> inventoryMap = lineSideLibraryApiDao.getInventoryByMaterialCode(barcode);
			// 物料消耗追溯
			if(inventoryMap != null) {
				if("1".equals(inventoryMap.get("type").toString())) {
					lineSideLibraryApiDao.inventoryDeductionBatch(barcode, 1);
				}else {
					lineSideLibraryApiDao.inventoryDeductionSingle(barcode);
				}
				Object materialNo = inventoryMap.get("MATERIALPN");
				Object rockId = inventoryMap.get("rockId");
				Integer un = lineSideLibraryApiDao.updateRetrospect(materialNo, sn, stationId);
				if(un <= 0) {
					lineSideLibraryApiDao.insertRetrospect(materialNo, sn, stationId, emp, rockId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		assembleMaterialDao.updateAssembleKeypartPT(barcode, emp, ID);

		// 更新步序表
		if(stepNo != null) {
			checkSnDao.updateStep(sn, lineName, stationName, stepNo);
		}

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
		return jo;
	}

}
