package com.skeqi.mes.service.zch;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.ProductionManagementDao;
import com.skeqi.mes.util.ToolUtils;

@Service
public class ProductionManagementServiceImpl implements ProductionManagementService {

	@Autowired
	ProductionManagementDao dao;

	@Override
	public List<Map<String, Object>> findPlanList(Map<String, Object> map) {
		return dao.findPlanList(map);
	}

	@Override
	public Integer addPlan(Map<String, Object> map) {
		Map<String, Object> planMap = dao.getPlanRepetition(map);
		if(planMap != null) {
			return -1;
		}
		return dao.addPlan(map);
	}

	@Override
	public Integer editPlan(Map<String, Object> map) {
		Map<String, Object> planMap = dao.getPlanRepetition(map);
		if(planMap != null) {
			return -1;
		}
		return dao.editPlan(map);
	}

	@Override
	public Integer deletePlan(Map<String, Object> map) {
		return dao.deletePlan(map);
	}

	@Override
	public void mrpExecute() throws Exception {
		// 计划单
		List<Map<String, Object>> planList = dao.findPlanListMrp();
		// 列表不存在 退出执行
		if(planList == null || planList.size() == 0) return;

		// 获取最后计划号
		Map<String, Object> lastPlan = planList.get(planList.size() - 1);
		String lastPlanNo = lastPlan.get("planNo").toString();

		// 总剩余库存
		Map<String, Integer> materialQuantityMap = new HashMap<>();

		//获取仓管及线边库各物料库存
		List<Map<String, Object>> instanceList = dao.findMaterialInstance();
		List<Map<String, Object>> lslInstanceList = dao.findLslInstance();
		Map<String, Integer> instanceMap = new HashMap<>();
		for (Map<String, Object> map : instanceList) {
			instanceMap.put(map.get("materialNo").toString(), Integer.parseInt(map.get("remainingNum").toString()));
		}
		for (Map<String, Object> map : lslInstanceList) {
			String materialNo = map.get("materialNo").toString();
			Integer quantity = Integer.parseInt(map.get("quantity").toString());
			if(instanceMap.get(materialNo) != null) {
				quantity += instanceMap.get(materialNo);
			}
			instanceMap.put(materialNo, quantity);
		}

		ListIterator<Map<String, Object>> iterator = planList.listIterator();
		while (iterator.hasNext()) {
			Map<String, Object> planMap = (Map<String, Object>) iterator.next();
			String projectNo = "";
			Object projectNoObj = planMap.get("projectNo");
			Integer demandQuantity = Integer.parseInt(planMap.get("quantity").toString());

			Map<String, Object> materialMap = dao.getMaterialByProductId(planMap);
			Object purchasingStrategy = materialMap.get("PURCHASING_STRATEGY");
			Integer strategy = 1;
			if(purchasingStrategy != null && "2".equals(purchasingStrategy.toString())) strategy = 2;
//			if(strategy == 2) {
//				projectNo = projectNoObj.toString();
//			}
//			Map<String, Object> instancePara = new HashMap<>();
//			instancePara.put("materialNo", materialNo);
//			instancePara.put("projectNo", projectNo);

			// 判断是否追溯项目号
			if(strategy == 1) {
				planMap.put("projectNo", "");
			}else {
				projectNo = projectNoObj.toString();
			}

			// 查询配方原料
			List<Map<String, Object>> demandMaterialList = dao.findDemandMaterialList(planMap);
			Map<String, Object> instancePara = new HashMap<>();
			for (Map<String, Object> demandMap : demandMaterialList) {
				// 物料编码
				String materialNo = demandMap.get("materialNo").toString();
//				Object procurementCycle = demandMap.get("PROCUREMENT_CYCLE");
				String materialType = demandMap.get("MATERIAL_TYPE").toString();
				Integer demandNum = Integer.parseInt(demandMap.get("demandNum").toString());
				// 不是原料或半成品跳过
				if (!"4".equals(materialType) && !"5".equals(materialType)) continue;

				Integer remainingQuantity = materialQuantityMap.get(materialNo + projectNo);
				Integer remainingNum = 0;
				// 判断剩余库存是否存在
				if(remainingQuantity == null) {
					if(instanceMap.get("materialNo") != null) {
						if(strategy == 2) {
							// 查询仓管以及线边库库存
							instancePara.put("materialNo", materialNo);
							instancePara.put("projectNo", projectNo);
							Map<String, Object> wInstanceMap = dao.getMaterialInstanceByNo(instancePara);
							Map<String, Object> lslInstanceMap = dao.getLslInstanceByNo(instancePara);
							Integer wInt = 0;
							Integer lInt = 0;
							if(wInstanceMap != null) wInt = Integer.parseInt(wInstanceMap.get("remainingNum").toString());
							if(lslInstanceMap != null) lInt = Integer.parseInt(lslInstanceMap.get("quantity").toString());
							remainingNum = wInt + lInt;
						} else {
							remainingNum = instanceMap.get("materialNo");
						}
						// 查询已下单采购单
						Map<String, Object> purchaseMap = dao.getPurchaseMap(instancePara);
						if(purchaseMap != null) {
							remainingNum += Integer.parseInt(purchaseMap.get("quantity").toString());
						}
						// 查询采购申请单
						Map<String, Object> purchaseDemandMap = dao.getPurchaseDemandMap(instancePara);
						if(purchaseDemandMap != null) {
							remainingNum += Integer.parseInt(purchaseDemandMap.get("quantity").toString());
						}
						// 半成品考虑工单产出以及已排计划单
						if("5".equals(materialType)) {
							// 工单产出
							Map<String, Object> workorderMap = dao.getWorkorderMap(instancePara);
							if(workorderMap != null) {
								remainingNum += Integer.parseInt(workorderMap.get("quantity").toString());
							}
							// 已排计划单
							Map<String, Object> scheduledPlanMap = dao.getScheduledPlanMap(instancePara);
							if(scheduledPlanMap != null) {
								remainingNum += Integer.parseInt(scheduledPlanMap.get("quantity").toString());
							}
						}
						// 计算工单锁定数
						Map<String, Object> workorderLockMap = dao.getWorkorderLockMap(instancePara);
						if(workorderLockMap != null) {
							remainingNum -= Integer.parseInt(workorderLockMap.get("quantity").toString());
						}
					}
				} else {
					remainingNum = remainingQuantity;
				}
				// 计算当前计划当前物料需求数
				demandNum = demandNum * demandQuantity;
				// 扣除需求
				remainingNum -= demandNum;
				if(remainingNum >= 0) {
					materialQuantityMap.put(materialNo + projectNo, remainingNum);
				} else { // 库存不足
					Integer difference = 0 - remainingNum;
					if("5".equals(materialType)) { // 半成品添加计划单
						Map<String, Object> newPlanMap = new HashMap<>();
						newPlanMap.put("planNo", ToolUtils.generateNewNumber(lastPlanNo, "P", "yyyyMMdd", 4));
						newPlanMap.put("projectNo", planMap.get("projectNo"));
						newPlanMap.put("projectName", planMap.get("projectName"));
						newPlanMap.put("productId", dao.getProductIdByMaterialNo(instancePara).get("productId"));
						newPlanMap.put("materialNo", materialNo);
						newPlanMap.put("lineId", planMap.get("lineId"));
						newPlanMap.put("quantity", difference);
						newPlanMap.put("customer", planMap.get("customer"));
						newPlanMap.put("planStartDate", planMap.get("planStartDate"));
						newPlanMap.put("planEndDate", planMap.get("planEndDate"));
						dao.addPlan(newPlanMap);
						iterator.add(newPlanMap);
						iterator.previous();
					} else { // 原料生产采购单
						Map<String, Object> lastPurchaseDemandMap = dao.getLastPurchaseDemand();
						String lastRequestCode = "";
						if(lastPurchaseDemandMap != null) {
							lastRequestCode = lastPurchaseDemandMap.get("request_code").toString();
						}
						// 生成新采购请求单号
						String newRequestCode = ToolUtils.generateNewNumber(lastRequestCode, "PR", "yyyyMMdd", 6);
						Map<String, Object> purchaseDemandMap = new HashMap<>();
						purchaseDemandMap.put("request_code", newRequestCode);
						purchaseDemandMap.put("project_code", projectNo);
						purchaseDemandMap.put("project_name", planMap.get("projectName"));
						dao.insertPurchaseDemand(purchaseDemandMap);
						Map<String, Object> purchaseDemandRMap = new HashMap<>();
						purchaseDemandRMap.put("request_code", newRequestCode);
						purchaseDemandRMap.put("material_code", materialNo);
						purchaseDemandRMap.put("material_name", materialMap.get("MATERIAL_NAME"));
						purchaseDemandRMap.put("count", difference);
						purchaseDemandRMap.put("unit", materialMap.get("STOCK_UNIT"));
						dao.insertPurchaseDemandR(purchaseDemandRMap);
					}
					materialQuantityMap.put(materialNo + projectNo, 0);
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> findSNList(Map<String, Object> map) {
		return dao.findSNList(map);
	}

	@Override
	public Integer transcodingSN(Map<String, Object> map) {
		Map<String, Object> trackingMap = dao.getTrackingBySn(map);
		if(trackingMap == null) return -1;
		Integer countR = dao.getTrackingCountRBySn(map);
		Integer countP = dao.getTrackingCountPBySn(map);
		if(countR + countP > 0) return -2;
		trackingMap.put("SN", map.get("newSn"));
		// 复制源码内容到新码
		dao.insertTracking(trackingMap);
		// 插入转码记录表
		map.put("type", 1);
		dao.insertTranscodingRecord(map);
		// 替换生产过程表绑定sn
		dao.updateSnBinding(map);
		// 原码状态修改
		dao.updateOriginalSnState(map);

		return 1;
	}

	@Override
	public Integer splitSN(Map<String, Object> map) {
		Map<String, Object> trackingMap = dao.getTrackingBySn(map);
		if(trackingMap == null) return -1;
		Integer quantity = Integer.parseInt(map.get("quantity").toString());
		map.put("type", 2);
		for (int i = 1; i <= quantity; i++) {
			trackingMap.put("SN", map.get("sn").toString() + "_" + i);
			// 复制源码内容到新码
			dao.insertTracking(trackingMap);
			// 插入转码记录表
			dao.insertTranscodingRecord(map);
			// 替换生产过程表绑定sn
			map.put("i", i);
			dao.copySnBinding(map);
			// 原码状态修改
			dao.updateOriginalSnState(map);
		}
		return 1;
	}

	@Override
	public List<Map<String, Object>> findContainerList(Map<String, Object> map) {
		return dao.findContainerList(map);
	}

	@Override
	public Integer addContainer(Map<String, Object> map) {
		Map<String, Object> containerMap = dao.getContainerRepetition(map);
		if(containerMap != null) {
			return -1;
		}
		return dao.addContainer(map);
	}

	@Override
	public Integer editContainer(Map<String, Object> map) {
		Map<String, Object> containerMap = dao.getContainerRepetition(map);
		if(containerMap != null) {
			return -1;
		}
		return dao.editContainer(map);
	}

	@Override
	public Integer deleteContainer(Map<String, Object> map) {
		return dao.deleteContainer(map);
	}

	@Override
	public List<Map<String, Object>> findPackList(Map<String, Object> map) {
		return dao.findPackList(map);
	}

	@Override
	public Integer addPack(Map<String, Object> map) {
		Map<String, Object> packMap = dao.getPackRepetition(map);
		if(packMap != null) {
			return -1;
		}
		return dao.addPack(map);
	}

	@Override
	public Integer editPack(Map<String, Object> map) {
		Map<String, Object> packMap = dao.getPackRepetition(map);
		if(packMap != null) {
			return -1;
		}
		return dao.editPack(map);
	}

	@Override
	public Integer deletePack(Map<String, Object> map) {
		List<Map<String, Object>> packDetailList = dao.findPackDetailListByPackId(map);
		if(packDetailList != null && packDetailList.size() > 0) {
			return -1;
		}
		return dao.deletePack(map);
	}

	@Override
	public List<Map<String, Object>> findPackDetailList(Map<String, Object> map) {
		return dao.findPackDetailList(map);
	}

	@Override
	public Integer addPackDetail(Map<String, Object> map) {
		Map<String, Object> packDetailMap = dao.getPackDetailRepetition(map);
		if(packDetailMap != null) {
			return -1;
		}
		map.put("newSn", map.get("sn"));
		Integer count1 = dao.getTrackingCountRBySn(map);
		Integer count2 = dao.getTrackingCountPBySn(map);
		if(count1 + count2 <= 0) {
			return -2;
		}
		return dao.addPackDetail(map);
	}

	@Override
	public Integer editPackDetail(Map<String, Object> map) {
		Map<String, Object> packDetailMap = dao.getPackDetailRepetition(map);
		if(packDetailMap != null) {
			return -1;
		}
		return dao.editPackDetail(map);
	}

	@Override
	public Integer deletePackDetail(Map<String, Object> map) {
		return dao.deletePackDetail(map);
	}
}
