package com.skeqi.mes.service.sqq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.skeqi.mes.mapper.sqq.RouteLineDao;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class RouteLineServiceImpl implements RouteLineService {

	@Autowired
	RouteLineDao dao;

	@Override
	public List<JSONObject> findAllStation(Integer lineId) {
		return dao.findAllStation(lineId);
	}

	@Override
	public List<JSONObject> findAllLine() {
		return dao.findAllLine();
	}

	@Override
	public List<JSONObject> findAllProduction() {
		return dao.findAllProduction();
	}

	@Override
	public List<JSONObject> findAllRoute(Integer lineId,Integer ProductionId) {
		return dao.findAllRoute(lineId, ProductionId);
	}

	@Override
	public List<JSONObject> findRoute(Integer lineId,Integer ProductionId,Integer RouteId) {
		return dao.findRoute(lineId, ProductionId, RouteId);
	}

	@Override
	public void addRoute(CMesRoutingT t) {
//		Integer lineId = t.getLineId();
//		Integer productionId = t.getProductionId();
//		String name = t.getName();
//		String json = t.getJson();
//		Integer status = t.getStatus();

		 dao.addRoute(t);
	}

	@Override
	public void deleteRoute (Integer routeId) {
		 dao.deleteRoute(routeId);
	}

	@Override
	public void updateRoute(Integer routeId,String json) {
		 dao.updateRoute(routeId,json);
	}

	@Override
	public Integer updatestatus(Integer lineId, Integer productionId) {
		// TODO Auto-generated method stub
		return dao.updatestatus(lineId, productionId);
	}

	public static void main(String[] args) {
		String str = "{\"name\":\"工位\",\"nodeList\":[{\"id\":\"start\",\"name\":\"开始\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"40px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"end\",\"name\":\"结束\",\"type\":\"task\",\"left\":\"458px\",\"top\":\"64px\",\"ico\":\"el-icon-goods\"},{\"id\":\"309\",\"name\":\"油压机1\",\"type\":\"task\",\"left\":\"360px\",\"top\":\"184px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"310\",\"name\":\"油压机2\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"120px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"311\",\"name\":\"油压机3\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"160px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"312\",\"name\":\"油压机4\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"200px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"320\",\"name\":\"下油尽\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"240px\",\"ico\":\"el-icon-user-solid\"},{\"id\":\"334\",\"name\":\"staion1\",\"type\":\"task\",\"left\":\"40px\",\"top\":\"280px\",\"ico\":\"el-icon-user-solid\"}],\"lineList\":[{\"from\":\"start\",\"to\":\"310\"}]}";
		JSONObject json = JSONObject.parseObject(str);

		System.out.println(json.get("lineList"));

	}

	@Override
	public Integer findStationId(String id) {
		// TODO Auto-generated method stub
		return dao.findStationId( id);
	}

	@Override
	public Integer addProductionWay(int sid, Integer rid, int i) {
		// TODO Auto-generated method stub
		return dao.addProductionWay( sid,  rid,  i);
	}

	@Override
	public List<JSONObject> findRouteById(Integer routeId) {
		return dao.findRouteById(routeId);
	}

	@Override
	public List<JSONObject> findRoutingIdAndName(Integer id, String name,Integer lineId) {
		return dao.findRoutingIdAndName(id,name,lineId);
	}
}
