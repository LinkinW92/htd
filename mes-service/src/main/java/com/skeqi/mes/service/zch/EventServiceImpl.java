package com.skeqi.mes.service.zch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.EventDao;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao dao;

	/**
	 * 事件列表
	 */
	@Override
	public List<Map<String, Object>> findList(Map<String, Object> map) {
		return dao.findList(map);
	}

	@Override
	public List<Map<String, Object>> findEventIds(Map<String, Object> map) {
		return dao.findEventIds(map);
	}

	@Override
	public List<Map<String, Object>> findEventById(List<String> ids) {
		return dao.findEventById(ids);
	}

	@Override
	public Integer addEvent(Map<String, Object> map) {
		return dao.addEvent(map);
	}

	@Override
	public List<Map<String, Object>> findMaterialEventList(Map<String, Object> map) {
		return dao.findMaterialEventList(map);
	}

	@Override
	public List<Map<String, Object>> findMaterialEventListLike(Map<String, Object> map) {
		return dao.findMaterialEventListLike(map);
	}

	@Override
	public List<Map<String, Object>> findOrderEventByOrderId(Map<String, Object> map) {
		return dao.findOrderEventByOrderId(map);
	}

	@Override
	public List<Map<String, Object>> findWorkOrderByWorkOrderId(int WORK_ORDER_ID) {
		return dao.findWorkOrderByWorkOrderId(WORK_ORDER_ID);
	}

	@Override
	public Map<String, Object> getLineCode(Map<String, Object> map) {
		return dao.getLineCode(map);
	}

	@Override
	public Map<String, Object> getMaterialId(Map<String, Object> map) {
		return dao.getMaterialId(map);
	}

	@Override
	public Map<String, Object> getKeyPartMaterialId(Map<String, Object> map) {
		return dao.getKeyPartMaterialId(map);
	}

    @Override
    public List<Map<String, Object>> findWorkOrderByobjectId(List<String> sn) {
        return dao.findWorkOrderByobjectId(sn);
    }

	@Override
	public List<Map<String, Object>> findEventTimeShaft(Map<String, Object> map) {
		return dao.findEventTimeShaft(map);
	}

	@Override
	public List<Map<String, Object>> findMaterialEventLately() {
		return dao.findMaterialEventLately();
	}

	@Override
	public Map<String, Object> findMaterialEventStatisticsLately() {
		Map<String, Object> map = new HashMap<>();
		/*入库统计*/
		List<Map<String, Object>> warehousingList = dao.findWarehousingLately();
		/*产品生产*/
		List<Map<String, Object>> yieldList = dao.findYieldLately();
		/*原料消耗*/
		List<Map<String, Object>> consumeMaterial = dao.findConsumeMaterial();
		/*质检*/
		// TODO: 2021/1/26 物料首页质检暂时没有
		List<Map<String, Object>> qualityInspection = new ArrayList<>();
		map.put("warehousingList", warehousingList);
		map.put("yieldList", yieldList);
		map.put("consumeMaterial",consumeMaterial);
		map.put("qualityInspection",qualityInspection);
		return map;
	}

}
