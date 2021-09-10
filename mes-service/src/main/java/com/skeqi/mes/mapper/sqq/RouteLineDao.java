package com.skeqi.mes.mapper.sqq;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;

public interface RouteLineDao {
	// 根据产线id查询所有的工位
	public List<JSONObject> findAllStation(@Param("lineId")Integer lineId);
	// 查询所有的产线
	public List<JSONObject> findAllLine();
	// 查询所有的产品
	public List<JSONObject> findAllProduction();
	// 查询所有的工艺路线
	public List<JSONObject> findAllRoute(@Param("lineId")Integer lineId,@Param("productionId")Integer productionId);

	// 查询工艺路线
	public List<JSONObject> findRoute(@Param("lineId")Integer lineId,@Param("productionId")Integer productionId,@Param("routeId")Integer routeId);

	// 添加工艺路线
//	public void addRoute(@Param("lineId")Integer lineId,@Param("productionId")Integer productionId,@Param("name")String name,@Param("json")String json,@Param("status")Integer status);

	// 删除工艺路线,
	void deleteRoute(@Param("routeId") Integer routeId);


	// 修改工艺路线
	public void updateRoute(@Param("routeId")Integer routeId,@Param("json")String json);

	//更改默认工艺路线
	public Integer updatestatus(@Param("lined")Integer lineId,@Param("pid")Integer productionId);

	@Select("SELECT COUNT(*) from  c_mes_station_t  WHERE ID=#{id}")
	public Integer findStationId(@Param("id")String id);

	@Insert("insert into c_mes_production_way_t(DT,ST_ID,ROUTING_ID,SERIAL_NO) value(now(),#{sid},#{rid},#{i})")
	public Integer addProductionWay(@Param("sid")int sid,@Param("rid") Integer rid,@Param("i") int i);
	// 添加工艺路线
	public void addRoute(CMesRoutingT t);

    List<JSONObject> findRouteById(Integer routeId);

    List<JSONObject> findRoutingIdAndName(@Param("id") Integer id,@Param("name") String name,@Param("lineId") Integer lineId);

}
