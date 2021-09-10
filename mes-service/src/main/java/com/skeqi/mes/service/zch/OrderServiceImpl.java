package com.skeqi.mes.service.zch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.zch.OrderDao;
import com.skeqi.mes.util.Rjson;
import org.springframework.util.StringUtils;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao dao;

	@Autowired
	private CustomPropertyDao cMesCustomPropertyMapper;

	@Autowired
	CMesCustomPropertyService customPropertyService;

	/**
	 * 获取订单列表
	 */
	@Override
	public List<Map<String, Object>> findOrderList(Map<String, Object> map) {
		return dao.findOrderList(map);
	}

	/**
	 * 操作自定义属性值（内容）
	 * @param map
	 * @throws Exception
	 */
	private void handleCustomValue(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> editList = new ArrayList<>();
		List<Map<String, Object>> addList = new ArrayList<>();
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("custom");
		if (list != null && list.size() > 0) {
			for (Map<String, Object> object : list) {
				if (!StringUtils.isEmpty(object)) {
					System.out.println(object);
					object.put("objectCode", map.get("CODE"));
					if (object.get("bindScopeKey").equals("0")) {
						object.put("propertyId", object.get("id"));
						if (object.get("value") == null || "".equals(object.get("value"))) {
							object.put("value", object.get("defaults"));
						}
					}
				}
				CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
				cMesCustomProperty.setObjectType(object.get("objectType").toString());
				cMesCustomProperty.setBindScopeValue(object.get("objectCode").toString());
				List<Integer> customPropertyValueAll = cMesCustomPropertyMapper.selectCustomPropertyValueAll(cMesCustomProperty);
				if (customPropertyValueAll.size()>0){
					//编辑
					editList.add(object);
				}else {
					//新增
					addList.add(object);
				}
			}

			//新增操作
			if (addList.size()>0){
				Map<String, Object> map1 = new HashMap<>();
				map1.put("list", addList);
				Integer editCustomPropertyValue = cMesCustomPropertyMapper.addCustomPropertyValue(map1);
				if (editCustomPropertyValue < 1) {
					throw new Exception("添加自定义属性内容失败");
				}
			}

			//编辑操作
			if (editList.size()>0){
				for (Object object : editList) {
					if (!StringUtils.isEmpty(object)){
						Map<String, Object> hashMap = (Map<String, Object>) object;
						if (hashMap.get("bindScopeKey").equals("0")) {
							if (StringUtils.isEmpty(hashMap.get("bindScopeValue"))) {
								hashMap.put("bindScopeValue",map.get("CODE"));
							}
						}
						Integer integer = cMesCustomPropertyMapper.editCustomPropertyValue(hashMap);
						if (integer < 1) {
							throw new Exception("编辑自定义属性内容失败");
						}
					}
				}
			}
		}
	}

	/**
	 * 新增订单
	 */
	@Override
	public Integer addOrder(Map<String, Object> map) throws Exception {
		//新增自定义属性值（内容）
		handleCustomValue(map);
		return dao.addOrder(map);
	}

	/**
	 * 修改订单
	 */
	@Override
	public Integer updateOrder(Map<String, Object> map) throws Exception {
		//修改自定义属性值（内容）
		handleCustomValue(map);
		//清空
		map.put("CODE","");
		return dao.updateOrder(map);
	}

	/**
	 * 获取订单记录
	 */
	@Override
	public List<Map<String, Object>> findOrderrecondList(Map<String, Object> map) {
		return dao.findOrderrecondList(map);
	}

	/**
	 * 新增订单记录
	 */
	@Override
	public Integer addOrderrecord(Map<String, Object> map) {
		return dao.addOrderrecord(map);
	}

	/**
	 * 修改订单记录
	 */
	@Override
	public Integer updateOrderrecord(Map<String, Object> map) {
		return dao.updateOrderrecord(map);
	}

	/**
	 * 排产订单及记录列表
	 */
	@Override
	public List<Map<String, Object>> schedulingOrderList() {
		List<Map<String, Object>> list = dao.findOrderListOnScheduling();
		for (Map<String, Object> map : list) {
			Map<String, Object> mapOrder = new HashMap<>();
			mapOrder.put("order_id", map.get("ID"));
			map.put("orderrecordList", dao.findOrderrecondList(mapOrder));
			Map<String, Object> mapNum = dao.getSchedulingNum(mapOrder);
			Integer orderNum = Integer.parseInt(mapNum.get("orderNum").toString());
			Integer demandNum = Integer.parseInt(mapNum.get("demandNum").toString());
			if(demandNum != 0){
				map.put("schedulingRatio", orderNum * 100 / demandNum + "%");
			}else {
				map.put("schedulingRatio", "0%");
			}
		}
		return Rjson.getListByFormatTime(list);
	}

	/**
	 * 排产工单列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> schedulingWorkorderList(Map<String, Object> map) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Map<String, Object>> list = new ArrayList<>();
		String LINE_ID = map.get("LINE_ID").toString();
		String[] lineIds = LINE_ID.split(",");
		for (String lineId : lineIds) {
			map.put("LINE_ID", lineId);
			Date BEGIN_DATE = sdf.parse(map.get("BEGIN_DATE").toString());
			Date END_DATE = sdf.parse(map.get("END_DATE").toString());
			Calendar c = Calendar.getInstance();
			c.setTime(BEGIN_DATE);
			Date date = BEGIN_DATE;
			if(BEGIN_DATE.compareTo(END_DATE) <= 0){
				while (date.compareTo(END_DATE) <= 0){
					map.put("time", date);
					List<Map<String, Object>> listWO = dao.findWorkorderByLineId(map);
					Map<String, Object> mapWO = new HashMap<>();
					mapWO.put("workorderList", Rjson.getListByFormatTime(listWO));
					mapWO.put("date", sdf.format(date));
					mapWO.put("LINE_ID", lineId);
					list.add(mapWO);
					c.add(Calendar.DAY_OF_MONTH, 1);
					date=c.getTime();
				}
			}else{
				throw new Exception("开始时间不能大于截止时间");
			}
		}
		return list;
	}

	/**
	 * 删除订单
	 */
	@Override
	public Integer deleteOrder(Map<String, Object> map) throws Exception {
		//查询当前将被删除的订单
		map.put("id",map.get("ID"));
		Map<String, Object> orderByIdOrCode = dao.findOrderByIdOrCode(map);
		if (StringUtils.isEmpty(orderByIdOrCode)) {
			throw new Exception("删除订单失败");
		}
		CMesCustomProperty customProperty = new CMesCustomProperty();
		customProperty.setBindScopeValue((String) orderByIdOrCode.get("CODE"));
		customProperty.setObjectType(CustomAttributesConstant.orderManagement);

		Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
		if (integer<1){
			throw new Exception("删除失败");
		}
		return dao.deleteOrder(map);
	}

	/**
	 * 删除订单记录
	 */
	@Override
	public Integer deleteOrderrecord(Map<String, Object> map) {
		return dao.deleteOrderrecord(map);
	}

	/**
	 * 根据id获取订单
	 */
	@Override
	public Map<String, Object> getByID(Map<String, Object> map) {
		return dao.getByID(map);
	}

	/**
	 * 根据id获取订单详情
	 */
	@Override
	public Map<String, Object> getOrderrecordByID(Map<String, Object> map) {
		return dao.getOrderrecordByID(map);
	}

	/**
	 * 查询工单需求物料列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findDemandMaterial(Map<String, Object> map)  throws Exception {
		Map<String, Object> mapResult = new HashMap<>();
		List<String> listName = new ArrayList<>();
		List<String> listNO = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		String beginTime = map.get("beginTime").toString();
		String todayTime = sdf.format(new Date());
		// 预测未来库存，需从今天开始计算
		if(beginTime.compareTo(todayTime) > 0){
			map.put("beginTime", todayTime);
		}

		// 获取物料需求列表
		List<Map<String, Object>> list = dao.findDemandMaterial(map);
		// 页面物料信息缓存
		List<Map<String, Object>> listwlTemp = new ArrayList<>();
		Map<String, Object> mapStock = new HashMap<>();

		for (Map<String, Object> mapMa : list) {
			// 从开始日期开始查
			String date = mapMa.get("PLAN_START_TIME").toString().substring(0, 10).replace("-", "/");
			int flog = 0;

			for (int i = 0; i < listwlTemp.size(); i++) {
				// 查找同日期同种物料数量增加，否则赋值
				Map<String, Object> mapwl = listwlTemp.get(i);
				if(date.equals(mapwl.get("date"))){
					flog = 1;
					List<Map<String, Object>> materialList = (List<Map<String, Object>>) mapwl.get("materialList");
					int flogM = 0;
					for (Map<String, Object> mapMaterial : materialList) {
						if(mapMaterial.get("MATERIAL_NAME").toString().equals(mapMa.get("MATERIAL_NAME"))){
							flogM = 1;
							Integer num1 = Integer.parseInt(mapMaterial.get("DEMAND_NUM").toString());
							Integer num2 = Integer.parseInt(mapMa.get("DEMAND_NUM").toString());
							mapMaterial.put("DEMAND_NUM", num1 + num2);
							break;
						}
					}

					if(flogM == 0){
						Map<String, Object> mapMaterial = new HashMap<>();
						mapMaterial.put("MATERIAL_NO", mapMa.get("MATERIAL_NO"));
						mapMaterial.put("MATERIAL_NAME", mapMa.get("MATERIAL_NAME"));
						mapMaterial.put("DEMAND_NUM", mapMa.get("DEMAND_NUM"));

						materialList.add(mapMaterial);
					}
					break;
				}
			}

			if(flog == 0){
				Map<String, Object> mapwl = new HashMap<>();
				mapwl.put("date", date);
				List<Map<String, Object>> materialList = new ArrayList<>();
				Map<String, Object> mapMaterial = new HashMap<>();
				mapMaterial.put("MATERIAL_NO", mapMa.get("MATERIAL_NO"));
				mapMaterial.put("MATERIAL_NAME", mapMa.get("MATERIAL_NAME"));
				mapMaterial.put("DEMAND_NUM", mapMa.get("DEMAND_NUM"));

				materialList.add(mapMaterial);
				mapwl.put("materialList", materialList);
				listwlTemp.add(mapwl);
			}

			if(!listNO.contains(mapMa.get("MATERIAL_NO").toString())){
				listName.add(mapMa.get("MATERIAL_NAME").toString());
				listNO.add(mapMa.get("MATERIAL_NO").toString());
			}
		}

		Date BEGIN_DATE = sdf.parse(map.get("beginTime").toString());
		Date END_DATE = sdf.parse(map.get("endTime").toString());
		Calendar c = Calendar.getInstance();
		c.setTime(BEGIN_DATE);
		Date date = BEGIN_DATE;
		if(BEGIN_DATE.compareTo(END_DATE) <= 0){
			List<Map<String, Object>> listwlTempExtra = new ArrayList<>();
			while (date.compareTo(END_DATE) <= 0){
				int flog2 = 0;
				for (Map<String, Object> mapwl : listwlTemp) {
					if(mapwl.get("date").toString().equals(sdf2.format(date))){
						List<Map<String, Object>> listMa = (List<Map<String, Object>>) mapwl.get("materialList");
						List<Map<String, Object>> listMaExtra = new ArrayList<>();
						for (int i = 0; i < listNO.size(); i++) {
							String MATERIAL_NO = listNO.get(i);
							String MATERIAL_NAME = listName.get(i);
							int flog4 = 0;
							for (Map<String, Object> mapMa : listMa) {
								if(MATERIAL_NO.equals(mapMa.get("MATERIAL_NO").toString())){
									flog4 = 1;
									break;
								}
							}
							if(flog4 == 0){
								Map<String, Object> mapMaterial = new HashMap<>();
								mapMaterial.put("MATERIAL_NO", MATERIAL_NO);
								mapMaterial.put("MATERIAL_NAME", MATERIAL_NAME);
								mapMaterial.put("DEMAND_NUM", 0);
								listMaExtra.add(mapMaterial);
							}
						}
						listMa.addAll(listMaExtra);
						flog2 ++;
						break;
					}
				}
				if(flog2 == 0){
					Map<String, Object> mapwl = new HashMap<>();
					mapwl.put("date", sdf2.format(date));
					List<Map<String, Object>> materialList = new ArrayList<>();
					for (int i = 0; i < listNO.size(); i++) {
						String MATERIAL_NO = listNO.get(i);
						String MATERIAL_NAME = listName.get(i);
						Map<String, Object> mapMaterial = new HashMap<>();
						mapMaterial.put("MATERIAL_NO", MATERIAL_NO);
						mapMaterial.put("MATERIAL_NAME", MATERIAL_NAME);
						mapMaterial.put("DEMAND_NUM", 0);
						materialList.add(mapMaterial);
					}
					mapwl.put("materialList", materialList);
					listwlTempExtra.add(mapwl);
				}

				c.add(Calendar.DAY_OF_MONTH, 1);
				date=c.getTime();
			}
			System.out.println("------listwlTempExtra: " + listwlTempExtra);
			listwlTemp.addAll(listwlTempExtra);
			System.out.println("------listwlTemp: " + listwlTemp);
		}
		c.setTime(BEGIN_DATE);
		date = BEGIN_DATE;

		List<Map<String, Object>> materialDemand = new ArrayList<>();
		if(BEGIN_DATE.compareTo(END_DATE) <= 0){
			while (date.compareTo(END_DATE) <= 0){
				int flog3 = 0;
				for (Map<String, Object> mapwl : listwlTemp) {
					if(mapwl.get("date").toString().equals(sdf2.format(date))){
						List<Map<String, Object>> listMa = (List<Map<String, Object>>) mapwl.get("materialList");
						if(mapwl.get("date").toString().compareTo(sdf2.format(new Date())) >= 0){
							for (Map<String, Object> mapMa : listMa) {

								//查库存
								if(mapStock.get(mapMa.get("MATERIAL_NO").toString()) == null){
									List<Map<String, Object>> listStock = dao.getMaterialStock(mapMa);
									mapStock.put(mapMa.get("MATERIAL_NO").toString(), 0);
									if(listStock != null && listStock.size() > 0){
										mapStock.put(mapMa.get("MATERIAL_NO").toString(), listStock.get(0).get("sum"));
									}
									mapMa.put("STOCK_NUM", mapStock.get(mapMa.get("MATERIAL_NO").toString()));
								}else {
									Integer sum = Integer.parseInt(mapStock.get(mapMa.get("MATERIAL_NO").toString()).toString());
									Integer demand = Integer.parseInt(mapMa.get("DEMAND_NUM").toString());
									mapMa.put("STOCK_NUM", mapStock.get(mapMa.get("MATERIAL_NO").toString()));
									mapStock.put(mapMa.get("MATERIAL_NO").toString(), sum - demand);
								}

							}
						}
						else {
							//查今日之前
							for (Map<String, Object> mapMa : listMa) {

								//查库存
								Map<String, Object> mapM = new HashMap<>();
								mapM.put("date", date);
								mapM.put("MATERIAL_NO", mapMa.get("MATERIAL_NO"));
								Map<String, Object> mapInventory = dao.getInventoryByNo(mapM);
								if(mapInventory == null || mapInventory.get("MATERIAL_NUMBER") == null){
									mapMa.put("STOCK_NUM", 0);
								}else{
									mapMa.put("STOCK_NUM", mapInventory.get("MATERIAL_NUMBER"));
								}

							}
						}

						if(sdf.format(date).compareTo(beginTime) >= 0)
							materialDemand.add(mapwl);
						flog3=1;
						break;
					}
				}
				if(flog3 == 0){
					Map<String, Object> mapwl = new HashMap<>();
					mapwl.put("date", sdf2.format(date));
					mapwl.put("materialList", new ArrayList<>());
					if(sdf.format(date).compareTo(beginTime) >= 0)
						materialDemand.add(mapwl);
				}
				c.add(Calendar.DAY_OF_MONTH, 1);
				date=c.getTime();
			}
		}else{
			throw new Exception("开始时间不能大于截止时间");
		}

		StringBuffer materialStr = new StringBuffer();
		for (String str : listName) {
			materialStr.append(str).append(",");
		}

		if(materialStr.length() > 0){
			mapResult.put("materialStr", materialStr.substring(0, materialStr.length()-1));
		}else {
			mapResult.put("materialStr", "");
		}

		mapResult.put("materialDemand", materialDemand);
		return mapResult;
	}

	@Override
	public List<List<Map<String, Object>>> findMaterialInventory(Map<String, Object> map) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = sdf.parse(map.get("startDt").toString());
		Date endDt = sdf.parse(map.get("endDt").toString());
		String[] materialCodes = JSONArray.parseArray(map.get("materialCodes").toString()).toArray(new String[0]);
		StringBuffer tempMaterialCodes = new StringBuffer();
		for (String code : materialCodes) {
			tempMaterialCodes.append("'").append(code).append("',");
		}
		map.put("materialCode", tempMaterialCodes.substring(0, tempMaterialCodes.length() - 1));

		Date today = new Date();

		List<List<Map<String, Object>>> resultList = new ArrayList<>();

		// 开始时间不大于结束时间
		if (startDt.compareTo(endDt) <= 0) {

			Calendar calendar = Calendar.getInstance();
			// 查询历史库存
			if (endDt.compareTo(today) < 0) {
				// 获取历史库存
				List<Map<String, Object>> inventoryList = dao.getMaterialInventoryList(map);
				// 获取物料消耗
				List<Map<String, Object>> consumeList = dao.findAllConsume(map);
				// 获取产品产量
				List<Map<String, Object>> yieldList = dao.findAllYield(map);

				for (String code : materialCodes) {
					calendar.setTime(startDt);
					List<Map<String, Object>> invList = new ArrayList<>();
					while (calendar.getTime().compareTo(endDt) <= 0) {
						Date tempTime = calendar.getTime();
						// 最小单位记录内容
						Map<String, Object> invMap = new HashMap<>();
						invMap.put("dateTime", sdf.format(tempTime));
						invMap.put("materialCode", code);
						invMap.put("materialNumber", 0);
						invMap.put("consumeNumber", 0);
						invMap.put("yieldNumber", 0);

						//库存
						for (Map<String, Object> inventoryMap : inventoryList) {
							if (code.equals(inventoryMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(inventoryMap.get("INVENTORY_TIME").toString())) {
								invMap.put("materialNumber", inventoryMap.get("MATERIAL_NUMBER"));
								break;
							}
						}

						//物料消耗
						for (Map<String, Object> consumeMap : consumeList) {
							if(code.equals(consumeMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(consumeMap.get("PLAN_START_TIME").toString())){
								Integer consumeNum = Integer.parseInt(consumeMap.get("DEMAND_NUM").toString());
								invMap.put("consumeNumber", consumeNum);
								break;
							}
						}

						// 产品产量
						for (Map<String, Object> yieldMap : yieldList) {
							if(code.equals(yieldMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(yieldMap.get("PLAN_START_TIME").toString())){
								Integer yieldNum = Integer.parseInt(yieldMap.get("ORDER_NUMBER").toString());
								invMap.put("yieldNumber", yieldNum);
								break;
							}
						}

						invList.add(invMap);

						calendar.add(Calendar.DAY_OF_MONTH, 1);
					}

					// 添加到result
					resultList.add(invList);
				}

			} else {

				// 获取今日库存
				List<Map<String, Object>> inventoryList = dao.getMaterialInventoryList(map);
				map.put("startDt", sdf.format(today));
				// 获取物料消耗
				List<Map<String, Object>> consumeList = dao.findAllConsume(map);
				// 获取物料采购
				List<Map<String, Object>> purchaseList = dao.findAllPurchase(map);
				// 获取产品产量
				List<Map<String, Object>> yieldList = dao.findAllYield(map);

				for (String code : materialCodes) {
					// 查今日之后库存预测
					calendar.setTime(today);

					Integer currentInventory = 0;
					for (Map<String, Object> inventoryMap : inventoryList) {
						if(code.equals(inventoryMap.get("MATERIAL_NO").toString())){
							currentInventory = Integer.parseInt(inventoryMap.get("MATERIAL_NUMBER").toString());
							break;
						}
					}
					List<Map<String, Object>> invList = new ArrayList<>();
					while (sdf.format(calendar.getTime()).compareTo(sdf.format(endDt)) <= 0) {
						Date tempTime = calendar.getTime();

						Map<String, Object> invMap = new HashMap<>();
						invMap.put("dateTime", sdf.format(tempTime));
						invMap.put("materialCode", code);
						invMap.put("materialNumber", currentInventory.intValue());
						invMap.put("consumeNumber", 0);
						invMap.put("yieldNumber", 0);

						//物料消耗
						for (Map<String, Object> consumeMap : consumeList) {
							if(code.equals(consumeMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(consumeMap.get("PLAN_START_TIME").toString())){
								Integer consumeNum = Integer.parseInt(consumeMap.get("DEMAND_NUM").toString());
								currentInventory -= consumeNum;
								invMap.put("consumeNumber", consumeNum);
								break;
							}
						}

						// 物料采购
						for (Map<String, Object> purchaseMap : purchaseList) {
							if(code.equals(purchaseMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(purchaseMap.get("ARRIVAL_TIME").toString())){
								Integer purchaseNum = Integer.parseInt(purchaseMap.get("MATERIAL_NUM").toString());
								currentInventory += purchaseNum;
								break;
							}
						}

						// 产品产量
						for (Map<String, Object> yieldMap : yieldList) {
							if(code.equals(yieldMap.get("MATERIAL_NO").toString())
									&& sdf.format(tempTime).equals(yieldMap.get("PLAN_START_TIME").toString())){
								Integer yieldNum = Integer.parseInt(yieldMap.get("ORDER_NUMBER").toString());
								currentInventory += yieldNum;
								invMap.put("yieldNumber", yieldNum);
								break;
							}
						}

						if(tempTime.compareTo(startDt) >= 0){ // 只有不小于开始日期的内容才返回前端
							invList.add(invMap);
						}

						calendar.add(Calendar.DAY_OF_MONTH, 1);
					}

					// 添加到result
					resultList.add(invList);
				}
			}

		} else {
			throw new Exception("开始时间不能大于结束时间");
		}

		return resultList;
	}

	@Override
	public Map<String, Object> findOrderByIdOrCode(Map<String, Object> map) {
		return dao.findOrderByIdOrCode(map);
	}
	@Override
	public void materialInventory() {
		List<Map<String, Object>> list = dao.findAllInventory();
		Iterator<Map<String, Object>> iterator = list.iterator();
		while (iterator.hasNext()){
			Map<String, Object> map = iterator.next();
			Integer count = dao.getInventoryCount(map);
			if(count > 0){
				iterator.remove();
			}
		}
		dao.InsertInventory(list);

	}

	/**
	 * 根据产品名称查产品型号
	 */
	@Override
	public List<Map<String, Object>> findProductModelByName(Map<String, Object> map) {
		return dao.findProductModelByName(map);
	}

	@Override
	public List<Map<String, Object>> findListAll() {
		return dao.findListAll();
	}


}
