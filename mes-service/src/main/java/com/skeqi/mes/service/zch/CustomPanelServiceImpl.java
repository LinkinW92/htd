package com.skeqi.mes.service.zch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.AssembleBoltDao;
import com.skeqi.mes.mapper.zch.AssembleMaterialDao;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.CustomPanelDao;
import com.skeqi.mes.mapper.zch.LineSideLibraryApiDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslDictionaryTService;
import com.skeqi.mes.util.UDPUtils;
import com.skeqi.mes.util.wf.UniversalUtil;

@Service
public class CustomPanelServiceImpl implements CustomPanelService {

	@Autowired
	CustomPanelDao dao;
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
	@Autowired
	ProductionManagementService productionManagementService;
	@Autowired
	CLslDictionaryTService dictionaryTService;

	@Override
	public JSONObject snDetailedList(JSONObject json) {
		JSONObject jo = new JSONObject();
		JSONArray show = json.getJSONArray("show");
		JSONObject query = json.getJSONObject("query");

		List<Map<String, Object>> snList = dao.snDetailedList(query);
		List<String[]> tdDatas = new ArrayList<>();
		for (Map<String, Object> map : snList) {
			String[] tdData = new String[show.size()];
			for (int i = 0; i < show.size(); i++) {
				String s = show.getString(i);
				Object value = "";
				switch (s) {
				case "SN":
					value = map.getOrDefault("SN", "");
					break;
				case "物料编码":
					value = map.getOrDefault("MATERIAL_CODE", "");
					break;
				case "物料名称":
					value = map.getOrDefault("PRODUCTION_NAME", "");
					break;
				case "数量":
					value = map.getOrDefault("PLAN_NUM", "");
					break;
				case "工单号":
					value = map.getOrDefault("WORKORDER_ID", "");
					break;
				}
				tdData[i] = value.toString();
			}
			tdDatas.add(tdData);
		}
		JSONObject data = new JSONObject();
		data.put("thDatas", show);
		data.put("tdDatas", tdDatas);
		jo.put("code", 200);
		jo.put("errMsg", "");
		jo.put("data", data);
		return jo;
	}

	@Override
	public JSONObject assemblyMaterial(JSONObject json) {
		String sn = json.getString("sn");
		String lineName = json.getString("line");
		Integer stepNo = json.getInteger("step");
		String stationName = json.getString("station");
		String barcode = json.getString("barcode");
		String emp = json.getString("emp");

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
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		lineId = lineMap.get("ID");
		Map<String, Object> stationMap = checkSnDao.getStationByName(stationName, lineId);
		if(stationMap == null) {
			return errJson(jo, "101", "传入产线或工位不存在");
		}
		stationId = stationMap.get("ID");

		// 获取运行表
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(sn);

		if(trackingMap == null){
			return errJson(jo, "109", "该总成未上线");
		}

        // 判断产线工位是否与条码匹配
        if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
            return errJson(jo, "120", "传入条码与产线不匹配");
        }

		workorderId = trackingMap.get("WORK_ORDER_ID");

		if(workorderId == null) {
			// 不存在，离线生产
			List<Map<String, Object>> productionList = checkSnDao.findProductionOffline();
			if(productionList.size() == 0){
				return errJson(jo, "102", "系统中未配置离线产品");
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
				return errJson(jo, "103", "未找到匹配条码的产品");
			}
			// 获取默认配方
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productionId, lineId);

			// 判断是否配置默认工艺路线和配方
			if(totalRecipeMap == null) {
				return errJson(jo, "104", "此产品产线未配置默认工艺路线或配方");
			}
			// 总配方id
			totalRecipeId = totalRecipeMap.get("ID");
		}else {
			// 存在，在线生产
			// 获取工单信息
			workorderMap = checkSnDao.getWorkorderById(workorderId);
			if(workorderMap == null) {
				return errJson(jo, "105", "工单未开始或状态异常");
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
			return errJson(jo, "111", "当前步序未查到配方明细");
		}

		// 是否校验
		Object CHEKORNO = recipeDetailMap.get("CHEKORNO");
		// 校验规则
		Object FEACODE = recipeDetailMap.get("FEACODE");
		// 物料类别 0：批次追溯 1：精确追溯
		Object EXACTORNO = recipeDetailMap.get("EXACTORNO");
		// 物料编码
		Object MATERIALPN = recipeDetailMap.get("MATERIALPN");

		// 上料预扫码
		if(StringUtils.isEmpty(barcode)) {
			Map<String, Object> loadingMap = dao.getLoadingMap(stationId, lineId, workorderId, MATERIALPN);
			if(loadingMap == null) {
				return errJson(jo, "202", "未传递物料码，且未预扫码");
			}
			barcode = loadingMap.get("batchCode").toString();
		}

		if("1".equals(CHEKORNO)){
			// 校验
			if(StringUtils.isEmpty(FEACODE)) {
				return errJson(jo, "115", "校验规则不存在");
			}
			//匹配条码
            Pattern compile = Pattern.compile(FEACODE.toString());
            Matcher matcher = compile.matcher(barcode);
            if(!matcher.find()){
                return errJson(jo, "116", "物料校验不通过");
            }

          //查询批追还是精追
            if("1".equals(EXACTORNO)) {
            	//精追,判断是否被装配
                Integer pKeypart = assembleMaterialDao.findPKeypart(barcode);
                Integer rKeypart = assembleMaterialDao.findRKeypart(barcode);
                if(pKeypart>0 || rKeypart>0){
                	return errJson(jo, "117", "物料已被装配");
                }
            }
		}

		// 获取R表该物料数据
		Map<String, Object> keypartMap = assembleMaterialDao.getKerpartRByMaterialPN(sn, stationName, MATERIALPN);
		if(keypartMap == null) {
			return errJson(jo, "118", "系统没有初始化该物料信息");
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
				return errJson(jo, "119", "物料已经装配");
			}

			if("".equals(SECOND_NUM)) {
				return errJson(jo, "119", "物料已经装配");
			}
		}

		// 线边仓动态扣料
		try {
			Map<String, Object> inventoryMap = lineSideLibraryApiDao.getInventoryByMaterialCode(barcode);
			lineSideLibraryApiDao.inventoryDeductionSingle(barcode);
			// 物料消耗追溯
			if(inventoryMap != null) {
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
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}

	/**
	 * 返回错误信息json
	 * @param code
	 * @param errMsg
	 * @return
	 */
	private JSONObject errJson(JSONObject jo, String code, String errMsg) {
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		return jo;
	}

	@Override
	public JSONObject splitSN(JSONObject json) {
		JSONObject jo = new JSONObject();

		Map<String, Object> map = new HashMap<>();
		map.put("sn", json.get("sn"));
		map.put("quantity", json.get("quantity"));
		map.put("lineId", json.get("lineId"));
		Integer result = productionManagementService.splitSN(map);
		if(result == -1) {
			return errJson(jo, "109", "未查到sn");
		}
		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}

	@Override
	public JSONObject findLoadingMaterialList(JSONObject json) {
		JSONObject jo = new JSONObject();
		// 配方详情
		List<Map<String, Object>> recipeMaterialList = dao.findRecipeMaterialList(json);

		String[] thDatas = new String[]{"物料编码", "物料名称", "库存条码", "剩余数量"};
		List<String[]> tdDatas = new ArrayList<>();
		for (Map<String, Object> map : recipeMaterialList) {
			String[] tdData = new String[thDatas.length];
			tdData[0] = map.getOrDefault("materialNo", "").toString();
			tdData[1] = map.getOrDefault("materialName", "").toString();
			tdData[2] = map.getOrDefault("batchCode", "").toString();
			tdData[3] = map.getOrDefault("remainQuantity", "").toString();
			tdDatas.add(tdData);
		}
		JSONObject data = new JSONObject();
		data.put("thDatas", thDatas);
		data.put("tdDatas", tdDatas);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("data", data);
		return jo;
	}

	@Override
	public JSONObject addLoadingMaterial(JSONObject json) {
		JSONObject jo = new JSONObject();

		Map<String, Object> batchMap = dao.getLoadingMaterial(json);
		if(batchMap != null) {
			return errJson(jo, "203", "该批次号已录入");
		}
		dao.insertLoadingMaterial(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}


	@Override
	public JSONObject findNcCodeConfigList(JSONObject json) {
		JSONObject jo = new JSONObject();
		List<Map<String, Object>> list = dao.findNcCodeConfigList(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("data", list);
		return jo;
	}

	@Override
	public JSONObject findNcCodeRecordList(JSONObject json) {
		JSONObject jo = new JSONObject();
		// 配方详情
		List<Map<String, Object>> list = dao.findNcCodeRecordList(json);

		String[] thDatas = new String[]{"不合格编码", "操作者", "操作时间", "状态"};
		List<String[]> tdDatas = new ArrayList<>();
		for (Map<String, Object> map : list) {
			String[] tdData = new String[thDatas.length];
			tdData[0] = map.getOrDefault("nc_code", "").toString();
			tdData[1] = map.getOrDefault("staff", "").toString();
			tdData[2] = map.getOrDefault("operationTime", "").toString();
			String status = "关闭";
			if("0".equals(map.getOrDefault("status", "").toString())) status = "开启";
			tdData[3] = status;
			tdDatas.add(tdData);
		}
		JSONObject data = new JSONObject();
		data.put("thDatas", thDatas);
		data.put("tdDatas", tdDatas);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("data", data);
		return jo;
	}

	@Override
	public JSONObject addNcCodeRecord(JSONObject json) {
		JSONObject jo = new JSONObject();
		json.put("number", UniversalUtil.generateNumber());
		dao.addNcCodeRecord(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}

	@Override
	public JSONObject hornAlarm(JSONObject json) throws IOException {
		JSONObject jo = new JSONObject();
		Map<String, Object> trumMap = dao.getTrumpetByStation(json);
		if(trumMap == null) {
			return errJson(jo, "330", "该工位未设置报警信息");
		}

		Map<String, String> map = new HashMap<>();
		map.put("line", json.get("line").toString());
		map.put("station", json.get("station").toString());
		map.put("message", json.get("message").toString());
		map.put("trumpet", trumMap.getOrDefault("trumpet", "").toString());
		map.put("loopSum", trumMap.getOrDefault("loopSum", "").toString());

		List<CLslDictionaryT> cLslDictionaryTS = dictionaryTService.selectAll();
		// 获取喇叭配置
        cLslDictionaryTS.forEach(cLslDictionaryT -> {
            if (cLslDictionaryT.getKey().equals("loopSum")) {
                if (map.get("loopSum")==null||map.get("loopSum").equals("")){
                	map.put("loopSum", cLslDictionaryT.getValue());
                }
            }
            if (cLslDictionaryT.getKey().equals("ip")) {
            	map.put("ip", cLslDictionaryT.getValue());
            }
            if (cLslDictionaryT.getKey().equals("portNumber")) {
            	map.put("portNumber", cLslDictionaryT.getValue());
            }
        });

        // 创建数据包
        UDPUtils.sendUDP(map.toString(),map.get("ip").toString(), Integer.valueOf(map.get("portNumber").toString()));

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}

	@Override
	public JSONObject getLoadingCode(JSONObject json) {
		JSONObject jo = new JSONObject();
		String batchCode = dao.getLoadingCode(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("batchCode", batchCode);
		return jo;
	}

	@Override
	public JSONObject findDataCollectionRecordList(JSONObject json) {
		JSONObject jo = new JSONObject();
		List<Map<String, Object>> list = dao.findDataCollectionRecordList(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("data", list);
		return jo;
	}

	@Override
	public JSONObject findDataCollectionParams(JSONObject json) {
		JSONObject jo = new JSONObject();
		List<Map<String, Object>> list = dao.findDataCollectionParams(json);

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		jo.put("data", list);
		return jo;
	}

	@Override
	public JSONObject addDataCollection(JSONObject json) {
		JSONObject jo = new JSONObject();
		String number = UniversalUtil.generateNumber();
		json.put("number", number);
		JSONArray ja = json.getJSONArray("paramsData");

		dao.addDataCollectionRecord(json);
		List<Map<String, Object>> list = dao.findDataCollectionParamsAll(json);
		for (Map<String, Object> map : list) {
			for (int i = 0; i < ja.size(); i++) {
				JSONObject joo = ja.getJSONObject(i);
				if(map.get("number").toString().equals(joo.getString("number"))) {
					map.put("value", joo.get("value"));
				}
			}
			map.put("record_number", number);
			dao.addDataCollectionRecordDetail(map);
		}

		jo.put("isSuccess", true);
		jo.put("code", "200");
		jo.put("errMsg", "");
		return jo;
	}

}
