package com.skeqi.mes.service.sqq;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;

public interface RouteLineService {

	public List<JSONObject> findAllStation(Integer lineId);

	public List<JSONObject> findAllLine();

	public List<JSONObject> findAllProduction();

	public List<JSONObject> findAllRoute(Integer lineId,Integer productionId);

	public List<JSONObject> findRoute(Integer lineId,Integer productionId,Integer routeId);

	//public void addRoute(Integer lineId,Integer productionId,String name,String json,Integer status);

	public void deleteRoute (Integer routeId);

	public void updateRoute(Integer routeId,String json);

	public Integer updatestatus(@Param("lined")Integer lineId,@Param("pid")Integer productionId);

	void addRoute(CMesRoutingT t);

	public Integer findStationId(String id);

	public Integer addProductionWay(int sid, Integer rid, int i);

    List<JSONObject> findRouteById(Integer routeId);

	List<JSONObject> findRoutingIdAndName(Integer id, String name,Integer lineId);
}
