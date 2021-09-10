package com.skeqi.mes.mapper.lcy;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;

/**
 * 工艺路线
 *
 * @author YinP
 *
 */
public interface ProcessRouteDao {
	/**
	 * 查询所有产线
	 *
	 * @return
	 */
	List<CMesLineT> getLineList();

	/***
	 * 根据产线id获取工艺路线name
	 *
	 * @param lineID
	 * @return
	 */
	List<CMesRoutingT> getRoutingByLineID(@Param("lineID") String lineID, @Param("productionId") String productionId);


	List<CMesRoutingT> getRoutingByLineIDAndName(@Param("id") String id, @Param("name") String name);



	/***
	 * 新增工艺路线routing
	 *
	 * @param lineID
	 * @param routingID
	 * @param routingName
	 * @param processRouteData
	 */
	void saveProcessRouting(@Param("lineID") String lineID, @Param("routingName") String routingName,
			@Param("processRouteData") String processRouteData, @Param("productionId") String productionId,
			@Param("defaultRoute") String defaultRoute);

	/***
	 * 新增工艺路线productionWay
	 *
	 * @param routingID
	 * @param lineID
	 * @param stationID
	 * @param serialNo
	 */
	void saveProcessWay(@Param("routingID") String routingID, @Param("lineID") String lineID,
			@Param("stationID") String stationID, @Param("serialNo") int serialNo);

	/**
	 * 通过所属产线跟所属产品 删除工艺路线
	 *
	 * @param lineID
	 * @param routingID
	 */
	void deleteProcessRouting(@Param("lineID") String lineID, @Param("routingID") String routingID);

	/**
	 * 通过所属产线跟所属产品 删除删除方法
	 *
	 * @param lineID
	 * @param routingID
	 */
	void deleteProcessWay(@Param("lineID") String lineID, @Param("routingID") String routingID);

	/******************************************************************************
	 *
	 *
	 *
	 *
	 *
	 * /** 查询所属产线跟所属产品下有多少条 工艺路线数据
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	int queryProcessRouteRoutingNumber(@Param("lineID") String lineID, @Param("routingID") String routingID,
			@Param("productionId") String productionId);

	/**
	 * 查询所属产线跟所属产品下有多少条 生产方法数据
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	int queryProcessRouteWayNumber(@Param("routingID") String routingID);

	/**
	 * 通过所属产线跟所属产品 查询ROUTE
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	CMesRoutingT queryProcessRouteWayData(@Param("lineID") String lineID, @Param("routingID") String routingID);

	/**
	 * 通过查询查询工位
	 *
	 * @param lineID
	 * @return
	 */
	List<CMesStationT> queryStationList(@Param("lineID") String lineID);

	int queryProcessRoutingID(@Param("lineID") String lineID, @Param("routingName") String routingName,
			@Param("productionId") String productionId);

	void updateProcessRouteRoutingData(@Param("routingID") String routingID,
			@Param("processRouteData") String processRouteData);

	void removeProcessRouting(@Param("lineID") String lineID, @Param("routingID") String routingID);

	List<CMesProductionT> getProductionByLineId();

	void cleanAllDefaultProcess(@Param("lineID") String lineID, @Param("productionId") String productionId);

	void updateDefaultProcess(@Param("routingId") String routingId);

	Integer addRouting(Map<String, Object> map);

	Integer updateRouting(Map<String, Object> map);

	List<Map<String, Object>> findRoutingList(Map<String, Object> map);

	Integer getRoutingCountByName(Map<String, Object> map);

	Integer addProductionWay(@Param("list") List<Map<String, Object>> list);

	Integer deleteRouting(Map<String, Object> map);

	Integer deleteWay(Map<String, Object> map);

	List<Map<String, Object>> getRouting(Map<String, Object> map);

	Integer findDefaultRouting(Map<String, Object> map);

}
