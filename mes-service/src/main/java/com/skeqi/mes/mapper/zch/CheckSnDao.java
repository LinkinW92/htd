package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.service.zch.CheckSnServiceImpl.BoltInfo;


public interface CheckSnDao {

	Map<String, Object> getStationByName(@Param("station") String station, @Param("lineId") Object lineId);

	Map<String, Object> getLineByName(@Param("line") String line);

	Map<String, Object> getPrintBySN(@Param("SN") String snBarcode, @Param("lineId") Object lineId);

	List<Map<String, Object>> findProductionOffline();

	Map<String, Object> getDefaultRouting(@Param("productionId") Object productionId, @Param("lineId") Object lineId);

	Map<String, Object> getDefaultTotalRecipe(@Param("productionId") Object productionId, @Param("lineId") Object lineId);

	Map<String, Object> getWorkorderById(@Param("workorderId") Object workorderId);

	Integer getSerialMin(@Param("routingId") Object routingId);

	Integer getSerialNoByStation(@Param("routingId") Object routingId, @Param("stationId") Object stationId);

	Integer getTrackingCountP(@Param("SN") String snBarcode, @Param("lineId") Object lineId);

	Integer getTrackingCountR(@Param("SN") String snBarcode, @Param("lineId") Object lineId);

	Integer getFlagCount(@Param("SN") String snBarcode, @Param("serialMin") Integer serialMin);

	Integer getSerialFlagCount(@Param("SN") String snBarcode);

	List<Map<String, Object>> queryStationWayList(@Param("SN") String snBarcode, @Param("routingId") Object routingId);

	Integer insertStationDerialFlag(@Param("list") List<Map<String, Object>> list);

	Integer insertTracking(@Param("station")String station,@Param("snBarcode")String snBarcode,
			@Param("tempStationIndex")Object tempStationIndex,
			@Param("tempTrackingProductionId")Object tempTrackingProductionId,
			@Param("tempStationLineId")Object tempStationLineId,
			@Param("tempWorkOrderId")Object tempWorkOrderId,
			@Param("totalRecipeId")Object totalRecipeId);

	Integer insertStationPass(@Param("snBarcode")String snBarcode, @Param("tempStationName")String tempStationName, @Param("tempStationLineId")Object tempStationLineId);

	Integer updateOnlineNumber(@Param("workorderId") Object workorderId);

	Integer getSerialTotalCount(@Param("SN") String snBarcode, @Param("serialNo") Integer serialNo);

	Integer getSerialCompleteCount(@Param("SN") String snBarcode, @Param("serialNo") Integer serialNo);

	List<String> findOfflineStationName(@Param("routingId") Object routingId);

	Integer findBoltCount(@Param("stationName") String stationName, @Param("SN") String snBarcode);

	List<Map<String, Object>> findRecipeDetails(@Param("totalRecipeId") Object totalRecipeId, @Param("stationId") Object stationId);

	Integer getUnUsedMaterial(@Param("MATERIAL_NAME")String MATERIAL_NAME);

	Integer updateStateByID(@Param("ID") Integer ID);

	Integer insertBoltData(List<BoltInfo> boltInfos);

	Integer findKeypartCount(@Param("stationName") String stationName, @Param("SN") String snBarcode);

	Integer findWeightCount(@Param("stationName") String stationName, @Param("SN") String snBarcode);

	Integer findleakageCount(@Param("stationName") String stationName, @Param("SN") String snBarcode);

	Integer insertMaterial(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")Object cRecipesMaterialName,@Param("cRecipesMaterialpn")Object cRecipesMaterialpn,@Param("step")String step);

	Integer insertUserInput(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")Object cRecipesMaterialName,@Param("cRecipesMaterialpn")Object cRecipesMaterialpn);

	Integer insertLeakage(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")Object cRecipesMaterialName,@Param("cRecipesMaterialpn")Object cRecipesMaterialpn,@Param("step")String step);

	Integer insertWeight(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")Object cRecipesMaterialName,@Param("cRecipesMaterialpn")Object cRecipesMaterialpn);

	Integer insertSecondary(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")Object cRecipesMaterialName,@Param("cRecipesMaterialpn")Object cRecipesMaterialpn, @Param("MATERIAL_INSTANCE_ID")Integer MATERIAL_INSTANCE_ID);

	List<Map<String, Object>> findStepListOffline(@Param("SN") String snBarcode, @Param("stationName") String station, @Param("productionId") Object productionId);

	List<Map<String, Object>> findStepListOnline(@Param("sn") String snBarcode, @Param("stationName") String station, @Param("productionId") Object productionId);

	Map<String, Object> getStep(@Param("SN") String snBarcode, @Param("lineName") String line, @Param("stationName") String station);

	Integer insertStep(@Param("SN") String snBarcode, @Param("lineName") String line, @Param("stationName") String station);

	Integer updateStep(@Param("SN") String snBarcode, @Param("lineName") String line, @Param("stationName") String station, @Param("stepNo") Integer stepNo);

	Integer updateStepEmp(@Param("SN") String snBarcode, @Param("lineName") String line, @Param("stationName") String station, @Param("stepNo") Integer stepNo, @Param("emp") String emp);

}
