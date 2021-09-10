package com.skeqi.mes.service.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.CheckSnDao;
import com.skeqi.mes.mapper.zch.LineSideLibraryDao;
import com.skeqi.mes.mapper.zch.UpdateSnDao;
import com.skeqi.mes.pojo.zch.MesBoltT;
import com.skeqi.mes.pojo.zch.MesKeypartT;
import com.skeqi.mes.pojo.zch.MesLeakageT;
import com.skeqi.mes.pojo.zch.MesPrintT;

@Service
public class UpdateSnServiceImpl implements UpdateSnService {
	@Autowired
	CheckSnDao checkSnDao;
	@Autowired
	UpdateSnDao updateSnDao;
	@Autowired
	HookService hookService;
	@Autowired
	LineSideLibraryDao lineSideLibraryDao;

	@Override
	public JSONObject updateSN(String snBarcode, String stationName, String lineName) {

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
//		Object WORKORDER_ID = null;

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
//		stationIndex = stationMap.get("STATION_INDEX");

		// Hook
		Map<String, Object> checkMap = new HashMap<>();
		checkMap.put("stationId", stationId);
		checkMap.put("sn", snBarcode);
		checkMap.put("inOut", 2);
		try {
			if(!hookService.checkJob(checkMap)) {
				return errJson("151", "关联校验未通过");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取下线表
		Integer trackingCountP = checkSnDao.getTrackingCountP(snBarcode, lineId);
		if(trackingCountP > 0) {
			return errJson("107", "此产品已正常生产且下线");
		}

		// 获取上线表
		Map<String, Object> trackingMap = updateSnDao.getTrackingR(snBarcode);

		if(trackingMap == null){
			return errJson("109", "该总成未上线");
		}
		// 判断产线工位是否与条码匹配
		if(!lineId.toString().equals(trackingMap.get("LINE_ID").toString())) {
			return errJson("120", "传入条码与产线不匹配");
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
				// 获取默认工艺路线
				Map<String, Object> routingMap = checkSnDao.getDefaultRouting(productionId, lineId);

				// 判断是否配置默认工艺路线
				if(routingMap == null) {
					return errJson("104", "此产品产线未配置默认工艺路线或配方");
				}
				// 工艺路线id
				routingId = routingMap.get("ID");
			}else {
				//存在，在线生产
				onlineFlag = 1;
				// 获取工单信息
				workorderMap = checkSnDao.getWorkorderById(workorderId);
				if(workorderMap == null) {
					return errJson("105", "工单未开始或状态异常");
				}
				//获取产品id
				productionId = workorderMap.get("PRODUCT_ID");
				// 工单编号
//				WORKORDER_ID = workorderMap.get("WORKORDER_ID");
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
				return errJson("106", "工艺路线配置错误");
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
//				updateSnDao.insertMaterialInstance(snBarcode, productMap.get("MATERIAL_CODE"), productMap.get("PRODUCTION_NAME"), WORKORDER_ID);
				// 添加到线边库库存里
				Map<String, Object> map = new HashMap<>();
				map.put("quantity", 1);
				map.put("productId", productionId);
				map.put("stationId", stationId);
				map.put("lineId", lineId);
				map.put("type", 2);
				map.put("materialCode", snBarcode);
				if(productMap != null) {
					map.put("materialNo", productMap.get("MATERIAL_CODE"));
					map.put("materialName", productMap.get("PRODUCTION_NAME"));
				}
				lineSideLibraryDao.insertInventory(map);
			}
		}else {
			return errJson("111", "此条码已NG");
		}

		JSONObject jo = new JSONObject();
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
