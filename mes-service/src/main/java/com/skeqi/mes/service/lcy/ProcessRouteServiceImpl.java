package com.skeqi.mes.service.lcy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.lcy.ProcessRouteDao;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesProductionWayStation;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class ProcessRouteServiceImpl implements ProcessRouteService {

	@Autowired
	ProcessRouteDao dao;


	@Override
	public List<CMesLineT> getLineList() {
		return dao.getLineList();
	}

	@Override
	public int queryProcessRouteRoutingNumber(String lineID, String routingID,String productionId) {
		return dao.queryProcessRouteRoutingNumber(lineID, routingID,productionId);
	}

	@Override
	public int queryProcessRouteWayNumber(String routingID) {
		return dao.queryProcessRouteWayNumber(routingID);
	}


	@Override
	public CMesRoutingT queryProcessRouteRoutingData(String lineID, String routingID) {
		return dao.queryProcessRouteWayData(lineID, routingID);
	}

	@Override
	public List<CMesStationT> queryStationList(String lineID) {

		List<CMesStationT> stationList = dao.queryStationList(lineID);
		return stationList;
	}



	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject saveProcessWay(String lineID, String routingID, String processRouteData) {

		JSONObject jo = new JSONObject();

		JSONObject processRouteJSONData = (JSONObject) JSONObject.parse(processRouteData);

		JSONObject paths = (JSONObject) processRouteJSONData.get("paths");
		JSONObject states = (JSONObject) processRouteJSONData.get("states");

		List<String> pathList = new ArrayList<String>();

		List<String> rectList = new ArrayList<String>();

		net.sf.json.JSONObject pathsJson = net.sf.json.JSONObject.fromObject(paths.toString());
		net.sf.json.JSONObject statesJson = net.sf.json.JSONObject.fromObject(states.toString());

		Iterator pathsIterator = pathsJson.keys();
		Iterator statesIterator = statesJson.keys();

		String startRect = null;
		String endRect = null;
		while (statesIterator.hasNext()) {
			String key = (String) statesIterator.next().toString();
			rectList.add(key);

			if ("start".equals(((JSONObject) states.get(key)).get("type"))) {
				// 获取开始的rect
				startRect = key;
			}
			if ("end".equals(((JSONObject) states.get(key)).get("type"))) {
				// 获取结束的rect
				endRect = key;
			}
		}

		while (pathsIterator.hasNext()) {
			String key = (String) pathsIterator.next().toString();
			pathList.add(key);
		}

		Set<String> fromSet = new HashSet<String>();

		Set<String> toSet = new HashSet<String>();

		for (int i = 0; i < pathList.size(); i++) {
			fromSet.add((String) (((JSONObject) (paths.get(pathList.get(i)))).get("from")));
			toSet.add((String) (((JSONObject) (paths.get(pathList.get(i)))).get("to")));
		}

		// 如果数目和pathList 对不上 说明 工艺路线连接有问题
		if ((fromSet.size() != pathList.size()) || (toSet.size() != pathList.size())) {

			dao.deleteProcessRouting(lineID, routingID);
			dao.deleteProcessWay(lineID, routingID);
			jo.put("flag", 0);
			jo.put("msg", "工艺路线连接有误");
			return jo;

		}

		//
		if(!toSet.contains(endRect)) {

			dao.deleteProcessRouting(lineID, routingID);
			dao.deleteProcessWay(lineID, routingID);
			jo.put("flag", 0);
			jo.put("msg", "必须以end为工艺路线的结束");
			return jo;

		}

		if(!fromSet.contains(startRect)) {
			dao.deleteProcessRouting(lineID, routingID);
			dao.deleteProcessWay(lineID, routingID);
			jo.put("flag", 0);
			jo.put("msg", "必须以start为工艺路线的开始");
			return jo;

		}


		for (String string : toSet) {


			if (startRect.equals(string)) {
				dao.deleteProcessRouting(lineID, routingID);
				dao.deleteProcessWay(lineID, routingID);
				jo.put("flag", 0);
				jo.put("msg", "必须以start为工艺路线的开始");
				return jo;
			}
		}



		for (String string : fromSet) {

			if (endRect.equals(string)) {
				dao.deleteProcessRouting(lineID, routingID);
				dao.deleteProcessWay(lineID, routingID);

				jo.put("flag", 0);
				jo.put("msg", "必须以end为工艺路线的结束");
				return jo;
			}

		}

		// 说明没有连线
		if (pathList.size() == 0) {

			dao.deleteProcessRouting(lineID, routingID);
			dao.deleteProcessWay(lineID, routingID);
			jo.put("flag", 0);
			jo.put("msg", "工艺路线没有连接,无法保存");
			return jo;
		}

		List<CMesProductionWayStation> productionWayList = new ArrayList<>();

		CMesProductionWayStation startCMesProductionWayStation = new CMesProductionWayStation();

		startCMesProductionWayStation.setType("start");

		startCMesProductionWayStation.setID(1);

		startCMesProductionWayStation.setStationID("-1");

		startCMesProductionWayStation.setRectNum(startRect);

		// ((JSONObject)(((JSONObject)states.get(startRect)).get("text"))).get("text");

		startCMesProductionWayStation.setStationName("start");

		productionWayList.add(startCMesProductionWayStation);

		String nowProductionWayStation = startRect;

		for (int i = 0; i < pathList.size(); i++) {

			for (int j = 0; j < pathList.size(); j++) {

				if (nowProductionWayStation
						.equals((String) (((JSONObject) (paths.get(pathList.get(j)))).get("from")))) {

					nowProductionWayStation = (String) (((JSONObject) (paths.get(pathList.get(j)))).get("to"));

					if (endRect.equals(nowProductionWayStation)) {

						CMesProductionWayStation endCMesProductionWayStation = new CMesProductionWayStation();

						endCMesProductionWayStation.setType("end");

						endCMesProductionWayStation.setID((pathList.size()) + 2);

						endCMesProductionWayStation.setStationID("-2");

						endCMesProductionWayStation.setRectNum(endRect);

						// ((JSONObject)(((JSONObject)states.get(startRect)).get("text"))).get("text");

						endCMesProductionWayStation.setStationName("end");

						productionWayList.add(endCMesProductionWayStation);

						break;
					} else {

						CMesProductionWayStation stationCMesProductionWayStation = new CMesProductionWayStation();

						stationCMesProductionWayStation.setID(i + 2);
						stationCMesProductionWayStation.setRectNum(nowProductionWayStation);

						stationCMesProductionWayStation.setStationName(
								(String) ((JSONObject) (((JSONObject) (states.get(nowProductionWayStation)))
										.get("text"))).get("text"));
						stationCMesProductionWayStation
								.setStationID((String) (((JSONObject) (((JSONObject) (((JSONObject) (states
										.get(nowProductionWayStation))).get("props"))).get("assignee"))).get("value")));
						stationCMesProductionWayStation
								.setType((String) (((JSONObject) (states.get(nowProductionWayStation))).get("type")));
						productionWayList.add(stationCMesProductionWayStation);
						break;

					}

				}
			}
		}
		for (int i = 0; i < productionWayList.size(); i++) {
        	if("start".equals(productionWayList.get(i).getType())) {

        		productionWayList.remove(i);

        	}else if("end".equals(productionWayList.get(i).getType())) {

        		productionWayList.remove(i);

        	}
		}

        //由小到大 排序完成
        Collections.sort(productionWayList);

        dao.deleteProcessWay(lineID, routingID);

        for (int i = 0; i < productionWayList.size(); i++) {
        	productionWayList.get(i).setSerialNo(i+1);
        	dao.saveProcessWay(routingID,lineID,productionWayList.get(i).getStationID(), (i+1));
		}

        jo.put("flag",1);
        jo.put("msg","保存成功");


		return jo;

	}

	@Override
	public void deleteProcessRouteRoutingData(String lineID, String routingID) {
		dao.deleteProcessRouting(lineID, routingID);
		dao.deleteProcessWay(lineID, routingID);
	}

	@Override
	public List<CMesRoutingT> getRoutingByLineID(String processLineID,String productionId) {
		return dao.getRoutingByLineID(processLineID,productionId);
	}


	@Override
	public int saveProcessRouting(String lineID, String routingName, String processRouteData,String productionId,String defaultRoute) {
		dao.saveProcessRouting(lineID, routingName,processRouteData,productionId,defaultRoute);
		return dao.queryProcessRoutingID(lineID,routingName,productionId);
	}

	@Override
	public void updateProcessRouteRoutingData(String lineID, String routingID, String processRouteData) {
		dao.updateProcessRouteRoutingData(routingID,processRouteData);

	}


	@Override
	public void removeProcessRouting(String lineID, String routingID){

		dao.removeProcessRouting(lineID,routingID);


	}

	@Override
	public List<CMesProductionT> getProductionByLineId() {
		return dao.getProductionByLineId();
	}

	@Override
	public void updateDefaultProcess(String routingId, String lineId, String productionId) {

		dao.cleanAllDefaultProcess(lineId,productionId);
		dao.updateDefaultProcess(routingId);

	}

	@Override
	public Object addRouting(Map<String, Object> map) {
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = JSONArray.parseObject(map.get("stepList").toString(),List.class);
		dao.addRouting(map);
		for (Map<String, Object> mapStep : list) {
			mapStep.put("ID", map.get("ID"));
		}
		dao.addProductionWay(list);
		return map.get("ID");
	}

	@Override
	public Object updateRouting(Map<String, Object> map) {
		dao.deleteWay(map);
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> list = JSONArray.parseObject(map.get("stepList").toString(),List.class);
		for (Map<String, Object> mapStep : list) {
			mapStep.put("ID", map.get("ID"));
		}
		dao.addProductionWay(list);
		return dao.updateRouting(map);
	}

	@Override
	public List<Map<String, Object>> findRoutingList(Map<String, Object> map) {
		return dao.findRoutingList(map);
	}

	@Override
	public Integer getRoutingCountByName(Map<String, Object> map) {
		return dao.getRoutingCountByName(map);
	}

	@Override
	public Integer deleteRouting(Map<String, Object> map) {
		dao.deleteRouting(map);
		return dao.deleteWay(map);
	}

	@Override
	public List<Map<String, Object>> getRouting(Map<String, Object> map) {
		return dao.getRouting(map);
	}

	@Override
	public Integer findDefaultRouting(Map<String, Object> map) {
		return dao.findDefaultRouting(map);
	}

	@Override
	public List<CMesRoutingT> getRoutingByLineIDAndName(String id, String name) {
		return dao.getRoutingByLineIDAndName(id,name);
	}
}
