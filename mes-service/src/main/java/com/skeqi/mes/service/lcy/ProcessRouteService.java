package com.skeqi.mes.service.lcy;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import org.apache.ibatis.annotations.Param;

/**
 * 宸ヨ壓璺嚎
 *
 * @author YinP
 *
 */
public interface ProcessRouteService {

	/**
	 * 鏌ヨ鎵�鏈変骇绾�
	 *
	 * @return
	 */
	List<CMesLineT> getLineList();

	/**
	 * 鏌ヨ鎵�灞炰骇绾胯窡鎵�灞炰骇鍝佷笅鏈夊灏戞潯 宸ヨ壓璺嚎鏁版嵁
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	int queryProcessRouteRoutingNumber(String lineID, String routingID,String productionId);
	/**
	 * 鏌ヨ鎵�灞炰骇绾胯窡鎵�灞炰骇鍝佷笅鏈夊灏戞潯 鐢熶骇鏂规硶鏁版嵁
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	int queryProcessRouteWayNumber( String routingID);


	/**
	 * 閫氳繃鎵�灞炰骇绾胯窡鎵�灞炰骇鍝� 鍒犻櫎鐢熶骇鏂规硶
	 *
	 * @param lineID
	 * @param routingID
	 */
	CMesRoutingT queryProcessRouteRoutingData(String lineID, String routingID);

	/**
	 * 閫氳繃鎵�灞炰骇绾胯窡鎵�灞炰骇鍝� 鏌ヨROUTE
	 *
	 * @param lineID
	 * @param routingID
	 * @return
	 */
	List<CMesStationT> queryStationList(String lineID);


	/**
	 * 鍒犻櫎宸ヨ壓璺嚎
	 * @param lineID
	 * @param routingID
	 */
	void deleteProcessRouteRoutingData(String lineID, String routingID);

	/***
	 * 鏍规嵁浜х嚎id 鑾峰彇宸ヨ壓璺嚎鍚嶇О鍜宨d
	 * @param processLineID
	 * @return
	 */
	List<CMesRoutingT> getRoutingByLineID(String processLineID,String productionId);

	List<CMesRoutingT> getRoutingByLineIDAndName(String id, String name);

	//淇濆瓨宸ヨ壓璺嚎
	JSONObject saveProcessWay(String lineID, String routingID, String processRouteData);
	//淇濆瓨routing
	int saveProcessRouting(String lineID, String routingName, String processRouteData,String productionId,String defaultRoute);

	void updateProcessRouteRoutingData(String lineID, String routingID, String processRouteData);

	void removeProcessRouting(String lineID, String routingID);

	List<CMesProductionT> getProductionByLineId();

	void updateDefaultProcess(String routingId, String lineId, String productionId);

	Object addRouting(Map<String, Object> map);

	Object updateRouting(Map<String, Object> map);

	List<Map<String, Object>> findRoutingList(Map<String, Object> map);

	Integer getRoutingCountByName(Map<String, Object> map);

	Integer deleteRouting(Map<String, Object> map);

	List<Map<String, Object>> getRouting(Map<String, Object> map);

	Integer findDefaultRouting(Map<String, Object> map);
}
