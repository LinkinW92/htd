package com.skeqi.mes.service.zch;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.skeqi.mes.mapper.zch.NextBarcodeDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.util.UDPUtils;
import com.skeqi.mes.util.yp.FileReading;

@Service
public class LineSideLibraryApiServiceImpl implements LineSideLibraryApiService {

	private String ptlIp = FileReading.getValue("config.properties", "PTLCallIp");

	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	AssembleBoltDao assembleBoltDao;
	@Autowired
	NextBarcodeDao nextBarcodeDao;
	@Autowired
	LineSideLibraryApiDao lineSideLibraryApiDao;

	@Override
	public JSONObject materialReceivingQuery(Map<String, Object> map) {
		Object lineId = null;
		Object stationId = null;
		Object productId = null;

		String line = map.get("line").toString();
		String station = map.get("station").toString();

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

		Map<String, Object> workorderMap = nextBarcodeDao.getHighPriorityWorkorderByLineId(lineId);
		if(workorderMap != null) {
			productId = workorderMap.get("PRODUCT_ID");
		}

		map.put("lineId", lineId);
		map.put("stationId", stationId);
		map.put("productId", productId);

		List<Map<String, Object>> questList = lineSideLibraryApiDao.findMaterialQuestListByStation(map);
		for (Map<String, Object> questMap : questList) {
			map.put("billNo", questMap.get("billNo"));
			List<Map<String, Object>> detailList = lineSideLibraryApiDao.findMaterialQuestDetailListByStation(map);
			questMap.put("detailList", detailList);
		}

		JSONObject jo = successJson();
		jo.put("questList", questList);

		return jo;
	}

	/**
	 * 收料确认
	 */
	@Override
	public JSONObject materialReceiving(Map<String, Object> map) {

		Map<String, Object> requestMap = lineSideLibraryApiDao.getRequestByBillNo(map);
		if(requestMap == null) {
			return errJson("133", "未查到要料单据");
		}
		if(!"2".equals(requestMap.get("status").toString())) {
			return errJson("134", "该单据未出库或已上架");
		}

		lineSideLibraryApiDao.insertInventory(map);

		List<Map<String, Object>> responseList = lineSideLibraryApiDao.findMaterialResponseListByBillNo(map);

		for (Map<String, Object> responseMap : responseList) {
			lineSideLibraryApiDao.reducePickingLockNumber(responseMap);
		}

		map.put("status", 3);
		lineSideLibraryApiDao.updateRequestStatus(map);

		JSONObject jo = successJson();

		return jo;
	}

	@Override
	public JSONObject manualRequest(Map<String, Object> map) throws ParseException, IOException {
		Object lineId = null;
		Object stationId = null;
		Object rockId = null;
		Object productId = null;

		String line = map.get("line").toString();
		String station = map.get("station").toString();

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

		Map<String, Object> workorderMap = nextBarcodeDao.getHighPriorityWorkorderByLineId(lineId);
		if(workorderMap != null) {
			productId = workorderMap.get("PRODUCT_ID");
		}

		Map<String, Object> materialMap = lineSideLibraryApiDao.getMaterialByCode(map);
		if(materialMap == null) {
			return errJson("129", "未查到物料信息");
		}

		Map<String, Object> rockMap = lineSideLibraryApiDao.getRock(map);
		if(rockMap == null) {
			return errJson("135", "料架号不存在");
		}
		rockId = rockMap.get("ID");

		map.put("lineId", lineId);
		map.put("stationId", stationId);
		map.put("productId", productId);
		map.put("rockId", rockId);
		map.put("materialName", materialMap.get("MATERIAL_NAME"));
		map.put("tracesType", materialMap.get("TRACES_TYPE"));

		// 获得最新单据
		Map<String, Object> lastMap = lineSideLibraryApiDao.getLastQuest();
		Integer serialNumber = 0;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf2.format(new Date());
		if(lastMap != null) {
			Date cdt = sdf1.parse(lastMap.get("cdt").toString());

            if(sdf2.format(cdt).equals(nowDate)) {
    			serialNumber = Integer.parseInt(lastMap.get("serialNumber").toString());
            }
		}
		serialNumber ++;
		String billNo = nowDate + "-" + String.format("%04d", serialNumber);
		map.put("billNo", billNo);
		map.put("serialNumber", String.format("%04d", serialNumber));

		// 插入请求单据
		lineSideLibraryApiDao.insertRequest(map);
		// 插入请求详情
		lineSideLibraryApiDao.insertRequestDetail(map);

		return successJson();
	}

	@Override
	public JSONObject reclaimAlarm(Map<String, Object> map) throws IOException {

		Object productId = null;
		Object totalRecipeId = null;
		Object lineId = null;
		Object stationId = null;
		Object workorderId = null;
		Map<String, Object> workorderMap = null;

		String lineName = map.get("line").toString();
		String stationName = map.get("station").toString();
		String sn = map.get("sn").toString();
		Integer stepNo = Integer.parseInt(map.get("step").toString());

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
					productId = productionMap.get("ID");
					break;
				}
			}
			// 未找到匹配条码的产品
			if(productId == null){
				return errJson("103", "未找到匹配条码的产品");
			}
			// 获取默认配方
			Map<String, Object> totalRecipeMap = checkSnDao.getDefaultTotalRecipe(productId, lineId);

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
			productId = workorderMap.get("PRODUCT_ID");
		}

		// 获取工位配方明细
//		Map<String, Object> recipeDetailMap = assembleBoltDao.getMaterialRecipeDetail(totalRecipeId, stationId, stepNo);
		Map<String, Object> recipeDetailMap = lineSideLibraryApiDao.getMaterialRecipeDetail(totalRecipeId, stationId, stepNo);

		if(recipeDetailMap == null) {
			return errJson("111", "当前步序未查到配方明细");
		}
		// 配方数量
		Integer numb = 1;
		Object NUMBERS = recipeDetailMap.get("NUMBERS");
		if(!StringUtils.isEmpty(NUMBERS)) {
			numb = Integer.parseInt(NUMBERS.toString());
		}

        map.put("lineId", lineId);
		map.put("stationId", stationId);
		map.put("productId", productId);
		map.put("materialNo", recipeDetailMap.get("MATERIALPN"));

        List<Map<String, Object>> rockNoList = lineSideLibraryApiDao.findRockConfigPTL(map);
        if(rockNoList == null || rockNoList.size() == 0){
        	return errJson("131", "未找到物料位置信息");
        }
        String ptlNo = "";
        String data = "P100";
        for (Map<String, Object> rockNoMap : rockNoList) {
        	if(!StringUtils.isEmpty(rockNoMap.get("ptlNo"))) {
        		String pn = rockNoMap.get("ptlNo").toString();
    			ptlNo = ptlNo + pn + ",";

    			data += pn;
    			data += String.format("%05d", numb);
        	}
		}
        if(ptlNo == "") {
        	return errJson("132", "未配置物料位置信息");
        }

        UDPUtils.sendUDP(data, ptlIp, 4001);

		JSONObject jo = successJson();
		jo.put("ptlNo", ptlNo.substring(0, ptlNo.length() - 1));

		return jo;
	}

	@Override
	public JSONObject materialScrap(Map<String, Object> map) {
//		Object lineId = null;
//		Object stationId = null;
//		Object productId = null;
//
//		String line = map.get("line").toString();
//		String station = map.get("station").toString();
//
//		// 获取工位、产线信息
//		Map<String, Object> lineMap = checkSnDao.getLineByName(line);
//		// 判断产线工位是否存在
//		if(lineMap == null) {
//			return errJson("101", "传入产线或工位不存在");
//		}
//		lineId = lineMap.get("ID");
//		Map<String, Object> stationMap = checkSnDao.getStationByName(station, lineId);
//		if(stationMap == null) {
//			return errJson("101", "传入产线或工位不存在");
//		}
//		stationId = stationMap.get("ID");
//        Map<String, Object> productMap = lineSideLibraryApiDao.getProductByName(map);
//        if(productMap == null) {
//            return errJson("301", "传入产品不存在");
//        }
//        productId = productMap.get("ID");
//
//        map.put("lineId", lineId);
//		map.put("stationId", stationId);
//		map.put("productId", productId);

		Map<String, Object> inventoryMap = lineSideLibraryApiDao.getInventoryByCode(map);
		if(inventoryMap != null) {
			lineSideLibraryApiDao.updateInventoryStatus(inventoryMap);
		}

		return successJson();
	}

	@Override
	public List<RLslMaterialRequestT> findLastRequest() {
		return lineSideLibraryApiDao.findLastRequest();
	}

	private JSONObject successJson(){
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		return jo;
	}

	private JSONObject errJson(String code, String errMsg) {
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		return jo;
	}
}
